
package Beans;

import DAO.tipoCentroEducativoDAO;
import ENTIDAD.tipoCentroEducativoC;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="tipoCentroEducativoB")
@ViewScoped
public class tipoCentroEducativo implements Serializable{
	
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<tipoCentroEducativoC> tipoCentroEducativoL=new ArrayList<>(); 
    private tipoCentroEducativoC tipoCentroEducativo; 
    
    tipoCentroEducativoDAO tipoCentroEducativoD;    
    public List<tipoCentroEducativoC> mostrarTipoCentroEducativo(){
        tipoCentroEducativoD=new tipoCentroEducativoDAO();
        tipoCentroEducativoL=tipoCentroEducativoD.mostrarTipoCentroEducativo();
        return tipoCentroEducativoL;
    }
    
      public tipoCentroEducativoC mostrarTipoCentroEducativo(int tipoCentroEducacion){
        tipoCentroEducativoD=new tipoCentroEducativoDAO();
        tipoCentroEducativo=tipoCentroEducativoD.mostrarTipoCentroEducativo(tipoCentroEducacion);
        return tipoCentroEducativo;
    }
    public List<tipoCentroEducativoC> getTipoCentroEducativoL() {
        return tipoCentroEducativoL;
    }
    public void setTipoCentroEducativoL(List<tipoCentroEducativoC> tipoCentroEducativoL) {
        this.tipoCentroEducativoL = tipoCentroEducativoL;
    }
    public tipoCentroEducativoC getTipoCentroEducativo() {
        return tipoCentroEducativo;
    }
    public void setTipoCentroEducativo(tipoCentroEducativoC tipoCentroEducativo) {
        this.tipoCentroEducativo = tipoCentroEducativo;
    }
}
