
package Beans;

import DAO.formacionProfesionalDAO;
import ENTIDAD.formacionProfesionalC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "formacionProfesionalB")
@ViewScoped
public class formacionProfesional {

    private List<formacionProfesionalC> formacionProfesionalL;

    formacionProfesionalDAO formacionProfesionalD;

    public List<formacionProfesionalC> mostrarFormacionProfesional() {
        formacionProfesionalD = new formacionProfesionalDAO();
        formacionProfesionalL = formacionProfesionalD.mostrarformacionProfesional();
        return formacionProfesionalL;
    }
    public List<formacionProfesionalC> getFormacionProfesionalL() {
        return formacionProfesionalL;
    }
    public void setFormacionProfesionalL(List<formacionProfesionalC> formacionProfesionalL) {
        this.formacionProfesionalL = formacionProfesionalL;
    }

}
