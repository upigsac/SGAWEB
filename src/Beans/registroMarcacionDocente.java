

package Beans;

import DAO.cursoDAO;
import DAO.cursoSeccionDocenteDAO;
import DAO.personaDAO;
import DAO.personalDAO;
import DAO.registroMarcacionDocenteDAO;
import DAO.seccionDAO;
import ENTIDAD.cursoSeccionDocenteC;
import ENTIDAD.cursosC;

import ENTIDAD.seccionC;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



@ManagedBean(name="registroMarcacionDocenteB")
@ViewScoped
public class registroMarcacionDocente implements Serializable{
    
  
	private static final long serialVersionUID = 1L;
	private List<cursoSeccionDocenteC> cursoSeccionDocenteL=new ArrayList<>();
    private List<cabecera> cabeceraL=new ArrayList<>();
    private List<cuerpo> cuerpoL=new ArrayList<>();
    private List<pie> pieL=new ArrayList<>();
    
   
    private String persona;
    private String codigoPersonal;
    private String codigoCarrera="%";
    
    cursoSeccionDocenteDAO cursoSeccionDocenteD;
    registroMarcacionDocenteDAO registroMarcacionDocenteD;
    cursoDAO cursoD;
    seccionDAO seccionD;
    personalDAO personalD;
    personaDAO personaD;
    
    
    public registroMarcacionDocente() {
    }
   
    
  /*
	public void exportarExcel(String nombreHoja) throws IOException  {
    	
    	
    	FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("Upig", "false", null);
    	
        double[]totalMes=new double[12];
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(nombreHoja);        
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
        
        HSSFCellStyle styleFila = workbook.createCellStyle();   
        styleFila.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        styleFila.setBottomBorderColor((short)8);
        styleFila.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        styleFila.setLeftBorderColor((short)8);
        styleFila.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        styleFila.setRightBorderColor((short)8);
        styleFila.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        	
        
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 30);
        font.setFontName("IMPACT");
        font.setItalic(true);
        font.setColor(HSSFColor.BRIGHT_GREEN.index);
        
        
        HSSFCellStyle stylePie = workbook.createCellStyle();   
       
        stylePie.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        stylePie.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        stylePie.setFont(font);
        
        registroMarcacionDocenteD=new registroMarcacionDocenteDAO();  
        List<cabecera> itemCabeceraL=new ArrayList<>();
        itemCabeceraL=registroMarcacionDocenteD.mostrarCabecera(1, "20161", "%");
        
        // CABECERA
       
            HSSFCell cellCC = row.createCell(0);
            	  
            cellCC.setCellValue("PERSONAL DOCENTE");
            
            cellCC = row.createCell(1);  
           
            cellCC.setCellValue("CURSO");
            int i=2;        
            for(cabecera colum : itemCabeceraL)
            {   
                        HSSFCell cellC = row.createCell(i);    
                        cellC.setCellStyle(style);
                        sheet.setColumnWidth(i, 900);
                        cellC.setCellValue(util.listaDiasSemana.get(colum.dia) +" "+   util.convertDate(colum.fecha, "dd-MM-yyyy"));
                       i++;
            }    
                       
            cellCC = row.createCell(itemCabeceraL.size()+2);    
            cellCC.setCellStyle(style);
            cellCC.setCellValue("MARCADA");
            cellCC = row.createCell(itemCabeceraL.size()+3);    
            cellCC.setCellStyle(style);
            cellCC.setCellValue("PROYECTADA");
            
            cursoSeccionDocenteD=new cursoSeccionDocenteDAO();        
                int x=0;
                int totalMarcada=0;
                int totalProyectada=0;
                personaC itemPersona;
                cursosC itemCurso;
                personaD=new personaDAO();
                cursoD=new cursoDAO();
                for (cursoSeccionDocenteC fila : cursoSeccionDocenteD.mostrarCursoSeccionDocentePrincipal("1", "20161","%","%","%","%", "%")) {
                    totalMarcada=0;
                    totalProyectada=0;
                    System.out.println("-------INICIO-------");
                    System.out.println(fila.getPersonal() +" - " + fila.getCurso());
                      row = sheet.createRow(x+1); 
                      HSSFCell cellDC= row.createCell(0); 
                     
                      itemPersona=personaD.CodigoPersonaPersonal(fila.getPersonal());
                      cellDC.setCellValue(itemPersona.getPaterno() +" "+ itemPersona.getMaterno() +" "+ itemPersona.getNombres());
                      
                      cellDC= row.createCell(1);     
                      itemCurso=cursoD.mostrarCurso(fila.getCurso());
                      cellDC.setCellValue(itemCurso.getCurso() +" - "+itemCurso.getDescripcion());
                      
                      
                      i=0;
                 
                     for(cuerpoHora itemCuerpoHora :  registroMarcacionDocenteD.mostrarCuerpoHoras(fila.getInstitucion(), fila.getPeriodo(), fila.getCarrera(), fila.getMalla(), fila.getCurso(), fila.getSeccion(), fila.getPersonal())){
                     System.out.println("***************INICIO FECHA ************");
                     for(int colum=i;colum<itemCabeceraL.size();colum++){
                         
                         System.out.println(colum + " | " + itemCabeceraL.get(colum).fecha + " | " + itemCuerpoHora.fechaIngreso);
                          cellDC = row.createCell(colum+2) ;                         
                         
                         
                        if(itemCabeceraL.get(colum).fecha.equals(itemCuerpoHora.fechaIngreso)  ){
                        	totalMes[itemCuerpoHora.fechaIngreso.getMonth()] +=itemCuerpoHora.horaMarcada;
                        	cellDC.setCellValue(itemCuerpoHora.horaMarcada );  
                        	cellDC.setCellType(Cell.CELL_TYPE_NUMERIC);
                            totalMarcada+=itemCuerpoHora.horaMarcada;
                             totalProyectada+=itemCuerpoHora.horaProyectada;
                             System.out.println("*************** SALTO  MES ************");
                             i++;
                            break;
                        }else{
                        	cellDC.setCellValue(" ");
                             i++;
                        }    
                     }
                     
                     
                        
                }
                     HSSFCell cellD ;
                     cellD = row.createCell(itemCabeceraL.size()+2) ;                         
                     cellD.setCellStyle(styleFila);
                     cellD.setCellValue(totalMarcada);
                     cellD = row.createCell(itemCabeceraL.size()+3) ;                         
                     cellD.setCellStyle(styleFila);
                     cellD.setCellValue(totalProyectada);
                    
             x++;
             System.out.println("-------FIN -------");
        }       
                
                int colum=0;
                 row = sheet.createRow(x+1); 
                
                for (pie itemPie : registroMarcacionDocenteD.mostrarPie(1, "20161", "%")) {
             	  
                    HSSFCell cellDC= row.createCell(colum +2);
                    
                    System.out.println(" Mes "+ util.listaMeses.get(itemPie.mes -1 )  +" "+ totalMes[itemPie.mes -1 ]);
                    cellDC.setCellStyle(stylePie);
                    cellDC.setCellValue(util.listaMeses.get(itemPie.mes -1 )  + " = " + totalMes[itemPie.mes -1 ]);
                    sheet.addMergedRegion(new CellRangeAddress(x+1, x+1, colum+2, itemPie.cantidad + colum+1));
                    colum+=itemPie.cantidad;   
                  
                    
 			}
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ExternalContext externalContext = facesContext.getExternalContext();
    externalContext.setResponseContentType("application/vnd.ms-excel");
  //  externalContext.setResponseContentType("application/csv");
    externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"cargaElectiva.xls\"");
    workbook.write(externalContext.getResponseOutputStream());
    facesContext.responseComplete();
 

}
    */
    
