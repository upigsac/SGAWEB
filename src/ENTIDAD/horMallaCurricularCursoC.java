
package ENTIDAD;

import java.io.Serializable;

public class horMallaCurricularCursoC implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int institucion;
    private String carrera;
    private String malla;
    private String curso;
    private String curso_padre;
    private int estadoRegistro;
    
    public horMallaCurricularCursoC() {
		// TODO Auto-generated constructor stub
	}

   
    public int getInstitucion() {
        return institucion;
    }
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
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
    public String getCurso_padre() {
        return curso_padre;
    }
    public void setCurso_padre(String curso_padre) {
        this.curso_padre = curso_padre;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    
}
