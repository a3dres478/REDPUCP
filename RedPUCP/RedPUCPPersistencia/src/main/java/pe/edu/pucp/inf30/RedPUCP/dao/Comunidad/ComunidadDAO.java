/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.dao.Comunidad;

//import pe.edu.pucp.inf30.RedPUCP.dao.ICrud;
import java.sql.Date;
import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.dao.Persistible;
import pe.edu.pucp.inf30.RedPUCP.dao.PersistibleTransaccional;
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.Comunidad;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;
/**
 *
 * @author andre
 */
public interface ComunidadDAO extends PersistibleTransaccional<Comunidad, Integer>{
    List<Comunidad> listarcomunidadfiltros(String descripcion);/*faltan agregar los filtros*/
}
