<%@ Page Title="" Language="C#" MasterPageFile="~/estefano/masterPage/MasterPageAdmin.master" AutoEventWireup="true" CodeBehind="Dashboard.aspx.cs" Inherits="RedPucpWeb.estefano.pantallas.Dashboard" %>
<asp:Content ID="Content1" runat="server" ContentPlaceHolderID="HeadContent">
</asp:Content>

<asp:Content ID="Content2" runat="server" ContentPlaceHolderID="MainContent">

    <h2 class="mb-4">Dashboard general</h2>

    <div class="row g-4">

        <div class="col-md-4">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h5>Total Usuarios</h5>
                    <h3>
                        <asp:Label ID="lblTotalUsuarios" runat="server"></asp:Label>
                    </h3>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h5>Comunidades creadas</h5>
                    <h3>
                        <asp:Label ID="lblComunidades" runat="server"></asp:Label>
                    </h3>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h5>Reportes activos</h5>
                    <h3>
                        <asp:Label ID="lblReportes" runat="server"></asp:Label>
                    </h3>
                </div>
            </div>
        </div>

    </div>

</asp:Content>
