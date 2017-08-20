
package Beans;

import DAO.ubigeoDistritoDAO;
import ENTIDAD.ubigeoDistritoC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "ubigeoDistritoB")
@ViewScoped
public class ubigeoDistrito {

    private List<ubigeoDistritoC> distritoL;
    private ubigeoDistritoC distrito;
    private String selecion;

    ubigeoDistritoDAO ubigeoDistritoD;

    public List<ubigeoDistritoC> mostrarDistrito(String departamento, String provincia) {
        ubigeoDistritoD = new ubigeoDistritoDAO();
        distritoL = ubigeoDistritoD.mostrarDistrito(departamento, provincia);
        return distritoL;
    }
    
      public ubigeoDistritoC mostrarDistrito(String departamento, String provincia,String distrito) {
        ubigeoDistritoD = new ubigeoDistritoDAO();
        this.distrito = ubigeoDistritoD.mostrarDistrito(departamento, provincia,distrito);
        return this.distrito;
    }
        public ubigeoDistritoC mostrarUbigeo(String ubigeo) {
        ubigeoDistritoD = new ubigeoDistritoDAO();
        this.distrito = ubigeoDistritoD.mostrarUbigeo(ubigeo);
        return this.distrito;
    }
    public List<ubigeoDistritoC> getDistritoL() {
        return distritoL;
    }
    public void setDistritoL(List<ubigeoDistritoC> distritoL) {
        this.distritoL = distritoL;
    }
    public String getSelecion() {
        return selecion;
    }
    public void setSelecion(String selecion) {
        this.selecion = selecion;
    }
    public ubigeoDistritoC getDistrito() {
        return distrito;
    }
    public void setDistrito(ubigeoDistritoC distrito) {
        this.distrito = distrito;
    }
}
