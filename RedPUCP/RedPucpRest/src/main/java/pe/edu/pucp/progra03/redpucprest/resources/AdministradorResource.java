/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucprest.resources;
/*-------------------------------------*/
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
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Administrador;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IAdministradorBO;
import pe.edu.pucp.progra03.redpucp.boimpl.AdministradorBOImpl;
/*-----------------------------------*/

/*    */

/**
 *
 * @author andre main
 */
@Path("administradores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)


public class AdministradorResource {
    private IAdministradorBO adminBO;
    
    public AdministradorResource(){
        adminBO=new AdministradorBOImpl();
    }
    
    @GET
    public List<Administrador>listar(){
        return this.adminBO.listar();
    }
    
    @GET
    @Path("{id}")
    public Response obtener (@PathParam("id")int id){
        Administrador admin =this.adminBO.obtener(id);
        if(admin==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error","Administrador: "+id+", no encontrada"))
                    .build();
        }
        return Response.ok(admin).build();
    }
    
    @POST
    public Response crear (Administrador admin){
        if(admin == null || admin.getNombre() == null || admin.getNombre().isBlank()){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El admin no es valido")
                    .build();
        }
        
        this.adminBO.guardar(admin, Estado.Nuevo);
        URI location = URI.create("/RedPUCPRest/api/administradores/"+admin.getIdUsuario());
        
        return Response.ok(location)
                .entity(admin)
                .build();
    }
    
    @PUT
    @Path("{id}")
    public Response actualizar(@PathParam("id")int id,Administrador admin){
        if(admin == null || admin.getNombre() == null || admin.getNombre().isBlank()){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error","Los datos de administrador no son validos"))
                    .build();
        }
        
        if (this.adminBO.obtener(id)==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Administrador: "+ id + ", no encontrada")
                    .build();
        }
        this.adminBO.guardar(admin, Estado.Modificar);
        return Response.ok(admin).build();
    }
    
    
    @DELETE
    @Path("{id}")
    public Response eliminar (@PathParam("id") int id){
        if (this.adminBO.obtener(id)==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Administrador: "+ id + ", no encontrado")
                    .build();
        }
        
        this.adminBO.eliminar(id);
        
        
        return Response.noContent().build();
    }
}
