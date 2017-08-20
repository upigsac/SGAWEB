package ENTIDAD;

import java.io.Serializable;

public class conceptoInstitucionC implements Serializable {

	private static final long serialVersionUID = 1L;
	private int institucion;
	private String concepto;
	private int tipoConcepto;
	private boolean categoria;
	private int estadoRegistro;
	
	public conceptoInstitucionC() {
		// TODO Auto-generated constructor stub
	}
	
	public conceptoInstitucionC( int institucion,String concepto,int tipoConcepto,boolean categoria,int estadoRegistro	) 
	{
		this.institucion=institucion;
		this.concepto=concepto;
		this.tipoConcepto=tipoConcepto;
		this.categoria=categoria;
		this.estadoRegistro=estadoRegistro;
	}
	
	
	
	public int getInstitucion() {
		return institucion;
	}
	public void setInstitucion(int institucion) {
		this.institucion = institucion;
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
	public boolean isCategoria() {
		return categoria;
	}
	public void setCategoria(boolean categoria) {
		this.categoria = categoria;
	}
	public int getEstadoRegistro() {
		return estadoRegistro;
	}
	public void setEstadoRegistro(int estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
	
}
