	
package Beans;

import DAO.tramRequisitoDAO;
import ENTIDAD.tramRequisitoC;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="tramRequisitoB")
@ViewScoped
public class tramRequisito implements Serializable {
   
	private static final long serialVersionUID = 1L;

	private List<tramRequisitoC> tramRequisitoL=new ArrayList<>();   
    private tramRequisitoC tramRequisito;    
    private String busDescripcion;        
    private boolean  bandera;
    private String txtBusqueda;
    
    
    tramRequisitoDAO tramRequisitoD;
    public tramRequisito() {
        tramRequisitoD=new tramRequisitoDAO();
        tramRequisitoL=tramRequisitoD.mostrarTodoRequisito();
        if(tramRequisitoL.size()>1){
        	tramRequisito=tramRequisitoL.get(0);
        }
    }
    
    
    
    
    public void nuevoRequisito(){
        tramRequisito=new tramRequisitoC(0,"","",1);
        txtBusqueda="";
        bandera=true;
        
        util.script("dlgTramiteRequisito.show()");
        
   
    }
    
    public void nuevo(){
    	tramRequisito=new tramRequisitoC(0,"","",1);
        txtBusqueda="";
        bandera=true;
        
        util.script("dlgTramiteRequisito.show()");
    }
    
    
    
    public void editar(){
        bandera=true;
        txtBusqueda="";
    }
    public void guardar(){
        String codigo;
        tramRequisitoD=new tramRequisitoDAO();
        codigo=tramRequisitoD.insertar(tramRequisito);
        tramRequisito.setRequisito(Integer.parseInt(codigo));
        bandera=false;
        tramRequisitoD=new tramRequisitoDAO();
        tramRequisitoL=tramRequisitoD.mostrarTodoRequisito();
        util.script("PF('bui').hide()");
    }
   
    public void estadoRequisito(tramRequisitoC item){
    	
    	item.setEstadoRegistro((item.getEstadoRegistro()==1?0:1));
    	tramRequisitoD.insertar(item);
    }
    
     public void cancelar(){
        bandera=false;
        util.script("PF('bui').hide()");
    }
    public void eliminar(){
        tramRequisitoD=new tramRequisitoDAO();        
        tramRequisitoD.eliminar(tramRequisito);         
        tramRequisitoL=new tramRequisitoDAO().mostrarTodoRequisito();
        
    }
 
    public List<tramRequisitoC> mostrarRequisitos(int tramite){
        tramRequisitoD=new tramRequisitoDAO();
        tramRequisitoL=tramRequisitoD.mostrarRequisito(tramite);
        return tramRequisitoL;
    }
    
    public List<tramRequisitoC> mostrarTodoRequisito(){
        tramRequisitoD=new tramRequisitoDAO();
        tramRequisitoL=tramRequisitoD.mostrarTodoRequisito();
        return tramRequisitoL;
    }
    
      public List<tramRequisitoC> mostrarRequisitosEstado(int tramite){
        tramRequisitoD=new tramRequisitoDAO();
        
        tramRequisitoL=tramRequisitoD.mostrarRequisitoEstado(tramite);
        return tramRequisitoL;
    }
    
     public List<tramRequisitoC> mostrarRequisitosEntregados(int tramite,String expediente){
        tramRequisitoD=new tramRequisitoDAO();
        tramRequisitoL=tramRequisitoD.mostrarRequisitoEntregados(tramite,expediente);
        return tramRequisitoL;
    }
     
  
     
    public List<tramRequisitoC> fitroRequisitos(String descripcion){       
        tramRequisitoD=new tramRequisitoDAO(); 
        tramRequisitoL=tramRequisitoD.filtroRequisito(descripcion);
        return tramRequisitoL;
        
    }
    

    
    public List<tramRequisitoC> getTramRequisitoL() {
        return tramRequisitoL;
    }

    
    public void setTramRequisitoL(List<tramRequisitoC> tramRequisitoL) {
        this.tramRequisitoL = tramRequisitoL;
    }

    
    public tramRequisitoC getTramRequisito() {
        return tramRequisito;
    }

  
    public void setTramRequisito(tramRequisitoC tramRequisito) {
        this.tramRequisito = tramRequisito;
    }

  
    public String getBusDescripcion() {
        return busDescripcion;
    }

  
    public void setBusDescripcion(String busDescripcion) {
        this.busDescripcion = busDescripcion;
    }

    public boolean isBandera() {
        return bandera;
    }

    
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    
    public String getTxtBusqueda() {
        return txtBusqueda;
    }

   
    public void setTxtBusqueda(String txtBusqueda) {
        this.txtBusqueda = txtBusqueda;
    }

    
}
