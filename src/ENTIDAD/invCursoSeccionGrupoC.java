/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ENTIDAD;

import java.util.Date;

/**
 *
 * @author Administrador
 */
public class invCursoSeccionGrupoC {
    private int institucion;
    private String periodo;
    private String carrera;
    private String malla;
    private String curso;
    private String seccion;
    private int grupoExamen;
    private String tema;
    private int vacanteMaximo;
    private int vacanteActual;
    private Date fechaPonente;
    private Date fechaAsistente;
    private Date horaInicio;
    private String modalidad;
    private int estadoRegistro;

    public invCursoSeccionGrupoC() {
    }

    public invCursoSeccionGrupoC(int institucion, String periodo, String carrera, String malla, String curso, String seccion, int grupoExamen, String tema, int vacanteMaximo, int vacanteActual, int estadoRegistro) {
        this.institucion = institucion;
        this.periodo = periodo;
        this.carrera = carrera;
        this.malla = malla;
        this.curso = curso;
        this.seccion = seccion;
        this.grupoExamen = grupoExamen;
        this.tema = tema;
        this.vacanteMaximo = vacanteMaximo;
        this.vacanteActual = vacanteActual;
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
     * @return the tema
     */
    public String getTema() {
        return tema;
    }

    /**
     * @param tema the tema to set
     */
    public void setTema(String tema) {
        this.tema = tema;
    }

    /**
     * @return the vacanteMaximo
     */
    public int getVacanteMaximo() {
        return vacanteMaximo;
    }

    /**
     * @param vacanteMaximo the vacanteMaximo to set
     */
    public void setVacanteMaximo(int vacanteMaximo) {
        this.vacanteMaximo = vacanteMaximo;
    }

    /**
     * @return the vacanteActual
     */
    public int getVacanteActual() {
        return vacanteActual;
    }

    /**
     * @param vacanteActual the vacanteActual to set
     */
    public void setVacanteActual(int vacanteActual) {
        this.vacanteActual = vacanteActual;
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

    /**
     * @return the fechaPonente
     */
    public Date getFechaPonente() {
        return fechaPonente;
    }

    /**
     * @param fechaPonente the fechaPonente to set
     */
    public void setFechaPonente(Date fechaPonente) {
        this.fechaPonente = fechaPonente;
    }

    /**
     * @return the fechaAsistente
     */
    public Date getFechaAsistente() {
        return fechaAsistente;
    }

    /**
     * @param fechaAsistente the fechaAsistente to set
     */
    public void setFechaAsistente(Date fechaAsistente) {
        this.fechaAsistente = fechaAsistente;
    }

    /**
     * @return the horaInicio
     */
    public Date getHoraInicio() {
        return horaInicio;
    }

    /**
     * @param horaInicio the horaInicio to set
     */
    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * @return the modalidad
     */
    public String getModalidad() {
        return modalidad;
    }

    /**
     * @param modalidad the modalidad to set
     */
    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }
    
    
}
