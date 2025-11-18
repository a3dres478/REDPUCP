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
import pe.edu.pucp.inf30.RedPUCP.modelo.NotificacionesU.Notificacion;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
/**
 *
 * @author HECTOR
 */
@WebService(serviceName = "NotificacionWS",
        targetNamespace = "https://services.redpucp.ws/")
public class NotificacionWS {
    private final ResourceBundle config;
    private final String urlBase;
    private HttpClient client =  HttpClient.newHttpClient();
    private String NOMBRE_RECURSO = "notificaciones";
    
    public NotificacionWS(){
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
    }
    
    @WebMethod(operationName = "listarNotificaciones")
    public List<Notificacion>listarNotificaciones()throws Exception{
        String url =this.urlBase+"/"+this.NOMBRE_RECURSO;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper = DateDeserializerUtil.getObjectMapperWithDateHandling();
        List<Notificacion>notificaciones=mapper.readValue(json,new TypeReference<List<Notificacion>>() {});
        return notificaciones;
    }
    
    @WebMethod(operationName = "listarNotificacionesParaUsuario")
    public List<Notificacion>listarNotificacionesParaUsuario(@WebParam(name = "idUsuario") int idUsuario)throws Exception{
        String url =this.urlBase+"/"+this.NOMBRE_RECURSO+"/usuario/"+idUsuario;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper = DateDeserializerUtil.getObjectMapperWithDateHandling();
        List<Notificacion>notificaciones=mapper.readValue(json,new TypeReference<List<Notificacion>>() {});
        return notificaciones;
    }
    
    @WebMethod(operationName = "guardarNotificacion")
    public void guardarNotificacion(@WebParam(name = "notificacion") Notificacion notificacion,
        @WebParam(name = "estado") Estado estado) throws Exception{
        ObjectMapper mapper = DateDeserializerUtil.getObjectMapperWithDateHandling();
        String json = mapper.writeValueAsString(notificacion);
        
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
            url = this.urlBase+"/"+this.NOMBRE_RECURSO+"/"+notificacion.getId_notip();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type","application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();
        }
        
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod (operationName = "obtenerNotificacion")
    public Notificacion actualizarNotificacion(@WebParam(name = "idNotificacion") int id)throws Exception{
        String url = this.urlBase + "/" + this.NOMBRE_RECURSO + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper = DateDeserializerUtil.getObjectMapperWithDateHandling();
        Notificacion notificacion = mapper.readValue(json, Notificacion.class);
        
        return notificacion;
    }
    
    @WebMethod (operationName = "eliminarNotificacion")
    public void eliminarNotificacion (@WebParam(name = "idNotificacion") int id) throws Exception {
        String url = this.urlBase + "/" + this.NOMBRE_RECURSO + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    
}
