/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.bo;

import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Administrador;

/**
 *
 * @author invitado123
 */
public interface IAdministradorBO extends IBaseBO<Administrador> {
    Administrador obtenerAdminXCorreo(String correo);
    boolean verificarLoginAdmin(String email, String password);
}
