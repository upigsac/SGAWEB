
package Beans;

import DAO.parentescoDAO;
import ENTIDAD.parentescoC;

import java.util.List;
import javax.faces.bean.ManagedBean;


@ManagedBean(name = "parentescoB")
public class parentesco  {

    private List<parentescoC> parentescoL;
    private String seleccion;

    parentescoDAO parentescoD;

    public List<parentescoC> mostrarParentesco() {
        parentescoD = new parentescoDAO();
        parentescoL = parentescoD.mostrarParentesco();
        return parentescoL;

    }

    public List<parentescoC> getParentescoL() {
        return parentescoL;
    }
    public void setParentescoL(List<parentescoC> parentescoL) {
        this.parentescoL = parentescoL;
    }
    public String getSeleccion() {
        return seleccion;
    }
    public void setSeleccion(String seleccion) {
        this.seleccion = seleccion;
    }
}
