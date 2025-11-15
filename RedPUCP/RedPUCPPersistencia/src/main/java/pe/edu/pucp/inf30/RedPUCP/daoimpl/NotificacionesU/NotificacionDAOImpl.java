/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.daoimpl.NotificacionesU;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.config.DBManager; // Tu DBManager
import pe.edu.pucp.inf30.RedPUCP.dao.NotificacionesU.NotificacionDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.TransaccionalBaseDAO; // Tu Base
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Publicacion.PublicacionDAOimpl; // Para hidratar
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.Usuario_comunDAOimpl; // Para hidratar
import pe.edu.pucp.inf30.RedPUCP.modelo.NotificacionesU.Notificacion;

/**
 *
 * @author TYo
 */
public class NotificacionDAOImpl extends TransaccionalBaseDAO<Notificacion> implements NotificacionDAO {

    @Override
    protected CallableStatement comandoCrear(Connection conn, Notificacion noti) throws SQLException {
        // Asumo que se crea con estado 'N' (No Leída)
        String sql = "{CALL sp_insertarNotificacion(?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setString("p_tipo", noti.getTipo());
        cmd.setInt("p_idPublicacion", noti.getPublicacionnotificada().getId());
        cmd.setInt("p_idUsuarioNotificado", noti.getNotificar().getIdUsuario());
        
        cmd.registerOutParameter("p_idGenerado", Types.INTEGER);
        return cmd;
    }

    @Override
    protected CallableStatement comandoActualizar(Connection conn, Notificacion noti) throws SQLException {
        // Asumo que actualizar es para marcarla como Leída ('L')
        String sql = "{CALL sp_marcarNotificacionLeida(?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idNotificacion", noti.getId_notip());
        cmd.registerOutParameter("p_exito", Types.BOOLEAN);
        return cmd;
    }

    @Override
    protected CallableStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        // Asumo un borrado físico para notificaciones
        String sql = "{CALL sp_eliminarNotificacion(?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idNotificacion", id);
        cmd.registerOutParameter("p_exito", Types.BOOLEAN);
        return cmd;
    }

    @Override
    protected CallableStatement comandoLeer(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL sp_obtenerNotificacionPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idNotificacion", id);
        return cmd;
    }

    @Override
    protected CallableStatement comandoLeerTodos(Connection conn) throws SQLException {
        // LeerTodos() listará todas las notificaciones, sin filtrar
        String sql = "{CALL sp_listarNotificaciones()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }

    @Override
    protected Notificacion mapearModelo(ResultSet rs) throws SQLException {
        Notificacion noti = new Notificacion();
        
        noti.setId_notip(rs.getInt("idNotificacion"));
        noti.setTipo(rs.getString("tipo"));
        // noti.setEstado(rs.getString("estado").charAt(0)); // Si tienes estado

        // "Hidratar" objetos (Cargar los objetos completos)
        
        // Hidratar Publicacion
        noti.setPublicacionnotificada(new PublicacionDAOimpl().leer(rs.getInt("idPublicacion")));
        
        // Hidratar Usuario a notificar
        noti.setNotificar(new Usuario_comunDAOimpl().leer(rs.getInt("idUsuarioNotificado")));
        
        return noti;
    }

    // --- Implementación de Métodos Específicos de NotificacionDAO ---
    
    // Método para el SP de listar por usuario
    protected CallableStatement comandoListarPorUsuario(Connection conn, int idUsuarioNotificado) throws SQLException {
        // Asumo que este SP solo trae las No Leídas ('N')
        String sql = "{CALL sp_listarNotificacionesPorUsuario(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idUsuario", idUsuarioNotificado);
        return cmd;
    }

    @Override
    public List<Notificacion> listarPorUsuario(int idUsuarioNotificado) {
        // Sigo el patrón de tu "ComunidadDAOImpl.listarcomunidadfiltros"
        try (
            Connection conn = DBManager.getInstance().getConnection();  
            PreparedStatement ps = this.comandoListarPorUsuario(conn, idUsuarioNotificado);
        ) {
            ResultSet rs = ps.executeQuery();
            List<Notificacion> modelos = new ArrayList<>();
            while (rs.next()) {
                modelos.add(this.mapearModelo(rs));
            }
            return modelos;
        } catch (SQLException e) {
            System.err.println("Error SQL durante el listado de notificaciones: " + e.getMessage());
            throw new RuntimeException("No se pudo listar el registro.", e);
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al listar los registros.", e);
        }
    }
}
