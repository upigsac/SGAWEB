
package ENTIDAD;

import java.io.Serializable;

public class cursoSeccionDocenteC implements Serializable{
 
	private static final long serialVersionUID = 1L;
	private int institucion;
    private String periodo;
    private String carrera;
    private String malla;
    private String grupo;
    private String curso;
    private String seccion;
    private String personal;
    private int hora_teoria;
    private int hora_practica;
    private int estadoRegistro;

    public cursoSeccionDocenteC(int institucion, String periodo, String carrera, String malla, String grupo, String curso, String seccion, String personal, int hora_teoria, int hora_practica, int estadoRegistro) {
        this.institucion = institucion;
        this.periodo = periodo;
        this.carrera = carrera;
        this.malla = malla;
        this.grupo = grupo;
        this.curso = curso;
        this.seccion = seccion;
        this.personal = personal;
        this.hora_teoria = hora_teoria;
        this.hora_practica = hora_practica;
        this.estadoRegistro = estadoRegistro;
    }

    public cursoSeccionDocenteC() {
    }
    public int getInstitucion() {
        return institucion;
    }
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }
    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    public String getMalla() {
        return malla;
    }
    public void setMalla(String malla) {
        this.malla = malla;
    }
    public String getCurso() {
        return curso;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
    public String getSeccion() {
        return seccion;
    }
    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }
    public String getPersonal() {
        return personal;
    }
    public void setPersonal(String personal) {
        this.personal = personal;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    public int getHora_teoria() {
        return hora_teoria;
    }
    public void setHora_teoria(int hora_teoria) {
        this.hora_teoria = hora_teoria;
    }
    public int getHora_practica() {
        return hora_practica;
    }
    public void setHora_practica(int hora_practica) {
        this.hora_practica = hora_practica;
    }
    public String getGrupo() {
        return grupo;
    }
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
    public String getPeriodo() {
        return periodo;
    }
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }


}
