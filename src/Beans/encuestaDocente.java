
package Beans;

import DAO.alumnoCursoSeccionDAO;
import DAO.cursoDAO;
import DAO.cursoSeccionDocenteDAO;
import DAO.encuestaCursoSeccionDocenteDAO;
import DAO.encuestaDAO;
import DAO.encuestaGrupoDAO;
import DAO.encuestaPreguntaDAO;
import DAO.encuestaRespuestaDAO;
import DAO.personaDAO;
import ENTIDAD.alumnoCursoSeccionC;
import ENTIDAD.cursoSeccionDocenteC;
import ENTIDAD.cursosC;
import ENTIDAD.encuestaCursoSeccionDocenteC;
import ENTIDAD.encuestaGrupoC;
import ENTIDAD.encuestaGrupoPreguntaC;
import ENTIDAD.encuestaInstitucionC;
import ENTIDAD.encuestaPreguntaC;
import ENTIDAD.encuestaRespuestaC;
import ENTIDAD.personaC;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;



@ManagedBean(name="encuestaDocenteB")
@ViewScoped
public class encuestaDocente{
    private encuestaInstitucionC encuestaInstitucion;
    private List<encuestaGrupoC> encuestaGrupoL;
    private List<encuestaGrupoPreguntaC> encuestaGrupoPreguntaL;
    private List<encuestaRespuestaC> encuestaRespuestaL;
    private encuestaPreguntaC encuestaPregunta;
    private List<encuestaCursoSeccionDocenteC> encuestaCursoSeccionDocenteL=new ArrayList<>();
    
