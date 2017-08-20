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
public class bscCuadroC {
    private int anio;
    private int mes;
    private double actual;
    private double meta;
    private double porcentaje;
    private String color;

    public bscCuadroC() {
    }

    public bscCuadroC(int anio, int mes, double actual, double meta, double porcentaje, String color) {
        this.anio = anio;
        this.mes = mes;
        this.actual = actual;
        this.meta = meta;
        this.porcentaje = porcentaje;
        this.color = color;
    }
    
    

    /**
     * @return the anio
     */
    public int getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    /**
     * @return the mes
     */
    public int getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * @return the actual
     */
    public double getActual() {
        return actual;
    }

    /**
     * @param actual the actual to set
     */
    public void setActual(double actual) {
        this.actual = actual;
    }

    /**
     * @return the meta
     */
    public double getMeta() {
        return meta;
    }

    /**
     * @param meta the meta to set
     */
    public void setMeta(double meta) {
        this.meta = meta;
    }

    /**
     * @return the porcentaje
     */
    public double getPorcentaje() {
        return porcentaje;
    }

    /**
     * @param porcentaje the porcentaje to set
     */
    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }
}
