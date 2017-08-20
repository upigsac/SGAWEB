
package Beans;

import DAO.condicionLaboralDAO;
import ENTIDAD.condicionLaboralC;

import java.util.List;
import javax.faces.bean.ManagedBean;


@ManagedBean(name = "condicionLaboralB")
public class condicionLaboral {

    private List<condicionLaboralC> condicionLaboralL;

    condicionLaboralDAO condicionLaboralD;

    public List<condicionLaboralC> mostrarCondicionLaboral() {
        condicionLaboralD = new condicionLaboralDAO();
        condicionLaboralL = condicionLaboralD.mostrarCondicionLaboral();
        return condicionLaboralL;
    }
    public List<condicionLaboralC> getCondicionLaboralL() {
        return condicionLaboralL;
    }
    public void setCondicionLaboralL(List<condicionLaboralC> condicionLaboralL) {
        this.condicionLaboralL = condicionLaboralL;
    }
}
