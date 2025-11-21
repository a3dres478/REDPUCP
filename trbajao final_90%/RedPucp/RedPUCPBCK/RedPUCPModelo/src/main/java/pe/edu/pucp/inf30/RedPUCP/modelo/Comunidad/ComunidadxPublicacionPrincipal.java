/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad;

import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Publicacion;

/**
 *
 * @author andre
 */
public class ComunidadxPublicacionPrincipal {

    public int getIdComunidadxPublicacionPrincipal() {
        return idComunidadxPublicacionPrincipal;
    }

    public void setIdComunidadxPublicacionPrincipal(int idComunidadxPublicacionPrincipal) {
        this.idComunidadxPublicacionPrincipal = idComunidadxPublicacionPrincipal;
    }

    public Comunidad getComuni() {
        return comuni;
    }

    public void setComuni(Comunidad comuni) {
        this.comuni = comuni;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }
    
    private int idComunidadxPublicacionPrincipal;
    private Comunidad comuni;
    private Publicacion publicacion;
    
    
    
}
