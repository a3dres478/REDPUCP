/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.test.dao.Comunidad;

import java.time.Instant;
import java.util.Date;
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
import pe.edu.pucp.inf30.RedPUCP.dao.usuario.UsuarioDAO;
import pe.edu.pucp.inf30.RedPUCP.dao.usuario.Usuario_comunDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.UsuarioDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.Usuario_comunDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.Comunidad;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;

/**
 *
 * @author invitado123
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ComunidadDAOTest implements PersistibleProbable{
    private int testId;
    private int testUsuarioId;
    private final int idIncorrecto = 999999;

    @BeforeAll
    public void inicializar() {
        Usuario_comunDAO usuarioComunDAO = new Usuario_comunDAOimpl();
        Usuario_comun usuarioComun = new Usuario_comun();
        usuarioComun.setNombre("Usuario -> ComunidadTest");
        usuarioComun.setDescripcion("Usuario de Prueba para Comunidad");
        this.testUsuarioId = usuarioComunDAO.crear(usuarioComun);
    }
    
    @AfterAll
    public void limpiar() {
        Usuario_comunDAO usuarioComunDAO = new Usuario_comunDAOimpl();
        usuarioComunDAO.eliminar(testUsuarioId);
    }
    
    @Test
    @Order(1)
    @Override
    public void debeCrear(){
        Comunidad comunidad = new Comunidad();
        comunidad.setNombre("Comunidad_test");
        comunidad.setDescripcion("test");
        comunidad.set
    }
}
