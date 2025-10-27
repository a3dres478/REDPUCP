/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.ws;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IUsuario_comunBO;
import pe.edu.pucp.progra03.redpucp.boimpl.Usuario_comunBOImpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;

/**
 *
 * @author andre
 */
@WebService(serviceName = "UsuarioWS", targetNamespace = "   v ")
public class Usuario_comunWS {
    private final IUsuario_comunBO usuariocomunBO;
    
    public Usuario_comunWS(){
        usuariocomunBO=new Usuario_comunBOImpl();
    }
    
    @WebMethod(operationName = "guardarUsuarioComun")
    public void guardarUsuarioComun(@WebParam(name = "usuario") Usuario_comun usuario1, @WebParam(name = "estado") Estado estado) {
        this.usuariocomunBO.guardar(usuario1, estado);
    }
    @WebMethod(operationName = "ListarUsuarioComun")
        public List<Usuario_comun>listarUsuarioComun(){
            return this.usuariocomunBO.listar();
        }
    @WebMethod(operationName = "obtenerUsuario")
    public Usuario_comun obtenerUsuarioComun(@WebParam(name="id")int id){
        return this.usuariocomunBO.obtener(id);
    }
    
    @WebMethod (operationName = "eliminarUsuarioComun")
    public void eliminarUsuarioComun(@WebParam(name="id")int id){
        this.usuariocomunBO.eliminar(id);
    }
    
    
}   
