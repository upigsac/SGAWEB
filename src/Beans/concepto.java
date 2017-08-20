/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.conceptoDAO;
import ENTIDAD.conceptoC;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Administrador
 */
@ManagedBean(name="conceptoB")
@ViewScoped
public class concepto {
    private List<conceptoC> conceptoL;
    
    
    
    conceptoDAO conceptoD;

    
    
    public List<conceptoC> mostrarConcepto(){
        conceptoD=new conceptoDAO();
        conceptoL=conceptoD.mostrarConcepto();
        return conceptoL;
    }
    /**
     * @return the conceptoL
     */
    public List<conceptoC> getConceptoL() {
        return conceptoL;
    }

    /**
     * @param conceptoL the conceptoL to set
     */
    public void setConceptoL(List<conceptoC> conceptoL) {
        this.conceptoL = conceptoL;
    }
    
}
