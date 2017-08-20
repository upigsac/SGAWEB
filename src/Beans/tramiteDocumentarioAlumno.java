
package Beans;



import DAO.tramTramiteDAO;
import DAO.tramTramitePersonaDAO;

import DAO.validacionDAO;

import ENTIDAD.tramTramiteC;
import ENTIDAD.tramTramitePersonaC;
import ENTIDAD.tramTramiteSeguimientoC;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="tramiteDocumentarioAlumnoB")
@ViewScoped
public class tramiteDocumentarioAlumno {
    
    private List<tramiteAlumnoC> tramiteAlumnoL=new ArrayList<>();
 

    public tramiteDocumentarioAlumno() {
       
       
       
    }
    
    
    
    
    










	tramTramitePersonaDAO tramTramitePersonaD;
    validacionDAO validacionD;
    
    
    
    public void load(int institucion,String persona){
    	tramiteAlumnoL=new ArrayList<>();
    	tramiteAlumnoC itemTramiteAlumno;
    	for(tramTramitePersonaC item :new tramTramitePersonaDAO().mostrarTramitePersona(""+institucion, persona)){
    		itemTramiteAlumno=new tramiteAlumnoC(item);
    		itemTramiteAlumno.tramTramite=new tramTramiteDAO().mostrarTramiteDocumentario(1, item.getTramite());
    		tramiteAlumnoL.add(itemTramiteAlumno);
    	}
        
    }
    
    
    
  
   
  
   
  

    
    public  class  tramiteAlumnoC  {
       private tramTramitePersonaC tramTramitePersona;
       private tramTramiteC tramTramite;
       private List<tramTramiteSeguimientoC> tramTramiteSeguimientoL;
       
       public tramiteAlumnoC() {

	}
       public tramiteAlumnoC(tramTramitePersonaC tramTramitePersona) {
    	   this.tramTramitePersona=tramTramitePersona;
   		
   	}
       
       
       
	public tramTramitePersonaC getTramTramitePersona() {
		return tramTramitePersona;
	}
	public void setTramTramitePersona(tramTramitePersonaC tramTramitePersona) {
		this.tramTramitePersona = tramTramitePersona;
	}
	
	
	public tramTramiteC getTramTramite() {
		return tramTramite;
	}
	public void setTramTramite(tramTramiteC tramTramite) {
		this.tramTramite = tramTramite;
	}
	public List<tramTramiteSeguimientoC> getTramTramiteSeguimientoL() {
		return tramTramiteSeguimientoL;
	}
	public void setTramTramiteSeguimientoL(List<tramTramiteSeguimientoC> tramTramiteSeguimientoL) {
		this.tramTramiteSeguimientoL = tramTramiteSeguimientoL;
	}
	
       
    }
    
    public List<tramiteAlumnoC> getTramiteAlumnoL() {
  		return tramiteAlumnoL;
  	}










  	public void setTramiteAlumnoL(List<tramiteAlumnoC> tramiteAlumnoL) {
  		this.tramiteAlumnoL = tramiteAlumnoL;
  	}

}
