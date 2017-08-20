
package Beans;

import DAO.modalidadIngresoDAO;
import ENTIDAD.modalidadIngresoC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "modalidadIngresoB")
@ViewScoped
public class modalidadIngreso  {

    private List<modalidadIngresoC> modalidadIngresoL;
    private modalidadIngresoC modalidadIngreso;

    modalidadIngresoDAO modalidadIngresoD;

    public List<modalidadIngresoC> mostrarModalidadIngreso() {
        modalidadIngresoD = new modalidadIngresoDAO();
        modalidadIngresoL = modalidadIngresoD.mostrarModalidadIngreso();
        return modalidadIngresoL;
    }

    public modalidadIngresoC mostrarModalidadIngreso(int modalidad) {
        modalidadIngresoD = new modalidadIngresoDAO();
        modalidadIngreso = modalidadIngresoD.mostrarModalidadIngreso(modalidad);
        return modalidadIngreso;
    }
    public List<modalidadIngresoC> getModalidadIngresoL() {
        return modalidadIngresoL;
    }
    public void setModalidadIngresoL(List<modalidadIngresoC> modalidadIngresoL) {
        this.modalidadIngresoL = modalidadIngresoL;
    }
    public modalidadIngresoC getModalidadIngreso() {
        return modalidadIngreso;
    }
    public void setModalidadIngreso(modalidadIngresoC modalidadIngreso) {
        this.modalidadIngreso = modalidadIngreso;
    }

}
