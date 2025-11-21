/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.test.dao.Usuario;


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
import pe.edu.pucp.inf30.RedPUCP.dao.usuario.UsuarioDAO;
import pe.edu.pucp.inf30.RedPUCP.dao.usuario.Usuario_comunDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Comunidad.ComunidadDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.UsuarioDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.Usuario_comunDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.Comunidad;
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.EstadoComunidad;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;


/**
 *
 * @author andre
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class Usuario_comunDAOTest implements  PersistibleProbable{
    private int testId;
    private int testUsuarioId;
    private final int idIncorrecto = 999999;
   
    @Test
    @Order(1)
    @Override
    public void debeCrear(){
        //funciona
       // Date fechaactual= new Date(System.currentTimeMillis());
//        Usuario_comunDAO usuariocomunDAO=new Usuario_comunDAOimpl();
//        Usuario_comun comun= new Usuario_comun();
//        comun.setCodigopucp("123456");
//        comun.setContrasenha("123456");
//        comun.setDescripcion("Usuario_comunDes112");
//        comun.setNombre("Usuario_comun1234");
//        comun.setEmail("pruebauser11@gmail.com");
//        comun.setFechaRegistro(new java.util.Date());
//        comun.setEstadouser('A');
//        comun.setKarma(0);
//        this.testId=usuariocomunDAO.crear(comun);
//        assertTrue(this.testId>0);
    }
    
    @Test
    @Order(2)
    @Override
    public void debeActualizarSiIdExiste() {
//        Usuario_comunDAO usuariocomunDAO=new Usuario_comunDAOimpl();
//        Usuario_comun comun= usuariocomunDAO.leer(testId);
//        
//        comun.setIdUsuario(this.testId);
//        comun.setNombre("Usuario_comunactualizadoGAA");
//        comun.setCodigopucp("12345");
//        comun.setDescripcion("Usuario_comuACDes1");
//        comun.setKarma(0);
//        comun.setEstadouser('A');
//        //comun.setEmail("correousuariocomun13@gmail");
//        comun.setFechaRegistro(new java.util.Date());
//        boolean modifico=usuariocomunDAO.actualizar(comun);
//        assertTrue(modifico);
//        Usuario_comun usuariocomun=usuariocomunDAO.leer(this.testId);
//        assertEquals(usuariocomun.getNombre(),"Usuario_comun2");
//        assertEquals(usuariocomun.getDescripcion(),"Usuario_comunDes1");
//        assertEquals(usuariocomun.getEstadouser(), 'S');
//        assertEquals(usuariocomun.getCodigopucp(),"1234");
    }
    
    @Test
    @Order(3)
    @Override
    public void noDebeActualizarSiIdNoExiste() {
//        Usuario_comunDAO usuariocomunDAO=new Usuario_comunDAOimpl();
//        Usuario_comun comun= new Usuario_comun();
//        comun.setIdUsuario(this.idIncorrecto);
//        comun.setNombre("Usuario_comun3");
//        comun.setCodigopucp("1234");
//        comun.setDescripcion("Usuario_comunDes3");
//        comun.setKarma(0);
//        comun.setEstadouser('S');
//        comun.setEmail("correo@gmail");
//        comun.setFechaRegistro(new java.util.Date());
//        
//        boolean modifico=usuariocomunDAO.actualizar(comun);
//        assertTrue(modifico);
//        Usuario_comun usuariocomun=usuariocomunDAO.leer(this.testId);
//        assertEquals(usuariocomun.getNombre(),"Usuario_comun2");
//        assertEquals(usuariocomun.getDescripcion(),"Usuario_comunDes1");
//        assertEquals(usuariocomun.getEstadouser(), 'S');
//        assertEquals(usuariocomun.getCodigopucp(),"1234");
//        
//        assertFalse(modifico);
    }
    
    @Test
    @Order(4)
    @Override
    public void noDebeEliminarSiIdNoExiste() {
//        Usuario_comunDAO usuarioDAO=new Usuario_comunDAOimpl();
//        boolean elimino =usuarioDAO.eliminar(this.idIncorrecto);
//        assertFalse(elimino);
        
        
        
        
    }
    
    @Test
    @Order(5)
    @Override
    public void debeLeerSiIdExiste() {
//        Usuario_comunDAO usuarioDAO=new Usuario_comunDAOimpl();
//        Usuario_comun comun= usuarioDAO.leer(this.testId);
//        assertNotNull(comun);
    }
    
    @Test
    @Order(6)
    @Override
    public void noDebeLeerSiIdNoExiste() {
//        Usuario_comunDAO usuarioDAO=new Usuario_comunDAOimpl();
//        Usuario_comun comun= usuarioDAO.leer(this.idIncorrecto);
//        assertNull(comun);
    }
    
    @Test
    @Order(7)
    @Override
    public void debeLeerTodos() {
//        Usuario_comunDAO usuarioDAO= new Usuario_comunDAOimpl();
//        List<Usuario_comun> usuarios =usuarioDAO.leerTodos();
//        //assertNotNull(usuarios);
//        assertFalse(usuarios.isEmpty());
    }
    
    @Test
    @Order(8)
    @Override
    public void debeEliminarSiIdExiste() {
//        Usuario_comunDAO usuarioDAO= new Usuario_comunDAOimpl();
//        boolean elimino = usuarioDAO.eliminar(this.testId);
//        assertTrue(elimino); 
    }


}
