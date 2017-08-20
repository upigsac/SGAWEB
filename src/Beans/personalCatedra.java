
package Beans;

import DAO.personalCatedraDAO;
import ENTIDAD.personalCatedraC;

import java.util.List;
import javax.faces.bean.ManagedBean;


@ManagedBean(name = "personalCatedraB")
public class personalCatedra  {

    private List<personalCatedraC> personalCatedraL;

    personalCatedraDAO personalCatedraD;

    public List<personalCatedraC> mostrarPersonalCatedra(String personal) {
        personalCatedraD = new personalCatedraDAO();
        personalCatedraL = personalCatedraD.mostrarPersonalCatedra(personal);
        return personalCatedraL;
    }

    public List<personalCatedraC> getPersonalCatedraL() {
        return personalCatedraL;
    }
    public void setPersonalCatedraL(List<personalCatedraC> personalCatedraL) {
        this.personalCatedraL = personalCatedraL;
    }
}
