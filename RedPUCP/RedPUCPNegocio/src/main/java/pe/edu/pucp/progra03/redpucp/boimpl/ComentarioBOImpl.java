/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.boimpl;

import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.dao.Publicacion.ComentarioDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Publicacion.ComentarioDAOImpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Comentario;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IComentarioBO;

/**
 *
 * @author invitado123
 */
public class ComentarioBOImpl implements IComentarioBO{
    private final ComentarioDAO comentarioDAO;
    
    public ComentarioBOImpl(){
        comentarioDAO = new ComentarioDAOImpl();
    }
    
    @Override
    public List<Comentario> listar(){
        return this.comentarioDAO.leerTodos();
    }
    
    @Override 
    public Comentario obtener(int id){
        return this.comentarioDAO.leer(id);
    }
    
    @Override
    public void eliminar(int id){
        this.comentarioDAO.eliminar(id);
    }
    
    @Override
    public void guardar(Comentario comentario,Estado estado){
        if(estado==Estado.Nuevo){
            int id=this.comentarioDAO.crear(comentario);
            comentario.setId(id);
        }else{
            this.comentarioDAO.actualizar(comentario);
        }
    }
    
    @Override
    public List<Comentario>listarComentariosXPublicacion(int idPublicacion){
        return this.comentarioDAO.listarComentariosXPublicacion(idPublicacion);
    }
}
