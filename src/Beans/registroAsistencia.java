
package Beans;


import DAO.alumnoCursoAsistenciaDAO;
import DAO.alumnoCursoSeccionDAO;
import DAO.alumnoDAO;
import DAO.carrerasDAO;
import DAO.cursoDAO;
import DAO.cursoPeriodoDAO;
import DAO.cursoSeccionDocenteDAO;
import DAO.personaDAO;
import DAO.personalDAO;
import DAO.registroAsistenciaDAO;
import DAO.registrotdDAO;
import DAO.seccionDAO;
import ENTIDAD.alumnoC;
import ENTIDAD.alumnoCursoAsistenciaC;

import ENTIDAD.carrerasC;
import ENTIDAD.cursoSeccionDocenteC;
import ENTIDAD.cursosC;
import ENTIDAD.personaC;
import ENTIDAD.registrotdC;
import ENTIDAD.seccionC;
import ENTIDAD.seccionPeriodoC;

import java.io.Serializable;
import java.util.ArrayList;


import java.util.Date;
import java.util.HashMap;
import java.util.List;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;




import org.primefaces.event.timeline.TimelineSelectEvent;
import org.primefaces.model.timeline.TimelineEvent;
import org.primefaces.model.timeline.TimelineModel;


@ManagedBean(name = "registroAsistenciaB")
@ViewScoped
public class registroAsistencia implements Serializable {
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<fechaAsistencia> fechaAsistenciaL=new ArrayList<>();
    
    private List<alumnoCursoAsistencia> alumnoCursoAsistenciaL=new ArrayList<>();
    private List<personalCursoSeccion> personalCursoSeccionL=new ArrayList<>();
    private personalCursoSeccion personalCursoSeccionS=null;
    private List<cursoSeccionDocenteC> cursoSeccionDocenteL=new ArrayList<>();
    private cursoSeccionDocenteC cursoSeccionDocente;
    
    
    private String carreraS;
    private String seccionS;
    private String cursoS;
    private String personaS;
    private String personalS;

    
    private Date fechaFinal;//=util.fechaHoy();
    private Date fechaInicial;//=util.dateAdd(fechaFinal, 2, -1);;
    
    private boolean bandera;
    private boolean banderaBotonera;
    
    private List<alumnoAsistencia> alumnoAsistenciaL=new ArrayList<>();
    private String fecha;
    
    //--------------------------------------------------------------------------
    private List<cursoSeccionDocenteP> cursoSeccionDocentePL=new ArrayList<>();
    private cursoSeccionDocenteP cursoSeccionDocenteP;
    private registrotdC registrotd;
    
    private TimelineModel model; 
   
    registroAsistenciaDAO registroAsistenciaD;
    alumnoCursoAsistenciaDAO alumnoCursoAsistenciaD;
    personalDAO personalD;
    cursoPeriodoDAO cursoPeriodoD;
    alumnoCursoSeccionDAO alumnoCursoSeccionD;
    alumnoDAO alumnoD;
    personaDAO personaD;
    cursoSeccionDocenteDAO cursoSeccionDocenteD;
    cursoDAO cursoD;
    carrerasDAO carreraD;
    seccionDAO seccionD;
    registrotdDAO registrotdD;
   
    
    
    
    public registroAsistencia() {
        
      

    }
    
    
    public void mostrarCursoDocente(String periodo,String personal){
        cursoSeccionDocenteD=new cursoSeccionDocenteDAO();
       cursoD=new cursoDAO();
       carreraD=new carrerasDAO();
       seccionD=new seccionDAO();
       
       
       
       cursoSeccionDocenteP itemCursoSeccionDocenteP;
       for (cursoSeccionDocenteC itemCursoSeccionDocente : cursoSeccionDocenteD.mostrarCursoSeccionDocentePrincipal("%", periodo,"%","%","%","%", personal)) {
            itemCursoSeccionDocenteP=new cursoSeccionDocenteP(itemCursoSeccionDocente);
            itemCursoSeccionDocenteP.cursos=cursoD.mostrarCurso(itemCursoSeccionDocente.getCurso());
            itemCursoSeccionDocenteP.carrera=carreraD.mostrarCarrerasCodigo(itemCursoSeccionDocente.getCarrera());
            itemCursoSeccionDocenteP.seccion=seccionD.mostrarSeccion(itemCursoSeccionDocente.getInstitucion(), itemCursoSeccionDocente.getSeccion());
            cursoSeccionDocentePL.add(itemCursoSeccionDocenteP);
        
    }
    }
    
    
    public void seleccionar(int indice){
        alumnoAsistenciaL.get(indice).alumnoCursoAsistencia.setAsistencia(! alumnoAsistenciaL.get(indice).alumnoCursoAsistencia.isAsistencia());
    }
    public void seleccionarCurso(cursoSeccionDocenteC itemCursoSeccionDocente){
        cursoSeccionDocente=itemCursoSeccionDocente;
        banderaBotonera=false;
        alumnoAsistenciaL=new ArrayList<>();
        mostrarAsistenciaAlumno();
    }
    
