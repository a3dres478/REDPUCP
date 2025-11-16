/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import pe.edu.pucp.inf30.RedPUCP.config.DBManager;
//REPORTEUSUARIOCOMUNIDAD->REPORTEDETALLECOMUNIDADES
/**
 *
 * @author andre
 */
//obtiene el id del usuario que pertenece a la comunidad y su subreporte es ReporteDetalleComunidades
@WebServlet(name = "ReporteUsuarioporComunidad", urlPatterns = {"/reportes/ReporteUsuarioporComunidad"})
public class ReporteUsuarioporComunidad extends HttpServlet {

    private final String NOMBRE_REPORTE = 
            " .jasper";
    private final String NOMBRE_LOGO = 
            ".png";
   private final String SUB_REPORTE=
           "";
    
    @Override
    protected void doGet(
            HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/pdf");
        
        try {
            InputStream reporte = 
                    getClass().getClassLoader()
                            .getResourceAsStream(
                                    this.NOMBRE_REPORTE);
            
            if (reporte == null) {
                throw new FileNotFoundException("No se encontró el archivo 'ComunidadDetallada.jasper'");
            }
            
            Map<String, Object> parametros = 
                    new HashMap<>();
            parametros.put("comunidad", request.getParameter("comuni"));
            
            InputStream subreporteStream = getClass().getClassLoader().getResourceAsStream(this.SUB_REPORTE);
            if (subreporteStream == null) {
                throw new FileNotFoundException("No se encontró el subreporte 'detalleComunidades.jasper'");
            }
            
            InputStream logoStream = getClass().getClassLoader().
                    getResourceAsStream(this.NOMBRE_LOGO);
            if (logoStream != null) {
                Image logo = ImageIO.read(logoStream);
                parametros.put("Logo", logo);
            }
            
            try (Connection conexion = DBManager.getInstance().getConnection()) {
                JasperPrint jp = 
                        JasperFillManager.fillReport(reporte, 
                                parametros, 
                                conexion);
                JasperExportManager.exportReportToPdfStream(
                        jp, response.getOutputStream());
            }
            catch (SQLException | ClassNotFoundException ex) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                               "Error al generar el reporte: " + ex.getMessage());
            }
        }
        catch (IOException | JRException ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                               "Error al generar el reporte: " + ex.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Genera reporte de Detalle por COmunidad";
    }
    

}
