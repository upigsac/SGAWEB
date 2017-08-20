
package Beans;

import DAO.carrerasDAO;
import DAO.periodoDAO;
import DAO.personaDAO;

import DAO.seccionDAO;
import ENTIDAD.carrerasC;
import ENTIDAD.periodoC;
import ENTIDAD.personaC;
import ENTIDAD.seccionC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



@ManagedBean(name = "boleta_notasB")
@SessionScoped
public class boletaNotas {

    private List<periodoC> listaperiodo;
    private List<carrerasC> listacarrera;
    private List<seccionC> listaseccion;
    private List<personaC> listaalumno;
    private int seleccionPeriodo;
    private String seleccionCarrera;
    private String seleccionSeccion;
    private String seleccionAlumno;

   
    periodoDAO perid = null;
    carrerasDAO carrera = null;
    seccionDAO seccion = null;
    personaDAO persona = null;

    public List<carrerasC> mostrarCarreraPerido(int institucion,String  periodo) {
        carrera = new carrerasDAO();
        listacarrera = carrera.listaCarreraPeriodo(institucion,periodo);
        return listacarrera;
    }

    public void mostrarSeccionPeridoCarrera() {
        seccion = new seccionDAO();
        listaseccion = seccion.mostrarSeccionPeriodoCarrera(seleccionPeriodo, seleccionCarrera);
    }

   

    public List<personaC> mostrarAlumnoPeridoCarrera(int periodo,String carrera) {
        persona = new personaDAO();
        listaalumno = persona.BuscaPersonaPeriodoCarrera(periodo,carrera);
        return listaalumno;
    }

    public List<periodoC> getListaperiodo() {
        perid = new periodoDAO();

        listaperiodo = perid.getPeriodoAdminitrativo(1);
        return listaperiodo;
    }
    public void setListaperiodo(List<periodoC> listaperiodo) {
        this.listaperiodo = listaperiodo;
    }
    public int getSeleccionPeriodo() {
        return seleccionPeriodo;
    }
    public void setSeleccionPeriodo(int seleccionPeriodo) {
        this.seleccionPeriodo = seleccionPeriodo;
    }
    public List<carrerasC> getListacarrera() {
        return listacarrera;
    }
    public void setListacarrera(List<carrerasC> listacarrera) {
        this.listacarrera = listacarrera;
    }
    public String getSeleccionCarrera() {
        return seleccionCarrera;
    }
    public void setSeleccionCarrera(String seleccionCarrera) {
        this.seleccionCarrera = seleccionCarrera;
    }
    public List<seccionC> getListaseccion() {
        return listaseccion;
    }
    public void setListaseccion(List<seccionC> listaseccion) {
        this.listaseccion = listaseccion;
    }
    public String getSeleccionSeccion() {
        return seleccionSeccion;
    }
    public void setSeleccionSeccion(String seleccionSeccion) {
        this.seleccionSeccion = seleccionSeccion;
    }
    public List<personaC> getListaalumno() {

        return listaalumno;
    }
    public void setListaalumno(List<personaC> listaalumno) {
        this.listaalumno = listaalumno;
    }
    public String getSeleccionAlumno() {
        return seleccionAlumno;
    }
    public void setSeleccionAlumno(String seleccionAlumno) {
        this.seleccionAlumno = seleccionAlumno;
    }
}
