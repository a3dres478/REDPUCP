/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.ws;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;

import pe.edu.pucp.progra03.redpucp.bo.IVotoPublicacionBO;
import pe.edu.pucp.progra03.redpucp.boimpl.ComunidadBOImpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.voto.VotoPublicacion;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.boimpl.VotoPublicacionBOImpl;
/**
 *
 * @author invitado123
 */
public class VotoPublicacionWS {
    private final IVotoPublicacionBO votoPublicaBO;

    public VotoPublicacionWS() {
        votoPublicaBO = new VotoPublicacionBOImpl();
    }

    @WebMethod(operationName = "listarComunidades")
    public List<VotoPublicacion> listarVotosComentarios() {
        return this.votoPublicaBO.listar();
    }

    @WebMethod(operationName = "obtenerComunidad")
    public VotoPublicacion obtenerVotosComentarios(@WebParam(name = "id") int id) {
        return this.votoPublicaBO.obtener(id);
    }

    @WebMethod(operationName = "eliminarComunidad")
    public void eliminarVotoComentario(@WebParam(name="id")int id){
        this.votoPublicaBO.eliminar(id);
    }
    @WebMethod (operationName ="guardarComunidad")
    public void guardarVotosComentarios(@WebParam(name="public")VotoPublicacion comunidad,@WebParam(name="estado")Estado estado ){
        this.votoPublicaBO.guardar(comunidad, estado);
    }
}
