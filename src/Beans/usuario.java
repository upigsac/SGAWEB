
package Beans;

import DAO.accesoDAO;
import DAO.alumnoCarreraDAO;
import DAO.alumnoCursoSeccionDAO;
import DAO.alumnoDAO;
import DAO.alumnoPeriodoDAO;
import DAO.carrerasDAO;
import DAO.cursoDAO;
import DAO.foroAlumnoDocenteDAO;
import DAO.foroPersonaCursoSeccionPreguntaDAO;
import DAO.foroPersonaCursoSeccionRespuestaDAO;
import DAO.institucionAccesoDAO;
import DAO.institucionDAO;
import DAO.menuDAO;
import DAO.periodoDAO;
import DAO.personaDAO;
import DAO.personalDAO;
import DAO.personalOficinaDAO;
import DAO.seccionPeriodoDAO;
import DAO.usuarioDAO;
import DAO.usuarioHistorialDAO;
import ENTIDAD.accesoC;
import ENTIDAD.alumnoC;
import ENTIDAD.alumnoCarreraC;
import ENTIDAD.alumnoCursoSeccionC;
import ENTIDAD.alumnoPeriodoC;
import ENTIDAD.carrerasC;
import ENTIDAD.cursosC;
import ENTIDAD.foroPersonaCursoSeccionPreguntaC;
import ENTIDAD.foroPersonaCursoSeccionRespuestaC;
import ENTIDAD.institucionAccesoC;
import ENTIDAD.institucionC;
import ENTIDAD.menuC;
import ENTIDAD.periodoC;
import ENTIDAD.personaC;
import ENTIDAD.personalC;
import ENTIDAD.personalOficinaC;
import ENTIDAD.seccionPeriodoC;
import ENTIDAD.usuarioC;
import ENTIDAD.usuarioHistorialC;


import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;


import org.primefaces.context.RequestContext;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;


import org.apache.commons.lang.StringEscapeUtils;



@ManagedBean(name = "usuarioB")
@SessionScoped
public class usuario implements Serializable {   
	private static final long serialVersionUID = 1L;
	
	
	
	private personaC persona;
	
	
    private carrerasC carrera;
    private usuarioC usuarios;
    private personalC personal;
    private alumnoPeriodoC alumnoPeriodo;
    private alumnoPeriodoC alumnoPeriodoS;
    private personalOficinaC personalOficina;
    private institucionAccesoC institucionAcceso;
    private List<alumnoPeriodoC> alumnoPeriodoL;    
    private List<alumnoC> alumnoInstitucionL;
    private List<institucionC> institucionL;
    private List<personalOficinaC> personalOficinaL;
    private seccionPeriodoC seccionPeriodoS;
    private alumnoCarreraC alumnoCarrera;
    private accesoC accesos=null;
    private List<alumnoCursoSeccionC> alumnoCursoSeccionL;
    private alumnoCursoSeccionC alumnoCursoSeccion;
    private cursosC curso;
    
    private List<periodoC> periodoL=new ArrayList<>();

    private institucionC institucion=new institucionC();
    private int institucionS;
    private String periodoS;

    private String claveIngresada;
    private String nuevaClave;
    private String RepetirClave;
    private String usu = "";
    private String pass = "";
    private int tipo=1;
    private int itemHistorial;
    
  

    private List<ArrayList<String>> controlDeMensajes;
    private List<ArrayList<String>> listaRecuperarClave;
    
    private boolean banderaMenu;
    private boolean logeado=false;
    private boolean banderaForo=true;
    private boolean banderaPersona=false;
    private Date fecha;
    private String capcha;//=util.generarNumero();
    private String capchaS="";
    
    
    //------------ FORO ALUMNO  ----------------------
    private List<foroAlumno> foroAlumnoL=new ArrayList<>();
    private List<foroAlumnoDocente> foroAlumnoDocenteL=new ArrayList<>();
    
    private foroPersonaCursoSeccionPreguntaC foroPersonaCursoSeccionPregunta;
    private foroPersonaCursoSeccionRespuestaC foroPersonaCursoSeccionRespuesta;
    private List<menuC> menuL=new ArrayList<>();
    private List<menuC> menuHijoL=new ArrayList<>();
    

    
    
    public List<menuC> getMenuL() {
		return menuL;
	}
	public void setMenuL(List<menuC> menuL) {
		this.menuL = menuL;
	}
	public List<menuC> getMenuHijoL() {
		return menuHijoL;
	}
	public void setMenuHijoL(List<menuC> menuHijoL) {
		this.menuHijoL = menuHijoL;
	}



