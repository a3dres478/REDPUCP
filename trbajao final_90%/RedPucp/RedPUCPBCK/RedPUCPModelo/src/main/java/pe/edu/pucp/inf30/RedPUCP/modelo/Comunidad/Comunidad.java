/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.modelo.Comunidad;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;

/**
 *
 * @author andre
 */
public class Comunidad {

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }
    private int id_comunidad;
    private String nombre;
    private String descripcion;
    private Date fecha_creacion;
    private int cantidadmiembros;
    private Usuario_comun administrador;
    private char estado;
    
    public Comunidad(){
        
    }
    
    public int getId_comunidad() {
        return id_comunidad;
    }

    public void setId_comunidad(int id_comunidad) {
        this.id_comunidad = id_comunidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public int getCantidadmiembros() {
        return cantidadmiembros;
    }

    public void setCantidadmiembros(int cantidadmiembros) {
        this.cantidadmiembros = cantidadmiembros;
    }

    public Usuario_comun getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Usuario_comun administrador) {
        this.administrador = administrador;
    }

    
   
    
}
