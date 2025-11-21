/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.bo;

import java.util.List;

/**
 *
 * @author andre
 * @param <T>
 */
public interface IBaseBO<T> {
    List<T>listar();
    T obtener (int id);
    void eliminar (int id);
    void guardar (T modelo, Estado estado);
    
}
