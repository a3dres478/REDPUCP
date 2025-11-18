/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.daoimpl.voto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.config.DBManager;
import pe.edu.pucp.inf30.RedPUCP.dao.voto.VotoComentarioDAO;
//import pe.edu.pucp.inf30.RedPUCP.daoimpl.BaseDAOImplement;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Comunidad.ComunidadDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Publicacion.PublicacionDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.UsuarioDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.PublicacionxComunidad;
import pe.edu.pucp.inf30.RedPUCP.modelo.voto.VotoComentario;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Comentario;
import pe.edu.pucp.inf30.RedPUCP.dao.Publicacion.ComentarioDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Publicacion.ComentarioDAOImpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.TransaccionalBaseDAO;
/**
 *
 * @author andre
 */
public class VotoComentarioDAOImpl extends TransaccionalBaseDAO<VotoComentario> implements VotoComentarioDAO{
    
    @Override
    protected CallableStatement comandoCrear(Connection conn, VotoComentario modelo) throws SQLException {
       String sql = "{CALL sp_crearVotoComentario(?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idUsuario", modelo.getUsuario().getIdUsuario());
        cmd.setString("p_tipo", String.valueOf(modelo.getTipo()));
        cmd.setInt("p_idComentario", modelo.getComentarioVotado().getId());
        cmd.registerOutParameter("p_idGenerado", Types.INTEGER);
        return cmd;
    }
    @Override
    protected CallableStatement comandoActualizar(Connection conn, VotoComentario sed) throws SQLException {
        String sql = "{CALL sp_actualizarVoto(?,?,?)}";
       CallableStatement cmd = conn.prepareCall(sql);
       //cmd.setInt("p_idUsuario", usu.getUsuario().getIdUsuario());
        cmd.setInt("p_idVoto", sed.getId());
        //cmd.setString("p_tipo", String.valueOf(usu.getTipo()));
        cmd.setString("p_tipo",String.valueOf(sed.getTipo()));
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
    protected CallableStatement comandoLeer(Connection conn, Integer id) throws SQLException{
        String sql = "{CALL sp_obtenerPorIdVotoComentario(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idVoto", id);
        return cmd;
    }
    @Override
    protected CallableStatement comandoLeerTodos (Connection conn) throws SQLException{
        String sql = "{CALL sp_leerTodosVotosComentario()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
    
    
    
    @Override
    protected VotoComentario mapearModelo(ResultSet rs) throws SQLException{
        VotoComentario usu = new VotoComentario();
        usu.setId(rs.getInt("idVoto"));
        
        usu.setUsuario(new UsuarioDAOimpl().leer(rs.getInt("idUsuario")));
        usu.setFechaRegistro(rs.getTimestamp("fechaRegistro"));
        usu.setTipo(rs.getString("tipo").charAt(0));
        usu.setComentarioVotado(new ComentarioDAOImpl().leer(rs.getInt("idComentario")));
        return usu;
    }
    
    @Override
    public List<VotoComentario> listarVotosXComentario(int idComentario) {
        try (
            Connection conn = DBManager.getInstance().getConnection(); 
            PreparedStatement ps = this.comandoListarVotosXComentario(conn, idComentario);) {
            ResultSet rs = ps.executeQuery();
            List<VotoComentario> modelos = new ArrayList<>();
            while (rs.next()) {
                modelos.add(this.mapearModelo(rs));
            }
            return modelos;

        } catch (SQLException e) {
            System.err.println("Error SQL durante el listado: " + e.getMessage());
            throw new RuntimeException("No se pudo listar el registro.", e);
        } catch (Exception e) {
            System.err.println("Error inpesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al listar los registros.", e);
        }
    }

    protected CallableStatement comandoListarVotosXComentario(Connection conn, int idComentario) throws SQLException {
        String sql = "{CALL sp_leerVotosXComentario(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idComentario", idComentario);
        return cmd;

    }
    
}