    //--------------------------------------------------------------------------

    public List<alumnoAsistencia> getAlumnoAsistenciaL() {
        return alumnoAsistenciaL;
    }


    public void setAlumnoAsistenciaL(List<alumnoAsistencia> alumnoAsistenciaL) {
        this.alumnoAsistenciaL = alumnoAsistenciaL;
    }


    public List<cursoSeccionDocenteC> getCursoSeccionDocenteL() {
        return cursoSeccionDocenteL;
    }

  
    public void setCursoSeccionDocenteL(List<cursoSeccionDocenteC> cursoSeccionDocenteL) {
        this.cursoSeccionDocenteL = cursoSeccionDocenteL;
    }

    
    public cursoSeccionDocenteC getCursoSeccionDocente() {
        return cursoSeccionDocente;
    }

   
    public void setCursoSeccionDocente(cursoSeccionDocenteC cursoSeccionDocente) {
        this.cursoSeccionDocente = cursoSeccionDocente;
    }

  
    public String getFecha() {
        return fecha;
    }

   
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    
    public List<cursoSeccionDocenteP> getCursoSeccionDocentePL() {
        return cursoSeccionDocentePL;
    }

    
    public void setCursoSeccionDocentePL(List<cursoSeccionDocenteP> cursoSeccionDocentePL) {
        this.cursoSeccionDocentePL = cursoSeccionDocentePL;
    }

 
    public cursoSeccionDocenteP getCursoSeccionDocenteP() {
        return cursoSeccionDocenteP;
    }

    
    public void setCursoSeccionDocenteP(cursoSeccionDocenteP cursoSeccionDocenteP) {
        this.cursoSeccionDocenteP = cursoSeccionDocenteP;
    }

   
    public registrotdC getRegistrotd() {
        return registrotd;
    }

    public void setRegistrotd(registrotdC registrotd) {
        this.registrotd = registrotd;
    }

    
    public boolean isBanderaBotonera() {
        return banderaBotonera;
    }

   
    public void setBanderaBotonera(boolean banderaBotonera) {
        this.banderaBotonera = banderaBotonera;
    }

    
    public class alumnoAsistencia{
        private alumnoC alumno=new alumnoC();
        private personaC persona=new personaC();
        private alumnoCursoAsistenciaC alumnoCursoAsistencia;

        public alumnoAsistencia() {
        }

        public alumnoAsistencia(alumnoCursoAsistenciaC alumnoCursoAsistencia) {
            this.alumnoCursoAsistencia = alumnoCursoAsistencia;
        }
        

        
        public alumnoC getAlumno() {
            return alumno;
        }

        public void setAlumno(alumnoC alumno) {
            this.alumno = alumno;
        }
        public personaC getPersona() {
            return persona;
        }
        public void setPersona(personaC persona) {
            this.persona = persona;
        }
        public alumnoCursoAsistenciaC getAlumnoCursoAsistencia() {
            return alumnoCursoAsistencia;
        }
        public void setAlumnoCursoAsistencia(alumnoCursoAsistenciaC alumnoCursoAsistencia) {
            this.alumnoCursoAsistencia = alumnoCursoAsistencia;
        }
    }
   
