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
public class encuestaCursoSeccionDocenteC {
    private int institucion;
    private String periodo;
    private String carrera;
    private String malla;
    private String curso;
    private String seccion;
    private String grupo;
    private String personal;
    private String alumno;
    private int encuesta;
    private int grupoEncuesta;
    private int pregunta;
    private int respuesta;
    private int estadoRegistro;

    public encuestaCursoSeccionDocenteC() {
    }

    public encuestaCursoSeccionDocenteC(int institucion, String periodo, String carrera, String malla, String curso, String seccion, String grupo, String personal, String alumno, int encuesta, int grupoEncuesta, int pregunta, int respuesta, int estadoRegistro) {
        this.institucion = institucion;
        this.periodo = periodo;
        this.carrera = carrera;
        this.malla = malla;
        this.curso = curso;
        this.seccion = seccion;
        this.grupo = grupo;
        this.personal = personal;
        this.alumno = alumno;
        this.encuesta = encuesta;
        this.grupoEncuesta = grupoEncuesta;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
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
     * @return the grupo
     */
    public String getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    /**
     * @return the personal
     */
    public String getPersonal() {
        return personal;
    }

    /**
     * @param personal the personal to set
     */
    public void setPersonal(String personal) {
        this.personal = personal;
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
     * @return the encuesta
     */
    public int getEncuesta() {
        return encuesta;
    }

    /**
     * @param encuesta the encuesta to set
     */
    public void setEncuesta(int encuesta) {
        this.encuesta = encuesta;
    }

    /**
     * @return the grupoEncuesta
     */
    public int getGrupoEncuesta() {
        return grupoEncuesta;
    }

    /**
     * @param grupoEncuesta the grupoEncuesta to set
     */
    public void setGrupoEncuesta(int grupoEncuesta) {
        this.grupoEncuesta = grupoEncuesta;
    }

    /**
     * @return the pregunta
     */
    public int getPregunta() {
        return pregunta;
    }

    /**
     * @param pregunta the pregunta to set
     */
    public void setPregunta(int pregunta) {
        this.pregunta = pregunta;
    }

    /**
     * @return the respuesta
     */
    public int getRespuesta() {
        return respuesta;
    }

    /**
     * @param respuesta the respuesta to set
     */
    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }

    /**
     * @return the estadoRegistro
     */
    public int getEstadoRegistro() {
        return estadoRegistro;
    }

    /**
     * @param estadoRegistro the estadoRegistro to set
     */
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
            
}
