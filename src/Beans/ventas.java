

package Beans;


import DAO.accesoDAO;

import DAO.cursoDAO;

import DAO.cursoSeccionDocenteDAO;
import DAO.descuentoDAO;
import DAO.frecuenciaDiaDAO;
import DAO.frecuenciaHoraDAO;
import DAO.personaDAO;
import DAO.personaInformeSegDAO;
import DAO.personalDAO;
import DAO.tipoProductoDAO;
import DAO.ventasDAO;
import ENTIDAD.accesoC;

import ENTIDAD.cursoSeccionDocenteC;
import ENTIDAD.cursosC;
import ENTIDAD.descuentoC;
import ENTIDAD.frecuenciaDiaC;
import ENTIDAD.frecuenciaHoraC;
import ENTIDAD.periodoSeccionVencimientoC;
import ENTIDAD.personaC;
import ENTIDAD.personaInformeSegC;
import ENTIDAD.personalC;
import ENTIDAD.productoC;
import ENTIDAD.seccionPeriodoC;
import ENTIDAD.tipoProductoC;


import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;

import org.primefaces.event.CellEditEvent;


import javax.mail.*;
import javax.mail.internet.*;


@ManagedBean(name="ventaB")
@ViewScoped
public class ventas implements Serializable{
 
	private static final long serialVersionUID = 1L;
	private accesoC accesos;
    private List<institucionCarrera> institucionCarreraL;
    private institucionCarrera institucionCarreraS;
    private List<carreraSecciones> carreraSeccionesL;
    private List<carreraSecciones> carreraSeccionesInterezL;
    private carreraSecciones carreraSeccionesS=new carreraSecciones();
    private List<seccionCurso> seccionCursoL;
    private List<descuentoSeccion> descuentoSeccionL=new ArrayList<>();
    private List<frecuenciaDiaC> frecuenciaDiaL;
    private List<frecuenciaDiaC> filtroFrecuenciaDiaL=new ArrayList<>();
   
	private frecuenciaDiaC frecuenciaDiaS;
    private List<frecuenciaHoraC> frecuenciaHoraL;
    private frecuenciaHoraC frecuenciaHoraS;
    private personaInformeSegC personaInformeSeg;
    private personaC  persona=new personaC();
  
    private personaC  personaDocente=new personaC();
    private descuentoC  descuento;
    private productoC producto=new productoC(); 
    
    private List<productoCarrera> productoCarreraL;
    private productoCarrera productoCarreraS;
    private List<personaProducto> personaProductoL;
    
    private periodoSeccionVencimientoC periodoSeccionVecimiento=new periodoSeccionVencimientoC();
    private List<detalleProductoConcepto> detalleProductoConceptoL=new ArrayList<>();
    private detalleProductoConcepto detalleProductoConcepto =new detalleProductoConcepto();
    
    private List<tipoProductoC> tipoProductoL=new ArrayList<>();
    
  
	private int accion=1;
    private int accionProducto=1;
    private int accionSecion=0;
    private seccionPeriodoC seccionPeriodo=new seccionPeriodoC();

 
    private String[] productoCurso;

    
    private boolean bandera=true;
    private boolean subBandera=true;
    
    private boolean banderaPersona=true;
    private int tipoFrecuencia;
    private String filtroEstado="40";
    private String filtroTipoFrecuencia="%";
    private String filtroTurno="%";
    private String filtroAnio=""+util.a�o();
    private String filtroMes="%";
    private String filtroSede="%";
    private String filtroTipoProducto="%";
    private String filtroDesProducto="";
    private String descuentoConcepto;
    private String asunto;
    private String contenidoCorreo;
    private String filtroFrecuenciaDia="%";
    
    
 
	
	private String busPaterno;
 
	private String busMaterno;
    private String busNombres;
    private personalC personal=new personalC();
    
   
    

    cursoSeccionDocenteDAO cursoSeccionDocenteD;
    
    public ventas() {
    	
    }
    private String usuario="";
    private int institucion;
    private String periodo="";
    private String codPersonal="";
    
    public void load(int institucion,String periodo, String usuario,String codPersonal){
    	this.institucion=institucion;
    	this.periodo=periodo;
    	this.usuario=usuario;
    	this.codPersonal=codPersonal;
    	institucionCarreraL=new ventasDAO().mostrarCarreraInstitucion(institucion, periodo,"%");
    }
    
    





