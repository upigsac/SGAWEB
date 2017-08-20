
package Beans;

import com.crystaldecisions.report.web.viewer.ReportExportControl;
import com.crystaldecisions.reports.reportengineinterface.JPEReportSourceFactory;
import com.crystaldecisions.sdk.occa.report.data.ConnectionInfo;
import com.crystaldecisions.sdk.occa.report.data.ConnectionInfos;
import com.crystaldecisions.sdk.occa.report.data.Fields;
import com.crystaldecisions.sdk.occa.report.data.ParameterField;
import com.crystaldecisions.sdk.occa.report.data.ParameterFieldDiscreteValue;
import com.crystaldecisions.sdk.occa.report.data.Values;
import com.crystaldecisions.sdk.occa.report.exportoptions.ExportOptions;
import com.crystaldecisions.sdk.occa.report.exportoptions.ReportExportFormat;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKExceptionBase;
import com.crystaldecisions.sdk.occa.report.reportsource.IReportSourceFactory2;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;




import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
// ---------------exportar excel

//----------------------
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.context.RequestContext;


@ManagedBean(name = "utilB")
@ViewScoped
public class util implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	
	
	
	

 
	private String ruta;//= "http://intranet.upig.edu.pe/";
    private String url;
    private String rpt;
    
    
    private List<String> listaAbecedario
            = new ArrayList<String>() {
                {
                    add("A");
                    add("B");
                    add("C");
                    add("D");
                    add("E");
                    add("F");
                    add("G");
                    add("H");
                    add("I");
                    add("J");
                }
            };
    
    
    private List<String> listaCiclos
            = new ArrayList<String>() {
                {
                    add("I");
                    add("II");
                    add("III");
                    add("IV");
                    add("V");
                    add("VI");
                    add("VII");
                    add("VIII");
                    add("IX");
                    add("X");
                    add("XI");
                    add("XII");
                }
            };
    public static List<String> listaDiasSemana
            = new ArrayList<String>() {
                {
                    add("DOMINGO");
                    add("LUNES");
                    add("MARTES");
                    add("MIERCOLES");
                    add("JUEVES");
                    add("VIERNES");
                    add("SABADO");
                    add("DOMINGO");
                }
            };
    public List<String> listaDiasSemanaNormal
            = new ArrayList<String>() {
                {
                    add("LUNES");
                    add("MARTES");
                    add("MIERCOLES");
                    add("JUEVES");
                    add("VIERNES");
                    add("SABADO");
                    add("DOMINGO");
                }
            };
            public static List<String> listaDiaMinSemanaNormal
            = new ArrayList<String>() {
                {
                    add("L");
                    add("M");
                    add("MI");
                    add("J");
                    add("V");
                    add("S");
                    add("D");
                }
            };
    public List<String> getListaDiaMinSemanaNormal() {
				return listaDiaMinSemanaNormal;
			}

			public void setListaDiaMinSemanaNormal(List<String> listaDiaMinSemanaNormal) {
				this.listaDiaMinSemanaNormal = listaDiaMinSemanaNormal;
			}


	public static List<String> listaMeses
            = new ArrayList<String>() {
                {
                    add("ENERO");
                    add("FEBRERO");
                    add("MARZO");
                    add("ABRIL");
                    add("MAYO");
                    add("JUNIO");
                    add("JULIO");
                    add("AGOSTO");
                    add("SEPTIEMBRE");
                    add("OCTUBRE");
                    add("NOVIEMBRE");
                    add("DICIEMBRE");
                }
            };

    private boolean bandera;

    
    
    
    
    
    
    
    
   
   
    
    
    public void thumbnail() throws FileNotFoundException, IOException{
        File pdfFile = new File("D://thumbnail//2.pdf");
        RandomAccessFile raf = new RandomAccessFile(pdfFile, "r");
        FileChannel channel = raf.getChannel();
        MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
        PDFFile pdf = new PDFFile(buf);
        PDFPage page = pdf.getPage(0);

        // create the image
        Rectangle rect = new Rectangle(0, 0, (int) page.getBBox().getWidth(),
                                         (int) page.getBBox().getHeight());
        BufferedImage bufferedImage = new BufferedImage(rect.width, rect.height,
                                          BufferedImage.TYPE_INT_RGB);

        Image image = page.getImage(rect.width, rect.height,    // width & height
                                    rect,                       // clip rect
                                    null,                       // null for the ImageObserver
                                    true,                       // fill background with white
                                    true                        // block until drawing is done
        );
        Graphics2D bufImageGraphics = bufferedImage.createGraphics();
        bufImageGraphics.drawImage(image, 0, 0, null);
        ImageIO.write(bufferedImage, "JPG", new File( "D://thumbnail//image.jpg" ));
    }
    
    
    private int suma=0;
    public void sumarcolumna(int columna){
        suma+=columna;        
    }
    
    public int obtenerSumar(){
        int retorno=suma;
        suma=0;
        return retorno;        
    }
    
  
    
    public static String charset(String cadena) throws UnsupportedEncodingException {
    	// UTF-8 -- ISO-8859-1
    	String texto=new String(cadena.getBytes(Charset.defaultCharset()), "ISO-8859-1");
    	
    	
        return texto;
    }
    
    
    
    
     public static String ipPublica() {
          HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
          
      
          
     
          return req.getRemoteAddr();
     }
     
   
     
     
     public static void macAddress() throws UnknownHostException{
  HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
          
          InetAddress addr = InetAddress.getByName(req.getRemoteAddr());  // DOMAIN NAME from IP
          String host = addr.getHostName();
          System.out.println(host);
          
     
        
       }
     
     public static void crearDirectorio(String ruta){
         
        File folder = new File(ruta);        
        folder.mkdirs();
     }
      public String classIcono(String fomato) {
            switch (fomato) {
                case "docx":
                case "doc":
                    return "fa fa-file-word-o";//"../imagenes/word.png";
                case "pdf":
                    return "fa fa-file-pdf-o";//"../imagenes/pdf.png";
                case "xlsx":
                case "xls":
                    return "fa fa-file-excel-o"; //"../imagenes/xls.png";
                case "txt":
                    return "fa fa-file-text-o";
                case "html":
                    return "fa fa-firefox";
                case "pptx":
                case "ppt":
                    return "fa fa-file-powerpoint-o";//"../imagenes/ppt.png";    
                case "jpg":
                case "png":
                case "bmp":
                    return "fa fa-file-image-o";
                case "rar":
                case "zip":
                    return "fa fa-file-archive-o";//"../imagenes/rar.png"; 
                default:
                    return "fa fa-file-o";//"../imagenes/folder.png";
            }
        
    }
    
 public static String navegador(){
       ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String userAgent = externalContext.getRequestHeaderMap().get("User-Agent");
    if(userAgent.contains("MSIE")){ 
        userAgent= "Internet Explorer";
    }
    else if(userAgent.contains("Firefox")){ 
        userAgent= "Firefox";
    }
    else if(userAgent.contains("Chrome")){ 
        userAgent= "Chrome";
    }
    else if(userAgent.contains("Opera")){ 
        userAgent= "Opera";
    }
    else if(userAgent.contains("Safari")){ 
        userAgent= "Safari";
    }
    else {
        userAgent= "NO DEFINIDO";
    }
    return userAgent;
 }   
    


    
    
    public void postProcessXLS(Object document) {
            HSSFWorkbook wb = (HSSFWorkbook) document;
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow header = sheet.getRow(0);
            HSSFCellStyle cellStyle = wb.createCellStyle();
            
            cellStyle.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cellStyle.setFillBackgroundColor(HSSFColor.BLUE_GREY.index);
            
            
            for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
            header.getCell(i).setCellStyle(cellStyle);
            }
}

    
    
    public static void alert(String cadena){
        
         RequestContext.getCurrentInstance().execute("alert('"+ cadena.replaceAll("'", " ") +"')");
         
    }
    
    public static void consolaCliente(String mensaje){
        
         RequestContext.getCurrentInstance().execute("console.log('"+mensaje.replaceAll("'", " ")+"')");
         
    }
     public static void script(String cadena){
        RequestContext.getCurrentInstance().execute(cadena);
    }
     public static int mesAño() {
         java.util.Date fecha = new Date();
         return fecha.getYear();
     }
     
      public static int año() {
          java.util.Date fecha = new Date();
         
         Calendar calendario = Calendar.getInstance();
         calendario.setTime(fecha);
         return calendario.get(Calendar.YEAR);
     }

    public static void enviarCorreo(String destino, String asunto, String correo) throws IOException {
    	
        Properties config = new Properties();
        InputStream entrada = null;
    	
        String ruta=Thread.currentThread().getContextClassLoader().getResource("/").getPath()+"Conexiones/config.properties";
        entrada = new FileInputStream(ruta.replace("%20"," "));
        config.load(entrada);
    	
    	
    	
        Properties propiedades = new Properties();
        Session sesion;
        propiedades.put("mail.smtp.host",config.getProperty("smtp")); // hotmail "smtp.live.com" -- gmail "smtp.gmail.com" ofice368  "smtp.office365.com"
        propiedades.put("mail.smtp.starttls.enable", "true"); //
        propiedades.put("mail.smtp.port", config.getProperty("puerto")); // // hotmail "25" -- gmail "587" ofice365 "587"
        propiedades.put("mail.smtp.mail.sender", config.getProperty("correo"));
        propiedades.put("mail.smtp.password", config.getProperty("passCorreo"));
        propiedades.put("mail.smtp.user", config.getProperty("correo"));
        propiedades.put("mail.smtp.auth", "true");
        sesion = Session.getDefaultInstance(propiedades);                                  //String mensajeCorreo
        try {
        	System.out.println("----1-------");
            MimeMessage mensaje = new MimeMessage(sesion);
            System.out.println("----2-------");
                        mensaje.setFrom(new InternetAddress((String) propiedades.get("mail.smtp.mail.sender")));
            System.out.println("----3-------");
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
            System.out.println("----4-------");
            mensaje.setSubject(asunto);
            System.out.println("----5-------");
            Multipart multipart = new MimeMultipart("related");
            System.out.println("----6-------");
            BodyPart bodyhtml = new MimeBodyPart();
            System.out.println("----7-------");
            bodyhtml.setContent(correo, "text/html");
            System.out.println("----8-------");
            multipart.addBodyPart(bodyhtml);
            System.out.println("----9-------");
            mensaje.setContent(multipart);
            System.out.println("----10-------");
            Transport t = sesion.getTransport("smtp");
            System.out.println("----11-------");
            
            t.connect(config.getProperty("correo"), config.getProperty("passCorreo"));
            
            System.out.println("----12-------");            
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            
            System.out.println("----13-------");
            t.close();
             
             
             System.out.println("Se genero Correctamen");
             
        } catch (MessagingException e) {              
        	System.out.println("Error");
           
        }
    }

    
    
    public void enviarCorreo(String asunto,String texto){
        
        
  
   String para = "maicol_24@hotmail.com";

    String de = "sistemas.upig@upig.edu.pe";

    // El servidor (host). En este caso usamos localhost
    String host = "smtp.office365.com";
    String puerto = "587";
    String usuario = "sistemas.upig@upig.edu.pe";
    String clave = "Sistemas2010*";

    // Obtenemos las propiedades del sistema
    Properties propiedades = new Properties();

    // Configuramos el servidor de correo
   
    propiedades.setProperty("mail.smtp.host", host);// hotmail "smtp.live.com" -- gmail "smtp.gmail.com" ofice368  "smtp.office365.com"
    propiedades.setProperty("mail.smtp.starttls.enable", "true"); //     
    propiedades.setProperty("mail.smtp.port", puerto); // // hotmail "25" -- gmail "587" ofice365 "587"    
    propiedades.setProperty("mail.smtp.password", clave);
    propiedades.setProperty("mail.smtp.mail.sender", usuario);//correo de upig gmail o yahoo
    propiedades.setProperty("mail.smtp.user", usuario);
    propiedades.setProperty("mail.smtp.auth", "true");
 
    // Obtenemos la sesiÃ³n por defecto
    Session sesion = Session.getDefaultInstance(propiedades);
    

    try{
      // Creamos un objeto mensaje tipo MimeMessage por defecto.
      MimeMessage mensaje = new MimeMessage(sesion);

    
      mensaje.setFrom(new InternetAddress(de));
      

     
      mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
      
      // copia oculta
  
       
    //  mensaje.addRecipient(Message.RecipientType.BCC, new InternetAddress("jgutierrez@upig.edu.pe"));
   
      
      
////      Address[] addresses=new Address[personaProductoL.size()] ;
////      
////        for (int x=0;x<= personaProductoL.size()-1;x++) {
////            
////            addresses[x] =new InternetAddress(personaProductoL.get(x).correo);
////        }
// 
//      
//      mensaje.addRecipients(Message.RecipientType.TO, addresses);

      // Asignamos el asunto
      mensaje.setSubject(asunto);

      // Asignamos el mensaje como tal
      //mensaje.setText(texto);
      
      // MENSAJE CON CONTENIDO HTML
      mensaje.setContent(texto, "text/html");
      
      
      
      // Enviamos el correo
      //Transport.send(mensaje);
          Transport t = sesion.getTransport("smtp");
          t.connect((String) propiedades.get("mail.smtp.user"), (String) propiedades.get("mail.smtp.password"));
          t.sendMessage(mensaje, mensaje.getAllRecipients());
          t.close();
      
      
      System.out.println("Mensaje enviado");
      
      
      
      util.script("dlgCorreo.hide()");
      
    } catch (MessagingException e) {
      System.out.println("error: " + e.getMessage());
      
    }
    
    }
    public static String convertDateTime(Date strFecha) {
        String s = "";
        if (strFecha != null) {
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            s = formatter.format(strFecha);

        }

        return s;
    }

    public static Date dateAdd(String fecha , int minutos) throws ParseException {
        Calendar c = Calendar.getInstance();
        if (!fecha.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            c = Calendar.getInstance();
            c.setTime(sdf.parse(fecha));
            
            c.add(Calendar.MINUTE, minutos);
           // fecha = sdf.format(c.getTime());
        }

        return c.getTime();
    }    
    
     
      public static Date dateAdd(Date fecha ,int opt, int intervalo)  {
          /*
         util.consolaCliente("minute :"+ Calendar.MINUTE); //12
         util.consolaCliente("month "+ Calendar.MONTH); // 2
         util.consolaCliente("DAY_OF_MONTH"+ Calendar.DAY_OF_MONTH); //5
         util.consolaCliente(" year"+ Calendar.YEAR); // 1
          
          */
           util.consolaCliente(""+Calendar.MONDAY);
        Calendar c = Calendar.getInstance();                  
        c.setTime(fecha);                
        
        c.add(opt, intervalo);
        return c.getTime();
    }

    public  long dateIff(String fechaInicio, String fechaFin) {

        Date d1 ;
        Date d2 ;
        long diff = 0;
        try {
            if (!(fechaInicio.isEmpty() && fechaFin.isEmpty())) {
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                d1 = format.parse(fechaInicio);
                d2 = format.parse(fechaFin);

                //in milliseconds
                diff = d2.getTime() - d1.getTime();

			//long diffSeconds = diff / 1000 % 60;
                //long diffMinutes = diff / (60 * 1000) % 60;
                //long diffHours = diff / (60 * 60 * 1000) % 24;
                //long diffDays = diff / (24 * 60 * 60 * 1000);
                //  util.consolaCliente( diff);
                //  util.consolaCliente( fechaInicio);
                //   util.consolaCliente( fechaFin);
                //  util.consolaCliente( diffMinutes);
                //  util.consolaCliente( diff /60000);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return diff;
    }
    
    
    public void eliminarSesiones(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    public void cerrarSeccion() throws IOException{
    	          
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml");
    }
    
    
    public static long stringIff(String fechaInicio, String fechaFin,int intervalo) {      
        Date d1 ;
        Date d2 ;
        long diff = 9;
        try {
            if (!(fechaInicio.isEmpty() && fechaFin.isEmpty())) {
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                d1 = format.parse(fechaInicio);
                d2 = format.parse(fechaFin);

                //in milliseconds
                diff = d2.getTime() - d1.getTime();
                switch (intervalo){
                    case 1: diff = diff / (60 * 1000) ;   break; // MINUTOS
                    case 2: diff = diff / (60 * 60 * 1000) % 24; break; // HORAS
                    case 3: diff = diff / (24 * 60 * 60 * 1000); break; // DIAS
                    case 4: diff = (diff / (24 * 60 * 60 * 1000))/7; break; // SEMANA        
               }		
            }

        } catch (ParseException e) {            
            
            consolaCliente("error dateiff String" + e.getMessage());
        }

        return diff;
    }
    
    
    
    
    
     public static long dateIff(Date fechaInicio, Date fechaFin,int intervalo) {

        
        long diff = 0;
        
        try{
          
               diff = fechaFin.getTime() - fechaInicio.getTime();
               
               switch (intervalo){
                   case 0: diff=diff / 1000 ;break ;// segundos
                    case 1: diff = diff / (60 * 1000) ; break; // MINUTOS
                    case 2: diff = diff / (60 * 60 * 1000) % 24; break; // HORAS
                    case 3: diff = diff / (24 * 60 * 60 * 1000); break; // DIAS
                    case 4: diff = (diff / (24 * 60 * 60 * 1000))/7; break; // SEMANA        
               }
        }catch(Exception e){            
            
            System.out.println(e.getMessage());
        }
        return diff;
    }
     
     
    

   
    public formatos getTipoFormarto() {
        return tipoFormarto;
    }

   
    public void setTipoFormarto(formatos tipoFormarto) {
        this.tipoFormarto = tipoFormarto;
    }

   
    public List<String> getListaDiasSemanaNormal() {
        return listaDiasSemanaNormal;
    }

   
    public void setListaDiasSemanaNormal(List<String> listaDiasSemanaNormal) {
        this.listaDiasSemanaNormal = listaDiasSemanaNormal;
    }

  
    public boolean isBandera() {
        return bandera;
    }

    
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

  
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

  
    public String getRpt() {
        return rpt;
    }

    
    public void setRpt(String rpt) {
        this.rpt = rpt;
    }
    public List<String> getListaAbecedario() {
        return listaAbecedario;
    }
    public void setListaAbecedario(List<String> listaAbecedario) {
        this.listaAbecedario = listaAbecedario;
    }

    public enum formatos {

        excel, pdf, word
    }

    private formatos tipoFormarto;

    public static Date fechaHoy() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
        formateador.format(ahora);
        return ahora;
    }
    
    public static Date HoraHoy() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
        formateador.format(ahora);
        return ahora;
    }
    public static Date FechaHoraHoy() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        formateador.format(ahora);
        return ahora;
    }

   
    
       public static String convertDate(Date strFecha) {
        String s = "";
        if (strFecha != null) {
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            
            s = formatter.format(strFecha);
        }

        return s;
    }
       
        public static Date convertDate(String strFecha) {

        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(strFecha);
            
        } catch (Exception e) {
        	System.out.println(e.getMessage());
           
        }
        return fecha;
    }
        
    
        public Date convertTime(String strFecha,String formato) {

        SimpleDateFormat formatoDelTexto = new SimpleDateFormat(formato);
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(strFecha);
        } catch (Exception e) {
            //util.consolaCliente( e.getMessage());
        }
        return fecha;
    }


 

    public static  String  convertDate(Date strFecha, String formato) {
        String s = "";
        if (strFecha != null) {            
            DateFormat formatter = new SimpleDateFormat(formato);
            s = formatter.format(strFecha);
        }
       
        	
        return s.isEmpty()?null:s;
    }
    public static  String  convertDateString(Date strFecha, String formato) {
        String s = "";
        
        if (strFecha != null) {            
            DateFormat formatter = new SimpleDateFormat(formato);
            s = formatter.format(strFecha);
        }
       
        	
        return s.isEmpty()?null:s;
    }
    
    public static Date convertDate(String strFecha,String formato) {

        SimpleDateFormat formatoDelTexto = new SimpleDateFormat(formato);
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(strFecha);
            
        } catch (Exception e) {
            
            System.out.println("error: " + e.getMessage());
           
        }
        return fecha;
    }

  

    public static String convertTimes(Date strFecha) {
        String s = "";
        if (strFecha != null) {
            DateFormat formatter = new SimpleDateFormat("HH:mm");
            s = formatter.format(strFecha);

        }

        return s;
    }

    public int diaSemanaHoy() {
        java.util.Date fecha = new Date();
        return fecha.getDay();
    }
   

    public int diaSemanaHoy(int ano, String mes, String dia) {
        try {
            java.util.Date fecha = new Date(ano, Integer.parseInt(mes) - 1, Integer.parseInt(dia) - 1);

            return fecha.getDay();
        } catch (NumberFormatException nfe) {
            return 7;
        }

    }
  

    public double redondeo(double numero) {
        double num;

        num = (Math.round(numero));

        return num;
    }
    
    public double redondeoDecimal(double numero) {
        double num;
        num=Math.rint(numero * 100) / 100;
        return num;
    }
 public static int datePart(int formato,Date fecha) {

        
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);

        //util.consolaCliente( fecha);
        return calendario.get(Calendar.DAY_OF_WEEK );
    }
    public int mes() {

        java.util.Date fecha = new Date();        
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);

        //util.consolaCliente( fecha);
        return calendario.get(Calendar.MONTH);
    }

    public int mesSiguiente(int meses) {
        if (meses == 11) {
            meses = 0;
        } else {
            meses++;
        }

        return meses;

    }

    public int parseInt(String cadena) {
        return Integer.parseInt(cadena);
    }

    public int mesAnterior(int meses) {
        if (meses == 0) {
            meses = 11;
        } else {
            meses--;
        }

        return meses;

    }

    public int diaMensualHoy() {
        java.util.Date fecha = new Date();
        
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        return calendario.get(Calendar.DAY_OF_MONTH);
    }

    public static int mesHoy() {
        java.util.Date fecha = new Date();
        return fecha.getMonth();
    }

   

    public String obtenerCiclo(String ciclo) {
        switch (ciclo) {
            case "1":
                return "I";
            case "2":
                return "II";
            case "3":
                return "III";
            case "4":
                return "IV";
            case "5":
                return "V";
            case "6":
                return "VI";
            case "7":
                return "VII";
            case "8":
                return "VIII";
            case "9":
                return "IX";

            default:
                return "X";
        }

    }

    public ParameterField creaParametro(String name, Object value) {
        ParameterField param = new ParameterField();
        param.setName(name);
        Values vals = new Values();
        ParameterFieldDiscreteValue val = new ParameterFieldDiscreteValue();
        val.setValue(value);
        vals.add(val);
        param.setCurrentValues(vals);
        return param;
    }

    public void imprimir_crystal(String ruta, String reporte, int formato, String[] parametros) throws ReportSDKExceptionBase {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);

        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        ServletContext servletCont = (ServletContext) context.getExternalContext().getContext();

        ConnectionInfos connections;
        ConnectionInfo connection;
        IReportSourceFactory2 rptSrcFactory;
        ReportExportControl exportControl;
        ExportOptions exportOptions;
        Object reportSource;
        Fields fields1;
        String path, report;
        path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/").replace("\\build", "") + ruta;
        reportSource = session.getAttribute("reportSource");
	//if the report source has not been opened
        //if (reportSource == null)
        //{
        //you will need to modify report variable to point to your report
        report = path + reporte + ".rpt";
        rptSrcFactory = new JPEReportSourceFactory();
        reportSource = rptSrcFactory.createReportSource(report, request.getLocale());
        session.setAttribute("reportSource", reportSource);
	//}

	//---------- Create the Parameter Field Objects -------------
        //Create a Fields collection object to store the parameter fields in.
        //Create a ParameterField object for each field that you wish to set.
        fields1 = new Fields();
        for (int i = 0; i < parametros.length; i += 2) {
           // JOptionPane.showMessageDialog(null,parametros[i].toString()+" - "+ parametros[i+1].toString()); 
            fields1.add(creaParametro(parametros[i].toString(), parametros[i + 1].toString()));
        }
        // Create the ReportExportControl that will export the report
        exportControl = new ReportExportControl();

        //Set ExportOptions - export format type (RTF or PDF)
        exportOptions = new ExportOptions();
            //exportOptions.setExportFormatType(ReportExportFormat.PDF); 

        if (formato == 1) {
            exportOptions.setExportFormatType(ReportExportFormat.MSExcel);

        } else if (formato == 2) {
            exportOptions.setExportFormatType(ReportExportFormat.MSWord);
        } else {
            exportOptions.setExportFormatType(ReportExportFormat.PDF);

        }
        exportControl.setExportOptions(exportOptions);
        //Set the report source for the Report Export Control
        exportControl.setReportSource(reportSource);
        //Set the parameters for the Report Export Control
        exportControl.setParameterFields(fields1);

        //Set Report Export Control Options
        connections = new ConnectionInfos();
        connection = new ConnectionInfo();
        connection.setUserName("sa");
        connection.setPassword("Sql2015");
        connections.add(connection);
                
       
        exportControl.setDatabaseLogonInfos(connections);

       // exportControl.setEnableLogonPrompt(false);
        // exportControl.setExportAsAttachment(true);
        //exportControl.setEnableParameterPrompt(false);
        session.setAttribute("refreshed", "true");
        //exportControl.processHttpRequest(request, response, getServletConfig().getServletContext(), null);
        exportControl.processHttpRequest(request, response, servletCont, null);

    }
    public static void redirigir(String url){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(util.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static boolean isPostBack(){
        return FacesContext.getCurrentInstance().isPostback();
    }
    
    public static void calcularTiempo(int diferencia){
        int horas;
        int minutos;
        
        horas = (diferencia / 3600);
        diferencia = diferencia - (3600 * horas);
        minutos = (diferencia / 60);
    }

    public void imprimirIreports(String nombreReporte, int tipo, String[] paramArray) throws ClassNotFoundException, SQLException, JRException, IOException {        
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conexion= DriverManager.getConnection("jdbc:sqlserver://172.16.1.249;databaseName=BDUP", "sa", "Sql2015");
           
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletCont = (ServletContext) context.getExternalContext().getContext();

        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

        //response.setContentType("application/pdf");
        //response.setHeader("Content-disposition", "attachment; filename=ListadoPDF.xls");
        //response.setHeader("Content-Disposition", "attachment;filename=document.pdf");  // Change from 
        ServletOutputStream out = response.getOutputStream();
        //JasperReport reporte = (JasperReport) JRLoader.loadObject(getServletContext().getRealPath("vacio.jasper")); 

        JasperReport reporte = (JasperReport) JRLoader.loadObject(servletCont.getRealPath("ireport/" + nombreReporte + ".jasper"));
        Map parametros = new HashMap();
        for (int i = 0; i < paramArray.length; i += 2) {   
            
           System.out.println(paramArray[i].toString()+" - "+ paramArray[i + 1].toString());
            parametros.put(paramArray[i].toString(), paramArray[i + 1].toString());
        }

        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);

        switch (tipo) {
            case 1:
                
                response.setContentType("application/pdf");
             //   response.addHeader("Content-Disposition", "attachment;filename=document.pdf");  // Change from 
                JRExporter exporter = new JRPdfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                //exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,  new java.io.File("reporte3PDF.pdf"));
                
                exporter.exportReport();                
                break;
            case 2:
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-disposition", "attachment; filename=ListadoPDF.xls");
                JRXlsExporter exporterexcel = new JRXlsExporter();
                exporterexcel.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporterexcel.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                /*
                exporterexcel.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                exporterexcel.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                exporterexcel.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                exporterexcel.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                */
                exporterexcel.exportReport();                
                break;
            case 3:
                break;
        }
        out.flush();
        out.close();
        FacesContext.getCurrentInstance().responseComplete();
        /*
         JRExporter exporter = new JRPdfExporter();
         exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
         exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
     
        
         JRXlsExporter exporterexcel = new JRXlsExporter();
         exporterexcel.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
         exporterexcel.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
         exporterexcel.exportReport   ();
         */
    }
    public void imprimirIreports(String nombreReporte, int tipo, String[] paramArray,int cn) throws ClassNotFoundException, SQLException, JRException, IOException {        
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conexion ;
        if (cn==0){
            conexion= DriverManager.getConnection("jdbc:sqlserver://172.16.1.249;databaseName=BDUP", "sa", "Sql2015");
        }else{
            conexion= DriverManager.getConnection("jdbc:sqlserver://172.16.1.249;databaseName=BDUPLIBRE", "sa", "Sql2015");
        }        
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletCont = (ServletContext) context.getExternalContext().getContext();

        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

        //response.setContentType("application/pdf");
        //response.setHeader("Content-disposition", "attachment; filename=ListadoPDF.xls");
        //response.setHeader("Content-Disposition", "attachment;filename=document.pdf");  // Change from 
        ServletOutputStream out = response.getOutputStream();
        //JasperReport reporte = (JasperReport) JRLoader.loadObject(getServletContext().getRealPath("vacio.jasper")); 

        JasperReport reporte = (JasperReport) JRLoader.loadObject(servletCont.getRealPath("ireport/" + nombreReporte + ".jasper"));
        Map<String, Object> parametros = new HashMap<String, Object>();
        for (int i = 0; i < paramArray.length; i += 2) {   
//            JOptionPane.showMessageDialog(null,paramArray[i].toString()+" - "+ paramArray[i + 1].toString());           
            parametros.put(paramArray[i].toString(), paramArray[i + 1].toString());
        }

        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);

        switch (tipo) {
            case 1:
                response.setContentType("application/pdf");
                JRExporter exporter = new JRPdfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                //exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,  new java.io.File("reporte3PDF.pdf"));
                
                exporter.exportReport();
                
                break;
            case 2:
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-disposition", "attachment; filename=ListadoPDF.xls");
                JRXlsExporter exporterexcel = new JRXlsExporter();
                exporterexcel.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporterexcel.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                exporterexcel.exportReport();
                break;
            case 3:

                break;
        }
         out.flush();
        out.close();
        FacesContext.getCurrentInstance().responseComplete();
        /*
         JRExporter exporter = new JRPdfExporter();
         exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
         exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
     
        
         JRXlsExporter exporterexcel = new JRXlsExporter();
         exporterexcel.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
         exporterexcel.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
         exporterexcel.exportReport   ();
         */
    }
    public static void render(String objeto){
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(objeto);
    }
    
    
    
    public static String generarNumero(){
        char letra;
        String cadena="";
        for(int i=0;i<5;i++){
            System.out.println((int)(Math.random()*122+1));
            letra=(char)((int)(Math.random()*122+1));
            cadena+=letra;
            
        }
        return cadena;
        
    }
    
    
     public void imprimirIreportsDinamico(String nombreReporte, int tipo, String[] paramArray) throws ClassNotFoundException, SQLException, JRException, IOException {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conexion = DriverManager.getConnection("jdbc:sqlserver://192.168.1.249;databaseName=BDUP", "sa", "Sql2015");
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletCont = (ServletContext) context.getExternalContext().getContext();

        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

        response.setContentType("application/pdf");
        //response.setHeader("Content-disposition", "attachment; filename=ListadoPDF.xls");
       // ServletOutputStream out = response.getOutputStream();
        //JasperReport reporte = (JasperReport) JRLoader.loadObject(getServletContext().getRealPath("vacio.jasper")); 

        JasperReport reporte = (JasperReport) JRLoader.loadObject(servletCont.getRealPath("ireport/" + nombreReporte + ".jasper"));
        Map<String, Object> parametros = new HashMap<String, Object>();
        for (int i = 0; i < paramArray.length; i += 2) {   
            //util.consolaCliente( paramArray[i].toString()+" - "+ paramArray[i + 1].toString());
            parametros.put(paramArray[i].toString(), paramArray[i + 1].toString());
        }

        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
       //jasperPrint.setPageWidth(90000);
        switch (tipo) {
            case 1:
                //JasperExportManager.exportReportToPdfFile(jasperPrint,"d:reporte.pdf");
//                response.setContentType("application/pdf");
//                JRExporter exporter = new JRPdfExporter();
//                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
             //   exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
              //  exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,  new java.io.File("reporte3PDF.pdf"));
                
              //  exporter.exportReport();
                
                
                    JRPrintServiceExporter jrprintServiceExporter = new JRPrintServiceExporter();
                    jrprintServiceExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint );
                    
                    jrprintServiceExporter.exportReport();
                break;
            case 2:
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-disposition", "attachment; filename=ListadoPDF.xls");
                JRXlsExporter exporterexcel = new JRXlsExporter();
                exporterexcel.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
              //  exporterexcel.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                exporterexcel.exportReport();
                break;
            case 3:

                break;
        }

        /*
         JRExporter exporter = new JRPdfExporter();
         exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
         exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
     
        
         JRXlsExporter exporterexcel = new JRXlsExporter();
         exporterexcel.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
         exporterexcel.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
         exporterexcel.exportReport   ();
         */
    }

    public String usuario() {
        String usuarioB = "";

        return usuarioB;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

  
    public List<String> getListaCiclos() {
        return listaCiclos;
    }

   
    public void setListaCiclos(List<String> listaCiclos) {
        this.listaCiclos = listaCiclos;
    }

    public List<String> getListaDiasSemana() {
        return listaDiasSemana;
    }


    public void setListaDiasSemana(List<String> listaDiasSemana) {
        this.listaDiasSemana = listaDiasSemana;
    }

   
    public List<String> getListaMeses() {
        return listaMeses;
    }

   
    public void setListaMeses(List<String> listaMeses) {
        this.listaMeses = listaMeses;
    }

}
