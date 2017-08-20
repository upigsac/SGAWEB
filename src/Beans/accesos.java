
package Beans;

import DAO.accesoDAO;

import ENTIDAD.accesoC;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;



@ManagedBean(name = "accesoB")
@ViewScoped
public class accesos implements Serializable {

   
	private static final long serialVersionUID = 1L;
	private String seleccion;
    private List<accesoC> accesosL;
    private accesoC accesos;
    

   
  

    public accesoC mostrarAccesos(String usuario, int programa, int modulo) {
    
      
        accesos = new accesoDAO().mostrarAccesos(usuario, programa, modulo);
        return accesos;
    }
     public void mostrarAcceso(String usuario, int programa, int modulo) {
      
        
        accesos = new accesoDAO().mostrarAccesos(usuario, programa, modulo);
        
    }
     

    public String redirect(String menu) {
        this.seleccion = menu;
        return menu;

    }

    public void insertarAcceso() {
       

        new accesoDAO().insertar(accesos);
    }

    public String mensaje(String menu) {
        this.seleccion = menu;
        return "escritorio";
    }

   
    public String getSeleccion() {
        return seleccion;
    }

 
    public void setSeleccion(String seleccion) {
        this.seleccion = seleccion;
    }

  
    public List<accesoC> getAccesosL() {
        return accesosL;
    }

    public void setAccesosL(List<accesoC> accesosL) {
        this.accesosL = accesosL;
    }

    public accesoC getAccesos() {
        return accesos;
    }

    public void setAccesos(accesoC accesos) {
        this.accesos = accesos;
    }

}
