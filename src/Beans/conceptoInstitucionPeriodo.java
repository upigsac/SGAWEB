/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.conceptoInstitucionPeriodoDAO;
import ENTIDAD.conceptoInstitucionPeriodoC;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="conceptoInstitucionPeriodoB")
@ViewScoped
public class conceptoInstitucionPeriodo {
    private conceptoInstitucionPeriodoC conceptoInstitucionPeriodo;
    
    
    
    conceptoInstitucionPeriodoDAO conceptoInstitucionPeriodoD;
    
    public conceptoInstitucionPeriodoC mostrarConceptoInstitucinPeriodo(String concepto){
        
        conceptoInstitucionPeriodoD=new conceptoInstitucionPeriodoDAO();
        //conceptoInstitucionPeriodo=conceptoInstitucionPeriodoD.mostrarConceptoInstitucionPeriodo(concepto);
                
        return conceptoInstitucionPeriodo;
    }
   
    public conceptoInstitucionPeriodoC getConceptoInstitucionPeriodo() {
        return conceptoInstitucionPeriodo;
    }

   
    public void setConceptoInstitucionPeriodo(conceptoInstitucionPeriodoC conceptoInstitucionPeriodo) {
        this.conceptoInstitucionPeriodo = conceptoInstitucionPeriodo;
    }
}
