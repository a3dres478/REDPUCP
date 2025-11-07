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
    
    private UsuarioComunResource(){
        comunBO=new Usuario_comunBOImpl();
    }
    
    @GET
    public List<Usuario_comun>listar(){
        return this.comunBO.listar();
    }
    
    @GET
    @Path("{id}")
    public Response obtener (@PathParam("id")int id){
        Usuario_comun admin =this.comunBO.obtener(id);
        if(admin==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error","Publicacion: "+id+", no encontrado"))
                    .build();
        }
        return Response.ok(admin).build();
    }
    
    @POST
    public Response crear (Usuario_comun comuni){
        if(comuni == null || comuni.getNombre()==null || comuni.getNombre().isBlank()){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("La publiacion no es valido")
                    .build();
        }
        
        this.comunBO.guardar(comuni, Estado.Nuevo);
        URI location = URI.create("/RedPUCPRest/api/usuarioscomunes/"+comuni.getIdUsuario());
        
        return Response.ok(location)
                .entity(comuni)
                .build();
    }
    
    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id")int id,Usuario_comun comuni){
        if(comuni == null || comuni.getNombre()==null || comuni.getNombre().isBlank()){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error","El usuario no es valido"))
                    .build();
        }
        
        if (this.comunBO.obtener(id)==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuario común: "+ id + ", no encontrada")
                    .build();
        }
        this.comunBO.guardar(comuni, Estado.Modificar);
        return Response.ok(comuni).build();
    }
    
    
    @DELETE
    @Path("{id}")
    public Response eliminar (@PathParam("id") int id){
        if (this.comunBO.obtener(id)==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuario: "+ id + ", no encontrada")
                    .build();
        }
        
        this.comunBO.eliminar(id);
        
        
        return Response.noContent().build();
    }
    
}
