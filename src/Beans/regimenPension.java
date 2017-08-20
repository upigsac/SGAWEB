

package Beans;

import DAO.regimenPensionDAO;
import ENTIDAD.regimenPensionC;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="regimenPension")
@ViewScoped
public class regimenPension implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private List<regimenPensionC> regimenPensionL;
    
    
    regimenPensionDAO regimenPensionD;
    
    public List<regimenPensionC> mostrarRegimenPension(){
        regimenPensionD=new regimenPensionDAO();
        regimenPensionL=regimenPensionD.mostrarRegimenPension();
        
        return regimenPensionL;
    }
    public List<regimenPensionC> getRegimenPensionL() {
        return regimenPensionL;
    }
    public void setRegimenPensionL(List<regimenPensionC> regimenPensionL) {
        this.regimenPensionL = regimenPensionL;
    }

    
}
