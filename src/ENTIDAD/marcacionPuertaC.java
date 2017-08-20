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
public class marcacionPuertaC {
    private int local;
    private int puerta;
    private Date fecha;
    private int item;
    private String persona;
    private int estadoRegistro;

    public marcacionPuertaC() {
    }

    public marcacionPuertaC(int local, int puerta, Date fecha, int item, String persona, int estadoRegistro) {
        this.local = local;
        this.puerta = puerta;
        this.fecha = fecha;
        this.item = item;
        this.persona = persona;
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
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
     * @return the persona
     */
    public String getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(String persona) {
        this.persona = persona;
    }
}
