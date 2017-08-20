package Beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import DAO.parentescoDAO;
import DAO.personaDAO;
import DAO.personaParentescoDAO;
import ENTIDAD.parentescoC;
import ENTIDAD.personaC;
import ENTIDAD.personaParentescoC;


@ViewScoped
@ManagedBean(name="personaParentescoB")
public class personaParentesco {
	private List<detallePersonaParentescoC> detallePersonaParentescoL=new ArrayList<>();
	
	private List<parentescoC> parentescoL=new ArrayList<>();
	private List<personaParentescoC> personaParentescoL=new ArrayList<>();
	private personaParentescoC personaParentesco=new personaParentescoC();
	
	private personaC persona=new personaC();
	private personaC personaP=new personaC();
	private List<personaC> personaL=new ArrayList<>();
	
	
	private String busPaterno="";
	private String busMaterno="";
	private String busNombres="";
	
	public personaParentesco() {
		// TODO Auto-generated constructor stub
		
	}
	
	public void nuevo(){
		parentescoL=new parentescoDAO().mostrarParentesco();		
		personaParentesco=new personaParentescoC(persona.getPersona(), 0, null, false, false, false, 0, 0, 0.0, 1);
		util.script("dlgParentesco.show()");
	}
	
	public void eliminar(personaParentescoC itemPersonaParentesco ){
		new personaParentescoDAO().eliminar(itemPersonaParentesco);
		mostrarPersonaParentesco();
	}
	
	public void insertar(){
		new personaParentescoDAO().insertar(personaParentesco);
		mostrarPersonaParentesco();
		util.script("dlgParentesco.hide()");
	}
	
	public void filtroPersona(){
		personaL=new personaDAO().filtroPersona(busPaterno.trim(), busMaterno.trim(), busNombres.trim());
	}
	
	
	
	public void seleccionBusqueda(){
	
	
		
		
	}
	
	public void mostrarPersonaParentesco(){
		detallePersonaParentescoL=new ArrayList<>();
		detallePersonaParentescoC 	itemDetallePersonaParentescoC=null;
		for (personaParentescoC itemPersonaParentesco : new personaParentescoDAO().mostrarPersonaParentesco(persona.getPersona())) {
			itemDetallePersonaParentescoC=new detallePersonaParentescoC(itemPersonaParentesco);
			itemDetallePersonaParentescoC.persona=new personaDAO().getBuscaPersonaCodigo(itemPersonaParentesco.getPersonaParentesco());
			itemDetallePersonaParentescoC.parentesco=new parentescoDAO().mostrarParentesco(itemPersonaParentesco.getParentesco());
			detallePersonaParentescoL.add(itemDetallePersonaParentescoC);
		}
	}
	
	public void seleccionParentesco(){
		personaParentesco.setPersonaParentesco(personaP.getPersona());
		
	}
	
	
	
	
	public List<detallePersonaParentescoC> getDetallePersonaParentescoL() {
		return detallePersonaParentescoL;
	}

	public void setDetallePersonaParentescoL(List<detallePersonaParentescoC> detallePersonaParentescoL) {
		this.detallePersonaParentescoL = detallePersonaParentescoL;
	}




	public static class  detallePersonaParentescoC{
		private personaParentescoC personaParentesco;
		private personaC persona=new personaC();
		private parentescoC parentesco=new parentescoC();
		
		public detallePersonaParentescoC(personaParentescoC personaParentesco) {
			this.personaParentesco=personaParentesco;
		
		}
		
		public personaParentescoC getPersonaParentesco() {
			return personaParentesco;
		}
		public void setPersonaParentesco(personaParentescoC personaParentesco) {
			this.personaParentesco = personaParentesco;
		}
		public personaC getPersona() {
			return persona;
		}
		public void setPersona(personaC persona) {
			this.persona = persona;
		}
		public parentescoC getParentesco() {
			return parentesco;
		}
		public void setParentesco(parentescoC parentesco) {
			this.parentesco = parentesco;
		}
	}
	
	
	
	public personaC getPersonaP() {
		return personaP;
	}

	public void setPersonaP(personaC personaP) {
		this.personaP = personaP;
	}

	public personaParentescoC getPersonaParentesco() {
		return personaParentesco;
	}

	public void setPersonaParentesco(personaParentescoC personaParentesco) {
		this.personaParentesco = personaParentesco;
	}

	public String getBusPaterno() {
		return busPaterno;
	}







	public void setBusPaterno(String busPaterno) {
		this.busPaterno = busPaterno;
	}







	public String getBusMaterno() {
		return busMaterno;
	}







	public void setBusMaterno(String busMaterno) {
		this.busMaterno = busMaterno;
	}







	public String getBusNombres() {
		return busNombres;
	}







	public void setBusNombres(String busNombres) {
		this.busNombres = busNombres;
	}







	public List<personaC> getPersonaL() {
		return personaL;
	}



	public void setPersonaL(List<personaC> personaL) {
		this.personaL = personaL;
	}




	
	

	public List<personaParentescoC> getPersonaParentescoL() {
		return personaParentescoL;
	}

	public void setPersonaParentescoL(List<personaParentescoC> personaParentescoL) {
		this.personaParentescoL = personaParentescoL;
	}

	public personaC getPersona() {
		return persona;
	}

	public void setPersona(personaC persona) {
		this.persona = persona;
	}

	public List<parentescoC> getParentescoL() {
		return parentescoL;
	}

	public void setParentescoL(List<parentescoC> parentescoL) {
		this.parentescoL = parentescoL;
	} 
	
	
}
