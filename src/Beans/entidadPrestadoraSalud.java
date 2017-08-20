

package Beans;

import DAO.entidadPrestadoraSaludDAO;
import ENTIDAD.entidadPrestadoraSaludC;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="entidadPrestadoraSaludB")
@ViewScoped
public class entidadPrestadoraSalud implements Serializable{
	
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	private List<entidadPrestadoraSaludC> entidadPrestadoraSaludL;
    
    
    
    entidadPrestadoraSaludDAO entidadPrestadoraSaludD;
    public List<entidadPrestadoraSaludC> mostrarEntidadPrestadoraSalud(){
        entidadPrestadoraSaludD=new entidadPrestadoraSaludDAO();
        entidadPrestadoraSaludL=entidadPrestadoraSaludD.mostrarEntidadPrestadoraSalud();
        return entidadPrestadoraSaludL;
    }
    public List<entidadPrestadoraSaludC> getEntidadPrestadoraSaludL() {
        return entidadPrestadoraSaludL;
    }
    public void setEntidadPrestadoraSaludL(List<entidadPrestadoraSaludC> entidadPrestadoraSaludL) {
        this.entidadPrestadoraSaludL = entidadPrestadoraSaludL;
    }
    
}
