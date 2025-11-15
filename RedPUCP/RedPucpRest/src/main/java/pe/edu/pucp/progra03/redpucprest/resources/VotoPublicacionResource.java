/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucprest.resources;
/*  */
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
import pe.edu.pucp.inf30.RedPUCP.modelo.voto.VotoComentario;
import pe.edu.pucp.inf30.RedPUCP.modelo.voto.VotoPublicacion;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IVotoPublicacionBO;
import pe.edu.pucp.progra03.redpucp.boimpl.VotoPublicacionBOImpl;

/**
 *
 * @author andre
 */
@Path("votospublicacion")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VotoPublicacionResource {
    private IVotoPublicacionBO votopubli;
    
    public VotoPublicacionResource(){
        votopubli=new VotoPublicacionBOImpl();
    }
    
    
    @GET
    public List<VotoPublicacion>listar(){
        return this.votopubli.listar();
    }
    
    @GET
    @Path("{id}")
    public Response obtener (@PathParam("id")int id){
        VotoPublicacion admin =this.votopubli.obtener(id);
        if(admin==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error","Voto publicacion: "+id+", no encontrado"))
                    .build();
        }
        return Response.ok(admin).build();
    }
    
    @POST
    public Response crear (VotoPublicacion voto){
        if(voto == null || voto.getUsuario()==null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El Voto publicacion no es valido")
                    .build();
        }
        
        this.votopubli.guardar(voto, Estado.Nuevo);
        URI location = URI.create("/RedPUCPRest/api/votospublicacion/"+voto.getId());
        
        return Response.ok(location)
                .entity(voto)
                .build();
    }
    
    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id")int id,VotoPublicacion voto){
        if(voto == null || voto.getUsuario()==null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error","El voto no es valido"))
                    .build();
        }
        
        if (this.votopubli.obtener(id)==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("votopublicacion: "+ id + ", no encontrado")
                    .build();
        }
        this.votopubli.guardar(voto, Estado.Modificar);
        return Response.ok(voto).build();
    }
    
    
    @DELETE
    @Path("{id}")
    public Response eliminar (@PathParam("id") int id){
        if (this.votopubli.obtener(id)==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Voto publicacion: "+ id + ", no encontrado")
                    .build();
        }
        
        this.votopubli.eliminar(id);
        
        
        return Response.noContent().build();
    }
}
