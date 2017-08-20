
package ENTIDAD;

import java.io.Serializable;

public class firmaCertificacionCursoC implements Serializable {
	
   
	private static final long serialVersionUID = 1L;
	private int institucion;
    private String periodo;
    private String carrera;
    private String malla;
    private String curso;
    private String seccion;
    private String persona;
    private String cargo;
    private String abreviatura;
    private int estadoRegistro;
    
    public firmaCertificacionCursoC() {
		// TODO Auto-generated constructor stub
	}
    
    public firmaCertificacionCursoC(int institucion,String periodo,String carrera,String malla,String curso,String seccion,String persona,String cargo,String abreviatura,int estadoRegistro) {
    	this.institucion=institucion;
    	this.periodo=periodo;
    	this.carrera=carrera;
    	this.malla=malla;
    	this.curso=curso;
    	this.seccion=seccion;
    	this.persona=persona;
    	this.cargo=cargo;
    	this.abreviatura=abreviatura;
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

	public String getCarrera() {
        return carrera;
    }

   
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    
    public String getMalla() {
        return malla;
    }

  
    public void setMalla(String malla) {
        this.malla = malla;
    }

    
    public String getCurso() {
        return curso;
    }

   
    public void setCurso(String curso) {
        this.curso = curso;
    }

 
    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

   
    public String getPersona() {
        return persona;
    }

  
    public void setPersona(String persona) {
        this.persona = persona;
    }

 
    public String getCargo() {
        return cargo;
    }

   
    public void setCargo(String cargo) {
        this.cargo = cargo;
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
