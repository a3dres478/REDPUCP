/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.boimpl;

import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Comunidad.ComunidadXPublicacionPrincipalDAOImpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.ComunidadxPublicacionPrincipal;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IComunidadXPublicacionPrincipalBO;

/**
 *
 * @author HECTOR
 */
public class ComunidadXPublicacionPrincipalBOImpl implements IComunidadXPublicacionPrincipalBO{
    private final ComunidadXPublicacionPrincipalDAOImpl comunidadXpublicacion_principalDAO;
    
    public ComunidadXPublicacionPrincipalBOImpl(){
        comunidadXpublicacion_principalDAO = new ComunidadXPublicacionPrincipalDAOImpl();
    }
    
    @Override
    public List<ComunidadxPublicacionPrincipal> listar(){
        return this.comunidadXpublicacion_principalDAO.leerTodos();
    }

    @Override
    public ComunidadxPublicacionPrincipal obtener(int id){
        return this.comunidadXpublicacion_principalDAO.leer(id);
    }

    @Override 
    public void eliminar(int id){
        this.comunidadXpublicacion_principalDAO.eliminar(id);
    }
    
    @Override 
    public void guardar(ComunidadxPublicacionPrincipal comXpubli_princ,Estado estado){
        if(estado==Estado.Nuevo){
            int idGenerado = this.comunidadXpublicacion_principalDAO.crear(comXpubli_princ);
            comXpubli_princ.setIdComunidadxPublicacionPrincipal(idGenerado);
        }else{
            this.comunidadXpublicacion_principalDAO.actualizar(comXpubli_princ);
        }
    }

}
