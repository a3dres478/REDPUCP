/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.boimpl;

import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.dao.voto.VotoPublicacionDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.voto.VotoPublicacionDAOImpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.voto.VotoPublicacion;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IVotoPublicacionBO;

/**
 *
 * @author invitado123
 */
public class VotoPublicacionBOImpl implements IVotoPublicacionBO {
    private final VotoPublicacionDAO votoPublicacionDAO;

    public VotoPublicacionBOImpl() {
        this.votoPublicacionDAO = new VotoPublicacionDAOImpl();
    }
    
    @Override 
    public List<VotoPublicacion> listar(){
        return this.votoPublicacionDAO.leerTodos();
    }
    
    @Override 
    public VotoPublicacion obtener(int id){
        return this.votoPublicacionDAO.leer(id);
    }
    
    @Override 
    public void eliminar(int id){
        this.votoPublicacionDAO.eliminar(id);
    }
    
    @Override 
    public void guardar(VotoPublicacion votoPublicacion,Estado estado){
        if(estado==Estado.Nuevo){
            int id=this.votoPublicacionDAO.crear(votoPublicacion);
            votoPublicacion.setId(id);
        }else{
            this.votoPublicacionDAO.actualizar(votoPublicacion);
        }
    }
    
    @Override
    public List<VotoPublicacion> listarVotosXPublicacion(int idPublicacion){
        return this.votoPublicacionDAO.listarVotosXPublicacion(idPublicacion);
    }    
}
