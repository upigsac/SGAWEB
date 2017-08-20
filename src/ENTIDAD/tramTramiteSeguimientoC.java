/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package  ENTIDAD;

import java.io.Serializable;
import java.util.Date;

public class tramTramiteSeguimientoC implements Serializable {
  
    
	private static final long serialVersionUID = 1L;
	private String expediente;
    private int oficina;
    private int item;
    private Date fechaEntrega;   
    private Date horaEntrega;
    private String usuarioEmisor;
    
    private String usuarioReceptor;
    private String asunto;
    private Date fechaResivido;
    private Date horaResivido;
    private String observacion;
    private int estadoRegistro;
    
    public tramTramiteSeguimientoC() {
		
	}
    
	public tramTramiteSeguimientoC(String expediente,int oficina,Date fechaEntrega,Date horaEntrega,String usuarioEmisor,String usuarioReceptor,String asunto,Date fechaResivido,Date horaResivido,int estadoRegistro) {
				this.expediente=expediente;
				this.oficina=oficina;
				this.fechaEntrega=fechaEntrega;
				this.horaEntrega=horaEntrega;
				this.usuarioEmisor=usuarioEmisor;
				this.usuarioReceptor=usuarioReceptor;
				this.asunto=asunto;
				this.fechaResivido=fechaResivido;
				this.horaResivido=horaResivido;
				this.estadoRegistro=estadoRegistro;
	}
    
    

    
    public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getExpediente() {
        return expediente;
    }

    
    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

   
    public int getOficina() {
        return oficina;
    }

    
    public void setOficina(int oficina) {
        this.oficina = oficina;
    }

   
    public int getItem() {
        return item;
    }

    
    public void setItem(int item) {
        this.item = item;
    }

    
    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    
    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

  
    public Date getHoraEntrega() {
        return horaEntrega;
    }

    
    public void setHoraEntrega(Date horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

   
    public String getUsuarioEmisor() {
        return usuarioEmisor;
    }

   
    public void setUsuarioEmisor(String usuarioEmisor) {
        this.usuarioEmisor = usuarioEmisor;
    }

   
    public String getUsuarioReceptor() {
        return usuarioReceptor;
    }

  
    public void setUsuarioReceptor(String usuarioReceptor) {
        this.usuarioReceptor = usuarioReceptor;
    }

   
    public Date getFechaResivido() {
        return fechaResivido;
    }

    
    public void setFechaResivido(Date fechaResivido) {
        this.fechaResivido = fechaResivido;
    }

   
    public Date getHoraResivido() {
        return horaResivido;
    }

    
    public void setHoraResivido(Date horaResivido) {
        this.horaResivido = horaResivido;
    }


    public int getEstadoRegistro() {
        return estadoRegistro;
    }

   
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

   

  
    public String getAsunto() {
        return asunto;
    }

    
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    
}
