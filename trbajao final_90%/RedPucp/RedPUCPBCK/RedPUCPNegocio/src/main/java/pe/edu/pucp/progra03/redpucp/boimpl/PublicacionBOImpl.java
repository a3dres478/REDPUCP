/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.boimpl;

import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.dao.Publicacion.PublicacionDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.Publicacion.PublicacionDAOimpl;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.CategoriaPublicacion;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.OrdenamientoPublicacion;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Publicacion;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
import pe.edu.pucp.progra03.redpucp.bo.IPublicacionBO;

/**
 *
 * @author invitado123
 */
public class PublicacionBOImpl implements IPublicacionBO{
    private final PublicacionDAO publicacionDAO;

    public PublicacionBOImpl() {
        this.publicacionDAO = new PublicacionDAOimpl();
    }
    
    @Override 
    public List<Publicacion> listar(){
        return this.publicacionDAO.leerTodos();
    }
    
    @Override 
    public Publicacion obtener(int id){
        return this.publicacionDAO.leer(id);
    }
    
    @Override 
    public void eliminar(int id){
        this.publicacionDAO.eliminar(id);
    }
    
    @Override 
    public void guardar(Publicacion publicacion,Estado estado){
        if(publicacion.getCategoria()==null){
            publicacion.setCategoria(String.valueOf(CategoriaPublicacion.Sin_categoria));
        }
        
        if(estado==Estado.Nuevo){
            int id=this.publicacionDAO.crear(publicacion);
            publicacion.setId(id);
        }else{
            this.publicacionDAO.actualizar(publicacion);
        }
    }
    
    @Override
    public List<Publicacion> listarPublicacionesXFiltros(int idComunidad,String categoria,String ordenamiento){
        if(categoria == null || categoria.trim().isEmpty()) {
            categoria = String.valueOf(CategoriaPublicacion.Sin_categoria);
        }

        if(ordenamiento == null || ordenamiento.trim().isEmpty()) {
            ordenamiento = String.valueOf(OrdenamientoPublicacion.Fecha_reciente);
        }
        return this.publicacionDAO.listarPublicacionesXFiltros(idComunidad,categoria, ordenamiento);
    }
    
    @Override 
    public List<Publicacion> listarPublicacionesXUsuario(int idUsuario){
        return this.publicacionDAO.listarPublicacionesXUsuario(idUsuario);
    }
    
    @Override 
    public List<Publicacion> listarPublicacionesVirales(){
        return this.publicacionDAO.listarPublicacionesVirales();
    }
    
    @Override
    public void actualizarImagen(int idPublicacion, String imagenUrl) {
        this.publicacionDAO.actualizarImagen(idPublicacion, imagenUrl);
    }
}
