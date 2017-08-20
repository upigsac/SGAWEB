package Beans;



import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.alumnoPracticaDAO;
import DAO.constanciaPracticaDAO;
import ENTIDAD.alumnoPracticaC;



@ManagedBean(name="constanciaPracticaB")
@ViewScoped
public class constanciaPractica {
	private String filtroAlumno="";
	private String filtroDesPersona="";
	private List<constanciaC> constanciaL=new ArrayList<>();
	private List<constanciaC> constanciaSL=new ArrayList<>();
	
	
	public constanciaPractica() {
		// TODO Auto-generated constructor stub
		filtrar();
	}
	public void filtrar(){
		constanciaL=new constanciaPracticaDAO().mostrarPracticaCostancia(filtroDesPersona, filtroAlumno);
		constanciaSL=new ArrayList<>();
	}
	
	
	
	public List<constanciaC> getConstanciaSL() {
		return constanciaSL;
	}
	public void setConstanciaSL(List<constanciaC> constanciaSL) {
		this.constanciaSL = constanciaSL;
	}
	public String imprimir(){
		
		String parramArray="";
		for (constanciaC item : constanciaSL) {
			
			parramArray+=item.alumnoPractica.getAlumno()+",";
		}
		
		return parramArray.substring(0, parramArray.length()-1) ;
	}
	public void entregar(alumnoPracticaC itemAlumnoPractica){
		itemAlumnoPractica.setFechaConstancia(util.fechaHoy());
		new alumnoPracticaDAO().insertar(itemAlumnoPractica);
		filtrar();
	}


	public static class constanciaC{
		
		private alumnoPracticaC alumnoPractica=new alumnoPracticaC();		
		private String paterno;
		private String materno;
		private String nombres;
		
		private Date fechaInicio;
		private Date fechaFinal;
		
		
		
		
		public alumnoPracticaC getAlumnoPractica() {
			return alumnoPractica;
		}
		public void setAlumnoPractica(alumnoPracticaC alumnoPractica) {
			this.alumnoPractica = alumnoPractica;
		}
	
		public String getPaterno() {
			return paterno;
		}
		public void setPaterno(String paterno) {
			this.paterno = paterno;
		}
		public String getMaterno() {
			return materno;
		}
		public void setMaterno(String materno) {
			this.materno = materno;
		}
		public String getNombres() {
			return nombres;
		}
		public void setNombres(String nombres) {
			this.nombres = nombres;
		}
		public Date getFechaInicio() {
			return fechaInicio;
		}
		public void setFechaInicio(Date fechaInicio) {
			this.fechaInicio = fechaInicio;
		}
		public Date getFechaFinal() {
			return fechaFinal;
		}
		public void setFechaFinal(Date fechaFinal) {
			this.fechaFinal = fechaFinal;
		}
	
		
		
	}
	
	
	
	public String getFiltroAlumno() {
		return filtroAlumno;
	}
	public void setFiltroAlumno(String filtroAlumno) {
		this.filtroAlumno = filtroAlumno;
	}
	public String getFiltroDesPersona() {
		return filtroDesPersona;
	}
	public void setFiltroDesPersona(String filtroDesPersona) {
		this.filtroDesPersona = filtroDesPersona;
	}
	public List<constanciaC> getConstanciaL() {
		return constanciaL;
	}
	public void setConstanciaL(List<constanciaC> constanciaL) {
		this.constanciaL = constanciaL;
	}
}
