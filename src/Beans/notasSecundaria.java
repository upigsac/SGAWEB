/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Administrador
 */
@ManagedBean(name="notasSecundariaB")
@ViewScoped
public class notasSecundaria {
    private String subNota;

    /**
     * @return the subNota
     */
    public String getSubNota() {
        return subNota;
    }

    /**
     * @param subNota the subNota to set
     */
    public void setSubNota(String subNota) {
        this.subNota = subNota;
    }
}
