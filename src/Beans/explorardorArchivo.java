

package Beans;




import DAO.exploradorArchivoDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;

import java.util.ArrayList;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;


@ManagedBean(name="explorardorArchivoB")
@ViewScoped
public class explorardorArchivo {
    
    private List<directorios>  directoriosL;
    private String ruta="D:\\";
    private String direcctorioS="RAIZ";
    private String raiz="D:\\";
    private List<cursos> cursoL=new ArrayList<>();
    exploradorArchivoDAO exploradorArchivoD;
    
    public explorardorArchivo() {
       explorar(raiz);
       
       exploradorArchivoD=new exploradorArchivoDAO();
       cursoL=exploradorArchivoD.mostrarCursosDoncete(1, "20152", "2007020088");
      
       
       
    }
    
    public void seleccionCurso(String cadena){        
        
        
        raiz="d:\\"+ cadena+"\\";
        ruta=raiz ;
       
        System.out.println(ruta);
        direcctorioS="RAIZ";
        explorar(ruta);
    }
    
    public void explorar(String ruta){
         directoriosL=new ArrayList<>();
        File raiz = new File(ruta);
        //si la ruta es un directorio
        File[] ficheros = raiz.listFiles();
        
        
           if (ficheros == null) {
                System.out.println("No hay ficheros en el directorio");
            } else {
        
        
        for (File itemFichero : ficheros) {
            directorios item=new directorios();            
            item.setNombre(FilenameUtils.removeExtension(itemFichero.getName()));
            item.setArchivo(itemFichero.getName());
            item.setPeso(itemFichero.length()/1024);
            if(itemFichero.isDirectory() ){
                item.setTipo(0);
                item.setIcono("fa-archive");
                if (item.peso>0){
                    item.setColor("blue");
                }else{
                    item.setColor("gold");
                }
                        
                
            }else{
                item.setTipo(1);
         //       FilenameUtils.removeExtension(filenameWithExtension);
        String ext = FilenameUtils.getExtension(itemFichero.getName()).toLowerCase();
        
        switch (ext){
            case "mp4":case "webm":case "flv":case "mkv":case "avi":case "3gp":
                item.setIcono("fa-file-video-o");
                item.setColor("blue");
           break;
           case "jpg":case "png":case"bmp":case"gif":case "ico":
                item.setIcono("fa-file-image-o");
                item.setColor("silver");
                item.setCategoria("imagen");
           break;     
            
            case "doc":case "docx":
                 item.setIcono("fa-file-word-o");
                item.setColor("green");
            break;
                        
            case"xls":case"xlsx":
             item.setIcono("fa-file-excel-o");
                item.setColor("green");
            break;
            case "pptx":case "ppt":
                item.setIcono("fa-file-powerpoint-o");
                item.setColor("green");
            break;
           
            case "pdf":
                  item.setIcono("fa-file-pdf-o");
                item.setColor("red");
             break;
                
            case "zip": case"rar":
                  item.setIcono("fa-file-archive-o");
                item.setColor("brown");
             break;
                
                
            case "txt": 
                item.setIcono("fa-file-text-o");
                item.setColor("black");
             break;
            default:
                item.setIcono("fa-file-o");
                item.setColor("black");                
                
        }
            }
            directoriosL.add(item);
           // item.getName()
        }
      }
    }
    
    public void adelante(String carpeta){
       
     direcctorioS=carpeta;
      ruta+=carpeta+"\\";
      System.out.println(ruta);
        explorar(ruta);
    }
    
    
    public void descargar(String item) throws FileNotFoundException, IOException{
        File archivo = new File(ruta+"\\"+item);

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
    public void atras(){
        
        
        
     if (! ruta.endsWith(raiz))   {
         String cadena[] =ruta.split("\\\\");     
      
      
       if (cadena.length>1){
        ruta="";
        for (int i =0;i<cadena.length-1;i++){
            
             ruta+=cadena[i].concat("\\") ;  
        }
            explorar(ruta);
 
       }
     }
        
    
      
        
        
    }

    /**
     * @return the directoriosL
     */
    public List<directorios> getDirectoriosL() {
        return directoriosL;
    }

    /**
     * @param directoriosL the directoriosL to set
     */
    public void setDirectoriosL(List<directorios> directoriosL) {
        this.directoriosL = directoriosL;
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
     * @return the direcctorioS
     */
    public String getDirecctorioS() {
        return direcctorioS;
    }

    /**
     * @param direcctorioS the direcctorioS to set
     */
    public void setDirecctorioS(String direcctorioS) {
        this.direcctorioS = direcctorioS;
    }

    /**
     * @return the cursoL
     */
    public List<cursos> getCursoL() {
        return cursoL;
    }

    /**
     * @param cursoL the cursoL to set
     */
    public void setCursoL(List<cursos> cursoL) {
        this.cursoL = cursoL;
    }

    /**
     * @return the raiz
     */
    public String getRaiz() {
        return raiz;
    }

    /**
     * @param raiz the raiz to set
     */
    public void setRaiz(String raiz) {
        this.raiz = raiz;
    }

   
    public static class cursos{
        private int institucion;
        private String periodo;
        private String carrera;
        private String curso;
        private String desCurso;
        private String seccion;
        private String personal;
        private String persona;
        private String desPersona;

        public cursos() {
        }

        public cursos(int institucion, String periodo, String carrera, String curso, String desCurso, String seccion) {
            this.institucion = institucion;
            this.periodo = periodo;
            this.carrera = carrera;
            this.curso = curso;
            this.desCurso = desCurso;
            this.seccion = seccion;
        }
        
        
        

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
        
        
        
        
    }
    
    
    
    
    
    
    
    public static class directorios{
        private String nombre;
        private String archivo;
        private int tipo;
        private String icono;
        private String color;
        private String categoria;
        private long peso;

        public directorios() {
        }

        public directorios(String nombre, int tipo) {
            this.nombre = nombre;
            this.tipo = tipo;
        }
        

        /**
         * @return the nombre
         */
        public String getNombre() {
            return nombre;
        }

        /**
         * @param nombre the nombre to set
         */
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        /**
         * @return the tipo
         */
        public int getTipo() {
            return tipo;
        }

        /**
         * @param tipo the tipo to set
         */
        public void setTipo(int tipo) {
            this.tipo = tipo;
        }

        /**
         * @return the icono
         */
        public String getIcono() {
            return icono;
        }

        /**
         * @param icono the icono to set
         */
        public void setIcono(String icono) {
            this.icono = icono;
        }

        /**
         * @return the color
         */
        public String getColor() {
            return color;
        }

        /**
         * @param color the color to set
         */
        public void setColor(String color) {
            this.color = color;
        }

        /**
         * @return the categoria
         */
        public String getCategoria() {
            return categoria;
        }

        /**
         * @param categoria the categoria to set
         */
        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        /**
         * @return the archivo
         */
        public String getArchivo() {
            return archivo;
        }

        /**
         * @param archivo the archivo to set
         */
        public void setArchivo(String archivo) {
            this.archivo = archivo;
        }

        /**
         * @return the peso
         */
        public long getPeso() {
            return peso;
        }

        /**
         * @param peso the peso to set
         */
        public void setPeso(long peso) {
            this.peso = peso;
        }

       
        
    }
    
}
