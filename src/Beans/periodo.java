
package Beans;

import DAO.pagosPendientesDAO;
import DAO.periodoDAO;
import ENTIDAD.periodoC;

import java.util.ArrayList;

import java.util.List;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

@ManagedBean(name = "periodoB")
@ViewScoped
public class periodo  {

    private periodoC periodo;
    private List<periodoC> periodoL;

    private List<ArrayList<String>> listaperiodoAcademico;
    private List<ArrayList<String>> listaPagosPendientes;


    private int tipoFormato;
    private String alumno;

    periodoDAO periodoD = null;
    pagosPendientesDAO ppdao = null;

    

    public periodoC mostrarPeriodo(int institucion, String periodo) {
        periodoD = new periodoDAO();
        this.periodo = periodoD.mostrarPeriodo(institucion, periodo);
        return this.periodo;
    }

  
     public List<periodoC> mostrarPeriodo(int institucion) {
        periodoD = new periodoDAO();
        return periodoD.mostrarPeriodoInstitucion(institucion);
        
    }


    public String reporteNotasAcademicas() {

        return "reporte.jsp";
    }

    public void mostrarPeridoAlumno(String alumno, String persona) {

        periodoD = new periodoDAO();

        periodoL = periodoD.getPeriodoAlumno(alumno);
        if (periodoL.size() > 0) {
            //-------------------------- periodo principal ---------------------------------------------
//            seleccionadoPrincipal = Integer.toString(periodoL.get(0).getPeriodo());
//          
//            seleccionPeriodoIni = periodoL.get(periodoL.size() - 1).getPeriodo();
//            seleccionPeriodoFin = periodoL.get(0).getPeriodo();
        }

    }

    public List<ArrayList<String>> mostrarPagosPendientes(String persona, String periodo) {
        ppdao = new pagosPendientesDAO();
        listaPagosPendientes = ppdao.pagosPendientesAlumno(persona, periodo);
        return listaPagosPendientes;
    }

 

    public int ultimoPeriodoAlumno(String alumno) {
        periodoD = new periodoDAO();
        return periodoD.ultimoPeriodoAlumno(alumno);

    }


    public void refrescar() {
        
        RequestContext.getCurrentInstance().execute("document.location=document.location");
    }
    public int getTipoFormato() {
        return tipoFormato;
    }
    public void setTipoFormato(int tipoFormato) {
        this.tipoFormato = tipoFormato;
    }

    public List<ArrayList<String>> getListaperiodoAcademico() {
        return listaperiodoAcademico;
    }

    public void setListaperiodoAcademico(List<ArrayList<String>> listaperiodoAcademico) {
        this.listaperiodoAcademico = listaperiodoAcademico;
    }

    public List<ArrayList<String>> getListaPagosPendientes() {
        return listaPagosPendientes;
    }

    public void setListaPagosPendientes(List<ArrayList<String>> listaPagosPendientes) {
        this.listaPagosPendientes = listaPagosPendientes;
    }

 
    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }
    public periodoC getPeriodo() {
        return periodo;
    }
    public void setPeriodo(periodoC periodo) {
        this.periodo = periodo;
    }
    public List<periodoC> getPeriodoL() {
        return periodoL;
    }
    public void setPeriodoL(List<periodoC> periodoL) {
        this.periodoL = periodoL;
    }

}
