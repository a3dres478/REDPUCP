    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.modelo.NotificacionesU;

import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Publicacion;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;

public class Notificacion {

    private int id_notip;
    private Publicacion publicacion_origen_notificacion; // Publicación que origina la noti
    private Usuario_comun usuarioNotificado; // El usuario que recibe la noti
    private String tipo; // Ej: "COMENTARIO", "VOTO", "REPORTE"
    private char estado;

    // Constructor vacío
    public Notificacion() {
    }

    // Getters y Setters
    public int getId_notip() {
        return id_notip;
    }

    public void setId_notip(int id_notip) {
        this.id_notip = id_notip;
    }

    public Publicacion getPublicacionnotificada() {
        return publicacion_origen_notificacion;
    }

    public void setPublicacionnotificada(Publicacion publicacionnotificada) {
        this.publicacion_origen_notificacion = publicacionnotificada;
    }

    public Usuario_comun getUsuarioNotificado() {
        return usuarioNotificado;
    }

    public void setUsuarioNotificado(Usuario_comun usuario_notificado) {
        this.usuarioNotificado = usuario_notificado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }
}
