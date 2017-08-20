
package Beans;

import DAO.cronogramaAcademicoDAO;
import DAO.cronogramaAcademicoVencimientoModuloDAO;
import ENTIDAD.cronogramaAcademicoC;
import ENTIDAD.cronogramaAcademicoVencimientoModuloC;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;


@ManagedBean(name="cronogramaAcademicoB")
@ViewScoped
public class cronogramaAcademico implements Serializable {
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;




	private List<cronogramaVencimiento> cronogramaVencimientoL;
    
    
    
    
    private cronogramaAcademicoDAO cronogramaAcademicoD;
    cronogramaAcademicoVencimientoModuloDAO cronogramaAcademicoVencimientoModuloD;
 
    // ALUMNO 
    public List<cronogramaVencimiento>  mostrarCronogramaAcademico(int institucion,String periodo,String alumno){
         cronogramaAcademicoD=new cronogramaAcademicoDAO();
        cronogramaAcademicoVencimientoModuloD=new cronogramaAcademicoVencimientoModuloDAO();
        cronogramaVencimientoL=new ArrayList<>();
        for (cronogramaVencimiento itemVencimiento : cronogramaAcademicoD.mostrarCronogramaVencimiento(institucion, periodo, alumno)) {
            for(cronogramaAcademicoVencimientoModuloC itemCronogramaAcademicoVencimientoModulo : cronogramaAcademicoVencimientoModuloD.mostrarCronogramaAcademicoVencimientoModulo(itemVencimiento.institucion, itemVencimiento.periodo, itemVencimiento.vencimiento))
            {                
                cronogramaVencimientoModulo itemCronogramaVencimientoModulo=new cronogramaVencimientoModulo(itemCronogramaAcademicoVencimientoModulo);                               
                
                  for (cronograma itemSemana : cronogramaAcademicoD.mostrarFechaCronogramaAcademico(itemCronogramaVencimientoModulo.cronogramaAcademicoVencimientoModulo.getFechaInicio(),itemCronogramaVencimientoModulo.cronogramaAcademicoVencimientoModulo.getSemanas())) {
                      itemCronogramaVencimientoModulo.cronogramaL.add(itemSemana);                     
                    }
                    for (cronogramaAcademicoC itemAcademico : cronogramaAcademicoD.mostrarCronogramaAcademico(itemVencimiento.institucion,itemVencimiento.periodo,itemVencimiento.vencimiento,itemCronogramaVencimientoModulo.cronogramaAcademicoVencimientoModulo.getModulo())) {
                        itemCronogramaVencimientoModulo.cronogramaAcademicoL.add(itemAcademico);                       
                    }
                itemVencimiento.cronogramaVencimientoModuloL.add(itemCronogramaVencimientoModulo);
            }
          
            cronogramaVencimientoL.add(itemVencimiento);
            
        }
        return cronogramaVencimientoL;
    }

  
    
    // DOCENTE 
    public List<cronogramaVencimiento>  mostrarCronogramaAcademicoDocente(int institucion,String periodo,String personal){
         cronogramaAcademicoD=new cronogramaAcademicoDAO();
         cronogramaAcademicoVencimientoModuloD=new cronogramaAcademicoVencimientoModuloDAO();
        cronogramaVencimientoL=new ArrayList<>();
        
        for (cronogramaVencimiento itemVencimiento : cronogramaAcademicoD.mostrarCronogramaVencimientoDocente(institucion, periodo, personal)) {
            for(cronogramaAcademicoVencimientoModuloC itemCronogramaAcademicoVencimientoModulo : cronogramaAcademicoVencimientoModuloD.mostrarCronogramaAcademicoVencimientoModulo(itemVencimiento.institucion, itemVencimiento.periodo, itemVencimiento.vencimiento))
            {                
                cronogramaVencimientoModulo itemCronogramaVencimientoModulo=new cronogramaVencimientoModulo(itemCronogramaAcademicoVencimientoModulo);                               
                
                  for (cronograma itemSemana : cronogramaAcademicoD.mostrarFechaCronogramaAcademico(itemCronogramaVencimientoModulo.cronogramaAcademicoVencimientoModulo.getFechaInicio(),itemCronogramaVencimientoModulo.cronogramaAcademicoVencimientoModulo.getSemanas())) {
                      itemCronogramaVencimientoModulo.cronogramaL.add(itemSemana);                     
                    }
                    for (cronogramaAcademicoC itemAcademico : cronogramaAcademicoD.mostrarCronogramaAcademico(itemVencimiento.institucion,itemVencimiento.periodo,itemVencimiento.vencimiento,itemCronogramaVencimientoModulo.cronogramaAcademicoVencimientoModulo.getModulo())) {
                        itemCronogramaVencimientoModulo.cronogramaAcademicoL.add(itemAcademico);                       
                    }
                itemVencimiento.cronogramaVencimientoModuloL.add(itemCronogramaVencimientoModulo);
            }
          
            cronogramaVencimientoL.add(itemVencimiento);
            
        }
        return cronogramaVencimientoL;
    }
    
  

  

    /**
     * @return the cronogramaAcademicoD
     */
    public cronogramaAcademicoDAO getCronogramaAcademicoD() {
        return cronogramaAcademicoD;
    }

    /**
     * @param cronogramaAcademicoD the cronogramaAcademicoD to set
     */
    public void setCronogramaAcademicoD(cronogramaAcademicoDAO cronogramaAcademicoD) {
        this.cronogramaAcademicoD = cronogramaAcademicoD;
    }



