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
public class bscIndicadorC extends bscCuadroC{
    private String indicador;
    private String descripcion;
    private String abreviatura;
    private int estadoRegistro;

    public bscIndicadorC() {
    }

    public bscIndicadorC(String indicador, String descripcion, String abreviatura, int estadoRegistro) {
        this.indicador = indicador;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
        this.estadoRegistro = estadoRegistro;
    }

    public bscIndicadorC(String indicador, String descripcion, String abreviatura, int estadoRegistro, int anio, int mes, double actual, double meta, double porcentaje, String color) {
        super(anio, mes, actual, meta, porcentaje, color);
        this.indicador = indicador;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
        this.estadoRegistro = estadoRegistro;
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
     * @return the indicador
     */
    public String getIndicador() {
        return indicador;
    }

    /**
     * @param indicador the indicador to set
     */
    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }
}