	public void nuevoCorreo(int institucion,String periodo,carreraSecciones item){
        carreraSeccionesS=item;
        asunto="";
        contenidoCorreo="";
        personaProductoL=new ArrayList<>();
        ventaD=new ventasDAO();
        personaProductoL= ventaD.mostrarPersonaInterezado(institucion, periodo, carreraSeccionesS.carrera, carreraSeccionesS.seccion);
        
        
        util.render("frmVenta:pnCorreo");
    }
    public void eliminarCorreo(int indice){
        personaProductoL.remove(indice);
    }
    
    
    
    
    public void enviarCorreo(){
        
        
        // La dirección de envío (to)
  //  String para = "molano@upig.edu.pe";

    // La dirección de la cuenta de envío (from)
    String de = "anavarro@upig.edu.pe";

    // El servidor (host). En este caso usamos localhost
    String host = "smtp.office365.com";
    String puerto = "587";
    String usuario = "anavarro@upig.edu.pe";
    String clave = "@lv@r0-nb1";

    // Obtenemos las propiedades del sistema
    Properties propiedades = new Properties();

    // Configuramos el servidor de correo
   
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
      // Creamos un objeto mensaje tipo MimeMessage por defecto.
      MimeMessage mensaje = new MimeMessage(sesion);

    
      mensaje.setFrom(new InternetAddress(de));     

   
    //  mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
      
      // copia oculta
  
      
      mensaje.addRecipient(Message.RecipientType.BCC, new InternetAddress("jgutierrez@upig.edu.pe"));
      
      
      
      Address[] addresses=new Address[personaProductoL.size()] ;
      
        for (int x=0;x<= personaProductoL.size()-1;x++) {
            
            addresses[x] =new InternetAddress(personaProductoL.get(x).correo);
        }
 
      
      mensaje.addRecipients(Message.RecipientType.TO, addresses);

      // Asignamos el asunto
      mensaje.setSubject(asunto);

      // Asignamos el mensaje como tal
      //mensaje.setText(contenidoCorreo);
      
      // MENSAJE CON CONTENIDO HTML
      mensaje.setContent(contenidoCorreo, "text/html");
      
      
      
      // Enviamos el correo
      //Transport.send(mensaje);
          Transport t = sesion.getTransport("smtp");
          t.connect((String) propiedades.get("mail.smtp.user"), (String) propiedades.get("mail.smtp.password"));
          t.sendMessage(mensaje, mensaje.getAllRecipients());
          t.close();
      
      
      System.out.println("Mensaje enviado");
      util.script("dlgCorreo.hide()");
      
    } catch (MessagingException e) {
      
      util.alert(e.getMessage());
    }
    
    }
            
    
    
    
    
    public void validarPermisos(){
         
         accesos = new accesoDAO().mostrarAccesos(usuario, 15, 105);
    }
   
      public void onCellEdit(CellEditEvent event) {
          
        //Object oldValue = event.getOldValue();
        //Object newValue = event.getNewValue();
          descuentoSeccionL.get(event.getRowIndex()).setMontoDecuento(12);
    }
      
   public void limpiarBusqueda(){
        busPaterno="";
        busMaterno="";
        busNombres="";
        util.script("dlgBusquedaAlumno.show()");
      
    }
   
   
   public void nuevaCursoPersonal(carreraSecciones item){
       carreraSeccionesS=item;
       personaDocente=new personaC();
       personal=new personalC();
       util.script("PF('pnCargaElectiva').show()");
               
   }
   
   
   
   
   public void insertarCursoPersonal(seccionCurso item){
    cursoSeccionDocenteD=new cursoSeccionDocenteDAO();
    ventaD=new ventasDAO();
    item.setPersonal(personal.getPersonal());
    cursoSeccionDocenteD.insertar(new cursoSeccionDocenteC( item.institucion, item.periodo, item.carrera,item.malla, item.grupo, item.curso, item.seccion, item.personal, 1, 1, 1));
    carreraSeccionesS.getSeccionCursoL().clear();
    for(seccionCurso itemCurso : ventaD.mostrarSeccionCurso(item.institucion, item.periodo, item.carrera, item.seccion)){
            carreraSeccionesS.getSeccionCursoL().add(itemCurso);
    }
}
   
   
   
    public void eliminarCursoPersonal(seccionCurso item){
            cursoSeccionDocenteD=new cursoSeccionDocenteDAO();
            ventaD=new ventasDAO();            
            cursoSeccionDocenteD.eliminar(new cursoSeccionDocenteC( item.institucion, item.periodo, item.carrera,item.malla, item.grupo, item.curso, item.seccion, item.personal, 1, 1, 0));
            carreraSeccionesS.getSeccionCursoL().clear();
            for(seccionCurso itemCurso : ventaD.mostrarSeccionCurso(item.institucion, item.periodo, item.carrera, item.seccion)){
                    carreraSeccionesS.getSeccionCursoL().add(itemCurso);
            }
    }
    
     public void eliminarTodosCursoPersonal(){
            cursoSeccionDocenteD=new cursoSeccionDocenteDAO();
            ventaD=new ventasDAO();         
            for(seccionCurso item : carreraSeccionesS.getSeccionCursoL()){
                if (item.personal !=null){
                    if(item.personal.endsWith(personal.getPersonal()) ){
                    cursoSeccionDocenteD.eliminar(new cursoSeccionDocenteC( item.institucion, item.periodo, item.carrera,item.malla, item.grupo, item.curso, item.seccion, item.personal, 1, 1, 0));
                }
                }
                
                
            }
            
            carreraSeccionesS.getSeccionCursoL().clear();
            for(seccionCurso itemCurso : ventaD.mostrarSeccionCurso(carreraSeccionesS.institucion, carreraSeccionesS.periodo, carreraSeccionesS.carrera, carreraSeccionesS.seccion)){
                    carreraSeccionesS.getSeccionCursoL().add(itemCurso);
            }
    }
   
   
      public void nuevoSeccionVencimiento(carreraSecciones item ){
          carreraSeccionesS=item;
          periodoSeccionVecimiento=new periodoSeccionVencimientoC(carreraSeccionesS.getInstitucion(),carreraSeccionesS.getPeriodo(),carreraSeccionesS.getCarrera(),carreraSeccionesS.getSeccion(),0,null,0,1);
          util.script("PF('dlgFechaVencimiento').show()");
      }
      public void preEliminar(){
          subBandera=false;
          accionSecion=3;
      }
       public void preCancelar(){
          subBandera=true;
          accionSecion=0;
      }
        public void preImprimir(){
          subBandera=false;
          accionSecion=6;
      }
       
      public void insertarSeccionVencimiento(){
          ventaD=new ventasDAO();
          ventaD.insertarSeccionVencimiento(periodoSeccionVecimiento) ;
          mostrarSeccion(institucion,periodo,institucionCarreraS);
          util.script("PF('dlgFechaVencimiento').hide()");
      }
      public void insertarProducto(){
        
          if (detalleProductoConceptoL.size()>0){
                ventaD=new ventasDAO(); 
                ventaD.insertarProducto(institucionCarreraS.institucion, producto.getProducto(), producto.getDescripcion(), producto.getTipoProducto(), "0000000000", institucionCarreraS.carrera, tipoFrecuencia,detalleProductoConceptoL,productoCurso);
                productoCarreraL= ventaD.mostrarProductoCarrera(institucionCarreraS.institucion, institucionCarreraS.carrera);
                util.script("PF('dlgProducto').hide()");
                util.render("frmVenta:pnManteProductoSeccion");
          }else {
              util.alert("EL PRODUCTO DEBE DE CONTENER POR LO MENOS UN CONCEPTO");
          }
          
      }
      
      
      public void calcularDescuento(int indice ){
          double precio=descuentoSeccionL.get(indice).getMontoPago();
          double total=0.0;
          descuentoD=new descuentoDAO();
                  
          descuento= descuentoD.mostrarDescuento(descuentoSeccionL.get(indice).descuento);
          
          if (descuento.getTipoDescuento()==2){              
              total=(precio * descuento.getCantidad())/100;
             descuentoSeccionL.get(indice).setMontoDecuento(total);
             descuentoSeccionL.get(indice).setTotal(precio-total);
          }else if(descuento.getTipoDescuento()==1){
            
             descuentoSeccionL.get(indice).setMontoDecuento(descuento.getCantidad());
             descuentoSeccionL.get(indice).setTotal(precio-descuento.getCantidad());
          }
          
         
      }
    
    
    public void seleccionPersona(){
        
        bandera=false;
        if(institucionCarreraS != null){
            mostrarSeccion(institucion,periodo,institucionCarreraS);
        }
         
        
    }
    personalDAO personalD;
    public void seleccionPersonaDocente(){
        personalD=new personalDAO();
        personal=personalD.mostrarPersona(personaDocente.getPersona());
     
    }
    
    public void mostrarMatriculados(int institucion,String periodo,carreraSecciones item){
        carreraSeccionesS=item;
        ventaD=new ventasDAO();
        personaProductoL= ventaD.mostrarPersonaProducto(institucion, periodo, carreraSeccionesS.carrera, carreraSeccionesS.seccion);
        util.script("dlgMatriculados.show()");
        
    }
    public void nuevoCarreraProducto(){
        accion=1;
        institucionCarreraL=ventaD.mostrarCarreraInstitucion(institucion, periodo,"%");
    }
    
    public void cancelarPersona(){
        if (!banderaPersona){
            banderaPersona=true;
    }else{
            persona=new personaC();
            accion=1;
            bandera=true;
        }
    }
    
    
    public void editarPersona(){
        banderaPersona=false;
        util.script("dlgPersonal.show()");
    }

    
    public void nuevaPersona(){
        persona=new personaC();
        persona.setPersona("");
        persona.setPais(143);
        util.script("PF('dlgPersonal').show()");
    }
    public void ingresoPersona(){
    	personaC item=new personaDAO().validaDNI(persona.getPersona(), persona.getNumero_documento());
    	if(item==null){
    		persona.setPersona(new personaDAO().insertar(persona));      
            seleccionPersona();
            util.script("PF('dlgPersonal').hide()");
            util.script("PF('dlgBusqueda').hide()");
            util.render("frmVenta:pnDatos");
            util.render("frmVenta:pnBotenera");
            util.render("frmVenta:pnPrincipal");
    	}else{
    		
    		util.script("notificacion('El DNI "+ item.getNumero_documento()+"  le pertenece "+ item.nombreCompletos() +"',500,5000,'error')");
    	}
        
        
    }
  
    
   
    public void atrasProducto(){
    	if(accionProducto>1){
    		accionProducto-=1;	
    	}
    	
    }
    
    
    
    
    public void eliminarSeccionProducto(carreraSecciones item){
        
        int cantDocente=0;
        for(seccionCurso itemCursoDocente : item.getSeccionCursoL()){
            if (itemCursoDocente.personal!=null){
                cantDocente++;
            }
        }
        
        if (cantDocente==0){
            if (item.matriculados==0){
            ventaD=new ventasDAO();
            ventaD.ElimnarProductoSeccion(item.institucion, item.periodo, item.carrera, item.seccion, item.paquete, item.producto);
            mostrarSeccion(institucion,periodo,institucionCarreraS);
            util.render("frmVenta:tbSeccion");
        }else{
                 
            util.script("notificacion('La seccion tiene alumnos matriculados',500,5000,'error')");
        }
            
        }else{
                     
            util.script("notificacion('La seccion tiene docentes asignados',500,5000,'error')");
        }
        
        
    }
    public void nuevoProducto(){
        producto=new productoC();
        detalleProductoConceptoL=new ArrayList<>();       
    }

    
    public void nuevoProductoSeccion(){
       accionProducto=1;
       ventaD=new ventasDAO();
       productoCarreraL= ventaD.mostrarProductoCarrera(institucionCarreraS.institucion, institucionCarreraS.carrera);
       seccionPeriodo=new seccionPeriodoC();
       
       frecuenciaDiaL=new ArrayList<>();
       frecuenciaHoraL=new ArrayList<>();
       
       seccionPeriodo.setSeccion(null);
       seccionPeriodo.setInstitucion(institucion);
       seccionPeriodo.setPeriodo(periodo);
       seccionPeriodo.setSede(1);
       seccionPeriodo.setCarrera(institucionCarreraS.carrera);
       seccionPeriodo.setVacantes(20);
       seccionPeriodo.setNivelModular(1);
       seccionPeriodo.setEstadoRegistro(40);
       util.script("PF('dlgSeccion').show()");
       
    }
    public void nuevoFrecuenciaDia(productoCarrera item){
        accionProducto=2;
        productoCarreraS=item;
        frecuenciaDiaD=new frecuenciaDiaDAO();
        frecuenciaDiaL=frecuenciaDiaD.mostrarFrecuenciaDia(productoCarreraS.tipoFrecuencia);
        
        seccionPeriodo.setPaquete(productoCarreraS.getPaqueda());
        seccionPeriodo.setProducto(productoCarreraS.getProducto());
        
    }
    
    public void nuevoFrecuenciaHora(frecuenciaDiaC item){
        accionProducto=3;
        frecuenciaDiaS=item;
        frecuenciaHoraD=new frecuenciaHoraDAO();
        frecuenciaHoraL=frecuenciaHoraD.mostrarFrecuenciaHora(productoCarreraS.tipoFrecuencia,frecuenciaDiaS.getFrecuenciaDia());
        seccionPeriodo.setFrecuenciaDia(frecuenciaDiaS.getFrecuenciaDia());
    }
    public void nuevoProductoFrecuencia(frecuenciaHoraC item){
        accionProducto=4;
        frecuenciaHoraS=item;
        seccionPeriodo.setTurno(frecuenciaHoraS.getTurno());
        seccionPeriodo.setFrecuenciaHora(frecuenciaHoraS.getFrecuenciaHora());
    }
    
    
    public void insertarProductoSeccion(){
        String rpta;
        rpta=ventaD.insertarProductoSeccion(seccionPeriodo);
        if(rpta.endsWith("EXITO")){
            mostrarSeccion(institucion,periodo,institucionCarreraS);
            util.alert("SE GENERO NUEVO PRODUCTO CORRECTAMENTE");
            util.script("PF('dlgSeccion').hide()");
        }else{
            
            util.alert(rpta);
        }
            
    }   
 
    public void actualizar(){
        ventaD=new ventasDAO();
      institucionCarreraL=ventaD.mostrarCarreraInstitucion(institucion, periodo,"%");
      accion=1;
    }
    

    public void atras(){
        ventaD=new ventasDAO();
        institucionCarreraL=ventaD.mostrarCarreraInstitucion(institucion, periodo,"%");
        accion=1;
    }
    
    public void eliminarMatricula(carreraSecciones item){
       
        carreraSeccionesS=item;
        
        personaInformeSeg=new personaInformeSegC(persona.getPersona(), carreraSeccionesS.informe, carreraSeccionesS.institucion, carreraSeccionesS.paquete, carreraSeccionesS.producto, carreraSeccionesS.carrera, "grupo", carreraSeccionesS.periodo, carreraSeccionesS.seccion, codPersonal, 1, 1, 0, 1);        
        
        personaInformeSegD=new personaInformeSegDAO();
        personaInformeSegD.insertar(personaInformeSeg);        
        
        ventaD=new ventasDAO();
        
        ventaD.insertar(2,item.institucion, item.periodo, item.carrera, item.seccion,item.paquete,item.producto, persona.getPersona(),item.vencimiento,item.nivelModular);
        if (accion==2){
            mostrarSeccionInterez();
        }else{
           mostrarSeccion(institucion,periodo,institucionCarreraS);
        }
       
    }
    
    public void matricularDescuento(){
        
        
       if(carreraSeccionesS.seccionCursoL.size()>0){
            ventaD=new ventasDAO();
            ventaD.insertar(3,carreraSeccionesS.institucion  , carreraSeccionesS.periodo, carreraSeccionesS.carrera, carreraSeccionesS.seccion, carreraSeccionesS.paquete, carreraSeccionesS.producto, persona.getPersona(), (carreraSeccionesS.venVencimiento==0?carreraSeccionesS.vencimiento:carreraSeccionesS.venVencimiento), carreraSeccionesS.nivelModular, descuentoSeccionL);

            personaInformeSeg=new personaInformeSegC(persona.getPersona(), carreraSeccionesS.informe, carreraSeccionesS.institucion, carreraSeccionesS.paquete, carreraSeccionesS.producto, carreraSeccionesS.carrera, "grupo", carreraSeccionesS.periodo, carreraSeccionesS.seccion, codPersonal , 1, 1, 1, 1);                
            personaInformeSegD=new personaInformeSegDAO();
            personaInformeSegD.insertar(personaInformeSeg);

             if (accion==2){
                 mostrarSeccionInterez();
             }else{
                mostrarSeccion(institucion,periodo,institucionCarreraS); 
             }
             util.script("PF('dlgDescuento').hide()");
       } else{
            util.alert("NO TIENE CURSOS ASIGNADOS CONSULTE CON EL ADMINISTRADOR DE BASE DATOS");
       }
      
    }
    
    public void matricula(carreraSecciones item){
    	
    	
    	new ventasDAO().insertar(institucion, periodo, persona.getPersona(), item.carrera,"201401",item.seccion,"1",item.curso);
    	
    }
    
    public void matricular( carreraSecciones item){
    	
    

        if (item.seccionCursoL.size()>0){
                    
            new ventasDAO().insertar(1,item.institucion, item.periodo, item.carrera, item.seccion,item.paquete,item.producto, persona.getPersona(),(item.venVencimiento==0?item.vencimiento:item.venVencimiento),item.nivelModular);
            carreraSeccionesS=item;        
            /*
            for(cursosC itemCurso: item.cursoL){
            	new alumnoCursoSeccionDAO().insertar(new alumnoCursoSeccionC(item.institucion, item.periodo, item.carrera, null, itemCurso.getCurso(), item.seccion, alumno.getAlumno(), null, 0.0, 1));
            }
          */
            
            
            
            personaInformeSeg=new personaInformeSegC(persona.getPersona(), carreraSeccionesS.informe, carreraSeccionesS.institucion, carreraSeccionesS.paquete, carreraSeccionesS.producto, carreraSeccionesS.carrera, "grupo", carreraSeccionesS.periodo, carreraSeccionesS.seccion, codPersonal, 1, 1, 1, 1);                
         
            new personaInformeSegDAO().insertar(personaInformeSeg);

            if (accion==2){
                mostrarSeccionInterez();
            }else{
               mostrarSeccion(institucion,periodo,institucionCarreraS); 
            }
        }else{
            util.alert("NO TIENE CURSOS ASIGNADOS CONSULTE CON EL ADMINISTRADOR DE BASE DATOS");
        }
        
      
        
    }
    public void cerrarMatricula(carreraSecciones item){
        ventaD=new ventasDAO();
        ventaD.cerrarMatricula(item.institucion, item.periodo, item.carrera, item.seccion, 42); // estado de cerrado
         mostrarSeccion(institucion,periodo,institucionCarreraS); 
    }
    
    public void interezado(carreraSecciones item){
        carreraSeccionesS=item;
        personaInformeSegD=new personaInformeSegDAO();
        personaInformeSeg=new personaInformeSegC(persona.getPersona(), carreraSeccionesS.informe, carreraSeccionesS.institucion, carreraSeccionesS.paquete, carreraSeccionesS.producto, carreraSeccionesS.carrera, "grupo", carreraSeccionesS.periodo, carreraSeccionesS.seccion, codPersonal, 1, 1, 0, 1);                
       
        personaInformeSegD.insertar(personaInformeSeg);
        mostrarSeccion(institucion,periodo,institucionCarreraS);
    }   
    public void filtrar(){
         mostrarSeccion(institucion,periodo,institucionCarreraS);
    }
    
    public void mostrarSeccion(int institucion,String periodo,institucionCarrera item){
    	
    	
    	
     
        institucionCarreraS=item;
        carreraSeccionesL=new ArrayList<>();
        ventaD=new ventasDAO();
        accion=0;
        if (persona ==null){
            persona=new personaC();
        }
        
       
        
        for (carreraSecciones itemSeccion : ventaD.mostrarCarreraSeccion(3,institucion, periodo,"%", item.carrera,persona.getPersona(),filtroEstado,filtroTipoFrecuencia,filtroTurno,filtroAnio,filtroMes,filtroTipoProducto,filtroDesProducto,filtroFrecuenciaDia)) {
           
            for (seccionCurso itemCurso : ventaD.mostrarSeccionCurso(itemSeccion.getInstitucion(), itemSeccion.getPeriodo(), itemSeccion.getCarrera(), itemSeccion.getSeccion())) {
                               
                itemSeccion.getSeccionCursoL().add(itemCurso)  ;
            }
             for(cursosC itemCurso: new cursoDAO().mostrarCursoVenta(itemSeccion.getInstitucion(), itemSeccion.getPeriodo(), itemSeccion.getCarrera(), itemSeccion.getSeccion(),"2014200006")){
            	 itemSeccion.cursoL.add(itemCurso);
             }
            
            carreraSeccionesL.add(itemSeccion);
            
            
        }
        //-- filtro tipo produto
        tipoProductoL=new tipoProductoDAO().mostrarTipoProducto(institucion,periodo,item.carrera);
        filtroFrecuenciaDiaL=new frecuenciaDiaDAO().mostrarFrecuenciaDia(institucion,periodo,item.carrera);
     
    }
    
    public void seleccionCarrera(int institucion,String periodo,institucionCarrera item){
    	filtroDesProducto="";
    	filtroTipoProducto="%";
    	filtroMes="%";
    	mostrarSeccion(institucion,periodo,item);
    }
    
  
    
    
    public void mostrarSeccionInterez(){
        
        carreraSeccionesInterezL=new ArrayList<>();
        ventaD=new ventasDAO();
        accion=2;
        for (carreraSecciones itemSeccion : ventaD.mostrarCarreraSeccion(4,institucion, periodo,"%", "%",persona.getPersona(),"%","%","%","%","%","%","%","%")) {
           
            for (seccionCurso itemCurso : ventaD.mostrarSeccionCurso(itemSeccion.getInstitucion(), itemSeccion.getPeriodo(), itemSeccion.getCarrera(), itemSeccion.getSeccion())) {
                               
                itemSeccion.getSeccionCursoL().add(itemCurso)  ;
            }
            carreraSeccionesInterezL.add(itemSeccion);
            
            
        }
    }
    
    public void agregarProductoConcepto(){
        detalleProductoConcepto.concepto="0000000000";
        detalleProductoConcepto.periodo=periodo;
        detalleProductoConcepto.descuento=descuentoConcepto;
        detalleProductoConceptoL.add(detalleProductoConcepto);
        detalleProductoConcepto=new detalleProductoConcepto();
        
    }
    
   public void eliminarProductoConcepto(int indice){
        detalleProductoConceptoL.remove(indice);
        
    }
   public void agregarDescuentoConcepto(int indice){
      
       detalleProductoConceptoL.get(indice).descuentoL.add(new descuentoC(descuentoConcepto,"",0,0,0,0));
       
   }
    
    
    public void mostrarConceptoSeccion(carreraSecciones item){
        carreraSeccionesS=item;
        ventaD=new ventasDAO();
        descuentoSeccionL=ventaD.mostrarDescuentoSeccion(item.institucion, item.periodo, item.carrera, item.seccion);
        util.script("PF('dlgDescuento').show()");
    }
    
    ventasDAO ventaD;
    personaDAO personaD;
    personaInformeSegDAO personaInformeSegD;
    frecuenciaHoraDAO frecuenciaHoraD;
    frecuenciaDiaDAO frecuenciaDiaD;
    descuentoDAO descuentoD;

    public List<institucionCarrera> getInstitucionCarreraL() {
        return institucionCarreraL;
    }

    public void setInstitucionCarreraL(List<institucionCarrera> institucionCarreraL) {
        this.institucionCarreraL = institucionCarreraL;
    }

    public List<carreraSecciones> getCarreraSeccionesL() {
        return carreraSeccionesL;
    }

    public void setCarreraSeccionesL(List<carreraSecciones> carreraSeccionesL) {
        this.carreraSeccionesL = carreraSeccionesL;
    }

    public List<seccionCurso> getSeccionCursoL() {
        return seccionCursoL;
    }

    public void setSeccionCursoL(List<seccionCurso> seccionCursoL) {
        this.seccionCursoL = seccionCursoL;
    }

    

    public personaInformeSegC getPersonaInformeSeg() {
        return personaInformeSeg;
    }

    public void setPersonaInformeSeg(personaInformeSegC personaInformeSeg) {
        this.personaInformeSeg = personaInformeSeg;
    }

    public carreraSecciones getCarreraSeccionesS() {
        return carreraSeccionesS;
    }

    public void setCarreraSeccionesS(carreraSecciones carreraSeccionesS) {
        this.carreraSeccionesS = carreraSeccionesS;
    }

    public List<descuentoSeccion> getDescuentoSeccionL() {
        return descuentoSeccionL;
    }

    public void setDescuentoSeccionL(List<descuentoSeccion> descuentoSeccionL) {
        this.descuentoSeccionL = descuentoSeccionL;
    }

    public institucionCarrera getInstitucionCarreraS() {
        return institucionCarreraS;
    }

    public void setInstitucionCarreraS(institucionCarrera institucionCarreraS) {
        this.institucionCarreraS = institucionCarreraS;
    }

    public personaC getPersona() {
        return persona;
    }

    public void setPersona(personaC persona) {
        this.persona = persona;
    }

    public int getAccion() {
        return accion;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }

    public List<productoCarrera> getProductoCarreraL() {
        return productoCarreraL;
    }

    public void setProductoCarreraL(List<productoCarrera> productoCarreraL) {
        this.productoCarreraL = productoCarreraL;
    }

    public List<frecuenciaDiaC> getFrecuenciaDiaL() {
        return frecuenciaDiaL;
    }

    public void setFrecuenciaDiaL(List<frecuenciaDiaC> frecuenciaDiaL) {
        this.frecuenciaDiaL = frecuenciaDiaL;
    }

    public List<frecuenciaHoraC> getFrecuenciaHoraL() {
        return frecuenciaHoraL;
    }

    public void setFrecuenciaHoraL(List<frecuenciaHoraC> frecuenciaHoraL) {
        this.frecuenciaHoraL = frecuenciaHoraL;
    }

    public frecuenciaDiaC getFrecuenciaDiaS() {
        return frecuenciaDiaS;
    }

    public void setFrecuenciaDiaS(frecuenciaDiaC frecuenciaDiaS) {
        this.frecuenciaDiaS = frecuenciaDiaS;
    }

    public frecuenciaHoraC getFrecuenciaHoraS() {
        return frecuenciaHoraS;
    }

    public void setFrecuenciaHoraS(frecuenciaHoraC frecuenciaHoraS) {
        this.frecuenciaHoraS = frecuenciaHoraS;
    }

    public productoCarrera getProductoCarreraS() {
        return productoCarreraS;
    }

    public void setProductoCarreraS(productoCarrera productoCarreraS) {
        this.productoCarreraS = productoCarreraS;
    }

    public seccionPeriodoC getSeccionPeriodo() {
        return seccionPeriodo;
    }

    public void setSeccionPeriodo(seccionPeriodoC seccionPeriodo) {
        this.seccionPeriodo = seccionPeriodo;
    }

    public int getAccionProducto() {
        return accionProducto;
    }

    public void setAccionProducto(int accionProducto) {
        this.accionProducto = accionProducto;
    }



    public boolean isBandera() {
        return bandera;
    }

    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    public String getFiltroEstado() {
        return filtroEstado;
    }

    public void setFiltroEstado(String filtroEstado) {
        this.filtroEstado = filtroEstado;
    }

    public String getFiltroTipoFrecuencia() {
        return filtroTipoFrecuencia;
    }

    public void setFiltroTipoFrecuencia(String filtroTipoFrecuencia) {
        this.filtroTipoFrecuencia = filtroTipoFrecuencia;
    }


    public String getFiltroTurno() {
        return filtroTurno;
    }

    public void setFiltroTurno(String filtroTurno) {
        this.filtroTurno = filtroTurno;
    }

 

    public List<personaProducto> getPersonaProductoL() {
        return personaProductoL;
    }

    public void setPersonaProductoL(List<personaProducto> personaProductoL) {
        this.personaProductoL = personaProductoL;
    }

    public boolean isSubBandera() {
        return subBandera;
    }

    public void setSubBandera(boolean subBandera) {
        this.subBandera = subBandera;
    }

    public List<carreraSecciones> getCarreraSeccionesInterezL() {
        return carreraSeccionesInterezL;
    }

    public void setCarreraSeccionesInterezL(List<carreraSecciones> carreraSeccionesInterezL) {
        this.carreraSeccionesInterezL = carreraSeccionesInterezL;
    }

    public descuentoC getDescuento() {
        return descuento;
    }

    public void setDescuento(descuentoC descuento) {
        this.descuento = descuento;
    }

    public String getFiltroAnio() {
        return filtroAnio;
    }

    public void setFiltroAnio(String filtroAnio) {
        this.filtroAnio = filtroAnio;
    }

    public String getFiltroMes() {
        return filtroMes;
    }

    public void setFiltroMes(String filtroMes) {
        this.filtroMes = filtroMes;
    }

    public accesoC getAccesos() {
        return accesos;
    }

    public void setAccesos(accesoC accesos) {
        this.accesos = accesos;
    }

    public periodoSeccionVencimientoC getPeriodoSeccionVecimiento() {
        return periodoSeccionVecimiento;
    }

    public void setPeriodoSeccionVecimiento(periodoSeccionVencimientoC periodoSeccionVecimiento) {
        this.periodoSeccionVecimiento = periodoSeccionVecimiento;
    }

    public List<detalleProductoConcepto> getDetalleProductoConceptoL() {
        return detalleProductoConceptoL;
    }

    public void setDetalleProductoConceptoL(List<detalleProductoConcepto> detalleProductoConceptoL) {
        this.detalleProductoConceptoL = detalleProductoConceptoL;
    }

    public detalleProductoConcepto getDetalleProductoConcepto() {
        return detalleProductoConcepto;
    }

    public void setDetalleProductoConcepto(detalleProductoConcepto detalleProductoConcepto) {
        this.detalleProductoConcepto = detalleProductoConcepto;
    }

 
    public productoC getProducto() {
        return producto;
    }

    public void setProducto(productoC producto) {
        this.producto = producto;
    }

    public String[] getProductoCurso() {
        return productoCurso;
    }

    public void setProductoCurso(String[] productoCurso) {
        this.productoCurso = productoCurso;
    }

    public int getTipoFrecuencia() {
        return tipoFrecuencia;
    }

    public void setTipoFrecuencia(int tipoFrecuencia) {
        this.tipoFrecuencia = tipoFrecuencia;
    }

    public String getDescuentoConcepto() {
        return descuentoConcepto;
    }

    public void setDescuentoConcepto(String descuentoConcepto) {
        this.descuentoConcepto = descuentoConcepto;
    }

    public int getAccionSecion() {
        return accionSecion;
    }

    public void setAccionSecion(int accionSecion) {
        this.accionSecion = accionSecion;
    }

    public boolean isBanderaPersona() {
        return banderaPersona;
    }

    public void setBanderaPersona(boolean banderaPersona) {
        this.banderaPersona = banderaPersona;
    }

    public String getBusPaterno() {
        return busPaterno;
    }

    public void setBusPaterno(String busPaterno) {
        this.busPaterno = busPaterno;
    }

    public String getBusMaterno() {
        return busMaterno;
    }

    public void setBusMaterno(String busMaterno) {
        this.busMaterno = busMaterno;
    }

    public String getBusNombres() {
        return busNombres;
    }

    public void setBusNombres(String busNombres) {
        this.busNombres = busNombres;
    }

    public personalC getPersonal() {
        return personal;
    }

    public void setPersonal(personalC personal) {
        this.personal = personal;
    }

    public personaC getPersonaDocente() {
        return personaDocente;
    }

    public void setPersonaDocente(personaC personaDocente) {
        this.personaDocente = personaDocente;
    }

    public String getFiltroSede() {
        return filtroSede;
    }

    public void setFiltroSede(String filtroSede) {
        this.filtroSede = filtroSede;
    }


    public String getAsunto() {
        return asunto;
    }


    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }


    public String getContenidoCorreo() {
        return contenidoCorreo;
    }

    public void setContenidoCorreo(String contenidoCorreo) {
        this.contenidoCorreo = contenidoCorreo;
    }
    public static class productoCarrera{
        private int institucion;
        private String carrera;
        private String paqueda;
        private String producto;
        private String desProducto;
        private int tipoFrecuencia;
        private int estadoRegistro;

        public int getInstitucion() {
            return institucion;
        }

        public void setInstitucion(int institucion) {
            this.institucion = institucion;
        }

        public String getCarrera() {
            return carrera;
        }

        public void setCarrera(String carrera) {
            this.carrera = carrera;
        }

        public String getPaqueda() {
            return paqueda;
        }

        public void setPaqueda(String paqueda) {
            this.paqueda = paqueda;
        }

        public String getProducto() {
            return producto;
        }

        public void setProducto(String producto) {
            this.producto = producto;
        }

        public String getDesProducto() {
            return desProducto;
        }

        public void setDesProducto(String desProducto) {
            this.desProducto = desProducto;
        }

        public int getEstadoRegistro() {
            return estadoRegistro;
        }

        public void setEstadoRegistro(int estadoRegistro) {
            this.estadoRegistro = estadoRegistro;
        }

        public int getTipoFrecuencia() {
            return tipoFrecuencia;
        }

        public void setTipoFrecuencia(int tipoFrecuencia) {
            this.tipoFrecuencia = tipoFrecuencia;
        }
                
    }
    
    
    public static class descuentoSeccion{
        
        private int tipoConcepto;
        private String desTipoConcepto;
        private String concepto;
        private String desConcepto;
        private double montoPago;
        private int numCuota;
        private int tipoMoneda;
        private String desTipoMoneda;
        private String descuento;
        private String desDescuento;
        private double montoDecuento;
        private double total;

        public int getTipoConcepto() {
            return tipoConcepto;
        }

        public void setTipoConcepto(int tipoConcepto) {
            this.tipoConcepto = tipoConcepto;
        }

        public String getDesTipoConcepto() {
            return desTipoConcepto;
        }

        public void setDesTipoConcepto(String desTipoConcepto) {
            this.desTipoConcepto = desTipoConcepto;
        }

        public String getConcepto() {
            return concepto;
        }

        public void setConcepto(String concepto) {
            this.concepto = concepto;
        }

        public String getDesConcepto() {
            return desConcepto;
        }


        public void setDesConcepto(String desConcepto) {
            this.desConcepto = desConcepto;
        }

        public double getMontoPago() {
            return montoPago;
        }

        public void setMontoPago(double montoPago) {
            this.montoPago = montoPago;
        }

        public int getNumCuota() {
            return numCuota;
        }

        public void setNumCuota(int numCuota) {
            this.numCuota = numCuota;
        }

        public String getDescuento() {
            return descuento;
        }

        public void setDescuento(String descuento) {
            this.descuento = descuento;
        }

        public String getDesDescuento() {
            return desDescuento;
        }

        public void setDesDescuento(String desDescuento) {
            this.desDescuento = desDescuento;
        }

        public double getMontoDecuento() {
            return montoDecuento;
        }

        public void setMontoDecuento(double montoDecuento) {
            this.montoDecuento = montoDecuento;
        }

        public int getTipoMoneda() {
            return tipoMoneda;
        }

        public void setTipoMoneda(int tipoMoneda) {
            this.tipoMoneda = tipoMoneda;
        }

        public String getDesTipoMoneda() {
            return desTipoMoneda;
        }

        public void setDesTipoMoneda(String desTipoMoneda) {
            this.desTipoMoneda = desTipoMoneda;
        }


        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }
    }
    
    public static class seccionCurso{
        private int institucion;
        private String periodo;
        private String malla;
        private String carrera;
        private String seccion;
        private String grupo;
        private String curso;
        private String descurso;
        private Boolean marcar;
        private String persona;
        private String personal;
        private String desPersonal;

        public seccionCurso() {
        }

        public int getInstitucion() {
            return institucion;
        }

        public void setInstitucion(int institucion) {
            this.institucion = institucion;
        }

        public String getPeriodo() {
            return periodo;
        }

        public void setPeriodo(String periodo) {
            this.periodo = periodo;
        }

        public String getCarrera() {
            return carrera;
        }

        public void setCarrera(String carrera) {
            this.carrera = carrera;
        }

        public String getSeccion() {
            return seccion;
        }

        public void setSeccion(String seccion) {
            this.seccion = seccion;
        }

        public String getCurso() {
            return curso;
        }

        public void setCurso(String curso) {
            this.curso = curso;
        }

        public String getDescurso() {
            return descurso;
        }

        public void setDescurso(String descurso) {
            this.descurso = descurso;
        }

        public Boolean getMarcar() {
            return marcar;
        }

        public void setMarcar(Boolean marcar) {
            this.marcar = marcar;
        }

        public String getPersona() {
            return persona;
        }

        public void setPersona(String persona) {
            this.persona = persona;
        }

        public String getPersonal() {
            return personal;
        }

        public void setPersonal(String personal) {
            this.personal = personal;
        }


        public String getDesPersonal() {
            return desPersonal;
        }

        public void setDesPersonal(String desPersonal) {
            this.desPersonal = desPersonal;
        }

        public String getMalla() {
            return malla;
        }

        public void setMalla(String malla) {
            this.malla = malla;
        }

        public String getGrupo() {
            return grupo;
        }

        public void setGrupo(String grupo) {
            this.grupo = grupo;
        }
    }
    
    public static class personaProducto{
        private String alumno;
        private String persona;
        private String paterno;
        private String materno;
        private String nombres;
        private String telefono;
        private String celular;
        private String correo;

        public personaProducto() {
        }

        public personaProducto(String alumno, String persona, String paterno, String materno, String nombres, String telefono, String celular, String correo) {
            this.alumno = alumno;
            this.persona = persona;
            this.paterno = paterno;
            this.materno = materno;
            this.nombres = nombres;
            this.telefono = telefono;
            this.celular = celular;
            this.correo = correo;
        }

        public String getAlumno() {
            return alumno;
        }

        public void setAlumno(String alumno) {
            this.alumno = alumno;
        }

        public String getPersona() {
            return persona;
        }

        public void setPersona(String persona) {
            this.persona = persona;
        }

        public String getPaterno() {
            return paterno;
        }

        public void setPaterno(String paterno) {
            this.paterno = paterno;
        }


        public String getMaterno() {
            return materno;
        }

        public void setMaterno(String materno) {
            this.materno = materno;
        }

        public String getNombres() {
            return nombres;
        }

        public void setNombres(String nombres) {
            this.nombres = nombres;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public String getCelular() {
            return celular;
        }

        public void setCelular(String celular) {
            this.celular = celular;
        }

  
        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }
    }
    
    public static class carreraSecciones{
        private int institucion;
        private String periodo;
        private String carrera;
    
        private String desCarrera;
        private String seccion;
        private String desSeccion;
        private Date fechaInicio;
        private Date fechaFinal;
        private Date venFechaInicio;
        private int venVencimiento;
        private String paquete;
        private String producto;
        private String desProducto;
        private int preMatricula;
        private String sede;
        private int turno;
        private String desTurno;
        private int frecuencia;
        private int vacantes;
        private int matriculados;
        private int interezados;
        private String desFrecuencia;
        private int frecuenciaDia;
        private String desFrecuenciaDia;
        private int frecuenciaHora;
        private String desFrecuenciaHora;
        private int informe;
        private int estadoRegistro;
        private int vencimiento;
        private int nivelModular;
        private List<seccionCurso> seccionCursoL=new ArrayList<>();
        private List<cursosC> cursoL=new ArrayList<>();
        private String[]curso;
        
        
        public carreraSecciones() {
        	
        }

        public carreraSecciones(int institucion, String periodo, String carrera, String seccion, String desSeccion, Date fechaInicio, Date fechaFinal) {
            this.institucion = institucion;
            this.periodo = periodo;
            this.carrera = carrera;
            this.seccion = seccion;
            this.desSeccion = desSeccion;
            this.fechaInicio = fechaInicio;
            this.fechaFinal = fechaFinal;
        }
        

        
        
        
        
        public String[] getCurso() {
			return curso;
		}

		public void setCurso(String[] curso) {
			this.curso = curso;
		}

		public int getInstitucion() {
            return institucion;
        }


        public void setInstitucion(int institucion) {
            this.institucion = institucion;
        }

        public String getPeriodo() {
            return periodo;
        }

        public void setPeriodo(String periodo) {
            this.periodo = periodo;
        }

        public String getSeccion() {
            return seccion;
        }

        public void setSeccion(String seccion) {
            this.seccion = seccion;
        }

        public String getDesSeccion() {
            return desSeccion;
        }

        public void setDesSeccion(String desSeccion) {
            this.desSeccion = desSeccion;
        }


        public String getCarrera() {
            return carrera;
        }

        public void setCarrera(String carrera) {
            this.carrera = carrera;
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

        public List<seccionCurso> getSeccionCursoL() {
            return seccionCursoL;
        }

        public void setSeccionCursoL(List<seccionCurso> seccionCursoL) {
            this.seccionCursoL = seccionCursoL;
        }

        public String getPaquete() {
            return paquete;
        }

        public void setPaquete(String paquete) {
            this.paquete = paquete;
        }

        public String getProducto() {
            return producto;
        }

        public void setProducto(String producto) {
            this.producto = producto;
        }

        public int getPreMatricula() {
            return preMatricula;
        }

        public void setPreMatricula(int preMatricula) {
            this.preMatricula = preMatricula;
        }

        public int getInforme() {
            return informe;
        }

        public void setInforme(int informe) {
            this.informe = informe;
        }

        public int getTurno() {
            return turno;
        }

        public void setTurno(int turno) {
            this.turno = turno;
        }

        public String getDesTurno() {
            return desTurno;
        }

        public void setDesTurno(String desTurno) {
            this.desTurno = desTurno;
        }

       
        public int getFrecuencia() {
            return frecuencia;
        }

       
        public void setFrecuencia(int frecuencia) {
            this.frecuencia = frecuencia;
        }

       
        public String getDesFrecuencia() {
            return desFrecuencia;
        }

       
        public void setDesFrecuencia(String desFrecuencia) {
            this.desFrecuencia = desFrecuencia;
        }

        
        public String getSede() {
            return sede;
        }

        
        public void setSede(String sede) {
            this.sede = sede;
        }

        
        public int getVacantes() {
            return vacantes;
        }

        
        public void setVacantes(int vacantes) {
            this.vacantes = vacantes;
        }

        
        public String getDesProducto() {
            return desProducto;
        }

        
        public void setDesProducto(String desProducto) {
            this.desProducto = desProducto;
        }

        
        public int getEstadoRegistro() {
            return estadoRegistro;
        }

        
        public void setEstadoRegistro(int estadoRegistro) {
            this.estadoRegistro = estadoRegistro;
        }

        
        public int getFrecuenciaDia() {
            return frecuenciaDia;
        }

       
        public void setFrecuenciaDia(int frecuenciaDia) {
            this.frecuenciaDia = frecuenciaDia;
        }

      
        public String getDesFrecuenciaDia() {
            return desFrecuenciaDia;
        }

      
        public void setDesFrecuenciaDia(String desFrecuenciaDia) {
            this.desFrecuenciaDia = desFrecuenciaDia;
        }

       
        public int getFrecuenciaHora() {
            return frecuenciaHora;
        }

      
        public void setFrecuenciaHora(int frecuenciaHora) {
            this.frecuenciaHora = frecuenciaHora;
        }

       
        public String getDesFrecuenciaHora() {
            return desFrecuenciaHora;
        }

       
        public void setDesFrecuenciaHora(String desFrecuenciaHora) {
            this.desFrecuenciaHora = desFrecuenciaHora;
        }

     
        public int getMatriculados() {
            return matriculados;
        }

      
        public void setMatriculados(int matriculados) {
            this.matriculados = matriculados;
        }

        
        public int getInterezados() {
            return interezados;
        }

       
        public void setInterezados(int interezados) {
            this.interezados = interezados;
        }

       
        public int getVencimiento() {
            return vencimiento;
        }

       
        public void setVencimiento(int vencimiento) {
            this.vencimiento = vencimiento;
        }

       
        public String getDesCarrera() {
            return desCarrera;
        }

        
        public void setDesCarrera(String desCarrera) {
            this.desCarrera = desCarrera;
        }

       
        public int getNivelModular() {
            return nivelModular;
        }

       
        public void setNivelModular(int nivelModular) {
            this.nivelModular = nivelModular;
        }

       
        public Date getVenFechaInicio() {
            return venFechaInicio;
        }

       
        public void setVenFechaInicio(Date venFechaInicio) {
            this.venFechaInicio = venFechaInicio;
        }

        
        public int getVenVencimiento() {
            return venVencimiento;
        }

       
        public void setVenVencimiento(int venVencimiento) {
            this.venVencimiento = venVencimiento;
        }

		public List<cursosC> getCursoL() {
			return cursoL;
		}

		public void setCursoL(List<cursosC> cursoL) {
			this.cursoL = cursoL;
		}
        
        
        
                
    }
    public static class detalleProductoConcepto{
        private int institucion;
        private String paquete;
        private String producto;
        private String periodo;
        private String concepto;
        private String desconcepto;
        private int tipoConcepto;
        private String desTipoConcepto;
        private int numCuota;
        private double montoPago;
        private String descuento;
        private int tipoMoneda;
        
        private List<descuentoC> descuentoL=new ArrayList<>();;
       
        public int getInstitucion() {
            return institucion;
        }

       
        public void setInstitucion(int institucion) {
            this.institucion = institucion;
        }

        
        public String getPaquete() {
            return paquete;
        }

       
        public void setPaquete(String paquete) {
            this.paquete = paquete;
        }

        
        public String getProducto() {
            return producto;
        }

       
        public void setProducto(String producto) {
            this.producto = producto;
        }

       
        public String getPeriodo() {
            return periodo;
        }

       
        public void setPeriodo(String periodo) {
            this.periodo = periodo;
        }

        
        public String getConcepto() {
            return concepto;
        }

       
        public void setConcepto(String concepto) {
            this.concepto = concepto;
        }

       
        public int getTipoConcepto() {
            return tipoConcepto;
        }

       
        public void setTipoConcepto(int tipoConcepto) {
            this.tipoConcepto = tipoConcepto;
        }

        
        public String getDesTipoConcepto() {
            return desTipoConcepto;
        }

       
        public void setDesTipoConcepto(String desTipoConcepto) {
            this.desTipoConcepto = desTipoConcepto;
        }

       
        public int getNumCuota() {
            return numCuota;
        }

     
        public void setNumCuota(int numCuota) {
            this.numCuota = numCuota;
        }

        
        public double getMontoPago() {
            return montoPago;
        }

        
        public void setMontoPago(double montoPago) {
            this.montoPago = montoPago;
        }

     

       
        public int getTipoMoneda() {
            return tipoMoneda;
        }

      
        public void setTipoMoneda(int tipoMoneda) {
            this.tipoMoneda = tipoMoneda;
        }

        
        public String getDesconcepto() {
            return desconcepto;
        }

        
        public void setDesconcepto(String desconcepto) {
            this.desconcepto = desconcepto;
        }

       
        public List<descuentoC> getDescuentoL() {
            return descuentoL;
        }

       
        public void setDescuentoL(List<descuentoC> descuentoL) {
            this.descuentoL = descuentoL;
        }

       
        public String getDescuento() {
            return descuento;
        }

      
        public void setDescuento(String descuento) {
            this.descuento = descuento;
        }
    }
    
    public static class institucionCarrera{
        private int institucion;
        private String carrera;
        private String descarrera;
        private String prefijo;
        private int tipoCarrera;
        private String desTipoCarrera;
        private int totalSecciones;
        private int totalMatricula;
        private int totalCerrado;

       
        public int getInstitucion() {
            return institucion;
        }

       
        public void setInstitucion(int institucion) {
            this.institucion = institucion;
        }

        
        public String getCarrera() {
            return carrera;
        }

        
        public void setCarrera(String carrera) {
            this.carrera = carrera;
        }

      
        public String getDescarrera() {
            return descarrera;
        }

      
        public void setDescarrera(String descarrera) {
            this.descarrera = descarrera;
        }

       
        public int getTipoCarrera() {
            return tipoCarrera;
        }

      
        public void setTipoCarrera(int tipoCarrera) {
            this.tipoCarrera = tipoCarrera;
        }

       
        public String getDesTipoCarrera() {
            return desTipoCarrera;
        }

       
        public void setDesTipoCarrera(String desTipoCarrera) {
            this.desTipoCarrera = desTipoCarrera;
        }

       
        public int getTotalSecciones() {
            return totalSecciones;
        }

       
        public void setTotalSecciones(int totalSecciones) {
            this.totalSecciones = totalSecciones;
        }

        
        public int getTotalMatricula() {
            return totalMatricula;
        }

       
        public void setTotalMatricula(int totalMatricula) {
            this.totalMatricula = totalMatricula;
        }

        
        public int getTotalCerrado() {
            return totalCerrado;
        }

       
        public void setTotalCerrado(int totalCerrado) {
            this.totalCerrado = totalCerrado;
        }

        
        public String getPrefijo() {
            return prefijo;
        }

       
        public void setPrefijo(String prefijo) {
            this.prefijo = prefijo;
        }
        
    }
    public List<tipoProductoC> getTipoProductoL() {
  		return tipoProductoL;
  	}
  	public void setTipoProductoL(List<tipoProductoC> tipoProductoL) {
  		this.tipoProductoL = tipoProductoL;
  	}
    
    public String getFiltroTipoProducto() {
 		return filtroTipoProducto;
 	}

 	public void setFiltroTipoProducto(String filtroTipoProducto) {
 		this.filtroTipoProducto = filtroTipoProducto;
 	}
 	public String getFiltroDesProducto() {
		return filtroDesProducto;
	}

	public void setFiltroDesProducto(String filtroDesProducto) {
		this.filtroDesProducto = filtroDesProducto;
	}
	   public String getFiltroFrecuenciaDia() {
			return filtroFrecuenciaDia;
		}

		public void setFiltroFrecuenciaDia(String filtroFrecuenciaDia) {
			this.filtroFrecuenciaDia = filtroFrecuenciaDia;
		}
		 public List<frecuenciaDiaC> getFiltroFrecuenciaDiaL() {
				return filtroFrecuenciaDiaL;
			}

			public void setFiltroFrecuenciaDiaL(List<frecuenciaDiaC> filtroFrecuenciaDiaL) {
				this.filtroFrecuenciaDiaL = filtroFrecuenciaDiaL;
			}
}
