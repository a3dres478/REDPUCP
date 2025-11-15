/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucprest.resources;
/*-----------------*/
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
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Comentario;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IComentarioBO;
import pe.edu.pucp.progra03.redpucp.boimpl.ComentarioBOImpl;
/*-----------------*/

/*-----------------*/
/**
 *
 * @author andre
 */
@Path("comentarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ComentarioResource {
    private IComentarioBO comentarioBO;
    
    public ComentarioResource(){
        comentarioBO=new ComentarioBOImpl();
    }
    @GET
    public List<Comentario>listar(){
        return this.comentarioBO.listar();
    }
    
    @GET
    @Path("{id}")
    public Response obtener (@PathParam("id")int id){
        Comentario modelo =this.comentarioBO.obtener(id);
        if(modelo==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error","Comentario: "+id+", no encontrado"))
                    .build();
        }
        return Response.ok(modelo).build();
    }
    
    @POST
    public Response crear (Comentario modelo){
        if(modelo == null || modelo.getAutor() == null ){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El comentario no es valido")
                    .build();
        }
        
        this.comentarioBO.guardar(modelo, Estado.Nuevo);
        URI location = URI.create("/RedPUCPRest/api/comentarios/"+modelo.getId());
        
        return Response.ok(location)
                .entity(modelo)
                .build();
    }
    
    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id")int id,Comentario modelo){
        if(modelo == null || modelo.getAutor()==null||modelo.getContenido().isBlank()){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error","El comentario no es valido"))
                    .build();
        }
        
        if (this.comentarioBO.obtener(id)==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Comentario: "+ id + ", no encontrada")
                    .build();
        }
        this.comentarioBO.guardar(modelo, Estado.Modificar);
        return Response.ok(modelo).build();
    }
    
    
    @DELETE
    @Path("{id}")
    public Response eliminar (@PathParam("id") int id){
        if (this.comentarioBO.obtener(id)==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Comentario: "+ id + ", no encontrado")
                    .build();
        }
        
        this.comentarioBO.eliminar(id);
        
        
        return Response.noContent().build();
    }
}
