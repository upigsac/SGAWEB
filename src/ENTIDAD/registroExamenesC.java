
package ENTIDAD;

import java.io.Serializable;
import java.util.Date;



public class registroExamenesC  implements Serializable {
	
	
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
    private int turno;
    private int nivelModular;
    private int grupo;
    private String personal;
    private String cpersonal;
    private Date fecha_examen;
    private Date hora_ingreso;
    private Date hora_salida;
    private String aula;
    private String tipoExamen;
    private int estadoRegistro;

    public registroExamenesC() {
    }

    public registroExamenesC(int institucion, String periodo, String carrera, String malla, String curso, String seccion, int turno, int nivelModular, int grupo, String personal, String cpersonal, Date fecha_examen, Date hora_ingreso, Date hora_salida, String aula, String tipoExamen, int estadoRegistro) {
        this.institucion = institucion;
        this.periodo = periodo;
        this.carrera = carrera;
        this.malla = malla;
        this.curso = curso;
        this.seccion = seccion;
        this.turno = turno;
        this.nivelModular = nivelModular;
        this.grupo = grupo;
        this.personal = personal;
        this.cpersonal = cpersonal;
        this.fecha_examen = fecha_examen;
        this.hora_ingreso = hora_ingreso;
        this.hora_salida = hora_salida;
        this.aula = aula;
        this.tipoExamen = tipoExamen;
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
    public String getMalla() {
        return malla;
    }
    public void setMalla(String malla) {
        this.malla = malla;
    }
    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
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
    public int getTurno() {
        return turno;
    }
    public void setTurno(int turno) {
        this.turno = turno;
    }
    public int getGrupo() {
        return grupo;
    }
    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }
    public String getCpersonal() {
        return cpersonal;
    }
    public void setCpersonal(String cpersonal) {
        this.cpersonal = cpersonal;
    }
    public Date getFecha_examen() {
        return fecha_examen;
    }
    public void setFecha_examen(Date fecha_examen) {
        this.fecha_examen = fecha_examen;
    }
    public Date getHora_ingreso() {
        return hora_ingreso;
    }
    public void setHora_ingreso(Date hora_ingreso) {
        this.hora_ingreso = hora_ingreso;
    }
    public Date getHora_salida() {
        return hora_salida;
    }
    public void setHora_salida(Date hora_salida) {
        this.hora_salida = hora_salida;
    }
    public String getAula() {
        return aula;
    }
    public void setAula(String aula) {
        this.aula = aula;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    public String getTipoExamen() {
        return tipoExamen;
    }
    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }
    public int getNivelModular() {
        return nivelModular;
    }
    public void setNivelModular(int nivelModular) {
        this.nivelModular = nivelModular;
    }
    public String getPersonal() {
        return personal;
    }
    public void setPersonal(String personal) {
        this.personal = personal;
    }
    
    
}
