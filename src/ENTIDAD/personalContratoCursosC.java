
package ENTIDAD;

import java.io.Serializable;
import java.util.Date;


public class personalContratoCursosC implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String personal;
    private int contrato;    
    private int institucion;
    private String periodo;
    private String carrera;
    private String malla;
    private String seccion;
    private String curso;
    private Date fechaInicio;
    private Date fechaFinal;
    private String observacion;
    private int estadoRegistro;

    public personalContratoCursosC() {
    }

    public personalContratoCursosC(String personal, int contrato, int institucion, String periodo, String carrera, String malla, String seccion, String curso, Date fechaInicio, Date fechaFinal, String observacion, int estadoRegistro) {
        this.personal = personal;
        this.contrato = contrato;
        this.institucion = institucion;
        this.periodo = periodo;
        this.carrera = carrera;
        this.malla = malla;
        this.seccion = seccion;
        this.curso = curso;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.observacion = observacion;
        this.estadoRegistro = estadoRegistro;
    }


    public String getPersonal() {
        return personal;
    }
    public void setPersonal(String personal) {
        this.personal = personal;
    }
    public int getContrato() {
        return contrato;
    }
    public void setContrato(int contrato) {
        this.contrato = contrato;
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
    public String getSeccion() {
        return seccion;
    }
    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }
    public String getCurso() {
        return curso;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    public String getMalla() {
        return malla;
    }
    public void setMalla(String malla) {
        this.malla = malla;
    }
    public Date getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public Date getFechaFinal() {
        return fechaFinal;
    }
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
    public String getObservacion() {
        return observacion;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    
}
