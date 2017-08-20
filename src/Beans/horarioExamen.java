
package Beans;

import DAO.horarioSemanalDAO;

import DAO.registroExamenesDAO;
import DAO.seccionPeriodoDAO;
import DAO.semanaExamenDAO;


import ENTIDAD.registroExamenesC;
import ENTIDAD.seccionPeriodoC;
import ENTIDAD.semanaExamenC;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="horarioExamenB")
@ViewScoped
public class horarioExamen {
    private String carreraS;
    private String seccionS;
    private String tipoExamenS;

    private String turnoS;
    
    
    private List<detalleDia> detalleDiaL=new ArrayList<>();
    private List<detalleDia> detalleDiaT=new ArrayList<>();
    private seccionPeriodoC seccionPeriodo=new seccionPeriodoC();
    private boolean bandera;
    private int accion=0;
    private semanaExamenC semanaExamenes;
 
   
   public void mensaje(){
     
       util.script("$('.button').click()");
   }
    
    public void agregar(detalleHorario item,int dia,int indice){
        item.setTop(0);
       item.setAlto(item.turno==1?100:90);
       item.setDia(dia);
       item.setTipoExamen(tipoExamenS);
       item.setFecha(util.dateAdd(semanaExamenes.getFechaInicio(), 5, dia-1));
        detalleDiaL.get(dia).detalleHorarioL.add(item);
        detalleDiaL.get(0).detalleHorarioL.remove(indice);
    }
    public void eliminar(detalleHorario item,int dia,int indice){
        item.setDia(0);
        item.setFecha(null);
        item.setHoraInicio(null);
        item.setHoraFinal(null);
        detalleDiaL.get(0).detalleHorarioL.add(item);        
        detalleDiaL.get(dia).detalleHorarioL.remove(indice);   
    }
    public void mostrar(){
        mostrarHorarioExamen();
        util.script("$('.mover' ).draggable({disabled: true });");
    }
    
    public void mostrarHorarioExamen(){
      
             
       seccionPeriodo=new seccionPeriodoDAO().mostrarSeccionPeriodo(1, "20171", carreraS, seccionS);
       
       
       
      
       semanaExamenes=new semanaExamenDAO().mostrarSemanaExamenes(1, "20171", seccionPeriodo.getVencimiento(), tipoExamenS);  
         detalleDia itemDia;
         detalleDiaL=new ArrayList<>();
         if( semanaExamenes!=null){
           for(int dia=0;dia<=7;dia++){
             itemDia=new detalleDia(dia,semanaExamenes.getFechaInicio());
         
             for (detalleHorario itemHorario : new horarioSemanalDAO().mostrarHorarioExamen(1, "20171",carreraS,seccionS,tipoExamenS,dia,util.dateAdd(semanaExamenes.getFechaInicio(), 5, dia))) {
                 itemDia.getDetalleHorarioL().add(itemHorario);
             }
             detalleDiaL.add(itemDia);
         }  
         }
         
         accion=1;
         
        
    }
    public void cancelar(){
       detalleDiaL=detalleDiaT;
       mostrarHorarioExamen();
       bandera=false;
       util.script("$('.mover' ).draggable({disabled: true });");
       
    }
     public void guardar(){
          for (detalleDia item : detalleDiaL) {
             for (detalleHorario itemHora : item.getDetalleHorarioL()) {
               
                 if(itemHora.dia!=0 && itemHora.horaInicio!=null && itemHora.tipoClase==4){                          
                        
                      
                        new registroExamenesDAO().insertar(new registroExamenesC ( itemHora.institucion, itemHora.periodo, itemHora.carrera, itemHora.malla, itemHora.curso, itemHora.seccion, itemHora.turno, itemHora.nivelModular , seccionPeriodo.getVencimiento(),itemHora.personal, itemHora.cpersonal, itemHora.fecha, itemHora.horaInicio, itemHora.horaFinal, "aula", itemHora.TipoExamen, 1));
                 }
                 else if(itemHora.dia==0 && itemHora.tipoClase==4){
                       
                         new registroExamenesDAO().eliminar(new registroExamenesC ( itemHora.institucion, itemHora.periodo, itemHora.carrera, itemHora.malla, itemHora.curso, itemHora.seccion, itemHora.turno, itemHora.nivelModular , seccionPeriodo.getVencimiento(),itemHora.personal ,itemHora.cpersonal, itemHora.fecha, itemHora.horaInicio, itemHora.horaFinal, "aula", itemHora.TipoExamen, 1));
                 }
           
                
            }
        }
        mostrarHorarioExamen();
       bandera=false;
       
        util.script("$('.mover' ).draggable({disabled: true });");
        util.render("frmHorarioExamen:pnHorario");
        util.render("frmHorarioExamen:pnBotonera");
        util.render("frmHorarioExamen:pnCursos");
       
    }
    
      public void editar(){
      detalleDiaT=detalleDiaL;
      bandera=true;
      util.script("$('.mover' ).draggable({disabled: false });");
        
    }

    /**
     * @return the detalleDiaL
     */
    public List<detalleDia> getDetalleDiaL() {
        return detalleDiaL;
    }

