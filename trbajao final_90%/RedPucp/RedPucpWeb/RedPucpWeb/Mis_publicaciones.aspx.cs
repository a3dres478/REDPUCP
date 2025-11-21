using System;
using System.Linq;
using System.Web.UI.WebControls;
using RedPucpWeb.RedPucpWS;

namespace RedPucpWeb
{
    public partial class MisPublicaciones : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarMisPublicaciones();
            }
        }

        private usuarioComun ObtenerUsuarioActual()
        {
            // login
            return Session["UsuarioCompleto"] as usuarioComun;
        }

        private int ObtenerIdUsuarioActual()
        {
            var usuario = ObtenerUsuarioActual();
            if (usuario == null) return 0;
            return usuario.idUsuario;
        }



        private void CargarMisPublicaciones()
        {
            try
            {
                int idUsuario = ObtenerIdUsuarioActual();

                if (idUsuario == 0)
                {
                    lblMensaje.Text = "Debes iniciar sesión para ver tus publicaciones.";
                    pnlMisPublicaciones.Visible = false;
                    return;
                }

                using (var cliente = new PublicacionWSClient())
                {
                    var mis_publicaciones = cliente.listarPublicacionesXUsuario(idUsuario);

                    if (mis_publicaciones == null)
                    {
                        pnlMisPublicaciones.Visible = true;
                        lblSinPublicaciones.Visible = true;
                        rptMisPublicaciones.Visible = false;
                        return;
                    }
                    

                    pnlMisPublicaciones.Visible = true;

                    if (mis_publicaciones.Length == 0)
                    {
                        lblSinPublicaciones.Visible = true;
                        rptMisPublicaciones.Visible = false;
                    }
                    else
                    {
                        lblSinPublicaciones.Visible = false;
                        rptMisPublicaciones.Visible = true;

                        rptMisPublicaciones.DataSource = mis_publicaciones;
                        rptMisPublicaciones.DataBind();
                    }
                }
            }
            catch (System.Exception ex)
            {
                pnlMisPublicaciones.Visible = true;
                lblSinPublicaciones.Visible = true;
                lblSinPublicaciones.Text = "No se pudieron cargar tus publicaciones: " + ex.Message;
            }
        }

        protected void rptMisPublicaciones_ItemCommand(object source, RepeaterCommandEventArgs e)
        {
            if (e.CommandName == "Eliminar")
            {
                try
                {
                    int idPublicacion;
                    if (int.TryParse(e.CommandArgument.ToString(), out idPublicacion))
                    {
                        using (var cliente = new PublicacionWSClient())
                        {
                            // @WebMethod(operationName = "eliminarPublicacion")
                            cliente.eliminarPublicacion(idPublicacion);
                        }

                        // Recargar
                        CargarMisPublicaciones();
                        lblMensaje.CssClass = "text-success";
                        lblMensaje.Text = "Publicación eliminada correctamente.";
                    }
                }
                catch (System.Exception ex)
                {
                    lblMensaje.CssClass = "text-danger";
                    lblMensaje.Text = "No se pudo eliminar la publicación: " + ex.Message;
                }
            }
        }
    }
}
