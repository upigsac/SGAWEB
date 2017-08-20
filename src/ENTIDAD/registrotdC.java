
package ENTIDAD;

import java.io.Serializable;
import java.util.Date;

import Beans.util;
public class registrotdC extends util implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int padre;
	
	
	
	
    
	private int institucion;
    private String periodo;
    private int dia ;
    private int semana;
    private Date fecha;
    private Date hora_ing;
    private Date hora_sal;
    private String cpersonal;
    private String carrera;
    private String curso;
    private String seccion;
    private int turno;
    private String ciclo;
    private String aula;
    private String tipo;
    private int sesion;
    private String tema;
    private String hora_ingreso;
    private String hora_salida;
    private int tardanza;
    private int estudiante;
    private int tipoHora;
    private int tipoClase;
    private String observacion;
    private int estadoRegistro;

    public registrotdC() {
    }

    public registrotdC(int id, int institucion, String periodo, int dia, int semana, Date fecha, Date hora_ing, Date hora_sal, String cpersonal, String carrera, String curso, String seccion, int turno, String ciclo, String aula, String tipo, int sesion, String tema, String hora_ingreso, String hora_salida, int tardanza, int estudiante, int tipoHora, int tipoClase,String obervacion, int estadoRegistro) {
        this.id = id;
        this.institucion = institucion;
        this.periodo = periodo;
        this.dia = dia;
        this.semana = semana;
        this.fecha = fecha;
        this.hora_ing = hora_ing;
        this.hora_sal = hora_sal;
        this.cpersonal = cpersonal;
        this.carrera = carrera;
        this.curso = curso;
        this.seccion = seccion;
        this.turno = turno;
        this.ciclo = ciclo;
        this.aula = aula;
        this.tipo = tipo;
        this.sesion = sesion;
        this.tema = tema;
        this.hora_ingreso = hora_ingreso;
        this.hora_salida = hora_salida;
        this.tardanza = tardanza;
        this.estudiante = estudiante;
        this.tipoHora = tipoHora;
        this.tipoClase = tipoClase;
        this.observacion=obervacion;
        this.estadoRegistro = estadoRegistro;
    }
    
    public int getPadre() {
		return padre;
	}

	public void setPadre(int padre) {
		this.padre = padre;
	}
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public int getDia() {
        return dia;
    }
    public void setDia(int dia) {
        this.dia = dia;
    }
    public int getSemana() {
        return semana;
    }
    public void setSemana(int semana) {
        this.semana = semana;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Date getHora_ing() {
        return hora_ing;
    }
    public void setHora_ing(Date hora_ing) {
        this.hora_ing = hora_ing;
    }
    public Date getHora_sal() {
        return hora_sal;
    }
    public void setHora_sal(Date hora_sal) {
        this.hora_sal = hora_sal;
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
    public int getTurno() {
        return turno;
    }
    public void setTurno(int turno) {
        this.turno = turno;
    }
    public String getCiclo() {
        return ciclo;
    }
    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
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
    public int getSesion() {
        return sesion;
    }
    public void setSesion(int sesion) {
        this.sesion = sesion;
    }
    public String getTema() {
        return tema;
    }
    public void setTema(String tema) {
        this.tema = tema;
    }
    public String getHora_ingreso() {
        return hora_ingreso;
    }
    public void setHora_ingreso(String hora_ingreso) {
        this.hora_ingreso = hora_ingreso;
    }
    public String getHora_salida() {
        return hora_salida;
    }
    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }
    public int getTardanza() {
        return tardanza;
    }
    public void setTardanza(int tardanza) {
        this.tardanza = tardanza;
    }
    public int getEstudiante() {
        return estudiante;
    }
    public void setEstudiante(int estudiante) {
        this.estudiante = estudiante;
    }
    public int getTipoHora() {
        return tipoHora;
    }
    public void setTipoHora(int tipoHora) {
        this.tipoHora = tipoHora;
    }
    public int getTipoClase() {
        return tipoClase;
    }
    public void setTipoClase(int tipoClase) {
        this.tipoClase = tipoClase;
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
   public double totalHora(int duracion){   	
    	
    	return  (dateIff(convertDateTime(this.hora_ing), convertDateTime(this.hora_sal))/60000)/duracion;
    }
   
   public long totalMinutos(){   	
   	
   	return  dateIff(this.hora_ing, hora_sal,1);
   }
    
}
