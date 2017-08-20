
package Beans;

import DAO.alumnoDAO;
import DAO.empresaDAO;
import DAO.oficinaDAO;
import DAO.personaDAO;
import DAO.tramRequisitoDAO;
import DAO.tramRequisitoEntregadoDAO;
import DAO.tramTipoTramiteDAO;
import DAO.tramTramiteDAO;
import DAO.tramTramiteOficinaDAO;
import DAO.tramTramitePersonaDAO;

import DAO.tramTramiteSeguimientoDAO;
import ENTIDAD.alumnoC;
import ENTIDAD.empresaC;
import ENTIDAD.oficinaC;
import ENTIDAD.personaC;
import ENTIDAD.tramRequisitoC;
import ENTIDAD.tramTipoTramiteC;
import ENTIDAD.tramTramiteC;
import ENTIDAD.tramTramiteOficinaC;
import ENTIDAD.tramTramitePersonaC;
import ENTIDAD.tramTramiteRequisitoEntregadoC;
import ENTIDAD.tramTramiteSeguimientoC;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import org.primefaces.event.SelectEvent;



@ManagedBean(name="tramTramitePersonaB")
@ViewScoped
public class tramTramitePersona implements Serializable {
	
    
	private static final long serialVersionUID = 1L;
	
	private List<tramTipoTramiteC> tramTipoTramiteL=new ArrayList<>();
	private List<tramiteOficinaExpediente> tramiteOficinaExpedienteL;
	private List<tramiteOficinaExpediente> tramiteOficinaExpedienteLT;
	private oficinaC oficina=new oficinaC();
	private List<oficinaC> oficinaL=new ArrayList<>();
	private List<tramTramitePersonaC> tramTramitePersonaL;
	
    private List<String> requisitosL=new ArrayList<>();
    private tramTramiteC tramTramite;
    private tramTramitePersonaC tramTramitePersona=new tramTramitePersonaC();
    private tramTramitePersonaC tramTramitePersonaT=new tramTramitePersonaC();
    private tramTramiteSeguimientoC tramTramiteSeguimiento=new tramTramiteSeguimientoC();
  
    private tramTipoTramiteC tramTipoTramite=new tramTipoTramiteC(); 
    
  
    private List<tramTramitePersonaEmpresa> tramTramitePersonaEmpresaL=new ArrayList<>();
    private tramTramitePersonaEmpresa tramTramitePersonaEmpresa=new tramTramitePersonaEmpresa();
    
    
    
	
	


	
	
	private List<tramTramiteC> tramTramiteDocumentarioL;
    
    private empresaC empresa;
    
    private personaEmpresa  personaEmpresa=new personaEmpresa();
    private personaEmpresa  personaEmpresaT=new personaEmpresa();
    private List<personaEmpresa>  personaEmpresaL;
    private String busquedaNumero;
    private String busPersonaEmpresa;
    private int tipoDocumento;
    private List<tramRequisitoC> tramRequisitoL=new ArrayList<>();
    
    private List<tramTramiteOficinaC> tramTramiteOficinaL=new ArrayList<>();
   
    private personaC persona;
    private alumnoC alumno;
    private boolean  bandera;
    private boolean banderaAccion;
    private boolean banderaBusqueda;
    private int indice=1;
    private int ultimoIndice=0;
    
    private String busExpediente;
    private int optFitro=1;
    private String procesado;
    
    private String codigoT;
  
    
    
  
	



