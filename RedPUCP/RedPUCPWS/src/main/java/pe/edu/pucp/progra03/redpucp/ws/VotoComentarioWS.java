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
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;

import pe.edu.pucp.progra03.redpucp.bo.IVotoComentarioBO;
import pe.edu.pucp.progra03.redpucp.boimpl.ComunidadBOImpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.voto.VotoComentario;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.boimpl.VotoComentarioBOImpl;

/**
 *
 * @author invitado123
 */
@WebService(serviceName = "VotoComentarioWS",
        targetNamespace = "https://services.redpucp.ws/")
public class VotoComentarioWS {

    private final ResourceBundle config;
    private final String urlBase;
    private HttpClient client = HttpClient.newHttpClient();
    private String NOMBRE_RECURSO = "votoscomentarios";

    public VotoComentarioWS() {
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
    }
    
    @WebMethod(operationName = "listarVotosComentario")
    public List<VotoComentario>listarVotosComentario()throws Exception{
        String url =this.urlBase+"/"+this.NOMBRE_RECURSO;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper= new ObjectMapper();
        List<VotoComentario>votoscom=mapper.readValue(json,new TypeReference<List<VotoComentario>>() {});
        return votoscom;
    }
    
    @WebMethod(operationName = "obtenerVotoComentario")
    public VotoComentario obtenerVotoComentario(@WebParam(name = "idvotoComen") int id)throws Exception{
        String url = this.urlBase + "/" + this.NOMBRE_RECURSO + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper= new ObjectMapper();
        VotoComentario votocom = mapper.readValue(json, VotoComentario.class);
        
        return votocom;
    }
    
    
    @WebMethod(operationName = "eliminarVotoComentario")
    public void eliminarVotoComentario (@WebParam(name = "idVotoComen") int id) throws Exception {
        String url = this.urlBase + "/" + this.NOMBRE_RECURSO + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod (operationName ="guardarVotoComentario")
    public void guardarVotoComentario(@WebParam(name = "votocomentario") VotoComentario votocomentario, @WebParam(name = "estado") Estado estado) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(votocomentario);
        
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
            url = this.urlBase+"/"+this.NOMBRE_RECURSO+"/"+votocomentario.getId();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type","application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();
        }
        
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    
//    private final IVotoComentarioBO votoBO;
//
//    public VotoComentarioWS() {
//        votoBO = new VotoComentarioBOImpl();
//    }
//
//    @WebMethod(operationName = "listarVotosComentario")
//    public List<VotoComentario> listarVotosComentarios() {
//        return this.votoBO.listar();
//    }
//
//    @WebMethod(operationName = "obtenerVotoComentario")
//    public VotoComentario obtenerVotosComentarios(@WebParam(name = "id") int id) {
//        return this.votoBO.obtener(id);
//    }
//
//    @WebMethod(operationName = "eliminarVotoComentario")
//    public void eliminarVotoComentario(@WebParam(name="id")int id){
//        this.votoBO.eliminar(id);
//    }
//    @WebMethod (operationName ="guardarVotoComentario")
//    public void guardarVotosComentarios(@WebParam(name="public")VotoComentario voto,@WebParam(name="estado")Estado estado ){
//        this.votoBO.guardar(voto, estado);
//    }
}
