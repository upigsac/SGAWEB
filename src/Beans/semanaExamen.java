
package Beans;

import DAO.cronogramaAcademicoDAO;
import DAO.cronogramaAcademicoVencimientoDAO;
import DAO.semanaExamenDAO;
import DAO.tipoExamenDAO;

import DAO.vencimientoDAO;
import ENTIDAD.cronogramaAcademicoVencimientoC;
import ENTIDAD.semanaExamenC;
import ENTIDAD.tipoExamenC;

import java.util.ArrayList;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "semanaExamenB")
@ViewScoped
public class semanaExamen {
 
  private cronogramaAcademicoVencimientoC cronogramaAcademicoVencimiento=new cronogramaAcademicoVencimientoC();
  
  private semanaExamenC semanaExamen=new semanaExamenC() ;
  private List<cronogramaAcademico.cronograma> cabeceraL=new ArrayList<>();
  private List<tipoExamenC> tipoExamenL;
  
  private List<vencimientoExamen> vencimientoExamenL=new ArrayList<>();

    private List<cronogramaAcademicoVencimientoC> cronogramaAcademicoVencimientoL=new ArrayList<>();
 
 
 
    semanaExamenDAO semanaExamenD;
    vencimientoDAO vencimientoD;
    cronogramaAcademicoDAO  cronogramaAcademicoD;
    cronogramaAcademicoVencimientoDAO cronogramaAcademicoVencimientoD;
    tipoExamenDAO tipoExamenD;
            
    public semanaExamen() {
      vencimientoD=new vencimientoDAO();
      
      
      cronogramaAcademicoVencimientoD=new cronogramaAcademicoVencimientoDAO();
      cronogramaAcademicoVencimientoL =cronogramaAcademicoVencimientoD.mostrarCronogramaAcademicoVencimiento(1, "20162");
       
       
    }
    
    public void seleccionCronogramaVencimiento(cronogramaAcademicoVencimientoC itemCronogramaAcademicoVencimiento){
        cronogramaAcademicoVencimiento=itemCronogramaAcademicoVencimiento;
        mostrarSemanaExamen();
    }
  
    
    public void mostrarSemanaExamen(){
        
        
        
        
            cronogramaAcademicoD=new cronogramaAcademicoDAO();
            cabeceraL= cronogramaAcademicoD.mostrarFechaCronogramaAcademico(cronogramaAcademicoVencimiento.getFechaInicio(),cronogramaAcademicoVencimiento.getSemana());
            semanaExamenD=new semanaExamenDAO();
            tipoExamenD=new tipoExamenDAO();
            vencimientoExamenL=new ArrayList<>();
            vencimientoExamen itemVencimientoExamen;
            for (semanaExamenC itemSemanaExamen : semanaExamenD.mostrarSemanaExamenes(cronogramaAcademicoVencimiento.getInstitucion(), cronogramaAcademicoVencimiento.getPeriodo(), cronogramaAcademicoVencimiento.getVencimiento())) {
                itemVencimientoExamen=new vencimientoExamen(itemSemanaExamen);
                itemVencimientoExamen.tipoExamen=tipoExamenD.mostrarTipoExamen(itemSemanaExamen.getTipoExamen());
                vencimientoExamenL.add(itemVencimientoExamen);
            }
            
            
         
            
            tipoExamenL=tipoExamenD.mostrarTipoExamen(cronogramaAcademicoVencimiento.getInstitucion(), cronogramaAcademicoVencimiento.getPeriodo(), cronogramaAcademicoVencimiento.getVencimiento());
            
        
    }
    public List<vencimientoExamen> getVencimientoExamenL() {
        return vencimientoExamenL;
    }
    public void setVencimientoExamenL(List<vencimientoExamen> vencimientoExamenL) {
        this.vencimientoExamenL = vencimientoExamenL;
    }
    
    
    public class vencimientoExamen{
        private semanaExamenC semanaExamen;
        private  tipoExamenC tipoExamen=new tipoExamenC();

        public vencimientoExamen() {
        }

        public vencimientoExamen(semanaExamenC semanaExamen) {
            this.semanaExamen = semanaExamen;
        }
        public semanaExamenC getSemanaExamen() {
            return semanaExamen;
        }
        public void setSemanaExamen(semanaExamenC semanaExamen) {
            this.semanaExamen = semanaExamen;
        }
        public tipoExamenC getTipoExamen() {
            return tipoExamen;
        }
        public void setTipoExamen(tipoExamenC tipoExamen) {
            this.tipoExamen = tipoExamen;
        }
        

        
    }
    
    
    
    public void nuevo(String tipoExamen){
        semanaExamen=new semanaExamenC();
        semanaExamen.setInstitucion(cronogramaAcademicoVencimiento.getInstitucion());
        semanaExamen.setPeriodo(cronogramaAcademicoVencimiento.getPeriodo());
        semanaExamen.setTipoExamen(tipoExamen);
        semanaExamen.setSemanaLibre(1);
        semanaExamen.setGrupo(cronogramaAcademicoVencimiento.getVencimiento());
        util.script("dlgRegistro.show()");
    }
    
    public void insertar(){
        semanaExamenD=new semanaExamenDAO();
        semanaExamenD.insertar(semanaExamen);    
         mostrarSemanaExamen();
        util.script("dlgRegistro.hide()");
        util.alert("se guardo correctamente");
    }
    public void eliminar(semanaExamenC item){
        semanaExamenD=new semanaExamenDAO();
        semanaExamenD.eliminar(item);
        mostrarSemanaExamen();
        util.alert("se eliminar correctamente");
    }
    public void editar(semanaExamenC item){
        semanaExamen=item;
        util.script("dlgRegistro.show()");
    }
  
    
    public List<cronogramaAcademico.cronograma> getCabeceraL() {
        return cabeceraL;
    }
    public void setCabeceraL(List<cronogramaAcademico.cronograma> cabeceraL) {
        this.cabeceraL = cabeceraL;
    }
    public semanaExamenC getSemanaExamen() {
        return semanaExamen;
    }
    public void setSemanaExamen(semanaExamenC semanaExamen) {
        this.semanaExamen = semanaExamen;
    }
    public cronogramaAcademicoVencimientoC getCronogramaAcademicoVencimiento() {
        return cronogramaAcademicoVencimiento;
    }
    public void setCronogramaAcademicoVencimiento(cronogramaAcademicoVencimientoC cronogramaAcademicoVencimiento) {
        this.cronogramaAcademicoVencimiento = cronogramaAcademicoVencimiento;
    }
    public List<tipoExamenC> getTipoExamenL() {
        return tipoExamenL;
    }
    public void setTipoExamenL(List<tipoExamenC> tipoExamenL) {
        this.tipoExamenL = tipoExamenL;
    }
    public List<cronogramaAcademicoVencimientoC> getCronogramaAcademicoVencimientoL() {
        return cronogramaAcademicoVencimientoL;
    }
    public void setCronogramaAcademicoVencimientoL(List<cronogramaAcademicoVencimientoC> cronogramaAcademicoVencimientoL) {
        this.cronogramaAcademicoVencimientoL = cronogramaAcademicoVencimientoL;
    }

 

}
