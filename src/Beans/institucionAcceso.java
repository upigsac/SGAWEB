
package Beans;

import DAO.institucionAccesoDAO;
import ENTIDAD.institucionAccesoC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "institucionAccesoB")
@ViewScoped
public class institucionAcceso {

    private institucionAccesoC institucionAcceso;
    private List<institucionAccesoC> institucionAccesoL;

    institucionAccesoDAO institucionAccesoD;

    public List<institucionAccesoC> mostrarInstitucionAcceso() {
        institucionAccesoD = new institucionAccesoDAO();
        institucionAccesoL = institucionAccesoD.mostrarInstitucionAcceso();
        return institucionAccesoL;
    }

    public institucionAccesoC mostrarInstitucionAcceso(int institucion) {
        institucionAccesoD = new institucionAccesoDAO();
        institucionAcceso = institucionAccesoD.mostrarInstitucionAcceso(institucion);
        return institucionAcceso;
    }
    public institucionAccesoC getInstitucionAcceso() {
        return institucionAcceso;
    }
    public void setInstitucionAcceso(institucionAccesoC institucionAcceso) {
        this.institucionAcceso = institucionAcceso;
    }
    public List<institucionAccesoC> getInstitucionAccesoL() {
        return institucionAccesoL;
    }
    public void setInstitucionAccesoL(List<institucionAccesoC> institucionAccesoL) {
        this.institucionAccesoL = institucionAccesoL;
    }
}
