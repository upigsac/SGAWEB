package ENTIDAD;

import java.io.Serializable;

public class tipoContratoPersonalC implements Serializable {

	private static final long serialVersionUID = 1L;
	private int tipoContrato;
	private int tipoPersona;
	private String observacion;

	private String seccion1;
	private String seccion2;
	private int estadoRegistro;
	
	public tipoContratoPersonalC() {
	
	}
	
	public tipoContratoPersonalC( int tipoContrato,	 int tipoPersona,	 String observacion,	 String seccion1, String seccion2,		 int estadoRegistro) {
		this.tipoContrato=tipoContrato;
		this.tipoPersona=tipoPersona;
		this.observacion=observacion;
		this.seccion1=seccion1;
		this.seccion2=seccion2;
		this.estadoRegistro=estadoRegistro;
	}
	
	
	public int getTipoContrato() {
		return tipoContrato;
	}
	public void setTipoContrato(int tipoContrato) {
		this.tipoContrato = tipoContrato;
	}
	public int getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(int tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	
	
	



	public String getSeccion1() {
		return seccion1;
	}

	public void setSeccion1(String seccion1) {
		this.seccion1 = seccion1;
	}

	public String getSeccion2() {
		return seccion2;
	}

	public void setSeccion2(String seccion2) {
		this.seccion2 = seccion2;
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
	
	
	
}
