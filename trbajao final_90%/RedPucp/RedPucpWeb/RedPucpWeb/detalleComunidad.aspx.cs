using System;
using System.Collections;
using System.Linq;
using RedPucpWeb.RedPucpWS;

namespace RedPucpWeb
{
    public partial class DetalleComunidad : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                int idComunidad = ObtenerIdComunidad();

                if (idComunidad == 0)
                {
                    lblMensaje.Text = "ID de comunidad inválido.";
                    pnlComunidad.Visible = false;
                    pnlPublicaciones.Visible = false;
                    return;
                }

         
                hfIdComunidad.Value = idComunidad.ToString();
                CargarComunidad();
                CargarPublicacionesComunidad(idComunidad);
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

        //comu

        private int ObtenerIdComunidad()
        {
            string idStr = Request.QueryString["idComunidad"];
            if (string.IsNullOrEmpty(idStr))
                idStr = Request.QueryString["id"];

            if (!int.TryParse(idStr, out int id))
                return 0;

            return id;
        }

        private void CargarComunidad()
        {
            try
            {
                int id = ObtenerIdComunidad();
                if (id == 0)
                {
                    lblMensaje.Text = "ID de comunidad inválido.";
                    return;
                }

                hfIdComunidad.Value = id.ToString();

                using (var cliente = new ComunidadWSClient())
                {
                    var comunidades = cliente.listarComunidades();
                    var com = comunidades.FirstOrDefault(c => c.id_comunidad == id);

                    if (com == null)
                    {
                        lblMensaje.Text = "No se encontró la comunidad.";
                        return;
                    }

                    pnlComunidad.Visible = true;

                    lblNombre.Text = com.nombre;
                    lblDescripcion.Text = com.descripcion;

                    // Admin
                    if (com.administrador != null)
                    {
                        if (!string.IsNullOrWhiteSpace(com.administrador.nombre))
                            lblAdmin.Text = com.administrador.nombre;
                        else
                            lblAdmin.Text = "Usuario #" + com.administrador.idUsuario;
                    }
                    else
                    {
                        lblAdmin.Text = "No asignado";
                    }

                    // Fecha de creación
                    if (com.fecha_creacionSpecified)
                        lblFecha.Text = com.fecha_creacion.ToString("dd/MM/yyyy");
                    else
                        lblFecha.Text = "-";

                    // Miembros
                    lblMiembros.Text = com.cantidadmiembros.ToString();
                }

                // volver feed
                lnkVolver.NavigateUrl = "Feed.aspx?idComunidad=" + id;
            }
            catch (System.Exception ex)
            {
                lblMensaje.Text = "Error cargando comunidad: " + ex.Message;
            }
        }

        protected void btnUnirme_Click(object sender, EventArgs e)
        {
            try
            {
                //verificar logeo
                int idUsuario = ObtenerIdUsuarioActual();
                if (idUsuario == 0)
                {
                    lblMensaje.Text = "Debes iniciar sesión para unirte a la comunidad.";
                    return;
                }

                // 2. Obtener id de comunidad
                int idComunidad;
                if (!int.TryParse(hfIdComunidad.Value, out idComunidad) || idComunidad == 0)
                {
                    lblMensaje.Text = "ID de comunidad inválido.";
                    return;
                }

               //unirse a comu
                btnUnirme.Enabled = false;
                btnUnirme.CssClass = "btn btn-secondary";
                lblUnido.Visible = true;
                lblUnido.Text = "Te has unido a la comunidad";

                

                int miembrosActuales;
                if (int.TryParse(lblMiembros.Text, out miembrosActuales))
                    lblMiembros.Text = (miembrosActuales + 1).ToString();
            }
            catch (System.Exception ex)
            {
                lblMensaje.Text = "Error al unirte a la comunidad: " + ex.Message;
            }
        }



        //publicaciones por comunidad

        private void CargarPublicacionesComunidad(int idComunidad)
        {
            try
            {
                using (var clientePubli = new PublicacionWSClient())
                {
                    string categoria = "Sin_categoria";
                    string ordenamiento = "Fecha_reciente";

                    var publicaciones = clientePubli.listarPublicacionesXFiltros(
                        idComunidad,
                        categoria,
                        ordenamiento
                    );


                    var filtradas = publicaciones;
                    //var filtradas = (publicaciones ?? Array.Empty<publicacion>())
                    //    .Where(p => p.comunidad != null &&
                    //                p.comunidad.id_comunidad == idComunidad)
                    //    .ToList();

                    if (filtradas == null /*|| filtradas.Count == 0*/)
                    {
                        pnlPublicaciones.Visible = true;
                        lblSinPublicaciones.Visible = true;
                        rptPublicacionesComunidad.Visible = false;
                    }
                    else
                    {
                        pnlPublicaciones.Visible = true;
                        lblSinPublicaciones.Visible = false;
                        rptPublicacionesComunidad.Visible = true;

                        rptPublicacionesComunidad.DataSource = filtradas;
                        rptPublicacionesComunidad.DataBind();
                    }
                }
            }
            catch (System.Exception ex)
            {
                pnlPublicaciones.Visible = true;
                lblSinPublicaciones.Visible = true;
                lblSinPublicaciones.Text = "No se pudieron cargar las publicaciones: " + ex.Message;
            }
        }

    }
}
