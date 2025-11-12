using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.UI.WebControls;

namespace RedPucpWeb.estefano.pantallas
{
    public partial class ReporteComunidades : System.Web.UI.Page
    {
        // ========== MODELO DEMO ==========
        public class Comunidad
        {
            public int Numero { get; set; }
            public string Nombre { get; set; }
            public string Categoria { get; set; }
            public int Miembros { get; set; }
            public DateTime FechaCreacion { get; set; }
            public string Estado { get; set; }
        }

        // Fuente demo (reemplaza por tu BO/DAO cuando lo conectes a BD)
        private static readonly List<Comunidad> comunidades = new List<Comunidad>
        {
            new Comunidad { Numero=1, Nombre="r/informatica",  Categoria="Ingeniería y Tecnología", Miembros=385, FechaCreacion=new DateTime(2023,03,10), Estado="Activa" },
            new Comunidad { Numero=2, Nombre="r/ee.gg.cc",     Categoria="Ciencias",               Miembros=322, FechaCreacion=new DateTime(2023,05,18), Estado="Inactiva" },
            new Comunidad { Numero=3, Nombre="r/eventosPUCP",  Categoria="Difusión",               Miembros=220, FechaCreacion=new DateTime(2023,09,20), Estado="Inactiva" },
            new Comunidad { Numero=4, Nombre="r/robotica",     Categoria="Ingeniería aplicada",    Miembros=160, FechaCreacion=new DateTime(2024,03,28), Estado="Activa" },
            new Comunidad { Numero=5, Nombre="r/ee.gg.ll",     Categoria="Letras",                 Miembros=150, FechaCreacion=new DateTime(2025,02,18), Estado="Activa" }
        };

        // ========== ESTADO EN VIEWSTATE ==========
        private string Filtro
        {
            get { return (string)(ViewState["filtro"] ?? ""); }
            set { ViewState["filtro"] = value; }
        }

        // Tuple<SortExpression, Ascendente?>
        private Tuple<string, bool> Orden
        {
            get
            {
                var t = ViewState["orden"] as Tuple<string, bool>;
                return t ?? Tuple.Create("Numero", true); // asc por defecto
            }
            set { ViewState["orden"] = value; }
        }

        // ========== CICLO DE VIDA ==========
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Usuario"] == null)
                Response.Redirect("~/estefano/pantallas/Login.aspx");

            if (!IsPostBack)
            {
                CargarCombos();
                CargarTabla();
            }
        }


        // ========== BIND PRINCIPAL ==========
        private void CargarTabla()
        {
            IEnumerable<Comunidad> data = comunidades;

            // 1) Texto libre
            if (!string.IsNullOrWhiteSpace(Filtro))
            {
                string t = Filtro.ToLowerInvariant();
                data = data.Where(c =>
                    c.Nombre.ToLower().Contains(t) ||
                    c.Categoria.ToLower().Contains(t) ||
                    c.Estado.ToLower().Contains(t));
            }

            // 2) Categoría
            if (!string.IsNullOrEmpty(FCat))
                data = data.Where(c => c.Categoria == FCat);

            // 3) Estado
            if (!string.IsNullOrEmpty(FEst))
                data = data.Where(c => string.Equals(c.Estado, FEst, StringComparison.OrdinalIgnoreCase));

            // 4) Miembros
            if (FMin.HasValue) data = data.Where(c => c.Miembros >= FMin.Value);
            if (FMax.HasValue) data = data.Where(c => c.Miembros <= FMax.Value);

            // 5) Rango de fechas (inclusive)
            if (FDesde.HasValue) data = data.Where(c => c.FechaCreacion.Date >= FDesde.Value.Date);
            if (FHasta.HasValue) data = data.Where(c => c.FechaCreacion.Date <= FHasta.Value.Date);

            // Ordenamiento existente (Orden.Item1 / Item2)
            string sortExpr = Orden.Item1;
            bool asc = Orden.Item2;

            Func<Comunidad, object> key;
            switch (sortExpr)
            {
                case "Numero": key = c => c.Numero; break;
                case "Nombre": key = c => c.Nombre; break;
                case "Categoria": key = c => c.Categoria; break;
                case "Miembros": key = c => c.Miembros; break;
                case "FechaCreacion": key = c => c.FechaCreacion; break;
                case "Estado": key = c => c.Estado; break;
                default: key = c => c.Numero; break;
            }
            data = asc ? data.OrderBy(key) : data.OrderByDescending(key);

            gvComunidades.DataSource = data.ToList();
            gvComunidades.DataBind();
        }


        // ========== EVENTOS UI ==========
        protected void btnBuscar_Click(object sender, EventArgs e)
        {
            Filtro = (txtBuscar.Text ?? "").Trim();
            gvComunidades.PageIndex = 0; // vuelve a la primera página al buscar
            CargarTabla();
        }

