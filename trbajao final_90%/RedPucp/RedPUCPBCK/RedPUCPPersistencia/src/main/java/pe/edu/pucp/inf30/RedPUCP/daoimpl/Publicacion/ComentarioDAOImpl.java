/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.daoimpl.Publicacion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.config.DBManager;
import pe.edu.pucp.inf30.RedPUCP.dao.Publicacion.ComentarioDAO;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Comentario;
//import pe.edu.pucp.inf30.RedPUCP.daoimpl.BaseDAOImplement;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.TransaccionalBaseDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.UsuarioDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.Usuario_comunDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.EstadoImagen;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Publicacion;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;


/**
 *
 * @author andre
 */
public class ComentarioDAOImpl extends TransaccionalBaseDAO<Comentario> implements ComentarioDAO{
    @Override
    protected CallableStatement comandoCrear(Connection conn, Comentario sed) throws SQLException {
        String sql = "{CALL sp_insertarComentario(?,?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
       
        cmd.setInt("p_idAutor",sed.getAutor().getIdUsuario());
        cmd.setInt("p_idPublicacion",sed.getPublicacion().getId());
        
        cmd.setString("p_contenido", sed.getContenido());
        cmd.setString("p_imagenUrl", sed.getImagenUrl());

        cmd.registerOutParameter("p_idGenerado", Types.INTEGER);
        
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
        sed.setAutor(new Usuario_comunDAOimpl().leer(rs.getInt("idAutor")));
        sed.setContenido(rs.getString("contenido"));
        sed.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
        sed.setUltimaEdicion(rs.getTimestamp("ultimaEdicion"));
        sed.setVotosPositivos(rs.getInt("votosPositivos"));
        sed.setVotosNegativos(rs.getInt("votosNegativos"));
        sed.setEstado(rs.getString("estado").charAt(0));
        
        String imagenUrl = rs.getString("imagen_url");
        if(imagenUrl == null || imagenUrl.trim().isEmpty()) {
            sed.setImagenUrl(String.valueOf(EstadoImagen.Sin_imagen));
        } else {
            sed.setImagenUrl(imagenUrl);
        }
        
        return sed;
    }
    
    @Override
    public void actualizarImagen(int idComentario, String imagenUrl) {
        try (Connection conn = DBManager.getInstance().getConnection();
             PreparedStatement ps = this.comandoActualizarImagenComentario(conn,idComentario,imagenUrl);        
        ){
            ps.execute();
            System.out.println("Se actualizo la imagen");
        }catch (SQLException e) {
            System.err.println("Error SQL al actualizar la imagen: " + e.getMessage());
            throw new RuntimeException("No se pudo actualizar la imagen.", e);
        } catch (Exception e) {
            System.err.println("Error inesperado al actualizar imagen: " + e.getMessage());
            throw new RuntimeException("No se pudo actualizar la imagen.", e);
        }
    }
    
    protected CallableStatement comandoActualizarImagenComentario(Connection conn, 
        int idComentario, String imagenUrl) throws SQLException 
    {
        String sql = "{CALL sp_actualizarImagenComentario(?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        if(imagenUrl==null){
            imagenUrl = String.valueOf(EstadoImagen.Sin_imagen);
        }
        cmd.setInt("p_idComentario", idComentario);
        cmd.setString("p_imagenUrl", imagenUrl);
        return cmd;
    }
    
    @Override
    public List<Comentario> listarComentariosXPublicacion(int idPublicacion) {
        try (
            Connection conn = DBManager.getInstance().getConnection(); 
            PreparedStatement ps = this.comandoListarComentariosXPublicacion(conn, idPublicacion);
        ) {
            ResultSet rs = ps.executeQuery();
            List<Comentario> modelos = new ArrayList<>();
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

    protected CallableStatement comandoListarComentariosXPublicacion(Connection conn, int idPublicacion) throws SQLException {
        String sql = "{CALL sp_listarComentariosPorPublicacion(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idPublicacion", idPublicacion);
        return cmd;

    }
    
}

