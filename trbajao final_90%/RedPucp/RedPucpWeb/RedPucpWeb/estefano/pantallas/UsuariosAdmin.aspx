<%@ Page Title="" Language="C#" MasterPageFile="~/estefano/masterPage/MasterPageAdmin.master" AutoEventWireup="true" CodeBehind="UsuariosAdmin.aspx.cs" Inherits="RedPucpWeb.estefano.pantallas.UsuariosAdmin" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">

    <h2 class="titulo-seccion">Gestión de Usuarios</h2>

    <div class="contenedor-acciones">
        <asp:TextBox ID="txtBuscar" runat="server" CssClass="input-buscar" 
            Placeholder="Buscar por nombre o correo..."></asp:TextBox>

        <asp:Button ID="btnBuscar" runat="server" CssClass="btn-primario" Text="Buscar" OnClick="btnBuscar_Click" />

        <asp:DropDownList ID="ddlEstado" runat="server" CssClass="input-buscar">
            <asp:ListItem Text="-- Estado --" Value=""></asp:ListItem>
            <asp:ListItem Text="Activo" Value="1"></asp:ListItem>
            <asp:ListItem Text="Bloqueado" Value="2"></asp:ListItem>
        </asp:DropDownList>

        <asp:Label ID="lblMensaje" runat="server" CssClass="text-danger"></asp:Label>

        <asp:Button ID="btnReporte" runat="server" CssClass="btn-secundario"
            Text="Generar Reporte" OnClick="btnReporte_Click" />

        <asp:Button ID="btnNuevo" runat="server" CssClass="btn-secundario" Text="Nuevo Usuario" 
            OnClick="btnNuevo_Click" />
    </div>

    <asp:GridView ID="gvUsuarios" runat="server" CssClass="tabla"
        AutoGenerateColumns="False" AllowPaging="True" PageSize="10"
        OnPageIndexChanging="gvUsuarios_PageIndexChanging">

        <Columns>
            <asp:BoundField DataField="idUsuario" HeaderText="ID" />
            <asp:BoundField DataField="nombre" HeaderText="Nombre" />
            <asp:BoundField DataField="email" HeaderText="Correo" />
            <asp:BoundField DataField="descripcion" HeaderText="Descripción" />
            <asp:BoundField DataField="karma" HeaderText="Karma" />
            <asp:BoundField DataField="tipousuario" HeaderText="Tipo" />
            <asp:BoundField DataField="estadouser" HeaderText="Estado" />

            <asp:TemplateField HeaderText="Acciones">
                <ItemTemplate>
                    <asp:LinkButton ID="btnEditar" runat="server" Text="Editar"
                        CommandName="Editar" CommandArgument='<%# Eval("idUsuario") %>'
                        CssClass="btn-accion" />

                    <asp:LinkButton ID="btnEliminar" runat="server" Text="Eliminar"
                        CommandName="Eliminar" CommandArgument='<%# Eval("idUsuario") %>'
                        CssClass="btn-accion-eliminar" />
                </ItemTemplate>
            </asp:TemplateField>
        </Columns>
    </asp:GridView>

</asp:Content>
