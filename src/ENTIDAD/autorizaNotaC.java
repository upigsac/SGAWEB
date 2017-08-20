
package ENTIDAD;

import java.io.Serializable;
import java.util.Date;


public class autorizaNotaC implements Serializable {

	private static final long serialVersionUID = 1L;
	private int institucion;
    private String periodo;
    private int autorizacion;
    private String documento;
    private String carrera;
    private String malla;
    private String curso;
    private String seccion;
    private int grupo;
    private String personal;
    private String tipoExamen;
    private String alumno;
    private Date fechaInicio;
    private Date fechaFin;
    private String observacion;
    private int estadoRegistro;

    public autorizaNotaC() {
    }

    public autorizaNotaC(int institucion, String periodo, int autorizacion, String documento, String carrera, String malla, String curso, String seccion, int grupo, String personal, String tipoExamen, String alumno, Date fechaInicio, Date fechaFin, String observacion, int estadoRegistro) {
        this.institucion = institucion;
        this.periodo = periodo;
        this.autorizacion = autorizacion;
        this.documento = documento;
        this.carrera = carrera;
        this.malla = malla;
        this.curso = curso;
        this.seccion = seccion;
        this.grupo = grupo;
        this.personal = personal;
        this.tipoExamen = tipoExamen;
        this.alumno = alumno;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.observacion = observacion;
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
    public int getAutorizacion() {
        return autorizacion;
    }
    public void setAutorizacion(int autorizacion) {
        this.autorizacion = autorizacion;
    }
    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
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
    public int getGrupo() {
        return grupo;
    }
    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }
    public String getPersonal() {
        return personal;
    }
    public void setPersonal(String personal) {
        this.personal = personal;
    }
    public String getTipoExamen() {
        return tipoExamen;
    }
    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }
    public String getAlumno() {
        return alumno;
    }
    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }
    public Date getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public Date getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    public String getObservacion() {
        return observacion;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
