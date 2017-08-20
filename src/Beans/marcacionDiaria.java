/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.registrotmDAO;
import DAO.turnoDAO;

import ENTIDAD.turnoC;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author molano
 */
@ManagedBean(name="marcacionDiariaB")
@ViewScoped
public class marcacionDiaria {
    private Date fecha=util.fechaHoy();

    private List<detalleMarcacionDiaria> detalleMarcacionDiariaL;
    private String horaInicio;
    private String horaFinal;
    private int maxMinutos=959;
    private int minMinutos=0;
    private String inicio;
    private String fin;
    private int totalCompleta;
    private int totalIncompleta;
    private int totalFalta;
    private turnoC turno;
    
    private String turnoS="%";
    private String carreraS="%";
    registrotmDAO registrotmD;
    turnoDAO turnoD;

    public marcacionDiaria() {
        this.turno=new turnoC(-1,"todos","todos",util.convertDate("01-01-1999 08:00"),util.convertDate("01-01-1999 23:59"),null,null,1);
    }

  
    
    
    
    public void calcularHoras(String turno){
        
        if (turno.endsWith("%")){
            this.turno=new turnoC(-1,"todos","todos",util.convertDate("01-01-1999 08:00"),util.convertDate("01-01-1999 23:59"),null,null,1);
             maxMinutos=(int)util.dateIff(this.turno.getHoraInicio(), this.turno.getHoraFin(),1);
        }else{
             turnoD=new turnoDAO();
            this.turno=turnoD.mostrarTurno(Integer.parseInt(turno));        
            maxMinutos=(int)util.dateIff(this.turno.getHoraInicio(), this.turno.getHoraFin(),1);
            util.consolaCliente("maximo minutos " + maxMinutos);
        }
       
        
    }
    
    public void mostrarRegistroDiario(String periodo,String personal,String carrera,String turno,Date fecha,String horaInicio,String horaFinal) throws ParseException{
        
        registrotmD=new registrotmDAO();
        horaInicio= util.convertTimes(util.dateAdd(util.convertDateTime(this.turno.getHoraInicio()), minMinutos));
        horaFinal=util.convertTimes(util.dateAdd(util.convertDateTime(this.turno.getHoraInicio()), maxMinutos));
        
        detalleMarcacionDiariaL=registrotmD.mostrarRegistrotmDiario(periodo,personal,carrera, turno, fecha,horaInicio,horaFinal);
        totales();
    }
    public void totales(){
        totalFalta=0;
        totalIncompleta=0;
        totalCompleta=0;
        for (detalleMarcacionDiaria item : detalleMarcacionDiariaL) {
           switch ( item.getEstadoRegistro()){
               case 0: totalFalta++;break;
               case 56: totalIncompleta++;break;
               case 57: totalCompleta++; break;
                   
           }
            
        }
    }

    /**
     * @return the detalleMarcacionDiariaL
     */
    public List<detalleMarcacionDiaria> getDetalleMarcacionDiariaL() {
        return detalleMarcacionDiariaL;
    }

    /**
     * @param detalleMarcacionDiariaL the detalleMarcacionDiariaL to set
     */
    public void setDetalleMarcacionDiariaL(List<detalleMarcacionDiaria> detalleMarcacionDiariaL) {
        this.detalleMarcacionDiariaL = detalleMarcacionDiariaL;
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
    
    
    public static class   detalleMarcacionDiaria{
        private int institucion;
        private String periodo;
        private String grupo;
        private Date fecha;
        private int dia;
        private Date horaInicio;
        private Date horaFinal;
        private String cpersonal;
        private String personal;
        private String persona;
        private String desPersonal;
        private String carrera;
        private String desCarrera;
        private String curso;
        private String desCurso;
        private String seccion;
        private int turno;
        private int desTurno;
        private String desTipoClase;
        private String tipo;
        private String aula;
        private String observacion;
        private int estadoRegistro;

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
         * @return the grupo
         */
        public String getGrupo() {
            return grupo;
        }

        /**
         * @param grupo the grupo to set
         */
        public void setGrupo(String grupo) {
            this.grupo = grupo;
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
         * @return the desTurno
         */
        public int getDesTurno() {
            return desTurno;
        }

        /**
         * @param desTurno the desTurno to set
         */
        public void setDesTurno(int desTurno) {
            this.desTurno = desTurno;
        }

        /**
         * @return the desTipoClase
         */
        public String getDesTipoClase() {
            return desTipoClase;
        }

        /**
         * @param desTipoClase the desTipoClase to set
         */
        public void setDesTipoClase(String desTipoClase) {
            this.desTipoClase = desTipoClase;
        }

        /**
         * @return the tipo
         */
        public String getTipo() {
            return tipo;
        }

        /**
         * @param tipo the tipo to set
         */
        public void setTipo(String tipo) {
            this.tipo = tipo;
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
         * @return the observacion
         */
        public String getObservacion() {
            return observacion;
        }

        /**
         * @param observacion the observacion to set
         */
        public void setObservacion(String observacion) {
            this.observacion = observacion;
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
     * @return the horaInicio
     */
    public String getHoraInicio() {
        return horaInicio;
    }

    /**
     * @param horaInicio the horaInicio to set
     */
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * @return the horaFinal
     */
    public String getHoraFinal() {
        return horaFinal;
    }

    /**
     * @param horaFinal the horaFinal to set
     */
    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    /**
     * @return the totalIncompleta
     */
    public int getTotalIncompleta() {
        return totalIncompleta;
    }

    /**
     * @param totalIncompleta the totalIncompleta to set
     */
    public void setTotalIncompleta(int totalIncompleta) {
        this.totalIncompleta = totalIncompleta;
    }

    /**
     * @return the totalFalta
     */
    public int getTotalFalta() {
        return totalFalta;
    }

    /**
     * @param totalFalta the totalFalta to set
     */
    public void setTotalFalta(int totalFalta) {
        this.totalFalta = totalFalta;
    }

    /**
     * @return the registrotmD
     */
    public registrotmDAO getRegistrotmD() {
        return registrotmD;
    }

    /**
     * @param registrotmD the registrotmD to set
     */
    public void setRegistrotmD(registrotmDAO registrotmD) {
        this.registrotmD = registrotmD;
    }

    /**
     * @return the totalCompleta
     */
    public int getTotalCompleta() {
        return totalCompleta;
    }

    /**
     * @param totalCompleta the totalCompleta to set
     */
    public void setTotalCompleta(int totalCompleta) {
        this.totalCompleta = totalCompleta;
    }

    /**
     * @return the turno
     */
    public turnoC getTurno() {
        return turno;
    }

    /**
     * @param turno the turno to set
     */
    public void setTurno(turnoC turno) {
        this.turno = turno;
    }

    /**
     * @return the maxMinutos
     */
    public int getMaxMinutos() {
        return maxMinutos;
    }

    /**
     * @param maxMinutos the maxMinutos to set
     */
    public void setMaxMinutos(int maxMinutos) {
        this.maxMinutos = maxMinutos;
    }

    /**
     * @return the minMinutos
     */
    public int getMinMinutos() {
        return minMinutos;
    }

    /**
     * @param minMinutos the minMinutos to set
     */
    public void setMinMinutos(int minMinutos) {
        this.minMinutos = minMinutos;
    }

    /**
     * @return the inicio
     */
    public String getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    /**
     * @return the fin
     */
    public String getFin() {
        return fin;
    }

    /**
     * @param fin the fin to set
     */
    public void setFin(String fin) {
        this.fin = fin;
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
}
