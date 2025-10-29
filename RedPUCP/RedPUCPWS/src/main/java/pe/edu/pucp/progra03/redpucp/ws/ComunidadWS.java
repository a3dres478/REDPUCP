/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.Comunidad;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IComunidadBO;
import pe.edu.pucp.progra03.redpucp.boimpl.ComunidadBOImpl;

/**
 *
 * @author invitado123
 */
@WebService(serviceName = "ComunidadWS",
        targetNamespace = "https://services.redpucp.ws/")
public class ComunidadWS {
    private final IComunidadBO comunidadBO;

    public ComunidadWS() {
        comunidadBO = new ComunidadBOImpl();
    }

    @WebMethod(operationName = "listarComunidades")
    public List<Comunidad> listarComunidades() {
        return this.comunidadBO.listar();
    }

    @WebMethod(operationName = "obtenerComunidad")
    public Comunidad obtenerComunidad(@WebParam(name = "id") int id) {
        return this.comunidadBO.obtener(id);
    }

    @WebMethod(operationName = "eliminarComunidad")
    public void eliminarComunidad(@WebParam(name="id")int id){
        this.comunidadBO.eliminar(id);
    }
    @WebMethod (operationName ="guardarComunidad")
    public void guardarComunidad(@WebParam(name="public")Comunidad comunidad,@WebParam(name="estado")Estado estado ){
        this.comunidadBO.guardar(comunidad, estado);
    }
}
