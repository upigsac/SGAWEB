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
public class cronogramaAcademicoVencimientoModuloC {
    private int institucion;
    private String periodo;
    private int vencimiento;
    private int modulo;
    private int semanas;
    private String descripcion;
    private Date fechaInicio;
    private String pieModulo;
    private int estadoRegistro;

    public cronogramaAcademicoVencimientoModuloC() {
    }

    public cronogramaAcademicoVencimientoModuloC(int institucion, String periodo, int vencimiento, int modulo, int semanas, String descripcion, Date fechaInicio, String pieModulo, int estadoRegistro) {
        this.institucion = institucion;
        this.periodo = periodo;
        this.vencimiento = vencimiento;
        this.modulo = modulo;
        this.semanas = semanas;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.pieModulo = pieModulo;
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
     * @return the semanas
     */
    public int getSemanas() {
        return semanas;
    }

    /**
     * @param semanas the semanas to set
     */
    public void setSemanas(int semanas) {
        this.semanas = semanas;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
     * @return the modulo
     */
    public int getModulo() {
        return modulo;
    }

    /**
     * @param modulo the modulo to set
     */
    public void setModulo(int modulo) {
        this.modulo = modulo;
    }

    /**
     * @return the pieModulo
     */
    public String getPieModulo() {
        return pieModulo;
    }

    /**
     * @param pieModulo the pieModulo to set
     */
    public void setPieModulo(String pieModulo) {
        this.pieModulo = pieModulo;
    }
}
