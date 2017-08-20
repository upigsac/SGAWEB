
package ENTIDAD;

import java.io.Serializable;

public class tipoAulaC implements Serializable {

	private static final long serialVersionUID = 1L;
	private int tipoAula;
    private String descripcion;
    private String abreviatura;
    private int estadoRegistro;
    public tipoAulaC() {
		// TODO Auto-generated constructor stub
	}

   
    
    
    

    public int getTipoAula() {
		return tipoAula;
	}






	public void setTipoAula(int tipoAula) {
		this.tipoAula = tipoAula;
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
