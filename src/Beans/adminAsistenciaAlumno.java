
package Beans;



import DAO.cuadroAsistenciaDAO;
import DAO.cursoSeccionDocenteDAO;
import DAO.personaDAO;
import DAO.personalDAO;

import ENTIDAD.cuadroAsistenciaC;
import ENTIDAD.cursoSeccionDocenteC;
import ENTIDAD.personaC;
import ENTIDAD.personalC;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="adminAsistenciaAlumnoB")
@ViewScoped
public class adminAsistenciaAlumno {
    private List<detalleAlumnoCursoSeccionAsistencia> detalleAlumnoCursoSeccionAsistenciaL=new ArrayList<>();
    private List<personaC> personaL=new ArrayList<>();
    private personaC persona=new personaC();
    private List<cursoSeccionDocenteC> cursoSeccionDocenteL=new ArrayList<>();
    private cursoSeccionDocenteC cursoSeccionDocente=new cursoSeccionDocenteC();
    private String filtroPersonal="";
    private int institucion;
    private String periodo;
    
    public adminAsistenciaAlumno() {
    	
    	
    
      
      
    	mostrarAsistencia();
            
    }
    
    public void load(int institucion,String periodo){
    	this.institucion=institucion;
    	this.periodo=periodo;
    	personaL= new personaDAO().mostrarPersonaPrincipal(institucion, periodo, "%", "%",filtroPersonal);
    }
    
    public void mostrarAsistencia(){
    	detalleAlumnoCursoSeccionAsistenciaL=new ArrayList<>();
    	detalleAlumnoCursoSeccionAsistencia itemAsistenciaAlumno=null;
        
        for(cuadroAsistenciaC item:  new cuadroAsistenciaDAO().mostrarCuadroAsistencia(cursoSeccionDocente.getInstitucion(), cursoSeccionDocente.getPeriodo(),cursoSeccionDocente.getCarrera(),cursoSeccionDocente.getCurso(),cursoSeccionDocente.getSeccion(),"%")){
     	   itemAsistenciaAlumno=new detalleAlumnoCursoSeccionAsistencia(item);     	   
     	   itemAsistenciaAlumno.persona=new personaDAO().busquedaAlumnocodigo(item.getInstitucion(), item.getAlumno());     
            detalleAlumnoCursoSeccionAsistenciaL.add(itemAsistenciaAlumno);
        }
    }
    public cursoSeccionDocenteC getCursoSeccionDocente() {
		return cursoSeccionDocente;
	}



	public void setCursoSeccionDocente(cursoSeccionDocenteC cursoSeccionDocente) {
		this.cursoSeccionDocente = cursoSeccionDocente;
	}




    public void seleccionarPersona(personaC item){
    	persona=item;
    	detalleAlumnoCursoSeccionAsistenciaL=new ArrayList<>();
    	personalC personal =new personalDAO().BuscaPersonaCodigo(item.getPersona());
    	cursoSeccionDocenteL=new cursoSeccionDocenteDAO().mostrarCursoSeccionDocentePrincipal(""+institucion, periodo, "%", "%", "%", "%", personal.getPersonal());
    }
    
   
public void seleccionaCursoSeccion(cursoSeccionDocenteC item){
	cursoSeccionDocente=item;
	mostrarAsistencia();
}


	public List<cursoSeccionDocenteC> getCursoSeccionDocenteL() {
		return cursoSeccionDocenteL;
	}
	public void setCursoSeccionDocenteL(List<cursoSeccionDocenteC> cursoSeccionDocenteL) {
		this.cursoSeccionDocenteL = cursoSeccionDocenteL;
	}
	public void mostrarDocente(){
   	 personaL= new personaDAO().mostrarPersonaPrincipal(institucion, periodo, "%", "%",filtroPersonal);
    }

    
    
    
  
    public List<detalleAlumnoCursoSeccionAsistencia> getDetalleAlumnoCursoSeccionAsistenciaL() {
        return detalleAlumnoCursoSeccionAsistenciaL;
    }
    public void setDetalleAlumnoCursoSeccionAsistenciaL(List<detalleAlumnoCursoSeccionAsistencia> detalleAlumnoCursoSeccionAsistenciaL) {
        this.detalleAlumnoCursoSeccionAsistenciaL = detalleAlumnoCursoSeccionAsistenciaL;
    }

    
    
    
    
    
    
    
    public List<personaC> getPersonaL() {
		return personaL;
	}



	public void setPersonaL(List<personaC> personaL) {
		this.personaL = personaL;
	}



	public String getFiltroPersonal() {
		return filtroPersonal;
	}



	public void setFiltroPersonal(String filtroPersonal) {
		this.filtroPersonal = filtroPersonal;
	}

	
	
	


	public personaC getPersona() {
		return persona;
	}

	public void setPersona(personaC persona) {
		this.persona = persona;
	}






	public class detalleAlumnoCursoSeccionAsistencia{
    	 	
      
        private personaC persona;
       
        private cuadroAsistenciaC cuadroAsistencia;
        
        
        
        public detalleAlumnoCursoSeccionAsistencia(cuadroAsistenciaC cuadroAsistencia) {
            this.cuadroAsistencia = cuadroAsistencia;
        }
        
        
        
        
        

        public personaC getPersona() {
			return persona;
		}

		public void setPersona(personaC persona) {
			this.persona = persona;
		}

		public cuadroAsistenciaC getCuadroAsistencia() {
			return cuadroAsistencia;
		}

		public void setCuadroAsistencia(cuadroAsistenciaC cuadroAsistencia) {
			this.cuadroAsistencia = cuadroAsistencia;
		}

		

		public detalleAlumnoCursoSeccionAsistencia() {
        }

       
 
        
   

      
    }
    
}
