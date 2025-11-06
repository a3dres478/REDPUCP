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
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.Comunidad;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Publicacion;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IPublicacionBO;
import pe.edu.pucp.progra03.redpucp.boimpl.PublicacionBOImpl;

/**
 *
 * @author andre
 */
@WebService(serviceName = "PublicacionWS",
        targetNamespace = "https://services.redpucp.ws/")
public class PublicacionWS {

    private final ResourceBundle config;
    private final String urlBase;
    private HttpClient client = HttpClient.newHttpClient();
    private String NOMBRE_RECURSO = "publicaciones";
    
    
    public PublicacionWS(){
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
    }
    
    @WebMethod(operationName = "listarPublicaciones")
    public List<Publicacion>listarPublicaciones()throws Exception{
        String url =this.urlBase+"/"+this.NOMBRE_RECURSO;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper= new ObjectMapper();
        List<Publicacion>publicaciones=mapper.readValue(json,new TypeReference<List<Publicacion>>() {});
        return publicaciones;
    }
    
    @WebMethod(operationName = "obtenerPublicacion")
    public Publicacion obtenerPublicacion(@WebParam(name = "idPublicacion") int id)throws Exception{
        String url = this.urlBase + "/" + this.NOMBRE_RECURSO + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper= new ObjectMapper();
        Publicacion publicacion = mapper.readValue(json, Publicacion.class);
        
        return publicacion;
    }
    
    @WebMethod(operationName = "eliminarPublicacion")
    public void eliminarPublicacion (@WebParam(name = "idPublicacion") int id) throws Exception {
        String url = this.urlBase + "/" + this.NOMBRE_RECURSO + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod (operationName ="guardarPublicacion")
    public void guardarPublicacion(@WebParam(name = "publicacion") Publicacion publicacion, @WebParam(name = "estado") Estado estado) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(publicacion);
        
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
            url = this.urlBase+"/"+this.NOMBRE_RECURSO+"/"+publicacion.getId();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type","application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();
        }
        
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    
//    private final IPublicacionBO publicacionBO;
//
//    public PublicacionWS() {
//        publicacionBO = new PublicacionBOImpl();
//    }
//
//    @WebMethod(operationName = "listarPublicaciones")
//    public List<Publicacion> listarPublicaciones() {
//        return this.publicacionBO.listar();
//    }
//
//    @WebMethod(operationName = "obtenerPublicacion")
//    public Publicacion obtenerPublicacion(@WebParam(name = "id") int id) {
//        return this.publicacionBO.obtener(id);
//    }
//
//    @WebMethod(operationName = "eliminarPublicacion")
//    public void eliminarPublicacion(@WebParam(name="id")int id){
//        this.publicacionBO.eliminar(id);
//    }
//    @WebMethod (operationName ="guardarPublicacion")
//    public void guardarPublicacion(@WebParam(name="public")Publicacion publi,@WebParam(name="estado")Estado estado ){
//        this.publicacionBO.guardar(publi, estado);
//    }
}
