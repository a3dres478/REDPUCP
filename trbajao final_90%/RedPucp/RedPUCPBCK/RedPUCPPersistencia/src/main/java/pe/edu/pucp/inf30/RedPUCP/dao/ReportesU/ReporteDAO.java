/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.dao.ReportesU;

import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.dao.PersistibleTransaccional;
import pe.edu.pucp.inf30.RedPUCP.modelo.ReportesU.Reporte;

/**
 *
 * @author aaa
 */
public interface ReporteDAO extends PersistibleTransaccional<Reporte, Integer> {
    
    // Aquí podríamos agregar métodos específicos si los necesitas, por ejemplo:
    
    /**
     * Lista todos los reportes hechos por un usuario específico.
     * @param idReportador El ID del usuario que realizó los reportes.
     * @return Una lista de reportes.
     */
    List<Reporte> listarReportesPorReportador(int idReportador);
    
    /**
     * Lista todos los reportes que están pendientes de revisión (ej: estado 'A').
     * @return Una lista de reportes pendientes.
     */
    List<Reporte> listarReportesPendientes();
}
