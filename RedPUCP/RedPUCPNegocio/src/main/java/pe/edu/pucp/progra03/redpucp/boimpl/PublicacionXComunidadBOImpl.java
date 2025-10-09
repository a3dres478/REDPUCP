/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.boimpl;

import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.dao.Publicacion.PublicacionxComunidadDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Publicacion.PublicacionxComunidadDAOImpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.PublicacionxComunidad;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IPublicacionXComunidadBO;

/**
 *
 * @author invitado123
 */
public class PublicacionXComunidadBOImpl implements IPublicacionXComunidadBO{
    private final PublicacionxComunidadDAO publiXcom_DAO;

    public PublicacionXComunidadBOImpl() {
        this.publiXcom_DAO = new PublicacionxComunidadDAOImpl();
    }
    
    @Override 
    public List<PublicacionxComunidad> listar(){
        return this.publiXcom_DAO.leerTodos();
    }
    
    @Override 
    public PublicacionxComunidad obtener(int id){
        return this.publiXcom_DAO.leer(id);
    }
    
    @Override 
    public void eliminar(int id){
        this.publiXcom_DAO.eliminar(id);
    }
    
    @Override
    public void guardar(PublicacionxComunidad publiXcom,Estado estado){
        if(estado==Estado.Nuevo){
            this.publiXcom_DAO.crear(publiXcom);
        }else{
            this.publiXcom_DAO.actualizar(publiXcom);
        }
    }
    
}
