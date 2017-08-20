package Beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.constanciaMatriculaDAO;
import ENTIDAD.alumnoC;
import ENTIDAD.alumnoCarreraC;
import ENTIDAD.carrerasC;
import ENTIDAD.constanciaMatriculaC;
import ENTIDAD.personaC;

@ManagedBean(name="constanciaMatriculaB")
@ViewScoped

public class constanciaMatricula {
	private String filtroCarrea="%";
	private String filtroAlumno="";
	private String filtroPersona="";
	private List<matriculaC> matriculaL=new ArrayList<>();
	
	
	
	
	




	public constanciaMatricula() {
		// TODO Auto-generated constructor stub
		matriculaL=new constanciaMatriculaDAO().mostrarConstanciaMatricula(1,filtroCarrea,filtroPersona,filtroAlumno);
	}
	
	public void filtrar(){
		matriculaL=new constanciaMatriculaDAO().mostrarConstanciaMatricula(1,filtroCarrea,filtroPersona,filtroAlumno);
	}
	
	public void insertar(matriculaC itemMatricula){
		new constanciaMatriculaDAO().insertar(new constanciaMatriculaC(null, 1, itemMatricula.carrera.getCarrera(),itemMatricula.alumnoCarrera.getMalla(), itemMatricula.alumno.getAlumno(), 1));
		filtrar();
	}
	
	public List<matriculaC> getMatriculaL() {
		return matriculaL;
	}
	public void setMatriculaL(List<matriculaC> matriculaL) {
		this.matriculaL = matriculaL;
	}




	public static class matriculaC{
		private constanciaMatriculaC constanciaMatricula=new constanciaMatriculaC();
		private personaC persona=new personaC();
		private alumnoC alumno=new alumnoC();
		private carrerasC carrera=new carrerasC();
		private alumnoCarreraC alumnoCarrera=new alumnoCarreraC(); 
		
		
		
		public alumnoCarreraC getAlumnoCarrera() {
			return alumnoCarrera;
		}
		public void setAlumnoCarrera(alumnoCarreraC alumnoCarrera) {
			this.alumnoCarrera = alumnoCarrera;
		}
		public constanciaMatriculaC getConstanciaMatricula() {
			return constanciaMatricula;
		}
		public void setConstanciaMatricula(constanciaMatriculaC constanciaMatricula) {
			this.constanciaMatricula = constanciaMatricula;
		}
		public personaC getPersona() {
			return persona;
		}
		public void setPersona(personaC persona) {
			this.persona = persona;
		}
		public alumnoC getAlumno() {
			return alumno;
		}
		public void setAlumno(alumnoC alumno) {
			this.alumno = alumno;
		}
		public carrerasC getCarrera() {
			return carrera;
		}
		public void setCarrera(carrerasC carrera) {
			this.carrera = carrera;
		}
	}
	
	
	
	
	public String getFiltroAlumno() {
		return filtroAlumno;
	}
	public void setFiltroAlumno(String filtroAlumno) {
		this.filtroAlumno = filtroAlumno;
	}
	public String getFiltroPersona() {
		return filtroPersona;
	}
	public void setFiltroPersona(String filtroPersona) {
		this.filtroPersona = filtroPersona;
	}
	
	
	
	public String getFiltroCarrea() {
		return filtroCarrea;
	}




	public void setFiltroCarrea(String filtroCarrea) {
		this.filtroCarrea = filtroCarrea;
	}

	
	
}
