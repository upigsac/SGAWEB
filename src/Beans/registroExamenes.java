
package Beans;

import DAO.registroExamenesDAO;
import ENTIDAD.registroExamenesC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "registroExamenesB")
@ViewScoped
public class registroExamenes  {

    private List<registroExamenesC> registroExamenesL;
    private registroExamenesC registroExamenes = new registroExamenesC();

    registroExamenesDAO registroExamenesD;

    public List<registroExamenesC> mostrarRegistroExamenes(int institucion, String periodo, String malla, String carrera, String curso, String seccion, String cpersonal) {
        registroExamenesD = new registroExamenesDAO();
        registroExamenesL = registroExamenesD.mostrarRegistroExamenes(institucion, periodo, malla, carrera, curso, seccion, cpersonal);
        return registroExamenesL;
    }
    public List<registroExamenesC> getRegistroExamenesL() {
        return registroExamenesL;
    }
    public void setRegistroExamenesL(List<registroExamenesC> registroExamenesL) {
        this.registroExamenesL = registroExamenesL;
    }
    public registroExamenesC getRegistroExamenes() {
        return registroExamenes;
    }
    public void setRegistroExamenes(registroExamenesC registroExamenes) {
        this.registroExamenes = registroExamenes;
    }

}
