/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.daoimpl.voto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.inf30.RedPUCP.dao.voto.VotoPublicacionDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.TransaccionalBaseDAO;
//import pe.edu.pucp.inf30.RedPUCP.daoimpl.BaseDAOImplement;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.UsuarioDAOimpl;
//import pe.edu.pucp.inf30.RedPUCP.modelo.voto.Voto;
import pe.edu.pucp.inf30.RedPUCP.modelo.voto.VotoPublicacion;
/**
 *
 * @author andre
 */
public class VotoPublicacionDAOImpl extends TransaccionalBaseDAO<VotoPublicacion> implements VotoPublicacionDAO{
    
    @Override
    protected CallableStatement comandoCrear(Connection conn, VotoPublicacion usu) throws SQLException {
        String sql = "{CALL sp_crearVotoPublicacion(?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idUsuario", usu.getUsuario().getIdUsuario());
        //cmd.setString("p_tipo", String.valueOf(usu.getTipo()));
        cmd.setString("p_tipo", "UP");
        //cmd.setDate("p_fechaRegistro", new java.sql.Date(usu.getFechaRegistro().getTime()));
        cmd.registerOutParameter("p_idGenerado", Types.INTEGER);
        
        
        return cmd;
    }

    @Override
    protected CallableStatement comandoActualizar(Connection conn, VotoPublicacion usu) throws SQLException {
        String sql = "{CALL sp_actualizarVotoPublicacion(?,?,?)}";
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
    protected VotoPublicacion mapearModelo(ResultSet rs) throws SQLException {
        VotoPublicacion usu = new VotoPublicacion();
        usu.setId(rs.getInt("idVoto"));
        usu.setUsuario(new UsuarioDAOimpl().leer(rs.getInt("idUsuario")));
        usu.setFechaRegistro(rs.getTimestamp("fechaRegistro"));
        usu.setTipo(rs.getString("tipo").charAt(0));
        
        
        return usu;
    }
    
}
