/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.modelo.voto;

import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Publicacion;

/**
 *
 * @author andre
 */
public class VotoPublicacion extends Voto {
    public Publicacion getPublicacionVotada() {
        return publicacionVotada;
    }

    public void setPublicacionVotada(Publicacion publicacionVotada) {
        this.publicacionVotada = publicacionVotada;
    }
    private Publicacion publicacionVotada;
}
