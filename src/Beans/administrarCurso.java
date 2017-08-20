
package Beans;

import DAO.cursoSeccionPrincipalDAO;
import DAO.cursoSeccionSecundarioDAO;
import ENTIDAD.cursoSeccionPrincipalC;
import ENTIDAD.cursoSeccionSecundarioC;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;

@ManagedBean(name="administrarCursoB")
@ViewScoped
public class administrarCurso {
    private List<cursoSeccion> cursoSeccionBusquedaL;
    private List<cursoSeccion> cursoSeccionBusquedaLS=new ArrayList<>();
    private List<cursoSeccion> cursoSeccionPrincipalL=new ArrayList<>();
    private cursoSeccion cursoSeccionPrincipal;
    private List<cursoSeccion> cursoSeccionSecundarioL;
    private String accion;
    private String filtroCurso="";
    private String filtroCursoP="";
    
    
    
    private int institucion;
    private String periodo;
    private String periodoS="20162";
    
    private boolean bandera;
 

    public administrarCurso() {


    }
    public void load(int institucion,String periodo){
    	this.institucion=institucion;
    	this.periodo=periodo;
    	mostrarCursoPricipal();
    }
    
    public void mostrarCursoPricipal(){
    	cursoSeccionPrincipalL=new cursoSeccionPrincipalDAO().mostrarCursoSeccionP(institucion, periodo,"%",filtroCursoP);
    }

    
    public void nuevoCurso(String opcion){
        accion=opcion;            
        cursoSeccionBusquedaLS=new ArrayList<>();
        cursoSeccionBusquedaL=new cursoSeccionPrincipalDAO().mostrarCursoSeccionBusqueda(1,periodoS, filtroCurso +"%");
        util.script("dlgCursoPrincipal.show()");
        util.script("$('#txtBusqueda').focus()");
    }
    
    
    public void eliminarCursoPrincipal(cursoSeccion item){
  
        new cursoSeccionPrincipalDAO().eliminar(new cursoSeccionPrincipalC(item.principal,item.institucion,item.periodo,item.carrera,item.malla,item.curso,item.seccion,1));
        
        cursoSeccionPrincipalL=new cursoSeccionPrincipalDAO().mostrarCursoSeccionP(1, periodo,"%",filtroCursoP);
    }
     public void eliminarCursoSecundario(cursoSeccion item){
    
        new cursoSeccionSecundarioDAO().eliminar(new cursoSeccionSecundarioC(item.principal,item.institucion,item.periodo,item.carrera,item.malla,item.curso,item.seccion,1));       
        
        cursoSeccionSecundarioL=new cursoSeccionPrincipalDAO().mostrarCursoSeccionS(cursoSeccionPrincipal.principal,cursoSeccionPrincipal.institucion, cursoSeccionPrincipal.periodo);
    }
    
    public void insertarCurso(){
        if(accion.endsWith("P")){
        	
              for(cursoSeccion item :cursoSeccionBusquedaLS){
            	  new cursoSeccionPrincipalDAO().insertar(new cursoSeccionPrincipalC(-1,item.institucion,item.periodo,item.carrera,item.malla,item.curso,item.seccion,1));  
              }
                      
              cursoSeccionPrincipalL=new cursoSeccionPrincipalDAO().mostrarCursoSeccionP(1, periodo,"%",filtroCursoP);
       
        }else{
            for(cursoSeccion item :cursoSeccionBusquedaLS){
            	new cursoSeccionSecundarioDAO().insertar(new cursoSeccionSecundarioC(cursoSeccionPrincipal.principal,item.institucion,item.periodo,item.carrera,item.malla,item.curso,item.seccion,1));	
            }
        	        
            cursoSeccionSecundarioL=new cursoSeccionPrincipalDAO().mostrarCursoSeccionS(cursoSeccionPrincipal.principal,cursoSeccionPrincipal.institucion, cursoSeccionPrincipal.periodo);
      
        }
      
    } 
    
    public void seccionarCursoPrincipal(cursoSeccion itemPrincipal){
        cursoSeccionPrincipal=itemPrincipal;

        cursoSeccionSecundarioL=new cursoSeccionPrincipalDAO().mostrarCursoSeccionS(itemPrincipal.principal,itemPrincipal.institucion, itemPrincipal.periodo);
        bandera=true;
    }
    
    public void atras(){
    	bandera=false;
    }
    
    public void busquedaCurso(){
  
        cursoSeccionBusquedaL=new cursoSeccionPrincipalDAO().mostrarCursoSeccionBusqueda(1,periodoS, filtroCurso +"%");
    }
    
    
     
