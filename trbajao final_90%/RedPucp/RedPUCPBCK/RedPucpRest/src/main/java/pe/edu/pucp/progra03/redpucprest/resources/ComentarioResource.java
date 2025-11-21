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
    @Path ("publicacion/{idPublicacion}")
    public List<Comentario>listarComentariosXPublicacion(@PathParam("idPublicacion") int idPublicacion){
        return this.comentarioBO.listarComentariosXPublicacion(idPublicacion);
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
    
    @PUT
    @Path("{id}/imagen")
    @Consumes(MediaType.TEXT_PLAIN)  // Solo recibe texto plano (la URL)
    public Response actualizarImagenUrl(
        @PathParam("id") int id, 
        String imagenUrl) {
        
        try {
            // Validar que la publicación existe
            Comentario comentario = this.comentarioBO.obtener(id);
            if (comentario == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Comentario: " + id + ", no encontrado")
                        .build();
            }
            
            // Validar que la URL no esté vacía
            if (imagenUrl == null || imagenUrl.trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("La URL de la imagen no puede estar vacía")
                        .build();
            }
            
            // Actualizar solo la imagen
            this.comentarioBO.actualizarImagen(id, imagenUrl);
            
            return Response.ok().entity("Imagen actualizada correctamente").build();
            
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al actualizar imagen: " + e.getMessage())
                    .build();
        }
    }
}
