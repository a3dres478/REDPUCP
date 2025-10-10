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
import java.util.HashSet;
import java.util.Set;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.PublicacionxComunidad;
//import pe.edu.pucp.inf30.RedPUCP.daoimpl.BaseDAOImplement;
import pe.edu.pucp.inf30.RedPUCP.dao.Publicacion.PublicacionxComunidadDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Comunidad.ComunidadDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.TransaccionalBaseDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.UsuarioDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Publicacion;

/**
 *
 * @author andre
 */

public class PublicacionxComunidadDAOImpl extends TransaccionalBaseDAO<PublicacionxComunidad> implements PublicacionxComunidadDAO{
    
    @Override
    protected CallableStatement comandoCrear(Connection conn, PublicacionxComunidad modelo) throws SQLException {
        String sql = "{CALL sp_crearPublicacionxComunidad(?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idPublicacion", modelo.getPubli().getId());
        cmd.setInt("p_idComunidad", modelo.getComu().getId_comunidad());
        cmd.registerOutParameter("p_idGenerado", Types.INTEGER);
        return cmd;
    }
    @Override
    protected CallableStatement comandoActualizar(Connection conn, PublicacionxComunidad sed) throws SQLException {
        String sql = "{CALL sp_actualizarPublicacionxComunidad(?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idPublicacion",sed.getPubli().getId());
        cmd.setInt("p_idComunidad",sed.getComu().getId_comunidad());
     
        cmd.registerOutParameter("p_exito", Types.BOOLEAN);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL sp_eliminarPublicacionxComunidad(?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idPublicacion", id);
        cmd.registerOutParameter("p_exito", Types.BOOLEAN);
        return cmd;             
    }
    
    @Override
    protected CallableStatement comandoLeer(Connection conn, Integer id) throws SQLException{
        String sql= "{CALL sp_obtenerPublicacionxComunidadPorId(?)}";
        CallableStatement cmd=conn.prepareCall(sql);
        cmd.setInt("p_idPublicacion",id);
        return cmd;
    }
    @Override
    protected CallableStatement comandoLeerTodos (Connection conn) throws SQLException{
        String sql= "{CALL sp_listarPublicacionesxComunidad()}"; //FALTA IMPLEMENTAR PROCEDURE
        CallableStatement cmd=conn.prepareCall(sql);
        //cmd.setInt("p_idComunidad",-1);
        return cmd;
    }
    
    
    //REVISAR URGENTE
    @Override
    protected PublicacionxComunidad mapearModelo(ResultSet rs) throws SQLException{
        PublicacionxComunidad sed= new PublicacionxComunidad();
        sed.setComu(new ComunidadDAOimpl().leer(rs.getInt("idComunidad")));
        sed.setPubli(new PublicacionDAOimpl().leer(rs.getInt("id_publicacion")));
        //sed.setId_publicacionXcomunidad(rs.getInt("id_publicacionXcomunidad"));
        return sed;
    }
}
