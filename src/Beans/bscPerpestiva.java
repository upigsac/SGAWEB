/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;


import DAO.bscIndicadorDAO;
import DAO.bscObjetivoDAO;
import DAO.bscPerpestivaObjetivoDAO;
import DAO.bscPerpestivaObjetivoIndicadorDetalleDAO;

import ENTIDAD.bscIndicadorC;
import ENTIDAD.bscObjetivoC;
import ENTIDAD.bscPerpestivaObjetivoC;
import ENTIDAD.bscPerpestivaObjetivoIndicadorDetalleC;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Administrador
 */
@ManagedBean(name="bscPerpestivaB")
@ViewScoped
public class bscPerpestiva {
    private BarChartModel barModel=new BarChartModel();
    private List<bscPerpestivaObjetivoIndicadorDetalleC> perpestivaObjectivoIndicadorDetalleL=new ArrayList<>();
    private bscIndicadorC bscIndicadorS=new bscIndicadorC();
    private bscObjetivoC bscObjetivoS;
    private bscPerpestivaObjetivoC bscPerpestivaObjetivo;
    private boolean bandera;
    
    
    
    @ManagedProperty(value="#{bscB}")
    private bsc bscS;
    
    
    @PostConstruct
    public void init() {
      bscObjetivoD=new bscObjetivoDAO();
        bscIndicadorD=new bscIndicadorDAO();
        objectivoIndicadoresL=new ArrayList<>();
         objectivoIndicadores itemObjectivoIndicadores ;
          for (bscObjetivoC itemObjectivo : bscObjetivoD.mostrarObjetivo(bscS.getPerspetiva(),bscS.getAnio())) {
              itemObjectivoIndicadores=new objectivoIndicadores(itemObjectivo);            
              for (bscIndicadorC  itemIndicador: bscIndicadorD.mostrarIndicador(bscS.getPerspetiva(), itemObjectivo.getObjetivo(),bscS.getAnio())) {
                  itemObjectivoIndicadores.bscIndicadorL.add(itemIndicador);
              }
             objectivoIndicadoresL.add(itemObjectivoIndicadores); 
          }
      
    }
    
            
    
    private List<objectivoIndicadores> objectivoIndicadoresL=new ArrayList<>();
    public bscPerpestiva() {
       
    }
    
    
    public void seleccionIndicador(bscObjetivoC itemObjetivo,bscIndicadorC itemIndicador){
        bscObjetivoS=itemObjetivo;
        
        bscIndicadorS=itemIndicador;
        
        bscPerpestivaObjetivoD=new bscPerpestivaObjetivoDAO();
        bscPerpestivaObjetivo=bscPerpestivaObjetivoD.mostrarPerpestivaObjetivo(bscS.getPerspetiva(), bscObjetivoS.getObjetivo());
        createBarModel();
        
    }
    public void insertarPerpestivaObjetivo(){
        bscPerpestivaObjetivoD=new bscPerpestivaObjetivoDAO();
        bscPerpestivaObjetivoD.insertar(bscPerpestivaObjetivo);
      bandera=false;
     
    }
    public void editarPerpestivaObjetivo(){
       bandera=true;  
    }
    
       public void cancelarPerpestivaObjetivo(){
       bandera=false;  
    }
    
      bscPerpestivaObjetivoIndicadorDetalleDAO perpestivaObjectivoIndicadorDetalleD;
      bscObjetivoDAO bscObjetivoD;
      bscIndicadorDAO bscIndicadorD;
      bscPerpestivaObjetivoDAO bscPerpestivaObjetivoD;
      
      private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        
        perpestivaObjectivoIndicadorDetalleD=new bscPerpestivaObjetivoIndicadorDetalleDAO();
        perpestivaObjectivoIndicadorDetalleL=perpestivaObjectivoIndicadorDetalleD.mostrarPerpestivaObjetivoDetalle(bscS.getPerspetiva(), bscObjetivoS.getObjetivo(), bscIndicadorS.getIndicador(),bscS.getAnio());        
          
         ChartSeries boys = new ChartSeries();
        boys.setLabel("ACTUAL");
        
