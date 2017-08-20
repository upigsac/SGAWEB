/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;



import DAO.alumnoCursoSeccionDAO;
import DAO.cursoDAO;
import DAO.foroPersonaCursoSeccionPreguntaDAO;
import DAO.foroPersonaCursoSeccionRespuestaDAO;
import DAO.personaDAO;
import ENTIDAD.alumnoCursoSeccionC;
import ENTIDAD.cursosC;
import ENTIDAD.foroPersonaCursoSeccionPreguntaC;
import ENTIDAD.foroPersonaCursoSeccionRespuestaC;
import ENTIDAD.personaC;
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
@ManagedBean(name="foroB")
@ViewScoped
public class foro {
    private List<foroAlumno> foroAlumnoL=new ArrayList<>();
    private List<foroAlumnoDocente> foroAlumnoDocenteL=new ArrayList<>();
    private foroAlumnoDocente foroAlumnoDocente=new foroAlumnoDocente();
  //  private alumnoCursoSeccionC alumnoCursoSeccion=new alumnoCursoSeccionC();    
    
    private foroPersonaCursoSeccionPreguntaC foroPersonaCursoSeccionPregunta;
    private foroPersonaCursoSeccionRespuestaC foroPersonaCursoSeccionRespuesta;
    
    
    
    foroPersonaCursoSeccionPreguntaDAO foroPersonaCursoSeccionPreguntaD;
    foroPersonaCursoSeccionRespuestaDAO foroPersonaCursoSeccionRespuestaD;
    alumnoCursoSeccionDAO alumnoCursoSeccionD;
    personaDAO personaD;
    cursoDAO cursoD;
    
    
    
    @ManagedProperty(value ="#{usuarioB}")
    private usuario usuB;
    @PostConstruct
    public void init() {
        alumnoCursoSeccionD=new alumnoCursoSeccionDAO();
        personaD=new personaDAO();
        cursoD=new cursoDAO();
        foroAlumnoDocente itemAlumnoDocente;
        for (alumnoCursoSeccionC itemAlumnoCursoSeccion : alumnoCursoSeccionD.mostrarAlumnoCursosSeccionMatriculados(usuB.getInstitucionS(), usuB.getPeriodoS(), usuB.getUsu())) {
            itemAlumnoDocente=new foroAlumnoDocente();
            itemAlumnoDocente.alumnoCursoSeccion=itemAlumnoCursoSeccion;
            itemAlumnoDocente.persona=personaD.mostrarPersona(itemAlumnoCursoSeccion.getInstitucion(), itemAlumnoCursoSeccion.getPeriodo(), itemAlumnoCursoSeccion.getMalla(), itemAlumnoCursoSeccion.getCarrera(), itemAlumnoCursoSeccion.getSeccion(), itemAlumnoCursoSeccion.getCurso());
            itemAlumnoDocente.curso=cursoD.mostrarCurso(itemAlumnoCursoSeccion.getCurso());
           foroAlumnoDocenteL.add(itemAlumnoDocente);
                    
        }
        if(foroAlumnoDocenteL.size()>0){
           seleccionAlumnoCurso(foroAlumnoDocenteL.get(0)); 
        }
        
        
        //alumnoCursoSeccion=new alumnoCursoSeccionC (1, "20132", "000001", "200702", "IS1001", "SIS10N1", "2009010010", "0", 0.0, 1);
        
      
    }

    public foro() {
     
        
    }

    
      public void seleccionAlumnoCurso(foroAlumnoDocente itemforoAlumnoDocente){
        //alumnoCursoSeccion=itemAlumnoCursoSeccion;
        foroAlumnoDocente=itemforoAlumnoDocente;
        
        foroPersonaCursoSeccionPregunta=new foroPersonaCursoSeccionPreguntaC(-1, foroAlumnoDocente.alumnoCursoSeccion.getInstitucion(), foroAlumnoDocente.alumnoCursoSeccion.getPeriodo(), foroAlumnoDocente.alumnoCursoSeccion.getCarrera(), foroAlumnoDocente.alumnoCursoSeccion.getMalla(), foroAlumnoDocente.alumnoCursoSeccion.getCurso(), foroAlumnoDocente.alumnoCursoSeccion.getSeccion(),2,  usuB.getPersona().getPersona(), null, 1);
        foroPersonaCursoSeccionRespuesta=new foroPersonaCursoSeccionRespuestaC(-1, -1, foroAlumnoDocente.alumnoCursoSeccion.getInstitucion(), foroAlumnoDocente.alumnoCursoSeccion.getPeriodo(), foroAlumnoDocente.alumnoCursoSeccion.getCarrera(), foroAlumnoDocente.alumnoCursoSeccion.getMalla(), foroAlumnoDocente.alumnoCursoSeccion.getCurso(), foroAlumnoDocente.alumnoCursoSeccion.getSeccion(),2, usuB.getPersona().getPersona(), null, 1);       
        mostrarForoAlumno();       
    }
      