	//================== DATOS ======================
    tramTramitePersonaDAO tramTramitePersonaD;
    alumnoDAO alumnoD;
    personaDAO personaD;   
    tramTramiteSeguimientoDAO tramTramiteSeguimientoD;
    tramRequisitoEntregadoDAO tramRequisitoEntregadoD;
    tramTramiteDAO tramTramiteD;
    tramRequisitoDAO tramRequisitoD;
    empresaDAO empresaD;
    oficinaDAO oficinaD;
    tramTipoTramiteDAO tramTipoTramiteD;
    tramTramiteOficinaDAO tramTramiteOficinaD;
    //================================================
    
    
     public tramTramitePersona() {
        
        tramTramitePersonaD=new tramTramitePersonaDAO();
        tramTramitePersona=tramTramitePersonaD.mostrarUltimoTramitePersona();
      
       
       
        if (tramTramitePersona!=null){
       
        indice=tramTramitePersona.getItem();
        ultimoIndice=indice;
        busExpediente=tramTramitePersona.getExpediente();
        
        
        tramTramiteD=new tramTramiteDAO();
        tramTramite=tramTramiteD.mostrarTramiteDocumentario(1, tramTramitePersona.getTramite());    
        
        
        
        tramRequisitoL=new tramRequisitoDAO().mostrarRequisitoExpediente(tramTramitePersona.getExpediente());
        
        tramTipoTramite=new tramTipoTramiteDAO().mostrarTipoTramite(tramTramitePersona.getTipoTramite());
        
       
        
       personaEmpresa=tramTramitePersonaD.buscarTipoPersona(tramTramitePersona.getTipoPersona(), tramTramitePersona.getPersonaEmpresa());
       mostrarTramiteOficina();
    }
        
        
    
       
         
    }
   
     
     
     
     public void eliminarTramiteOficina(tramTramiteSeguimientoC  itemTramTramiteSeguimiento){
    	 itemTramTramiteSeguimiento.setEstadoRegistro(0);
    	 new tramTramiteSeguimientoDAO().insertar(itemTramTramiteSeguimiento);
    	 mostrarTramiteOficina();
    	 
     }
     
     public void nuevaBusqueda(){
    	 busPersonaEmpresa="";
    	 tramTramitePersonaEmpresa=new tramTramitePersonaEmpresa();
    	 util.script("dlgBusqueda.show()");
     }
     
     public void seleccionBusqueda(){
    	 
    
    	 
    	 	
    	  	 tramTramitePersonaD=new tramTramitePersonaDAO();     
              tramTramitePersona=tramTramitePersonaD.mostrarTramitePersona(tramTramitePersonaEmpresa.tramTramitePersona.getExpediente());
              indice=tramTramitePersona.getItem();
              tramRequisitoEntregadoD=new tramRequisitoEntregadoDAO();
              requisitosL=tramRequisitoEntregadoD.mostrarRequisitoEntregado(tramTramitePersona.getExpediente());
              personaEmpresa=tramTramitePersonaD.buscarTipoPersona(tramTramitePersona.getTipoPersona(), tramTramitePersona.getPersonaEmpresa());
              mostrarTramiteOficina();
              busExpediente=tramTramitePersonaEmpresa.tramTramitePersona.getExpediente();
              tramRequisitoL=new tramRequisitoDAO().mostrarRequisitoExpediente(tramTramitePersona.getExpediente());
              util.script("dlgBusqueda.hide()");
     }
     
     
    public List<tramTramiteOficinaC> getTramTramiteOficinaL() {
		return tramTramiteOficinaL;
	}
	public void setTramTramiteOficinaL(List<tramTramiteOficinaC> tramTramiteOficinaL) {
		this.tramTramiteOficinaL = tramTramiteOficinaL;
	}
	public void mostrarTramiteOficina(){
    	
        	tramiteOficinaExpedienteL=new ArrayList<>();
        	tramTramiteSeguimientoD=new tramTramiteSeguimientoDAO();
        	tramiteOficinaExpediente itemTramiteOficinaExpediente;
        	for(tramTramiteSeguimientoC itemtramTramiteSeguimiento: tramTramiteSeguimientoD.mostrarTramiteSeguimiento(tramTramitePersona.getExpediente()))
        	{
        		
        		itemTramiteOficinaExpediente=new tramiteOficinaExpediente(itemtramTramiteSeguimiento);
        		itemTramiteOficinaExpediente.oficina=new oficinaDAO().mostrarOficina(1, itemtramTramiteSeguimiento.getOficina());
        		tramiteOficinaExpedienteL.add(itemTramiteOficinaExpediente);
        		
        	}
        	
        	
        	
     }
    
    public void tipoPersona(){
    	personaEmpresa=new personaEmpresa();
    	util.script("document.getElementById('frmTramiteDocumentario\\:txtNombres').focus()");
    }
    
     public List<personaEmpresa> completeTheme(String nombre) {         
    	 personaEmpresa=new personaEmpresa();
          banderaBusqueda=true;
          
          tramTramitePersonaD=new tramTramitePersonaDAO();
        
        if (tramTramitePersona.getTipoPersona().endsWith("1")){
            personaEmpresaL= tramTramitePersonaD.filtroPersona(nombre);
            
        }else{
            personaEmpresaL= tramTramitePersonaD.filtroEmpresa(nombre);
            
        }
        
        if (personaEmpresaL.size()>0){
            
        }
       
    
        return personaEmpresaL;
    }
    
