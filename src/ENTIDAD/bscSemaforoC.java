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
public class bscSemaforoC {
    private int semaforo;
    private int desde;
    private int hasta;
    private String color;
    private String descripcion;
    private int estadoRegistro;

    public bscSemaforoC() {
    }

    public bscSemaforoC(int semaforo, int desde, int hasta, String color, String descripcion, int estadoRegistro) {
        this.semaforo = semaforo;
        this.desde = desde;
        this.hasta = hasta;
        this.color = color;
        this.descripcion = descripcion;
        this.estadoRegistro = estadoRegistro;
    }
    
    
    
    
    
    

    /**
     * @return the semaforo
     */
    public int getSemaforo() {
        return semaforo;
    }

    /**
     * @param semaforo the semaforo to set
     */
    public void setSemaforo(int semaforo) {
        this.semaforo = semaforo;
    }

    /**
     * @return the desde
     */
    public int getDesde() {
        return desde;
    }

    /**
     * @param desde the desde to set
     */
    public void setDesde(int desde) {
        this.desde = desde;
    }

    /**
     * @return the hasta
     */
    public int getHasta() {
        return hasta;
    }

    /**
     * @param hasta the hasta to set
     */
    public void setHasta(int hasta) {
        this.hasta = hasta;
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
}
