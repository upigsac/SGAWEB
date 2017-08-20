/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.asistenciaInstitucionDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Administrador
 */
@ManagedBean(name="asistenciaInstitucionB")
@ViewScoped
public class asistenciaInstitucion {
    
    
    private List<detalle> detalleL=new ArrayList<>();
    private Date fechaInicio=util.FechaHoraHoy();
    private Date fechaFinal=util.FechaHoraHoy();
    
    
    
    
    asistenciaInstitucionDAO asistenciaInstitucionD;

    public asistenciaInstitucion() {
        
        asistenciaInstitucionD=new asistenciaInstitucionDAO();
        detalleL=asistenciaInstitucionD.mostrarAsistenciaInstitucion("00000000000000017313", fechaInicio, fechaFinal);
    }
    
    public void mostra(){
        asistenciaInstitucionD=new asistenciaInstitucionDAO();
        detalleL=asistenciaInstitucionD.mostrarAsistenciaInstitucion("00000000000000017313", fechaInicio, fechaFinal);
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
    
    
    
    
    
    public static class detalle{
        private int local;
        private String desLocal;
        private Date fecha;
        private String entrada;
        private String salida;
        private String duracion;

        /**
         * @return the local
         */
        public int getLocal() {
            return local;
        }

        /**
         * @param local the local to set
         */
        public void setLocal(int local) {
            this.local = local;
        }

        /**
         * @return the desLocal
         */
        public String getDesLocal() {
            return desLocal;
        }

        /**
         * @param desLocal the desLocal to set
         */
        public void setDesLocal(String desLocal) {
            this.desLocal = desLocal;
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
         * @return the entrada
         */
        public String getEntrada() {
            return entrada;
        }

        /**
         * @param entrada the entrada to set
         */
        public void setEntrada(String entrada) {
            this.entrada = entrada;
        }

        /**
         * @return the salida
         */
        public String getSalida() {
            return salida;
        }

        /**
         * @param salida the salida to set
         */
        public void setSalida(String salida) {
            this.salida = salida;
        }

        /**
         * @return the duracion
         */
        public String getDuracion() {
            return duracion;
        }

        /**
         * @param duracion the duracion to set
         */
        public void setDuracion(String duracion) {
            this.duracion = duracion;
        }
                
    }
}
