

package  ENTIDAD;

import java.io.Serializable;

public class tramTipoTramiteC implements Serializable {
 
	private static final long serialVersionUID = 1L;
	private int tipoTramite;
    private String descripcion;
    private String abreviatura;
    private String icono;
    private int estadoRegistro;
    
    
    
    
    public tramTipoTramiteC() {
		
	}


  
    public int getEstadoRegistro() {
        return estadoRegistro;
    }

   
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    
    public int getTipoTramite() {
        return tipoTramite;
    }

 
    public void setTipoTramite(int tipoTramite) {
        this.tipoTramite = tipoTramite;
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



	public String getIcono() {
		return icono;
	}



	public void setIcono(String icono) {
		this.icono = icono;
	}
    
}
