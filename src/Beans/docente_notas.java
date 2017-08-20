
package Beans;

import DAO.carrerasDAO;
import DAO.cursoDAO;
import DAO.cursoSeccionDocenteDAO;
import DAO.docente_notaDAO;
import DAO.personaDAO;
import DAO.personalDAO;
import DAO.seccionDAO;
import ENTIDAD.carrerasC;
import ENTIDAD.cursoSeccionDocenteC;
import ENTIDAD.cursosC;


import ENTIDAD.seccionC;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;



import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;


@ManagedBean(name = "docente_notaB")
@ViewScoped
public class docente_notas  {

    private List<ColumnModel> columnas;

    private String seleccionCarrera;
    private String seleccionCurso;
    private String seleccionSeccion;
 
    private List<carrerasC> listaCarreraDocente;
    private List<cursosC> listaCursoDocente;
    private List<seccionC> listaSeccionDocente;
    private List<tbCargaCursos> listaCargaCursos;
    private tbCargaCursos CargaCursos;
    private int formula;
    private cursoSeccionDocenteC cursoSeccionDocente;
    
    
    private boolean filaseleccion = false;

    private List<HashMap<String,String>> notasL;
    private List<HashMap<String,String>> notasSubL;
    private String subNotas = "";
    
    @ManagedProperty(value = "#{usuarioB}")
    private usuario usuarioS;

    
    
     @PostConstruct
    public void init() {
      personalD = new personalDAO();
      listaCargaCursos = personalD.cargaCursosPersonal("%", usuarioS.getPeriodoS(), usuarioS.getPersona().getPersona());
    }
    

    carrerasDAO cardao;
    personalDAO personalD;
    personaDAO personadao;
    cursoDAO curdao;
    seccionDAO secdao;
    cursoSeccionDocenteDAO cursoSeccionDocenteD;

    public void onCellEdit(CellEditEvent event) {
        String promedio;
        Object newValue = event.getNewValue();
        HashMap<String, String> lista;
        lista = notasL.get(event.getRowIndex());
        docente_notasD = new docente_notaDAO();
        promedio=docente_notasD.insertarNotaAlumno(CargaCursos.institucion, usuarioS.getPeriodoS(), CargaCursos.malla, CargaCursos.carrera, CargaCursos.curso, CargaCursos.seccion, lista.get("alumno"), event.getColumn().getHeaderText().toUpperCase(), Double.parseDouble(newValue.toString()));
        if (!subNotas.isEmpty()) {
           
            RequestContext.getCurrentInstance().execute("$('#frmsubNotas\\\\:notasSub tbody tr:nth-child(" + (event.getRowIndex() + 1) + ") td:nth-last-child(2)').text('" + Double.parseDouble(promedio) + "');");

        }else{
            RequestContext.getCurrentInstance().execute("$('#frmNotas\\\\:notas tbody tr:nth-child(" + (event.getRowIndex() + 1) + ") td:nth-last-child(2)').text('" + Math.round(Double.parseDouble(promedio)) + "');");
        }
    }

   

    public void refrescar() {
         notasSubL=new ArrayList<>();
         notasL=docente_notasD.getDocente_notas(CargaCursos.institucion, usuarioS.getPeriodoS(), CargaCursos.malla,CargaCursos.grupo, CargaCursos.carrera, CargaCursos.curso, CargaCursos.seccion, subNotas,"%");

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
        columnas.add(new ColumnModel("ALUMNO", "alumno", false, 0));
        columnas.add(new ColumnModel("NOMBRES", "nombres", false, 0));

        columnL = docente_notasD.getColumna(CargaCursos.institucion,usuarioS.getPeriodoS(), CargaCursos.malla, CargaCursos.carrera, CargaCursos.curso, subNotas);

        for (int i = 0; i < columnL.size(); i++) {
            formula = Integer.parseInt(columnL.get(i).get(4).toString());

            columnas.add(new ColumnModel(columnL.get(i).get(2).toString() + "<br/>" + columnL.get(i).get(3).toString(), columnL.get(i).get(0).toString().toLowerCase(), docente_notasD.getEditable(usuarioS.getPeriodoS(), CargaCursos.malla, CargaCursos.carrera, CargaCursos.curso, CargaCursos.curso, usuarioS.getPersona().getPersona(), columnL.get(i).get(0).toString().toLowerCase()), 0));
        }

        columnas.add(new ColumnModel("PROMEDIO", "promedio", false, 0));
        columnas.add(new ColumnModel("ESTADO", "estado_registro", false, 0));

    }

