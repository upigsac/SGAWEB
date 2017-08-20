
package Beans;

import DAO.horMallaCurricularCursoActDAO;
import ENTIDAD.horMallaCurricularCursoActC;

import java.util.List;
import javax.faces.bean.ManagedBean;


@ManagedBean(name = "horMallaCurricularCursoActB")
public class horMallaCurricularCursoAct  {
    private List<horMallaCurricularCursoActC> horMallaCurricularCursoActL;
    horMallaCurricularCursoActDAO horMallaCurricularCursoActD;

    public List<horMallaCurricularCursoActC> mostrarMallaCurricularAct(int institucion, String carrera, int nivel,String malla) {
        horMallaCurricularCursoActD = new horMallaCurricularCursoActDAO();
      //  horMallaCurricularCursoActL = horMallaCurricularCursoActD.mostrarMostrarMallaCurso(institucion, carrera, nivel,malla);
        return horMallaCurricularCursoActL;
    }
    public List<horMallaCurricularCursoActC> getHorMallaCurricularCursoActL() {
        return horMallaCurricularCursoActL;
    }
    public void setHorMallaCurricularCursoActL(List<horMallaCurricularCursoActC> horMallaCurricularCursoActL) {
        this.horMallaCurricularCursoActL = horMallaCurricularCursoActL;
    }

}