    private List<personaEncuestaGrupo> personaEncuestaGrupoL=new ArrayList<>();
    private int respuesta;
    private boolean bandera;
    private boolean banderaImpresion=false;
    private List<alumnoCursoSeccionC> alumnoCursoSeccionL;
    private alumnoCursoSeccionC alumnoCursoSeccion;
    private List<cursoSeccionDocenteC> cursoSeccionDocenteL;
    private cursoSeccionDocenteC cursoSeccionDocente;
    private List<alumnoCursoPersonal> alumnoCursoPersonalL=new ArrayList<>();
    private alumnoCursoPersonal alumnoCursoPersonal;
    
    
    
    
   @ManagedProperty(value ="#{usuarioB}")
    private usuario usuB;
    
    
    encuestaDAO encuestaD;
    encuestaGrupoDAO encuestaGrupoD;
    encuestaPreguntaDAO encuestaPreguntaD;
    encuestaRespuestaDAO encuestaRespuestaD;
    encuestaCursoSeccionDocenteDAO encuestaCursoSeccionDocenteD;
    alumnoCursoSeccionDAO alumnoCursoSeccionD;
    cursoSeccionDocenteDAO cursoSeccionDocenteD;
    personaDAO personaD;
    cursoDAO cursoD;
    @PostConstruct
    public void init() {   
        mostrarEncuestaPersonal();
       
        cursoSeccionDocenteD=new cursoSeccionDocenteDAO();
        personaD=new personaDAO();
        cursoD=new cursoDAO();
        encuestaCursoSeccionDocenteD=new encuestaCursoSeccionDocenteDAO();
        alumnoCursoPersonal itemAlumnoCursoPersonal;
        for(cursoSeccionDocenteC itemCursoSeccionDocente :cursoSeccionDocenteD.mostrarCursoSeccionAlumno(usuB.getInstitucionS(), usuB.getPeriodoS(), usuB.getUsu())){
            itemAlumnoCursoPersonal=new alumnoCursoPersonal(itemCursoSeccionDocente);
            itemAlumnoCursoPersonal.persona=personaD.CodigoPersonaPersonal(itemCursoSeccionDocente.getPersonal());
            itemAlumnoCursoPersonal.cursos=cursoD.mostrarCurso(itemCursoSeccionDocente.getCurso());
            
            for(encuestaCursoSeccionDocenteC itemEncuestaCursoSeccionDocente: encuestaCursoSeccionDocenteD.mostrarEncuestaCursoSeccionDocente(itemCursoSeccionDocente.getInstitucion(), itemCursoSeccionDocente.getPeriodo(), itemCursoSeccionDocente.getCarrera(), itemCursoSeccionDocente.getMalla(), itemCursoSeccionDocente.getCurso(), itemCursoSeccionDocente.getSeccion(), itemCursoSeccionDocente.getPersonal(),usuB.getUsu())){
                itemAlumnoCursoPersonal.getEncuestaCursoSeccionDocenteL().add(itemEncuestaCursoSeccionDocente);
                
            }
            
            
            alumnoCursoPersonalL.add(itemAlumnoCursoPersonal);
        }
        validarImpresion();
       
    }
    public encuestaDocente() {
    
    }
    public void validarImpresion(){
        banderaImpresion=true;
         for(alumnoCursoPersonal itemAlumnoCursoPersonal :alumnoCursoPersonalL){
             if(itemAlumnoCursoPersonal.encuestaCursoSeccionDocenteL.isEmpty()){
                 banderaImpresion=false;
             } 
         }
    }
    
    
    public void calcular(){
        
        encuestaCursoSeccionDocenteD=new encuestaCursoSeccionDocenteDAO();
        for (encuestaCursoSeccionDocenteC itemEncuestaCursoSeccionDocente : encuestaCursoSeccionDocenteL) {
            System.out.println("pregunta: " + itemEncuestaCursoSeccionDocente.getPregunta() +" respuesta: "+ itemEncuestaCursoSeccionDocente.getRespuesta()); 
             encuestaCursoSeccionDocenteD.ingreso(itemEncuestaCursoSeccionDocente);
        }
         encuestaCursoSeccionDocenteL.clear();
            cursoSeccionDocente=null;
        // refrescar los encuestados
        
        for(alumnoCursoPersonal itemAlumnoCursoPersonal :alumnoCursoPersonalL){
            itemAlumnoCursoPersonal.encuestaCursoSeccionDocenteL.clear();
            for(encuestaCursoSeccionDocenteC itemEncuestaCursoSeccionDocente: encuestaCursoSeccionDocenteD.mostrarEncuestaCursoSeccionDocente(itemAlumnoCursoPersonal.cursoSeccionDocente.getInstitucion(), itemAlumnoCursoPersonal.cursoSeccionDocente.getPeriodo(), itemAlumnoCursoPersonal.cursoSeccionDocente.getCarrera(), itemAlumnoCursoPersonal.cursoSeccionDocente.getMalla(), itemAlumnoCursoPersonal.cursoSeccionDocente.getCurso(), itemAlumnoCursoPersonal.cursoSeccionDocente.getSeccion(), itemAlumnoCursoPersonal.cursoSeccionDocente.getPersonal(),usuB.getUsu()))
            {
                itemAlumnoCursoPersonal.encuestaCursoSeccionDocenteL.add(itemEncuestaCursoSeccionDocente);
            }    
        }
        validarImpresion();
     
        util.alert("Se registro correctamente");
    }
    public void seleccion(cursoSeccionDocenteC itemCursoSeccionDocente){
        
        cursoSeccionDocente=itemCursoSeccionDocente;
        encuestaCursoSeccionDocenteL=new ArrayList<>();
        for(personaEncuestaGrupo itemPersonaEncuestaGrupo: personaEncuestaGrupoL){
            for(personaEncuestaGrupoPreguntaRespuesta itemEncuestaPregunta:itemPersonaEncuestaGrupo.personaEncuestaGrupoPreguntaRespuestaL){
                encuestaCursoSeccionDocenteL.add(new encuestaCursoSeccionDocenteC(cursoSeccionDocente.getInstitucion(), cursoSeccionDocente.getPeriodo(), cursoSeccionDocente.getCarrera(), cursoSeccionDocente.getMalla(), cursoSeccionDocente.getCurso(), cursoSeccionDocente.getSeccion(), cursoSeccionDocente.getGrupo(), cursoSeccionDocente.getPersonal(), usuB.getUsu(), 11, itemPersonaEncuestaGrupo.encuestaGrupo.getGrupo(), itemEncuestaPregunta.encuestaPregunta.getPregunta(), -1, 1));
                
            }
        }
    
    }
    
    
    public void mostrarEncuestaPersonal(){
        encuestaGrupoD=new encuestaGrupoDAO();
        encuestaPreguntaD=new encuestaPreguntaDAO();
        encuestaRespuestaD=new encuestaRespuestaDAO();
        
        personaEncuestaGrupo itemPersonaEncuestaGrupo=null;
        encuestaCursoSeccionDocenteL.clear();
        for (encuestaGrupoC itemEncuestaGrupo:encuestaGrupoD.mostrarGrupo(11)) {
            itemPersonaEncuestaGrupo=new personaEncuestaGrupo(itemEncuestaGrupo);
            for(encuestaPreguntaC itemEncuestaPregunta:encuestaPreguntaD.mostrarPregunta(11,itemEncuestaGrupo.getGrupo())){
                personaEncuestaGrupoPreguntaRespuesta itemPersonaEncuestaGrupoPreguntaRespuesta=new personaEncuestaGrupoPreguntaRespuesta(itemEncuestaPregunta);
                
                for(encuestaRespuestaC itemEncuestaRespuesta:encuestaRespuestaD.mostrarRespuesta(11, itemEncuestaGrupo.getGrupo(), itemEncuestaPregunta.getPregunta())){
                    itemPersonaEncuestaGrupoPreguntaRespuesta.getEncuestaRespuestaL().add(itemEncuestaRespuesta);
                }
                itemPersonaEncuestaGrupo.personaEncuestaGrupoPreguntaRespuestaL.add(itemPersonaEncuestaGrupoPreguntaRespuesta);
            }
            personaEncuestaGrupoL.add(itemPersonaEncuestaGrupo);
        }

    }

