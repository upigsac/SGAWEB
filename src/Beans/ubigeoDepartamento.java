
package Beans;

import DAO.ubigeoDepartamentoDAO;
import ENTIDAD.ubigeoDepartamentoC;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "ubigeoDepartamentoB")
@ViewScoped
public class ubigeoDepartamento implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<ubigeoDepartamentoC> departamentoL;
    private String seleccion;

    ubigeoDepartamentoDAO ubigeoDepartamentoD;

    public List<ubigeoDepartamentoC> mostrarDepartamento() {
        ubigeoDepartamentoD = new ubigeoDepartamentoDAO();
        departamentoL = ubigeoDepartamentoD.mostrarDepartamento();
        return departamentoL;
    }
    public List<ubigeoDepartamentoC> getDepartamentoL() {
        return departamentoL;
    }
    public void setDepartamentoL(List<ubigeoDepartamentoC> departamentoL) {
        this.departamentoL = departamentoL;
    }
    public String getSeleccion() {
        return seleccion;
    }
    public void setSeleccion(String seleccion) {
        this.seleccion = seleccion;
    }
}
