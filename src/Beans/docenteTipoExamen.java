
package Beans;

import DAO.alumnoCursoSeccionGrupoExamenDAO;
import DAO.cursoPeriodoDAO;
import DAO.cursoPeriodoGrupoExamenDAO;
import DAO.cursoSeccionDAO;
import DAO.cursoSeccionDocenteDAO;
import DAO.cursoSeccionDocenteTipoExamenDAO;
import DAO.docenteTipoExamenDAO;
import DAO.personalDAO;
import DAO.seccionPeriodoDAO;
import ENTIDAD.alumnoCursoSeccionGrupoExamenC;

import ENTIDAD.cursoPeriodoGrupoExamenC;

import ENTIDAD.cursoSeccionDocenteC;
import ENTIDAD.cursoSeccionDocenteTipoExamenC;
import ENTIDAD.personaC;
import ENTIDAD.seccionPeriodoC;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



@ManagedBean(name = "docenteTipoExamenB")
@ViewScoped
public class docenteTipoExamen {

    private cursoSeccionDocenteC cursoSeccionDocente = new cursoSeccionDocenteC();
    private cursoSeccionDocenteTipoExamenC cursoSeccionDocenteTipoExamen = new cursoSeccionDocenteTipoExamenC();
    
    
  
    private List<detalleAlumnoGrupo> alumnoGrupoLibresL=  new ArrayList<>();
    private List<detalleAlumnoGrupo> alumnoGrupoL=  new ArrayList<>();
    private detalleAlumnoGrupo alumnoGrupoLibresS=new detalleAlumnoGrupo();
    private detalleAlumnoGrupo alumnoGrupoS=new detalleAlumnoGrupo();
    
    private List<grupoTipoExamenDocente> grupoTipoExamenDocenteL;
    private grupoTipoExamenDocente grupoTipoExamenDocenteS;
    
    
    private alumnoCursoSeccionGrupoExamenC alumnoCursoSeccionGrupoExamen;
 
    private personaC persona;
    private seccionPeriodoC seccionPeriodo=new seccionPeriodoC();
    
    private String busAlumno;
    
    private List<cursoPeriodoGrupoExamenC> cursoPeriodoGrupoExamenL=new ArrayList<>();
    private cursoPeriodoGrupoExamenC cursoPeriodoGrupoExamenS=new cursoPeriodoGrupoExamenC();
    private cursoPeriodoGrupoExamenC cursoPeriodoGrupoExamen=new cursoPeriodoGrupoExamenC();
    
    
    private List<detallegrupoDocente> detallegrupoDocenteL;
    private detallegrupoDocente detallegrupoDocente=new detallegrupoDocente();
    
    private List<cursoSeccionDocenteC> cursoSeccionDocenteL;
    
    
  
    
    
    private boolean banderaGrupo;
   
    
    
    private int cantGrupo=0;
    private int totalVacantes=0 ;
    private int vacantesDisponibles=0;
    private int totalVacantesGrupo=0;

   
    cursoPeriodoGrupoExamenDAO cursoPeriodoGrupoExamenD;
    seccionPeriodoDAO seccionPeriodoD;
    cursoSeccionDocenteDAO cursoSeccionDocenteD;
    cursoSeccionDocenteTipoExamenDAO cursoSeccionDocenteTipoExamenD;
    docenteTipoExamenDAO docenteTipoExamenD;
    alumnoCursoSeccionGrupoExamenDAO alumnoCursoSeccionGrupoExamenD;
    cursoSeccionDAO cursoSeccionD;
    personalDAO personalD;
    cursoPeriodoDAO cursoPeriodoD;
    
