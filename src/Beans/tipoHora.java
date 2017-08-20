
package Beans;

import DAO.tipoHoraDAO;
import ENTIDAD.tipoHoraC;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="tipoHoraB")
public class tipoHora {
    private List<tipoHoraC> tipoHoraL;
    private tipoHoraC tipoHora;
    tipoHoraDAO tipoHoraD;
    
    
    public List<tipoHoraC> mostrarTipoHora(){
        tipoHoraD=new tipoHoraDAO();
        tipoHoraL=tipoHoraD.mostrarTipoHora();
        return tipoHoraL;
    }
    
    public tipoHoraC mostrarTipoHora(int tipoHora){
        tipoHoraD=new tipoHoraDAO();
        this.tipoHora=tipoHoraD.mostrarTipoHora(tipoHora);
        return this.tipoHora;
    }
    public List<tipoHoraC> getTipoHoraL() {
        return tipoHoraL;
    }
    public void setTipoHoraL(List<tipoHoraC> tipoHoraL) {
        this.tipoHoraL = tipoHoraL;
    }
    public tipoHoraC getTipoHora() {
        return tipoHora;
    }
    public void setTipoHora(tipoHoraC tipoHora) {
        this.tipoHora = tipoHora;
    }
    

 
}
