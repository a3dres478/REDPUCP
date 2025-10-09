/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.inf30.RedPUCP.test.dao;

/**
 *
 * @author invitado123
 */
public interface PersistibleProbable {
    void debeCrear();
    void debeActualizarSiIdExiste();
    void noDebeActualizarSiIdNoExiste();
    void noDebeEliminarSiIdNoExiste();
    void debeLeerSiIdExiste();
    void noDebeLeerSiIdNoExiste();
    void debeLeerTodos();
    void debeEliminarSiIdExiste();
}