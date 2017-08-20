
package Beans;

import DAO.feriadoDAO;
import ENTIDAD.feriadosC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



@ManagedBean(name = "feriadoB")
@ViewScoped
public class feriado  {

    
    private  List<feriadosC> feriadosL;
    feriadoDAO feriadoD;
    public String mostrarTotalFeriado(int institucion, String periodo,String personal,String carrera,String curso,String seccion) {
        String total ;
        feriadoD = new feriadoDAO();
        total = feriadoD.mostrarTotalferiado(institucion, periodo,personal,carrera,curso,seccion);
        return total;
    }
    
     public List<feriadosC> mostrarFeriados(int institucion, String periodo,String personal,String carrera,String curso,String seccion) {
       
        feriadoD = new feriadoDAO();
        feriadosL = feriadoD.mostrarFeriados(institucion, periodo,personal,carrera,curso,seccion);
        return feriadosL;
    }
    public List<feriadosC> getFeriadosL() {
        return feriadosL;
    }
    public void setFeriadosL(List<feriadosC> feriadosL) {
        this.feriadosL = feriadosL;
    }
}
