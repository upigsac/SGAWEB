package ENTIDAD;

import java.io.Serializable;

public class estadoC implements Serializable  {

	private static final long serialVersionUID = 1L;
	private int estado;
    private String descripcion;
    private String abreviatura;
    private int tipo;
    
    public estadoC( int estado,     String descripcion,     String abreviatura,     int tipo) {
    	this.estado=estado;
    	this.descripcion=descripcion;
    	this.abreviatura=abreviatura;
    	this.tipo=tipo;
	}
    
    
    public estadoC() {
		// TODO Auto-generated constructor stub
	}

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
}
