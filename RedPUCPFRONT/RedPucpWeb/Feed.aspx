<%@ Page Title="" Language="C#" MasterPageFile="~/RedPucpMast.Master"
    AutoEventWireup="true" CodeBehind="Feed.aspx.cs" Inherits="RedPucpWeb.Feed" %>

<asp:Content ID="HeadContent" ContentPlaceHolderID="cphTitulo" runat="server">
  Feed
</asp:Content>

<asp:Content ID="MainContent" ContentPlaceHolderID="cphContenido" runat="server">
  <!-- Caja de publicación / filtro -->
  <div class="card mb-3">
    <div class="card-body">
      <div class="row g-3 align-items-center">
        <div class="col">
          <input type="text" class="form-control" placeholder="¿Qué quieres compartir hoy?" />
        </div>
        <div class="col-auto">
          <button class="btn btn-dark">Publicar</button>
        </div>
      </div>

      <ul class="nav nav-tabs mt-3">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Más recientes</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Más populares</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Por relevancia</a>
        </li>
      </ul>
    </div>
  </div>

  <div class="row g-4">
    <!-- Columna principal: posts -->
    <div class="col-lg-8">
      <!-- Post 1 -->
      <article class="card shadow-sm">
        <div class="card-header bg-white">
          <h5 class="mb-1">10 tips para remontar el ciclo</h5>
          <div class="text-muted small">Sepepian04xd · hace 2h</div>
        </div>
        <div class="card-body">
          <!-- Imagen  -->
          <img src="assets/primer-post.png" alt="imagen del post"
               class="post-img mb-3" />

          <p class="mb-3">
            Compilé 10 tips prácticos para recuperarte después de los parciales:
            planificación semanal, técnica Pomodoro, bancos de preguntas, etc.
          </p>

        <div class="d-flex gap-3 mt-3">
       <a href="#" class="text-dark"><i class="fa-regular fa-comment me-1"></i><a href="DetallePublicacion.aspx" class="text-dark">
        <i class="fa-regular fa-comment me-1"></i>5 comentarios
       <a href="#" class="text-danger ms-auto"><i class="fa-regular fa-flag me-1"></i>Reportar</a>
     </div>
      </article>

      <!-- Post 2 -->
      <article class="card shadow-sm mt-3">
        <div class="card-header bg-white">
          <h5 class="mb-1">Plantillas para Prog2/Prog3</h5>
          <div class="text-muted small">c0d3rPUCP · hace 5h</div>
        </div>
        <div class="card-body">
          <div class="ratio ratio-16x9 mb-3">
            <img src="assets/segundo-post.png" alt="plantillas prog"
                 class="post-img-cover" />
          </div>

          <p class="mb-3">
            Dejo un repo con ejercicios típicos: arreglos, recursividad, colecciones,
            LINQ, ADO.NET y validaciones. ¡Aporte y PRs bienvenidos!
          </p>

          <div class="d-flex gap-3 mt-3">
                <a href="#" class="text-dark"><i class="fa-regular fa-comment me-1"></i><a href="DetallePublicacion.aspx" class="text-dark">
                 <i class="fa-regular fa-comment me-1"></i>15 comentarios
                </a></a>
                <a href="#" class="text-danger ms-auto"><i class="fa-regular fa-flag me-1"></i>Reportar</a>
              </div>
        </div>
      </article>
    </div>

    <!-- Sidebar -->
    <aside class="col-lg-4">
      <!-- Comunidades destacadas -->
      <div class="card shadow-sm mb-3">
        <div class="card-header bg-white fw-semibold">Comunidades destacadas</div>
        <div class="list-group list-group-flush">
          <a href="#" class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
            <span>baje de pepa 2025</span>
            <button class="btn btn-outline-secondary btn-sm">Unirse</button>
          </a>
          <a href="#" class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
            <span>recomendaciones trafis</span>
            <button class="btn btn-outline-secondary btn-sm">Unirse</button>
          </a>
          <a href="#" class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
            <span>dengue verde</span>
            <button class="btn btn-outline-secondary btn-sm">Unirse</button>
          </a>
        </div>
      </div>

      <!-- Usuarios con más karma -->
      <div class="card shadow-sm">
        <div class="card-header bg-white fw-semibold">Usuarios con más karma</div>
        <ul class="list-group list-group-flush">
          <li class="list-group-item d-flex align-items-center">
            <span class="truncate-1">pocoyoDisc</span>
            <span class="user-score ms-auto">770</span>
          </li>
          <li class="list-group-item d-flex align-items-center">
            <span class="truncate-1">alianzistaForEver</span>
            <span class="user-score ms-auto">550</span>
          </li>
          <li class="list-group-item d-flex align-items-center">
            <span class="truncate-1">RosarioZT</span>
            <span class="user-score ms-auto">540</span>
          </li>
        </ul>
      </div>
    </aside>
  </div>
</asp:Content>
