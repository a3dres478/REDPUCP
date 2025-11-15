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
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Publicacion;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IUsuarioBO;
import pe.edu.pucp.progra03.redpucp.bo.IUsuario_comunBO;
import pe.edu.pucp.progra03.redpucp.boimpl.Usuario_comunBOImpl;

/*--------------*/
/**
 *
 * @author andre
 */
@Path("usuarioscomunes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioComunResource {
    private IUsuario_comunBO comunBO;
    
    public UsuarioComunResource(){
        comunBO=new Usuario_comunBOImpl();
    }
    
    @GET
    public List<Usuario_comun>listar(){
        return this.comunBO.listar();
    }
    
    @GET
    @Path("{id}")
    public Response obtener (@PathParam("id")int id){
        Usuario_comun modelo =this.comunBO.obtener(id);
        if(modelo==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error","Usuario comun: "+id+", no encontrado"))
                    .build();
        }
        return Response.ok(modelo).build();
    }
    
    @POST
    public Response crear (Usuario_comun modelo){
        if(modelo == null || modelo.getNombre()==null || modelo.getNombre().isBlank()){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El usuario no es valido")
                    .build();
        }
        
        this.comunBO.guardar(modelo, Estado.Nuevo);
        URI location = URI.create("/RedPUCPRest/api/usuarioscomunes/"+modelo.getIdUsuario());
        
        return Response.ok(location)
                .entity(modelo)
                .build();
    }
    
    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id")int id,Usuario_comun modelo){
        if(modelo == null || modelo.getNombre()==null || modelo.getNombre().isBlank()){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error","El usuario no es valido"))
                    .build();
        }
        
        if (this.comunBO.obtener(id)==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuario común: "+ id + ", no encontrado")
                    .build();
        }
        this.comunBO.guardar(modelo, Estado.Modificar);
        return Response.ok(modelo).build();
    }
    
    
    @DELETE
    @Path("{id}")
    public Response eliminar (@PathParam("id") int id){
        if (this.comunBO.obtener(id)==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuario: "+ id + ", no encontrado")
                    .build();
        }
        
        this.comunBO.eliminar(id);
        
        
        return Response.noContent().build();
    }
}
