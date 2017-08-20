
package ENTIDAD;

import java.io.Serializable;

public class carrerasC implements Serializable {
    
 
	private static final long serialVersionUID = 1L;
	private String carrera;
    private String descripcion;
    private String abreviatura;
    private String color;
    private int estadoRegistro;

    public carrerasC() {
    }
    
    
    public carrerasC(	 String carrera,     String descripcion,     String abreviatura,  String color,   int estadoRegistro   ) {
    	this.carrera=carrera;
    	this.descripcion=descripcion;
    	this.abreviatura=abreviatura;
    	this.color=color;
    	this.estadoRegistro=estadoRegistro;
    }
   
    
    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
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
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    
    
    
    
}
