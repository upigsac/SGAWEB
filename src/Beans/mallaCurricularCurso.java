
package Beans;

import DAO.cursoPeriodoDAO;
import DAO.horMallaCurricularCursoDAO;
import ENTIDAD.cursoPeriodoC;
import ENTIDAD.horMallaCurricularCursoC;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



@ManagedBean(name = "mallaCurricularCursoB")
@ViewScoped
public class mallaCurricularCurso {

    private horMallaCurricularCursoC mallaCurricularCurso;
    private List<horMallaCurricularCursoC> mallaCurricularCursoL;
    private List<horMallaCurricularCursoC> mallaCurricularCursoSeleccionL;

  
    horMallaCurricularCursoDAO horMallaCurricularCursoD;

    public List<horMallaCurricularCursoC> mostrarMallaCurricularCurso(int institucion, String carrera, String malla) {
        horMallaCurricularCursoD = new horMallaCurricularCursoDAO();
        mallaCurricularCursoL = horMallaCurricularCursoD.mostrarMallaCurricularCurso(institucion, carrera, malla);
        return mallaCurricularCursoL;
    }

    public List<horMallaCurricularCursoC> mostrarMallaCurricularCurso(int institucion, String periodo, String carrera, String malla) {
        horMallaCurricularCursoD = new horMallaCurricularCursoDAO();
        mallaCurricularCursoL = horMallaCurricularCursoD.mostrarMallaCurricularCurso(institucion, periodo, carrera, malla);
        return mallaCurricularCursoL;
    }
    cursoPeriodoDAO cursoPeriodoD;

    public void insertarCursoPeriodo(List<horMallaCurricularCursoC> horMallaCurricularCurso) {

        for (horMallaCurricularCursoC item : horMallaCurricularCurso) {
            cursoPeriodoD = new cursoPeriodoDAO();
            cursoPeriodoD.insertar(new cursoPeriodoC(item.getInstitucion(), "20151", item.getCarrera(), item.getMalla(), item.getCurso(), 1, 1, 1));
        }
    }

    public horMallaCurricularCursoC getMallaCurricularCurso() {
        return mallaCurricularCurso;
    }

  
    public void setMallaCurricularCurso(horMallaCurricularCursoC mallaCurricularCurso) {
        this.mallaCurricularCurso = mallaCurricularCurso;
    }

    public List<horMallaCurricularCursoC> getMallaCurricularCursoL() {
        return mallaCurricularCursoL;
    }

    public void setMallaCurricularCursoL(List<horMallaCurricularCursoC> mallaCurricularCursoL) {
        this.mallaCurricularCursoL = mallaCurricularCursoL;
    }

    public List<horMallaCurricularCursoC> getMallaCurricularCursoSeleccionL() {
        return mallaCurricularCursoSeleccionL;
    }

    public void setMallaCurricularCursoSeleccionL(List<horMallaCurricularCursoC> mallaCurricularCursoSeleccionL) {
        this.mallaCurricularCursoSeleccionL = mallaCurricularCursoSeleccionL;
    }
}
