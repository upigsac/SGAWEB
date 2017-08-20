
package  ENTIDAD;

import java.io.Serializable;

public class accesoC implements Serializable{
	
    
	private static final long serialVersionUID = 1L;
	
	
	private String usuario;
    private int programa;
    private int modulo;
    private int tipoSeguridad;
    private int estadoRegistro;
    public accesoC() {
		
	}
    
    public accesoC( String usuario,int programa,int modulo,int tipoSeguridad,int estadoRegistro) {
    	this.usuario=usuario;
    	this.programa=programa;
    	this.modulo=modulo;
    	this.tipoSeguridad=tipoSeguridad;
    	this.estadoRegistro=estadoRegistro;
	}

  
    public String getUsuario() {
        return usuario;
    }

   
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

  
    public int getPrograma() {
        return programa;
    }

  
    public void setPrograma(int programa) {
        this.programa = programa;
    }

   
    public int getModulo() {
        return modulo;
    }

   
    public void setModulo(int modulo) {
        this.modulo = modulo;
    }

    
    public int getTipoSeguridad() {
        return tipoSeguridad;
    }

    
    public void setTipoSeguridad(int tipoSeguridad) {
        this.tipoSeguridad = tipoSeguridad;
    }

   
    public int getEstadoRegistro() {
        return estadoRegistro;
    }

   
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    
}
