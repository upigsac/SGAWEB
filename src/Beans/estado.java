
package Beans;

import DAO.estadoDAO;
import ENTIDAD.estadoC;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "estadoB")
@ViewScoped
public class estado implements Serializable {

  
	private static final long serialVersionUID = 1L;

	private estadoC estado;

    estadoDAO estadoD;

    public estadoC mostrarEstadoCodigo(int codigo) {
        estadoD = new estadoDAO();
        estado = estadoD.mostrarEstadoCodigo(codigo);
        return estado;
    }

    public estadoC getEstado() {
        return estado;
    }

    public void setEstado(estadoC estado) {
        this.estado = estado;
    }
}