    public void mostrarGrupos(int institucion,String periodo,String carrera,String malla,String curso){
        cursoPeriodoGrupoExamenD=new cursoPeriodoGrupoExamenDAO();
        cursoPeriodoGrupoExamenL=cursoPeriodoGrupoExamenD.mostrarListaCursoPeriodoGrupoExamen(institucion, periodo, carrera, malla, curso);
    }
    public void mostrarCursoGrupo(cursoPeriodoGrupoExamenC item){
        cursoPeriodoGrupoExamenS=item;
        cursoSeccionDocenteTipoExamenD=new cursoSeccionDocenteTipoExamenDAO();
        grupoTipoExamenDocenteL=cursoSeccionDocenteTipoExamenD.mostrarCursoDocenteTipoExamen(item.getInstitucion(), item.getPeriodo(), item.getCarrera(), item.getMalla(), item.getCurso(), item.getGrupoExamen());
       
    }
   

    /**
     * @return the detallegrupoDocente
     */
    public detallegrupoDocente getDetallegrupoDocente() {
        return detallegrupoDocente;
    }

    /**
     * @param detallegrupoDocente the detallegrupoDocente to set
     */
    public void setDetallegrupoDocente(detallegrupoDocente detallegrupoDocente) {
        this.detallegrupoDocente = detallegrupoDocente;
    }

    /**
     * @return the totalVacantes
     */
    public int getTotalVacantes() {
        return totalVacantes;
    }

    /**
     * @param totalVacantes the totalVacantes to set
     */
    public void setTotalVacantes(int totalVacantes) {
        this.totalVacantes = totalVacantes;
    }

 
    /**
     * @return the cursoSeccionDocenteL
     */
    public List<cursoSeccionDocenteC> getCursoSeccionDocenteL() {
        return cursoSeccionDocenteL;
    }

    /**
     * @param cursoSeccionDocenteL the cursoSeccionDocenteL to set
     */
    public void setCursoSeccionDocenteL(List<cursoSeccionDocenteC> cursoSeccionDocenteL) {
        this.cursoSeccionDocenteL = cursoSeccionDocenteL;
    }

    /**
     * @return the vacantesDisponibles
     */
    public int getVacantesDisponibles() {
        return vacantesDisponibles;
    }

    /**
     * @param vacantesDisponibles the vacantesDisponibles to set
     */
    public void setVacantesDisponibles(int vacantesDisponibles) {
        this.vacantesDisponibles = vacantesDisponibles;
    }

    /**
     * @return the totalVacantesGrupo
     */
    public int getTotalVacantesGrupo() {
        return totalVacantesGrupo;
    }

    /**
     * @param totalVacantesGrupo the totalVacantesGrupo to set
     */
    public void setTotalVacantesGrupo(int totalVacantesGrupo) {
        this.totalVacantesGrupo = totalVacantesGrupo;
    }

    /**
     * @return the banderaGrupo
     */
    public boolean isBanderaGrupo() {
        return banderaGrupo;
    }

    /**
     * @param banderaGrupo the banderaGrupo to set
     */
    public void setBanderaGrupo(boolean banderaGrupo) {
        this.banderaGrupo = banderaGrupo;
    }

    /**
     * @return the alumnoGrupoLibresL
     */
    public List<detalleAlumnoGrupo> getAlumnoGrupoLibresL() {
        return alumnoGrupoLibresL;
    }

    /**
     * @param alumnoGrupoLibresL the alumnoGrupoLibresL to set
     */
    public void setAlumnoGrupoLibresL(List<detalleAlumnoGrupo> alumnoGrupoLibresL) {
        this.alumnoGrupoLibresL = alumnoGrupoLibresL;
    }

    /**
     * @return the alumnoGrupoL
     */
    public List<detalleAlumnoGrupo> getAlumnoGrupoL() {
        return alumnoGrupoL;
    }

    /**
     * @param alumnoGrupoL the alumnoGrupoL to set
     */
    public void setAlumnoGrupoL(List<detalleAlumnoGrupo> alumnoGrupoL) {
        this.alumnoGrupoL = alumnoGrupoL;
    }

   

    /**
     * @return the alumnoGrupoS
     */
  

