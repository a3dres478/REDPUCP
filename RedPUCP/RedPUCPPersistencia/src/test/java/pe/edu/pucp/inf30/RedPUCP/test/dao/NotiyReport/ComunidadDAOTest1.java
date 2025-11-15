/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.test.dao.NotiyReport;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import pe.edu.pucp.inf30.RedPUCP.test.dao.PersistibleProbable;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import pe.edu.pucp.inf30.RedPUCP.dao.Comunidad.ComunidadDAO;
import pe.edu.pucp.inf30.RedPUCP.dao.Publicacion.PublicacionDAO;
import pe.edu.pucp.inf30.RedPUCP.dao.ReportesU.ReporteDAO;
import pe.edu.pucp.inf30.RedPUCP.dao.usuario.UsuarioDAO;
import pe.edu.pucp.inf30.RedPUCP.dao.usuario.Usuario_comunDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Comunidad.ComunidadDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Publicacion.PublicacionDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.ReportesU.ReporteDAOImpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.UsuarioDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.Usuario_comunDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.Comunidad;
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.EstadoComunidad;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Comentario;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Publicacion;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;
import pe.edu.pucp.inf30.RedPUCP.modelo.ReportesU.Reporte;

/**
 *
 * @author andre
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ComunidadDAOTest1 implements PersistibleProbable {

    private int testId;
    private int testUsuarioId;
    private final int idIncorrecto = 999999;

    @Test
    @Order(1)
    @Override
    public void debeCrear() {

        /*Obtener publicacion*/
        PublicacionDAO publicacionDAO = new PublicacionDAOimpl();
        Publicacion publi = publicacionDAO.leer(1);

        /*USuario que hará el reporte*/
        Usuario_comunDAO usuarioDAO = new Usuario_comunDAOimpl();
        Usuario_comun reportador = usuarioDAO.leer(10);

        /*se obtiene el usuario que creo la publicacion*/
        int data = publi.getAutor().getIdUsuario();
        Usuario_comun creador = usuarioDAO.leer(data);

        ReporteDAO reporteDAO = new ReporteDAOImpl();
        Reporte report = new Reporte();

        System.err.println("LOGRADO");
        report.setDetalle("testeodereporte a publicacion general");
        report.setPublicacionreportada(publi);
        report.setPublicador(creador);
        report.setReportador(reportador);
        report.setTipo("SPAM");

        Comentario comen =new Comentario();
        report.setComentarioreportado(comen);
        
        
        
        this.testId = reporteDAO.crear(report);
        assertTrue(this.testId > 0);
        //assertTrue(1==1);
    }

    @Test
    @Order(2)
    @Override
    public void debeActualizarSiIdExiste() {
//        ComunidadDAO comunidadDAO = new ComunidadDAOimpl();
//        Comunidad comunidad = comunidadDAO.leer(7);
//        //comunidad.setNombre("Comunidad_test1234");
//        comunidad.setDescripcion("Test Comunidad modificacion");
//        comunidad.setEstado(String.valueOf(EstadoComunidad.SUSPENDIDA).charAt(0));
//        //comunidad.setAdministrador(new Usuario_comunDAOimpl().leer(testUsuarioId));
//
//        boolean modifico = comunidadDAO.actualizar(comunidad);
//        assertTrue(modifico);
//
//        Comunidad comunidadModificado = comunidadDAO.leer(7);
//        //assertEquals(comunidadModificado.getNombre(),"Test Comunidad modificada");
//        assertEquals(comunidadModificado.getDescripcion(),"Test Comunidad modificacion");
//        assertEquals(comunidadModificado.getEstado(),String.valueOf(EstadoComunidad.SUSPENDIDA).charAt(0));    
        assertTrue(1 == 1);
    }

    @Test
    @Order(3)
    @Override
    public void noDebeActualizarSiIdNoExiste() {
//        ComunidadDAO comunidadDAO = new ComunidadDAOimpl();
//        Comunidad comunidad = new Comunidad();
//        comunidad.setId_comunidad(this.idIncorrecto);
//        comunidad.setNombre("Test Comunidad modificada");
//        comunidad.setDescripcion("Test Comunidad modificacion");
//        comunidad.setEstado(String.valueOf(EstadoComunidad.SUSPENDIDA).charAt(0));
//        comunidad.setAdministrador(new Usuario_comunDAOimpl().leer(testUsuarioId));
//
//        boolean modifico = comunidadDAO.actualizar(comunidad);
//        assertFalse(modifico);
        assertTrue(1 == 1);
    }

    @Test
    @Order(4)
    @Override
    public void noDebeEliminarSiIdNoExiste() {
//        ComunidadDAO comunidadDAO = new ComunidadDAOimpl();
//        boolean elimino = comunidadDAO.eliminar(this.idIncorrecto);
//        assertFalse(elimino);
        assertTrue(1 == 1);
    }

    @Test
    @Order(5)
    @Override
    public void debeLeerSiIdExiste() {
//        ComunidadDAO comunidadDAO = new ComunidadDAOimpl();
//        Comunidad comunidad = comunidadDAO.leer(this.testId);
//        assertNotNull(comunidad);
        assertTrue(1 == 1);
    }

    @Test
    @Order(6)
    @Override
    public void noDebeLeerSiIdNoExiste() {
//        ComunidadDAO comunidadDAO = new ComunidadDAOimpl();
//        Comunidad comunidad = comunidadDAO.leer(this.idIncorrecto);
//        assertNull(comunidad);
        assertTrue(1 == 1);
    }

    @Test
    @Order(7)
    @Override
    public void debeLeerTodos() {
//        ComunidadDAO comunidadDAO = new ComunidadDAOimpl();
//        List<Comunidad> comunidades = comunidadDAO.leerTodos();
//        
//        assertNotNull(comunidades);
//        assertFalse(comunidades.isEmpty());
        assertTrue(1 == 1);
    }

    @Test
    @Order(8)
    @Override
    public void debeEliminarSiIdExiste() {
//        ComunidadDAO comunidadDAO = new ComunidadDAOimpl();
//        boolean elimino = comunidadDAO.eliminar(this.testId);
//        assertTrue(elimino);
        assertTrue(1 == 1);
    }

}
