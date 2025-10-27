/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.ws;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Comentario;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IComentarioBO;
import pe.edu.pucp.progra03.redpucp.boimpl.ComentarioBOImpl;

/**
 *
 * @author andre
 */
public class ComentarioWS {
    private final IComentarioBO comentarioBO;
    public ComentarioWS(){
        comentarioBO=new ComentarioBOImpl();
    }
    @WebMethod(operationName = "listarComentario")
    public List<Comentario>listarComentarios(){
        return this.comentarioBO.listar();
    }
    @WebMethod (operationName ="guardarComentario")
    public void guardarComentario(@WebParam(name="comentario")Comentario comentario,@WebParam(name="estado")Estado estado){
        this.comentarioBO.guardar(comentario,estado);
    }
    
    @WebMethod (operationName = "obtenerComentario")
    public Comentario obtenerComentario(@WebParam(name="id")int id){
        return this.comentarioBO.obtener(id);
    }
    @WebMethod (operationName = "eliminarAcademia")
    public void eliminarComentario(@WebParam(name="id")int id){
        this.comentarioBO.eliminar(id);
    }
    
    
}
