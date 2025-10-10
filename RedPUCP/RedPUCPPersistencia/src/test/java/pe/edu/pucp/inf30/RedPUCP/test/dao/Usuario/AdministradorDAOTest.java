/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.test.dao.Usuario;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;


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
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Administrador;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.AdministradorDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.dao.usuario.AdministradorDAO;

/**
 *
 * @author andre
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)


public class AdministradorDAOTest implements  PersistibleProbable{
    
    private int testId;
    private int testUsuarioId;
    private final int idIncorrecto = 999999;

    @Test
    @Order(1)
    @Override
    public void debeCrear(){
       // Date fechaactual= new Date(System.currentTimeMillis());
        AdministradorDAO administradorDAO =new AdministradorDAOimpl();
       Administrador comun =new Administrador();
        
        
        //Usuario_comunDAO usuariocomunDAO=new Usuario_comunDAOimpl();
        //Usuario_comun comun= new Usuario_comun();
        comun.setClave_acceso("abc");
        comun.setContrasenha("1234");
        comun.setDescripcion("ADMIN1");
        comun.setNombre("ADMIN1");
        comun.setEmail("correo@gmail.com");
        comun.setFechaRegistro(new java.util.Date());
        comun.setEstadouser('A');
        comun.setKarma(0);
        this.testId=administradorDAO.crear(comun);
        //this.testId=usuariocomunDAO.crear(comun);
        assertTrue(this.testId>0);
    }
    
    @Test
    @Order(2)
    @Override
    public void debeActualizarSiIdExiste() {
       
        AdministradorDAO administradorDAO =new AdministradorDAOimpl();
       Administrador comun =new Administrador();
        
        
        //Usuario_comunDAO usuariocomunDAO=new Usuario_comunDAOimpl();
        //Usuario_comun comun= new Usuario_comun();
        comun.setClave_acceso("abc");
        
        
        comun.setIdUsuario(this.testId);
        comun.setNombre("ADMIN2");
        comun.setClave_acceso("abc");
        comun.setContrasenha("1234");
        comun.setDescripcion("ADMIN1");
        comun.setKarma(0);
        comun.setEstadouser('A');
        comun.setEmail("correo@gmail");
        comun.setFechaRegistro(new java.util.Date());
        boolean modifico=administradorDAO.actualizar(comun);
        assertTrue(modifico);
        Administrador usuariocomun=administradorDAO.leer(this.testId);
        assertEquals(usuariocomun.getNombre(),"Usuario_comun2");
        assertEquals(usuariocomun.getDescripcion(),"Usuario_comunDes1");
        assertEquals(usuariocomun.getEstadouser(), 'S');
        assertEquals(usuariocomun.getClave_acceso(),"abc");
    }
    
    @Test
    @Order(3)
    @Override
    public void noDebeActualizarSiIdNoExiste() {
        AdministradorDAO administradorDAO =new AdministradorDAOimpl();
       Administrador comun =new Administrador();
        
       comun.setClave_acceso("abc");
        
        
        comun.setIdUsuario(this.idIncorrecto);
        comun.setNombre("ADMIN3");
        comun.setDescripcion("ADMIN3");
        comun.setKarma(0);
        comun.setEstadouser('A');
        comun.setEmail("correo@gmail");
        comun.setFechaRegistro(new java.util.Date());
        comun.setContrasenha("1234");
        
        boolean modifico=administradorDAO.actualizar(comun);
        assertTrue(modifico);
        Administrador usuariocomun=administradorDAO.leer(this.testId);
        assertEquals(usuariocomun.getNombre(),"Usuario_comun2");
        assertEquals(usuariocomun.getDescripcion(),"Usuario_comunDes1");
        assertEquals(usuariocomun.getEstadouser(), 'S');
        assertEquals(usuariocomun.getClave_acceso(),"abc");
        
        assertFalse(modifico);
    }
    
    @Test
    @Order(4)
    @Override
    public void noDebeEliminarSiIdNoExiste() {
        AdministradorDAO usuarioDAO=new AdministradorDAOimpl(); 
        boolean elimino =usuarioDAO.eliminar(this.idIncorrecto);
        assertFalse(elimino);        
    }
    
    @Test
    @Order(5)
    @Override
    public void debeLeerSiIdExiste() {        
        AdministradorDAO usuarioDAO=new AdministradorDAOimpl(); 
        Administrador comun= usuarioDAO.leer(this.testId);
        assertNotNull(comun);
    }
    
    @Test
    @Order(6)
    @Override
    public void noDebeLeerSiIdNoExiste() {
        AdministradorDAO usuarioDAO=new AdministradorDAOimpl(); 
        Administrador comun= usuarioDAO.leer(this.idIncorrecto);
        assertNull(comun);
    }
    
    @Test
    @Order(7)
    @Override
    public void debeLeerTodos() {
        AdministradorDAO usuarioDAO= new AdministradorDAOimpl();
        List<Administrador> usuarios =usuarioDAO.leerTodos();
        assertNotNull(usuarios);
        assertFalse(usuarios.isEmpty());
    }
    
    @Test
    @Order(8)
    @Override
    public void debeEliminarSiIdExiste() {
        AdministradorDAO usuarioDAO=new AdministradorDAOimpl(); 
        boolean elimino = usuarioDAO.eliminar(this.testId);
        assertTrue(elimino); 
    }
    
    
}
