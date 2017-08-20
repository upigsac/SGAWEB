/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.carpetaInstitucionDAO;
import ENTIDAD.carpetaInstitucionC;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Administrador
 */
@ManagedBean(name="carpetaInstitucionB")
@ViewScoped
public class carpetaInstitucion {
    private List<carpetaInstitucionC> carpetaInstitucionL;
    
    
    
    carpetaInstitucionDAO carpetaInstitucionD;
    public List<carpetaInstitucionC> mostrarCarpetaInstitucion(int institucion,String periodo){
    carpetaInstitucionD=new carpetaInstitucionDAO();
    carpetaInstitucionL=carpetaInstitucionD.mostrarCarpetaInstitucion(institucion, institucion);
    return carpetaInstitucionL;
}
        

    /**
     * @return the carpetaInstitucionL
     */
    public List<carpetaInstitucionC> getCarpetaInstitucionL() {
        return carpetaInstitucionL;
    }

    /**
     * @param carpetaInstitucionL the carpetaInstitucionL to set
     */
    public void setCarpetaInstitucionL(List<carpetaInstitucionC> carpetaInstitucionL) {
        this.carpetaInstitucionL = carpetaInstitucionL;
    }
    
}
