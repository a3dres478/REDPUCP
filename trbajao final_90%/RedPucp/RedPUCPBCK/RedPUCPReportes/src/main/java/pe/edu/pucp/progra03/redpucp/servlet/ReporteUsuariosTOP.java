/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarException;
import javax.imageio.ImageIO;
import pe.edu.pucp.inf30.RedPUCP.config.DBManager;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author andre
 */
@WebServlet(name = "ReporteUsuariosTOP", 
        urlPatterns = {"/reportes/usuarios"})
public class ReporteUsuariosTOP extends HttpServlet {
    private final String NOMBRE_REPORTE = 
            "reportes/RF013.jasper";
    //ups wir haben keinen
    private final String NOMBRE_LOGO = 
            "imagenes/logo.png";
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
                throw new FileNotFoundException("No se encontró el archivo 'UsuariosTOP.jasper'");
            }
            
            Map<String, Object> parametros = 
                    new HashMap<>();
            
            int numtip = Integer.parseInt(request.getParameter("tipodeusuario"));
            parametros.put("numerodetipo",numtip);

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
        return "Genera reporte de Usuarios top";
    }
    
    
    
}
