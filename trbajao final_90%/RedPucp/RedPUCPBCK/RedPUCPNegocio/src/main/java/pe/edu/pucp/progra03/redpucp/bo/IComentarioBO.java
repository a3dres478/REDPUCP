/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.bo;
import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Comentario;
/**
 *
 * @author andre
 */
public interface IComentarioBO extends IBaseBO<Comentario>{
    List<Comentario>listarComentariosXPublicacion(int idPublicacion);
    void actualizarImagen(int idComentario, String imagenUrl);
}