       public void eliminarForoPregunta(foroPersonaCursoSeccionPreguntaC item){
        foroPersonaCursoSeccionPreguntaD=new foroPersonaCursoSeccionPreguntaDAO();
        foroPersonaCursoSeccionPreguntaD.eliminar(item);
        mostrarForoAlumno();
    }
        public void eliminarForoRespuesta(foroPersonaCursoSeccionRespuestaC item){
        foroPersonaCursoSeccionRespuestaD=new foroPersonaCursoSeccionRespuestaDAO();
        foroPersonaCursoSeccionRespuestaD.eliminar(item);
        mostrarForoAlumno();
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
    
    
 
    public void mostrarForoAlumno(){
        foroAlumnoL.clear();
        foroAlumno itemForoAlumno;
        foroPersonaCursoSeccionPreguntaD=new foroPersonaCursoSeccionPreguntaDAO();
        for (foroPersonaCursoSeccionPreguntaC itemPregunta : foroPersonaCursoSeccionPreguntaD.mostrarPregunta(foroAlumnoDocente.getAlumnoCursoSeccion().getInstitucion(), foroAlumnoDocente.getAlumnoCursoSeccion().getPeriodo(), foroAlumnoDocente.getAlumnoCursoSeccion().getCarrera(), foroAlumnoDocente.getAlumnoCursoSeccion().getMalla(), foroAlumnoDocente.getAlumnoCursoSeccion().getCurso(), foroAlumnoDocente.getAlumnoCursoSeccion().getSeccion())) {
           itemForoAlumno=new foroAlumno(itemPregunta);
           foroPersonaCursoSeccionRespuestaD=new foroPersonaCursoSeccionRespuestaDAO();
            for (foroPersonaCursoSeccionRespuestaC itemRespuesta : foroPersonaCursoSeccionRespuestaD.mostrarRespuesta(itemPregunta.getPregunta(), itemPregunta.getInstitucion(), itemPregunta.getPeriodo(), itemPregunta.getCarrera(), itemPregunta.getMalla(), itemPregunta.getCurso(), itemPregunta.getSeccion())) {
                itemForoAlumno.getForoPersonaCursoSeccionRespuestaL().add(itemRespuesta);
            }
            foroAlumnoL.add(itemForoAlumno);
        }
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

    /**
     * @return the foroAlumnoDocenteL
     */
    public List<foroAlumnoDocente> getForoAlumnoDocenteL() {
        return foroAlumnoDocenteL;
    }

    /**
     * @param foroAlumnoDocenteL the foroAlumnoDocenteL to set
     */
    public void setForoAlumnoDocenteL(List<foroAlumnoDocente> foroAlumnoDocenteL) {
        this.foroAlumnoDocenteL = foroAlumnoDocenteL;
    }

    /**
     * @return the foroAlumnoDocente
     */
    public foroAlumnoDocente getForoAlumnoDocente() {
        return foroAlumnoDocente;
    }

    /**
     * @param foroAlumnoDocente the foroAlumnoDocente to set
     */
    public void setForoAlumnoDocente(foroAlumnoDocente foroAlumnoDocente) {
        this.foroAlumnoDocente = foroAlumnoDocente;
    }
       
       
       public static class foroAlumnoDocente{
        private alumnoCursoSeccionC alumnoCursoSeccion=new alumnoCursoSeccionC();
        private personaC persona=new personaC();
        private cursosC curso=new cursosC();

        public foroAlumnoDocente() {
        }
        

        /**
         * @return the alumnoCursoSeccion
         */
        public alumnoCursoSeccionC getAlumnoCursoSeccion() {
            return alumnoCursoSeccion;
        }

        /**
         * @param alumnoCursoSeccion the alumnoCursoSeccion to set
         */
        public void setAlumnoCursoSeccion(alumnoCursoSeccionC alumnoCursoSeccion) {
            this.alumnoCursoSeccion = alumnoCursoSeccion;
        }

        /**
         * @return the persona
         */
        public personaC getPersona() {
            return persona;
        }

        /**
         * @param persona the persona to set
         */
        public void setPersona(personaC persona) {
            this.persona = persona;
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

      
        
    }

    /**
     * @return the foroAlumnoL
     */
    public List<foroAlumno> getForoAlumnoL() {
        return foroAlumnoL;
    }

    /**
     * @param foroAlumnoL the foroAlumnoL to set
     */
    public void setForoAlumnoL(List<foroAlumno> foroAlumnoL) {
        this.foroAlumnoL = foroAlumnoL;
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

  

   
    
    
    public class foroAlumno{
        private  foroPersonaCursoSeccionPreguntaC foroPersonaCursoSeccionPregunta=new foroPersonaCursoSeccionPreguntaC();
        private List<foroPersonaCursoSeccionRespuestaC> foroPersonaCursoSeccionRespuestaL=new ArrayList<>();

        public foroAlumno() {
        }

        public foroAlumno(foroPersonaCursoSeccionPreguntaC foroPersonaCursoSeccionPregunta) {
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
    
    
   
}
