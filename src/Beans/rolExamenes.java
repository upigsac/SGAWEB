
package Beans;

import DAO.ContenidoDAO;
import DAO.carrerasDAO;
import DAO.personalDAO;
import DAO.rolExamenDAO;
import DAO.vencimientoDAO;
import ENTIDAD.carrerasC;
import ENTIDAD.rolExamenC;
import ENTIDAD.vencimientoC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "rolExamenB")
@ViewScoped
public class rolExamenes implements Serializable {

	private static final long serialVersionUID = 1L;
	carrerasDAO cardao;
    ContenidoDAO contdao;
    personalDAO perldao;

    private String periodo;
    private String seleccionCarrera;
    private String seleccionExamen;
    private String nombreArchivo;
    private int grupo = 0;

    private String mensajeTabla;

    private UploadedFile archivoPF;
    private static final int BUFFER_SIZE = 6124;

    private List<carrerasC> listaCarreras;
    private List<carrerasC> carreraL;
    
    private rolExamenC rolExamen;
    private List<rolExamenC> rolExamenL=new ArrayList<>();
    
    
    
    
    
    
    
    
    
    
    
    
    public rolExamenC getRolExamen() {
		return rolExamen;
	}


	public void setRolExamen(rolExamenC rolExamen) {
		this.rolExamen = rolExamen;
	}


	public List<rolExamenC> getRolExamenL() {
		return rolExamenL;
	}


	public void setRolExamenL(List<rolExamenC> rolExamenL) {
		this.rolExamenL = rolExamenL;
	}
	
    
    
    
    
    
    public List<carrerasC> getCarreraL() {
		return carreraL;
	}


	public void setCarreraL(List<carrerasC> carreraL) {
		this.carreraL = carreraL;
	}
	private List<ArrayList<String>> archivos;
    private int institucion;
    private List<vencimientoC> vencimientoL=new ArrayList<>();
    
    
    
    public void mostrarCarrera(){
    	carreraL=new carrerasDAO().listaCarreraPeriodo(institucion, periodo);
    }
    
    
    public void mostrarVencimiento(){
    	vencimientoL=new vencimientoDAO().mostrarVencimientoGrupo(institucion, periodo);
    }
    

    public List<vencimientoC> getVencimientoL() {
		return vencimientoL;
	}



	public void setVencimientoL(List<vencimientoC> vencimientoL) {
		this.vencimientoL = vencimientoL;
	}



	public void refrescarInstitucionPeriodo(int institucion, String periodo) {
        this.institucion = 1;
        this.setPeriodo(periodo);
        mostrarCarrera();
        mostrarVencimiento();
        rolExamen=new rolExamenC(institucion, periodo, null, 0, null, null, null, 1);
    }
    
	public void insertarArchivo(){
		
		 if (archivoPF.getSize() == 0) {
	        	
	        	
	        	
	        	util.script("notificacion('Seleccionar Archivo',500,5000,'erroo')");

	        } else {

	            try {

	                //crear las carpetas donde se guardara el archivo
	                File result = new File("D:\\descargas\\" + rolExamen.getInstitucion() + "\\" + rolExamen.getPeriodo() + "\\" + rolExamen.getCarrera() + "\\" + rolExamen.getVencimiento() + "\\"+  rolExamen.getTipoExamen() + "\\");
	                result.mkdirs();
	              
	                File rutaArchivo = new File("D:\\descargas\\" + rolExamen.getInstitucion() + "\\" + rolExamen.getPeriodo() + "\\" + rolExamen.getCarrera() + "\\" + rolExamen.getVencimiento() + "\\" + rolExamen.getTipoExamen() + "\\" + (rolExamen.getArchivo().trim()).toUpperCase() + "." + FilenameUtils.getExtension(archivoPF.getFileName()));
	                String ruta = "descargas\\" + rolExamen.getInstitucion() + "\\" + rolExamen.getPeriodo() + "\\" + rolExamen.getCarrera() + "\\" + rolExamen.getVencimiento() + "\\" +rolExamen.getTipoExamen() + "\\"+ (rolExamen.getArchivo().trim()).toUpperCase() + "." + FilenameUtils.getExtension(archivoPF.getFileName());

	                
	                InputStream is;

	                FileOutputStream fos = new FileOutputStream(rutaArchivo);

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

	                rolExamen.setRuta(ruta);
	                new rolExamenDAO().insertar(rolExamen);
	                rolExamenL=new rolExamenDAO().mostrarRolExamen(rolExamen.getInstitucion(), rolExamen.getPeriodo(), rolExamen.getCarrera(), rolExamen.getVencimiento(), rolExamen.getTipoExamen());
	                rolExamen.setArchivo("");
	               
	                archivoPF = null;
	                
	                
	                util.script("notificacion('Su archivo fue subido Exitosamente',500,5000,'correcto')");
	                util.script("$('#btSubir').attr('disabled','disabled')");
	                
	            } catch (IOException e) {
	                System.out.println(e.getMessage());

	            }

	        }
		
		
		
		
		
		
		
		
		
		
		 
	}
    

