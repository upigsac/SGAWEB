

package Beans;


import DAO.cicloCarreraDAO;

import DAO.horMallaCurricularCursoActDAO;
import DAO.horMallaCurricularCursoReqDAO;
import ENTIDAD.alumnoCursoSeccionC;
import ENTIDAD.cicloCarreraC;
import ENTIDAD.cursosC;
import ENTIDAD.horMallaCurricularCursoActC;
import ENTIDAD.horMallaCurricularCursoReqC;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="mallaCurricularB")
@ViewScoped

public class mallaCurricular {
    private List<detalleMallaCurricular> detalleMallaCurricularL=new ArrayList<>();
    
    private int institucion;
    private String carrera;
    private String malla;
    private String alumno;
    
    
    
    public void load(int institucion,String carrera,String malla,String alumno){
    	this.institucion=institucion;
    	this.carrera=carrera;
    	this.malla=malla;
    	this.alumno=alumno;
    	mostrarMallaCurricular();
    }
    
    
    public mallaCurricular() {
       
        
 
    }
    public void mostrarMallaCurricular(){
    	 detalleMallaCurricular itemMallaCurricular;
         
         for (cicloCarreraC item : new cicloCarreraDAO().mostrarCicloCarrera(institucion, carrera)) {
             itemMallaCurricular=new detalleMallaCurricular(item);
           
             
             for(detalleMallaCurso itemDetalleMallaCurso :new horMallaCurricularCursoActDAO().mostrarMostrarMallaCurso(item.getInstitucion(), item.getCarrera(),malla, item.getCiclo(), alumno) ){
            	  itemMallaCurricular.getDetalleMallaCursoL().add(itemDetalleMallaCurso);
            	  
            		for(horMallaCurricularCursoReqC itemRequisito :new horMallaCurricularCursoReqDAO().mostrarMallaCurricularCurso(item.getInstitucion(), item.getCarrera(), itemDetalleMallaCurso.horMallaCurricularCursoAct.getMalla(), itemDetalleMallaCurso.horMallaCurricularCursoAct.getCurso())){
                 		itemDetalleMallaCurso.getHorMallaCurricularCursoReqL().add(itemRequisito);
                 	}
             }
     
             
                 detalleMallaCurricularL.add(itemMallaCurricular);
         }
    }
    
    
    public  class detalleMallaCurricular{
    	private cicloCarreraC cicloCarrera;         
        
        private List<detalleMallaCurso> detalleMallaCursoL=new ArrayList<>();

        public detalleMallaCurricular() {
        }
        public detalleMallaCurricular(cicloCarreraC cicloCarrera) {
        	this.cicloCarrera=cicloCarrera;
        }
       
        public cicloCarreraC getCicloCarrera() {
			return cicloCarrera;
		}
		public void setCicloCarrera(cicloCarreraC cicloCarrera) {
			this.cicloCarrera = cicloCarrera;
		}
		
		public List<detalleMallaCurso> getDetalleMallaCursoL() {
			return detalleMallaCursoL;
		}
		public void setDetalleMallaCursoL(List<detalleMallaCurso> detalleMallaCursoL) {
			this.detalleMallaCursoL = detalleMallaCursoL;
		}
        
        
        
        
    }
    
    
    public static class detalleMallaCurso{
    	private horMallaCurricularCursoActC horMallaCurricularCursoAct=new horMallaCurricularCursoActC();
    	private cursosC curso=new cursosC();
    	private alumnoCursoSeccionC alumnoCursoSeccion=new alumnoCursoSeccionC(); 
    	private List<horMallaCurricularCursoReqC> horMallaCurricularCursoReqL=new ArrayList<>();
    	public detalleMallaCurso() {
			// TODO Auto-generated constructor stub
		}
    	public detalleMallaCurso(horMallaCurricularCursoActC horMallaCurricularCursoAct) {
			this.horMallaCurricularCursoAct=horMallaCurricularCursoAct;
		}
    	
    
    	
    	
    	
    	
		public alumnoCursoSeccionC getAlumnoCursoSeccion() {
			return alumnoCursoSeccion;
		}
		public void setAlumnoCursoSeccion(alumnoCursoSeccionC alumnoCursoSeccion) {
			this.alumnoCursoSeccion = alumnoCursoSeccion;
		}
		public horMallaCurricularCursoActC getHorMallaCurricularCursoAct() {
			return horMallaCurricularCursoAct;
		}
		public void setHorMallaCurricularCursoAct(horMallaCurricularCursoActC horMallaCurricularCursoAct) {
			this.horMallaCurricularCursoAct = horMallaCurricularCursoAct;
		}
		public cursosC getCurso() {
			return curso;
		}
		public void setCurso(cursosC curso) {
			this.curso = curso;
		}
		public List<horMallaCurricularCursoReqC> getHorMallaCurricularCursoReqL() {
			return horMallaCurricularCursoReqL;
		}
		public void setHorMallaCurricularCursoReqL(List<horMallaCurricularCursoReqC> horMallaCurricularCursoReqL) {
			this.horMallaCurricularCursoReqL = horMallaCurricularCursoReqL;
		}
	
    	
    	
    	
    	
    	
    }
    
    
    
    
    
    public List<detalleMallaCurricular> getDetalleMallaCurricularL() {
        return detalleMallaCurricularL;
    }
    public void setDetalleMallaCurricularL(List<detalleMallaCurricular> detalleMallaCurricularL) {
        this.detalleMallaCurricularL = detalleMallaCurricularL;
    }
}
