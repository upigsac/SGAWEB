
package Beans;

import DAO.tipoExamenDAO;
import ENTIDAD.tipoExamenC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "tipoExamenB")
@ViewScoped
public class tipoExamen  {

    private List<tipoExamenC> tipoExamenL;
    private tipoExamenC tipoExamen;
    private String seleccion="EF";

    tipoExamenDAO tipoExamenD = null;

    public List<tipoExamenC> mostrarTipoExamen() {
        tipoExamenD = new tipoExamenDAO();

        tipoExamenL = tipoExamenD.mostrarTipoExamen();

        return tipoExamenL;
    }

    public tipoExamenC mostrarTipoExamen(String tipoExamen) {
        tipoExamenD = new tipoExamenDAO();
        this.tipoExamen = tipoExamenD.mostrarTipoExamen(tipoExamen);

        return this.tipoExamen;
    }
    
     public List<tipoExamenC> mostrarTipoExamen(int institucion,String periodo,String malla,String carrera,String curso,String dependencia,Boolean subDocente) {
        tipoExamenD = new tipoExamenDAO();
        tipoExamenL= tipoExamenD.mostrarTipoExamen(institucion, periodo, malla, carrera, curso, dependencia,subDocente);
        return tipoExamenL;
    }
     
      public List<tipoExamenC> mostrarTipoExamenPersonalSecundario(int institucion,String periodo,String personal,String carrera,String seccion,String curso) {
        tipoExamenD = new tipoExamenDAO();
        tipoExamenL= tipoExamenD.mostrarTipoExamenPersonalSecundario(institucion, periodo, personal, carrera, seccion, curso);
        return tipoExamenL;
    }
    public tipoExamenC getTipoExamen() {
        return tipoExamen;
    }
    public void setTipoExamen(tipoExamenC tipoExamen) {
        this.tipoExamen = tipoExamen;
    }
    public List<tipoExamenC> getTipoExamenL() {
        return tipoExamenL;
    }
    public void setTipoExamenL(List<tipoExamenC> tipoExamenL) {
        this.tipoExamenL = tipoExamenL;
    }
    public String getSeleccion() {
        return seleccion;
    }
    public void setSeleccion(String seleccion) {
        this.seleccion = seleccion;
    }

}
