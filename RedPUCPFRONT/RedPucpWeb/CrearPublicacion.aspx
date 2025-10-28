<%@ Page Title="Crear Publicación - REDPUCP" Language="C#" MasterPageFile="~/RedPucpMast.Master" AutoEventWireup="true" CodeBehind="CrearPublicacion.aspx.cs" Inherits="RedPucpWeb.CrearPublicacion" %>

<asp:Content ID="Content1" ContentPlaceHolderID="cphTitulo" runat="server">
  Crear Publicación - REDPUCP
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="cphContenido" runat="server">

  <div class="row justify-content-center">
    <div class="col-12 col-lg-8">
      <div class="card">
        <div class="card-header fw-semibold">Crear Publicación</div>
        <div class="card-body">

          <div class="mb-3">
            <label class="form-label">Título</label>
            <input type="text" class="form-control" placeholder="Ingresa un título claro" />
          </div>

          <div class="mb-3">
            <label class="form-label">Descripción</label>
            <textarea class="form-control" rows="6" placeholder="Escribe los detalles de tu publicación"></textarea>
          </div>

          <div class="row g-3">
            <div class="col-md-6">
              <label class="form-label">Categoría</label>
              <select class="form-select">
                <option>Ayuda</option>
                <option>Discusión</option>
                <option>Evento</option>
                <option>Convocatoria</option>
              </select>
            </div>
            <div class="col-md-6">
              <label class="form-label">Comunidad</label>
              <select class="form-select">
                <option>Selecciona...</option>
                <option>Comunidad A</option>
                <option>Comunidad B</option>
              </select>
            </div>
          </div>

          <div class="mt-3">
            <label class="form-label">Imagen (opcional)</label>
            <input type="file" class="form-control" />
            <div class="placeholder-glow mt-3">
              <span class="placeholder col-12" style="height:180px;"></span>
            </div>
            <div class="form-text">Formatos: JPG/PNG/WebP. Máx 10MB (solo visual, sin validación aún).</div>
          </div>

          <div class="mt-4 d-flex gap-2">
            <a href="Feed.aspx" class="btn btn-outline-dark">Cancelar</a>
            <button class="btn btn-dark"><i class="fa-solid fa-paper-plane me-2"></i>Publicar</button>
          </div>

        </div>
      </div>
    </div>
  </div>

</asp:Content>
