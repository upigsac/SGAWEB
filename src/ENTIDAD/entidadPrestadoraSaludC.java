package ENTIDAD;

import java.io.Serializable;


public class entidadPrestadoraSaludC implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int eps;
    private String descripcion;
    private String abreviatura;
    private int estadoRegistro;

    /**
     * @return the eps
     */
    public int getEps() {
        return eps;
    }

    /**
     * @param eps the eps to set
     */
    public void setEps(int eps) {
        this.eps = eps;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the abreviatura
     */
    public String getAbreviatura() {
        return abreviatura;
    }

    /**
     * @param abreviatura the abreviatura to set
     */
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    /**
     * @return the estadoRegistro
     */
    public int getEstadoRegistro() {
        return estadoRegistro;
    }

    /**
     * @param estadoRegistro the estadoRegistro to set
     */
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
}
