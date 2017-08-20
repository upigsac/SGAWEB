
package ENTIDAD;

import java.io.Serializable;
import java.util.Date;

public class alumnoInformePracticaC  implements Serializable {
	
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String alumno;
    private String curso;
    private int item;
    private Date fecha;
    private Date fechaRevisado;
    private String informe;
    private int tipo;
    private String ruta;
    private boolean aprobado;
    private int estadoRegistro;

    public alumnoInformePracticaC() {
    }

    public alumnoInformePracticaC(String alumno, String curso,int tipo, int item, Date fecha, String informe, String ruta, int estadoRegistro) {
        this.alumno = alumno;
        this.curso = curso;
        this.tipo=tipo;
        this.item = item;
        this.fecha = fecha;
        this.informe = informe;
        
        this.ruta = ruta;
        this.estadoRegistro = estadoRegistro;
    }
    
    
    
    
    
    
    
    
    
    public Date getFechaRevisado() {
		return fechaRevisado;
	}

	public void setFechaRevisado(Date fechaRevisado) {
		this.fechaRevisado = fechaRevisado;
	}

	public boolean isAprobado() {
		return aprobado;
	}

	public void setAprobado(boolean aprobado) {
		this.aprobado = aprobado;
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
    
    
    public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getItem() {
        return item;
    }
    public void setItem(int item) {
        this.item = item;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getInforme() {
        return informe;
    }
    public void setInforme(String informe) {
        this.informe = informe;
    }
    public String getRuta() {
        return ruta;
    }
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }


    
}
