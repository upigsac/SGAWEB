package Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import Beans.admin_notas.listaTipoExamen;

import DAO.alumnoCursoNotaDAO;
import DAO.alumnoCursoSeccionDAO;
import DAO.autorizaNotaDAO;
import DAO.carrerasDAO;
import DAO.cursoDAO;
import DAO.cursoSeccionCierreActaDAO;
import DAO.cursoSeccionDocenteDAO;

import DAO.formulaDAO;
import DAO.formulaExamenDAO;

import DAO.personaDAO;
import DAO.personalDAO;
import DAO.registrotmDAO;
import DAO.seccionDAO;
import DAO.seccionPeriodoDAO;
import DAO.tipoExamenDAO;
import ENTIDAD.alumnoCursoNotaC;
import ENTIDAD.alumnoCursoSeccionC;
import ENTIDAD.autorizaNotaC;
import ENTIDAD.carrerasC;
import ENTIDAD.cursoSeccionCierreActaC;
import ENTIDAD.cursoSeccionDocenteC;
import ENTIDAD.cursosC;
import ENTIDAD.formulaC;
import ENTIDAD.formulaExamenC;
import ENTIDAD.personaC;
import ENTIDAD.personalC;
import ENTIDAD.registrotmC;
import ENTIDAD.seccionC;
import ENTIDAD.seccionPeriodoC;
import ENTIDAD.semanaExamenC;
import ENTIDAD.tipoExamenC;