	public void cargarMenu(){
    	int tipoMenu=0;
    	String perfil="";
    	
    	switch (tipo) {
		case 1:
			tipoMenu=13;
			perfil="{ALUMNOUPIG}";
			break;
		case 2:
			tipoMenu=14;
			perfil="{DOCENTE}";
					break;
		case 3:
			tipoMenu=16;
			perfil="{PADREUPIG}";
			break;
		case 4:
			tipoMenu=institucionAcceso.getPrograma();
			perfil=usu;
			break;


		}
        

           menuL=new ArrayList<>();   
           menuHijoL=new ArrayList<>();
           for (menuC itemCabecera : new menuDAO().getModulo(tipoMenu, 1, perfil, 0)) {
           
           menuHijoL=new menuDAO().getModuloHijo(itemCabecera.getSigu(), itemCabecera.getmPadre(), perfil);
             for (menuC itemDetalle : menuHijoL) {
            	 itemCabecera.getMenuL().add(itemDetalle);
             }
          menuL.add(itemCabecera);
           
       }
       
   }
    
    
    public void editarPersona(){
        banderaPersona=true;
    }
    public void cancelarPersona(){
         banderaPersona=false;
    }
    public void insertarPersona(){
 
        new personaDAO().insertar(persona); 
        util.redirigir("index.xhtml");
        banderaPersona=false;
    }
    public void validaNumeroDocumento(){
    	if(!persona.getNumero_documento().isEmpty()){
    		if (new personaDAO().validaNumeroDocumento(persona.getNumero_documento(),persona.getPersona())){
        		
        		
        		persona.setNumero_documento("");
        		util.script("$('#txtNumeroDocumento').focus();");
        		util.script("notificacion('El numero ingresado ya existe ',500,5000,'error')");
        	}	
    	}
    	
    }
    
    
    
    public void mostrarAlumnoCurso(){
   
       alumnoCursoSeccionL=new alumnoCursoSeccionDAO().mostrarAlumnoCursosSeccionMatriculados(institucionS, periodoS, usu);
 
       foroAlumnoDocenteL =new foroAlumnoDocenteDAO().mostrarCursoAlumno(institucionS, periodoS, usu);
     
    }
    public void seleccionAlumnoCurso(alumnoCursoSeccionC itemAlumnoCursoSeccion){
     
        curso=new cursoDAO().mostrarCurso(itemAlumnoCursoSeccion.getCurso());
        
        alumnoCursoSeccion=itemAlumnoCursoSeccion;
        foroPersonaCursoSeccionPregunta=new foroPersonaCursoSeccionPreguntaC(-1, alumnoCursoSeccion.getInstitucion(), alumnoCursoSeccion.getPeriodo(), alumnoCursoSeccion.getCarrera(), alumnoCursoSeccion.getMalla(), alumnoCursoSeccion.getCurso(), alumnoCursoSeccion.getSeccion(),2, persona.getPersona(), null, 1);
        foroPersonaCursoSeccionRespuesta=new foroPersonaCursoSeccionRespuestaC(-1, -1, alumnoCursoSeccion.getInstitucion(), alumnoCursoSeccion.getPeriodo(), alumnoCursoSeccion.getCarrera(), alumnoCursoSeccion.getMalla(), alumnoCursoSeccion.getCurso(), alumnoCursoSeccion.getSeccion(),2, persona.getPersona(), null, 1);
        banderaForo=false;
        mostrarForoAlumno();
        util.script("$('.foro-cuerpo').scrollTop(50000);");
    }
    public void eliminarForoPregunta(foroPersonaCursoSeccionPreguntaC item){
        foroPersonaCursoSeccionPreguntaD=new foroPersonaCursoSeccionPreguntaDAO();
        foroPersonaCursoSeccionPreguntaD.eliminar(item);
        mostrarForoAlumno();
    }
    public void eliminarForoRespuesta(foroPersonaCursoSeccionRespuestaC item){
        foroPersonaCursoSeccionRespuestaD=new foroPersonaCursoSeccionRespuestaDAO();
        foroPersonaCursoSeccionRespuestaD.eliminar(item);
        mostrarForoAlumno();
    }
    
    
    public void maximizaMinizar(){
        banderaForo=!banderaForo;
    }
    
   
    public List<foroAlumno> getForoAlumnoL() {
        return foroAlumnoL;
    }

   
    public void setForoAlumnoL(List<foroAlumno> foroAlumnoL) {
        this.foroAlumnoL = foroAlumnoL;
    }

   
    public foroPersonaCursoSeccionRespuestaC getForoPersonaCursoSeccionRespuesta() {
        return foroPersonaCursoSeccionRespuesta;
    }

 
    public void setForoPersonaCursoSeccionRespuesta(foroPersonaCursoSeccionRespuestaC foroPersonaCursoSeccionRespuesta) {
        this.foroPersonaCursoSeccionRespuesta = foroPersonaCursoSeccionRespuesta;
    }

    
    public List<alumnoCursoSeccionC> getAlumnoCursoSeccionL() {
        return alumnoCursoSeccionL;
    }

