
package Beans;

import DAO.feriadoDAO;
import DAO.horarioSemanalDAO;
import ENTIDAD.feriadosC;
import ENTIDAD.horarioCuerpoC;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="horarioSemanalAlumnoB")
@ViewScoped
public class horarioSemanalAlumno {
    
    private Date fechaInicio=util.fechaHoy();
    private List<detalleDia> detalleDiaL;
    
       
    @ManagedProperty(value ="#{usuarioB}")
    private usuario usuB;

 
    @PostConstruct
    public void init() {
    
        int diaLunes=util.datePart(0, fechaInicio)-2;
        fechaInicio=util.dateAdd(fechaInicio, 5, -diaLunes);
        mostrarHorario();
    }
    
    
    horarioSemanalDAO horarioSemanalD;
    feriadoDAO feriadoD;
     public void mostrarHorario(){
        horarioSemanalD=new horarioSemanalDAO();        
        feriadoD=new feriadoDAO();
        detalleDiaL=new ArrayList<>();
        for(int dia=1;dia<=7;dia++){
            detalleDia itemDia=new detalleDia(fechaInicio, dia);
            
             for (feriadosC itemFeriado : feriadoD.mostrarFeriados(fechaInicio)) {
                itemDia.getFeriadosL().add(itemFeriado);
                
            }
            
            
            for (horarioCuerpoC itemHorario : horarioSemanalD.mostrarHorarioAlumno("%",usuB.getPeriodoS(),usuB.getUsu(), dia,fechaInicio)) {
                itemDia.getDetalleHorariol().add(itemHorario);
            }
            detalleDiaL.add(itemDia);
            fechaInicio=util.dateAdd(fechaInicio, 5, 1);
        }
        
        
        
        
    }
    
    
      public void siguiente(){
        fechaInicio=util.dateAdd(fechaInicio, 5, 0);
        mostrarHorario();
    
    }
    
    public void anterior(){
        fechaInicio=util.dateAdd(fechaInicio, 5, -14);
        mostrarHorario();
    }
    
    public List<detalleDia> getDetalleDiaL() {
        return detalleDiaL;
    }
    public void setDetalleDiaL(List<detalleDia> detalleDiaL) {
        this.detalleDiaL = detalleDiaL;
    }
    public usuario getUsuB() {
        return usuB;
    }
    public void setUsuB(usuario usuB) {
        this.usuB = usuB;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static class detalleDia{
        private Date fecha;
        private int dia;
        private List<horarioCuerpoC> detalleHorariol=new ArrayList<>();
        private List<feriadosC> feriadosL=new ArrayList<>();

        public detalleDia() {
        }

        public detalleDia(Date fecha, int dia) {
            this.fecha = fecha;
            this.dia = dia;
        }
        public Date getFecha() {
            return fecha;
        }
        public void setFecha(Date fecha) {
            this.fecha = fecha;
        }
        public int getDia() {
            return dia;
        }
        public void setDia(int dia) {
            this.dia = dia;
        }
        public List<horarioCuerpoC> getDetalleHorariol() {
            return detalleHorariol;
        }
        public void setDetalleHorariol(List<horarioCuerpoC> detalleHorariol) {
            this.detalleHorariol = detalleHorariol;
        }
        public List<feriadosC> getFeriadosL() {
            return feriadosL;
        }
        public void setFeriadosL(List<feriadosC> feriadosL) {
            this.feriadosL = feriadosL;
        }
    }
    
    
    
    
    
    
    
    
    
    
    

    public Date getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

   
}
