package ENTIDAD;

import java.io.Serializable;

public class conceptoInstitucionDescuentoC implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int institucion;
	private String periodo;
	private String concepto;
	private int tipoConcepto;
	private String descuento;
	private String centroCosto;
	private int estadoRegistro;
	
	public conceptoInstitucionDescuentoC() {
		// TODO Auto-generated constructor stub
	}
	
	public conceptoInstitucionDescuentoC(int institucion,String periodo,String concepto,int tipoConcepto,String descuento,String centroCosto,int estadoRegistro) {
		this.institucion=institucion;
		this.periodo=periodo;
		this.concepto=concepto;
		this.tipoConcepto=tipoConcepto;
		this.descuento=descuento;
		this.centroCosto=centroCosto;
		this.estadoRegistro=estadoRegistro;
		
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
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public int getTipoConcepto() {
		return tipoConcepto;
	}
	public void setTipoConcepto(int tipoConcepto) {
		this.tipoConcepto = tipoConcepto;
	}
	public String getDescuento() {
		return descuento;
	}
	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}
	public String getCentroCosto() {
		return centroCosto;
	}
	public void setCentroCosto(String centroCosto) {
		this.centroCosto = centroCosto;
	}
	public int getEstadoRegistro() {
		return estadoRegistro;
	}
	public void setEstadoRegistro(int estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
}
