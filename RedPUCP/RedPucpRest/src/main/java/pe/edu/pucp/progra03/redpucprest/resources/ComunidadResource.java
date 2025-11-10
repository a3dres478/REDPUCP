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
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Comentario;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IComunidadBO;
import pe.edu.pucp.progra03.redpucp.boimpl.ComentarioBOImpl;
import pe.edu.pucp.progra03.redpucp.boimpl.ComunidadBOImpl;
/*-------------*/


/*-------------*/
/**
 *
 * @author andre
 */
@Path("comunidades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ComunidadResource {
    private IComunidadBO comunidadBO;
    
    public ComunidadResource(){
    comunidadBO=new ComunidadBOImpl();
    }
    @GET
    public List<Comunidad>listar(){
        return this.comunidadBO.listar();
    }
    
    @GET
    @Path ("nombre/{nombre}")
    public List<Comunidad>listarpornombre(@PathParam("nombre") String nombre){
        return this.comunidadBO.buscarcomunidadpornombres(nombre);
    }
    
    
    @GET
    @Path("{id}")
    public Response obtener (@PathParam("id")int id){
        Comunidad admin =this.comunidadBO.obtener(id);
        if(admin==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error","Comunidad: "+id+", no encontrado"))
                    .build();
        }
        return Response.ok(admin).build();
    }
    
    @POST
    public Response crear (Comunidad comuni){
        if(comuni == null || comuni.getNombre() == null || comuni.getNombre().isBlank()){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El comentario no es valido")
                    .build();
        }
        
        this.comunidadBO.guardar(comuni, Estado.Nuevo);
        URI location = URI.create("/RedPUCPRest/api/comunidades/"+comuni.getId_comunidad());
        
        return Response.ok(location)
                .entity(comuni)
                .build();
    }
    
    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id")int id,Comunidad comuni){
        if(comuni == null || comuni.getAdministrador()==null||comuni.getNombre().isBlank()){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error","La comunidad no es valida"))
                    .build();
        }
        
        if (this.comunidadBO.obtener(id)==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Administrador: "+ id + ", no encontrada")
                    .build();
        }
        this.comunidadBO.guardar(comuni, Estado.Modificar);
        return Response.ok(comuni).build();
    }
    
    
    @DELETE
    @Path("{id}")
    public Response eliminar (@PathParam("id") int id){
        if (this.comunidadBO.obtener(id)==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("comunidad: "+ id + ", no encontrada")
                    .build();
        }
        
        this.comunidadBO.eliminar(id);
        
        
        return Response.noContent().build();
    }
            
            
}