    public void subirArchivo(String personal) {

        perldao = new personalDAO();
       

        if (archivoPF.getSize() == 0) {
        	
        	
        	util.script("alert('SELECCIONAR ARCHIVO')");
         

        } else {

            try {

                //crear las carpetas donde se guardara el archivo
                File result = new File("D:\\descargas\\" + getPeriodo() + "\\" + seleccionCarrera + "\\" + personal + "\\"
                        + seleccionExamen + "\\");

                result.mkdirs();

                //ruta del archivo donde se grabara
                File rutaArchivo = new File("D:\\descargas\\" + getPeriodo() + "\\" + seleccionCarrera + "\\" + personal + "\\"
                        + seleccionExamen + "\\" + (nombreArchivo.trim()).toUpperCase() + "." + FilenameUtils.getExtension(archivoPF.getFileName()));

                //parte de un insert a base
                String rutaArchivoBD = "descargas\\" + getPeriodo() + "\\" + seleccionCarrera + "\\" + personal + "\\"
                        + seleccionExamen + "\\" + (nombreArchivo.trim()).toUpperCase() + "." + FilenameUtils.getExtension(archivoPF.getFileName());

                InputStream is;

                FileOutputStream fos = new FileOutputStream(rutaArchivo);

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

            //1 por la institucion --- 2 para administrativo
             //   new ContenidoDAO().insertarSubidaArchivo(1, getPeriodo(), grupo, personal, nombreArchivo.trim().toUpperCase(), rutaArchivoBD, seleccionExamen, seleccionCarrera, "", "", 2);
              
                buscarArchivosCarpeta();
                nombreArchivo = "";
                archivoPF = null;
                
                util.script("alert('SU ARCHIVO FUE SUBIDO EXITOSAMENTE')");
                RequestContext.getCurrentInstance().execute("$('#btSubir').attr('disabled','disabled')");
            } catch (IOException e) {
                //si hubo un problema
                util.consolaCliente( "problema");

            }

        }

    }

    public void buscarArchivosCarpeta() {
        contdao = new ContenidoDAO();
        archivos = contdao.mostrarRolExamenesAdmin(getPeriodo(), seleccionCarrera, seleccionExamen);
        
        rolExamenL=new rolExamenDAO().mostrarRolExamen(rolExamen.getInstitucion(), rolExamen.getPeriodo(), rolExamen.getCarrera(), rolExamen.getVencimiento(), rolExamen.getTipoExamen());

    }

    public void buscarArchivosCarpeta(String personal) { //--------- SIN BASE DE DATOS

        perldao = new personalDAO();
        contdao = new ContenidoDAO();

        File result = new File("D:\\descargas\\" + getPeriodo() + "\\" + seleccionCarrera + "\\" + personal + "\\"
                + seleccionExamen + "\\");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a");

        File[] ficheros = result.listFiles();
        archivos = new ArrayList<>();

        if (ficheros == null) {
            mensajeTabla = "No hay ficheros en el directorio especificado";
        } else {
            for (int x = 0; x < ficheros.length; x++) {

                ArrayList<String> array = new ArrayList<>();

                array.add(ficheros[x].getName());
                array.add(sdf.format(ficheros[x].lastModified()));
                array.add(ficheros[x].getAbsolutePath());

                archivos.add(array);

            }
        }
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

    public void eliminarArchivos(String ruta) {

        contdao = new ContenidoDAO();

        try {

            File file = new File("D:\\" + ruta);
            System.gc();
            if (file.delete()) {
                contdao.eliminarArchivoDocente(ruta);
                buscarArchivosCarpeta();
                RequestContext.getCurrentInstance().execute("msg('EL ARCHIVO FUE ELIMINADO','correcto')");
            } else {

            }

        } catch (Exception e) {

        }

    }

    
    public void eliminar(rolExamenC itemRolExamen) {
    	
    	
    	  new rolExamenDAO().eliminar(itemRolExamen);
          rolExamenL=new rolExamenDAO().mostrarRolExamen(rolExamen.getInstitucion(), rolExamen.getPeriodo(), rolExamen.getCarrera(), rolExamen.getVencimiento(), rolExamen.getTipoExamen());
          
          util.script("notificacion('El archivo fue Eliminado',500,5000,'correcto')");
    	
        try {

            File file = new File("D:\\" + itemRolExamen.getRuta());
            System.gc();
            if (file.delete()) {
              
            } 
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }

    }
    public void descargarArchivo(rolExamenC itemRolExamen) {

        try {
            //util.consolaCliente( ruta);
            File archivo = new File("D:\\" + itemRolExamen.getRuta());
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
            System.out.println(fnf.getMessage());
        } catch (IOException ex) {
            
            System.out.println(ex.getMessage());
        }

    }
    
    
    
    
    
    
    
    
    
    


    public String getSeleccionCarrera() {
        return seleccionCarrera;
    }
    public void setSeleccionCarrera(String seleccionCarrera) {
        this.seleccionCarrera = seleccionCarrera;
    }
    public List<carrerasC> getListaCarreras() {
      
        listaCarreras = new carrerasDAO().listaCarreraInstitucion(institucion);
     
        return listaCarreras;
    }
    public void setListaCarreras(List<carrerasC> listaCarreras) {
        this.listaCarreras = listaCarreras;
    }
    public String getSeleccionExamen() {
        return seleccionExamen;
    }
    public void setSeleccionExamen(String seleccionExamen) {
        this.seleccionExamen = seleccionExamen;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    public UploadedFile getArchivoPF() {
        return archivoPF;
    }
    public void setArchivoPF(UploadedFile archivoPF) {
        this.archivoPF = archivoPF;
    }
    public List<ArrayList<String>> getArchivos() {
        return archivos;
    }
    public void setArchivos(List<ArrayList<String>> archivos) {
        this.archivos = archivos;
    }
    public String getMensajeTabla() {
        return mensajeTabla;
    }
    public void setMensajeTabla(String mensajeTabla) {
        this.mensajeTabla = mensajeTabla;
    }
    public int getInstitucion() {
        return institucion;
    }
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }
    public int getGrupo() {
        return grupo;
    }
    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }
    public String getPeriodo() {
        return periodo;
    }
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

}
