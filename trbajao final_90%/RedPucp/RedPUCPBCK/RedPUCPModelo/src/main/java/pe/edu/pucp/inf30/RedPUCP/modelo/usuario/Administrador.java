/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.modelo.usuario;

/**
 *
 * @author andre
 */
public class Administrador extends Usuario{
    private String clave_acceso;
    //private tipouser tipousuario;
    //para Sepepian
    public Administrador(){
        this.setEstadouser('A');
        this.setTipousuario("A");
    }
    public String getClave_acceso() {
        return clave_acceso;
    }

    public void setClave_acceso(String clave_acceso) {
        this.clave_acceso = clave_acceso;
    }
    
    
    
    
}
