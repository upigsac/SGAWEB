
package ENTIDAD;

import java.io.Serializable;

public class alumnoCursoSeccionC implements Serializable {
	
	
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int institucion;
    private String periodo;
    private String carrera;
    private String malla;
    private String curso;
    private String seccion;
    private String alumno;
    private String grupo;
    private Double promedio;
    private String resolucion;
    private String observacion;
    private boolean convalidado;
    
    private int estadoRegistro;

    public alumnoCursoSeccionC() {
    }

    public alumnoCursoSeccionC(int institucion, String periodo, String carrera, String malla, String curso, String seccion, String alumno, String grupo, Double promedio, int estadoRegistro) {
        this.institucion = institucion;
        this.periodo = periodo;
        this.carrera = carrera;
        this.malla = malla;
        this.curso = curso;
        this.seccion = seccion;
        this.alumno = alumno;
        this.grupo = grupo;
        this.promedio = promedio;
        this.estadoRegistro = estadoRegistro;
    }
    
    
    
    
    
    public boolean isConvalidado() {
		return convalidado;
	}

	public void setConvalidado(boolean convalidado) {
		this.convalidado = convalidado;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getResolucion() {
		return resolucion;
	}

	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
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
    public String getAlumno() {
        return alumno;
    }
    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }
    public String getGrupo() {
        return grupo;
    }
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
    public Double getPromedio() {
        return promedio;
    }
    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    public int getInstitucion() {
        return institucion;
    }
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }
}
