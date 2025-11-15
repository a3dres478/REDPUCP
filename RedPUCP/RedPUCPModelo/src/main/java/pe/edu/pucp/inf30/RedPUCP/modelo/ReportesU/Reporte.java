/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.modelo.ReportesU;

import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Comentario;
import pe.edu.pucp.inf30.RedPUCP.modelo.Publicacion.Publicacion;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;

public class Reporte {

    private int id_report;
    private String tipo;
    private Publicacion publicacionreportada;
    private Comentario comentarioreportado;
    private String detalle;
    private Usuario_comun publicador; // El dueño del contenido reportado
    private Usuario_comun reportador; // Quien hace el reporte
    private char estado;

    // Constructor vacío
    public Reporte() {
    }

    // Getters y Setters
    public int getId_report() {
        return id_report;
    }

    public void setId_report(int id_report) {
        this.id_report = id_report;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Publicacion getPublicacionreportada() {
        return publicacionreportada;
    }

    public void setPublicacionreportada(Publicacion publicacionreportada) {
        this.publicacionreportada = publicacionreportada;
    }

    public Comentario getComentarioreportado() {
        return comentarioreportado;
    }

    public void setComentarioreportado(Comentario comentarioreportado) {
        this.comentarioreportado = comentarioreportado;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Usuario_comun getPublicador() {
        return publicador;
    }

    public void setPublicador(Usuario_comun publicador) {
        this.publicador = publicador;
    }

    public Usuario_comun getReportador() {
        return reportador;
    }

    public void setReportador(Usuario_comun reportador) {
        this.reportador = reportador;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }
}
