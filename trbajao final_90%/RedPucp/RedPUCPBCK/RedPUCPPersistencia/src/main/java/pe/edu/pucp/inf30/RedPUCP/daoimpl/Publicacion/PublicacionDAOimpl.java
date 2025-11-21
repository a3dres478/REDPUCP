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
//import pe.edu.pucp.inf30.RedPUCP.daoimpl.BaseDAOImplement;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.UsuarioDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Publicacion;
import pe.edu.pucp.inf30.RedPUCP.dao.Publicacion.PublicacionDAO;
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.Comunidad;
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.EstadoComunidad;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Comunidad.ComunidadDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.TransaccionalBaseDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.Usuario_comunDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.CategoriaPublicacion;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.EstadoImagen;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;

/**
 *
 * @author andre
 */
public class PublicacionDAOimpl extends TransaccionalBaseDAO<Publicacion> implements PublicacionDAO{
    @Override
    protected CallableStatement comandoCrear(Connection conn, Publicacion publicacion) throws SQLException {
        String sql = "{CALL sp_insertarPublicacion(?,?,?,?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idAutor",publicacion.getAutor().getIdUsuario());
        cmd.setInt("p_idComunidad",publicacion.getComunidad().getId_comunidad());
        cmd.setString("p_descripcion",publicacion.getDescripcion());
        cmd.setString("p_titulo",publicacion.getTitulo());
        
        cmd.setString("p_categoria", publicacion.getCategoria());
        cmd.setString("p_imagenUrl", publicacion.getImagenUrl());

        cmd.registerOutParameter("p_idGenerado", Types.INTEGER);
        return cmd;
    }
    @Override
    protected CallableStatement comandoActualizar(Connection conn, Publicacion sed) throws SQLException {
        String sql = "{CALL sp_actualizarPublicacion(?,?,?,?,?,?)}";
       CallableStatement cmd = conn.prepareCall(sql);
       cmd.setInt("p_idPublicacion",sed.getId());
        cmd.setString("p_titulo",sed.getTitulo());
        cmd.setString("p_descripcion",sed.getDescripcion());
        
        cmd.setString("p_categoria", sed.getCategoria());
        cmd.setString("p_imagenUrl", sed.getImagenUrl());
        
        cmd.registerOutParameter("p_exito", Types.BOOLEAN);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL sp_eliminarPublicacion(?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idPublicacion", id);
        cmd.registerOutParameter("p_exito", Types.BOOLEAN);
        return cmd;             
    }
    
    @Override
    protected CallableStatement comandoLeer(Connection conn, Integer id) throws SQLException{
        String sql= "{CALL sp_obtenerPublicacionPorId(?)}";
        CallableStatement cmd=conn.prepareCall(sql);
        cmd.setInt("p_idPublicacion",id);
        return cmd;
    }
    @Override
    protected CallableStatement comandoLeerTodos (Connection conn) throws SQLException{
        String sql= "{CALL sp_listarPublicaciones()}"; 
        CallableStatement cmd=conn.prepareCall(sql);
        return cmd;
    }
    
    
    //REVISAR URGENTE
    @Override
    protected Publicacion mapearModelo(ResultSet rs) throws SQLException{
        Publicacion sed= new Publicacion();
        sed.setId(rs.getInt("idPublicacion"));
        sed.setAutor(new Usuario_comunDAOimpl().leer(rs.getInt("idAutor")));
        sed.setComunidad(new ComunidadDAOimpl().leer(rs.getInt("idComunidad")));
        sed.setTitulo(rs.getString("titulo"));
        sed.setDescripcion(rs.getString("descripcion"));
        sed.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
        sed.setUltimaEdicion(rs.getTimestamp("ultimaEdicion"));
        sed.setVotosPositivos(rs.getInt("votosPositivos"));
        sed.setVotosNegativos(rs.getInt("votosNegativos"));
        sed.setEstado(rs.getString("estado").charAt(0));
        
        String categoria = rs.getString("categoria");
        if(categoria == null || categoria.trim().isEmpty()) {
            sed.setCategoria(String.valueOf(CategoriaPublicacion.Sin_categoria));
        } else {
            sed.setCategoria(categoria);
        }
        
        String imagenUrl = rs.getString("imagen_url");
        if(imagenUrl == null || imagenUrl.trim().isEmpty()) {
            sed.setImagenUrl(String.valueOf(EstadoImagen.Sin_imagen));
        } else {
            sed.setImagenUrl(imagenUrl);
        }
        
        return sed;
    }
    
    @Override
    public void actualizarImagen(int idPublicacion, String imagenUrl) {
        try (Connection conn = DBManager.getInstance().getConnection();
             PreparedStatement ps = this.comandoActualizarImagenPublicacion(conn,idPublicacion,imagenUrl);        
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
    
    protected CallableStatement comandoActualizarImagenPublicacion(Connection conn, 
        int idPublicacion, String imagenUrl) throws SQLException 
    {
        String sql = "{CALL sp_actualizarImagenPublicacion(?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        if(imagenUrl==null){
            imagenUrl = String.valueOf(EstadoImagen.Sin_imagen);
        }
        cmd.setInt("p_idPublicacion", idPublicacion);
        cmd.setString("p_imagenUrl", imagenUrl);
        return cmd;

    }
    
    @Override
    public List<Publicacion> listarPublicacionesXFiltros(int idComunidad,String categoria,String ordenamiento) {
        try (
            Connection conn = DBManager.getInstance().getConnection(); 
            PreparedStatement ps = this.comandolistarPublicacionesXFiltros(conn,idComunidad,categoria,ordenamiento);
        ) {
            ResultSet rs = ps.executeQuery();
            List<Publicacion> modelos = new ArrayList<>();
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

    protected CallableStatement comandolistarPublicacionesXFiltros(Connection conn, 
        int idComunidad, String categoria,String ordenamiento) throws SQLException {
        String sql = "{CALL sp_listarPublicacionesXFiltros(?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        if(categoria==null){
            categoria = String.valueOf(CategoriaPublicacion.Sin_categoria);
        }
        cmd.setInt("p_idComunidad", idComunidad);
        cmd.setString("p_categoria", categoria);
        cmd.setString("p_ordenamiento", ordenamiento);
        return cmd;

    }
    
    @Override
    public List<Publicacion> listarPublicacionesXUsuario(int idUsuario) {
        try (
            Connection conn = DBManager.getInstance().getConnection(); 
            PreparedStatement ps = this.comandolistarPublicacionesXUsuario(conn,idUsuario);
        ) {
            ResultSet rs = ps.executeQuery();
            List<Publicacion> modelos = new ArrayList<>();
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

    protected CallableStatement comandolistarPublicacionesXUsuario(Connection conn, 
        int idUsuario) throws SQLException {
        String sql = "{CALL sp_listarPublicacionesDeUsuario(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idUsuario", idUsuario);
        return cmd;
    }
    
    @Override
    public List<Publicacion> listarPublicacionesVirales() {
        try (
            Connection conn = DBManager.getInstance().getConnection(); 
            PreparedStatement ps = this.comandolistarPublicacionesVirales(conn);
        ) {
            ResultSet rs = ps.executeQuery();
            List<Publicacion> modelos = new ArrayList<>();
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

    protected CallableStatement comandolistarPublicacionesVirales(Connection conn) 
        throws SQLException {
        String sql = "{CALL sp_listarPublicacionesVirales()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
}
