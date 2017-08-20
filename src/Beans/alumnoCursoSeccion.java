
package Beans;

import DAO.alumnoCursoSeccionDAO;
import ENTIDAD.alumnoCursoSeccionC;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "alumnoCursoSeccionB")
public class alumnoCursoSeccion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private alumnoCursoSeccionC alumnoCursoSeccion;
    private List<alumnoCursoSeccionC> alumnoCursoSeccionL;

    alumnoCursoSeccionDAO alumnoCursoSeccionD;

    public List<alumnoCursoSeccionC> mostrarAlumnoCursoSeccion(int institucion, String periodo, String carrera, String malla, String seccion, String curso) {
        alumnoCursoSeccionD = new alumnoCursoSeccionDAO();
        alumnoCursoSeccionL = alumnoCursoSeccionD.mostrarAlumnoCursoSeccion(institucion, periodo, carrera, malla, seccion, curso);
        return alumnoCursoSeccionL;
    }

    public List<alumnoCursoSeccionC> mostrarAlumnoCursoSeccion(int institucion, String periodo, String alumno) {
        alumnoCursoSeccionD = new alumnoCursoSeccionDAO();
        alumnoCursoSeccionL = alumnoCursoSeccionD.mostrarAlumnoCursoSeccion(institucion, periodo, alumno);
        return alumnoCursoSeccionL;
    }
    
    public List<alumnoCursoSeccionC> mostrarAlumnoCursosSeccionMatriculados(int institucion, String periodo, String alumno) {
        alumnoCursoSeccionD = new alumnoCursoSeccionDAO();
        alumnoCursoSeccionL = alumnoCursoSeccionD.mostrarAlumnoCursosSeccionMatriculados(institucion, periodo, alumno);
        return alumnoCursoSeccionL;
    }

    /**
     * @return the alumnoCursoSeccion
     */
    public alumnoCursoSeccionC getAlumnoCursoSeccion() {
        return alumnoCursoSeccion;
    }

    /**
     * @param alumnoCursoSeccion the alumnoCursoSeccion to set
     */
    public void setAlumnoCursoSeccion(alumnoCursoSeccionC alumnoCursoSeccion) {
        this.alumnoCursoSeccion = alumnoCursoSeccion;
    }

    /**
     * @return the alumnoCursoSeccionL
     */
    public List<alumnoCursoSeccionC> getAlumnoCursoSeccionL() {
        return alumnoCursoSeccionL;
    }

    /**
     * @param alumnoCursoSeccionL the alumnoCursoSeccionL to set
     */
    public void setAlumnoCursoSeccionL(List<alumnoCursoSeccionC> alumnoCursoSeccionL) {
        this.alumnoCursoSeccionL = alumnoCursoSeccionL;
    }
}
