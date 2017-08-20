
package Beans;

import DAO.accesoDAO;
import DAO.alumnoCursoSeccionDAO;
import DAO.autorizaNotaDAO;
import DAO.carrerasDAO;
import DAO.cursoDAO;
import DAO.cursoPeriodoDAO;
import DAO.cursoSeccionActaDAO;
import DAO.cursoSeccionCierreActaDAO;
import DAO.cursoSeccionDAO;
import DAO.cursoSeccionDocenteDAO;
import DAO.docente_notaDAO;

import DAO.personaDAO;

import DAO.seccionDAO;
import DAO.tipoExamenDAO;
import ENTIDAD.accesoC;
import ENTIDAD.autorizaNotaC;
import ENTIDAD.personaC;
import ENTIDAD.carrerasC;
import ENTIDAD.cursoPeriodoC;
import ENTIDAD.cursoSeccionActaC;
import ENTIDAD.cursoSeccionC;
import ENTIDAD.cursoSeccionCierreActaC;
import ENTIDAD.cursoSeccionDocenteC;
import ENTIDAD.cursosC;
import ENTIDAD.formulaExamenC;


import ENTIDAD.seccionC;
import ENTIDAD.tipoExamenC;

import java.io.Serializable;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean(name = "admin_notaB")
@ViewScoped
public class admin_notas implements Serializable {

   
	private static final long serialVersionUID = 1L;
	private List<ColumnModel> columnas;
    private String seleccionDocente;
    private String seleccionCarrera;
    private String seleccionCurso;
    private String seleccionSeccion;
    private String seleccionMalla="200702";
    private String filtroPersona="";
    private String filtroCurso="";
    private List<busquedaC> busquedaL=new ArrayList<>();
    private busquedaC busqueda=new busquedaC();
    
  
	
	
	private cursoPeriodoC cursoPeriodo;
    
    private List<personaC> listaDocentePeriodo;
    private List<cursosPersonal> listaCursosPersonal;
    private List<personaC> listaDocentePeriodoI;
    private List<carrerasC> listaCarreraDocente;
    private List<cursosC> listaCursoDocente;
    private List<seccionC> listaSeccionDocente;
    private int formula;
    
    
    private int institucion;
    private String periodo;
    
    private cursoSeccionDocenteC cursoSeccionDocente=new cursoSeccionDocenteC();
    private List<HashMap<String,String>> notasL;
    private List<HashMap<String,String>> notasSubL;
    private String subNotas = "";
    private List<HashMap<String,String>> notaL;
    private String docente;
    private boolean fitroAlumno=true;
    
    private String seleccionGrupoExamen;
    private String seleccionTipoExamen;
    private boolean banderaAtras;
    private tipoExamenC tipoExamen;
    private formulaExamenC formulaExamen;
    private cursoSeccionC cursoSeccion;
    private accesoC acceso;
    
    
    
    
    
    
    
    
    
    public accesoC getAcceso() {
		return acceso;
	}
	public void setAcceso(accesoC acceso) {
		this.acceso = acceso;
	}
	private int validaActas;
    private int totalApro;
    private int totalDesa;
    private cursoSeccionActaC cursoSeccionActa=new cursoSeccionActaC();
    
    
    private cursoSeccionCierreActaC cursoSeccionCierreActa=new cursoSeccionCierreActaC();
    
    //---------------------------
    private Date fechaInicio;
    private Date fechaFin;
    private String observacion;
    private TreeNode root3;
     private TreeNode[] selectedNodes1;
     private List<listaTipoExamen> listaTipoExamenL;
     private List<listaTipoExamen> listaTipoExamenLHijo;
     
     
     
