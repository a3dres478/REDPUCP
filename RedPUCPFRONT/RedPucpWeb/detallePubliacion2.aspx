<%@ Page Title="" Language="C#" MasterPageFile="~/RedPucpMast.Master" AutoEventWireup="true" CodeBehind="detallePubliacion2.aspx.cs" Inherits="RedPucpWeb.detallePubliacion2" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
    Detalle de Publicación - REDPUCP
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">
    <div class="row g-4">
        <!-- Centro -->
        <div class="col-12 col-lg-8">
            <article class="card shadow-sm">
                <header class="card-body pb-0">
                    <h3 class="mb-1">Fijas de Prog3 y Prog2</h3>
                    <div class="text-muted small mb-3">
                        Por <a href="#" class="link-secondary">c0d3rPUCP</a> · hace 2h
                    </div>
                </header>

                <!-- Imagen principal (opcional) -->
                <figure class="ratio ratio-16x9">
                    <img src="assets/segundo-post.png" alt="Portada del post"
                        class="post-detail-img" />
                </figure>

                <div class="card-body">
                    <!-- Contenido  -->
                    <div class="prose">
                        <p>
                            Al parecer los cursos de progra son los que mas laboratorios tienen. Y como alumnos tenemos que cubir gran cantidad de temas
              pero no te preocupes compañero. Pues, la idéa no es estudiar mucho sino eficiente mente . Para ello, aqui te tragigo las "fija" 
              que aplican para cualquier curso de progra.
                        </p>

                        <ol>
                            <li>Haz los labs pasados del 2022-2, pues al volver de la pandemia los profes reformularon la forma de evaluar</li>
                            <li>Practica con un cronómetro para que no te falte tiempo en el verdadero lab</li>
                            <li>Estudia en grupo y ayuda a tus amigos</li>
                            <li>Para prog2 y prog3 la fija es ir a todas las clases del profe Huiza</li>
                            <li>Para el lab de java de prog2 la fija es ver el github del profe Huiza de ese tema</li>
                        </ol>

                        <p class="mb-0">
                            <em>Bonus:</em> Si pasaste TP puedes con prog2 y prog3
                        </p>
                    </div>

                    <hr class="my-4" />

                    <div class="d-flex align-items-center">
                        <!-- Upvote -->
                        <div class="d-flex align-items-center me-3">
                            <button type="button" class="btn p-0 text-success d-flex align-items-center">
                                <i class="fa-solid fa-arrow-up fa-lg"></i>
                            </button>
                            <span class="text-success ms-1 fw-semibold">13</span>
                        </div>

                        <!-- Downvote -->
                        <div class="d-flex align-items-center me-3">
                            <button type="button" class="btn p-0 text-danger d-flex align-items-center">
                                <i class="fa-solid fa-arrow-down fa-lg"></i>
                            </button>
                            <span class="text-danger ms-1 fw-semibold">6</span>
                        </div>

                        <!-- Reportar -->
                        <a href="ReportarPublicacion.aspx" class="text-danger ms-auto">
                            <i class="fa-regular fa-flag me-1"></i>Reportar
                        </a>
                    </div>
                </div>
            </article>

            <!-- Comentarios -->
            <section class="card mt-3">
                <div class="card-header fw-semibold"><i class="fa-regular fa-comment me-1"></i>4 comentarios</div>
                <div class="card-body">

                    <div class="input-group mb-3">
                        <input type="text" class="form-control" placeholder="Escribir un comentario..." />
                        <button class="btn btn-dark"><i class="fa-regular fa-paper-plane"></i></button>
                    </div>

                    <!-- Comentario 1 -->
                    <div class="d-flex gap-3 mb-3">
                        <!-- si luego quieres avatar, aquí va un <img class="avatar"> -->
                        <div class="flex-fill">
                            <div class="fw-semibold">
                                marianito23 <span class="text-muted small">· hace 2 min</span>
                            </div>
                            <div>¡Muy buenos consejos, graciasss!</div>
                        </div>
                    </div>

                    <!-- Comentario 2 -->
                    <div class="d-flex gap-3 mb-3">
                        <div class="flex-fill">
                            <div class="fw-semibold">
                                agelRe_pro <span class="text-muted small">· hace 5 min</span>
                            </div>
                            <div>interesante</div>
                        </div>
                    </div>

                    <!-- Comentario 3 -->
                    <div class="d-flex gap-3 mb-3">
                        <div class="flex-fill">
                            <div class="fw-semibold">
                                estudianteAnonimojaja <span class="text-muted small">· hace 30 min</span>
                            </div>
                            <div>y yo en TP todavia</div>
                        </div>
                    </div>

                    <!-- Comentario 4 -->
                    <div class="d-flex gap-3 mb-3">
                        <div class="flex-fill">
                            <div class="fw-semibold">
                                cuevaTupia <span class="text-muted small">· hace 40 min</span>
                            </div>
                            <div>ojo al último</div>
                        </div>
                    </div>



                </div>
            </section>
        </div>

        <!-- Derecha: autor/karma -->
        <aside class="col-12 col-lg-4">
            <div class="card shadow-sm">
                <div class="card-header fw-semibold">Autor</div>
                <div class="card-body d-flex gap-3 align-items-center">

                    <div>
                        <div class="fw-semibold">c0d3rPUCP</div>
                        <div class="text-muted small">Karma: 245</div>
                        <div class="text-muted small">Publicaciones: 8</div>
                    </div>
                </div>
            </div>
        </aside>
    </div>
</asp:Content>
