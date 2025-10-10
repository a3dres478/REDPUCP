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
//import pe.edu.pucp.inf30.RedPUCP.daoimpl.BaseDAOImplement;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.UsuarioDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Publicacion;
import pe.edu.pucp.inf30.RedPUCP.dao.Publicacion.PublicacionDAO;
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.Comunidad;
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.EstadoComunidad;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Comunidad.ComunidadDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.TransaccionalBaseDAO;

/**
 *
 * @author andre
 */
public class PublicacionDAOimpl extends TransaccionalBaseDAO<Publicacion> implements PublicacionDAO{
    /*
    private int id;
    private Usuario autor;
    private String titulo;
    private String descripcion;
    private Date fechaCreacion;
    private Date ultimaEdicion;
    private int votosPositivos;
    private int votosNegativos;
    private char estado;
    */
    @Override
    protected CallableStatement comandoCrear(Connection conn, Publicacion publicacion) throws SQLException {
        String sql = "{CALL sp_insertarPublicacion(?,?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idAutor",publicacion.getAutor().getIdUsuario());
        cmd.setInt("p_idComunidad",publicacion.getComunidad().getId_comunidad());
        cmd.setString("p_descripcion",publicacion.getDescripcion());
        cmd.setString("p_titulo",publicacion.getTitulo());

        cmd.registerOutParameter("p_idPublicacion", Types.INTEGER);
        return cmd;
    }
    @Override
    protected CallableStatement comandoActualizar(Connection conn, Publicacion sed) throws SQLException {
        String sql = "{CALL sp_actualizarPublicacion(?,?,?,?)}";
       CallableStatement cmd = conn.prepareCall(sql);
       cmd.setInt("p_idPublicacion",sed.getId());
        cmd.setString("p_titulo",sed.getTitulo());
        cmd.setString("p_descripcion",sed.getDescripcion());
        
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
        sed.setAutor(new UsuarioDAOimpl().leer(rs.getInt("idAutor")));
        sed.setComunidad(new ComunidadDAOimpl().leer(rs.getInt("idComunidad")));
        sed.setTitulo(rs.getString("titulo"));
        sed.setDescripcion(rs.getString("descripcion"));
        sed.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
        sed.setUltimaEdicion(rs.getTimestamp("ultimaEdicion"));
        sed.setVotosPositivos(rs.getInt("votosPositivos"));
        sed.setVotosNegativos(rs.getInt("votosNegativos"));
        sed.setEstado(rs.getString("estado").charAt(0));
        
        return sed;
    }
    
    
}