@ManagedBean(name="registroNotaAdminB")
@ViewScoped
public class registroNotaAdmin implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<cursoSeccionDocenteC> cursoSeccionDocenteL;
    private List<cursoPrincipalSecundario> cursoPrincipalSecundarioL=new ArrayList<>();
    private cursoPrincipalSecundario cursoPrincipalSecundario=null;
    private List<alumnoCursoSeccionPersona> alumnoCursoSeccionPersonaL=new ArrayList<>();
    private List<cabeceraNota> cabeceraNotaL=new ArrayList<>();
    private String tipoExamenPadre=null;
  
    private String periodo;
    private int institucion;
    private personalC personal;
    private List<personaC> personaL=new ArrayList<>();
    private personaC persona;
    private String filtroPersonal="";
    private cursoSeccionCierreActaC cursoSeccionCierreActa=null; 
    private List<autorizaNotaC> autotizaNotaL=new ArrayList<>();
    private autorizaNotaC autotizaNota;
    
  
	private TreeNode root3;
    private TreeNode[] selectedNodes1;
    
    
    private int aprobado;
    private int desaprobado;
    private LineChartModel animatedModel1=new LineChartModel();
    
    
    
   



	

	
	public void load(int institucion,String periodo){
    	this.institucion=institucion;
    	this.periodo=periodo;
    	mostrarDocente();
    }
	 public registroNotaAdmin() {
			
			
}

    
	

	
	
	  public void mostrarCuadroResumen(alumnoCursoSeccionC item){
		  
          LineChartModel model = new LineChartModel();
           LineChartSeries series1 = new LineChartSeries();
           
       
           
           
           series1.setLabel(item.getAlumno());

         for (alumnoCursoNotaC itemAlumnoCursoNota : new  alumnoCursoNotaDAO().mostrarAlumno(item.getInstitucion(), item.getPeriodo(), item.getCarrera(), item.getMalla(), item.getCurso(), item.getSeccion(), item.getAlumno())) {
           series1.set(itemAlumnoCursoNota.getTipoExamen(), itemAlumnoCursoNota.getNota());
           System.out.println(itemAlumnoCursoNota.getTipoExamen());
           System.out.println(itemAlumnoCursoNota.getNota());
         }
         model.addSeries(series1);
       animatedModel1 = model;
       animatedModel1.setTitle("PROGRESO ..");
       animatedModel1.setAnimate(true);
       animatedModel1.setLegendPosition("se");
       animatedModel1.setShowPointLabels(true);
       animatedModel1.getAxes().put(AxisType.X, new CategoryAxis("TIPO NOTA"));
       Axis yAxis = animatedModel1.getAxis(AxisType.Y);
       yAxis.setMin(0);
       yAxis.setMax(20);
    
       	util.script("dlgProgreso.show()");
   }
	
 public void mostrarCuadroResumen(){
		  
          LineChartModel model = new LineChartModel();
           
       	 
       	 
         for (alumnoCursoSeccionPersona item : alumnoCursoSeccionPersonaL) {
        	 LineChartSeries series1 = new LineChartSeries();
        	 series1.setLabel(item.getAlumnoCursoSeccion().getAlumno());
        	 for (Entry<String, alumnoCursoNotaC> sub :item.getAlumnoCursoNotaL().entrySet()){
        		 series1.set(sub.getValue().getTipoExamen(),sub.getValue().getNota());
        		 
        	 }
        		 
			
        	 model.addSeries(series1);
 		}

        
        
       animatedModel1 = model;
       animatedModel1.setTitle("PROGRESO ..");
       animatedModel1.setAnimate(true);
       animatedModel1.setLegendPosition("se");
       animatedModel1.setShowPointLabels(true);
       animatedModel1.getAxes().put(AxisType.X, new CategoryAxis("TIPO NOTA"));
       Axis yAxis = animatedModel1.getAxis(AxisType.Y);
       yAxis.setMin(0);
       yAxis.setMax(20);
    
       	util.script("dlgProgreso.show()");
   }
	
	
	
	
	
	
	
	
	
	public void insertarCierreActa(){
		
		 	new cursoSeccionCierreActaDAO().insertar(new cursoSeccionCierreActaC(cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getInstitucion(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getPeriodo(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCarrera(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getMalla(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCurso(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getSeccion(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getGrupo(), util.fechaHoy(), "dd", 1));
		 	cursoSeccionCierreActa=  new cursoSeccionCierreActaDAO().mostrarCursoSeccionCierreActa(cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getInstitucion(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getPeriodo(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCarrera(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCurso(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getSeccion());
		 	 // CABECERA
	        mostrarNotaPadre();
	        // ALUMNO
	        mostrarAlumnoCursoNotaPadre();
		 	
		 	
			util.script("notificacion('Se cerro el acta correctamente',500,5000,'correcto')");
	}
	


	
	
	public void nuevoAutorizaNota(){
		autotizaNota=new autorizaNotaC();
		
	        root3=new CheckboxTreeNode(new listaTipoExamen("1","PRINCIPAL",true),null);
	         for (tipoExamenC item : new tipoExamenDAO().mostrarTipoExamen( cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getInstitucion(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getPeriodo(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCarrera(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getMalla(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCurso(),"P")) {
	             TreeNode child=new CheckboxTreeNode(item,root3);
	             for (tipoExamenC itemHijo : new tipoExamenDAO().mostrarTipoExamen(cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getInstitucion(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getPeriodo(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCarrera(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getMalla(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCurso(),"H",item.getTipo_examen())) {
	                     TreeNode childItem=new CheckboxTreeNode(itemHijo,child);
	                 }
	          
	                child.setExpanded(true);
	         }
		
		util.script("dlgAutorizarNota.show()");
	}
	
	public void mostrarAutorizaNota(){
		autotizaNotaL=new autorizaNotaDAO().mostrarAutorizaNota(cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getInstitucion(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getPeriodo(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCarrera(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getMalla(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCurso(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getSeccion());
		util.script("dlgAutorizar.show()");
	}
    
    public void insertarAutorizaNota(){
    	
    	 for(TreeNode item : selectedNodes1) {
    		 
    		 tipoExamenC fila=(tipoExamenC)item.getData();
    		 
    		 if(item.isLeaf()){
    			 new autorizaNotaDAO().insertar(new autorizaNotaC( cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getInstitucion(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getPeriodo(), -1, null, cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCarrera(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getMalla(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCurso(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getSeccion() ,0, cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getPersonal(), fila.getTipo_examen(), "%", autotizaNota.getFechaInicio(), autotizaNota.getFechaFin(),autotizaNota.getObservacion(), 1)); 
    		 }
    		
    		
            }
            
          util.script("dlgAutorizarNota.hide()");
          util.script("dlgAutorizar.hide()");
          util.script("notificacion('Se registro Correctamente',500,5000,'correcto')");
    }
  
 public void mostrarDocente(){
	 personaL= new personaDAO().mostrarPersonaPrincipal(institucion, periodo, "%", "%",filtroPersonal);
 }

    
    public void selectPersona(personaC item){
    	persona=item;
    	personal=new personalDAO().mostrarPersona(persona.getPersona());
    	aprobado=0;desaprobado=0;
        alumnoCursoSeccionPersonaL=new ArrayList<>();
    	
    	
    	
    	
    	mostrarCursoDocente();
    }


	

	public void insertarNota(alumnoCursoNotaC itemAlumnoCursoNota ,int indice){
        String promedio="";
    
        if(itemAlumnoCursoNota.getNotaT().equals(""))
        {
    
            promedio=new alumnoCursoNotaDAO().eliminar(itemAlumnoCursoNota);
        }
        else{
            itemAlumnoCursoNota.setNota(Double.parseDouble(itemAlumnoCursoNota.getNotaT()));
            
            promedio=new alumnoCursoNotaDAO().inserta(itemAlumnoCursoNota);
            
            
            
        }
        System.out.println("promedio:" + promedio);
       util.script("$('.tbNota tbody tr:nth-child("+ (indice )+") td:nth-last-child(2)').text('"+promedio+"');");
    }
    
    
    public void mostrarCursoDocente(){
       
    	cursoPrincipalSecundario=null;
    	cursoPrincipalSecundarioL=new ArrayList<>();
        cursoPrincipalSecundario itemCursoPrincipalSecundario;
        for (cursoSeccionDocenteC itemCursoSeccionDocenteP : new cursoSeccionDocenteDAO().mostrarCursoSeccionDocentePrincipal(""+institucion, periodo, "%", "%", "%", "%", personal.getPersonal() )) {
            
            itemCursoPrincipalSecundario=new cursoPrincipalSecundario(itemCursoSeccionDocenteP);
            itemCursoPrincipalSecundario.cursos=new cursoDAO().mostrarCurso(itemCursoSeccionDocenteP.getCurso());
            itemCursoPrincipalSecundario.carrera=new carrerasDAO().mostrarCarrerasCodigo(itemCursoSeccionDocenteP.getCarrera());
            itemCursoPrincipalSecundario.seccion=new seccionDAO().mostrarSeccion(itemCursoSeccionDocenteP.getInstitucion(), itemCursoSeccionDocenteP.getSeccion());
            itemCursoPrincipalSecundario.formula=new formulaDAO().mostrarFormula(itemCursoSeccionDocenteP.getInstitucion(), itemCursoSeccionDocenteP.getPeriodo(), itemCursoSeccionDocenteP.getCarrera(), itemCursoSeccionDocenteP.getMalla(), itemCursoSeccionDocenteP.getCurso());
            itemCursoPrincipalSecundario.periodoSeccion= new seccionPeriodoDAO().mostrarSeccionPeriodo(itemCursoSeccionDocenteP.getInstitucion(), itemCursoSeccionDocenteP.getPeriodo(), itemCursoSeccionDocenteP.getCarrera(), itemCursoSeccionDocenteP.getSeccion());
            itemCursoPrincipalSecundario.registrotmL =new registrotmDAO().mostrarRegistrotm(itemCursoSeccionDocenteP.getInstitucion(), itemCursoSeccionDocenteP.getPeriodo(), itemCursoSeccionDocenteP.getCarrera(), itemCursoSeccionDocenteP.getCurso(), itemCursoSeccionDocenteP.getSeccion(), itemCursoSeccionDocenteP.getPersonal(), "1");
            cursoSecundario itemCursoSecundario;
            for (cursoSeccionDocenteC itemCursoSeccionDocenteS : new cursoSeccionDocenteDAO().mostrarCursoSeccionDocenteSecundario(itemCursoSeccionDocenteP.getInstitucion(), itemCursoSeccionDocenteP.getPeriodo(), itemCursoSeccionDocenteP.getCarrera(), itemCursoSeccionDocenteP.getMalla(), itemCursoSeccionDocenteP.getCurso(), itemCursoSeccionDocenteP.getSeccion(), itemCursoSeccionDocenteP.getPersonal())) {
                itemCursoSecundario=new cursoSecundario(itemCursoSeccionDocenteS);                
                itemCursoSecundario.curso=new cursoDAO().mostrarCurso(itemCursoSeccionDocenteS.getCurso());
                itemCursoSecundario.carrera=new carrerasDAO().mostrarCarrerasCodigo(itemCursoSeccionDocenteS.getCarrera());
                itemCursoSecundario.seccion=new seccionDAO().mostrarSeccion(itemCursoSeccionDocenteS.getInstitucion(), itemCursoSeccionDocenteS.getSeccion());
                itemCursoPrincipalSecundario.getCursoSeccionDocenteSecundarioL().add(itemCursoSecundario);
                //itemCursoPrincipalSecundario.getCursoSeccionDocenteSecundarioL().add(itemCursoSeccionDocenteS);
            }
            cursoPrincipalSecundarioL.add(itemCursoPrincipalSecundario);
        }
    }
    public void seleccionCursoSeccion(cursoPrincipalSecundario itemCursoPrincipalSecundario){
        
        cursoPrincipalSecundario=itemCursoPrincipalSecundario;
        tipoExamenPadre=null;
        
        cursoSeccionCierreActa=  new cursoSeccionCierreActaDAO().mostrarCursoSeccionCierreActa(cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getInstitucion(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getPeriodo(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCarrera(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCurso(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getSeccion());

        
        
        // CABECERA
        mostrarNotaPadre();
        // ALUMNO
        mostrarAlumnoCursoNotaPadre();
        
          
    }
    public void mostrarAlumnoCursoNotaPadre(){
        
      
       aprobado=0;desaprobado=0;
        alumnoCursoSeccionPersonaL=new ArrayList<>();
        alumnoCursoSeccionPersona itemAlumnoCursoSeccionPersona;
      
      
        for(alumnoCursoSeccionC itemAlumnoCursoSeccion : new alumnoCursoSeccionDAO().mostrarAlumnoCursoSeccion(cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getInstitucion(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getPeriodo(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCarrera(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getMalla(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCurso(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getSeccion(), "%"))
        {
           
        
        	itemAlumnoCursoSeccionPersona=new alumnoCursoSeccionPersona(itemAlumnoCursoSeccion, new personaDAO().busquedaAlumnocodigo(itemAlumnoCursoSeccion.getInstitucion(), itemAlumnoCursoSeccion.getAlumno()));        	
        //	itemAlumnoCursoSeccionPersona.carrera=new carrerasDAO().mostrarCarrerasCodigo(itemAlumnoCursoSeccion.getCarrera());
        	
        	for(alumnoCursoNotaC itemAlumnoCursoNota :new  alumnoCursoNotaDAO().mostrarAlumnoCursoNotaP(itemAlumnoCursoSeccion.getInstitucion(), itemAlumnoCursoSeccion.getPeriodo(), itemAlumnoCursoSeccion.getCarrera(), itemAlumnoCursoSeccion.getMalla(), itemAlumnoCursoSeccion.getCurso(), itemAlumnoCursoSeccion.getSeccion(), itemAlumnoCursoSeccion.getAlumno(),"%"))
            {
           
                itemAlumnoCursoSeccionPersona.alumnoCursoNotaL.put(itemAlumnoCursoNota.getTipoExamen(), itemAlumnoCursoNota);
           
                if(itemAlumnoCursoNota.getTipoExamen().equals("PROF")  ){
                	
                	if(itemAlumnoCursoNota.getNota() >=11){
                		aprobado++;	
                	}else{
                		desaprobado++;
                	}
                	
                	
                }
                
               
                
                
            }   
                   
            alumnoCursoSeccionPersonaL.add(itemAlumnoCursoSeccionPersona);   
          
        }
    
       
    }
    
     public void mostrarAlumnoCursoNotaHijo(){
       
      
        alumnoCursoSeccionPersonaL=new ArrayList<>();
        alumnoCursoSeccionPersona itemAlumnoCursoSeccionPersona;
        for(alumnoCursoSeccionC itemAlumnoCursoSeccion : new alumnoCursoSeccionDAO().mostrarAlumnoCursoSeccion(cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getInstitucion(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getPeriodo(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCarrera(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getMalla(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCurso(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getSeccion(), "%"))
        {
            itemAlumnoCursoSeccionPersona=new alumnoCursoSeccionPersona(itemAlumnoCursoSeccion, new personaDAO().busquedaAlumnocodigo(itemAlumnoCursoSeccion.getInstitucion(), itemAlumnoCursoSeccion.getAlumno()));        
            for(alumnoCursoNotaC itemAlumnoCursoNota:new alumnoCursoNotaDAO().mostrarAlumnoCursoNotaH(itemAlumnoCursoSeccion.getInstitucion(), itemAlumnoCursoSeccion.getPeriodo(), itemAlumnoCursoSeccion.getCarrera(), itemAlumnoCursoSeccion.getMalla(), itemAlumnoCursoSeccion.getCurso(), itemAlumnoCursoSeccion.getSeccion(), itemAlumnoCursoSeccion.getAlumno(),tipoExamenPadre))
            {
                itemAlumnoCursoSeccionPersona.alumnoCursoNotaL.put(itemAlumnoCursoNota.getTipoExamen(), itemAlumnoCursoNota);
            }
            
            alumnoCursoSeccionPersonaL.add(itemAlumnoCursoSeccionPersona);
            
           
        }
    }
    
    public void mostrarNotaPadre(){
            tipoExamenPadre=null;
          
            cabeceraNota itemCabeceraNota;
            cabeceraNotaL=new ArrayList<>();
            
            //for(formulaExamenC itemFormulaExamen:formulaExamenD.mostrarFormulaExamen(itemCursoSeccionDocente.getInstitucion(), itemCursoSeccionDocente.getPeriodo(), itemCursoSeccionDocente.getCarrera(), itemCursoSeccionDocente.getMalla(), itemCursoSeccionDocente.getCurso(),  "P"))
            for(formulaExamenC itemFormulaExamen:new formulaExamenDAO().mostrarFormulaExamenPadre(cursoPrincipalSecundario.formula.getFormula(), "%", "P", "%"))
            {
                itemCabeceraNota=new cabeceraNota(itemFormulaExamen);
                itemCabeceraNota.tipoExamen=new tipoExamenDAO().mostrarTipoExamen(itemFormulaExamen.getTipoExamen());      
                
                //itemCabeceraNota.semanaExamen=new semanaExamenDAO().mostrarSemanaExamen(cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getInstitucion(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getPeriodo(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCarrera(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getSeccion(), itemFormulaExamen.getTipoExamen());                
                itemCabeceraNota.validaEdicion=(cursoSeccionCierreActa ==null?!itemFormulaExamen.isLectura():false);
                cabeceraNotaL.add(itemCabeceraNota);
                
            }
            
             util.script("$('.pnPersonaCurso').removeClass('bloqueo')");
    }
    
    public void mostrarNotaHijo(String tipoExamenPadre){
            this.tipoExamenPadre=tipoExamenPadre;
         
            cabeceraNota itemCabeceraNota;
            cabeceraNotaL=new ArrayList<>();
            //for(formulaExamenC itemFormulaExamen:formulaExamenD.mostrarFormulaExamen(cursoSeccionDocente.getInstitucion(), cursoSeccionDocente.getPeriodo(), cursoSeccionDocente.getCarrera(), cursoSeccionDocente.getMalla(), cursoSeccionDocente.getCurso(),  "H",tipoExamenPadre))
            for(formulaExamenC itemFormulaExamen:new formulaExamenDAO().mostrarFormulaExamenHijo(cursoPrincipalSecundario.formula.getFormula(), "%", "H", tipoExamenPadre))
            {
                itemCabeceraNota=new cabeceraNota(itemFormulaExamen);
                itemCabeceraNota.tipoExamen=new tipoExamenDAO().mostrarTipoExamen(itemFormulaExamen.getTipoExamen());
                //itemCabeceraNota.semanaExamen=new semanaExamenDAO().mostrarSemanaExamen(cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getInstitucion(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getPeriodo(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getCarrera(), cursoPrincipalSecundario.cursoSeccionDocentePrincipal.getSeccion(), itemFormulaExamen.getTipoExamen());                
                itemCabeceraNota.validaEdicion=(cursoSeccionCierreActa ==null?!itemFormulaExamen.isLectura():false);
                cabeceraNotaL.add(itemCabeceraNota);
                
            }
             mostrarAlumnoCursoNotaHijo();
             util.script("$('.pnPersonaCurso').addClass('bloqueo')");
            //-----------------------------------------------------------
                        
    }
    public void actualizar(){
        if (tipoExamenPadre == null){
            mostrarAlumnoCursoNotaPadre();
        }else{
            mostrarAlumnoCursoNotaHijo();
        }
    }
    public void atras(){
    	tipoExamenPadre=null;
        // CABECERA
        mostrarNotaPadre();
        // ALUMNO
        mostrarAlumnoCursoNotaPadre();
    }
    public List<cursoPrincipalSecundario> getCursoPrincipalSecundarioL() {
        return cursoPrincipalSecundarioL;
    }
    public void setCursoPrincipalSecundarioL(List<cursoPrincipalSecundario> cursoPrincipalSecundarioL) {
        this.cursoPrincipalSecundarioL = cursoPrincipalSecundarioL;
    }
    public List<alumnoCursoSeccionPersona> getAlumnoCursoSeccionPersonaL() {
        return alumnoCursoSeccionPersonaL;
    }
    public void setAlumnoCursoSeccionPersonaL(List<alumnoCursoSeccionPersona> alumnoCursoSeccionPersonaL) {
        this.alumnoCursoSeccionPersonaL = alumnoCursoSeccionPersonaL;
    }
    public List<cabeceraNota> getCabeceraNotaL() {
        return cabeceraNotaL;
    }
    public void setCabeceraNotaL(List<cabeceraNota> cabeceraNotaL) {
        this.cabeceraNotaL = cabeceraNotaL;
    }
    public cursoPrincipalSecundario getCursoPrincipalSecundario() {
        return cursoPrincipalSecundario;
    }
    public void setCursoPrincipalSecundario(cursoPrincipalSecundario cursoPrincipalSecundario) {
        this.cursoPrincipalSecundario = cursoPrincipalSecundario;
    }
    public String getTipoExamenPadre() {
        return tipoExamenPadre;
    }
    public void setTipoExamenPadre(String tipoExamenPadre) {
        this.tipoExamenPadre = tipoExamenPadre;
    }
  
    
    
    public class cabeceraNota{
        private formulaExamenC formulaExamen;
        private tipoExamenC tipoExamen=new tipoExamenC();
        private semanaExamenC semanaExamen;
        private boolean validaEdicion;
         
         
         
         
        public cabeceraNota() {
        }

        public cabeceraNota(formulaExamenC formulaExamen) {
            this.formulaExamen = formulaExamen;
        }
        public formulaExamenC getFormulaExamen() {
            return formulaExamen;
        }

       
        public void setFormulaExamen(formulaExamenC formulaExamen) {
            this.formulaExamen = formulaExamen;
        }

       
        public tipoExamenC getTipoExamen() {
            return tipoExamen;
        }

       
        public void setTipoExamen(tipoExamenC tipoExamen) {
            this.tipoExamen = tipoExamen;
        }

       
        public semanaExamenC getSemanaExamen() {
            return semanaExamen;
        }

        
        public void setSemanaExamen(semanaExamenC semanaExamen) {
            this.semanaExamen = semanaExamen;
        }

       
        public boolean isValidaEdicion() {
            return validaEdicion;
        }

       
        public void setValidaEdicion(boolean validaEdicion) {
            this.validaEdicion = validaEdicion;
        }
        
    }
    
    
    
    public class alumnoCursoSeccionPersona{
        private alumnoCursoSeccionC alumnoCursoSeccion;
        private personaC persona;
        private carrerasC carrera;
        
     

		private HashMap<String,alumnoCursoNotaC> alumnoCursoNotaL=new HashMap<>();
        public alumnoCursoSeccionPersona() {
        }

        public alumnoCursoSeccionPersona(alumnoCursoSeccionC alumnoCursoSeccion, personaC persona) {
            this.alumnoCursoSeccion = alumnoCursoSeccion;
            this.persona = persona;
        }
        
        public carrerasC getCarrera() {
			return carrera;
		}

		public void setCarrera(carrerasC carrera) {
			this.carrera = carrera;
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

        
        public HashMap<String,alumnoCursoNotaC> getAlumnoCursoNotaL() {
            return alumnoCursoNotaL;
        }

        
        public void setAlumnoCursoNotaL(HashMap<String,alumnoCursoNotaC> alumnoCursoNotaL) {
            this.alumnoCursoNotaL = alumnoCursoNotaL;
        }
    }
    
    
    public class cursoSecundario{
        private  cursoSeccionDocenteC cursoSeccionDocente;
        private cursosC curso=new cursosC();
        private carrerasC carrera=new carrerasC();
        private seccionC seccion=new seccionC(); 

        public cursoSecundario() {
        }

        public cursoSecundario(cursoSeccionDocenteC cursoSeccionDocente) {
            this.cursoSeccionDocente = cursoSeccionDocente;
        }

      

       
        public cursosC getCurso() {
            return curso;
        }

       
        public void setCurso(cursosC curso) {
            this.curso = curso;
        }

      
        public carrerasC getCarrera() {
            return carrera;
        }

        
         
         
        public void setCarrera(carrerasC carrera) {
            this.carrera = carrera;
        }

        
        public seccionC getSeccion() {
            return seccion;
        }

        
        public void setSeccion(seccionC seccion) {
            this.seccion = seccion;
        }

      
        public cursoSeccionDocenteC getCursoSeccionDocente() {
            return cursoSeccionDocente;
        }

       
        public void setCursoSeccionDocente(cursoSeccionDocenteC cursoSeccionDocente) {
            this.cursoSeccionDocente = cursoSeccionDocente;
        }
    }
    
    
    public  class cursoPrincipalSecundario{
        private cursoSeccionDocenteC cursoSeccionDocentePrincipal;
        private carrerasC carrera;
        private cursosC cursos;
        private seccionC seccion;
        private formulaC formula;
        private seccionPeriodoC periodoSeccion; 
        private List<registrotmC> registrotmL;
        
        
        
        
        
        
        
        
        
        
       
        public List<registrotmC> getRegistrotmL() {
			return registrotmL;
		}

		public void setRegistrotmL(List<registrotmC> registrotmL) {
			this.registrotmL = registrotmL;
		}

		public seccionPeriodoC getPeriodoSeccion() {
			return periodoSeccion;
		}

		public void setPeriodoSeccion(seccionPeriodoC periodoSeccion) {
			this.periodoSeccion = periodoSeccion;
		}


		private List<cursoSecundario> cursoSeccionDocenteSecundarioL=new ArrayList<>();

        public cursoPrincipalSecundario() {
        }

        public cursoPrincipalSecundario(cursoSeccionDocenteC cursoSeccionDocentePrincipal) {
            this.cursoSeccionDocentePrincipal = cursoSeccionDocentePrincipal;
        }

    

    
       

      
        public cursoSeccionDocenteC getCursoSeccionDocentePrincipal() {
            return cursoSeccionDocentePrincipal;
        }

       
        public void setCursoSeccionDocentePrincipal(cursoSeccionDocenteC cursoSeccionDocentePrincipal) {
            this.cursoSeccionDocentePrincipal = cursoSeccionDocentePrincipal;
        }

        
        public cursosC getCursos() {
            return cursos;
        }

      
        public void setCursos(cursosC cursos) {
            this.cursos = cursos;
        }

      
        public carrerasC getCarrera() {
            return carrera;
        }

        
        public void setCarrera(carrerasC carrera) {
            this.carrera = carrera;
        }

        
        public seccionC getSeccion() {
            return seccion;
        }

        
        public void setSeccion(seccionC seccion) {
            this.seccion = seccion;
        }

        
        public formulaC getFormula() {
            return formula;
        }

       
        public void setFormula(formulaC formula) {
            this.formula = formula;
        }

       
        public List<cursoSecundario> getCursoSeccionDocenteSecundarioL() {
            return cursoSeccionDocenteSecundarioL;
        }

        
        public void setCursoSeccionDocenteSecundarioL(List<cursoSecundario> cursoSeccionDocenteSecundarioL) {
            this.cursoSeccionDocenteSecundarioL = cursoSeccionDocenteSecundarioL;
        }

    }
    
    


    
    public List<cursoSeccionDocenteC> getCursoSeccionDocenteL() {
        return cursoSeccionDocenteL;
    }

    
    public void setCursoSeccionDocenteL(List<cursoSeccionDocenteC> cursoSeccionDocenteL) {
        this.cursoSeccionDocenteL = cursoSeccionDocenteL;
    }
    
    public String getPeriodo() {
		return periodo;
	}


	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}


	public int getInstitucion() {
		return institucion;
	}


	public void setInstitucion(int institucion) {
		this.institucion = institucion;
	}
	public List<personaC> getPersonaL() {
		return personaL;
	}


	public void setPersonaL(List<personaC> personaL) {
		this.personaL = personaL;
	}
	 public personaC getPersona() {
			return persona;
		}



		public void setPersona(personaC persona) {
			this.persona = persona;
		}
		public String getFiltroPersonal() {
			return filtroPersonal;
		}



		public void setFiltroPersonal(String filtroPersonal) {
			this.filtroPersonal = filtroPersonal;
		}

		 public cursoSeccionCierreActaC getCursoSeccionCierreActa() {
				return cursoSeccionCierreActa;
			}



			public void setCursoSeccionCierreActa(cursoSeccionCierreActaC cursoSeccionCierreActa) {
				this.cursoSeccionCierreActa = cursoSeccionCierreActa;
			}
			
			public List<autorizaNotaC> getAutotizaNotaL() {
				return autotizaNotaL;
			}
			public void setAutotizaNotaL(List<autorizaNotaC> autotizaNotaL) {
				this.autotizaNotaL = autotizaNotaL;
			}
			
			public TreeNode getRoot3() {
				return root3;
			}
			public void setRoot3(TreeNode root3) {
				this.root3 = root3;
			}
			public TreeNode[] getSelectedNodes1() {
				return selectedNodes1;
			}
			public void setSelectedNodes1(TreeNode[] selectedNodes1) {
				this.selectedNodes1 = selectedNodes1;
				
			}  
			public autorizaNotaC getAutotizaNota() {
				return autotizaNota;
			}
			public void setAutotizaNota(autorizaNotaC autotizaNota) {
				this.autotizaNota = autotizaNota;
			}
			
			public LineChartModel getAnimatedModel1() {
				return animatedModel1;
			}
			public void setAnimatedModel1(LineChartModel animatedModel1) {
				this.animatedModel1 = animatedModel1;
			}
			public int getAprobado() {
				return aprobado;
			}
			public void setAprobado(int aprobado) {
				this.aprobado = aprobado;
			}
			public int getDesaprobado() {
				return desaprobado;
			}
			public void setDesaprobado(int desaprobado) {
				this.desaprobado = desaprobado;
			}
}
