package ENTIDAD;

import java.io.Serializable;

public class mensajeAlumnoC implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int mensaje;	
	private int institucion;
	private String periodo;
	private String carrera;
	private String alumno;
	private int estadoRegistro;

	public mensajeAlumnoC() {
		// TODO Auto-generated constructor stub
	}
	
	public mensajeAlumnoC( int mensaje,	 int institucion,String periodo,	 String carrera,	 String alumno,	 int estadoRegistro) {
		
		this.mensaje=mensaje;
		this.institucion=institucion;
		this.periodo=periodo;
		this.carrera=carrera;
		this.alumno=alumno;
		this.estadoRegistro=estadoRegistro;
	}
	
	
	
	
	
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public int getMensaje() {
		return mensaje;
	}




	public void setMensaje(int mensaje) {
		this.mensaje = mensaje;
	}
	
	
	public int getInstitucion() {
		return institucion;
	}
	public void setInstitucion(int institucion) {
		this.institucion = institucion;
	}
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	public String getAlumno() {
		return alumno;
	}
	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}
	public int getEstadoRegistro() {
		return estadoRegistro;
	}
	public void setEstadoRegistro(int estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
}
