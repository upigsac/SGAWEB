
package Beans;

import DAO.alumnoDAO;
import ENTIDAD.alumnoC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "alumnoB")
@ViewScoped
public class alumno {
	

    private alumnoC alumno=new alumnoC();
    private List<alumnoC> alumnoL;
   
    alumnoDAO alumnoD;
    
    public void insertarAlumnoInstitucion(int institucion,String persona){
        alumnoD=new alumnoDAO();
        alumnoD.insertarAlumnoInstitucion(institucion, persona);
    }
    
     
    
    
    
    public String mostrarComprobanteCertificado(int institucion, int periodo, String carrera, String curso, String seccion, String alumno){
        alumnoD=new alumnoDAO();
        String comprobante="";
        comprobante=alumnoD.mostrarPagosCertificado(institucion, periodo, carrera, curso, seccion, alumno);
        return comprobante;
    }
    
     public String mostrarPagoTotalCertificado(int institucion, String concepto, String alumno){
        
        alumnoD=new alumnoDAO();
        String totalPago="";
        totalPago=alumnoD.mostrarPagoTotalCertificado(institucion, concepto, alumno);
        return totalPago;
    }
    
    
    
    

    public List<alumnoC> mostrarAlumno(int institucion, String periodo, String malla, String carrera, String seccion, String curso, String tipoNota) {
        alumnoD = new alumnoDAO();
        alumnoL = alumnoD.mostrarAlumnoSinNota(institucion, periodo, malla, carrera, seccion, curso, tipoNota);
        return alumnoL;
    }

    public alumnoC mostrarAlumno(int institucion, String persona) {
        alumnoD = new alumnoDAO();
        alumno = alumnoD.mostrarAlumno(institucion, persona);
        return alumno;
    }

    public alumnoC mostrarAlumnoCodigo(String institucion, String alumno) {
        
        alumnoD = new alumnoDAO();
        
        this.alumno = alumnoD.mostrarAlumnoCodigo(institucion, alumno);
        if (this.alumno ==null){
            this.alumno=new alumnoC();
        }
        
        return this.alumno;
    }

    public alumnoC getAlumno() {
        return alumno;
    }
    public void setAlumno(alumnoC alumno) {
        this.alumno = alumno;
    }
    public List<alumnoC> getAlumnoL() {
        return alumnoL;
    }
    public void setAlumnoL(List<alumnoC> alumnoL) {
        this.alumnoL = alumnoL;
    }
}
