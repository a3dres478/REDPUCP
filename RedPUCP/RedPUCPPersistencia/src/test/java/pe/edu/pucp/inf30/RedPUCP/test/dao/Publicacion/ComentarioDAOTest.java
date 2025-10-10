/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.test.dao.Publicacion;

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
import pe.edu.pucp.inf30.RedPUCP.dao.Publicacion.ComentarioDAO;
import pe.edu.pucp.inf30.RedPUCP.dao.Publicacion.PublicacionDAO;
import pe.edu.pucp.inf30.RedPUCP.dao.usuario.UsuarioDAO;
import pe.edu.pucp.inf30.RedPUCP.dao.usuario.Usuario_comunDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Comunidad.ComunidadDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Publicacion.ComentarioDAOImpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Publicacion.PublicacionDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.UsuarioDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.Usuario_comunDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.Comunidad;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Comentario;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Publicacion;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;



/**
 *
 * @author invitado123
 */
public class ComentarioDAOTest implements PersistibleProbable {
    private int testId;
    private int testUsuarioId;
    private int testPublicacionId;
    private int testComunidadId;
    private final int idIncorrecto = 999999;
    
    @BeforeAll
    public void inicializar() {
        Usuario_comunDAO usuarioDAO = new Usuario_comunDAOimpl();
        Usuario_comun usuario = new Usuario_comun();
        usuario.setNombre("Usuario -> ComentarioTest");
        usuario.setDescripcion("Usuario de Prueba para Comentario");
        usuario.setCodigopucp("30225787");
        this.testUsuarioId = usuarioDAO.crear(usuario);
        
        ComunidadDAO comunidadDAO = new ComunidadDAOimpl();
        Comunidad comunidad = new Comunidad();
        comunidad.setAdministrador(usuario);
        comunidad.setNombre("Comunidad -> ComentarioTest");
        comunidad.setDescripcion("Comunidad para ComentarioTest");
        this.testComunidadId = comunidadDAO.crear(comunidad);
        
        PublicacionDAO publicacionDAO = new PublicacionDAOimpl();
        Publicacion publicacion = new Publicacion();
        publicacion.setAutor(usuario);
        publicacion.setComunidad(comunidad);
        publicacion.setTitulo("Publicacion -> ComentarioTest");
        publicacion.setDescripcion("Publicacion para ComentarioTest");
        this.testPublicacionId = publicacionDAO.crear(publicacion);
    }
    
    @AfterAll
    public void limpiar() {
        PublicacionDAO publicacionDAO = new PublicacionDAOimpl();
        publicacionDAO.eliminar(testPublicacionId);
        ComunidadDAO comunidadDAO = new ComunidadDAOimpl();
        comunidadDAO.eliminar(testComunidadId);
        UsuarioDAO usuarioDAO = new UsuarioDAOimpl();
        usuarioDAO.eliminar(testUsuarioId);
    }
    
    @Test
    @Order(1)
    @Override
    public void debeCrear(){
        ComentarioDAO comentarioDAO = new ComentarioDAOImpl();
        Comentario comentario = new Comentario();
        comentario.setNombre("Comunidad_test");
        comentario.setDescripcion("test");
        comentario.setAdministrador(new Usuario_comunDAOimpl().leer(testUsuarioId));
        
        this.testId = comunidadDAO.crear(comunidad);
        assertTrue(this.testId > 0);
    }
    
    @Test
    @Order(2)
    @Override
    public void debeActualizarSiIdExiste() {
        ComunidadDAO comunidadDAO = new ComunidadDAOimpl();
        Comunidad comunidad = new Comunidad();
        comunidad.setNombre("Test Comunidad modificada");
        comunidad.setDescripcion("Test Comunidad modificacion");
        comunidad.setEstado(String.valueOf(EstadoComunidad.SUSPENDIDA).charAt(0));
        comunidad.setAdministrador(new Usuario_comunDAOimpl().leer(testUsuarioId));

        boolean modifico = comunidadDAO.actualizar(comunidad);
        assertTrue(modifico);

        Comunidad comunidadModificado = comunidadDAO.leer(this.testId);
        assertEquals(comunidadModificado.getNombre(),"Test Comunidad modificada");
        assertEquals(comunidadModificado.getDescripcion(),"Test Comunidad modificacion");
        assertEquals(comunidadModificado.getEstado(),String.valueOf(EstadoComunidad.SUSPENDIDA).charAt(0));    
    }
    
    @Test
    @Order(3)
    @Override
    public void noDebeActualizarSiIdNoExiste() {
        ComunidadDAO comunidadDAO = new ComunidadDAOimpl();
        Comunidad comunidad = new Comunidad();
        comunidad.setId_comunidad(this.idIncorrecto);
        comunidad.setNombre("Test Comunidad modificada");
        comunidad.setDescripcion("Test Comunidad modificacion");
        comunidad.setEstado(String.valueOf(EstadoComunidad.SUSPENDIDA).charAt(0));
        comunidad.setAdministrador(new Usuario_comunDAOimpl().leer(testUsuarioId));

        boolean modifico = comunidadDAO.actualizar(comunidad);
        assertFalse(modifico);
    }
    
    @Test
    @Order(4)
    @Override
    public void noDebeEliminarSiIdNoExiste() {
        ComunidadDAO comunidadDAO = new ComunidadDAOimpl();
        boolean elimino = comunidadDAO.eliminar(this.idIncorrecto);
        assertFalse(elimino);
    }
    
    @Test
    @Order(5)
    @Override
    public void debeLeerSiIdExiste() {
        ComunidadDAO comunidadDAO = new ComunidadDAOimpl();
        Comunidad comunidad = comunidadDAO.leer(this.testId);
        assertNotNull(comunidad);
    }
    
    @Test
    @Order(6)
    @Override
    public void noDebeLeerSiIdNoExiste() {
        ComunidadDAO comunidadDAO = new ComunidadDAOimpl();
        Comunidad comunidad = comunidadDAO.leer(this.idIncorrecto);
        assertNull(comunidad);
    }
    
    @Test
    @Order(7)
    @Override
    public void debeLeerTodos() {
        ComunidadDAO comunidadDAO = new ComunidadDAOimpl();
        List<Comunidad> comunidades = comunidadDAO.leerTodos();
        
        assertNotNull(comunidades);
        assertFalse(comunidades.isEmpty());
    }
    
    @Test
    @Order(8)
    @Override
    public void debeEliminarSiIdExiste() {
        ComunidadDAO comunidadDAO = new ComunidadDAOimpl();
        boolean elimino = comunidadDAO.eliminar(this.testId);
        assertTrue(elimino);
    }
    
}