    //---------------------------
cursoPeriodoDAO cursoPeriodoD;
     cursoSeccionDAO cursoSeccionD;
     public admin_notas() {
        
       
    }
     public void seleccionBusqueda(){
    	 
    	 seleccionDocente=busqueda.persona;
    	 listaCarreraDocente = new carrerasDAO().mostrarCarrerasDocentePeriodo(institucion, periodo, seleccionDocente);
    	 seleccionCarrera=busqueda.carrera;
    	 listaSeccionDocente = new seccionDAO().mostrarSeccionPeriodoCarreraDocente(institucion, periodo, seleccionCarrera, seleccionDocente);
    	 seleccionSeccion=busqueda.seccion;
    	 listaCursoDocente = new cursoDAO().BuscaCursosPeriodoCarreraDocenteSeccion(periodo, seleccionCarrera, seleccionDocente, seleccionSeccion);
    	 seleccionCurso=busqueda.curso;
    	 mostrarDetalle();
    	 
    	 util.script("dlgBuquedaPersona.hide()");
     }
     
     
      public void mostrarCursoPeriodo(int institucion, String periodo, String carrera,String curso) {
        cursoPeriodoD = new cursoPeriodoDAO();
        cursoPeriodo = cursoPeriodoD.mostrarCursoPerido(institucion, periodo, carrera,curso);
        cursoSeccionD=new cursoSeccionDAO();
        cursoSeccion=cursoSeccionD.mostrarCursoSeccion(""+institucion, periodo, carrera, curso, seleccionSeccion);
    }
     public void nuevoAutorizaNota(){
        fechaInicio=null;
        fechaFin=null;
        observacion="" ;
       
        listaTipoExamenL=new autorizaNotaDAO().datosTipoAutorizacionPadre(cursoSeccionDocente.getInstitucion(), cursoSeccionDocente.getPeriodo(), cursoSeccionDocente.getCarrera(), cursoSeccionDocente.getMalla(), cursoSeccionDocente.getCurso());
        root3=new CheckboxTreeNode(new listaTipoExamen("1","PRINCIPAL",true),null);
         for (listaTipoExamen item : listaTipoExamenL) {
             TreeNode child=new CheckboxTreeNode(item,root3);
             if(item.lectura){
              
                 listaTipoExamenLHijo=new autorizaNotaDAO().datosTipoAutorizacionHijo(cursoSeccionDocente.getInstitucion(), cursoSeccionDocente.getPeriodo(), cursoSeccionDocente.getCarrera(), cursoSeccionDocente.getMalla(), cursoSeccionDocente.getCurso(),item.tipoExamen);
                 for (listaTipoExamen itemHijo : listaTipoExamenLHijo) {
                     TreeNode childItem=new CheckboxTreeNode(itemHijo,child);
                 }
             }
                child.setExpanded(true);
         }
         util.script("dlgAutorizaNota.show()");
     }
     public void guardarTipoExamen(){
     
         
          for(TreeNode item : selectedNodes1) {
             listaTipoExamen fila=(listaTipoExamen)item.getData();
                if (!fila.lectura){
                    util.consolaCliente(fila.tipoExamen); 
                    
                    new autorizaNotaDAO().insertar(new autorizaNotaC( cursoSeccionDocente.getInstitucion(), cursoSeccionDocente.getPeriodo(), -1, null, seleccionCarrera, cursoSeccionDocente.getMalla(), seleccionCurso, seleccionSeccion ,0, cursoSeccionDocente.getPersonal(), fila.tipoExamen, "%", fechaInicio, fechaFin,observacion, 1));
                }
                
            }
          util.script("dlgAutorizaNota.hide()");
          util.alert("Se genero Correctamente");
     }

  
    public List<listaTipoExamen> getListaTipoExamenL() {
        return listaTipoExamenL;
    }

  
    public void setListaTipoExamenL(List<listaTipoExamen> listaTipoExamenL) {
        this.listaTipoExamenL = listaTipoExamenL;
    }

   
    public List<listaTipoExamen> getListaTipoExamenLHijo() {
        return listaTipoExamenLHijo;
    }

    
    public void setListaTipoExamenLHijo(List<listaTipoExamen> listaTipoExamenLHijo) {
        this.listaTipoExamenLHijo = listaTipoExamenLHijo;
    }

  
    public String getObservacion() {
        return observacion;
    }

   
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

 
    public boolean isFitroAlumno() {
        return fitroAlumno;
    }

  
    public void setFitroAlumno(boolean fitroAlumno) {
        this.fitroAlumno = fitroAlumno;
    }

    
    public cursoSeccionC getCursoSeccion() {
        return cursoSeccion;
    }

   
    public void setCursoSeccion(cursoSeccionC cursoSeccion) {
        this.cursoSeccion = cursoSeccion;
    }

  
    public String getSeleccionMalla() {
        return seleccionMalla;
    }

 
    public void setSeleccionMalla(String seleccionMalla) {
        this.seleccionMalla = seleccionMalla;
    }

 
     
