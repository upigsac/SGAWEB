
package Beans;

import DAO.estadoCivilDAO;
import ENTIDAD.estadoCivilC;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "estadoCivilB")
@ViewScoped
public class estadoCivil implements Serializable {

	private static final long serialVersionUID = 1L;
	private estadoCivilC estadoCivil;
    private List<estadoCivilC> estadoCivilL=new ArrayList<>();

    estadoCivilDAO estadoCivilD;

    public List<estadoCivilC> mostrarEstadoCivil() {
        estadoCivilD = new estadoCivilDAO();
        estadoCivilL = estadoCivilD.mostrarEstadoCivil();
        return estadoCivilL;
    }

    public estadoCivilC mostrarEstadoCivil(int codigo) {
        estadoCivilD = new estadoCivilDAO();
        estadoCivil = estadoCivilD.mostrarEstadoCivil(codigo);
        return estadoCivil;
    }

   
    public estadoCivilC getEstadoCivil() {
        return estadoCivil;
    }

    
    public void setEstadoCivil(estadoCivilC estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    
    public List<estadoCivilC> getEstadoCivilL() {
        return estadoCivilL;
    }

  
    public void setEstadoCivilL(List<estadoCivilC> estadoCivilL) {
        this.estadoCivilL = estadoCivilL;
    }
}
