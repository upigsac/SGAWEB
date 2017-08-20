
package Beans;

import DAO.personaDAO;
import ENTIDAD.personaC;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
import org.primefaces.event.SelectEvent;


@ManagedBean(name = "estadoCuentaAlumnoB")
@ViewScoped
public class estadoCuentaControl  {

    private String codigo;
    private String nombres;
    private personaC persona;

    public void onRowSelect(SelectEvent event) {

        nombres = "michell olano";
    }

    personaDAO per;

    public void busqueda() {

        per = new personaDAO();
        persona = per.busquedaAlumnocodigo(1, codigo);
        if (persona != null) {
            nombres = persona.getPaterno() + ' ' + persona.getMaterno() + ' ' + persona.getNombres();
        }

    }

    public void imprimire() throws ClassNotFoundException, SQLException, JRException, IOException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conexion = DriverManager.getConnection("jdbc:sqlserver://192.168.1.249;databaseName=BDUP", "anavarro", "123456");
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletCont = (ServletContext) context.getExternalContext().getContext();

        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        response.setContentType("application/pdf");
        ServletOutputStream out = response.getOutputStream();
        //JasperReport reporte = (JasperReport) JRLoader.loadObject(getServletContext().getRealPath("vacio.jasper")); 

        JasperReport reporte = (JasperReport) JRLoader.loadObject(servletCont.getRealPath("ireport/prueba.jasper"));
      //   Map parametros = new HashMap();
        //   parametros.put("nombre", "sss");

        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, conexion);

        JRExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);

        exporter.exportReport();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public personaC getPersona() {
        return persona;
    }

    public void setPersona(personaC persona) {
        this.persona = persona;
    }

}
