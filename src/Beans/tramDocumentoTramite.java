/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.tramDocumentoTramiteDAO;
import ENTIDAD.tramDocumentoTramiteC;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Administrador
 */
@ManagedBean(name="tramDocumentoTramiteB")
@ViewScoped
public class tramDocumentoTramite {
    private List<tramDocumentoTramiteC> tramDocumentoTramiteL;
    
    
    tramDocumentoTramiteDAO tramDocumentoTramiteD;
    public List<tramDocumentoTramiteC> mostrarDocumentoTramite(int documento){
        tramDocumentoTramiteD=new tramDocumentoTramiteDAO();
        tramDocumentoTramiteL=tramDocumentoTramiteD.mostrarDocumento(documento);
        return tramDocumentoTramiteL;
        
    }

    /**
     * @return the tramDocumentoTramiteL
     */
    public List<tramDocumentoTramiteC> getTramDocumentoTramiteL() {
        return tramDocumentoTramiteL;
    }

    /**
     * @param tramDocumentoTramiteL the tramDocumentoTramiteL to set
     */
    public void setTramDocumentoTramiteL(List<tramDocumentoTramiteC> tramDocumentoTramiteL) {
        this.tramDocumentoTramiteL = tramDocumentoTramiteL;
    }

  
}
