
package Beans;

import DAO.situacionViviendaDAO;
import ENTIDAD.situacionViviendaC;

import java.util.List;
import javax.faces.bean.ManagedBean;


@ManagedBean(name = "situacionViviendaB")
public class situacionVivienda  {

    private List<situacionViviendaC> situacionViviendaL;

    situacionViviendaDAO situacionViviendaD;

    public List<situacionViviendaC> mostrarSituacionVivienda() {
        situacionViviendaD = new situacionViviendaDAO();
        situacionViviendaL = situacionViviendaD.mostrarSituacionVivienda();
        return situacionViviendaL;
    }
    public List<situacionViviendaC> getSituacionViviendaL() {
        return situacionViviendaL;
    }
    public void setSituacionViviendaL(List<situacionViviendaC> situacionViviendaL) {
        this.situacionViviendaL = situacionViviendaL;
    }

}