    public void buscarTramiteExpedinte(String expediente){
        tramTramitePersonaD=new tramTramitePersonaDAO();       
        if (tramTramitePersonaD.mostrarTramitePersona(expediente)==null){            
            
            busExpediente=tramTramitePersona.getExpediente();
            util.alert("Expediente no encontrado");
        }else{
            
            tramTramitePersona=tramTramitePersonaD.mostrarTramitePersona(expediente);
            indice=tramTramitePersona.getItem();
            tramRequisitoEntregadoD=new tramRequisitoEntregadoDAO();
            requisitosL=tramRequisitoEntregadoD.mostrarRequisitoEntregado(tramTramitePersona.getExpediente());
            personaEmpresa=tramTramitePersonaD.buscarTipoPersona(tramTramitePersona.getTipoPersona(), tramTramitePersona.getPersonaEmpresa());
            mostrarTramiteOficina();
        }
    }
    
    
    
    
    public void filtroPersonaEmpresa(){
        tramTramitePersonaD=new tramTramitePersonaDAO();         
        tramTramitePersonaL=new tramTramitePersonaDAO().mostrarFiltroTramitePersona(1,busPersonaEmpresa);
        
        tramTramitePersonaEmpresaL=new ArrayList<>();
        tramTramitePersonaEmpresa itemTramTramitePersonaEmpresa;
        for(tramTramitePersonaC tramTramitePersona : new tramTramitePersonaDAO().mostrarFiltroTramitePersona(1,busPersonaEmpresa)){
        	itemTramTramitePersonaEmpresa=new tramTramitePersonaEmpresa(tramTramitePersona);
        	itemTramTramitePersonaEmpresa.persona=new personaDAO().mostrarPersona(tramTramitePersona.getPersonaEmpresa());
        	itemTramTramitePersonaEmpresa.tramTramite=new tramTramiteDAO().mostrarTramiteDocumentario(1, tramTramitePersona.getTramite());
        	tramTramitePersonaEmpresaL.add(itemTramTramitePersonaEmpresa);
        	
        }
        
        
     
    }
    
    public void siguiente(){
        try{
            if ( ultimoIndice>indice  )
        {
           indice++;
           desplazamiento();
        }
        }catch(Exception e){
            System.out.println(e.getMessage());
           
        }
    }
    
      public void anterior(){
        try{
        if (indice!=1){
            indice--;            
                desplazamiento();        
        }else{
            util.alert("Ya se encuentra en el Primer Registro");
        }
        }catch(Exception e){
            System.out.println(e.getMessage());
            //util.consolaCliente(e.getMessage());
        }
            
    }
    
    
    
    public void ultimo(){
        tramTramitePersonaD=new tramTramitePersonaDAO();
        ultimoIndice=tramTramitePersonaD.mostrarUltimoTramitePersona().getItem();
         indice=ultimoIndice;
         desplazamiento();
    }
    public void primero(){
         indice=1;
         desplazamiento();
    }
    
