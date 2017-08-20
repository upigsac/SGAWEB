
package Beans;

import DAO.tipoDocumentoDAO;
import ENTIDAD.tipoDocumentoC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "tipoDocumentoB")
@ViewScoped
public class tipoDocumento {

    private tipoDocumentoC tipoDocumento;
    private List<tipoDocumentoC> tipoDocumentoL;

    tipoDocumentoDAO tipoDocumentoD;

    public tipoDocumentoC mostrarTipoDocumento(String codigo) {
        tipoDocumentoD = new tipoDocumentoDAO();
        tipoDocumento = tipoDocumentoD.mostrarTipoEnfermedad(codigo);
        return tipoDocumento;
    }
     public List<tipoDocumentoC> mostrarTipoDocumentos(boolean persona) {
        tipoDocumentoD = new tipoDocumentoDAO();
        tipoDocumentoL = tipoDocumentoD.mostrarTipoDocumento(persona);
        return tipoDocumentoL;
    }
    public List<tipoDocumentoC> getTipoDocumentoL() {
        return tipoDocumentoL;
    }
    public void setTipoDocumentoL(List<tipoDocumentoC> tipoDocumentoL) {
        this.tipoDocumentoL = tipoDocumentoL;
    }

}

