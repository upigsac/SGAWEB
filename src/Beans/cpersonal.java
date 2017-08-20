
package Beans;

import DAO.cpersonalDAO;
import ENTIDAD.cpersonalC;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "cpersonalB")
@ViewScoped
public class cpersonal implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private cpersonalC cpersonal = new cpersonalC();
    private List<cpersonalC> cpersonalL;
    
    cpersonalDAO cpersonalD;

 

    public cpersonalC getCpersonal() {
        return cpersonal;
    }

    public void setCpersonal(cpersonalC cpersonal) {
        this.cpersonal = cpersonal;
    }

    public void insertar(cpersonalC item) {
        cpersonalD = new cpersonalDAO();
        cpersonalD.insertar(item);
        
    }

    public void nuevo(String personal) {
       

    }
    public List<cpersonalC> getCpersonalL() {
        return cpersonalL;
    }
    public void setCpersonalL(List<cpersonalC> cpersonalL) {
        this.cpersonalL = cpersonalL;
    }
}
