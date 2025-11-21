using RedPucpWeb.RedPucpWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RedPucpWeb.estefano.pantallas
{
    public partial class Perfil : System.Web.UI.Page
    {
        private usuarioComun usuario;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Usuario"] == null)
                Response.Redirect("Login.aspx");

            if (!IsPostBack)
            {
                int idUsuario = ((usuarioComun)Session["UsuarioCompleto"]).idUsuario;
                //usuario = Session["UsuarioCompleto"] as usuarioComun;
                usuario = new Usuario_comunWSClient().obtenerUsuario(idUsuario);
                lblUsuario.Text = usuario.nombre;
                lblCodigo.Text = usuario.codigopucp;
                lblCorreo.Text = usuario.email;
                lblKarma.Text = "Karma: "+usuario.karma.ToString();
                txtDescripcion.Text = usuario.descripcion;
            }
        }

        protected void btnEditarPerfil_Click(object sender, EventArgs e)
        {
            try
            {
                usuario = Session["UsuarioCompleto"] as usuarioComun;
                usuario.descripcion = txtDescripcion.Text.Trim();

                using (var usuarioClient = new Usuario_comunWSClient())
                {
                    usuarioClient.guardarUsuarioComun(usuario, estado.Modificar);
                }
            }
            catch (System.Exception ex)
            {
                // Manejo de errores
            }
        }

        protected void btnEliminarCuenta_Click(object sender, EventArgs e)
        {
            try
            {
                usuario = Session["UsuarioCompleto"] as usuarioComun;

                using (var usuarioClient = new Usuario_comunWSClient())
                {
                    usuarioClient.eliminarUsuarioComun(usuario.idUsuario);

                }

                // Limpiar sesión y redirigir
                Session.Clear();
                Response.Redirect("Login.aspx?mensaje=CuentaEliminada");
            }
            catch (System.Exception ex)
            {
                Page.ClientScript.RegisterStartupScript(this.GetType(), "Error", $"alert('Error al eliminar: {ex.Message}');", true);
            }
        }

    }
}