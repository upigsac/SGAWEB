
package Beans;

import DAO.aulaDAO;
import ENTIDAD.aulaC;

import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "aulaB")
@ViewScoped
public class aula  {

    private List<aulaC> aulaL;
    private aulaC aula = new aulaC();

    aulaDAO aulaD;

    public List<aulaC> mostrarAula() {
        aulaD = new aulaDAO();
        aulaL = aulaD.mostrarAula();
        return aulaL;
    }

    public void mostrarAula(Date fechaInicio, Date fechaFin, int dia, Date horaInicio, Date horaFin) {
        aulaD = new aulaDAO();
        aulaL = aulaD.mostrarAula(fechaInicio, fechaFin, dia, horaInicio, horaFin);

    }

    public List<aulaC> mostrarAula(int tipo) {
        aulaD = new aulaDAO();
        aulaL = aulaD.mostrarAula(tipo);
        return aulaL;
    }
    public List<aulaC> getAulaL() {
        return aulaL;
    }
    public void setAulaL(List<aulaC> aulaL) {
        this.aulaL = aulaL;
    }
    public aulaC getAula() {
        return aula;
    }
    public void setAula(aulaC aula) {
        this.aula = aula;
    }
}
