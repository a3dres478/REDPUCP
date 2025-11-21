/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.dao.usuario;

//import pe.edu.pucp.inf30.RedPUCP.dao.ICrud;
import pe.edu.pucp.inf30.RedPUCP.dao.PersistibleTransaccional;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Comentario;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;
/**
 *
 * @author andre
 */
public interface Usuario_comunDAO extends PersistibleTransaccional<Usuario_comun,Integer>{
    boolean verificarLogin(String email, String password);
    public Usuario_comun obtenerUsuarioComunXCorreo(String correo);
    void actualizarKarma(int idUsuario, int aumento);
}