    public List<personaEncuestaGrupo> getPersonaEncuestaGrupoL() {
        return personaEncuestaGrupoL;
    }
    public void setPersonaEncuestaGrupoL(List<personaEncuestaGrupo> personaEncuestaGrupoL) {
        this.personaEncuestaGrupoL = personaEncuestaGrupoL;
    }
    public List<alumnoCursoSeccionC> getAlumnoCursoSeccionL() {
        return alumnoCursoSeccionL;
    }
    public void setAlumnoCursoSeccionL(List<alumnoCursoSeccionC> alumnoCursoSeccionL) {
        this.alumnoCursoSeccionL = alumnoCursoSeccionL;
    }
    public alumnoCursoSeccionC getAlumnoCursoSeccion() {
        return alumnoCursoSeccion;
    }
    public void setAlumnoCursoSeccion(alumnoCursoSeccionC alumnoCursoSeccion) {
        this.alumnoCursoSeccion = alumnoCursoSeccion;
    }
    public usuario getUsuB() {
        return usuB;
    }
    public void setUsuB(usuario usuB) {
        this.usuB = usuB;
    }
    public List<cursoSeccionDocenteC> getCursoSeccionDocenteL() {
        return cursoSeccionDocenteL;
    }
    public void setCursoSeccionDocenteL(List<cursoSeccionDocenteC> cursoSeccionDocenteL) {
        this.cursoSeccionDocenteL = cursoSeccionDocenteL;
    }
    public List<alumnoCursoPersonal> getAlumnoCursoPersonalL() {
        return alumnoCursoPersonalL;
    }
    public void setAlumnoCursoPersonalL(List<alumnoCursoPersonal> alumnoCursoPersonalL) {
        this.alumnoCursoPersonalL = alumnoCursoPersonalL;
    }
    public alumnoCursoPersonal getAlumnoCursoPersonal() {
        return alumnoCursoPersonal;
    }
    public void setAlumnoCursoPersonal(alumnoCursoPersonal alumnoCursoPersonal) {
        this.alumnoCursoPersonal = alumnoCursoPersonal;
    }
    public boolean isBanderaImpresion() {
        return banderaImpresion;
    }
    public void setBanderaImpresion(boolean banderaImpresion) {
        this.banderaImpresion = banderaImpresion;
    }
    
    public class alumnoCursoPersonal{
        private cursoSeccionDocenteC cursoSeccionDocente;
        private personaC persona=new personaC();
        private cursosC cursos=new cursosC();
        private List<encuestaCursoSeccionDocenteC> encuestaCursoSeccionDocenteL=new ArrayList<>();

        public alumnoCursoPersonal(cursoSeccionDocenteC cursoSeccionDocente) {
            this.cursoSeccionDocente = cursoSeccionDocente;
        }

        public alumnoCursoPersonal() {
        }

        public cursoSeccionDocenteC getCursoSeccionDocente() {
            return cursoSeccionDocente;
        }

        public void setCursoSeccionDocente(cursoSeccionDocenteC cursoSeccionDocente) {
            this.cursoSeccionDocente = cursoSeccionDocente;
        }

