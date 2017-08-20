
package Beans;

import DAO.facultadDAO;
import ENTIDAD.facultadC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "facultadB")
@ViewScoped
public class facultad {

    private List<facultadC> facultadL;

    facultadDAO facultadD;

    public List<facultadC> mostrarFacultad(int institucion) {
        facultadD = new facultadDAO();
        facultadL = facultadD.mostrarFacultad(institucion);
        return facultadL;
    }
    public List<facultadC> getFacultadL() {
        return facultadL;
    }
    public void setFacultadL(List<facultadC> facultadL) {
        this.facultadL = facultadL;
    }
}
