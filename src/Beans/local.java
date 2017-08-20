

package Beans;

import DAO.localDAO;
import ENTIDAD.localC;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="localB")
@ViewScoped
public class local implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private localC local;
    private List<localC> localL;
    
    
    localDAO localD;    
    public localC mostarLocal(int local){
        localD=new localDAO();
        this.local=localD.mostrarLocal(local);
        return this.local;
    } 
    
     public List<localC> mostarLocal(){
        localD=new localDAO();
        this.localL=localD.mostrarLocal();
        return this.localL;
    } 
     
     
      public List<localC> filtroLocal(int institucion){
        localD=new localDAO();
        this.localL=localD.filtroLocal(institucion);
        return this.localL;
    } 
    public localC getLocal() {
        return local;
    }
    public void setLocal(localC local) {
        this.local = local;
    }
    public List<localC> getLocalL() {
        return localL;
    }
    public void setLocalL(List<localC> localL) {
        this.localL = localL;
    }
}
