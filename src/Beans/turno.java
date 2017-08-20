
package Beans;

import DAO.turnoDAO;
import ENTIDAD.turnoC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "turnoB")
@ViewScoped
public class turno{

    private List<turnoC> turnoL;
    private turnoC turno;
    private String seleccion = "%";

    turnoDAO turnoD;

    public List<turnoC> mostrarTurno() {
        turnoD = new turnoDAO();
        turnoL = turnoD.mostrarTurno();
        return turnoL;
    }

    public turnoC mostrarTurno(int turno) {
        turnoD = new turnoDAO();
        this.turno = turnoD.mostrarTurno(turno);
        return this.turno;
    }

    public turnoC mostrarTurno(int institucion, String periodo, String seccion) {
        turnoD = new turnoDAO();
        this.turno = turnoD.mostrarTurno(institucion, periodo, seccion);
        return this.turno;
    }
    public List<turnoC> getTurnoL() {
        return turnoL;
    }
    public void setTurnoL(List<turnoC> turnoL) {
        this.turnoL = turnoL;
    }
    public String getSeleccion() {
        return seleccion;
    }
    public void setSeleccion(String seleccion) {
        this.seleccion = seleccion;
    }
    public turnoC getTurno() {
        return turno;
    }
    public void setTurno(turnoC turno) {
        this.turno = turno;
    }
}
