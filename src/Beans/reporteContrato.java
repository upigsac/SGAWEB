package Beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.carrerasDAO;
import DAO.personaDAO;
import ENTIDAD.carrerasC;
import ENTIDAD.personaC;

@ManagedBean(name="reporteContratoB")
@ViewScoped
public class reporteContrato {
	private List<personaC> personaL;
	private List<carrerasC> carreraL;
	private int institucion;
	private String periodo;
	private String carrera;
	private String persona;
	
	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	public void load(int institucion,String periodo){
		this.institucion=institucion;
		this.periodo=periodo;
		carreraL=new carrerasDAO().listaCarreraPeriodo(institucion, periodo);
	}
	
	public void mostrarPersona(){
		personaL=new personaDAO().mostrarPersonaCarrera(institucion, periodo, carrera);
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

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	
	
	
	
	
	


	
	
	
	
	

}
