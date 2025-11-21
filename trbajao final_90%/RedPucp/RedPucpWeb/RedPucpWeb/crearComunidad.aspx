<%@ Page Title="Crear comunidad"
    Language="C#"
    MasterPageFile="~/RedPucpMast.Master"
    AutoEventWireup="true"
    CodeBehind="CrearComunidad.aspx.cs"
    Inherits="RedPucpWeb.CrearComunidad" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Crear comunidad
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">

    <div class="container mt-4">
        <div class="card shadow-sm">
            <div class="card-header bg-white">
                <h4 class="mb-0">Nueva comunidad</h4>
            </div>

            <div class="card-body">
                <asp:Label ID="lblMensaje" runat="server" CssClass="text-danger"></asp:Label>

                <!-- NOMBRE -->
                <div class="mb-3">
                    <label for="txtNombre" class="form-label">Nombre de la comunidad</label>
                    <asp:TextBox ID="txtNombre" runat="server"
                        CssClass="form-control"
                        MaxLength="200" />
                </div>

                <!-- DESCRIPCIÓN -->
                <div class="mb-3">
                    <label for="txtDescripcion" class="form-label">Descripción</label>
                    <asp:TextBox ID="txtDescripcion" runat="server"
                        CssClass="form-control"
                        TextMode="MultiLine"
                        Rows="4"
                        MaxLength="500" />
                </div>

            </div>

            <div class="card-footer text-end">
                <asp:Button ID="btnCancelar" runat="server"
                    Text="Cancelar"
                    CssClass="btn btn-outline-secondary"
                    PostBackUrl="~/Feed.aspx" />

                <asp:Button ID="btnCrear" runat="server"
                    Text="Crear comunidad"
                    CssClass="btn btn-primary ms-2"
                    OnClick="btnCrear_Click" />
            </div>
        </div>
    </div>

</asp:Content>
