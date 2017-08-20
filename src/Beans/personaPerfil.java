/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.personaDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Administrador
 */
@ManagedBean(name="personaPerfilB")
@ViewScoped
public class personaPerfil {
    
    
    
    private boolean bandera;
    
    
    
    
    @ManagedProperty(value ="#{usuarioB}")
    private usuario sesionUsuario;
    
    personaDAO personaD;
       public void editarPersona(){
        bandera=true;
    }
    public void cancelarPersona(){
         bandera=false;
    }
    public void insertarPersona(){
        personaD=new personaDAO();
        personaD.insertar(sesionUsuario.getPersona()); 
        
        bandera=false;
    }
    

    /**
     * @return the bandera
     */
    public boolean isBandera() {
        return bandera;
    }

    /**
     * @param bandera the bandera to set
     */
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    /**
     * @return the sesionUsuario
     */
    public usuario getSesionUsuario() {
        return sesionUsuario;
    }

    /**
     * @param sesionUsuario the sesionUsuario to set
     */
    public void setSesionUsuario(usuario sesionUsuario) {
        this.sesionUsuario = sesionUsuario;
    }
    
}
