/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.bo;

import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.modelo.voto.VotoPublicacion;

/**
 *
 * @author invitado123
 */
public interface IVotoPublicacionBO extends IBaseBO<VotoPublicacion>{
    List<VotoPublicacion> listarVotosXPublicacion(int idPublicacion);
}
