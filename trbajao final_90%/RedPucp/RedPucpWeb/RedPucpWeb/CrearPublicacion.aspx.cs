using CloudinaryDotNet.Actions;
using Prueba_Frontend.Servicios;
using RedPucpWeb.RedPucpWS;
using RedPucpWeb.Servicios;
using RedPucpWeb.utils;
using System;
using System.Web.UI.WebControls;

namespace RedPucpWeb
{
    public partial class CrearPublicacion : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarComunidades();
                CargarCategorias();
            }
        }

        private void CargarCategorias()
        {
            // Limpiar items existentes
            ddlCategoria.Items.Clear();

            // Agregar categorías desde el enum
            ddlCategoria.Items.Add(new ListItem("📝 Sin categoría específica", CategoriaPublicacion.Sin_categoria.ToString()));
            ddlCategoria.Items.Add(new ListItem("🆘 Ayuda", CategoriaPublicacion.Ayuda.ToString()));
            ddlCategoria.Items.Add(new ListItem("❓ Consulta", CategoriaPublicacion.Consulta.ToString()));
            ddlCategoria.Items.Add(new ListItem("💬 Discusión", CategoriaPublicacion.Discusion.ToString()));
            ddlCategoria.Items.Add(new ListItem("📅 Evento", CategoriaPublicacion.Evento.ToString()));
            ddlCategoria.Items.Add(new ListItem("📢 Convocatoria", CategoriaPublicacion.Convocatoria.ToString()));

            // Opción por defecto:
            ddlCategoria.SelectedValue = CategoriaPublicacion.Sin_categoria.ToString();
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

        //comu

        private void CargarComunidades()
        {
            try
            {
                using (var cliente = new ComunidadWSClient())
                {
                    var comunidades = cliente.listarComunidades();

                    ddlComunidad.DataSource = comunidades;
                    ddlComunidad.DataTextField = "nombre";
                    ddlComunidad.DataValueField = "id_comunidad";
                    ddlComunidad.DataBind();
                }

                ddlComunidad.Items.Insert(0, new ListItem("Seleccione una comunidad", ""));
            }
            catch (System.Exception ex)
            {
                lblMensaje.Text = "Error cargando comunidades: " + ex.Message;
            }
        }

        //publicar

        protected void btnPublicar_Click(object sender, EventArgs e)
        {
            lblMensaje.CssClass = "text-danger";
            lblMensaje.Text = "";

            // verifica login
            int idUsuario = ObtenerIdUsuarioActual();
            if (idUsuario == 0)
            {
                lblMensaje.Text = "Debes iniciar sesión para poder crear una publicación.";
                return;
            }

            // Validaciones básicas
            if (string.IsNullOrWhiteSpace(txtTitulo.Text) ||
                string.IsNullOrWhiteSpace(txtContenido.Text) ||
                string.IsNullOrEmpty(ddlComunidad.SelectedValue) ||
                string.IsNullOrEmpty(ddlCategoria.SelectedValue))
            {
                lblMensaje.Text = "Complete todos los campos obligatorios.";
                return;
            }

            string imagenUrl = null;

            // 1. Subir imagen usando el servicio modularizado
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

                imagenUrl = uploadResult.ImageUrl;
            }

            try
            {
                // 2. Crear objeto publicación
                var nuevaPublicacion = new publicacion
                {
                    titulo = txtTitulo.Text.Trim(),
                    descripcion = txtContenido.Text.Trim(),
                    fechaCreacion = DateTime.Now,
                    votosPositivos = 0,
                    votosNegativos = 0,
                    imagenUrl = imagenUrl,
                    categoria = ddlCategoria.SelectedValue
                };

                // Comunidad
                nuevaPublicacion.comunidad = new comunidad
                {
                    id_comunidad = int.Parse(ddlComunidad.SelectedValue)
                };

                // Autor: ahora usamos el usuario logueado (no quemado)
                nuevaPublicacion.autor = new usuarioComun
                {
                    idUsuario = idUsuario
                };

                // 3. Guardar publicación mediante el WS
                using (var cliente = new PublicacionWSClient())
                {
                    cliente.guardarPublicacion(nuevaPublicacion, estado.Nuevo);
                    Karma.AplicarKarma(idUsuario,Karma.AccionKarma.CrearPublicacion);
                }

                // 4. Visualizar mis publicaciones si todo salió bien
                Response.Redirect("Mis_publicaciones.aspx");
            }
            catch (System.Exception ex)
            {
                lblMensaje.Text = "Error al guardar la publicación: " + ex.Message;
            }
        }
    }
}