    public void mensaje(String mensaje){
         util.alert(mensaje);
    }
    
     public void onSelect(TimelineSelectEvent e) {  
         
        TimelineEvent timelineEvent = e.getTimelineEvent();  
        String[] fecha= timelineEvent.getData().toString().split("<br/>");
        this.fecha=fecha[1];     
        registrotdD=new registrotdDAO();
        registrotd=registrotdD.mostrarRegistrotd(cursoSeccionDocente.getInstitucion(), cursoSeccionDocente.getPeriodo(), cursoSeccionDocente.getCarrera(), cursoSeccionDocente.getCurso(), cursoSeccionDocente.getSeccion(), timelineEvent.getStartDate());
        banderaBotonera = timelineEvent.getStyleClass().equalsIgnoreCase("activo");
        
        mostrarAlumnoAsistenciaFecha();
       
     }
     
     public void mostrarAlumnoAsistenciaFecha(){
          alumnoCursoAsistenciaD=new alumnoCursoAsistenciaDAO();
        personaD =new  personaDAO();
        alumnoAsistencia itemAlumnoAsistencia;
        alumnoAsistenciaL=new ArrayList<>();
        for(alumnoCursoAsistenciaC itemAlumnoCursoAsistencia :alumnoCursoAsistenciaD.mostrarAlumnoCursoAsistenciaPrincipal(cursoSeccionDocente.getInstitucion(), cursoSeccionDocente.getPeriodo(), cursoSeccionDocente.getCarrera(), cursoSeccionDocente.getMalla(), cursoSeccionDocente.getCurso(), cursoSeccionDocente.getSeccion(), "%", fecha)){
            itemAlumnoAsistencia=new alumnoAsistencia(itemAlumnoCursoAsistencia);
            
            itemAlumnoAsistencia.alumno=new alumnoC(1,itemAlumnoCursoAsistencia.getAlumno(),null,null,1);
            itemAlumnoAsistencia.persona=personaD.busquedaAlumnocodigo(1, itemAlumnoCursoAsistencia.getAlumno());
            alumnoAsistenciaL.add(itemAlumnoAsistencia);
        }
     }
    

    
    
   
    
 
    
    public List<personalCursoSeccion> mostrarCursoSeccionPersonal(int institucion,String periodo,String personal){
          registroAsistenciaD=new registroAsistenciaDAO();
        personalCursoSeccionL=registroAsistenciaD.mostrarPersonalCursoSeccion(institucion, periodo, personal);
        if(personalCursoSeccionS ==null && personalCursoSeccionL.size()>0){
            selecionarCursoSeccion(personalCursoSeccionL.get(0));
            
        }
        return personalCursoSeccionL;
    }

  
    public void selecionarCursoSeccion(personalCursoSeccion item){
        personalCursoSeccionS=item;
        fechaInicial=personalCursoSeccionS.fechaInicio;
        fechaFinal=personalCursoSeccionS.fechaFinal;
        mostrarAsistencia();
    }
    
    public void seleccionPersona(){
    personalD=new personalDAO();
    personalS=personalD.mostrarPersona(personaS).getPersonal();
    }
    
    public void editar(int indice,Date fecha){
        fechaAsistenciaL.get(indice).bandera=false;
        for (alumnoCursoAsistencia itemAlumno : alumnoCursoAsistenciaL) {
            if (!itemAlumno.getAlumnoRegistroAsistenciaL().get(util.convertDate(fecha, "dd-MM-yyyy")).asitencia){
                itemAlumno.getAlumnoRegistroAsistenciaL().get(util.convertDate(fecha, "dd-MM-yyyy")).asitencia=true;
            }
        }
        
        
        
    }
    public void editar(){        
            bandera=true;
           util.script("$('.panel').addClass('bloqueo')");
            
        
    }
    public void seleccionarTodo(){
     for(int i=0;i<=alumnoAsistenciaL.size()-1;i++){
                alumnoAsistenciaL.get(i).alumnoCursoAsistencia.setAsistencia(true);
            }
    }
    
