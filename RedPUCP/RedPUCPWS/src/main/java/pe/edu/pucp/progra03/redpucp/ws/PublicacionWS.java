/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Publicacion;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IPublicacionBO;
import pe.edu.pucp.progra03.redpucp.boimpl.PublicacionBOImpl;

/**
 *
 * @author andre
 */
@WebService(serviceName = "PublicacionWS",
        targetNamespace = "https://services.redpucp.ws/")
public class PublicacionWS {

    private final IPublicacionBO publicacionBO;

    public PublicacionWS() {
        publicacionBO = new PublicacionBOImpl();
    }

    @WebMethod(operationName = "listarPublicaciones")
    public List<Publicacion> listarPublicaciones() {
        return this.publicacionBO.listar();
    }

    @WebMethod(operationName = "obtenerPublicacion")
    public Publicacion obtenerPublicacion(@WebParam(name = "id") int id) {
        return this.publicacionBO.obtener(id);
    }

    @WebMethod(operationName = "eliminarPublicacion")
    public void eliminarPublicacion(@WebParam(name="id")int id){
        this.publicacionBO.eliminar(id);
    }
    @WebMethod (operationName ="guardarPublicacion")
    public void guardarPublicacion(@WebParam(name="public")Publicacion publi,@WebParam(name="estado")Estado estado ){
        this.publicacionBO.guardar(publi, estado);
    }
}