 public void load(String institucion,String periodo,String personal){
  
        
        
        cuerpoL=new ArrayList<>();
        registroMarcacionDocenteD=new registroMarcacionDocenteDAO();
        cabeceraL=registroMarcacionDocenteD.mostrarCabecera("1", periodo, personal);
        cursoSeccionDocenteD=new cursoSeccionDocenteDAO();
        cursoD=new cursoDAO();
        seccionD=new seccionDAO();
        cuerpo itemCuerpo;
        for (cursoSeccionDocenteC itemSeccionDocente : cursoSeccionDocenteD.mostrarCursoSeccionDocentePrincipal("1", periodo,"%","%","%","%", personal)) {
            itemCuerpo=new cuerpo(itemSeccionDocente);
            itemCuerpo.curso=cursoD.mostrarCurso(itemSeccionDocente.getCurso());
            itemCuerpo.seccion=seccionD.mostrarSeccion(itemSeccionDocente.getInstitucion(), itemSeccionDocente.getSeccion());
            for (cuerpoHora itemCuerpoHora:registroMarcacionDocenteD.mostrarCuerpoHoras(itemSeccionDocente.getInstitucion(), itemSeccionDocente.getPeriodo(), itemSeccionDocente.getCarrera(), itemSeccionDocente.getMalla(), itemSeccionDocente.getCurso(), itemSeccionDocente.getSeccion(), itemSeccionDocente.getPersonal()))
            {
               
                itemCuerpo.cuerpoHoraL.put(itemCuerpoHora.fechaIngreso, itemCuerpoHora);
            }
            cuerpoL.add(itemCuerpo);
            System.out.println("-------------------------");
        }
        System.out.println("FIN");
        pieL=registroMarcacionDocenteD.mostrarPie("1", periodo, personal);
    }
    
    
    public void mostrarCargaHorario(){
        personalD=new personalDAO();
        if(! codigoPersonal.endsWith("%")){
            codigoPersonal=personalD.BuscaPersonaCodigo(codigoPersonal).getPersonal();
        }
        
        
        cuerpoL=new ArrayList<>();
        registroMarcacionDocenteD=new registroMarcacionDocenteDAO();
        cabeceraL=registroMarcacionDocenteD.mostrarCabecera("1", "20161", codigoPersonal);
        cursoSeccionDocenteD=new cursoSeccionDocenteDAO();
        cursoD=new cursoDAO();
        seccionD=new seccionDAO();
        cuerpo itemCuerpo;
        for (cursoSeccionDocenteC itemSeccionDocente : cursoSeccionDocenteD.mostrarCursoSeccionDocentePrincipal("1", "20161",codigoCarrera,"%","%","%", codigoPersonal)) {
            itemCuerpo=new cuerpo(itemSeccionDocente);
            itemCuerpo.curso=cursoD.mostrarCurso(itemSeccionDocente.getCurso());
            itemCuerpo.seccion=seccionD.mostrarSeccion(itemSeccionDocente.getInstitucion(), itemSeccionDocente.getSeccion());
            for (cuerpoHora itemCuerpoHora:registroMarcacionDocenteD.mostrarCuerpoHoras(itemSeccionDocente.getInstitucion(), itemSeccionDocente.getPeriodo(), itemSeccionDocente.getCarrera(), itemSeccionDocente.getMalla(), itemSeccionDocente.getCurso(), itemSeccionDocente.getSeccion(), itemSeccionDocente.getPersonal()))
            {
               
                itemCuerpo.cuerpoHoraL.put(itemCuerpoHora.fechaIngreso, itemCuerpoHora);
            }
            cuerpoL.add(itemCuerpo);
            System.out.println("-------------------------");
        }
        System.out.println("FIN");
        pieL=registroMarcacionDocenteD.mostrarPie("1", "20161", codigoPersonal);
    }
    
    
    
