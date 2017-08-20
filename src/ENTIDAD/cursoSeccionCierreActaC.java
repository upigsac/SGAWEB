
package ENTIDAD;

import java.io.Serializable;
import java.util.Date;

public class cursoSeccionCierreActaC implements Serializable {
	
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int institucion;
    private String periodo;
    private String carrera;
    private String malla;
    private String curso;
    private String seccion;
    private String grupo;
    private Date fecha;    
    private String personal;
    private int estadoRegistro;

    public cursoSeccionCierreActaC() {
    }

    public cursoSeccionCierreActaC(int institucion, String periodo, String carrera, String malla, String curso, String seccion, String grupo, Date fecha, String personal, int estadoRegistro) {
        this.institucion = institucion;
        this.periodo = periodo;
        this.carrera = carrera;
        this.malla = malla;
        this.curso = curso;
        this.seccion = seccion;
        this.grupo = grupo;
        this.fecha = fecha;
        this.personal = personal;
        this.estadoRegistro = estadoRegistro;
    }
    public int getInstitucion() {
        return institucion;
    }
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }
    public String getPeriodo() {
        return periodo;
    }
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
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
    public String getGrupo() {
        return grupo;
    }
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    public String getPersonal() {
        return personal;
    }
    public void setPersonal(String personal) {
        this.personal = personal;
    }

    
}