     public static class listaTipoExamen{
         
         
         private String tipoExamen;
         private String descripcion;
         private boolean lectura;

        public listaTipoExamen() {
        }

        public listaTipoExamen(String tipoExamen, String descripcion, boolean lectura) {
            this.tipoExamen = tipoExamen;
            this.descripcion = descripcion;
            this.lectura = lectura;
        }

      

       

       

        public boolean isLectura() {
            return lectura;
        }
        public void setLectura(boolean lectura) {
            this.lectura = lectura;
        }
        public String getDescripcion() {
            return descripcion;
        }
        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }
        public String getTipoExamen() {
            return tipoExamen;
        }
        public void setTipoExamen(String tipoExamen) {
            this.tipoExamen = tipoExamen;
        }
         
     }
   
    
     public void event_hns_malla(){
    	 		 
    	 JOptionPane.showMessageDialog(null,"SECCION : "+seleccionSeccion);
     }
    
    public void editarCelda(CellEditEvent event) {
        try{
            Object newValue = event.getNewValue();
        
        HashMap<String, String> item;
        item =   notaL.get(event.getRowIndex());
        
       docente_notasD = new docente_notaDAO();
      
        
       	// JOptionPane.showMessageDialog(null,"hans malla"+ seleccionMalla);
        seleccionSeccion=new alumnoCursoSeccionDAO().mostrarItemAlumnoCursoSeccion(1, periodo, seleccionCarrera, seleccionMalla, seleccionCurso, "%", item.get("alumno")).getSeccion();
         docente_notasD.insertarNotaAlumno(1, periodo, seleccionMalla, seleccionCarrera, seleccionCurso, seleccionSeccion, item.get("alumno"), event.getColumn().getHeaderText().toUpperCase(), Double.parseDouble(newValue.toString()));
    
//        
//        notasL = new ArrayList<>();
//        notasL = docente_notasD.getDocente_notas(1, "20151", "200702","0", seleccionCarrera, seleccionCurso, seleccionSeccion, seleccionTipoExamen);
//        lista = ((notasC) notasL.get(event.getRowIndex()));
//        docente_notasD.insertarNotaAlumno(1, "20151", "200702", seleccionCarrera, seleccionCurso, seleccionSeccion, lista.getAlumno(), seleccionTipoExamen, lista.getPromedio());
//        
//
//        // ------------- registar promedio final ----
//        notasSubL = new ArrayList<>();
//        notasSubL = docente_notasD.getDocente_notas(1, "20151", "200702","0", seleccionCarrera, seleccionCurso, seleccionSeccion, seleccionTipoExamen);
//       lista = ((notasC) notasSubL.get(event.getRowIndex()));
//        
//        docente_notasD.insertarPromedioAlumno(1, "20151", seleccionCarrera, seleccionCurso, seleccionSeccion, lista.getAlumno(), lista.getPromedio());
//        RequestContext.getCurrentInstance().execute("$('#frmNotas\\\\:notas tbody tr:nth-child(" + (event.getRowIndex() + 1) + ") td:nth-last-child(2)').text('" + Math.round(lista.getPromedio()) + "');");
//       //   --------------------------------------------
        }catch(Exception e){
            util.consolaCliente( e.getMessage());
        }
        

    }
    public void cerrarActas(){
     
        new cursoSeccionCierreActaDAO().insertar(cursoSeccionCierreActa);
        util.alert("Se genero correctamente");
        validaActas=1;
        refrescar();
    }
    public void refrescarInstitucionPeriodo(int institucion, String periodo,String usuario,int programa,int modulo) {
        this.institucion = institucion;
        this.periodo = periodo;
        acceso = new accesoDAO().mostrarAccesos(usuario, programa, modulo);

    }
    
    public void refrescarInstitucionPeriodo(int institucion, String periodo) {
        this.institucion = institucion;
        this.periodo = periodo;
        

    }
    
    
    
    public void insertarActa(String tipoExamen){
    
        cursoSeccionActa.setTipoExamen(tipoExamen);
        
        
        new cursoSeccionActaDAO().insertarCurso(cursoSeccionActa);
        
    }
    
    

    public void onCellEdit(CellEditEvent event) {

        //Object oldValue = event.getOldValue();  
        String promedio;
        Object newValue = event.getNewValue();
        HashMap<String, String> lista;
        lista = notasL.get(event.getRowIndex());

        docente_notasD = new docente_notaDAO();
        promedio=docente_notasD.insertarNotaAlumno(institucion, ""+periodo, cursoSeccionDocente.getMalla(), seleccionCarrera, seleccionCurso, seleccionSeccion, lista.get("alumno"), event.getColumn().getHeaderText().toUpperCase(), Double.parseDouble(newValue.toString()));
        
        if (!subNotas.isEmpty()) {

            //-------- registrar promedio de sub nota ---
            
            RequestContext.getCurrentInstance().execute("$('#notasSub tbody tr:nth-child(" + (event.getRowIndex() + 1) + ") td:nth-last-child(2)').text('" + promedio + "');");
        }
        else{
            RequestContext.getCurrentInstance().execute("$('#notas tbody tr:nth-child(" + (event.getRowIndex() + 1) + ") td:nth-last-child(2)').text('" + Math.round(Double.parseDouble(promedio)) + "');");
        }

        // ------------- registar promedio final ----
        
        if (institucion!=1){
            docente_notasD.insertarPromedioAlumno(institucion, ""+periodo, seleccionCarrera, seleccionCurso, seleccionSeccion, lista.get("alumno"));
        }
      //  
        
       //   --------------------------------------------
        


    }
    
    


    public void refrescar() {
        createDynamicColumns();
        
        notasL=new ArrayList<>();
        notasL=docente_notasD.getDocente_notas(institucion, ""+periodo, cursoSeccionDocente.getMalla(),cursoSeccionDocente.getGrupo(), seleccionCarrera, seleccionCurso, seleccionSeccion, subNotas, (fitroAlumno?"1":"73"));

    }

    public List<ColumnModel> getColumnas() {
        return columnas;
    }
    public void setColumnas(List<ColumnModel> columnas) {
        this.columnas = columnas;
    }
    private List<ArrayList<String>> columnL;

    

    public void createDynamicColumns() {
       
        columnas = new ArrayList<>();
        docente_notasD = new docente_notaDAO();
        columnL = new ArrayList<>();
        columnas.add(new ColumnModel("ALUMNO", "alumno", false, 0,20,0));
        columnas.add(new ColumnModel("NOMBRES", "nombres", false, 0,20,0));
        
        columnL = docente_notasD.getColumna(institucion, periodo, cursoSeccionDocente.getMalla(), seleccionCarrera, seleccionCurso, subNotas);
        
        for (int i = 0; i < columnL.size(); i++) {
            
            
            formula = Integer.parseInt(columnL.get(i).get(4).toString());
            columnas.add(new ColumnModel(columnL.get(i).get(2).toString() + "<br/>" + columnL.get(i).get(3).toString(), columnL.get(i).get(0).toString().toLowerCase(), (columnL.get(i).get(5).endsWith("0")), Integer.parseInt(columnL.get(i).get(5).toString()),Integer.parseInt(columnL.get(i).get(7).toString()),Integer.parseInt(columnL.get(i).get(6).toString())));
        }
        
        columnas.add(new ColumnModel("PROMEDIO", "promedio", false, 0,20,0));
        columnas.add(new ColumnModel("ESTADO", "estado_registro", false, 0,20,0));
        
       
           
        
         
    }
    
    
       public void createDynamicColumns(int institucion,String periodo,String malla,String carrera,String curso,String tipoExamen) {
       
        columnas = new ArrayList<>();
        docente_notasD = new docente_notaDAO();
        columnL = new ArrayList<>();
        columnas.add(new ColumnModel("ALUMNO", "alumno", false, 0,20,0));
        columnas.add(new ColumnModel("NOMBRES", "nombres", false, 0,20,0));
        
        columnL = docente_notasD.getColumna(institucion, periodo, malla, carrera, curso, tipoExamen);
        
        for (int i = 0; i < columnL.size(); i++) {
            
            
            formula = Integer.parseInt(columnL.get(i).get(4).toString());
            columnas.add(new ColumnModel(columnL.get(i).get(2).toString() + "<br/>" + columnL.get(i).get(3).toString(), columnL.get(i).get(0).toString().toLowerCase(), (columnL.get(i).get(5).endsWith("0")), Integer.parseInt(columnL.get(i).get(5).toString()),Integer.parseInt(columnL.get(i).get(7).toString()),Integer.parseInt(columnL.get(i).get(6).toString())));
        }
        
        columnas.add(new ColumnModel("PROMEDIO", "promedio", false, 0,20,0));
        columnas.add(new ColumnModel("ESTADO", "estado_registro", false, 0,20,0));
        
       
           
        
         
    }
    public int getFormula() {
        return formula;
    }
    public void setFormula(int formula) {
        this.formula = formula;
    }
    public String getSubNotas() {
        return subNotas;
    }
    public void setSubNotas(String subNotas) {
        this.subNotas = subNotas;
    }
    public String getDocente() {

        return docente;
    }
    public void setDocente(String docente) {
        this.docente = docente;
    }

   
    public List<personaC> mostrarDocenteInstitucionPeriodo(int institucion, String periodo) {
        perDAO = new personaDAO();
        listaDocentePeriodo = perDAO.BuscaPersonaPeriodo(institucion, periodo);
        return listaDocentePeriodo;

    }

    public List<personaC> getListaDocentePeriodo() {
        perDAO = new personaDAO();
        listaDocentePeriodo = perDAO.BuscaPersonaPeriodo(institucion, ""+periodo);
        return listaDocentePeriodo;
    }
    public void setListaDocentePeriodo(List<personaC> listaDocentePeriodo) {
        this.listaDocentePeriodo = listaDocentePeriodo;
    }
    public String getSeleccionDocente() {
        return seleccionDocente;
    }
    public void setSeleccionDocente(String seleccionDocente) {
        this.seleccionDocente = seleccionDocente;
    }
    public String getPeriodo() {
        return periodo;
    }
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    public List<personaC> getListaDocentePeriodoI() {
        perDAO = new personaDAO();

        listaDocentePeriodoI = perDAO.BuscaPersonaPeriodoInstitucion(institucion, ""+periodo);

        return listaDocentePeriodoI;
    }
    public void setListaDocentePeriodoI(List<personaC> listaDocentePeriodoI) {
        this.listaDocentePeriodoI = listaDocentePeriodoI;
    }
    public int getInstitucion() {
        return institucion;
    }
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }
    public List<cursosPersonal> getListaCursosPersonal() {
        return listaCursosPersonal;
    }
    public void setListaCursosPersonal(List<cursosPersonal> listaCursosPersonal) {
        this.listaCursosPersonal = listaCursosPersonal;
    }
    public cursoSeccionDocenteC getCursoSeccionDocente() {
        return cursoSeccionDocente;
    }
    public void setCursoSeccionDocente(cursoSeccionDocenteC cursoSeccionDocente) {
        this.cursoSeccionDocente = cursoSeccionDocente;
    }
    public String getSeleccionGrupoExamen() {
        return seleccionGrupoExamen;
    }
    public void setSeleccionGrupoExamen(String seleccionGrupoExamen) {
        this.seleccionGrupoExamen = seleccionGrupoExamen;
    }
    public String getSeleccionTipoExamen() {
        return seleccionTipoExamen;
    }
    public void setSeleccionTipoExamen(String seleccionTipoExamen) {
        this.seleccionTipoExamen = seleccionTipoExamen;
    }
    public List<HashMap<String,String>> getNotaL() {
        return notaL;
    }
    public void setNotaL(List<HashMap<String,String>> notaL) {
        this.notaL = notaL;
    }
    public List<HashMap<String,String>> getNotasL() {
        return notasL;
    }
    public void setNotasL(List<HashMap<String,String>> notasL) {
        this.notasL = notasL;
    }
    public List<HashMap<String,String>> getNotasSubL() {
        return notasSubL;
    }
    public void setNotasSubL(List<HashMap<String,String>> notasSubL) {
        this.notasSubL = notasSubL;
    }
    public tipoExamenC getTipoExamen() {
        return tipoExamen;
    }
    public void setTipoExamen(tipoExamenC tipoExamen) {
        this.tipoExamen = tipoExamen;
    }
    public formulaExamenC getFormulaExamen() {
        return formulaExamen;
    }
    public void setFormulaExamen(formulaExamenC formulaExamen) {
        this.formulaExamen = formulaExamen;
    }
    public cursoPeriodoC getCursoPeriodo() {
        return cursoPeriodo;
    }
    public void setCursoPeriodo(cursoPeriodoC cursoPeriodo) {
        this.cursoPeriodo = cursoPeriodo;
    }
    public boolean isBanderaAtras() {
        return banderaAtras;
    }
    public void setBanderaAtras(boolean banderaAtras) {
        this.banderaAtras = banderaAtras;
    }
    public cursoSeccionCierreActaC getCursoSeccionCierreActa() {
        return cursoSeccionCierreActa;
    }
    public void setCursoSeccionCierreActa(cursoSeccionCierreActaC cursoSeccionCierreActa) {
        this.cursoSeccionCierreActa = cursoSeccionCierreActa;
    }
    public int getValidaActas() {
        return validaActas;
    }
    public void setValidaActas(int validaActas) {
        this.validaActas = validaActas;
    }
    public int getTotalApro() {
        return totalApro;
    }
    public void setTotalApro(int totalApro) {
        this.totalApro = totalApro;
    }
    public int getTotalDesa() {
        return totalDesa;
    }
    public void setTotalDesa(int totalDesa) {
        this.totalDesa = totalDesa;
    }
    public cursoSeccionActaC getCursoSeccionActa() {
        return cursoSeccionActa;
    }
    public void setCursoSeccionActa(cursoSeccionActaC cursoSeccionActa) {
        this.cursoSeccionActa = cursoSeccionActa;
    }
    public Date getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public Date getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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

   

    public static class cursosPersonal {

        private int item;
        private String carrera;
        private String seccion;
        private String curso;
        private String desCurso;

        public int getItem() {
            return item;
        }
        public void setItem(int item) {
            this.item = item;
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
        public String getDesCurso() {
            return desCurso;
        }
        public void setDesCurso(String desCurso) {
            this.desCurso = desCurso;
        }

    }
    public void nuevaBusqueda(){
    	filtroCurso="";
    	filtroPersona="";
    	busqueda=new busquedaC();
    	busquedaL=new ArrayList<>();
    	util.script("dlgBuquedaPersona.show()");
    }
    
    public void filtro(){
    	busquedaL=new docente_notaDAO().busquedaPersonal(institucion, periodo, filtroPersona, filtroCurso);
    }
    
    
    public static class busquedaC{
    	private int institucion;
    	private String periodo;
    	private String carrera;
    	private String curso;
    	private String desCurso;
    	private String seccion;
    	private String desSeccion;
    	private String persona;
    	private String personal;
    	private String paterno;
    	private String materno;
    	private String nombres;
    	private String despesona;
    	private String frecuenciaDia;
    	private String frecuenciaHora;
    	
    	
    	
    	
    	
    	
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
		public String getCurso() {
			return curso;
		}
		public void setCurso(String curso) {
			this.curso = curso;
		}
		public String getDesCurso() {
			return desCurso;
		}
		public void setDesCurso(String desCurso) {
			this.desCurso = desCurso;
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
		public String getDespesona() {
			return despesona;
		}
		public void setDespesona(String despesona) {
			this.despesona = despesona;
		}
		public String getFrecuenciaDia() {
			return frecuenciaDia;
		}
		public void setFrecuenciaDia(String frecuenciaDia) {
			this.frecuenciaDia = frecuenciaDia;
		}
		public String getFrecuenciaHora() {
			return frecuenciaHora;
		}
		public void setFrecuenciaHora(String frecuenciaHora) {
			this.frecuenciaHora = frecuenciaHora;
		}
		
    	
    }
    
    

    public static class ColumnModel {

        private String header;
        private String property;
        private boolean editable;
        private int lectura;
        private int numeroMaximo;
        private int numeroMinimo;

        public ColumnModel() {
        }

        public ColumnModel(String header, String property, boolean editable, int lectura, int numeroMaximo, int numeroMinimo) {
            this.header = header;
            this.property = property;
            this.editable = editable;
            this.lectura = lectura;
            this.numeroMaximo = numeroMaximo;
            this.numeroMinimo = numeroMinimo;
        }

        

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }
        public boolean getEditable() {
            return editable;
        }
        public void setEditable(boolean editable) {
            this.editable = editable;
        }
        public int getLectura() {
            return lectura;
        }
        public void setLectura(int lectura) {
            this.lectura = lectura;
        }
        public int getNumeroMaximo() {
            return numeroMaximo;
        }
        public void setNumeroMaximo(int numeroMaximo) {
            this.numeroMaximo = numeroMaximo;
        }
        public int getNumeroMinimo() {
            return numeroMinimo;
        }
        public void setNumeroMinimo(int numeroMinimo) {
            this.numeroMinimo = numeroMinimo;
        }
    }

    public void mostrarCursosDocente() {

   
        seleccionCurso = "";
        listaCursoDocente = new cursoDAO().BuscaCursosPeriodoCarreraDocenteSeccion(periodo, seleccionCarrera, seleccionDocente, seleccionSeccion);
        
    }

    public void cerrar() {
        subNotas = "";
        formula = 0;
        createDynamicColumns();
        refrescar();

    }


   

    public List<carrerasC> listarCarrerarDocente() {
        return listaCarreraDocente;
    }

    public boolean getEditable(String tipo) {

        return true;
    }

    public void mostrarDetalle() {
      
        subNotas = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cabecera");        
        if (subNotas == null) {
            subNotas = "";
        }
      
        
        cursoSeccionDocente=new cursoSeccionDocenteDAO().mostrarPeriodoCarreraCursoSeccion(institucion,  periodo, seleccionCarrera, seleccionCurso, seleccionSeccion);
        createDynamicColumns();
        docente_notasD = new docente_notaDAO();        
        notasL=new ArrayList<>();       
        notasL=docente_notasD.getDocente_notas(institucion,periodo, cursoSeccionDocente.getMalla(),cursoSeccionDocente.getGrupo(), seleccionCarrera, seleccionCurso, seleccionSeccion, subNotas, (fitroAlumno?"1":"73"));
        
        total_Aprovados_Desaprobados();
        //----------- VALIDAR CIERRE DE REGISTRO -------------  
       
        cursoSeccionCierreActa=new cursoSeccionCierreActaDAO().mostrarCursoSeccionCierreActa(institucion,  periodo, seleccionCarrera, seleccionCurso, seleccionSeccion);
        if (cursoSeccionCierreActa==null){ 
            cursoSeccionCierreActa=new cursoSeccionCierreActaC(institucion, periodo, seleccionCarrera, cursoSeccionDocente.getMalla(),  seleccionCurso, seleccionSeccion, "0", null,"", 1);
            validaActas=0;
        }else{            
            validaActas=cursoSeccionCierreActa.getEstadoRegistro();
        }
        
        //------------------------------------------------------
        
        
    }
    
    public void total_Aprovados_Desaprobados(){
        util js=new util();
        totalApro=0;
        totalDesa=0;
       for(int x=0;x<=notasL.size()-1 ;x++){
    
           if ((js.redondeo(Double.parseDouble(notasL.get(x).get("promedio"))))>=11){
               totalApro+=1;
           }else{
               totalDesa+=1;
           }
               
           
       }
       util.consolaCliente("Aprobados" + totalApro); 
       util.consolaCliente("desaprobados" + totalDesa); 
    }
   
    public void mostrarSubNota(int institucion,String periodo,String carrera,String malla,String curso,String seccion,String tipoExamen,  String grupoExamen,boolean bandera) {
        
        //----- DATOS INFORMATIVOS
       
        this.tipoExamen=new tipoExamenDAO().mostrarTipoExamen(tipoExamen);        
        banderaAtras=bandera;       
        //------------------
        
      
        cursoSeccionDocente=new cursoSeccionDocenteDAO().mostrarPeriodoCarreraCursoSeccion(institucion,  periodo, carrera, curso, seccion);
        createDynamicColumns(institucion, periodo, malla, carrera, curso, tipoExamen);
        docente_notasD = new docente_notaDAO();
        notaL = new ArrayList<>();       
        notaL = docente_notasD.mostrarAlumnosTipoExamen(institucion, periodo, malla,"0", carrera, curso, seccion, tipoExamen,grupoExamen);
    }
        
        
        
    
    
    
    personaDAO perDAO;

    public void mostrarCarreraDocente() {
     
        seleccionCarrera = "";

        listaCarreraDocente = new carrerasDAO().mostrarCarrerasDocentePeriodo(institucion, ""+periodo, seleccionDocente);
        //listaCursosPersonal=personalD.
    }

    public void mostrarCarreraDocenteTitulacion() {

        seleccionCarrera = "";

        listaCarreraDocente = new carrerasDAO().mostrarCarrerasDocentePeriodo(institucion, ""+periodo, seleccionDocente);
    }

    public void mostrarDocentePeriodo() {
        perDAO = new personaDAO();

        listaDocentePeriodo = perDAO.BuscaPersonaPeriodo(institucion,""+ periodo);

    }

    public void mostrarSeccionDocente() {

        
        seleccionSeccion = "";
        listaSeccionDocente = new seccionDAO().mostrarSeccionPeriodoCarreraDocente(institucion, periodo, seleccionCarrera, seleccionDocente);

    }
    docente_notaDAO docente_notasD;

  
    public String getSeleccionCarrera() {
        return seleccionCarrera;
    }
    public void setSeleccionCarrera(String seleccionCarrera) {
        this.seleccionCarrera = seleccionCarrera;
    }
    public String getSeleccionCurso() {
        return seleccionCurso;
    }
    public void setSeleccionCurso(String seleccionCurso) {
        this.seleccionCurso = seleccionCurso;
    }
    public List<cursosC> getListaCursoDocente() {
        return listaCursoDocente;
    }

    public void setListaCursoDocente(List<cursosC> listaCursoDocente) {
        this.listaCursoDocente = listaCursoDocente;
    }
    public List<carrerasC> getListaCarreraDocente() {

        return listaCarreraDocente;
    }
    public void setListaCarreraDocente(List<carrerasC> listaCarreraDocente) {
        this.listaCarreraDocente = listaCarreraDocente;
    }
    public List<seccionC> getListaSeccionDocente() {
        return listaSeccionDocente;
    }
    public void setListaSeccionDocente(List<seccionC> listaSeccionDocente) {
        this.listaSeccionDocente = listaSeccionDocente;
    }
    public String getSeleccionSeccion() {
        return seleccionSeccion;
    }
    public void setSeleccionSeccion(String seleccionSeccion) {
        this.seleccionSeccion = seleccionSeccion;
    }
    
    public String getFiltroPersona() {
  		return filtroPersona;
  	}


  	public void setFiltroPersona(String filtroPersona) {
  		this.filtroPersona = filtroPersona;
  	}


  	public String getFiltroCurso() {
  		return filtroCurso;
  	}


  	public void setFiltroCurso(String filtroCurso) {
  		this.filtroCurso = filtroCurso;
  	}
  	public List<busquedaC> getBusquedaL() {
		return busquedaL;
	}


	public void setBusquedaL(List<busquedaC> busquedaL) {
		this.busquedaL = busquedaL;
	}
	public busquedaC getBusqueda() {
		return busqueda;
	}


	public void setBusqueda(busquedaC busqueda) {
		this.busqueda = busqueda;
	}
}
