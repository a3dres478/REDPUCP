/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.modelo.voto;

import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Comentario;

/**
 *
 * @author andre
 */
public class VotoComentario extends Voto{
    private Comentario comentarioVotado;

    public Comentario getComentarioVotado() {
        return comentarioVotado;
    }

    public void setComentarioVotado(Comentario comentarioVotado) {
        this.comentarioVotado = comentarioVotado;
    }
}
