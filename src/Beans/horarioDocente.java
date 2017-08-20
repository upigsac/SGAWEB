
package Beans;

import DAO.carrerasDAO;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;


@ManagedBean(name = "horarioDocenteB")
public class horarioDocente  {

    private String seleccionCarrera;
    private List<ArrayList<String>> horarioL;
    carrerasDAO carreraD = null;

    public List<ArrayList<String>> mostrarHorario(int institucion, int periodo, String carrera, String personal) {

        carreraD = new carrerasDAO();
        horarioL = carreraD.horarioDocente(institucion, periodo, carrera, personal);
        return horarioL;
    }
    public String getSeleccionCarrera() {
        return seleccionCarrera;
    }
    public void setSeleccionCarrera(String seleccionCarrera) {
        this.seleccionCarrera = seleccionCarrera;
    }
    public List<ArrayList<String>> getHorarioL() {
        return horarioL;
    }
    public void setHorarioL(List<ArrayList<String>> horarioL) {
        this.horarioL = horarioL;
    }
}
