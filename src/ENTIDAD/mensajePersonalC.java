/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ENTIDAD;

import java.util.Date;

/**
 *
 * @author Diseño
 */
public class mensajePersonalC {
    
    private String mensaje;
    private String cuerpo;
    private String pie;
    private Date fecha_caducidad;
    private String estado;

    public mensajePersonalC(){        
    }
    
    public mensajePersonalC(String mensaje,String cuerpo,String pie,Date fecha_caducidad){
        this.mensaje=mensaje;
        this.cuerpo=cuerpo;
        this.pie=pie;
        this.fecha_caducidad=fecha_caducidad;
    }
    
    
    
    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the cuerpo
     */
    public String getCuerpo() {
        return cuerpo;
    }

    /**
     * @param cuerpo the cuerpo to set
     */
    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    /**
     * @return the pie
     */
    public String getPie() {
        return pie;
    }

    /**
     * @param pie the pie to set
     */
    public void setPie(String pie) {
        this.pie = pie;
    }

    /**
     * @return the fecha_caducidad
     */
    public Date getFecha_caducidad() {
        return fecha_caducidad;
    }

    /**
     * @param fecha_caducidad the fecha_caducidad to set
     */
    public void setFecha_caducidad(Date fecha_caducidad) {
        this.fecha_caducidad = fecha_caducidad;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
