
package ENTIDAD;

import java.io.Serializable;

public class tipoCentroEducativoC implements Serializable {
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tipoCentroEducativo;
    private String descripcion;
    private String abreviatura;
    private int estadoRegistro;

  
    public int getTipoCentroEducativo() {
        return tipoCentroEducativo;
    }
    public void setTipoCentroEducativo(int tipoCentroEducativo) {
        this.tipoCentroEducativo = tipoCentroEducativo;
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
