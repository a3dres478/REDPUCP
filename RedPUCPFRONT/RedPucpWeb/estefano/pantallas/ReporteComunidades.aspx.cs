using System;
using System.Collections.Generic;
using System.Linq;

namespace RedPucpWeb.estefano.pantallas
{
    public partial class ReporteComunidades : System.Web.UI.Page
    {
        // Clase interna representando una comunidad
        public class Comunidad
        {
            public int Numero { get; set; }
            public string Nombre { get; set; } // ✅ Renombrada
            public string Categoria { get; set; }
            public int Miembros { get; set; }
            public string FechaCreacion { get; set; }
            public string Estado { get; set; }
        }


        // Lista simulada de comunidades (simula datos desde la BD)
        private static List<Comunidad> comunidades = new List<Comunidad>
        {
            new Comunidad { Numero=1, Nombre="r/informatica", Categoria="Ingeniería y Tecnología", Miembros=385, FechaCreacion="2023-03-10", Estado="Activa" },
            new Comunidad { Numero=2, Nombre="r/ee.gg.cc", Categoria="Ciencias", Miembros=322, FechaCreacion="2023-05-18", Estado="Inactiva" },
            new Comunidad { Numero=3, Nombre="r/eventosPUCP", Categoria="Difusión", Miembros=220, FechaCreacion="2023-09-20", Estado="Inactiva" },
            new Comunidad { Numero=4, Nombre="r/robotica", Categoria="Ingeniería aplicada", Miembros=160, FechaCreacion="2024-03-28", Estado="Activa" },
            new Comunidad { Numero=5, Nombre="r/ee.gg.ll", Categoria="Letras", Miembros=150, FechaCreacion="2025-02-18", Estado="Activa" }
        };


        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Usuario"] == null)
                Response.Redirect("~/estefano/pantallas/Login.aspx");

            if (!IsPostBack)
                CargarTabla();
        }

        private void CargarTabla(IEnumerable<Comunidad> data = null)
        {
            gvComunidades.DataSource = data ?? comunidades;
            gvComunidades.DataBind();
        }

        protected void btnBuscar_Click(object sender, EventArgs e)
        {
            string termino = txtBuscar.Text.Trim().ToLower();

            var filtradas = comunidades.Where(c =>
                c.Nombre.ToLower().Contains(termino) ||
                c.Categoria.ToLower().Contains(termino) ||
                c.Estado.ToLower().Contains(termino));

            CargarTabla(filtradas);
        }
    }
}
