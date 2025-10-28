/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario;
import java.util.Date;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.inf30.RedPUCP.dao.usuario.UsuarioDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.BaseDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.TransaccionalBaseDAO;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario;
/**
 *
 * @author andre
 */
public class UsuarioDAOimpl extends TransaccionalBaseDAO<Usuario> implements UsuarioDAO {
    
    
    @Override
    protected CallableStatement comandoCrear(Connection conn, Usuario usu) throws SQLException {
        String sql = "{CALL sp_crearUsuario(?,?,?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setString("p_nombre", usu.getNombre());
        cmd.setString("p_descripcion", usu.getDescripcion());
        cmd.setString("p_email", usu.getEmail());
        cmd.setString("p_contrasena", usu.getContrasenha());
        cmd.setString("p_tipoUsuario", String.valueOf(usu.getTipousuario()) );
        //cmd.setInt("p_karma", usu.getKarma());
        //cmd.setDate("p_fechaRegistro", new java.sql.Date(usu.getFechaRegistro().getTime()) );
       // cmd.setString("p_estado", String.valueOf(usu.getEstadouser()) ); 
        //cmd.setString("p_estado", "ACTIVO"); 
        cmd.registerOutParameter("p_id", Types.INTEGER);
        
        return cmd;
    }
    
    
    @Override
    protected CallableStatement comandoActualizar(Connection conn, Usuario usu) throws SQLException {
        String sql = "{CALL sp_actualizarUsuario(?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idUsuario", usu.getIdUsuario());
        cmd.setString("p_nombre", usu.getNombre());
        cmd.setString("p_descripcion", usu.getDescripcion());
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
        String sql = "{CALL sp_listarTodosUsuarios()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
    
    @Override
    protected Usuario mapearModelo(ResultSet rs) throws SQLException {
        Usuario usu = new Usuario();
        usu.setIdUsuario(rs.getInt("idUsuario"));
        usu.setNombre(rs.getString("nombre"));
        usu.setDescripcion(rs.getString("descripcion"));
        usu.setEmail(rs.getString("email"));
        usu.setContrasenha(rs.getString("contrasena"));
        usu.setKarma(rs.getInt("karma"));
        usu.setFechaRegistro(rs.getDate("fechaRegistro"));
        usu.setEstadouser(rs.getString("estado").charAt(0));
        //usu.setTipousuario(rs.getString("tipo").charAt(0));
        
        /*  private int idUsuario;
    private String Nombre;
    private String Descripcion;
    private String email;
    private String contrasenha;
    private int karma;
    private char estadouser;
    private char tipousuario;
    */
        return usu;
    }
    
    
}
