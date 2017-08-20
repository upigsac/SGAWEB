
package Beans;

import DAO.alumnoDAO;
import DAO.alumnoPeriodoDAO;
import DAO.bannerPuertaDAO;
import DAO.localPuertaDAO;
import DAO.marcacionPuertaDAO;
import DAO.notaDAO;
import DAO.personaDAO;
import DAO.puertaDAO;
import DAO.tipoPersonaDAO;
import ENTIDAD.alumnoC;
import ENTIDAD.alumnoPeriodoC;
import ENTIDAD.bannerPuertaC;
import ENTIDAD.localPuertaC;
import ENTIDAD.marcacionPuertaC;
import ENTIDAD.notaC;
import ENTIDAD.personaC;
import ENTIDAD.tipoPersonaC;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="puertaB")
@ViewScoped
public class puerta {
    private String codigo;
    private personaC persona=new personaC();
    private List<personaC> personaL=new ArrayList<>();
   
    private List<personaDeuda> personaDeudaL=new ArrayList<>();
    private List<notaC> notaL=new ArrayList<>();
    private List<bannerPuertaC> bannerPuertaL;
    private alumnoC alumno;
    private boolean bandera;
    private alumnoPeriodoC alumnoPeriodo;
    private localPuertaC localPuerta;
    private tipoPersonaC tipoPersona;
    
    private String paternoB="";
    private String maternoB="";
    private String nombresB="";
    
    personaDAO personaD;
    puertaDAO puertaD;
    marcacionPuertaDAO marcacionPuertaD;
    notaDAO notaD;
    alumnoPeriodoDAO alumnoPeriodoD;
    localPuertaDAO localPuertaD;
    bannerPuertaDAO bannerPuertaD;
    tipoPersonaDAO tipoPersonaD;
    alumnoDAO alumnoD;
    public puerta() {
      bannerPuertaD=new bannerPuertaDAO();
      bannerPuertaL=bannerPuertaD.mostrarBannerPuerta();
    }
    
    public void validarPuerta(){
        if(! util.isPostBack()){
            System.out.println("primera vez");
              localPuertaD=new localPuertaDAO();
            System.out.println(util.ipPublica());
            localPuerta=localPuertaD.mostrarLocalPuerta(util.ipPublica());
            if(localPuerta==null){
                System.out.println("No esta autorizado");
                util.redirigir("http://www.upig.edu.pe/");
            }
        }
    }
    
    
    
    public void busquedaAlumno(){
        personaD=new personaDAO();      
        persona=personaD.mostrarPersonaPuerta(codigo);
        
        String mensaje="";
        String parramArray[];
        if(persona!=null){        	
            marcacionPuertaD=new marcacionPuertaDAO();
            mensaje=marcacionPuertaD.insertar(new marcacionPuertaC(localPuerta.getLocal(), localPuerta.getPuerta(), util.FechaHoraHoy(), 0, persona.getPersona(), 1));            
            parramArray=mensaje.split("=>");            
            if(parramArray[0].equals("TRUE")){           	
                 alumno=new alumnoDAO().mostrarAlumnoPersona(persona.getPersona());
                 personaDeudaL=new puertaDAO().mostrarPersonaDeuda(persona.getPersona());
                 tipoPersona=new tipoPersonaDAO().mostrarTipoPersona(persona.getPersona());                 
                 if(alumno!=null){
                	 
                     alumnoPeriodo=new alumnoPeriodoDAO().mostrarUltimaMatricula(1, alumno.getAlumno());
                 }
                 if(alumnoPeriodo!=null){
                     notaL= new notaDAO().mostrarNota(alumnoPeriodo.getInstitucion(),alumnoPeriodo.getPeriodo(),alumnoPeriodo.getCarrera(),"",persona.getPersona());
                 }else{
                     notaL= new notaDAO().mostrarNota(1,"","","",persona.getPersona());
                 }
                 bandera=true;    
                 util.script("notificacion('"+ parramArray[1] +"',500,5000,'correcto')");
                 util.script("start()");
            	
            }
            else{
            	

            	  util.script("notificacion('"+ parramArray[1] +"',500,5000,'error')");
            	 bandera=false;
            }
            
           
        }else{
            bandera=false;
           
        }
            codigo="";
               
    }
    
    
    public void filtroPersona(){
        personaD=new personaDAO();
        personaL=personaD.filtroPersona(paternoB, maternoB, nombresB);
    }
    
