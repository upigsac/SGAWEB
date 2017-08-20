package ENTIDAD;
public class cuadroAsistenciaC {
       private int institucion;
        private String periodo;
        private String carrera;
        private String malla;
        private String curso;
        private String desCurso;
        private String seccion;
        private String desSeccion;
        private String alumno;
        private int semana;
        private int clase;
        
        private int asistencia;
        private int porcentaje;

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
    public String getDesCurso() {
        return desCurso;
    }
    public void setDesCurso(String desCurso) {
        this.desCurso = desCurso;
    }
    public String getSeccion() {
        return seccion;
    }
    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }
    public String getDesSeccion() {
        return desSeccion;
    }
    public void setDesSeccion(String desSeccion) {
        this.desSeccion = desSeccion;
    }
    
    

    public String getAlumno() {
		return alumno;
	}
	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}
	
	
	
	public int getClase() {
        return clase;
    }
    public void setClase(int clase) {
        this.clase = clase;
    }
    public int getAsistencia() {
        return asistencia;
    }
    public void setAsistencia(int asistencia) {
        this.asistencia = asistencia;
    }
    public int getPorcentaje() {
        return porcentaje;
    }
    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
    public int getSemana() {
        return semana;
    }
    public void setSemana(int semana) {
        this.semana = semana;
    }

}
