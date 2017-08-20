/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.tramRequisitoDAO;
import DAO.tramTipoTramiteDAO;
import DAO.tramTramiteDAO;
import DAO.tramTramiteOficinaDAO;
import DAO.tramTramiteRequisitoDAO;
import ENTIDAD.oficinaC;
import ENTIDAD.tramRequisitoC;
import ENTIDAD.tramTramiteC;
import ENTIDAD.tramTramiteOficinaC;
import ENTIDAD.tramTramiteRequisitoC;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;


@ManagedBean(name="tramTramiteRequisitoB")
@ViewScoped
public class tramTramiteRequisito implements Serializable {
 
	private static final long serialVersionUID = 1L;
	private boolean bandera;
    private boolean banderaOficina;
    private boolean banderaRequisito;
    private List<tramTramiteC> tramTramiteDocumentarioL;
    private tramTramiteC tramTramiteDocumentario;
    private tramTramiteC tramTramiteDocumentarioT;
    private List<tramRequisitoC> tramRequisitoL=new ArrayList<>();
    private List<tramRequisitoC> tramRequisitoLT=new ArrayList<>();
  


	private List<tramTramiteOficinaC> tramTramiteOficinaL;
	private List<tramTramiteOficinaC> tramTramiteOficinaLT;
 

	private tramTramiteOficinaC tramTramiteOficina;
    private tramRequisitoC tramRequisito;

    
   
   
    
    private String busquedaRequisito;
    private String busquedaTramite;
    private List<oficinaC> oficinaL=new ArrayList<>();
    
    
    tramTramiteDAO tramTramiteDocumentarioD;
    tramTramiteRequisitoDAO tramTramiteRequisitoD;
    tramTramiteOficinaDAO tramTramiteOficinaD;
    tramTipoTramiteDAO tramTipoTramiteD;
    tramRequisitoDAO tramRequisitoD;
    


    public tramTramiteRequisito() {
       
               
    	tramTramiteDocumentario=new tramTramiteDAO().mostrarUltimoTramiteDocumentario();
    	mostrarTramiteRequisito();
    	mostrarTramiteOficina();
              
    }

    public void nuevoRequisito(){
    	tramRequisitoL=new ArrayList<>();
    	tramRequisito=new tramRequisitoC();
    	busquedaRequisito="";
    	util.script("dlgRequisito.show()");
    }
    
    public void reordenar(){
    	banderaOficina=true;
    	for(tramTramiteOficinaC item :tramTramiteOficinaL){
    		item.setOrden(0);
    	}
    	cont=0;
    }
    
    int cont=0;
    public void enumerar(tramTramiteOficinaC itemTramiteOficina){
    	cont++;
    	itemTramiteOficina.setOrden(cont);
    	
    }
    
    public void guardarOrden(){
    	tramTramiteOficinaD=new tramTramiteOficinaDAO();
    	for(tramTramiteOficinaC item :tramTramiteOficinaL){
    		
    		tramTramiteOficinaD.insertar(item);
    	}
    	mostrarTramiteOficina();
    	banderaOficina=false;
    }
    public void cancelarOrden(){
    	banderaOficina=false;
    }
    
    public List<oficinaC> getOficinaL() {
        return oficinaL;
    }

   
    public void setOficinaL(List<oficinaC> oficinaL) {
        this.oficinaL = oficinaL;
    }


    
    public void insertarOficina(oficinaC itemOficina){
    	tramTramiteOficinaC itemTramiteOficina=new tramTramiteOficinaC( tramTramiteDocumentario.getTramite(),-1,itemOficina.getOficina(),0,0,"",1); 
    	
    	insertarTramiteOficina(itemTramiteOficina);
    
    }
    
    public void insertarTramiteOficina(tramTramiteOficinaC itemTramiteOficina){
    	tramTramiteOficinaD=new tramTramiteOficinaDAO();
    	tramTramiteOficinaD.insertar(itemTramiteOficina);
    	mostrarTramiteOficina();
    }
    
    public void eliminarTramiteOficina(tramTramiteOficinaC itemTramiteOficina){
    	tramTramiteOficinaD=new tramTramiteOficinaDAO();
    	tramTramiteOficinaD.eliminar(itemTramiteOficina);
    	mostrarTramiteOficina();
    }
     
     
    
     
    public void filtrarRequisitoTramite(){
          
      
            banderaRequisito=false;
            tramRequisitoD=new tramRequisitoDAO(); 
            
            tramRequisitoL=tramRequisitoD.filtroRequisito(""+tramTramiteDocumentario.getTramite(),busquedaRequisito);            
       
        
        
        
    }
    
    
    