    /**
     * @return the busAlumno
     */
    public String getBusAlumno() {
        return busAlumno;
    }

    /**
     * @param busAlumno the busAlumno to set
     */
    public void setBusAlumno(String busAlumno) {
        this.busAlumno = busAlumno;
    }

    /**
     * @return the alumnoGrupoLibresS
     */
    public detalleAlumnoGrupo getAlumnoGrupoLibresS() {
        return alumnoGrupoLibresS;
    }

    /**
     * @param alumnoGrupoLibresS the alumnoGrupoLibresS to set
     */
    public void setAlumnoGrupoLibresS(detalleAlumnoGrupo alumnoGrupoLibresS) {
        this.alumnoGrupoLibresS = alumnoGrupoLibresS;
    }

    /**
     * @return the alumnoGrupoS
     */
    public detalleAlumnoGrupo getAlumnoGrupoS() {
        return alumnoGrupoS;
    }

    /**
     * @param alumnoGrupoS the alumnoGrupoS to set
     */
    public void setAlumnoGrupoS(detalleAlumnoGrupo alumnoGrupoS) {
        this.alumnoGrupoS = alumnoGrupoS;
    }

    /**
     * @return the cursoPeriodoGrupoExamenS
     */
    public cursoPeriodoGrupoExamenC getCursoPeriodoGrupoExamenS() {
        return cursoPeriodoGrupoExamenS;
    }

    /**
     * @param cursoPeriodoGrupoExamenS the cursoPeriodoGrupoExamenS to set
     */
    public void setCursoPeriodoGrupoExamenS(cursoPeriodoGrupoExamenC cursoPeriodoGrupoExamenS) {
        this.cursoPeriodoGrupoExamenS = cursoPeriodoGrupoExamenS;
    }

    /**
     * @return the grupoTipoExamenDocenteL
     */
    public List<grupoTipoExamenDocente> getGrupoTipoExamenDocenteL() {
        return grupoTipoExamenDocenteL;
    }

    /**
     * @param grupoTipoExamenDocenteL the grupoTipoExamenDocenteL to set
     */
    public void setGrupoTipoExamenDocenteL(List<grupoTipoExamenDocente> grupoTipoExamenDocenteL) {
        this.grupoTipoExamenDocenteL = grupoTipoExamenDocenteL;
    }

    /**
     * @return the grupoTipoExamenDocenteS
     */
    public grupoTipoExamenDocente getGrupoTipoExamenDocenteS() {
        return grupoTipoExamenDocenteS;
    }

    /**
     * @param grupoTipoExamenDocenteS the grupoTipoExamenDocenteS to set
     */
    public void setGrupoTipoExamenDocenteS(grupoTipoExamenDocente grupoTipoExamenDocenteS) {
        this.grupoTipoExamenDocenteS = grupoTipoExamenDocenteS;
    }



 
  

  public static class grupoTipoExamenDocente{
       private int institucion;
        private String periodo;
        private String carrera;
        private String malla;
        private String curso;
        private Date fechaInicio;
        private Date fechaFin;
        private String centro;
        private String personal;
        private String persona;
        private String desPersona;
        
        private int grupoExamen;
        private String tipoExamen;
        private String desTipoExamen;

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
         * @return the fechaFin
         */
        public Date getFechaFin() {
            return fechaFin;
        }

        /**
         * @param fechaFin the fechaFin to set
         */
        public void setFechaFin(Date fechaFin) {
            this.fechaFin = fechaFin;
        }

        /**
         * @return the centro
         */
        public String getCentro() {
            return centro;
        }

