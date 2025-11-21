using System;
using System.Web;
using System.Web.UI;

namespace RedPucpWeb.estefano.masterPage
{
    public partial class MasterPage : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            // Tomamos el indicador de sesión que ya vienes usando
            var usuario = Session["Usuario"] as string;
            bool usuarioLogeado = !string.IsNullOrWhiteSpace(usuario);

            // Alterna paneles según estado
            PanelInvitado.Visible = !usuarioLogeado;
            PanelUsuario.Visible = usuarioLogeado;

            // Muestra el nombre en la navbar si está logueado
            if (usuarioLogeado)
            {
                lblUsuario.Text = usuario;
            }
        }

        protected void btnPerfil_Click(object sender, EventArgs e)
        {
            // Ir al perfil del usuario
            string url = ResolveUrl("~/estefano/pantallas/Perfil.aspx");
            Response.Redirect(url, false);
            Context.ApplicationInstance.CompleteRequest();
        }

        protected void btnLogout_Click(object sender, EventArgs e)
        {
            // Cerrar sesión y volver al inicio (versión invitado)
            Session.RemoveAll();
            Session.Abandon();

            string url = ResolveUrl("~/estefano/pantallas/Inicio.aspx");
            Response.Redirect(url, false);
            Context.ApplicationInstance.CompleteRequest();
        }
    }
}
