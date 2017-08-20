
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
         path=FacesContext.getCurrentInstance().getExternalContext().getRealPath("/").replace("\\build","") + "\\Alumno\\academico\\reporte\\";
         
         if(request.getParameter("reporte").equals("EVALPER")){
         reportSource = session.getAttribute("reportSource");
         
	//if the report source has not been opened
//	if (reportSource == null)
//	{
		//you will need to modify report variable to point to your report
		 report = path+"EVALPER.RPT";
    
		rptSrcFactory = new JPEReportSourceFactory();
		reportSource = rptSrcFactory.createReportSource(report,request.getLocale());
		session.setAttribute("reportSource", reportSource);
//	}
  
	//---------- Create the Parameter Field Objects -------------
	//Create a Fields collection object to store the parameter fields in.
	

	//Create a ParameterField object for each field that you wish to set.
        fields1 = new Fields();
        fields1.add( creaParametro("@LSALUMNO", request.getParameter("CODIGO"))) ;
        fields1.add( creaParametro("@LSPERIOD", request.getParameter("formReporte:cboPeriodo")) );
        
                
	// Create the ReportExportControl that will export the report
             exportControl = new ReportExportControl();

	//Set ExportOptions - export format type (RTF or PDF)
             exportOptions = new ExportOptions();
            //exportOptions.setExportFormatType(ReportExportFormat.PDF); 
             
             if(request.getParameter("formato").equals("2")){
              exportOptions.setExportFormatType(ReportExportFormat.MSExcel); 
             }
              else if(request.getParameter("formato").equals("3")){
                     exportOptions.setExportFormatType(ReportExportFormat.MSWord); 
              }
              else{
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
         
         
         }else if(request.getParameter("reporte").equals("apyr0004a")){
    
    
         reportSource = session.getAttribute("reportSource");
          
	//if the report source has not been opened
//	if (reportSource == null)
//	{
		//you will need to modify report variable to point to your report
		report = path+"apyr0004a.rpt";
    
		rptSrcFactory = new JPEReportSourceFactory();
		reportSource = rptSrcFactory.createReportSource(report,request.getLocale());
		session.setAttribute("reportSource", reportSource);
//	}
  
	//---------- Create the Parameter Field Objects -------------
	//Create a Fields collection object to store the parameter fields in.
	

	//Create a ParameterField object for each field that you wish to set.
        fields1 = new Fields();
        //fields1.add( creaParametro("@LITIPSQL", "1")) ;
        //fields1.add( creaParametro("@LIINSTIT", "1") );
        fields1.add( creaParametro("@LSPERIOD", "%"));
        //fields1.add( creaParametro("@LSFACULT", "%") );
        //fields1.add( creaParametro("@LSCARRER", "%")) ;
        //fields1.add( creaParametro("@LSMALLAS", "%") );
        //fields1.add( creaParametro("@LSCURSOS", "%")) ;
        //fields1.add( creaParametro("@LSSECCIO", "%") );
        ///fields1.add( creaParametro("@LSGRUPOS", "%")) ;
        //fields1.add( creaParametro("@LSPERSON", "%") );
        //fields1.add( creaParametro("@LSPERNAL", "%"));
        fields1.add( creaParametro("@LSALUMNO", request.getParameter("alumno")) );
        //fields1.add( creaParametro("@LSTIPEXA", "%") );
        //fields1.add( creaParametro("@LSESTADO", "%") );
        //fields1.add( creaParametro("@LSPERFIN", request.getParameter("frmReporte:cboPeriodoFin")));
        
        
        
        
        
	// Create the ReportExportControl that will export the report
             exportControl = new ReportExportControl();

	//Set ExportOptions - export format type (RTF or PDF)
            exportOptions = new ExportOptions();
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
        
   
        
        
        
        
        
   session.setAttribute("refreshed", "true");
    
        
        
    exportControl.processHttpRequest(request, response, getServletConfig().getServletContext(), null);
   
         }
         
    else if(request.getParameter("reporte").equals("boletaNota")){
             reportSource = session.getAttribute("reportSource");
         
	//if the report source has not been opened
//	if (reportSource == null)
//	{
		//you will need to modify report variable to point to your report
		report = path+"boletaNota.RPT";
    
		rptSrcFactory = new JPEReportSourceFactory();
		reportSource = rptSrcFactory.createReportSource(report,request.getLocale());
		session.setAttribute("reportSource", reportSource);
//	}
  
	//---------- Create the Parameter Field Objects -------------
	//Create a Fields collection object to store the parameter fields in.
	

	//Create a ParameterField object for each field that you wish to set.
        fields1 = new Fields();
        fields1.add( creaParametro("@LITIPSQL", "7")) ;
        fields1.add( creaParametro("@LIINSTIT", "1") );
        fields1.add( creaParametro("@LSPERIOD", request.getParameter("formReporte:cboPeriodo")) );
        fields1.add( creaParametro("@LSFACULT", "%") );
        fields1.add( creaParametro("@LSCARRER", request.getParameter("CARRERA")));
        fields1.add( creaParametro("@LSMALLAS", "%") );
        fields1.add( creaParametro("@LSCURSOS", "%") );
        fields1.add( creaParametro("@LSSECCIO", "%") );
        fields1.add( creaParametro("@LSGRUPOS", "%") );
        fields1.add( creaParametro("@LSPERSON", "%") );
        fields1.add( creaParametro("@LSPERNAL", "%") );
        fields1.add( creaParametro("@LSALUMNO", request.getParameter("CODIGO")) );
        fields1.add( creaParametro("@LSTIPEXA", "%") );
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
   
   
      }
    
    
%>
<%--
<%=request.getParameter("frmReporte:cboPeriodoIni")%>
<%=request.getParameter("frmReporte:cboPeriodoFin")%>
<%=request.getParameter("alumno")%>

<%=request.getParameter("formReporte:cboPeriodo")%>
<%=request.getParameter("CODIGO")%>
<%=request.getParameter("CARRERA")%>
<%=request.getParameter("reporte")%>
--%>

    </body>
</html>
