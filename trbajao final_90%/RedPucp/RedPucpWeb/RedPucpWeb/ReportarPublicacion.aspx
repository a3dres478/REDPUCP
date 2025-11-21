<%@ Page Title="Reportar publicación - REDPUCP"
    Language="C#"
    MasterPageFile="~/RedPucpMast.Master"
    AutoEventWireup="true"
    CodeBehind="ReportarPublicacion.aspx.cs"
    Inherits="RedPucpWeb.ReportarPublicacion" %>

<asp:Content ID="t" ContentPlaceHolderID="cphTitulo" runat="server">
    Reportar publicación
</asp:Content>

<asp:Content ID="c" ContentPlaceHolderID="cphContenido" runat="server">

    <!-- Aviso de éxito -->
    <div id="alertOk" runat="server" class="alert alert-success d-none" role="alert">
        ¡Gracias! Tu reporte ha sido enviado para revisión.
    </div>

    <asp:Label ID="lblMensaje" runat="server" CssClass="text-danger"></asp:Label>

    <div class="row justify-content-center mt-2">
        <div class="col-12 col-lg-8">
            <div class="card shadow-sm">
                <div class="card-header fw-semibold">Reportar publicación</div>

                <div class="card-body">

                    <!-- Preview del post REAL -->
                    <div class="mb-4 p-3 rounded border bg-light">
                        <div class="small text-muted mb-1">Publicación a reportar</div>
                        <h5 class="mb-1">
                            <asp:Label ID="lblTitulo" runat="server" />
                        </h5>
                        <div class="text-muted small">
                            Autor:
                            <asp:Label ID="lblAutor" runat="server" />
                            &nbsp;·&nbsp; Comunidad:
                            <asp:Label ID="lblComunidad" runat="server" />
                        </div>
                    </div>

                    <!-- Formulario de reporte -->
                    <div class="mb-3">
                        <label class="form-label" for="ddlMotivo">Motivo</label>
                        <asp:DropDownList ID="ddlMotivo" runat="server" CssClass="form-select">
                            <asp:ListItem Text="Seleccione un motivo" Value="" />
                            <asp:ListItem Text="Spam o publicidad no deseada" Value="SPAM" />
                            <asp:ListItem Text="Lenguaje ofensivo o de odio" Value="ODIO" />
                            <asp:ListItem Text="Plagio o infracción de derechos" Value="PLAGIO" />
                            <asp:ListItem Text="Información engañosa" Value="ENGANOSA" />
                            <asp:ListItem Text="Contenido NSFW" Value="NSFW" />
                            <asp:ListItem Text="Otro" Value="OTRO" />
                        </asp:DropDownList>
                    </div>

                    <div class="mb-3">
                        <label class="form-label" for="txtDetalles">Detalles (opcional)</label>
                        <asp:TextBox ID="txtDetalles" runat="server"
                            TextMode="MultiLine" Rows="5"
                            CssClass="form-control"
                            placeholder="Explica brevemente por qué consideras que debe reportarse..." />
                    </div>

                

                    <asp:HiddenField ID="hfIdPublicacion" runat="server" />

                    <div class="d-flex gap-2">
                        <asp:HyperLink ID="lnkCancelar" runat="server"
                            CssClass="btn btn-outline-dark">
                            Cancelar
                        </asp:HyperLink>

                        <asp:Button ID="btnEnviar" runat="server"
                            CssClass="btn btn-dark"
                            Text="Enviar reporte"
                            OnClick="btnEnviar_Click" />
                    </div>

                </div>
            </div>
        </div>
    </div>

</asp:Content>
