
package ENTIDAD;

import java.io.Serializable;

public class horMallaCurricularCursoActC implements Serializable{
   
	private static final long serialVersionUID = 1L;
	private int institucion;
    private String carrera;
    private String malla;
    private String curso;
    private int creditos;
    private int nivelModular;
    private int horasTeoria;
    private int horasPracticas;
    private int tipoCurso;
    private int estadoRegistro;
  
    public horMallaCurricularCursoActC() {
		// TODO Auto-generated constructor stub
	}
    
    public horMallaCurricularCursoActC(	 int institucion, String carrera, String malla,String curso, int creditos, int nivelModular,int horasTeoria,  int horasPracticas,int tipoCurso, int estadoRegistro) {
		    this.institucion=institucion;
		    this.carrera=carrera;
		    this.malla=malla;
		    this.curso=curso;
		    this.creditos=creditos;
		    this.nivelModular=nivelModular;
		    this.horasTeoria=horasTeoria;
		    this.horasPracticas=horasPracticas;
		    this.tipoCurso=tipoCurso;
		    this.estadoRegistro=estadoRegistro;
    
      
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
    public int getCreditos() {
        return creditos;
    }
    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
    public int getNivelModular() {
        return nivelModular;
    }
    public void setNivelModular(int nivelModular) {
        this.nivelModular = nivelModular;
    }
    public int getHorasTeoria() {
        return horasTeoria;
    }
    public void setHorasTeoria(int horasTeoria) {
        this.horasTeoria = horasTeoria;
    }
    public int getHorasPracticas() {
        return horasPracticas;
    }
    public void setHorasPracticas(int horasPracticas) {
        this.horasPracticas = horasPracticas;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    public int getTipoCurso() {
        return tipoCurso;
    }
    public void setTipoCurso(int tipoCurso) {
        this.tipoCurso = tipoCurso;
    }
}
