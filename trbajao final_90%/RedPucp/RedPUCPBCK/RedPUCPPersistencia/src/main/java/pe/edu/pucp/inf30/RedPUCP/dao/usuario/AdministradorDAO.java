/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.dao.usuario;

//import pe.edu.pucp.inf30.RedPUCP.dao.ICrud;
import pe.edu.pucp.inf30.RedPUCP.dao.PersistibleTransaccional;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Comentario;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Administrador;
/**
 *
 * @author andre
 */
public interface AdministradorDAO extends PersistibleTransaccional<Administrador,Integer> {
    boolean verificarLoginAdministrador(String email, String password);
    public Administrador obtenerAdministradorXCorreo(String correo);
}
