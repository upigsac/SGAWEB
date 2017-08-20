

package Beans;

import DAO.tipoMonedaDAO;
import ENTIDAD.tipoMonedaC;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="tipoMonedaB")
@ViewScoped
public class tipoMoneda implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	private List<tipoMonedaC> tipoMonedaL;

    
    
    tipoMonedaDAO tipoMonedaD;
    
    public List<tipoMonedaC> mostrarTipoMoneda(){
        tipoMonedaD=new tipoMonedaDAO();
        tipoMonedaL=tipoMonedaD.mostrarTipoMoneda();
        
        
        return tipoMonedaL;
        
    }
    public List<tipoMonedaC> getTipoMonedaL() {
        return tipoMonedaL;
    }
    public void setTipoMonedaL(List<tipoMonedaC> tipoMonedaL) {
        this.tipoMonedaL = tipoMonedaL;
    }
  
}
