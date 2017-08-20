/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.cursoSeccionSilabuDAO;
import ENTIDAD.cursoSeccionSilabuC;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name="administrarSilabusB")
@ViewScoped
public class administrarSilabus {
    private List<listaSilabus> detalleSilabusL=new ArrayList<>();
    private listaSilabus itemSilabuS=new listaSilabus();
    
    
     private UploadedFile uploadedFile;
     private String carreraF="%";
     private String seccionF="%";
     private String desPersonaF="";
     private String estadoArchivo="%";
     private DefaultStreamedContent download;
     
        cursoSeccionSilabuDAO cursoSeccionSilabuD;
        
   public void prepDownload(String date) throws Exception {
          System.out.println(date);
        File file = new File(date);
        InputStream input = new FileInputStream(file);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        setDownload(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
}
        
        
        
        
        
        
        
        
        
        
     public void seleccionar(listaSilabus item){
         itemSilabuS=item;
         util.consolaCliente(item.desCurso);
         
         
     }
    
     
     
    public void save(String personal) throws IOException  {

        
        String ruta="D:\\FTPUSER\\upig.edu.pe\\silabus\\"+itemSilabuS.periodo +"\\"+itemSilabuS.carrera+"\\"+itemSilabuS.malla+"\\"+itemSilabuS.seccion+"\\"+itemSilabuS.curso;
        util.crearDirectorio(ruta);
        
        
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
    inputStream = uploadedFile.getInputstream(); //leemos el fichero local
    // write the inputStream to a FileOutputStream
    
    
    outputStream = new FileOutputStream(new File(ruta+"\\"+util.charset(uploadedFile.getFileName())));
 
    int read = 0;
    byte[] bytes = new byte[1024];
 
    while ((read = inputStream.read(bytes)) != -1) {
       outputStream.write(bytes, 0, read);
    }
    cursoSeccionSilabuD=new cursoSeccionSilabuDAO();
    cursoSeccionSilabuD.insertar(new cursoSeccionSilabuC(itemSilabuS.institucion, itemSilabuS.periodo, itemSilabuS.carrera, itemSilabuS.malla, itemSilabuS.curso, itemSilabuS.seccion, util.charset(uploadedFile.getFileName()), ruta,FilenameUtils.getExtension(uploadedFile.getFileName()), uploadedFile.getSize(), 1));
    detalleSilabusL=cursoSeccionSilabuD.mostrarDetalleSilabu(itemSilabuS.institucion, itemSilabuS.periodo, carreraF, seccionF,desPersonaF,personal,estadoArchivo);
   } catch (IOException e) {
            System.out.println(e.getMessage());
   } finally {
        if (inputStream != null) {
       inputStream.close();
       
    }
    if (outputStream != null) {
       outputStream.close();
    }
    
   }

}
    public void eliminar(listaSilabus item,String personal){
        File fichero = new File(item.ruta+"\\"+item.silabu);
        if (fichero.delete()){
                cursoSeccionSilabuD=new cursoSeccionSilabuDAO();
                cursoSeccionSilabuD.eliminar(new cursoSeccionSilabuC(item.institucion, item.periodo, item.carrera, item.malla, item.curso, item.seccion, "", "","", 0, 1));
                detalleSilabusL=cursoSeccionSilabuD.mostrarDetalleSilabu(item.institucion, item.periodo, carreraF, seccionF,desPersonaF,personal,estadoArchivo);
                System.out.println("El fichero ha sido borrado satisfactoriamente");
         
        }
       
         else
            System.out.println("El fichero no puede ser borrado");
      }
    
