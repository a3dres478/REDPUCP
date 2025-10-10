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
import pe.edu.pucp.inf30.RedPUCP.dao.Publicacion.PublicacionDAO;
import pe.edu.pucp.inf30.RedPUCP.dao.usuario.UsuarioDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Comunidad.ComunidadDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Publicacion.PublicacionDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.UsuarioDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.Comunidad;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.EstadoPublicacion;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Publicacion;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.tipouser;



/**
 *
 * @author invitado123
 */
public class PublicacionDAOTest implements PersistibleProbable{
    private int testId;
    private int testUsuarioId;
    private int testComunidadId;
    private final int idIncorrecto = 999999;
    
    @BeforeAll
    public void inicializar() {
        UsuarioDAO usuarioDAO = new UsuarioDAOimpl();
        Usuario usuario = new Usuario();
        usuario.setNombre("Usuario -> Publicacion-Test");
        usuario.setDescripcion("Usuario de Prueba para publicacion");
        //usuario.setCodigopucp("30225787");
        usuario.setContrasenha("123");
        usuario.setEmail("usuario_prueba@yahoo.com");
        usuario.setKarma(50);
        usuario.setTipousuario(String.valueOf(tipouser.COMUN).charAt(0));
        this.testUsuarioId = usuarioDAO.crear(usuario);
        
        ComunidadDAO comunidadDAO = new ComunidadDAOimpl();
        Comunidad comunidad = new Comunidad();
        comunidad.setAdministrador((Usuario_comun)usuario);
        comunidad.setNombre("Comunidad -> Publicacion-Test");
        comunidad.setDescripcion("Comunidad para Publicacion-Test");
        comunidad.setCantidadmiembros(0);
        this.testComunidadId = comunidadDAO.crear(comunidad);
    }
    
    @AfterAll
    public void limpiar() {
        ComunidadDAO comunidadDAO = new ComunidadDAOimpl();
        comunidadDAO.eliminar(testComunidadId);
        UsuarioDAO usuarioDAO = new UsuarioDAOimpl();
        usuarioDAO.eliminar(testUsuarioId);
    }
    
    @Test
    @Order(1)
    @Override
    public void debeCrear(){
        PublicacionDAO publicacionDAO = new PublicacionDAOimpl();
        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo("Publicacion test");
        publicacion.setDescripcion("Publicacion test descripcion");
        publicacion.setVotosNegativos(1);
        publicacion.setVotosPositivos(1);
        publicacion.setAutor(new UsuarioDAOimpl().leer(testUsuarioId));
        publicacion.setComunidad(new ComunidadDAOimpl().leer(testComunidadId));
        
        this.testId = publicacionDAO.crear(publicacion);
        assertTrue(this.testId > 0);
    }
    
    @Test
    @Order(2)
    @Override
    public void debeActualizarSiIdExiste() {
        PublicacionDAO publicacionDAO = new PublicacionDAOimpl();
        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo("Publicacion Modificacion test");
        publicacion.setDescripcion("Test modificacion descripcion");
        publicacion.setVotosNegativos(5);
        publicacion.setVotosPositivos(5);
        publicacion.setAutor(new UsuarioDAOimpl().leer(testUsuarioId));
        publicacion.setComunidad(new ComunidadDAOimpl().leer(testComunidadId));
        publicacion.setEstado(String.valueOf(EstadoPublicacion.BLOQUEADA).charAt(0));
        
        
        boolean modifico = publicacionDAO.actualizar(publicacion);
        assertTrue(modifico);

        Publicacion publicacionModificado = publicacionDAO.leer(this.testId);
        assertEquals(publicacionModificado.getTitulo(),"Publicacion Modificacion test");
        assertEquals(publicacionModificado.getDescripcion(),"Test modificacion descripcion");
        assertEquals(publicacionModificado.getEstado(),String.valueOf(EstadoPublicacion.BLOQUEADA).charAt(0));    
    }
    
    @Test
    @Order(3)
    @Override
    public void noDebeActualizarSiIdNoExiste() {
        PublicacionDAO publicacionDAO = new PublicacionDAOimpl();
        Publicacion publicacion = new Publicacion();
        publicacion.setId(this.idIncorrecto);
        publicacion.setTitulo("Publicacion Modificacion test");
        publicacion.setDescripcion("Test modificacion descripcion");
        publicacion.setVotosNegativos(5);
        publicacion.setVotosPositivos(5);
        publicacion.setAutor(new UsuarioDAOimpl().leer(testUsuarioId));
        publicacion.setComunidad(new ComunidadDAOimpl().leer(testComunidadId));
        publicacion.setEstado(String.valueOf(EstadoPublicacion.BLOQUEADA).charAt(0));

        boolean modifico = publicacionDAO.actualizar(publicacion);
        assertFalse(modifico);
    }
    
    @Test
    @Order(4)
    @Override
    public void noDebeEliminarSiIdNoExiste() {
        PublicacionDAO publicacionDAO = new PublicacionDAOimpl();
        boolean elimino = publicacionDAO.eliminar(this.idIncorrecto);
        assertFalse(elimino);
    }
    
    @Test
    @Order(5)
    @Override
    public void debeLeerSiIdExiste() {
        PublicacionDAO publicacionDAO = new PublicacionDAOimpl();
        Publicacion publicacion = publicacionDAO.leer(this.testId);
        assertNotNull(publicacion);
    }
    
    @Test
    @Order(6)
    @Override
    public void noDebeLeerSiIdNoExiste() {
        PublicacionDAO publicacionDAO = new PublicacionDAOimpl();
        Publicacion publicacion = publicacionDAO.leer(this.idIncorrecto);
        assertNull(publicacion);
    }
    
    @Test
    @Order(7)
    @Override
    public void debeLeerTodos() {
        PublicacionDAO publicacionDAO = new PublicacionDAOimpl();
        List<Publicacion> publicaciones = publicacionDAO.leerTodos();
        
        assertNotNull(publicaciones);
        assertFalse(publicaciones.isEmpty());
    }
    
    @Test
    @Order(8)
    @Override
    public void debeEliminarSiIdExiste() {
        PublicacionDAO publicacionDAO = new PublicacionDAOimpl();
        boolean elimino = publicacionDAO.eliminar(this.testId);
        assertTrue(elimino);
    }
}
