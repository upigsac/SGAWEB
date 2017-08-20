
package Beans;

import DAO.ubigeoProvinciaDAO;
import ENTIDAD.ubigeoProvinciaC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "ubigeoProvinciaB")
@ViewScoped
public class ubigeoProvincia  {

    private List<ubigeoProvinciaC> provinciaL;
    private String seleccion;

   
    ubigeoProvinciaDAO ubigeoProvinciaD;

    public List<ubigeoProvinciaC> mostrarProvincia(String departamento) {
        ubigeoProvinciaD = new ubigeoProvinciaDAO();
        provinciaL = ubigeoProvinciaD.mostrarProvincia(departamento);
        return provinciaL;
    }

    public List<ubigeoProvinciaC> getProvinciaL() {
        return provinciaL;
    }
    public void setProvinciaL(List<ubigeoProvinciaC> provinciaL) {
        this.provinciaL = provinciaL;
    }
    public String getSeleccion() {
        return seleccion;
    }
    public void setSeleccion(String seleccion) {
        this.seleccion = seleccion;
    }
}
