
package Beans;

import DAO.bienesDAO;
import ENTIDAD.bienesC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



@ManagedBean(name = "bienesB")
@ViewScoped
public class bienes  {

    private List<bienesC> bienesL;

   
    bienesDAO bienesD;

    public List<bienesC> mostrarBienes() {
        bienesD = new bienesDAO();
        bienesL = bienesD.mostrarBienes();

        return bienesL;
    }

    public List<bienesC> getBienesL() {
        return bienesL;
    }
    public void setBienesL(List<bienesC> bienesL) {
        this.bienesL = bienesL;
    }
}
