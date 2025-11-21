using System;
using RedPucpWeb.RedPucpWS;

namespace RedPucpWeb
{
    public partial class CrearComunidad : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            // vacío
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

        // crear comu

        protected void btnCrear_Click(object sender, EventArgs e)
        {
            lblMensaje.Text = string.Empty;
            lblMensaje.CssClass = "text-danger";

            // verficacion loegeo
            int idUsuario = ObtenerIdUsuarioActual();
            if (idUsuario == 0)
            {
                lblMensaje.Text = "Debes iniciar sesión para poder crear una comunidad.";
                return;
            }

            // Validaciones básicas
            if (string.IsNullOrWhiteSpace(txtNombre.Text) ||
                string.IsNullOrWhiteSpace(txtDescripcion.Text))
            {
                lblMensaje.Text = "Complete nombre y descripción.";
                return;
            }

            try
            {
                
                var com = new comunidad
                {
                    nombre = txtNombre.Text.Trim(),
                    descripcion = txtDescripcion.Text.Trim(),

                    // Cantidad de miembros al inicio
                    cantidadmiembros = 1, // contamos al creador como primer usuer

                    // Fecha de creación
                    fecha_creacion = DateTime.Now,
                    fecha_creacionSpecified = true,
                    estado = 1
                };

                // Administrador = usuario logueado
                var admin = new usuarioComun
                {
                    idUsuario = idUsuario
                };
                com.administrador = admin;

                // Llamar al WS
                using (var cliente = new ComunidadWSClient())
                {
                    cliente.guardarComunidad(com, estado.Nuevo);
                }

                // Si todo ok, volvemos al feed
                Response.Redirect("Feed.aspx");
            }
            catch (System.Exception ex)
            {
                lblMensaje.Text = "Error al crear comunidad: " + ex.Message;
            }
        }
    }
}
