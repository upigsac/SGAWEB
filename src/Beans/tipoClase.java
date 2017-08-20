/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.tipoClaseDAO;
import ENTIDAD.tipoClaseC;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Administrador
 */
@ManagedBean(name="tipoClaseB")
@ViewScoped
public class tipoClase {
    private tipoClaseC tipoClase;
    
    
    tipoClaseDAO tipoClaseD;
    public  tipoClaseC mostrarTipoClase(int tipoClase){
        tipoClaseD=new tipoClaseDAO();
        this.tipoClase=tipoClaseD.mostrarTipoClase(tipoClase);
        return this.tipoClase;
    }

    /**
     * @return the tipoClase
     */
    public tipoClaseC getTipoClase() {
        return tipoClase;
    }

    /**
     * @param tipoClase the tipoClase to set
     */
    public void setTipoClase(tipoClaseC tipoClase) {
        this.tipoClase = tipoClase;
    }
}