    public void cancelar(int indice,Date fecha){
        //fechaAsistenciaL.get(indice).bandera=true;
        mostrarAsistencia();
                
    }
    public void cancelar(){
        bandera=false;
        mostrarAlumnoAsistenciaFecha();
         util.script("$('.panel').removeClass('bloqueo')");
    }
    public void guardar(){
        registroAsistenciaD=new registroAsistenciaDAO();
            for(alumnoAsistencia itemAlumnoAsistencia:alumnoAsistenciaL){
                registroAsistenciaD.insertar(itemAlumnoAsistencia.alumnoCursoAsistencia);
            }
           
        
        
        bandera=false;
        
        
        util.script("$('.panel').removeClass('bloqueo')");
    }
    
    
    public void guardar(int indice,Date fecha){
        
        
        
        registroAsistenciaD=new registroAsistenciaDAO();
        for (alumnoCursoAsistencia itemAlumno : alumnoCursoAsistenciaL) {
            registroAsistenciaD.insertar(new alumnoCursoAsistenciaC(personalCursoSeccionS.institucion, personalCursoSeccionS.periodo, personalCursoSeccionS.carrera, personalCursoSeccionS.malla,personalCursoSeccionS.curso, personalCursoSeccionS.seccion, itemAlumno.alumno, fecha, itemAlumno.getAlumnoRegistroAsistenciaL().get(util.convertDate(fecha, "dd-MM-yyyy")).asitencia, 1));
        }
        fechaAsistenciaL.get(indice).bandera=true;
    }
    
    
    public void mostrarAsistencia(){
        registroAsistenciaD=new registroAsistenciaDAO();
     
        alumnoCursoAsistenciaL=new ArrayList<>();        
        for (alumnoCursoAsistencia itemalumno : registroAsistenciaD.mostrarAlumnoCursoAsistencia(personalCursoSeccionS.getInstitucion(), personalCursoSeccionS.getPeriodo(), personalCursoSeccionS.getCarrera(), personalCursoSeccionS.getMalla(), personalCursoSeccionS.getCurso(), personalCursoSeccionS.getSeccion(),"%")) {
            for (alumnoRegistroAsistencia itemAsistencia : registroAsistenciaD.mostrarRegistroAsistencia(itemalumno.institucion, itemalumno.periodo, itemalumno.carrera, itemalumno.malla, itemalumno.curso, itemalumno.seccion,itemalumno.alumno)) {
                itemalumno.getAlumnoRegistroAsistenciaL().put(util.convertDate(itemAsistencia.fecha, "dd-MM-yyyy") ,itemAsistencia);
            }
        alumnoCursoAsistenciaL.add(itemalumno);
        }
       
        fechaAsistenciaL=registroAsistenciaD.mostrarFechaAsistencia(personalCursoSeccionS.getInstitucion(), personalCursoSeccionS.getPeriodo(), personalCursoSeccionS.getCarrera(), personalCursoSeccionS.getMalla(), personalCursoSeccionS.getCurso(), personalCursoSeccionS.getSeccion(),fechaInicial,fechaFinal);
        
        
        
       
         
        
        
    }
    public void mostrarAsistenciaAlumno(){
        registroAsistenciaD=new registroAsistenciaDAO();
         fechaAsistenciaL=registroAsistenciaD.mostrarFechaAsistencia(cursoSeccionDocente.getInstitucion(), cursoSeccionDocente.getPeriodo(), cursoSeccionDocente.getCarrera(), cursoSeccionDocente.getMalla(), cursoSeccionDocente.getCurso(), cursoSeccionDocente.getSeccion());       
        model = new TimelineModel(); 
        String clase;
         for (fechaAsistencia itemFechaAsistencia : fechaAsistenciaL) {
             String texto=util.listaDiasSemana.get(itemFechaAsistencia.dia)+"<br/>"+ util.convertDate(itemFechaAsistencia.fecha, "dd-MM-yyyy");
             
             if(itemFechaAsistencia.fecha.after(util.fechaHoy())){
                 clase="desactivo";
             }else{
                 if(itemFechaAsistencia.estadoRegistro==0){
                 clase="bloqueado";
                }else{
                 clase="activo";
                 }
             }
             
             
            model.add(new TimelineEvent(texto, itemFechaAsistencia.fecha,false,null,clase));  
           
         }
    }
    
    
    
