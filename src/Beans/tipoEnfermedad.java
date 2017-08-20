
package Beans;

import DAO.tipoEnfermedadDAO;
import ENTIDAD.tipoEnfermedadC;

import java.util.List;
import javax.faces.bean.ManagedBean;


@ManagedBean(name = "tipoEnfermedadB")
public class tipoEnfermedad {

    private List<tipoEnfermedadC> tipoEnfermedadL;

    tipoEnfermedadDAO tipoEnfermedadD;

    public List<tipoEnfermedadC> mostrarTipoEnfermedad() {
        tipoEnfermedadD = new tipoEnfermedadDAO();
        tipoEnfermedadL = tipoEnfermedadD.mostrarTipoEnfermedad();
        return tipoEnfermedadL;
    }
    public List<tipoEnfermedadC> getTipoEnfermedadL() {
        return tipoEnfermedadL;
    }
    public void setTipoEnfermedadL(List<tipoEnfermedadC> tipoEnfermedadL) {
        this.tipoEnfermedadL = tipoEnfermedadL;
    }

}
