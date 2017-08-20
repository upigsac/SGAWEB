
package Beans;

import DAO.gradoInstruccionDAO;
import ENTIDAD.gradoInstruccionC;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "gradoInstruccionB")
@ViewScoped
public class gradoInstruccion implements Serializable {
	
	
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<gradoInstruccionC> gradoInstruccionL;
    private gradoInstruccionC gradoInstruccion;

    gradoInstruccionDAO gradoInstruccionD;

    public List<gradoInstruccionC> mostrarGradoInstruccion(int nivel) {
        gradoInstruccionD = new gradoInstruccionDAO();
        gradoInstruccionL = gradoInstruccionD.mostrarGrado(nivel);
        return gradoInstruccionL;
    }
      public gradoInstruccionC mostrarGradoAcademico(int grado) {
        gradoInstruccionD = new gradoInstruccionDAO();
        gradoInstruccion=gradoInstruccionD.mostrarGradoAcademico(grado);
        return gradoInstruccion;
    }
    public List<gradoInstruccionC> getGradoInstruccionL() {
        return gradoInstruccionL;
    }
    public void setGradoInstruccionL(List<gradoInstruccionC> gradoInstruccionL) {
        this.gradoInstruccionL = gradoInstruccionL;
    }
    public gradoInstruccionC getGradoInstruccion() {
        return gradoInstruccion;
    }
    public void setGradoInstruccion(gradoInstruccionC gradoInstruccion) {
        this.gradoInstruccion = gradoInstruccion;
    }

}