    public void regresar(){
        bandera=false;
        util.script("stop()");
    }
    public void seleccionaPersona(){
            
            marcacionPuertaD=new marcacionPuertaDAO();
            marcacionPuertaD.insertar(new marcacionPuertaC(localPuerta.getLocal(), localPuerta.getPuerta(), util.FechaHoraHoy(), 0, persona.getPersona(), 1));
            puertaD=new puertaDAO();
        
            personaDeudaL=puertaD.mostrarPersonaDeuda(persona.getPersona());
            
            if(alumno!=null){
                alumnoPeriodoD=new alumnoPeriodoDAO();
                alumnoPeriodo=alumnoPeriodoD.mostrarUltimaMatricula(1, alumno.getAlumno());
            }
            
            notaD=new notaDAO();
            if(alumnoPeriodo!=null){
                notaL= notaD.mostrarNota(alumnoPeriodo.getInstitucion(),alumnoPeriodo.getPeriodo(),alumnoPeriodo.getCarrera(),"",persona.getPersona());
            }else{
                notaL= notaD.mostrarNota(1,"","","",persona.getPersona());
            }
            
            
            bandera=true;
            
            util.script("start()");
    }
 

    public boolean isBandera() {
        return bandera;
    }

    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    public List<personaDeuda> getPersonaDeudaL() {
        return personaDeudaL;
    }

    public void setPersonaDeudaL(List<personaDeuda> personaDeudaL) {
        this.personaDeudaL = personaDeudaL;
    }

    public List<notaC> getNotaL() {
        return notaL;
    }

    public void setNotaL(List<notaC> notaL) {
        this.notaL = notaL;
    }
    public alumnoPeriodoC getAlumnoPeriodo() {
        return alumnoPeriodo;
    }
    public void setAlumnoPeriodo(alumnoPeriodoC alumnoPeriodo) {
        this.alumnoPeriodo = alumnoPeriodo;
    }
    public List<personaC> getPersonaL() {
        return personaL;
    }
    public void setPersonaL(List<personaC> personaL) {
        this.personaL = personaL;
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
    public localPuertaC getLocalPuerta() {
        return localPuerta;
    }
    public void setLocalPuerta(localPuertaC localPuerta) {
        this.localPuerta = localPuerta;
    }
    public List<bannerPuertaC> getBannerPuertaL() {
        return bannerPuertaL;
    }
    public void setBannerPuertaL(List<bannerPuertaC> bannerPuertaL) {
        this.bannerPuertaL = bannerPuertaL;
    }
    public tipoPersonaC getTipoPersona() {
        return tipoPersona;
    }
    public void setTipoPersona(tipoPersonaC tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
    public alumnoC getAlumno() {
        return alumno;
    }
    public void setAlumno(alumnoC alumno) {
        this.alumno = alumno;
    }
    
    
    
    
    public static class personaDeuda{
        private String persona;
        private String periodo;
        private String concepto;
        private String desConcepto;
        private Date fecha;
        private int dia;
        private int numCuota;
        private double montoTotal;

      
        public String getPersona() {
            return persona;
        }

        public void setPersona(String persona) {
            this.persona = persona;
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

        public int getNumCuota() {
            return numCuota;
        }

        public void setNumCuota(int numCuota) {
            this.numCuota = numCuota;
        }

        public double getMontoTotal() {
            return montoTotal;
        }

        public void setMontoTotal(double montoTotal) {
            this.montoTotal = montoTotal;
        }

        public String getPeriodo() {
            return periodo;
        }

        public void setPeriodo(String periodo) {
            this.periodo = periodo;
        }
        
        
    }
    
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public personaC getPersona() {
        return persona;
    }
    public void setPersona(personaC persona) {
        this.persona = persona;
    }
}
