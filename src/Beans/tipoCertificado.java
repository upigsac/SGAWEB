/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.tipoCertificadoDAO;
import ENTIDAD.tipoCertificadoC;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Administrador
 */
@ManagedBean(name="tipoCertificadoB")
@ViewScoped
public class tipoCertificado {
    private tipoCertificadoC tipoCertificado;
    
    
    tipoCertificadoDAO tipoCertificadoD;
    public tipoCertificadoC mostrarTipoCertificado(int tipoCertificado){
        tipoCertificadoD=new tipoCertificadoDAO();
        this.tipoCertificado=tipoCertificadoD.mostrarTipoCertificado(tipoCertificado);
        return this.tipoCertificado;
    }
    

    /**
     * @return the tipoCertificado
     */
    public tipoCertificadoC getTipoCertificado() {
        return tipoCertificado;
    }

    /**
     * @param tipoCertificado the tipoCertificado to set
     */
    public void setTipoCertificado(tipoCertificadoC tipoCertificado) {
        this.tipoCertificado = tipoCertificado;
    }
}
