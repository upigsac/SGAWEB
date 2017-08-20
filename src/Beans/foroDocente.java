/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.carrerasDAO;
import DAO.cursoDAO;
import DAO.cursoSeccionDocenteDAO;
import DAO.foroPersonaCursoSeccionPreguntaDAO;
import DAO.foroPersonaCursoSeccionRespuestaDAO;
import DAO.seccionDAO;
import ENTIDAD.carrerasC;
import ENTIDAD.cursoSeccionDocenteC;
import ENTIDAD.cursosC;
import ENTIDAD.foroPersonaCursoSeccionPreguntaC;
import ENTIDAD.foroPersonaCursoSeccionRespuestaC;
import ENTIDAD.seccionC;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang.StringEscapeUtils;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author Administrador
 */
@ManagedBean(name="foroDocenteB")
@ViewScoped
public class foroDocente {
    private List<foroPreguntaRespuestaDocente> foroPreguntaRespuestaDocenteL=new ArrayList<>();
    private List<foroCursoSeccion> foroCursoSeccionL=new ArrayList<>();
    private foroCursoSeccion foroCursoSeccion;
    
    
    private foroPersonaCursoSeccionPreguntaC foroPersonaCursoSeccionPregunta;
    private foroPersonaCursoSeccionRespuestaC foroPersonaCursoSeccionRespuesta;
    
    
    
    foroPersonaCursoSeccionPreguntaDAO foroPersonaCursoSeccionPreguntaD;
    foroPersonaCursoSeccionRespuestaDAO foroPersonaCursoSeccionRespuestaD;
    
    
    @ManagedProperty(value ="#{usuarioB}")
    private usuario usuB;
    @PostConstruct
    public void init() {
        
        
         cursoSeccionDocenteD=new cursoSeccionDocenteDAO();
        carreraD=new carrerasDAO();
        cursoD=new cursoDAO();
        seccionD=new seccionDAO();
        foroCursoSeccion itemForoCursoSeccion;
        for (cursoSeccionDocenteC itemCursoSeccionDocente : cursoSeccionDocenteD.mostrarCursoSeccionDocente(usuB.getInstitucionS(), usuB.getPeriodoS(), usuB.getPersonal().getPersonal())) {
            itemForoCursoSeccion=new foroCursoSeccion();
            itemForoCursoSeccion.cursoSeccionDocente=itemCursoSeccionDocente;
            itemForoCursoSeccion.carrera=carreraD.mostrarCarrerasCodigo(itemCursoSeccionDocente.getCarrera());
            itemForoCursoSeccion.curso= cursoD.mostrarCurso(itemCursoSeccionDocente.getCurso());
            itemForoCursoSeccion.seccion=seccionD.mostrarSeccion(itemCursoSeccionDocente.getInstitucion(), itemCursoSeccionDocente.getSeccion());
            foroCursoSeccionL.add(itemForoCursoSeccion);
        }
        if(foroCursoSeccionL.size()>0){
            seleccionCursoSeccion(foroCursoSeccionL.get(0));
        }
       
        
        
        
        
      
    }

    public foroDocente() {
        
  
    }
    
    
    
    
  
    
     public void seleccionCursoSeccion(foroCursoSeccion itemforoCursoSeccion){
        //alumnoCursoSeccion=itemAlumnoCursoSeccion;
        foroCursoSeccion=itemforoCursoSeccion;
        
        foroPersonaCursoSeccionPregunta=new foroPersonaCursoSeccionPreguntaC( -1, foroCursoSeccion.cursoSeccionDocente.getInstitucion(), foroCursoSeccion.cursoSeccionDocente.getPeriodo(), foroCursoSeccion.cursoSeccionDocente.getCarrera(), foroCursoSeccion.cursoSeccionDocente.getMalla(), foroCursoSeccion.cursoSeccionDocente.getCurso(), foroCursoSeccion.cursoSeccionDocente.getSeccion(),4,  usuB.getPersona().getPersona(), null, 1);
        foroPersonaCursoSeccionRespuesta=new foroPersonaCursoSeccionRespuestaC(-1, -1, foroCursoSeccion.cursoSeccionDocente.getInstitucion(), foroCursoSeccion.cursoSeccionDocente.getPeriodo(), foroCursoSeccion.cursoSeccionDocente.getCarrera(), foroCursoSeccion.cursoSeccionDocente.getMalla(), foroCursoSeccion.cursoSeccionDocente.getCurso(), foroCursoSeccion.cursoSeccionDocente.getSeccion(),4, usuB.getPersona().getPersona(), null, 1);       
        mostrarForoDocente();       
    }
    
    
    
    cursoSeccionDocenteDAO cursoSeccionDocenteD;
    carrerasDAO carreraD;
    cursoDAO cursoD;
    seccionDAO seccionD;
    
    
    
