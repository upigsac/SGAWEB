package Beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.alumnoPeriodoDAO;
import ENTIDAD.alumnoPeriodoC;
import ENTIDAD.personaC;

@ManagedBean(name="rankingAlumnoB")
@ViewScoped
public class rankingAlumno {
	
	
	private List<detalleRankingC> detalleRankingL=new ArrayList<>();
	
	
	
	
	
	
	public rankingAlumno() {
		// TODO Auto-generated constructor stub
		 
		
		
	}
	
	public void load(int institucion,String periodo,String carrera){
		detalleRankingL=new alumnoPeriodoDAO().mostrarAlumnoPeriodoRanking(institucion,periodo,carrera,"%","%");
	}
	
	
	
	public List<detalleRankingC> getDetalleRankingL() {
		return detalleRankingL;
	}











	public void setDetalleRankingL(List<detalleRankingC> detalleRankingL) {
		this.detalleRankingL = detalleRankingL;
	}











	public static class detalleRankingC{
		private alumnoPeriodoC alumnoPeriodo=new alumnoPeriodoC();
		private personaC persona=new personaC();
		public detalleRankingC() {
			// TODO Auto-generated constructor stub
		}
		
		public alumnoPeriodoC getAlumnoPeriodo() {
			return alumnoPeriodo;
		}
		public void setAlumnoPeriodo(alumnoPeriodoC alumnoPeriodo) {
			this.alumnoPeriodo = alumnoPeriodo;
		}
		public personaC getPersona() {
			return persona;
		}
		public void setPersona(personaC persona) {
			this.persona = persona;
		}
		
		
		
	}
}