    public class cursoSeccionDocenteP{
        private cursoSeccionDocenteC cursoSeccionDocente;
        private cursosC cursos=new cursosC();
        private seccionPeriodoC seccionPeriodo=new  seccionPeriodoC();
        private seccionC seccion=new seccionC();
        private carrerasC carrera=new carrerasC();

        public cursoSeccionDocenteP() {
        }

        public cursoSeccionDocenteP(cursoSeccionDocenteC cursoSeccionDocente) {
            this.cursoSeccionDocente = cursoSeccionDocente;
        }
        
        

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
         * @return the cursos
         */
        public cursosC getCursos() {
            return cursos;
        }

        /**
         * @param cursos the cursos to set
         */
        public void setCursos(cursosC cursos) {
            this.cursos = cursos;
        }

        /**
         * @return the seccionPeriodo
         */
        public seccionPeriodoC getSeccionPeriodo() {
            return seccionPeriodo;
        }

        /**
         * @param seccionPeriodo the seccionPeriodo to set
         */
        public void setSeccionPeriodo(seccionPeriodoC seccionPeriodo) {
            this.seccionPeriodo = seccionPeriodo;
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
 

    /**
     * @return the fechaAsistenciaL
     */
    public List<fechaAsistencia> getFechaAsistenciaL() {
        return fechaAsistenciaL;
    }

    /**
     * @param fechaAsistenciaL the fechaAsistenciaL to set
     */
    public void setFechaAsistenciaL(List<fechaAsistencia> fechaAsistenciaL) {
        this.fechaAsistenciaL = fechaAsistenciaL;
    }

  

    /**
     * @return the alumnoCursoAsistenciaL
     */
    public List<alumnoCursoAsistencia> getAlumnoCursoAsistenciaL() {
        return alumnoCursoAsistenciaL;
    }

    /**
     * @param alumnoCursoAsistenciaL the alumnoCursoAsistenciaL to set
     */
    public void setAlumnoCursoAsistenciaL(List<alumnoCursoAsistencia> alumnoCursoAsistenciaL) {
        this.alumnoCursoAsistenciaL = alumnoCursoAsistenciaL;
    }

    /**
     * @return the carreraS
     */
    public String getCarreraS() {
        return carreraS;
    }

    /**
     * @param carreraS the carreraS to set
     */
    public void setCarreraS(String carreraS) {
        this.carreraS = carreraS;
    }

    /**
     * @return the seccionS
     */
    public String getSeccionS() {
        return seccionS;
    }

    /**
     * @param seccionS the seccionS to set
     */
    public void setSeccionS(String seccionS) {
        this.seccionS = seccionS;
    }

    /**
     * @return the cursoS
     */
    public String getCursoS() {
        return cursoS;
    }

    /**
     * @param cursoS the cursoS to set
     */
    public void setCursoS(String cursoS) {
        this.cursoS = cursoS;
    }

    /**
     * @return the personaS
     */
    public String getPersonaS() {
        return personaS;
    }

    /**
     * @param personaS the personaS to set
     */
    public void setPersonaS(String personaS) {
        this.personaS = personaS;
    }

    /**
     * @return the personalS
     */
    public String getPersonalS() {
        return personalS;
    }

    /**
     * @param personalS the personalS to set
     */
    public void setPersonalS(String personalS) {
        this.personalS = personalS;
    }

 

    /**
     * @return the fechaFinal
     */
    public Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    /**
     * @return the fechaInicial
     */
    public Date getFechaInicial() {
        return fechaInicial;
    }

    /**
     * @param fechaInicial the fechaInicial to set
     */
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * @return the personalCursoSeccionL
     */
    public List<personalCursoSeccion> getPersonalCursoSeccionL() {
        return personalCursoSeccionL;
    }

    /**
     * @param personalCursoSeccionL the personalCursoSeccionL to set
     */
    public void setPersonalCursoSeccionL(List<personalCursoSeccion> personalCursoSeccionL) {
        this.personalCursoSeccionL = personalCursoSeccionL;
    }

    /**
     * @return the personalCursoSeccionS
     */
    public personalCursoSeccion getPersonalCursoSeccionS() {
        return personalCursoSeccionS;
    }

    /**
     * @param personalCursoSeccionS the personalCursoSeccionS to set
     */
    public void setPersonalCursoSeccionS(personalCursoSeccion personalCursoSeccionS) {
        this.personalCursoSeccionS = personalCursoSeccionS;
    }

    /**
     * @return the model
     */
    public TimelineModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(TimelineModel model) {
        this.model = model;
    }

    /**
     * @return the bandera
     */
    public boolean isBandera() {
        return bandera;
    }

    /**
     * @param bandera the bandera to set
     */
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

 

   

  public static class personalCursoSeccion{
        private int institucion;
        private String periodo;
        private String carrera;
        private String desCarrera;
        private String malla;
        private String curso;
        private String desCurso;
        private String seccion;
        private String desSeccion;  
        private int turno;
        private String desturno;
        private String desSede;
        private String personal;
        private String persona;
        private String desPersona;
        private Date fechaInicio;
        private Date fechaFinal;

        /**
         * @return the institucion
         */
        public int getInstitucion() {
            return institucion;
        }

        /**
         * @param institucion the institucion to set
         */
        public void setInstitucion(int institucion) {
            this.institucion = institucion;
        }

        /**
         * @return the periodo
         */
        public String getPeriodo() {
            return periodo;
        }

        /**
         * @param periodo the periodo to set
         */
        public void setPeriodo(String periodo) {
            this.periodo = periodo;
        }

        /**
         * @return the carrera
         */
        public String getCarrera() {
            return carrera;
        }

        /**
         * @param carrera the carrera to set
         */
        public void setCarrera(String carrera) {
            this.carrera = carrera;
        }

        /**
         * @return the desCarrera
         */
        public String getDesCarrera() {
            return desCarrera;
        }

        /**
         * @param desCarrera the desCarrera to set
         */
        public void setDesCarrera(String desCarrera) {
            this.desCarrera = desCarrera;
        }

        /**
         * @return the malla
         */
        public String getMalla() {
            return malla;
        }

        /**
         * @param malla the malla to set
         */
        public void setMalla(String malla) {
            this.malla = malla;
        }

        /**
         * @return the curso
         */
        public String getCurso() {
            return curso;
        }

        /**
         * @param curso the curso to set
         */
        public void setCurso(String curso) {
            this.curso = curso;
        }

        /**
         * @return the desCurso
         */
        public String getDesCurso() {
            return desCurso;
        }

        /**
         * @param desCurso the desCurso to set
         */
        public void setDesCurso(String desCurso) {
            this.desCurso = desCurso;
        }

        /**
         * @return the seccion
         */
        public String getSeccion() {
            return seccion;
        }

        /**
         * @param seccion the seccion to set
         */
        public void setSeccion(String seccion) {
            this.seccion = seccion;
        }

        /**
         * @return the desSeccion
         */
        public String getDesSeccion() {
            return desSeccion;
        }

        /**
         * @param desSeccion the desSeccion to set
         */
        public void setDesSeccion(String desSeccion) {
            this.desSeccion = desSeccion;
        }

        /**
         * @return the turno
         */
        public int getTurno() {
            return turno;
        }

        /**
         * @param turno the turno to set
         */
        public void setTurno(int turno) {
            this.turno = turno;
        }

        /**
         * @return the desturno
         */
        public String getDesturno() {
            return desturno;
        }

        /**
         * @param desturno the desturno to set
         */
        public void setDesturno(String desturno) {
            this.desturno = desturno;
        }

        /**
         * @return the desSede
         */
        public String getDesSede() {
            return desSede;
        }

        /**
         * @param desSede the desSede to set
         */
        public void setDesSede(String desSede) {
            this.desSede = desSede;
        }

        /**
         * @return the personal
         */
        public String getPersonal() {
            return personal;
        }

        /**
         * @param personal the personal to set
         */
        public void setPersonal(String personal) {
            this.personal = personal;
        }

        /**
         * @return the persona
         */
        public String getPersona() {
            return persona;
        }

        /**
         * @param persona the persona to set
         */
        public void setPersona(String persona) {
            this.persona = persona;
        }

        /**
         * @return the desPersona
         */
        public String getDesPersona() {
            return desPersona;
        }

        /**
         * @param desPersona the desPersona to set
         */
        public void setDesPersona(String desPersona) {
            this.desPersona = desPersona;
        }

        /**
         * @return the fechaInicio
         */
        public Date getFechaInicio() {
            return fechaInicio;
        }

        /**
         * @param fechaInicio the fechaInicio to set
         */
        public void setFechaInicio(Date fechaInicio) {
            this.fechaInicio = fechaInicio;
        }

        /**
         * @return the fechaFinal
         */
        public Date getFechaFinal() {
            return fechaFinal;
        }

        /**
         * @param fechaFinal the fechaFinal to set
         */
        public void setFechaFinal(Date fechaFinal) {
            this.fechaFinal = fechaFinal;
        }
  }
    
 public static class alumnoCursoAsistencia{
     private int institucion;
     private String periodo;
     private String carrera;
     private String malla;
     private String curso;
     private String seccion;
     private String alumno;
     private String persona;
     private String desPersona;
     private int estadoRegistro;
     private HashMap<String,alumnoRegistroAsistencia> alumnoRegistroAsistenciaL=new HashMap<>();

        /**
         * @return the alumno
         */
        public String getAlumno() {
            return alumno;
        }

        /**
         * @param alumno the alumno to set
         */
        public void setAlumno(String alumno) {
            this.alumno = alumno;
        }

        /**
         * @return the persona
         */
        public String getPersona() {
            return persona;
        }

        /**
         * @param persona the persona to set
         */
        public void setPersona(String persona) {
            this.persona = persona;
        }

        /**
         * @return the desPersona
         */
        public String getDesPersona() {
            return desPersona;
        }

        /**
         * @param desPersona the desPersona to set
         */
        public void setDesPersona(String desPersona) {
            this.desPersona = desPersona;
        }

       

      

        /**
         * @return the estadoRegistro
         */
        public int getEstadoRegistro() {
            return estadoRegistro;
        }

        /**
         * @param estadoRegistro the estadoRegistro to set
         */
        public void setEstadoRegistro(int estadoRegistro) {
            this.estadoRegistro = estadoRegistro;
        }

        /**
         * @return the alumnoRegistroAsistenciaL
         */
        public HashMap<String,alumnoRegistroAsistencia> getAlumnoRegistroAsistenciaL() {
            return alumnoRegistroAsistenciaL;
        }

        /**
         * @param alumnoRegistroAsistenciaL the alumnoRegistroAsistenciaL to set
         */
        public void setAlumnoRegistroAsistenciaL(HashMap<String,alumnoRegistroAsistencia> alumnoRegistroAsistenciaL) {
            this.alumnoRegistroAsistenciaL = alumnoRegistroAsistenciaL;
        }

        /**
         * @return the institucion
         */
        public int getInstitucion() {
            return institucion;
        }

        /**
         * @param institucion the institucion to set
         */
        public void setInstitucion(int institucion) {
            this.institucion = institucion;
        }

        /**
         * @return the periodo
         */
        public String getPeriodo() {
            return periodo;
        }

        /**
         * @param periodo the periodo to set
         */
        public void setPeriodo(String periodo) {
            this.periodo = periodo;
        }

        /**
         * @return the carrera
         */
        public String getCarrera() {
            return carrera;
        }

        /**
         * @param carrera the carrera to set
         */
        public void setCarrera(String carrera) {
            this.carrera = carrera;
        }

        /**
         * @return the malla
         */
        public String getMalla() {
            return malla;
        }

        /**
         * @param malla the malla to set
         */
        public void setMalla(String malla) {
            this.malla = malla;
        }

        /**
         * @return the curso
         */
        public String getCurso() {
            return curso;
        }

        /**
         * @param curso the curso to set
         */
        public void setCurso(String curso) {
            this.curso = curso;
        }

        /**
         * @return the seccion
         */
        public String getSeccion() {
            return seccion;
        }

        /**
         * @param seccion the seccion to set
         */
        public void setSeccion(String seccion) {
            this.seccion = seccion;
        }
 }
 
    
    
 public static class alumnoRegistroAsistencia{
     private String alumno;
     private Date fecha;
     private boolean asitencia;

        /**
         * @return the alumno
         */
        public String getAlumno() {
            return alumno;
        }

        /**
         * @param alumno the alumno to set
         */
        public void setAlumno(String alumno) {
            this.alumno = alumno;
        }

        /**
         * @return the fecha
         */
        public Date getFecha() {
            return fecha;
        }

        /**
         * @param fecha the fecha to set
         */
        public void setFecha(Date fecha) {
            this.fecha = fecha;
        }

        /**
         * @return the asitencia
         */
        public boolean isAsitencia() {
            return asitencia;
        }

        /**
         * @param asitencia the asitencia to set
         */
        public void setAsitencia(boolean asitencia) {
            this.asitencia = asitencia;
        }
 }
    

 public static class  fechaAsistencia{
     
     private Date fecha;
     private int dia;
     private String desDia;
     private String tema;
     private boolean feriado;
     private String desFeriado;
     private int tipoClase;
     private boolean bandera=true;
     private int estadoRegistro;
        /**
         * @return the fecha
         */
        public Date getFecha() {
            return fecha;
        }

        /**
         * @param fecha the fecha to set
         */
        public void setFecha(Date fecha) {
            this.fecha = fecha;
        }

        /**
         * @return the dia
         */
        public int getDia() {
            return dia;
        }

        /**
         * @param dia the dia to set
         */
        public void setDia(int dia) {
            this.dia = dia;
        }

        /**
         * @return the desDia
         */
        public String getDesDia() {
            return desDia;
        }

        /**
         * @param desDia the desDia to set
         */
        public void setDesDia(String desDia) {
            this.desDia = desDia;
        }

        /**
         * @return the tema
         */
        public String getTema() {
            return tema;
        }

        /**
         * @param tema the tema to set
         */
        public void setTema(String tema) {
            this.tema = tema;
        }

        /**
         * @return the feriado
         */
        public boolean isFeriado() {
            return feriado;
        }

        /**
         * @param feriado the feriado to set
         */
        public void setFeriado(boolean feriado) {
            this.feriado = feriado;
        }

        /**
         * @return the desFeriado
         */
        public String getDesFeriado() {
            return desFeriado;
        }

        /**
         * @param desFeriado the desFeriado to set
         */
        public void setDesFeriado(String desFeriado) {
            this.desFeriado = desFeriado;
        }

        /**
         * @return the tipoClase
         */
        public int getTipoClase() {
            return tipoClase;
        }

        /**
         * @param tipoClase the tipoClase to set
         */
        public void setTipoClase(int tipoClase) {
            this.tipoClase = tipoClase;
        }

        /**
         * @return the bandera
         */
        public boolean isBandera() {
            return bandera;
        }

        /**
         * @param bandera the bandera to set
         */
        public void setBandera(boolean bandera) {
            this.bandera = bandera;
        }

        /**
         * @return the estadoRegistro
         */
        public int getEstadoRegistro() {
            return estadoRegistro;
        }

        /**
         * @param estadoRegistro the estadoRegistro to set
         */
        public void setEstadoRegistro(int estadoRegistro) {
            this.estadoRegistro = estadoRegistro;
        }
 } 
}
