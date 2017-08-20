package Beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



import DAO.aulaDAO;
import DAO.horarioSemanalDAO;
import DAO.tipoAulaDAO;
import ENTIDAD.aulaC;
import ENTIDAD.feriadosC;
import ENTIDAD.horarioCuerpoC;
import ENTIDAD.tipoAulaC;

@ManagedBean(name="horarioAulaAdminB")
@ViewScoped
public class horarioAulaAdmin {
	private List<tipoAulaC> tipoAulaL=new ArrayList<>();
	private int tipoAula;
	private List<aulaC> aulaL=new ArrayList<>();
	private String aula;
	private List<horarioCabecera> horarioCabeceraL;
	
	
	
	
	
	
	
	
	

	private Date fechaInicio=util.fechaHoy();  
	
	public void anterior(){
        fechaInicio=util.dateAdd(fechaInicio, 5, -14);
        mostrarHorario();
        
    }
    public void siguiente(){
        fechaInicio=util.dateAdd(fechaInicio, 5, 0);
        mostrarHorario();
        
    }


	public horarioAulaAdmin() {
	
		tipoAulaL=new tipoAulaDAO().mostrarTipoAula();
		int diaLunes=util.datePart(0, fechaInicio)-2;
	    fechaInicio=util.dateAdd(fechaInicio, 5, -diaLunes);
	}
	
	public void mostrarHorarioAula(){
		fechaInicio=util.fechaHoy();
		int diaLunes=util.datePart(0, fechaInicio)-2;
	    fechaInicio=util.dateAdd(fechaInicio, 5, -diaLunes);
	    mostrarHorario();
	}
	
	
	
public void mostrarHorario(){
		
	    
	    
	 	horarioCabeceraL=new ArrayList<>();
        
        
        for (int dia=1;dia<=7;dia++){
       	 horarioCabecera itemDia =new horarioCabecera(fechaInicio,dia);
        
            for(horarioCuerpoC itemHorario :new horarioSemanalDAO().mostrarHorarioPersonalDocente("%","%", dia,fechaInicio,"%","%",aula)){
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
	
	public String getAula() {
		return aula;
	}



	public void setAula(String aula) {
		this.aula = aula;
	}



	public List<aulaC> getAulaL() {
		return aulaL;
	}



	public void setAulaL(List<aulaC> aulaL) {
		this.aulaL = aulaL;
	}



	public int getTipoAula() {
		return tipoAula;
	}



	public void setTipoAula(int tipoAula) {
		this.tipoAula = tipoAula;
	}


	
	public void mostrarAula(){
		aulaL=new aulaDAO().mostrarAula(tipoAula);
	}
	

	public List<tipoAulaC> getTipoAulaL() {
		return tipoAulaL;
	}

	public void setTipoAulaL(List<tipoAulaC> tipoAulaL) {
		this.tipoAulaL = tipoAulaL;
	}
	public List<horarioCabecera> getHorarioCabeceraL() {
		return horarioCabeceraL;
	}
	public void setHorarioCabeceraL(List<horarioCabecera> horarioCabeceraL) {
		this.horarioCabeceraL = horarioCabeceraL;
	}
}