        public personaC getPersona() {
            return persona;
        }

        public void setPersona(personaC persona) {
            this.persona = persona;
        }

        public cursosC getCursos() {
            return cursos;
        }

        public void setCursos(cursosC cursos) {
            this.cursos = cursos;
        }

        public List<encuestaCursoSeccionDocenteC> getEncuestaCursoSeccionDocenteL() {
            return encuestaCursoSeccionDocenteL;
        }

        public void setEncuestaCursoSeccionDocenteL(List<encuestaCursoSeccionDocenteC> encuestaCursoSeccionDocenteL) {
            this.encuestaCursoSeccionDocenteL = encuestaCursoSeccionDocenteL;
        }

     
    }
    
    public class personaEncuestaGrupo{
        private encuestaGrupoC encuestaGrupo;
        private List<personaEncuestaGrupoPreguntaRespuesta> personaEncuestaGrupoPreguntaRespuestaL=new ArrayList<>();

        public personaEncuestaGrupo() {
        }

        public personaEncuestaGrupo(encuestaGrupoC encuestaGrupo) {
            this.encuestaGrupo = encuestaGrupo;
        }
      
        public encuestaGrupoC getEncuestaGrupo() {
            return encuestaGrupo;
        }

        public void setEncuestaGrupo(encuestaGrupoC encuestaGrupo) {
            this.encuestaGrupo = encuestaGrupo;
        }

        public List<personaEncuestaGrupoPreguntaRespuesta> getPersonaEncuestaGrupoPreguntaRespuestaL() {
            return personaEncuestaGrupoPreguntaRespuestaL;
        }

        public void setPersonaEncuestaGrupoPreguntaRespuestaL(List<personaEncuestaGrupoPreguntaRespuesta> personaEncuestaGrupoPreguntaRespuestaL) {
            this.personaEncuestaGrupoPreguntaRespuestaL = personaEncuestaGrupoPreguntaRespuestaL;
        }
    }
    
    
    public class personaEncuestaGrupoPreguntaRespuesta{
        private encuestaPreguntaC encuestaPregunta;
        private List<encuestaRespuestaC> encuestaRespuestaL=new ArrayList<>();

        public personaEncuestaGrupoPreguntaRespuesta() {
        }

        public personaEncuestaGrupoPreguntaRespuesta(encuestaPreguntaC encuestaPregunta) {
            this.encuestaPregunta = encuestaPregunta;
        }
        public encuestaPreguntaC getEncuestaPregunta() {
            return encuestaPregunta;
        }
        public void setEncuestaPregunta(encuestaPreguntaC encuestaPregunta) {
            this.encuestaPregunta = encuestaPregunta;
        }
        public List<encuestaRespuestaC> getEncuestaRespuestaL() {
            return encuestaRespuestaL;
        }
        public void setEncuestaRespuestaL(List<encuestaRespuestaC> encuestaRespuestaL) {
            this.encuestaRespuestaL = encuestaRespuestaL;
        }
    }
    
    
    
    public encuestaInstitucionC mostrarEncuestaInstitucion(int institucion,String periodo){
        encuestaD=new encuestaDAO();
        encuestaInstitucion=encuestaD.mostrarEncuestaInstitucion(institucion, periodo);
        return encuestaInstitucion;
    }
    
    
    public List<encuestaGrupoC> mostrarEncuestaGrupo(int encuesta){
        encuestaD=new encuestaDAO();
        encuestaGrupoL=encuestaD.mostrarGrupo(encuesta);
        return encuestaGrupoL;
    }
    public List<encuestaGrupoPreguntaC> mostrarEncuestaGrupoPregunta(int encuesta,int grupo){
      encuestaD=new encuestaDAO();
      encuestaGrupoPreguntaL=encuestaD.mostrarGrupoPregunta( encuesta,grupo);
      return encuestaGrupoPreguntaL;
    }
      
      
    public List<encuestaRespuestaC> mostrarEncuestaRespuesta(int encuesta,int grupo,int pregunta){
    encuestaD=new encuestaDAO();
    encuestaRespuestaL=encuestaD.mostrarRespuesta(encuesta,grupo,pregunta);
    return encuestaRespuestaL;
    }
    
