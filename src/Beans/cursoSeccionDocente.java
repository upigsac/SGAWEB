
package Beans;

import DAO.cursoSeccionDocenteDAO;
import ENTIDAD.cursoSeccionDocenteC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "cursoSeccionDocenteB")
@ViewScoped
public class cursoSeccionDocente  {

    private List<cursoSeccionDocenteC> cursoSeccionDocenteL;
    private cursoSeccionDocenteC cursoSeccionDocente;

    cursoSeccionDocenteDAO cursoSeccionDocenteD;

    public cursoSeccionDocenteC mostrarCursoSeccionDocente(int institucion, String periodo, String malla, String carrera, String curso, String seccion) {
        cursoSeccionDocenteD = new cursoSeccionDocenteDAO();
        cursoSeccionDocente = cursoSeccionDocenteD.mostrarPeriodoCarreraCursoSeccion(institucion, periodo, carrera, curso, seccion);
        return cursoSeccionDocente;
    }

    public cursoSeccionDocenteC mostrarCursoSeccionDocentes(int institucion, String periodo, String malla, String carrera, String curso, String seccion) {
        cursoSeccionDocenteD = new cursoSeccionDocenteDAO();
        cursoSeccionDocente = cursoSeccionDocenteD.mostrarPeriodoCarreraCursoSeccion(institucion, periodo, malla, carrera, curso, seccion);
        return cursoSeccionDocente;
    }
    
    public List<cursoSeccionDocenteC> mostrarListaCursoSeccionDocente(int institucion, String periodo, String malla, String carrera, String curso, String seccion) {
        cursoSeccionDocenteD = new cursoSeccionDocenteDAO();
        cursoSeccionDocenteL = cursoSeccionDocenteD.mostrarListaCursoSeccionDocente(institucion, periodo, malla, carrera, curso, seccion);
        return cursoSeccionDocenteL;
    }

    public List<cursoSeccionDocenteC> mostrarCursoSeccionDocente(int institucion, String periodo, String persona) {
        
        cursoSeccionDocenteD = new cursoSeccionDocenteDAO();
        cursoSeccionDocenteL = cursoSeccionDocenteD.mostrarCursoSeccionDocente(institucion, periodo, persona);
        return cursoSeccionDocenteL;
    }
    public List<cursoSeccionDocenteC> mostrarCursoSeccionDocenteAlumno(int institucion, String periodo, String alumno) {
        
        cursoSeccionDocenteD = new cursoSeccionDocenteDAO();
        cursoSeccionDocenteL = cursoSeccionDocenteD.mostrarCursoSeccionAlumno(institucion, periodo, alumno);
        return cursoSeccionDocenteL;
    }

    public void insertarRegistro(cursoSeccionDocenteC item) {
        cursoSeccionDocenteD = new cursoSeccionDocenteDAO();
        cursoSeccionDocenteD.insertar(item);
    }
    public List<cursoSeccionDocenteC> getCursoSeccionDocenteL() {
        return cursoSeccionDocenteL;
    }
    public void setCursoSeccionDocenteL(List<cursoSeccionDocenteC> cursoSeccionDocenteL) {
        this.cursoSeccionDocenteL = cursoSeccionDocenteL;
    }
    public cursoSeccionDocenteC getCursoSeccionDocente() {
        return cursoSeccionDocente;
    }
    public void setCursoSeccionDocente(cursoSeccionDocenteC cursoSeccionDocente) {
        this.cursoSeccionDocente = cursoSeccionDocente;
    }

}
