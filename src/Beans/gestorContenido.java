
package Beans;

import DAO.cursoSeccionDocenteDAO;
import DAO.subidaArchivoDAO;
import ENTIDAD.cursoSeccionDocenteC;
import ENTIDAD.subidaArchivoC;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "gestorContenidoB")
@ViewScoped
public class gestorContenido  {

    private List<cursoSeccionDocenteC> cursoSeccionDocenteL = new ArrayList<>();
    private cursoSeccionDocenteC cursoSeccionDocente = new cursoSeccionDocenteC();
    private List<subidaArchivoC> subidaArchivoL = new ArrayList<>();
    private int opcion = 0;

    private String[] carpetas = {"DIAPOSITIVAS", "SEPARATAS", "PRACTICAS"};
    private String[] semana = {"semana1", "semana2", "semana3", "semana4", "semana5", "semana6",};
    private String[] url;
    String ruta = "Upig/";

    cursoSeccionDocenteDAO cursoSeccionDocenteD;
    subidaArchivoDAO subidaArchivoD;

    public List<cursoSeccionDocenteC> mostrarCursoSeccionDocente(int institucion, String periodo, String alumno) {

        cursoSeccionDocenteD = new cursoSeccionDocenteDAO();
        cursoSeccionDocenteL = cursoSeccionDocenteD.mostrarCursoSeccionAlumno(institucion, periodo, alumno);
        return cursoSeccionDocenteL;
    }

    public void mostrarArchivos(cursoSeccionDocenteC item) {
        opcion = 1;
        cursoSeccionDocente = item;
        ruta = "Upig" + "/" + item.getCurso();
        url = ruta.split("/");
    }

    public void mostrarSemana(String semana) {
        opcion = 2;

        ruta = ruta + "/" + semana;
        url = ruta.split("/");
    }

    public void mostrarDocumentos(int institucion, String periodo, String carrera, String curso, String seccion, String carpeta) {
        opcion = 3;
        /*
         System.out.println( url.length);
         int[] temp = new int[url.length + 1];
         System.arraycopy(url, 0, temp, 0,  Math.min(url.length, temp.length));
         System.out.println( url.length);
         //url[url.length-1]="1111";
         url=new String[url.length+1];
       
         url[url.length-1]="indice: " + url.length;
         System.out.println( url.length);
         */
        subidaArchivoD = new subidaArchivoDAO();
        subidaArchivoL = subidaArchivoD.mostrarSubidaArchivo(institucion, periodo, carrera, curso, seccion, carpeta);

        ruta = ruta + "/" + semana;
        url = ruta.split("/");
    }

    public void desplazarDireccion() {
        ruta = "carpeta/carpet1/xxx";
        url = ruta.split("/");
    }
    public List<cursoSeccionDocenteC> getCursoSeccionDocenteL() {
        return cursoSeccionDocenteL;
    }
    public void setCursoSeccionDocenteL(List<cursoSeccionDocenteC> cursoSeccionDocenteL) {
        this.cursoSeccionDocenteL = cursoSeccionDocenteL;
    }
    public String[] getUrl() {
        return url;
    }
    public void setUrl(String[] url) {
        this.url = url;
    }
    public List<subidaArchivoC> getSubidaArchivoL() {
        return subidaArchivoL;
    }
    public void setSubidaArchivoL(List<subidaArchivoC> subidaArchivoL) {
        this.subidaArchivoL = subidaArchivoL;
    }
    public int getOpcion() {
        return opcion;
    }
    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }
    public String[] getSemana() {
        return semana;
    }
    public void setSemana(String[] semana) {
        this.semana = semana;
    }
    public cursoSeccionDocenteC getCursoSeccionDocente() {
        return cursoSeccionDocente;
    }
    public void setCursoSeccionDocente(cursoSeccionDocenteC cursoSeccionDocente) {
        this.cursoSeccionDocente = cursoSeccionDocente;
    }
    public String[] getCarpetas() {
        return carpetas;
    }
    public void setCarpetas(String[] carpetas) {
        this.carpetas = carpetas;
    }
    

}
