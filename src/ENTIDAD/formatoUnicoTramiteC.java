package ENTIDAD;

import java.io.Serializable;

public class formatoUnicoTramiteC implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fut;
	private String numeroComprobante;
	private String concepto;
	private String persona;
	private String observacion;
	private int estadoRegistro;
	
	
	public formatoUnicoTramiteC() {
		// TODO Auto-generated constructor stub
	}
	
	
	public formatoUnicoTramiteC(String fut,String numeroComprobante,String concepto,String persona,String observacion,int estadoRegistro) {
		this.fut=fut;
		this.numeroComprobante=numeroComprobante;
		this.concepto=concepto;
		this.persona=persona;
		this.observacion=observacion;
		this.estadoRegistro=estadoRegistro;
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	public String getFut() {
		return fut;
	}
	public void setFut(String fut) {
		this.fut = fut;
	}
	public String getNumeroComprobante() {
		return numeroComprobante;
	}
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getPersona() {
		return persona;
	}
	public void setPersona(String persona) {
		this.persona = persona;
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
