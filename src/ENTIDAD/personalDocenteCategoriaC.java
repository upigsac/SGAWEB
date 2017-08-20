package ENTIDAD;

import java.io.Serializable;

public class personalDocenteCategoriaC implements Serializable {


	private static final long serialVersionUID = 1L;
	private int categoria;
	private String descripcion;
	private String abreviatura;
	private int estadoRegistro;
	
	public personalDocenteCategoriaC() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
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
