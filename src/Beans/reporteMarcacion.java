
package Beans;

import DAO.accesoDAO;
import DAO.horasAgregadasDAO;
import DAO.marcacionDocenteDAO;
import DAO.personaDAO;
import DAO.registrotdDAO;
import ENTIDAD.accesoC;
import ENTIDAD.horasAgregadasC;
import ENTIDAD.personaC;
import ENTIDAD.registrotdC;

import java.io.IOException;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.poi.hssf.usermodel.HSSFCell;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.hssf.util.HSSFColor;


@ManagedBean
@ViewScoped
public class reporteMarcacion implements Serializable {

	private static final long serialVersionUID = 1L;
	personaDAO perdao;
   

    private List<marcacion.marcacionDocenteC> listadoReporte;
    private List<personaC> listadoDocentes;
    private String ffin;
    private String fini;
    private String seleccionTipo = "%";
    private boolean sin_marcacion = true;

    private int seleccionReporte;
    private String seleccionCarrera = "%";
    private int seleccionMes = 0;
    private String seleccionDia;

    private String filtroDocente;
    private String seleccionDocente;
    private horasAgregadasC horasAgregadas=new horasAgregadasC();
    private List<horasAgregadasC> horasAgregadasL;

    
    private Date fechaInicio;
    private Date fechaFinal;
    private personaC personaS;
    
    
    
    /**
     * *******REPORTE MENSUAL*********
     */
    private List<ColumnModel> columnas;

    private List<ArrayList<String>> marcacionL;
    private List<detalleMarcacion> marcacionDetalleL;
    
    
    
    private int totalDia;
    private int totalFeriado;
    private accesoC acceso=new accesoC();
    
    
    private boolean banderaRegulariza;
    
    private String institucion;
    private String periodo="%";
    
    
 

    
    public boolean isBanderaRegulariza() {
		return banderaRegulariza;
	}





	public void setBanderaRegulariza(boolean banderaRegulariza) {
		this.banderaRegulariza = banderaRegulariza;
	}





	public void load(String institucion,String usuario){
    	this.institucion=institucion;
    	acceso=new accesoDAO().mostrarAccesos(usuario, 15, 86);
    }





	public void getReportData() throws IOException {
        
  
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Reporte Web");
        
        HSSFRow row =sheet.createRow(0) ;
    
        HSSFCellStyle style = workbook.createCellStyle();
               
        style.setFillForegroundColor(HSSFColor.ORANGE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        style.setBottomBorderColor((short)8);
        style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        style.setLeftBorderColor((short)8);
        style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        style.setRightBorderColor((short)8);
        style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        style.setTopBorderColor((short)8);
        style.setRotation((short)90);
       
        
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        style2.setBottomBorderColor((short)8);
        style2.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        style2.setLeftBorderColor((short)8);
        style2.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        style2.setRightBorderColor((short)8);
        style2.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        style2.setTopBorderColor((short)8);
        style2.setRotation((short)90);
        
        
        HSSFCellStyle styleFila = workbook.createCellStyle();
        
        
        styleFila.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        styleFila.setBottomBorderColor((short)8);
        styleFila.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        styleFila.setLeftBorderColor((short)8);
        styleFila.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        styleFila.setRightBorderColor((short)8);
        styleFila.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        styleFila.setTopBorderColor((short)8);
     //   styleFila.setWrapText(true);
        
    
    // CABECERA
            for(int i=0;i<=columnas.size()-1;i++)
            {
                HSSFCell cellC = row.createCell(i);     
                if(i>=2){                    
                    cellC.setCellStyle((columnas.get(i).feriado==1?style:style2));
                    sheet.setColumnWidth(i, 900);
                }else{                   
                    cellC.setCellStyle(styleFila);                    
                }                
                cellC.setCellValue(columnas.get(i).header);
            }    
    
        //----- CUERPO 
            //row = sheet.createRow(i);
            for(int x=0;x<=marcacionL.size()-1;x++){
                row = sheet.createRow(x+1);
                 for(int i=0;i<=columnas.size()-1;i++)
                {               
                
                HSSFCell cellD  = row.createCell(i);
                cellD.setCellValue(marcacionL.get(x).get(i).toString());
                cellD.setCellStyle(styleFila);
                sheet.autoSizeColumn((short)x+1);
            }
            
        }
    

    FacesContext facesContext = FacesContext.getCurrentInstance();
    ExternalContext externalContext = facesContext.getExternalContext();
    externalContext.setResponseContentType("application/vnd.ms-excel");
    externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"Registro Asistencia.xls\"");

    workbook.write(externalContext.getResponseOutputStream());
    facesContext.responseComplete();

}
    
    
    
    
    
    public int getTotalDia() {
		return totalDia;
	}





	public void setTotalDia(int totalDia) {
		this.totalDia = totalDia;
	}





