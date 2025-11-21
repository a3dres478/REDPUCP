using System;
using RedPucpWeb.RedPucpWS;

namespace RedPucpWeb
{
    public partial class ReportarPublicacion : System.Web.UI.Page
    {
        // ===== Helpers de sesión (login de tu compañero) =====
        private usuarioComun ObtenerUsuarioActual()
        {
            // El login guarda el usuario completo aquí
            return Session["UsuarioCompleto"] as usuarioComun;
        }

        private int ObtenerIdUsuarioActual()
        {
            var usuario = ObtenerUsuarioActual();
            if (usuario == null) return 0;
            return usuario.idUsuario;
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarPublicacion();
            }
        }

        private int ObtenerIdPublicacion()
        {
            string idStr = Request.QueryString["id"];
            if (!int.TryParse(idStr, out int idPubli))
                return 0;
            return idPubli;
        }

        private void CargarPublicacion()
        {
            try
            {
                int idPubli = ObtenerIdPublicacion();
                if (idPubli == 0)
                {
                    lblMensaje.Text = "ID de publicación inválido.";
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

                    if (publi.autor != null)
                        lblAutor.Text = publi.autor.nombre ?? ("Usuario #" + publi.autor.idUsuario);
                    else
                        lblAutor.Text = "Desconocido";

                    if (publi.comunidad != null)
                        lblComunidad.Text = publi.comunidad.nombre;
                    else
                        lblComunidad.Text = "Sin comunidad";
                }

                // Link de cancelar regresa al detalle de esa publicación
                lnkCancelar.NavigateUrl = "plantillaDetallePublicacion.aspx?id=" + idPubli;
            }
            catch (System.Exception ex)
            {
                lblMensaje.Text = "Error cargando publicación: " + ex.Message;
            }
        }

        protected void btnEnviar_Click(object sender, EventArgs e)
        {
            try
            {
                // 1. Verificar login
                int idUsuario = ObtenerIdUsuarioActual();
                if (idUsuario == 0)
                {
                    lblMensaje.Text = "Debes iniciar sesión para poder reportar una publicación.";
                    return;
                }

                // 2. Validar ID de publicación
                int idPubli;
                if (!int.TryParse(hfIdPublicacion.Value, out idPubli) || idPubli == 0)
                {
                    lblMensaje.Text = "ID de publicación inválido.";
                    return;
                }

                // 3. Validar motivo
                if (string.IsNullOrEmpty(ddlMotivo.SelectedValue))
                {
                    lblMensaje.Text = "Seleccione un motivo para el reporte.";
                    return;
                }

                string motivo = ddlMotivo.SelectedValue;
                string detalle = txtDetalles.Text.Trim();




                // 4. Construir objeto reporte (modelo generado del WS)
                var rep = new reporte
                {
                    // tipo de reporte (SPAM, OFENSIVO, etc.)
                    tipo = motivo,

                    // Solo reportamos la publicación (no un comentario en específico)
                    publicacionreportada = new publicacion { id = idPubli },

                    // No estamos reportando un comentario concreto
                    // comentarioreportado queda null por defecto

                    // texto libre con más detalles
                    detalle = detalle,

                    // quién reporta
                    reportador = new usuarioComun { idUsuario = idUsuario },

                    // estado inicial del reporte: Pendiente (P)
                    estado = 'P'
                };

                // 5. Llamar al WS de Reportes
                using (var cliente = new ReporteWSClient())
                {
                    cliente.guardarReporte(rep, estado.Nuevo);
                }

                // 6. Mostrar mensaje de éxito
                alertOk.Attributes["class"] = "alert alert-success"; // si antes tenía d-none, se la quitas en el .aspx
                lblMensaje.Text = string.Empty;
            }
            catch (System.Exception ex)
            {
                lblMensaje.Text = "Error al enviar el reporte: " + ex.Message;
            }
        }

    }
}
