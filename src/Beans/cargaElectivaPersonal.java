/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.cursoSeccionDocenteDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Administrador
 */
@ManagedBean(name="cargaElectivaPersonalB")
@ViewScoped
public class cargaElectivaPersonal {
    private List<detalleCargaElectiva> detalleCargaElectivaL;
    private String filtroCarrera="%";
    private String filtroPersona="%";
    
    
    
    cursoSeccionDocenteDAO cursoSeccionDocenteD;
    public int totalCreditos(String persona){
        int total=0;
        for (detalleCargaElectiva item : detalleCargaElectivaL) {
            if (item.persona.endsWith(persona)){
                total+=item.credito;
            }
        }
        return total;
    }
    
    
      public int totalHoras(String persona){
        int horas=0;
        for (detalleCargaElectiva item : detalleCargaElectivaL) {
            if (item.persona.endsWith(persona)){
                horas+=item.totalHoras;
            }
        }
        return horas;
    }
    
    public void mostrarDetalle(int institucion,String periodo,String carrera,String persona){
        cursoSeccionDocenteD=new cursoSeccionDocenteDAO();
        detalleCargaElectivaL=cursoSeccionDocenteD.mostrarCargaElectiva(institucion, periodo, carrera,persona);
    }

    /**
     * @return the detalleCargaElectivaL
     */
    public List<detalleCargaElectiva> getDetalleCargaElectivaL() {
        return detalleCargaElectivaL;
    }

    /**
     * @param detalleCargaElectivaL the detalleCargaElectivaL to set
     */
    public void setDetalleCargaElectivaL(List<detalleCargaElectiva> detalleCargaElectivaL) {
        this.detalleCargaElectivaL = detalleCargaElectivaL;
    }

    /**
     * @return the filtroCarrera
     */
    public String getFiltroCarrera() {
        return filtroCarrera;
    }

    /**
     * @param filtroCarrera the filtroCarrera to set
     */
    public void setFiltroCarrera(String filtroCarrera) {
        this.filtroCarrera = filtroCarrera;
    }

    /**
     * @return the filtroPersona
     */
    public String getFiltroPersona() {
        return filtroPersona;
    }

    /**
     * @param filtroPersona the filtroPersona to set
     */
    public void setFiltroPersona(String filtroPersona) {
        this.filtroPersona = filtroPersona;
    }
    
    
    
    public static class detalleCargaElectiva{
        private int institucion;
        private String periodo;
        private String carrera;
        private String desCarrera;
        private String seccion;
        private String desSeccion;
        private int turno;
        private String desTurno;
        private String curso;
        private int credito;
        private String personal;
        private String cpersonal;
        private String persona;
        private String desPersona;
        private int nivelModular;
        private int tipoCurso;
        private String desTipoCurso;
        private int horasPractica;
        private int horasTeoria;
        private int totalHoras;
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
         * @return the credito
         */
        public int getCredito() {
            return credito;
        }

        /**
         * @param credito the credito to set
         */
        public void setCredito(int credito) {
            this.credito = credito;
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
         * @return the tipoCurso
         */
        public int getTipoCurso() {
            return tipoCurso;
        }

        /**
         * @param tipoCurso the tipoCurso to set
         */
        public void setTipoCurso(int tipoCurso) {
            this.tipoCurso = tipoCurso;
        }

        /**
         * @return the desTipoCurso
         */
        public String getDesTipoCurso() {
            return desTipoCurso;
        }

        /**
         * @param desTipoCurso the desTipoCurso to set
         */
        public void setDesTipoCurso(String desTipoCurso) {
            this.desTipoCurso = desTipoCurso;
        }

        /**
         * @return the horasPractica
         */
        public int getHorasPractica() {
            return horasPractica;
        }

        /**
         * @param horasPractica the horasPractica to set
         */
        public void setHorasPractica(int horasPractica) {
            this.horasPractica = horasPractica;
        }

        /**
         * @return the horasTeoria
         */
        public int getHorasTeoria() {
            return horasTeoria;
        }

        /**
         * @param horasTeoria the horasTeoria to set
         */
        public void setHorasTeoria(int horasTeoria) {
            this.horasTeoria = horasTeoria;
        }

        /**
         * @return the totalHoras
         */
        public int getTotalHoras() {
            return totalHoras;
        }

        /**
         * @param totalHoras the totalHoras to set
         */
        public void setTotalHoras(int totalHoras) {
            this.totalHoras = totalHoras;
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
