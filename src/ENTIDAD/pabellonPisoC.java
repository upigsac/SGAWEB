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
public class pabellonPisoC {
    private String pabellon;
    private int piso;
    private int maxAula;
    private int estadoRegistro;

    public pabellonPisoC() {
    }

    public pabellonPisoC(String pabellon, int piso, int maxAula, int estadoRegistro) {
        this.pabellon = pabellon;
        this.piso = piso;
        this.maxAula = maxAula;
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
     * @return the piso
     */
    public int getPiso() {
        return piso;
    }

    /**
     * @param piso the piso to set
     */
    public void setPiso(int piso) {
        this.piso = piso;
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
     * @return the maxAula
     */
    public int getMaxAula() {
        return maxAula;
    }

    /**
     * @param maxAula the maxAula to set
     */
    public void setMaxAula(int maxAula) {
        this.maxAula = maxAula;
    }
}
