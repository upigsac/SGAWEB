
package  ENTIDAD;

import java.io.Serializable;

public class asignacionC implements Serializable {

	private static final long serialVersionUID = 1L;
	private int asignacion;
    private String descripcion;
    private String abreviatura;
    private int estadoRegistro;

    public asignacionC() {
		// TODO Auto-generated constructor stub
	}
    
    
    public int getAsignacion() {
        return asignacion;
    }
    public void setAsignacion(int asignacion) {
        this.asignacion = asignacion;
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
