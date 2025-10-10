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
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.EstadoComentario;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Publicacion;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.tipouser;



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
        usuario.setContrasenha("123");
        usuario.setEmail("usuario_prueba@yahoo.com");
        usuario.setKarma(50);
        usuario.setTipousuario(String.valueOf(tipouser.COMUN).charAt(0));
        this.testUsuarioId = usuarioDAO.crear(usuario);
        
        ComunidadDAO comunidadDAO = new ComunidadDAOimpl();
        Comunidad comunidad = new Comunidad();
        comunidad.setAdministrador(usuario);
        comunidad.setNombre("Comunidad -> ComentarioTest");
        comunidad.setDescripcion("Comunidad para ComentarioTest");
        comunidad.setCantidadmiembros(0);
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
        comentario.setContenido("Comentario test");
        comentario.setVotosNegativos(1);
        comentario.setVotosPositivos(1);
        comentario.setAutor(new Usuario_comunDAOimpl().leer(testUsuarioId));
        comentario.setPublicacion(new PublicacionDAOimpl().leer(testPublicacionId));
        
        this.testId = comentarioDAO.crear(comentario);
        assertTrue(this.testId > 0);
    }
    
    @Test
    @Order(2)
    @Override
    public void debeActualizarSiIdExiste() {
        ComentarioDAO comentarioDAO = new ComentarioDAOImpl();
        Comentario comentario = new Comentario();
        comentario.setContenido("Comentario Modificacion test");
        comentario.setVotosNegativos(1);
        comentario.setVotosPositivos(1);
        comentario.setAutor(new Usuario_comunDAOimpl().leer(testUsuarioId));
        comentario.setPublicacion(new PublicacionDAOimpl().leer(testPublicacionId));
        comentario.setEstado(String.valueOf(EstadoComentario.BLOQUEADO).charAt(0));
        
        
        boolean modifico = comentarioDAO.actualizar(comentario);
        assertTrue(modifico);

        Comentario comentarioModificado = comentarioDAO.leer(this.testId);
        assertEquals(comentarioModificado.getContenido(),"Comentario Modificacion test");
        assertEquals(comentarioModificado.getEstado(),String.valueOf(EstadoComentario.BLOQUEADO).charAt(0));    
    }
    
    @Test
    @Order(3)
    @Override
    public void noDebeActualizarSiIdNoExiste() {
        ComentarioDAO comentarioDAO = new ComentarioDAOImpl();
        Comentario comentario = new Comentario();
        comentario.setId(this.idIncorrecto);
        comentario.setContenido("Test Comentario modificada");
        comentario.setVotosNegativos(1);
        comentario.setVotosPositivos(1);
        comentario.setAutor(new Usuario_comunDAOimpl().leer(testUsuarioId));
        comentario.setPublicacion(new PublicacionDAOimpl().leer(testPublicacionId));
        comentario.setEstado(String.valueOf(EstadoComentario.BLOQUEADO).charAt(0));

        boolean modifico = comentarioDAO.actualizar(comentario);
        assertFalse(modifico);
    }
    
    @Test
    @Order(4)
    @Override
    public void noDebeEliminarSiIdNoExiste() {
        ComentarioDAO comentarioDAO = new ComentarioDAOImpl();
        boolean elimino = comentarioDAO.eliminar(this.idIncorrecto);
        assertFalse(elimino);
    }
    
    @Test
    @Order(5)
    @Override
    public void debeLeerSiIdExiste() {
        ComentarioDAO comentarioDAO = new ComentarioDAOImpl();
        Comentario comentario = comentarioDAO.leer(this.testId);
        assertNotNull(comentario);
    }
    
    @Test
    @Order(6)
    @Override
    public void noDebeLeerSiIdNoExiste() {
        ComentarioDAO comentarioDAO = new ComentarioDAOImpl();
        Comentario comentario = comentarioDAO.leer(this.idIncorrecto);
        assertNull(comentario);
    }
    
    @Test
    @Order(7)
    @Override
    public void debeLeerTodos() {
        ComentarioDAO comentarioDAO = new ComentarioDAOImpl();
        List<Comentario> comentarios = comentarioDAO.leerTodos();
        
        assertNotNull(comentarios);
        assertFalse(comentarios.isEmpty());
    }
    
    @Test
    @Order(8)
    @Override
    public void debeEliminarSiIdExiste() {
        ComentarioDAO comentarioDAO = new ComentarioDAOImpl();
        boolean elimino = comentarioDAO.eliminar(this.testId);
        assertTrue(elimino);
    }
    
}
