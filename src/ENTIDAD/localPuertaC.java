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
public class localPuertaC {
    private int local;
    private int puerta ;
    private String descripcion;
    private String abreviatura;
    private String estacion;
    private int estadoRegistro;

    public localPuertaC() {
    }

    public localPuertaC(int local, int puerta, String descripcion, String abreviatura, String estacion, int estadoRegistro) {
        this.local = local;
        this.puerta = puerta;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
        this.estacion = estacion;
        this.estadoRegistro = estadoRegistro;
    }
    

    /**
     * @return the local
     */
    public int getLocal() {
        return local;
    }

    /**
     * @param local the local to set
     */
    public void setLocal(int local) {
        this.local = local;
    }

    /**
     * @return the puerta
     */
    public int getPuerta() {
        return puerta;
    }

    /**
     * @param puerta the puerta to set
     */
    public void setPuerta(int puerta) {
        this.puerta = puerta;
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
     * @return the estacion
     */
    public String getEstacion() {
        return estacion;
    }

    /**
     * @param estacion the estacion to set
     */
    public void setEstacion(String estacion) {
        this.estacion = estacion;
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
    
}
