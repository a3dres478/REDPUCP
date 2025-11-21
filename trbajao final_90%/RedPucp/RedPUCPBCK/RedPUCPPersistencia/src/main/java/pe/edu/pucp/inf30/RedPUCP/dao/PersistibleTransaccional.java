/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.dao;

import java.sql.Connection;

/**
 *
 * @author invitado123
 */
public interface PersistibleTransaccional<T, I> extends Persistible<T, I> {
    I crear(T modelo, Connection conexion);
    boolean actualizar(T modelo, Connection conexion);
    boolean eliminar(I id, Connection conexion);
}
