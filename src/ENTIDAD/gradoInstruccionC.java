/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ENTIDAD;

import java.io.Serializable;

/**
 *
 * @author Administrador
 */
public class gradoInstruccionC implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int gradoInstruccion;
    private String descripcion;
    private String abreviatura;
    private int estadoregistro;

    public gradoInstruccionC() {
    }

    public gradoInstruccionC(int gradoInstruccion, String descripcion, String abreviatura, int estadoregistro) {
        this.gradoInstruccion = gradoInstruccion;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
        this.estadoregistro = estadoregistro;
    }
    

    /**
     * @return the gradoInstruccion
     */
    public int getGradoInstruccion() {
        return gradoInstruccion;
    }

    /**
     * @param gradoInstruccion the gradoInstruccion to set
     */
    public void setGradoInstruccion(int gradoInstruccion) {
        this.gradoInstruccion = gradoInstruccion;
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
     * @return the abreviatura
     */
    public String getAbreviatura() {
        return abreviatura;
    }

    /**
     * @param abreviatura the abreviatura to set
     */
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    /**
     * @return the estadoregistro
     */
    public int getEstadoregistro() {
        return estadoregistro;
    }

    /**
     * @param estadoregistro the estadoregistro to set
     */
    public void setEstadoregistro(int estadoregistro) {
        this.estadoregistro = estadoregistro;
    }
}
