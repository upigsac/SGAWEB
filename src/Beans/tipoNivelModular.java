/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.tipoNivelModularDAO;
import ENTIDAD.tipoNivelModularC;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Administrador
 */
@ManagedBean(name="tipoNivelModularB")
@ViewScoped
public class tipoNivelModular {
    private tipoNivelModularC  tipoNivelModular;
    private List<tipoNivelModularC>  tipoNivelModularL;
    
    
    tipoNivelModularDAO tipoNivelModularD;
    public List<tipoNivelModularC> mostrarTipoNivelModular(){
        tipoNivelModularD=new tipoNivelModularDAO();
        tipoNivelModularL=tipoNivelModularD.mostrarTipoNivelModular();
        return tipoNivelModularL;
    }
    
    public tipoNivelModularC mostrarTipoNivelModular(int tipoNivelModular){
        tipoNivelModularD=new tipoNivelModularDAO();
        this.tipoNivelModular=tipoNivelModularD.mostrarTipoNivelModular(tipoNivelModular);
        return this.tipoNivelModular;
    }

    /**
     * @return the tipoNivelModular
     */
    public tipoNivelModularC getTipoNivelModular() {
        return tipoNivelModular;
    }

    /**
     * @param tipoNivelModular the tipoNivelModular to set
     */
    public void setTipoNivelModular(tipoNivelModularC tipoNivelModular) {
        this.tipoNivelModular = tipoNivelModular;
    }

    /**
     * @return the tipoNivelModularL
     */
    public List<tipoNivelModularC> getTipoNivelModularL() {
        return tipoNivelModularL;
    }

    /**
     * @param tipoNivelModularL the tipoNivelModularL to set
     */
    public void setTipoNivelModularL(List<tipoNivelModularC> tipoNivelModularL) {
        this.tipoNivelModularL = tipoNivelModularL;
    }
    
}
