

package Beans;

import DAO.tipoPersonaDAO;
import ENTIDAD.tipoPersonaC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="tipoPersonaB")
@ViewScoped
public class tipoPersona {
    private List<tipoPersonaC> tipoPersonaL;
    
    
    tipoPersonaDAO tipoPersonaD;
    public List<tipoPersonaC> mostrarTipoPersona(){
        tipoPersonaD=new tipoPersonaDAO();
        tipoPersonaL=tipoPersonaD.mostrarTipoPersona();
        return tipoPersonaL;
    }
    public List<tipoPersonaC> getTipoPersonaL() {
        return tipoPersonaL;
    }
    public void setTipoPersonaL(List<tipoPersonaC> tipoPersonaL) {
        this.tipoPersonaL = tipoPersonaL;
    }
}
