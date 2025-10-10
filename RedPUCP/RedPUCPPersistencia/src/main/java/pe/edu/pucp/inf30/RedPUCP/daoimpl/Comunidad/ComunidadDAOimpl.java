/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.daoimpl.Comunidad;

import java.util.Date;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.inf30.RedPUCP.dao.Comunidad.ComunidadDAO;
//import pe.edu.pucp.inf30.RedPUCP.daoimpl.BaseDAOImplement;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.TransaccionalBaseDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.Usuario_comunDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.Comunidad;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;

/**
 *
 * @author HECTOR
 */
public class ComunidadDAOimpl extends TransaccionalBaseDAO<Comunidad> implements ComunidadDAO {
    
    @Override
    protected CallableStatement comandoCrear(Connection conn, Comunidad com) throws SQLException {
        String sql = "{CALL sp_insertarComunidad(?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setString("p_nombre", com.getNombre());
        cmd.setString("p_descripcion", com.getDescripcion());
        cmd.setInt("p_idAdmin", com.getAdministrador().getIdUsuario());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        
        return cmd;
    }
    
    
    @Override
    protected CallableStatement comandoActualizar(Connection conn, Comunidad com) throws SQLException {
        String sql = "{CALL sp_actualizarComunidad(?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idComunidad", com.getId_comunidad());
        cmd.setString("p_nombre", com.getNombre());
        cmd.setString("p_descripcion", com.getDescripcion());
        cmd.registerOutParameter("p_exito", Types.BOOLEAN);
        
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL sp_eliminarComunidad(?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idComunidad", id);
        cmd.registerOutParameter("p_exito", Types.BOOLEAN);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoLeer(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL sp_obtenerComunidadPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idComunidad", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoLeerTodos(Connection conn) throws SQLException {
        String sql = "{CALL sp_listarComunidades()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
    
    @Override
    protected Comunidad mapearModelo(ResultSet rs) throws SQLException {
        Comunidad com = new Comunidad();
        com.setId_comunidad(rs.getInt("idComunidad"));
        com.setNombre(rs.getString("nombre"));
        com.setDescripcion(rs.getString("descripcion"));
        com.setFecha_creacion(rs.getDate("fechaCreacion"));
        com.setCantidadmiembros(rs.getInt("cantidadMiembros"));
        com.setAdministrador(new Usuario_comunDAOimpl().leer(rs.getInt("idAdministrador"))); 
        com.setEstado(rs.getString("estado").charAt(0));

        return com;
    }
    
}
