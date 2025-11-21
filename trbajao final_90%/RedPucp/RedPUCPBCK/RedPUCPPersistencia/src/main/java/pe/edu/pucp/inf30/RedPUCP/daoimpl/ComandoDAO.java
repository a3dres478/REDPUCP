package pe.edu.pucp.inf30.RedPUCP.daoimpl;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author eric
 */
public interface ComandoDAO<R> {
    R ejecutar(Connection conn) throws SQLException;
}
