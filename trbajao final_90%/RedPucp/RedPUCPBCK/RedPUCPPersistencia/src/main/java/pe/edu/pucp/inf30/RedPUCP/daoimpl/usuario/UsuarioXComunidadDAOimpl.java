/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.TransaccionalBaseDAO;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario;
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.Comunidad;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.UsuarioxComunidad;
import pe.edu.pucp.inf30.RedPUCP.dao.usuario.UsuarioXComunidadDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.UsuarioDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Comunidad.ComunidadDAOimpl;

/**
 *
 * @author invitado123
 */
public class UsuarioXComunidadDAOimpl extends TransaccionalBaseDAO<UsuarioxComunidad> implements UsuarioXComunidadDAO {
    // FALTAN PROCEDURES
    @Override 
    protected CallableStatement comandoCrear(Connection conn,UsuarioxComunidad usuXcom) throws SQLException{
        String sql="{CALL }"; 
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_",usuXcom.getUsu().getIdUsuario());
        cmd.setInt("p_",usuXcom.getComu().getId_comunidad());
        cmd.registerOutParameter("p_",Types.INTEGER);
        
        return cmd;
    }
    
    @Override 
    protected CallableStatement comandoActualizar(Connection conn, UsuarioxComunidad usuXcom) throws SQLException{
        String sql = "{CALL sp(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_", usuXcom.getUsu().getIdUsuario());
        cmd.setInt("p_", usuXcom.getComu().getId_comunidad());
        
        return cmd;
    }
    
    @Override 
    protected CallableStatement comandoEliminar(Connection conn, Integer id) throws SQLException{
        String sql = "{CALL sp_()}";
        CallableStatement cmd = conn.prepareCall(sql);
        
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoLeer(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL sp_obtenerUsuarioPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idUsuario", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoLeerTodos(Connection conn) throws SQLException {
        String sql = "{CALL sp_listarTodosUsuarios()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
    
    @Override
    protected UsuarioxComunidad mapearModelo(ResultSet rs) throws SQLException {
        UsuarioxComunidad usuXcom = new UsuarioxComunidad();
        usuXcom.setUsu(new UsuarioDAOimpl().leer(rs.getInt("id_usuario")));
        usuXcom.setComu(new ComunidadDAOimpl().leer(rs.getInt("id_comunidad")));
        usuXcom.setId_usuarioXcomunidad(rs.getInt("id"));
        
        return usuXcom;
    }
}