	public int getTotalFeriado() {
		return totalFeriado;
	}





	public void setTotalFeriado(int totalFeriado) {
		this.totalFeriado = totalFeriado;
	}





	public void postProcessXLS(Object document) {
            HSSFWorkbook wb = (HSSFWorkbook) document;
            HSSFSheet sheet = wb.getSheetAt(0);
            
           
            
            HSSFRow header = sheet.getRow(0);
            
            
            for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
                
               HSSFCellStyle cellStyle; 
                if (i<=2){
                   
                    cellStyle = wb.createCellStyle();            
                    cellStyle.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
                    cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                    cellStyle.setFillBackgroundColor(HSSFColor.LIGHT_BLUE.index);
                    cellStyle.setRotation((short)0);
                   // cellStyle.setWrapText(true);
                    sheet.autoSizeColumn((short)i);
                    
                }else{
                    cellStyle = wb.createCellStyle(); 
                    cellStyle.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
                    cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                    cellStyle.setFillBackgroundColor(HSSFColor.LIGHT_BLUE.index);
                    cellStyle.setRotation((short)90);
                  //   cellStyle.setWrapText(true);
                    sheet.setColumnWidth(i, 900);
                }
                
                
               header.getCell(i).setCellStyle(cellStyle);
            }
}
    /**
     * *******REPORTE MENSUAL*********
     */

    horasAgregadasDAO horasAgregadasD;
    registrotdDAO registrotdD;
    personaDAO personaD;
    String cpersonal="";
    public void mostrarHorasAgregadas(String cpersonal){
       this.cpersonal=cpersonal;
       horasAgregadasD=new horasAgregadasDAO();
       horasAgregadasL= horasAgregadasD.mostrarhorasAgregadas(cpersonal);              
      
       util.script("dlgRegulariza.show()");
    }
    
    public void nuevoHoraAgregada(){
    	banderaRegulariza=true;
    	horasAgregadas=new horasAgregadasC(util.año(), util.mesHoy(), 0, 0, 0.0, cpersonal, "", 1);
    }
    public void cancelarAgregada(){
    	banderaRegulariza=false;
    	
    }
    public void insertarHorasAgregadas( ){
        
        horasAgregadasD=new horasAgregadasDAO();
        horasAgregadasD.insertarHorasAgregadas(horasAgregadas);        
        horasAgregadasL= horasAgregadasD.mostrarhorasAgregadas(cpersonal);
    	banderaRegulariza=false;
    }
    public void eliminarHorasAgregadas(horasAgregadasC item){
        
        
        
        item.setEstadoRegistro(0);
        horasAgregadasD=new horasAgregadasDAO();
        horasAgregadasD.insertarHorasAgregadas(item);
        horasAgregadasL= horasAgregadasD.mostrarhorasAgregadas(item.getCpersonal());
        
    }
    
    
    public void onRowEdit(detalleMarcacion item) {
        
    	
        new registrotdDAO().insertar(new registrotdC(item.id, item.institucion, item.periodo, item.dia, item.semana, item.fecha, item.hora_ing, item.hora_sal, item.cpersonal, item.carrera, item.curso, item.seccion, item.turno, item.ciclo, item.aula, item.tipo, 0, item.tema, util.convertDate(item.registroIngreso,"HH:mm:ss"), util.convertDate(item.registroSalida, "HH:mm:ss") , 0, 0, item.tipoHora, item.tipoClase,"REGULARIZADO POR SISTEMA", 1));
        
      if (item.id==-1){
         
           marcacionDetalleL = new marcacionDocenteDAO().listadoDetalleSinMarcacion(item.cpersonal, item.fecha);
      }else{
          
      }
    }

    public void crearColumnasDinamicas() {
 
        columnas = new ArrayList<>();
      


    }

    public void cargarMarcacionMensual() {
    	totalDia=0;
    	totalFeriado=0;
    
        columnas = new ArrayList<>();
        columnas = new marcacionDocenteDAO().listadoColumnaDinamica(fechaInicio, fechaFinal);
        
        for (ColumnModel item : columnas) {
			if(item.getFeriado()==1){
				totalFeriado++;
			}else{
				totalDia++;
			}
		}
        totalDia-=10;
        marcacionL = new marcacionDocenteDAO().listadoMensualMarcacion(institucion,fechaInicio, fechaFinal, periodo, seleccionDocente, seleccionCarrera, seleccionTipo , sin_marcacion);
    }
    public void mostrarTodo(){
    	String institucionT=institucion;
    	institucion="%";
    	cargarMarcacionMensual();
    	institucion=institucionT;
    }

    public List<detalleMarcacion> detalleMarcacionDocente(String cpersonal, String docente, String fecha, String tipo) {
       
        seleccionDocente = docente;
        marcacionDetalleL = new marcacionDocenteDAO().listadoDetalleMarcacion(cpersonal, fecha, tipo);
        personaS=new personaDAO().mostrarPersona_CPersonal(cpersonal);
        util.script("dglDetalle.show()");
        return marcacionDetalleL;

    }

    public List<detalleMarcacion> detalleMarcacionSinDocente(String cpersonal, String docente, String fecha, String tipo) {
        seleccionDocente = docente;   
       
        marcacionDetalleL = new marcacionDocenteDAO().listadoDetalleSinMarcacion(cpersonal, util.convertDate(fecha,"yyyy-MM-dd")); 
        personaS=new personaDAO().mostrarPersona_CPersonal(cpersonal);       
         util.script("dglDetalle.show()");
        return marcacionDetalleL;

    }

    public void cargarMarcacionTardanzas(Date fechaIni, Date fechaFin, String docente, String carrera, String tipo) {
     
        columnas = new ArrayList<>();
        columnas = new marcacionDocenteDAO().listadoColumnaDinamica(fechaIni, fechaFin);
        marcacionL = new marcacionDocenteDAO().listadoMensualTardanzas(fechaIni, fechaFin, docente, carrera, tipo);
    }
    public List<marcacion.marcacionDocenteC> getListadoReporte() {
        if (listadoReporte == null) {
            listadoReporte = new ArrayList<>();
            System.out.println("entro a listado reporte nulo");
        }
        return listadoReporte;
    }
    public void setListadoReporte(List<marcacion.marcacionDocenteC> listadoReporte) {
        this.listadoReporte = listadoReporte;
    }
    public List<personaC> getListadoDocentes() {
        if (listadoDocentes == null) {
            listadoDocentes = new ArrayList<>();
            System.out.println("entro a listado docentes nulo");
        }
        return listadoDocentes;
    }
    public void setListadoDocentes(List<personaC> listadoDocentes) {
        this.listadoDocentes = listadoDocentes;
    }
    public int getSeleccionReporte() {
        return seleccionReporte;
    }
    public void setSeleccionReporte(int seleccionReporte) {
        this.seleccionReporte = seleccionReporte;
    }
    public String getSeleccionCarrera() {
        return seleccionCarrera;
    }
    public void setSeleccionCarrera(String seleccionCarrera) {
        this.seleccionCarrera = seleccionCarrera;
    }
    public int getSeleccionMes() {
        return seleccionMes;
    }
    public void setSeleccionMes(int seleccionMes) {
        this.seleccionMes = seleccionMes;
    }
    public String getSeleccionDia() {
        return seleccionDia;
    }
    public void setSeleccionDia(String seleccionDia) {
        this.seleccionDia = seleccionDia;
    }
    public String getFiltroDocente() {
        return filtroDocente;
    }
    public void setFiltroDocente(String filtroDocente) {
        this.filtroDocente = filtroDocente;
    }
    public String getSeleccionDocente() {
        return seleccionDocente;
    }
    public void setSeleccionDocente(String seleccionDocente) {
        this.seleccionDocente = seleccionDocente;
    }
    public List<ColumnModel> getColumnas() {
        return columnas;
    }
    public void setColumnas(List<ColumnModel> columnas) {
        this.columnas = columnas;
    }
    public String getFfin() {
        return ffin;
    }
    public void setFfin(String ffin) {
        this.ffin = ffin;
    }
    public String getFini() {
        return fini;
    }
    public void setFini(String fini) {
        this.fini = fini;
    }
    public List<ArrayList<String>> getMarcacionL() {
        return marcacionL;
    }
    public void setMarcacionL(List<ArrayList<String>> marcacionL) {
        this.marcacionL = marcacionL;
    }
    public List<detalleMarcacion> getMarcacionDetalleL() {
        return marcacionDetalleL;
    }
    public void setMarcacionDetalleL(List<detalleMarcacion> marcacionDetalleL) {
        this.marcacionDetalleL = marcacionDetalleL;
    }
    public String getSeleccionTipo() {
        return seleccionTipo;
    }
    public void setSeleccionTipo(String seleccionTipo) {
        this.seleccionTipo = seleccionTipo;
    }
    public boolean isSin_marcacion() {
        return sin_marcacion;
    }
    public void setSin_marcacion(boolean sin_marcacion) {
        this.sin_marcacion = sin_marcacion;
    }
    public horasAgregadasC getHorasAgregadas() {
        return horasAgregadas;
    }
    public void setHorasAgregadas(horasAgregadasC horasAgregadas) {
        this.horasAgregadas = horasAgregadas;
    }
    public List<horasAgregadasC> getHorasAgregadasL() {
        return horasAgregadasL;
    }
    public void setHorasAgregadasL(List<horasAgregadasC> horasAgregadasL) {
        this.horasAgregadasL = horasAgregadasL;
    }
    public Date getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public Date getFechaFinal() {
        return fechaFinal;
    }
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
    public personaC getPersonaS() {
        return personaS;
    }
    public void setPersonaS(personaC personaS) {
        this.personaS = personaS;
    }

    public static class detalleMarcacion {

        private int id;
        private int institucion;
        private String periodo;
        private int dia;
        private int semana;
        private Date fecha;
        private Date hora_ing;
        private Date hora_sal;
        private String cpersonal;
        private String personal;
        private String docente;
        private String carrera;
        private String descarrera;
        private String ciclo;
        private String curso;
        private String desCurso;
        private String seccion;
        private int turno;
        private String aula;
        private String tipo;
        private String tardanza;
        private String tema;
   
        private Date registroIngreso;
        private Date registroSalida;
        private String total_horas;
        private int tipoClase;
        private int tipoHora;

       
      
        public String getPeriodo() {
            return periodo;
        }
        public void setPeriodo(String periodo) {
            this.periodo = periodo;
        }
        public int getDia() {
            return dia;
        }
        public void setDia(int dia) {
            this.dia = dia;
        }
        public int getSemana() {
            return semana;
        }
        public void setSemana(int semana) {
            this.semana = semana;
        }
        
        public Date getFecha() {
			return fecha;
		}
		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}
		public Date getHora_ing() {
            return hora_ing;
        }
        public void setHora_ing(Date hora_ing) {
            this.hora_ing = hora_ing;
        }
        public Date getHora_sal() {
            return hora_sal;
        }
        public void setHora_sal(Date hora_sal) {
            this.hora_sal = hora_sal;
        }
        public String getCpersonal() {
            return cpersonal;
        }
        public void setCpersonal(String cpersonal) {
            this.cpersonal = cpersonal;
        }
        public String getDocente() {
            return docente;
        }
        public void setDocente(String docente) {
            this.docente = docente;
        }
        public String getCarrera() {
            return carrera;
        }
        public void setCarrera(String carrera) {
            this.carrera = carrera;
        }
        public String getDescarrera() {
            return descarrera;
        }
        public void setDescarrera(String descarrera) {
            this.descarrera = descarrera;
        }
        public String getCiclo() {
            return ciclo;
        }
        public void setCiclo(String ciclo) {
            this.ciclo = ciclo;
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

        public String getAula() {
            return aula;
        }

        public void setAula(String aula) {
            this.aula = aula;
        }
        public String getTipo() {
            return tipo;
        }
        public void setTipo(String tipo) {
            this.tipo = tipo;
        }
        public String getTema() {
            return tema;
        }
        public void setTema(String tema) {
            this.tema = tema;
        }
       
        public String getTotal_horas() {
            return total_horas;
        }
        public void setTotal_horas(String total_horas) {
            this.total_horas = total_horas;
        }
        public String getTardanza() {
            return tardanza;
        }
        public void setTardanza(String tardanza) {
            this.tardanza = tardanza;
        }
        public int getTipoClase() {
            return tipoClase;
        }
        public void setTipoClase(int tipoClase) {
            this.tipoClase = tipoClase;
        }
        public int getTipoHora() {
            return tipoHora;
        }
        public void setTipoHora(int tipoHora) {
            this.tipoHora = tipoHora;
        }
        public int getInstitucion() {
            return institucion;
        }
        public void setInstitucion(int institucion) {
            this.institucion = institucion;
        }
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public int getTurno() {
            return turno;
        }
        public void setTurno(int turno) {
            this.turno = turno;
        }
        public String getPersonal() {
            return personal;
        }
        public void setPersonal(String personal) {
            this.personal = personal;
        }
        public Date getRegistroIngreso() {
            return registroIngreso;
        }
        public void setRegistroIngreso(Date registroIngreso) {
            this.registroIngreso = registroIngreso;
        }
        public Date getRegistroSalida() {
            return registroSalida;
        }
        public void setRegistroSalida(Date registroSalida) {
            this.registroSalida = registroSalida;
        }
    }

    public static class ColumnModel {

        private String header;
        private String property;
        private int indice;
        private int feriado;
        private boolean editable;

        public ColumnModel() {
        }

        public ColumnModel(String header, String property, int indice, int feriado) {
            this.header = header;
            this.property = property;
            this.indice = indice;
            this.feriado = feriado;
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
        public int getIndice() {
            return indice;
        }
        public void setIndice(int indice) {
            this.indice = indice;
        }
        public int getFeriado() {
            return feriado;
        }
        public void setFeriado(int feriado) {
            this.feriado = feriado;
        }
        public boolean isEditable() {
            return editable;
        }
        public void setEditable(boolean editable) {
            this.editable = editable;
        }

    }
    public accesoC getAcceso() {
		return acceso;
	}





	public void setAcceso(accesoC acceso) {
		this.acceso = acceso;
	}
}
