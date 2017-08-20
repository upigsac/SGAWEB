
package ENTIDAD;

import java.io.Serializable;

public class tipoAmonestacionC implements  Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tipoAmonestacion;
    private String descripcion;
    private String abreviatura;
    private int estadoRegistro;

    /**
     * @return the tipoAmonestacion
     */
    public int getTipoAmonestacion() {
        return tipoAmonestacion;
    }

    /**
     * @param tipoAmonestacion the tipoAmonestacion to set
     */
    public void setTipoAmonestacion(int tipoAmonestacion) {
        this.tipoAmonestacion = tipoAmonestacion;
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