    public double totalMarcadoMes(String anio,int mes){
    	
        double totalMes=0.0;
        for(cuerpo itemCuerpo: cuerpoL) {
            for (cuerpoHora itemCuerpoHora : itemCuerpo.cuerpoHoraL.values()) {
                if (util.convertDate(itemCuerpoHora.fechaIngreso,"yyyy").endsWith(anio) &&  itemCuerpoHora.fechaIngreso.getMonth()==mes){
                    
                    totalMes+=itemCuerpoHora.horaMarcada;
                }
            }
 
        }
            
        return totalMes;
    }
     public double totalProyectadoMes(String anio,int mes){
        
        double totalMes=0.0;
        for(cuerpo itemCuerpo: cuerpoL) {
            for (cuerpoHora itemCuerpoHora : itemCuerpo.cuerpoHoraL.values()) {
                if (util.convertDate(itemCuerpoHora.fechaIngreso,"yyyy").endsWith(anio) && itemCuerpoHora.fechaIngreso.getMonth()==mes){
                
                    totalMes+=itemCuerpoHora.horaProyectada;
                }
            }
 
        }
            
        return totalMes;
    }
    
    

  
    public List<cabecera> getCabeceraL() {
        return cabeceraL;
    }

   
    public void setCabeceraL(List<cabecera> cabeceraL) {
        this.cabeceraL = cabeceraL;
    }

    
    public List<cursoSeccionDocenteC> getCursoSeccionDocenteL() {
        return cursoSeccionDocenteL;
    }

  
    public void setCursoSeccionDocenteL(List<cursoSeccionDocenteC> cursoSeccionDocenteL) {
        this.cursoSeccionDocenteL = cursoSeccionDocenteL;
    }

  
    public List<cuerpo> getCuerpoL() {
        return cuerpoL;
    }

 
    public void setCuerpoL(List<cuerpo> cuerpoL) {
        this.cuerpoL = cuerpoL;
    }

