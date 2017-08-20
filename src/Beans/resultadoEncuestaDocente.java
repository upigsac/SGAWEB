

package Beans;


import DAO.encuestaDAO;
import ENTIDAD.encuestaPreguntaC;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="resultadoEncuetaDocenteB")
@ViewScoped
public class resultadoEncuestaDocente {
    private List<encuestaPreguntaC>  encuestaPreguntaL;
    private List<detallePrenguta> detallePrengutaL=new ArrayList<detallePrenguta>();
    private List<resumenEncuesta> resumenEncuestaL=new ArrayList<resumenEncuesta>();
    private resumenEncuesta resumenEncuestaS=new resumenEncuesta();
    encuestaDAO encuestaD;
    
    public int suma(){
        int total=0;        
        
        for (detallePrenguta item : detallePrengutaL) {  
            
            total+=item.alumno;            
        }
        return total;
    }
    
    public int sumaTotalCurso(){
        int total=0;        
        for (resumenEncuesta item : resumenEncuestaL) {              
            total+=item.totalMaximo;            
        }
        return total;
    }
    public void  mostrarDetallePregunta(int institucion,String periodo,String carrera,String malla,String curso,String seccion,int encuesta,int grupo,int pregunta){
        encuestaD=new encuestaDAO();
        detallePrengutaL=encuestaD.mostrarResultadoPregunta(institucion, periodo, carrera, malla, curso, seccion, encuesta, grupo, pregunta);                
        
    }
    
       public List<resumenEncuesta> mostrarResultadoDetalle(int institucion,String periodo,String personal){
        
        encuestaD=new encuestaDAO();
        resumenEncuestaL=encuestaD.mostrarResumenResultado(institucion, periodo, personal);                
        return resumenEncuestaL;
    }
    
    
    
    public List<encuestaPreguntaC> getEncuestaPreguntaL() {
        return encuestaPreguntaL;
    }
    public void setEncuestaPreguntaL(List<encuestaPreguntaC> encuestaPreguntaL) {
        this.encuestaPreguntaL = encuestaPreguntaL;
    }
    public List<detallePrenguta> getDetallePrengutaL() {
        return detallePrengutaL;
    }
    public void setDetallePrengutaL(List<detallePrenguta> detallePrengutaL) {
        this.detallePrengutaL = detallePrengutaL;
    }
    public List<resumenEncuesta> getResumenEncuestaL() {
        return resumenEncuestaL;
    }
    public void setResumenEncuestaL(List<resumenEncuesta> resumenEncuestaL) {
        this.resumenEncuestaL = resumenEncuestaL;
    }

    public resumenEncuesta getResumenEncuestaS() {
        return resumenEncuestaS;
    }
    public void setResumenEncuestaS(resumenEncuesta resumenEncuestaS) {
        this.resumenEncuestaS = resumenEncuestaS;
    }
    
    public static class resumenEncuesta{
        private String carrera;
        private String seccion;
        private String curso;
        private int numeroAula;
        private int numeroAlumno;
        private int totalMaximo;
        private int totalObtenido;

       
        public String getCurso() {
            return curso;
        }

       
        public void setCurso(String curso) {
            this.curso = curso;
        }

        
        public int getNumeroAula() {
            return numeroAula;
        }

        
        public void setNumeroAula(int numeroAula) {
            this.numeroAula = numeroAula;
        }

        public int getNumeroAlumno() {
            return numeroAlumno;
        }
        public void setNumeroAlumno(int numeroAlumno) {
            this.numeroAlumno = numeroAlumno;
        }
        public int getTotalMaximo() {
            return totalMaximo;
        }
        public void setTotalMaximo(int totalMaximo) {
            this.totalMaximo = totalMaximo;
        }
        public int getTotalObtenido() {
            return totalObtenido;
        }
        public void setTotalObtenido(int totalObtenido) {
            this.totalObtenido = totalObtenido;
        }
        public String getSeccion() {
            return seccion;
        }
        public void setSeccion(String seccion) {
            this.seccion = seccion;
        }
        public String getCarrera() {
            return carrera;
        }
        public void setCarrera(String carrera) {
            this.carrera = carrera;
        }
        
    }
    
    
    public static class detallePrenguta{
        private int encuesta;
        private int grupo;
        private int pregunta;
        private int respuesta;
        private String desRespuesta;
        private int alumno;
        private int porcetaje;

      
        public int getEncuesta() {
            return encuesta;
        }
        public void setEncuesta(int encuesta) {
            this.encuesta = encuesta;
        }
        public int getGrupo() {
            return grupo;
        }
        public void setGrupo(int grupo) {
            this.grupo = grupo;
        }
        public int getPregunta() {
            return pregunta;
        }
        public void setPregunta(int pregunta) {
            this.pregunta = pregunta;
        }

        public String getDesRespuesta() {
            return desRespuesta;
        }
        public void setDesRespuesta(String desRespuesta) {
            this.desRespuesta = desRespuesta;
        }
        public int getAlumno() {
            return alumno;
        }
        public void setAlumno(int alumno) {
            this.alumno = alumno;
        }
        public int getPorcetaje() {
            return porcetaje;
        }
        public void setPorcetaje(int porcetaje) {
            this.porcetaje = porcetaje;
        }

        public int getRespuesta() {
            return respuesta;
        }
        public void setRespuesta(int respuesta) {
            this.respuesta = respuesta;
        }
        
    }
    
   

}
