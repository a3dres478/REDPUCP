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
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Usuario"] == null)
                Response.Redirect("Login.aspx");

            if (!IsPostBack)
            {
                lblUsuario.Text = Session["Usuario"].ToString();
                lblCodigo.Text = Session["Codigo"] != null ? Session["Codigo"].ToString() : "20220000";
                lblCorreo.Text = Session["Correo"]?.ToString() ?? "usuario@pucp.edu.pe";
                lblKarma.Text = $"{new Random().Next(1000, 5000)} Karma";
                txtDescripcion.Text = Session["Descripcion"]?.ToString() ?? "Estudiante de 7mo ciclo de la carrera de matemáticas que le gusta...";
            }
        }

        protected void btnEditarPerfil_Click(object sender, EventArgs e)
        {
            // Guarda la descripción editada
            Session["Descripcion"] = txtDescripcion.Text;
        }
    }
}