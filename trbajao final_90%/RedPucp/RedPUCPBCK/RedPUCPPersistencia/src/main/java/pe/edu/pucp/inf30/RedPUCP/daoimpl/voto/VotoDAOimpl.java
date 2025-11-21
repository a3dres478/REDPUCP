/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author andre
 */
package pe.edu.pucp.inf30.RedPUCP.daoimpl.voto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
//import pe.edu.pucp.inf30.RedPUCP.daoimpl.BaseDAOImplement;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.UsuarioDAOimpl;
//import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Administrador;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import pe.edu.pucp.inf30.RedPUCP.modelo.voto.Voto;
import pe.edu.pucp.inf30.RedPUCP.dao.voto.VotoDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.TransaccionalBaseDAO;
/**
 *
 * @author andre
 */
public class VotoDAOimpl extends TransaccionalBaseDAO<Voto> implements VotoDAO{
    /*
    private int idVoto;
    private char tipo;
    private Date fechaRegistro;
    private Usuario usuario;
    */
    @Override
    protected CallableStatement comandoCrear(Connection conn, Voto usu) throws SQLException {
        String sql = "{CALL sp_insertarVoto(?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idUsuario", usu.getUsuario().getIdUsuario());
        //cmd.setString("p_tipo", String.valueOf(usu.getTipo()));
        cmd.setString("p_tipo", "UP");
        //cmd.setDate("p_fechaRegistro", new java.sql.Date(usu.getFechaRegistro().getTime()));
        cmd.registerOutParameter("p_idGenerado", Types.INTEGER);
        
        
        return cmd;
    }

    @Override
    protected CallableStatement comandoActualizar(Connection conn, Voto usu) throws SQLException {
        String sql = "{CALL sp_actualizarVoto(?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        //cmd.setInt("p_idUsuario", usu.getUsuario().getIdUsuario());
        cmd.setInt("p_idVoto", usu.getId());
        //cmd.setString("p_tipo", String.valueOf(usu.getTipo()));
        cmd.setString("p_tipo", "DOWN");
        //cmd.setDate("p_fechaRegistro", new java.sql.Date(usu.getFechaRegistro().getTime()));
        cmd.registerOutParameter("p_exito", Types.BOOLEAN);
        return cmd;
    }


    @Override
    protected CallableStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL sp_eliminarVoto(?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idVoto", id);
        cmd.registerOutParameter("p_exito", Types.BOOLEAN);
        return cmd;
    }


    @Override
    protected CallableStatement comandoLeer(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL sp_obtenerVotoPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idVoto", id);
        return cmd;
    }


    @Override
    protected CallableStatement comandoLeerTodos(Connection conn) throws SQLException {
        String sql = "{CALL sp_listarVotos()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }

    @Override
    protected Voto mapearModelo(ResultSet rs) throws SQLException {
        Voto usu = new Voto();
        usu.setId(rs.getInt("idVoto"));
        usu.setUsuario(new UsuarioDAOimpl().leer(rs.getInt("idUsuario")));
        usu.setFechaRegistro(rs.getTimestamp("fechaRegistro"));
        usu.setTipo(rs.getString("tipo").charAt(0));
        return usu;
    }
    
    
}