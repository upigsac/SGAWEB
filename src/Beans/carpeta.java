
package Beans;

import DAO.carpetaDAO;
import ENTIDAD.carpetaC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="carpetaB")
@ViewScoped
public class carpeta {
    private List<carpetaC> carpetaL;
    
    
    carpetaDAO carpetaD;
    
    
    public List<carpetaC> mostrarCarpeta(int institucion,String periodo){
        carpetaD=new carpetaDAO();
        carpetaL=carpetaD.mostrarCarpeta(institucion, periodo);
        return carpetaL;
    }
    public List<carpetaC> getCarpetaL() {
        return carpetaL;
    }
    public void setCarpetaL(List<carpetaC> carpetaL) {
        this.carpetaL = carpetaL;
    }
    
}
