
package ENTIDAD;

import java.io.Serializable;
import java.util.Date;

import Beans.util;


public class registrotmC extends util implements Serializable  {

	private static final long serialVersionUID = 1L;
	private int id;
    private int institucion;
    private String periodo;
    private String malla;
    private String grupo;
    private Date fecha_inicio;
    private Date fecha_fin;
    private int dia;
    private Date hora_inicio;
    private Date hora_fin;
    private String cpersonal;
    private String personal;
    private String carrera;
    private String curso;
    private String seccion;
    private String aula;
    private String tipo;
    private int turno;
    private int tipoHora;
    private int tipoClase;
    private String observacion;
    private int estadoRegistro;
    private int sede;
    private String ciclo;
    private String creacionUsuario;
    private String fut;
    private Date fechaRecuperacion;
    private Date horaInicioRecuperacion;
    private Date horaFinRecuperacion;

    
    
    
    
    
    


	public Date getHoraInicioRecuperacion() {
		return horaInicioRecuperacion;
	}

	public void setHoraInicioRecuperacion(Date horaInicioRecuperacion) {
		this.horaInicioRecuperacion = horaInicioRecuperacion;
	}

	public Date getHoraFinRecuperacion() {
		return horaFinRecuperacion;
	}

	public void setHoraFinRecuperacion(Date horaFinRecuperacion) {
		this.horaFinRecuperacion = horaFinRecuperacion;
	}

	public Date getFechaRecuperacion() {
		return fechaRecuperacion;
	}

	public void setFechaRecuperacion(Date fechaRecuperacion) {
		this.fechaRecuperacion = fechaRecuperacion;
	}

	public String getFut() {
		return fut;
	}

	public void setFut(String fut) {
		this.fut = fut;
	}

	public registrotmC() {
    }

    public registrotmC(int id, int institucion, String periodo, String malla, String grupo, Date fecha_inicio, Date fecha_fin, int dia, Date hora_inicio, Date hora_fin, String personal, String cpersonal, String carrera, String curso, String seccion, String aula, String tipo, int turno,int sede,int tipoClase,int tipoHora, String observacion,Date fechaRecuperacion,Date horaInicioRecuperacion,Date horaFinRecuperacion, int estadoRegistro) {
        this.id = id;
        this.institucion = institucion;
        this.periodo = periodo;
        this.malla = malla;
        this.grupo = grupo;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.dia = dia;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.personal = personal;
        this.cpersonal = cpersonal;
        this.carrera = carrera;
        this.curso = curso;
        this.seccion = seccion;
        this.aula = aula;
        this.tipo = tipo;
        this.turno = turno;
        this.sede = sede;
        this.tipoClase=tipoClase;
        this.tipoHora=tipoHora;
        this.observacion = observacion;
        this.fechaRecuperacion=fechaRecuperacion;
        this.horaInicioRecuperacion=horaInicioRecuperacion;
        this.horaFinRecuperacion=horaFinRecuperacion;
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
    public String getGrupo() {
        return grupo;
    }
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
    public int getDia() {
        return dia;
    }
    public void setDia(int dia) {
        this.dia = dia;
    }
    public String getCpersonal() {
        return cpersonal;
    }
    public void setCpersonal(String cpersonal) {
        this.cpersonal = cpersonal;
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
    public String getAula() {
        return aula;
    }
    public void setAula(String aula) {
        this.aula = aula;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getObservacion() {
        return observacion;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    public Date getFecha_inicio() {
        return fecha_inicio;
    }
    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    public Date getFecha_fin() {
        return fecha_fin;
    }
    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
    public Date getHora_inicio() {
        return hora_inicio;
    }
    public void setHora_inicio(Date hora_inicio) {
        this.hora_inicio = hora_inicio;
    }
    public Date getHora_fin() {
        return hora_fin;
    }
    public void setHora_fin(Date hora_fin) {
        this.hora_fin = hora_fin;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMalla() {
        return malla;
    }
    public void setMalla(String malla) {
        this.malla = malla;
    }
    public int getTurno() {
        return turno;
    }
    public void setTurno(int turno) {
        this.turno = turno;
    }
    public String getCreacionUsuario() {
        return creacionUsuario;
    }
    public void setCreacionUsuario(String creacionUsuario) {
        this.creacionUsuario = creacionUsuario;
    }
    public int getTipoHora() {
        return tipoHora;
    }
    public void setTipoHora(int tipoHora) {
        this.tipoHora = tipoHora;
    }
    public int getSede() {
        return sede;
    }
    public void setSede(int sede) {
        this.sede = sede;
    }
    public String getPersonal() {
        return personal;
    }
    public void setPersonal(String personal) {
        this.personal = personal;
    }
    public String getCiclo() {
        return ciclo;
    }
    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }
    public int getTipoClase() {
        return tipoClase;
    }
    public void setTipoClase(int tipoClase) {
        this.tipoClase = tipoClase;
    }
    
    public double totalHoras(int duracion){   	
    	
    	return  (dateIff(convertDateTime(this.hora_inicio), convertDateTime(this.getHora_fin()))/6000)/duracion;
    }
    
    public long totalMinutos(){   	
       	
       	return  dateIff(this.hora_inicio, this.hora_fin,1);
       }
    
}
