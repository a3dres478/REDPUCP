/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.bo;

import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Usuario_comun;

/**
 *
 * @author invitado123
 */
public interface IUsuario_comunBO extends IBaseBO<Usuario_comun> {
    Usuario_comun obtenerUsuarioComunXCorreo(String correo);
    boolean verificarLogin(String email, String password);
    void actualizarKarma(int idUsuario, int aumento);
}
