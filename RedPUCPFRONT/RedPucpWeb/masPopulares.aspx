<%@ Page Title="" Language="C#" MasterPageFile="~/RedPucpMast.Master" AutoEventWireup="true" CodeBehind="masPopulares.aspx.cs" Inherits="RedPucpWeb.masPopulares" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">

      <!-- posts -->
  <div class="col-lg-12">
      <!-- Post 1 -->
      <article class="card shadow-sm">
          <div class="card-header bg-white">
              <h5 class="mb-1">
                  <a href="popular1.aspx" class="text-decoration-none text-dark">Entrega de regalos FACI
                  </a>
              </h5>
              <div class="text-muted small">AlumnoFaciComunica · hace 2h</div>
          </div>

          <div class="card-body">
              <!-- Imagen -->
              <img src="assets/mostvoted-post.jpg"
                  alt="imagen del post"
                  class="post-img img-fluid mb-3" />

              <p class="mb-3">
                 Entrega de regalos a las 100 primeras personas que voten en la encuesta docente 
              </p>

              <div class="d-flex gap-3 mt-3 align-items-center">


                  <!-- Comentarios -->
                  <span class="text-dark d-flex align-items-center">
                      <i class="fa-regular fa-comment me-1"></i>0 comentarios
                  </span>
                  <!-- Upvotes -->
                  <span class="text-success d-flex align-items-center">
                      <i class="fa-solid fa-arrow-up me-1"></i>340
                  </span>
                  <!-- Downvotes -->
                  <span class="text-danger d-flex align-items-center">
                      <i class="fa-solid fa-arrow-down me-1"></i>25
                  </span>

              </div>
          </div>
      </article>



      <!-- Post 2 -->
      <article class="card shadow-sm mt-3">
          <div class="card-header bg-white">
              <h5 class="mb-1">
                  <a href="popular2.aspx" class="text-decoration-none text-dark">Fantasma es captado saliendo del biblioteca central
                  </a>
              </h5>
              <div class="text-muted small">paranormalPUCP · hace 5h</div>
          </div>
          <div class="card-body">
              <div class="ratio ratio-16x9 mb-3">
                  <img src="assets/fantasma.png" alt="plantillas prog"
                      class="post-img-cover" />
              </div>

              <p class="mb-3">
                 😱HORROR! actividad paranormal .Alumna capta un ente saliendo de la biblioteca central .leelo urgente antes que lo borren
              </p>

              <div class="d-flex gap-3 mt-3">
                  <!-- Comentarios-->
                  <span class="text-dark">
                      <i class="fa-regular fa-comment me-1" aria-hidden="true"></i>
                      120 comentarios
                  </span>
                  <!-- Upvotes -->
                  <span class="text-success d-flex align-items-center">
                      <i class="fa-solid fa-arrow-up me-1"></i>700
                  </span>
                  <!-- Downvotes -->
                  <span class="text-danger d-flex align-items-center">
                      <i class="fa-solid fa-arrow-down me-1"></i>10
                  </span>

              </div>
      </article>
  </div>

</asp:Content>


