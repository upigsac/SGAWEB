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
public class pabellonC {
    private String pabellon;
    private String descripcion;
    private String abreviatura;
    private int estadoRegistro;

    public pabellonC() {
    }

    public pabellonC(String pabellon, String descripcion, String abreviatura, int estadoRegistro) {
        this.pabellon = pabellon;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
        this.estadoRegistro = estadoRegistro;
    }
    
    

    /**
     * @return the pabellon
     */
    public String getPabellon() {
        return pabellon;
    }

    /**
     * @param pabellon the pabellon to set
     */
    public void setPabellon(String pabellon) {
        this.pabellon = pabellon;
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
}
