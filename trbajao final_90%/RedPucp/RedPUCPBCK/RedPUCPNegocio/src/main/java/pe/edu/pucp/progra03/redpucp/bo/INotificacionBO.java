/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.bo;

//import pe.edu.pucp.inf30.RedPUCP.dao.NotificacionesU.NotificacionDAO;
import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.modelo.NotificacionesU.Notificacion;

/**
 *
 * @author HECTOR
 */
public interface INotificacionBO extends IBaseBO<Notificacion> {
    List<Notificacion> listarPorUsuario(int idUsuarioNotificado);
}
