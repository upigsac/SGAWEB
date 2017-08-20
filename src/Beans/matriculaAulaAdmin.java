package Beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.carrerasDAO;
import DAO.cicloDAO;
import DAO.turnoDAO;
import ENTIDAD.carrerasC;
import ENTIDAD.cicloC;
import ENTIDAD.turnoC;

@ManagedBean(name="matriculaAulaAdminB")
@ViewScoped
public class matriculaAulaAdmin {
	private List<carrerasC> carreraL=new ArrayList<>();
	private List<cicloC> cicloL=new ArrayList<>();
	private List<turnoC> turnoL=new ArrayList<>();
	
	
	private String carrera="%";
	private String ciclo="%";
	private String turno="%";
	 
	
	
	public matriculaAulaAdmin() {
		// TODO Auto-generated constructor stub
		
	}
	
	public void mostrarCiclo(){
		cicloL=new cicloDAO().mostrarCiclo(1, carrera);
	}
	
	
	
	
	public void load(int institucion,String periodo){
		carreraL=new carrerasDAO().listaCarreraPeriodo(institucion, periodo);
		turnoL=new turnoDAO().mostrarTurno();
	}
	
	
	
	
	
	
	
	public List<carrerasC> getCarreraL() {
		return carreraL;
	}
	public void setCarreraL(List<carrerasC> carreraL) {
		this.carreraL = carreraL;
	}
	public List<cicloC> getCicloL() {
		return cicloL;
	}
	public void setCicloL(List<cicloC> cicloL) {
		this.cicloL = cicloL;
	}
	public List<turnoC> getTurnoL() {
		return turnoL;
	}
	public void setTurnoL(List<turnoC> turnoL) {
		this.turnoL = turnoL;
	}
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	public String getCiclo() {
		return ciclo;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	
	
	
}
