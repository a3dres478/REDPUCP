/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.daoimpl.Comunidad;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.inf30.RedPUCP.dao.Comunidad.ComunidadXPublicacionDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Publicacion.PublicacionDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.TransaccionalBaseDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.Usuario_comunDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.Comunidad;
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.ComunidadxPublicacionPrincipal;
/**
 *
 * @author andre
 */
public class ComunidadXPublicacionPrincipalDAOImpl extends TransaccionalBaseDAO<ComunidadxPublicacionPrincipal> implements ComunidadXPublicacionDAO{
    @Override
    protected CallableStatement comandoCrear(Connection conn, ComunidadxPublicacionPrincipal com) throws SQLException {
        String sql = "{CALL version()}";
        CallableStatement cmd = conn.prepareCall(sql);

        return cmd;
    }

    @Override
    protected CallableStatement comandoActualizar(Connection conn, ComunidadxPublicacionPrincipal com) throws SQLException {
        String sql = "{CALL sp_actualizarComunidadxPubliprincipal(?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);

        cmd.setInt("p_idComunidadxPublic", com.getIdComunidadxPublicacionPrincipal());
        cmd.setInt("p_idComunidad", com.getComuni().getId_comunidad());
        cmd.setInt("p_idPublicacion", com.getPublicacion().getId());
        cmd.registerOutParameter("p_exito", Types.BOOLEAN);

        return cmd;
    }

    @Override
    protected CallableStatement comandoEliminar(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL version()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }

    @Override
    protected CallableStatement comandoLeer(Connection conn, Integer id) throws SQLException {
        String sql = "{CALL sp_obtenerComunidadxpublicacionprincipal(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idComunidad", id);
        return cmd;
    }

    @Override
    protected CallableStatement comandoLeerTodos(Connection conn) throws SQLException {
        String sql = "{CALL sp_listarComunidadesxpublicacionprincipal()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }

    @Override
    protected ComunidadxPublicacionPrincipal mapearModelo(ResultSet rs) throws SQLException {
        ComunidadxPublicacionPrincipal com = new ComunidadxPublicacionPrincipal();
        
        com.setIdComunidadxPublicacionPrincipal(rs.getInt("idComunidadPublicacion"));
        com.setComuni(new ComunidadDAOimpl().leer(rs.getInt("idComunidad")));
        Integer idPub=rs.getInt("idPublicacion");
        if(rs.wasNull()){
            com.setPublicacion(null);
        }else{
            com.setPublicacion(new PublicacionDAOimpl().leer(idPub));
        }
        return com;
    }
}
