<%@ Page Title="Reporte de Comunidades - RedPUCP" Language="C#"
    MasterPageFile="~/estefano/masterPage/MasterPage.master"
    AutoEventWireup="true"
    CodeBehind="ReporteComunidades.aspx.cs"
    Inherits="RedPucpWeb.estefano.pantallas.ReporteComunidades" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container mt-5 mb-5">

        <!-- Encabezado -->
        <h2 class="text-center fw-bold mb-4">
            <i class="fa fa-table"></i>Reporte de Comunidades
        </h2>

        <!-- Filtro de búsqueda -->
        <div class="row mb-4 justify-content-center">
            <div class="col-md-6">
                <div class="input-group">
                    <span class="input-group-text bg-light"><i class="fa fa-search"></i></span>
                    <asp:TextBox ID="txtBuscar" runat="server" CssClass="form-control" Placeholder="Buscar comunidad o categoría..."></asp:TextBox>
                    <asp:Button ID="btnBuscar" runat="server" Text="Buscar" CssClass="btn btn-dark" OnClick="btnBuscar_Click" />
                </div>
            </div>
        </div>

        <!-- Filtros avanzados -->
        <div class="row g-2 mb-3">
            <div class="col-md-3">
                <label class="form-label small">Categoría</label>
                <asp:DropDownList ID="ddlCategoria" runat="server" CssClass="form-select" AutoPostBack="false" />
            </div>
            <div class="col-md-3">
                <label class="form-label small">Estado</label>
                <asp:DropDownList ID="ddlEstado" runat="server" CssClass="form-select" AutoPostBack="false">
                    <asp:ListItem Text="(Todos)" Value="" />
                    <asp:ListItem>Activa</asp:ListItem>
                    <asp:ListItem>En revisión</asp:ListItem>
                    <asp:ListItem>Inactiva</asp:ListItem>
                </asp:DropDownList>
            </div>
            <div class="col-md-3">
                <label class="form-label small">Miembros (mín.)</label>
                <asp:TextBox ID="txtMinMiembros" runat="server" CssClass="form-control" TextMode="Number" />
            </div>
            <div class="col-md-3">
                <label class="form-label small">Miembros (máx.)</label>
                <asp:TextBox ID="txtMaxMiembros" runat="server" CssClass="form-control" TextMode="Number" />
            </div>
        </div>

        <div class="row g-2 mb-4">
            <div class="col-md-3">
                <label class="form-label small">Fecha desde</label>
                <asp:TextBox ID="txtDesde" runat="server" CssClass="form-control" TextMode="Date" />
            </div>
            <div class="col-md-3">
                <label class="form-label small">Fecha hasta</label>
                <asp:TextBox ID="txtHasta" runat="server" CssClass="form-control" TextMode="Date" />
            </div>
            <div class="col-md-6 d-flex align-items-end gap-2">
                <asp:Button ID="btnBuscarAvanzado" runat="server" Text="Buscar" CssClass="btn btn-dark" OnClick="btnBuscarAvanzado_Click" />
                <asp:Button ID="btnLimpiar" runat="server" Text="Limpiar" CssClass="btn btn-outline-secondary" OnClick="btnLimpiar_Click" />
            </div>
        </div>


        <!-- Tabla -->
        <asp:GridView ID="gvComunidades" runat="server" AutoGenerateColumns="False"
            CssClass="table table-hover table-bordered align-middle text-center shadow-sm"
            HeaderStyle-BackColor="#f8f9fa" HeaderStyle-Font-Bold="true"
            HeaderStyle-ForeColor="#212529" BorderColor="#dee2e6" GridLines="None"
            AllowPaging="true" PageSize="5" OnPageIndexChanging="gvComunidades_PageIndexChanging"
            AllowSorting="true" OnSorting="gvComunidades_Sorting"
            OnRowDataBound="gvComunidades_RowDataBound">

            <Columns>
                <asp:BoundField DataField="Numero" HeaderText="N°" SortExpression="Numero" />
                <asp:BoundField DataField="Nombre" HeaderText="Comunidad" SortExpression="Nombre" />
                <asp:BoundField DataField="Categoria" HeaderText="Categoría" SortExpression="Categoria" />
                <asp:BoundField DataField="Miembros" HeaderText="Miembros" SortExpression="Miembros" />
                <asp:BoundField DataField="FechaCreacion" HeaderText="Fecha de creación"
                    DataFormatString="{0:dd/MM/yyyy}" HtmlEncode="False" SortExpression="FechaCreacion" />
                <asp:BoundField DataField="Estado" HeaderText="Estado" SortExpression="Estado" HtmlEncode="False" />
            </Columns>
        </asp:GridView>



    </div>
</asp:Content>