        ChartSeries girls = new ChartSeries();
        girls.setLabel("META");
        
          for (bscPerpestivaObjetivoIndicadorDetalleC item : perpestivaObjectivoIndicadorDetalleL){
              
              boys.set(util.listaMeses.get(item.getMes()-1), item.getActual());
              girls.set(util.listaMeses.get(item.getMes()-1), item.getMeta());
          }
 
 
       
        model.addSeries(boys);
        model.addSeries(girls);
         
        return model;
    }

    /**
     * @return the objectivoIndicadoresL
     */
    public List<objectivoIndicadores> getObjectivoIndicadoresL() {
        return objectivoIndicadoresL;
    }

    /**
     * @param objectivoIndicadoresL the objectivoIndicadoresL to set
     */
    public void setObjectivoIndicadoresL(List<objectivoIndicadores> objectivoIndicadoresL) {
        this.objectivoIndicadoresL = objectivoIndicadoresL;
    }



    /**
     * @return the bscIndicadorS
     */
    public bscIndicadorC getBscIndicadorS() {
        return bscIndicadorS;
    }

    /**
     * @param bscIndicadorS the bscIndicadorS to set
     */
    public void setBscIndicadorS(bscIndicadorC bscIndicadorS) {
        this.bscIndicadorS = bscIndicadorS;
    }

    /**
     * @return the bscObjetivoS
     */
    public bscObjetivoC getBscObjetivoS() {
        return bscObjetivoS;
    }

    /**
     * @param bscObjetivoS the bscObjetivoS to set
     */
    public void setBscObjetivoS(bscObjetivoC bscObjetivoS) {
        this.bscObjetivoS = bscObjetivoS;
    }

    /**
     * @return the bscPerpestivaObjetivo
     */
    public bscPerpestivaObjetivoC getBscPerpestivaObjetivo() {
        return bscPerpestivaObjetivo;
    }

    /**
     * @param bscPerpestivaObjetivo the bscPerpestivaObjetivo to set
     */
    public void setBscPerpestivaObjetivo(bscPerpestivaObjetivoC bscPerpestivaObjetivo) {
        this.bscPerpestivaObjetivo = bscPerpestivaObjetivo;
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
     * @return the perpestivaObjectivoIndicadorDetalleL
     */
    public List<bscPerpestivaObjetivoIndicadorDetalleC> getPerpestivaObjectivoIndicadorDetalleL() {
        return perpestivaObjectivoIndicadorDetalleL;
    }

    /**
     * @param perpestivaObjectivoIndicadorDetalleL the perpestivaObjectivoIndicadorDetalleL to set
     */
    public void setPerpestivaObjectivoIndicadorDetalleL(List<bscPerpestivaObjetivoIndicadorDetalleC> perpestivaObjectivoIndicadorDetalleL) {
        this.perpestivaObjectivoIndicadorDetalleL = perpestivaObjectivoIndicadorDetalleL;
    }

    /**
     * @return the bscS
     */
    public bsc getBscS() {
        return bscS;
    }

    /**
     * @param bscS the bscS to set
     */
    public void setBscS(bsc bscS) {
        this.bscS = bscS;
    }
      
      
    public class objectivoIndicadores{
        private bscObjetivoC bscObjetivo;
        private List<bscIndicadorC> bscIndicadorL=new ArrayList<>();

        public objectivoIndicadores() {
        }

        public objectivoIndicadores(bscObjetivoC bscObjetivo) {
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
    
    
    
    private void createBarModel() {
        barModel=new BarChartModel();        
        barModel = initBarModel();         
        
        barModel.setLegendPosition("ne");
         barModel.setAnimate(true);
       // Axis xAxis = barModel.getAxis(AxisType.X);
    //    xAxis.setLabel("MESES");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
   //     yAxis.setLabel("VALORES");
        yAxis.setMin(0);
        yAxis.setMax(100);
        yAxis.setTickInterval("25");
        
    }

    /**
     * @return the barModel
     */
    public BarChartModel getBarModel() {
        return barModel;
    }

    /**
     * @param barModel the barModel to set
     */
    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

 



  
}
