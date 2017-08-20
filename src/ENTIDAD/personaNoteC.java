package ENTIDAD;

import java.io.Serializable;

public class personaNoteC implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String persona;
	private String color;	
	
	
	
	private int note;
	private String descripcion;
	private int top;
	private int left;
	private int estadoRegistro;
	
	
	
	public personaNoteC() {
		// TODO Auto-generated constructor stub
	}
	
	public personaNoteC(String persona,String color, int note,String descripcion,int top,int left,int estadoRegistro) {
		this.persona=persona;
		this.color=color;
		this.note=note;
		this.descripcion=descripcion;
		this.top=top;
		this.left=left;
		this.left=left;
		this.estadoRegistro=estadoRegistro;
	}
	
	
	
	public String getPersona() {
		return persona;
	}
	public void setPersona(String persona) {
		this.persona = persona;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getNote() {
		return note;
	}
	public void setNote(int note) {
		this.note = note;
	}
	
	
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getEstadoRegistro() {
		return estadoRegistro;
	}
	public void setEstadoRegistro(int estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
}
