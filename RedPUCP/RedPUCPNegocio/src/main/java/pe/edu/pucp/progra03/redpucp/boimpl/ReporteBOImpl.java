/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.boimpl;

import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.dao.ReportesU.ReporteDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.ReportesU.ReporteDAOImpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.ReportesU.Reporte;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IReporteBO;

/**
 *
 * @author HECTOR
 */
public class ReporteBOImpl implements IReporteBO{
    private final ReporteDAO reporteDAO;
    
    public ReporteBOImpl(){
        reporteDAO = new ReporteDAOImpl();
    }

    @Override
    public List<Reporte> listar(){
        return reporteDAO.leerTodos();
    } 

    @Override 
    public Reporte obtener(int id){
        return reporteDAO.leer(id);
    }
    
    @Override 
    public void eliminar(int id){
        this.reporteDAO.eliminar(id);
    }
    
    @Override 
    public void guardar(Reporte reporte,Estado estado){
        if(estado==Estado.Nuevo){
            int idGenerado = this.reporteDAO.crear(reporte);
            reporte.setId_report(idGenerado);
        }else{
            this.reporteDAO.actualizar(reporte);
        }
    }

    @Override 
    public List<Reporte> listarReportesPendientes(){
        return this.reporteDAO.listarReportesPendientes();
    }
    
    @Override 
    public List<Reporte> listarReportesXReportador(int idReportador){
        return this.reporteDAO.listarReportesPorReportador(idReportador);
    }
}
