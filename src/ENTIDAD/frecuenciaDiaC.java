
package ENTIDAD;

import java.io.Serializable;

public class frecuenciaDiaC implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private int frecuenciaDia;
    private String descripcion;
    private int estadoRegistro;

    
    public frecuenciaDiaC() {
		// TODO Auto-generated constructor stub
	}
    
    public int getFrecuenciaDia() {
        return frecuenciaDia;
    }
    public void setFrecuenciaDia(int frecuenciaDia) {
        this.frecuenciaDia = frecuenciaDia;
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
