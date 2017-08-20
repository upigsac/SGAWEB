
package Beans;

import DAO.frecuenciaDAO;
import ENTIDAD.frecuenciaC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "frecuenciaB")
@ViewScoped
public class frecuencia{

    private frecuenciaC frecuencia;
    private List<frecuenciaC> frecuenciaL;

    frecuenciaDAO frecuenciaD;

    public frecuenciaC mostrarFrecuencia(int frecuencia) {
        frecuenciaD = new frecuenciaDAO();
        this.frecuencia = frecuenciaD.mostrarfrecuencia(frecuencia);

        return this.frecuencia;
    }
    
     public List<frecuenciaC> mostrarFrecuencia() {
        frecuenciaD = new frecuenciaDAO();
        frecuenciaL = frecuenciaD.mostrarfrecuencia();

        return frecuenciaL;
    }
    public frecuenciaC getFrecuencia() {
        return frecuencia;
    }
    public void setFrecuencia(frecuenciaC frecuencia) {
        this.frecuencia = frecuencia;
    }
    public List<frecuenciaC> getFrecuenciaL() {
        return frecuenciaL;
    }
    public void setFrecuenciaL(List<frecuenciaC> frecuenciaL) {
        this.frecuenciaL = frecuenciaL;
    }
}
