/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.bscIndicadorDAO;
import DAO.bscObjetivoDAO;
import DAO.bscPerpestivaDAO;
import DAO.bscPerpestivaObjetivoDAO;
import DAO.bscPerpestivaObjetivoIndicadorDAO;
import DAO.bscPerpestivaObjetivoIndicadorDetalleDAO;
import ENTIDAD.bscIndicadorC;
import ENTIDAD.bscObjetivoC;
import ENTIDAD.bscPerpestivaC;
import ENTIDAD.bscPerpestivaObjetivoC;
import ENTIDAD.bscPerpestivaObjetivoIndicadorC;
import ENTIDAD.bscPerpestivaObjetivoIndicadorDetalleC;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



@ManagedBean(name="bscDatoB")
@ViewScoped


public class bscDato implements Serializable{
	
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private bscObjetivoC bscObjetivo;
    private List<bscObjetivoC> bscObjetivoL;
    private bscIndicadorC bscIndicador;
    private List<bscIndicadorC> bscIndicadorL=new ArrayList<>();
    private bscIndicadorC bscIndicadorS;
    private bscPerpestivaC bscPerpestivaS;
    private List<perpestivaObjetivo> perpestivaObjetivoLL;
    private perpestivaObjetivo perpestivaObjetivo;
    
    private bscPerpestivaObjetivoC bscPerpestivaObjetivo;
    private bscPerpestivaObjetivoIndicadorC bscPerpestivaObjetivoIndicador;
    
    private bscObjetivoC bscObjetivoS;
    private int anio=2016;
    private boolean  bandera;
    private boolean banderaObjetivo;
    private boolean banderaIndicador;
    private String filtroObjetivo;
    
    
   
 
    
  
 
  

    public bscDato() {
     
        mostrarDatos();
        
        bscIndicadorL=new bscIndicadorDAO().mostrarIndicador();
        
        
    }
    public void seleccionPerpestiva(perpestivaObjetivo itemPerpestiva){
        perpestivaObjetivo=itemPerpestiva;
    }
            
    
    public void seleccionPerfil(boolean  item){
        bandera=item;
    }
    
    public void mostrarFiltroDatos(){
       
        
     
       
        perpestivaObjetivoLL=new ArrayList<>();
        perpestivaObjetivo itemPerpestivaObjectivo;
        for (bscPerpestivaC itemPerpestiva : new bscPerpestivaDAO().mostrarPerpestiva()) {
            itemPerpestivaObjectivo=new perpestivaObjetivo(itemPerpestiva);
            objetivoIndicador itemObjectivoIndicador;
            
            
            for (bscObjetivoC itemObjectivo : new bscObjetivoDAO().mostrarFiltroObjetivo(itemPerpestiva.getPerpestiva(),filtroObjetivo)) {
                itemObjectivoIndicador=new objetivoIndicador(itemObjectivo);
                
                for (bscIndicadorC itemIndicador : new bscIndicadorDAO().mostrarIndicador(itemPerpestiva.getPerpestiva(), itemObjectivo.getObjetivo(),"0000000008")) {//                   
                    indicadorMes itemIndicadorMes=new indicadorMes(itemIndicador);                   
                    for (bscPerpestivaObjetivoIndicadorDetalleC itemPerpestivaObjetivoIndicadorDetalle : new bscPerpestivaObjetivoIndicadorDetalleDAO().mostrarPerpestivaObjetivo(itemPerpestiva.getPerpestiva(), itemObjectivo.getObjetivo(), itemIndicador.getIndicador(), anio                    ))
                    {                        
                        itemIndicadorMes.bscPerpestivaObjetivoIndicadorDetalleL.add(itemPerpestivaObjetivoIndicadorDetalle);
                    }                    
                    itemObjectivoIndicador.indicadorMesL.add(itemIndicadorMes);      
                }
                itemPerpestivaObjectivo.objetivoIndicadorL.add(itemObjectivoIndicador);
            }
            perpestivaObjetivoLL.add(itemPerpestivaObjectivo);
        }
    }
    
    
    
    
    public void mostrarDatos(){
     
       
       
   
        perpestivaObjetivoLL=new ArrayList<>();
        perpestivaObjetivo itemPerpestivaObjectivo;
        for (bscPerpestivaC itemPerpestiva : new bscPerpestivaDAO().mostrarPerpestiva()) {
            itemPerpestivaObjectivo=new perpestivaObjetivo(itemPerpestiva);
            objetivoIndicador itemObjectivoIndicador;           
            
            for (bscObjetivoC itemObjectivo : new bscObjetivoDAO().mostrarObjetivo(itemPerpestiva.getPerpestiva())) {
                itemObjectivoIndicador=new objetivoIndicador(itemObjectivo);
                
                for (bscIndicadorC itemIndicador : new bscIndicadorDAO().mostrarIndicador(itemPerpestiva.getPerpestiva(), itemObjectivo.getObjetivo(),"0000000008")) {//                   
                    indicadorMes itemIndicadorMes=new indicadorMes(itemIndicador);                   
                    for (bscPerpestivaObjetivoIndicadorDetalleC itemPerpestivaObjetivoIndicadorDetalle : new bscPerpestivaObjetivoIndicadorDetalleDAO().mostrarPerpestivaObjetivo(itemPerpestiva.getPerpestiva(), itemObjectivo.getObjetivo(), itemIndicador.getIndicador(), anio                    ))
                    {                        
                        itemIndicadorMes.bscPerpestivaObjetivoIndicadorDetalleL.add(itemPerpestivaObjetivoIndicadorDetalle);
                    }                    
                    itemObjectivoIndicador.indicadorMesL.add(itemIndicadorMes);      
                }
                itemPerpestivaObjectivo.objetivoIndicadorL.add(itemObjectivoIndicador);
            }
            perpestivaObjetivoLL.add(itemPerpestivaObjectivo);
        }
    }
    
