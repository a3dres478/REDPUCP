/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.ws;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.ResourceBundle;
import pe.edu.pucp.inf30.RedPUCP.modelo.ReportesU.Reporte;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
/**
 *
 * @author HECTOR
 */
@WebService(serviceName = "ReporteWS",
        targetNamespace = "https://services.redpucp.ws/")
public class ReporteWS {
    private final ResourceBundle config;
    private final String urlBase;
    private HttpClient client =  HttpClient.newHttpClient();
    private String NOMBRE_RECURSO = "reportes";
    
    public ReporteWS(){
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
    }
    
    @WebMethod(operationName = "listarReportesPendientes")
    public List<Reporte>listarReportes()throws Exception{
        String url =this.urlBase+"/"+this.NOMBRE_RECURSO;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper = DateDeserializerUtil.getObjectMapperWithDateHandling();
        List<Reporte>reportes=mapper.readValue(json,new TypeReference<List<Reporte>>() {});
        return reportes;
    }
    
    @WebMethod(operationName = "listarReportesXReportador")
    public List<Reporte>listarReportesXReportador(@WebParam(name = "idReportador") int idReportador)throws Exception{
        String url =this.urlBase+"/"+this.NOMBRE_RECURSO+"/"+idReportador;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper = DateDeserializerUtil.getObjectMapperWithDateHandling();
        List<Reporte>reportes=mapper.readValue(json,new TypeReference<List<Reporte>>() {});
        return reportes;
    }
    
    @WebMethod(operationName = "guardarReporte")
    public void guardarReporte(@WebParam(name = "reporte") Reporte reporte,
        @WebParam(name = "estado") Estado estado) throws Exception{
        ObjectMapper mapper = DateDeserializerUtil.getObjectMapperWithDateHandling();
        String json = mapper.writeValueAsString(reporte);
        
        String url;
        HttpRequest request;
        
        if (estado == Estado.Nuevo){
            url = this.urlBase +"/"+this.NOMBRE_RECURSO;
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type","application/json" )
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
        }
        else{
            url = this.urlBase+"/"+this.NOMBRE_RECURSO+"/"+reporte.getId_report();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type","application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();
        }
        
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod (operationName = "obtenerReporte")
    public Reporte actualizarReporte(@WebParam(name = "idReporte") int id)throws Exception{
        String url = this.urlBase + "/" + this.NOMBRE_RECURSO + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper = DateDeserializerUtil.getObjectMapperWithDateHandling();
        Reporte reporte = mapper.readValue(json, Reporte.class);
        
        return reporte;
    }
    
    @WebMethod (operationName = "eliminarReporte")
    public void eliminarReporte (@WebParam(name = "idReporte") int id) throws Exception {
        String url = this.urlBase + "/" + this.NOMBRE_RECURSO + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
