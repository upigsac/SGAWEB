
package  ENTIDAD;

import java.io.Serializable;
import java.util.Date;

public class vencimientoC implements Serializable {
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int institucion;
    private String periodo;
    private int vencimiento;
    private String descripcion;
    private String abreviatura;
    private Date fechaInicio;
    private Date fechaFin;
    private int estadoRegistro;
    
    public vencimientoC() {
		// TODO Auto-generated constructor stub
	}
    public vencimientoC( int institucion,String periodo,int vencimiento,String descripcion,String abreviatura,Date fechaInicio,Date fechaFin,int estadoRegistro) {
    	this.institucion=institucion;
    	this.periodo=periodo;
    	this.vencimiento=vencimiento;
    	this.descripcion=descripcion;
    	this.abreviatura=abreviatura;
    	this.fechaInicio=fechaInicio;
    	this.fechaFin=fechaFin;
    	this.estadoRegistro=estadoRegistro;
	}

  
    public int getInstitucion() {
        return institucion;
    }
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }
    public String getPeriodo() {
        return periodo;
    }
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    public int getVencimiento() {
        return vencimiento;
    }
    public void setVencimiento(int vencimiento) {
        this.vencimiento = vencimiento;
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
    public Date getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public Date getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    
}