    public void setAlumnoCursoSeccionL(List<alumnoCursoSeccionC> alumnoCursoSeccionL) {
        this.alumnoCursoSeccionL = alumnoCursoSeccionL;
    }

  
    public alumnoCursoSeccionC getAlumnoCursoSeccion() {
        return alumnoCursoSeccion;
    }

  
    public void setAlumnoCursoSeccion(alumnoCursoSeccionC alumnoCursoSeccion) {
        this.alumnoCursoSeccion = alumnoCursoSeccion;
    }

    
    public boolean isBanderaForo() {
        return banderaForo;
    }

   
    public void setBanderaForo(boolean banderaForo) {
        this.banderaForo = banderaForo;
    }

    public cursosC getCurso() {
        return curso;
    }

    
    public void setCurso(cursosC curso) {
        this.curso = curso;
    }

   
    public List<foroAlumnoDocente> getForoAlumnoDocenteL() {
        return foroAlumnoDocenteL;
    }

    public void setForoAlumnoDocenteL(List<foroAlumnoDocente> foroAlumnoDocenteL) {
        this.foroAlumnoDocenteL = foroAlumnoDocenteL;
    }

    
    public boolean isBanderaPersona() {
        return banderaPersona;
    }

    
    public void setBanderaPersona(boolean banderaPersona) {
        this.banderaPersona = banderaPersona;
    }
    
    public static class foroAlumnoDocente{
        private alumnoCursoSeccionC alumnoCursoSeccion=new alumnoCursoSeccionC();
        private personaC persona=new personaC();

        public foroAlumnoDocente() {
        }

        public foroAlumnoDocente(alumnoCursoSeccionC alumnoCursoSeccion, personaC persona) {
            this.alumnoCursoSeccion = alumnoCursoSeccion;
            this.persona = persona;
        }
        

        
        public alumnoCursoSeccionC getAlumnoCursoSeccion() {
            return alumnoCursoSeccion;
        }

       
        public void setAlumnoCursoSeccion(alumnoCursoSeccionC alumnoCursoSeccion) {
            this.alumnoCursoSeccion = alumnoCursoSeccion;
        }

       
        public personaC getPersona() {
            return persona;
        }

      
        public void setPersona(personaC persona) {
            this.persona = persona;
        }
        
    }
    
    
     public class foroAlumno{
         private foroPersonaCursoSeccionPreguntaC foroPersonaCursoSeccionPregunta;
         private List<foroPersonaCursoSeccionRespuestaC> foroPersonaCursoSeccionRespuestaL=new ArrayList<>();

        public foroAlumno() {
        }

        public foroAlumno(foroPersonaCursoSeccionPreguntaC foroPersonaCursoSeccionPregunta) {
            this.foroPersonaCursoSeccionPregunta = foroPersonaCursoSeccionPregunta;
        }
        

   
        public foroPersonaCursoSeccionPreguntaC getForoPersonaCursoSeccionPregunta() {
            return foroPersonaCursoSeccionPregunta;
        }

     
        public void setForoPersonaCursoSeccionPregunta(foroPersonaCursoSeccionPreguntaC foroPersonaCursoSeccionPregunta) {
            this.foroPersonaCursoSeccionPregunta = foroPersonaCursoSeccionPregunta;
        }

       
        public List<foroPersonaCursoSeccionRespuestaC> getForoPersonaCursoSeccionRespuestaL() {
            return foroPersonaCursoSeccionRespuestaL;
        }

       
        public void setForoPersonaCursoSeccionRespuestaL(List<foroPersonaCursoSeccionRespuestaC> foroPersonaCursoSeccionRespuestaL) {
            this.foroPersonaCursoSeccionRespuestaL = foroPersonaCursoSeccionRespuestaL;
        }
     }
    foroPersonaCursoSeccionPreguntaDAO foroPersonaCursoSeccionPreguntaD;
    foroPersonaCursoSeccionRespuestaDAO foroPersonaCursoSeccionRespuestaD;
    public void mostrarForoAlumno(){
        foroAlumnoL.clear();
        foroAlumno itemForoAlumno;
        foroPersonaCursoSeccionPreguntaD=new foroPersonaCursoSeccionPreguntaDAO();
        for (foroPersonaCursoSeccionPreguntaC itemPregunta : foroPersonaCursoSeccionPreguntaD.mostrarPregunta(alumnoCursoSeccion.getInstitucion(), alumnoCursoSeccion.getPeriodo(), alumnoCursoSeccion.getCarrera(), alumnoCursoSeccion.getMalla(), alumnoCursoSeccion.getCurso(), alumnoCursoSeccion.getSeccion())) {
           itemForoAlumno=new foroAlumno(itemPregunta);
           foroPersonaCursoSeccionRespuestaD=new foroPersonaCursoSeccionRespuestaDAO();
            for (foroPersonaCursoSeccionRespuestaC itemRespuesta : foroPersonaCursoSeccionRespuestaD.mostrarRespuesta(itemPregunta.getPregunta(), itemPregunta.getInstitucion(), itemPregunta.getPeriodo(), itemPregunta.getCarrera(), itemPregunta.getMalla(), itemPregunta.getCurso(), itemPregunta.getSeccion())) {
                itemForoAlumno.getForoPersonaCursoSeccionRespuestaL().add(itemRespuesta);
            }
            foroAlumnoL.add(itemForoAlumno);
        }
    }