    public void descargar(listaSilabus item) throws FileNotFoundException, IOException{
      File file = new File(item.ruta+"\\"+item.silabu);
      InputStream fis = new FileInputStream(file);
      byte[] buf = new byte[1024];
      int offset = 0;
      int numRead = 0;
      while ((offset < buf.length) && ((numRead = fis.read(buf, offset, buf.length -offset)) >= 0)) 
      {
        offset += numRead;
      }
      fis.close();
      HttpServletResponse response =(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

     response.setContentType("application/octet-stream");
     response.setHeader("Content-Disposition", "attachment;filename="+item.silabu);
     response.getOutputStream().write(buf);
     response.getOutputStream().flush();
     response.getOutputStream().close();
     FacesContext.getCurrentInstance().responseComplete();
    }

   
    
   
     public List<listaSilabus> mostrarSilabus(int institucion,String periodo,String personal){
        cursoSeccionSilabuD=new cursoSeccionSilabuDAO();
        detalleSilabusL=cursoSeccionSilabuD.mostrarDetalleSilabu(institucion, periodo, carreraF, seccionF,desPersonaF,personal,estadoArchivo);
        return detalleSilabusL;
    }
    

    /**
     * @return the detalleSilabusL
     */
    public List<listaSilabus> getDetalleSilabusL() {
        return detalleSilabusL;
    }

    /**
     * @param detalleSilabusL the detalleSilabusL to set
     */
    public void setDetalleSilabusL(List<listaSilabus> detalleSilabusL) {
        this.detalleSilabusL = detalleSilabusL;
    }

    

    /**
     * @return the uploadedFile
     */
    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    /**
     * @param uploadedFile the uploadedFile to set
     */
    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    /**
     * @return the itemSilabuS
     */
    public listaSilabus getItemSilabuS() {
        return itemSilabuS;
    }

    /**
     * @param itemSilabuS the itemSilabuS to set
     */
    public void setItemSilabuS(listaSilabus itemSilabuS) {
        this.itemSilabuS = itemSilabuS;
    }

    /**
     * @return the carreraF
     */
    public String getCarreraF() {
        return carreraF;
    }

    /**
     * @param carreraF the carreraF to set
     */
    public void setCarreraF(String carreraF) {
        this.carreraF = carreraF;
    }

    /**
     * @return the seccionF
     */
    public String getSeccionF() {
        return seccionF;
    }

    /**
     * @param seccionF the seccionF to set
     */
    public void setSeccionF(String seccionF) {
        this.seccionF = seccionF;
    }

    /**
     * @return the desPersonaF
     */
    public String getDesPersonaF() {
        return desPersonaF;
    }

    /**
     * @param desPersonaF the desPersonaF to set
     */
    public void setDesPersonaF(String desPersonaF) {
        this.desPersonaF = desPersonaF;
    }

    /**
     * @return the download
     */
    public DefaultStreamedContent getDownload() {
        return download;
    }

    /**
     * @param download the download to set
     */
    public void setDownload(DefaultStreamedContent download) {
        this.download = download;
    }

    /**
     * @return the estadoArchivo
     */
    public String getEstadoArchivo() {
        return estadoArchivo;
    }

    /**
     * @param estadoArchivo the estadoArchivo to set
     */
    public void setEstadoArchivo(String estadoArchivo) {
        this.estadoArchivo = estadoArchivo;
    }
    
    public static class listaSilabus{
        private int institucion;
        private String periodo;
        private String carrera;
        private String malla;
        private String desCarrera;
        private String seccion;
        private String desSeccion;
        private Date fechaInicio;
        private Date fechaFinal;
        private String curso;
        private String desCurso;
        private String personal;
        private String persona;
        private String desPersona;
        private String silabu;
        private String ruta;
        private String peso;
        private String formato;

        /**
         * @return the institucion
         */
        public int getInstitucion() {
            return institucion;
        }

        /**
         * @param institucion the institucion to set
         */
        public void setInstitucion(int institucion) {
            this.institucion = institucion;
        }

        /**
         * @return the periodo
         */
        public String getPeriodo() {
            return periodo;
        }

        /**
         * @param periodo the periodo to set
         */
        public void setPeriodo(String periodo) {
            this.periodo = periodo;
        }

        /**
         * @return the carrera
         */
        public String getCarrera() {
            return carrera;
        }

        /**
         * @param carrera the carrera to set
         */
        public void setCarrera(String carrera) {
            this.carrera = carrera;
        }

        /**
         * @return the desCarrera
         */
        public String getDesCarrera() {
            return desCarrera;
        }

        /**
         * @param desCarrera the desCarrera to set
         */
        public void setDesCarrera(String desCarrera) {
            this.desCarrera = desCarrera;
        }

        /**
         * @return the seccion
         */
        public String getSeccion() {
            return seccion;
        }

        /**
         * @param seccion the seccion to set
         */
        public void setSeccion(String seccion) {
            this.seccion = seccion;
        }

        /**
         * @return the desSeccion
         */
        public String getDesSeccion() {
            return desSeccion;
        }

        /**
         * @param desSeccion the desSeccion to set
         */
        public void setDesSeccion(String desSeccion) {
            this.desSeccion = desSeccion;
        }

        /**
         * @return the fechaInicio
         */
        public Date getFechaInicio() {
            return fechaInicio;
        }

        /**
         * @param fechaInicio the fechaInicio to set
         */
        public void setFechaInicio(Date fechaInicio) {
            this.fechaInicio = fechaInicio;
        }

        /**
         * @return the fechaFinal
         */
        public Date getFechaFinal() {
            return fechaFinal;
        }

        /**
         * @param fechaFinal the fechaFinal to set
         */
        public void setFechaFinal(Date fechaFinal) {
            this.fechaFinal = fechaFinal;
        }

        /**
         * @return the curso
         */
        public String getCurso() {
            return curso;
        }

        /**
         * @param curso the curso to set
         */
        public void setCurso(String curso) {
            this.curso = curso;
        }

        /**
         * @return the desCurso
         */
        public String getDesCurso() {
            return desCurso;
        }

        /**
         * @param desCurso the desCurso to set
         */
        public void setDesCurso(String desCurso) {
            this.desCurso = desCurso;
        }

        /**
         * @return the personal
         */
        public String getPersonal() {
            return personal;
        }

        /**
         * @param personal the personal to set
         */
        public void setPersonal(String personal) {
            this.personal = personal;
        }

        /**
         * @return the persona
         */
        public String getPersona() {
            return persona;
        }

        /**
         * @param persona the persona to set
         */
        public void setPersona(String persona) {
            this.persona = persona;
        }

        /**
         * @return the desPersona
         */
        public String getDesPersona() {
            return desPersona;
        }

        /**
         * @param desPersona the desPersona to set
         */
        public void setDesPersona(String desPersona) {
            this.desPersona = desPersona;
        }

        /**
         * @return the silabu
         */
        public String getSilabu() {
            return silabu;
        }

        /**
         * @param silabu the silabu to set
         */
        public void setSilabu(String silabu) {
            this.silabu = silabu;
        }

        /**
         * @return the ruta
         */
        public String getRuta() {
            return ruta;
        }

        /**
         * @param ruta the ruta to set
         */
        public void setRuta(String ruta) {
            this.ruta = ruta;
        }

        /**
         * @return the malla
         */
        public String getMalla() {
            return malla;
        }

        /**
         * @param malla the malla to set
         */
        public void setMalla(String malla) {
            this.malla = malla;
        }

        /**
         * @return the formato
         */
        public String getFormato() {
            return formato;
        }

        /**
         * @param formato the formato to set
         */
        public void setFormato(String formato) {
            this.formato = formato;
        }

        /**
         * @return the peso
         */
        public String getPeso() {
            return peso;
        }

        /**
         * @param peso the peso to set
         */
        public void setPeso(String peso) {
            this.peso = peso;
        }
    }
}
