package Beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.carrerasDAO;
import DAO.cursoDAO;
import DAO.personaDAO;
import DAO.seccionDAO;
import ENTIDAD.carrerasC;

import ENTIDAD.cursosC;
import ENTIDAD.personaC;
import ENTIDAD.seccionC;

@ManagedBean(name="adminRegistroEvaluacionB")
@ViewScoped
public class adminRegistroEvaluacion {
	private List<personaC> personaL=new ArrayList<>();
	private List<carrerasC> carreraL=new ArrayList<>();
	private List<seccionC> seccionL=new ArrayList<>();
	private List<cursosC> cursoL=new ArrayList<>();
	
	private int institucion;

	private String periodo;
	
	
	
	private String persona;
	private String carrera;
	private String seccion;
	private String curso;
	
	public adminRegistroEvaluacion() {
		// TODO Auto-generated constructor stub
		personaL=new personaDAO().BuscaPersonaPeriodoInstitucion(institucion, periodo);
	}
	public void refrescar(int institucion,String periodo){
		this.institucion=institucion;
		this.periodo=periodo;
		System.out.println(this.institucion);
		System.out.println(this.periodo);
		personaL=new personaDAO().BuscaPersonaPeriodoInstitucion(institucion, periodo);
		persona="";
		carrera="";
		seccion="";
		curso="";
		
		carreraL=new ArrayList<>();
		seccionL=new ArrayList<>();
		cursoL=new ArrayList<>();
		
	}
	
	public void mostrarCarrera(){
		carreraL=new carrerasDAO().mostrarCarreraPrincipal(institucion, periodo,persona);
		carrera="";
	}
	public void mostrarSeccion(){
		seccionL=new seccionDAO().mostrarSeccionPrincipal(institucion, periodo, carrera, persona);
		seccion="";
	}
	public void mostrarCurso(){
		cursoL=new cursoDAO().mostrarCursoPrincipal(institucion, periodo, carrera, "%", "%", seccion, "%",persona);
		curso="";
	}
	
	
	
	
	
	
	
	
	
	
	
	public List<personaC> getPersonaL() {
		return personaL;
	}
	public void setPersonaL(List<personaC> personaL) {
		this.personaL = personaL;
	}
	public List<carrerasC> getCarreraL() {
		return carreraL;
	}
	public void setCarreraL(List<carrerasC> carreraL) {
		this.carreraL = carreraL;
	}
	public List<seccionC> getSeccionL() {
		return seccionL;
	}
	public void setSeccionL(List<seccionC> seccionL) {
		this.seccionL = seccionL;
	}
	public List<cursosC> getCursoL() {
		return cursoL;
	}
	public void setCursoL(List<cursosC> cursoL) {
		this.cursoL = cursoL;
	}
	public String getPersona() {
		return persona;
	}
	public void setPersona(String persona) {
		this.persona = persona;
	}
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	public String getSeccion() {
		return seccion;
	}
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	
}
