using System;
//using RedPucpWeb.RedPucpWS;  // lo usaremos luego cuando el WS funcione

namespace RedPucpWeb
{
    public partial class RedPucpMast : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarUsuario();
            }
        }

        private void CargarUsuario()
        {
            // 1. Intentamos obtener el nombre del usuario común
            var nombreUsuario = Session["Usuario"] as string;

            // 2. Si no hay usuario común, probamos con admin
            if (string.IsNullOrWhiteSpace(nombreUsuario))
            {
                nombreUsuario = Session["Admin"] as string;
            }

            // 3. Si hay alguien logueado (usuario o admin), lo mostramos
            if (!string.IsNullOrWhiteSpace(nombreUsuario))
            {
                lblUsuario.Text = nombreUsuario;
            }
            else
            {
                // 4. Nadie logueado → mostrar Invitado (o dejar vacío)
                lblUsuario.Text = "Invitado";
            }
        }



    }
}
