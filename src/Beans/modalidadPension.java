
package Beans;

import DAO.modalidadPensionDAO;
import ENTIDAD.modalidadPensionC;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="modalidadPensionB")
@ViewScoped
public class modalidadPension implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<modalidadPensionC> modalidadPensionL;
    
    modalidadPensionDAO modalidadPensionD;
    
    public List<modalidadPensionC> mostrarModalidadPension(){
        modalidadPensionD=new modalidadPensionDAO();
        modalidadPensionL=modalidadPensionD.mostrarModalidadIngreso();
        return modalidadPensionL;
    }
    public List<modalidadPensionC> getModalidadPensionL() {
        return modalidadPensionL;
    }
    public void setModalidadPensionL(List<modalidadPensionC> modalidadPensionL) {
        this.modalidadPensionL = modalidadPensionL;
    }
    

    
}
