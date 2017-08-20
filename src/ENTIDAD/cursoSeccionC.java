package ENTIDAD;

import java.io.Serializable;

public class cursoSeccionC implements Serializable{
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
    private String grupo;
    private int vacantesMaximas;
    private int vacantesMinimas;
    private int vacantesActuales;
    private int tipoCertificado;
    private int estadoRegistro;
    
    
    
    
    public cursoSeccionC() {
		// TODO Auto-generated constructor stub
	}
    
    public cursoSeccionC( int institucion, String periodo,String carrera, String malla,String curso,String seccion, String grupo,int vacantesMaximas,int vacantesMinimas,int vacantesActuales,int tipoCertificado,int estadoRegistro) {
    	this.institucion=institucion;
    	this.periodo=periodo;
    	this.carrera=carrera;
    	this.malla=malla;
    	this.curso=curso;
    	this.seccion=seccion;
    	this.grupo=grupo;
    	this.vacantesMaximas=vacantesMaximas;
    	this.vacantesMinimas=vacantesMinimas;
    	this.vacantesActuales=vacantesActuales;
    	this.tipoCertificado=tipoCertificado;
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

    public String getGrupo() {
        return grupo;
    }

 
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public int getVacantesMaximas() {
        return vacantesMaximas;
    }

    public void setVacantesMaximas(int vacantesMaximas) {
        this.vacantesMaximas = vacantesMaximas;
    }

    public int getVacantesMinimas() {
        return vacantesMinimas;
    }

    public void setVacantesMinimas(int vacantesMinimas) {
        this.vacantesMinimas = vacantesMinimas;
    }

    public int getVacantesActuales() {
        return vacantesActuales;
    }

    public void setVacantesActuales(int vacantesActuales) {
        this.vacantesActuales = vacantesActuales;
    }

    public int getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public int getTipoCertificado() {
        return tipoCertificado;
    }

   
    public void setTipoCertificado(int tipoCertificado) {
        this.tipoCertificado = tipoCertificado;
    }
    
}
