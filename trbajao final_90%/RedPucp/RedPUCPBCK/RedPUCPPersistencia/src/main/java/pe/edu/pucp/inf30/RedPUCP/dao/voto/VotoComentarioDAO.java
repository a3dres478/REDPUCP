/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.dao.voto;

//import pe.edu.pucp.inf30.RedPUCP.dao.ICrud;
import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.dao.PersistibleTransaccional;
import pe.edu.pucp.inf30.RedPUCP.modelo.voto.VotoComentario;
/**
 *
 * @author andre
 */
public interface VotoComentarioDAO extends PersistibleTransaccional<VotoComentario,Integer>{
    List<VotoComentario> listarVotosXComentario(int idComentario);
    void eliminarVotoComentario(int idUsuario,int idComentario);
}
