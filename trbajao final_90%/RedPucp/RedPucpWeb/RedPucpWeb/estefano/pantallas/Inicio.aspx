<%@ Page Title="" Language="C#" MasterPageFile="~/estefano/masterPage/MasterPage.master" AutoEventWireup="true" CodeBehind="Inicio.aspx.cs" Inherits="RedPucpWeb.estefano.pantallas.Inicio" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <style>
        .hero {
            background: linear-gradient(135deg, #007bff, #6610f2);
            color: white;
            text-align: center;
            padding: 100px 20px;
            border-radius: 10px;
        }

            .hero h1 {
                font-weight: 700;
            }

        .section-title {
            text-align: center;
            margin: 60px 0 30px 0;
            font-weight: bold;
            color: #333;
        }

        .post-card {
            border: 1px solid #e1e1e1;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
            transition: all 0.2s ease-in-out;
            background: white;
        }

            .post-card:hover {
                box-shadow: 0px 3px 10px rgba(0, 0, 0, 0.1);
                transform: translateY(-2px);
            }

        .post-meta {
            font-size: 0.9rem;
            color: #6c757d;
            margin-bottom: 10px;
        }

        .post-text {
            font-size: 1.05rem;
            color: #333;
            margin-bottom: 15px;
        }

        .post-karma {
            font-weight: bold;
            color: #ff4500;
        }

        .community-card {
            border: 1px solid #e1e1e1;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
            text-align: center;
            transition: all 0.2s ease-in-out;
            background: white;
            height: 100%;
        }

            .community-card:hover {
                box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
                transform: translateY(-2px);
            }

            .community-card h5 {
                font-weight: bold;
                color: #007bff;
                margin-bottom: 10px;
            }

            .community-card p {
                font-size: 0.95rem;
                color: #555;
                margin-bottom: 15px;
            }

        .loading {
            text-align: center;
            padding: 40px;
            color: #666;
        }

        .no-data {
            text-align: center;
            padding: 40px;
            color: #999;
            font-style: italic;
        }
    </style>

    <!-- Sección Hero -->
    <section class="hero">
        <h1 id="MensajeDeBienvenida" runat="server">Bienvenido a RedPUCP, invitado</h1>
        <p><strong>Explora comunidades, comparte tus ideas y únete a las conversaciones más interesantes.</strong></p>
        <asp:HyperLink ID="lnkAccion" runat="server" CssClass="btn btn-light btn-lg mt-3">Únete ahora</asp:HyperLink>
    </section>

    <!-- Publicaciones más virales -->
    <section class="container mt-5">
        <h2 class="section-title"><i class="fa fa-fire"></i>Publicaciones más virales</h2>

        <asp:Panel ID="pnlLoadingPublicaciones" runat="server" CssClass="loading">
            <i class="fa fa-spinner fa-spin fa-2x"></i>
            <p>Cargando publicaciones virales...</p>
        </asp:Panel>

        <asp:Repeater ID="rptPublicacionesVirales" runat="server" OnItemDataBound="rptPublicacionesVirales_ItemDataBound">
            <HeaderTemplate>
                <div class="row">
            </HeaderTemplate>
            <ItemTemplate>
                <div class="col-md-6">
                    <div class="post-card">
                        <div class="post-meta">
                            <i class="fa fa-user"></i> 
                            <asp:Label ID="lblAutor" runat="server" Text='<%# Eval("Autor") %>'></asp:Label> | 
                            <i class="fa fa-users"></i> 
                            <asp:Label ID="lblComunidad" runat="server" Text='<%# Eval("comunidad.nombre") %>'></asp:Label>
                        </div>
                        <div class="post-text">
                            <asp:HyperLink ID="lnkPublicacion" runat="server" CssClass="text-decoration-none text-dark">
                                <%# Eval("titulo") %>
                            </asp:HyperLink>
                        </div>
                        <div class="mt-3">
                            <span class="post-karma">
                                <i class="fa fa-arrow-up"></i>
                                <asp:Label ID="lblKarma" runat="server" Text='<%# Eval("autor.karma") %>'></asp:Label>
                            </span> puntos

                            <%--<asp:HyperLink ID="lnkComentarios" runat="server" CssClass="btn btn-outline-primary btn-sm float-end">
                                <i class="fa fa-comment"></i>
                                <asp:Label ID="lblCantidadComentarios" runat="server" Text='<%# Eval("CantidadComentarios") %>'></asp:Label> comentarios
                            </asp:HyperLink>--%>
                        </div>
                    </div>
                </div>
            </ItemTemplate>
            <FooterTemplate>
                </div>
                <asp:Label ID="lblNoPublicaciones" runat="server" Visible="false" CssClass="no-data">
                    No hay publicaciones virales en este momento.
                </asp:Label>
            </FooterTemplate>
        </asp:Repeater>
    </section>

    <!-- Comunidades más virales -->
    <section class="container mt-5">
        <h2 class="section-title"><i class="fa fa-users"></i>Comunidades más grandes</h2>

        <asp:Panel ID="pnlLoadingComunidades" runat="server" CssClass="loading">
            <i class="fa fa-spinner fa-spin fa-2x"></i>
            <p>Cargando comunidades...</p>
        </asp:Panel>

        <asp:Repeater ID="rptComunidadesVirales" runat="server" OnItemDataBound="rptComunidadesVirales_ItemDataBound">
            <HeaderTemplate>
                <div class="row">
            </HeaderTemplate>
            <ItemTemplate>
                <div class="col-md-4">
                    <div class="community-card">
                        <h5>r/<%# Eval("nombre") %></h5>
                        <p><%# Eval("descripcion") %></p>
                        <div style="font-size: 0.9rem; color: #6c757d; margin-bottom: 10px;">
                            <i class="fa fa-users"></i> <%# Eval("cantidadMiembros", "{0:N0}") %> miembros
                        </div>
                        <asp:HyperLink ID="lnkVerComunidad" runat="server" CssClass="btn btn-outline-primary btn-sm mt-2">
                            Ver comunidad
                        </asp:HyperLink>
                    </div>
                </div>
            </ItemTemplate>
            <FooterTemplate>
                </div>
                <asp:Label ID="lblNoComunidades" runat="server" Visible="false" CssClass="no-data">
                    No hay comunidades disponibles.
                </asp:Label>
            </FooterTemplate>
        </asp:Repeater>
    </section>

    <!-- sección final -->
    <section class="text-center my-5">
        <p>¿Quieres que tu publicación aparezca aquí? Empieza a compartir en tus comunidades favoritas 🚀</p>
        <asp:HyperLink ID="lnkCrearPublicacion" runat="server" CssClass="btn btn-primary btn-lg">Crear publicación</asp:HyperLink>
    </section>
</asp:Content>

