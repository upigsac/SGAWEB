
package Beans;

import DAO.personaDAO;
import ENTIDAD.personaC;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

@ManagedBean(name = "personaB")
@ViewScoped
public class persona implements Serializable {
   
	private static final long serialVersionUID = 1L;

	private String seleccion;

    private personaC persona;
    private List<personaC> personaL = new ArrayList<>();
    private List<personaC> personalPeriodoL;
    private String busPaterno;
    private String busMaterno;
    private String busNombres;
    private personaC seleccionTabla;
    private String busCodigo;
    
    private boolean bandera;

    personaDAO personaD = null;
    
    public void nuevoPersona(){
        persona=new personaC();
        bandera=true;
    }
    
     public void cancelarPersona(){
        
        bandera=false;
    }
      public void editarPersona(){
        
        bandera=true;
    }
      public void limpiarBusqueda(){
          busPaterno="";
          busMaterno="";
          busNombres="";
    }   
   
      public List<personaC> mostrarPersonaDocenteContrato(int institucion,String periodo){
          personaD=new personaDAO();
          personaL=personaD.mostrarDocenteContrato(institucion, periodo);
          return personaL;
      }
      
     public List<personaC> mostrarResponsableFirma(){
         personaD=new personaDAO();
         personaL=personaD.mostrarPersonaResponsableFirma();
         return personaL;
     }
     public void insertarUbigeo(String departamento,String provincia,String distrito){
         persona.setDireccion_ubigeo(departamento +""+provincia +""+distrito);
         util.consolaCliente( persona.getDireccion_ubigeo());
     }
    
    public void insertarPersona(){
         String personaR="";
         personaD=new personaDAO();
         personaR=personaD.insertar(this.persona);
         this.persona=personaD.mostrarPersona(personaR);                 
    }
    
    public void insertarPersona(personaC item){
         
         personaD=new personaDAO();
         personaD.insertar(item);
         bandera=false;
    }
    
    
     public List<personaC> mostrarPersonaPrincipal(int institucion,String periodo,String carrera,String malla) {
        personaD = new personaDAO();

        personaL = personaD.mostrarPersonaPrincipal(institucion, periodo,carrera,malla);

        return personaL;
    }
    
    
    public List<personaC> mostrarPersonaInstitucionPeriodo(int institucion,String periodo) {
        personaD = new personaDAO();

        personaL = personaD.BuscaPersonaPeriodoInstitucion(institucion, periodo);

        return personaL;
    }
    
       public List<personaC> mostrarPersonalSecundario(int institucion,String periodo) {
        personaD = new personaDAO();

        personaL = personaD.mostrarPersonalSecundario(institucion, periodo);

        return personaL;
    }

    public List<personaC> mostrarDocenteMarcacion(String periodo, String carrera, String tipo) {
        personaD = new personaDAO();
        personaL = personaD.mostrarDocenteMarcacion(periodo, carrera, tipo);
        return personaL;
    }

    public List<personaC> mostrarPersonal() {
        personaD = new personaDAO();
        personaL = personaD.mostrarPersonal();
        return personaL;
    }
    
    public List<personaC> mostrarTipoPersonal(int tipoPersona) {
        personaD = new personaDAO();
        personaL = personaD.mostrarTipoPersona(tipoPersona);
        return personaL;
    }
    
    public List<personaC> mostrarPersonaOficina(int institucion,int tipoPersona) {
        personaD = new personaDAO();
        personaL = personaD.mostrarPersonaOficina(institucion,tipoPersona);
        return personaL;
    }

    public List<personaC> buscarAlumno(String institucion, String paterno, String materno, String nombre) {

        personaD = new personaDAO();
        personaL = new ArrayList<>();
        personaL = personaD.BuscaAlumno(institucion, paterno, materno, nombre);
        return personaL;

    }

    public List<personaC> mostrarPersonalCarrera(String institucion, String periodo, String carrera) {
        personaD = new personaDAO();
        personaL = personaD.mostrarPersonalCarrera(institucion, periodo, carrera);
        return personaL;
    }

    public List<personaC> mostrarPersonalxCarrera(int institucion, String periodo, String carrera) {
        personaD = new personaDAO();
        personaL = personaD.mostrarPersonalxCarrera(institucion, periodo, carrera);
        return personaL;
    }
     public List<personaC> mostrarPersonalTurno(String institucion, String periodo, String turno) {
        personaD = new personaDAO();
        personaL = personaD.mostrarPersonalTurno(institucion, periodo, turno);
        return personaL;
    }

