/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.productoConceptoDAO;
import ENTIDAD.productoConceptoC;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Administrador
 */

@ManagedBean(name="productoConceptoB")
@ViewScoped
public class productoConcepto {
    private List<productoConceptoC> productoConceptoL;
    
    
    
    productoConceptoDAO productoConceptoD;
     
    public List<productoConceptoC> mostrarProductoConceto(int institucion,String producto){
        productoConceptoD=new productoConceptoDAO();
        productoConceptoL=productoConceptoD.mostrarProductoConcepto(institucion, producto);
        return productoConceptoL;
    }

    /**
     * @return the productoConceptoL
     */
    public List<productoConceptoC> getProductoConceptoL() {
        return productoConceptoL;
    }

    /**
     * @param productoConceptoL the productoConceptoL to set
     */
    public void setProductoConceptoL(List<productoConceptoC> productoConceptoL) {
        this.productoConceptoL = productoConceptoL;
    }

 
}
