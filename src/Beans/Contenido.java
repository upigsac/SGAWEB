
package Beans;

import DAO.ContenidoDAO;
import DAO.carpetaArchivoDAO;
import DAO.carpetaDAO;
import DAO.carpetaInstitucionDAO;
import DAO.cursoSeccionDAO;

import DAO.periodoDAO;
import DAO.personaDAO;
import DAO.personalDAO;

import DAO.seccionPeriodoDAO;
import ENTIDAD.carpetaArchivoC;
import ENTIDAD.carpetaC;
import ENTIDAD.personaC;
import ENTIDAD.carrerasC;
import ENTIDAD.cursoSeccionC;

import ENTIDAD.personalC;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;




import org.apache.commons.io.FilenameUtils;
import org.primefaces.context.RequestContext;

import org.primefaces.model.UploadedFile;


@ManagedBean(name = "contenidoB")
@ViewScoped
public class Contenido {

    private List<ArrayList<String>> listaDocentes;

    
    private List<ArrayList<String>> listaArchivosDocente;

   @ManagedProperty(value = "#{usuarioB}")
    private usuario usuarioS;
    ContenidoDAO contdao;

    /*=================
     GESTORCONTENID.XHTML
     ===================*/
    
    
    
    personalDAO perdao;
    personaDAO personadao;
    periodoDAO periodoD;
    seccionPeriodoDAO seccionPeriodoD;
    cursoSeccionDAO cursoSeccionD;
    
    carpetaArchivoDAO carpetaArchivoD;
    carpetaInstitucionDAO carpetaInstitucionD;
    carpetaDAO carpetaD;
    
    private List<ArrayList<String>> listaSemanas;
    private String seleccionSemana;
    private personalC PersonalDocente;
    private personaC datosDocente;
    private carpetaArchivoC carpetaArchivo;
    private carpetaC carpeta=new carpetaC();
    private List<carpetaC> carpetaL=new ArrayList<>();
    private carrerasC datosCarrera;
    private cursoSeccionC cursoSeccion;

    

    private String seleccionCarrera;
    private String seleccionCurso;
    private String seleccionSeccion;
    private String seleccionCarpeta = "";
    private String archivo;
    private String archivoSeleccionado;

    private List<ArrayList<String>> directorios;
    private List<ArrayList<String>> archivos;

    private UploadedFile archivoPF ;
    private String nombrePeriodo;
    private boolean bandera;

    private static final int BUFFER_SIZE = 6124;


   


    public void mostrarCarreraDocente(String periodo,String carrera) {
        contdao = new ContenidoDAO();
        listaDocentes = contdao.mostrarArchivos(periodo, carrera);
    }
   

    public List<ArrayList<String>> mostrarArchivosDocente(String docente,String periodo,String carrera) {
        contdao = new ContenidoDAO();

        listaArchivosDocente = contdao.mostrarArchivosDocente(docente, periodo, carrera);
        return listaArchivosDocente;
    }

