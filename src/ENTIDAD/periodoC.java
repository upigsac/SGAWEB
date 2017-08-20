
package ENTIDAD;

import java.io.Serializable;
import java.util.Date;

public class periodoC implements Serializable{
  
	private static final long serialVersionUID = 1L;
	private int institucion;
    private String periodo;
    private String desPeriodo;
    private String periodoAnt;
    private int ordinario;
    private Date fechaDesde ;
    private Date fechaHasta ;
    private Date fechaMaDesde ;
    private Date fechaMaHasta ;
    private int estadoResgistro;
    
    
    public periodoC() {
		
	}
    

    public int getInstitucion() {
        return institucion;
    }

   
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }

    

    public String getDesPeriodo() {
        return desPeriodo;
    }

    
    public void setDesPeriodo(String desPeriodo) {
        this.desPeriodo = desPeriodo;
    }

   
    public String getPeriodoAnt() {
        return periodoAnt;
    }

   
    public void setPeriodoAnt(String periodoAnt) {
        this.periodoAnt = periodoAnt;
    }

    
    public int getOrdinario() {
        return ordinario;
    }

    
    public void setOrdinario(int ordinario) {
        this.ordinario = ordinario;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

   
    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    
    public Date getFechaHasta() {
        return fechaHasta;
    }

    
    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

  
    public Date getFechaMaDesde() {
        return fechaMaDesde;
    }

  
    public void setFechaMaDesde(Date fechaMaDesde) {
        this.fechaMaDesde = fechaMaDesde;
    }

    
    public Date getFechaMaHasta() {
        return fechaMaHasta;
    }

    
    public void setFechaMaHasta(Date fechaMaHasta) {
        this.fechaMaHasta = fechaMaHasta;
    }

   
    public int getEstadoResgistro() {
        return estadoResgistro;
    }

    
    public void setEstadoResgistro(int estadoResgistro) {
        this.estadoResgistro = estadoResgistro;
    }

   
    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
}
