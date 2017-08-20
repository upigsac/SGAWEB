
package  ENTIDAD;

import java.io.Serializable;

public class aulaC implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private int institucion;
    private int sede;
    private String aula;
    private String descripcion;
    private String abreviatura;
    private int tipoAula;
    private int capacidad;
    private int estadoRegistro;
    
    public aulaC() {
		// TODO Auto-generated constructor stub
	}

  
    public int getInstitucion() {
        return institucion;
    }

   
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }

   
    public int getSede() {
        return sede;
    }

    
    public void setSede(int sede) {
        this.sede = sede;
    }

   
    public String getAula() {
        return aula;
    }

   
    public void setAula(String aula) {
        this.aula = aula;
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

   
    public int getTipoAula() {
        return tipoAula;
    }

    
    public void setTipoAula(int tipoAula) {
        this.tipoAula = tipoAula;
    }

 
    public int getCapacidad() {
        return capacidad;
    }

   
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

   
    public int getEstadoRegistro() {
        return estadoRegistro;
    }

    
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
}
