/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.modelo.voto;

import java.util.Date;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario;

/**
 *
 * @author invitado123
 */
public class Voto {

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
    private int idVoto;
    private char tipo;
    private Date fechaRegistro;
    private Usuario usuario;

    public int getId() {
        return idVoto;
    }

    public void setId(int id) {
        this.idVoto = id;
    }

    

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
