
package ENTIDAD;

import java.io.Serializable;


public class seccionC implements Serializable{

  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int institucion;
    private String seccion;
    private String descripcion;
    private int estadoRegistro;
    
    /**
     * @return the institucion
     */
    public int getInstitucion() {
        return institucion;
    }

    /**
     * @param institucion the institucion to set
     */
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }

    /**
     * @return the seccion
     */
    public String getSeccion() {
        return seccion;
    }

    /**
     * @param seccion the seccion to set
     */
    public void setSeccion(String seccion) {
        this.seccion = seccion;
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
    
      public seccionC(int institucion, String seccion, String descripcion, int estadoRegistro) {
        this.institucion = institucion;
        this.seccion = seccion;
        this.descripcion = descripcion;
        this.estadoRegistro = estadoRegistro;
    }
    public seccionC() {
        
    }
}
