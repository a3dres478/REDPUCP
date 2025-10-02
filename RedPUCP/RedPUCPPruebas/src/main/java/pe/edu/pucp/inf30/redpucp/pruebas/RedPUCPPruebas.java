/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pe.edu.pucp.inf30.redpucp.pruebas;

import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Types;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Publicacion;
import pe.edu.pucp.inf30.RedPUCP.dao.usuario.UsuarioDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.UsuarioDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.Comunidad;
import pe.edu.pucp.inf30.RedPUCP.dao.Comunidad.ComunidadDAO;
import pe.edu.pucp.inf30.RedPUCP.dao.Publicacion.PublicacionDAO;
import pe.edu.pucp.inf30.RedPUCP.dao.usuario.Usuario_comunDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Comunidad.ComunidadDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Publicacion.PublicacionDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.Usuario_comunDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;
import pe.edu.pucp.inf30.RedPUCP.dao.voto.VotoDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.voto.VotoDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.voto.Voto;
/**
 *
 * @author HECTOR
 */
public class RedPUCPPruebas {

    public static void main(String[] args) {
        
        // Publicacion prueba:
        
//        UsuarioDAO user_dao = new UsuarioDAOimpl();
//        Usuario user = user_dao.buscar(4);
//        ComunidadDAO com_dao = new ComunidadDAOimpl();
//        Comunidad com = com_dao.buscar(5);
//        
//        Publicacion publi = new Publicacion();
//        publi.setAutor(user);
//        publi.setComunidad(com);
//        publi.setTitulo("HolaRinconeros y tortolitos");
//        publi.setDescripcion("saludo");
//        
//        PublicacionDAO publi_dao = new PublicacionDAOimpl();
//        publi_dao.insertar(publi);
        
        //Types.BOOLEAN exito;
        
//        PublicacionDAO publi_dao = new PublicacionDAOimpl();
//        boolean exito = publi_dao.eliminar(1);
//        System.out.println(exito);
        
//        PublicacionDAO publi_dao = new PublicacionDAOimpl();
//        Publicacion publi_buscado = publi_dao.buscar(1);
//        System.out.println(publi_buscado.getTitulo());
//        System.out.println(publi_buscado.getDescripcion());
//        Usuario nuevo_usuario = new Usuario();
//        nuevo_usuario.setNombre("Kratos");
//        nuevo_usuario.setDescripcion("pruebaBD");
//        nuevo_usuario.setEmail("kratos@yahoo.com");
//        nuevo_usuario.setContrasenha("patito123");
//        nuevo_usuario.setKarma(25);
//        nuevo_usuario.setEstadouser('A');
//        nuevo_usuario.setTipousuario('C');
//        
        
//        int id = u_test.insertar(nuevo_usuario);
        //u_test.listar();
//        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
//        
//        usuarios = (ArrayList<Usuario>)u_test.listar();
//        for(int i=0;i<usuarios.size();i++){
//            Usuario user = usuarios.get(i);
//            System.out.println(user.getNombre());
//            System.out.println(user.getDescripcion());
//            System.out.println(user.getEmail());
//            System.out.println(String.valueOf(user.getFechaRegistro()));
//            System.out.println(user.getEstadouser());
//        }
        //Usuario_comun ucomunBuscado = u_comun_test.buscar(4);
//        UsuarioDAO u_test = new UsuarioDAOimpl();
//        Usuario uBuscado = u_test.buscar(6);
//        System.out.println(uBuscado.getNombre());
//        System.out.println(uBuscado.getEmail());
        
        UsuarioDAO u_dao = new UsuarioDAOimpl();
        Usuario uBuscado = u_dao.buscar(6);
        VotoDAO voto_dao = new VotoDAOimpl();
        Voto votoBuscado = voto_dao.buscar(3);
        voto_dao.modificar(votoBuscado);
        
        votoBuscado = voto_dao.buscar(3);
        System.out.println(votoBuscado.getId());
        System.out.println(String.valueOf(votoBuscado.getTipo()));
//        Voto nuevo_voto = new Voto();
//        nuevo_voto.setUsuario(uBuscado);
//        nuevo_voto.setTipo('D');
       // voto_dao.insertar(nuevo_voto);
        
        //int id = voto_dao.insertar(nuevo_voto);
        
//        boolean exito1 = voto_dao.eliminar(1);
//        boolean exito2 = voto_dao.eliminar(2);
//        System.out.println(exito1);
//        System.out.println(exito2);
//        System.out.println(votoBuscado.getId());
//        System.out.println(String.valueOf(votoBuscado.getTipo()));
        
        
//        ArrayList<Voto> votos = new ArrayList<Voto>();
//        votos = (ArrayList<Voto>)voto_dao.listar();
//        for (Voto voto : votos) {
//            System.out.println(voto.getId());
//            System.out.println(String.valueOf(voto.getTipo()));
//        }
        
//        Voto nuevo_voto = new Voto();
//        nuevo_voto.setUsuario(uBuscado);
//        nuevo_voto.setTipo('U');
//        voto_dao.insertar(nuevo_voto);
//        List<Voto> votos = voto_dao.listar();
//        assertNotNull(votos);
//        System.out.println(v.getId());
//        System.out.println(String.valueOf(v.getTipo()));
//        
//        for(int i=0;i<votos.size();i++){
//            Voto v = votos.get(i);
//            System.out.println(v.getId());
//            System.out.println(String.valueOf(v.getTipo()));
//        }
        
//        PublicacionDAO publi_dao = new PublicacionDAOimpl();
//        Publicacion publi_buscado = publi_dao.buscar(1);
//        System.out.println(publi_buscado.getTitulo());
//        System.out.println(publi_buscado.getDescripcion());
//        
//        publi_buscado.setDescripcion("Modificada");
//        
//        publi_dao.modificar(publi_buscado);
//        
//        Publicacion publi_modificado = publi_dao.buscar(1);
//        System.out.println(publi_modificado.getTitulo());
//        System.out.println(publi_modificado.getDescripcion());
//        
//        Usuario_comun ucomun = new Usuario_comun();
//        ucomun.setIdUsuario(4);
//        ucomun.setCodigopucp("30002011");
        
        
//        Usuario_comunDAO u_comun_test = new Usuario_comunDAOimpl();
//        
//        u_comun_test.insertar(ucomun);
        
//        Usuario_comunDAO u_comun_test = new Usuario_comunDAOimpl();
//        //u_comun_test.eliminar(4);
//        
//        UsuarioDAO u_test = new UsuarioDAOimpl();
//        Usuario_comun ucomunBuscado = u_comun_test.buscar(4);
//        
//        Usuario uBuscado = u_test.buscar(ucomunBuscado.getIdUsuario());
//        System.out.println(uBuscado.getNombre());
//        System.out.println(ucomunBuscado.getCodigopucp());
//        
//        Comunidad modelo = new Comunidad();
//        modelo.setNombre("ElRinconDeEdynson");
//        modelo.setDescripcion("pruebaDBComunidad");
//        modelo.setAdministrador(ucomunBuscado);
        
//        ComunidadDAO comunidadDao = new ComunidadDAOimpl();
//        //int id = comunidadDao.insertar(modelo);
//        
//        comunidadDao.listar();
//        ArrayList<Comunidad> comunidades = new ArrayList<Comunidad>();
//        
//        comunidades = (ArrayList<Comunidad>)comunidadDao.listar();
//        for(int i=0;i<comunidades.size();i++){
//            Comunidad com = comunidades.get(i);
//            System.out.println(com.getId_comunidad());
//            System.out.println(com.getNombre());
//            System.out.println(com.getDescripcion());
//        }
//        modelo = comunidadDao.buscar(id);
//        modelo.setId_comunidad(id);
//        
//        System.out.println(modelo.getNombre());
//        System.out.println(modelo.getDescripcion());
//        System.out.println(modelo.getId_comunidad());
        
//        UsuarioDAO u_test = new UsuarioDAOimpl();
//        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
//        
//        usuarios = (ArrayList<Usuario>)u_test.listar();
//        for(int i=0;i<usuarios.size();i++){
//            Usuario user = usuarios.get(i);
//            System.out.println(user.getNombre());
//            System.out.println(user.getDescripcion());
//            System.out.println(user.getEmail());
//            System.out.println(String.valueOf(user.getFechaRegistro()));
//            System.out.println(user.getEstadouser());
//        }
        
//        UsuarioDAO u_test = new UsuarioDAOimpl();
//        Usuario u1 = u_test.buscar(4);
//        
//        System.out.println(u1.getNombre());
//        System.out.println(u1.getDescripcion());
//        System.out.println(u1.getEmail());
//        System.out.println(u1.getFechaRegistro());
//        System.out.println(u1.getEstadouser()); // E: Eliminado
        
        //u1.setDescripcion("prueba_eliminacion");
        //u_test.eliminar(u1.getIdUsuario());
        
//        u1 = u_test.buscar(4);
//        
//        System.out.println(u1.getNombre());
//        System.out.println(u1.getDescripcion());
//        System.out.println(u1.getEmail());
//        System.out.println(u1.getFechaRegistro());
        
//        private String Nombre;
//        private String Descripcion;
//        private String email;
//        private String contrasenha;
//        private int karma;
//        private char estadouser;
//        private char tipousuario;

//        Usuario modelo = new Usuario();
//        modelo.setNombre("Sepepian");
//        modelo.setDescripcion("pruebaBD");
//        modelo.setEmail("linux@yahoo.com");
//        modelo.setContrasenha("patito123");
//        modelo.setKarma(25);
//        modelo.setEstadouser('A');
//        modelo.setTipousuario('B');
//        
//        UsuarioDAO usuarioDao = new UsuarioDAOimpl();
//        int id = usuarioDao.insertar(modelo);
//        modelo.setIdUsuario(id);
//        
//        System.out.println(modelo);
        
        
    }
}
