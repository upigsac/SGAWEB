
package Beans;

import DAO.carrerasDAO;
import DAO.cursoDAO;
import DAO.cursoSeccionDocenteDAO;
import DAO.docente_notaDAO;
import DAO.personaDAO;
import DAO.personalDAO;
import DAO.reprogramacionDAO;
import DAO.seccionDAO;
import DAO.tipoExamenDAO;
import ENTIDAD.carrerasC;
import ENTIDAD.cursosC;

import ENTIDAD.notasC;
import ENTIDAD.personaC;
import ENTIDAD.seccionC;
import ENTIDAD.tipoExamenC;
import java.io.IOException;

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
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;

import org.primefaces.event.SelectEvent;


@ManagedBean(name = "reprogramacionB")
@ViewScoped
public class reprogramacion  {

    private notasC notasAlumno;
    private List<tipoExamenC> tipoEvaluacionL;
    private List<personaC> personaL;
    private String tipoEvaluacion;
    private String alumno;
    private String persona;
    private String autoriza;
    private String observacion;
    private int nota;

    private List<ColumnModel> columnas;
    private String seleccionDocente;
    private String seleccionCarrera;
    private String seleccionCurso;
    private String seleccionSeccion;
    private String fecha_hasta = "2014/04/02";
    private List<personaC> listaDocentePeriodo;

    private List<personaC> listaDocentePeriodoI;
    private List<carrerasC> listaCarreraDocente;
    private List<cursosC> listaCursoDocente;
    private List<seccionC> listaSeccionDocente;
    private int formula;
    private String malla;
    private String periodo ;

    private List<notasC> notasL;
    private List<notasC> notasSubL;
    private String subNotas = "";

    private String docente;
    private boolean bandera = true;

    carrerasDAO cardao;
    personalDAO perdao;
    personaDAO personadao;
    cursoDAO curdao;
    seccionDAO secdao;
    cursoSeccionDocenteDAO cursoSeccionDocenteD;

    tipoExamenDAO tipoExamenD;
    personaDAO personaD;

    public void onRowSelect(SelectEvent event) {
        personaD = new personaDAO();
        alumno = ((notasC) event.getObject()).getAlumno().toString();
        persona = personaD.busquedaAlumnocodigo(1, alumno).getPersona();

    }

   
    reprogramacionDAO reprogramacionD;

    public void registrarReprogramacion(String carrera, String curso, String seccion) {

        reprogramacionD = new reprogramacionDAO();
        reprogramacionD.insertarNotaAlumnoReprogramacion(periodo, malla, carrera, curso, seccion, alumno, tipoEvaluacion, nota, autoriza, observacion);

        notasL = new ArrayList<notasC>();
       // notasL = docente_notasD.getDocente_notas(1, periodo, malla, seleccionCarrera, seleccionCurso, seleccionSeccion, formula, "");
        util.consolaCliente( "se inserto correctamente");

    }

    public notasC getNotasAlumno() {
        return notasAlumno;
    }
    public void setNotasAlumno(notasC notasAlumno) {
        this.notasAlumno = notasAlumno;
    }
    public List<tipoExamenC> getTipoEvaluacionL() {
        tipoExamenD = new tipoExamenDAO();
        tipoEvaluacionL = tipoExamenD.mostrarTipoExamenFormula(10);
        return tipoEvaluacionL;
    }

