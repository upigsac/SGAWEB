
package Beans;

import DAO.registrotmDAO;
import ENTIDAD.registrotmC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "registrotmB")
@ViewScoped
public class registrotm  {

    private registrotmC registrotm;
    private List<registrotmC> registrotmL;
    registrotmDAO registrotmD;

    public String mostrarTotalHoras(int institucion, String periodo, String malla, String carrera, String curso, String seccion, String personal) {
      
        String totalHoras = "0";
        registrotmD = new registrotmDAO();
        totalHoras = registrotmD.mostrarRegistrotmTotalHoras(institucion, periodo, malla, carrera, curso, seccion, personal);
        return totalHoras;
    }

    public String mostrarTotalHorasProyectadas(String institucion, String periodo, String personal) {

        String horasProyectadas = "0";
        registrotmD = new registrotmDAO();
        horasProyectadas = registrotmD.mostrarRegistrotmTotalHorasProyectadas(institucion, periodo, personal);
        return horasProyectadas;
    }

    public List<registrotmC> mostrarRegistrotm(int institucion, String periodo, String carrera, String curso, String seccion, String personal) {

        registrotmD = new registrotmDAO();
        registrotmL = registrotmD.mostrarRegistrotm(institucion, periodo, carrera, curso, seccion, personal,"%");
        return registrotmL;
    }
    public registrotmC getRegistrotm() {
        return registrotm;
    }
    public void setRegistrotm(registrotmC registrotm) {
        this.registrotm = registrotm;
    }
    public List<registrotmC> getRegistrotmL() {
        return registrotmL;
    }
    public void setRegistrotmL(List<registrotmC> registrotmL) {
        this.registrotmL = registrotmL;
    }
}
