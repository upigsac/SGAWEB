package Beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.alumnoDAO;
import DAO.carrerasDAO;
import DAO.institucionDAO;
import DAO.mensajeAlumnoDAO;
import DAO.mensajeDAO;
import DAO.periodoDAO;
import DAO.personaDAO;
import ENTIDAD.alumnoC;
import ENTIDAD.carrerasC;
import ENTIDAD.institucionC;
import ENTIDAD.mensajeAlumnoC;
import ENTIDAD.mensajeC;
import ENTIDAD.periodoC;
import ENTIDAD.personaC;

@ManagedBean(name="mensajeB")
@ViewScoped
public class mensaje {
	private mensajeC mensaje;
	
	private List<mensajeC> mensajeL;
	private List<mensajeAlumnoC> mensajeAlumnoL=new ArrayList<>();
	private mensajeAlumnoC mensajeAlumno=new mensajeAlumnoC(); 
	private String busquedaAlumno;	
	private boolean bandera;
	
	
	private List<institucionC> institucionL;
	private List<carrerasC> carreraL;
	private List<periodoC> periodoL;
	private List<personaC> personaL;
	
	
	
	public mensaje() {
		institucionL=new institucionDAO().mostrarInstitucion();
		mensajeL=new mensajeDAO().mostrarMensaje();
	}
	
	
	
	
	public List<mensajeC> getMensajeL() {
		return mensajeL;
	}

	public void setMensajeL(List<mensajeC> mensajeL) {
		this.mensajeL = mensajeL;
	}

	public List<personaC> getPersonaL() {
		return personaL;
	}

	public void setPersonaL(List<personaC> personaL) {
		this.personaL = personaL;
	}


	
	public void mostrarPeriodo(){
		periodoL=new periodoDAO().mostrarPeriodoInstitucion(mensajeAlumno.getInstitucion());
	}
	public void mostrarCarrera(){
		carreraL=new carrerasDAO().mostrarCarreraPrincipal(mensajeAlumno.getInstitucion(), mensajeAlumno.getPeriodo());
	}
	
	
	public void filtraPersona(){
		personaL=new personaDAO().filtroPersonaAlumno(mensajeAlumno.getInstitucion(), mensajeAlumno.getPeriodo(),mensajeAlumno.getCarrera(),busquedaAlumno);		
	}
	
	public void editar(mensajeC item){
		mensaje=item;
		bandera=true;
	}
	
	
	public void seleccionaPersona(personaC item){
		alumnoC alumno;
		alumno=new alumnoDAO().mostrarAlumno(mensajeAlumno.getInstitucion(), item.getPersona());
		mensajeAlumno.setMensaje(mensaje.getMensaje());
		mensajeAlumno.setAlumno(alumno.getAlumno()); 
		new mensajeAlumnoDAO().insertar(mensajeAlumno);
		mensajeAlumnoL.add(new mensajeAlumnoC(mensajeAlumno.getMensaje(), mensajeAlumno.getInstitucion(), mensajeAlumno.getPeriodo(), mensajeAlumno.getCarrera(), mensajeAlumno.getAlumno(), 1));
		busquedaAlumno="";
		personaL.clear();
		util.script("$('#txtBusqueda').focus();");
	}
	
	
	public mensajeAlumnoC getMensajeAlumno() {
		return mensajeAlumno;
	}

	public void setMensajeAlumno(mensajeAlumnoC mensajeAlumno) {
		this.mensajeAlumno = mensajeAlumno;
	}

	public List<institucionC> getInstitucionL() {
		return institucionL;
	}
	public void setInstitucionL(List<institucionC> institucionL) {
		this.institucionL = institucionL;
	}
	public List<carrerasC> getCarreraL() {
		return carreraL;
	}
	public void setCarreraL(List<carrerasC> carreraL) {
		this.carreraL = carreraL;
	}
	public List<periodoC> getPeriodoL() {
		return periodoL;
	}
	public void setPeriodoL(List<periodoC> periodoL) {
		this.periodoL = periodoL;
	}
	public void nuevo(){
		mensaje=new mensajeC();
		bandera=true;
	}
	public void insertar(){
		new mensajeDAO().insertar(mensaje);
		mensajeL=new mensajeDAO().mostrarMensaje();
		bandera=false;
	}
	
	public void cancelar(){
		bandera=false;
	}
	
	public void nuevoAlumnoMensaje(mensajeC item){
		mensaje=item;
		mensajeAlumnoL=new mensajeAlumnoDAO().mostrarMensajeAlumno(mensaje.getMensaje());
		
		mensajeAlumno=new mensajeAlumnoC();
		util.script("dlgMensajeAlumno.show()");
	}

	
	
	public void eliminarAlumnoMensaje(int indice){
		
		
		new mensajeAlumnoDAO().eliminar(mensajeAlumnoL.get(indice));
		mensajeAlumnoL.remove(indice);
	}
	
	
	
	public List<mensajeAlumnoC> getMensajeAlumnoL() {
		return mensajeAlumnoL;
	}
	public void setMensajeAlumnoL(List<mensajeAlumnoC> mensajeAlumnoL) {
		this.mensajeAlumnoL = mensajeAlumnoL;
	}
	public String getBusquedaAlumno() {
		return busquedaAlumno;
	}
	public void setBusquedaAlumno(String busquedaAlumno) {
		this.busquedaAlumno = busquedaAlumno;
	}
	public boolean isBandera() {
		return bandera;
	}

	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}

	public mensajeC getMensaje() {
		return mensaje;
	}

	public void setMensaje(mensajeC mensaje) {
		this.mensaje = mensaje;
	}
}
