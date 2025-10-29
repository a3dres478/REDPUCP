<%@ Page Title="Comunidades - RedPUCP" Language="C#" MasterPageFile="~/estefano/masterPage/MasterPage.master"
AutoEventWireup="true" CodeBehind="Comunidades.aspx.cs" Inherits="RedPucpWeb.estefano.pantallas.Comunidades" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container mt-5">
        <h2 class="text-center fw-bold mb-4">
            <i class="fa fa-users"></i> Explorar Comunidades
        </h2>

        <!-- FILTRO DE CATEGORÍA -->
        <div class="row justify-content-center mb-4">
            <div class="col-md-6 text-center">
                <label class="form-label fw-semibold">Filtrar por categoría</label>
                <asp:DropDownList ID="ddlCategoria" runat="server" CssClass="form-select text-center"
                    AutoPostBack="true" OnSelectedIndexChanged="ddlCategoria_SelectedIndexChanged">
                    <asp:ListItem>Todos</asp:ListItem>
                    <asp:ListItem>Tecnología</asp:ListItem>
                    <asp:ListItem>Gamer</asp:ListItem>
                    <asp:ListItem>Estudio</asp:ListItem>
                    <asp:ListItem>Difusión</asp:ListItem>
                    <asp:ListItem>Random</asp:ListItem>
                </asp:DropDownList>
            </div>
        </div>

        <!-- LISTADO DE COMUNIDADES -->
        <div class="row">
            <asp:Repeater ID="rptComunidades" runat="server" OnItemCommand="rptComunidades_ItemCommand">
                <ItemTemplate>
                    <div class="col-md-4 mb-4">
                        <div class="card shadow-sm h-100">
                            <div class="card-body">
                                <h5 class="fw-bold mb-1"><%# Eval("Nombre") %></h5>
                                <p class="text-muted mb-2"><i class="fa fa-tag"></i> <%# Eval("Categoria") %></p>
                                <p class="mb-2"><i class="fa fa-users"></i> <%# Eval("Miembros") %> miembros</p>
                                <p class="small text-secondary"><%# Eval("Descripcion") %></p>
                                <asp:Button ID="btnUnirme" runat="server" CommandName="ToggleJoin"
                                    CommandArgument='<%# Eval("Id") %>'
                                    Text='<%# (bool)Eval("Unido") ? "Salir" : "Unirme" %>'
                                    CssClass='<%# (bool)Eval("Unido") ? "btn btn-outline-danger w-100" : "btn btn-dark w-100" %>' />
                            </div>
                        </div>
                    </div>
                </ItemTemplate>
            </asp:Repeater>
        </div>

        <!-- SECCIÓN DE REPORTE -->
        <hr class="my-5" />

        <div class="text-center mb-4">
            <asp:Button ID="btnGenerarReporte" runat="server" Text="Generar Reporte de Comunidades"
                CssClass="btn btn-dark btn-lg" OnClick="btnGenerarReporte_Click" />
        </div>

        <!-- PANEL DE REPORTE -->
        <asp:Panel ID="pnlReporte" runat="server" Visible="false" CssClass="container mt-4">
            <h3 class="text-center fw-bold mb-3">
                <i class="fa fa-table"></i> Reporte General de Comunidades
            </h3>

            <asp:GridView ID="gvReporteComunidades" runat="server" AutoGenerateColumns="False"
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
        </asp:Panel>

    </div>
</asp:Content>
