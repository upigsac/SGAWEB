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
public class tramTramiteRequisitoC {
    private int tramite;
    private int requisito;
    private int estadoRegistro;

    public tramTramiteRequisitoC() {
    }

    public tramTramiteRequisitoC(int tramite, int requisito, int estadoRegistro) {
        this.tramite = tramite;
        this.requisito = requisito;
        this.estadoRegistro = estadoRegistro;
    }
    

    /**
     * @return the tramite
     */
    public int getTramite() {
        return tramite;
    }

    /**
     * @param tramite the tramite to set
     */
    public void setTramite(int tramite) {
        this.tramite = tramite;
    }

    /**
     * @return the requisito
     */
    public int getRequisito() {
        return requisito;
    }

    /**
     * @param requisito the requisito to set
     */
    public void setRequisito(int requisito) {
        this.requisito = requisito;
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
