/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucprest.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.Map;
import pe.edu.pucp.inf30.RedPUCP.modelo.ReportesU.Reporte;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IReporteBO;
import pe.edu.pucp.progra03.redpucp.boimpl.ReporteBOImpl;

/**
 *
 * @author HECTOR main
 */
@Path("reportes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReporteResource {
    private final IReporteBO reporteBO;
    
    public ReporteResource(){
        reporteBO = new ReporteBOImpl();
    }
    
    @GET
    public List<Reporte> listarReportesPendientes(){
        return this.reporteBO.listarReportesPendientes();
    }
    
    @GET
    @Path("{id}")
    public Response obtener(@PathParam("id")int id){
        Reporte reporte = this.reporteBO.obtener(id);
        if(reporte==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error","Reporte: "+id+", no encontrado"))
                    .build();
        }
        return Response.ok(reporte).build();
    }
    
    @GET
    @Path("reportador/{idReportador}")
    public List listarReportesXReportador(@PathParam("idReportador")int idReportador){
        List<Reporte> reportes = this.reporteBO.listarReportesXReportador(idReportador);
        return reportes;
    }
    
    @POST
    public Response crear (Reporte reporte){
        if(reporte == null || reporte.getReportador()== null || reporte.getPublicador()==null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El reporte no es valido")
                    .build();
        }
        
        this.reporteBO.guardar(reporte, Estado.Nuevo);
        URI location = URI.create("/RedPUCPRest/api/reportes/"+reporte.getId_report());
        
        return Response.ok(location)
                .entity(reporte)
                .build();
    }
    
    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id")int id,Reporte reporte){
        if(reporte == null || reporte.getReportador()== null || reporte.getPublicador()==null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error","El reporte no es valido"))
                    .build();
        }
        
        if (this.reporteBO.obtener(id)==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Reporte: "+ id + ", no encontrado")
                    .build();
        }
        this.reporteBO.guardar(reporte, Estado.Modificar);
        return Response.ok(reporte).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response eliminar (@PathParam("id") int id){
        if (this.reporteBO.obtener(id)==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Reporte: "+ id + ", no encontrado")
                    .build();
        }
        
        this.reporteBO.eliminar(id);
        
        
        return Response.noContent().build();
    }
}
