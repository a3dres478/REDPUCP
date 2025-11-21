using System;
using RedPucpWeb.RedPucpWS;

namespace RedPucpWeb
{
    public partial class Feed : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarPublicaciones();
                CargarComunidades();
            }
        }

        private void CargarPublicaciones()
        {
            try
            {
                using (var cliente = new PublicacionWSClient())
                {
                    // ws
                    var publicaciones = cliente.listarPublicacionesVirales();

                    rptPublicaciones.DataSource = publicaciones;
                    rptPublicaciones.DataBind();
                    
                }
            }
            catch (System.Exception ex)
            {
                Response.Write("<script>alert('Error cargando publicaciones: " + ex.Message + "');</script>");
            }
        }
        private void CargarComunidades()
        {
            try
            {
                using (var cliente = new ComunidadWSClient())
                {
                    var comunidades = cliente.listarComunidades();

                    rptComunidades.DataSource = comunidades;
                    rptComunidades.DataBind();
                }
            }
            catch (System.Exception ex)
            {
                lblMensajeComunidades.Text = "No se pudieron cargar las comunidades.";
            }
        }

    }
}
