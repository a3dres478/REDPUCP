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
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Comentario;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Administrador;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IComentarioBO;
import pe.edu.pucp.progra03.redpucp.boimpl.ComentarioBOImpl;

/**
 *
 * @author andre
 */
@WebService(serviceName = "ComentarioWS",
        targetNamespace = "https://services.redpucp.ws/")
public class ComentarioWS {
    
    private final ResourceBundle config;
    private final String urlBase;
    private HttpClient client = HttpClient.newHttpClient();
     private String NOMBRE_RECURSO = "comentarios";
    
    
    public ComentarioWS(){
        this.config= ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
    }
     
     @WebMethod(operationName = "listarComentario")
     public List<Comentario>listarComentario()throws Exception{
        String url =this.urlBase+"/"+this.NOMBRE_RECURSO;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper= new ObjectMapper();
        List<Comentario>comentarios=mapper.readValue(json,new TypeReference<List<Comentario>>() {});
        return comentarios;
    }
     
     @WebMethod (operationName ="guardarComentario")
     public void guardarComentario(@WebParam(name = "comentario") Comentario comentario, @WebParam(name = "estado") Estado estado) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(comentario);
        
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
            url = this.urlBase+"/"+this.NOMBRE_RECURSO+"/"+comentario.getId();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type","application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();
        }
        
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
     
     @WebMethod (operationName = "obtenerComentario")
     public Comentario obtenerComentario(@WebParam(name = "idComentario") int id)throws Exception{
        String url = this.urlBase + "/" + this.NOMBRE_RECURSO + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper= new ObjectMapper();
        Comentario comentario = mapper.readValue(json, Comentario.class);
        
        return comentario;
    }
     
     @WebMethod (operationName = "eliminarComentario")
     public void eliminarComentario (@WebParam(name = "idComentario") int id) throws Exception {
        String url = this.urlBase + "/" + this.NOMBRE_RECURSO + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
     
    //private final IComentarioBO comentarioBO;
//    public ComentarioWS(){
//        comentarioBO=new ComentarioBOImpl();
//    }
//    @WebMethod(operationName = "listarComentario")
//    public List<Comentario>listarComentarios(){
//        return this.comentarioBO.listar();
//    }
//    @WebMethod (operationName ="guardarComentario")
//    public void guardarComentario(@WebParam(name="comentario")Comentario comentario,@WebParam(name="estado")Estado estado){
//        this.comentarioBO.guardar(comentario,estado);
//    }
//    
//    @WebMethod (operationName = "obtenerComentario")
//    public Comentario obtenerComentario(@WebParam(name="id")int id){
//        return this.comentarioBO.obtener(id);
//    }
//    @WebMethod (operationName = "eliminarComentario")
//    public void eliminarComentario(@WebParam(name="id")int id){
//        this.comentarioBO.eliminar(id);
//    }
    
    
}
