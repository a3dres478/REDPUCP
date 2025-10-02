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
import pe.edu.pucp.inf30.RedPUCP.dao.voto.VotoComentarioDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.BaseDAOImplement;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Comunidad.ComunidadDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Publicacion.PublicacionDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.UsuarioDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.PublicacionxComunidad;
import pe.edu.pucp.inf30.RedPUCP.modelo.voto.VotoComentario;
import pe.edu.pucp.inf30.RedPUCP.dao.Publicacion.ComentarioDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Publicacion.ComentarioDAOImpl;
/**
 *
 * @author andre
 */
public class VotoComentarioDAOImpl extends BaseDAOImplement<VotoComentario> implements VotoComentarioDAO{
    
    @Override
    protected CallableStatement comandoInsertar(Connection conn, VotoComentario modelo) throws SQLException {
       String sql = "{CALL sp_crearVotoComentario(?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idUsuario", modelo.getUsuario().getIdUsuario());
        //cmd.setString("p_tipo", String.valueOf(usu.getTipo()));
        cmd.setInt("p_idcomentario", modelo.getComentarioVotado().getId());
        cmd.setString("p_tipo", "UP");
        cmd.setDate("p_fechaRegistro", new java.sql.Date(modelo.getFechaRegistro().getTime()));
        cmd.registerOutParameter("p_id", Types.INTEGER);
        return cmd;
    }
    @Override
    protected CallableStatement comandoModificar(Connection conn, VotoComentario sed) throws SQLException {
        String sql = "{CALL sp_crearVotoComentario(?,?,?,?)}";
       CallableStatement cmd = conn.prepareCall(sql);
       //cmd.setInt("p_idUsuario", usu.getUsuario().getIdUsuario());
        cmd.setInt("p_idVoto", sed.getId());
        //cmd.setString("p_tipo", String.valueOf(usu.getTipo()));
        cmd.setString("p_tipo", "DOWN");
        //cmd.setDate("p_fechaRegistro", new java.sql.Date(usu.getFechaRegistro().getTime()));
        cmd.registerOutParameter("p_exito", Types.BOOLEAN);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL sp_eliminarVotoComentario(?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idVoto", id);
        cmd.registerOutParameter("p_exito", Types.BOOLEAN);
        return cmd;           
    }
    
    @Override
    protected CallableStatement comandoBuscar(Connection conn, int id) throws SQLException{
        String sql = "{CALL sp_obtenerVotoComentarioPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idVoto", id);
        return cmd;
    }
    @Override
    protected CallableStatement comandoListar (Connection conn) throws SQLException{
        String sql = "{CALL sp_listarVotosComentario()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
    
    
    //REVISAR URGENTE
    @Override
    protected VotoComentario mapearModelo(ResultSet rs) throws SQLException{
        VotoComentario usu = new VotoComentario();
        usu.setId(rs.getInt("idVoto"));
        
        usu.setUsuario(new UsuarioDAOimpl().buscar(rs.getInt("idUsuario")));
        usu.setFechaRegistro(rs.getTimestamp("fechaRegistro"));
        usu.setTipo(rs.getString("tipo").charAt(0));
        usu.setComentarioVotado(new ComentarioDAOImpl().buscar(rs.getInt("idComentario")));
        
        return usu;
    }
    
    
}
