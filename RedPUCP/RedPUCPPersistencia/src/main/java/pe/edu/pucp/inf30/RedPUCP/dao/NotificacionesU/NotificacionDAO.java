/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.dao.NotificacionesU;

import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.dao.PersistibleTransaccional;
import pe.edu.pucp.inf30.RedPUCP.modelo.NotificacionesU.Notificacion;

/**
 *
 * @author aaaa
 */
public interface NotificacionDAO extends PersistibleTransaccional<Notificacion, Integer> {
    
    /**
     * Método específico clave para las notificaciones.
     * Lista todas las notificaciones pendientes (no leídas) para un usuario.
     * @param idUsuarioNotificado El ID del usuario del cual se quieren las notificaciones.
     * @return Una lista de notificaciones pendientes.
     */
    List<Notificacion> listarPorUsuario(int idUsuarioNotificado);
}