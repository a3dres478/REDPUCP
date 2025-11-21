using RedPucpWeb.RedPucpWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RedPucpWeb.estefano.pantallas
{
    public partial class ComunidadAdmin : System.Web.UI.Page
    {
        ComunidadWSClient comunidadWSClient = new ComunidadWSClient();
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
                CargarComunidades();
        }

        private void CargarComunidades(string filtro = "")
        {
            var lista = comunidadWSClient.listarComunidades().ToList();

            if (!string.IsNullOrEmpty(filtro))
            {
                filtro = filtro.ToLower();
                lista = lista.Where(u =>
                    u.nombre.ToLower().Contains(filtro) ||
                    u.descripcion.ToLower().Contains(filtro)
                ).ToList();
            }

            gvComunidades.DataSource = lista;
            gvComunidades.DataBind();
        }

        protected void btnBuscar_Click(object sender, EventArgs e)
        {
            CargarComunidades(txtBuscar.Text.Trim());
        }

        protected void gvComunidades_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvComunidades.PageIndex = e.NewPageIndex;
            CargarComunidades(txtBuscar.Text.Trim());
        }

        protected void btnNuevo_Click(object sender, EventArgs e)
        {
            Response.Redirect("NuevaComunidad.aspx");
        }

        protected void gvComunidades_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            int id = Convert.ToInt32(e.CommandArgument);
            if (e.CommandName == "Editar")
            {
                Response.Redirect("EditarComunidad.aspx?id=" + id);
            }
            else if (e.CommandName == "Eliminar")
            {
                comunidadWSClient.eliminarComunidad(id);
                CargarComunidades(txtBuscar.Text.Trim());
            }
        }

        protected void btnReporte_Click(object sender, EventArgs e)
        {
            string estadoSeleccionado = ddlEstado.SelectedValue;

            if (string.IsNullOrEmpty(estadoSeleccionado))
            {
                lblMensaje.Text = "Seleccione un estado antes de generar el reporte.";
                return;
            }

            string baseUrl = "http://localhost:8080/RedPUCPReportes/reportes/ReporteListarComunidades";

            string link = $"{baseUrl}?parametro={estadoSeleccionado}";

            Response.Redirect(link);

        }
    }
}