package ENTIDAD;

import java.io.Serializable;
import java.util.Date;

public class mensajeC implements Serializable {

	private static final long serialVersionUID = 1L;
	private int mensaje;

	private String cuerpo;
	private String pie;
	private Date fechaInicio;
	private Date fechaFinal;
	private int estadoRegistro;
	
	
	public mensajeC() {

	}
	
	
	public int getMensaje() {
		return mensaje;
	}


	public void setMensaje(int mensaje) {
		this.mensaje = mensaje;
	}


	
	public String getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	public String getPie() {
		return pie;
	}
	public void setPie(String pie) {
		this.pie = pie;
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
	public int getEstadoRegistro() {
		return estadoRegistro;
	}
	public void setEstadoRegistro(int estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
	

	
}
