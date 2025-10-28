using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RedPucpWeb.estefano.pantallas
{
    public partial class Login : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            ValidationSettings.UnobtrusiveValidationMode = UnobtrusiveValidationMode.None;
        }

        protected void btnLogin_Click(object sender, EventArgs e)
        {
            string email = txtEmail.Text.Trim();
            string password = txtPassword.Text.Trim();

            //luego se validará con el backend
            if (email == "usuario@pucp.edu.pe" && password == "12345")
            {
                Session["Usuario"] = "estefano";
                //Session["Usuario"] = "usuario@pucp.edu.pe"; <- aquí se registraría el nombre del usuario para la página de inicio
                Response.Redirect("Inicio.aspx");
            }
            else
            {
                lblError.Text = "Correo o contraseña incorrectos.";
                lblError.Visible = true;
            }
        }
    }
}