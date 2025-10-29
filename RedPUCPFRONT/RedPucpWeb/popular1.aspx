<%@ Page Title="" Language="C#" MasterPageFile="~/RedPucpMast.Master" AutoEventWireup="true" CodeBehind="popular1.aspx.cs" Inherits="RedPucpWeb.popular1" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">


     <div class="row g-4">
     <!-- Centro -->
     <div class="col-12 col-lg-8">
         <article class="card shadow-sm">
             <header class="card-body pb-0">
                 <h3 class="mb-1">Entrega de regalos FACI </h3>
                 <div class="text-muted small mb-3">
                     Por <a href="#" class="link-secondary">AlumnoFaciComunica</a> · hace 2h
                 </div>
             </header>

             <!-- Imagen principal (opcional) -->
             <figure class="ratio ratio-16x9">
                 <img src="assets/mostvoted-post.jpg" alt="Portada del post"
                     class="post-detail-img" />
             </figure>

             <div class="card-body">
                 <!-- Contenido  -->
                 <div class="prose">
                     <p>
                          Ultimo dia para votar 31/10/2025
                     </p>

                   

                    
                 </div>

                 <hr class="my-4" />

                 <div class="d-flex align-items-center">
                     <!-- Upvote -->
                     <div class="d-flex align-items-center me-3">
                         <button type="button" class="btn p-0 text-success d-flex align-items-center">
                             <i class="fa-solid fa-arrow-up fa-lg"></i>
                         </button>
                         <span class="text-success ms-1 fw-semibold">340</span>
                     </div>

                     <!-- Downvote -->
                     <div class="d-flex align-items-center me-3">
                         <button type="button" class="btn p-0 text-danger d-flex align-items-center">
                             <i class="fa-solid fa-arrow-down fa-lg"></i>
                         </button>
                         <span class="text-danger ms-1 fw-semibold">25</span>
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
             <div class="card-header fw-semibold"><i class="fa-regular fa-comment me-1"></i> comentarios</div>
             <div class="card-body">


               Los comentarios han sido desactivados por el creador del post.


             </div>
         </section>
     </div>

     <!-- Derecha: autor/karma -->
     <aside class="col-12 col-lg-4">
         <div class="card shadow-sm">
             <div class="card-header fw-semibold">Autor</div>
             <div class="card-body d-flex gap-3 align-items-center">

                 <div>
                     <div class="fw-semibold">AlumnoFaciComunica</div>
                     <div class="text-muted small">Karma: 20</div>
                     <div class="text-muted small">Publicaciones: 18</div>
                 </div>
             </div>
         </div>
     </aside>
 </div>

</asp:Content>