    private final static String CHANNEL = "/notify";
    public void insertarPregunta(){
        String summary = persona.getPaterno() +" "+ persona.getMaterno() +" "+ persona.getNombres();
        String detail =  "COMENTO .....";
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml(summary), StringEscapeUtils.escapeHtml(detail)));  
        
        
        foroPersonaCursoSeccionPreguntaD=new foroPersonaCursoSeccionPreguntaDAO();
        foroPersonaCursoSeccionPreguntaD.insertar(foroPersonaCursoSeccionPregunta);
        foroPersonaCursoSeccionPregunta.setMensaje("");
        //mostrarForoAlumno();
        util.script("$('.foro-cuerpo').scrollTop(50000);");
    }
    public void insertarRespuesta(int pregunta){
        String summary = persona.getPaterno() +" "+ persona.getMaterno() +" "+ persona.getNombres();
        String detail =  "COMENTO .....";
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml(summary), StringEscapeUtils.escapeHtml(detail))); 
        
        
        foroPersonaCursoSeccionRespuesta.setPregunta(pregunta);
        foroPersonaCursoSeccionRespuestaD=new foroPersonaCursoSeccionRespuestaDAO();        
        foroPersonaCursoSeccionRespuestaD.insertar(foroPersonaCursoSeccionRespuesta);        
        foroPersonaCursoSeccionRespuesta.setMensaje("");
        mostrarForoAlumno();
    }
    
    
    public void validaClaveDocente(){
        if (pass.endsWith("123456")){
            util.script("dlgCambiarClave.show()");
        }
    }
    
    
    public void verificarSesion(){
    	if(FacesContext.getCurrentInstance().isPostback()){
    		
    		System.out.println("primera");
    	}else{
    		if (!logeado){
                util.redirigir("/SGAWEB/faces/index.xhtml");
            }
    		System.out.println("segunda");
    	}
        
        
    }
    
    public void recaragar(){
        System.out.println("---inicio ...--");
       Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("modal", false);
        RequestContext.getCurrentInstance().openDialog("index", options, null);
        System.out.println("----final ... ---");
    }
    
    
    public void inicioUsuario() throws IOException {
        

        if (!logeado ) {

        } else {
            String ruta = "";
            switch (tipo) {
                case 1:
                    ruta = "/SGAWEB/faces/Alumno/index.xhtml";
                    break;
                case 2:
                    ruta = "/SGAWEB/faces/Docente/index.xhtml";
                    break;
                case 3:
                    ruta = "/SGAWEB/faces/Padre/index.xhtml";
                    break;
                case 4:
                    ruta = "/SGAWEB/faces/Administrativo/index.xhtml";
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect(ruta);

        }

        
       
    }
    
    public void periodoSeccionado(){
        
        alumnoPeriodoS=new alumnoPeriodoDAO().mostrarPeriodoAlumno(institucionS, periodoS, usu);
        
      
        seccionPeriodoS=new seccionPeriodoDAO().mostrarSeccionPeriodo(institucionS, periodoS, alumnoPeriodoS.getCarrera(), alumnoPeriodoS.getSeccion_referencial());
        
        
    }
            
    
    public void validaPagina(){
        
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = req.getRequestURL().toString();        
       
        String cadena[]=url.split("/");
        String pagina=cadena[cadena.length-1];        
             
        accesos=new accesoDAO().seguridadWeb(institucionAcceso.getPrograma(), "%" +pagina, usu);
        
        if (accesos==null){
            util.redirigir("http://intranet.upig.edu.pe/SGAWEB/faces/Administrativo/index.xhtml");
        }
        
    }

    public personaC getPersona() {
        return persona;
    }

    public void setPersona(personaC persona) {
        this.persona = persona;
    }

    public usuarioC getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(usuarioC usuarios) {
        this.usuarios = usuarios;
    }

    
    public String getUsu() {
        return usu;
    }

    
    public void setUsu(String usu) {
        this.usu = usu;
    }

    
    public String getPass() {
        return pass;
    }

    public void generarCapcha(){
        capcha=util.generarNumero();
    }
    
    public void ip() throws UnknownHostException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ip = httpServletRequest.getRemoteAddr();

        System.out.println("ipAddress:" + ip);
        System.out.println("usuario:" + httpServletRequest.getRemoteUser());
        System.out.println("host:" + httpServletRequest.getRemoteHost());
        System.out.println("host:" + httpServletRequest.getLocalName());

        InetAddress inetAddress = InetAddress.getByName("172.16.1.61");//get the host Inet using ip

        System.out.println("Host Name: " + inetAddress.getHostName());//display the host
    }

   
    public void setPass(String pass) {
        this.pass = pass;
    }

    
    public int getTipo() {

        return tipo;
    }

   
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    
    
    public List<ArrayList<String>> mensajeControl(int institucion,String periodo, String carrera) {

        controlDeMensajes = new usuarioDAO().controlMensaje(institucion,periodo,carrera, usu);
        return controlDeMensajes;
    }

 // METODOS DE ALUMNOS 
    
 
   
    
   


    
 
  
   
    

   
    
    
    ///////////
    public void mostrarInstitucionAcceso(int institucion){
        periodoS=null;
    
        institucionAcceso=new institucionAccesoDAO().mostrarInstitucionAcceso(institucion);
           //LISTADO PERIODO INSTITUCION
         
            periodoL=new periodoDAO().mostrarPeriodoInstitucion(institucion);
        
         if (periodoL.size() >0){
                    periodoS=periodoL.get(0).getPeriodo();
        }
         cargarMenu();
    }
    public void mostrarInstitucion(String usuario){
       
        institucionL = new  institucionDAO().mostrarInstitucionUsuario(usuario);
    }

    
    
    public void mostrarAlumnoPeriodo(){
       
        periodoL=new periodoDAO().mostrarPeriodoAlumno(institucionS, usu);
    }
    
    
    public void validarUsuarios() throws IOException {
        
      
       
       
     


      
       
        
              
       // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("acceso", null);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioBD", null);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("claveBD", null);
        
      
        if (tipo == 1) {   // SI ES ALUMNO
            
        	  
              
            usuarios = new usuarioDAO().accesoWebAlumno(usu, pass);
          
            
            if (usuarios != null) {
            
                persona = new personaDAO().getBuscaPersonaCodigo(usuarios.getPersona());
                
                institucionL=new  institucionDAO().mostrarInstitucionAlumno(usu);
               
                
                
                
                if(institucionL.size()>0){
                  institucionS= institucionL.get(0).getInstitucion();                	
                  
                   alumnoCarrera=new alumnoCarreraDAO().mostrarAlumnoCarrera(institucionS, usu);
                }                                
               
                
                
            
                periodoL = new periodoDAO().mostrarPeriodoAlumno(institucionS, usu);
                
                if (periodoL.size() >0){
                    periodoS=periodoL.get(0).getPeriodo();
                    
                    // alumno periodo
                    
                 
                    alumnoPeriodoS=new alumnoPeriodoDAO().mostrarPeriodoAlumno(institucionS, periodoS, usu);
                    
                    // seccion referencial
                    
                    seccionPeriodoS=new seccionPeriodoDAO().mostrarSeccionPeriodo(institucionS, periodoS, alumnoPeriodoS.getCarrera(), alumnoPeriodoS.getSeccion_referencial());
                    
                }
                alumnoInstitucionL=new alumnoDAO().mostrarAlumno(usu);
                carrera = new carrerasDAO().mostrarCarreraAlumno(institucionS, usu);
                alumnoPeriodo = new alumnoPeriodoDAO().mostrarUltimaMatricula(institucionS, usu);
                alumnoPeriodoL = new alumnoPeriodoDAO().mostrarAlumnoPeriodo(institucionS, usu);
               
                     
                
                
                logeado=true; 
                itemHistorial=Integer.parseInt(new usuarioHistorialDAO().insertar(new usuarioHistorialC(-1,usu, persona.getPersona(),"", util.navegador(), util.ipPublica(), "PERU",tipo, util.FechaHoraHoy(), 1))) ;
                cargarMenu();
                util.redirigir("/SGAWEB/faces/Alumno/index.xhtml");
                
             
            }
            else{
               usu="";
               pass="";
               util.script("$('#txtUsuario').focus();");
             
               util.script("notificacion('Los datos son Incorrectos',500,5000,'error')");
               
            }

        } else if (tipo == 2) {          // SI ES DOCENTE
           
            usuarios = (new usuarioDAO().getIngresoDocente(usu, pass,4));
            if (usuarios != null) {
                persona = new personaDAO().getBuscaPersonaCodigo(usuarios.getPersona());    
                personal=new personalDAO().mostrarPersona(usuarios.getPersona());                
                            
                periodoL=new periodoDAO().mostrarPeriodoPersonal(personal.getPersonal());
                institucionS=1;                
                if (periodoL.size() >0){
                    periodoS=periodoL.get(0).getPeriodo();
                }
                new usuarioHistorialDAO().insertar(new usuarioHistorialC(-1,usu, persona.getPersona(),"", util.navegador(), util.ipPublica(), "PERU",tipo, util.FechaHoraHoy(), 1));
                logeado=true;
                
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioBD", usu);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("claveBD", pass);
                cargarMenu();
                String correo="<html><body><b>Navegado: </b>"+util.navegador()+"<br /><b>IP: </b>"+util.ipPublica()+"<br /><b>fecha: </b>" +util.FechaHoraHoy()+ "</body></html>";
                javaMailHilo mensaje=new javaMailHilo(persona.getEmailP(), "INGRESO INTRANET UPIG", correo);
                mensaje.start();
                
                
                if (new personaDAO().validaDatosPersona(usuarios.getPersona()))
                {
                	util.redirigir("/SGAWEB/faces/Docente/actualizaDato.xhtml");
                }
                
                else
                {
                	util.redirigir("/SGAWEB/faces/Docente/index.xhtml");
                }
                
                                
                
                
                
                
              
            }
            else{
                usu="";
               pass="";
               util.script("notificacion('Los datos son Incorrectos',500,5000,'error')");
            }

        } else if (tipo == 3) {          // SI ES PADRE

            usuarios = (new usuarioDAO().getIngresoPadre(usu, pass));
            if (usuarios != null) {
                persona = new personaDAO().getBuscaPersonaCodigo(usuarios.getPersona());     
               institucionL=new  institucionDAO().mostrarInstitucionAlumno(usu);
                
                 if(institucionL.size()>0){
                   institucionS= institucionL.get(0).getInstitucion();
                }                                
                
             
                periodoL = new periodoDAO().mostrarPeriodoAlumno(institucionS, usu);
                
                if (periodoL.size() >0){
                    periodoS=periodoL.get(0).getPeriodo();
                }
                alumnoInstitucionL=new alumnoDAO().mostrarAlumno(usu);
                carrera = new carrerasDAO().mostrarCarreraAlumno(institucionS, usu);
                alumnoPeriodo = new alumnoPeriodoDAO().mostrarUltimaMatricula(institucionS, usu);
                alumnoPeriodoL = new alumnoPeriodoDAO().mostrarAlumnoPeriodo(institucionS, usu);
                logeado=true;
                cargarMenu();
                util.redirigir("/SGAWEB/faces/Padre/index.xhtml");
         
           
            }
            else{
                usu="";
               pass="";
               util.script("notificacion('Los datos son Incorrectos',500,5000,'error')");
            }

        } else if (tipo == 4) {           // SI ES ADMINISTRATIVO
         
            usuarios = (new usuarioDAO().getIngresoDocente(usu, pass,0));
            if (usuarios != null) {
                persona = new personaDAO().getBuscaPersonaCodigo(usuarios.getPersona());
       
                personal=new personalDAO().mostrarPersona(usuarios.getPersona());
            
                personalOficina=new personalOficinaDAO().mostrarPersonalOficina(1, personal.getPersonal());
                
                // LISTADO LAS INSTITUCION DE USUARIO
                institucionL=new  institucionDAO().mostrarInstitucionUsuario(usu);
                if(institucionL.size()>0){
                   institucionS= institucionL.get(0).getInstitucion();
                }    
                
                
                //LISTADO PERIODO INSTITUCION
           
                periodoL=new periodoDAO().mostrarPeriodoInstitucion(institucionS);
                if (periodoL.size() >0){
                    periodoS=periodoL.get(0).getPeriodo();
                }
                
                // CONTROL MENU
               
                institucionAcceso=new institucionAccesoDAO().mostrarInstitucionAcceso(institucionS);
        
        
                
                logeado=true;
                new usuarioHistorialDAO().insertar(new usuarioHistorialC(-1,usu, persona.getPersona(),"", util.navegador(), util.ipPublica(), "PERU",tipo, util.FechaHoraHoy(), 1));
                
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioBD", usu);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("claveBD", pass);
               
            	
                String correo="<html><body><b>Navegado: </b>"+util.navegador()+"<br /><b>IP: </b>"+util.ipPublica()+"<br /><b>fecha: </b>" +util.FechaHoraHoy()+ "</body></html>";
                javaMailHilo mensaje=new javaMailHilo(persona.getEmailP(), "INGRESO INTRANET UPIG", correo);
                mensaje.start();
                
                cargarMenu();
                util.redirigir("/SGAWEB/faces/Administrativo/index.xhtml");
                
            
            }else{
                usu="";
               pass="";
               util.script("notificacion('Los datos son Incorrectos',500,5000,'error')");
            }

        }
       
    }

    
    
    
    public void cambiarContrase�aAdmin() {

        String msg1;

  

        if (claveIngresada.equals(pass)) {

            if (nuevaClave.equals(RepetirClave)) {
                if (new usuarioDAO().actualizarAdmin(usu, nuevaClave, pass)) {

                    claveIngresada = "";
                    msg1 = "Se guardo correctamente";
                    //RequestContext.getCurrentInstance().execute("PF('dlgCambiarClave').hide()");
                    pass = nuevaClave;
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("claveBD", pass);
                } else {

                    msg1 = "Ocurrio un error";
                }
            } else {
                //util.consolaCliente( "No coincide las contraseñas");
                msg1 = "Contraseñas no coinciden";
            }

        } else {
            //util.consolaCliente( "La contraseña no es correcta");
            msg1 = "La contraseña actual no coincide";
        }

        RequestContext.getCurrentInstance().execute("msg('" + msg1 + "','alerta')");

    }

   
    
    public void cambiarContrase�a() {

        String msg1 ;

   

        if (claveIngresada.equals(pass)) {

            if (nuevaClave.equals(RepetirClave)) {
                if (new usuarioDAO().actualizarAlumno(usuarios.getUsuario(), getNuevaClave())) {
                        //util.consolaCliente( "Se Guardo Correctamente");

                    claveIngresada = "";
                    msg1 = "Se guardo correctamente";
                } else {
                    //util.consolaCliente( "Ocurrio un error");
                    msg1 = "Ocurrio un error";
                }
            } else {
                //util.consolaCliente( "No coincide las contraseñas");
                msg1 = "Contraseñas no coinciden";
            }

        } else {
            //util.consolaCliente( "LA contraseña no es correcta");
            msg1 = "La contraseña actual no coincide";
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg1, null));

    }
    
    public void nuevoRecuperacion(){
        
    }

    public void recuperarContrase�a() throws IOException {

    

       

        listaRecuperarClave = new ArrayList<>();
        listaRecuperarClave = new usuarioDAO().recuperarClaveAlumno(usu);

        if (listaRecuperarClave.isEmpty()) {
       
            
            util.script("notificacion('Usuario no existe',500,5000,'error')");
        } else {
            String destino = listaRecuperarClave.get(0).get(3).toString();//correo de usuario
            String asunto = "Recuperacion de Contrase�a";
            String nombrePersona = listaRecuperarClave.get(0).get(0).toString() + " " + listaRecuperarClave.get(0).get(1).toString()+ " " + listaRecuperarClave.get(0).get(2).toString();
            String contrase�aUsuario = listaRecuperarClave.get(0).get(4).toString();
            String correo="<html><body><div style='width: 100%;margin: 0 auto;height: 400px;position: relative;'><img src='http://www.logrosperu.com/images/logos/universidades/upig.jpg' alt='UPIG.' style='margin-left: -20px;border:1px solid '><br/><label style='font-size: 1.2em'> "+ nombrePersona +"</label><br/><label style='font-size: 1.05em'>Usuario es:"+ usu +"<br/>Su clave es :"+ contrase�aUsuario +" </div></body></html>";
            
            util.enviarCorreo(destino, asunto, correo);
            
             
            
        }
        util.script("dlgRecupera.hide()");
    }
    public void autotentifacionUsuario() throws IOException{
    	String correo="<html><body><div style='width: 100%;margin: 0 auto;height: 400px;position: relative;'>Su clave es 12313212 </div></body></html>";
    	util.enviarCorreo(persona.getEmailP(), "Upig", correo);
    }

    public String cerrarSesion() throws IOException {
        logeado=false;
        banderaForo=true;              
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }
    

    
    public String getNuevaClave() {
        return nuevaClave;
    }

    
    public void setNuevaClave(String nuevaClave) {
        this.nuevaClave = nuevaClave;
    }

   
    public String getRepetirClave() {
        return RepetirClave;
    }

    
    public void setRepetirClave(String RepetirClave) {
        this.RepetirClave = RepetirClave;
    }

  
    public String getClaveIngresada() {
        return claveIngresada;
    }

    
    public void setClaveIngresada(String claveIngresada) {
        this.claveIngresada = claveIngresada;
    }

 

    
    public List<ArrayList<String>> getListaRecuperarClave() {
        return listaRecuperarClave;
    }

   
    public void setListaRecuperarClave(List<ArrayList<String>> listaRecuperarClave) {
        this.listaRecuperarClave = listaRecuperarClave;
    }

  
    public List<ArrayList<String>> getControlDeMensajes() {
        return controlDeMensajes;
    }

   
    public void setControlDeMensajes(List<ArrayList<String>> controlDeMensajes) {
        this.controlDeMensajes = controlDeMensajes;
    }

   
    public carrerasC getCarrera() {
        return carrera;
    }

   
    public void setCarrera(carrerasC carrera) {
        this.carrera = carrera;
    }

    
    public alumnoPeriodoC getAlumnoPeriodo() {
        return alumnoPeriodo;
    }

   
    public void setAlumnoPeriodo(alumnoPeriodoC alumnoPeriodo) {
        this.alumnoPeriodo = alumnoPeriodo;
    }

   
    public List<alumnoPeriodoC> getAlumnoPeriodoL() {
        return alumnoPeriodoL;
    }

   
    public void setAlumnoPeriodoL(List<alumnoPeriodoC> alumnoPeriodoL) {
        this.alumnoPeriodoL = alumnoPeriodoL;
    }

   
    public List<alumnoC> getAlumnoInstitucionL() {
        return alumnoInstitucionL;
    }

    
    public void setAlumnoInstitucionL(List<alumnoC> alumnoInstitucionL) {
        this.alumnoInstitucionL = alumnoInstitucionL;
    }

   
    public List<institucionC> getInstitucionL() {
        return institucionL;
    }

    
    public void setInstitucionL(List<institucionC> institucionL) {
        this.institucionL = institucionL;
    }

    
    public institucionC getInstitucion() {
        return institucion;
    }

 
    public void setInstitucion(institucionC institucion) {
        this.institucion = institucion;
    }

 
    public int getInstitucionS() {
        return institucionS;
    }

  
    public void setInstitucionS(int institucionS) {
        this.institucionS = institucionS;
    }

   
    public boolean isBanderaMenu() {
        return banderaMenu;
    }

  
    public void setBanderaMenu(boolean banderaMenu) {
        this.banderaMenu = banderaMenu;
    }

    
    public personalOficinaC getPersonalOficina() {
        return personalOficina;
    }

    
    public void setPersonalOficina(personalOficinaC personalOficina) {
        this.personalOficina = personalOficina;
    }

    
    public personalC getPersonal() {
        return personal;
    }

   
    public void setPersonal(personalC personal) {
        this.personal = personal;
    }

    
    public List<personalOficinaC> getPersonalOficinaL() {
        return personalOficinaL;
    }

  
    public void setPersonalOficinaL(List<personalOficinaC> personalOficinaL) {
        this.personalOficinaL = personalOficinaL;
    }

 
    public boolean isLogeado() {
        return logeado;
    }

 
    public void setLogeado(boolean logeado) {
        this.logeado = logeado;
    }

    
    public accesoC getAccesos() {
        return accesos;
    }

   
    public void setAccesos(accesoC accesos) {
        this.accesos = accesos;
    }

    
    public institucionAccesoC getInstitucionAcceso() {
        return institucionAcceso;
    }

  
    public void setInstitucionAcceso(institucionAccesoC institucionAcceso) {
        this.institucionAcceso = institucionAcceso;
    }

    
    public List<periodoC> getPeriodoL() {
        return periodoL;
    }

   
    public void setPeriodoL(List<periodoC> periodoL) {
        this.periodoL = periodoL;
    }

    
    public String getPeriodoS() {
        return periodoS;
    }

    
    public void setPeriodoS(String periodoS) {
        this.periodoS = periodoS;
    }



    public alumnoPeriodoC getAlumnoPeriodoS() {
        return alumnoPeriodoS;
    }

    
    public void setAlumnoPeriodoS(alumnoPeriodoC alumnoPeriodoS) {
        this.alumnoPeriodoS = alumnoPeriodoS;
    }


    
    public seccionPeriodoC getSeccionPeriodoS() {
        return seccionPeriodoS;
    }

   
    public void setSeccionPeriodoS(seccionPeriodoC seccionPeriodoS) {
        this.seccionPeriodoS = seccionPeriodoS;
    }

   
    public int getItemHistorial() {
        return itemHistorial;
    }

    
    public void setItemHistorial(int itemHistorial) {
        this.itemHistorial = itemHistorial;
    }

   
    public alumnoCarreraC getAlumnoCarrera() {
        return alumnoCarrera;
    }

   
    public void setAlumnoCarrera(alumnoCarreraC alumnoCarrera) {
        this.alumnoCarrera = alumnoCarrera;
    }

   
    public Date getFecha() {
        return fecha;
    }

   
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

   
    public String getCapcha() {
        return capcha;
    }

  
    public void setCapcha(String capcha) {
        this.capcha = capcha;
    }

 
    public String getCapchaS() {
        return capchaS;
    }

  
    public void setCapchaS(String capchaS) {
        this.capchaS = capchaS;
    }

  

   
    public foroPersonaCursoSeccionPreguntaC getForoPersonaCursoSeccionPregunta() {
        return foroPersonaCursoSeccionPregunta;
    }

 
    public void setForoPersonaCursoSeccionPregunta(foroPersonaCursoSeccionPreguntaC foroPersonaCursoSeccionPregunta) {
        this.foroPersonaCursoSeccionPregunta = foroPersonaCursoSeccionPregunta;
    }


  

}
