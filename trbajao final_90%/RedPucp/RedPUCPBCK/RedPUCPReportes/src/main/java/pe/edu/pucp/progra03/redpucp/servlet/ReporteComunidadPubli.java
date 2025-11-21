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

/**
 *
 * @author andre
 */
@WebServlet(name = "ReporteComunidadPubli", urlPatterns = {"/reportes/ReporteComunidadPubli"})
public class ReporteComunidadPubli extends HttpServlet {
private final String NOMBRE_REPORTE = 
            "reportes/RF014.jasper";
    //ups wir haben keinen
    private final String NOMBRE_LOGO = 
            "imagenes/logo.png";
    private final String SUB_REPORTE=
            "reportes/DetallePublicacionesR014.jasper";
    
    
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
                throw new FileNotFoundException("No se encontró el archivo 'RF014.jasper'");
            }

            Map<String, Object> parametros = new HashMap<>();
            //java.sql.Date.valueOf(request.getParameter("fi")
            
            int numtip = Integer.parseInt(request.getParameter("parametro"));
            parametros.put("idComunidad",numtip);
            
            InputStream subreporteStream = getClass().getClassLoader().getResourceAsStream(this.SUB_REPORTE);
            if (subreporteStream == null) {
                throw new FileNotFoundException("No se encontró el subreporte 'DetallePublicacionesR014.jasper'");
            }
            parametros.put("subreporte", subreporteStream);
            
            InputStream logoStream = getClass().getClassLoader().
                    getResourceAsStream(this.NOMBRE_LOGO);
            if (logoStream != null) {
                Image logo = ImageIO.read(logoStream);
                parametros.put("logo", logo);
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
                               "Error al generar el reporte1: " + ex.getMessage());
            }
        }
        catch (IOException | JRException ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                               "Error al generar el reporte2: " + ex.getMessage());
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Esta servlet genera el reporte de publicaciones por comunidad.";
    }
    
    
    
}
