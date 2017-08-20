
package Beans;

import DAO.alumnoCursoSeccionGrupoDAO;
import DAO.cursoSeccionDAO;
import DAO.docenteTipoExamenDAO;
import DAO.invCursoSeccionGrupoDAO;
import ENTIDAD.alumnoCursoSeccionGrupoC;
import ENTIDAD.cursoSeccionC;
import ENTIDAD.invCursoSeccionGrupoC;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="administrarGrupoB")
@ViewScoped
public class administrarGrupo {
    private List<invCursoSeccionGrupoC> invCursoSeccionGrupoL=  new ArrayList<>();
    private invCursoSeccionGrupoC invCursoSeccionGrupo=new invCursoSeccionGrupoC();
    private invCursoSeccionGrupoC invCursoSeccionGrupoS=new invCursoSeccionGrupoC();
    
    private List<detalleAlumno> alumnoGrupoLibresL=  new ArrayList<>();
    private List<detalleAlumno> alumnoGrupoL=  new ArrayList<>();
    private detalleAlumno alumnoGrupoLibresS=new detalleAlumno();
    private detalleAlumno alumnoGrupoS=new detalleAlumno();
    
    private cursoSeccionC cursoSeccionS;
    
    private String carreraS;
    private String cursoS;
    private String seccionS;
    private String busAlumno;
    private int totalAsignados;
    
    
    
    @ManagedProperty(value = "#{usuarioB}")
    private usuario sessionUsuario;
    
    
    public administrarGrupo() {
       
    }
    
    docenteTipoExamenDAO docenteTipoExamenD;
    alumnoCursoSeccionGrupoDAO alumnoCursoSeccionGrupoD;
    cursoSeccionDAO cursoSeccionD;
    
    public void mostrarAlumnos(invCursoSeccionGrupoC item) {
        invCursoSeccionGrupoS=item;      
        docenteTipoExamenD=new docenteTipoExamenDAO();
        alumnoGrupoLibresL= docenteTipoExamenD.mostrarAlumnoGrupoInvestigacionLibre(invCursoSeccionGrupoS.getInstitucion(), invCursoSeccionGrupoS.getPeriodo(), invCursoSeccionGrupoS.getCarrera(), invCursoSeccionGrupoS.getCurso(),invCursoSeccionGrupoS.getSeccion(),"%");
        alumnoGrupoL= docenteTipoExamenD.mostrarAlumnoGrupoInvestigacion(invCursoSeccionGrupoS.getInstitucion(), invCursoSeccionGrupoS.getPeriodo(), invCursoSeccionGrupoS.getCarrera(), invCursoSeccionGrupoS.getCurso(),invCursoSeccionGrupoS.getSeccion(),invCursoSeccionGrupoS.getGrupoExamen());
        alumnoGrupoS=null;
        alumnoGrupoLibresS=null;
        util.script("dlgGrupoAlumnos.show()");
    }
      public void filtroAlumnoLibres(){
        docenteTipoExamenD=new docenteTipoExamenDAO();
        alumnoGrupoLibresL= docenteTipoExamenD.mostrarAlumnoGrupoInvestigacionLibre(invCursoSeccionGrupoS.getInstitucion(), invCursoSeccionGrupoS.getPeriodo(), invCursoSeccionGrupoS.getCarrera(), invCursoSeccionGrupoS.getCurso(),invCursoSeccionGrupoS.getSeccion(),"%" + busAlumno.concat("%"));
    }
    
      
      
