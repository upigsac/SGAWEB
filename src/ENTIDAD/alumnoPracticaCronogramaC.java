

package ENTIDAD;

import java.io.Serializable;
import java.util.Date;


public class alumnoPracticaCronogramaC implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cronograma;
    private String alumno;
    private String curso;
    private Date fechaInicio;
    private Date fechaFinal;
    private int estadoRegistro;

    public alumnoPracticaCronogramaC() {
    }

    public alumnoPracticaCronogramaC(int cronograma, String alumno, String curso, Date fechaInicio, Date fechaFinal, int estadoRegistro) {
        this.cronograma = cronograma;
        this.alumno = alumno;
        this.curso = curso;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.estadoRegistro = estadoRegistro;
    }
    

   
    public Date getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public int getCronograma() {
        return cronograma;
    }
    public void setCronograma(int cronograma) {
        this.cronograma = cronograma;
    }
    public String getAlumno() {
        return alumno;
    }
    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }
    public String getCurso() {
        return curso;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
    public Date getFechaFinal() {
        return fechaFinal;
    }
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
}
