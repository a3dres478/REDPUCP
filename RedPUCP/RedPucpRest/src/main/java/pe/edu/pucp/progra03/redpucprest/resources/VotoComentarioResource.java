/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucprest.resources;
/**/
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
/**/

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;
import pe.edu.pucp.inf30.RedPUCP.modelo.voto.VotoComentario;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IVotoComentarioBO;
import pe.edu.pucp.progra03.redpucp.boimpl.VotoComentarioBOImpl;

/**
 *
 * @author andre
 */
@Path("votoscomentario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VotoComentarioResource {
    private IVotoComentarioBO votoscomBO;
    
    public VotoComentarioResource(){
        votoscomBO=new VotoComentarioBOImpl();
    }
    
    
    @GET
    public List<VotoComentario>listar(){
        return this.votoscomBO.listar();
    }
    
    @GET
    @Path("comentario/{idComentario}")
    public List<VotoComentario>listarVotosXComentario(@PathParam("idComentario") int idComentario){
        return this.votoscomBO.listarVotosXComentario(idComentario);
    }
    
    @GET
    @Path("{id}")
    public Response obtener (@PathParam("id")int id){
        VotoComentario modelo =this.votoscomBO.obtener(id);
        if(modelo==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error","Voto Comentario: "+id+", no encontrado"))
                    .build();
        }
        return Response.ok(modelo).build();
    }
    
    @POST
    public Response crear (VotoComentario voto){
        if(voto == null || voto.getUsuario()==null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El VotoComentario no es valido")
                    .build();
        }
        
        this.votoscomBO.guardar(voto, Estado.Nuevo);
        URI location = URI.create("/RedPUCPRest/api/votoscomentario/"+voto.getId());
        
        return Response.ok(location)
                .entity(voto)
                .build();
    }
    
    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id")int id,VotoComentario voto){
        if(voto == null || voto.getUsuario()==null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error","El voto no es valido"))
                    .build();
        }
        
        if (this.votoscomBO.obtener(id)==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("VotoComentario: "+ id + ", no encontrada")
                    .build();
        }
        this.votoscomBO.guardar(voto, Estado.Modificar);
        return Response.ok(voto).build();
    }
    
    
    @DELETE
    @Path("{id}")
    public Response eliminar (@PathParam("id") int id){
        if (this.votoscomBO.obtener(id)==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Voto Comentario: "+ id + ", no encontrada")
                    .build();
        }
        
        this.votoscomBO.eliminar(id);
        
        
        return Response.noContent().build();
    }
}
