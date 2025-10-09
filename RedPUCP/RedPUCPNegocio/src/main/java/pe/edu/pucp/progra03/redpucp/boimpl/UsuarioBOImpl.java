/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.boimpl;

import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.dao.usuario.UsuarioDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.UsuarioDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IUsuarioBO;

/**
 *
 * @author invitado123
 */
public class UsuarioBOImpl implements IUsuarioBO{
    private final UsuarioDAO usuarioDAO;
    
    public UsuarioBOImpl(){
        usuarioDAO = new UsuarioDAOimpl();
    }
    
    @Override 
    public List<Usuario> listar(){
        return this.usuarioDAO.leerTodos();
    }
    
    @Override 
    public Usuario obtener(int id){
        return this.usuarioDAO.leer(id);
    }
    
    @Override 
    public void eliminar(int id){
        this.usuarioDAO.eliminar(id);
    }
    
    @Override 
    public void guardar(Usuario usu,Estado estado){
        if(estado==Estado.Nuevo){
            this.usuarioDAO.crear(usu);
        }else{
            this.usuarioDAO.actualizar(usu);
        }
    }
}
