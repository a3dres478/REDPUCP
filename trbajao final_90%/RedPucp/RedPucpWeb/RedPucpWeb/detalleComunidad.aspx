<%@ Page Title="Detalle de comunidad"
    Language="C#"
    MasterPageFile="~/RedPucpMast.Master"
    AutoEventWireup="true"
    CodeBehind="DetalleComunidad.aspx.cs"
    Inherits="RedPucpWeb.DetalleComunidad" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Detalle de comunidad
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">

    <div class="container mt-4">
        <asp:Label ID="lblMensaje" runat="server" CssClass="text-danger"></asp:Label>

        <asp:Panel ID="pnlComunidad" runat="server" CssClass="card shadow-sm" Visible="false">
            <div class="card-header bg-white">
                <h3 class="mb-0">
                    <asp:Label ID="lblNombre" runat="server" />
                </h3>
            </div>

            <div class="card-body">
                <p class="mb-3">
                    <asp:Label ID="lblDescripcion" runat="server" />
                </p>

                <dl class="row mb-0">
                    <dt class="col-sm-3">Administrador</dt>
                    <dd class="col-sm-9">
                        <asp:Label ID="lblAdmin" runat="server" />
                    </dd>

                    <dt class="col-sm-3">Fecha de creación</dt>
                    <dd class="col-sm-9">
                        <asp:Label ID="lblFecha" runat="server" />
                    </dd>

                    <dt class="col-sm-3">Miembros</dt>
                    <dd class="col-sm-9">
                        <asp:Label ID="lblMiembros" runat="server" />
                    </dd>
                </dl>
            </div>

            <div class="card-footer d-flex justify-content-between align-items-center">
                <asp:HyperLink ID="lnkVolver" runat="server"
                    NavigateUrl="~/Feed.aspx"
                    CssClass="btn btn-outline-secondary">
                    ← Volver al feed
                </asp:HyperLink>

                <asp:Button ID="btnUnirme" runat="server"
                    Text="Unirme a la comunidad"
                    CssClass="btn btn-primary"
                    OnClick="btnUnirme_Click" />
            </div>
        </asp:Panel>

        <!-- Mensaje de éxito al unirse -->
        <asp:Label ID="lblUnido" runat="server"
            CssClass="mt-3 d-block text-success"
            Visible="false" />

        <asp:HiddenField ID="hfIdComunidad" runat="server" />

        <!-- SECCIÓN: publicaciones de la comunidad -->
        <asp:Panel ID="pnlPublicaciones" runat="server" CssClass="card shadow-sm mt-4" Visible="false">
            <div class="card-header bg-white">
                <h5 class="mb-0">Publicaciones de esta comunidad</h5>
            </div>
            <div class="card-body">

                <asp:Label ID="lblSinPublicaciones" runat="server"
                    CssClass="text-muted" Visible="false"
                    Text="Esta comunidad aún no tiene publicaciones." />

                <asp:Repeater ID="rptPublicacionesComunidad" runat="server">
                    <ItemTemplate>
                        <div class="card mb-3 border">
                            <div class="card-header bg-white">
                                <h6 class="mb-1">
                                    <a href='<%# "plantillaDetallePublicacion.aspx?id=" + Eval("id") %>'
                                        class="text-decoration-none text-dark">
                                        <%# Eval("titulo") %>
                                    </a>
                                </h6>
                                <small class="text-muted d-block mb-2">
                                    <%# Eval("fechaCreacion", "{0:dd/MM/yyyy}") %> ·
                    <%# Eval("categoria") %>
                                </small>
                                <p class="mb-2">
                                    <%# Eval("descripcion") %>
                                </p>

                            </div>
                            <div class="card-body">
                                 <!-- imagen de la publicación (si tiene) -->
                                 <asp:image id="imgpublicacion" runat="server"
                                     imageurl='<%# Eval("imagenUrl") %>'
     
                                     visible='<%# !Convert.ToString(Eval("imagenUrl")).Equals("Sin_imagen") %>'
                                     cssclass="img-fluid mb-2" />

                                 <%--<p class="mb-2">
                                     <%# eval("descripcion") %>
                                 </p>--%>
                             </div>
                            <div class="card-footer">
                                <small class="text-muted">▲ <%# Eval("votosPositivos") %> &nbsp;&nbsp;
                    ▼ <%# Eval("votosNegativos") %>
                                </small>
                            </div>
                        </div>
                    </ItemTemplate>
                </asp:Repeater>


            </div>
        </asp:Panel>
    </div>

</asp:Content>
