
package Beans;
import DAO.carrerasDAO;
import DAO.facultadCarreraDAO;
import ENTIDAD.carrerasC;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "carreraB")
@ViewScoped
public class carrera implements Serializable {
  
	private static final long serialVersionUID = 1L;
	private carrerasC carrera;
    private List<carrerasC> carrerasL;
    private String seleccion = "%";
   
   
    
    

    public carrerasC mostrarCarreraPersonal(int institucion, String alumno) {       
        carrera = new carrerasDAO().mostrarCarreraAlumno(institucion, alumno);
        return carrera;
    }

    public List<carrerasC> mostrarCarrera(String institucion, String periodo, String personal) {
       
        carrerasL = new carrerasDAO().mostrarCarreras(institucion, periodo, personal);
        return carrerasL;
    }

    public List<carrerasC> mostrarCarrera(int institucion, String periodo) {       
        carrerasL = new carrerasDAO().listaCarreraPeriodo(institucion, periodo);
        return carrerasL;
    }

    public List<carrerasC> mostrarCarreraRegulares() {
  
        carrerasL = new carrerasDAO().listaCarrera();
        return carrerasL;
    }

    public List<carrerasC> mostrarCarreraFacultad(int institucion, int facultad) {
   
        carrerasL = new facultadCarreraDAO().mostrarFacultadCarrera(institucion, facultad);
        return carrerasL;
    }

    public carrerasC mostrarCarreraCodigo(String codigo) {
    
        carrera = new carrerasDAO().mostrarCarrerasCodigo(codigo);
        return carrera;
    }

    public List<carrerasC> mostrarCarreraInstitucionPeriodo(int institucion) {
      
        carrerasL = new carrerasDAO().listaCarreraInstitucion(institucion);
        return carrerasL;
    }

    public List<carrerasC> mostrarCarreraDocente(int institucion, String periodo, String persona) {
       
        carrerasL = new carrerasDAO().mostrarCarrerasDocentePeriodo(institucion, periodo, persona);
        return carrerasL;
    }
      public List<carrerasC> mostrarCarreraPersonalSecundario(int institucion, String periodo, String personal) {
       
        carrerasL = new carrerasDAO().mostrarCarrerasPersonalSecundario(institucion, periodo, personal);
        return carrerasL;
    }
      
      
      
      public List<carrerasC> mostrarCarreraPrincipal(int institucion, String periodo) {
     
          carrerasL = new carrerasDAO().mostrarCarreraPrincipal(institucion, periodo);
          return carrerasL;
      }

   
    public carrerasC getCarrera() {
        return carrera;
    }
    public void setCarrera(carrerasC carrera) {
        this.carrera = carrera;
    }
    public List<carrerasC> getCarrerasL() {
        return carrerasL;
    }
    public void setCarrerasL(List<carrerasC> carrerasL) {
        this.carrerasL = carrerasL;
    }
    public String getSeleccion() {
        return seleccion;
    }
    public void setSeleccion(String seleccion) {
        this.seleccion = seleccion;
    }

}
