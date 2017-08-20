

package ENTIDAD;

import java.io.Serializable;

public class tramTramiteOficinaC implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private int tramite;
    private int item;
    private int oficinaEmisor;
    private int oficinaReceptor;
    private int orden;
    private String descripcion;
    private int estadoRegistro;

    public tramTramiteOficinaC() {
    }

    public tramTramiteOficinaC(int tramite, int item, int oficinaEmisor, int oficinaReceptor, int orden, String descripcion, int estadoRegistro) {
        this.tramite = tramite;
        this.item = item;
        this.oficinaEmisor = oficinaEmisor;
        this.oficinaReceptor = oficinaReceptor;
        this.orden = orden;
        this.descripcion = descripcion;
        this.estadoRegistro = estadoRegistro;
    }

    
    
   
    public int getTramite() {
        return tramite;
    }

   
    public void setTramite(int tramite) {
        this.tramite = tramite;
    }

 

    public int getOrden() {
        return orden;
    }

    
    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getEstadoRegistro() {
        return estadoRegistro;
    }

    
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

  
    public String getDescripcion() {
        return descripcion;
    }

   
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    public int getOficinaEmisor() {
        return oficinaEmisor;
    }

   
    public void setOficinaEmisor(int oficinaEmisor) {
        this.oficinaEmisor = oficinaEmisor;
    }

 
    public int getOficinaReceptor() {
        return oficinaReceptor;
    }

  
    public void setOficinaReceptor(int oficinaReceptor) {
        this.oficinaReceptor = oficinaReceptor;
    }

   
    public int getItem() {
        return item;
    }

   
    public void setItem(int item) {
        this.item = item;
    }
}
