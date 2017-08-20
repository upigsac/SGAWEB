

package Beans;

import DAO.administrarPracticaDAO;
import DAO.alumnoPracticaActividadDAO;
import DAO.alumnoPracticaCriterioDAO;
import DAO.alumnoPracticaCriterioNotaDAO;
import DAO.practicaCriterioDAO;
import ENTIDAD.alumnoPracticaActividadC;
import ENTIDAD.alumnoPracticaCriterioC;
import ENTIDAD.alumnoPracticaCriterioNotaC;
import ENTIDAD.practicaCriterioC;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



@ManagedBean(name="practicaCalificacionB")
@ViewScoped
public class practicaCalificacion {
    private List<alumnoPracticaCriterioC> alumnoPracticaCriterioL;
    private List<alumnoPracticaActividadC> alumnoPracticaActividadL;
    private List<alumnoCriterio> alumnoCriterioL=new ArrayList<>();
    private List<alumnoCriterio> alumnoCriterioS=new ArrayList<>();
    private List<detalleNotaCriterio> detalleNotaCriterioL;
    private alumnoCalificacionC alumnoCalificacion=null;
    private List<alumnoCalificacionC> alumnoCalificacionL;
    private String paternoB="";
    private String maternoB="";
    private String nombresB="";
    
    alumnoPracticaActividadDAO alumnoPracticaActividadD;
    alumnoPracticaCriterioDAO alumnoPracticaCriterioD;
    alumnoPracticaCriterioNotaDAO alumnoPracticaCriterioNotaD;
    practicaCriterioDAO  practicaCriterioD;
    administrarPracticaDAO administrarPracticaD;
    public practicaCalificacion() {
        
    }
    
    public void nuevoCriterio(){
    alumnoCriterioS=new ArrayList<>();
    alumnoPracticaCriterioD=new alumnoPracticaCriterioDAO();
    alumnoCriterioL=alumnoPracticaCriterioD.mostrarAlumnoCriterio(alumnoCalificacion.alumno, alumnoCalificacion.curso);
        for (alumnoCriterio itemCriterio : alumnoCriterioL) {
            if (itemCriterio.estadoRegistro==1){
                    alumnoCriterioS.add(itemCriterio);
            }
        }
        util.script("dlgCriterio.show()");
    
    }
    
    public void busqueda(){
    	  paternoB="";
    	  maternoB="";
    	  nombresB="";
    	util.script("dlgBusqueda.show()");
    }
    
    public void insertarCriterio(){
        alumnoPracticaCriterioD=new alumnoPracticaCriterioDAO();
        for (alumnoCriterio item : alumnoCriterioS) {
            
            alumnoPracticaCriterioD.insertar(new alumnoPracticaCriterioC(alumnoCalificacion.alumno, alumnoCalificacion.curso, item.criterio, 2.0, 1));
        }
        mostrarPracticaAlumnoActividad();
        util.script("dlgCriterio.hide()");
    }
    public double totalCalificacion(String fecha){
        double total=0;
        for (detalleNotaCriterio itemCriterio : detalleNotaCriterioL) {
            total+=itemCriterio.practicaCriterioNotaL.get(fecha).getNota();
        }
        return total;
    }
     public void filtroAlumnoPracticaCalificacion(){
        administrarPracticaD=new administrarPracticaDAO();
        alumnoCalificacionL=administrarPracticaD.mostrarAlumnoPracticaCalificacion(paternoB, maternoB, nombresB);
    }
     
     
      
    
    public void insertarNota(int indice,String fecha){
        
        
        
        new alumnoPracticaCriterioNotaDAO().insertar(detalleNotaCriterioL.get(indice).practicaCriterioNotaL.get(fecha));
    }
            
    
    
    public void mostrarPracticaAlumnoActividad(){
    alumnoPracticaActividadD=new alumnoPracticaActividadDAO();
    alumnoPracticaActividadL=alumnoPracticaActividadD.mostrarAlumnoPracticaActivida(alumnoCalificacion.alumno, alumnoCalificacion.curso);
    
    practicaCriterioD=new practicaCriterioDAO();   
    
    alumnoPracticaCriterioNotaD =new alumnoPracticaCriterioNotaDAO();
    detalleNotaCriterioL=new ArrayList<>();
    
        for (practicaCriterioC itemCriterio : practicaCriterioD.mostrarPracticaCriterioAlumno(alumnoCalificacion.alumno, alumnoCalificacion.curso)) {
            detalleNotaCriterio item=new detalleNotaCriterio();
            item.practicaCriterio=itemCriterio;
            for (alumnoPracticaCriterioNotaC itemNota : alumnoPracticaCriterioNotaD.mostrarAlumnoPractica(alumnoCalificacion.alumno, alumnoCalificacion.curso, itemCriterio.getCriterio())) {
                item.getPracticaCriterioNotaL().put(util.convertDate(itemNota.getFecha(), "dd-MM-yyyy"), itemNota);
            }
            detalleNotaCriterioL.add(item);
        }
    }

   
    public List<alumnoCriterio> getAlumnoCriterioL() {
        return alumnoCriterioL;
    }

   
    public void setAlumnoCriterioL(List<alumnoCriterio> alumnoCriterioL) {
        this.alumnoCriterioL = alumnoCriterioL;
    }

    
    public List<alumnoCriterio> getAlumnoCriterioS() {
        return alumnoCriterioS;
    }

  
    public void setAlumnoCriterioS(List<alumnoCriterio> alumnoCriterioS) {
        this.alumnoCriterioS = alumnoCriterioS;
    }
    
