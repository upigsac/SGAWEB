package ENTIDAD;

import java.io.Serializable;


public class estadoCivilC implements Serializable{
   
	private static final long serialVersionUID = 1L;
	private int estadoCivil;
    private String descripcion;
    private String abreviatura;
    private int estadoRegistro;
    
    
    public estadoCivilC() {
	
	}

  
    public int getEstadoCivil() {
        return estadoCivil;
    }

   
    public void setEstadoCivil(int estadoCivil) {
        this.estadoCivil = estadoCivil;
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
