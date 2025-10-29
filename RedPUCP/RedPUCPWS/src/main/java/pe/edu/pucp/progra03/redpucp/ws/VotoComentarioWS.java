/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.ws;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;

import pe.edu.pucp.progra03.redpucp.bo.IVotoComentarioBO;
import pe.edu.pucp.progra03.redpucp.boimpl.ComunidadBOImpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.voto.VotoComentario;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.boimpl.VotoComentarioBOImpl;
/**
 *
 * @author invitado123
 */
@WebService(serviceName = "VotoComentarioWS",
        targetNamespace = "https://services.redpucp.ws/")
public class VotoComentarioWS {
    private final IVotoComentarioBO votoBO;

    public VotoComentarioWS() {
        votoBO = new VotoComentarioBOImpl();
    }

    @WebMethod(operationName = "listarVotosComentario")
    public List<VotoComentario> listarVotosComentarios() {
        return this.votoBO.listar();
    }

    @WebMethod(operationName = "obtenerVotoComentario")
    public VotoComentario obtenerVotosComentarios(@WebParam(name = "id") int id) {
        return this.votoBO.obtener(id);
    }

    @WebMethod(operationName = "eliminarVotoComentario")
    public void eliminarVotoComentario(@WebParam(name="id")int id){
        this.votoBO.eliminar(id);
    }
    @WebMethod (operationName ="guardarVotoComentario")
    public void guardarVotosComentarios(@WebParam(name="public")VotoComentario voto,@WebParam(name="estado")Estado estado ){
        this.votoBO.guardar(voto, estado);
    }
}
