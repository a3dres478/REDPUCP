using RedPucpWeb.RedPucpWS;
using RedPucpWeb.Servicios;
using RedPucpWeb.utils;
using System;
using System.Linq;
using System.Web.UI.WebControls;

namespace RedPucpWeb
{
    public partial class plantillaDetallePublicacion : System.Web.UI.Page
    {
        private int idAutorPublicacion = -1;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarPublicacion();
                CargarComentarios();
            }
        }

        private int ObtenerIdPublicacion()
        {
            string idStr = Request.QueryString["id"];
            int.TryParse(idStr, out int idPubli);
            return idPubli;
        }

        private usuarioComun ObtenerUsuarioActual()
        {
            return Session["UsuarioCompleto"] as usuarioComun;
        }

        private int ObtenerIdUsuarioActual()
        {
            var usuario = ObtenerUsuarioActual();
            if (usuario == null) return 0;
            return usuario.idUsuario;
        }

        private void CargarPublicacion()
        {
            try
            {
                int idPubli = ObtenerIdPublicacion();
                if (idPubli == 0)
                {
                    lblMensaje.Text = "ID inválido.";
                    return;
                }

                hfIdPublicacion.Value = idPubli.ToString();

                using (var cliente = new PublicacionWSClient())
                {
                    var publi = cliente.obtenerPublicacion(idPubli);

                    if (publi == null)
                    {
                        lblMensaje.Text = "No se encontró la publicación.";
                        return;
                    }

                    lblTitulo.Text = publi.titulo;
                    lblDescripcion.Text = publi.descripcion;
                    lblFecha.Text = publi.fechaCreacion.ToString("dd/MM/yyyy");

                    if (publi.comunidad != null)
                        lblComunidad.Text = publi.comunidad.nombre;

                    if (publi.autor != null) {
                        lblAutor.Text = publi.autor.nombre;
                        idAutorPublicacion = publi.autor.idUsuario; 
                    }

                    // Imagen
                    if (!string.IsNullOrEmpty(publi.imagenUrl))
                    {
                        imgPublicacion.ImageUrl = publi.imagenUrl;
                        imgPublicacion.Visible = true;
                    }
                    else
                    {
                        imgPublicacion.Visible = false;
                    }

                    // VOTOS 
                    lblUpvotes.Text = publi.votosPositivos.ToString();
                    lblDownvotes.Text = publi.votosNegativos.ToString();
                }
            }
            catch (System.Exception ex)
            {
                lblMensaje.Text = "Error cargando publicación: " + ex.Message;
            }
        }

        /* ===================== COMENTARIOS ===================== */

        private void CargarComentarios()
        {
            try
            {
                int idPubli = ObtenerIdPublicacion();
                if (idPubli == 0) return;

                using (var cliente = new ComentarioWSClient())
                {
                    //
                    var comentariosPublicacion = cliente.listarComentariosXPublicacion(idPubli);

                    if (comentariosPublicacion == null || comentariosPublicacion.Length == 0)
                    {
                        lblSinComentarios.Visible = true;
                        rptComentarios.Visible = false;
                    }
                    else
                    {
                        lblSinComentarios.Visible = false;
                        rptComentarios.Visible = true;
                        rptComentarios.DataSource = comentariosPublicacion;
                        rptComentarios.DataBind();
                    }
                }
            }
            catch (System.Exception ex)
            {
                lblMensaje.Text = "Error cargando los comentarios: " + ex.Message;
            }
        }

        protected void btnComentar_Click(object sender, EventArgs e)
        {
            try
            {
                string comentarioTexto = txtComentario.InnerText.Trim();
                if (string.IsNullOrWhiteSpace(comentarioTexto))
                {
                    lblMensaje.Text = "El comentario no puede estar vacío.";
                    return;
                }

                int idPubli = ObtenerIdPublicacion();
                if (idPubli == 0)
                {
                    lblMensaje.Text = "ID de publicación inválido.";
                    return;
                }

                int idUsuarioComentador = ObtenerIdUsuarioActual();
                if (idUsuarioComentador == 0)
                {
                    lblMensaje.Text = "Debes iniciar sesión para comentar.";
                    return;
                }

                // 1. Subir imagen usando el servicio modularizado
                string imagenSubida = null;
                if (fuImagen.HasFile)
                {
                    var uploadService = new ImageUploadService();
                    var uploadResult = uploadService.UploadImage(fuImagen.PostedFile);

                    if (!uploadResult.Success)
                    {
                        // Manejo específico del error 413 (RF008)
                        if (uploadResult.ErrorCode == 413)
                        {
                            lblMensaje.CssClass = "text-warning";
                            lblMensaje.Text = $"❌ {uploadResult.ErrorMessage}";
                        }
                        else
                        {
                            lblMensaje.Text = $"❌ {uploadResult.ErrorMessage}";
                        }
                        return;
                    }

                    imagenSubida = uploadResult.ImageUrl;
                }

                var nuevoComentario = new comentario
                {
                    autor = new usuarioComun { idUsuario= idUsuarioComentador },
                    publicacion = new publicacion { id = idPubli },
                    contenido = comentarioTexto,
                    imagenUrl = imagenSubida
                };


                using (var cliente = new ComentarioWSClient())
                {
                    cliente.guardarComentario(nuevoComentario, estado.Nuevo);
                    Karma.AplicarKarma(idUsuarioComentador,Karma.AccionKarma.Comentar);
                    if (idAutorPublicacion != -1) { 
                        Karma.AplicarKarma(idAutorPublicacion,Karma.AccionKarma.PublicacionComentada);
                    }
                }

                txtComentario.InnerText = string.Empty;
                CargarComentarios();
            }
            catch (System.Exception ex)
            {
                lblMensaje.Text = "Error al guardar el comentario: " + ex.Message;
            }
        }

        /* ===================== VOTOS DE COMENTARIOS ===================== */

        protected void rptComentarios_ItemCommand(object source, RepeaterCommandEventArgs e)
        {
            if (e.CommandName == "Upvote" || e.CommandName == "Downvote" || e.CommandName == "QuitarVoto")
            {
                int idComentario = Convert.ToInt32(e.CommandArgument);
                int idUsuario = ObtenerIdUsuarioActual();

                if (idUsuario == 0)
                {
                    lblMensaje.Text = "Debes iniciar sesión para votar.";
                    return;
                }

                try
                {
                    using (var cliente = new VotoComentarioWSClient())
                    {
                        if (e.CommandName == "Upvote")
                        {
                            var voto = new votoComentario
                            {
                                comentarioVotado = new comentario { id = idComentario },
                                usuario = new usuarioComun { idUsuario = idUsuario },
                                tipo = TipoVoto.ObtenerCaracter(TipoVoto.Valor.Up)
                            };
                            cliente.guardarVotoComentario(voto, estado.Nuevo);

                            // Aplicar karma al autor del comentario por recibir upvote
                            AplicarKarmaAutorComentario(idComentario, Karma.AccionKarma.ComentarioVotadoUp);
                        }
                        else if (e.CommandName == "Downvote")
                        {
                            var voto = new votoComentario
                            {
                                comentarioVotado = new comentario { id = idComentario },
                                usuario = new usuarioComun { idUsuario = idUsuario },
                                tipo = TipoVoto.ObtenerCaracter(TipoVoto.Valor.Down)
                            };
                            cliente.guardarVotoComentario(voto, estado.Nuevo);
                            // Aplicar karma al autor del comentario por recibir downvote
                            AplicarKarmaAutorComentario(idComentario, Karma.AccionKarma.ComentarioVotadoDown);
                        }
                        else if (e.CommandName == "QuitarVoto")
                        {
                            cliente.suprimirVotoComentario(idUsuario, idComentario);
                        }
                    }

                    // Recargar los comentarios para actualizar los contadores
                    CargarComentarios();
                }
                catch (System.Exception ex)
                {
                    lblMensaje.Text = "Error al procesar el voto: " + ex.Message;
                }
            }
        }

        private void AplicarKarmaAutorComentario(int idComentario, Karma.AccionKarma accion)
        {
            try
            {
                // Obtener el autor del comentario para aplicar karma
                using (var cliente = new ComentarioWSClient())
                {
                    var comentario = cliente.obtenerComentario(idComentario);
                    if (comentario?.autor != null)
                    {
                        Karma.AplicarKarma(comentario.autor.idUsuario, accion);
                    }
                }
            }
            catch (System.Exception ex)
            {
                // Log silencioso - no interrumpir el flujo principal
                System.Diagnostics.Debug.WriteLine($"Error aplicando karma a autor de comentario: {ex.Message}");
            }
        }

        /* ===================== VOTOS ===================== */

        protected void btnUpvote_Click(object sender, EventArgs e)
        {
            RegistrarVoto(TipoVoto.ObtenerCaracter(TipoVoto.Valor.Up));   // U = Upvote
        }

        protected void btnDownvote_Click(object sender, EventArgs e)
        {
            RegistrarVoto(TipoVoto.ObtenerCaracter(TipoVoto.Valor.Down));   // D = Downvote
        }

        protected void btnQuitarVoto_Click(object sender, EventArgs e) 
        {
            try
            {
                int idPubli = ObtenerIdPublicacion();
                if (idPubli == 0)
                {
                    lblMensaje.Text = "ID de publicación inválido.";
                    return;
                }

                int idUsuario = ObtenerIdUsuarioActual();
                if (idUsuario == 0)
                {
                    lblMensaje.Text = "Debes iniciar sesión para quitar tu voto.";
                    return;
                }
                using (var cliente = new VotoPublicacionWSClient())
                {
                    cliente.suprimirVotoPublicacion(idUsuario, idPubli);
                }
                // Refresca los datos (contadores de votos)
                CargarPublicacion();
            }
            catch (System.Exception ex)
            {
                lblMensaje.Text = "Error al eliminar el voto: " + ex.Message;
            }
        }

        private void RegistrarVoto(char tipoVoto)
        {
            try
            {
                int idPubli = ObtenerIdPublicacion();
                if (idPubli == 0)
                {
                    lblMensaje.Text = "ID de publicación inválido.";
                    return;
                }

                int idUsuario = ObtenerIdUsuarioActual();
                if (idUsuario == 0)
                {
                    lblMensaje.Text = "Debes iniciar sesión para votar.";
                    return;
                }

                var voto = new votoPublicacion
                {
                    publicacionVotada = new publicacion { id = idPubli },
                    usuario = new usuarioComun { idUsuario = idUsuario },
                    tipo = tipoVoto
                };


                using (var cliente = new VotoPublicacionWSClient())
                {
                    cliente.guardarVotoPublicacion(voto, estado.Nuevo);
                }
                using (var clientUsuario = new Usuario_comunWSClient())
                {
                    if (tipoVoto == 'U')
                    {
                        clientUsuario.actualizarKarma(idUsuario, Karma.ObtenerPuntaje(Karma.AccionKarma.PublicacionVotadaUp));
                    }
                    else {
                        clientUsuario.actualizarKarma(idUsuario, Karma.ObtenerPuntaje(Karma.AccionKarma.PublicacionVotadoDown));
                    }
                    
                }

                // Refresca los datos (contadores de votos)
                CargarPublicacion();
            }
            catch (System.Exception ex)
            {
                lblMensaje.Text = "Error al registrar el voto: " + ex.Message;
            }
        }

        /* ===================== REPORTE ===================== */

        protected void btnReportar_Click(object sender, EventArgs e)
        {
            try
            {
                int idPubli = ObtenerIdPublicacion();
                if (idPubli == 0)
                {
                    lblMensaje.Text = "ID de publicación inválido.";
                    return;
                }

                int idUsuario = ObtenerIdUsuarioActual();
                if (idUsuario == 0)
                {
                    lblMensaje.Text = "Debes iniciar sesión para reportar.";
                    return;
                }

                var rep = new reporte
                {
                    // Puedes cambiar el tipo por la razón que quieras manejar
                    tipo = "SPAM",
                    publicacionreportada = new publicacion { id = idPubli },
                    reportador = new usuarioComun { idUsuario = idUsuario },
                    estado = 'P' // P = pendiente, por ejemplo
                };

                using (var cliente = new ReporteWSClient())
                {
                    cliente.guardarReporte(rep, estado.Nuevo);
                }

                lblMensaje.CssClass = "text-success";
                lblMensaje.Text = "Reporte enviado. Gracias por ayudarnos a mantener la comunidad segura.";
            }
            catch (System.Exception ex)
            {
                lblMensaje.CssClass = "text-danger";
                lblMensaje.Text = "Error al enviar el reporte: " + ex.Message;
            }
        }
    }
}
