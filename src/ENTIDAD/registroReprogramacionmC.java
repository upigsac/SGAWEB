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
public class registroReprogramacionmC {
    private String reprogracion;
    private int institucion;
    private String periodo;
    private String malla;
    private String carrera;
    private String curso;
    private String seccion;
    private int turno;
    private String ciclo;
    private String aula;
    private String cpersonal;
    private Date fechaReprogramacion;
    private Date horaInicio;
    private Date horaFin;
    private String fut;
    private String observacion;
    private int estadoRegistro;

    public registroReprogramacionmC(String reprogracion, int institucion, String periodo, String malla, String carrera, String curso, String seccion, int turno, String ciclo, String aula, String cpersonal, Date fechaReprogramacion, Date horaInicio, Date horaFin, String fut, String observacion, int estadoRegistro) {
        this.reprogracion = reprogracion;
        this.institucion = institucion;
        this.periodo = periodo;
        this.malla = malla;
        this.carrera = carrera;
        this.curso = curso;
        this.seccion = seccion;
        this.turno = turno;
        this.ciclo = ciclo;
        this.aula = aula;
        this.cpersonal = cpersonal;
        this.fechaReprogramacion = fechaReprogramacion;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fut = fut;
        this.observacion = observacion;
        this.estadoRegistro = estadoRegistro;
    }

    public registroReprogramacionmC() {
    }

    /**
     * @return the reprogracion
     */
    public String getReprogracion() {
        return reprogracion;
    }

    /**
     * @param reprogracion the reprogracion to set
     */
    public void setReprogracion(String reprogracion) {
        this.reprogracion = reprogracion;
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
     * @return the turno
     */
    public int getTurno() {
        return turno;
    }

    /**
     * @param turno the turno to set
     */
    public void setTurno(int turno) {
        this.turno = turno;
    }

    /**
     * @return the ciclo
     */
    public String getCiclo() {
        return ciclo;
    }

    /**
     * @param ciclo the ciclo to set
     */
    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    /**
     * @return the aula
     */
    public String getAula() {
        return aula;
    }

    /**
     * @param aula the aula to set
     */
    public void setAula(String aula) {
        this.aula = aula;
    }

    /**
     * @return the cpersonal
     */
    public String getCpersonal() {
        return cpersonal;
    }

    /**
     * @param cpersonal the cpersonal to set
     */
    public void setCpersonal(String cpersonal) {
        this.cpersonal = cpersonal;
    }

    /**
     * @return the fechaReprogramacion
     */
    public Date getFechaReprogramacion() {
        return fechaReprogramacion;
    }

    /**
     * @param fechaReprogramacion the fechaReprogramacion to set
     */
    public void setFechaReprogramacion(Date fechaReprogramacion) {
        this.fechaReprogramacion = fechaReprogramacion;
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
     * @return the horaFin
     */
    public Date getHoraFin() {
        return horaFin;
    }

    /**
     * @param horaFin the horaFin to set
     */
    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    /**
     * @return the fut
     */
    public String getFut() {
        return fut;
    }

    /**
     * @param fut the fut to set
     */
    public void setFut(String fut) {
        this.fut = fut;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
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
