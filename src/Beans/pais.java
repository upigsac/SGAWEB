
package Beans;

import DAO.paisDAO;
import ENTIDAD.paisC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="paisB")
@ViewScoped
public class pais{
    private List<paisC> paisL;

    paisDAO paisD;
    
    
    public List<paisC> mostrarPais(){
        paisD=new paisDAO();
        paisL=paisD.mostrarPais();
        return paisL;
    }

    public List<paisC> getPaisL() {
        return paisL;
    }
    public void setPaisL(List<paisC> paisL) {
        this.paisL = paisL;
    }
    
  
}
