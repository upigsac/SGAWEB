
package Beans;

import DAO.tipoAutorizacionDAO;
import ENTIDAD.tipoAutorizacionC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="tipoAutorizacionB")
@ViewScoped
public class tipoAutorizacion {
    private List<tipoAutorizacionC> tipoAutorizacionL;
    tipoAutorizacionDAO tipoAutorizacionD;
    public List<tipoAutorizacionC> mostrarTipoAutorizacion(){
        tipoAutorizacionD=new tipoAutorizacionDAO();
        tipoAutorizacionL=tipoAutorizacionD.mostrarTipoAutorizacion();
        return tipoAutorizacionL;
    }
    public List<tipoAutorizacionC> getTipoAutorizacionL() {
        return tipoAutorizacionL;
    }
    public void setTipoAutorizacionL(List<tipoAutorizacionC> tipoAutorizacionL) {
        this.tipoAutorizacionL = tipoAutorizacionL;
    }
}
