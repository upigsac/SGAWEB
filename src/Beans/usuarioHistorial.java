

package Beans;

import DAO.usuarioHistorialDAO;
import ENTIDAD.usuarioHistorialC;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;


@ManagedBean(name="usuarioHistorialB")
public class usuarioHistorial {
    
    private List<usuarioHistorialC> usuarioHistorialL;    
    private int cantidad=10;
    
    @ManagedProperty(value ="#{usuarioB}")
    private usuario usuB;
    usuarioHistorialDAO usuarioHistorialD;
     
     
     
     @PostConstruct
    public void init() {
        usuarioHistorialD=new usuarioHistorialDAO();
        usuarioHistorialL=usuarioHistorialD.mostrar(cantidad,usuB.getUsu());
        
    }
    
   

    
    
    public void mostrarHistorial(){
        usuarioHistorialD=new usuarioHistorialDAO();
        usuarioHistorialL=usuarioHistorialD.mostrar(cantidad,usuB.getUsu());
    }
    
    
    
    
    
    public List<usuarioHistorialC> getUsuarioHistorialL() {
        return usuarioHistorialL;
    }

    
    public void setUsuarioHistorialL(List<usuarioHistorialC> usuarioHistorialL) {
        this.usuarioHistorialL = usuarioHistorialL;
    }

   
    public int getCantidad() {
        return cantidad;
    }

   
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

   
    public usuario getUsuB() {
        return usuB;
    }

   
    public void setUsuB(usuario usuB) {
        this.usuB = usuB;
    }
}
