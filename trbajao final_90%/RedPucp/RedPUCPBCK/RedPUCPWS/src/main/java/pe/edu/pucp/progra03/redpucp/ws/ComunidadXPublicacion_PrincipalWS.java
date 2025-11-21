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
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.ComunidadxPublicacionPrincipal;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
/**
 *
 * @author HECTOR
 */
@WebService(serviceName = "ComunidadXPublicacion_PrincipalWS",
        targetNamespace = "https://services.redpucp.ws/")
public class ComunidadXPublicacion_PrincipalWS {
    private final ResourceBundle config;
    private final String urlBase;
    private HttpClient client =  HttpClient.newHttpClient();
    private String NOMBRE_RECURSO = "comunidadXpublicacionPrincipal";
    
    public ComunidadXPublicacion_PrincipalWS(){
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
    }
    
    @WebMethod(operationName = "listar_comunidadXpublicacion_principal")
    public List<ComunidadxPublicacionPrincipal>listar()throws Exception{
        String url =this.urlBase+"/"+this.NOMBRE_RECURSO;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper = DateDeserializerUtil.getObjectMapperWithDateHandling();
        List<ComunidadxPublicacionPrincipal>lista=mapper.readValue(json,new TypeReference<List<ComunidadxPublicacionPrincipal>>() {});
        return lista;
    }
    
    @WebMethod(operationName = "guardar_ComunidadXPublicacion_principal")
    public void guardar(@WebParam(name = "reporte") ComunidadxPublicacionPrincipal modelo,
        @WebParam(name = "estado") Estado estado) throws Exception{
        ObjectMapper mapper = DateDeserializerUtil.getObjectMapperWithDateHandling();
        String json = mapper.writeValueAsString(modelo);
        
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
            url = this.urlBase+"/"+this.NOMBRE_RECURSO+"/"+modelo.getIdComunidadxPublicacionPrincipal();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type","application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();
        }
        
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod (operationName = "obtener_ComunidadXPublicacion_Principal")
    public ComunidadxPublicacionPrincipal actualizar(@WebParam(name = "idComunidadXPublicacion_Principal") int id)throws Exception{
        String url = this.urlBase + "/" + this.NOMBRE_RECURSO + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper = DateDeserializerUtil.getObjectMapperWithDateHandling();
        ComunidadxPublicacionPrincipal reporte = mapper.readValue(json, ComunidadxPublicacionPrincipal.class);
        
        return reporte;
    }
    
    @WebMethod (operationName = "eliminar_ComunidadXPublicacion_Principal")
    public void eliminar (@WebParam(name = "idComunidadXPublicacion_Principal") int id) throws Exception {
        String url = this.urlBase + "/" + this.NOMBRE_RECURSO + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
