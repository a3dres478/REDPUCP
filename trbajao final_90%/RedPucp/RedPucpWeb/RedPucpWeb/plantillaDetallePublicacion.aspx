<%@ Page Title="" Language="C#" MasterPageFile="~/RedPucpMast.Master"
    AutoEventWireup="true" CodeBehind="plantillaDetallePublicacion.aspx.cs"
    Inherits="RedPucpWeb.plantillaDetallePublicacion" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Detalle de publicación
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="container mt-4">

        <asp:Label ID="lblMensaje" runat="server" CssClass="text-danger"></asp:Label>
        <asp:HiddenField ID="hfIdPublicacion" runat="server" />

        <div class="card shadow-sm">
            <div class="card-header bg-white">
                <h3 class="mb-0">
                    <asp:Label ID="lblTitulo" runat="server" />
                </h3>
            </div>

            <div class="card-body">
                <p class="mb-3">
                    <asp:Label ID="lblDescripcion" runat="server" />
                </p>

                <!-- Imagen -->
                <div class="mb-3">
                    <asp:Image ID="imgPublicacion" runat="server" CssClass="img-fluid" Visible="false" />
                </div>

                <!-- Info comunidad / autor / fecha -->
                <div class="mb-2">
                    <strong>Comunidad: </strong><asp:Label ID="lblComunidad" runat="server" />
                </div>
                <div class="mb-2">
                    <strong>Autor: </strong><asp:Label ID="lblAutor" runat="server" />
                </div>
                <div class="mb-3">
                    <strong>Fecha de creación: </strong><asp:Label ID="lblFecha" runat="server" />
                </div>

                <!-- REACCIONES -->
                <div class="mb-4 d-flex align-items-center flex-wrap">
                    <asp:LinkButton ID="btnUpvote" runat="server"
                        CssClass="btn btn-sm btn-outline-success me-2 mb-2"
                        OnClick="btnUpvote_Click">
                        ▲ Me gusta
                    </asp:LinkButton>

                    <asp:LinkButton ID="btnDownvote" runat="server"
                        CssClass="btn btn-sm btn-outline-danger me-3 mb-2"
                        OnClick="btnDownvote_Click">
                        ▼ No me gusta
                    </asp:LinkButton>

                    <asp:LinkButton ID="btnQuitarVoto" runat="server"
                        CssClass="btn btn-sm btn-outline-dark me-3 mb-2"
                        OnClick="btnQuitarVoto_Click">
                        Quitar voto
                    </asp:LinkButton>

                    <span class="me-3 mb-2">
                        <strong>+ </strong><asp:Label ID="lblUpvotes" runat="server" Text="0" />
                        &nbsp;/&nbsp;
                        <strong>- </strong><asp:Label ID="lblDownvotes" runat="server" Text="0" />
                    </span>

                </div>
                <div class="mb-4 d-flex align-items-center flex-wrap">
                    <asp:LinkButton ID="btnReportar" runat="server"
                        CssClass="btn btn-sm btn-outline-warning mb-2"
                        OnClick="btnReportar_Click"
                        OnClientClick="return confirm('¿Seguro que quieres reportar esta publicación?');">
                        Reportar
                    </asp:LinkButton>
                </div>

                <!-- SEPARADOR -->
                <hr class="my-4" />

                <!-- COMENTARIOS -->
                <h5 class="mt-3 mb-3">Comentarios</h5>

                <asp:Repeater ID="rptComentarios" runat="server" OnItemCommand="rptComentarios_ItemCommand">
                    <ItemTemplate>
                        <div class="card mb-3">
                            <div class="card-header">
                                <h6 class="mb-0">
                                    <strong><%# Eval("autor.nombre") %></strong>
                                </h6>
                                <small class="text-muted">
                                    <%# Eval("fechaCreacion", "{0:dd/MM/yyyy}") %>
                                </small>
                            </div>
                            <div class="card-body">
                                <p class="mt-2 mb-0">
                                    <%# Eval("contenido") %>
                                </p>
                                 <!-- imagen del comentario (si tiene) -->
                                 <asp:image id="imgComentario" runat="server"
                                     imageurl='<%# Eval("imagenUrl") %>'
     
                                     visible='<%# !Convert.ToString(Eval("imagenUrl")).Equals("Sin_imagen") %>'
                                     cssclass="img-fluid mb-2" />

                                 <%--<p class="mb-2">
                                     <%# eval("descripcion") %>
                                 </p>--%>
                             </div>
                            <div class="card-footer">
                                <!-- BOTONES DE VOTO PARA COMENTARIO -->
                                <div class="d-flex align-items-center flex-wrap">
                                    <asp:LinkButton ID="btnUpvoteComentario" runat="server"
                                        CssClass="btn btn-sm btn-outline-success me-2 mb-2"
                                        CommandName="Upvote"
                                        CommandArgument='<%# Eval("id") %>'>
                                        ▲ Me gusta
                                    </asp:LinkButton>

                                    <asp:LinkButton ID="btnDownvoteComentario" runat="server"
                                        CssClass="btn btn-sm btn-outline-danger me-3 mb-2"
                                        CommandName="Downvote"
                                        CommandArgument='<%# Eval("id") %>'>
                                        ▼ No me gusta
                                    </asp:LinkButton>

                                    <asp:LinkButton ID="btnQuitarVotoComentario" runat="server"
                                        CssClass="btn btn-sm btn-outline-dark me-3 mb-2"
                                        CommandName="QuitarVoto"
                                        CommandArgument='<%# Eval("id") %>'>
                                        Quitar voto
                                    </asp:LinkButton>

                                    <span class="me-3 mb-2">
                                        <strong>+ </strong><asp:Label ID="lblUpvotesComentario" runat="server" 
                                            Text='<%# Eval("votosPositivos") %>' />
                                        &nbsp;/&nbsp;
                                        <strong>- </strong><asp:Label ID="lblDownvotesComentario" runat="server" 
                                            Text='<%# Eval("votosNegativos") %>' />
                                    </span>
                                </div>
                            </div>
                        </div>
                    </ItemTemplate>
                </asp:Repeater>

                <asp:Label ID="lblSinComentarios" runat="server"
                    CssClass="text-muted" Text="No hay comentarios aún."
                    Visible="false" />

                <!-- NUEVO COMENTARIO -->
                <div class="mt-4 card-body">
                    <strong>Agregar un comentario</strong>
                    <div class="mb-3">
                        <textarea id="txtComentario" runat="server"
                                  class="form-control"
                                  placeholder="Escribe tu comentario..."></textarea>
                    </div>
                    <!-- CARGA DE IMAGEN -->
                    <div class="mb-3">
                        <label for="fuImagen" class="form-label">Imagen (opcional)</label>
                        <asp:FileUpload ID="fuImagen" runat="server" CssClass="form-control" />
                        <div class="form-text">
                            Formatos permitidos: JPG, PNG, WebP (máx. 10MB).
                        </div>
                    </div>
                    <asp:Button ID="btnComentar" runat="server"
                        Text="Comentar"
                        CssClass="btn btn-primary"
                        OnClick="btnComentar_Click" />
                </div>
            </div>

            <div class="card-footer d-flex justify-content-between align-items-center">
                <asp:HyperLink ID="lnkVolver" runat="server"
                    NavigateUrl="~/Feed.aspx"
                    CssClass="btn btn-outline-secondary">
                    ← Volver al feed
                </asp:HyperLink>
            </div>
        </div>
    </div>
</asp:Content>
