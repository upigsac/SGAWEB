/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package  ENTIDAD;

import java.io.Serializable;

public class ubigeoProvinciaC implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String provincia;
    private String departamento;
    private String descripcion;
    private String abreviatura;
    private int estado_registro;

    /**
     * @return the provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * @param provincia the provincia to set
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * @return the departamento
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * @param departamento the departamento to set
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
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
     * @return the estado_registro
     */
    public int getEstado_registro() {
        return estado_registro;
    }

    /**
     * @param estado_registro the estado_registro to set
     */
    public void setEstado_registro(int estado_registro) {
        this.estado_registro = estado_registro;
    }
}
