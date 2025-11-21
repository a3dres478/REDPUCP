/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.bo;
import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Publicacion;

/**
 *
 * @author andre
 */
public interface IPublicacionBO extends IBaseBO<Publicacion>{
    List<Publicacion> listarPublicacionesXFiltros(int idComunidad,String categoria,String ordenamiento);
    List<Publicacion> listarPublicacionesXUsuario(int idUsuario);
    List<Publicacion> listarPublicacionesVirales();
    void actualizarImagen(int idPublicacion, String imagenUrl);
}
