
package Beans;


import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ENTIDAD.autorizaNotaC;



@ManagedBean(name = "autorizaNotaB")
@ViewScoped
public class autorizaNota  {
     private List<autorizaNotaC> autorizaNotaL;

   
    public List<autorizaNotaC> getAutorizaNotaL() {
        return autorizaNotaL;
    }

   
    public void setAutorizaNotaL(List<autorizaNotaC> autorizaNotaL) {
        this.autorizaNotaL = autorizaNotaL;
    }
   
}
