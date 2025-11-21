/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.inf30.RedPUCP.config.DBManager;
import pe.edu.pucp.inf30.RedPUCP.dao.usuario.AdministradorDAO;
import pe.edu.pucp.inf30.RedPUCP.dao.utils.EncryptionUtil;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Administrador;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario;
///import pe.edu.pucp.inf30.RedPUCP.daoimpl.BaseDAOImplement;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.BaseDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.TransaccionalBaseDAO;

/**
 *
 * @author andre
 */
public class AdministradorDAOimpl extends TransaccionalBaseDAO<Administrador> implements AdministradorDAO {
    
    @Override
    protected CallableStatement comandoCrear(Connection conn, Administrador usu) throws SQLException {
        String sql = "{CALL sp_insertarUsuario(?,?,?,?,?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setString("p_nombre", usu.getNombre());
        cmd.setString("p_descripcion", usu.getDescripcion());
        cmd.setString("p_email", usu.getEmail());

        String encrypted = EncryptionUtil.encrypt(usu.getContrasenha());
        cmd.setString("p_contrasena", encrypted);
        
        cmd.setString("p_rol", String.valueOf(usu.getTipousuario()));
        cmd.setString("p_codigo", null);
        cmd.setString("p_claveDeAcceso", usu.getClave_acceso());
        
        cmd.registerOutParameter("p_idGenerado", Types.INTEGER);
        
        return cmd;
    }

    @Override
    protected CallableStatement comandoActualizar(Connection conn, Administrador usu) throws SQLException {
        String sql = "{CALL sp_actualizarUsuario(?,?,?,?,?,?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idUsuario", usu.getIdUsuario());
        cmd.setString("p_nombre", usu.getNombre());
        cmd.setString("p_descripcion", usu.getDescripcion());
        cmd.setString("p_email", usu.getEmail());

        String encryptedPassword = EncryptionUtil.encrypt(usu.getContrasenha());
        cmd.setString("p_contrasena", encryptedPassword);
   
        cmd.setString("p_rol", String.valueOf(usu.getTipousuario()));
        cmd.setString("p_codigo", null);
        cmd.setString("p_claveDeAcceso", usu.getClave_acceso());
        
        cmd.registerOutParameter("p_exito", Types.BOOLEAN);
        
        return cmd;
    }


    @Override
    protected CallableStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL sp_eliminarUsuario(?)}";
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
        String sql = "{CALL sp_listarUsuariosAdministradores()}"; // FALTA PROCEDURE
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }

    @Override
    protected Administrador mapearModelo(ResultSet rs) throws SQLException {
        Administrador usu = new Administrador();
        usu.setIdUsuario(rs.getInt("idUsuario"));
        usu.setNombre(rs.getString("nombre"));
        usu.setDescripcion(rs.getString("descripcion"));
        usu.setEmail(rs.getString("email"));
        
//        usu.setContrasenha(rs.getString("contrasena"));
        String encryptedPassword = rs.getString("contrasena");
        if (encryptedPassword != null && !encryptedPassword.isEmpty()) {
            String decryptedPassword = EncryptionUtil.decrypt(encryptedPassword);
            usu.setContrasenha(decryptedPassword);
        }
        
        usu.setKarma(rs.getInt("karma"));
        usu.setEstadouser(rs.getString("estado").charAt(0));
        usu.setTipousuario(rs.getString("rol"));
        usu.setFechaRegistro(rs.getTimestamp("fechaRegistro"));
        usu.setClave_acceso(rs.getString("claveDeAcceso"));
        return usu;
    }
    
    @Override
    public boolean verificarLoginAdministrador(String email, String password) {
        try {
            Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement cmd = this.comandoAdministradorXCorreo(conn, email);
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
    public Administrador obtenerAdministradorXCorreo(String correo) {
        try (
            Connection conn = DBManager.getInstance().getConnection(); 
            PreparedStatement ps = this.comandoAdministradorXCorreo(conn, correo);
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

    protected CallableStatement comandoAdministradorXCorreo(Connection conn, String correo) throws SQLException {
        String sql = "{CALL sp_obtenerUsuarioXCorreo(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setString("p_email", correo);
        return cmd;

    }
}
