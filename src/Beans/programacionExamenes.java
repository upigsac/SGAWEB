
package Beans;


import DAO.rolExamenDAO;

import ENTIDAD.rolExamenC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


@ManagedBean(name = "programacionExamenesB")
@ViewScoped
public class programacionExamenes implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<rolExamenC> examenesL1=new ArrayList<>();
    private List<rolExamenC> examenesL2=new ArrayList<>();
    private List<rolExamenC> examenesL3=new ArrayList<>();
    private List<rolExamenC> examenesL4=new ArrayList<>();
    private List<rolExamenC> examenesL5=new ArrayList<>();


	
	
	
	public void load(int institucion,String periodo,String carrera,int vencimiento ){
		
		
		 examenesL1 = new rolExamenDAO().mostrarRolExamen(institucion, periodo, carrera, vencimiento, "practica1");
		 examenesL2 = new rolExamenDAO().mostrarRolExamen(institucion, periodo, carrera, vencimiento, "practica2");
		 examenesL3 = new rolExamenDAO().mostrarRolExamen(institucion, periodo, carrera, vencimiento, "examen_parcial");
		 examenesL4 = new rolExamenDAO().mostrarRolExamen(institucion, periodo, carrera, vencimiento, "examen_final");
		 examenesL5 = new rolExamenDAO().mostrarRolExamen(institucion, periodo, carrera, vencimiento, "sustitutorios");
		 
	
	}

    public void descargar(String ruta) {

        try {
            //util.consolaCliente( ruta);
            File archivo = new File("D:\\" + ruta);
            FacesContext ctx = FacesContext.getCurrentInstance();
            FileInputStream fis = new FileInputStream(archivo);
            byte[] bytes = new byte[1000];
            int read = 0;
            if (!ctx.getResponseComplete()) {
                String fileName = archivo.getName();
    //String contentType = "application/vnd.ms-excel";
                //String contentType = "application/pdf";
                HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
                // response.setContentType(contentType);
                response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
                ServletOutputStream out = response.getOutputStream();
                while ((read = fis.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                out.flush();
                out.close();
                ctx.responseComplete();

            }
        } catch (FileNotFoundException fnf) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ha ocurrido un problema al intentar descargar este archivo", null));
        } catch (IOException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hubo un problema con el archivo", null));
        }

    }

	public List<rolExamenC> getExamenesL1() {
		return examenesL1;
	}

	public void setExamenesL1(List<rolExamenC> examenesL1) {
		this.examenesL1 = examenesL1;
	}

	public List<rolExamenC> getExamenesL2() {
		return examenesL2;
	}

	public void setExamenesL2(List<rolExamenC> examenesL2) {
		this.examenesL2 = examenesL2;
	}

	public List<rolExamenC> getExamenesL3() {
		return examenesL3;
	}

	public void setExamenesL3(List<rolExamenC> examenesL3) {
		this.examenesL3 = examenesL3;
	}

	public List<rolExamenC> getExamenesL4() {
		return examenesL4;
	}

	public void setExamenesL4(List<rolExamenC> examenesL4) {
		this.examenesL4 = examenesL4;
	}

	public List<rolExamenC> getExamenesL5() {
		return examenesL5;
	}

	public void setExamenesL5(List<rolExamenC> examenesL5) {
		this.examenesL5 = examenesL5;
	}

   
   

}
