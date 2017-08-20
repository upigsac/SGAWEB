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
public class semanaExamenC {
    private int institucion;
    private String periodo;
    private int grupo;
    private int item;
    private String tipoExamen;
    private boolean suspension;
    private Date fechaInicio;
    private Date fechaFinal;
    private int semanaLibre;
    private String descripcion;
    private int estadoRegistro;

    public semanaExamenC() {
    }

    public semanaExamenC(int institucion, String periodo, int grupo, String tipoExamen, boolean suspension, Date fechaInicio, Date fechaFinal, int semanaLibre, String descripcion, int estadoRegistro) {
        this.institucion = institucion;
        this.periodo = periodo;
        this.grupo = grupo;
        this.tipoExamen = tipoExamen;
        this.suspension = suspension;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.semanaLibre = semanaLibre;
        this.descripcion = descripcion;
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
     * @return the suspension
     */
    public boolean isSuspension() {
        return suspension;
    }

    /**
     * @param suspension the suspension to set
     */
    public void setSuspension(boolean suspension) {
        this.suspension = suspension;
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
     * @return the fechaFinal
     */
    public Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
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
     * @return the semanaLibre
     */
    public int getSemanaLibre() {
        return semanaLibre;
    }

    /**
     * @param semanaLibre the semanaLibre to set
     */
    public void setSemanaLibre(int semanaLibre) {
        this.semanaLibre = semanaLibre;
    }

    /**
     * @return the item
     */
    public int getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(int item) {
        this.item = item;
    }

}
