
package Beans;

import DAO.ContenidoDAO;
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


@ManagedBean(name = "subirArchivoB")
@ViewScoped
public class subirArchvio  {

    private Part file;
    private String fileContent;
    private List<ArrayList<String>> directorios;
    private List<ArrayList<String>> ultimoArchivos;

    private String rutaArchivos = "D:\\FTPUSER\\upig.edu.pe\\siguWeb\\mgestordoc\\1\\";
    private String rutaArchivosLinea = "www.upig.edu.pe/siguWeb/mgestordoc/1";

    public List<ArrayList<String>> mostrarCarpetasDocente(int periodo, String docente) {
        conte = new ContenidoDAO();
        return conte.mostrarCarpetasDocente(periodo, docente);
    }

    public List<ArrayList<String>> mostrarArchivoDocenteExamen(int periodo, String docente, String examen) {
        conte = new ContenidoDAO();
        return conte.mostrarArchivoDocenteExamen(periodo, docente, examen);
    }

    public void Descargar(String nombre) throws FileNotFoundException, IOException {

        File archivo = new File(rutaArchivos + "\\" + nombre);

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
    }

    public void descargaExterna(String nombre) throws FileNotFoundException, IOException {

        File archivo = new File("D:\\FTPUSER\\upig.edu.pe\\siguWeb\\mgestordoc\\1\\" + nombre);

        FacesContext ctx = FacesContext.getCurrentInstance();
        FileInputStream fis = new FileInputStream(archivo);
        byte[] bytes = new byte[1000];
        int read = 0;
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

    ContenidoDAO conte;
    boolean bandera = false;

    public void mostrarDocentesArchivos(String alumno, int periodo, String carrera) {

        conte = new ContenidoDAO();
        //2009020176
        directorios = conte.gestorContenidoCursos(alumno, periodo, carrera);

    }

    public List<ArrayList<String>> archivosRecientes(int periodo, String carrera, String alumno) {

        conte = new ContenidoDAO();
        ultimoArchivos = conte.mostrarUltimosArchivos(periodo, carrera, alumno);
        return ultimoArchivos;
    }

    public void buscar() {

        if (bandera == true) {

            File dir = new File(rutaArchivos);

            //si la ruta es un directorio
            File[] ficheros = dir.listFiles();
            //util.consolaCliente( "este es un direc");

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a");
            directorios = new ArrayList<>();
            if (ficheros == null) {
                System.out.println("No hay ficheros en el directorio");
            } else {

                for (int x = 0; x < ficheros.length; x++) {
                    ArrayList<String> array = new ArrayList<>();

                    if (!(ficheros[x].getName().contains(".tmb"))) {
                        array.add(ficheros[x].getName());   //nombre del archivo para descargar
                        array.add(ficheros[x].getName());   //nombre para mostrar
                        array.add((ficheros[x].isDirectory() ? "carpeta" : "archivo"));
                        array.add(sdf.format(ficheros[x].lastModified()));
                        array.add((ficheros[x].isDirectory() ? "none" : "inline-block"));//aparece un boton descargar si no es un folder
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
            if (filtro.equals("docx") || filtro.equals(".doc")) {
                return "docx";//"../imagenes/word.png";
            } else if (filtro.equals(".pdf")) {
                return "pdf";//"../imagenes/pdf.png";
            } else if (filtro.equals("xlsx") || filtro.equals(".xls")) {
                return "xlsx"; //"../imagenes/xls.png";
            } else if (filtro.equals(".txt")) {
                return "txt";
            } else if (filtro.equals("pptx") || filtro.equals(".ppt")) {
                return "ppt";//"../imagenes/ppt.png";    
            } else if (filtro.equals(".jpg") || filtro.equals(".png") || filtro.equals(".bmp")) {
                return "imagen";
            } else if (filtro.equals(".rar") || filtro.equals(".zip")) {
                return "zip";//"../imagenes/rar.png"; 
            } else {
                return "folder";//"../imagenes/folder.png";
            }
        } else {
            return "folder";
        }

    }

    public void inicio() {
        util.consolaCliente( "INICIO");

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

    /**
     * @param file the file to set
     */
    public void setFile(Part file) {
        this.file = file;
    }

    /**
     * @return the fileContent
     */
    public String getFileContent() {
        return fileContent;
    }

    /**
     * @param fileContent the fileContent to set
     */
    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    /**
     * @return the directorios
     */
    public List<ArrayList<String>> getDirectorios() {

        return directorios;
    }

    /**
     * @param directorios the directorios to set
     */
    public void setDirectorios(List<ArrayList<String>> directorios) {
        this.directorios = directorios;
    }

    /**
     * @return the rutaArchivos
     */
    public String getRutaArchivos() {
        return rutaArchivos;
    }

    /**
     * @param rutaArchivos the rutaArchivos to set
     */
    public void setRutaArchivos(String rutaArchivos) {
        this.rutaArchivos = rutaArchivos;
    }

    /**
     * @return the rutaArchivosLinea
     */
    public String getRutaArchivosLinea() {
        return rutaArchivosLinea;
    }

    /**
     * @param rutaArchivosLinea the rutaArchivosLinea to set
     */
    public void setRutaArchivosLinea(String rutaArchivosLinea) {
        this.rutaArchivosLinea = rutaArchivosLinea;
    }

    /**
     * @return the ultimoArchivos
     */
    public List<ArrayList<String>> getUltimoArchivos() {
        return ultimoArchivos;
    }

    /**
     * @param ultimoArchivos the ultimoArchivos to set
     */
    public void setUltimoArchivos(List<ArrayList<String>> ultimoArchivos) {
        this.ultimoArchivos = ultimoArchivos;
    }

}
