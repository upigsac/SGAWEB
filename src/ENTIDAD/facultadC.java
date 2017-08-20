package ENTIDAD;

import java.io.Serializable;

public class facultadC implements Serializable {
	
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int institucion;
    private int falcutad;
    private String descripcion;
    private String abreviatura;
    private int estado;
    
    public facultadC() {
		// TODO Auto-generated constructor stub
	}

    
    public int getInstitucion() {
        return institucion;
    }
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }
    public int getFalcutad() {
        return falcutad;
    }
    public void setFalcutad(int falcutad) {
        this.falcutad = falcutad;
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
    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }
}
