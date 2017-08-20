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
public class personaBienesC {
    private String persona;
    private int bienes;
    private int estadoRegistro;

    public personaBienesC(String persona, int bienes, int estadoRegistro) {
        this.persona = persona;
        this.bienes = bienes;
        this.estadoRegistro = estadoRegistro;
    }

   

    
    public String getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(String persona) {
        this.persona = persona;
    }

    /**
     * @return the bienes
     */
    public int getBienes() {
        return bienes;
    }

    /**
     * @param bienes the bienes to set
     */
    public void setBienes(int bienes) {
        this.bienes = bienes;
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
