/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion;

import java.util.Date;
import pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad.Comunidad;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;

/**
 *
 * @author andre
 */
public class Publicacion {

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }
    private int idPublicacion;
    private Usuario_comun autor;
    private Comunidad comunidad;
    private String titulo;
    private String descripcion;
    private Date fechaCreacion;
    private Date ultimaEdicion;
    private int votosPositivos;
    private int votosNegativos;
    private char estado;
    
    public Publicacion(){
        
    }
    
    
    public int getId() {
        return idPublicacion;
    }

    public void setId(int id) {
        this.idPublicacion = id;
    }

    public Usuario_comun getAutor() {
        return autor;
    }

    public void setAutor(Usuario_comun autor) {
        this.autor = autor;
    }

    public Comunidad getComunidad() {
        return comunidad;
    }

    public void setComunidad(Comunidad comunidad) {
        this.comunidad = comunidad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
