/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucprest.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.Map;
import pe.edu.pucp.inf30.RedPUCP.modelo.NotificacionesU.Notificacion;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.INotificacionBO;
import pe.edu.pucp.progra03.redpucp.boimpl.NotificacionBOImpl;


/**
 *
 * @author HECTOR
 */
@Path("notificaciones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NotificacionResource {
    private final INotificacionBO notificacionBO;
    
    public NotificacionResource(){
        notificacionBO = new NotificacionBOImpl();
    }
    
    @GET
    public List<Notificacion> listar(){
        return this.notificacionBO.listar();
    }
    
    @GET
    @Path("usuario/{idUsuario}")
    public List listarNotificacionesParaUsuario(@PathParam("idUsuario")int idUsuario){
        List<Notificacion> notificaciones = this.notificacionBO.listarPorUsuario(idUsuario);
        return notificaciones;
    }
    
    @GET
    @Path("{id}")
    public Response obtener(@PathParam("id")int id){
        Notificacion notificacion = this.notificacionBO.obtener(id);
        if(notificacion==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error","Notificacion: "+id+", no encontrada"))
                    .build();
        }
        return Response.ok(notificacion).build();
    }
    
    @POST
    public Response crear (Notificacion notificacion){
        if(notificacion == null || notificacion.getPublicacionnotificada()== null 
            || notificacion.getUsuarioNotificado()==null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("La notificacion no es valida")
                    .build();
        }
        
        this.notificacionBO.guardar(notificacion, Estado.Nuevo);
        URI location = URI.create("/RedPUCPRest/api/notificaciones/"+notificacion.getId_notip());
        
        return Response.ok(location)
                .entity(notificacion)
                .build();
    }
    
    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id")int id,Notificacion notificacion){
        if(notificacion == null || notificacion.getPublicacionnotificada()== null 
            || notificacion.getUsuarioNotificado()==null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error","La notificacion no es valida"))
                    .build();
        }
        
        if (this.notificacionBO.obtener(id)==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Notificacion: "+ id + ", no encontrada")
                    .build();
        }
        this.notificacionBO.guardar(notificacion, Estado.Modificar);
        return Response.ok(notificacion).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response eliminar (@PathParam("id") int id){
        if (this.notificacionBO.obtener(id)==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Notificacion: "+ id + ", no encontrada")
                    .build();
        }
        
        this.notificacionBO.eliminar(id);
        
        
        return Response.noContent().build();
    }
}
