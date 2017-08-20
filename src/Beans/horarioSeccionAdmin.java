package Beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import DAO.carrerasDAO;
import DAO.cicloDAO;
import DAO.horarioSemanalDAO;
import DAO.seccionDAO;
import DAO.turnoDAO;
import ENTIDAD.carrerasC;
import ENTIDAD.cicloC;
import ENTIDAD.feriadosC;
import ENTIDAD.horarioCuerpoC;
import ENTIDAD.seccionC;
import ENTIDAD.turnoC;

@ManagedBean(name="horarioSeccionAdminB")
@ViewScoped
public class horarioSeccionAdmin {
	private List<carrerasC> carreraL=new ArrayList<>();
	private List<cicloC> cicloL=new ArrayList<>();
	private List<turnoC> turnoL=new ArrayList<>();
	private List<seccionC> seccionL=new ArrayList<>();
	private String carrera;
	private String ciclo="%";
	private String turno="%";
	private String seccion;
	private int institucion;
	private String periodo;
	
	private List<horarioCabecera> horarioCabeceraL;
	
	
	public horarioSeccionAdmin() {
		// TODO Auto-generated constructor stub
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
	
	
	public void load(int institucion,String periodo){
		this.institucion=institucion;
		this.periodo=periodo;
		carreraL=new carrerasDAO().listaCarreraPeriodo(institucion, periodo);
	}
	
	public void mostrarNivelAcademico(){
		cicloL=new cicloDAO().mostrarCiclo(institucion, carrera);
	}
	public void mostrarTurno(){
		turnoL=new turnoDAO().mostrarTurno(institucion, periodo, carrera, ciclo);
	}
	public void mostrarSeccion(){
		seccionL=new seccionDAO().mostrarSeccion(institucion, periodo, carrera, ciclo, turno);
	}
	
	public void mostrarHorarioSeccion(){
		fechaInicio=util.fechaHoy();
		int diaLunes=util.datePart(0, fechaInicio)-2;
	    fechaInicio=util.dateAdd(fechaInicio, 5, -diaLunes);
	    mostrarHorario();
	}
	
	
	
public void mostrarHorario(){
		
	    
	    
	 	horarioCabeceraL=new ArrayList<>();
        
        
        for (int dia=1;dia<=7;dia++){
       	 horarioCabecera itemDia =new horarioCabecera(fechaInicio,dia);
        
       	for(horarioCuerpoC itemHorario :new horarioSemanalDAO().mostrarHorarioPersonalDocente("%","%", dia,fechaInicio,"%",seccion,"%")){
            	itemDia.getHorarioCuerpoL().add(itemHorario);
            }
         
             
                
        
            fechaInicio=util.dateAdd(fechaInicio, 5, 1);
        
            horarioCabeceraL.add(itemDia);
        }
	}

	
	
	
	
	
	

public List<horarioCabecera> getHorarioCabeceraL() {
		return horarioCabeceraL;
	}
	public void setHorarioCabeceraL(List<horarioCabecera> horarioCabeceraL) {
		this.horarioCabeceraL = horarioCabeceraL;
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

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
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

	public List<seccionC> getSeccionL() {
		return seccionL;
	}

	public void setSeccionL(List<seccionC> seccionL) {
		this.seccionL = seccionL;
	}


	
	
	
}
