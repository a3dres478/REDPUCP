    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.modelo.usuario;

import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.Comunidad;

/**
 *
 * @author andre main
 */
public class UsuarioxComunidad {

    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public Comunidad getComu() {
        return comu;
    }

    public void setComu(Comunidad comu) {
        this.comu = comu;
    }

    public int getId_usuarioXcomunidad() {
        return id_usuarioXcomunidad;
    }

    public void setId_usuarioXcomunidad(int id_usuarioXcomunidad) {
        this.id_usuarioXcomunidad = id_usuarioXcomunidad;
    }
    private Usuario usu;
    private Comunidad comu;
    private int id_usuarioXcomunidad;
}
