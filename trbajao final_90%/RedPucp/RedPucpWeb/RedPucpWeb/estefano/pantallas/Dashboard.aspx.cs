using RedPucpWeb.RedPucpWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RedPucpWeb.estefano.pantallas
{
    public partial class Dashboard : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            var usuarioClient = new Usuario_comunWSClient();
            var comunidadClient = new ComunidadWSClient();
            var reporteClient = new ReporteWSClient();
            if (!IsPostBack)
            {
                lblTotalUsuarios.Text = usuarioClient.ListarUsuariosComunes().Count().ToString();
                lblComunidades.Text = comunidadClient.listarComunidades().Count().ToString();
                lblReportes.Text = 0.ToString(); //reporteClient.listarReportesPendientes().Count().ToString();
            }
        }
    }
}