    public List<personaC> mostrarAlumnoPeriodoCarrera(int institucion, String periodo, String carrera) {
        personaD = new personaDAO();
        personaL = personaD.mostrarAlumnoCarrera(institucion, periodo, carrera);
        return personaL;
    }

    public List<personaC> mostrarAlumnoCarrera(int institucion, String carrera) {
        personaD = new personaDAO();
        personaL = personaD.mostrarAlumnoCarrera(institucion, carrera);
        return personaL;
    }
    
    public List<personaC> mostrarPersonaChat(int tipoTrabajor, int oficina) {
        personaD = new personaDAO();
        personaL = personaD.mostrarPersonaChat(tipoTrabajor, oficina);
        return personaL;
    }

    public personaC CodigoPersonaPersonal(String personal) {
        personaD = new personaDAO();
        persona = personaD.CodigoPersonaPersonal(personal);
        return persona;
    }

    public personaC mostrarPersona(String persona) {
        
        personaD = new personaDAO();
        
        this.persona = personaD.getBuscaPersonaCodigo(persona);
        return this.persona;
    }

    public void imprimir(String codigo) throws ClassNotFoundException, SQLException, JRException, IOException {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conexion = DriverManager.getConnection("jdbc:sqlserver://192.168.1.249;databaseName=BDUP", "anavarro", "123456");
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletCont = (ServletContext) context.getExternalContext().getContext();

        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        response.setContentType("application/pdf");
        ServletOutputStream out = response.getOutputStream();

        JasperReport reporte = (JasperReport) JRLoader.loadObject(servletCont.getRealPath("ireport/datos.jasper"));
        Map parametros = new HashMap();
        parametros.put("per", codigo);

        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);

        JRExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);

        exporter.exportReport();

    }

    public personaC getPersona() {
        return persona;

    }

    public void setPersona(personaC persona) {
        this.persona = persona;
    }

    public void mostrarImagen() {

        personaD = new personaDAO();
       // foto= perDAO.obtenerImagenEnBD();

    }

    public personaC buscarPersonaAlumno(int institucion, String alumno) {
        personaD = new personaDAO();
        persona = personaD.busquedaAlumnocodigo(institucion, alumno);
        return persona;
    }

    public List<personaC> filtroPersona(String paterno, String materno, String nombres) {

        personaD = new personaDAO();
        personaL = personaD.filtroPersona(paterno.trim(), materno.trim(), nombres.trim());
        return personaL;
    }
    
    public List<personaC> filtroPersonal(String paterno, String materno, String nombres) {

        personaD = new personaDAO();
        personaL = personaD.filtroPersonal(paterno.trim(), materno.trim(), nombres.trim());
        return personaL;
    }
    
    public List<personaC> filtroAlumno(String institucion,String paterno,String materno,String nombres) {
        util.consolaCliente(paterno);
        
        util.consolaCliente(materno);
        util.consolaCliente(nombres);
        personaD = new personaDAO();

        
        personaL = personaD.BuscaAlumno(institucion, paterno.trim(), materno.trim(), nombres.trim());
        return personaL;
    }
    
    public void buscarPersonal() {

        personaD = new personaDAO();
        personaL = new ArrayList<>();
        personaL = personaD.BuscaPersona(busPaterno, busMaterno, busNombres);
           //return personalL;

    }


   
    public List<personaC> getPersonalL() {
        return personaL;
    }

   
    public void setPersonalL(List<personaC> personalL) {
        this.personaL = personalL;
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

   
    public personaC getSeleccionTabla() {
        return seleccionTabla;
    }

   
    public void setSeleccionTabla(personaC seleccion) {
        this.seleccionTabla = seleccion;
    }

    
    public String getBusCodigo() {
        return busCodigo;
    }

 
    public void setBusCodigo(String busCodigo) {
        this.busCodigo = busCodigo;
    }

   
    public List<personaC> getPersonalPeriodoL() {
        return personalPeriodoL;
    }

    public void setPersonalPeriodoL(List<personaC> personalPeriodoL) {
        this.personalPeriodoL = personalPeriodoL;
    }

    public String getSeleccion() {
        return seleccion;
    }

   
    public void setSeleccion(String seleccion) {
        this.seleccion = seleccion;
    }


    public boolean isBandera() {
        return bandera;
    }

  
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

}
