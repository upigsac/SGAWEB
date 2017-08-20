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
public class bscPerpestivaObjetivoIndicadorC {
    private int perpestiva;
    private String objetivo;
    private String indicador;
    private int estadoRegistro;

    public bscPerpestivaObjetivoIndicadorC() {
    }

    public bscPerpestivaObjetivoIndicadorC(int perpestiva, String objetivo, String indicador, int estadoRegistro) {
        this.perpestiva = perpestiva;
        this.objetivo = objetivo;
        this.indicador = indicador;
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
