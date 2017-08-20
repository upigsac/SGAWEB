
package Beans;

import DAO.alumnoPeriodoDAO;
import ENTIDAD.alumnoPeriodoC;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "alumnoPeriodoB")
@ViewScoped
public class alumnoPeriodo implements Serializable {
	
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<alumnoPeriodoC> alumnoPeriodoL;

    alumnoPeriodoDAO alumnoPeriodoD;

    public void mostrarAlumnoPeriodo(int institucion, String periodo, String carrera, String seccion) {
        alumnoPeriodoD = new alumnoPeriodoDAO();
        alumnoPeriodoL = alumnoPeriodoD.mostrarAlumnoPeriodo(institucion, periodo, carrera, seccion);

    }
    public List<alumnoPeriodoC> getAlumnoPeriodoL() {
        return alumnoPeriodoL;
    }
    public void setAlumnoPeriodoL(List<alumnoPeriodoC> alumnoPeriodoL) {
        this.alumnoPeriodoL = alumnoPeriodoL;
    }
}
