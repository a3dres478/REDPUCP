<%@ Page Title="Mis_publicaciones"
    Language="C#"
    MasterPageFile="~/RedPucpMast.Master"
    AutoEventWireup="true"
    CodeBehind="Mis_publicaciones.aspx.cs"
    Inherits="RedPucpWeb.MisPublicaciones" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Mis publicaciones
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">

    <div class="container mt-4">
        <asp:Label ID="lblMensaje" runat="server" CssClass="text-danger"></asp:Label>

        <asp:Panel ID="pnlMisPublicaciones" runat="server" CssClass="card shadow-sm" Visible="false">
            <div class="card-header bg-white d-flex justify-content-between align-items-center">
                <h4 class="mb-0">Publicaciones creadas por ti</h4>
            </div>

            <div class="card-body">

                <asp:Label ID="lblSinPublicaciones" runat="server"
                    CssClass="text-muted" Visible="false"
                    Text="Aún no has creado publicaciones." />

                <asp:Repeater ID="rptMisPublicaciones" runat="server"
                    OnItemCommand="rptMisPublicaciones_ItemCommand">
                    <ItemTemplate>
                        <div class="card mb-3 border">
                            <div class="card-body">
                                <div class="d-flex justify-content-between align-items-start">
                                    <div>
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
                                        <asp:image id="imgpublicacion" runat="server"
                                            imageurl='<%# Eval("imagenUrl") %>'
     
                                            visible='<%# !Convert.ToString(Eval("imagenUrl")).Equals("Sin_imagen") %>'
                                            cssclass="img-fluid mb-2" />
                                        
                                    </div>

                                    <div class="ms-3">
                                        <asp:LinkButton ID="btnEliminar" runat="server"
                                            CssClass="btn btn-sm btn-outline-danger"
                                            CommandName="Eliminar"
                                            CommandArgument='<%# Eval("id") %>'
                                            OnClientClick="return confirm('¿Seguro que deseas eliminar esta publicación?');">
                                            Eliminar
                                        </asp:LinkButton>
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer">
                                <small class="text-muted">
                                    ▲ <%# Eval("votosPositivos") %> &nbsp;&nbsp;
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
