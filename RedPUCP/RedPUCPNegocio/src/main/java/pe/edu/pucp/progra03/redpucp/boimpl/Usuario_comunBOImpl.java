/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.boimpl;

import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.dao.usuario.Usuario_comunDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.Usuario_comunDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IUsuario_comunBO;

/**
 *
 * @author invitado123
 */
public class Usuario_comunBOImpl implements IUsuario_comunBO {
    private final Usuario_comunDAO usuario_comunDAO;

    public Usuario_comunBOImpl() {
        this.usuario_comunDAO = new Usuario_comunDAOimpl();
    }
    
    @Override 
    public List<Usuario_comun> listar(){
        return this.usuario_comunDAO.leerTodos();
    }
    
    @Override 
    public Usuario_comun obtener(int id){
        return this.usuario_comunDAO.leer(id);
    }
    
    @Override 
    public void eliminar(int id){
        this.usuario_comunDAO.eliminar(id);
    }
    
    @Override 
    public void guardar(Usuario_comun usuario_comun,Estado estado){
        if(estado==Estado.Nuevo){
            this.usuario_comunDAO.crear(usuario_comun);
        }else{
            this.usuario_comunDAO.actualizar(usuario_comun);
        }
    }
}
