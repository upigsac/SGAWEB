
package Beans;

import DAO.marcacionDocenteDAO;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "reporteMarcacionSemetralB")
@ViewScoped
public class reporteMarcacionSemetral  {

    private List<ArrayList<String>> marcacionL;
    private String turnoS;
    private String docenteS;
    marcacionDocenteDAO marcacionDocenteD;

    public void mostrarCargaHoraria() {
        marcacionDocenteD = new marcacionDocenteDAO();
        marcacionL = marcacionDocenteD.listadoSemestralMarcacion(20141, docenteS, turnoS);
    }
    public List<ArrayList<String>> getMarcacionL() {

        return marcacionL;
    }
    public void setMarcacionL(List<ArrayList<String>> marcacionL) {
        this.marcacionL = marcacionL;
    }
    public String getTurnoS() {
        return turnoS;
    }
    public void setTurnoS(String turnoS) {
        this.turnoS = turnoS;
    }
    public String getDocenteS() {
        return docenteS;
    }
    public void setDocenteS(String docenteS) {
        this.docenteS = docenteS;
    }
}