     public encuestaPreguntaC mostrarPregunta(int pregunta){
    encuestaD=new encuestaDAO();
    encuestaPregunta=encuestaD.mostrarPreguntas(pregunta);
    return encuestaPregunta;
    }
     public int validaEncuesta(int institucion,String periodo,String carrera,String seccion,String curso,String alumno,int encuesta,int opcion){
 
         encuestaD=new encuestaDAO();
         return encuestaD.validaEncuesta(institucion, periodo, carrera, seccion, curso, alumno, encuesta,opcion);
     }
     
     public void guardar(){
         
         for (encuestaCursoSeccionDocenteC item : encuestaCursoSeccionDocenteL) {
             
             encuestaD=new encuestaDAO();
             encuestaD.registroEncuestaDocente(item);
         }
         encuestaCursoSeccionDocenteL.clear();
         respuesta=0;
         bandera=false;
         util.alert("Se registro correctamente");
         util.script(" $('html, body').animate({ scrollTop: 0 }, 600);");
     }
    
      public void agregar(int institucion,String periodo,String carrera,String malla,String curso,String seccion,String grupo,String personal,String alumno,int encuesta,int encuestaGrupo,int pregunta,int respuesta){
            if (!buscarRepuesta(pregunta)) {
                encuestaCursoSeccionDocenteL.add(new encuestaCursoSeccionDocenteC(institucion, periodo, carrera, malla, curso, seccion, grupo, personal, alumno, encuesta, encuestaGrupo, pregunta, respuesta, 1));
            }else{
                encuestaCursoSeccionDocenteL.set(index, new encuestaCursoSeccionDocenteC(institucion, periodo, carrera, malla, curso, seccion, grupo, personal, alumno, encuesta, encuestaGrupo, pregunta, respuesta, 1));
            }
          
      }
      
      
         int index = 0;
      
       public boolean buscarRepuesta(int pregunta) {
        index = 0;
        for (encuestaCursoSeccionDocenteC item : encuestaCursoSeccionDocenteL) {
            if (item.getPregunta() == pregunta) {
                return true;
            }
            index++;
        }
        return false;
    }

  
      
    public List<encuestaGrupoC> getEncuestaGrupoL() {
        return encuestaGrupoL;
    }
    public void setEncuestaGrupoL(List<encuestaGrupoC> encuestaGrupoL) {
        this.encuestaGrupoL = encuestaGrupoL;
    }
    public List<encuestaGrupoPreguntaC> getEncuestaGrupoPreguntaL() {
        return encuestaGrupoPreguntaL;
    }
    public void setEncuestaGrupoPreguntaL(List<encuestaGrupoPreguntaC> encuestaGrupoPreguntaL) {
        this.encuestaGrupoPreguntaL = encuestaGrupoPreguntaL;
    }
    public List<encuestaRespuestaC> getEncuestaRespuestaL() {
        return encuestaRespuestaL;
    }
    public void setEncuestaRespuestaL(List<encuestaRespuestaC> encuestaRespuestaL) {
        this.encuestaRespuestaL = encuestaRespuestaL;
    }
    public encuestaPreguntaC getEncuestaPregunta() {
        return encuestaPregunta;
    }
    public void setEncuestaPregunta(encuestaPreguntaC encuestaPregunta) {
        this.encuestaPregunta = encuestaPregunta;
    }
    public List<encuestaCursoSeccionDocenteC> getEncuestaCursoSeccionDocenteL() {
        return encuestaCursoSeccionDocenteL;
    }
    public void setEncuestaCursoSeccionDocenteL(List<encuestaCursoSeccionDocenteC> encuestaCursoSeccionDocenteL) {
        this.encuestaCursoSeccionDocenteL = encuestaCursoSeccionDocenteL;
    }
    public int getRespuesta() {
        return respuesta;
    }
    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }
    public cursoSeccionDocenteC getCursoSeccionDocente() {
        return cursoSeccionDocente;
    }
    public void setCursoSeccionDocente(cursoSeccionDocenteC cursoSeccionDocente) {
        this.cursoSeccionDocente = cursoSeccionDocente;
    }
    public boolean isBandera() {
        return bandera;
    }
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }
    public encuestaInstitucionC getEncuestaInstitucion() {
        return encuestaInstitucion;
    }
    public void setEncuestaInstitucion(encuestaInstitucionC encuestaInstitucion) {
        this.encuestaInstitucion = encuestaInstitucion;
    }

   
}
