
package  ENTIDAD;

import java.io.Serializable;

public class cicloC   implements Serializable {
	
	
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ciclo;
    private String descripcion;
    private int estadoRegistro;

  
    public int getCiclo() {
        return ciclo;
    }
    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
}