        public void pasar(){
            
            
            if (alumnoGrupoLibresS !=null)
            {
             alumnoCursoSeccionGrupoD = new alumnoCursoSeccionGrupoDAO();
             alumnoCursoSeccionGrupoD.insertar(new alumnoCursoSeccionGrupoC(invCursoSeccionGrupoS.getInstitucion(), invCursoSeccionGrupoS.getPeriodo(), invCursoSeccionGrupoS.getCarrera(), invCursoSeccionGrupoS.getMalla(), invCursoSeccionGrupoS.getCurso(), alumnoGrupoLibresS.seccion , alumnoGrupoLibresS.alumno ,invCursoSeccionGrupoS.getGrupoExamen(), 1));                
            
             docenteTipoExamenD=new docenteTipoExamenDAO();
             alumnoGrupoLibresL= docenteTipoExamenD.mostrarAlumnoGrupoInvestigacionLibre(invCursoSeccionGrupoS.getInstitucion(), invCursoSeccionGrupoS.getPeriodo(), invCursoSeccionGrupoS.getCarrera(), invCursoSeccionGrupoS.getCurso(),invCursoSeccionGrupoS.getSeccion(),"%");
             alumnoGrupoL= docenteTipoExamenD.mostrarAlumnoGrupoInvestigacion(invCursoSeccionGrupoS.getInstitucion(), invCursoSeccionGrupoS.getPeriodo(), invCursoSeccionGrupoS.getCarrera(), invCursoSeccionGrupoS.getCurso(),invCursoSeccionGrupoS.getSeccion(),invCursoSeccionGrupoS.getGrupoExamen());
             alumnoGrupoS=alumnoGrupoLibresS;             
             invCursoSeccionGrupoD=new invCursoSeccionGrupoDAO();
             invCursoSeccionGrupoS=invCursoSeccionGrupoD.mostrarCursoSeccionGrupo(invCursoSeccionGrupoS.getInstitucion(),invCursoSeccionGrupoS.getPeriodo(),invCursoSeccionGrupoS.getCarrera(),invCursoSeccionGrupoS.getMalla(),invCursoSeccionGrupoS.getCurso(),invCursoSeccionGrupoS.getSeccion(),invCursoSeccionGrupoS.getGrupoExamen());
             alumnoGrupoLibresS=null;
             busAlumno="";
         //    util.script("document.getElementById('frmGrupoAlumnos\\:txtBusqueda').focus()");
            }else{
                util.alert("Seleccione una alumno libre");
            }
                
            
             
        }
        
         public void regresar(){
              if (alumnoGrupoS !=null)
            {
            alumnoCursoSeccionGrupoD = new alumnoCursoSeccionGrupoDAO();
            alumnoCursoSeccionGrupoD.eliminar(new alumnoCursoSeccionGrupoC(invCursoSeccionGrupoS.getInstitucion(), invCursoSeccionGrupoS.getPeriodo(), invCursoSeccionGrupoS.getCarrera(), invCursoSeccionGrupoS.getMalla(), invCursoSeccionGrupoS.getCurso(), alumnoGrupoS.seccion, alumnoGrupoS.alumno ,  invCursoSeccionGrupoS.getGrupoExamen(), 1));                
            
            
            docenteTipoExamenD=new docenteTipoExamenDAO();
            
            alumnoGrupoLibresL= docenteTipoExamenD.mostrarAlumnoGrupoInvestigacionLibre(invCursoSeccionGrupoS.getInstitucion(), invCursoSeccionGrupoS.getPeriodo(), invCursoSeccionGrupoS.getCarrera(), invCursoSeccionGrupoS.getCurso(),invCursoSeccionGrupoS.getSeccion(),"%");
            alumnoGrupoL= docenteTipoExamenD.mostrarAlumnoGrupoInvestigacion(invCursoSeccionGrupoS.getInstitucion(), invCursoSeccionGrupoS.getPeriodo(), invCursoSeccionGrupoS.getCarrera(), invCursoSeccionGrupoS.getCurso(),invCursoSeccionGrupoS.getSeccion(),invCursoSeccionGrupoS.getGrupoExamen());
             alumnoGrupoLibresS=alumnoGrupoS;
             
             invCursoSeccionGrupoD=new invCursoSeccionGrupoDAO();
             invCursoSeccionGrupoS=invCursoSeccionGrupoD.mostrarCursoSeccionGrupo(invCursoSeccionGrupoS.getInstitucion(),invCursoSeccionGrupoS.getPeriodo(),invCursoSeccionGrupoS.getCarrera(),invCursoSeccionGrupoS.getMalla(),invCursoSeccionGrupoS.getCurso(),invCursoSeccionGrupoS.getSeccion(),invCursoSeccionGrupoS.getGrupoExamen());
             
             
             alumnoGrupoS=null;
         }else{
                util.alert("Seleccione una alumno del grupo" + invCursoSeccionGrupoS.getGrupoExamen() );
            }
        }

  
    
   
    
  
    
   
    invCursoSeccionGrupoDAO invCursoSeccionGrupoD;
    
    
    