    public void descargar(String ruta) {
        try {
            File archivo = new File("D:\\FTPUSER\\upig.edu.pe\\siguWeb\\mgestordoc\\1\\" + ruta);
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

        } catch (FileNotFoundException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Este archivo no existe o ha sido eliminado", ""));
        } catch (IOException io) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Hubo un problema con el archivo especificado", ""));
        }

    }

  
   

    

     //de subirArchivo para mostrar carpetas de directorio
   

    public void crearCarpetas(String personal) {

        try {
            bandera=true;
           
            
            
            for(carpetaC item:carpetaL){
              
                File nuevaCarpeta = new File("D:\\FTPUSER\\upig.edu.pe\\siguWeb\\mgestordoc\\1\\" + usuarioS.getPeriodoS() + "\\"
                    + seleccionCarrera + "\\" + seleccionCurso + "\\" + seleccionSeccion + "\\" + personal + "\\" + seleccionSemana + "\\"+ item.getDescripcion() +"\\");
                
                nuevaCarpeta.mkdirs();
            }
            

           

            //seleccionCarpeta= seleccionCarpeta.isEmpty() ? "DIAPOSITIVAS" : seleccionCarpeta;
            seleccionCarpeta=carpeta.getDescripcion();
            buscarArchivosCarpeta2();
        } catch (Exception e) {
            util.consolaCliente( "error " + e.getMessage());
        }

    }

    public List<ArrayList<String>> buscarCarpeta() {

        try {
            perdao = new personalDAO();
            String codigoDocente = usuarioS.getPersonal().getPersonal();

            File dir = new File("D:\\FTPUSER\\upig.edu.pe\\siguWeb\\mgestordoc\\1\\" + usuarioS.getPeriodoS() + "\\"
                    + seleccionCarrera + "\\" + seleccionCurso + "\\" + seleccionSeccion + "\\" + codigoDocente + "\\");

            File carpeta1 = new File("D:\\FTPUSER\\upig.edu.pe\\siguWeb\\mgestordoc\\1\\" + usuarioS.getPeriodoS() + "\\"
                    + seleccionCarrera + "\\" + seleccionCurso + "\\" + seleccionSeccion + "\\" + codigoDocente + "\\DIAPOSITIVAS\\");

            File carpeta2 = new File("D:\\FTPUSER\\upig.edu.pe\\siguWeb\\mgestordoc\\1\\" + usuarioS.getPeriodoS() + "\\"
                    + seleccionCarrera + "\\" + seleccionCurso + "\\" + seleccionSeccion + "\\" + codigoDocente + "\\SEPARATAS\\");

            File carpeta3 = new File("D:\\FTPUSER\\upig.edu.pe\\siguWeb\\mgestordoc\\1\\" + usuarioS.getPeriodoS() + "\\"
                    + seleccionCarrera + "\\" + seleccionCurso + "\\" + seleccionSeccion + "\\" + codigoDocente + "\\PRACTICAS\\");

            File carpeta4 = new File("D:\\FTPUSER\\upig.edu.pe\\siguWeb\\mgestordoc\\1\\" + usuarioS.getPeriodoS() + "\\"
                    + seleccionCarrera + "\\" + seleccionCurso + "\\" + seleccionSeccion + "\\" + codigoDocente + "\\SILABUS\\");

            dir.mkdirs();

            carpeta1.mkdirs();
            carpeta2.mkdirs();
            carpeta3.mkdirs();
            carpeta4.mkdirs();

            File[] ficheros = dir.listFiles();

            setDirectorios(new ArrayList<ArrayList<String>>());
            if (ficheros == null) {
                System.out.println("No hay ficheros en el directorio especificado");
            } else {
                for (File fichero : ficheros) {
                    ArrayList<String> array = new ArrayList<>();
                    array.add(fichero.getName());
                    //array.add((ficheros[x].isDirectory()?"carpeta":"archivo"));
                    //array.add(sdf.format(ficheros[x].lastModified()));
                    getDirectorios().add(array);
                }

            }
        } catch (Exception e) {
            util.consolaCliente( "error " + e.getMessage());
        }

        return getDirectorios();
    }

    public void crearCarpetasSemanas(String periodo,String personal,String carrera,String seccion) {
        try {
            
            
            cursoSeccionD=new cursoSeccionDAO();
            cursoSeccion=cursoSeccionD.mostrarCursoSeccion("%", usuarioS.getPeriodoS(), seleccionCarrera,seleccionCurso , seleccionSeccion);
            
            carpetaD=new carpetaDAO();
            carpetaL=carpetaD.mostrarCarpeta(cursoSeccion.getInstitucion(), periodo);
            if (carpetaL.size()>0){
                carpeta=carpetaL.get(0);
            }
            
            
            seccionPeriodoD=new seccionPeriodoDAO();            
            listaSemanas = seccionPeriodoD.cantSemanas("%", periodo,carrera,seccion);           

            File dir = new File("D:\\FTPUSER\\upig.edu.pe\\siguWeb\\mgestordoc\\1\\" + usuarioS.getPeriodoS() + "\\" + seleccionCarrera + "\\" + seleccionCurso + "\\" + seleccionSeccion + "\\" + personal + "\\");
            dir.mkdirs();
            File semanas;
            for (int i = 0; i < listaSemanas.size(); i++) {
                semanas = new File("D:\\FTPUSER\\upig.edu.pe\\siguWeb\\mgestordoc\\1\\" + usuarioS.getPeriodoS() + "\\" + seleccionCarrera + "\\" + seleccionCurso + "\\" + seleccionSeccion + "\\" + personal + "\\" + listaSemanas.get(i).get(1));
                semanas.mkdirs();
            }

            
        } catch (Exception e) {
            util.consolaCliente( "error " + e.getMessage());
        }

    }

    //========================================================================================================================== 
    public void setseleccionarCarpeta(String descarpeta,carpetaC item) {
        seleccionCarpeta = descarpeta;
        carpeta=item;
        buscarArchivosCarpeta2();
    }

    public void buscarArchivosCarpeta() {

        //crearCarpetasSemanas();
        perdao = new personalDAO();
        String codigoDocente = usuarioS.getPersonal().getPersonal();

        //aqui siempre sera 1 para la insitucion para el caso de UPIG 
        seleccionSemana = "SEMANA";
        
        File dir = new File("D:\\FTPUSER\\upig.edu.pe\\siguWeb\\mgestordoc\\1\\" + usuarioS.getPeriodoS() + "\\"
                + seleccionCarrera + "\\" + seleccionCurso + "\\" + seleccionSeccion + "\\" + codigoDocente + "\\SEMANA\\" + seleccionCarpeta + "\\");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a");

        File[] ficheros = dir.listFiles();
        archivos = new ArrayList<>();

        if (ficheros == null) {
            System.out.println("No hay ficheros en el directorio especificado");
        } else {
            for (File fichero : ficheros) {
                ArrayList<String> array = new ArrayList<>();
                array.add(fichero.getName());
                array.add(sdf.format(fichero.lastModified()));
                array.add(fichero.getAbsolutePath());
                archivos.add(array);
            }

        }

    }

    public void buscarArchivosCarpeta2() {

        perdao = new personalDAO();
        String codigoDocente = usuarioS.getPersonal().getPersonal();
        String ruta = "D:\\FTPUSER\\upig.edu.pe\\siguWeb\\mgestordoc\\1\\" + usuarioS.getPeriodoS() + "\\"
                + seleccionCarrera + "\\" + seleccionCurso + "\\" + seleccionSeccion + "\\" + codigoDocente + "\\" + seleccionSemana + "\\" + seleccionCarpeta + "\\";
        

        File dir = new File(ruta);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a");
        File[] ficheros = dir.listFiles();
        archivos = new ArrayList<>();
        if (ficheros == null) {
            System.out.println("No hay ficheros en el directorio especificado");
        } else {
            for (File fichero : ficheros) {
                ArrayList<String> array = new ArrayList<>();
                array.add(fichero.getName());
                array.add(sdf.format(fichero.lastModified()));
                array.add(fichero.getAbsolutePath());
                array.add(""+(fichero.length()/1024));
                archivos.add(array);
            }

        }

    }

    public void buscarArchivosSemanaCarpeta() {
        perdao = new personalDAO();
        String codigoDocente =usuarioS.getPersonal().getPersonal();

      //  crearCarpetasSemanas(codigoDocente);

      
        //aqui siempre sera 1 para la insitucion para el caso de UPIG 
        File dir = new File("D:\\FTPUSER\\upig.edu.pe\\siguWeb\\mgestordoc\\1\\" + usuarioS.getPeriodoS() + "\\"
                + seleccionCarrera + "\\" + seleccionCurso + "\\" + seleccionSeccion + "\\" + codigoDocente + "\\" + seleccionSemana + "\\" + seleccionCarpeta + "\\");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a");

        File[] ficheros = dir.listFiles();
        archivos = new ArrayList<>();

        if (ficheros == null) {
            System.out.println("No hay ficheros en el directorio especificado");
        } else {
            for (File fichero : ficheros) {
                ArrayList<String> array = new ArrayList<>();
                array.add(fichero.getName());
                array.add(sdf.format(fichero.lastModified()));
                array.add(fichero.getAbsolutePath());
                array.add(""+(fichero.length()/1024));
                archivos.add(array);
            }

        }

    }

    public void subirArchivo(String seccion, String descarpeta) {        
        
        
        carpetaArchivoD=new carpetaArchivoDAO();
        carpetaArchivo=carpetaArchivoD.mostrarCarpetaArchivo(carpeta.getCarpeta(), FilenameUtils.getExtension(archivoPF.getFileName()));
        if (carpetaArchivo==null){
            util.alert("El formato no es el adecuado");
            
        }else{
            perdao = new personalDAO();
        String codigoDocente = usuarioS.getPersonal().getPersonal();
        util.consolaCliente(""+archivoPF.getSize()/1024);
        if ((archivoPF.getSize()/1024) < carpetaArchivo.getPesoMaximo()) { // VALIDANDO 

            if (seccion.isEmpty()) {
                RequestContext.getCurrentInstance().execute("msg('Debe seleccionar los datos necesarios para subir de archivos','alerta')");

            } //si la extension es la correcta entonces pasa a grabar
            else {
                try {
                    //aqui siempre sera 1 para la insitucion para el caso de UPIG 
                    File result = new File("D:\\FTPUSER\\upig.edu.pe\\siguWeb\\mgestordoc\\1\\" + usuarioS.getPeriodoS() + "\\"
                            + seleccionCarrera + "\\" + seleccionCurso + "\\" + seleccionSeccion + "\\" + codigoDocente + "\\" + seleccionSemana + "\\" + descarpeta + "\\" + archivoPF.getFileName());

                    String rutaArchivo = usuarioS.getPeriodoS() + "\\" + seleccionCarrera + "\\" + seleccionCurso + "\\" + seleccionSeccion
                            + "\\" + codigoDocente + "\\" + seleccionSemana + "\\" + descarpeta + "\\" + archivoPF.getFileName();

                    InputStream is;
                    FileOutputStream fos = new FileOutputStream(result);
                    byte[] buffer = new byte[BUFFER_SIZE];

                    int bulk;
                    is = archivoPF.getInputstream();
                    while (true) {
                        bulk = is.read(buffer);
                        if (bulk < 0) {
                            break;
                        }
                        fos.write(buffer, 0, bulk);
                        fos.flush();

                    }
                    is.close();
                    contdao = new ContenidoDAO();
                    contdao.insertarSubidaArchivo(1, usuarioS.getPeriodoS(), 0, codigoDocente, archivoPF.getFileName().toString(), rutaArchivo, descarpeta, seleccionCarrera, seleccionCurso, seleccionSeccion, 5);
                    buscarArchivosCarpeta2();
                    archivoPF = null;
                    util.script("msg('SU ARCHIVO FUE SUBIDO SASTIFACTORIAMENTE','correcto')");
                    
                } catch (IOException e) {
                    util.script("msg('" + e.getMessage() + "','error')");
                    

                }
            }

        } else {
            RequestContext.getCurrentInstance().execute("msg('El archivo sobre pasa los "+carpetaArchivo.getPesoMaximo()+" kb.  permitido.','alerta')");
        }
        }
        

    }

    public void editar(String ruta) {
        //util.consolaCliente( ruta);
        String[] extraer = ruta.split("\\\\");

        archivo = extraer[extraer.length - 1].toString();
        archivo = FilenameUtils.getBaseName(archivo);

        //util.consolaCliente( FilenameUtils.getBaseName(archivo));
        archivoSeleccionado = ruta;
         //archivo=nombre;       

    }

    public void guardarCambios() {

        String codigoDocente = usuarioS.getPersonal().getPersonal();
        //util.consolaCliente( archivoSeleccionado);
        File fichero = new File(archivoSeleccionado);
        String ext = FilenameUtils.getExtension(archivoSeleccionado);
        //util.consolaCliente( archivoSeleccionado);
        File fichero2 = new File("D:\\FTPUSER\\upig.edu.pe\\siguWeb\\mgestordoc\\1\\" + usuarioS.getPeriodoS() + "\\"
                + seleccionCarrera + "\\" + seleccionCurso + "\\" + seleccionSeccion + "\\" + codigoDocente + "\\" + seleccionSemana + "\\" + seleccionCarpeta + "\\" + archivo + "." + ext);
        boolean success = fichero.renameTo(fichero2);
        if (!success) {
            RequestContext.getCurrentInstance().execute("msg('Error intentando cambiar el nombre de fichero','Error')");

            System.gc();
        } else {
            buscarArchivosCarpeta2();
            RequestContext.getCurrentInstance().execute("dlg.hide()");
        }
    }

    public void descargarArchivosDocente(String ruta) throws FileNotFoundException, IOException {
        File varArchivo = new File(ruta);
        String x = "";

        if (varArchivo.isFile()) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            FileInputStream fis = new FileInputStream(varArchivo);
            byte[] bytes = new byte[1000];
            int read ;
            if (!ctx.getResponseComplete()) {
                String fileName = varArchivo.getName();
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
                out = null;
                ctx.responseComplete();
            }
        }

    }
   

    public void eliminarArchivosDocente(String ruta) {

        String rutaDatos = ruta.substring(44);

        try {

            File file = new File(ruta);

            System.gc();
            if (file.delete()) {
                contdao = new ContenidoDAO();
                contdao.eliminarArchivoDocente(rutaDatos);
                buscarArchivosCarpeta2();
                RequestContext.getCurrentInstance().execute("msg('SU ARCHIVO FUE ELIMINADO SASTIFACTORIAMENTE','correcto')");
            } else {

            }

        } catch (Exception e) {
                util.consolaCliente( e.getMessage());

        }

    }

   
    public List<ArrayList<String>> getListaDocentes() {

        return listaDocentes;
    }

    public void setListaDocentes(List<ArrayList<String>> listaDocentes) {
        this.listaDocentes = listaDocentes;
    }

    public List<ArrayList<String>> getListaArchivosDocente() {
        return listaArchivosDocente;
    }
    public void setListaArchivosDocente(List<ArrayList<String>> listaArchivosDocente) {
        this.listaArchivosDocente = listaArchivosDocente;
    }
    public String getSeleccionCarrera() {
        return seleccionCarrera;
    }
    public void setSeleccionCarrera(String seleccionCarrera) {
        this.seleccionCarrera = seleccionCarrera;
    }
    



    public String getSeleccionCurso() {
        return seleccionCurso;
    }

   
    public void setSeleccionCurso(String seleccionCurso) {
        this.seleccionCurso = seleccionCurso;
    }

    
    public String getSeleccionSeccion() {
        return seleccionSeccion;
    }
    public void setSeleccionSeccion(String seleccionSeccion) {
        this.seleccionSeccion = seleccionSeccion;
    }

   
    public List<ArrayList<String>> getDirectorios() {
        return directorios;
    }
    public void setDirectorios(List<ArrayList<String>> directorios) {
        this.directorios = directorios;
    }
    public String getSeleccionCarpeta() {
        return seleccionCarpeta;
    }
    public void setSeleccionCarpeta(String seleccionCarpeta) {
        this.seleccionCarpeta = seleccionCarpeta;
    }
    public List<ArrayList<String>> getArchivos() {

        return archivos;
    }
    public void setArchivos(List<ArrayList<String>> archivos) {
        this.archivos = archivos;
    }
    public UploadedFile getArchivoPF() {
        return archivoPF;
    }
    public void setArchivoPF(UploadedFile archivoPF) {
        this.archivoPF = archivoPF;
    }
    public String getNombrePeriodo() {
        return nombrePeriodo;
    }
    public void setNombrePeriodo(String nombrePeriodo) {
        this.nombrePeriodo = nombrePeriodo;
    }
    public personalC gePersonalDocente() {
        return PersonalDocente;
    }
    public void setPersonalDocente(personalC PersonalDocente) {
        this.PersonalDocente = PersonalDocente;
    }
    public personaC getDatosDocente() {
        return datosDocente;
    }
    public void setDatosDocente(personaC datosDocente) {
        this.datosDocente = datosDocente;
    }
    public carrerasC getDatosCarrera() {
        return datosCarrera;
    }
    public void setDatosCarrera(carrerasC datosCarrera) {
        this.datosCarrera = datosCarrera;
    }


    public String getArchivo() {
        return archivo;
    }
    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
    public String getArchivoSeleccionado() {
        return archivoSeleccionado;
    }
    public void setArchivoSeleccionado(String archivoSeleccionado) {
        this.archivoSeleccionado = archivoSeleccionado;
    }
    public List<ArrayList<String>> getListaSemanas() {
        periodoD = new periodoDAO();        
        return listaSemanas;
    }
    public void setListaSemanas(List<ArrayList<String>> listaSemanas) {
        this.listaSemanas = listaSemanas;
    }
    public String getSeleccionSemana() {
        return seleccionSemana;
    }
    public void setSeleccionSemana(String seleccionSemana) {
        this.seleccionSemana = seleccionSemana;
    }
    public boolean isBandera() {
        return bandera;
    }
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }
    public carpetaArchivoC getCarpetaArchivo() {
        return carpetaArchivo;
    }
    public void setCarpetaArchivo(carpetaArchivoC carpetaArchivo) {
        this.carpetaArchivo = carpetaArchivo;
    }
    public carpetaC getCarpeta() {
        return carpeta;
    }
    public void setCarpeta(carpetaC carpeta) {
        this.carpeta = carpeta;
    }
    public List<carpetaC> getCarpetaL() {
        return carpetaL;
    }
    public void setCarpetaL(List<carpetaC> carpetaL) {
        this.carpetaL = carpetaL;
    }
    public cursoSeccionC getCursoSeccion() {
        return cursoSeccion;
    }
    public void setCursoSeccion(cursoSeccionC cursoSeccion) {
        this.cursoSeccion = cursoSeccion;
    }
    public usuario getUsuarioS() {
        return usuarioS;
    }
    public void setUsuarioS(usuario usuarioS) {
        this.usuarioS = usuarioS;
    }



}
