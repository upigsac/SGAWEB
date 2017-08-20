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
        <title>Reporte Academico</title>
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
   
        ConnectionInfos connections = new ConnectionInfos();
        ConnectionInfo connection = new ConnectionInfo(); 
   	IReportSourceFactory2 rptSrcFactory = new JPEReportSourceFactory();
        ReportExportControl exportControl = new ReportExportControl();
        ExportOptions exportOptions = new ExportOptions();
        Object reportSource;
         Fields fields1 = new Fields();
         String path,report;
         path=FacesContext.getCurrentInstance().getExternalContext().getRealPath("/").replace("\\build","") + "\\Administrativo\\academico\\reporte\\";
         
         reportSource = session.getAttribute("reportSource");
         

		//you will need to modify report variable to point to your report
		 report = path+"SGAWEB0031.rpt";
    
		rptSrcFactory = new JPEReportSourceFactory();
		reportSource = rptSrcFactory.createReportSource(report,request.getLocale());
		session.setAttribute("reportSource", reportSource);


	//Create a ParameterField object for each field that you wish to set.
        fields1 = new Fields();
        fields1.add( creaParametro("@LITIPSQL", "1")) ;
        fields1.add( creaParametro("@LSINSTIT", "1") );
        fields1.add( creaParametro("@LSPERIOD", "20142") );
        fields1.add( creaParametro("@LSCARRER", "%") );
        fields1.add( creaParametro("@LSCURSOS", "%") );
        fields1.add( creaParametro("@LSSECCIO", "%") );
        fields1.add( creaParametro("@LSNIVELE", "%") );
        fields1.add( creaParametro("@LSTURNOS", "%") );
        fields1.add( creaParametro("@LSPARAM1", "%") );
        fields1.add( creaParametro("@LSPARAM2", "%") );
        fields1.add( creaParametro("@LSPARAM3", "%") );
        fields1.add( creaParametro("@LSESTADO", "%") );
        
        
        
        
        
        
	// Create the ReportExportControl that will export the report
             exportControl = new ReportExportControl();

	//Set ExportOptions - export format type (RTF or PDF)
             exportOptions = new ExportOptions();
            //exportOptions.setExportFormatType(ReportExportFormat.PDF); 
             
             
               exportOptions.setExportFormatType(ReportExportFormat.PDF); 
            
                  
            
            
            exportControl.setExportOptions(exportOptions);
	
	//Set the report source for the Report Export Control
            exportControl.setReportSource(reportSource);

	//Set the parameters for the Report Export Control
        
   
            exportControl.setParameterFields(fields1);

	//Set Report Export Control Options
            connections = new ConnectionInfos();
            connection = new ConnectionInfo();
            connection.setUserName("ANAVARRO");
            connection.setPassword("123456");
            connections.add(connection);

             exportControl.setDatabaseLogonInfos(connections);
        
       // exportControl.setEnableLogonPrompt(false);
       // exportControl.setExportAsAttachment(true);
	//exportControl.setEnableParameterPrompt(false);
            session.setAttribute("refreshed", "true");



           exportControl.processHttpRequest(request, response, getServletConfig().getServletContext(), null);

   
 //---------------------------------------------------------------------------------------------------  
         
         
         
    
         
          
%>


    </body>
</html>