    public void mostrarListaGrupos(){
        invCursoSeccionGrupoD=new invCursoSeccionGrupoDAO();        
        invCursoSeccionGrupoL=invCursoSeccionGrupoD.mostrarGrupos(sessionUsuario.getInstitucionS(), sessionUsuario.getPeriodoS(), carreraS, cursoS, seccionS);
        
        
        cursoSeccionD =new cursoSeccionDAO();
        cursoSeccionS=cursoSeccionD.mostrarCursoSeccion(""+sessionUsuario.getInstitucionS(), sessionUsuario.getPeriodoS(), carreraS, cursoS, seccionS);
    }
    
    public void nuevoGrupo(){
        invCursoSeccionGrupo=new invCursoSeccionGrupoC();        
        invCursoSeccionGrupo.setInstitucion(cursoSeccionS.getInstitucion());
        invCursoSeccionGrupo.setPeriodo(cursoSeccionS.getPeriodo());
        invCursoSeccionGrupo.setCarrera(cursoSeccionS.getCarrera());
        invCursoSeccionGrupo.setMalla(cursoSeccionS.getMalla());
        invCursoSeccionGrupo.setCurso(cursoSeccionS.getCurso());
        invCursoSeccionGrupo.setSeccion(cursoSeccionS.getSeccion());
        invCursoSeccionGrupo.setGrupoExamen(-1);
        invCursoSeccionGrupo.setEstadoRegistro(1);
        totalAsignados=0;
        for (invCursoSeccionGrupoC item : invCursoSeccionGrupoL) {
            totalAsignados+= item.getVacanteMaximo();
        }
        util.script("dlgGrupo.show()");
    }
     public void editarGrupo(invCursoSeccionGrupoC item){
        invCursoSeccionGrupo=item;
        totalAsignados=0;
        
         for (invCursoSeccionGrupoC itemGrupo : invCursoSeccionGrupoL) {
              totalAsignados+= itemGrupo.getVacanteMaximo();
         } 
        totalAsignados-=item.getVacanteMaximo();
        util.script("dlgGrupo.show()");
           
    }
      public void eliminarGrupo(invCursoSeccionGrupoC item){
          
      if(item.getVacanteActual()>0){
          util.alert("AUN EXISTEN ALUMNO ASIGANADOS");
      }   else{
           invCursoSeccionGrupoD=new invCursoSeccionGrupoDAO();
      invCursoSeccionGrupoD.eliminar(item);
       mostrarListaGrupos();
      } 
          
          
     
    }
    
    
    
