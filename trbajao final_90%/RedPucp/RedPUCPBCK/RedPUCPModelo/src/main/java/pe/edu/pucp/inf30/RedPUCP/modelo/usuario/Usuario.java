/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.modelo.usuario;

import java.util.Date;
/**
 *
 * @author andre
 */
public class Usuario {

    public char getEstadouser() {
        return estadouser;
    }

    public void setEstadouser(char estadouser) {
        this.estadouser = estadouser;
    }

    public String getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(String tipousuario) {
        this.tipousuario = tipousuario;
    }

    
    private int idUsuario;
    private String nombre;
    private String descripcion;
    private String email;
    private String contrasenha;
    private int karma;
    private Date fechaRegistro;
    private char estadouser;
    private String tipousuario;
    
    public Usuario(){
        
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    
    
}
