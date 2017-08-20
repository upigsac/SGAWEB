
package Beans;

import DAO.personalSituacionDAO;
import ENTIDAD.personalSituacionC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="personalSituacionB")
@ViewScoped
public class personalSituacion {
    private List<personalSituacionC> personalSituacionL;
    
    
    
    personalSituacionDAO personalSituacionD;
    
    public List<personalSituacionC> mostrarPersonalSituacion(){
        personalSituacionD=new personalSituacionDAO();
        personalSituacionL=personalSituacionD.mostrarPersonalSituacion();
        return personalSituacionL;
    }

    public List<personalSituacionC> getPersonalSituacionL() {
        return personalSituacionL;
    }

    public void setPersonalSituacionL(List<personalSituacionC> personalSituacionL) {
        this.personalSituacionL = personalSituacionL;
    }
}
