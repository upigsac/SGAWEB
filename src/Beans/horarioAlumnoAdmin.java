package Beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import DAO.alumnoDAO;
import DAO.carrerasDAO;

import DAO.horarioSemanalDAO;
import DAO.personaDAO;
import ENTIDAD.alumnoC;
import ENTIDAD.carrerasC;
import ENTIDAD.feriadosC;
import ENTIDAD.horarioCuerpoC;
import ENTIDAD.personaC;

@ManagedBean(name="horarioAlumnoAdminB")
@ViewScoped
public class horarioAlumnoAdmin {
	private List<carrerasC> carreraL=new ArrayList<>();
	private List<personaC> personaL;
	
	private int institucion;
	private String periodo;
	private String persona;
	
	
	
	
	
	
	



	private List<horarioCabecera> horarioCabeceraL;


	private String carrera;
	
	private Date fechaInicio=util.fechaHoy();  
	private alumnoC alumno=new alumnoC();
	  
	
	
	


	public void load(int institucion,String periodo){
		this.institucion=institucion;
		this.periodo=periodo;
		carreraL=new carrerasDAO().listaCarreraPeriodo(institucion, periodo);
	}
	 	public void anterior(){
	        fechaInicio=util.dateAdd(fechaInicio, 5, -14);
	        mostrarHorario();
	    }
	    public void siguiente(){
	        fechaInicio=util.dateAdd(fechaInicio, 5, 0);
	        mostrarHorario();
	        
	    }
	    public void mostrarHorarioAlumno(){
	    	alumno=new alumnoDAO().mostrarAlumno(institucion, persona);
	    	fechaInicio=util.fechaHoy();
	    	int diaLunes=util.datePart(0, fechaInicio)-2;
		    fechaInicio=util.dateAdd(fechaInicio, 5, -diaLunes);
	    	mostrarHorario();
	    }

	public  horarioAlumnoAdmin() {
		int diaLunes=util.datePart(0, fechaInicio)-2;
	    fechaInicio=util.dateAdd(fechaInicio, 5, -diaLunes);
	}
	
	public void mostrarHorario(){
		
	    
	    
	 	horarioCabeceraL=new ArrayList<>();
        
        
        for (int dia=1;dia<=7;dia++){
       	 horarioCabecera itemDia =new horarioCabecera(fechaInicio,dia);
          
            for(horarioCuerpoC itemHorario :new horarioSemanalDAO().mostrarHorarioAlumno(""+institucion, periodo, dia, fechaInicio, alumno.getAlumno()))
            {
                System.out.print("------");
                itemDia.getHorarioCuerpoL().add(itemHorario);
            }
           
            fechaInicio=util.dateAdd(fechaInicio, 5, 1);
        
            horarioCabeceraL.add(itemDia);
        }
	}
	
	
	public void mostrarPersona(){
		 personaL = new personaDAO().mostrarAlumnoCarrera(institucion, periodo, carrera);
	}
	
	
	
	
	
	
	
	
	
	
	   public static class horarioCabecera{
	         private Date fecha;
	         private int dia;
	         private List<feriadosC> feriadosL=new ArrayList<>();
	         private List<horarioCuerpoC> horarioCuerpoL=new ArrayList<>();
	         public horarioCabecera(Date fecha, int dia) {
	             this.fecha = fecha;
	             this.dia = dia;
	            
	         }

	         public horarioCabecera() {
	         }
	         public Date getFecha() {
	             return fecha;
	         }
	         public void setFecha(Date fecha) {
	             this.fecha = fecha;
	         }
	         public int getDia() {
	             return dia;
	         }
	         public void setDia(int dia) {
	             this.dia = dia;
	         }
	         public List<feriadosC> getFeriadosL() {
	             return feriadosL;
	         }
	         public void setFeriadosL(List<feriadosC> feriadosL) {
	             this.feriadosL = feriadosL;
	         }

			public List<horarioCuerpoC> getHorarioCuerpoL() {
				return horarioCuerpoL;
			}

			public void setHorarioCuerpoL(List<horarioCuerpoC> horarioCuerpoL) {
				this.horarioCuerpoL = horarioCuerpoL;
			}
	        
	     }
	
	   
	     
	
	
	
	
		public List<horarioCabecera> getHorarioCabeceraL() {
			return horarioCabeceraL;
		}


		public void setHorarioCabeceraL(List<horarioCabecera> horarioCabeceraL) {
			this.horarioCabeceraL = horarioCabeceraL;
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
	public List<personaC> getPersonaL() {
		return personaL;
	}


	public void setPersonaL(List<personaC> personaL) {
		this.personaL = personaL;
	}
	
	public String getPersona() {
		return persona;
	}
	public void setPersona(String persona) {
		this.persona = persona;
	}
}
