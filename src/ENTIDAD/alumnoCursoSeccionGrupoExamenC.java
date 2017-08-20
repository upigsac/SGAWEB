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
public class alumnoCursoSeccionGrupoExamenC {
    private int institucion;
    private String periodo;
    private String carrera;
    private String malla;
    private String curso;
    private String seccion;
    private String alumno;
    private int grupo;
    private String tipoExamen;
    private int grupoExamen;
    private int estadoRegistro;

    public alumnoCursoSeccionGrupoExamenC() {
    }

    public alumnoCursoSeccionGrupoExamenC(int institucion, String periodo, String carrera, String malla, String curso, String seccion, String alumno, int grupo, String tipoExamen, int grupoExamen, int estadoRegistro) {
        this.institucion = institucion;
        this.periodo = periodo;
        this.carrera = carrera;
        this.malla = malla;
        this.curso = curso;
        this.seccion = seccion;
        this.alumno = alumno;
        this.grupo = grupo;
        this.tipoExamen = tipoExamen;
        this.grupoExamen = grupoExamen;
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
     * @return the grupo
     */
    public int getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(int grupo) {
        this.grupo = grupo;
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
     * @return the grupoExamen
     */
    public int getGrupoExamen() {
        return grupoExamen;
    }

    /**
     * @param grupoExamen the grupoExamen to set
     */
    public void setGrupoExamen(int grupoExamen) {
        this.grupoExamen = grupoExamen;
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
