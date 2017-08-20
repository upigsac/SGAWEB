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
public class cronogramaAcademicoVencimientoC {
        private int institucion;
        private String periodo;
        private int vencimiento;
        private Date fechaInicio;
        private String titulo;
        private int semana;
        private int estadoRegistro;

    public cronogramaAcademicoVencimientoC() {
    }

    public cronogramaAcademicoVencimientoC(int institucion, String periodo, int vencimiento, Date fechaInicio, String titulo, int semana, int estadoRegistro) {
        this.institucion = institucion;
        this.periodo = periodo;
        this.vencimiento = vencimiento;
        this.fechaInicio = fechaInicio;
        this.titulo = titulo;
        this.semana = semana;
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
     * @return the vencimiento
     */
    public int getVencimiento() {
        return vencimiento;
    }

    /**
     * @param vencimiento the vencimiento to set
     */
    public void setVencimiento(int vencimiento) {
        this.vencimiento = vencimiento;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the semana
     */
    public int getSemana() {
        return semana;
    }

    /**
     * @param semana the semana to set
     */
    public void setSemana(int semana) {
        this.semana = semana;
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
