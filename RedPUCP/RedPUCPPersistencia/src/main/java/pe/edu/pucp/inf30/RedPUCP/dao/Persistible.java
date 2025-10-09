/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.dao;

import java.util.List;

/**
 *
 * @author invitado123
 */
public interface Persistible<T, I> {
    I crear(T modelo);
    boolean actualizar(T modelo);
    boolean eliminar(I id);
    T leer(I id);
    List<T> leerTodos();
}

