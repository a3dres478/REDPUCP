/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edu.pucp.inf30.RedPUCP.dao.usuario.UsuarioDAO;
import pe.edu.pucp.inf30.RedPUCP.dao.usuario.Usuario_comunDAO;
//import pe.edu.pucp.inf30.RedPUCP.daoimpl.BaseDAOImplement;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.BaseDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.TransaccionalBaseDAO;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;

/**
 *
 * @author andre
 */
public class Usuario_comunDAOimpl extends TransaccionalBaseDAO<Usuario_comun> implements Usuario_comunDAO {
    
    @Override
    protected CallableStatement comandoCrear(Connection conn, Usuario_comun usu) throws SQLException {
        String sql = "{CALL sp_crearUsuarioComun(?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idUsuario", usu.getIdUsuario());
        cmd.setInt("p_codigoPUCP", Integer.parseInt(usu.getCodigopucp()) );
        
        return cmd;
    }
    
    
    @Override
    protected CallableStatement comandoActualizar(Connection conn, Usuario_comun usu) throws SQLException {
        String sql = "{CALL sp_actualizarUsuarioComun(?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idUsuario", usu.getIdUsuario());
        cmd.setInt("p_codigoPUCP", Integer.parseInt(usu.getCodigopucp()) );
        
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL sp_eliminarUsuarioComun(?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idUsuario", id);
        
        cmd.registerOutParameter("p_exito", Types.BOOLEAN);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoLeer(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL sp_obtenerUsuarioComunPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idUsuario", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoLeerTodos(Connection conn) throws SQLException {
        String sql = "{CALL sp_listarTodosUsuariosComunes()}"; // FALTA PROCEDURE
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
    
    @Override
    protected Usuario_comun mapearModelo(ResultSet rs) throws SQLException {
        Usuario_comun usu = new Usuario_comun();
        usu.setIdUsuario(rs.getInt("idUsuario"));
        usu.setCodigopucp( String.valueOf(rs.getInt("codigoPUCP")) );     
        return usu;
    }
    
    
}