    public void onRowSelect(SelectEvent event) {
    
    }
    
    
    
    public List<cursoSeccion> getCursoSeccionBusquedaLS() {
		return cursoSeccionBusquedaLS;
	}
	public void setCursoSeccionBusquedaLS(List<cursoSeccion> cursoSeccionBusquedaLS) {
		this.cursoSeccionBusquedaLS = cursoSeccionBusquedaLS;
	}
	public String getFiltroCursoP() {
		return filtroCursoP;
	}
	public void setFiltroCursoP(String filtroCursoP) {
		this.filtroCursoP = filtroCursoP;
	}
	public boolean isBandera() {
		return bandera;
	}


	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}


	public List<cursoSeccion> getCursoSeccionBusquedaL() {
        return cursoSeccionBusquedaL;
    }
    public void setCursoSeccionBusquedaL(List<cursoSeccion> cursoSeccionBusquedaL) {
        this.cursoSeccionBusquedaL = cursoSeccionBusquedaL;
    }
    public List<cursoSeccion> getCursoSeccionPrincipalL() {
        return cursoSeccionPrincipalL;
    }
    public void setCursoSeccionPrincipalL(List<cursoSeccion> cursoSeccionPrincipalL) {
        this.cursoSeccionPrincipalL = cursoSeccionPrincipalL;
    }
    public List<cursoSeccion> getCursoSeccionSecundarioL() {
        return cursoSeccionSecundarioL;
    }
    public void setCursoSeccionSecundarioL(List<cursoSeccion> cursoSeccionSecundarioL) {
        this.cursoSeccionSecundarioL = cursoSeccionSecundarioL;
    }
    public String getFiltroCurso() {
        return filtroCurso;
    }
    public void setFiltroCurso(String filtroCurso) {
        this.filtroCurso = filtroCurso;
    }

    public cursoSeccion getCursoSeccionPrincipal() {
        return cursoSeccionPrincipal;
    }
    public void setCursoSeccionPrincipalS(cursoSeccion cursoSeccionPrincipal) {
        this.cursoSeccionPrincipal = cursoSeccionPrincipal;
    }
    public String getAccion() {
        return accion;
    }
    public void setAccion(String accion) {
        this.accion = accion;
    }
 
    public String getPeriodoS() {
        return periodoS;
    }
    public void setPeriodoS(String periodoS) {
        this.periodoS = periodoS;
    }

    
    
    
    
    
    
    public static class  cursoSeccion{
        private int principal;
        private int institucion;
        private String periodo;
        private String carrera;
        private String desCarrera;
        private String malla;
        private String curso;
        private String desCurso;
        private String seccion;
        private String desSeccion;
        private String turno;
        private String desTurno;
        private String estado;
     

        
        public String getPeriodo() {
            return periodo;
        }
        public void setPeriodo(String periodo) {
            this.periodo = periodo;
        }
        public String getCarrera() {
            return carrera;
        }
        public void setCarrera(String carrera) {
            this.carrera = carrera;
        }
        public String getMalla() {
            return malla;
        }
        public void setMalla(String malla) {
            this.malla = malla;
        }
        public String getCurso() {
            return curso;
        }
        public void setCurso(String curso) {
            this.curso = curso;
        }
        public String getDesCurso() {
            return desCurso;
        }
        public void setDesCurso(String desCurso) {
            this.desCurso = desCurso;
        }
        public String getSeccion() {
            return seccion;
        }
        public void setSeccion(String seccion) {
            this.seccion = seccion;
        }
        public String getDesSeccion() {
            return desSeccion;
        }
        public void setDesSeccion(String desSeccion) {
            this.desSeccion = desSeccion;
        }
        public int getPrincipal() {
            return principal;
        }
        public void setPrincipal(int principal) {
            this.principal = principal;
        }
        public int getInstitucion() {
            return institucion;
        }
        public void setInstitucion(int institucion) {
            this.institucion = institucion;
        }
        public String getDesCarrera() {
            return desCarrera;
        }
        public void setDesCarrera(String desCarrera) {
            this.desCarrera = desCarrera;
        }
        public String getEstado() {
            return estado;
        }
        public void setEstado(String estado) {
            this.estado = estado;
        }
        public String getTurno() {
            return turno;
        }
        public void setTurno(String turno) {
            this.turno = turno;
        }
        public String getDesTurno() {
            return desTurno;
        }
        public void setDesTurno(String desTurno) {
            this.desTurno = desTurno;
        }
        
    }
    
}
