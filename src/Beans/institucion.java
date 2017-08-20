
package Beans;

import DAO.institucionDAO;
import ENTIDAD.institucionC;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;


import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;


@ManagedBean(name = "institucionB")
@ViewScoped
public class institucion implements Serializable {

   
	private static final long serialVersionUID = 1L;
	private List<institucionC> institucionL;
    private institucionC institucion;

    institucionDAO institucionD = null;

    public List<institucionC> mostrarInstitucionDocente(String periodo, String personal) {
        institucionD = new institucionDAO();
        return institucionD.mostrarInstitucionDocente(periodo, personal);
    }
    
    public List<institucionC> mostrarInstitucionAlumno(String alumno) {
        institucionD = new institucionDAO();
        return institucionD.mostrarInstitucionAlumno(alumno);
    }

    public List<institucionC> mostrarInstitucion() {
        institucionD = new institucionDAO();
        institucionL = institucionD.mostrarInstitucion();
        return institucionL;
    }

    public institucionC mostrarInstitucionCodigo(int codigo) {
        institucionD = new institucionDAO();
        institucion = institucionD.mostrarInstitucionCodigo(codigo);
        return institucion;
    }

    public List<institucionC> mostrarInstitucionPersona() {
        institucionD = new institucionDAO();

        //InstitucionL=insDAO.mostrarInstitucionPersona(usuarioB.getPersona().getPersona());
        return institucionL;
    }

    public List<institucionC> mostrarInstitucionUsuario(String usuario) {
        institucionD = new institucionDAO();
        institucionL = institucionD.mostrarInstitucionUsuario(usuario);
      

        return institucionL;
    }

    public void refrescar() {
    //   util.consolaCliente( seleccionInstitucion);
        // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("institucion",seleccionInstitucion);        
        RequestContext.getCurrentInstance().execute("document.location=document.location");

    }
    public List<institucionC> getInstitucionL() {
        return institucionL;
    }


    public void setInstitucionL(List<institucionC> InstitucionL) {
        this.institucionL = InstitucionL;
    }
    public institucionC getInstitucion() {
        return institucion;
    }
    public void setInstitucion(institucionC Institucion) {
        this.institucion = Institucion;
    }
}
