/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.tramTipoTramiteDAO;
import ENTIDAD.tramTipoTramiteC;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Administrador
 */
@ManagedBean(name="tramDocumentoB")
@ViewScoped
public class tramTipoTramite {
    private List<tramTipoTramiteC> tramDocumentoL;
    private tramTipoTramiteC tramDocumento;    
    tramTipoTramiteDAO tramTipoDocumentoD;
    
    
    
    public List<tramTipoTramiteC> mostrarTipoTramite(){
        tramTipoDocumentoD=new tramTipoTramiteDAO();
        tramDocumentoL=tramTipoDocumentoD.mostrarTipoTramite();
        return tramDocumentoL;
    }
    
     public tramTipoTramiteC mostrarTipoTramite(int tipoTramite){
        tramTipoDocumentoD=new tramTipoTramiteDAO();
        tramDocumento=tramTipoDocumentoD.mostrarTipoTramite(tipoTramite);
        return tramDocumento;
    }

    /**
     * @return the tramTipoTramiteL
     */
    public List<tramTipoTramiteC> getTramDocumentoL() {
        return tramDocumentoL;
    }

    /**
     * @param tramDocumentoL the tramTipoTramiteL to set
     */
    public void setTramDocumentoL(List<tramTipoTramiteC> tramDocumentoL) {
        this.tramDocumentoL = tramDocumentoL;
    }

    /**
     * @return the tramDocumento
     */
    public tramTipoTramiteC getTramDocumento() {
        return tramDocumento;
    }

    /**
     * @param tramDocumento the tramDocumento to set
     */
    public void setTramDocumento(tramTipoTramiteC tramDocumento) {
        this.tramDocumento = tramDocumento;
    }
    
}

