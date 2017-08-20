
package Beans;

import DAO.administrarPracticaDAO;
import DAO.alumnoCertificacionDAO;
import DAO.alumnoInformePracticaDAO;
import DAO.alumnoPracticaCronogramaCorteDAO;
import DAO.alumnoPracticaCronogramaDAO;
import DAO.alumnoPracticaDAO;
import DAO.detallePagoPersonaDAO;
import DAO.oficinaDAO;
import DAO.personaDAO;
import DAO.tramRequisitoDAO;
import DAO.tramRequisitoEntregadoDAO;
import DAO.tramTramitePersonaDAO;
import ENTIDAD.alumnoC;
import ENTIDAD.alumnoCertificacionC;
import ENTIDAD.alumnoCursoSeccionC;
import ENTIDAD.alumnoInformePracticaC;
import ENTIDAD.alumnoPracticaC;
import ENTIDAD.alumnoPracticaCronogramaC;
import ENTIDAD.alumnoPracticaCronogramaCorteC;

import ENTIDAD.cursosC;
import ENTIDAD.detallePagoPersonaC;
import ENTIDAD.oficinaC;
import ENTIDAD.personaC;
import ENTIDAD.seccionPeriodoC;
import ENTIDAD.tramRequisitoC;
import ENTIDAD.tramTramitePersonaC;
import ENTIDAD.tramTramiteRequisitoEntregadoC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.io.FilenameUtils;


import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;


@ManagedBean(name="administrarPracticaB")
@ViewScoped
public class administrarPractica {
    private List<alumnoPractica> alumnoPracticaL=new ArrayList<>();
    private alumnoPractica alumnoPracticaS=null;
    private List<alumnoInformePracticaC> alumnoInformePracticaL;
    private List<alumnoInformePracticaC> alumnoPlanPracticaL;
    private alumnoInformePracticaC alumnoInformePractica;
    
    private alumnoInformePracticaC alumnoInformePracticaS=new alumnoInformePracticaC();
    private List<cabeceraCronograma> cabeceraCronogramaL=new ArrayList<>();
    private alumnoPracticaCronogramaC alumnoPracticaCronograma;
    private alumnoPracticaCronograma alumnoPracticaCronogramaS=new alumnoPracticaCronograma();
    private List<alumnoPracticaCronograma> alumnoPracticaCronogramaL=new ArrayList<>();
    private alumnoPracticaCronogramaCorteC alumnoPracticaCronogramaCorte;
    private fichaActividad fichaActividad;
    private alumnoPracticaC alumnoPractica;
   
    private String paternoB="%";
    private String maternoB="%";
    private String nombresB="%";
    private UploadedFile uploadedFile;        
    private boolean bandera=false;
    private boolean banderaActividad;
    private int minutosProyectados=0;
    private int minutosEjecutados=0;
    private int minutosMax=12000;
    private String asunto;
    private String texto;
    private int estadoInforme;
    private DefaultStreamedContent download;
    private int diasSemanales;
    private Date fechaFin;
    private alumnoCertificacionC alumnoCertificacion=new alumnoCertificacionC();
    private int contDia;
    
    
    
    private List<tramRequisitoC> tramRequisitoL;
  
	private List<tramTramitePersonaC> tramTramitePersonaL;
    private tramTramitePersonaC tramTramitePersona=new tramTramitePersonaC();
    
    private String expediente=null;
    private String numeroComprobante;
    
    
    
    
    
    
    
    
    
    
    
