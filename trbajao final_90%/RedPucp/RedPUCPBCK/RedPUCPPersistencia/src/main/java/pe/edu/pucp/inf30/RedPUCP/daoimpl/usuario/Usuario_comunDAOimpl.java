/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edu.pucp.inf30.RedPUCP.config.DBManager;
import pe.edu.pucp.inf30.RedPUCP.dao.usuario.UsuarioDAO;
import pe.edu.pucp.inf30.RedPUCP.dao.usuario.Usuario_comunDAO;
import pe.edu.pucp.inf30.RedPUCP.dao.utils.EncryptionUtil;
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
        
//        cmd.setString("p_contrasena", usu.getContrasenha());

        String encrypted = EncryptionUtil.encrypt(usu.getContrasenha());
        cmd.setString("p_contrasena", encrypted);
        
        
        
        cmd.setString("p_rol", String.valueOf(usu.getTipousuario()));
        cmd.setString("p_codigo", usu.getCodigopucp());
        cmd.setString("p_claveDeAcceso", null);

        cmd.registerOutParameter("p_idGenerado", Types.INTEGER);

        return cmd;
    }

    @Override
    protected CallableStatement comandoActualizar(Connection conn, Usuario_comun usu) throws SQLException {
        String sql = "{CALL sp_actualizarUsuarioComun(?,?,?,?,?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);

        cmd.setInt("p_idUsuario", usu.getIdUsuario());
        cmd.setString("p_nombre", usu.getNombre());
        cmd.setString("p_descripcion", usu.getDescripcion());
        cmd.setString("p_email", usu.getEmail());
        
//        cmd.setString("p_contrasena", usu.getContrasenha());

        String encryptedPassword = EncryptionUtil.encrypt(usu.getContrasenha());
        cmd.setString("p_contrasena", encryptedPassword);
        
        cmd.setString("p_codigo", usu.getCodigopucp());
        cmd.setInt("p_karma", usu.getKarma()); 

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
        String sql = "{CALL sp_listarUsuariosComunes()}"; 
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
        
//        usu.setContrasenha(rs.getString("contrasena"));
        // Desencriptar la contraseña al obtener el usuario
        String passwordFromDB = rs.getString("contrasena");
        if (passwordFromDB != null && !passwordFromDB.isEmpty()) {
            try {
                // Intentar desencriptar (si está cifrada)
                String decryptedPassword = EncryptionUtil.decrypt(passwordFromDB);
                usu.setContrasenha(decryptedPassword);
            } catch (Exception e) {
                // Si falla la desencriptación, asumimos que no está cifrada
                usu.setContrasenha(passwordFromDB);
            }
        } else {
            usu.setContrasenha(passwordFromDB);
        }
        
        usu.setKarma(rs.getInt("karma"));
        usu.setEstadouser(rs.getString("estado").charAt(0));
        usu.setTipousuario(rs.getString("rol"));
        usu.setFechaRegistro(rs.getTimestamp("fechaRegistro"));
        usu.setCodigopucp(rs.getString("codigo"));

        return usu;
    }

    @Override
    public boolean verificarLogin(String email, String password) {
        try {
            Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement cmd = this.comandoUsuarioComunXCorreo(conn, email);
            ResultSet rs = cmd.executeQuery();
            
            if (rs.next()) {
                String encryptedPassword = rs.getString("contrasena");
                if (encryptedPassword != null && !encryptedPassword.isEmpty()) {
                    String decryptedPassword = EncryptionUtil.decrypt(encryptedPassword);
                    return decryptedPassword.equals(password);
                }
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            throw new RuntimeException("No se pudo realizar el login.", e);
        } catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al realizar el login.", e);
        }
    }
    
    @Override
    public Usuario_comun obtenerUsuarioComunXCorreo(String correo) {
        try (
            Connection conn = DBManager.getInstance().getConnection(); 
            PreparedStatement ps = this.comandoUsuarioComunXCorreo(conn, correo);
        ) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapearModelo(rs);
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.err.println("Error SQL durante el listado: " + e.getMessage());
            throw new RuntimeException("No se pudo listar el registro.", e);
        } catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al listar el registro.", e);
        }
    }

    protected CallableStatement comandoUsuarioComunXCorreo(Connection conn, String correo) throws SQLException {
        String sql = "{CALL sp_obtenerUsuarioXCorreo(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setString("p_email", correo);
        return cmd;

    }
    
    @Override
    public void actualizarKarma(int idUsuario, int aumento) {
        try (Connection conn = DBManager.getInstance().getConnection(); 
            PreparedStatement ps = this.comandoActualizarKarma(conn, idUsuario,aumento);
        ) 
        {
            ps.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al actualizar karma: " + e.getMessage());
            throw new RuntimeException("No se pudo actualizar el karma.", e);
        } catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al actualizar el karma.", e);
        }
    }
    
    protected CallableStatement comandoActualizarKarma(Connection conn,int idUsuario,int aumento) throws SQLException {
        String sql = "{CALL sp_actualizarKarma(?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idUsuario", idUsuario);
        cmd.setInt("p_aumento", aumento);
        return cmd;

    }
}
