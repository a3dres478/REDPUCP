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
        }

            .community-card:hover {
                box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
                transform: translateY(-2px);
            }

            .community-card h5 {
                font-weight: bold;
                color: #007bff;
            }

            .community-card p {
                font-size: 0.95rem;
                color: #555;
            }
    </style>

    <!-- arriba -->
    <section class="hero">
        <h1 id="MensajeDeBienvenida" runat="server">Bienvenido a RedPUCP, invitado</h1>
        <p>Explora comunidades, comparte tus ideas y únete a las conversaciones más interesantes.</p>
        <asp:HyperLink ID="lnkAccion" runat="server" CssClass="btn btn-light btn-lg mt-3">Únete ahora</asp:HyperLink>
    </section>

    <!-- publicaciones más virales -->
    <section class="container mt-5">
        <h2 class="section-title"><i class="fa fa-fire"></i>Publicaciones más virales</h2>

        <div class="row">
            <!-- Publicación 1 -->
            <div class="col-md-6">
                <div class="post-card">
                    <div class="post-meta">u/JuanPerez | r/Programacion</div>
                    <div class="post-text">
                        “Hoy logré entender cómo funcionan los hilos en C. Me siento como un verdadero ingeniero 😎.”
                    </div>
                    <div class="mt-3">
                        <span class="post-karma"><i class="fa fa-arrow-up"></i>420</span> puntos
                        <button class="btn btn-outline-primary btn-sm float-end"><i class="fa fa-comment"></i>23 comentarios</button>
                    </div>
                </div>
            </div>

            <!-- Publicación 2 -->
            <div class="col-md-6">
                <div class="post-card">
                    <div class="post-meta">u/AnaCode | r/DiseñoWeb</div>
                    <div class="post-text">
                        “Bootstrap sigue salvando vidas. En menos de una hora terminé todo el diseño responsive 😅.”
                    </div>
                    <div class="mt-3">
                        <span class="post-karma"><i class="fa fa-arrow-up"></i>310</span> puntos
                        <button class="btn btn-outline-primary btn-sm float-end"><i class="fa fa-comment"></i>10 comentarios</button>
                    </div>
                </div>
            </div>

            <!-- Publicación 3 -->
            <div class="col-md-6">
                <div class="post-card">
                    <div class="post-meta">u/GeekDev | r/IAyMachineLearning</div>
                    <div class="post-text">
                        “Entrené mi primer modelo de IA y predijo correctamente el 92% de los casos 😍.”
                    </div>
                    <div class="mt-3">
                        <span class="post-karma"><i class="fa fa-arrow-up"></i>500</span> puntos
                        <button class="btn btn-outline-primary btn-sm float-end"><i class="fa fa-comment"></i>45 comentarios</button>
                    </div>
                </div>
            </div>

            <!-- Publicación 4 -->
            <div class="col-md-6">
                <div class="post-card">
                    <div class="post-meta">u/LuisGames | r/Videojuegos</div>
                    <div class="post-text">
                        “Acabo de terminar Elden Ring sin morir una sola vez 😤🔥.”
                    </div>
                    <div class="mt-3">
                        <span class="post-karma"><i class="fa fa-arrow-up"></i>890</span> puntos
                        <button class="btn btn-outline-primary btn-sm float-end"><i class="fa fa-comment"></i>67 comentarios</button>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- comunidades más virales -->

    <section class="container mt-5">
        <h2 class="section-title"><i class="fa fa-users"></i>Comunidades más grandes</h2>

        <div class="row">
            <!-- Comunidad 1 -->
            <div class="col-md-4">
                <div class="community-card">
                    <h5>r/Programacion</h5>
                    <p>El lugar donde los desarrolladores comparten código, ideas y memes de bugs 🧠💻.</p>
                    <a href="Comunidad.aspx?id=1" class="btn btn-outline-primary btn-sm mt-2">Ver comunidad</a>
                </div>
            </div>

            <!-- Comunidad 2 -->
            <div class="col-md-4">
                <div class="community-card">
                    <h5>r/Videojuegos</h5>
                    <p>Comparte tus experiencias, teorías y logros en tus juegos favoritos 🎮🔥.</p>
                    <a href="Comunidad.aspx?id=2" class="btn btn-outline-primary btn-sm mt-2">Ver comunidad</a>
                </div>
            </div>

            <!-- Comunidad 3 -->
            <div class="col-md-4">
                <div class="community-card">
                    <h5>r/IAyMachineLearning</h5>
                    <p>Explora el fascinante mundo de la inteligencia artificial y el aprendizaje automático 🤖.</p>
                    <a href="Comunidad.aspx?id=3" class="btn btn-outline-primary btn-sm mt-2">Ver comunidad</a>
                </div>
            </div>
        </div>
    </section>

    <!-- sección final -->
    <section class="text-center my-5">
        <p>¿Quieres que tu publicación aparezca aquí? Empieza a compartir en tus comunidades favoritas 🚀</p>
        <a href="CrearPublicacion.aspx" class="btn btn-primary btn-lg">Crear publicación</a>
    </section>
</asp:Content>

