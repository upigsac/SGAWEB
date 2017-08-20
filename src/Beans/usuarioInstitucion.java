/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import ENTIDAD.usuarioInstitucionC;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Administrador
 */
@ManagedBean(name="usuarioInstitucionB")
@ViewScoped
public class usuarioInstitucion {
    private usuarioInstitucionC usuarioInstitucion;
    private List<usuarioInstitucionC> usuarioInstitucionL;

    /**
     * @return the usuarioInstitucion
     */
    public usuarioInstitucionC getUsuarioInstitucion() {
        return usuarioInstitucion;
    }

    /**
     * @param usuarioInstitucion the usuarioInstitucion to set
     */
    public void setUsuarioInstitucion(usuarioInstitucionC usuarioInstitucion) {
        this.usuarioInstitucion = usuarioInstitucion;
    }

    /**
     * @return the usuarioInstitucionL
     */
    public List<usuarioInstitucionC> getUsuarioInstitucionL() {
        return usuarioInstitucionL;
    }

    /**
     * @param usuarioInstitucionL the usuarioInstitucionL to set
     */
    public void setUsuarioInstitucionL(List<usuarioInstitucionC> usuarioInstitucionL) {
        this.usuarioInstitucionL = usuarioInstitucionL;
    }
}