     public void onRowSelect(SelectEvent event) {
         
         
         
         mostrarTramiteRequisito();
    }
     
     
     
     public List<tramRequisitoC> mostrarRequisitos(int tramite){
        tramRequisitoD=new tramRequisitoDAO();
        tramRequisitoL=tramRequisitoD.mostrarRequisito(tramite);
        return tramRequisitoL;
    }
    
    public List<tramTramiteC> mostrarTramiteDocumentario(int documento){
        tramTramiteDocumentarioD=new tramTramiteDAO();
        tramTramiteDocumentarioL=tramTramiteDocumentarioD.mostrarTramiteDocumentario(documento);
        return tramTramiteDocumentarioL;
    }
     public List<tramTramiteC> mostrarTramiteDocumentario(){
        tramTramiteDocumentarioD=new tramTramiteDAO();
        tramTramiteDocumentarioL=tramTramiteDocumentarioD.mostrarTramiteDocumentario();
        return tramTramiteDocumentarioL;
    }
       public List<tramTramiteC> filtroTramiteDocumentario(String tipoTramite,String descripcion){
        tramTramiteDocumentarioD=new tramTramiteDAO();
        tramTramiteDocumentarioL=tramTramiteDocumentarioD.filtroTramiteDocumentario(tipoTramite, descripcion);
        return tramTramiteDocumentarioL;
    }
     
      public tramTramiteC mostrarTramiteDocumentario(int institucion,int tramite){
        tramTramiteDocumentarioD=new tramTramiteDAO();
        tramTramiteDocumentario=tramTramiteDocumentarioD.mostrarTramiteDocumentario(institucion,tramite);        
        return tramTramiteDocumentario;
    }
      public void mostrarTramiteOficina(){
          
          tramTramiteOficinaD=new tramTramiteOficinaDAO();
          tramTramiteOficinaL=tramTramiteOficinaD.mostrarTramiteOficina(tramTramiteDocumentario.getTramite());
          
      }
    
    public void nuevo(){
    	bandera=true;
        tramTramiteDocumentarioT=tramTramiteDocumentario;
        tramRequisitoLT=tramRequisitoL;
        tramTramiteOficinaLT=tramTramiteOficinaL;
        
        
        
        tramTramiteDocumentario=new tramTramiteC(1, -1, null, null, 1, 0, 1);     
        tramRequisitoL=new ArrayList<>();
        tramTramiteOficinaL=new ArrayList<>();
        util.script("document.getElementById('frmTramiteRequisito\\:cbTipoTramite').focus()");
        
    }
    public void cancelar(){
        bandera=false;
        tramTramiteDocumentario=tramTramiteDocumentarioT;
        tramRequisitoL=tramRequisitoLT;
        tramTramiteOficinaL=tramTramiteOficinaLT;
    }
    
    public void eliminar(){
       tramTramiteDocumentarioD=new tramTramiteDAO();
       tramTramiteDocumentario.setEstadoRegistro(0);
       tramTramiteDocumentarioD.insertar(tramTramiteDocumentario);
       tramRequisitoL=new ArrayList<>();
    }
    
    public void editar(){
         tramTramiteDocumentarioT=tramTramiteDocumentario;
        bandera=true;
    }
    public void guardar(){
        System.out.println("Guardar");
        tramTramiteDocumentarioD=new tramTramiteDAO();
        tramTramiteDocumentarioD.insertar(tramTramiteDocumentario);
        bandera=false;
        util.render("frmTramiteRequisito:pnDatos");
         util.render("frmTramiteRequisito:pnBotonera");
          util.render("frmTramiteRequisito:tbTramiteDocumentario");
        
    }
    
  
 
    
   
    
   
 
    
    
  
    
    
    
    public void insertarTramiteRequisito(){
        
    	tramTramiteRequisitoD=new tramTramiteRequisitoDAO();
    
    	tramTramiteRequisitoD.insertar(new tramTramiteRequisitoC(tramTramiteDocumentario.getTramite(),tramRequisito.getRequisito(),1));    
   
        tramRequisito=new tramRequisitoC();
        busquedaRequisito="";
        
        tramRequisitoD=new tramRequisitoDAO();
        tramRequisitoL=tramRequisitoD.mostrarRequisito(tramTramiteDocumentario.getTramite());
        util.script("dlgRequisito.hide()");
    
    }
    