        protected void gvComunidades_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvComunidades.PageIndex = e.NewPageIndex;
            CargarTabla();
        }

        protected void gvComunidades_Sorting(object sender, GridViewSortEventArgs e)
        {
            var cur = Orden;
            bool asc = (cur.Item1 == e.SortExpression) ? !cur.Item2 : true;
            Orden = Tuple.Create(e.SortExpression, asc);
            CargarTabla();
        }

        // Badges para Estado (compatible C# 7.3)
        protected void gvComunidades_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType != DataControlRowType.DataRow) return;

            int idx = 5; // índice de columna "Estado" (0-based). Ajusta si cambias el orden.
            if (e.Row.Cells.Count <= idx) return;

            TableCell cell = e.Row.Cells[idx];
            string raw = (cell.Text ?? "").Trim().ToLowerInvariant();

            string badge;
            switch (raw)
            {
                case "activa":
                    badge = "<span class='badge bg-success'>Activa</span>";
                    break;
                case "en revisión":
                case "en revision":
                    badge = "<span class='badge bg-warning text-dark'>En revisión</span>";
                    break;
                case "inactiva":
                    badge = "<span class='badge bg-secondary'>Inactiva</span>";
                    break;
                default:
                    badge = "<span class='badge bg-light text-dark'>" + cell.Text + "</span>";
                    break;
            }

            cell.Text = badge; // requiere HtmlEncode="False" en el BoundField de Estado
        }

        // ---- Filtros en ViewState ----
        private string FCat
        {
            get { return (string)(ViewState["f_cat"] ?? ""); }
            set { ViewState["f_cat"] = value; }
        }
        private string FEst
        {
            get { return (string)(ViewState["f_est"] ?? ""); }
            set { ViewState["f_est"] = value; }
        }
        private int? FMin
        {
            get { return ViewState["f_min"] as int?; }
            set { ViewState["f_min"] = value; }
        }
        private int? FMax
        {
            get { return ViewState["f_max"] as int?; }
            set { ViewState["f_max"] = value; }
        }
        private DateTime? FDesde
        {
            get { return ViewState["f_desde"] as DateTime?; }
            set { ViewState["f_desde"] = value; }
        }
        private DateTime? FHasta
        {
            get { return ViewState["f_hasta"] as DateTime?; }
            set { ViewState["f_hasta"] = value; }
        }

        // Llenar combos (llámalo en el primer Load)
        private void CargarCombos()
        {
            // Categorías únicas desde la fuente
            var cats = comunidades.Select(c => c.Categoria).Distinct().OrderBy(s => s).ToList();
            ddlCategoria.Items.Clear();
            ddlCategoria.Items.Add(new ListItem("(Todas)", ""));
            foreach (var c in cats) ddlCategoria.Items.Add(c);
        }

        // Leer UI a ViewState
        private void TomarFiltrosDeUI()
        {
            Filtro = (txtBuscar.Text ?? "").Trim();
            FCat = ddlCategoria.SelectedValue;
            FEst = ddlEstado.SelectedValue;

            int v;
            FMin = int.TryParse(txtMinMiembros.Text, out v) ? (int?)v : null;
            FMax = int.TryParse(txtMaxMiembros.Text, out v) ? (int?)v : null;

            DateTime d;
            FDesde = DateTime.TryParse(txtDesde.Text, out d) ? (DateTime?)d : null;
            FHasta = DateTime.TryParse(txtHasta.Text, out d) ? (DateTime?)d : null;
        }

        // Escribir ViewState a UI (para “Limpiar” o recargar)
        private void PonerFiltrosEnUI()
        {
            txtBuscar.Text = Filtro;
            ddlCategoria.SelectedValue = FCat ?? "";
            ddlEstado.SelectedValue = FEst ?? "";
            txtMinMiembros.Text = FMin?.ToString() ?? "";
            txtMaxMiembros.Text = FMax?.ToString() ?? "";
            txtDesde.Text = FDesde?.ToString("yyyy-MM-dd") ?? "";
            txtHasta.Text = FHasta?.ToString("yyyy-MM-dd") ?? "";
        }


        protected void btnBuscarAvanzado_Click(object sender, EventArgs e)
        {
            TomarFiltrosDeUI();
            gvComunidades.PageIndex = 0;
            CargarTabla();
        }

        protected void btnLimpiar_Click(object sender, EventArgs e)
        {
            // Resetea filtros
            Filtro = ""; FCat = ""; FEst = ""; FMin = null; FMax = null; FDesde = null; FHasta = null;
            PonerFiltrosEnUI();
            gvComunidades.PageIndex = 0;
            CargarTabla();
        }

    }

}
