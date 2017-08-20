/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.bscIndicadorDAO;
import DAO.bscObjetivoDAO;
import DAO.bscPerpestivaDAO;
import DAO.bscSemaforoDAO;
import ENTIDAD.bscIndicadorC;
import ENTIDAD.bscObjetivoC;
import ENTIDAD.bscPerpestivaC;
import ENTIDAD.bscSemaforoC;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.MeterGaugeChartModel;

/**
 *
 * @author Administrador
 */
@ManagedBean(name="bscBalanceB")
@ViewScoped
public class bscBalance {
    
    private MeterGaugeChartModel meterGaugeModel2;
    private List<perpestivaObjectivoIndicador> perpestivaObjectivoIndicadorL=new ArrayList<>();
    private List<bscSemaforoC> bscSemaforoL;
    private int anio=2016;
    private int mes=1;
    
    
    bscPerpestivaDAO bscPerpestivaD;
    bscObjetivoDAO bscObjetivoD;
    bscIndicadorDAO bscIndicadorD;
    bscSemaforoDAO bscSemaforoD;
    public bscBalance() {
           bscSemaforoD=new bscSemaforoDAO();
           bscSemaforoL=bscSemaforoD.mostrarSemaforo();
           mostrarCuadros();
        
    }
    
    public void mostrarCuadros(){
        bscPerpestivaD=new bscPerpestivaDAO();
        bscObjetivoD=new bscObjetivoDAO();
        bscIndicadorD=new bscIndicadorDAO();
        
        perpestivaObjectivoIndicadorL=new ArrayList<>();
        perpestivaObjectivoIndicador itemPerpestivaObjectivoIndicador;
        for (bscPerpestivaC itemPerpestiva : bscPerpestivaD.mostrarPerpestiva(anio,mes)) {
            
            itemPerpestivaObjectivoIndicador=new perpestivaObjectivoIndicador(itemPerpestiva);
            
            objetivoIndicadorC itemObjectivoIndicador;
             for (bscObjetivoC itemObjetivo : bscObjetivoD.mostrarObjetivo(itemPerpestiva.getPerpestiva(),anio,mes)) {
                 itemObjectivoIndicador=new objetivoIndicadorC(itemObjetivo);
                 for (bscIndicadorC itemIndicador : bscIndicadorD.mostrarIndicador(itemPerpestiva.getPerpestiva(), itemObjetivo.getObjetivo(),anio,mes)) {
                     itemObjectivoIndicador.bscIndicadorL.add(itemIndicador);
                 }
                 
                 itemPerpestivaObjectivoIndicador.objetivoIndicadorL.add(itemObjectivoIndicador);
                 
             }
             perpestivaObjectivoIndicadorL.add(itemPerpestivaObjectivoIndicador);
        }
    }
    
    
    
    public void mostrarPerpestiva(int perpestiva){        
        bscObjetivoD=new bscObjetivoDAO();
        bscIndicadorD=new bscIndicadorDAO();
    
    }
    
     
    public MeterGaugeChartModel crearCuadro(int valor) {
  
         
        meterGaugeModel2 = initMeterGaugeModel(valor);       
        meterGaugeModel2.setSeriesColors(colores());
        
        meterGaugeModel2.setGaugeLabelPosition("top");
        meterGaugeModel2.setShowTickLabels(false);
        
        meterGaugeModel2.setLabelHeightAdjust(40);
        meterGaugeModel2.setIntervalOuterRadius(30);
        return meterGaugeModel2;
    }
    
    
    private String colores(){
        String cadena="";
         for (bscSemaforoC itemSemaforo : bscSemaforoL) {
               cadena+=itemSemaforo.getColor()+",";
            }
         return cadena;
    }
    
    private MeterGaugeChartModel initMeterGaugeModel(int valor) {
        List<Number> intervals = new ArrayList<>();
            for (bscSemaforoC itemSemaforo : bscSemaforoL) {
                
                intervals.add(itemSemaforo.getHasta());
            }
        return new MeterGaugeChartModel(valor, intervals);
    }

