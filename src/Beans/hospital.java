
package Beans;

import DAO.hospitalDAO;
import ENTIDAD.hospitalC;

import java.util.List;
import javax.faces.bean.ManagedBean;


@ManagedBean(name = "hospitalB")
public class hospital {

    private List<hospitalC> hospitalL;

    hospitalDAO hospitalD;

    public List<hospitalC> mostrarHospital() {
        hospitalD = new hospitalDAO();
        hospitalL = hospitalD.mostrarHospital();
        return hospitalL;
    }
    public List<hospitalC> getHospitalL() {
        return hospitalL;
    }
    public void setHospitalL(List<hospitalC> hospitalL) {
        this.hospitalL = hospitalL;
    }
}
