package Beans;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

@ManagedBean(name = "svReporteIreport")
public class SReporteIreport extends HttpServlet {

  
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JRException, ClassNotFoundException, SQLException {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        Connection conexion = DriverManager.getConnection("jdbc:sqlserver://192.168.1.249;databaseName=BDUP", "sa", "Sql2015");
        
        response.setContentType("application/pdf");
        ServletOutputStream out = response.getOutputStream();
        
        String rutaArchivo = request.getParameter("ruta");
        String[] paramArray = request.getParameter("parametros").toString().replace("$", "%").split(",");
        
        
        
        JasperReport reporte = (JasperReport) JRLoader.loadObject(getServletContext().getRealPath("ireport/"+ rutaArchivo +".jasper"));
        Map parametros = new HashMap();
        
          for (int i = 0; i < paramArray.length; i += 2) {   
          //  JOptionPane.showMessageDialog(null,paramArray[i].toString()+" - "+ paramArray[i + 1].toString());    
            parametros.put(paramArray[i].toString(), paramArray[i + 1].toString());
        }
        

        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);

        JRExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);

        exporter.exportReport();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JRException ex) {
            //util.consolaCliente( ex.getMessage());
            Logger.getLogger(SReporteIreport.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SReporteIreport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SReporteIreport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JRException ex) {
            //util.consolaCliente( ex.getMessage());
            Logger.getLogger(SReporteIreport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SReporteIreport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SReporteIreport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
