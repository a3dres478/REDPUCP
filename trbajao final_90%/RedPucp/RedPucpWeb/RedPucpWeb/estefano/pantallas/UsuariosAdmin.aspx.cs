using RedPucpWeb.RedPucpWS;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.Services.Description;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RedPucpWeb.estefano.pantallas
{
    public partial class UsuariosAdmin : System.Web.UI.Page
    {
        Usuario_comunWSClient usuarioClient = new Usuario_comunWSClient();
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                cargarUsuarios();
            }
        }

        private void cargarUsuarios(string filtro = "")
        {
            var lista = usuarioClient.ListarUsuariosComunes().ToList();

            if (!string.IsNullOrEmpty(filtro))
            {
                filtro = filtro.ToLower();
                lista = lista.Where(u =>
                    u.nombre.ToLower().Contains(filtro) ||
                    u.email.ToLower().Contains(filtro)
                ).ToList();
            }

            gvUsuarios.DataSource = lista;
            gvUsuarios.DataBind();
        }

        protected void btnBuscar_Click(object sender, EventArgs e)
        {
            cargarUsuarios(txtBuscar.Text.Trim());
        }

        protected void gvUsuarios_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvUsuarios.PageIndex = e.NewPageIndex;
            cargarUsuarios(txtBuscar.Text.Trim());
        }

        protected void btnNuevo_Click(object sender, EventArgs e)
        {
            Response.Redirect("NuevoUsuario.aspx");
        }

        protected void gvUsuarios_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            int id = Convert.ToInt32(e.CommandArgument);

            if (e.CommandName == "Editar")
            {
                Response.Redirect($"EditarUsuario.aspx?id={id}");
            }
            else if (e.CommandName == "Eliminar")
            {
                usuarioClient.eliminarUsuarioComun(id);
                cargarUsuarios();
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

            string baseUrl = "http://localhost:8080/RedPUCPReportes/reportes/usuarios";

            string link = $"{baseUrl}?tipodeusuario={estadoSeleccionado}";

            Response.Redirect(link);
        }
    }
}