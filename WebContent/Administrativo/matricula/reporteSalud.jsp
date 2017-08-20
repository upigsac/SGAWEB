<%-- 
    Document   : reporte
    Created on : 10-dic-2013, 10:10:54
    Author     : Administrador
--%>

<%@page import="com.crystaldecisions.sdk.occa.report.exportoptions.ReportExportFormat"%>
<%@page import="com.crystaldecisions.sdk.occa.report.exportoptions.ExportOptions"%>
<%@page import="javax.faces.context.FacesContext"%>
<%@page import="com.crystaldecisions.sdk.occa.report.data.ParameterField"%>
<%@page import="java.util.Date,java.text.*,com.crystaldecisions.reports.reportengineinterface.*" %>
<%@page import=" com.crystaldecisions.report.web.viewer.*" %>
<%@page import= "com.crystaldecisions.sdk.occa.report.data.*" %>
<%@page import=" com.crystaldecisions.sdk.occa.report.reportsource.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       
    
        
        <%!       

            ParameterField  creaParametro(String name, Object value){
            ParameterField param = new ParameterField();
            param.setName(name);
            Values vals = new Values();
            ParameterFieldDiscreteValue val = new ParameterFieldDiscreteValue();
            val.setValue(value);
            vals.add(val);
            param.setCurrentValues(vals);
            return param;
        };
        %>
        
        
       
            
        
        
        
  
   

   
   
   
   
   
   
   
   <%   
       
   /*
  IReportSourceFactory2 rptSrcFactory = new JPEReportSourceFactory();    
  CrystalReportViewer viewer= new CrystalReportViewer();
  Fields fields1 = new Fields();
  ConnectionInfos connections = new ConnectionInfos();
  ConnectionInfo connection = new ConnectionInfo();      
  String reporturl = ""; 
  
  
  
  String path=FacesContext.getCurrentInstance().getExternalContext().getRealPath("/").replace("\\build","") + "\\Alumno\\matricula\\reporte\\";
  
  
  
 if(request.getParameter("tipo").equals("apyr0043")) {          
     
          reporturl=path+"apyr0043.rpt";
     
      
  

if (!reporturl.equals("")) {
    
    session.setAttribute("reportSource", null);
   // session.setAttribute("refreshed", null);
   
  }
Object reportSource = session.getAttribute("reportSource");
if (reportSource == null)
{
    
try{
rptSrcFactory = new JPEReportSourceFactory();
reportSource = rptSrcFactory.createReportSource(reporturl, request.getLocale());
session.setAttribute("reportSource", reportSource);
}catch (Exception e) {}
}



viewer= new CrystalReportViewer();
try{
 viewer.setReportSource(reportSource);
}catch (Exception e) {}
viewer.setHasLogo(false);// logo tipo
viewer.setHasToggleParameterPanelButton(false);
viewer.setHasViewList(false);
viewer.setDisplayGroupTree(false);
viewer.setEnableParameterPrompt(false);
viewer.setEnableDrillDown(false);
viewer.setHasToggleGroupTreeButton(true);
//viewer.setPrintMode(CrPrintMode.PDF);



viewer.setOwnPage(true);
viewer.setReuseParameterValuesOnRefresh(false);




fields1 = new Fields();
fields1.add( creaParametro("@LITIPSQL", 8) );
fields1.add( creaParametro("@LIINSTIT",1) );
fields1.add( creaParametro("@LSPERIOD",request.getParameter("formReporte:cboPeriodo")) );
fields1.add( creaParametro("@LSFACULT","%") );
fields1.add( creaParametro("@LSCARRER","%") );
fields1.add( creaParametro("@LSCURSOS","%") );
fields1.add( creaParametro("@LSSECCIO","%") );
fields1.add( creaParametro("@LSGRUPOS","%") );
fields1.add( creaParametro("@LSPERSON","%") );
fields1.add( creaParametro("@LSALUMNO",request.getParameter("codigo")));
fields1.add( creaParametro("@LSNIVELE","%") );
fields1.add( creaParametro("@LSTURNOS","%") );
fields1.add( creaParametro("@LSESTADO",1) );
viewer.setParameterFields(fields1);
connections = new ConnectionInfos();
connection = new ConnectionInfo();
connection.setUserName("ANAVARRO");
connection.setPassword("123456");
connections.add(connection);
viewer.setDatabaseLogonInfos(connections) ;

 


if (session.getAttribute("refreshed") == null)
{
//viewer.refresh();
session.setAttribute("refreshed", "true");
}
//viewer.refresh();
try
{

viewer.processHttpRequest(request, response, getServletConfig().getServletContext(), null);

} 
catch(Exception e)
{
System.out.println("errores  " + e.getMessage());
}

}
     */   

