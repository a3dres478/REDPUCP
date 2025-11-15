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
import pe.edu.pucp.inf30.RedPUCP.modelo.voto.VotoComentario;

import pe.edu.pucp.progra03.redpucp.bo.IVotoPublicacionBO;
import pe.edu.pucp.progra03.redpucp.boimpl.ComunidadBOImpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.voto.VotoPublicacion;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.boimpl.VotoPublicacionBOImpl;
/**
 *
 * @author invitado123
 */
@WebService(serviceName = "VotoPublicacionWS",
        targetNamespace = "https://services.redpucp.ws/")
public class VotoPublicacionWS {
    
    private final ResourceBundle config;
    private final String urlBase;
    private HttpClient client = HttpClient.newHttpClient();
    private String NOMBRE_RECURSO = "votospublicaciones";
    
    public VotoPublicacionWS(){
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
    }
    
    @WebMethod(operationName = "listarVotosPublicacion")
    public List<VotoPublicacion>listarVotosPublicacion()throws Exception{
        String url =this.urlBase+"/"+this.NOMBRE_RECURSO;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper= new ObjectMapper();
        List<VotoPublicacion>votospubli=mapper.readValue(json,new TypeReference<List<VotoPublicacion>>() {});
        return votospubli;
    }
    
    @WebMethod(operationName = "obtenerVotoPublicacion")
    public VotoPublicacion obtenerVotoPublicacion(@WebParam(name = "idvotoPubli") int id)throws Exception{
        String url = this.urlBase + "/" + this.NOMBRE_RECURSO + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper= new ObjectMapper();
        VotoPublicacion votopubli = mapper.readValue(json, VotoPublicacion.class);
        
        return votopubli;
    }
    
    
    @WebMethod(operationName = "eliminarVotoPublicacion")
    public void eliminarVotoPublicacion (@WebParam(name = "idVotoPubli") int id) throws Exception {
        String url = this.urlBase + "/" + this.NOMBRE_RECURSO + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod (operationName ="guardarVotoPublicacion")
    public void guardarVotoPublicacion(@WebParam(name = "votopublicacion") VotoPublicacion votoPublicacion, @WebParam(name = "estado") Estado estado) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(votoPublicacion);
        
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
            url = this.urlBase+"/"+this.NOMBRE_RECURSO+"/"+votoPublicacion.getId();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type","application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();
        }
        
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
//    
//    private final IVotoPublicacionBO votoPublicaBO;
//
//    public VotoPublicacionWS() {
//        votoPublicaBO = new VotoPublicacionBOImpl();
//    }
//
//    @WebMethod(operationName = "listarVotosPublicacion")
//    public List<VotoPublicacion> listarVotosComentarios() {
//        return this.votoPublicaBO.listar();
//    }
//
//    @WebMethod(operationName = "obtenerVotoPublicacion")
//    public VotoPublicacion obtenerVotosComentarios(@WebParam(name = "id") int id) {
//        return this.votoPublicaBO.obtener(id);
//    }
//
//    @WebMethod(operationName = "eliminarVotoPublicacion")
//    public void eliminarVotoComentario(@WebParam(name="id")int id){
//        this.votoPublicaBO.eliminar(id);
//    }
//    @WebMethod (operationName ="guardarVotoPublicacion")
//    public void guardarVotosComentarios(
//        @WebParam(name="public")VotoPublicacion voto,
//        @WebParam(name="estado")Estado estado ){
//        this.votoPublicaBO.guardar(voto, estado);
//    }
}
