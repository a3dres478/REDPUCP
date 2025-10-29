/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.ws;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Administrador;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IAdministradorBO;
import pe.edu.pucp.progra03.redpucp.boimpl.AdministradorBOImpl;


/**
 *
 * @author andre
 */
@WebService(serviceName = "AdministradorWS",
        targetNamespace = "https://services.redpucp.ws/")
public class AdministradorWS {
    private final IAdministradorBO administradorBO;
    
    public AdministradorWS(){
     administradorBO=new AdministradorBOImpl();
    }
    
    @WebMethod(operationName = "listarAdministradores")
    public List<Administrador>listarAdministradores(){
        return this.administradorBO.listar();
    }
    @WebMethod (operationName ="guardarAdministrador")
    public void guardarAdministrador(@WebParam(name="admin")Administrador admin,@WebParam(name="estado")Estado estado){
        this.administradorBO.guardar(admin,estado);
    }
    @WebMethod (operationName = "obtenerAdmin")
    public Administrador obtenerAdministrador(@WebParam(name="id")int id){
        return this.administradorBO.obtener(id);
    }
    @WebMethod (operationName = "eliminarAdministrador")
    public void eliminarAdministrador(@WebParam(name="id")int id){
        this.administradorBO.eliminar(id);
    }
}