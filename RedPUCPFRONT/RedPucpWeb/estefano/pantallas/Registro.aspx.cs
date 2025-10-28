using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RedPucpWeb.estefano.pantallas
{
    public partial class Registro : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }
        protected void btnRegistrar_Click(object sender, EventArgs e)
        {
            string nombre = txtNombre.Text.Trim();
            string email = txtEmail.Text.Trim();
            string codigo = txtCodigo.Text.Trim();
            string contrasena = txtContrasena.Text.Trim();
            string descripcion = txtDescripcion.Text.Trim();

            // aqupi iria la logica con el backend para registrar al usuario
            bool exito = true;

            if (exito)
            {
                //guardamos la sesion del usuario
                Session["Usuario"] = nombre;
                Response.Redirect("Login.aspx");
            }
            else
            {
                Response.Write("<script>alert('Hubo un error al registrarse. Intente nuevamente.');</script>");
            }
        }
    }
}