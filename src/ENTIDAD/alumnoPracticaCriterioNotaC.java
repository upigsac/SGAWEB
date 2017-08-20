
package ENTIDAD;
import java.io.Serializable;
import java.util.Date;

public class alumnoPracticaCriterioNotaC implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String alumno;
    private String curso;
    private int cronograma;
    private Date fecha;
    private String criterio;
    private Double nota;
    private int estadoRegistro;

    public alumnoPracticaCriterioNotaC() {
    }

    public alumnoPracticaCriterioNotaC(String alumno, String curso, int cronograma, Date fecha, String criterio, Double nota, int estadoRegistro) {
        this.alumno = alumno;
        this.curso = curso;
        this.cronograma = cronograma;
        this.fecha = fecha;
        this.criterio = criterio;
        this.nota = nota;
        this.estadoRegistro = estadoRegistro;
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
    public int getCronograma() {
        return cronograma;
    }
    public void setCronograma(int cronograma) {
        this.cronograma = cronograma;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getCriterio() {
        return criterio;
    }
    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }
    public Double getNota() {
        return nota;
    }
    public void setNota(Double nota) {
        this.nota = nota;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
}
