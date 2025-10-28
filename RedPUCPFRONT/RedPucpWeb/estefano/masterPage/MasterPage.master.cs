using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RedPucpWeb.estefano.masterPage
{
    public partial class MasterPage : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            bool usuarioLogeado = Session["Usuario"] != null;

            if (usuarioLogeado)
            {
                PanelInvitado.Visible = false;
                PanelUsuario.Visible = true;

                // Mostrar el nombre del usuario
                lblUsuario.Text = Session["Usuario"].ToString();
            }
            else
            {
                PanelInvitado.Visible = true;
                PanelUsuario.Visible = false;
            }
        }

        protected void btnPerfil_Click(object sender, EventArgs e)
        {
            Response.Redirect("Perfil.aspx");
        }

        protected void btnLogout_Click(object sender, EventArgs e)
        {
            Session.Clear();
            Response.Redirect("Inicio.aspx");
        }
    }
}