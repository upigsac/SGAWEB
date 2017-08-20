
package Beans;

import DAO.tipoConceptoDAO;
import ENTIDAD.tipoConceptoC;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="tipoConceptoB")
@ViewScoped
public class tipoConcepto  {
    private List<tipoConceptoC> tipoConceptoL;
    private tipoConceptoC tipoConcepto;
    
    
    
    tipoConceptoDAO tipoConceptoD;
    public List<tipoConceptoC> mostrarTipoConcepto(){
        tipoConceptoD=new tipoConceptoDAO();
        tipoConceptoL=tipoConceptoD.mostrarTipoConcepto();
        return tipoConceptoL;
    }
    
    
    public tipoConceptoC mostrarTipoConcepto(int tipoConcepto){
        tipoConceptoD=new tipoConceptoDAO();
        this.tipoConcepto=tipoConceptoD.mostrarTipoConcepto(tipoConcepto);
        return this.tipoConcepto;
    }
    public List<tipoConceptoC> getTipoConceptoL() {
        return tipoConceptoL;
    }
    public void setTipoConceptoL(List<tipoConceptoC> tipoConceptoL) {
        this.tipoConceptoL = tipoConceptoL;
    }
    public tipoConceptoC getTipoConcepto() {
        return tipoConcepto;
    }
    public void setTipoConcepto(tipoConceptoC tipoConcepto) {
        this.tipoConcepto = tipoConcepto;
    }

   
}