    public void mostrarDatosPerpestivaObjectivo(int indiceP,int indiceO){
        
               
                
                perpestivaObjetivoLL.get(indiceP).objetivoIndicadorL.get(indiceO).indicadorMesL.clear();
                objetivoIndicador itemObjectivoIndicador;
                
                itemObjectivoIndicador=new objetivoIndicador(perpestivaObjetivoLL.get(indiceP).objetivoIndicadorL.get(indiceO).bscObjetivo);
                
                System.out.println("Perpestiva = " + perpestivaObjetivoLL.get(indiceP).bscPerpestiva.getPerpestiva());
                System.out.println("objetivo = " + itemObjectivoIndicador.bscObjetivo.getObjetivo());
                
                for (bscIndicadorC itemIndicador : new bscIndicadorDAO().mostrarIndicador(perpestivaObjetivoLL.get(indiceP).bscPerpestiva.getPerpestiva(), itemObjectivoIndicador.bscObjetivo.getObjetivo(),"0000000008")) {//                   
                    System.out.println("indicador = " + itemIndicador.getIndicador());
                    indicadorMes itemIndicadorMes=new indicadorMes(itemIndicador); 
                    for (bscPerpestivaObjetivoIndicadorDetalleC itemPerpestivaObjetivoIndicadorDetalle : new bscPerpestivaObjetivoIndicadorDetalleDAO().mostrarPerpestivaObjetivo(perpestivaObjetivoLL.get(indiceP).bscPerpestiva.getPerpestiva(), itemObjectivoIndicador.bscObjetivo.getObjetivo(), itemIndicador.getIndicador(), anio))
                    {                        
                        itemIndicadorMes.bscPerpestivaObjetivoIndicadorDetalleL.add(itemPerpestivaObjetivoIndicadorDetalle);
                    }                    
                    itemObjectivoIndicador.indicadorMesL.add(itemIndicadorMes);    
                    perpestivaObjetivoLL.get(indiceP).objetivoIndicadorL.get(indiceO).indicadorMesL.add(itemIndicadorMes);
                    
                }
                //perpestivaObjetivoLL.get(indiceP).objetivoIndicadorL.get(indiceO).indicadorMesL.clear();
                
                
                
               
             System.out.println("--------------------------------------------------------------------------------------------");
    }
    
    
    public void insertarObjetivo(){
       
        if (banderaObjetivo){
            bscPerpestivaObjetivo.setObjetivo(new bscObjetivoDAO().insertar(bscObjetivo));        
            insertarPerpestivaObjetivo();
        }else{
        	new bscObjetivoDAO().insertar(bscObjetivo);
        }
        
        
        util.script("dlgObjectivo.hide()");
    }
    
