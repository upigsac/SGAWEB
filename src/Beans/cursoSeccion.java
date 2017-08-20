
package Beans;

import DAO.cursoSeccionDAO;
import ENTIDAD.cursoSeccionC;

import java.util.List;
import javax.faces.bean.ManagedBean;


@ManagedBean(name = "cursoSeccionB")
public class cursoSeccion  {

    private cursoSeccionC cursoSeccion;
    private List<cursoSeccionC> cursoSeccionL;

    cursoSeccionDAO cursoSeccionD;

    public cursoSeccionC mostrarCursoSeccion(int institucion, String periodo, String carrera, String curso, String seccion) {
        cursoSeccionD = new cursoSeccionDAO();
        cursoSeccion = cursoSeccionD.mostrarCursoSeccion(""+institucion, periodo, carrera, curso, seccion);
        return cursoSeccion;
    }

    public List<cursoSeccionC> mostrarCursoSeccion(int institucion, String periodo, String carrera, String seccion) {
        cursoSeccionD = new cursoSeccionDAO();
        cursoSeccionL = cursoSeccionD.mostrarCursoSeccion(institucion, periodo, carrera, seccion);
        return cursoSeccionL;
    }

    public List<cursoSeccionC> mostrarCursoSeccionTipo(String institucion, String periodo, String carrera, String tipo) {
        cursoSeccionD = new cursoSeccionDAO();
        cursoSeccionL = cursoSeccionD.mostrarCursoSeccionTipo(institucion, periodo, carrera, tipo);
        return cursoSeccionL;
    }
    public cursoSeccionC getCursoSeccion() {
        return cursoSeccion;
    }
    public void setCursoSeccion(cursoSeccionC cursoSeccion) {
        this.cursoSeccion = cursoSeccion;
    }
    public List<cursoSeccionC> getCursoSeccionL() {
        return cursoSeccionL;
    }
    public void setCursoSeccionL(List<cursoSeccionC> cursoSeccionL) {
        this.cursoSeccionL = cursoSeccionL;
    }
}
