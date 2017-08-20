
package  ENTIDAD;

import java.io.Serializable;
import java.util.Date;

public class alumnoPracticaCronogramaCorteC implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String alumno;
    private String curso;
    private int cronograma;
    private Date fecha;
    private String fut;
    private String practica;
    private String observacion;
    private String autorizado;
    private int estadoRegistro;
    
    public alumnoPracticaCronogramaCorteC() {
		
	}
    
    
    public alumnoPracticaCronogramaCorteC(String alumno,String curso,int cronograma,Date fecha, String fut, String observacion, int estadoRegistro) {
    	this.alumno=alumno;
    	this.curso=curso;
    	this.cronograma=cronograma;
    	this.fecha=fecha;
    	this.fut=fut;
    	this.observacion=observacion;
    	this.estadoRegistro=estadoRegistro;
 	}
    
    
    
    

    
    public String getPractica() {
		return practica;
	}


	public void setPractica(String practica) {
		this.practica = practica;
	}


	public String getAutorizado() {
		return autorizado;
	}


	public void setAutorizado(String autorizado) {
		this.autorizado = autorizado;
	}


	public String getAlumno() {
        return alumno;
    }
    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }
    public String getCurso() {
        return curso;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
    public int getCronograma() {
        return cronograma;
    }
    public void setCronograma(int cronograma) {
        this.cronograma = cronograma;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getFut() {
        return fut;
    }
    public void setFut(String fut) {
        this.fut = fut;
    }
    public String getObservacion() {
        return observacion;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
}
