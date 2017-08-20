/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.cronogramaPagoDAO;
import ENTIDAD.cronogramaPagoC;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Administrador
 */
@ManagedBean(name="cronogramaB")
@ViewScoped
public class cronograma {
    private List<cronogramaPagoC> cronogramaPagoL=new ArrayList<>();
    
    private List<vencimiento> vencimientoCronogramaL=new ArrayList<>();

    public cronograma() {
        util.consolaCliente("entre");
    }
    
    
    cronogramaPagoDAO cronogramaPagoD;
    public List<cronogramaPagoC> mostrarCronogramaPago(int institucion,String periodo,int vencimiento,String carrera,String ciclo){
        cronogramaPagoD=new cronogramaPagoDAO();
        cronogramaPagoL=cronogramaPagoD.mostrarCronogramaPago(institucion, periodo,vencimiento,carrera,ciclo);
        return cronogramaPagoL;
        
    }
    
    public List<vencimiento> mostrarCronogramaVencimineto(int institucion,String periodo,int vencimiento){
        cronogramaPagoD=new cronogramaPagoDAO();
        vencimientoCronogramaL=cronogramaPagoD.mostrarCronogramaVencimiento(institucion, periodo,vencimiento);
        return vencimientoCronogramaL;
        
    }

    /**
     * @return the vencimientoCronogramaL
     */
    public List<vencimiento> getVencimientoCronogramaL() {
        return vencimientoCronogramaL;
    }

    /**
     * @param vencimientoCronogramaL the vencimientoCronogramaL to set
     */
    public void setVencimientoCronogramaL(List<vencimiento> vencimientoCronogramaL) {
        this.vencimientoCronogramaL = vencimientoCronogramaL;
    }
    
    public static class vencimiento{
        private int vencimiento;
        private Date fecha;
        private String concepto;
        private String desConcepto;
        private int cuota;

        /**
         * @return the vencimiento
         */
        public int getVencimiento() {
            return vencimiento;
        }

        /**
         * @param vencimiento the vencimiento to set
         */
        public void setVencimiento(int vencimiento) {
            this.vencimiento = vencimiento;
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
         * @return the concepto
         */
        public String getConcepto() {
            return concepto;
        }

        /**
         * @param concepto the concepto to set
         */
        public void setConcepto(String concepto) {
            this.concepto = concepto;
        }

        /**
         * @return the desConcepto
         */
        public String getDesConcepto() {
            return desConcepto;
        }

        /**
         * @param desConcepto the desConcepto to set
         */
        public void setDesConcepto(String desConcepto) {
            this.desConcepto = desConcepto;
        }

        /**
         * @return the cuota
         */
        public int getCuota() {
            return cuota;
        }

        /**
         * @param cuota the cuota to set
         */
        public void setCuota(int cuota) {
            this.cuota = cuota;
        }
    }
    
    

    /**
     * @return the cronogramaPagoL
     */
    public List<cronogramaPagoC> getCronogramaPagoL() {
        return cronogramaPagoL;
    }

    /**
     * @param cronogramaPagoL the cronogramaPagoL to set
     */
    public void setCronogramaPagoL(List<cronogramaPagoC> cronogramaPagoL) {
        this.cronogramaPagoL = cronogramaPagoL;
    }
}
