
package Beans;

import DAO.cursoSeccionDocenteDAO;
import DAO.matriculaDAO;
import DAO.personaDAO;
import ENTIDAD.cursoSeccionDocenteC;
import ENTIDAD.personaC;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;

@ManagedBean(name = "fichaMatriculaB")
@ViewScoped
public class fichaMatricula  {

    private List<detalle> detalleL=new ArrayList<>();
    private detalle detalle=new detalle();
    private personaC persona=new personaC();
    private cursoSeccionDocenteC cursoSeccionDocente=new cursoSeccionDocenteC();
     private DefaultStreamedContent download;

    matriculaDAO matriculaD;
    personaDAO personaD;
    cursoSeccionDocenteDAO cursoSeccionDocenteD;

    public List<detalle> mostrarMatricula(int institucion, String periodo, String alumno) {
        detalle=new detalle();
        matriculaD = new matriculaDAO();
        detalleL = matriculaD.mostrarMatriculaAlumnos(institucion, periodo, alumno);
        return detalleL;
        
    }
       public void prepDownload(String date) throws Exception {
          System.out.println(date);
        File file = new File(date);
        InputStream input = new FileInputStream(file);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        setDownload(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
}
    
    public  void descargar(String ruta,String archivo) throws FileNotFoundException, IOException{
      File file = new File(ruta);
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
     response.setHeader("Content-Disposition", "attachment;filename="+archivo);
     response.getOutputStream().write(buf);
     response.getOutputStream().flush();
     response.getOutputStream().close();
     FacesContext.getCurrentInstance().responseComplete();
    }
  

    public void onRowSelect(SelectEvent event) {
    
        
        detalle item = ((detalle) event.getObject());
        
        personaD = new personaDAO();
        cursoSeccionDocenteD = new cursoSeccionDocenteDAO();
        persona = personaD.mostrarPersona(item.institucion, item.periodo, item.malla, item.carrera, item.seccion, item.curso);

    }
    public List<detalle> getDetalleL() {
        return detalleL;
    }
    public void setDetalleL(List<detalle> detalleL) {
        this.detalleL = detalleL;
    }
    public detalle getDetalle() {
        return detalle;
    }
    public void setDetalle(detalle detalle) {
        this.detalle = detalle;
    }
    public personaC getPersona() {
        return persona;
    }
    public void setPersona(personaC persona) {
        this.persona = persona;
    }
    public cursoSeccionDocenteC getCursoSeccionDocente() {
        return cursoSeccionDocente;
    }
    public void setCursoSeccionDocente(cursoSeccionDocenteC cursoSeccionDocente) {
        this.cursoSeccionDocente = cursoSeccionDocente;
    }

    
    public DefaultStreamedContent getDownload() {
        return download;
    }

   
    public void setDownload(DefaultStreamedContent download) {
        this.download = download;
    }

    public static class  detalle {

        private int institucion;
        private String periodo;
        private String malla;
        private String carrera;
        private String curso;
        private String desCurso;
        private int creditos;
        private int turno;
        private String desTurno;
        private String seccion;
        private String desSeccion;
        private int ciclo;
        private String fechaInicio;
        private String fechaFin;
        private String persona;
        private String desPersona;
        private int horaTeoria;
        private int horaPractica;
        private String ruta;
        private String silabu;
        private int formula  ;

        public detalle() {
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
         * @return the creditos
         */
        public int getCreditos() {
            return creditos;
        }

        /**
         * @param creditos the creditos to set
         */
        public void setCreditos(int creditos) {
            this.creditos = creditos;
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
         * @return the ciclo
         */
        public int getCiclo() {
            return ciclo;
        }

        /**
         * @param ciclo the ciclo to set
         */
        public void setCiclo(int ciclo) {
            this.ciclo = ciclo;
        }

        /**
         * @return the fechaInicio
         */
        public String getFechaInicio() {
            return fechaInicio;
        }

        /**
         * @param fechaInicio the fechaInicio to set
         */
        public void setFechaInicio(String fechaInicio) {
            this.fechaInicio = fechaInicio;
        }

        /**
         * @return the fechaFin
         */
        public String getFechaFin() {
            return fechaFin;
        }

        /**
         * @param fechaFin the fechaFin to set
         */
        public void setFechaFin(String fechaFin) {
            this.fechaFin = fechaFin;
        }

        /**
         * @return the turno
         */
        public int getTurno() {
            return turno;
        }

        /**
         * @param turno the turno to set
         */
        public void setTurno(int turno) {
            this.turno = turno;
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
         * @return the desTurno
         */
        public String getDesTurno() {
            return desTurno;
        }

        /**
         * @param desTurno the desTurno to set
         */
        public void setDesTurno(String desTurno) {
            this.desTurno = desTurno;
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
         * @return the horaTeoria
         */
        public int getHoraTeoria() {
            return horaTeoria;
        }

        /**
         * @param horaTeoria the horaTeoria to set
         */
        public void setHoraTeoria(int horaTeoria) {
            this.horaTeoria = horaTeoria;
        }

        /**
         * @return the horaPractica
         */
        public int getHoraPractica() {
            return horaPractica;
        }

        /**
         * @param horaPractica the horaPractica to set
         */
        public void setHoraPractica(int horaPractica) {
            this.horaPractica = horaPractica;
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
         * @return the formula
         */
        public int getFormula() {
            return formula;
        }

        /**
         * @param formula the formula to set
         */
        public void setFormula(int formula) {
            this.formula = formula;
        }
    }
}
