/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.boimpl;

import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.dao.NotificacionesU.NotificacionDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.NotificacionesU.NotificacionDAOImpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.NotificacionesU.Notificacion;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.INotificacionBO;

/**
 *
 * @author HECTOR
 */
public class NotificacionBOImpl implements INotificacionBO {
    private final NotificacionDAO notificacionDAO;
    
    public NotificacionBOImpl(){
        notificacionDAO = new NotificacionDAOImpl();
    }
    
    @Override
    public List<Notificacion> listar(){
        return this.notificacionDAO.leerTodos();
    }

    @Override
    public Notificacion obtener(int id){
        return this.notificacionDAO.leer(id);
    }

    @Override 
    public void eliminar(int id){
        this.notificacionDAO.eliminar(id);
    }
    
    @Override 
    public void guardar(Notificacion notificacion,Estado estado){
        if(estado==Estado.Nuevo){
            int idGenerado = this.notificacionDAO.crear(notificacion);
            notificacion.setId_notip(idGenerado);
        }else{
            this.notificacionDAO.actualizar(notificacion);
        }
    }
    
    @Override
    public List<Notificacion> listarPorUsuario(int idUsuarioNotificado){
        return this.notificacionDAO.listarPorUsuario(idUsuarioNotificado);
    }
}
