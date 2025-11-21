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
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElement;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.ResourceBundle;
import javax.xml.namespace.QName;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Comentario;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Administrador;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;
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
     
     @WebMethod(operationName = "listarComentarios")
     public List<Comentario>listarComentarios()throws Exception{
        String url =this.urlBase+"/"+this.NOMBRE_RECURSO;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper= DateDeserializerUtil.getObjectMapperWithDateHandling();
        List<Comentario>comentarios=mapper.readValue(json,new TypeReference<List<Comentario>>() {});
        return comentarios;
    }
     
    @WebMethod (operationName ="listarComentariosXPublicacion")
    public List<Comentario> listarComentariosXPublicacion(@WebParam(name="idPublicacion")int idPublicacion)throws Exception{
        String url = this.urlBase + "/" + this.NOMBRE_RECURSO+"/publicacion/"+idPublicacion;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
//        ObjectMapper mapper= new ObjectMapper();
        ObjectMapper mapper= DateDeserializerUtil.getObjectMapperWithDateHandling();
        List<Comentario> comentarios = mapper.readValue(json, new TypeReference<List<Comentario>>() {});
        
        return comentarios;
    }
     
     @WebMethod (operationName ="guardarComentario")
     public void guardarComentario(@WebParam(name = "comentario") Comentario comentario, @WebParam(name = "estado") Estado estado) throws Exception{
//        ObjectMapper mapper = new ObjectMapper();
        ObjectMapper mapper = DateDeserializerUtil.getObjectMapperWithDateHandling();
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
        ObjectMapper mapper= DateDeserializerUtil.getObjectMapperWithDateHandling();
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
     
    @WebMethod(operationName = "actualizarImagenComentario")
    public String actualizarImagenComentario(
        @WebParam(name = "idComentario") int idComentario,
        @WebParam(name = "imagenUrl") String imagenUrl) {
        
        try {
            String restUrl = this.urlBase + "/" + this.NOMBRE_RECURSO + "/" + idComentario + "/imagen";
            
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(restUrl))
                .header("Content-Type", "text/plain")
                .PUT(HttpRequest.BodyPublishers.ofString(imagenUrl))
                .build();
            
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            return response.body();
            
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }
}
