
package ENTIDAD;
import java.io.Serializable;
import java.util.Date;

public class registroTemasC implements Serializable {

	private static final long serialVersionUID = 1L;
	private int institucion;
    private String periodo;
    private String carrera;
    private String malla;
    private String curso;
    private String seccion;
    private String personal;
    private Date fecha;
    private int semana;
    private int sesion;
    private int dia;
    private String tema;
    private int estadoRegistro;

    public registroTemasC() {
    }

    public registroTemasC(int institucion, String periodo, String carrera, String malla, String curso, String seccion, String personal, Date fecha, int semana, int sesion, int dia, String tema, int estadoRegistro) {
        this.institucion = institucion;
        this.periodo = periodo;
        this.carrera = carrera;
        this.malla = malla;
        this.curso = curso;
        this.seccion = seccion;
        this.personal = personal;
        this.fecha = fecha;
        this.semana = semana;
        this.sesion = sesion;
        this.dia = dia;
        this.tema = tema;
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
    public String getPersonal() {
        return personal;
    }
    public void setPersonal(String personal) {
        this.personal = personal;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public int getSemana() {
        return semana;
    }
    public void setSemana(int semana) {
        this.semana = semana;
    }
    public int getSesion() {
        return sesion;
    }
    public void setSesion(int sesion) {
        this.sesion = sesion;
    }
    public int getDia() {
        return dia;
    }
    public void setDia(int dia) {
        this.dia = dia;
    }
    public String getTema() {
        return tema;
    }
    public void setTema(String tema) {
        this.tema = tema;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
}
