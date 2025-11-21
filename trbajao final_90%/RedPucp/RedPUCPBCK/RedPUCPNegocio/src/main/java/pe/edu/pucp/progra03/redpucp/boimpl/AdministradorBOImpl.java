/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.progra03.redpucp.boimpl;

import java.util.List;
import pe.edu.pucp.inf30.RedPUCP.dao.usuario.AdministradorDAO;
import pe.edu.pucp.inf30.RedPUCP.daoimpl.usuario.AdministradorDAOimpl;
import pe.edu.pucp.progra03.redpucp.bo.IAdministradorBO;
import pe.edu.pucp.inf30.RedPUCP.modelo.usuario.Administrador;
import pe.edu.pucp.progra03.redpucp.bo.Estado;
/**
 *
 * @author invitado123
 */
public class AdministradorBOImpl implements IAdministradorBO {
    private final AdministradorDAO administradorDAO;
    
    public AdministradorBOImpl(){
        this.administradorDAO = new AdministradorDAOimpl();
    }
    
    @Override
    public List<Administrador> listar(){
        return this.administradorDAO.leerTodos();
    }
    
    @Override 
    public Administrador obtener(int id){
        return this.administradorDAO.leer(id);
    }
    
    @Override
    public void eliminar(int id){
        this.administradorDAO.eliminar(id);
    }
    
    @Override
    public void guardar(Administrador admin,Estado estado){
        if(estado==Estado.Nuevo){
            int id=this.administradorDAO.crear(admin);
            admin.setIdUsuario(id);
        }else{
            this.administradorDAO.actualizar(admin);
        }
    }
    
    @Override
    public Administrador obtenerAdminXCorreo(String correo){
        return this.administradorDAO.obtenerAdministradorXCorreo(correo);
    }
    @Override
    public boolean verificarLoginAdmin(String email, String password){
        return this.administradorDAO.verificarLoginAdministrador(email, password);
    }
}
