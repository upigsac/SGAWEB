package ENTIDAD;

import java.io.Serializable;

public class comisionC implements Serializable {

	private static final long serialVersionUID = 1L;
	private int comision;
    private String descripcion;
    private String abreviatura;
    private int estadoRegistro;

    
    public comisionC() {
		// TODO Auto-generated constructor stub
	}
    
    public int getComision() {
        return comision;
    }
    public void setComision(int comision) {
        this.comision = comision;
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
