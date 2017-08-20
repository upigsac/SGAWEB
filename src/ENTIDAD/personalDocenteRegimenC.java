package ENTIDAD;

import java.io.Serializable;

public class personalDocenteRegimenC  implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int regimen;
	private String descripcion;
	private String abreviatura;
	private int estadoRegistro;
	
	
	public personalDocenteRegimenC() {
		// TODO Auto-generated constructor stub
	}
	
	
	public int getRegimen() {
		return regimen;
	}
	public void setRegimen(int regimen) {
		this.regimen = regimen;
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
