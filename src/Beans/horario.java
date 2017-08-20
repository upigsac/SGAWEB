
package Beans;

import DAO.seccionDAO;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "horarioB")
@ViewScoped
public class horario {

    private List<ArrayList<String>> horarioSeccion;
    private List<ArrayList<String>> horarioPersonal;
    private String tipoExamne = "%";

   
    seccionDAO seccionD;

    public List<ArrayList<String>> horarioSeccionAlumno(int institucion, int periodo, String alumno, String turno) {
        seccionD = new seccionDAO();
        if (this.tipoExamne.toString().equals("%")) {
            horarioSeccion = seccionD.horarioSeccion(institucion, periodo, alumno, turno);
        } else {

            horarioSeccion = seccionD.horarioSeccionExamen(institucion, periodo, alumno, tipoExamne);
        }

        return horarioSeccion;

    }

    public List<ArrayList<String>> horarioDocente(int institucion, String periodo, String carrera, String personal, String turno) {

        seccionD = new seccionDAO();
        if (tipoExamne.toString().equals("%")) {
            horarioPersonal = seccionD.horarioDocente(institucion, periodo, carrera, personal, turno);
        
            
        } else {
            String[] var = tipoExamne.split("-");
            horarioPersonal = seccionD.horarioDocenteExamen(institucion, periodo, carrera, personal, turno, var[0], var[1]);
       
        }

        return horarioPersonal;

    }

    public List<ArrayList<String>> horarioAula(int institucion, int periodo, String aula, String turno) {
        seccionD = new seccionDAO();
        horarioSeccion = seccionD.horarioAula(institucion, periodo, aula, turno);
        return horarioSeccion;

    }

    public List<ArrayList<String>> horarioCiclo(int institucion, int periodo, String carrera, String ciclo, String turno,String seccion) {
        seccionD = new seccionDAO();
        horarioSeccion = seccionD.horarioCiclo(institucion, periodo, carrera, ciclo, turno,seccion);
        return horarioSeccion;

    }

    public void examenesParcial() {
        tipoExamne = "EP";
       // util.consolaCliente( "examen parcial");

    }

  

    public void examenesFinal() {
        tipoExamne = "EF";
        //   util.consolaCliente( "examen final");        
    }



    public List<ArrayList<String>> getHorarioSeccion() {
        return horarioSeccion;
    }
    public void setHorarioSeccion(List<ArrayList<String>> horarioSeccion) {
        this.horarioSeccion = horarioSeccion;
    }
    public String getTipoExamne() {
        return tipoExamne;
    }
    public void setTipoExamne(String tipoExamne) {
        this.tipoExamne = tipoExamne;
    }
    public List<ArrayList<String>> getHorarioPersonal() {
        return horarioPersonal;
    }
    public void setHorarioPersonal(List<ArrayList<String>> horarioPersonal) {
        this.horarioPersonal = horarioPersonal;
    }

}