    public List<pie> getPieL() {
        return pieL;
    }

   
    public void setPieL(List<pie> pieL) {
        this.pieL = pieL;
    }

  
    public String getPersona() {
        return persona;
    }

    
    public void setPersona(String persona) {
        this.persona = persona;
    }

 
    public String getCodigoPersonal() {
        return codigoPersonal;
    }

  
    public void setCodigoPersonal(String codigoPersonal) {
        this.codigoPersonal = codigoPersonal;
    }
    
    
  

	public String getCodigoCarrera() {
		return codigoCarrera;
	}


	public void setCodigoCarrera(String codigoCarrera) {
		this.codigoCarrera = codigoCarrera;
	}




	public static class cabecera{
        private int dia;
        private Date fecha;
        private int item;
        private boolean feriado;
        

      
        public boolean isFeriado() {
			return feriado;
		}


		public void setFeriado(boolean feriado) {
			this.feriado = feriado;
		}


		public int getDia() {
            return dia;
        }

       
        public void setDia(int dia) {
            this.dia = dia;
        }

     
        public Date getFecha() {
            return fecha;
        }

       
        public void setFecha(Date fecha) {
            this.fecha = fecha;
        }

    
        public int getItem() {
            return item;
        }

     
        public void setItem(int item) {
            this.item = item;
        }

     
       
    }
    
    public class cuerpo{
        private cursoSeccionDocenteC cursoSeccionDocenteC;
        private cursosC curso;
        private seccionC seccion;
        
        
        
       

		private HashMap<Date,cuerpoHora> cuerpoHoraL=new HashMap<>();

        public cuerpo() {
        }

        public cuerpo(cursoSeccionDocenteC cursoSeccionDocenteC) {
            this.cursoSeccionDocenteC = cursoSeccionDocenteC;
        }
        
        public double totalHoraProyectada(){
            
            double total =0.0;
            for(cuerpoHora p : cuerpoHoraL.values()) {
                total+=p.horaProyectada;
                
                }
          
          
            return total;
        }
        
         public double totalHoraMarcadas(){
            
            double total =0.0;
            for(cuerpoHora p : cuerpoHoraL.values()) {
                total+=p.horaMarcada;
                
                }
          
          
            return total;
        }
         public seccionC getSeccion() {
 			return seccion;
 		}

 		public void setSeccion(seccionC seccion) {
 			this.seccion = seccion;
 		}


      
        public cursoSeccionDocenteC getCursoSeccionDocenteC() {
            return cursoSeccionDocenteC;
        }

        public void setCursoSeccionDocenteC(cursoSeccionDocenteC cursoSeccionDocenteC) {
            this.cursoSeccionDocenteC = cursoSeccionDocenteC;
        }

      
        public HashMap<Date,cuerpoHora> getCuerpoHoraL() {
            return cuerpoHoraL;
        }

        
        public void setCuerpoHoraL(HashMap<Date,cuerpoHora> cuerpoHoraL) {
            this.cuerpoHoraL = cuerpoHoraL;
        }

        
        public cursosC getCurso() {
            return curso;
        }

        
        public void setCurso(cursosC curso) {
            this.curso = curso;
        }

     
    }
    
    public static class cuerpoHora{
        private Date fechaIngreso;
        private double horaMarcada;
        private double horaProyectada;

      
        public Date getFechaIngreso() {
            return fechaIngreso;
        }

      
        public void setFechaIngreso(Date fechaIngreso) {
            this.fechaIngreso = fechaIngreso;
        }

     
        public double getHoraMarcada() {
            return horaMarcada;
        }

      
        public void setHoraMarcada(double horaMarcada) {
            this.horaMarcada = horaMarcada;
        }

     
        public double getHoraProyectada() {
            return horaProyectada;
        }

   
        public void setHoraProyectada(double horaProyectada) {
            this.horaProyectada = horaProyectada;
        }
        
    }
    
    
    
    public static class pie{
        private String anio;
        private int mes;
        private int cantidad;

     
        public int getMes() {
            return mes;
        }

       
        public void setMes(int mes) {
            this.mes = mes;
        }

        
        public int getCantidad() {
            return cantidad;
        }

       
        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public String getAnio() {
            return anio;
        }

       
        public void setAnio(String anio) {
            this.anio = anio;
        }

       
        
    }
}
