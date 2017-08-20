package Beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import Beans.admin_notas.ColumnModel;
import DAO.cursoDAO;
import DAO.cursoSeccionDocenteDAO;
import DAO.cursoSeccionDocenteTipoExamenDAO;
import DAO.docente_notaDAO;
import DAO.personaDAO;
import DAO.personalDAO;
import DAO.seccionDAO;
import DAO.tipoExamenDAO;
import ENTIDAD.carrerasC;
import ENTIDAD.cursoSeccionDocenteC;
import ENTIDAD.cursoSeccionDocenteTipoExamenC;
import ENTIDAD.cursosC;
import ENTIDAD.personaC;
import ENTIDAD.seccionC;
import ENTIDAD.tipoExamenC;

@ManagedBean(name="ingresoNotaCampoClinicoB")
@ViewScoped
public class ingresoNotaCampoClinico {
private List<personaC> personaL=new ArrayList<>();
private List<carrerasC> carreraL; 
private List<seccionC> seccionL;
private List<cursosC> cursoL;
private List<tipoExamenC> tipoExamenL;
private List<cursoSeccionDocenteTipoExamenC> cursoSeccionDocenteTipoExamenL;
private List<ColumnModel> columnas;
private List<ArrayList<String>> columnL;
private List<HashMap<String,String>> notaL;
private boolean banderaAtras;

private String codPersona;
private String codPersonal;
private String codCarrera="000005"; // la carrera es por defecto de enfermeria 
private String codSeccion;
private String codCurso;
private String codTipoExamen;
private String codGrupo;
private String codMalla;
private int institucion;
private String periodo;
private cursoSeccionDocenteC cursoSeccionDocente; 

public ingresoNotaCampoClinico() {
	
}



public List<HashMap<String, String>> getNotaL() {
	return notaL;
}



public void setNotaL(List<HashMap<String, String>> notaL) {
	this.notaL = notaL;
}



public boolean isBanderaAtras() {
	return banderaAtras;
}



public void setBanderaAtras(boolean banderaAtras) {
	this.banderaAtras = banderaAtras;
}



public void createDynamicColumns() {
    
    columnas = new ArrayList<>();
    
    columnL = new ArrayList<>();
    columnas.add(new ColumnModel("ALUMNO", "alumno", false, 0,20,0));
    columnas.add(new ColumnModel("NOMBRES", "nombres", false, 0,20,0));
    
    columnL = new docente_notaDAO().getColumna(institucion, periodo, codMalla, codCarrera, codCurso, codTipoExamen);
    
    for (int i = 0; i < columnL.size(); i++) {
        
        
        //formula = Integer.parseInt(columnL.get(i).get(4).toString());
        columnas.add(new ColumnModel(columnL.get(i).get(2).toString() + "<br/>" + columnL.get(i).get(3).toString(), columnL.get(i).get(0).toString().toLowerCase(), (columnL.get(i).get(5).endsWith("0")), Integer.parseInt(columnL.get(i).get(5).toString()),Integer.parseInt(columnL.get(i).get(7).toString()),Integer.parseInt(columnL.get(i).get(6).toString())));
    }
    
    columnas.add(new ColumnModel("PROMEDIO", "promedio", false, 0,20,0));
    columnas.add(new ColumnModel("ESTADO", "estado_registro", false, 0,20,0));
    
   
       
    
     
}



public void mostrarSubNota(boolean bandera ) {
    
    //----- DATOS INFORMATIVOS
   
    //this.tipoExamen=new tipoExamenDAO().mostrarTipoExamen(tipoExamen);        
    banderaAtras=bandera;       
    //------------------
    
  
    
    createDynamicColumns();
    
    notaL = new ArrayList<>();       
    notaL = new docente_notaDAO().mostrarAlumnosTipoExamen(institucion, periodo, codMalla,"0", codCarrera, codCurso, codSeccion, codTipoExamen,codGrupo);
}


public void load(int institucion,String periodo){
	this.institucion=institucion;
	this.periodo=periodo;
	personaL = new personaDAO().mostrarPersonalSecundario(institucion, periodo);
}

public void mostrarSeccion(){
	codPersonal=new personalDAO().BuscaPersonaCodigo(codPersona).getcPersonal();
	seccionL = new seccionDAO().mostrarSeccionPersonalSecundario(institucion, periodo, codPersonal, codCarrera);
}
public void mostrarCurso(){
	cursoL = new cursoDAO().mostrarCursoPersonalSecundarion(institucion, periodo,codPersonal, codCarrera,codSeccion);
}
public void mostrarTipoExamen(){
	tipoExamenL= new tipoExamenDAO().mostrarTipoExamenPersonalSecundario(institucion, periodo, codPersonal, codCarrera, codSeccion, codCurso);
	cursoSeccionDocente=new cursoSeccionDocenteDAO().mostrarPeriodoCarreraCursoSeccion(institucion,  periodo, codCarrera, codCurso, codSeccion);
	codMalla=cursoSeccionDocente.getMalla();
}

public void mostrarGrupo(){
	cursoSeccionDocenteTipoExamenL=new cursoSeccionDocenteTipoExamenDAO().mostrarCursoSeccionDocenteTipoExamenGrupo(institucion, periodo, codPersonal, codCarrera, codSeccion, codCurso, codTipoExamen);
}


public List<cursoSeccionDocenteTipoExamenC> getCursoSeccionDocenteTipoExamenL() {
	return cursoSeccionDocenteTipoExamenL;
}



public String getCodMalla() {
	return codMalla;
}



public void setCodMalla(String codMalla) {
	this.codMalla = codMalla;
}



public void setCursoSeccionDocenteTipoExamenL(List<cursoSeccionDocenteTipoExamenC> cursoSeccionDocenteTipoExamenL) {
	this.cursoSeccionDocenteTipoExamenL = cursoSeccionDocenteTipoExamenL;
}



public String getCodGrupo() {
	return codGrupo;
}



public void setCodGrupo(String codGrupo) {
	this.codGrupo = codGrupo;
}



public String getCodTipoExamen() {
	return codTipoExamen;
}



public void setCodTipoExamen(String codTipoExamen) {
	this.codTipoExamen = codTipoExamen;
}



public List<tipoExamenC> getTipoExamenL() {
	return tipoExamenL;
}



public void setTipoExamenL(List<tipoExamenC> tipoExamenL) {
	this.tipoExamenL = tipoExamenL;
}



public String getCodCurso() {
	return codCurso;
}



public void setCodCurso(String codCurso) {
	this.codCurso = codCurso;
}



public List<cursosC> getCursoL() {
	return cursoL;
}



public void setCursoL(List<cursosC> cursoL) {
	this.cursoL = cursoL;
}



public List<seccionC> getSeccionL() {
	return seccionL;
}



public void setSeccionL(List<seccionC> seccionL) {
	this.seccionL = seccionL;
}



public String getCodPersona() {
	return codPersona;
}



public void setCodPersona(String codPersona) {
	this.codPersona = codPersona;
}



public String getCodCarrera() {
	return codCarrera;
}



public void setCodCarrera(String codCarrera) {
	this.codCarrera = codCarrera;
}



public String getCodSeccion() {
	return codSeccion;
}



public void setCodSeccion(String codSeccion) {
	this.codSeccion = codSeccion;
}



public List<carrerasC> getCarreraL() {
	return carreraL;
}



public void setCarreraL(List<carrerasC> carreraL) {
	this.carreraL = carreraL;
}



public List<personaC> getPersonaL() {
	return personaL;
}

public void setPersonaL(List<personaC> personaL) {
	this.personaL = personaL;
}





}