      public void insertarIndicador(){
        System.out.println("Se registro correctamente..");  

          if(banderaIndicador){
            bscPerpestivaObjetivoIndicador.setIndicador(new bscIndicadorDAO().insertar(bscIndicador));      
            insertarPerpestivaObjetivoIndicador();        
        }else{
        	new bscIndicadorDAO().insertar(bscIndicador);
           //    mostrarDatos();
        }
        
        util.script("dlgIndicador.hide()");
    }
    
     public void insertarPerpestivaObjetivo(){
     
        new bscPerpestivaObjetivoDAO().insertar(bscPerpestivaObjetivo);
        mostrarDatos();        
    }
     
     
      public void insertarPerpestivaObjetivoIndicador(){
           
        new bscPerpestivaObjetivoIndicadorDAO().insertar(bscPerpestivaObjetivoIndicador);
        mostrarDatos();
        
    }
      
      
      
       public void insertarPerpestivaObjetivoIndicadorDetalle(bscPerpestivaObjetivoIndicadorDetalleC item){
        item.setEstadoRegistro(1);
       
        new bscPerpestivaObjetivoIndicadorDetalleDAO().insertar(item);
        mostrarDatos();
        
    }
    
    public void nuevoObjetivo(){
        bscObjetivo=new bscObjetivoC();
        bscObjetivo.setObjetivo(bscPerpestivaS.getPrefijo());
        bscObjetivo.setEstadoRegistro(1);                
        banderaObjetivo=true;
        util.script("dlgObjectivo.show()");
    }  
    public void nuevoIndicador(){
        bscIndicador=new bscIndicadorC();
        bscIndicador.setIndicador(bscObjetivoS.getObjetivo());
        bscIndicador.setEstadoRegistro(1);
        banderaIndicador=true;
        util.script("dlgIndicador.show()");
        
    }
    
     public void nuevoPerpestivaObjetivo(bscPerpestivaC item){
        bscPerpestivaS=item;
        bscObjetivo=new bscObjetivoC();
        bscPerpestivaObjetivo=new bscPerpestivaObjetivoC(bscPerpestivaS.getPerpestiva(), null, null, null, 1);
        nuevoObjetivo();
    }
     
       public void nuevoPerpestivaObjetivoIndicador(bscPerpestivaC itemP,bscObjetivoC itemO){
        bscPerpestivaS=itemP;
        bscObjetivoS=itemO;
        bscPerpestivaObjetivoIndicador=new bscPerpestivaObjetivoIndicadorC(bscPerpestivaS.getPerpestiva(), bscObjetivoS.getObjetivo(),null, 1);
        nuevoIndicador();
    }
     public void eliminarPerpestivaObjetivo(bscPerpestivaC itemPerpestiva,bscObjetivoC itemObjectivo){
   
        new bscPerpestivaObjetivoDAO().eliminar(new bscPerpestivaObjetivoC(itemPerpestiva.getPerpestiva(), itemObjectivo.getObjetivo(), null, null, 1));
        
       new bscObjetivoDAO().eliminar(itemObjectivo);
        
        
        mostrarDatos();
    }
     public void editarPerpestivaObjectivo(bscPerpestivaC itemPerpestiva,bscObjetivoC itemObjectivo){
         bscPerpestivaS=itemPerpestiva;
         bscObjetivo=itemObjectivo;
         banderaObjetivo=false;
         util.script("dlgObjectivo.show()");
     }
     
    public void eliminarPerpestivaIndicadorObjetivo(bscPerpestivaC itemPerpestiva,bscObjetivoC itemObjectivo,bscIndicadorC itemIndicador,int indiceP,int indiceO){

        new bscPerpestivaObjetivoIndicadorDAO().eliminar(new bscPerpestivaObjetivoIndicadorC(itemPerpestiva.getPerpestiva(), itemObjectivo.getObjetivo(), itemIndicador.getIndicador() , 1));
   
        new bscIndicadorDAO().eliminar(itemIndicador);
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println(indiceP);
        System.out.println(indiceO);
        mostrarDatosPerpestivaObjectivo(indiceP,indiceO);
        
                
        
       // mostrarDatos();
    }
    
