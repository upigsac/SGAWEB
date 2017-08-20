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

@ManagedBean(name = "horarioCarreraB")
@ViewScoped
public class horarioCarrera {
    private String carreraS;
    private String turnoS;

    /**
     * @return the carreraS
     */
    public String getCarreraS() {
        return carreraS;
    }

    /**
     * @param carreraS the carreraS to set
     */
    public void setCarreraS(String carreraS) {
        this.carreraS = carreraS;
    }

    /**
     * @return the turnoS
     */
    public String getTurnoS() {
        return turnoS;
    }

    /**
     * @param turnoS the turnoS to set
     */
    public void setTurnoS(String turnoS) {
        this.turnoS = turnoS;
    }
}
