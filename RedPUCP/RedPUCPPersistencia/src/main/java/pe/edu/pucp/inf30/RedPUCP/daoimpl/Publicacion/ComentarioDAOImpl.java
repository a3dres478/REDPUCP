/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.daoimpl.Publicacion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.inf30.RedPUCP.dao.Publicacion.ComentarioDAO;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Comentario;
//import pe.edu.pucp.inf30.RedPUCP.daoimpl.BaseDAOImplement;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.TransaccionalBaseDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.UsuarioDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Publicacion;


/**
 *
 * @author andre
 */
public class ComentarioDAOImpl extends TransaccionalBaseDAO<Comentario> implements ComentarioDAO{
    
    /*private int id;
    private Usuario autor;
    private Publicacion publicacion;
    private Comentario comentarioPadre;
    private String contenido;
    private Date fechaCreacion;
    private Date ultimaEdicion;
    private int votosPositivos;
    private int votosNegativos;
    private char estado;*/
    @Override
    protected CallableStatement comandoCrear(Connection conn, Comentario sed) throws SQLException {
        String sql = "{CALL sp_insertarComentario(?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
       
        cmd.setInt("p_idautor",sed.getAutor().getIdUsuario());
        cmd.setInt("p_idPublicacion",sed.getPublicacion().getId());
//        Integer idPadre =(sed.getComentarioPadre() !=null )? sed.getComentarioPadre().getId():null;
//        if(idPadre == null) cmd.setNull("p_idComentarioPadre", Types.INTEGER);
//        else cmd.setInt("p_idComentarioPadre", idPadre);
       // cmd.setInt("p_id", 0);//revisar
        
        cmd.setString("p_contenido", sed.getContenido());
//        cmd.setDate("p_fechacreacion", new java.sql.Date(sed.getFechaCreacion().getTime()));
//        cmd.setDate("p_ultimaedicion", new java.sql.Date(sed.getUltimaEdicion().getTime()));
//        cmd.setInt("p_votospositivos", sed.getVotosPositivos());
//        cmd.setInt("p_votosnegativos", sed.getVotosNegativos());
//        cmd.setString("p_estado", String.valueOf(sed.getEstado()));
        cmd.registerOutParameter("p_idComentario", Types.INTEGER);
        
        return cmd;
    }
    @Override
    protected CallableStatement comandoActualizar(Connection conn, Comentario sed) throws SQLException {
        String sql = "{CALL sp_actualizarComentario(?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idComentario",sed.getId());
        cmd.setString("p_contenido", sed.getContenido());
        cmd.registerOutParameter("p_exito", Types.BOOLEAN);
        
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL sp_eliminarComentario(?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        
        cmd.setInt("p_idComentario",id);
        cmd.registerOutParameter("p_exito", Types.BOOLEAN);
        
        return cmd;             
    }
    
    @Override
    protected CallableStatement comandoLeer(Connection conn, Integer id) throws SQLException{
        String sql= "{CALL sp_obtenerComentarioPorId(?)}";
        CallableStatement cmd=conn.prepareCall(sql);
        cmd.setInt("p_idComentario",id);
        return cmd;
    }
    @Override
    protected CallableStatement comandoLeerTodos (Connection conn) throws SQLException{
        String sql= "{CALL sp_listarComentarios()}"; 
        CallableStatement cmd=conn.prepareCall(sql);
        return cmd;
    }
    
    
    //REVISAR URGENTE
    @Override
    protected Comentario mapearModelo(ResultSet rs) throws SQLException{
        Comentario sed= new Comentario();
        
        sed.setId(rs.getInt("idComentario"));
        sed.setPublicacion(new PublicacionDAOimpl().leer(rs.getInt("idPublicacion")));
        sed.setAutor(new UsuarioDAOimpl().leer(rs.getInt("idAutor")));
        sed.setContenido(rs.getString("contenido"));
        sed.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
        sed.setUltimaEdicion(rs.getTimestamp("ultimaEdicion"));
        sed.setVotosPositivos(rs.getInt("votosPositivos"));
        sed.setVotosNegativos(rs.getInt("votosNegativos"));
        sed.setEstado(rs.getString("estado").charAt(0));
        
        return sed;
    }
    
}
