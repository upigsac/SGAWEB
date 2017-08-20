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
public class pabellonPisoAulaC {
    private String pabellon;
    private int piso;
    private String aula;
    private int estadoRegistro;

    public pabellonPisoAulaC() {
    }

    public pabellonPisoAulaC(String pabellon, int piso, String aula, int estadoRegistro) {
        this.pabellon = pabellon;
        this.piso = piso;
        this.aula = aula;
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
     * @return the aula
     */
    public String getAula() {
        return aula;
    }

    /**
     * @param aula the aula to set
     */
    public void setAula(String aula) {
        this.aula = aula;
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