    public void insertarGrupo(){
        int total;
        
        
        if(invCursoSeccionGrupo.getGrupoExamen()==-1){ // NUEVO
             total=invCursoSeccionGrupo.getVacanteMaximo() + totalAsignados;
            if (total>cursoSeccionS.getVacantesActuales()){
                util.alert("El grupo supera los vacantes");
            }else  {
                    if (invCursoSeccionGrupo.getVacanteMaximo()>0){
                         invCursoSeccionGrupoD=new invCursoSeccionGrupoDAO();
                        invCursoSeccionGrupoD.insertar(invCursoSeccionGrupo);
                        util.render("frmAdministrarGrupos:pbGrupos");                        
                        mostrarListaGrupos();
                        util.script("dlgGrupo.hide()");
                    }
                    else{
                        util.alert("Ingrese una cantidad mayor a 0");
                    }

            }
        }else { //EDITAR
            
            
            if(invCursoSeccionGrupo.getVacanteActual()<=invCursoSeccionGrupo.getVacanteMaximo()){
                  total=(invCursoSeccionGrupo.getVacanteMaximo() + totalAsignados);
                    if (total>cursoSeccionS.getVacantesActuales()){
                       util.alert("El grupo supera los vacantes");
                   }else  {
                           if (invCursoSeccionGrupo.getVacanteMaximo()>0){
                                invCursoSeccionGrupoD=new invCursoSeccionGrupoDAO();
                               invCursoSeccionGrupoD.insertar(invCursoSeccionGrupo);
                               
                               mostrarListaGrupos();
                               util.render("frmAdministrarGrupos:pbGrupos");
                               util.script("dlgGrupo.hide()");
                               
                               
                           }
                           else{
                               util.alert("Ingrese una cantidad mayor a 0");
                           }

                   }
            }else{
                util.alert("El grupo aun tiene alumnoss");
            }
                  
        }
        
        
        
       
    }
    public List<detalleAlumno> getAlumnoGrupoLibresL() {
        return alumnoGrupoLibresL;
    }
    public void setAlumnoGrupoLibresL(List<detalleAlumno> alumnoGrupoLibresL) {
        this.alumnoGrupoLibresL = alumnoGrupoLibresL;
    }
    public List<detalleAlumno> getAlumnoGrupoL() {
        return alumnoGrupoL;
    }
    public void setAlumnoGrupoL(List<detalleAlumno> alumnoGrupoL) {
        this.alumnoGrupoL = alumnoGrupoL;
    }
    public detalleAlumno getAlumnoGrupoLibresS() {
        return alumnoGrupoLibresS;
    }
    public void setAlumnoGrupoLibresS(detalleAlumno alumnoGrupoLibresS) {
        this.alumnoGrupoLibresS = alumnoGrupoLibresS;
    }
    public detalleAlumno getAlumnoGrupoS() {
        return alumnoGrupoS;
    }
    public void setAlumnoGrupoS(detalleAlumno alumnoGrupoS) {
        this.alumnoGrupoS = alumnoGrupoS;
    }
    public invCursoSeccionGrupoC getInvCursoSeccionGrupoS() {
        return invCursoSeccionGrupoS;
    }
    public void setInvCursoSeccionGrupoS(invCursoSeccionGrupoC invCursoSeccionGrupoS) {
        this.invCursoSeccionGrupoS = invCursoSeccionGrupoS;
    }
    public String getBusAlumno() {
        return busAlumno;
    }
    public void setBusAlumno(String busAlumno) {
        this.busAlumno = busAlumno;
    }
    public cursoSeccionC getCursoSeccionS() {
        return cursoSeccionS;
    }
    public void setCursoSeccionS(cursoSeccionC cursoSeccionS) {
        this.cursoSeccionS = cursoSeccionS;
    }
    public int getTotalAsignados() {
        return totalAsignados;
    }
    public void setTotalAsignados(int totalAsignados) {
        this.totalAsignados = totalAsignados;
    }
    public usuario getSessionUsuario() {
        return sessionUsuario;
    }
    public void setSessionUsuario(usuario sessionUsuario) {
        this.sessionUsuario = sessionUsuario;
    }
    
    
    
    
    
    public static class detalleAlumno{
        private int institucion;
        private String periodo;
        private String carrera;
        private String malla;
        private String curso;
        private String alumno;
        private String persona;
        private String desPersona;
        private String seccion;
        private int grupo;

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
         * @return the grupo
         */
        public int getGrupo() {
            return grupo;
        }

        /**
         * @param grupo the grupo to set
         */
        public void setGrupo(int grupo) {
            this.grupo = grupo;
        }
    }

    /**
     * @return the invCursoSeccionGrupoL
     */
    public List<invCursoSeccionGrupoC> getInvCursoSeccionGrupoL() {
        return invCursoSeccionGrupoL;
    }

    /**
     * @param invCursoSeccionGrupoL the invCursoSeccionGrupoL to set
     */
    public void setInvCursoSeccionGrupoL(List<invCursoSeccionGrupoC> invCursoSeccionGrupoL) {
        this.invCursoSeccionGrupoL = invCursoSeccionGrupoL;
    }

    /**
     * @return the invCursoSeccionGrupo
     */
    public invCursoSeccionGrupoC getInvCursoSeccionGrupo() {
        return invCursoSeccionGrupo;
    }

    /**
     * @param invCursoSeccionGrupo the invCursoSeccionGrupo to set
     */
    public void setInvCursoSeccionGrupo(invCursoSeccionGrupoC invCursoSeccionGrupo) {
        this.invCursoSeccionGrupo = invCursoSeccionGrupo;
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
}
