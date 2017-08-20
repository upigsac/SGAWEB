
package Beans;

import DAO.dedicacionDAO;
import ENTIDAD.dedicacionC;

import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "dedicacionB")
public class dedicacion  {

    private List<dedicacionC> dedicacionL;

    dedicacionDAO dedicacionD;

    public List<dedicacionC> mostrarDadicacion() {
        dedicacionD = new dedicacionDAO();
        dedicacionL = dedicacionD.mostrarDedicacion();
        return dedicacionL;
    }

    public List<dedicacionC> getDedicacionL() {
        return dedicacionL;
    }
    public void setDedicacionL(List<dedicacionC> dedicacionL) {
        this.dedicacionL = dedicacionL;
    }
}
