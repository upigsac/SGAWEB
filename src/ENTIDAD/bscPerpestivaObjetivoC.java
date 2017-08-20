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
public class bscPerpestivaObjetivoC {

  
    private int perpestiva;
    private String objetivo;
    private String analisis;
    private String recomendacion;
    private int estadoRegistro;

    public bscPerpestivaObjetivoC() {
    }

    public bscPerpestivaObjetivoC( int perpestiva, String objetivo, String analisis, String recomendacion, int estadoRegistro) {
        
        this.perpestiva = perpestiva;
        this.objetivo = objetivo;
        this.analisis = analisis;
        this.recomendacion = recomendacion;
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
     * @return the analisis
     */
    public String getAnalisis() {
        return analisis;
    }

    /**
     * @param analisis the analisis to set
     */
    public void setAnalisis(String analisis) {
        this.analisis = analisis;
    }

    /**
     * @return the recomendacion
     */
    public String getRecomendacion() {
        return recomendacion;
    }

    /**
     * @param recomendacion the recomendacion to set
     */
    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
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
}