    public void createDynamicColumns(int institucion, String malla, String carrera, String curso, String seccion) {

        columnas = new ArrayList<>();
        docente_notasD = new docente_notaDAO();
        columnL = new ArrayList<>();
        columnas.add(new ColumnModel("CODIGO", "alumno", false, 0));
        columnas.add(new ColumnModel("ESTUDIANTE", "nombres", false, 0));
        columnL = docente_notasD.getColumna(institucion, usuarioS.getPeriodoS(), malla, carrera, curso, subNotas);

        for (int i = 0; i < columnL.size(); i++) {
            formula = Integer.parseInt(columnL.get(i).get(4).toString());


             columnas.add(new ColumnModel(columnL.get(i).get(2).toString() + "<br/>" + columnL.get(i).get(3).toString(), columnL.get(i).get(0).toString().toLowerCase(), docente_notasD.controlNotaLibre(institucion,usuarioS.getPeriodoS(),  carrera,malla, curso, seccion, columnL.get(i).get(0).toString().toLowerCase(), usuarioS.getPersona().getPersona()), Integer.parseInt(columnL.get(i).get(5).toString())));
        }
        columnas.add(new ColumnModel("PROMEDIO", "promedio", false, 0));
        columnas.add(new ColumnModel("ESTADO", "estado_registro", false, 0));

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
    public List<tbCargaCursos> getListaCargaCursos() {
       
        return listaCargaCursos;
    }
    public void setListaCargaCursos(List<tbCargaCursos> listaCargaCursos) {
        this.listaCargaCursos = listaCargaCursos;
    }

    public void onRowSelect(SelectEvent event) {
        setFilaseleccion(true);
    }
    public tbCargaCursos getCargaCursos() {
        return CargaCursos;
    }
    public void setCargaCursos(tbCargaCursos CargaCursos) {
        this.CargaCursos = CargaCursos;
    }

    public boolean isFilaseleccion() {
        return filaseleccion;
    }
    public void setFilaseleccion(boolean filaseleccion) {
        this.filaseleccion = filaseleccion;
    }
    public cursoSeccionDocenteC getCursoSeccionDocente() {
        return cursoSeccionDocente;
    }
    public void setCursoSeccionDocente(cursoSeccionDocenteC cursoSeccionDocente) {
        this.cursoSeccionDocente = cursoSeccionDocente;
    }
    public List<HashMap<String,String>> getNotasSubL() {
        return notasSubL;
    }
    public void setNotasSubL(List<HashMap<String,String>> notasSubL) {
        this.notasSubL = notasSubL;
    }
    public List<HashMap<String,String>> getNotasL() {
        return notasL;
    }
    public void setNotasL(List<HashMap<String,String>> notasL) {
        this.notasL = notasL;
    }
    public usuario getUsuarioS() {
        return usuarioS;
    }
    public void setUsuarioS(usuario usuarioS) {
        this.usuarioS = usuarioS;
    }
    public static class tbCargaCursos {

        private int item;
        private int institucion;
        private String desInstitucion;
        private int periodo;
        private String carrera;
        private String desCarrera;
        private String malla;
        private String seccion;
        private String curso;
        private String desCurso;
        private String grupo;

        public int getInstitucion() {
            return institucion;
        }
        public void setInstitucion(int institucion) {
            this.institucion = institucion;
        }
        public int getPeriodo() {
            return periodo;
        }
        public void setPeriodo(int periodo) {
            this.periodo = periodo;
        }
        public String getCarrera() {
            return carrera;
        }
        public void setCarrera(String carrera) {
            this.carrera = carrera;
        }
        public String getDesCarrera() {
            return desCarrera;
        }
        public void setDesCarrera(String desCarrera) {
            this.desCarrera = desCarrera;
        }
        public String getMalla() {
            return malla;
        }
        public void setMalla(String malla) {
            this.malla = malla;
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
        public String getDesInstitucion() {
            return desInstitucion;
        }
        public void setDesInstitucion(String desInstitucion) {
            this.desInstitucion = desInstitucion;
        }
        public int getItem() {
            return item;
        }
        public void setItem(int item) {
            this.item = item;
        }
        public String getGrupo() {
            return grupo;
        }
        public void setGrupo(String grupo) {
            this.grupo = grupo;
        }

    }

    public static class ColumnModel {

        private String header;
        private String property;
        private boolean editable;
        private int lectura;

        public ColumnModel() {
        }

        public ColumnModel(String header, String property, boolean editable, int lectura) {
            this.header = header;
            this.property = property;
            this.editable = editable;
            this.lectura = lectura;
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
    }

    public void mostrarCursosDocente() {

        curdao = new cursoDAO();
        cardao = new carrerasDAO();
        listaCursoDocente = curdao.BuscaCursosPersonaPeriodo(usuarioS.getPersona().getPersona(), usuarioS.getPeriodoS(), seleccionCarrera);

    }

    public void cerrar() {
        subNotas = "";
        formula = 0;
        createDynamicColumns(CargaCursos.institucion, CargaCursos.malla, CargaCursos.carrera, CargaCursos.curso, CargaCursos.seccion);
        refrescar();

    }

    public void imprimire() {
        util.consolaCliente( "imprime");
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

        cursoSeccionDocenteD = new cursoSeccionDocenteDAO();
      
        //malla = cursoSeccionDocenteD.mostrarPeriodoCarreraCursoSeccion(1, "" + periodo, seleccionCarrera, seleccionCurso, seleccionSeccion).getMalla();
        cursoSeccionDocente = cursoSeccionDocenteD.mostrarPeriodoCarreraCursoSeccion(1, usuarioS.getPeriodoS(), seleccionCarrera, seleccionCurso, seleccionSeccion);
        createDynamicColumns();

        docente_notasD = new docente_notaDAO();
        notasL=new ArrayList<>();
        setNotasL(docente_notasD.getDocente_notas(1, usuarioS.getPeriodoS(), cursoSeccionDocente.getMalla(),cursoSeccionDocente.getPeriodo(), seleccionCarrera, seleccionCurso, seleccionSeccion, subNotas,"%"));

    }

    public void mostrarDetalle(tbCargaCursos item) {
        System.out.println(item.seccion);
        System.out.println(item.curso);
        subNotas = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cabecera");

        if (subNotas == null) {
            subNotas = "";
        }
        createDynamicColumns(item.institucion, item.malla, item.carrera, item.curso, item.seccion);
        notasL=new ArrayList<>();
        docente_notasD = new docente_notaDAO();
        notasL=docente_notasD.getDocente_notas(item.institucion, usuarioS.getPeriodoS(), item.malla,item.grupo, item.carrera, item.curso, item.seccion, subNotas,"%");

    }

    public void mostrarSeccionDocente() {

        secdao = new seccionDAO();

        listaSeccionDocente = secdao.mostrarSeccionCursoDocente(usuarioS.getPersona().getPersona(), usuarioS.getPeriodoS(), seleccionCarrera, seleccionCurso);

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
        cardao = new carrerasDAO();

        listaCarreraDocente = cardao.mostrarCarrerasDocentePeriodo(1, usuarioS.getPeriodoS(), usuarioS.getPersona().getPersona());
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

}
