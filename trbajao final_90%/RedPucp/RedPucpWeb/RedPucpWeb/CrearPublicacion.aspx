<%@ Page Title="Crear publicación"
    Language="C#"
    MasterPageFile="~/RedPucpMast.Master"
    AutoEventWireup="true"
    CodeBehind="CrearPublicacion.aspx.cs"
    Inherits="RedPucpWeb.CrearPublicacion" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Crear Publicación
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">

    <div class="container mt-4">
        <div class="card shadow-sm">
            <div class="card-header bg-white">
                <h3 class="mb-0">Crear nueva publicación</h3>
            </div>

            <div class="card-body">

                <!-- TÍTULO -->
                <div class="mb-3">
                    <label for="txtTitulo" class="form-label">Título</label>
                    <asp:TextBox ID="txtTitulo" runat="server" CssClass="form-control" />
                </div>

                <!-- CONTENIDO -->
                <div class="mb-3">
                    <label for="txtContenido" class="form-label">Contenido</label>
                    <asp:TextBox ID="txtContenido" runat="server" TextMode="MultiLine"
                                 Rows="5" CssClass="form-control" />
                </div>

                <!-- COMUNIDAD -->
                <div class="mb-3">
                    <label for="ddlComunidad" class="form-label">Comunidad</label>
                    <asp:DropDownList ID="ddlComunidad" runat="server" CssClass="form-select" />
                </div>

                <!-- CATEGORÍA -->
                <div class="mb-3">
                    <label for="ddlCategoria" class="form-label">Categoría</label>
                    <asp:DropDownList ID="ddlCategoria" runat="server" CssClass="form-select">
                        <asp:ListItem Text="Académico" Value="ACADEMICO" />
                        <asp:ListItem Text="Entretenimiento" Value="ENTRETENIMIENTO" />
                        <asp:ListItem Text="Ayuda" Value="AYUDA" />
                    </asp:DropDownList>
                </div>

                <!-- CARGA DE IMAGEN -->
                <div class="mb-3">
                    <label for="fuImagen" class="form-label">Imagen (opcional)</label>
                    <asp:FileUpload ID="fuImagen" runat="server" CssClass="form-control" />
                    <div class="form-text">
                        Formatos permitidos: JPG, PNG, WebP (máx. 10MB).
                    </div>
                </div>

                <asp:Label ID="lblMensaje" runat="server" CssClass="text-danger"></asp:Label>

            </div>

            <div class="card-footer text-end">
                <asp:Button ID="btnCancelar" runat="server" Text="Cancelar"
                    CssClass="btn btn-outline-secondary" PostBackUrl="~/Feed.aspx" />
                <asp:Button ID="btnPublicar" runat="server" Text="Publicar"
                    CssClass="btn btn-primary ms-2" OnClick="btnPublicar_Click" />
            </div>
        </div>
    </div>

</asp:Content>
