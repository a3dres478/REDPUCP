/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion;

import java.util.Date;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;

/**
 *
 * @author invitado123 main
 */
public class Comentario {

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    private int idComentario;
    private Usuario_comun autor;
    private Publicacion publicacion;
    //private Comentario comentarioPadre;
    private String contenido;
    private Date fechaCreacion;
    private Date ultimaEdicion;
    private int votosPositivos;
    private int votosNegativos;
    private char estado;
    
    public Comentario(){
        
    }
    
    
    public int getId() {
        return idComentario;
    }

    public void setId(int id) {
        this.idComentario = id;
    }

    public Usuario_comun getAutor() {
        return autor;
    }

    public void setAutor(Usuario_comun autor) {
        this.autor = autor;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

//    public Comentario getComentarioPadre() {
//        return comentarioPadre;
//    }
//
//    public void setComentarioPadre(Comentario comentarioPadre) {
//        this.comentarioPadre = comentarioPadre;
//    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getUltimaEdicion() {
        return ultimaEdicion;
    }

    public void setUltimaEdicion(Date ultimaEdicion) {
        this.ultimaEdicion = ultimaEdicion;
    }

    public int getVotosPositivos() {
        return votosPositivos;
    }

    public void setVotosPositivos(int votosPositivos) {
        this.votosPositivos = votosPositivos;
    }

    public int getVotosNegativos() {
        return votosNegativos;
    }

    public void setVotosNegativos(int votosNegativos) {
        this.votosNegativos = votosNegativos;
    }

    
    
    
}
