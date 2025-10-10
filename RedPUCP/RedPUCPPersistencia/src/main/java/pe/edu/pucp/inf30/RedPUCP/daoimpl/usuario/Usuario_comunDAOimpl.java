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
        String sql = "{CALL sp_insertarUsuario(?,?,?,?,?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setString("p_nombre", usu.getNombre());
        cmd.setString("p_descripcion", usu.getDescripcion());
        cmd.setString("p_email", usu.getEmail());
        cmd.setString("p_contrasena", usu.getContrasenha());
        cmd.setString("p_rol", String.valueOf(usu.getTipousuario()));
        cmd.setString("p_codigo", usu.getCodigopucp());
        cmd.setString("p_claveDeAcceso", null);

        cmd.registerOutParameter("p_idGenerado", Types.INTEGER);

        return cmd;
    }

    @Override
    protected CallableStatement comandoActualizar(Connection conn, Usuario_comun usu) throws SQLException {
        String sql = "{CALL sp_actualizarUsuario(?,?,?,?,?,?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);

        cmd.setInt("p_idUsuario", usu.getIdUsuario());
        cmd.setString("p_nombre", usu.getNombre());
        cmd.setString("p_descripcion", usu.getDescripcion());
        cmd.setString("p_email", usu.getEmail());
        cmd.setString("p_contrasena", usu.getContrasenha());
        cmd.setString("p_rol", String.valueOf(usu.getTipousuario()));
        cmd.setString("p_codigo", usu.getCodigopucp());
        cmd.setString("p_claveDeAcceso", null);

        cmd.registerOutParameter("p_exito", Types.BOOLEAN);

        return cmd;
    }

    @Override
    protected CallableStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL sp_eliminarUsuario(?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idUsuario", id);

        cmd.registerOutParameter("p_exito", Types.BOOLEAN);
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
        String sql = "{CALL sp_listarUsuariosComunes()}"; // FALTA PROCEDURE
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }

    @Override
    protected Usuario_comun mapearModelo(ResultSet rs) throws SQLException {
        Usuario_comun usu = new Usuario_comun();
        usu.setIdUsuario(rs.getInt("idUsuario"));
        usu.setNombre(rs.getString("nombre"));
        usu.setDescripcion(rs.getString("descripcion"));
        usu.setEmail(rs.getString("email"));
        usu.setContrasenha(rs.getString("contrasena"));
        usu.setKarma(rs.getInt("karma"));
        usu.setEstadouser(rs.getString("estado").charAt(0));
        usu.setTipousuario(rs.getString("rol").charAt(0));
        usu.setFechaRegistro(rs.getTimestamp("fechaRegistro"));
        usu.setCodigopucp(rs.getString("codigo"));

        return usu;
    }

}
