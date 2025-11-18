/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.dao.Publicacion;
import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Publicacion;
//import pe.edu.pucp.inf30.RedPUCP.dao.ICrud;
import pe.edu.pucp.inf30.RedPUCP.dao.PersistibleTransaccional;

/**
 *
 * @author andre
 */
public interface PublicacionDAO extends PersistibleTransaccional<Publicacion,Integer>{
    List<Publicacion> listarPublicacionesXFiltros(String categoria,String ordenamiento);
}
