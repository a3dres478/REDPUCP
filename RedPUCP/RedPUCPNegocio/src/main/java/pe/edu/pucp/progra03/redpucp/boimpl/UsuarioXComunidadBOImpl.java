/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.boimpl;

import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.dao.usuario.UsuarioXComunidadDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.UsuarioXComunidadDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.UsuarioxComunidad;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IUsuarioXComunidadBO;

/**
 *
 * @author invitado123
 */
public class UsuarioXComunidadBOImpl implements IUsuarioXComunidadBO {
    private final UsuarioXComunidadDAO usuXcomDAO;

    public UsuarioXComunidadBOImpl() {
        this.usuXcomDAO = new UsuarioXComunidadDAOimpl();
    }
    
    @Override 
    public List<UsuarioxComunidad> listar(){
        return this.usuXcomDAO.leerTodos();
    }
    
    @Override 
    public UsuarioxComunidad obtener(int id){
        return this.usuXcomDAO.leer(id);
    }
    
    @Override 
    public void eliminar(int id){
        this.usuXcomDAO.eliminar(id);
    }
    
    @Override 
    public void guardar(UsuarioxComunidad usuXcom,Estado estado){
        if(estado==Estado.Nuevo){
            this.usuXcomDAO.crear(usuXcom);
        }else{
            this.usuXcomDAO.actualizar(usuXcom);
        }
    }
}
