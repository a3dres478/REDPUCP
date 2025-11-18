/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.bo;

import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.modelo.voto.VotoComentario;

/**
 *
 * @author invitado123 main
 */
public interface IVotoComentarioBO extends IBaseBO<VotoComentario>{
    List<VotoComentario> listarVotosXComentario(int idComentario);
}
