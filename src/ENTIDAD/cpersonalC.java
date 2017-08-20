

package ENTIDAD;

import java.io.Serializable;
import java.util.Date;

public class cpersonalC implements Serializable {
	
			
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cpersonal;
    private String personal;
    private int modalidad;
    private String periodo;
    private String semestre;
    private Date fechaInicio;
    
    private  int estadoRegistro;

    public cpersonalC() {
    }

    public cpersonalC(String cpersonal, String personal, int modalidad, String periodo, String semestre, int estadoRegistro) {
        this.cpersonal = cpersonal;
        this.personal = personal;
        this.modalidad = modalidad;
        this.periodo = periodo;
        this.semestre = semestre;
        this.estadoRegistro = estadoRegistro;
    }
    public String getCpersonal() {
        return cpersonal;
    }
    public void setCpersonal(String cpersonal) {
        this.cpersonal = cpersonal;
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
    public int getModalidad() {
        return modalidad;
    }
    public void setModalidad(int modalidad) {
        this.modalidad = modalidad;
    }
    public String getPeriodo() {
        return periodo;
    }
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    public String getSemestre() {
        return semestre;
    }
    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
    public Date getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}