    public void eliminarTramiteRequisito(tramRequisitoC item){
    	tramTramiteRequisitoD=new tramTramiteRequisitoDAO();
        
    	tramTramiteRequisitoD.eliminar(new tramTramiteRequisitoC(tramTramiteDocumentario.getTramite(),item.getRequisito(),1));        
    	mostrarTramiteRequisito();
    }
    
    public void mostrarTramiteRequisito(){
    	 tramRequisitoL=new tramRequisitoDAO().mostrarRequisito(tramTramiteDocumentario.getTramite());
    }
    
    public void mostrarTramiteDocumento(){
    	mostrarTramiteRequisito();
    	mostrarTramiteOficina();
    	util.script("dlgTramite.hide()");
    }

   
    public List<tramTramiteC> getTramTramiteDocumentarioL() {
        return tramTramiteDocumentarioL;
    }

    public void setTramTramiteDocumentarioL(List<tramTramiteC> tramTramiteDocumentarioL) {
        this.tramTramiteDocumentarioL = tramTramiteDocumentarioL;
    }

    
    public tramTramiteC getTramTramiteDocumentario() {
        return tramTramiteDocumentario;
    }

    
    public void setTramTramiteDocumentario(tramTramiteC tramTramiteDocumentario) {
        this.tramTramiteDocumentario = tramTramiteDocumentario;
    }

   
    public boolean isBandera() {
        return bandera;
    }

    
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    
    public List<tramRequisitoC> getTramRequisitoL() {
        return tramRequisitoL;
    }

    
    public void setTramRequisitoL(List<tramRequisitoC> tramRequisitoL) {
        this.tramRequisitoL = tramRequisitoL;
    }

   
    public List<tramTramiteOficinaC> getTramTramiteOficinaL() {
        return tramTramiteOficinaL;
    }

    
    public void setTramTramiteOficinaL(List<tramTramiteOficinaC> tramTramiteOficinaL) {
        this.tramTramiteOficinaL = tramTramiteOficinaL;
    }

   
    public tramTramiteOficinaC getTramTramiteOficina() {
        return tramTramiteOficina;
    }

   
    public void setTramTramiteOficina(tramTramiteOficinaC tramTramiteOficina) {
        this.tramTramiteOficina = tramTramiteOficina;
    }

   
    public boolean isBanderaOficina() {
        return banderaOficina;
    }

   
    public void setBanderaOficina(boolean banderaOficina) {
        this.banderaOficina = banderaOficina;
    }


    public String getBusquedaRequisito() {
        return busquedaRequisito;
    }

    
    public void setBusquedaRequisito(String busquedaRequisito) {
        this.busquedaRequisito = busquedaRequisito;
    }

    
    public String getBusquedaTramite() {
        return busquedaTramite;
    }

   
    public void setBusquedaTramite(String busquedaTramite) {
        this.busquedaTramite = busquedaTramite;
    }

    
    public tramTramiteC getTramTramiteDocumentarioT() {
        return tramTramiteDocumentarioT;
    }

    
    public void setTramTramiteDocumentarioT(tramTramiteC tramTramiteDocumentarioT) {
        this.tramTramiteDocumentarioT = tramTramiteDocumentarioT;
    }

   
    public tramRequisitoC getTramRequisito() {
        return tramRequisito;
    }

   
    public void setTramRequisito(tramRequisitoC tramRequisito) {
        this.tramRequisito = tramRequisito;
    }

    public boolean isBanderaRequisito() {
        return banderaRequisito;
    }

 
    public void setBanderaRequisito(boolean banderaRequisito) {
        this.banderaRequisito = banderaRequisito;
    }
    public List<tramRequisitoC> getTramRequisitoLT() {
  		return tramRequisitoLT;
  	}



  	public void setTramRequisitoLT(List<tramRequisitoC> tramRequisitoLT) {
  		this.tramRequisitoLT = tramRequisitoLT;
  	}
    public List<tramTramiteOficinaC> getTramTramiteOficinaLT() {
 		return tramTramiteOficinaLT;
 	}



 	public void setTramTramiteOficinaLT(List<tramTramiteOficinaC> tramTramiteOficinaLT) {
 		this.tramTramiteOficinaLT = tramTramiteOficinaLT;
 	}
}