    /**
     * @param detalleDiaL the detalleDiaL to set
     */
    public void setDetalleDiaL(List<detalleDia> detalleDiaL) {
        this.detalleDiaL = detalleDiaL;
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

    /**
     * @return the detalleDiaT
     */
    public List<detalleDia> getDetalleDiaT() {
        return detalleDiaT;
    }

    /**
     * @param detalleDiaT the detalleDiaT to set
     */
    public void setDetalleDiaT(List<detalleDia> detalleDiaT) {
        this.detalleDiaT = detalleDiaT;
    }

//    /**
//     * @return the periodoGrupo
//     */
//    public periodoGrupoC getPeriodoGrupo() {
//        return periodoGrupo;
//    }
//
//    /**
//     * @param periodoGrupo the periodoGrupo to set
//     */
//    public void setPeriodoGrupo(periodoGrupoC periodoGrupo) {
//        this.periodoGrupo = periodoGrupo;
//    }

    /**
     * @return the accion
     */
    public int getAccion() {
        return accion;
    }

    /**
     * @param accion the accion to set
     */
    public void setAccion(int accion) {
        this.accion = accion;
    }

    /**
     * @return the turnoS
     */
    public String getTurnoS() {
        return turnoS;
    }

    /**
     * @param turnoS the turnoS to set
     */
    public void setTurnoS(String turnoS) {
        this.turnoS = turnoS;
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static class detalleDia{
        private int dia;
        private Date fecha;
        private List<detalleHorario> detalleHorarioL=new ArrayList<>();

        public detalleDia() {
            
        } 

        public detalleDia(int dia, Date fecha) {
            this.dia = dia;
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
         * @return the detalleHorarioL
         */
        public List<detalleHorario> getDetalleHorarioL() {
            return detalleHorarioL;
        }

        /**
         * @param detalleHorarioL the detalleHorarioL to set
         */
        public void setDetalleHorarioL(List<detalleHorario> detalleHorarioL) {
            this.detalleHorarioL = detalleHorarioL;
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
    }
    
    
    public  static class detalleHorario{
        private int institucion;
        private String periodo;        
        private String carrera;      
        private String malla;      
        private String curso;
        private String desCurso;
        private String seccion;
        private String aula;
        private Date fecha;
        private String persona;
        private String cpersonal;
        private String personal;
        private String desPersonal;
        private int dia;
        private int turno;
        private int nivelModular;
        private String TipoExamen;
        private int tipoClase;
        private Date horaInicio; 
        private Date horaFinal; 
        private int alto;
        private int top;
        private int estadoRegistro;

       

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
         * @return the aula
         */
        public String getAula() {
            return aula;
        }

        /**
         * @param aula the aula to set
         */
        public void setAula(String aula) {
            this.aula = aula;
        }

       
        /**
         * @return the horaInicio
         */
        public Date getHoraInicio() {
            return horaInicio;
        }

        /**
         * @param horaInicio the horaInicio to set
         */
        public void setHoraInicio(Date horaInicio) {
            this.horaInicio = horaInicio;
        }

        /**
         * @return the horaFinal
         */
        public Date getHoraFinal() {
            return horaFinal;
        }

        /**
         * @param horaFinal the horaFinal to set
         */
        public void setHoraFinal(Date horaFinal) {
            this.horaFinal = horaFinal;
        }

        /**
         * @return the alto
         */
        public int getAlto() {
            return alto;
        }

        /**
         * @param alto the alto to set
         */
        public void setAlto(int alto) {
            this.alto = alto;
        }

        /**
         * @return the top
         */
        public int getTop() {
            return top;
        }

        /**
         * @param top the top to set
         */
        public void setTop(int top) {
            this.top = top;
        }

        /**
         * @return the desPersonal
         */
        public String getDesPersonal() {
            return desPersonal;
        }

        /**
         * @param desPersonal the desPersonal to set
         */
        public void setDesPersonal(String desPersonal) {
            this.desPersonal = desPersonal;
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
         * @return the TipoExamen
         */
        public String getTipoExamen() {
            return TipoExamen;
        }

        /**
         * @param TipoExamen the TipoExamen to set
         */
        public void setTipoExamen(String TipoExamen) {
            this.TipoExamen = TipoExamen;
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
         * @return the cpersonal
         */
        public String getCpersonal() {
            return cpersonal;
        }

        /**
         * @param cpersonal the cpersonal to set
         */
        public void setCpersonal(String cpersonal) {
            this.cpersonal = cpersonal;
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
         * @return the nivelModular
         */
        public int getNivelModular() {
            return nivelModular;
        }

        /**
         * @param nivelModular the nivelModular to set
         */
        public void setNivelModular(int nivelModular) {
            this.nivelModular = nivelModular;
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

  


    
    
    
    


    /**
     * @return the tipoExamenS
     */
    public String getTipoExamenS() {
        return tipoExamenS;
    }

    /**
     * @param tipoExamenS the tipoExamenS to set
     */
    public void setTipoExamenS(String tipoExamenS) {
        this.tipoExamenS = tipoExamenS;
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
    
}
