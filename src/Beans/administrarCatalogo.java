/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.revistaDetalleDAO;
import ENTIDAD.revistaDetalleC;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.servlet.http.Part;

/**
 *
 * @author Administrador
 */
@ManagedBean(name="administrarCatalogoB")
@RequestScoped
public class administrarCatalogo {
    private List<revistaDetalleC> revistaDetalleL;
    private Part fileUpload;
    
    
    revistaDetalleDAO revistaDetalleD;

    
    public List<revistaDetalleC> mostrarRevistaDetalle(String revista){
        revistaDetalleD=new revistaDetalleDAO();
        revistaDetalleL=revistaDetalleD.detalleRevista(revista);
        return revistaDetalleL;
    }
    
    /**
     * @return the revistaDetalleL
     */
    public List<revistaDetalleC> getRevistaDetalleL() {
        return revistaDetalleL;
    }

    /**
     * @param revistaDetalleL the revistaDetalleL to set
     */
    public void setRevistaDetalleL(List<revistaDetalleC> revistaDetalleL) {
        this.revistaDetalleL = revistaDetalleL;
    }

    /**
     * @return the fileUpload
     */
    public Part getFileUpload() {
        return fileUpload;
    }

    /**
     * @param fileUpload the fileUpload to set
     */
    public void setFileUpload(Part fileUpload) {
        this.fileUpload = fileUpload;
    }
}
