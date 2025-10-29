<%@ Page Title="Reporte de Comunidades - RedPUCP" Language="C#" 
    MasterPageFile="~/estefano/masterPage/MasterPage.master" 
    AutoEventWireup="true" 
    CodeBehind="ReporteComunidades.aspx.cs" 
    Inherits="RedPucpWeb.estefano.pantallas.ReporteComunidades" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container mt-5 mb-5">

        <!-- Encabezado -->
        <h2 class="text-center fw-bold mb-4">
            <i class="fa fa-table"></i> Reporte de Comunidades
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

        <!-- Tabla -->
        <asp:GridView ID="gvComunidades" runat="server" AutoGenerateColumns="False"
            CssClass="table table-hover table-bordered align-middle text-center shadow-sm"
            HeaderStyle-BackColor="#f8f9fa" HeaderStyle-Font-Bold="true" 
            HeaderStyle-ForeColor="#212529" BorderColor="#dee2e6" GridLines="None">
            <Columns>
                <asp:BoundField DataField="Numero" HeaderText="N°" />
                <asp:BoundField DataField="Comunidad" HeaderText="Comunidad" />
                <asp:BoundField DataField="Categoria" HeaderText="Categoría" />
                <asp:BoundField DataField="Miembros" HeaderText="Miembros" />
                <asp:BoundField DataField="FechaCreacion" HeaderText="Fecha de creación" />
                <asp:BoundField DataField="Estado" HeaderText="Estado" />
            </Columns>
        </asp:GridView>

    </div>
</asp:Content>