    public String getNumeroComprobante() {
		return numeroComprobante;
	}
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}
	private List<String> requisito=new ArrayList<>(); ;
    
    
    
    
	
	

	private List<oficinaC> oficinaL;
    private List<personaC> personaTutorL;
    private boolean banderaTramite;
    
    
    private List<detallePagoPersonaC> detallePagoPersonaL;
    
    
    
    
    
    
 
	
	
	
	
	public List<detallePagoPersonaC> getDetallePagoPersonaL() {
		return detallePagoPersonaL;
	}
	public void setDetallePagoPersonaL(List<detallePagoPersonaC> detallePagoPersonaL) {
		this.detallePagoPersonaL = detallePagoPersonaL;
	}
	public administrarPractica() {
    	oficinaL=new oficinaDAO().mostrarOficinas(1);
    	
        
    }
    public void mostrarTramitePersona(){
    	
    	tramTramitePersona=new tramTramitePersonaDAO().mostrarTramitePersona(expediente);
    	tramRequisitoL=new tramRequisitoDAO().mostrarRequisitoEntregado(expediente,"%");
    	
    	
    	for (tramRequisitoC item : new tramRequisitoDAO().mostrarRequisitoEntregado(expediente,"1")) {
    		requisito.add(""+item.getRequisito());
    
		}
		
    }
    
    public boolean requisitoConstains(String texto){
    	for(String item  :requisito){
    		if(item.equals(texto)){
    			return true;
    		}
    	}
    	return false;
    }
    public void editarRequisitoPersona(){
    	banderaTramite=true;
    }
    public void cancelarRequisitoPersona(){
    	banderaTramite=false;
    }
    
    public void insertarRequisitoPersona(){
    	
    	
    	for (tramRequisitoC item:tramRequisitoL) {
    		
    	
			 if (requisitoConstains(""+item.getRequisito())){
				 
				 new tramRequisitoEntregadoDAO().insertar(new tramTramiteRequisitoEntregadoC(expediente, item.getRequisito(),true, util.fechaHoy(), 1));
			 }
			 else{
				 new tramRequisitoEntregadoDAO().insertar(new tramTramiteRequisitoEntregadoC(expediente, item.getRequisito(),false, null, 1));
			
			 }
			
		}
    	banderaTramite=false;
    }
    
    public void nuevoInforme(){
    	
    	alumnoInformePractica=new alumnoInformePracticaC(alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso(),2, -1, util.fechaHoy(), null, null, 1);
    
    }
    public void nuevoPlan(){
    	
    	alumnoInformePractica=new alumnoInformePracticaC(alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso(),1, -1, util.fechaHoy(), null, null, 1);
    
    }


  
    
    public void mostrarPersonaOficina(){
    	if(alumnoPractica!=null){
    		personaTutorL = new personaDAO().mostrarPersonaOficina(1,alumnoPractica.getOficina());
    	}
    	
    }
    
    
    public void busqueda(){
    	paternoB="";
    	maternoB="";
    	nombresB="";
    	util.script("dlgBusquedaAlumno.show()");
    }
    
    public void nuevoCronogramaCorte(){
        alumnoPracticaCronogramaCorte=new alumnoPracticaCronogramaCorteC(alumnoPracticaCronogramaS.alumno, alumnoPracticaCronogramaS.curso, alumnoPracticaCronogramaS.cronograma, null, null, null, 1);
       
        util.script("dlgCronogramaCorte.show()");
    }
    public void estadoPractica(){
    	alumnoPractica.setEstadoRegistro(alumnoPractica.getEstadoRegistro()==1?53:1);
    	new alumnoPracticaDAO().insertar(alumnoPractica);
    }
    
      public void guardarCronogramaCorte(){
          
          new alumnoPracticaCronogramaCorteDAO().insertar(alumnoPracticaCronogramaCorte);
          
          util.script("dlgCronogramaCorte.hide()");
      }
    
    public void nuevoCronograma(){
        alumnoPracticaCronograma=new alumnoPracticaCronogramaC(-1, alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso(), null, null, 1);
        
        util.script("dlgCronograma.show()");
        
    }
    
    public void editarCronograma(alumnoPracticaCronograma item){
    	
        alumnoPracticaCronograma=new alumnoPracticaCronogramaC(item.cronograma, item.alumno, item.curso, item.fechaInicio, item.fechaFinal, item.estadoRegistro);
      
        util.script("dlgCronograma.show()");
    }
    
     public void eliminarCronograma(alumnoPracticaCronograma item){
        alumnoPracticaCronograma=new alumnoPracticaCronogramaC(item.cronograma, item.alumno, item.curso, item.fechaInicio, item.fechaFinal, item.estadoRegistro);
        
        new  alumnoPracticaCronogramaDAO().eliminar(alumnoPracticaCronograma);        
        alumnoPracticaCronogramaL=new administrarPracticaDAO().mostrarAlumnoPracticaCronograma(alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso());
        
        
    }
    
    public void guardarCronograma(){
        
        new alumnoPracticaCronogramaDAO().insertar(alumnoPracticaCronograma);
        
     
        
        
        alumnoPracticaCronogramaL=new  administrarPracticaDAO().mostrarAlumnoPracticaCronograma(alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso());
        minutosProyectados=0;
        
        for (alumnoPracticaCronograma itemCronograma : alumnoPracticaCronogramaL) {
            minutosProyectados+=itemCronograma.minutoProyectado;
         
                alumnoPracticaCronogramaS=itemCronograma;
         
        }
        
        
        util.script("dlgCronograma.hide()");
        
    }
    
    public void seleccionCronograma(alumnoPracticaCronograma item){
        
        alumnoPracticaCronogramaS=item;
        
        cabeceraCronogramaL=new ArrayList<>();
      
           for (cabeceraCronograma itemFecha : new administrarPracticaDAO().mostrarCabeceraCronograma(alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso(),alumnoPracticaCronogramaS.cronograma)) {
            for (fichaActividad itemActividad : new administrarPracticaDAO().mostrarFichaActividad(alumnoPracticaCronogramaS.cronograma,alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso(), itemFecha.fecha)) {
                
                itemFecha.getFichaActividadL().add(itemActividad);
            }
            cabeceraCronogramaL.add(itemFecha);
        } 
       
        
    }
    

    
    
    public void calcularFecha(){
        
    }
    public void mostrarCronograma(){
        cabeceraCronogramaL=new ArrayList<>();
        
        
        
        for (alumnoPracticaCronograma itemCronograma : alumnoPracticaCronogramaL) {
            minutosProyectados+=itemCronograma.minutoProyectado; 
            minutosEjecutados+=itemCronograma.minutoEjecutado;
        }        
       if (alumnoPracticaCronogramaS !=null){
           for (cabeceraCronograma itemFecha : new administrarPracticaDAO().mostrarCabeceraCronograma(alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso(),alumnoPracticaCronogramaS.getCronograma())) {
            for (fichaActividad itemActividad : new administrarPracticaDAO().mostrarFichaActividad(alumnoPracticaCronogramaS.getCronograma(),alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso(), itemFecha.fecha)) {
                
                itemFecha.getFichaActividadL().add(itemActividad);
            }
            cabeceraCronogramaL.add(itemFecha);
        } 
       }
       
    }
    
    public void nuevo(){
        bandera=true;
        
        alumnoPractica=new alumnoPracticaC(alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso(), 0, null, null, util.fechaHoy(),null, null, 1);;
    }
    public void cancelar(){
        
    	alumnoPractica=new alumnoPracticaDAO().mostrarAlumnoPractica(alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso());
        bandera=false;
    }
    
    public void editar(){        
        
        bandera=true;
    }
    
    public void guardar(){
       
     
        new alumnoPracticaDAO().insertar(alumnoPractica);
        bandera=false;
    }
    public void nuevoActividad(){
        fichaActividad=new fichaActividad();
        fichaActividad.setCronogroma(getAlumnoPracticaCronogramaS().getCronograma());
        fichaActividad.setAlumno(alumnoPracticaS.alumno.getAlumno());
        fichaActividad.setCurso(alumnoPracticaS.curso.getCurso());
        fichaActividad.setEstadoRegistro(1);
        banderaActividad=true;
        util.script("dlgRegistroCronograma.show()");
    }
    public void nuevaActividadDiaria(int dia,Date fecha){
        fichaActividad=new fichaActividad();
        fichaActividad.setCronogroma(getAlumnoPracticaCronogramaS().getCronograma());
        fichaActividad.setAlumno(alumnoPracticaS.alumno.getAlumno());
        fichaActividad.setCurso(alumnoPracticaS.curso.getCurso());
        fichaActividad.setDia(dia);
        fichaActividad.fecha=fecha;
        fichaActividad.fechaFin=fecha;
        fichaActividad.setEstadoRegistro(1);
        banderaActividad=true;
        util.script("dlgRegistroCronograma.show()");
    }
    
    public void eliminarActividad(fichaActividad item ){
        
        new administrarPracticaDAO().eliminar(item);
        
        
              
        alumnoPracticaCronogramaL=new administrarPracticaDAO().mostrarAlumnoPracticaCronograma(alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso());
        minutosProyectados=0;
         for (alumnoPracticaCronograma itemCronograma : alumnoPracticaCronogramaL) {
            minutosProyectados+=itemCronograma.minutoProyectado; 
        }
        cabeceraCronogramaL=new ArrayList<>();
     
        
      
           for (cabeceraCronograma itemFecha : new administrarPracticaDAO().mostrarCabeceraCronograma(alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso(),alumnoPracticaCronogramaS.cronograma)) {
            for (fichaActividad itemActividad : new administrarPracticaDAO().mostrarFichaActividad(alumnoPracticaCronogramaS.cronograma,alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso(), itemFecha.fecha)) {
                
                itemFecha.getFichaActividadL().add(itemActividad);
            }
            cabeceraCronogramaL.add(itemFecha);
        }
    }
    
    public void editarActividad(fichaActividad item ){
        
        fichaActividad=item;
        fichaActividad.fechaFin =fichaActividad.fecha;
        banderaActividad=false;
        util.script("dlgRegistroCronograma.show()");
    }
    public void guardarActividad(){
    	
    	int hora=(int) util.dateIff(fichaActividad.horaInicio, fichaActividad.horaFinal, 2);
    	System.out.println(hora);
    	if(hora >=6){
    		 if (banderaActividad){
    	            new administrarPracticaDAO().insertar(fichaActividad);
    	        }else{ //editar            
    	          
    	            new administrarPracticaDAO().editar(fichaActividad);
    	        }
    	        
    	        
    	          
    	        alumnoPracticaCronogramaL=new administrarPracticaDAO().mostrarAlumnoPracticaCronograma(alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso());
    	        minutosProyectados=0;
    	         for (alumnoPracticaCronograma itemCronograma : alumnoPracticaCronogramaL) {
    	            minutosProyectados+=itemCronograma.minutoProyectado; 
    	        }
    	         
    	         
    	        cabeceraCronogramaL=new ArrayList<>();
    	     
    	        
    	      
    	           for (cabeceraCronograma itemFecha : new administrarPracticaDAO().mostrarCabeceraCronograma(alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso(),alumnoPracticaCronogramaS.cronograma)) {
    	            for (fichaActividad itemActividad : new administrarPracticaDAO().mostrarFichaActividad(alumnoPracticaCronogramaS.cronograma,alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso(), itemFecha.fecha)) {
    	                
    	                itemFecha.getFichaActividadL().add(itemActividad);
    	            }
    	            cabeceraCronogramaL.add(itemFecha);
    	        }
    	        
    	          
    	        util.script("dlgRegistroCronograma.hide()");
    	}
    	else{
    		util.script("notificacion('el minimo de horas por dia es de 6 horas',500,5000,'error')");
    	}
    	
    	
       
      
    }
    
       public void prepDownload(String archivo) throws Exception {
    
       File file = new File(archivo);
       InputStream input = new FileInputStream(file);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();  
        setDownload(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
}
       public  String calcularTiempo(int tiempo){
           int horas;
           int minutos;
           String cadena;
           horas = tiempo / 60;        
           minutos = (tiempo % 60);
           cadena=horas+":"+minutos;
           return cadena;
       }
       
    public void save() throws IOException  {

        String archivo;
        
        if(alumnoInformePractica.getTipo()==1){
        	archivo="Plan_Trabajo_" +(alumnoPlanPracticaL.size()+1)+"."+FilenameUtils.getExtension(uploadedFile.getFileName());
        }else{
        	archivo="Informe_" +(alumnoInformePracticaL.size()+1)+"."+FilenameUtils.getExtension(uploadedFile.getFileName());
        }
     
        String ruta="D:\\FTPUSER\\upig.edu.pe\\practicas\\"+alumnoPracticaS.alumno +"\\"+alumnoPracticaS.curso;
        util.crearDirectorio(ruta);
        
        
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
    inputStream = uploadedFile.getInputstream(); //leemos el fichero local
    // write the inputStream to a FileOutputStream
    outputStream = new FileOutputStream(new File(ruta+"\\"+archivo));
 
    int read = 0;
    byte[] bytes = new byte[1024];
 
    while ((read = inputStream.read(bytes)) != -1) {
       outputStream.write(bytes, 0, read);
    }
    
    
    	alumnoInformePractica.setInforme(archivo);;
    	alumnoInformePractica.setRuta(ruta);
  
         new alumnoInformePracticaDAO().insertar(alumnoInformePractica);
      
        
        alumnoPlanPracticaL=new alumnoInformePracticaDAO().mostrarAlumnoInforme(alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso(),"1");
        alumnoInformePracticaL=new alumnoInformePracticaDAO().mostrarAlumnoInforme(alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso(),"2");
        
   } catch (IOException e) {
            System.out.println(e.getMessage());
   } finally {
        if (inputStream != null) {
       inputStream.close();
       
    }
    if (outputStream != null) {
       outputStream.close();
    }
    
   }

}
    
    
  public void enviarCorreo(){
   String para = "maicol_24@hotmail.com";
    String de = "sistemas.upig@upig.edu.pe";

    
    String host = "smtp.office365.com";
    String puerto = "587";
    String usuario = "sistemas.upig@upig.edu.pe";
    String clave = "Sistemas2010**";

   
    Properties propiedades = new Properties();

  
   
    propiedades.setProperty("mail.smtp.host", host);// hotmail "smtp.live.com" -- gmail "smtp.gmail.com" ofice368  "smtp.office365.com"
    propiedades.setProperty("mail.smtp.starttls.enable", "true"); //     
    propiedades.setProperty("mail.smtp.port", puerto); // // hotmail "25" -- gmail "587" ofice365 "587"    
    propiedades.setProperty("mail.smtp.password", clave);
    propiedades.setProperty("mail.smtp.mail.sender", usuario);//correo de upig gmail o yahoo
    propiedades.setProperty("mail.smtp.user", usuario);
    propiedades.setProperty("mail.smtp.auth", "true");
 
    // Obtenemos la sesión por defecto
    Session sesion = Session.getDefaultInstance(propiedades);
    

    try{
   
      MimeMessage mensaje = new MimeMessage(sesion);


      mensaje.setFrom(new InternetAddress(de));
      

   
      mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
      
      // copia oculta
  
       
    //  mensaje.addRecipient(Message.RecipientType.BCC, new InternetAddress("jgutierrez@upig.edu.pe"));
   
      
      
////      Address[] addresses=new Address[personaProductoL.size()] ;
////      
////        for (int x=0;x<= personaProductoL.size()-1;x++) {
////            
////            addresses[x] =new InternetAddress(personaProductoL.get(x).correo);
////        }
// 
//      
//      mensaje.addRecipients(Message.RecipientType.TO, addresses);

      // Asignamos el asunto
      mensaje.setSubject(asunto);

      // Asignamos el mensaje como tal
      //mensaje.setText(texto);
      
      // MENSAJE CON CONTENIDO HTML
      mensaje.setContent(texto, "text/html");
      
      
      
      // Enviamos el correo
      //Transport.send(mensaje);
          Transport t = sesion.getTransport("smtp");
          t.connect((String) propiedades.get("mail.smtp.user"), (String) propiedades.get("mail.smtp.password"));
          t.sendMessage(mensaje, mensaje.getAllRecipients());
          t.close();
      
      
      System.out.println("Mensaje enviado");
    
      util.script("dlgCorreo.hide()");
      
    } catch (MessagingException e) {
      System.out.println("error: " + e.getMessage());
      
    }
    
    }
  
  
    

    
    
    public void filtroAlumnoPractica(){
        
        alumnoPracticaL=new administrarPracticaDAO().mostrarAlumnoPractica(paternoB, maternoB, nombresB);
    }
    
    public void procesarInforme(alumnoInformePracticaC item,int estado){
       
        alumnoInformePracticaS=item;
        estadoInforme=estado;
        asunto=estado==2?"aprobado":"desaprobado";
        util.script("dlgCorreo.show()");
    
    }
    public void aprobarInforme(alumnoInformePracticaC item){
    	 item.setAprobado(true);
    	 item.setFechaRevisado(util.fechaHoy());
    	 new alumnoInformePracticaDAO().insertar(item);
    	 
    	 if(item.getTipo()==1){
    		 
    		 alumnoPlanPracticaL=new alumnoInformePracticaDAO().mostrarAlumnoInforme(alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso(),"1");
    		 alumnoPractica.setFechaPlan(util.fechaHoy());
    	 }else{    		 
    		 alumnoInformePracticaL=new alumnoInformePracticaDAO().mostrarAlumnoInforme(alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso(),"2");
    		 alumnoPractica.setFechaInforme(util.fechaHoy());    		 
    	 }
    	 
    	 new alumnoPracticaDAO().insertar(alumnoPractica);
    	 alumnoPractica=new alumnoPracticaDAO().mostrarAlumnoPractica(alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso());    
    	
        
    
    }
    public void deshaprobarInforme(alumnoInformePracticaC item){
        
    	item.setAprobado(false);
    	item.setFechaRevisado(util.fechaHoy());
    	new alumnoInformePracticaDAO().insertar(item);
    	alumnoInformePracticaL=new alumnoInformePracticaDAO().mostrarAlumnoInforme(alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso(),"2");
   	 	alumnoPlanPracticaL=new alumnoInformePracticaDAO().mostrarAlumnoInforme(alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso(),"1");
        
    }
    
    
    public void seleccionBusqueda() {
    	
    	
    	expediente="";
    	tramRequisitoL=null;
    	tramTramitePersona=new tramTramitePersonaC();
    	contDia=(int) util.dateIff(alumnoPracticaS.getPeriodoSeccion().getFechaInicio(), util.fechaHoy(), 3); 
        alumnoPractica=new alumnoPracticaDAO().mostrarAlumnoPractica(alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso());    
   
        alumnoInformePracticaL=new alumnoInformePracticaDAO().mostrarAlumnoInforme(alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso(),"2");
        alumnoPlanPracticaL=new alumnoInformePracticaDAO().mostrarAlumnoInforme(alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso(),"1");        
        alumnoPracticaCronogramaL=new administrarPracticaDAO().mostrarAlumnoPracticaCronograma(alumnoPracticaS.alumno.getAlumno(), alumnoPracticaS.curso.getCurso());
        alumnoCertificacion=new alumnoCertificacionDAO().mostrarUltimoAlumnoCertificacion(alumnoPracticaS.alumnoCursoSeccion.getInstitucion(), alumnoPracticaS.alumnoCursoSeccion.getPeriodo(), alumnoPracticaS.alumnoCursoSeccion.getCarrera(), alumnoPracticaS.alumnoCursoSeccion.getMalla(), alumnoPracticaS.curso.getCurso(), alumnoPracticaS.alumnoCursoSeccion.getSeccion(), alumnoPracticaS.alumno.getAlumno());
        
        alumnoPracticaCronogramaS=null;
        minutosProyectados=0;
        minutosEjecutados=0;
        mostrarCronograma();
        mostrarPersonaOficina();
        tramTramitePersonaL=new  tramTramitePersonaDAO().mostrarTramitePersona(alumnoPracticaS.persona.getPersona(), 75);
        if(tramTramitePersonaL.size()!=0){
        	expediente=tramTramitePersonaL.get(0).getExpediente();
        	mostrarTramitePersona();
        }
        detallePagoPersonaL=new detallePagoPersonaDAO().mostrarDetallePagoPersona("1", "0000001706", alumnoPracticaS.persona.getPersona());
    }
    
    
    public List<alumnoPractica> getAlumnoPracticaL() {
        return alumnoPracticaL;
    }

    
    public void setAlumnoPracticaL(List<alumnoPractica> alumnoPracticaL) {
        this.alumnoPracticaL = alumnoPracticaL;
    }

    
    public alumnoPractica getAlumnoPracticaS() {
        return alumnoPracticaS;
    }

   
    public void setAlumnoPracticaS(alumnoPractica alumnoPracticaS) {
        this.alumnoPracticaS = alumnoPracticaS;
    }

    
    public String getPaternoB() {
        return paternoB;
    }

    
    public void setPaternoB(String paternoB) {
        this.paternoB = paternoB;
    }

   
    public String getMaternoB() {
        return maternoB;
    }

   
    public void setMaternoB(String maternoB) {
        this.maternoB = maternoB;
    }

    
    public String getNombresB() {
        return nombresB;
    }

   
    public void setNombresB(String nombresB) {
        this.nombresB = nombresB;
    }

    
    public List<alumnoInformePracticaC> getAlumnoInformePracticaL() {
        return alumnoInformePracticaL;
    }

    
    public void setAlumnoInformePracticaL(List<alumnoInformePracticaC> alumnoInformePracticaL) {
        this.alumnoInformePracticaL = alumnoInformePracticaL;
    }

    
    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

   
    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    
    public boolean isBandera() {
        return bandera;
    }

    
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    
    public alumnoPracticaC getAlumnoPractica() {
        return alumnoPractica;
    }

    
    public void setAlumnoPractica(alumnoPracticaC alumnoPractica) {
        this.alumnoPractica = alumnoPractica;
    }

   
    public List<cabeceraCronograma> getCabeceraCronogramaL() {
        return cabeceraCronogramaL;
    }

    
    public void setCabeceraCronogramaL(List<cabeceraCronograma> cabeceraCronogramaL) {
        this.cabeceraCronogramaL = cabeceraCronogramaL;
    }



    
    public fichaActividad getFichaActividad() {
        return fichaActividad;
    }

    
    public void setFichaActividad(fichaActividad fichaActividad) {
        this.fichaActividad = fichaActividad;
    }

  

   
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

   
    public String getAsunto() {
        return asunto;
    }

   
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

  
    public int getEstadoInforme() {
        return estadoInforme;
    }

   
    public void setEstadoInforme(int estadoInforme) {
        this.estadoInforme = estadoInforme;
    }

 
    public alumnoInformePracticaC getAlumnoInformePracticaS() {
        return alumnoInformePracticaS;
    }

  
    public void setAlumnoInformePracticaS(alumnoInformePracticaC alumnoInformePracticaS) {
        this.alumnoInformePracticaS = alumnoInformePracticaS;
    }

   
    public boolean isBanderaActividad() {
        return banderaActividad;
    }

    
    public void setBanderaActividad(boolean banderaActividad) {
        this.banderaActividad = banderaActividad;
    }

   
    public int getMinutosMax() {
        return minutosMax;
    }

    
    public void setMinutosMax(int minutosMax) {
        this.minutosMax = minutosMax;
    }

 

   
    public DefaultStreamedContent getDownload() {
        return download;
    }

    
    public void setDownload(DefaultStreamedContent download) {
        this.download = download;
    }

    
 

    
    public int getDiasSemanales() {
        return diasSemanales;
    }

    
    public void setDiasSemanales(int diasSemanales) {
        this.diasSemanales = diasSemanales;
    }

    
    public Date getFechaFin() {
        return fechaFin;
    }

   
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    
    public List<alumnoPracticaCronograma> getAlumnoPracticaCronogramaL() {
        return alumnoPracticaCronogramaL;
    }

   
    public void setAlumnoPracticaCronogramaL(List<alumnoPracticaCronograma> alumnoPracticaCronogramaL) {
        this.alumnoPracticaCronogramaL = alumnoPracticaCronogramaL;
    }

    
    public alumnoPracticaCronogramaC getAlumnoPracticaCronograma() {
        return alumnoPracticaCronograma;
    }

   
    public void setAlumnoPracticaCronograma(alumnoPracticaCronogramaC alumnoPracticaCronograma) {
        this.alumnoPracticaCronograma = alumnoPracticaCronograma;
    }

    
    public alumnoPracticaCronograma getAlumnoPracticaCronogramaS() {
        return alumnoPracticaCronogramaS;
    }

    
    public void setAlumnoPracticaCronogramaS(alumnoPracticaCronograma alumnoPracticaCronogramaS) {
        this.alumnoPracticaCronogramaS = alumnoPracticaCronogramaS;
    }

    
    public alumnoPracticaCronogramaCorteC getAlumnoPracticaCronogramaCorte() {
        return alumnoPracticaCronogramaCorte;
    }

    
    public void setAlumnoPracticaCronogramaCorte(alumnoPracticaCronogramaCorteC alumnoPracticaCronogramaCorte) {
        this.alumnoPracticaCronogramaCorte = alumnoPracticaCronogramaCorte;
    }

    
    public int getMinutosEjecutados() {
        return minutosEjecutados;
    }

    
    public void setMinutosEjecutados(int minutosEjecutados) {
        this.minutosEjecutados = minutosEjecutados;
    }

   
    public int getMinutosProyectados() {
        return minutosProyectados;
    }

   
    public void setMinutosProyectados(int minutosProyectados) {
        this.minutosProyectados = minutosProyectados;
    }

   
    
    
    public static class alumnoPracticaCronograma{
        private int cronograma;
        private String alumno;
        private String curso;
        private Date fechaInicio;
        private Date fechaFinal;
        private int minutoProyectado;
        private int minutoEjecutado;
        private int corte;
        private Date fechaCorte;
        private String fut;
        private String obervacion;
        private int estadoRegistro;

        
        public int getCronograma() {
            return cronograma;
        }

        
        public void setCronograma(int cronograma) {
            this.cronograma = cronograma;
        }

      
        public String getAlumno() {
            return alumno;
        }

       
        public void setAlumno(String alumno) {
            this.alumno = alumno;
        }

       
        public String getCurso() {
            return curso;
        }

       
        public void setCurso(String curso) {
            this.curso = curso;
        }

        
        public Date getFechaInicio() {
            return fechaInicio;
        }

        
        public void setFechaInicio(Date fechaInicio) {
            this.fechaInicio = fechaInicio;
        }

    

      

       
        public Date getFechaFinal() {
            return fechaFinal;
        }

       
        public void setFechaFinal(Date fechaFinal) {
            this.fechaFinal = fechaFinal;
        }

      
        public int getEstadoRegistro() {
            return estadoRegistro;
        }

       
        public void setEstadoRegistro(int estadoRegistro) {
            this.estadoRegistro = estadoRegistro;
        }

       
        public int getCorte() {
            return corte;
        }

       
        public void setCorte(int corte) {
            this.corte = corte;
        }

        
        public Date getFechaCorte() {
            return fechaCorte;
        }

       
        public void setFechaCorte(Date fechaCorte) {
            this.fechaCorte = fechaCorte;
        }

       
        public String getObervacion() {
            return obervacion;
        }

        
        public void setObervacion(String obervacion) {
            this.obervacion = obervacion;
        }

      
        public String getFut() {
            return fut;
        }

       
        public void setFut(String fut) {
            this.fut = fut;
        }

       
        public int getMinutoProyectado() {
            return minutoProyectado;
        }

        
        public void setMinutoProyectado(int minutoProyectado) {
            this.minutoProyectado = minutoProyectado;
        }

      
        public int getMinutoEjecutado() {
            return minutoEjecutado;
        }

        
        public void setMinutoEjecutado(int minutoEjecutado) {
            this.minutoEjecutado = minutoEjecutado;
        }
        
    }
    
    
    
    public static class cabeceraCronograma{
        private Date fecha;
        private int dia;
        private List<fichaActividad> fichaActividadL=new ArrayList<>();

        public cabeceraCronograma() {
        }

        public cabeceraCronograma(Date fecha, int dia) {
            this.fecha = fecha;
            this.dia = dia;
        }

        
        
        public Date getFecha() {
            return fecha;
        }

       
        public void setFecha(Date fecha) {
            this.fecha = fecha;
        }

      
        public int getDia() {
            return dia;
        }

       
        public void setDia(int dia) {
            this.dia = dia;
        }

      
        public List<fichaActividad> getFichaActividadL() {
            return fichaActividadL;
        }

        
        public void setFichaActividadL(List<fichaActividad> fichaActividadL) {
            this.fichaActividadL = fichaActividadL;
        }
        
    }
    
    public static class fichaActividad{
        private int cronogroma;
        private String alumno;
        private String curso;
        private int dia;
        private Date fecha;
        private Date fechaFin;
        private Date horaInicio;
        private Date horaFinal;
        private String actividad;
        private int duracion;
        private int alto;
        private int distancia;
        private int distanciaFicha;
        private int altoFicha;
        private Date horaEntrada;
        private Date horaSalida;
        private int estadoRegistro;

       
        public String getAlumno() {
            return alumno;
        }

       
        public void setAlumno(String alumno) {
            this.alumno = alumno;
        }

        
        public String getCurso() {
            return curso;
        }

       
        public void setCurso(String curso) {
            this.curso = curso;
        }

        
        public int getDia() {
            return dia;
        }

       
        public void setDia(int dia) {
            this.dia = dia;
        }

       
        public Date getFecha() {
            return fecha;
        }

      
        public void setFecha(Date fecha) {
            this.fecha = fecha;
        }

        
        public Date getHoraInicio() {
            return horaInicio;
        }

        
        public void setHoraInicio(Date horaInicio) {
            this.horaInicio = horaInicio;
        }

       
        public Date getHoraFinal() {
            return horaFinal;
        }

       
        public void setHoraFinal(Date horaFinal) {
            this.horaFinal = horaFinal;
        }

     
        public String getActividad() {
            return actividad;
        }

     
        public void setActividad(String actividad) {
            this.actividad = actividad;
        }

        public int getAlto() {
            return alto;
        }

       
        public void setAlto(int alto) {
            this.alto = alto;
        }

        
        public int getDistancia() {
            return distancia;
        }

        
        public void setDistancia(int distancia) {
            this.distancia = distancia;
        }

       
        public Date getFechaFin() {
            return fechaFin;
        }

      
        public void setFechaFin(Date fechaFin) {
            this.fechaFin = fechaFin;
        }

       
        public int getEstadoRegistro() {
            return estadoRegistro;
        }

      
        public void setEstadoRegistro(int estadoRegistro) {
            this.estadoRegistro = estadoRegistro;
        }

     
        public int getDuracion() {
            return duracion;
        }

    
        public void setDuracion(int duracion) {
            this.duracion = duracion;
        }

     
        public int getCronogroma() {
            return cronogroma;
        }

    
        public void setCronogroma(int cronogroma) {
            this.cronogroma = cronogroma;
        }

       
        public Date getHoraEntrada() {
            return horaEntrada;
        }

       
        public void setHoraEntrada(Date horaEntrada) {
            this.horaEntrada = horaEntrada;
        }

       
        public Date getHoraSalida() {
            return horaSalida;
        }

        
        public void setHoraSalida(Date horaSalida) {
            this.horaSalida = horaSalida;
        }

        
        public int getDistanciaFicha() {
            return distanciaFicha;
        }

      
        public void setDistanciaFicha(int distanciaFicha) {
            this.distanciaFicha = distanciaFicha;
        }

       
        public int getAltoFicha() {
            return altoFicha;
        }

     
        public void setAltoFicha(int altoFicha) {
            this.altoFicha = altoFicha;
        }
    }
    
    public static class alumnoPractica{
    	
    	private personaC persona=new personaC();
    	private cursosC curso=new cursosC();
    	private alumnoC alumno=new alumnoC();
    	private alumnoCursoSeccionC alumnoCursoSeccion=new alumnoCursoSeccionC(); 
    	private seccionPeriodoC periodoSeccion=new seccionPeriodoC();
    	
    	
    	
    	
    	public personaC getPersona() {
			return persona;
		}
		public void setPersona(personaC persona) {
			this.persona = persona;
		}
		public cursosC getCurso() {
			return curso;
		}
		public void setCurso(cursosC curso) {
			this.curso = curso;
		}
		public alumnoC getAlumno() {
			return alumno;
		}
		public void setAlumno(alumnoC alumno) {
			this.alumno = alumno;
		}
		public alumnoCursoSeccionC getAlumnoCursoSeccion() {
			return alumnoCursoSeccion;
		}
		public void setAlumnoCursoSeccion(alumnoCursoSeccionC alumnoCursoSeccion) {
			this.alumnoCursoSeccion = alumnoCursoSeccion;
		}
		public seccionPeriodoC getPeriodoSeccion() {
			return periodoSeccion;
		}
		public void setPeriodoSeccion(seccionPeriodoC periodoSeccion) {
			this.periodoSeccion = periodoSeccion;
		}
		
    	
    	
        
       
        
    }
    
    public alumnoCertificacionC getAlumnoCertificacion() {
		return alumnoCertificacion;
	}
	public void setAlumnoCertificacion(alumnoCertificacionC alumnoCertificacion) {
		this.alumnoCertificacion = alumnoCertificacion;
	}
	public List<oficinaC> getOficinaL() {
		return oficinaL;
	}
	public void setOficinaL(List<oficinaC> oficinaL) {
		this.oficinaL = oficinaL;
	}
	public List<personaC> getPersonaTutorL() {
		return personaTutorL;
	}
	public void setPersonaTutorL(List<personaC> personaTutorL) {
		this.personaTutorL = personaTutorL;
	}
	
	
	 public int getContDia() {
			return contDia;
		}

		public void setContDia(int contDia) {
			this.contDia = contDia;
		}
		public List<alumnoInformePracticaC> getAlumnoPlanPracticaL() {
			return alumnoPlanPracticaL;
		}

		public void setAlumnoPlanPracticaL(List<alumnoInformePracticaC> alumnoPlanPracticaL) {
			this.alumnoPlanPracticaL = alumnoPlanPracticaL;
		}
		public alumnoInformePracticaC getAlumnoInformePractica() {
			return alumnoInformePractica;
		}

		public void setAlumnoInformePractica(alumnoInformePracticaC alumnoInformePractica) {
			this.alumnoInformePractica = alumnoInformePractica;
		}

		public List<tramRequisitoC> getTramRequisitoL() {
			return tramRequisitoL;
		}
		public void setTramRequisitoL(List<tramRequisitoC> tramRequisitoL) {
			this.tramRequisitoL = tramRequisitoL;
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
		  public String getExpediente() {
				return expediente;
			}
			public void setExpediente(String expediente) {
				this.expediente = expediente;
			}
			public List<String> getRequisito() {
				return requisito;
			}
			public void setRequisito(List<String> requisito) {
				this.requisito = requisito;
			}
			
			   public boolean isBanderaTramite() {
					return banderaTramite;
				}
				public void setBanderaTramite(boolean banderaTramite) {
					this.banderaTramite = banderaTramite;
				}


}