    public void desplazamiento(){
        try{
            
            
           tramTramitePersonaD=new tramTramitePersonaDAO();
           tramTramitePersona=tramTramitePersonaD.mostrarDesplazamientoTramitePersona(indice);
           busExpediente=tramTramitePersona.getExpediente();
           personaEmpresa=tramTramitePersonaD.buscarTipoPersona(tramTramitePersona.getTipoPersona(), tramTramitePersona.getPersonaEmpresa());
           busquedaNumero=personaEmpresa.numero;

           tramTramiteD=new tramTramiteDAO();
           tramTramite=tramTramiteD.mostrarTramiteDocumentario(1, tramTramitePersona.getTramite());   
           
           //REQUISITOS
           tramRequisitoL=new tramRequisitoDAO().mostrarRequisitoExpediente(tramTramitePersona.getExpediente());
	       //tramRequisitoEntregadoD=new tramRequisitoEntregadoDAO();
	       //requisitosL=tramRequisitoEntregadoD.mostrarRequisitoEntregado(tramTramitePersona.getExpediente());
	       
	       
	       
	       
       
       tramTipoTramite=new tramTipoTramiteDAO().mostrarTipoTramite(tramTramitePersona.getTipoTramite());
       
        mostrarTramiteOficina();    
        
           util.render("pnDesplazamiento");
           util.render("pnDatosSolicitante");
           util.render("pnBotonera");
           util.render("pnDatosExpediente");
           util.render("pnOficinaDestino");
           util.render("pnTramiteOficina");
           util.render("pnDatosTramite");
           util.render("pnTramiteRequisito");
          
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
          
           
           
           
           
          
    }
    
    
  
    
    
    
     public void mostrarTramite(){
    	 
    	 
    	 
        tramTramiteD=new tramTramiteDAO();
        tramTramiteDocumentarioL=tramTramiteD.mostrarTramiteDocumentario(tramTramitePersona.getTipoTramite());
        tramRequisitoL=new ArrayList<>();
        
        
    }
     
     
     public void mostrarOficinaTramite(){
    	 tramTramiteOficinaL=new tramTramiteOficinaDAO().mostrarTramiteOficina(tramTramitePersona.getTipoTramite());
     }
     
    public void nuevo(){
    	
    	
    	
    	codigoT=tramTramitePersona.getExpediente();
        personaEmpresaT=personaEmpresa;
        personaEmpresa=new personaEmpresa();
        tramTramitePersonaT=tramTramitePersona;
        
        
        tramiteOficinaExpedienteLT=tramiteOficinaExpedienteL;
       
        
        tramTramitePersona=new tramTramitePersonaC();
        tramTramitePersona.setTipoPersona("1");
        tramTramitePersona.setTramite(-1);
        tramTramitePersona.setInstitucion(1);
        tramTramitePersona.setFechaIngreso(util.fechaHoy());
        tramTramitePersona.setEstadoRegistro(1);
        
        tramTramite=new tramTramiteC();
 
        requisitosL=new ArrayList<>();
        tramiteOficinaExpedienteL=new ArrayList<>();
        
        
        tramRequisitoL=new ArrayList<>();
        
        
        
        
        persona=new personaC();        
        
        busquedaNumero="";      
      
        bandera=true;
        banderaAccion=false;
        util.script("document.getElementById('txtNombres').focus()");
        
        
         
    }
    public void nuevoEmpresa(){
        empresa=new empresaC();
    }
    public void insertarEmpresa(){
        empresaD=new empresaDAO();
        empresaD.insertar(empresa);
        util.script("dlgEmpresa.hide()");
    }
    public void validarRuc(){
        empresaD=new empresaDAO();
        if (empresaD.mostrarEmpresa(empresa.getRuc())!=null){
            empresa.setRuc("");
            util.alert("El Numero de ruc ya existe");
        }
    }
    
    public void mostrarTramite(int institucion,int tramite){
        requisitosL=new ArrayList<>();
        tramTramiteD=new tramTramiteDAO();
        tramTramite=tramTramiteD.mostrarTramiteDocumentario(institucion,tramite);        
        mostrarRequisitos(tramTramitePersona.getTipoTramite(),tramTramitePersona.getTramite());
    }
    
    
     public void nuevoTramite(){
    	
    	 
    	 if(banderaAccion){
    	
    		 tramTramiteD=new tramTramiteDAO();
    	     tramTramiteDocumentarioL=tramTramiteD.mostrarTramiteDocumentario(tramTramitePersona.getTipoTramite());
    	 }
    	 util.script("dlgEnviarTramite.show()");
     }
     
     public void mostrarTipoTramite(){
    	 tramTipoTramiteL=new tramTipoTramiteDAO().mostrarTipoTramite();
         
         
     }
    
    public void cancelar(){    	
    	
    	
    	
        tramTramitePersona=tramTramitePersonaD.mostrarTramitePersona(codigoT);
        indice=tramTramitePersona.getItem();        
        requisitosL=new tramRequisitoEntregadoDAO().mostrarRequisitoEntregado(tramTramitePersona.getExpediente());
        personaEmpresa=tramTramitePersonaD.buscarTipoPersona(tramTramitePersona.getTipoPersona(), tramTramitePersona.getPersonaEmpresa());
        
        // TIPO TRAMITE
        tramTipoTramite=new tramTipoTramiteDAO().mostrarTipoTramite(tramTramitePersona.getTipoTramite());
        
       // TRAMITE
        tramTramite=new tramTramiteDAO().mostrarTramiteDocumentario(1, tramTramitePersona.getTramite());   
        
        //OFICINA
        mostrarTramiteOficina();
        //REQUISITOS
   
        tramRequisitoL=new tramRequisitoDAO().mostrarRequisitoExpediente(tramTramitePersona.getExpediente());
        
        bandera=false;
        banderaBusqueda=false;
    }
    public void editar(){
    	codigoT=tramTramitePersona.getExpediente();
        bandera=true;
        banderaAccion=true;
        personaEmpresaT=personaEmpresa;        
        tramTramitePersonaT=tramTramitePersona;
    }
    
    public void eliminar(){
        tramTramitePersona.setEstadoRegistro(0);
        util.consolaCliente("eliminando");
    
    }
    
    
    
    
    public void guardar(){
    	
    	
    		
    		if(tramRequisitoL.size()==requisitosL.size()){
    			String codigo;      
            	tramTramitePersonaD=new tramTramitePersonaDAO();
                codigo=tramTramitePersonaD.insertar(tramTramitePersona);
                tramTramitePersona.setExpediente(codigo);
                if (!banderaAccion){
                	tramRequisitoEntregadoD=new tramRequisitoEntregadoDAO();
                	 for (tramRequisitoC itemRequisito : tramRequisitoL) {
                         
                         if (requisitosL.contains(String.valueOf(itemRequisito.getRequisito()))){
                            
                             tramRequisitoEntregadoD.insertar(new tramTramiteRequisitoEntregadoC(codigo, itemRequisito.getRequisito(),true,util.fechaHoy(), 1));            
                         }else{
                            
                             tramRequisitoEntregadoD.eliminar(new tramTramiteRequisitoEntregadoC(codigo, itemRequisito.getRequisito(),true,util.fechaHoy(), 1));            
                         }     
                     }
                	
                	
                	for(tramTramiteOficinaC itemTramTramiteOficina:new tramTramiteOficinaDAO().mostrarTramiteOficina(tramTramitePersona.getTramite())){
                    	new tramTramiteSeguimientoDAO().insertar(new tramTramiteSeguimientoC(codigo, itemTramTramiteOficina.getOficinaEmisor(), null, null, null, null, null, null, null, 1));
                    }	
                }
                
           
                
                busExpediente=codigo;    
                tramTramitePersonaD=new tramTramitePersonaDAO();
                ultimoIndice=tramTramitePersonaD.mostrarUltimoTramitePersona().getItem();
                mostrarTramiteOficina();
                bandera=false;
                util.script("dlgEnviarTramite.hide()");
    		}else{
    			util.alert("debe Seleccionar todos los requisitos");
    		}
    		
    		
        	
        
        
        
       
    }
    
    public void mostrarRequisitos(int tipoTramite,int tramite ){
        
        tramRequisitoD=new tramRequisitoDAO();
        tramRequisitoL=tramRequisitoD.mostrarRequisito(tipoTramite, tramite);
        
    }
    
    
    public void nuevaTramiteSeguimiento(){
   	   oficinaL=new oficinaDAO().mostrarOficinas(1);
   	   
   	   tramTramiteSeguimiento=new tramTramiteSeguimientoC(tramTramitePersona.getExpediente(), 0, null, null, null, null, null, null, null, 1);
   	   util.script("dlgNuevaOficinaTramite.show()");
     }
    
    public void insertarTramiteSeguimiento(){
    	System.out.println("insetar");
    	new tramTramiteSeguimientoDAO().insertar(tramTramiteSeguimiento);
    	mostrarTramiteOficina();
    	util.script("dlgNuevaOficinaTramite.hide()");
    }
    
    public void insertarTramiteOficina(){
      tramTramiteSeguimientoD=new tramTramiteSeguimientoDAO();    
      tramTramiteSeguimiento.setFechaEntrega(util.fechaHoy());
      tramTramiteSeguimiento.setHoraEntrega(util.HoraHoy());
      tramTramiteSeguimiento.setEstadoRegistro(69);
      tramTramiteSeguimientoD.insertar(tramTramiteSeguimiento);
      mostrarTramiteOficina();
      util.script("dlgEnviarOficina.hide()");
}
    public void enviarTramiteOficina(oficinaC itemOficina,tramTramiteSeguimientoC itemTramTramiteSeguimiento){
    	oficina=itemOficina;
    	tramTramiteSeguimiento=itemTramTramiteSeguimiento;
    	util.script("dlgEnviarOficina.show()");
    }
    
  
   
   public void busquedaPersonaEmpresa(int opcion,String busqueda){
       tramTramitePersonaD=new tramTramitePersonaDAO();
       personaEmpresa=tramTramitePersonaD.mostrarPersonaEmpresa(opcion, busqueda);
       if(personaEmpresa ==null){
           util.alert("no encontre");
           busquedaNumero="";
       }else{
           tramTramitePersona.setPersonaEmpresa(personaEmpresa.codigo);
           
           util.script("document.getElementById('frmTramiteDocumentario\\:cbTipoTramite').focus()");
           
       }
   }
    
    public void busquedaAlumno(String institucion,String alumno){
        
        alumnoD=new alumnoDAO();
        this.alumno=alumnoD.mostrarAlumnoCodigo(institucion, alumno);
        if (this.alumno!=null){
            personaD=new personaDAO();
            persona=personaD.mostrarPersona(this.alumno.getPersona());
            tramTramitePersona.setPersonaEmpresa(this.alumno.getPersona());
            tramTramitePersona.setAlumno(this.alumno.getAlumno());
            util.script("document.getElementById('frmTramiteDocumentario\\:cboInstitucion').focus()");
        }else{
            util.alert("NO SE ENCONTRO ALUMNO");
        }
    }
    
    public void onRowSelect(SelectEvent event) {
        busquedaAlumno("1",((personaC) event.getObject()).getPersona());        
    }
 
    
    public void mostrarFitroTramitePersona(int opcion,String cadena){
        tramTramitePersonaD=new tramTramitePersonaDAO();
        tramTramitePersonaL=tramTramitePersonaD.mostrarFiltroTramitePersona(opcion,cadena);
    }
    
    
    public void seleccionTramite(){        
      
        busquedaAlumno("%",tramTramitePersona.getAlumno());
        tramRequisitoEntregadoD=new tramRequisitoEntregadoDAO();
        requisitosL=tramRequisitoEntregadoD.mostrarRequisitoEntregado(tramTramitePersona.getExpediente());
        
        personaD=new personaDAO();
        persona=personaD.mostrarPersona(tramTramitePersona.getPersonaEmpresa());
        
    }
    
    
    public void seleccionPersonaEmpresa(personaEmpresa item){
    	
    	
        personaEmpresa=item;
        tramTramitePersona.setPersonaEmpresa(personaEmpresa.codigo);
        banderaBusqueda=false;
        util.script("document.getElementById('frmTramiteDocumentario\\:cbTipoTramite').focus()");
    }
    
    public tramTramitePersonaC mostrarTramitePersona( String expediente){
        tramTramitePersonaD=new tramTramitePersonaDAO();
        tramTramitePersona=tramTramitePersonaD.mostrarTramitePersona(expediente);
        return tramTramitePersona;
    }
    
    
     public void onRowSelectPersonaEmpresa(SelectEvent event) {
         tramTramitePersona.setPersonaEmpresa(((personaEmpresa) event.getObject()).getCodigo());
         busquedaNumero=((personaEmpresa) event.getObject()).getNumero();
         util.script("dlgBusqueda.hide()");
        
    }
     public void selectPersona(){
         tramTramitePersona.setPersonaEmpresa(personaEmpresa.codigo);
         busquedaNumero=personaEmpresa.numero;
         util.script("dlgBusqueda.hide()");
     }
    

   
 

   
    public boolean isBanderaAccion() {
        return banderaAccion;
    }

    
    public void setBanderaAccion(boolean banderaAccion) {
        this.banderaAccion = banderaAccion;
    }

   
    public int getIndice() {
        return indice;
    }

    
    public void setIndice(int indice) {
        this.indice = indice;
    }

   
    public String getBusquedaNumero() {
        return busquedaNumero;
    }

   
    public void setBusquedaNumero(String busquedaNumero) {
        this.busquedaNumero = busquedaNumero;
    }

    public personaEmpresa getPersonaEmpresa() {
        return personaEmpresa;
    }

   
    public void setPersonaEmpresa(personaEmpresa personaEmpresa) {
        this.personaEmpresa = personaEmpresa;
    }

   
    public int getTipoDocumento() {
        return tipoDocumento;
    }

   
    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

   
    public List<personaEmpresa> getPersonaEmpresaL() {
        return personaEmpresaL;
    }

    
    public void setPersonaEmpresaL(List<personaEmpresa> personaEmpresaL) {
        this.personaEmpresaL = personaEmpresaL;
    }

   
    public String getBusPersonaEmpresa() {
        return busPersonaEmpresa;
    }

   
    public void setBusPersonaEmpresa(String busPersonaEmpresa) {
        this.busPersonaEmpresa = busPersonaEmpresa;
    }

   
    public personaEmpresa getPersonaEmpresaT() {
        return personaEmpresaT;
    }

    
    public void setPersonaEmpresaT(personaEmpresa personaEmpresaT) {
        this.personaEmpresaT = personaEmpresaT;
    }

   
    public List<tramTramiteC> getTramTramiteDocumentarioL() {
        return tramTramiteDocumentarioL;
    }

   
    public void setTramTramiteDocumentarioL(List<tramTramiteC> tramTramiteDocumentarioL) {
        this.tramTramiteDocumentarioL = tramTramiteDocumentarioL;
    }

   
    public int getUltimoIndice() {
        return ultimoIndice;
    }

   
    public void setUltimoIndice(int ultimoIndice) {
        this.ultimoIndice = ultimoIndice;
    }

   
    public List<tramRequisitoC> getTramRequisitoL() {
        return tramRequisitoL;
    }

    
    public void setTramRequisitoL(List<tramRequisitoC> tramRequisitoL) {
        this.tramRequisitoL = tramRequisitoL;
    }

   
    public boolean isBanderaBusqueda() {
        return banderaBusqueda;
    }

    public void setBanderaBusqueda(boolean banderaBusqueda) {
        this.banderaBusqueda = banderaBusqueda;
    }

   
    public String getProcesado() {
        return procesado;
    }

 
    public void setProcesado(String procesado) {
        this.procesado = procesado;
    }

  
    public empresaC getEmpresa() {
        return empresa;
    }

   
    public void setEmpresa(empresaC empresa) {
        this.empresa = empresa;
    }
    
    public static class tramTramitePersonaEmpresa{
    	private tramTramitePersonaC tramTramitePersona;    
		private personaC persona=new personaC();
    	private tramTramiteC tramTramite=new tramTramiteC();
    	public tramTramitePersonaEmpresa() {
			
		}
    	public tramTramitePersonaEmpresa(tramTramitePersonaC tramTramitePersona) {
    		this.tramTramitePersona=tramTramitePersona;
		}
    	
    	
    	public tramTramitePersonaC getTramTramitePersona() {
			return tramTramitePersona;
		}
		public void setTramTramitePersona(tramTramitePersonaC tramTramitePersona) {
			this.tramTramitePersona = tramTramitePersona;
		}
		public personaC getPersona() {
			return persona;
		}
		public void setPersona(personaC persona) {
			this.persona = persona;
		}
		public tramTramiteC getTramTramite() {
			return tramTramite;
		}
		public void setTramTramite(tramTramiteC tramTramite) {
			this.tramTramite = tramTramite;
		}
    	
    	
    }
    
    public static class personaEmpresa{
        private String codigo;
        private String nombre;
        private String nombreWeb;
        private String numero;
        private String direccion;
        private String telefono;
        private String celular;
        private String correo;

       
        public String getCodigo() {
            return codigo;
        }

      
        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

       
        public String getNombre() {
            return nombre;
        }

     
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

   
        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

       
        public String getTelefono() {
            return telefono;
        }

        
        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

       
        public String getCorreo() {
            return correo;
        }

      
        public void setCorreo(String correo) {
            this.correo = correo;
        }

        
        public String getNumero() {
            return numero;
        }

        
        public void setNumero(String numero) {
            this.numero = numero;
        }

      
        public String getCelular() {
            return celular;
        }

       
        public void setCelular(String celular) {
            this.celular = celular;
        }

      
        public String getNombreWeb() {
            return nombreWeb;
        }

   
        public void setNombreWeb(String nombreWeb) {
            this.nombreWeb = nombreWeb;
        }
    }
    public static class tramiteOficinaExpediente{
    	
    	
    	
    	private tramTramiteSeguimientoC tramTramiteSeguimiento;    	
        private oficinaC oficina=new oficinaC();
       
        public tramiteOficinaExpediente() {

		}
        
        public tramiteOficinaExpediente(tramTramiteSeguimientoC tramTramiteSeguimiento) {
        	this.tramTramiteSeguimiento=tramTramiteSeguimiento;

		}
       
		
		public oficinaC getOficina() {
			return oficina;
		}
		public void setOficina(oficinaC oficina) {
			this.oficina = oficina;
		}
		public tramTramiteSeguimientoC getTramTramiteSeguimiento() {
			return tramTramiteSeguimiento;
		}
		public void setTramTramiteSeguimiento(tramTramiteSeguimientoC tramTramiteSeguimiento) {
			this.tramTramiteSeguimiento = tramTramiteSeguimiento;
		} 
    	
		
        
    }
    
  

    public boolean isBandera() {
        return bandera;
    }

   
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    
    public List<tramTramitePersonaC> getTramTramitePersonaL() {
        return tramTramitePersonaL;
    }

    
    public void setTramTramitePersonaL(List<tramTramitePersonaC> tramTramitePersonaL) {
        this.tramTramitePersonaL = tramTramitePersonaL;
    }

   
    public tramTramitePersonaC getTramTramitePersona() {
        return tramTramitePersona;
    }

   
    public void setTramTramitePersona(tramTramitePersonaC tramTramitePersona) {
        this.tramTramitePersona = tramTramitePersona;
    }

  
    public List<String> getRequisitosL() {
        return requisitosL;
    }

  
    public void setRequisitosL(List<String> requisitosL) {
        this.requisitosL = requisitosL;
    }

  
    public personaC getPersona() {
        return persona;
    }

    
    public void setPersona(personaC persona) {
        this.persona = persona;
    }

    
    public alumnoC getAlumno() {
        return alumno;
    }

   
    public void setAlumno(alumnoC alumno) {
        this.alumno = alumno;
    }

  
    public tramTramiteSeguimientoC getTramTramiteSeguimiento() {
        return tramTramiteSeguimiento;
    }

 
    public void setTramTramiteSeguimiento(tramTramiteSeguimientoC tramTramiteSeguimiento) {
        this.tramTramiteSeguimiento = tramTramiteSeguimiento;
    }

   
 

    
    public tramTramiteC getTramTramite() {
		return tramTramite;
	}
	public void setTramTramite(tramTramiteC tramTramite) {
		this.tramTramite = tramTramite;
	}
	public String getBusExpediente() {
        return busExpediente;
    }

    
    public void setBusExpediente(String busExpediente) {
        this.busExpediente = busExpediente;
    }

   
    public tramTramitePersonaC getTramTramitePersonaT() {
        return tramTramitePersonaT;
    }

  
    public void setTramTramitePersonaT(tramTramitePersonaC tramTramitePersonaT) {
        this.tramTramitePersonaT = tramTramitePersonaT;
    }

    
    public int getOptFitro() {
        return optFitro;
    }

    
    public void setOptFitro(int optFitro) {
        this.optFitro = optFitro;
    }



    
    

	
	public tramTipoTramiteC getTramTipoTramite() {
		return tramTipoTramite;
	}
	public void setTramTipoTramite(tramTipoTramiteC tramTipoTramite) {
		this.tramTipoTramite = tramTipoTramite;
	}
	public oficinaC getOficina() {
		return oficina;
	}
	public void setOficina(oficinaC oficina) {
		this.oficina = oficina;
	}
	public List<tramiteOficinaExpediente> getTramiteOficinaExpedienteL() {
		return tramiteOficinaExpedienteL;
	}
	public void setTramiteOficinaExpedienteL(List<tramiteOficinaExpediente> tramiteOficinaExpedienteL) {
		this.tramiteOficinaExpedienteL = tramiteOficinaExpedienteL;
	}
	public List<tramiteOficinaExpediente> getTramiteOficinaExpedienteLT() {
		return tramiteOficinaExpedienteLT;
	}
	public void setTramiteOficinaExpedienteLT(List<tramiteOficinaExpediente> tramiteOficinaExpedienteLT) {
		this.tramiteOficinaExpedienteLT = tramiteOficinaExpedienteLT;
	}
	public List<tramTramitePersonaEmpresa> getTramTramitePersonaEmpresaL() {
		return tramTramitePersonaEmpresaL;
	}
	public void setTramTramitePersonaEmpresaL(List<tramTramitePersonaEmpresa> tramTramitePersonaEmpresaL) {
		this.tramTramitePersonaEmpresaL = tramTramitePersonaEmpresaL;
	}
	
	public tramTramitePersonaEmpresa getTramTramitePersonaEmpresa() {
		return tramTramitePersonaEmpresa;
	}
	public void setTramTramitePersonaEmpresa(tramTramitePersonaEmpresa tramTramitePersonaEmpresa) {
		this.tramTramitePersonaEmpresa = tramTramitePersonaEmpresa;
	}
	public List<oficinaC> getOficinaL() {
		return oficinaL;
	}

	public void setOficinaL(List<oficinaC> oficinaL) {
		this.oficinaL = oficinaL;
	}
	
	public List<tramTipoTramiteC> getTramTipoTramiteL() {
		return tramTipoTramiteL;
	}




	public void setTramTipoTramiteL(List<tramTipoTramiteC> tramTipoTramiteL) {
		this.tramTipoTramiteL = tramTipoTramiteL;
	}
	
	public String getCodigoT() {
		return codigoT;
	}




	public void setCodigoT(String codigoT) {
		this.codigoT = codigoT;
	}

}
