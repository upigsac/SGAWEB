
package ENTIDAD;

import java.io.Serializable;

public class parentescoC implements Serializable {

	private static final long serialVersionUID = 1L;
private int parentesco;   
   private String descripcion;
   private String abreviatura;
   private int estadoRegistro;

 
    public int getParentesco() {
        return parentesco;
    }

 
    public void setParentesco(int parentesco) {
        this.parentesco = parentesco;
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
