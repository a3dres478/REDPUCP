/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion;

import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.Comunidad;

/**
 *
 * @author andre
 */
public class PublicacionxComunidad {

    public Publicacion getPubli() {
        return publi;
    }

    public void setPubli(Publicacion publi) {
        this.publi = publi;
    }

    public Comunidad getComu() {
        return comu;
    }

    public void setComu(Comunidad comu) {
        this.comu = comu;
    }
    private Publicacion publi;
    private Comunidad comu;
    
    
    
}
