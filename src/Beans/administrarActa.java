package Beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.carrerasDAO;
import DAO.cursoDAO;
import DAO.cursoPeriodoDAO;
import DAO.personaDAO;
import DAO.seccionDAO;
import DAO.tipoExamenDAO;
import ENTIDAD.carrerasC;
import ENTIDAD.cursoPeriodoC;
import ENTIDAD.cursosC;
import ENTIDAD.personaC;
import ENTIDAD.seccionC;
import ENTIDAD.tipoExamenC;

@ManagedBean(name="administrarActaB")
@ViewScoped
public class administrarActa {
	private List<personaC> personaL=new ArrayList<>();
	private List<carrerasC> carreraL=new ArrayList<>();
	private List<seccionC> seccionL=new ArrayList<>();
	private List<cursosC> cursoL=new ArrayList<>();
	private List<tipoExamenC> tipoExamenL=new ArrayList<>();
	private cursoPeriodoC cursoPeriodo=new cursoPeriodoC();
	
	
	

	private int institucion;

	private String periodo;
	
	
	
	private String persona;
	private String carrera;
	private String seccion;
	private String curso;
	private String tipoExamen;
	
	
	
	

	

	public administrarActa() {
		// TODO Auto-generated constructor stub
		
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
		tipoExamen="";
		carreraL=new ArrayList<>();
		seccionL=new ArrayList<>();
		cursoL=new ArrayList<>();
		tipoExamenL=new ArrayList<>();
		cursoPeriodo=new cursoPeriodoC();
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
	
	public void mostrarTipoExamen(){
		cursoPeriodo=new cursoPeriodoDAO().mostrarCursoPerido(institucion, periodo, carrera,"%", curso,seccion);
		System.out.println("FORMULA " +cursoPeriodo.getFormula());
		tipoExamenL=new tipoExamenDAO().mostrarTipoExamen(cursoPeriodo.getFormula());
		tipoExamen="";
	}
	
	
	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
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

	public List<tipoExamenC> getTipoExamenL() {
		return tipoExamenL;
	}

	public void setTipoExamenL(List<tipoExamenC> tipoExamenL) {
		this.tipoExamenL = tipoExamenL;
	}

	public List<personaC> getPersonaL() {
		return personaL;
	}

	public void setPersonaL(List<personaC> personaL) {
		this.personaL = personaL;
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
	
	
	public cursoPeriodoC getCursoPeriodo() {
		return cursoPeriodo;
	}

	public void setCursoPeriodo(cursoPeriodoC cursoPeriodo) {
		this.cursoPeriodo = cursoPeriodo;
	}
	public String getTipoExamen() {
		return tipoExamen;
	}

	public void setTipoExamen(String tipoExamen) {
		this.tipoExamen = tipoExamen;
	}
	public int getInstitucion() {
		return institucion;
	}

	public void setInstitucion(int institucion) {
		this.institucion = institucion;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
}