//check to see if the report source already exists
	Object reportSource = session.getAttribute("reportSource");
        String path=FacesContext.getCurrentInstance().getExternalContext().getRealPath("/").replace("\\build","") + "\\Administrativo\\matricula\\reporte\\";
	//if the report source has not been opened
	//if (reportSource == null)
	//{
		//you will need to modify report variable to point to your report
		String report = path + request.getParameter("reporte") + ".rpt";
    
		IReportSourceFactory2 rptSrcFactory = new JPEReportSourceFactory();
		reportSource = rptSrcFactory.createReportSource(report,request.getLocale());
		session.setAttribute("reportSource", reportSource);
	//}
  
	//---------- Create the Parameter Field Objects -------------
	//Create a Fields collection object to store the parameter fields in.
	Fields fields1 = new Fields();

	//Create a ParameterField object for each field that you wish to set.
	

        
        fields1 = new Fields();
        fields1.add( creaParametro("@LITIPSQL", 3) );
        fields1.add( creaParametro("@LIINSTIT",11) );
        fields1.add( creaParametro("@LSPERIOD",request.getParameter("Periodo")) );
        fields1.add( creaParametro("@LSFACULT","%") );
        fields1.add( creaParametro("@LSCARRER","%") );
        fields1.add( creaParametro("@LSCURSOS","%") );
        fields1.add( creaParametro("@LSSECCIO","%") );
        fields1.add( creaParametro("@LSGRUPOS","%") );
        fields1.add( creaParametro("@LSPERSON","%") );
        fields1.add( creaParametro("@LSALUMNO",request.getParameter("codigo")));
        fields1.add( creaParametro("@LSNIVELE","%") );
        fields1.add( creaParametro("@LSTURNOS","%") );
        fields1.add( creaParametro("@LSESTADO",1) );
        
        
        
        
        
	// Create the ReportExportControl that will export the report
	ReportExportControl exportControl = new ReportExportControl();

	//Set ExportOptions - export format type (RTF or PDF)
    ExportOptions exportOptions = new ExportOptions();
    

               exportOptions.setExportFormatType(ReportExportFormat.PDF); 
             
        exportControl.setExportOptions(exportOptions);
	
	//Set the report source for the Report Export Control
	exportControl.setReportSource(reportSource);

	//Set the parameters for the Report Export Control
	exportControl.setParameterFields(fields1);

	//Set Report Export Control Options
        
        
          ConnectionInfos connections = new ConnectionInfos();
        ConnectionInfo connection = new ConnectionInfo(); 
        connections = new ConnectionInfos();
        connection = new ConnectionInfo();
        connection.setUserName("ANAVARRO");
        connection.setPassword("123456");
        connections.add(connection);
                
        exportControl.setDatabaseLogonInfos(connections);
        
       // exportControl.setEnableLogonPrompt(false);
       // exportControl.setExportAsAttachment(true);
	//exportControl.setEnableParameterPrompt(false);
        
        
        
        
         

    //Export the report
    exportControl.processHttpRequest(request, response, getServletConfig().getServletContext(), null);




%>

    </body>
</html>
