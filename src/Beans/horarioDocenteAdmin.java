package Beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import DAO.carrerasDAO;
import DAO.horarioSemanalDAO;
import DAO.personaDAO;
import DAO.personalDAO;
import ENTIDAD.carrerasC;
import ENTIDAD.feriadosC;
import ENTIDAD.horarioCuerpoC;
import ENTIDAD.personaC;
import ENTIDAD.personalC;

@ManagedBean(name="horarioDocenteAdminB")
@ViewScoped

public class horarioDocenteAdmin {
	private List<horarioCabecera> horarioCabeceraL;
	private List<carrerasC> carreraL=new ArrayList<>();
	private List<personaC> personaL=new ArrayList<>();;
	private String carrera="%";
	private String persona;
	private personalC personal;
	private int institucion;
	private String periodo;
	
	
	
	
	
	public void load(int institucion,String periodo){
		carrera="%";
		this.institucion=institucion;
		this.periodo=periodo;
		
		carreraL=new carrerasDAO().listaCarreraPeriodo(institucion, periodo);		
		persona="";
		mostrarPersona();
	}

	public void mostrarPersona(){
		personaL=new personaDAO().mostrarPersonaCarrera(institucion, periodo, carrera);
		horarioCabeceraL=new ArrayList<>();
		
	}

	private Date fechaInicio=util.fechaHoy();  
	
	public void anterior(){
        fechaInicio=util.dateAdd(fechaInicio, 5, -14);
        mostrarHorario();
        
    }
    public void siguiente(){
        fechaInicio=util.dateAdd(fechaInicio, 5, 0);
        mostrarHorario();
        
    }


public horarioDocenteAdmin() {
	
	int diaLunes=util.datePart(0, fechaInicio)-2;
    fechaInicio=util.dateAdd(fechaInicio, 5, -diaLunes);
}

public void mostrarHorarioPersonal(){
	personal=new personalDAO().mostrarPersona(persona);
	fechaInicio=util.fechaHoy();
	int diaLunes=util.datePart(0, fechaInicio)-2;
    fechaInicio=util.dateAdd(fechaInicio, 5, -diaLunes);
	mostrarHorario();
}




public void mostrarHorario(){
		
	    
	    
	 	horarioCabeceraL=new ArrayList<>();
        
        
        for (int dia=1;dia<=7;dia++){
       	 horarioCabecera itemDia =new horarioCabecera(fechaInicio,dia);
        
       	for(horarioCuerpoC itemHorario :new horarioSemanalDAO().mostrarHorarioPersonalDocente("%","%", dia,fechaInicio,personal.getPersonal(),"%","%")){
            	itemDia.getHorarioCuerpoL().add(itemHorario);
            }
         
             
                
        
            fechaInicio=util.dateAdd(fechaInicio, 5, 1);
        
            horarioCabeceraL.add(itemDia);
        }
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

public List<carrerasC> getCarreraL() {
	return carreraL;
}
public void setCarreraL(List<carrerasC> carreraL) {
	this.carreraL = carreraL;
}
public List<personaC> getPersonaL() {
	return personaL;
}
public void setPersonaL(List<personaC> personaL) {
	this.personaL = personaL;
}
public String getCarrera() {
	return carrera;
}
public void setCarrera(String carrera) {
	this.carrera = carrera;
}
public String getPersona() {
	return persona;
}
public void setPersona(String persona) {
	this.persona = persona;
}
public List<horarioCabecera> getHorarioCabeceraL() {
	return horarioCabeceraL;
}
public void setHorarioCabeceraL(List<horarioCabecera> horarioCabeceraL) {
	this.horarioCabeceraL = horarioCabeceraL;
}
}
