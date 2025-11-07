/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.ws;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.ResourceBundle;
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.Comunidad;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Comentario;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IComunidadBO;
import pe.edu.pucp.progra03.redpucp.boimpl.ComunidadBOImpl;

/**
 *
 * @author invitado123
 */
@WebService(serviceName = "ComunidadWS",
        targetNamespace = "https://services.redpucp.ws/")
public class ComunidadWS {

    private final ResourceBundle config;
    private final String urlBase;
    private HttpClient client = HttpClient.newHttpClient();
    private String NOMBRE_RECURSO = "comunidades";

    public ComunidadWS() {
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
    }

    @WebMethod(operationName = "listarComunidades")
    public List<Comunidad>listarComunidades()throws Exception{
        String url =this.urlBase+"/"+this.NOMBRE_RECURSO;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper= new ObjectMapper();
        List<Comunidad>comunidades=mapper.readValue(json,new TypeReference<List<Comunidad>>() {});
        return comunidades;
    }
    
    @WebMethod(operationName = "obtenerComunidad")
    public Comunidad obtenerComunidad(@WebParam(name = "idComunidad") int id)throws Exception{
        String url = this.urlBase + "/" + this.NOMBRE_RECURSO + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper= new ObjectMapper();
        Comunidad comunidad = mapper.readValue(json, Comunidad.class);
        
        return comunidad;
    }
    
    @WebMethod(operationName = "eliminarComunidad")
    public void eliminarComunidad (@WebParam(name = "idComunidad") int id) throws Exception {
        String url = this.urlBase + "/" + this.NOMBRE_RECURSO + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod (operationName ="guardarComunidad")
     public void guardarComentario(@WebParam(name = "comunidad") Comunidad comunidad, @WebParam(name = "estado") Estado estado) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(comunidad);
        
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
            url = this.urlBase+"/"+this.NOMBRE_RECURSO+"/"+comunidad.getId_comunidad();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type","application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();
        }
        
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
     
    @WebMethod (operationName ="listarusandofiltros")
    public List<Comunidad> listarusandofiltros(@WebParam(name="filtro1")String filtro1)throws Exception{
        String url = this.urlBase + "/" + this.NOMBRE_RECURSO+"/filtro1/"+filtro1;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper= new ObjectMapper();
        List<Comunidad> comunidades = mapper.readValue(json, new TypeReference<List<Comunidad>>() {});
        
        return comunidades;
    }
    
//    private final IComunidadBO comunidadBO;
//
//    public ComunidadWS() {
//        comunidadBO = new ComunidadBOImpl();
//    }
//
//    @WebMethod(operationName = "listarComunidades")
//    public List<Comunidad> listarComunidades() {
//        return this.comunidadBO.listar();
//    }
//
//    @WebMethod(operationName = "obtenerComunidad")
//    public Comunidad obtenerComunidad(@WebParam(name = "id") int id) {
//        return this.comunidadBO.obtener(id);
//    }
//
//    @WebMethod(operationName = "eliminarComunidad")
//    public void eliminarComunidad(@WebParam(name="id")int id){
//        this.comunidadBO.eliminar(id);
//    }
//    @WebMethod (operationName ="guardarComunidad")
//    public void guardarComunidad(@WebParam(name="public")Comunidad comunidad,@WebParam(name="estado")Estado estado ){
//        this.comunidadBO.guardar(comunidad, estado);
//    }
}
