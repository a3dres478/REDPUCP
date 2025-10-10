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
import pe.edu.pucp.inf30.RedPUCP.dao.Publicacion.PublicacionxComunidadDAO;
import pe.edu.pucp.inf30.RedPUCP.dao.usuario.UsuarioDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Comunidad.ComunidadDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Publicacion.PublicacionDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Publicacion.PublicacionxComunidadDAOImpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.UsuarioDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.Comunidad;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Publicacion;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.PublicacionxComunidad;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.tipouser;


/**
 *
 * @author invitado123
 */
public class PublicacionXComunidadDAOTest implements PersistibleProbable{
    private int testId;
    private int testUsuarioId;
    private int testComunidadId;
    private int testPublicacionId;
    private int testComunidadIdModificacion;
    private int testPublicacionIdModificacion;
    private final int idIncorrecto = 999999;
    
    @BeforeAll
    public void inicializar() {
        UsuarioDAO usuarioDAO = new UsuarioDAOimpl();
        Usuario usuario = new Usuario();
        usuario.setNombre("publicacionXcomunidad-Test");
        usuario.setDescripcion("Usuario de Prueba para publicacionXcomunidad");
        //usuario.setCodigopucp("30225787");
        usuario.setContrasenha("123");
        usuario.setEmail("usuario_prueba@yahoo.com");
        usuario.setKarma(50);
        usuario.setTipousuario(String.valueOf(tipouser.COMUN).charAt(0));
        this.testUsuarioId = usuarioDAO.crear(usuario);
        
        ComunidadDAO comunidadDAO = new ComunidadDAOimpl();
        Comunidad comunidad = new Comunidad();
        comunidad.setAdministrador((Usuario_comun)usuario);
        comunidad.setNombre("publicacionXcomunidad-Test");
        comunidad.setDescripcion("Comunidad para publicacionXcomunidad");
        comunidad.setCantidadmiembros(0);
        this.testComunidadId = comunidadDAO.crear(comunidad);
        
        //ComunidadDAO comunidadDAO = new ComunidadDAOimpl();
        Comunidad comunidadMod = new Comunidad();
        comunidadMod.setAdministrador((Usuario_comun)usuario);
        comunidadMod.setNombre("publicacionXcomunidad-Test MOD");
        comunidadMod.setDescripcion("Comunidad para publicacionXcomunidad MOD");
        comunidadMod.setCantidadmiembros(0);
        this.testComunidadIdModificacion = comunidadDAO.crear(comunidadMod);
        
        PublicacionDAO publicacionDAO = new PublicacionDAOimpl();
        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo("publicacionXcomunidad-Test");
        publicacion.setDescripcion("Publicacion para publicacionXcomunidad");
        publicacion.setVotosNegativos(1);
        publicacion.setVotosPositivos(1);
        publicacion.setAutor(new UsuarioDAOimpl().leer(testUsuarioId));
        publicacion.setComunidad(new ComunidadDAOimpl().leer(testComunidadId));
        this.testPublicacionId = publicacionDAO.crear(publicacion);
        
        //PublicacionDAO publicacionDAO = new PublicacionDAOimpl();
        Publicacion publicacionMod = new Publicacion();
        publicacion.setTitulo("publicacionXcomunidad-Test MOD");
        publicacion.setDescripcion("Publicacion para publicacionXcomunidad MOD");
        publicacion.setVotosNegativos(1);
        publicacion.setVotosPositivos(1);
        publicacion.setAutor(new UsuarioDAOimpl().leer(testUsuarioId));
        publicacion.setComunidad(new ComunidadDAOimpl().leer(testComunidadIdModificacion));
        this.testPublicacionIdModificacion = publicacionDAO.crear(publicacionMod);
    }
    
    @AfterAll
    public void limpiar() {
        PublicacionDAO publicacionDAO = new PublicacionDAOimpl();
        publicacionDAO.eliminar(testPublicacionId);
        publicacionDAO.eliminar(testPublicacionIdModificacion);
        ComunidadDAO comunidadDAO = new ComunidadDAOimpl();
        comunidadDAO.eliminar(testComunidadId);
        comunidadDAO.eliminar(testComunidadIdModificacion);
        UsuarioDAO usuarioDAO = new UsuarioDAOimpl();
        usuarioDAO.eliminar(testUsuarioId);
    }
    
