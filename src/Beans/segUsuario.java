
package Beans;

import DAO.segUsuarioDAO;
import ENTIDAD.segUsuarioC;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "segUsuarioB")
@ViewScoped
public class segUsuario implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private List<segUsuarioC> segUsuarioL;
    private segUsuarioC segUsuario;

    segUsuarioDAO segUsuarioD;

    public List<segUsuarioC> mostrarSegUsuario(String persona) {
        segUsuarioD = new segUsuarioDAO();
        segUsuarioL = segUsuarioD.mostrarSegUsuario(persona);
        return segUsuarioL;
    }

    public void insertar(segUsuarioC item) {
        segUsuarioD = new segUsuarioDAO();
        segUsuarioD.insertaraSegUsuario(item);
    }
    public List<segUsuarioC> getSegUsuarioL() {
        return segUsuarioL;
    }
    public void setSegUsuarioL(List<segUsuarioC> segUsuarioL) {
        this.segUsuarioL = segUsuarioL;
    }
    public segUsuarioC getSegUsuario() {
        return segUsuario;
    }
    public void setSegUsuario(segUsuarioC segUsuario) {
        this.segUsuario = segUsuario;
    }
}
