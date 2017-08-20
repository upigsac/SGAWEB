
package Beans;
import DAO.ContenidoDAOC;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;

@ManagedBean(name = "subirArchivoBB")
@ViewScoped
public class subirArchvioB {

    private Part file;
    private String fileContent;
    private List<ArrayList<String>> directorios;
    private List<ArrayList<String>> ultimoArchivos;

    private String rutaArchivos = "D:\\FTPUSER\\upig.edu.pe\\siguWeb\\mgestordoc\\1\\";
    private String rutaArchivosLinea = "www.upig.edu.pe/siguWeb/mgestordoc/1";

    public void vistaReporte() {
   //     ReportViewer vista=null;

    }

    public subirArchvioB() {
        // buscar();
    }

    public void Descargar(String nombre) throws FileNotFoundException, IOException {

        File archivo = new File(rutaArchivos + "\\" + nombre);

        FacesContext ctx = FacesContext.getCurrentInstance();
        FileInputStream fis = new FileInputStream(archivo);
        byte[] bytes = new byte[1000];
        int read ;
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
    }

    public void descargaExterna(String nombre) throws FileNotFoundException, IOException {

        File archivo = new File("D:\\FTPUSER\\upig.edu.pe\\siguWeb\\mgestordoc\\1\\" + nombre);

        FacesContext ctx = FacesContext.getCurrentInstance();
        FileInputStream fis = new FileInputStream(archivo);
        byte[] bytes = new byte[1000];
        int read ;
        if (!ctx.getResponseComplete()) {
            String fileName = archivo.getName();

            HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();

            response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
            ServletOutputStream out = response.getOutputStream();
            while ((read = fis.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
            ctx.responseComplete();
        }
    }

    ContenidoDAOC conte;
    boolean bandera = false;

    public void cursoAlumno(String alumno, String periodo, String carrera) {

        conte = new ContenidoDAOC();
        directorios = new ArrayList<>();
        
        directorios = conte.gestorContenidoCursos(alumno, periodo, carrera);

    }

    public List<ArrayList<String>> archivosRecientes(int periodo, String carrera, String alumno) {

        conte = new ContenidoDAOC();
        ultimoArchivos = conte.mostrarUltimosArchivos(periodo, carrera, alumno);
        return ultimoArchivos;
    }

    public void buscar() {

        if (bandera == true) {

            //util.consolaCliente( rutaArchivos);
            
            File dir = new File(rutaArchivos);
            //si la ruta es un directorio
            File[] ficheros = dir.listFiles();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a");
            directorios = new ArrayList<>();
            if (ficheros == null) {
                System.out.println("No hay ficheros en el directorio");
            } else {
                for (File fichero : ficheros) {
                    ArrayList<String> array = new ArrayList<>();
                    if (!(fichero.getName().contains(".tmb"))) {
                        array.add(fichero.getName()); //nombre del archivo para descargar
                        array.add(fichero.getName()); //nombre para mostrar
                        array.add(fichero.isDirectory() ? "carpeta" : "archivo");
                        array.add(sdf.format(fichero.lastModified()));
                        array.add(fichero.isDirectory() ? "none" : "inline-block"); //aparece un boton descargar si no es un folder
                        array.add(""+(fichero.length()/1024)); //aparece un boton descargar si no es un folder
                        directorios.add(array);
                    }
                }

            }

        }
        bandera = true;

    }

    public String Imagen(String archivo) {
        String filtro;

        if (archivo.length() > 5) {

            filtro = archivo.substring(archivo.length() - 4);
            switch (filtro) {
                case "docx":
                case ".doc":
                    return "docx";//"../imagenes/word.png";
                case ".pdf":
                    return "pdf";//"../imagenes/pdf.png";
                case "xlsx":
                case ".xls":
                    return "xlsx"; //"../imagenes/xls.png";
                case ".txt":
                    return "txt";
                case "pptx":
                case ".ppt":
                    return "ppt";//"../imagenes/ppt.png";    
                case ".jpg":
                case ".png":
                case ".bmp":
                    return "imagen";
                case ".rar":
                case ".zip":
                    return "zip";//"../imagenes/rar.png"; 
                default:
                    return "folder";//"../imagenes/folder.png";
            }
        } else {
            return "folder";
        }

    }
    public void verOnline(String ruta) throws IOException{
        String ext = FilenameUtils.getExtension(ruta);
        switch (ext){
            case "mp4":case "webm":
                util.consolaCliente("reproductor html5");
                break;
            case "flv":case "mkv":case "avi":case "3gp":
                util.consolaCliente("reproductor flash");
                break;
            case "doc":case "docx":case"xls":case"xlsx":case "pptx":case "ppt":
                util.consolaCliente("ofice ");
                break;
           case "jpg":case "png":case"bmp":case"gif":case "ico":
                util.consolaCliente("imagen");
                break;     
             
                
        }
       
        util.redirigir("http:\\www.google.com.pe");
        //return "http://docs.google.com/viewer?url="+rutaArchivosLinea+"/"+ruta+"&amp;embedded=true";
    }
  

    public void upload() {

        try {

       //    util.consolaCliente( "entre:" + getFileContent());
            //fileContent = new Scanner(file.getInputStream())
            //    .useDelimiter("\\A").next();    
            file.write("D:\\subidos\\" + fileContent.replaceAll("C:\\\\fakepath\\\\", ""));

            FacesMessage ms = new FacesMessage("ARCHIVO SUBIDO ..");
            FacesContext.getCurrentInstance().addMessage(null, ms);
            // buscar("");
        } catch (IOException e) {
          //util.consolaCliente( e.getMessage());
            // Error handling
        }
    }

    public Part getFile() {
        return file;
    }
    public void setFile(Part file) {
        this.file = file;
    }
    public String getFileContent() {
        return fileContent;
    }
    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }
    public List<ArrayList<String>> getDirectorios() {
        return directorios;
    }
    public void setDirectorios(List<ArrayList<String>> directorios) {
        this.directorios = directorios;
    }
    public String getRutaArchivos() {
        return rutaArchivos;
    }
    public void setRutaArchivos(String rutaArchivos) {
        this.rutaArchivos = rutaArchivos;
    }
    public String getRutaArchivosLinea() {
        return rutaArchivosLinea;
    }
    public void setRutaArchivosLinea(String rutaArchivosLinea) {
        this.rutaArchivosLinea = rutaArchivosLinea;
    }
    public List<ArrayList<String>> getUltimoArchivos() {
        return ultimoArchivos;
    }
    public void setUltimoArchivos(List<ArrayList<String>> ultimoArchivos) {
        this.ultimoArchivos = ultimoArchivos;
    }

}
