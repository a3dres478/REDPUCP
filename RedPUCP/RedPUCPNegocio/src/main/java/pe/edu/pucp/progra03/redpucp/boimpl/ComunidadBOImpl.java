/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.boimpl;

import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.dao.Comunidad.ComunidadDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Comunidad.ComunidadDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.Comunidad;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IComunidadBO;

/**
 *
 * @author invitado123
 */
public class ComunidadBOImpl implements IComunidadBO{
    private final ComunidadDAO comunidadDAO;
    
    public ComunidadBOImpl(){
        this.comunidadDAO = new ComunidadDAOimpl();
    }
    
    @Override
    public List<Comunidad> listar(){
        return this.comunidadDAO.leerTodos();
    }
    
    @Override 
    public Comunidad obtener(int id){
        return this.comunidadDAO.leer(id);
    }
    
    @Override 
    public void eliminar(int id){
        this.comunidadDAO.eliminar(id);
    }
    
    @Override 
    public void guardar(Comunidad comunidad,Estado estado){
        if(estado==Estado.Nuevo){
            this.comunidadDAO.crear(comunidad);
        }else{
            this.comunidadDAO.actualizar(comunidad);
        }
    }
    /*REVISAR LOS FILTROS*/
    @Override
    public List<Comunidad>filtrarcomunidades(String filtro1){
        return this.comunidadDAO.listarcomunidadfiltros(filtro1);
    }
}
