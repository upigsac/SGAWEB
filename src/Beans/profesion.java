

package Beans;

import DAO.profesionDAO;
import ENTIDAD.profesionC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="profesionB")
@ViewScoped
public class profesion {
    private List<profesionC> profesionL;
    profesionDAO profesionD;
    
    public List<profesionC> mostrarProfesion(){
        profesionD=new profesionDAO();
        profesionL=profesionD.mostrarProfesion();
        return profesionL;
    }
    public List<profesionC> getProfesionL() {
        return profesionL;
    }
    public void setProfesionL(List<profesionC> profesionL) {
        this.profesionL = profesionL;
    }
}
