
package Beans;

import DAO.asistenciaAlumnoDAO;
import DAO.cursoSeccionDocenteDAO;
import DAO.personaDAO;
import ENTIDAD.alumnoCursoAsistenciaC;
import ENTIDAD.cursoSeccionDocenteC;
import ENTIDAD.personaC;
import ENTIDAD.registrotdC;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="asistenciaAlumnoB")
@ViewScoped
public class asistenciaAlumno {
    
  
    
    private List<asistenciaCurso> asistenciaCursoL;
   
    private List<docentePersona> docentePersonaL;
	private cursoSeccionDocenteC cursoSeccionDocente=new cursoSeccionDocenteC();
    private int falta=0;
    private int ingreso=0;
    
    
    
    
    
    
    public cursoSeccionDocenteC getCursoSeccionDocente() {
		return cursoSeccionDocente;
	}
	public void setCursoSeccionDocente(cursoSeccionDocenteC cursoSeccionDocente) {
		this.cursoSeccionDocente = cursoSeccionDocente;
	}
	public int getFalta() {
		return falta;
	}
	public void setFalta(int falta) {
		this.falta = falta;
	}
	public int getIngreso() {
		return ingreso;
	}
	public void setIngreso(int ingreso) {
		this.ingreso = ingreso;
	}
	public List<docentePersona> getDocentePersonaL() {
		return docentePersonaL;
	}
	public void setDocentePersonaL(List<docentePersona> docentePersonaL) {
		this.docentePersonaL = docentePersonaL;
	}


	private String alumno;
    public asistenciaAlumno() {
		// TODO Auto-generated constructor stub
    	
    	
	}
    public void load(int institucion,String periodo,String alumno){
    	this.alumno=alumno;
    	docentePersonaL=new ArrayList<>();
    	docentePersona item;
    	
    	for(cursoSeccionDocenteC itemCursoSeccionDocente :new cursoSeccionDocenteDAO().mostrarCursoSeccionAlumno(institucion, periodo, alumno)){
    		item=new docentePersona(itemCursoSeccionDocente);
    		item.persona=new personaDAO().CodigoPersonaPersonal(itemCursoSeccionDocente.getPersonal());
    		docentePersonaL.add(item);
    	}
    }
    
    public void seleccionCursoSeccion(cursoSeccionDocenteC item){
    	cursoSeccionDocente=item;
    	ingreso=0;
    	falta=0;
    	asistenciaCursoL=new asistenciaAlumnoDAO().mostrarAsistenciaAlumno(item.getInstitucion(), item.getPeriodo(), item.getCarrera(), item.getCurso(), item.getSeccion(), alumno);
    	
    	
    	
    	for(asistenciaCurso itemCurso:asistenciaCursoL ){
    		if(itemCurso.alumnoCursoAsistencia.getAlumno() != null){
    			if(itemCurso.alumnoCursoAsistencia.isAsistencia()){
    				ingreso++;
    			}else{
    				falta++;
    			}
    		}
    	}
    }
  

  
	  public List<asistenciaCurso> getAsistenciaCursoL() {
			return asistenciaCursoL;
		}
		public void setAsistenciaCursoL(List<asistenciaCurso> asistenciaCursoL) {
			this.asistenciaCursoL = asistenciaCursoL;
		}
		
		
		
	

	public class docentePersona{
		private cursoSeccionDocenteC cursoSeccionDocente;
		private personaC persona;
		
		public docentePersona() {
			// TODO Auto-generated constructor stub
		}
		public docentePersona(cursoSeccionDocenteC cursoSeccionDocente) {
			this.cursoSeccionDocente=cursoSeccionDocente;
		}
		
		public cursoSeccionDocenteC getCursoSeccionDocente() {
			return cursoSeccionDocente;
		}
		public void setCursoSeccionDocente(cursoSeccionDocenteC cursoSeccionDocente) {
			this.cursoSeccionDocente = cursoSeccionDocente;
		}
		public personaC getPersona() {
			return persona;
		}
		public void setPersona(personaC persona) {
			this.persona = persona;
		}
		
		
		
		
		
	}


	public static class asistenciaCurso{
    	private registrotdC registrotd=new registrotdC();
    	private alumnoCursoAsistenciaC alumnoCursoAsistencia=new alumnoCursoAsistenciaC();
    	
    	
    	
    	
		public registrotdC getRegistrotd() {
			return registrotd;
		}
		public void setRegistrotd(registrotdC registrotd) {
			this.registrotd = registrotd;
		}
		public alumnoCursoAsistenciaC getAlumnoCursoAsistencia() {
			return alumnoCursoAsistencia;
		}
		public void setAlumnoCursoAsistencia(alumnoCursoAsistenciaC alumnoCursoAsistencia) {
			this.alumnoCursoAsistencia = alumnoCursoAsistencia;
		} 
    	
    	
    	
    	
    }
    
    
    
    
    
    
    
}
