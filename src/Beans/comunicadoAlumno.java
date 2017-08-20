package Beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.mensajeDAO;
import ENTIDAD.mensajeC;

@ManagedBean(name="comunicadoAlumnoB")
@ViewScoped
public class comunicadoAlumno {
	
	
	
	private List<mensajeC> mensajeL;
	
	
	
	public comunicadoAlumno() {
		// TODO Auto-generated constructor stub
	}
	public void load(int institucion,String periodo,String carrera,String alumno){
		mensajeL=new mensajeDAO().mostrarMensaje(institucion,periodo,carrera, alumno);
	}
	public List<mensajeC> getMensajeL() {
		return mensajeL;
	}
	public void setMensajeL(List<mensajeC> mensajeL) {
		this.mensajeL = mensajeL;
	}


	
	
}
