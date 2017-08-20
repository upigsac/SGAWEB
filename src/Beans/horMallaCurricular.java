/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import DAO.horMallaCurricularDAO;
import ENTIDAD.horMallaCurricularC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "horMallaCurricularB")
@ViewScoped
public class horMallaCurricular{

    private List<horMallaCurricularC> horMallaCurricularL;
    private String seleccion;

    horMallaCurricularDAO horMallaCurricularD;

    public List<horMallaCurricularC> mostrarMallaCurricular(int institucion, String carrera) {
        
        horMallaCurricularL = new horMallaCurricularDAO().mostrarMostrarMalla(institucion, carrera);
        return horMallaCurricularL;
    }
    public List<horMallaCurricularC> getHorMallaCurricularL() {
        return horMallaCurricularL;
    }
    public void setHorMallaCurricularL(List<horMallaCurricularC> horMallaCurricularL) {
        this.horMallaCurricularL = horMallaCurricularL;
    }
    public String getSeleccion() {
        return seleccion;
    }
    public void setSeleccion(String seleccion) {
        this.seleccion = seleccion;
    }

}
