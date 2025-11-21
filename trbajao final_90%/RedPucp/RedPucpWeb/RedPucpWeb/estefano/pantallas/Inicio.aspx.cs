using RedPucpWeb.RedPucpWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RedPucpWeb.estefano.pantallas
{
    public partial class Inicio : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (Session["Usuario"] != null)
                {
                    string nombreUsuario = Session["Usuario"].ToString();
                    MensajeDeBienvenida.InnerText = $"Bienvenido a RedPUCP, {nombreUsuario}";
                    lnkAccion.Text = "Ir al Feed Principal";
                    lnkAccion.NavigateUrl = "~/Feed.aspx";
                }
                else
                {
                    MensajeDeBienvenida.InnerText = "Bienvenido a RedPUCP, invitado";
                    lnkAccion.Text = "Únete ahora";
                    lnkAccion.NavigateUrl = "Login.aspx";
                }

                CargarComunidadesVirales();
                CargarPublicacionesVirales();
            }
        }

        private void CargarPublicacionesVirales()
        {
            try
            {
                pnlLoadingPublicaciones.Visible = true;

                using (var publicacionClient = new PublicacionWSClient())
                {
                    var publicacionesVirales = publicacionClient.listarPublicacionesVirales()?.ToList();

                    if (publicacionesVirales != null && publicacionesVirales.Any())
                    {
                        rptPublicacionesVirales.DataSource = publicacionesVirales;
                        rptPublicacionesVirales.DataBind();
                        pnlLoadingPublicaciones.Visible = false;
                    }
                    else
                    {
                        MostrarNoPublicaciones();
                    }
                }
            }
            catch (System.Exception ex)
            {
                pnlLoadingPublicaciones.Visible = false;
            }
        }

        private void CargarComunidadesVirales()
        {
            try
            {
                pnlLoadingComunidades.Visible = true;

                using (var comunidadClient = new ComunidadWSClient())
                {
                    var todasComunidades = comunidadClient.listarComunidades()?.ToList();

                    if (todasComunidades != null && todasComunidades.Any())
                    {
                        var comunidadesVirales = todasComunidades
                            .Where(c => c.cantidadmiembros > 0)
                            .OrderByDescending(c => c.cantidadmiembros)
                            .Take(3)
                            .ToList();

                        rptComunidadesVirales.DataSource = comunidadesVirales;
                        rptComunidadesVirales.DataBind();
                        pnlLoadingComunidades.Visible = false;
                    }
                    else
                    {
                        MostrarNoComunidades();
                    }
                }
            }
            catch (System.Exception ex)
            {
                pnlLoadingComunidades.Visible = false;
            }
        }

        private string LimitarContenido(string contenido, int maxLength)
        {
            if (string.IsNullOrEmpty(contenido))
                return "Sin contenido...";

            if (contenido.Length <= maxLength)
                return contenido;

            return contenido.Substring(0, maxLength) + "...";
        }

        private string ObtenerNombreComunidad(int idComunidad)
        {
            try
            {
                using (var comunidadClient = new ComunidadWSClient())
                {
                    var comunidad = comunidadClient.obtenerComunidad(idComunidad);
                    return comunidad?.nombre ?? "Comunidad";
                }
            }
            catch
            {
                return "Comunidad";
            }
        }

        private int ObtenerCantidadComentarios(int idPublicacion)
        {
            try
            {
                using (var comentarioClient = new ComentarioWSClient())
                {
                    var todosComentarios = comentarioClient.listarComentarios()?.ToList();
                    return todosComentarios?.Count(c => c.publicacion.id == idPublicacion) ?? 0;
                }
            }
            catch
            {
                return 0;
            }
        }

        protected void rptPublicacionesVirales_ItemDataBound(object sender, RepeaterItemEventArgs e)
        {
            if (e.Item.ItemType == ListItemType.Item || e.Item.ItemType == ListItemType.AlternatingItem)
            {
                var publicacion = e.Item.DataItem as PublicacionViewModel;
                if (publicacion != null)
                {
                    var lnkPublicacion = e.Item.FindControl("lnkPublicacion") as HyperLink;
                    var lnkComentarios = e.Item.FindControl("lnkComentarios") as HyperLink;

                    if (lnkPublicacion != null)
                    {
                        lnkPublicacion.NavigateUrl = $"Publicacion.aspx?id={publicacion.IdPublicacion}";
                    }

                    if (lnkComentarios != null)
                    {
                        lnkComentarios.NavigateUrl = $"Publicacion.aspx?id={publicacion.IdPublicacion}#comentarios";
                    }
                }
            }
            else if (e.Item.ItemType == ListItemType.Footer)
            {
                var lblNoPublicaciones = e.Item.FindControl("lblNoPublicaciones") as Label;
                if (lblNoPublicaciones != null && rptPublicacionesVirales.Items.Count == 0)
                {
                    lblNoPublicaciones.Visible = true;
                    pnlLoadingPublicaciones.Visible = false;
                }
            }
        }

        protected void rptComunidadesVirales_ItemDataBound(object sender, RepeaterItemEventArgs e)
        {
            if (e.Item.ItemType == ListItemType.Item || e.Item.ItemType == ListItemType.AlternatingItem)
            {
                var comunidad = e.Item.DataItem as comunidad;
                if (comunidad != null)
                {
                    var lnkVerComunidad = e.Item.FindControl("lnkVerComunidad") as HyperLink;
                    if (lnkVerComunidad != null)
                    {
                        lnkVerComunidad.NavigateUrl = $"Comunidad.aspx?id={comunidad.id_comunidad}";
                    }
                }
            }
            else if (e.Item.ItemType == ListItemType.Footer)
            {
                var lblNoComunidades = e.Item.FindControl("lblNoComunidades") as Label;
                if (lblNoComunidades != null && rptComunidadesVirales.Items.Count == 0)
                {
                    lblNoComunidades.Visible = true;
                    pnlLoadingComunidades.Visible = false;
                }
            }
        }
        private void MostrarNoPublicaciones()
        {
            pnlLoadingPublicaciones.Visible = false;
        }

        private void MostrarNoComunidades()
        {
            pnlLoadingComunidades.Visible = false;
        }
    }

    public class PublicacionViewModel
    {
        public int IdPublicacion { get; set; }
        public string Titulo { get; set; }
        public string Contenido { get; set; }
        public string ContenidoCorto { get; set; }
        public int Karma { get; set; }
        public DateTime FechaCreacion { get; set; }
        public int IdUsuario { get; set; }
        public int IdComunidad { get; set; }
        public string Autor { get; set; }
        public string ComunidadNombre { get; set; }
        public int CantidadComentarios { get; set; }
    }
}