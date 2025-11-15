/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.boimpl;

import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.dao.voto.VotoDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.voto.VotoDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.voto.Voto;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IVotoBO;

/**
 *
 * @author invitado123
 */
public class VotoBOImpl implements IVotoBO {
    private final VotoDAO votoDAO;

    public VotoBOImpl() {
        this.votoDAO = new VotoDAOimpl();
    }
    
    @Override 
    public List<Voto> listar(){
        return this.votoDAO.leerTodos();
    }
    
    @Override 
    public Voto obtener(int id){
        return this.votoDAO.leer(id);
    }
    
    @Override 
    public void eliminar(int id){
        this.votoDAO.eliminar(id);
    }
    
    @Override 
    public void guardar(Voto voto,Estado estado){
        if(estado==Estado.Nuevo){
            int id=this.votoDAO.crear(voto);
            voto.setId(id);
        }else{
            this.votoDAO.actualizar(voto);
        }
    }
}