           public void eliminarForoPregunta(foroPersonaCursoSeccionPreguntaC item){
        foroPersonaCursoSeccionPreguntaD=new foroPersonaCursoSeccionPreguntaDAO();
        foroPersonaCursoSeccionPreguntaD.eliminar(item);
        mostrarForoDocente();
    }
        public void eliminarForoRespuesta(foroPersonaCursoSeccionRespuestaC item){
        foroPersonaCursoSeccionRespuestaD=new foroPersonaCursoSeccionRespuestaDAO();
        foroPersonaCursoSeccionRespuestaD.eliminar(item);
        mostrarForoDocente();
    }
        
        
        private final static String PREGUNTA = "/pregunta";
    public void insertarPregunta(){
        String summary = "PREGUNTA";
        String detail =  "COMENTO .....";
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(PREGUNTA, new FacesMessage(StringEscapeUtils.escapeHtml(summary), StringEscapeUtils.escapeHtml(detail)));
        
        
        foroPersonaCursoSeccionPreguntaD=new foroPersonaCursoSeccionPreguntaDAO();
        foroPersonaCursoSeccionPreguntaD.insertar(getForoPersonaCursoSeccionPregunta());
        getForoPersonaCursoSeccionPregunta().setMensaje("");
    //    mostrarForoAlumno();
      //  util.script("$('.foro-cuerpo').scrollTop(50000);");
    }  
        
        
     private final static String REPUESTA = "/respuesta";
       public void insertarRespuesta(int pregunta){        
        String summary = "RESPUESTA";
        String detail =  "COMENTO .....";
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(REPUESTA, new FacesMessage(StringEscapeUtils.escapeHtml(summary), StringEscapeUtils.escapeHtml(detail)));
        foroPersonaCursoSeccionRespuesta.setPregunta(pregunta);
        foroPersonaCursoSeccionRespuestaD=new foroPersonaCursoSeccionRespuestaDAO();        
        foroPersonaCursoSeccionRespuestaD.insertar(foroPersonaCursoSeccionRespuesta);        
        foroPersonaCursoSeccionRespuesta.setMensaje("");        
     //   mostrarForoAlumno();
    }
    
  public void mostrarForoDocente(){
        foroPreguntaRespuestaDocenteL.clear();
        foroPreguntaRespuestaDocente foroPreguntaRespuestaDocente;
        foroPersonaCursoSeccionPreguntaD=new foroPersonaCursoSeccionPreguntaDAO();
        for (foroPersonaCursoSeccionPreguntaC itemPregunta : foroPersonaCursoSeccionPreguntaD.mostrarPregunta(foroCursoSeccion.cursoSeccionDocente.getInstitucion(), foroCursoSeccion.cursoSeccionDocente.getPeriodo(), foroCursoSeccion.cursoSeccionDocente.getCarrera(), foroCursoSeccion.cursoSeccionDocente.getMalla(), foroCursoSeccion.cursoSeccionDocente.getCurso(), foroCursoSeccion.cursoSeccionDocente.getSeccion())) {
           foroPreguntaRespuestaDocente=new foroPreguntaRespuestaDocente(itemPregunta);
           foroPersonaCursoSeccionRespuestaD=new foroPersonaCursoSeccionRespuestaDAO();
            for (foroPersonaCursoSeccionRespuestaC itemRespuesta : foroPersonaCursoSeccionRespuestaD.mostrarRespuesta(itemPregunta.getPregunta(), itemPregunta.getInstitucion(), itemPregunta.getPeriodo(), itemPregunta.getCarrera(), itemPregunta.getMalla(), itemPregunta.getCurso(), itemPregunta.getSeccion())) {
                foroPreguntaRespuestaDocente.getForoPersonaCursoSeccionRespuestaL().add(itemRespuesta);
            }
            foroPreguntaRespuestaDocenteL.add(foroPreguntaRespuestaDocente);
        }
    }

    /**
     * @return the foroPreguntaRespuestaDocenteL
     */
    public List<foroPreguntaRespuestaDocente> getForoPreguntaRespuestaDocenteL() {
        return foroPreguntaRespuestaDocenteL;
    }

    /**
     * @param foroPreguntaRespuestaDocenteL the foroPreguntaRespuestaDocenteL to set
     */
    public void setForoPreguntaRespuestaDocenteL(List<foroPreguntaRespuestaDocente> foroPreguntaRespuestaDocenteL) {
        this.foroPreguntaRespuestaDocenteL = foroPreguntaRespuestaDocenteL;
    }

    /**
     * @return the usuB
     */
    public usuario getUsuB() {
        return usuB;
    }

    /**
     * @param usuB the usuB to set
     */
    public void setUsuB(usuario usuB) {
        this.usuB = usuB;
    }
  
  
  
   public class foroPreguntaRespuestaDocente{
        private  foroPersonaCursoSeccionPreguntaC foroPersonaCursoSeccionPregunta;
        private List<foroPersonaCursoSeccionRespuestaC> foroPersonaCursoSeccionRespuestaL=new ArrayList<>();

        public foroPreguntaRespuestaDocente() {
        }

