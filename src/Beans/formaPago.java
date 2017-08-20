
package Beans;

import DAO.formaPagoDAO;
import ENTIDAD.formaPagoC;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="formaPagoB")
@ViewScoped
public class formaPago implements Serializable{
	
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private List<formaPagoC> formaPagoL;
    
    
    formaPagoDAO formaPagoD;
    
    public List<formaPagoC> mostrarFormaPago(){
        formaPagoD=new formaPagoDAO();
        formaPagoL=formaPagoD.mostrarFormaPago();
        return formaPagoL;
    }
    public List<formaPagoC> getFormaPagoL() {
        return formaPagoL;
    }
    public void setFormaPagoL(List<formaPagoC> formaPagoL) {
        this.formaPagoL = formaPagoL;
    }
}
