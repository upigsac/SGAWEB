/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.tramTramitePersonaDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Administrador
 */
@ManagedBean(name="mesaParteB")
@ViewScoped
public class mesaParte {
    private Date fechaInicio=util.dateAdd(util.fechaHoy(), 2, -1);
    private Date fechaFinal=util.fechaHoy();
    private List<detalleTramite> detalleTramiteL=new ArrayList<>();
    private String filtroOficina="%";
    private String busPersonaEmpresa;

    public mesaParte() {
        
        tramTramitePersonaD=new tramTramitePersonaDAO();
        detalleTramiteL=tramTramitePersonaD.filtroMesaParte(fechaInicio, fechaFinal, filtroOficina,"%");
    }

    public void mostarFiltro(){
        util.consolaCliente(filtroOficina);
        tramTramitePersonaD=new tramTramitePersonaDAO();
        detalleTramiteL=tramTramitePersonaD.filtroMesaParte(fechaInicio, fechaFinal, filtroOficina,"%");
    }
    
    tramTramitePersonaDAO tramTramitePersonaD;
    /**
     * @return the detalleTramiteL
     */
    public List<detalleTramite> getDetalleTramiteL() {
        return detalleTramiteL;
    }

    /**
     * @param detalleTramiteL the detalleTramiteL to set
     */
    public void setDetalleTramiteL(List<detalleTramite> detalleTramiteL) {
        this.detalleTramiteL = detalleTramiteL;
    }
    
    
    
    public void mostraDetalleTramite(){
        
    }

    /**
     * @return the busPersonaEmpresa
     */
    public String getBusPersonaEmpresa() {
        return busPersonaEmpresa;
    }

    /**
     * @param busPersonaEmpresa the busPersonaEmpresa to set
     */
    public void setBusPersonaEmpresa(String busPersonaEmpresa) {
        this.busPersonaEmpresa = busPersonaEmpresa;
    }
    
    
    public static class detalleTramite{
        private String expediente;
        private String fut;
        private String desTipoTramite;
        private String persona;
        private String desPersona;
        private String desTramite;
        private String estadoTramite;
        private String desEstadoTramite;
        private String desOficinaActual;
        private String observacion;
        private Date fechaIngreso;
        private Date fechaEntrega;
        private Date fechaFin;
        private int numeroDias;
        private Date horaIngreso;

        /**
         * @return the expediente
         */
        public String getExpediente() {
            return expediente;
        }

        /**
         * @param expediente the expediente to set
         */
        public void setExpediente(String expediente) {
            this.expediente = expediente;
        }

        /**
         * @return the fut
         */
        public String getFut() {
            return fut;
        }

        /**
         * @param fut the fut to set
         */
        public void setFut(String fut) {
            this.fut = fut;
        }

        /**
         * @return the desTipoTramite
         */
        public String getDesTipoTramite() {
            return desTipoTramite;
        }

        /**
         * @param desTipoTramite the desTipoTramite to set
         */
        public void setDesTipoTramite(String desTipoTramite) {
            this.desTipoTramite = desTipoTramite;
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
         * @return the desTramite
         */
        public String getDesTramite() {
            return desTramite;
        }

        /**
         * @param desTramite the desTramite to set
         */
        public void setDesTramite(String desTramite) {
            this.desTramite = desTramite;
        }

        /**
         * @return the estadoTramite
         */
        public String getEstadoTramite() {
            return estadoTramite;
        }

        /**
         * @param estadoTramite the estadoTramite to set
         */
        public void setEstadoTramite(String estadoTramite) {
            this.estadoTramite = estadoTramite;
        }

        /**
         * @return the desOficinaActual
         */
        public String getDesOficinaActual() {
            return desOficinaActual;
        }

        /**
         * @param desOficinaActual the desOficinaActual to set
         */
        public void setDesOficinaActual(String desOficinaActual) {
            this.desOficinaActual = desOficinaActual;
        }

     

        /**
         * @return the fechaIngreso
         */
        public Date getFechaIngreso() {
            return fechaIngreso;
        }

        /**
         * @param fechaIngreso the fechaIngreso to set
         */
        public void setFechaIngreso(Date fechaIngreso) {
            this.fechaIngreso = fechaIngreso;
        }

        /**
         * @return the horaIngreso
         */
        public Date getHoraIngreso() {
            return horaIngreso;
        }

        /**
         * @param horaIngreso the horaIngreso to set
         */
        public void setHoraIngreso(Date horaIngreso) {
            this.horaIngreso = horaIngreso;
        }

        /**
         * @return the desEstadoTramite
         */
        public String getDesEstadoTramite() {
            return desEstadoTramite;
        }

        /**
         * @param desEstadoTramite the desEstadoTramite to set
         */
        public void setDesEstadoTramite(String desEstadoTramite) {
            this.desEstadoTramite = desEstadoTramite;
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
         * @return the fechaEntrega
         */
        public Date getFechaEntrega() {
            return fechaEntrega;
        }

        /**
         * @param fechaEntrega the fechaEntrega to set
         */
        public void setFechaEntrega(Date fechaEntrega) {
            this.fechaEntrega = fechaEntrega;
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
         * @return the numeroDias
         */
        public int getNumeroDias() {
            return numeroDias;
        }

        /**
         * @param numeroDias the numeroDias to set
         */
        public void setNumeroDias(int numeroDias) {
            this.numeroDias = numeroDias;
        }
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

    /**
     * @return the filtroOficina
     */
    public String getFiltroOficina() {
        return filtroOficina;
    }

    /**
     * @param filtroOficina the filtroOficina to set
     */
    public void setFiltroOficina(String filtroOficina) {
        this.filtroOficina = filtroOficina;
    }
}
