/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ENTIDAD;

/**
 *
 * @author Administrador
 */
public class alumnoCursoNotaC {
    private int institucion;
    private String periodo;
    private String carrera;
    private String malla;
    private String curso;
    private String seccion;
    private String alumno;
    private String tipoExamen;
    private double nota;
    private String notaT;
    private String autPersonal;
    private String autObservacion;
    private int estadoRegistro;

    public alumnoCursoNotaC() {
    }

    public alumnoCursoNotaC(int institucion, String periodo, String carrera, String malla, String curso, String seccion, String alumno, String tipoExamen, double nota, String autPersonal, String autObservacion, int estadoRegistro) {
        this.institucion = institucion;
        this.periodo = periodo;
        this.carrera = carrera;
        this.malla = malla;
        this.curso = curso;
        this.seccion = seccion;
        this.alumno = alumno;
        this.tipoExamen = tipoExamen;
        this.nota = nota;
        this.autPersonal = autPersonal;
        this.autObservacion = autObservacion;
        this.estadoRegistro = estadoRegistro;
    }
    
    

    /**
     * @return the institucion
     */
    public int getInstitucion() {
        return institucion;
    }

    /**
     * @param institucion the institucion to set
     */
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }

    /**
     * @return the periodo
     */
    public String getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    /**
     * @return the carrera
     */
    public String getCarrera() {
        return carrera;
    }

    /**
     * @param carrera the carrera to set
     */
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    /**
     * @return the malla
     */
    public String getMalla() {
        return malla;
    }

    /**
     * @param malla the malla to set
     */
    public void setMalla(String malla) {
        this.malla = malla;
    }

    /**
     * @return the curso
     */
    public String getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(String curso) {
        this.curso = curso;
    }

    /**
     * @return the seccion
     */
    public String getSeccion() {
        return seccion;
    }

    /**
     * @param seccion the seccion to set
     */
    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    /**
     * @return the alumno
     */
    public String getAlumno() {
        return alumno;
    }

    /**
     * @param alumno the alumno to set
     */
    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    /**
     * @return the tipoExamen
     */
    public String getTipoExamen() {
        return tipoExamen;
    }

    /**
     * @param tipoExamen the tipoExamen to set
     */
    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    /**
     * @return the nota
     */
    public double getNota() {
        return nota;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(double nota) {
        this.nota = nota;
    }

    /**
     * @return the estadoREgistro
     */
    public int getEstadoRegistro() {
        return estadoRegistro;
    }

    /**
     * @param estadoREgistro the estadoREgistro to set
     */
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    /**
     * @return the autPersonal
     */
    public String getAutPersonal() {
        return autPersonal;
    }

    /**
     * @param autPersonal the autPersonal to set
     */
    public void setAutPersonal(String autPersonal) {
        this.autPersonal = autPersonal;
    }

    /**
     * @return the autObservacion
     */
    public String getAutObservacion() {
        return autObservacion;
    }

    /**
     * @param autObservacion the autObservacion to set
     */
    public void setAutObservacion(String autObservacion) {
        this.autObservacion = autObservacion;
    }

    /**
     * @return the notaT
     */
    public String getNotaT() {
        return notaT;
    }

    /**
     * @param notaT the notaT to set
     */
    public void setNotaT(String notaT) {
        this.notaT = notaT;
    }
    
}
