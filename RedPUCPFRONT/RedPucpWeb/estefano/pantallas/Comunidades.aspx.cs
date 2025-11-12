using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.UI.WebControls;

namespace RedPucpWeb.estefano.pantallas
{
    public partial class Comunidades : System.Web.UI.Page
    {
        // --- MODELOS DE DATOS ---
        public class Comunidad
        {
            public int Id { get; set; }
            public string Nombre { get; set; }
            public string Categoria { get; set; }
            public int Miembros { get; set; }
            public string Descripcion { get; set; }
            public bool Unido { get; set; }

            // Datos para el reporte
            public int Numero { get; set; }
            public string ComunidadNombre { get; set; }
            public string FechaCreacion { get; set; }
            public string Estado { get; set; }
        }

        // --- LISTA PRINCIPAL DE COMUNIDADES ---
        private static List<Comunidad> comunidades = new List<Comunidad>
        {
            new Comunidad { Id=1, Nombre="r/Programacion", Categoria="Tecnología", Miembros=253, Descripcion="Desarrolladores que comparten código y proyectos.", Unido=false, Numero=1, ComunidadNombre="r/Programacion", FechaCreacion="2023-03-10", Estado="Activa"},
            new Comunidad { Id=2, Nombre="r/Videojuegos", Categoria="Gamer", Miembros=180, Descripcion="Comparte experiencias y logros en tus juegos favoritos.", Unido=false, Numero=2, ComunidadNombre="r/Videojuegos", FechaCreacion="2023-05-18", Estado="Activa"},
            new Comunidad { Id=3, Nombre="r/IAyMachineLearning", Categoria="Tecnología", Miembros=120, Descripcion="Todo sobre inteligencia artificial y aprendizaje automático.", Unido=false, Numero=3, ComunidadNombre="r/IAyMachineLearning", FechaCreacion="2023-07-22", Estado="Activa"},
            new Comunidad { Id=4, Nombre="r/EstudioPUCP", Categoria="Estudio", Miembros=200, Descripcion="Estudiantes que comparten apuntes y recursos académicos.", Unido=false, Numero=4, ComunidadNombre="r/EstudioPUCP", FechaCreacion="2024-01-12", Estado="Activa"},
            new Comunidad { Id=5, Nombre="r/RandomPUCP", Categoria="Random", Miembros=95, Descripcion="Temas libres, memes y anécdotas universitarias.", Unido=false, Numero=5, ComunidadNombre="r/RandomPUCP", FechaCreacion="2024-09-05", Estado="Activa"}
        };

        // --- CARGA INICIAL ---
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarComunidades(comunidades);
            }
        }

        private void CargarComunidades(IEnumerable<Comunidad> data)
        {
            rptComunidades.DataSource = data;
            rptComunidades.DataBind();
        }

        // --- FILTRAR POR CATEGORÍA ---
        protected void ddlCategoria_SelectedIndexChanged(object sender, EventArgs e)
        {
            string categoria = ddlCategoria.SelectedValue;

            if (categoria == "Todos")
                CargarComunidades(comunidades);
            else
                CargarComunidades(comunidades.Where(c => c.Categoria == categoria));
        }

        // --- UNIRME / SALIR ---
        protected void rptComunidades_ItemCommand(object source, RepeaterCommandEventArgs e)
        {
            if (e.CommandName == "ToggleJoin")
            {
                int id = Convert.ToInt32(e.CommandArgument);
                var comunidad = comunidades.FirstOrDefault(c => c.Id == id);
                if (comunidad != null)
                {
                    comunidad.Unido = !comunidad.Unido;
                    comunidad.Miembros += comunidad.Unido ? 1 : -1;
                }
                CargarComunidades(comunidades);
            }
        }

    }
}