        /**
         * @param centro the centro to set
         */
        public void setCentro(String centro) {
            this.centro = centro;
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
         * @return the grupoExamen
         */
        public int getGrupoExamen() {
            return grupoExamen;
        }

        /**
         * @param grupoExamen the grupoExamen to set
         */
        public void setGrupoExamen(int grupoExamen) {
            this.grupoExamen = grupoExamen;
        }

        /**
         * @return the tipoExamen
         */
        public String getTipoExamen() {
            return tipoExamen;
        }

        /**
         * @param tipoExamen the tipoExamen to set
         */
        public void setTipoExamen(String tipoExamen) {
            this.tipoExamen = tipoExamen;
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
         * @return the desTipoExamen
         */
        public String getDesTipoExamen() {
            return desTipoExamen;
        }

        /**
         * @param desTipoExamen the desTipoExamen to set
         */
        public void setDesTipoExamen(String desTipoExamen) {
            this.desTipoExamen = desTipoExamen;
        }
  }
    
    
    public static class detallegrupoDocente{
        private int institucion;
        private String periodo;
        private String carrera;
        private String malla;
        private String curso;
        private int maximoVacantes;
        private int actualVacantes;
        private int grupoExamen;
        private String tipoExamen;
        private String personal;
        private Date fechaInicio;
        private Date fechaFin;
        private String centro;

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
         * @return the grupoExamen
         */
        public int getGrupoExamen() {
            return grupoExamen;
        }

        /**
         * @param grupoExamen the grupoExamen to set
         */
        public void setGrupoExamen(int grupoExamen) {
            this.grupoExamen = grupoExamen;
        }

        /**
         * @return the tipoExamen
         */
        public String getTipoExamen() {
            return tipoExamen;
        }

        /**
         * @param tipoExamen the tipoExamen to set
         */
        public void setTipoExamen(String tipoExamen) {
            this.tipoExamen = tipoExamen;
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
         * @return the centro
         */
        public String getCentro() {
            return centro;
        }

        /**
         * @param centro the centro to set
         */
        public void setCentro(String centro) {
            this.centro = centro;
        }

        /**
         * @return the maximoVacantes
         */
        public int getMaximoVacantes() {
            return maximoVacantes;
        }

        /**
         * @param maximoVacantes the maximoVacantes to set
         */
        public void setMaximoVacantes(int maximoVacantes) {
            this.maximoVacantes = maximoVacantes;
        }

        /**
         * @return the actualVacantes
         */
        public int getActualVacantes() {
            return actualVacantes;
        }

        /**
         * @param actualVacantes the actualVacantes to set
         */
        public void setActualVacantes(int actualVacantes) {
            this.actualVacantes = actualVacantes;
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
         * @return the fechaFin
         */
        public Date getFechaFin() {
            return fechaFin;
        }

        /**
         * @param fechaFin the fechaFin to set
         */
        public void setFechaFin(Date fechaFin) {
            this.fechaFin = fechaFin;
        }
    }
    
    public void mostrarTutorCurso(int institucion, String periodo, String malla, String carrera, String curso, String seccion) {
              
        
        cursoSeccionDocenteD = new cursoSeccionDocenteDAO();
        cursoSeccionDocente = cursoSeccionDocenteD.mostrarPeriodoCarreraCursoSeccion(institucion, periodo, malla, carrera, curso, seccion);
        seccionPeriodoD=new seccionPeriodoDAO();
        seccionPeriodo=seccionPeriodoD.mostrarSeccionPeriodo(institucion, periodo, carrera, seccion);
    }
    
   

    public void insertarDocenteGrupo() {

        cursoSeccionDocenteTipoExamenD = new cursoSeccionDocenteTipoExamenDAO();
        cursoSeccionDocenteTipoExamenD.insertar(cursoSeccionDocenteTipoExamen);
        
        cursoSeccionDocenteTipoExamenD=new cursoSeccionDocenteTipoExamenDAO();
        grupoTipoExamenDocenteL=cursoSeccionDocenteTipoExamenD.mostrarCursoDocenteTipoExamen(cursoPeriodoGrupoExamenS.getInstitucion(), cursoPeriodoGrupoExamenS.getPeriodo(), cursoPeriodoGrupoExamenS.getCarrera(), cursoPeriodoGrupoExamenS.getMalla(), cursoPeriodoGrupoExamenS.getCurso(), cursoPeriodoGrupoExamenS.getGrupoExamen());
        util.script("dlgGrupoDocente.hide()");
    }
      public void insertarGrupo(String tipoExamen) {
          
      
                cursoPeriodoGrupoExamenD=new cursoPeriodoGrupoExamenDAO();         
                cursoPeriodoGrupoExamenD.insertar(cursoPeriodoGrupoExamen);
                mostrarGrupos(cursoPeriodoGrupoExamen.getInstitucion(),cursoPeriodoGrupoExamen.getPeriodo(),cursoPeriodoGrupoExamen.getCarrera(),cursoPeriodoGrupoExamen.getMalla(),cursoPeriodoGrupoExamen.getCurso());
                util.script("dlgGrupo.hide()"); 
        
    }

    public void nuevoGrupo(int institucion, String periodo, String carrera, String malla, String curso ) {
        
        cursoPeriodoGrupoExamen = new cursoPeriodoGrupoExamenC(institucion, periodo, carrera, malla, curso,0,0,0,1);
        
        
        
        mostrarTotalVacantes(institucion, periodo, carrera, curso, "%");
    }
    public void mostrarTotalVacantes(int institucion, String periodo, String carrera, String curso ,String seccion){
        cursoSeccionD=new cursoSeccionDAO();
        totalVacantes=cursoSeccionD.mostrarTotalMatriculados(institucion, periodo, carrera, curso, seccion);
        cursoPeriodoGrupoExamenD=new cursoPeriodoGrupoExamenDAO();
        totalVacantesGrupo=cursoPeriodoGrupoExamenD.mostrarTotalVacantes(institucion, periodo, carrera, curso, "%");
        cantGrupo=0;
        vacantesDisponibles=totalVacantes-totalVacantesGrupo;
    }
    
     public void editarGrupo(cursoPeriodoGrupoExamenC item){
       cursoPeriodoGrupoExamenS=item;
       cursoPeriodoGrupoExamenD=new cursoPeriodoGrupoExamenDAO();
       cursoPeriodoGrupoExamen=cursoPeriodoGrupoExamenD.mostrarCursoPeriodoGrupoExamen(cursoPeriodoGrupoExamenS.getInstitucion(), cursoPeriodoGrupoExamenS.getPeriodo(), cursoPeriodoGrupoExamenS.getCarrera(), cursoPeriodoGrupoExamenS.getMalla(), cursoPeriodoGrupoExamenS.getCurso(), cursoPeriodoGrupoExamenS.getGrupoExamen());       
       banderaGrupo=true;
    }
    public void nuevoDocenteGrupo(detallegrupoDocente item,String tipoExamen){
        cursoSeccionDocenteTipoExamen=new cursoSeccionDocenteTipoExamenC(item.institucion, item.periodo, item.carrera, item.malla, item.curso, "", 0, item.personal, tipoExamen, item.grupoExamen, item.getFechaInicio(), item.getFechaFin(), item.centro, 1);
        
    }
    
    public void nuevoDocenteTipoCurso(grupoTipoExamenDocente item){
        grupoTipoExamenDocenteS=item;
        cursoSeccionDocenteTipoExamen=new cursoSeccionDocenteTipoExamenC(item.institucion, item.periodo, item.carrera, item.malla, item.curso, "", 0, item.personal, item.tipoExamen, item.grupoExamen, item.getFechaInicio(), item.getFechaFin(), item.centro, 1);
        util.script("dlgGrupoDocente.show()");
    }
    
    public void eliminarDocenteGrupo(detallegrupoDocente item,String tipoExamen){
        cursoSeccionDocenteTipoExamen=new cursoSeccionDocenteTipoExamenC(item.institucion, item.periodo, item.carrera, item.malla, item.curso, "", 0, item.personal, tipoExamen, item.grupoExamen, item.getFechaInicio(), item.getFechaFin(), item.centro, 0);
        cursoSeccionDocenteTipoExamenD=new cursoSeccionDocenteTipoExamenDAO();        
        cursoSeccionDocenteTipoExamenD.insertar(cursoSeccionDocenteTipoExamen);
        mostrarGrupos(cursoPeriodoGrupoExamen.getInstitucion(),cursoPeriodoGrupoExamen.getPeriodo(),cursoPeriodoGrupoExamen.getCarrera(),cursoPeriodoGrupoExamen.getMalla(),cursoPeriodoGrupoExamen.getCurso());
    }
    
 
    public void mostrarCursoPeriodo(int institucion, String periodo, String carrera,  String curso){
        
        mostrarGrupos(institucion, periodo, carrera, "200702", curso);
        mostrarTotalVacantes(institucion, periodo, carrera, curso, "%");
        grupoTipoExamenDocenteL=new ArrayList<>();
    }

  
    
  

   
    
    
 
    public void mostrarAlumnos(cursoPeriodoGrupoExamenC item) {
        
        
        cursoPeriodoGrupoExamenS=item;      
        docenteTipoExamenD=new docenteTipoExamenDAO();
        alumnoGrupoLibresL= docenteTipoExamenD.mostrareAlumnoGrupoLibre(cursoPeriodoGrupoExamenS.getInstitucion(), cursoPeriodoGrupoExamenS.getPeriodo(), cursoPeriodoGrupoExamenS.getCarrera(), cursoPeriodoGrupoExamenS.getCurso(),"%");
        alumnoGrupoL= docenteTipoExamenD.mostrareAlumnoGrupo(cursoPeriodoGrupoExamenS.getInstitucion(), cursoPeriodoGrupoExamenS.getPeriodo(), cursoPeriodoGrupoExamenS.getCarrera(), cursoPeriodoGrupoExamenS.getCurso(),cursoPeriodoGrupoExamenS.getGrupoExamen());
        alumnoGrupoS=null;
        alumnoGrupoLibresS=null;
    }
    
    
    
     public void filtroAlumnoLibres(){
        docenteTipoExamenD=new docenteTipoExamenDAO();
        alumnoGrupoLibresL= docenteTipoExamenD.mostrareAlumnoGrupoLibre(cursoPeriodoGrupoExamenS.getInstitucion(), cursoPeriodoGrupoExamenS.getPeriodo(), cursoPeriodoGrupoExamenS.getCarrera(), cursoPeriodoGrupoExamenS.getCurso(),"%" + busAlumno.concat("%"));
    }
    
        public void pasar(){
            
            
            if (alumnoGrupoLibresS !=null)
            {
                alumnoCursoSeccionGrupoExamenD = new alumnoCursoSeccionGrupoExamenDAO();
            alumnoCursoSeccionGrupoExamenD.insertar(new alumnoCursoSeccionGrupoExamenC(cursoPeriodoGrupoExamenS.getInstitucion(), cursoPeriodoGrupoExamenS.getPeriodo(), cursoPeriodoGrupoExamenS.getCarrera(), cursoPeriodoGrupoExamenS.getMalla(), cursoPeriodoGrupoExamenS.getCurso(), alumnoGrupoLibresS.seccion , alumnoGrupoLibresS.alumno , 0,"",  cursoPeriodoGrupoExamenS.getGrupoExamen(), 1));                
            
             docenteTipoExamenD=new docenteTipoExamenDAO();
             alumnoGrupoLibresL= docenteTipoExamenD.mostrareAlumnoGrupoLibre(cursoPeriodoGrupoExamenS.getInstitucion(), cursoPeriodoGrupoExamenS.getPeriodo(), cursoPeriodoGrupoExamenS.getCarrera(), cursoPeriodoGrupoExamenS.getCurso(),"%");
             alumnoGrupoL= docenteTipoExamenD.mostrareAlumnoGrupo(cursoPeriodoGrupoExamenS.getInstitucion(), cursoPeriodoGrupoExamenS.getPeriodo(), cursoPeriodoGrupoExamenS.getCarrera(), cursoPeriodoGrupoExamenS.getCurso(),cursoPeriodoGrupoExamenS.getGrupoExamen());
             alumnoGrupoS=alumnoGrupoLibresS;
             
             cursoPeriodoGrupoExamenD=new cursoPeriodoGrupoExamenDAO();
             cursoPeriodoGrupoExamenS=cursoPeriodoGrupoExamenD.mostrarCursoPeriodoGrupoExamen(cursoPeriodoGrupoExamenS.getInstitucion(),cursoPeriodoGrupoExamenS.getPeriodo(),cursoPeriodoGrupoExamenS.getCarrera(),cursoPeriodoGrupoExamenS.getMalla(),cursoPeriodoGrupoExamenS.getCurso(),cursoPeriodoGrupoExamenS.getGrupoExamen());
             alumnoGrupoLibresS=null;
             busAlumno="";
             util.script("document.getElementById('frmGrupoAlumnos\\:txtBusqueda').focus()");
            }else{
                util.alert("Seleccione una alumno libre");
            }
                
            
             
        }
        
         public void regresar(){
              if (alumnoGrupoS !=null)
            {
            alumnoCursoSeccionGrupoExamenD = new alumnoCursoSeccionGrupoExamenDAO();
            alumnoCursoSeccionGrupoExamenD.eliminar(new alumnoCursoSeccionGrupoExamenC(cursoPeriodoGrupoExamenS.getInstitucion(), cursoPeriodoGrupoExamenS.getPeriodo(), cursoPeriodoGrupoExamenS.getCarrera(), cursoPeriodoGrupoExamenS.getMalla(), cursoPeriodoGrupoExamenS.getCurso(), alumnoGrupoS.seccion, alumnoGrupoS.alumno , 0,"",  cursoPeriodoGrupoExamenS.getGrupoExamen(), 1));                
            
            
            docenteTipoExamenD=new docenteTipoExamenDAO();
            
                    alumnoGrupoLibresL= docenteTipoExamenD.mostrareAlumnoGrupoLibre(cursoPeriodoGrupoExamenS.getInstitucion(), cursoPeriodoGrupoExamenS.getPeriodo(), cursoPeriodoGrupoExamenS.getCarrera(), cursoPeriodoGrupoExamenS.getCurso(),"%");
        alumnoGrupoL= docenteTipoExamenD.mostrareAlumnoGrupo(cursoPeriodoGrupoExamenS.getInstitucion(), cursoPeriodoGrupoExamenS.getPeriodo(), cursoPeriodoGrupoExamenS.getCarrera(), cursoPeriodoGrupoExamenS.getCurso(),cursoPeriodoGrupoExamenS.getGrupoExamen());
             
             alumnoGrupoLibresS=alumnoGrupoS;
             
             cursoPeriodoGrupoExamenD=new cursoPeriodoGrupoExamenDAO();
             cursoPeriodoGrupoExamenS=cursoPeriodoGrupoExamenD.mostrarCursoPeriodoGrupoExamen(cursoPeriodoGrupoExamenS.getInstitucion(),cursoPeriodoGrupoExamenS.getPeriodo(),cursoPeriodoGrupoExamenS.getCarrera(),cursoPeriodoGrupoExamenS.getMalla(),cursoPeriodoGrupoExamenS.getCurso(),cursoPeriodoGrupoExamenS.getGrupoExamen());
             alumnoGrupoS=null;
         }else{
                util.alert("Seleccione una alumno del grupo" + cursoPeriodoGrupoExamenS.getGrupoExamen() );
            }
        }

  

    public void busquedaPersonal() {
        personalD = new personalDAO();        
        cursoSeccionDocenteTipoExamen.setPersonal(personalD.mostrarPersona(persona.getPersona()).getPersonal()); 
        //util.consolaCliente( cursoSeccionDocenteTipoExamen.getPersonal());
    }

   

    

    /**
     * @return the alumnoCursoSeccionGrupoExamen
     */
    public alumnoCursoSeccionGrupoExamenC getAlumnoCursoSeccionGrupoExamen() {
        return alumnoCursoSeccionGrupoExamen;
    }

    /**
     * @param alumnoCursoSeccionGrupoExamen the alumnoCursoSeccionGrupoExamen to
     * set
     */
    public void setAlumnoCursoSeccionGrupoExamen(alumnoCursoSeccionGrupoExamenC alumnoCursoSeccionGrupoExamen) {
        this.alumnoCursoSeccionGrupoExamen = alumnoCursoSeccionGrupoExamen;
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
     * @return the cantGrupo
     */
    public int getCantGrupo() {
        return cantGrupo;
    }

    /**
     * @param cantGrupo the cantGrupo to set
     */
    public void setCantGrupo(int cantGrupo) {
        this.cantGrupo = cantGrupo;
    }

    
    /**
     * @return the cursoPeriodoGrupoExamenL
     */
    public List<cursoPeriodoGrupoExamenC> getCursoPeriodoGrupoExamenL() {
        return cursoPeriodoGrupoExamenL;
    }

    /**
     * @param cursoPeriodoGrupoExamenL the cursoPeriodoGrupoExamenL to set
     */
    public void setCursoPeriodoGrupoExamenL(List<cursoPeriodoGrupoExamenC> cursoPeriodoGrupoExamenL) {
        this.cursoPeriodoGrupoExamenL = cursoPeriodoGrupoExamenL;
    }

    /**
     * @return the cursoPeriodoGrupoExamen
     */
    public cursoPeriodoGrupoExamenC getCursoPeriodoGrupoExamen() {
        return cursoPeriodoGrupoExamen;
    }

    /**
     * @param cursoPeriodoGrupoExamen the cursoPeriodoGrupoExamen to set
     */
    public void setCursoPeriodoGrupoExamen(cursoPeriodoGrupoExamenC cursoPeriodoGrupoExamen) {
        this.cursoPeriodoGrupoExamen = cursoPeriodoGrupoExamen;
    }

    

    public static class detalleAlumnoGrupo {
        private int institucion;
        private String periodo;
        private String carrera;
        private String malla;
        private String curso;
        private String alumno;
        private String persona;
        private String desPersona;
        private String seccion;
        private int grupoExamen;

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
         * @return the grupoExamen
         */
        public int getGrupoExamen() {
            return grupoExamen;
        }

        /**
         * @param grupoExamen the grupoExamen to set
         */
        public void setGrupoExamen(int grupoExamen) {
            this.grupoExamen = grupoExamen;
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
    }

    /**
     * @return the cursoSeccionDocenteTipoExamen
     */
    public cursoSeccionDocenteTipoExamenC getCursoSeccionDocenteTipoExamen() {
        return cursoSeccionDocenteTipoExamen;
    }

    /**
     * @param cursoSeccionDocenteTipoExamen the cursoSeccionDocenteTipoExamen to
     * set
     */
    public void setCursoSeccionDocenteTipoExamen(cursoSeccionDocenteTipoExamenC cursoSeccionDocenteTipoExamen) {
        this.cursoSeccionDocenteTipoExamen = cursoSeccionDocenteTipoExamen;
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
     * @return the detallegrupoDocenteL
     */
    public List<detallegrupoDocente> getDetallegrupoDocenteL() {
        return detallegrupoDocenteL;
    }

    /**
     * @param detallegrupoDocenteL the detallegrupoDocenteL to set
     */
    public void setDetallegrupoDocenteL(List<detallegrupoDocente> detallegrupoDocenteL) {
        this.detallegrupoDocenteL = detallegrupoDocenteL;
    }

}
