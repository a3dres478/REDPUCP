/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.inf30.RedPUCP.dao.usuario.AdministradorDAO;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Administrador;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.BaseDAOImplement;

/**
 *
 * @author andre
 */
public class AdministradorDAOimpl extends BaseDAOImplement<Administrador> implements AdministradorDAO {
    
    @Override
    protected CallableStatement comandoInsertar(Connection conn, Administrador usu) throws SQLException {
        String sql = "{CALL insertarUsuario(?,?,?,?,?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        
        
        return cmd;
    }

    @Override
    protected CallableStatement comandoModificar(Connection conn, Administrador usu) throws SQLException {
        String sql = "{CALL modificarUsuario(?,?,?,?,?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        
        
        return cmd;
    }


    @Override
    protected CallableStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL eliminarUsuario(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_id", id);
        return cmd;
    }


    @Override
    protected CallableStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL buscarUsuarioPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_id", id);
        return cmd;
    }


    @Override
    protected CallableStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL listarUsuario()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }

    @Override
    protected Administrador mapearModelo(ResultSet rs) throws SQLException {
        Administrador usu = new Administrador();
        usu.setIdUsuario(rs.getInt("idUsuario"));
        usu.setNombre(rs.getString("Nombre"));
        usu.setDescripcion(rs.getString("Descripcion"));
        usu.setEmail(rs.getString("email"));
        usu.setContrasenha(rs.getString("email"));
        usu.setKarma(rs.getInt("karma"));
        usu.setEstadouser(rs.getString("estadouser").charAt(0));
        usu.setTipousuario(rs.getString("tipoUsuarioi").charAt(0));
        usu.setClave_acceso(rs.getString("clave_acceso"));
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
