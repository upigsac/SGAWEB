/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.resumenTipoNotaDAO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Administrador
 */
@ManagedBean(name="resumenTipoNotaB")
@ViewScoped
public class resumenTipoNota {
    
    private String carreraS;
    private String tipoExamenS;
    
    private List<cabecera> cabeceraL;
    private List<detalle> detalleL;
    
    resumenTipoNotaDAO resumenTipoNotaD;
    public  void mostrar(int institucion,String periodo){
        cabeceraL=new ArrayList<>();
        resumenTipoNotaD=new resumenTipoNotaDAO();
       
        cabecera item;
        for (cabecera itemCabecera : resumenTipoNotaD.mostrarCabecera(institucion,periodo,carreraS,tipoExamenS)) {
            
            item=new cabecera(itemCabecera.institucion, itemCabecera.desperiodo, itemCabecera.descarrera, itemCabecera.desCiclo,itemCabecera.curso, itemCabecera.desCurso, itemCabecera.seccion, itemCabecera.desTurno,itemCabecera.persona, itemCabecera.cpersonal, itemCabecera.personal, itemCabecera.desPersona,itemCabecera.matriculados,itemCabecera.aprobados,itemCabecera.desaprobados);
            detalleL=resumenTipoNotaD.mostrarDetalle(itemCabecera.institucion, itemCabecera.periodo, itemCabecera.carrera,itemCabecera.curso,itemCabecera.seccion,tipoExamenS);
            for (detalle itemDetalle : detalleL) {                
                item.getDetalleL().add(new detalle(itemDetalle.codigo, itemDetalle.alumno, itemDetalle.nota, itemDetalle.estadoNota,itemDetalle.presente,itemDetalle.falta));
            }            
        
            cabeceraL.add(item);
            
        }
  

        
    }

    public resumenTipoNota() {
        
    }
    

    
    /**
     * @return the cabeceraL
     */
    public List<cabecera> getCabeceraL() {
        return cabeceraL;
    }

    /**
     * @param cabeceraL the cabeceraL to set
     */
    public void setCabeceraL(List<cabecera> cabeceraL) {
        this.cabeceraL = cabeceraL;
    }

    /**
     * @return the detalleL
     */
    public List<detalle> getDetalleL() {
        return detalleL;
    }

