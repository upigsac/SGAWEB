
package Beans;

import DAO.descuentoDAO;
import ENTIDAD.descuentoC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="descuentoB")
@ViewScoped
public class descuento {
    private descuentoC descuento;
    private List<descuentoC> descuentoL;
    descuentoDAO descuentoD;
    
    public descuentoC mostrarDescuento(String descuento){
        descuentoD=new descuentoDAO();
        this.descuento=descuentoD.mostrarDescuento(descuento);
        return this.descuento;
    }
    
     public List<descuentoC> mostrarDescuento(){
        descuentoD=new descuentoDAO();
        descuentoL=descuentoD.mostrarDescuento();
        return descuentoL;
    }
       public List<descuentoC> mostrarDescuento(int institucion,String periodo,String concepto){
        descuentoD=new descuentoDAO();
        descuentoL=descuentoD.mostrarDescuento(institucion, periodo, concepto);
        return descuentoL;
    }
    public descuentoC getDescuento() {
        return descuento;
    }
    public void setDescuento(descuentoC descuento) {
        this.descuento = descuento;
    }
    public List<descuentoC> getDescuentoL() {
        return descuentoL;
    }
    public void setDescuentoL(List<descuentoC> descuentoL) {
        this.descuentoL = descuentoL;
    }
}
