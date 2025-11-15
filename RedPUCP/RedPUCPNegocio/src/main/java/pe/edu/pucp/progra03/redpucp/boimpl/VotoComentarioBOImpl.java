/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.boimpl;

import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.dao.voto.VotoComentarioDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.voto.VotoComentarioDAOImpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.voto.VotoComentario;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IVotoComentarioBO;

/**
 *
 * @author invitado123
 */
public class VotoComentarioBOImpl implements IVotoComentarioBO {
    private final VotoComentarioDAO votoComentarioDAO;

    public VotoComentarioBOImpl() {
        this.votoComentarioDAO = new VotoComentarioDAOImpl();
    }
    
    @Override 
    public List<VotoComentario> listar(){
        return this.votoComentarioDAO.leerTodos();
    }
    
    @Override 
    public VotoComentario obtener(int id){
        return this.votoComentarioDAO.leer(id);
    }
    
    @Override 
    public void eliminar(int id){
        this.votoComentarioDAO.eliminar(id);
    }
    
    @Override 
    public void guardar(VotoComentario votoComentario,Estado estado){
        if(estado==Estado.Nuevo){
            int id=this.votoComentarioDAO.crear(votoComentario);
            votoComentario.setId(id);
        }else{
            this.votoComentarioDAO.actualizar(votoComentario);
        }
    }
}