    /**
     * @param detalleL the detalleL to set
     */
    public void setDetalleL(List<detalle> detalleL) {
        this.detalleL = detalleL;
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


    
    
    public static class cabecera{
        private int institucion;
        private String desperiodo;
        private String periodo;
        private String descarrera;
        private String carrera;
        private String desCiclo;
        private String desCurso;
        private String curso;
        private String seccion;
        private String desTurno;
        private String cpersonal;
        private String personal;
        private String persona;
        private String desPersona;
        private int matriculados;
        private int aprobados;
        private int desaprobados;
        private int totalPresente;
        private int totalFaltas;
        private List<detalle> detalleL;
        public cabecera() {
        }

        public cabecera(int institucion, String desperiodo, String descarrera, String desCiclo,String curso, String desCurso, String seccion, String desTurno,String persona, String cpersonal, String personal, String desPersona,int matriculados,int aprobados,int desaprobados)
        {
            this.institucion = institucion;
            this.desperiodo = desperiodo;
            this.descarrera = descarrera;
            this.desCiclo = desCiclo;
            this.curso = curso;
            this.desCurso = desCurso;
            this.seccion = seccion;
            this.desTurno = desTurno;
            this.persona = persona;
            this.cpersonal = cpersonal;
            this.personal = personal;
            this.desPersona = desPersona;
            this.matriculados=matriculados;
            this.aprobados=aprobados;
            this.desaprobados=desaprobados;
            
            detalleL=new ArrayList<>();
        }

        
       
        
        
        
        public cabecera(String desPersona) {
            this.desPersona=desPersona;
            detalleL=new ArrayList<>();
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
         * @return the desperiodo
         */
        public String getDesperiodo() {
            return desperiodo;
        }

        /**
         * @param desperiodo the desperiodo to set
         */
        public void setDesperiodo(String desperiodo) {
            this.desperiodo = desperiodo;
        }

        /**
         * @return the descarrera
         */
        public String getDescarrera() {
            return descarrera;
        }

        /**
         * @param descarrera the descarrera to set
         */
        public void setDescarrera(String descarrera) {
            this.descarrera = descarrera;
        }

        /**
         * @return the desCiclo
         */
        public String getDesCiclo() {
            return desCiclo;
        }

        /**
         * @param desCiclo the desCiclo to set
         */
        public void setDesCiclo(String desCiclo) {
            this.desCiclo = desCiclo;
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
         * @return the desTurno
         */
        public String getDesTurno() {
            return desTurno;
        }

        /**
         * @param desTurno the desTurno to set
         */
        public void setDesTurno(String desTurno) {
            this.desTurno = desTurno;
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
         * @return the detalleL
         */
        public List<detalle> getDetalleL() {
            return detalleL;
        }

        /**
         * @param detalleL the detalleL to set
         */
        public void setDetalleL(List<detalle> detalleL) {
            this.detalleL = detalleL;
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
         * @return the matriculados
         */
        public int getMatriculados() {
            return matriculados;
        }

        /**
         * @param matriculados the matriculados to set
         */
        public void setMatriculados(int matriculados) {
            this.matriculados = matriculados;
        }

        /**
         * @return the aprobados
         */
        public int getAprobados() {
            return aprobados;
        }

        /**
         * @param aprobados the aprobados to set
         */
        public void setAprobados(int aprobados) {
            this.aprobados = aprobados;
        }

        /**
         * @return the desaprobados
         */
        public int getDesaprobados() {
            return desaprobados;
        }

        /**
         * @param desaprobados the desaprobados to set
         */
        public void setDesaprobados(int desaprobados) {
            this.desaprobados = desaprobados;
        }

        /**
         * @return the totalPresente
         */
        public int getTotalPresente() {
            return totalPresente;
        }

        /**
         * @param totalPresente the totalPresente to set
         */
        public void setTotalPresente(int totalPresente) {
            this.totalPresente = totalPresente;
        }

        /**
         * @return the totalFaltas
         */
        public int getTotalFaltas() {
            return totalFaltas;
        }

        /**
         * @param totalFaltas the totalFaltas to set
         */
        public void setTotalFaltas(int totalFaltas) {
            this.totalFaltas = totalFaltas;
        }
    }
    
    public static class detalle{
        private String codigo;
        private String alumno;
        private int nota;
        private String estadoNota;
        private String presente;
        private String falta;

        public detalle() {
        }

        public detalle(String codigo, String alumno, int nota, String estadoNota, String presente, String falta) {
            this.codigo = codigo;
            this.alumno = alumno;
            this.nota = nota;
            this.estadoNota = estadoNota;
            this.presente = presente;
            this.falta = falta;
          
        }

       
        

        /**
         * @return the codigo
         */
        public String getCodigo() {
            return codigo;
        }

        /**
         * @param codigo the codigo to set
         */
        public void setCodigo(String codigo) {
            this.codigo = codigo;
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
         * @return the nota
         */
        public int getNota() {
            return nota;
        }

        /**
         * @param nota the nota to set
         */
        public void setNota(int nota) {
            this.nota = nota;
        }

        /**
         * @return the estadoNota
         */
        public String getEstadoNota() {
            return estadoNota;
        }

        /**
         * @param estadoNota the estadoNota to set
         */
        public void setEstadoNota(String estadoNota) {
            this.estadoNota = estadoNota;
        }

        /**
         * @return the presente
         */
        public String getPresente() {
            return presente;
        }

        /**
         * @param presente the presente to set
         */
        public void setPresente(String presente) {
            this.presente = presente;
        }

        /**
         * @return the falta
         */
        public String getFalta() {
            return falta;
        }

        /**
         * @param falta the falta to set
         */
        public void setFalta(String falta) {
            this.falta = falta;
        }

     
        
    }
}