    /**
     * @return the perpestivaObjectivoIndicadorL
     */
    public List<perpestivaObjectivoIndicador> getPerpestivaObjectivoIndicadorL() {
        return perpestivaObjectivoIndicadorL;
    }

    /**
     * @param perpestivaObjectivoIndicadorL the perpestivaObjectivoIndicadorL to set
     */
    public void setPerpestivaObjectivoIndicadorL(List<perpestivaObjectivoIndicador> perpestivaObjectivoIndicadorL) {
        this.perpestivaObjectivoIndicadorL = perpestivaObjectivoIndicadorL;
    }

    /**
     * @return the anio
     */
    public int getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    /**
     * @return the mes
     */
    public int getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * @return the bscSemaforoL
     */
    public List<bscSemaforoC> getBscSemaforoL() {
        return bscSemaforoL;
    }

    /**
     * @param bscSemaforoL the bscSemaforoL to set
     */
    public void setBscSemaforoL(List<bscSemaforoC> bscSemaforoL) {
        this.bscSemaforoL = bscSemaforoL;
    }

  

        public class perpestivaObjectivoIndicador{
            private bscPerpestivaC perpestiva;
            private List<objetivoIndicadorC> objetivoIndicadorL=new ArrayList<>();

        public perpestivaObjectivoIndicador() {
        }

        public perpestivaObjectivoIndicador(bscPerpestivaC perpestiva) {
            this.perpestiva = perpestiva;
        }
            
            

        /**
         * @return the perpestiva
         */
        public bscPerpestivaC getPerpestiva() {
            return perpestiva;
        }

        /**
         * @param perpestiva the perpestiva to set
         */
        public void setPerpestiva(bscPerpestivaC perpestiva) {
            this.perpestiva = perpestiva;
        }

        /**
         * @return the objectivoIndicadorL
         */
        public List<objetivoIndicadorC> getObjetivoIndicadorL() {
            return objetivoIndicadorL;
        }

        /**
         * @param objectivoIndicadorL the objectivoIndicadorL to set
         */
        public void setObjetivoIndicadorL(List<objetivoIndicadorC> objectivoIndicadorL) {
            this.objetivoIndicadorL = objectivoIndicadorL;
        }
        }
        
        
        
       public class objetivoIndicadorC{
            private bscObjetivoC bscObjetivo;
            private List<bscIndicadorC> bscIndicadorL=new ArrayList<>();

        public objetivoIndicadorC() {
        }

        public objetivoIndicadorC(bscObjetivoC bscObjetivo) {
            this.bscObjetivo = bscObjetivo;
        }

      

        /**
         * @return the bscObjetivo
         */
        public bscObjetivoC getBscObjetivo() {
            return bscObjetivo;
        }

        /**
         * @param bscObjetivo the bscObjetivo to set
         */
        public void setBscObjetivo(bscObjetivoC bscObjetivo) {
            this.bscObjetivo = bscObjetivo;
        }

        /**
         * @return the bscIndicadorL
         */
        public List<bscIndicadorC> getBscIndicadorL() {
            return bscIndicadorL;
        }

        /**
         * @param bscIndicadorL the bscIndicadorL to set
         */
        public void setBscIndicadorL(List<bscIndicadorC> bscIndicadorL) {
            this.bscIndicadorL = bscIndicadorL;
        }
            
         }

 

    /**
     * @return the meterGaugeModel2
     */
    public MeterGaugeChartModel getMeterGaugeModel2() {
        return meterGaugeModel2;
    }

    /**
     * @param meterGaugeModel2 the meterGaugeModel2 to set
     */
    public void setMeterGaugeModel2(MeterGaugeChartModel meterGaugeModel2) {
        this.meterGaugeModel2 = meterGaugeModel2;
    }
}
