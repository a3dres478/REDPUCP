<%@ Page Title="Detalle de Publicación - REDPUCP" Language="C#" MasterPageFile="~/RedPucpMast.Master" AutoEventWireup="true" CodeBehind="DetallePublicacion.aspx.cs" Inherits="RedPucpWeb.DetallePublicacion" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
  Detalle de Publicación - REDPUCP
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">

  <div class="row g-4">
    <!-- Centro -->
    <div class="col-12 col-lg-8">
      <article class="card shadow-sm">
        <header class="card-body pb-0">
          <h3 class="mb-1">10 tips para remontar el ciclo</h3>
          <div class="text-muted small mb-3">
            Por <a href="#" class="link-secondary">Sepepian404xd</a> · hace 2h
          </div>
        </header>

        <!-- Imagen principal (opcional) -->
        <figure class="ratio ratio-16x9">
          <img src="assets/primer-post.png" alt="Portada del post"
               class="post-detail-img" />
        </figure>

        <div class="card-body">
          <!-- Contenido del post con tipografía agradable -->
          <div class="prose">
            <p>
              Los parciales ya pasaron y probablemente te dejaron con más dudas que certezas 😅,
              pero tranquilo, todavía estás a tiempo de remontar el ciclo. Aquí te dejo 10 tips
              realistas y probados para recuperarte y cerrar el semestre con fuerza:
            </p>

            <ol>
              <li><strong>Haz una autopsia académica:</strong> revisa en qué temas fallaste y por qué. No basta con decir “no estudié”.</li>
              <li><strong>Prioriza tus cursos críticos:</strong> pon foco en los que más pesan o podrían jalarte.</li>
              <li><strong>Habla con tus profes/AYs:</strong> suelen dar pistas muy útiles para el final.</li>
              <li><strong>Mini metas semanales:</strong> divide el avance en partes pequeñas y alcanzables.</li>
              <li><strong>Estudia con quien sabe:</strong> el grupo correcto acelera muchísimo.</li>
              <li><strong>Hábitos básicos:</strong> dormir, comer bien y estudiar con foco &gt; trasnochar sin plan.</li>
              <li><strong>Revisa exámenes pasados:</strong> las “fijas” existen; entiende el estilo de tus profes.</li>
              <li><strong>Evita el TODO al final:</strong> empieza hoy, aunque sea 30 min diarios.</li>
              <li><strong>Plan realista:</strong> horarios que sí puedas cumplir, sin sobrecargarte.</li>
              <li><strong>Confía en ti:</strong> la mitad es mental. Muchos ya remontaron, tú también puedes.</li>
            </ol>

            <p class="mb-0">
              🔥 <em>Bonus:</em> si sobreviviste a los parciales, puedes sobrevivir a lo que queda del ciclo.
            </p>
          </div>

          <hr class="my-4" />

          <div class="d-flex gap-3">
            <a href="#" class="text-dark"><i class="fa-solid fa-arrow-up me-1"></i>Upvote</a>
            <a href="#" class="text-dark"><i class="fa-solid fa-arrow-down me-1"></i>Downvote</a>
            <a href="#" class="text-danger ms-auto"><i class="fa-regular fa-flag me-1"></i>Reportar</a>
          </div>
        </div>
      </article>

      <!-- Comentarios -->
      <section class="card mt-3">
        <div class="card-header fw-semibold">Comentarios</div>
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
                masterArturo <span class="text-muted small">· hace 30 min</span>
              </div>
              <div>¡Muy buenos consejos, graciasss!</div>
            </div>
          </div>

          <!-- Comentario 2 -->
          <div class="d-flex gap-3 mb-3">
            <div class="flex-fill">
              <div class="fw-semibold">
                estefanoRePro <span class="text-muted small">· hace 50 min</span>
              </div>
              <div>Tome su upvote, buen hombre.</div>
            </div>
          </div>

          <!-- Comentario 3 -->
          <div class="d-flex gap-3 mb-3">
            <div class="flex-fill">
              <div class="fw-semibold">
                estudianteAnonimojaja <span class="text-muted small">· hace 30 min</span>
              </div>
              <div>Excelente.</div>
            </div>
          </div>

          <!-- Comentario 4 -->
          <div class="d-flex gap-3 mb-3">
            <div class="flex-fill">
              <div class="fw-semibold">
                supercraest <span class="text-muted small">· hace 30 min</span>
              </div>
              <div>Ya me los sabía.</div>
            </div>
          </div>

          <!-- Comentario 5 -->
          <div class="d-flex gap-3">
            <div class="flex-fill">
              <div class="fw-semibold">
                remontador <span class="text-muted small">· hace 30 min</span>
              </div>
              <div>El ciclo recién empieza, ¡con todo muchachos!</div>
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
          <!-- Avatar opcional -->
          <!-- <img src="assets/avatar.png" class="avatar" alt="avatar" /> -->
          <div>
            <div class="fw-semibold">Usuario</div>
            <div class="text-muted small">Karma: 1,245</div>
            <div class="text-muted small">Publicaciones: 32</div>
          </div>
        </div>
      </div>
    </aside>
  </div>

</asp:Content>