    /**
     * @return the cronogramaVencimientoL
     */
    public List<cronogramaVencimiento> getCronogramaVencimientoL() {
        return cronogramaVencimientoL;
    }

    /**
     * @param cronogramaVencimientoL the cronogramaVencimientoL to set
     */
    public void setCronogramaVencimientoL(List<cronogramaVencimiento> cronogramaVencimientoL) {
        this.cronogramaVencimientoL = cronogramaVencimientoL;
    }
    
    
    
    public class cronogramaVencimientoModulo{
        private cronogramaAcademicoVencimientoModuloC cronogramaAcademicoVencimientoModulo;
        private List<cronograma> cronogramaL=new ArrayList<>();
        private List<cronogramaAcademicoC> cronogramaAcademicoL=new ArrayList<>();

        public cronogramaVencimientoModulo() {
        }

        public cronogramaVencimientoModulo(cronogramaAcademicoVencimientoModuloC cronogramaAcademicoVencimientoModulo) {
            this.cronogramaAcademicoVencimientoModulo = cronogramaAcademicoVencimientoModulo;
        }
        

        /**
         * @return the cronogramaAcademicoVencimientoModulo
         */
        public cronogramaAcademicoVencimientoModuloC getCronogramaAcademicoVencimientoModulo() {
            return cronogramaAcademicoVencimientoModulo;
        }

        /**
         * @param cronogramaAcademicoVencimientoModulo the cronogramaAcademicoVencimientoModulo to set
         */
        public void setCronogramaAcademicoVencimientoModulo(cronogramaAcademicoVencimientoModuloC cronogramaAcademicoVencimientoModulo) {
            this.cronogramaAcademicoVencimientoModulo = cronogramaAcademicoVencimientoModulo;
        }

        /**
         * @return the cronogramaL
         */
        public List<cronograma> getCronogramaL() {
            return cronogramaL;
        }

        /**
         * @param cronogramaL the cronogramaL to set
         */
        public void setCronogramaL(List<cronograma> cronogramaL) {
            this.cronogramaL = cronogramaL;
        }

        /**
         * @return the cronogramaAcademicoL
         */
        public List<cronogramaAcademicoC> getCronogramaAcademicoL() {
            return cronogramaAcademicoL;
        }

        /**
         * @param cronogramaAcademicoL the cronogramaAcademicoL to set
         */
        public void setCronogramaAcademicoL(List<cronogramaAcademicoC> cronogramaAcademicoL) {
            this.cronogramaAcademicoL = cronogramaAcademicoL;
        }
    }

   
    
    public static class cronogramaVencimiento{
        private int institucion;
        private String periodo;
        private int vencimiento;
        private Date fechaInicio;
        private String titulo;
        private int semana;
        private List<cronogramaVencimientoModulo> cronogramaVencimientoModuloL=new ArrayList<>();
        
        
       

        public cronogramaVencimiento(int institucion, String periodo, int vencimiento, Date fechaInicio, String titulo, int semana) {
            this.institucion = institucion;
            this.periodo = periodo;
            this.vencimiento = vencimiento;
            this.fechaInicio = fechaInicio;
            this.titulo = titulo;
            this.semana = semana;
        }

        public cronogramaVencimiento() {
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
         * @return the vencimiento
         */
        public int getVencimiento() {
            return vencimiento;
        }

        /**
         * @param vencimiento the vencimiento to set
         */
        public void setVencimiento(int vencimiento) {
            this.vencimiento = vencimiento;
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
         * @return the titulo
         */
        public String getTitulo() {
            return titulo;
        }

        /**
         * @param titulo the titulo to set
         */
        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        /**
         * @return the semana
         */
        public int getSemana() {
            return semana;
        }

        /**
         * @param semana the semana to set
         */
        public void setSemana(int semana) {
            this.semana = semana;
        }


        /**
         * @return the cronogramaVencimientoModuloL
         */
        public List<cronogramaVencimientoModulo> getCronogramaVencimientoModuloL() {
            return cronogramaVencimientoModuloL;
        }

        /**
         * @param cronogramaVencimientoModuloL the cronogramaVencimientoModuloL to set
         */
        public void setCronogramaVencimientoModuloL(List<cronogramaVencimientoModulo> cronogramaVencimientoModuloL) {
            this.cronogramaVencimientoModuloL = cronogramaVencimientoModuloL;
        }
        
    }
   
    
    public static class cronograma{
        private int semana;
        private Date fechaIni;
        private Date fechaFin;
        private String descripcion;

        /**
         * @return the semana
         */
        public int getSemana() {
            return semana;
        }

        /**
         * @param semana the semana to set
         */
        public void setSemana(int semana) {
            this.semana = semana;
        }

        /**
         * @return the fechaIni
         */
        public Date getFechaIni() {
            return fechaIni;
        }

        /**
         * @param fechaIni the fechaIni to set
         */
        public void setFechaIni(Date fechaIni) {
            this.fechaIni = fechaIni;
        }

        /**
         * @return the fechaFin
         */
        public Date getFechaFin() {
            return fechaFin;
        }

        /**
         * @param fechaFin the fechaFin to set
         */
        public void setFechaFin(Date fechaFin) {
            this.fechaFin = fechaFin;
        }

        /**
         * @return the descripcion
         */
        public String getDescripcion() {
            return descripcion;
        }

        /**
         * @param descripcion the descripcion to set
         */
        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }
    }
}
