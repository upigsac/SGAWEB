/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.periocidadDAO;
import ENTIDAD.periocidadC;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="periocidadB")
@ViewScoped
public class periocidad implements Serializable{
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private List<periocidadC> periocidadL;
    
    
    periocidadDAO periodoContratoD;
    
    public List<periocidadC> mostrarPeriocidad(){
        periodoContratoD=new periocidadDAO();
        periocidadL=periodoContratoD.mostrarPeriocidad();
        return periocidadL;
    }
    public List<periocidadC> getPeriocidadL() {
        return periocidadL;
    }
    public void setPeriocidadL(List<periocidadC> periocidadL) {
        this.periocidadL = periocidadL;
    }
    
    


}
