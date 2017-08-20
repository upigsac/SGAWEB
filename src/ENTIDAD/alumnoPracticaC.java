
package ENTIDAD;

import java.util.Date;


public class alumnoPracticaC {
	
	
    private String alumno;
    private String curso;
    private int oficina;
    private String tutor; 
    private Date fechaInicio;
    private String observacion;
    private Date fechaInforme;
    private String informe;
    private Date fechaConstancia;
    private Date fechaPlan;
    private int situacion;
    private int estadoRegistro;

    public alumnoPracticaC() {
    }

    public alumnoPracticaC(String alumno, String curso, int oficina, String tutor, String observacion, Date fechaInicio, Date fechaInforme, String informe, int estadoRegistro) {
        this.alumno = alumno;
        this.curso = curso;
        this.oficina = oficina;
        this.tutor = tutor;
        this.observacion = observacion;
        this.fechaInicio=fechaInicio;
        this.fechaInforme = fechaInforme;
        this.informe = informe;
        this.estadoRegistro = estadoRegistro;
    }

    
    
    
    


 
 
    
    


	public Date getFechaPlan() {
		return fechaPlan;
	}

	public void setFechaPlan(Date fechaPlan) {
		this.fechaPlan = fechaPlan;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public int getSituacion() {
		return situacion;
	}

	public void setSituacion(int situacion) {
		this.situacion = situacion;
	}

	public Date getFechaConstancia() {
		return fechaConstancia;
	}

	public void setFechaConstancia(Date fechaConstancia) {
		this.fechaConstancia = fechaConstancia;
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


    public int getOficina() {
        return oficina;
    }

   
    public void setOficina(int oficina) {
        this.oficina = oficina;
    }

  
    public String getTutor() {
        return tutor;
    }

 
    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

 
    public int getEstadoRegistro() {
        return estadoRegistro;
    }

   
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

 
    public String getInforme() {
        return informe;
    }

   
    public void setInforme(String informe) {
        this.informe = informe;
    }

  
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

 
    public Date getFechaInforme() {
        return fechaInforme;
    }

    public void setFechaInforme(Date fechaInforme) {
        this.fechaInforme = fechaInforme;
    }
}
