package Beans;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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

@ManagedBean(name = "svReporte")
public class SReporte extends HttpServlet {

   
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JRException, ClassNotFoundException, SQLException {
    	
    	String rutaReporte=request.getParameter("reporte").toUpperCase();
    	String param=request.getParameter("param");
    	String paramArray[]=param.split(",");
    	//param
    	
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        Connection conexion = DriverManager.getConnection("jdbc:sqlserver://192.168.1.249;databaseName=BDUP", "sa", "Sql2015");

        response.setContentType("application/pdf");
        ServletOutputStream out = response.getOutputStream();
    
        JasperReport reporte = (JasperReport) JRLoader.loadObject(getServletContext().getRealPath("ireport/"+ rutaReporte+".jasper"));
        Map<String, Object> parametros = new HashMap<String, Object>();
        for (int i = 0; i < paramArray.length; i+=2) {
            parametros.put(paramArray[i], paramArray[i+1].replace("-1", "%"));
            
            System.out.println(paramArray[i] +" "+paramArray[i+1].replace("-1", "%"));
		}
        
        //parametros.put("per", codigo);

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
            	System.out.println("get");
				processRequest(request, response);
				
			} catch (ClassNotFoundException | JRException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
            try {
            	System.out.println("post");
				processRequest(request, response);
			} catch (ClassNotFoundException | JRException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
