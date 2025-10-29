<%@ Page Title="" Language="C#" MasterPageFile="~/RedPucpMast.Master" AutoEventWireup="true" CodeBehind="popular2.aspx.cs" Inherits="RedPucpWeb.popular2" %>
<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">


    
     <div class="row g-4">
     <!-- Centro -->
     <div class="col-12 col-lg-8">
         <article class="card shadow-sm">
             <header class="card-body pb-0">
                 <h3 class="mb-1">Fantasma es captado saliendo del biblioteca central</h3>
                 <div class="text-muted small mb-3">
                     Por <a href="#" class="link-secondary">paranormalPUCP</a> · hace 2h
                 </div>
             </header>

             <!-- Imagen principal (opcional) -->
             <figure class="ratio ratio-16x9">
                 <img src="assets/fantasma.png" alt="Portada del post"
                     class="post-detail-img" />
             </figure>

             <div class="card-body">
                 <!-- Contenido  -->
                 <div class="prose">
                     <p>
                        😱HORROR! actividad paranormal .Alumna capta un ente saliendo de la biblioteca central .leelo urgente antes que lo borren
                     </p>

                     <p>
                       El día de ayer (10/28/2025) a las 11pm aprox mi amiga que no voy a decir su nombre me llama al whatsapp totalmente desperada y llorando.
                        Le decia que se calme pues por sus llantos no entendia a entender lo que decía. momentos despues me manda esta foto con el soguiente mensaje: "Auxilio me quede 
                         encerrada dentro de Cato y cuando cuando baje del A senti que alguien me perseguia ,cuando llegue a central vi esto AYUDAME". si
                         quieres leer en que termina ve a mi perfil de ig: pepe_paredes20
                     </p>

                    
                 </div>

                 <hr class="my-4" />

                 <div class="d-flex align-items-center">
                     <!-- Upvote -->
                     <div class="d-flex align-items-center me-3">
                         <button type="button" class="btn p-0 text-success d-flex align-items-center">
                             <i class="fa-solid fa-arrow-up fa-lg"></i>
                         </button>
                         <span class="text-success ms-1 fw-semibold">700</span>
                     </div>

                     <!-- Downvote -->
                     <div class="d-flex align-items-center me-3">
                         <button type="button" class="btn p-0 text-danger d-flex align-items-center">
                             <i class="fa-solid fa-arrow-down fa-lg"></i>
                         </button>
                         <span class="text-danger ms-1 fw-semibold">10</span>
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
             <div class="card-header fw-semibold"><i class="fa-regular fa-comment me-1"></i>120 comentarios</div>
             <div class="card-body">

                

              Advertencia: Los comentarios ha sido desactivada por un moderador.
                

                
                



             </div>
         </section>
     </div>

     <!-- Derecha: autor/karma -->
     <aside class="col-12 col-lg-4">
         <div class="card shadow-sm">
             <div class="card-header fw-semibold">Autor</div>
             <div class="card-body d-flex gap-3 align-items-center">

                 <div>
                     <div class="fw-semibold">paranormalPUCP </div>
                     <div class="text-muted small">Karma: - </div>
                     <div class="text-muted small">Publicaciones: 2</div>
                 </div>
             </div>
         </div>
     </aside>
 </div>



</asp:Content>
