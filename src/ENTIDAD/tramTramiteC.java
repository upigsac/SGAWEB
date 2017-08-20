
package ENTIDAD;

import java.io.Serializable;

public class tramTramiteC implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int institucion;
    private int tramite;
    private String descripcion;
    private String abreviatura;
    private int tiempoEntrega;
    private int tipoTramite;
    private int estadoRegistro;

    public tramTramiteC() {
    }

    public tramTramiteC(int institucion, int tramite, String descripcion, String abreviatura, int tiempoEntrega, int tipoTramite, int estadoRegistro) {
        this.institucion = institucion;
        this.tramite = tramite;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
        this.tiempoEntrega = tiempoEntrega;
        this.tipoTramite = tipoTramite;
        this.estadoRegistro = estadoRegistro;
    }
    
   
    public int getInstitucion() {
        return institucion;
    }

    
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }

 
    public int getTramite() {
        return tramite;
    }

   
    public void setTramite(int tramite) {
        this.tramite = tramite;
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

   
    public int getTiempoEntrega() {
        return tiempoEntrega;
    }

    
    public void setTiempoEntrega(int tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    
    public int getTipoTramite() {
        return tipoTramite;
    }

  
    public void setTipoTramite(int tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

  
    
}