    @Test
    @Order(1)
    @Override
    public void debeCrear(){
        PublicacionxComunidadDAO publiXcomDAO = new PublicacionxComunidadDAOImpl();
        PublicacionxComunidad publiXcom = new PublicacionxComunidad();
        publiXcom.setPubli(new PublicacionDAOimpl().leer(testPublicacionId));
        publiXcom.setComu(new ComunidadDAOimpl().leer(testComunidadId));
        
        this.testId = publiXcomDAO.crear(publiXcom);
        assertTrue(this.testId > 0);
    }
    
    @Test
    @Order(2)
    @Override
    public void debeActualizarSiIdExiste() {
        PublicacionxComunidadDAO publiXcomDAO = new PublicacionxComunidadDAOImpl();
        PublicacionxComunidad publiXcom = new PublicacionxComunidad();

        publiXcom.setComu(new ComunidadDAOimpl().leer(testComunidadIdModificacion));
        publiXcom.setPubli(new PublicacionDAOimpl().leer(testPublicacionIdModificacion));
                
        boolean modifico = publiXcomDAO.actualizar(publiXcom);
        assertTrue(modifico);

        PublicacionxComunidad publiXcomModificado = publiXcomDAO.leer(this.testId);
        assertEquals(publiXcomModificado.getComu().getId_comunidad(),testComunidadIdModificacion);
        assertEquals(publiXcomModificado.getPubli().getId(),testPublicacionIdModificacion); 
    }
    
    @Test
    @Order(3)
    @Override
    public void noDebeActualizarSiIdNoExiste() {
        PublicacionxComunidadDAO publiXcomDAO = new PublicacionxComunidadDAOImpl();
        PublicacionxComunidad publiXcom = new PublicacionxComunidad();
        publiXcom.setId_publicacionXcomunidad(this.idIncorrecto);
        
        publiXcom.setComu(new ComunidadDAOimpl().leer(testComunidadIdModificacion));
        publiXcom.setPubli(new PublicacionDAOimpl().leer(testPublicacionIdModificacion));

        boolean modifico = publiXcomDAO.actualizar(publiXcom);
        assertFalse(modifico);
    }
    
    @Test
    @Order(4)
    @Override
    public void noDebeEliminarSiIdNoExiste() {
        PublicacionxComunidadDAO publiXcomDAO = new PublicacionxComunidadDAOImpl();
        boolean elimino = publiXcomDAO.eliminar(this.idIncorrecto);
        assertFalse(elimino);
    }
    
    @Test
    @Order(5)
    @Override
    public void debeLeerSiIdExiste() {
        PublicacionxComunidadDAO publiXcomDAO = new PublicacionxComunidadDAOImpl();
        PublicacionxComunidad publiXcom = publiXcomDAO.leer(this.testId);
        assertNotNull(publiXcom);
    }
    
    @Test
    @Order(6)
    @Override
    public void noDebeLeerSiIdNoExiste() {
        PublicacionxComunidadDAO publiXcomDAO = new PublicacionxComunidadDAOImpl();
        PublicacionxComunidad publiXcom = publiXcomDAO.leer(this.idIncorrecto);
        assertNull(publiXcom);
    }
    
    @Test
    @Order(7)
    @Override
    public void debeLeerTodos() {
        PublicacionxComunidadDAO publiXcomDAO = new PublicacionxComunidadDAOImpl();
        List<PublicacionxComunidad> publiXcom_s = publiXcomDAO.leerTodos();
        
        assertNotNull(publiXcom_s);
        assertFalse(publiXcom_s.isEmpty());
    }
    
    @Test
    @Order(8)
    @Override
    public void debeEliminarSiIdExiste() {
        PublicacionxComunidadDAO publiXcomDAO = new PublicacionxComunidadDAOImpl();
        boolean elimino = publiXcomDAO.eliminar(this.testId);
        assertTrue(elimino);
    }
}
