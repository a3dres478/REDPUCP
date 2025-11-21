/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.modelo.usuario;

/**
 *
 * @author andre
 */
public class Usuario_comun extends Usuario{
    private String codigopucp;
    //private tipouser tipousuario;
    public Usuario_comun(){
         this.setTipousuario("C");
        this.setEstadouser('A');
    }
    
    public String getCodigopucp() {
        return codigopucp;
    }

    public void setCodigopucp(String codigopucp) {
        this.codigopucp = codigopucp;
    }
    
}
