<%@ Page Title="" Language="C#" MasterPageFile="~/RedPucpMast.Master"
    AutoEventWireup="true" CodeBehind="Feed.aspx.cs" Inherits="RedPucpWeb.Feed" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Publicaciones Recientes
</asp:Content>




<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">

    <div class="container mt-4">
        <div class="row">

            <!-- COLUMNA PRINCIPAL -->
            <div class="col-lg-8">

                <asp:Repeater ID="rptPublicaciones" runat="server">
                    <ItemTemplate>

                        <article class="card shadow-sm mb-3">
                            <div class="card-header bg-white">
                                <h5 class="mb-1">
                                    <a href='<%# "plantillaDetallePublicacion.aspx?id=" + Eval("id") %>'
                                        class="text-decoration-none text-dark">
                                        <%# Eval("titulo") %>
                                    </a>

                                </h5>

                                <div class="text-muted small">
                                    <%# Eval("fechaCreacion", "{0:dd/MM/yyyy}") %>
                                ·
                                <%# Eval("categoria") %>
                                </div>
                            </div>

                            <!-- visible='<# !string.IsNullOrEmpty(Convert.ToString(Eval("imagenUrl"))) %>'  -->

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
                                ▲ <%# Eval("votosPositivos") %>
                            &nbsp;&nbsp;
                            ▼ <%# Eval("votosNegativos") %>
                            </div>
                        </article>

                    </ItemTemplate>
                </asp:Repeater>

            </div>

            <!-- SIDEBAR -->
            <div class="col-lg-4">
                <div class="card shadow-sm">
                    <div class="card-header fw-semibold">
                        Comunidades
                    </div>
                    <div class="card-body">
                        <asp:Repeater ID="rptComunidades" runat="server">
                            <ItemTemplate>
                                <a href='<%# "DetalleComunidad.aspx?idComunidad=" + Eval("id_comunidad") %>'
                                    class="btn btn-outline-secondary btn-sm w-100 mb-2 text-start">
                                    <%# Eval("nombre") %>
                                </a>

                            </ItemTemplate>
                        </asp:Repeater>

                        <asp:Label ID="lblMensajeComunidades" runat="server"
                            CssClass="text-danger small"></asp:Label>
                    </div>
                </div>
            </div>

        </div>
    </div>

</asp:Content>
