
package Beans;

import DAO.cicloDAO;
import ENTIDAD.cicloC;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "cicloB")
@ViewScoped
public class ciclo {

    private String seleccionCiclo;
    private List<cicloC> cicloL;
    
    
    cicloDAO cicloD;
public List<cicloC> mostrarCiclo(int institucion,String carrera){

    cicloL=new cicloDAO().mostrarCiclo(institucion, carrera);
    return cicloL;
}
    public String getSeleccionCiclo() {
        return seleccionCiclo;
    }
    public void setSeleccionCiclo(String seleccionCiclo) {
        this.seleccionCiclo = seleccionCiclo;
    }
    public List<cicloC> getCicloL() {
        return cicloL;
    }
    public void setCicloL(List<cicloC> cicloL) {
        this.cicloL = cicloL;
    }
}
