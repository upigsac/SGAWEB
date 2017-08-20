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
public class bscPerpestivaObjetivoIndicadorDetalleC extends bscCuadroC {
    private int perpestiva;
    private String objetivo;
    private String indicador;
    private int anio;
    private int mes;
    private double actual;
    private double meta;
    private int estadoRegistro;

    public bscPerpestivaObjetivoIndicadorDetalleC() {
    }

    public bscPerpestivaObjetivoIndicadorDetalleC(int perpestiva, String objetivo, String indicador, int anio, int mes, double actual, double meta, int estadoRegistro) {
        this.perpestiva = perpestiva;
        this.objetivo = objetivo;
        this.indicador = indicador;
        this.anio = anio;
        this.mes = mes;
        this.actual = actual;
        this.meta = meta;
        this.estadoRegistro = estadoRegistro;
    }

    public bscPerpestivaObjetivoIndicadorDetalleC(int perpestiva, String objetivo, String indicador, int anio, int mes, double actual, double meta, int estadoRegistro,  double porcentaje, String color) {
        super(anio, mes, actual, meta, porcentaje, color);
        this.perpestiva = perpestiva;
        this.objetivo = objetivo;
        this.indicador = indicador;
        this.anio = anio;
        this.mes = mes;
        this.actual = actual;
        this.meta = meta;
        this.estadoRegistro = estadoRegistro;
    }
   

    /**
     * @return the perpestiva
     */
    public int getPerpestiva() {
        return perpestiva;
    }

    /**
     * @param perpestiva the perpestiva to set
     */
    public void setPerpestiva(int perpestiva) {
        this.perpestiva = perpestiva;
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
     * @return the objetivo
     */
    public String getObjetivo() {
        return objetivo;
    }

    /**
     * @param objetivo the objetivo to set
     */
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
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
