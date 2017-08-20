
package ENTIDAD;

import java.io.Serializable;

public class tramRequisitoC  implements Serializable {
   
	private static final long serialVersionUID = 1L;
	private int requisito;
    private String descripcion;
    private String abreviatura;
    private boolean entregado;
    private int estadoRegistro;

    public tramRequisitoC() {
    }

    public tramRequisitoC(int requisito, String descripcion, String abreviatura, int estadoRegistro) {
        this.requisito = requisito;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
        this.estadoRegistro = estadoRegistro;
    }

   

  
    public boolean isEntregado() {
		return entregado;
	}

	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
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

    
    public int getRequisito() {
        return requisito;
    }

   
    public void setRequisito(int requisito) {
        this.requisito = requisito;
    }

  
}
