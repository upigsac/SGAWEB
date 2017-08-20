package ENTIDAD;

import java.io.Serializable;

public class centroCostoC  implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String centroCosto;
	private String centroCostoPadre;
	private int nivel;
	private String descripcion; 
	private String abreviatura;
	private int estadoRegistro;
	
	public centroCostoC() {

	}
	
	public centroCostoC( String centroCosto,String centroCostoPadre,int nivel,String descripcion, String abreviatura, int estadoRegistro) {
		this.centroCosto=centroCosto;
		this.centroCostoPadre=centroCostoPadre;
		this.nivel=nivel;
		this.descripcion=descripcion;
		this.abreviatura=abreviatura;
		this.estadoRegistro=estadoRegistro;
		
	}
	
	
	public String getCentroCosto() {
		return centroCosto;
	}
	public void setCentroCosto(String centroCosto) {
		this.centroCosto = centroCosto;
	}
	public String getCentroCostoPadre() {
		return centroCostoPadre;
	}
	public void setCentroCostoPadre(String centroCostoPadre) {
		this.centroCostoPadre = centroCostoPadre;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	public int getEstadoRegistro() {
		return estadoRegistro;
	}
	public void setEstadoRegistro(int estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
}
