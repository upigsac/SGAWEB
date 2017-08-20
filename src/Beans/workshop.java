
package Beans;

import DAO.invAlumnoCursoSeccionGrupoDAO;
import DAO.invCursoSeccionGrupoDAO;
import DAO.personaDAO;
import ENTIDAD.invAlumnoCursoSeccionGrupoC;
import ENTIDAD.invCursoSeccionGrupoC;
import ENTIDAD.personaC;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="workshopB")
@ViewScoped
public class workshop {
    private List<invCursoSeccionGrupoC> invCursoSeccionGrupoL=new ArrayList<>();
    private List<grupoTemaAlumno> grupoTemaAlumnoL;
    
    private int institucion;
    private String periodo;
    private String alumno;
    
   
   
    public workshop() {
  
    }
    
    public void load(int institucion,String periodo,String alumno){
    	this.institucion=institucion;
    	this.periodo=periodo;
    	this.alumno=alumno;
        mostrarGrupo();
    }
    
     public void mostrarGrupo(){
      
    
    	 grupoTemaAlumnoL=new ArrayList<>();
        grupoTemaAlumno itemGrupoTemaAlumno;
         for (invCursoSeccionGrupoC itemCursoSeccionGrupo : new invCursoSeccionGrupoDAO().mostrarCursoSeccionGrupo(institucion, periodo, "%", "%", "%","%","%",alumno)) {
             itemGrupoTemaAlumno=new grupoTemaAlumno(itemCursoSeccionGrupo);
             for (invAlumnoCursoSeccionGrupoC itemAlumnoCursoSeccionGrupo : new invAlumnoCursoSeccionGrupoDAO().mostrarAlumnoCursoSeccionGrupo(itemCursoSeccionGrupo.getInstitucion(), itemCursoSeccionGrupo.getPeriodo(), itemCursoSeccionGrupo.getCarrera(), itemCursoSeccionGrupo.getMalla(), itemCursoSeccionGrupo.getCurso(), itemCursoSeccionGrupo.getSeccion(),itemCursoSeccionGrupo.getGrupoExamen())) {
                 
                 itemGrupoTemaAlumno.alumnoPersonaL.add(new AlumnoPersonaCursoSeccionGrupo(itemAlumnoCursoSeccionGrupo, new personaDAO().busquedaAlumnocodigo(itemCursoSeccionGrupo.getInstitucion(), itemAlumnoCursoSeccionGrupo.getAlumno())));

             }
             grupoTemaAlumnoL.add(itemGrupoTemaAlumno);
         }
        
    }
    public List<grupoTemaAlumno> getGrupoTemaAlumnoL() {
        return grupoTemaAlumnoL;
    }
    public void setGrupoTemaAlumnoL(List<grupoTemaAlumno> grupoTemaAlumnoL) {
        this.grupoTemaAlumnoL = grupoTemaAlumnoL;
    }
    
    public class AlumnoPersonaCursoSeccionGrupo{
        private invAlumnoCursoSeccionGrupoC invAlumnoCursoSeccionGrupo;
        private personaC persona;

        public AlumnoPersonaCursoSeccionGrupo() {
        }

        public AlumnoPersonaCursoSeccionGrupo(invAlumnoCursoSeccionGrupoC invAlumnoCursoSeccionGrupo, personaC persona) {
            this.invAlumnoCursoSeccionGrupo = invAlumnoCursoSeccionGrupo;
            this.persona = persona;
        }
        

      
        public invAlumnoCursoSeccionGrupoC getInvAlumnoCursoSeccionGrupo() {
            return invAlumnoCursoSeccionGrupo;
        }
        public void setInvAlumnoCursoSeccionGrupo(invAlumnoCursoSeccionGrupoC invAlumnoCursoSeccionGrupo) {
            this.invAlumnoCursoSeccionGrupo = invAlumnoCursoSeccionGrupo;
        }
        public personaC getPersona() {
            return persona;
        }
        public void setPersona(personaC persona) {
            this.persona = persona;
        }
                
    }
    
    public class grupoTemaAlumno{
        private invCursoSeccionGrupoC invCursoSeccionGrupo;       
        private List<AlumnoPersonaCursoSeccionGrupo> alumnoPersonaL=new ArrayList<>();

        public grupoTemaAlumno() {
        }

        public grupoTemaAlumno(invCursoSeccionGrupoC invCursoSeccionGrupo) {
            this.invCursoSeccionGrupo = invCursoSeccionGrupo;
        }
        public invCursoSeccionGrupoC getInvCursoSeccionGrupo() {
            return invCursoSeccionGrupo;
        }
        public void setInvCursoSeccionGrupo(invCursoSeccionGrupoC invCursoSeccionGrupo) {
            this.invCursoSeccionGrupo = invCursoSeccionGrupo;
        }
        public List<AlumnoPersonaCursoSeccionGrupo> getAlumnoPersonaL() {
            return alumnoPersonaL;
        }
        public void setAlumnoPersonaL(List<AlumnoPersonaCursoSeccionGrupo> alumnoPersonaL) {
            this.alumnoPersonaL = alumnoPersonaL;
        }

       

      
    }
    
    
    
   

    public List<invCursoSeccionGrupoC> getInvCursoSeccionGrupoL() {
        return invCursoSeccionGrupoL;
    }
    public void setInvCursoSeccionGrupoL(List<invCursoSeccionGrupoC> invCursoSeccionGrupoL) {
        this.invCursoSeccionGrupoL = invCursoSeccionGrupoL;
    }
}
