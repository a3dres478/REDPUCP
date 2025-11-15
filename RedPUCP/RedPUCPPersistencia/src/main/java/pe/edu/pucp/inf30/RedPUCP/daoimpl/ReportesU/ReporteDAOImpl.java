/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.daoimpl.ReportesU;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.config.DBManager; // Tu DBManager
import pe.edu.pucp.inf30.RedPUCP.dao.ReportesU.ReporteDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.TransaccionalBaseDAO; // Tu Base
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Publicacion.ComentarioDAOImpl; // Para hidratar
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Publicacion.PublicacionDAOimpl; // Para hidratar
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.Usuario_comunDAOimpl; // Para hidratar
import pe.edu.pucp.inf30.RedPUCP.modelo.ReportesU.Reporte;

/**
 *
 * @author Yo
 */
public class ReporteDAOImpl extends TransaccionalBaseDAO<Reporte> implements ReporteDAO {

    // --- Implementación de Métodos Abstractos de BaseDAO ---

    @Override
    protected CallableStatement comandoCrear(Connection conn, Reporte reporte) throws SQLException {
        // Asumo un SP con 'A' de Activo/Pendiente por defecto
        String sql = "{CALL sp_insertarReporte(?,?,?,?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);

        cmd.setString("p_tipo", reporte.getTipo());
        cmd.setString("p_detalle", reporte.getDetalle());

        // Un reporte puede ser de una publicación O un comentario.
        if (reporte.getPublicacionreportada() != null) {
            cmd.setInt("p_idPublicacion", reporte.getPublicacionreportada().getId());
        } else {
            cmd.setNull("p_idPublicacion", Types.INTEGER);
        }
        
        if (reporte.getComentarioreportado() != null) {
            cmd.setInt("p_idComentario", reporte.getComentarioreportado().getId());
        } else {
            cmd.setNull("p_idComentario", Types.INTEGER);
        }
        
        cmd.setInt("p_idPublicador", reporte.getPublicador().getIdUsuario());
        cmd.setInt("p_idReportador", reporte.getReportador().getIdUsuario());
        
        cmd.registerOutParameter("p_idGenerado", Types.INTEGER);
        return cmd;
    }

    @Override
    protected CallableStatement comandoActualizar(Connection conn, Reporte reporte) throws SQLException {
        // Asumo que actualizar es para cambiar el estado (ej: a Resuelto 'R')
        String sql = "{CALL sp_actualizarReporte(?,?,?,?)}"; // Asumo que se puede cambiar el estado
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idReporte", reporte.getId_report());
        cmd.setString("p_tipo", reporte.getTipo());
        cmd.setString("p_detalle", reporte.getDetalle());
        // cmd.setString("p_estado", String.valueOf(reporte.getEstado())); // Si tienes estado
        
        cmd.registerOutParameter("p_exito", Types.BOOLEAN);
        return cmd;
    }

    @Override
    protected CallableStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        // Asumo un borrado lógico (cambia estado a 'E' de Eliminado)
        String sql = "{CALL sp_eliminarReporte(?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idReporte", id);
        cmd.registerOutParameter("p_exito", Types.BOOLEAN);
        return cmd;
    }

    @Override
    protected CallableStatement comandoLeer(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL sp_obtenerReportePorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idReporte", id);
        return cmd;
    }

    @Override
    protected CallableStatement comandoLeerTodos(Connection conn) throws SQLException {
        // Por defecto, leerTodos() listará los pendientes/activos
        String sql = "{CALL sp_listarReportesPendientes()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }

    @Override
    protected Reporte mapearModelo(ResultSet rs) throws SQLException {
        Reporte rep = new Reporte();
        rep.setId_report(rs.getInt("idReporte"));
        rep.setTipo(rs.getString("tipo"));
        rep.setDetalle(rs.getString("detalle"));
        // rep.setEstado(rs.getString("estado").charAt(0)); // Si tienes estado

        // "Hidratar" objetos (Cargar los objetos completos)
        // Sigo el patrón de tus otros DAOImpl (N+1 queries)
        
        // Hidratar Publicacion
        int idPub = rs.getInt("idPublicacion");
        if (!rs.wasNull()) {
            rep.setPublicacionreportada(new PublicacionDAOimpl().leer(idPub));
        }
        
        // Hidratar Comentario
        int idCom = rs.getInt("idComentario");
        if (!rs.wasNull()) {
            rep.setComentarioreportado(new ComentarioDAOImpl().leer(idCom));
        }
        
        // Hidratar Publicador (Dueño del contenido)
        rep.setPublicador(new Usuario_comunDAOimpl().leer(rs.getInt("idPublicador")));
        
        // Hidratar Reportador (Quien reporta)
        rep.setReportador(new Usuario_comunDAOimpl().leer(rs.getInt("idReportador")));
        
        return rep;
    }

    // --- Implementación de Métodos Específicos de ReporteDAO ---

    // Método para el SP de listar por reportador
    protected CallableStatement comandoListarPorReportador(Connection conn, int idReportador) throws SQLException {
        String sql = "{CALL sp_listarReportesPorReportador(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idReportador", idReportador);
        return cmd;
    }

    @Override
    public List<Reporte> listarReportesPorReportador(int idReportador) {
        // Sigo el patrón de tu "ComunidadDAOImpl.listarcomunidadfiltros"
        try (
            Connection conn = DBManager.getInstance().getConnection();  
            PreparedStatement ps = this.comandoListarPorReportador(conn, idReportador);
        ) {
            ResultSet rs = ps.executeQuery();
            List<Reporte> modelos = new ArrayList<>();
            while (rs.next()) {
                modelos.add(this.mapearModelo(rs));
            }
            return modelos;
        } catch (SQLException e) {
            System.err.println("Error SQL durante el listado de reportes: " + e.getMessage());
            throw new RuntimeException("No se pudo listar el registro.", e);
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al listar los registros.", e);
        }
    }

    // Método para el SP de listar pendientes
    protected CallableStatement comandoListarPendientes(Connection conn) throws SQLException {
        String sql = "{CALL sp_listarReportesPendientes()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
    
    @Override
    public List<Reporte> listarReportesPendientes() {
        try (
            Connection conn = DBManager.getInstance().getConnection();  
            PreparedStatement ps = this.comandoListarPendientes(conn);
        ) {
            ResultSet rs = ps.executeQuery();
            List<Reporte> modelos = new ArrayList<>();
            while (rs.next()) {
                modelos.add(this.mapearModelo(rs));
            }
            return modelos;
        } catch (SQLException e) {
            System.err.println("Error SQL durante el listado de reportes pendientes: " + e.getMessage());
            throw new RuntimeException("No se pudo listar el registro.", e);
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al listar los registros.", e);
        }
    }
}