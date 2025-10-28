using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RedPucpWeb.estefano.pantallas
{
    public partial class Inicio : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (Session["Usuario"] != null)
                {
                    string nombreUsuario = Session["Usuario"].ToString();
                    MensajeDeBienvenida.InnerText = $"Bienvenido a RedPUCP, {nombreUsuario}";
                    lnkAccion.Text = "Ir al Feed Principal";
                    lnkAccion.NavigateUrl = "~/Feed.aspx";
                }
                else
                {
                    MensajeDeBienvenida.InnerText = "Bienvenido a RedPUCP, invitado";
                    lnkAccion.Text = "Únete ahora";
                    lnkAccion.NavigateUrl = "Login.aspx";
                }
            }
        }
    }
}