
package Beans;

import DAO.tipoAmonestacionDAO;
import ENTIDAD.tipoAmonestacionC;

import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="tipoAmonestacionB")
public class tipoAmonestacion {
    private List<tipoAmonestacionC> tipoAmonestacionL;
    private tipoAmonestacionC tipoAmonestacion;
    tipoAmonestacionDAO tipoAmonestacionD;
    
    public List<tipoAmonestacionC> mostrarTipoAmonestacion(){
        tipoAmonestacionD=new tipoAmonestacionDAO();
        tipoAmonestacionL=tipoAmonestacionD.mostrarTipoAmonestacion();
        return tipoAmonestacionL;
    }
    
    public tipoAmonestacionC mostrarTipoAmonestacion(int tipo){
        tipoAmonestacionD=new tipoAmonestacionDAO();
        tipoAmonestacion=tipoAmonestacionD.mostrarTipoAmonestacion(tipo);
        return tipoAmonestacion;
    }
    public List<tipoAmonestacionC> getTipoAmonestacionL() {
        return tipoAmonestacionL;
    }
    public void setTipoAmonestacionL(List<tipoAmonestacionC> tipoAmonestacionL) {
        this.tipoAmonestacionL = tipoAmonestacionL;
    }
    public tipoAmonestacionC getTipoAmonestacion() {
        return tipoAmonestacion;
    }
    public void setTipoAmonestacion(tipoAmonestacionC tipoAmonestacion) {
        this.tipoAmonestacion = tipoAmonestacion;
    }
}
