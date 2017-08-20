

package ENTIDAD;

import java.io.Serializable;
import java.util.Date;

public class tramTramiteRequisitoEntregadoC implements Serializable {

	private static final long serialVersionUID = 1L;
	private String expediente;
    private int requisito;
    private boolean entregado;
    private Date fechaEntrega;
    
    private int estadoRegistro;

    public tramTramiteRequisitoEntregadoC() {
    }

    public tramTramiteRequisitoEntregadoC(String expediente, int requisito,boolean entregado, Date fechaEntrega, int estadoRegistro) {
        this.expediente = expediente;
        this.requisito = requisito;
        this.entregado=entregado;
        this.fechaEntrega = fechaEntrega;
        this.estadoRegistro = estadoRegistro;
    }

  
    
    
    

    public boolean isEntregado() {
		return entregado;
	}

	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}

	public String getExpediente() {
        return expediente;
    }
    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }
    public int getRequisito() {
        return requisito;
    }
    public void setRequisito(int requisito) {
        this.requisito = requisito;
    }
    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
}
