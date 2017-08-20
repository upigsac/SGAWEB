/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import DAO.seccionDAO;
import ENTIDAD.seccionC;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "seccionB")
@ViewScoped
public class seccion implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private seccionC seccion=new seccionC();
    private seccionC seccionT=new seccionC();
    private List<seccionC> seccionL;
    private String seleccion;
    private List<ArrayList<String>> listaSecciones;

    private String seleccionTurno;
    private boolean  bandera;
    private String cadena;

    seccionDAO seccionD = null;
    
    public void nuevo(int institucion){
        seccionT=seccion;
        seccion=new seccionC(institucion, "", "", 1);
        bandera=true;
    }
    
     public void insertar(seccionC item){
        seccionD=new seccionDAO();
        seccionD.insertarSeccion(item);
        bandera=false;
    }
     public void cancelar(){
        seccion=seccionT;
        bandera=false;
    }
       public void editar(){
        seccionT=seccion;
        bandera=true;
    }
     public void filtro(int institucion,String cadena){
         
         seccionD=new seccionDAO();
         seccionL=seccionD.filtroSeccion(institucion,cadena);
         
     }

    public List<seccionC> mostrarSeccion(int institucion, String periodo, String carrera, String nivel, String turno) {

        seccionD = new seccionDAO();
        seccionL = seccionD.mostrarSeccion(institucion, periodo, carrera, nivel, turno);
        return seccionL;
    }
      public List<seccionC> mostrarSeccion(int institucion, String periodo, String carrera) {

        seccionD = new seccionDAO();
        seccionL = seccionD.mostrarSeccion(institucion, periodo, carrera);
        return seccionL;
    }
      
       public List<seccionC> filtroSeccion(int institucion, String periodo, String carrera,int turno) {

        seccionD = new seccionDAO();
        seccionL = seccionD.filtroSeccion(institucion, periodo, carrera,turno);
        return seccionL;
    }
      
      
      
      
    public List<seccionC> mostrarListaSeccion(int institucion, String periodo, String carrera,  String curso) {

        seccionD = new seccionDAO();
        seccionL = seccionD.mostrarListaSeccion(institucion, periodo, carrera, curso);
        return seccionL;
    }
    
    public List<seccionC> mostrarListaSeccion(String institucion, String periodo,String personal ,String carrera,  String curso) {

        seccionD = new seccionDAO();
        seccionL = seccionD.mostrarListaSeccion(institucion, periodo,personal, carrera, curso);
        return seccionL;
    }
    
    public List<seccionC> mostrarSeccion(int institucion, String periodo, String carrera, int nivel) {

        seccionD = new seccionDAO();
        seccionL = seccionD.mostrarSeccion(institucion, periodo, carrera, nivel);
        return seccionL;
    }
    
      public List<seccionC> mostrarSeccionPersonalSecundario(int institucion, String periodo, String personal, String carrera) {

        seccionD = new seccionDAO();
        seccionL = seccionD.mostrarSeccionPersonalSecundario(institucion, periodo, personal, carrera);
        return seccionL;
    }

    public List<seccionC> mostrarSeccion(int institucion, String periodo, String curso, String personal) {

        seccionD = new seccionDAO();
        seccionL = seccionD.mostrarSeccion(institucion, periodo, curso, personal);
        return seccionL;
    }

    public seccionC mostrarSeccion(int institucion, String seccion) {

        seccionD = new seccionDAO();
        this.setSeccion(seccionD.mostrarSeccion(institucion, seccion));
        return this.getSeccion();
    }
    
    
    public List<seccionC> mostrarSeccionPeriodoCarreraDocente(int institucion, String periodo,String carrera,String persona) {

        seccionD = new seccionDAO();
        seccionL=seccionD.mostrarSeccionPeriodoCarreraDocente(institucion, periodo,carrera,persona);
        return seccionL;
    }
    
     public List<seccionC> mostrarSeccion(int institucion) {

        seccionD = new seccionDAO();
        seccionL = seccionD.mostrarSeccion(institucion);
        return seccionL;
    }

  

    public List<seccionC> mostrarSeccionInstitucionPeriodoCarreraDocente(String institucion, String periodo, String carrera, String personal) {

        seccionD = new seccionDAO();
        seccionL = seccionD.mostrarSeccionInstitucionPeriodoDocente(institucion, periodo, carrera, personal);
        return seccionL;
    }

    public void mostrarSeccionesAlummo(String usuario, String periodo) {
        seccionD = new seccionDAO();
        listaSecciones = new ArrayList<>();
        listaSecciones = seccionD.listaSeccionAlumno(usuario, periodo);
    }

    public void mostrarSeccionesPeriodoCarrera(int periodo, String carrera) {
        seccionD = new seccionDAO();
        listaSecciones = new ArrayList<>();
        seccionL = seccionD.mostrarSeccionPeriodoCarrera(periodo, carrera);
    }

    public void mostrarSeccionesPeriodoCarreraTurno(int periodo, String carrera, String turno) {
        seccionD = new seccionDAO();

        seccionL = seccionD.mostrarSeccionPeriodoCarreraTurno(periodo, carrera, turno);
    }

    public String imprimir() {
        return "reporte.jsp";
    }

 
    public List<seccionC> getSeccionL() {
        return seccionL;
    }

   
    public void setSeccionL(List<seccionC> SeccionL) {
        this.seccionL = SeccionL;
    }

   
    public List<ArrayList<String>> getListaSecciones() {
        return listaSecciones;
    }

    
    public void setListaSecciones(List<ArrayList<String>> listaSecciones) {
        this.listaSecciones = listaSecciones;
    }

  
    public String getSeleccionTurno() {
        return seleccionTurno;
    }

   
    public void setSeleccionTurno(String seleccionTurno) {
        this.seleccionTurno = seleccionTurno;
    }

   
    public seccionC getSeccion() {
        return seccion;
    }

    
    public void setSeccion(seccionC seccion) {
        this.seccion = seccion;
    }

   
    public boolean isBandera() {
        return bandera;
    }

    
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    
    public seccionC getSeccionT() {
        return seccionT;
    }

   
    public void setSeccionT(seccionC seccionT) {
        this.seccionT = seccionT;
    }

    public String getCadena() {
        return cadena;
    }

  
    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

  
    public String getSeleccion() {
        return seleccion;
    }

 
    public void setSeleccion(String seleccion) {
        this.seleccion = seleccion;
    }

  
 

}
