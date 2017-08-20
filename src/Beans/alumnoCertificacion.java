
package Beans;

import DAO.alumnoCertificacionDAO;
import DAO.detallePagoPersonaDAO;
import DAO.pagoPersonaDAO;
import ENTIDAD.alumnoCertificacionC;
import ENTIDAD.detallePagoPersonaC;
import ENTIDAD.pagoPersonaC;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



@ManagedBean(name = "alumnoCertificacionB")
@ViewScoped
public class alumnoCertificacion implements Serializable {

   
	private static final long serialVersionUID = 1L;
	private alumnoCertificacionC alumnoCertificacion;
    private List<alumnoCertificacionC> alumnoCertificacionL;
    private List<detallePagoPersonaC> detallePagoPersonaL=new ArrayList<detallePagoPersonaC>();
    private List<pagoPersonaC> pagoPersonaL;
    private pagoPersonaC pagoPersona=new pagoPersonaC();
    private String comprobanteS;
    private String alumnoS;
    
    
    

    alumnoCertificacionDAO alumnoCertificacionD;
    pagoPersonaDAO pagoPersonaD;
    detallePagoPersonaDAO detallePagoPersonaD;
    
    
    public void verPagos(int institucion,String concepto,String alumno){        
        alumnoS=alumno;
        mostrarComprobantes(institucion,concepto,alumno);
        detallePagoPersonaL.clear();
        
    }
    
    public List<pagoPersonaC> mostrarComprobantes(int institucion,String concepto,String alumno){
        pagoPersonaD=new pagoPersonaDAO();
        pagoPersonaL=pagoPersonaD.mostrarPagoPersona(institucion, concepto, alumno);
        return pagoPersonaL;
    }
    
    public List<detallePagoPersonaC> mostrarDetallePagoPersona(int institucion,String numeroComprobante,String alumno){
        
        detallePagoPersonaD=new detallePagoPersonaDAO();
        detallePagoPersonaL=detallePagoPersonaD.mostrarDetallePagoPersona(institucion, numeroComprobante, alumno);
        return detallePagoPersonaL;
    }
    
    public void mostrarDetallePagoCertificadoPersona(int institucion,String concepto,String alumno){    
        
        detallePagoPersonaD=new detallePagoPersonaDAO();
        detallePagoPersonaL=detallePagoPersonaD.mostrarDetallePagoPersona(institucion, concepto, alumno);         
    }
    
    
    public alumnoCertificacionC mostrarUltimaAlumnoCertificacion(int institucion, String periodo, String carrera, String malla, String curso, String seccion, String alumno) {
        alumnoCertificacionD = new alumnoCertificacionDAO();
        alumnoCertificacion = alumnoCertificacionD.mostrarUltimoAlumnoCertificacion(institucion, periodo, carrera, malla, curso, seccion, alumno);
        return alumnoCertificacion;
    }

    public List<alumnoCertificacionC> mostrarAlumnoCertificacion(int institucion, String periodo, String carrera, String malla, String curso, String seccion, String alumno) {
        alumnoCertificacionD = new alumnoCertificacionDAO();
        alumnoCertificacionL = alumnoCertificacionD.mostrarAlumnoCertificacion(institucion, periodo, carrera, malla, curso, seccion, alumno);
        return alumnoCertificacionL;
    }

    public void insertar(alumnoCertificacionC item) {

        alumnoCertificacionD = new alumnoCertificacionDAO();
        alumnoCertificacionD.insertar(item);

    }

   
    public alumnoCertificacionC getAlumnoCertificacion() {
        return alumnoCertificacion;
    }

  
    public void setAlumnoCertificacion(alumnoCertificacionC alumnoCertificacion) {
        this.alumnoCertificacion = alumnoCertificacion;
    }

   
    public List<alumnoCertificacionC> getAlumnoCertificacionL() {
        return alumnoCertificacionL;
    }

    public void setAlumnoCertificacionL(List<alumnoCertificacionC> alumnoCertificacionL) {
        this.alumnoCertificacionL = alumnoCertificacionL;
    }

  

    
    public List<pagoPersonaC> getPagoPersonaL() {
        return pagoPersonaL;
    }

    
    public void setPagoPersonaL(List<pagoPersonaC> pagoPersonaL) {
        this.pagoPersonaL = pagoPersonaL;
    }

  
    public List<detallePagoPersonaC> getDetallePagoPersonaL() {
        return detallePagoPersonaL;
    }

 
    public void setDetallePagoPersonaL(List<detallePagoPersonaC> detallePagoPersonaL) {
        this.detallePagoPersonaL = detallePagoPersonaL;
    }

    
    public pagoPersonaC getPagoPersona() {
        return pagoPersona;
    }

   
    public void setPagoPersona(pagoPersonaC pagoPersona) {
        this.pagoPersona = pagoPersona;
    }

   
    public String getComprobanteS() {
        return comprobanteS;
    }

   
    public void setComprobanteS(String comprobanteS) {
        this.comprobanteS = comprobanteS;
    }

   
    public String getAlumnoS() {
        return alumnoS;
    }

   
    public void setAlumnoS(String alumnoS) {
        this.alumnoS = alumnoS;
    }
}
