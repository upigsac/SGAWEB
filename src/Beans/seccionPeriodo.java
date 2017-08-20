
package Beans;

import DAO.seccionPeriodoDAO;
import ENTIDAD.seccionPeriodoC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "seccionPeriodoB")
@ViewScoped
public class seccionPeriodo  {

    private seccionPeriodoC seccionPeriodo;
    private List<seccionPeriodoC> seccionPeriodoL;
    private boolean bandera;

    seccionPeriodoDAO seccionPeriodoD;
    
    
    public void nuevo(){
        bandera=false;
    }
    
    public void cancelar(){
        bandera=true;
    }

       public List<seccionPeriodoC> mostrarSeccionPeriodo(int institucion, String periodo) {
        seccionPeriodoD = new seccionPeriodoDAO();
        seccionPeriodoL = seccionPeriodoD.mostrarSeccionPeriodo(institucion, periodo);
        return seccionPeriodoL;
    }
    
    
    public seccionPeriodoC mostrarSeccionPeriodo(int institucion, String periodo, String carrera, String seccion) {
        seccionPeriodoD = new seccionPeriodoDAO();
        seccionPeriodo = seccionPeriodoD.mostrarSeccionPeriodo(institucion, periodo, carrera, seccion);
        return seccionPeriodo;
    }

    public List<seccionPeriodoC> mostrarSeccionPeriodo(int institucion, String periodo, String carrera) {
        seccionPeriodoD = new seccionPeriodoDAO();
        seccionPeriodoL = seccionPeriodoD.mostrarSeccionPeriodo(institucion, periodo, carrera);
        return seccionPeriodoL;
    }
    
     public List<seccionPeriodoC> mostrarReservaSeccion(int institucion, String periodo, String alumno) {
        seccionPeriodoD = new seccionPeriodoDAO();
        seccionPeriodoL = seccionPeriodoD.mostrarReservaSeccion(institucion, periodo, alumno);
        return seccionPeriodoL;
    }
    
     public String cantVacantes(int institucion, String periodo, String carrera,String seccion) {
        String vacantes="";
        seccionPeriodoD = new seccionPeriodoDAO();
        vacantes = seccionPeriodoD.cantVacantes(institucion, periodo, carrera,seccion);
        return vacantes;
    }
    public seccionPeriodoC getSeccionPeriodo() {
        return seccionPeriodo;
    }
    public void setSeccionPeriodo(seccionPeriodoC seccionPeriodo) {
        this.seccionPeriodo = seccionPeriodo;
    }
    public List<seccionPeriodoC> getSeccionPeriodoL() {
        return seccionPeriodoL;
    }
    public void setSeccionPeriodoL(List<seccionPeriodoC> seccionPeriodoL) {
        this.seccionPeriodoL = seccionPeriodoL;
    }
    public boolean isBandera() {
        return bandera;
    }
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

}
