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
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.ComunidadxPublicacionPrincipal;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IComunidadXPublicacionPrincipalBO;
import pe.edu.pucp.progra03.redpucp.boimpl.ComunidadXPublicacionPrincipalBOImpl;
/**
 *
 * @author HECTOR
 */

@Path("comunidadXpublicacionPrincipal")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ComunidadXPublicacionPrincipal_Resource {
    private final IComunidadXPublicacionPrincipalBO comunidadXpublicacion_principalBO;
    
    public ComunidadXPublicacionPrincipal_Resource(){
        comunidadXpublicacion_principalBO = new ComunidadXPublicacionPrincipalBOImpl();
    }
    
    @GET
    public List<ComunidadxPublicacionPrincipal> listar(){
        return this.comunidadXpublicacion_principalBO.listar();
    }
    
    @GET
    @Path("{id}")
    public Response obtener(@PathParam("id")int id){
        ComunidadxPublicacionPrincipal comuXpubli_principal = this.comunidadXpublicacion_principalBO.obtener(id);
        if(comuXpubli_principal==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error","ComunidadXpublicacion_principal: "+id+", no encontrada"))
                    .build();
        }
        return Response.ok(comuXpubli_principal).build();
    }
    
    @POST
    public Response crear (ComunidadxPublicacionPrincipal comuXpubli_principal){
        if(comuXpubli_principal == null || comuXpubli_principal.getComuni()== null 
            || comuXpubli_principal.getPublicacion()==null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("La ComunidadXpublicacion_principal no es valida")
                    .build();
        }
        
        this.comunidadXpublicacion_principalBO.guardar(comuXpubli_principal, Estado.Nuevo);
        URI location = URI.create("/RedPUCPRest/api/comunidadXpublicacionPrincipal/"
            +comuXpubli_principal.getIdComunidadxPublicacionPrincipal());
        
        return Response.ok(location)
                .entity(comuXpubli_principal)
                .build();
    }
    
    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id")int id,ComunidadxPublicacionPrincipal comuXpubli_principal){
        if(comuXpubli_principal == null || comuXpubli_principal.getComuni()== null 
            || comuXpubli_principal.getPublicacion()==null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error","La ComunidadXpublicacion_principal no es valida"))
                    .build();
        }
        
        if (this.comunidadXpublicacion_principalBO.obtener(id)==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("ComunidadXpublicacion_principal: "+ id + ", no encontrada")
                    .build();
        }
        this.comunidadXpublicacion_principalBO.guardar(comuXpubli_principal, Estado.Modificar);
        return Response.ok(comuXpubli_principal).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response eliminar (@PathParam("id") int id){
        if (this.comunidadXpublicacion_principalBO.obtener(id)==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("ComunidadXpublicacion_principal: "+ id + ", no encontrada")
                    .build();
        }
        
        this.comunidadXpublicacion_principalBO.eliminar(id);
        
        
        return Response.noContent().build();
    }
}
