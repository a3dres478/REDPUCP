<%@ Page Title="Reportar publicación - REDPUCP"
    Language="C#"
    MasterPageFile="~/RedPucpMast.Master"
    AutoEventWireup="true"
    CodeBehind="ReportarPublicacion.aspx.cs"
    Inherits="RedPucpWeb.ReportarPublicacion" %>


<asp:Content ID="t" ContentPlaceHolderID="cphTitulo" runat="server">
  Reportar publicación - REDPUCP
</asp:Content>

<asp:Content ID="c" ContentPlaceHolderID="cphContenido" runat="server">

  <!-- Aviso de éxito (solo visual) -->
  <div id="alertOk" class="alert alert-success d-none" role="alert">
    ¡Gracias! Tu reporte ha sido enviado para revisión.
  </div>

  <div class="row justify-content-center">
    <div class="col-12 col-lg-8">
      <div class="card shadow-sm">
        <div class="card-header fw-semibold">Reportar publicación</div>

        <div class="card-body">
          <!-- Preview del post (maqueta) -->
          <div class="mb-4 p-3 rounded border bg-light">
            <div class="small text-muted mb-1">Publicación a reportar</div>
            <h5 class="mb-1">10 tips para remontar el ciclo</h5>
            <div class="text-muted small">Por <a href="#" class="link-secondary">Sepepian04xd</a> · en <a href="#" class="link-secondary">Comunidad</a></div>
          </div>

          <!-- Form visual -->
          <div class="mb-3">
            <label class="form-label">Motivo</label>
            <select class="form-select">
              <option>Spam o publicidad no deseada</option>
              <option>Lenguaje ofensivo o de odio</option>
              <option>Plagio o infracción de derechos</option>
              <option>Información engañosa</option>
              <option>Contenido NSFW</option>
              <option>Otro</option>
            </select>
          </div>

          <div class="mb-3">
            <label class="form-label">Detalles (opcional)</label>
            <textarea class="form-control" rows="5" placeholder="Explica brevemente por qué consideras que debe reportarse..."></textarea>
          </div>

          <div class="mb-4">
            <label class="form-label">Evidencia (opcional)</label>
            <input type="file" class="form-control" />
            <div class="form-text">Formatos permitidos: JPG/PNG/PDF (solo demostrativo).</div>
          </div>

          <div class="d-flex gap-2">
            <a href="DetallePublicacion.aspx" class="btn btn-outline-dark">Cancelar</a>
            <!-- Botón solo visual: muestra la alerta de arriba -->
            <button type="button" class="btn btn-dark"
              onclick="document.getElementById('alertOk').classList.remove('d-none')">
              Enviar reporte
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>

</asp:Content>