    public void setTipoEvaluacionL(List<tipoExamenC> tipoEvaluacionL) {
        this.tipoEvaluacionL = tipoEvaluacionL;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getAutoriza() {
        return autoriza;
    }
    public void setAutoriza(String autoriza) {
        this.autoriza = autoriza;
    }
    public int getNota() {
        return nota;
    }
    public void setNota(int nota) {
        this.nota = nota;
    }
    public String getTipoEvaluacion() {
        return tipoEvaluacion;
    }
    public void setTipoEvaluacion(String tipoEvaluacion) {
        this.tipoEvaluacion = tipoEvaluacion;
    }

    public String getPersona() {
        return persona;
    }
    public void setPersona(String persona) {
        this.persona = persona;
    }
    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    
    public List<personaC> getPersonaL() {
        personaD = new personaDAO();
        personaL = personaD.mostrarAutorizacionNotaPersona(1, 20141);
        return personaL;
    }

  
    public void setPersonaL(List<personaC> personaL) {
        this.personaL = personaL;
    }

    public void onCellEdit(CellEditEvent event) {

        //Object oldValue = event.getOldValue();  
        Object newValue = event.getNewValue();
        notasC lista;
        lista = ((notasC) notasL.get(event.getRowIndex()));

        docente_notasD = new docente_notaDAO();
        docente_notasD.insertarNotaAlumno(1, ""+periodo, malla, seleccionCarrera, seleccionCurso, seleccionSeccion, lista.getAlumno(), event.getColumn().getHeaderText().toUpperCase(), Double.parseDouble(newValue.toString()));
        if (!subNotas.isEmpty()) {

            //-------- registrar promedio de sub nota ---
            notasL = new ArrayList<>();
//            notasL = docente_notasD.getDocente_notas(1, periodo, malla, seleccionCarrera, seleccionCurso, seleccionSeccion, formula, subNotas);
            lista = ((notasC) notasL.get(event.getRowIndex()));
            docente_notasD.insertarNotaAlumno(1, ""+periodo, malla, seleccionCarrera, seleccionCurso, seleccionSeccion, lista.getAlumno(), subNotas.toUpperCase(), lista.getPromedio());
            RequestContext.getCurrentInstance().execute("$('#frmsubNotas\\\\:notasSub tbody tr:nth-child(" + (event.getRowIndex() + 1) + ") td:nth-last-child(2)').text('" + lista.getPromedio() + "');");
        }

        // ------------- registar promedio final ----
        notasSubL = new ArrayList<>();
      //  notasSubL = docente_notasD.getDocente_notas(1, periodo, malla, seleccionCarrera, seleccionCurso, seleccionSeccion, formula, "");
        lista = ((notasC) notasSubL.get(event.getRowIndex()));
        docente_notasD.insertarPromedioAlumno(1, ""+periodo, seleccionCarrera, seleccionCurso, seleccionSeccion, lista.getAlumno());
        RequestContext.getCurrentInstance().execute("$('#frmNotas\\\\:notas tbody tr:nth-child(" + (event.getRowIndex() + 1) + ") td:nth-last-child(2)').text('" + Math.round(lista.getPromedio()) + "');");
       //   --------------------------------------------

    }

    public void refrescar() {
        createDynamicColumns();
        notasL = new ArrayList<>();
//        notasL = docente_notasD.getDocente_notas(1, periodo, malla, seleccionCarrera, seleccionCurso, seleccionSeccion, formula, subNotas);

    }

    public void atras() {
        subNotas = "";
        formula = 0;
        bandera = true;
        createDynamicColumns();
        refrescar();

    }

    public List<ColumnModel> getColumnas() {
        return columnas;
    }

    public void setColumnas(List<ColumnModel> columnas) {
        this.columnas = columnas;
    }
    private List<ArrayList<String>> columnL;

    public boolean validaEdicion(String Columna) {
        boolean valor = true;
        if (Columna.endsWith("u0")) {
            valor = false;

        } else if (Columna.endsWith("u5")) {
            valor = false;
        }
        return valor;

    }

    public void createDynamicColumns() {

        columnas = new ArrayList<>();
        docente_notasD = new docente_notaDAO();
        columnL = new ArrayList<>();
        columnas.add(new ColumnModel("ALUMNO", "alumno", false));
        columnas.add(new ColumnModel("NOMBRES", "nombres", false));
        //  columnL=docente_notasD.getColumna(1,periodo,malla,seleccionCarrera, seleccionCurso,subNotas);        
        
        for (int i = 0; i < columnL.size(); i++) {
            formula = Integer.parseInt(columnL.get(i).get(4).toString());

            columnas.add(new ColumnModel(columnL.get(i).get(2).toString() + "<br/>" + columnL.get(i).get(3).toString(), columnL.get(i).get(0).toString().toLowerCase(), validaEdicion(columnL.get(i).get(0).toString().toLowerCase())));
        }

        columnas.add(new ColumnModel("PROMEDIO", "promedio", false));

    }

    
    public int getFormula() {
        return formula;
    }
    public void setFormula(int formula) {
        this.formula = formula;
    }
    public List<notasC> getNotasSubL() {
        return notasSubL;
    }
    public void setNotasSubL(List<notasC> notasSubL) {
        this.notasSubL = notasSubL;
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
    public String getFecha_hasta() {
        return fecha_hasta;
    }
    public void setFecha_hasta(String fecha_hasta) {
        this.fecha_hasta = fecha_hasta;
    }

    public List<personaC> getListaDocentePeriodo() {
        perDAO = new personaDAO();
        listaDocentePeriodo = perDAO.BuscaPersonaPeriodo(1, ""+periodo);
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
    public String getMalla() {
        return malla;
    }
    public void setMalla(String malla) {
        this.malla = malla;
    }
    public List<personaC> getListaDocentePeriodoI() {
        perDAO = new personaDAO();
        listaDocentePeriodoI = perDAO.BuscaPersonaPeriodoInstitucion(8,""+ periodo);

        return listaDocentePeriodoI;
    }
    public void setListaDocentePeriodoI(List<personaC> listaDocentePeriodoI) {
        this.listaDocentePeriodoI = listaDocentePeriodoI;
    }
    public boolean isBandera() {
        return bandera;
    }
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }
    public static class ColumnModel {

        private String header;
        private String property;
        private boolean editable;

        public ColumnModel() {
        }

        public ColumnModel(String header, String property, boolean editable) {
            this.header = header;
            this.property = property;
            this.editable = editable;
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
    }

    public void mostrarCursosDocente() {

        curdao = new cursoDAO();
        seleccionCurso = "";
        listaCursoDocente = curdao.BuscaCursosPeriodoCarreraDocenteSeccion(periodo, seleccionCarrera, seleccionDocente, seleccionSeccion);

    }

    public void cerrar() {
        subNotas = "";
        formula = 0;
        createDynamicColumns();
        refrescar();

    }

    public void imprimire() {
        util.consolaCliente( "imprime");
    }

    public void imprimir() throws ClassNotFoundException, SQLException, JRException, IOException {
        if (formula == 10) {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conexion = DriverManager.getConnection("jdbc:sqlserver://192.168.1.249;databaseName=BDUP", "anavarro", "123456");
            FacesContext context = FacesContext.getCurrentInstance();
            ServletContext servletCont = (ServletContext) context.getExternalContext().getContext();

            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.setContentType("application/pdf");
            ServletOutputStream out = response.getOutputStream();
        //JasperReport reporte = (JasperReport) JRLoader.loadObject(getServletContext().getRealPath("vacio.jasper")); 

            JasperReport reporte = (JasperReport) JRLoader.loadObject(servletCont.getRealPath("ireport/notasDocente.jasper"));
            Map parametros = new HashMap();
            parametros.put("periodo", periodo);
            parametros.put("carrera", seleccionCarrera);
            parametros.put("curso", seleccionCurso);
            parametros.put("seccion", seleccionSeccion);

            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);

            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);

            exporter.exportReport();
        }

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
            bandera = true;
        } else {
            bandera = false;
        }
        cursoSeccionDocenteD = new cursoSeccionDocenteDAO();
        malla = cursoSeccionDocenteD.mostrarPeriodoCarreraCursoSeccion(1, "" + periodo, seleccionCarrera, seleccionCurso, seleccionSeccion).getMalla();
        createDynamicColumns();

        docente_notasD = new docente_notaDAO();
        notasL = new ArrayList<notasC>();
//        notasL = docente_notasD.getDocente_notas(1, periodo, malla, seleccionCarrera, seleccionCurso, seleccionSeccion, formula, subNotas);

    }
    personaDAO perDAO;

    public void mostrarCarreraDocente() {
        cardao = new carrerasDAO();
        seleccionCarrera = "";

        listaCarreraDocente = cardao.mostrarCarrerasDocentePeriodo(1, ""+periodo, seleccionDocente);
    }

    public void mostrarCarreraDocenteTitulacion() {
        cardao = new carrerasDAO();
        seleccionCarrera = "";

        listaCarreraDocente = cardao.mostrarCarrerasDocentePeriodo(8, ""+periodo, seleccionDocente);
    }

    public void mostrarDocentePeriodo() {
        perDAO = new personaDAO();

        listaDocentePeriodo = perDAO.BuscaPersonaPeriodo(1, ""+periodo);

    }

    public void mostrarSeccionDocente() {

        secdao = new seccionDAO();
        seleccionSeccion = "";

        listaSeccionDocente = secdao.mostrarSeccionPeriodoCarreraDocente(1, periodo, seleccionCarrera, seleccionDocente);

    }

    docente_notaDAO docente_notasD;

    public List<notasC> getNotasL() {

        return notasL;
    }
    public void setNotasL(List<notasC> notasL) {
        this.notasL = notasL;
    }
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

}
