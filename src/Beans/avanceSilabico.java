package Beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.registroTemasDAO;
import ENTIDAD.registroTemasC;


@ManagedBean(name = "avanceSilabicoB")
@ViewScoped
public class avanceSilabico {
	private List<registroTemasC> registroTemasL=new ArrayList<>();

	
	public avanceSilabico() {
		
		registroTemasL=new registroTemasDAO().mostrarRegistroTemas(); 
				
	}
	
	
	
	
	public void insertar(registroTemasC item){
		
		new registroTemasDAO().insertar(item);
	}
	
	
	
	
	
	public List<registroTemasC> getRegistroTemasL() {
		return registroTemasL;
	}

	public void setRegistroTemasL(List<registroTemasC> registroTemasL) {
		this.registroTemasL = registroTemasL;
	}
	
	
	
	
	
	
	
}