    public static class alumnoCriterio{
        private String criterio;
        private String desCriterio;
        private double valor;
        private int estadoRegistro;

        
        public String getCriterio() {
            return criterio;
        }

       
        public void setCriterio(String criterio) {
            this.criterio = criterio;
        }

       
        public String getDesCriterio() {
            return desCriterio;
        }

        
        public void setDesCriterio(String desCriterio) {
            this.desCriterio = desCriterio;
        }

        public double getValor() {
            return valor;
        }

        
        public void setValor(double valor) {
            this.valor = valor;
        }

     

        
        public int getEstadoRegistro() {
            return estadoRegistro;
        }

       
        public void setEstadoRegistro(int estadoRegistro) {
            this.estadoRegistro = estadoRegistro;
        }
    }

    
    public List<detalleNotaCriterio> getDetalleNotaCriterioL() {
        return detalleNotaCriterioL;
    }

   
    public void setDetalleNotaCriterioL(List<detalleNotaCriterio> detalleNotaCriterioL) {
        this.detalleNotaCriterioL = detalleNotaCriterioL;
    }


    public alumnoCalificacionC getAlumnoCalificacion() {
        return alumnoCalificacion;
    }


    public void setAlumnoCalificacion(alumnoCalificacionC alumnoCalificacion) {
        this.alumnoCalificacion = alumnoCalificacion;
    }

  
    public List<alumnoCalificacionC> getAlumnoCalificacionL() {
        return alumnoCalificacionL;
    }

    public void setAlumnoCalificacionL(List<alumnoCalificacionC> alumnoCalificacionL) {
        this.alumnoCalificacionL = alumnoCalificacionL;
    }

  
    public String getPaternoB() {
        return paternoB;
    }

    
    public void setPaternoB(String paternoB) {
        this.paternoB = paternoB;
    }

  
    public String getMaternoB() {
        return maternoB;
    }

    public void setMaternoB(String maternoB) {
        this.maternoB = maternoB;
    }

   
    public String getNombresB() {
        return nombresB;
    }

   
    public void setNombresB(String nombresB) {
        this.nombresB = nombresB;
    }
    
    public static class alumnoCalificacionC{
        private String persona;
        private String desPersona;
        private String carrera;
        private String desCarrera;
        private String alumno;
        private String ciclo;
        private String turno;
        private String sede;
        private String telefono;
        private String celular;
        private String direccion;
        private Date fechaInicio;
        private Date fechaFinal;
        private String curso;

        
        
        
     
        public String getCelular() {
			return celular;
		}


		public void setCelular(String celular) {
			this.celular = celular;
		}


		public String getPersona() {
            return persona;
        }

        
        public void setPersona(String persona) {
            this.persona = persona;
        }

      

        
        public String getCarrera() {
            return carrera;
        }

       
        public void setCarrera(String carrera) {
            this.carrera = carrera;
        }

        
        public String getAlumno() {
            return alumno;
        }

      
        public void setAlumno(String alumno) {
            this.alumno = alumno;
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

      
        public String getSede() {
            return sede;
        }

      
        public void setSede(String sede) {
            this.sede = sede;
        }

      
        public String getTelefono() {
            return telefono;
        }

      
        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

       
        public String getDireccion() {
            return direccion;
        }

       
        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

      
        public String getDesCarrera() {
            return desCarrera;
        }

       
        public void setDesCarrera(String desCarrera) {
            this.desCarrera = desCarrera;
        }



        public String getDesPersona() {
            return desPersona;
        }

        
        public void setDesPersona(String desPersona) {
            this.desPersona = desPersona;
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

        
        public String getCurso() {
            return curso;
        }

        
        public void setCurso(String curso) {
            this.curso = curso;
        }
        
    }
    
    
    public static class  detalleNotaCriterio{
        private practicaCriterioC practicaCriterio;
        private HashMap<String,alumnoPracticaCriterioNotaC> practicaCriterioNotaL=new HashMap<>();

        public detalleNotaCriterio() {
        }

        public detalleNotaCriterio(practicaCriterioC practicaCriterio) {
            this.practicaCriterio = practicaCriterio;
        }
        

        
        public practicaCriterioC getPracticaCriterio() {
            return practicaCriterio;
        }

        
        public void setPracticaCriterio(practicaCriterioC practicaCriterio) {
            this.practicaCriterio = practicaCriterio;
        }

        
        public HashMap<String,alumnoPracticaCriterioNotaC> getPracticaCriterioNotaL() {
            return practicaCriterioNotaL;
        }

        
        public void setPracticaCriterioNotaL(HashMap<String,alumnoPracticaCriterioNotaC> practicaCriterioNotaL) {
            this.practicaCriterioNotaL = practicaCriterioNotaL;
        }

   
        
    }
    
    public List<alumnoPracticaCriterioC> getAlumnoPracticaCriterioL() {
        return alumnoPracticaCriterioL;
    }

    
    public void setAlumnoPracticaCriterioL(List<alumnoPracticaCriterioC> alumnoPracticaCriterioL) {
        this.alumnoPracticaCriterioL = alumnoPracticaCriterioL;
    }

    
    public List<alumnoPracticaActividadC> getAlumnoPracticaActividadL() {
        return alumnoPracticaActividadL;
    }

    
    public void setAlumnoPracticaActividadL(List<alumnoPracticaActividadC> alumnoPracticaActividadL) {
        this.alumnoPracticaActividadL = alumnoPracticaActividadL;
    }

    
    
}
