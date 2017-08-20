
package Beans;

import DAO.cicloCarreraDAO;
import ENTIDAD.cicloCarreraC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "cicloCarreraB")
@ViewScoped
public class cicloCarrera  {

    private List<cicloCarreraC> cicloCarreraL;
    private int seleccion;

    cicloCarreraDAO cicloCarreraD;

    public List<cicloCarreraC> mostrarCicloCarrera(int institucion, String carerra) {
        cicloCarreraD = new cicloCarreraDAO();
        cicloCarreraL = cicloCarreraD.mostrarCicloCarrera(institucion, carerra);
        /*
         if (cicloCarreraL.size()>0){
         seleccion=cicloCarreraL.get(0).getCiclo();
         }
         */
        return cicloCarreraL;
    }

    public List<cicloCarreraC> getCicloCarreraL() {
        return cicloCarreraL;
    }

   
    public void setCicloCarreraL(List<cicloCarreraC> cicloCarreraL) {
        this.cicloCarreraL = cicloCarreraL;
    }

   
    public int getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(int seleccion) {
        this.seleccion = seleccion;
    }

}
