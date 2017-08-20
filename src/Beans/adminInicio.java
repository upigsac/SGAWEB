package Beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.personaNoteDAO;
import ENTIDAD.personaNoteC;

@ManagedBean(name="adminInicioB")
@ViewScoped
public class adminInicio {
	private List<personaNoteC> personaNoteL=new ArrayList<>();
	String persona;
	
	public void load(String persona){
		this.persona=persona;
		personaNoteL=new personaNoteDAO().mostrarNotePersona(persona);
	}
	
	public adminInicio() {
		// TODO Auto-generated constructor stub
		
	}
	
	public void insertarNote(personaNoteC item){
		System.out.println("top: "+item.getTop());
		System.out.println("left: "+item.getLeft());
		new personaNoteDAO().insertar(item);
		
	}
	
	public void nuevoNote(){
		new personaNoteDAO().insertar(new personaNoteC(persona, "yellow", -1, null, 0, 0, 1));
		personaNoteL=new personaNoteDAO().mostrarNotePersona(persona);
	}
	
	public void eliminarNote(personaNoteC item){
		new personaNoteDAO().eliminar(item);
		personaNoteL=new personaNoteDAO().mostrarNotePersona(persona);
	}
	

	public List<personaNoteC> getPersonaNoteL() {
		return personaNoteL;
	}

	public void setPersonaNoteL(List<personaNoteC> personaNoteL) {
		this.personaNoteL = personaNoteL;
	} 
	
	
	
	
	
	
}
