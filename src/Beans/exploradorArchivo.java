
package Beans;

import DAO.cursoSeccionArchivoDAO;

import ENTIDAD.cursoSeccionArchivoC;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name="exploradorArchivoB")
@ViewScoped
public class exploradorArchivo {
    private List<cursoSeccionArchivoC> cursoSeccionArchivoL;
    private cursoSeccionArchivoC cursoSeccionArchivoS=null;
    private UploadedFile uploadedFile;
    private String nombreCarpeta;
    private String archivoPadre="\\";
    private String rutaArchivo;
    private List<arbolArchivo> arbolArchivoL=new ArrayList<>();
    private List<cursoSeccionPersonal> cursoSeccionPersonalL;
    private cursoSeccionPersonal cursoSeccionPersonalS;
    private DefaultStreamedContent download;
    private boolean banderaCarpeta=false;
     public void prepDownload(String archivo) throws Exception {
    
       File file = new File(archivo);
       InputStream input = new FileInputStream(file);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();  
        setDownload(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
}
    
    public exploradorArchivo() {
        
    }
    
    public List<cursoSeccionPersonal> mostrarCursoArchivo(int institucion,String periodo,String personal){
            cursoSeccionArchivoD=new cursoSeccionArchivoDAO();
            cursoSeccionPersonalL=cursoSeccionArchivoD.mostrarCursoSeccionPersonal(institucion,periodo,personal);
            return cursoSeccionPersonalL;
    }
    public List<cursoSeccionPersonal> mostrarAlumnoCursoArchivo(int institucion,String periodo,String alumno){
            cursoSeccionArchivoD=new cursoSeccionArchivoDAO();
            cursoSeccionPersonalL=cursoSeccionArchivoD.mostrarCursoSeccionAlumno(institucion,periodo,alumno);
            return cursoSeccionPersonalL;
    }
    public void seleccionCurso(cursoSeccionPersonal item){
        cursoSeccionPersonalS=item;
        cursoSeccionArchivoS=null;
        archivoPadre="\\";
        rutaArchivo="D:\\FTPUSER\\upig.edu.pe\\SGAWEB\\portafolio\\"+cursoSeccionPersonalS.institucion +"\\"+cursoSeccionPersonalS.periodo +"\\"+cursoSeccionPersonalS.carrera+"\\"+cursoSeccionPersonalS.malla+"\\"+cursoSeccionPersonalS.seccion+"\\"+cursoSeccionPersonalS.curso;
        
        cursoSeccionArchivoD=new cursoSeccionArchivoDAO();
        cursoSeccionArchivoD.insertarCarpetaRaiz(new cursoSeccionArchivoC(cursoSeccionPersonalS.institucion, cursoSeccionPersonalS.periodo, cursoSeccionPersonalS.carrera, cursoSeccionPersonalS.malla, cursoSeccionPersonalS.curso, cursoSeccionPersonalS.seccion, null, rutaArchivo, null, 0, null, 1));
        
        
        cursoSeccionArchivoL=cursoSeccionArchivoD.mostrarCursoSeccionArchivo(item.institucion, item.periodo, item.carrera, item.malla, item.curso, item.seccion, archivoPadre);            
        arbolArchivoL=new ArrayList<>();
        arbolArchivoL.add(new arbolArchivo(1, archivoPadre,rutaArchivo));
    }
    
    public void insertarCarpeta(){  
        
         util.crearDirectorio(rutaArchivo+"\\"+nombreCarpeta);
         cursoSeccionArchivoD.insertar(new cursoSeccionArchivoC(cursoSeccionPersonalS.institucion, cursoSeccionPersonalS.periodo, cursoSeccionPersonalS.carrera, cursoSeccionPersonalS.malla, cursoSeccionPersonalS.curso, cursoSeccionPersonalS.seccion, nombreCarpeta, rutaArchivo, null, 0, archivoPadre, 1));
         mostrarArchivo();
         banderaCarpeta=false;
    }
    public void nuevaCarpeta(){
        banderaCarpeta=true;
     nombreCarpeta="";
    }
     public void cancelarCarpeta(){
        banderaCarpeta=false;
     
    }
    
    public void eliminar(cursoSeccionArchivoC item){
          
          
         
            File fichero = new File(item.getRuta() +"\\"+item.getArchivo());
        if (fichero.delete()){
                cursoSeccionArchivoD=new cursoSeccionArchivoDAO();
                cursoSeccionArchivoD.eliminar(item);
                mostrarArchivo();
                System.out.println("El fichero ha sido borrado satisfactoriamente");
         
        }
       
         else
            System.out.println("El fichero no puede ser borrado");
         
    }
    
        public void save() throws IOException  {
       
        util.crearDirectorio(rutaArchivo);
        
        
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
    inputStream = uploadedFile.getInputstream(); //leemos el fichero local
    // write the inputStream to a FileOutputStream
    outputStream = new FileOutputStream(new File(rutaArchivo+"\\"+util.charset(uploadedFile.getFileName()) ));
 
    int read = 0;
    byte[] bytes = new byte[1024];
 
    while ((read = inputStream.read(bytes)) != -1) {
       outputStream.write(bytes, 0, read);
    }
  
        cursoSeccionArchivoD=new cursoSeccionArchivoDAO();
          cursoSeccionArchivoD.insertar(new cursoSeccionArchivoC(cursoSeccionPersonalS.institucion, cursoSeccionPersonalS.periodo, cursoSeccionPersonalS.carrera, cursoSeccionPersonalS.malla, cursoSeccionPersonalS.curso, cursoSeccionPersonalS.seccion, util.charset(uploadedFile.getFileName()), rutaArchivo, FilenameUtils.getExtension(uploadedFile.getFileName()), 0, archivoPadre, 1));
          mostrarArchivo();
    
    
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
    
    
    
    cursoSeccionArchivoDAO cursoSeccionArchivoD;
   
    public void mostrarArchivo(){
        cursoSeccionArchivoD=new cursoSeccionArchivoDAO();
        cursoSeccionArchivoL=cursoSeccionArchivoD.mostrarCursoSeccionArchivo(cursoSeccionPersonalS.institucion, cursoSeccionPersonalS.periodo, cursoSeccionPersonalS.carrera, cursoSeccionPersonalS.malla, cursoSeccionPersonalS.curso, cursoSeccionPersonalS.seccion, archivoPadre);
    }
    public void seleccionArchivo(cursoSeccionArchivoC item,String archivoPadre){
        cursoSeccionArchivoS=item;                
        this.archivoPadre=archivoPadre;
        rutaArchivo+="\\"+archivoPadre;
        
        arbolArchivoL.add(new arbolArchivo(2, archivoPadre,rutaArchivo));            
        mostrarArchivo();
    }
    
    
    public void seleccionArbol(String archivoPadre,int indice){
        if(indice==0){
            cursoSeccionArchivoS=null;
        }
        
        for(int x=(arbolArchivoL.size()-1);x>indice;x-- ){
           
            arbolArchivoL.remove(x);
        }
        
        rutaArchivo=arbolArchivoL.get(indice).ruta;
        
         this.archivoPadre=archivoPadre;
         mostrarArchivo();
    }
    public List<arbolArchivo> getArbolArchivoL() {
        return arbolArchivoL;
    }
    public void setArbolArchivoL(List<arbolArchivo> arbolArchivoL) {
        this.arbolArchivoL = arbolArchivoL;
    }
    public List<cursoSeccionPersonal> getCursoSeccionPersonalL() {
        return cursoSeccionPersonalL;
    }
    public void setCursoSeccionPersonalL(List<cursoSeccionPersonal> cursoSeccionPersonalL) {
        this.cursoSeccionPersonalL = cursoSeccionPersonalL;
    }
    public cursoSeccionPersonal getCursoSeccionPersonalS() {
        return cursoSeccionPersonalS;
    }
    public void setCursoSeccionPersonalS(cursoSeccionPersonal cursoSeccionPersonalS) {
        this.cursoSeccionPersonalS = cursoSeccionPersonalS;
    }
    public String getArchivoPadre() {
        return archivoPadre;
    }
    public void setArchivoPadre(String archivoPadre) {
        this.archivoPadre = archivoPadre;
    }
    public String getRutaArchivo() {
        return rutaArchivo;
    }
    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }
    public DefaultStreamedContent getDownload() {
        return download;
    }
    public void setDownload(DefaultStreamedContent download) {
        this.download = download;
    }
    public boolean isBanderaCarpeta() {
        return banderaCarpeta;
    }
    public void setBanderaCarpeta(boolean banderaCarpeta) {
        this.banderaCarpeta = banderaCarpeta;
    }
    
    public static class cursoSeccionPersonal{
        private int institucion;
        private String periodo;
        private String carrera;
        private String desCarrera;
        private String malla;
        private String curso;
        private String desCurso;
        private String seccion;
        private String desSeccion;  
        private int turno;
        private String desturno;
        private String desSede;
        private String personal;
        private String persona;
        private String desPersona;
        public int getInstitucion() {
            return institucion;
        }
        public void setInstitucion(int institucion) {
            this.institucion = institucion;
        }
        public String getPeriodo() {
            return periodo;
        }
        public void setPeriodo(String periodo) {
            this.periodo = periodo;
        }
        public String getCarrera() {
            return carrera;
        }
        public void setCarrera(String carrera) {
            this.carrera = carrera;
        }
        public String getDesCarrera() {
            return desCarrera;
        }
        public void setDesCarrera(String desCarrera) {
            this.desCarrera = desCarrera;
        }
        public String getCurso() {
            return curso;
        }
        public void setCurso(String curso) {
            this.curso = curso;
        }
        public String getDesCurso() {
            return desCurso;
        }
        public void setDesCurso(String desCurso) {
            this.desCurso = desCurso;
        }
        public String getSeccion() {
            return seccion;
        }
        public void setSeccion(String seccion) {
            this.seccion = seccion;
        }
        public String getDesSeccion() {
            return desSeccion;
        }
        public void setDesSeccion(String desSeccion) {
            this.desSeccion = desSeccion;
        }
        public String getMalla() {
            return malla;
        }
        public void setMalla(String malla) {
            this.malla = malla;
        }
        public int getTurno() {
            return turno;
        }
        public void setTurno(int turno) {
            this.turno = turno;
        }
        public String getDesturno() {
            return desturno;
        }
        public void setDesturno(String desturno) {
            this.desturno = desturno;
        }
        public String getDesSede() {
            return desSede;
        }
        public void setDesSede(String desSede) {
            this.desSede = desSede;
        }
        public String getPersonal() {
            return personal;
        }
        public void setPersonal(String personal) {
            this.personal = personal;
        }
        public String getPersona() {
            return persona;
        }
        public void setPersona(String persona) {
            this.persona = persona;
        }
        public String getDesPersona() {
            return desPersona;
        }
        public void setDesPersona(String desPersona) {
            this.desPersona = desPersona;
        }
        
    }
    
    
    
    public static class arbolArchivo{
        private int indice;
        private String carpeta;
        private String ruta;

        public arbolArchivo() {
        }

        public arbolArchivo(int indice, String carpeta, String ruta) {
            this.indice = indice;
            this.carpeta = carpeta;
            this.ruta = ruta;
        }

       
        

        /**
         * @return the indice
         */
        public int getIndice() {
            return indice;
        }

        /**
         * @param indice the indice to set
         */
        public void setIndice(int indice) {
            this.indice = indice;
        }

        /**
         * @return the carpeta
         */
        public String getCarpeta() {
            return carpeta;
        }

        /**
         * @param carpeta the carpeta to set
         */
        public void setCarpeta(String carpeta) {
            this.carpeta = carpeta;
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
        
    }
    

    /**
     * @return the cursoSeccionArchivoL
     */
    public List<cursoSeccionArchivoC> getCursoSeccionArchivoL() {
        return cursoSeccionArchivoL;
    }

    /**
     * @param cursoSeccionArchivoL the cursoSeccionArchivoL to set
     */
    public void setCursoSeccionArchivoL(List<cursoSeccionArchivoC> cursoSeccionArchivoL) {
        this.cursoSeccionArchivoL = cursoSeccionArchivoL;
    }

    /**
     * @return the cursoSeccionArchivoS
     */
    public cursoSeccionArchivoC getCursoSeccionArchivoS() {
        return cursoSeccionArchivoS;
    }

    /**
     * @param cursoSeccionArchivoS the cursoSeccionArchivoS to set
     */
    public void setCursoSeccionArchivoS(cursoSeccionArchivoC cursoSeccionArchivoS) {
        this.cursoSeccionArchivoS = cursoSeccionArchivoS;
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
     * @return the nombreCarpeta
     */
    public String getNombreCarpeta() {
        return nombreCarpeta;
    }

    /**
     * @param nombreCarpeta the nombreCarpeta to set
     */
    public void setNombreCarpeta(String nombreCarpeta) {
        this.nombreCarpeta = nombreCarpeta;
    }
}
