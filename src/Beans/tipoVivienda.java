
package Beans;

import DAO.tipoViviendaDAO;
import ENTIDAD.tipoViviendaC;

import java.util.List;
import javax.faces.bean.ManagedBean;



@ManagedBean(name = "tipoViviendaB")
public class tipoVivienda  {

    private List<tipoViviendaC> tipoViviendaL;

    
    tipoViviendaDAO tipoViviendaD;

    public List<tipoViviendaC> mostrarTipoVivienda() {
        tipoViviendaD = new tipoViviendaDAO();
        tipoViviendaL = tipoViviendaD.mostrarTipoVivienda();

        return tipoViviendaL;
    }

    public List<tipoViviendaC> getTipoViviendaL() {
        return tipoViviendaL;
    }
    public void setTipoViviendaL(List<tipoViviendaC> tipoViviendaL) {
        this.tipoViviendaL = tipoViviendaL;
    }

}
