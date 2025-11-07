/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucprest.resources;

/*-------------*/
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
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.Comunidad;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Publicacion;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IPublicacionBO;
import pe.edu.pucp.progra03.redpucp.boimpl.PublicacionBOImpl;
/*-------------*/
/*-------------*/
/**
 *
 * @author andre
 */
@Path("publicaciones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PublicacionResource {
    private IPublicacionBO publicacionBO;
    
    public PublicacionResource(){
        publicacionBO=new PublicacionBOImpl();
    }
    
    @GET
    public List<Publicacion>listar(){
        return this.publicacionBO.listar();
    }
    
    @GET
    @Path("{id}")
    public Response obtener (@PathParam("id")int id){
        Publicacion admin =this.publicacionBO.obtener(id);
        if(admin==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error","Publicacion: "+id+", no encontrado"))
                    .build();
        }
        return Response.ok(admin).build();
    }
    
    @POST
    public Response crear (Publicacion comuni){
        if(comuni == null || comuni.getAutor() == null || comuni.getTitulo().isBlank()){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("La publiacion no es valido")
                    .build();
        }
        
        this.publicacionBO.guardar(comuni, Estado.Nuevo);
        URI location = URI.create("/RedPUCPRest/api/publicaciones/"+comuni.getId());
        
        return Response.ok(location)
                .entity(comuni)
                .build();
    }
    
    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id")int id,Publicacion comuni){
        if(comuni == null || comuni.getAutor() == null || comuni.getTitulo().isBlank()){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error","La publicacion no es valida"))
                    .build();
        }
        
        if (this.publicacionBO.obtener(id)==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Publicacion: "+ id + ", no encontrada")
                    .build();
        }
        this.publicacionBO.guardar(comuni, Estado.Modificar);
        return Response.ok(comuni).build();
    }
    
    
    @DELETE
    @Path("{id}")
    public Response eliminar (@PathParam("id") int id){
        if (this.publicacionBO.obtener(id)==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Publicacion: "+ id + ", no encontrada")
                    .build();
        }
        
        this.publicacionBO.eliminar(id);
        
        
        return Response.noContent().build();
    }
    
    
}
