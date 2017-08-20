
package Beans;

import DAO.pagosPendientesDAO;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "cronogramaAlumnoB")
@ViewScoped
public class cronogramaAlumno  {

    private List<ArrayList<String>> cronogramaL;

    pagosPendientesDAO pagosPendientesD;

    public List<ArrayList<String>> mostrarConograma(int institucion, int periodo, String usuario) {
        pagosPendientesD = new pagosPendientesDAO();
        cronogramaL = pagosPendientesD.cronogramaPagosAlumno(institucion, periodo, usuario);
        return cronogramaL;
    }
    public List<ArrayList<String>> getCronogramaL() {
        return cronogramaL;
    }
    public void setCronogramaL(List<ArrayList<String>> cronogramaL) {
        this.cronogramaL = cronogramaL;
    }

}