        public foroPreguntaRespuestaDocente(foroPersonaCursoSeccionPreguntaC foroPersonaCursoSeccionPregunta) {
            this.foroPersonaCursoSeccionPregunta = foroPersonaCursoSeccionPregunta;
        }

      
        
        
        

        /**
         * @return the foroPersonaCursoSeccionPregunta
         */
        public foroPersonaCursoSeccionPreguntaC getForoPersonaCursoSeccionPregunta() {
            return foroPersonaCursoSeccionPregunta;
        }

        /**
         * @param foroPersonaCursoSeccionPregunta the foroPersonaCursoSeccionPregunta to set
         */
        public void setForoPersonaCursoSeccionPregunta(foroPersonaCursoSeccionPreguntaC foroPersonaCursoSeccionPregunta) {
            this.foroPersonaCursoSeccionPregunta = foroPersonaCursoSeccionPregunta;
        }

        /**
         * @return the foroPersonaCursoSeccionRespuestaL
         */
        public List<foroPersonaCursoSeccionRespuestaC> getForoPersonaCursoSeccionRespuestaL() {
            return foroPersonaCursoSeccionRespuestaL;
        }

        /**
         * @param foroPersonaCursoSeccionRespuestaL the foroPersonaCursoSeccionRespuestaL to set
         */
        public void setForoPersonaCursoSeccionRespuestaL(List<foroPersonaCursoSeccionRespuestaC> foroPersonaCursoSeccionRespuestaL) {
            this.foroPersonaCursoSeccionRespuestaL = foroPersonaCursoSeccionRespuestaL;
        }
   }
    /**
     * @return the foroCursoSeccionL
     */
    public List<foroCursoSeccion> getForoCursoSeccionL() {
        return foroCursoSeccionL;
    }

    /**
     * @param foroCursoSeccionL the foroCursoSeccionL to set
     */
    public void setForoCursoSeccionL(List<foroCursoSeccion> foroCursoSeccionL) {
        this.foroCursoSeccionL = foroCursoSeccionL;
    }

    /**
     * @return the foroPersonaCursoSeccionRespuesta
     */
    public foroPersonaCursoSeccionRespuestaC getForoPersonaCursoSeccionRespuesta() {
        return foroPersonaCursoSeccionRespuesta;
    }

    /**
     * @param foroPersonaCursoSeccionRespuesta the foroPersonaCursoSeccionRespuesta to set
     */
    public void setForoPersonaCursoSeccionRespuesta(foroPersonaCursoSeccionRespuestaC foroPersonaCursoSeccionRespuesta) {
        this.foroPersonaCursoSeccionRespuesta = foroPersonaCursoSeccionRespuesta;
    }

    /**
     * @return the foroPersonaCursoSeccionPregunta
     */
    public foroPersonaCursoSeccionPreguntaC getForoPersonaCursoSeccionPregunta() {
        return foroPersonaCursoSeccionPregunta;
    }

    /**
     * @param foroPersonaCursoSeccionPregunta the foroPersonaCursoSeccionPregunta to set
     */
    public void setForoPersonaCursoSeccionPregunta(foroPersonaCursoSeccionPreguntaC foroPersonaCursoSeccionPregunta) {
        this.foroPersonaCursoSeccionPregunta = foroPersonaCursoSeccionPregunta;
    }

    /**
     * @return the foroCursoSeccion
     */
    public foroCursoSeccion getForoCursoSeccion() {
        return foroCursoSeccion;
    }

    /**
     * @param foroCursoSeccion the foroCursoSeccion to set
     */
    public void setForoCursoSeccion(foroCursoSeccion foroCursoSeccion) {
        this.foroCursoSeccion = foroCursoSeccion;
    }
    
    
    public class foroCursoSeccion{
        private cursoSeccionDocenteC cursoSeccionDocente;
        private carrerasC carrera;
        private cursosC curso;
        private seccionC seccion;

        /**
         * @return the cursoSeccionDocente
         */
        public cursoSeccionDocenteC getCursoSeccionDocente() {
            return cursoSeccionDocente;
        }

        /**
         * @param cursoSeccionDocente the cursoSeccionDocente to set
         */
        public void setCursoSeccionDocente(cursoSeccionDocenteC cursoSeccionDocente) {
            this.cursoSeccionDocente = cursoSeccionDocente;
        }

        /**
         * @return the carrera
         */
        public carrerasC getCarrera() {
            return carrera;
        }

        /**
         * @param carrera the carrera to set
         */
        public void setCarrera(carrerasC carrera) {
            this.carrera = carrera;
        }

        /**
         * @return the curso
         */
        public cursosC getCurso() {
            return curso;
        }

        /**
         * @param curso the curso to set
         */
        public void setCurso(cursosC curso) {
            this.curso = curso;
        }

        /**
         * @return the seccion
         */
        public seccionC getSeccion() {
            return seccion;
        }

        /**
         * @param seccion the seccion to set
         */
        public void setSeccion(seccionC seccion) {
            this.seccion = seccion;
        }
        
    }
}
