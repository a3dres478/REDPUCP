<%@ Page Title="" Language="C#" MasterPageFile="~/estefano/masterPage/MasterPageAdmin.master" AutoEventWireup="true" CodeBehind="ComunidadAdmin.aspx.cs" Inherits="RedPucpWeb.estefano.pantallas.ComunidadAdmin" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">

    <h2 class="titulo-seccion">Gestión de Comunidades</h2>

    <div class="contenedor-acciones">

        <asp:TextBox ID="txtBuscar" runat="server" CssClass="input-buscar"
            Placeholder="Buscar por nombre..."></asp:TextBox>

        <asp:Button ID="btnBuscar" runat="server" CssClass="btn-primario" Text="Buscar"
            OnClick="btnBuscar_Click" />

        <asp:DropDownList ID="ddlEstado" runat="server" CssClass="input-buscar">
            <asp:ListItem Text="-- Estado --" Value="" />
            <asp:ListItem Text="Activas" Value="1" />
            <asp:ListItem Text="Inactivas" Value="2" />
        </asp:DropDownList>

        <asp:Label ID="lblMensaje" runat="server" CssClass="text-danger"></asp:Label>

        <asp:Button ID="btnReporte" runat="server" CssClass="btn-primario"
            Text="Generar Reporte" OnClick="btnReporte_Click" />

        <asp:Button ID="btnNueva" runat="server" CssClass="btn-secundario"
            Text="Nueva Comunidad" OnClick="btnNuevo_Click" />

    </div>

    <asp:GridView ID="gvComunidades" runat="server" CssClass="tabla"
        AutoGenerateColumns="False" AllowPaging="True" PageSize="10"
        OnPageIndexChanging="gvComunidades_PageIndexChanging"
        OnRowCommand="gvComunidades_RowCommand">

        <Columns>

            <asp:BoundField DataField="id_comunidad" HeaderText="ID" />
            <asp:BoundField DataField="nombre" HeaderText="Nombre" />
            <asp:BoundField DataField="descripcion" HeaderText="Descripción" />
            <asp:BoundField DataField="estado" HeaderText="Estado" />
            <asp:BoundField DataField="fecha_creacion" HeaderText="Fecha Creación" DataFormatString="{0:yyyy-MM-dd}" />

            <asp:TemplateField HeaderText="Acciones">
                <ItemTemplate>

                    <asp:LinkButton ID="btnEditar" runat="server" Text="Editar"
                        CommandName="Editar" CommandArgument='<%# Eval("id_comunidad") %>'
                        CssClass="btn-accion" />

                    <asp:LinkButton ID="btnEliminar" runat="server" Text="Eliminar"
                        CommandName="Eliminar" CommandArgument='<%# Eval("id_comunidad") %>'
                        CssClass="btn-accion-eliminar" />

                </ItemTemplate>
            </asp:TemplateField>

        </Columns>

    </asp:GridView>

</asp:Content>

