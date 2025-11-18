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
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Publicacion;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IUsuario_comunBO;
import pe.edu.pucp.progra03.redpucp.boimpl.Usuario_comunBOImpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;

/**
 *
 * @author andre
 */
@WebService(serviceName = "Usuario_comunWS",
        targetNamespace = "https://services.redpucp.ws/")
public class Usuario_comunWS {
    private final ResourceBundle config;
    private final String urlBase;
    private HttpClient client = HttpClient.newHttpClient();
    private String NOMBRE_RECURSO = "usuarioscomunes";
    
    public Usuario_comunWS(){
        this.config=ResourceBundle.getBundle("app");
        this.urlBase=this.config.getString("app.services.rest.baseurl");       
    }
    
    @WebMethod(operationName = "ListarUsuariosComunes")
    public List<Usuario_comun>ListarUsuariosComunes()throws Exception{
        String url =this.urlBase+"/"+this.NOMBRE_RECURSO;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper = DateDeserializerUtil.getObjectMapperWithDateHandling();
        List<Usuario_comun>usuarios=mapper.readValue(json,new TypeReference<List<Usuario_comun>>() {});
        return usuarios;
    }
    
    @WebMethod(operationName = "obtenerUsuario")
    public Usuario_comun obtenerUsuario(@WebParam(name = "idUsuaricomun") int id)throws Exception{
        String url = this.urlBase + "/" + this.NOMBRE_RECURSO + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper = DateDeserializerUtil.getObjectMapperWithDateHandling();
        Usuario_comun usuario_comun = mapper.readValue(json, Usuario_comun.class);
        
        return usuario_comun;
    }
    
    @WebMethod(operationName = "eliminarUsuarioComun")
    public void eliminarUsuarioComun (@WebParam(name = "idUsuario") int id) throws Exception {
        String url = this.urlBase + "/" + this.NOMBRE_RECURSO + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod (operationName ="guardarUsuarioComun")
    public void guardarUsuarioComun(@WebParam(name = "usuarioComun") Usuario_comun usuariocomun, @WebParam(name = "estado") Estado estado) throws Exception{
//        ObjectMapper mapper = new ObjectMapper();
        ObjectMapper mapper = DateDeserializerUtil.getObjectMapperWithDateHandling();
        String json = mapper.writeValueAsString(usuariocomun);
        
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
            url = this.urlBase+"/"+this.NOMBRE_RECURSO+"/"+usuariocomun.getIdUsuario();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type","application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();
        }
        
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}   
