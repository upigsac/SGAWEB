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
public class personaTituloC {
    private String persona;
    private int item;
    private String titulo;
    private int institucion;
    private Date fecha;
    private int estadoRegistro;

    public personaTituloC() {
    }

    public personaTituloC(String persona, int item, String titulo, int institucion, Date fecha, int estadoRegistro) {
        this.persona = persona;
        this.item = item;
        this.titulo = titulo;
        this.institucion = institucion;
        this.fecha = fecha;
        this.estadoRegistro = estadoRegistro;
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
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the institucion
     */
    public int getInstitucion() {
        return institucion;
    }

    /**
     * @param institucion the institucion to set
     */
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
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