    public void editarIndicador(bscPerpestivaC itemPerpestiva,bscObjetivoC itemObjectivo,bscIndicadorC itemIndicador){
        System.out.println("Editar Indicador");
        bscPerpestivaS=itemPerpestiva;
        bscObjetivoS=itemObjectivo;
        bscIndicador=itemIndicador;
        banderaIndicador=false;
        util.script("dlgIndicador.show()");
    }
     
     public void seleccionObjetivo(bscObjetivoC item){
        bscObjetivoS=item;
        bscObjetivo=bscObjetivoS;
        bscPerpestivaObjetivo.setObjetivo(bscObjetivo.getObjetivo());
     }
     
     public void mostrarObjetivo(){
    
        new bscObjetivoDAO().mostrarObjetivo(bscPerpestivaS.getPerpestiva());
    }
    
    public void filtroObjectivo(){
       bscObjetivoS=null;
   
       bscObjetivoL=new bscObjetivoDAO().filtroObjetivo(bscObjetivo.getDescripcion());
    }
    public List<perpestivaObjetivo> getPerpestivaObjetivoLL() {
        return perpestivaObjetivoLL;
    }
    public void setPerpestivaObjetivoLL(List<perpestivaObjetivo> perpestivaObjetivoLL) {
        this.perpestivaObjetivoLL = perpestivaObjetivoLL;
    }
    public bscIndicadorC getBscIndicadorS() {
        return bscIndicadorS;
    }
    public void setBscIndicadorS(bscIndicadorC bscIndicadorS) {
        this.bscIndicadorS = bscIndicadorS;
    }
    public List<bscIndicadorC> getBscIndicadorL() {
        return bscIndicadorL;
    }
    public void setBscIndicadorL(List<bscIndicadorC> bscIndicadorL) {
        this.bscIndicadorL = bscIndicadorL;
    }
    public boolean isBandera() {
        return bandera;
    }
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }
    public int getAnio() {
        return anio;
    }
    public void setAnio(int anio) {
        this.anio = anio;
    }
    public bscPerpestivaObjetivoC getBscPerpestivaObjetivo() {
        return bscPerpestivaObjetivo;
    }
    public void setBscPerpestivaObjetivo(bscPerpestivaObjetivoC bscPerpestivaObjetivo) {
        this.bscPerpestivaObjetivo = bscPerpestivaObjetivo;
    }
    public bscPerpestivaObjetivoIndicadorC getBscPerpestivaObjetivoIndicador() {
        return bscPerpestivaObjetivoIndicador;
    }
    public void setBscPerpestivaObjetivoIndicador(bscPerpestivaObjetivoIndicadorC bscPerpestivaObjetivoIndicador) {
        this.bscPerpestivaObjetivoIndicador = bscPerpestivaObjetivoIndicador;
    }
    public boolean isBanderaObjetivo() {
        return banderaObjetivo;
    }
    public void setBanderaObjetivo(boolean banderaObjetivo) {
        this.banderaObjetivo = banderaObjetivo;
    }
    public perpestivaObjetivo getPerpestivaObjetivo() {
        return perpestivaObjetivo;
    }
    public void setPerpestivaObjetivo(perpestivaObjetivo perpestivaObjetivo) {
        this.perpestivaObjetivo = perpestivaObjetivo;
    }
    public String getFiltroObjetivo() {
        return filtroObjetivo;
    }
    public void setFiltroObjetivo(String filtroObjetivo) {
        this.filtroObjetivo = filtroObjetivo;
    }

   
    
    
    
   public class indicadorMes{
       private bscIndicadorC  bscIndicador;
       private List<bscPerpestivaObjetivoIndicadorDetalleC> bscPerpestivaObjetivoIndicadorDetalleL=new ArrayList<>();

        public indicadorMes() {
        }

        public indicadorMes(bscIndicadorC bscIndicador) {
            this.bscIndicador = bscIndicador;
        }
       
       
       

        /**
         * @return the bscIndicador
         */
        public bscIndicadorC getBscIndicador() {
            return bscIndicador;
        }

        /**
         * @param bscIndicador the bscIndicador to set
         */
        public void setBscIndicador(bscIndicadorC bscIndicador) {
            this.bscIndicador = bscIndicador;
        }

        /**
         * @return the bscPerpestivaObjetivoIndicadorDetalleL
         */
        public List<bscPerpestivaObjetivoIndicadorDetalleC> getBscPerpestivaObjetivoIndicadorDetalleL() {
            return bscPerpestivaObjetivoIndicadorDetalleL;
        }

        /**
         * @param bscPerpestivaObjetivoIndicadorDetalleL the bscPerpestivaObjetivoIndicadorDetalleL to set
         */
        public void setBscPerpestivaObjetivoIndicadorDetalleL(List<bscPerpestivaObjetivoIndicadorDetalleC> bscPerpestivaObjetivoIndicadorDetalleL) {
            this.bscPerpestivaObjetivoIndicadorDetalleL = bscPerpestivaObjetivoIndicadorDetalleL;
        }
   }

    
    public class objetivoIndicador{
        private bscObjetivoC bscObjetivo;
        private List<indicadorMes> indicadorMesL=new ArrayList<>();

        public objetivoIndicador() {
        }

        public objetivoIndicador(bscObjetivoC bscObjetivo) {
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
         * @return the indicadorMesL
         */
        public List<indicadorMes> getIndicadorMesL() {
            return indicadorMesL;
        }

        /**
         * @param indicadorMesL the indicadorMesL to set
         */
        public void setIndicadorMesL(List<indicadorMes> indicadorMesL) {
            this.indicadorMesL = indicadorMesL;
        }

   

      

   

     
    }
    
     public class perpestivaObjetivo{
         private bscPerpestivaC bscPerpestiva;
         private List<objetivoIndicador> objetivoIndicadorL=new ArrayList<>();

        public perpestivaObjetivo() {
        }

        public perpestivaObjetivo(bscPerpestivaC bscPerpestiva) {
            this.bscPerpestiva = bscPerpestiva;
        }

       
        

        /**
         * @return the bscPerpestiva
         */
        public bscPerpestivaC getBscPerpestiva() {
            return bscPerpestiva;
        }

        /**
         * @param bscPerpestiva the bscPerpestiva to set
         */
        public void setBscPerpestiva(bscPerpestivaC bscPerpestiva) {
            this.bscPerpestiva = bscPerpestiva;
        }

     
        /**
         * @return the objetivoIndicadorL
         */
        public List<objetivoIndicador> getObjetivoIndicadorL() {
            return objetivoIndicadorL;
        }

        /**
         * @param objetivoIndicadorL the objetivoIndicadorL to set
         */
        public void setObjetivoIndicadorL(List<objetivoIndicador> objetivoIndicadorL) {
            this.objetivoIndicadorL = objetivoIndicadorL;
        }

  
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
     * @return the bscIndicador
     */
    public bscIndicadorC getBscIndicador() {
        return bscIndicador;
    }

    /**
     * @param bscIndicador the bscIndicador to set
     */
    public void setBscIndicador(bscIndicadorC bscIndicador) {
        this.bscIndicador = bscIndicador;
    }

    /**
     * @return the bscObjetivoL
     */
    public List<bscObjetivoC> getBscObjetivoL() {
        return bscObjetivoL;
    }

    /**
     * @param bscObjetivoL the bscObjetivoL to set
     */
    public void setBscObjetivoL(List<bscObjetivoC> bscObjetivoL) {
        this.bscObjetivoL = bscObjetivoL;
    }

    /**
     * @return the bscPerpestivaS
     */
    public bscPerpestivaC getBscPerpestivaS() {
        return bscPerpestivaS;
    }

    /**
     * @param bscPerpestivaS the bscPerpestivaS to set
     */
    public void setBscPerpestivaS(bscPerpestivaC bscPerpestivaS) {
        this.bscPerpestivaS = bscPerpestivaS;
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

    
}
