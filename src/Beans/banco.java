

package Beans;

import DAO.bancoDAO;
import ENTIDAD.bancoC;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="bancoB")
@ViewScoped
public class banco implements Serializable{
	
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<bancoC> bancoL;
    
    bancoDAO bancoD;
    
    public List<bancoC> mostrarBanco(){
        bancoD=new bancoDAO();
        bancoL=bancoD.mostrarBanco();
        return bancoL;
    }
    public List<bancoC> getBancoL() {
        return bancoL;
    }
    public void setBancoL(List<bancoC> bancoL) {
        this.bancoL = bancoL;
    }
}
