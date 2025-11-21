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
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Administrador;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IAdministradorBO;
import pe.edu.pucp.progra03.redpucp.boimpl.AdministradorBOImpl;


/**
 *
 * @author andre
 */
@WebService(serviceName = "AdministradorWS",
        targetNamespace = "https://services.redpucp.ws/")
public class AdministradorWS {
    private final ResourceBundle config;
    private final String urlBase;
    private HttpClient client = HttpClient.newHttpClient();
    private String NOMBRE_RECURSO = "administradores";
    
    public AdministradorWS(){
        this.config= ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
    }
    
    @WebMethod(operationName = "listarAdministradores")
    public List<Administrador>listarAdministradores()throws Exception{
        String url =this.urlBase+"/"+this.NOMBRE_RECURSO;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        
        ObjectMapper mapper = DateDeserializerUtil.getObjectMapperWithDateHandling();

        List<Administrador>administradores=mapper.readValue(json,new TypeReference<List<Administrador>>() {});
        return administradores;
    }
    
    @WebMethod(operationName = "guardarAdministrador")
    public void guardarAdministrador(@WebParam(name = "administrador") Administrador administrador, @WebParam(name = "estado") Estado estado) throws Exception{
//        ObjectMapper mapper = new ObjectMapper();
        ObjectMapper mapper = DateDeserializerUtil.getObjectMapperWithDateHandling();
        String json = mapper.writeValueAsString(administrador);
        
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
            url = this.urlBase+"/"+this.NOMBRE_RECURSO+"/"+administrador.getIdUsuario();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type","application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();
        }
        
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod (operationName = "obtenerAdmin")
    public Administrador obtenerAdministrador(@WebParam(name = "idUsuario") int id)throws Exception{
        String url = this.urlBase + "/" + this.NOMBRE_RECURSO + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper= DateDeserializerUtil.getObjectMapperWithDateHandling();
        Administrador administrador = mapper.readValue(json, Administrador.class);
        
        return administrador;
    }
    
    @WebMethod (operationName = "eliminarAdministrador")
    public void eliminarAdministrador (@WebParam(name = "idUsuario") int id) throws Exception {
        String url = this.urlBase + "/" + this.NOMBRE_RECURSO + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod(operationName = "verificarLoginAdmin")
    public boolean verificarLoginAdmin(@WebParam(name = "email") String email, 
                                 @WebParam(name = "password") String password) {
        try {
            String url = this.urlBase + "/" + this.NOMBRE_RECURSO + "/login";

            // Codificar los parámetros para URL
            String formData = "email=" + java.net.URLEncoder.encode(email, "UTF-8") + 
                             "&password=" + java.net.URLEncoder.encode(password, "UTF-8");

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(formData))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar que la respuesta sea exitosa
            if (response.statusCode() == 200) {
                return Boolean.parseBoolean(response.body().trim());
            } else {
                System.err.println("Error en login, código: " + response.statusCode());
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @WebMethod(operationName = "obtenerAdminPorCorreo")
    public Administrador obtenerAdminPorCorreo(@WebParam(name = "correo") String correo) throws Exception {
        String url = this.urlBase + "/" + this.NOMBRE_RECURSO + "/correo/" + correo;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 404) {
            return null;
        }

        String json = response.body();
        ObjectMapper mapper = DateDeserializerUtil.getObjectMapperWithDateHandling();
        Administrador admin = mapper.readValue(json, Administrador.class);

        return admin;
    }
}