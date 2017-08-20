
package Beans;

import DAO.feriadoDAO;
import DAO.horarioSemanalDAO;
import ENTIDAD.feriadosC;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="horarioSemanalB")
@ViewScoped
public class horarioSemanal implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<detalleHorario> detalleHorarioL;
    private Date fechaInicio=util.fechaHoy();   
    private List<detalleDia> detalleDiaL;
    
    
    
    @ManagedProperty(value ="#{usuarioB}")
    private usuario usuB;
    
    feriadoDAO feriadoD;
    
    @PostConstruct
    public void init() {
    
        int diaLunes=util.datePart(0, fechaInicio)-2;
        fechaInicio=util.dateAdd(fechaInicio, 5, -diaLunes);
        mostrarHorario();
    }
    
   
    
    public void siguiente(){
        fechaInicio=util.dateAdd(fechaInicio, 5, 0);
        mostrarHorario();
    }
    
    public void anterior(){
        fechaInicio=util.dateAdd(fechaInicio, 5, -14);
        mostrarHorario();
    }
    
    
    
    public void mostrarHorario(){
        
        detalleDiaL=new ArrayList<>();
       
        
        for (int dia=1;dia<=7;dia++){
            detalleDia itemDia =new detalleDia(fechaInicio,dia);
            for (feriadosC itemFeriado : new feriadoDAO().mostrarFeriados(fechaInicio)) {
                itemDia.getFeriadosL().add(itemFeriado);
                
            }
            for(detalleHorario itemHorario :new horarioSemanalDAO().mostrarHorarioDocente("%",usuB.getPeriodoS(),usuB.getPersonal().getPersonal(), dia,fechaInicio)){
                itemDia.getDetalleHorarioL().add(itemHorario);
            }
            fechaInicio=util.dateAdd(fechaInicio, 5, 1);
            detalleDiaL.add(itemDia);
        }
        
    }
    
    
    horarioSemanalDAO horarioSemanalD;
    public List<detalleHorario> getDetalleHorarioL() {
        return detalleHorarioL;
    }
    public void setDetalleHorarioL(List<detalleHorario> detalleHorarioL) {
        this.detalleHorarioL = detalleHorarioL;
    }
    public Date getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
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
        private List<feriadosC> feriadosL=new ArrayList<>();
        private List<detalleHorario> detalleHorarioL=new ArrayList<>();
    

        public detalleDia(Date fecha, int dia) {
            this.fecha = fecha;
            this.dia = dia;
           
        }

        public detalleDia() {
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
        public List<feriadosC> getFeriadosL() {
            return feriadosL;
        }
        public void setFeriadosL(List<feriadosC> feriadosL) {
            this.feriadosL = feriadosL;
        }
        public List<detalleHorario> getDetalleHorarioL() {
            return detalleHorarioL;
        }
        public void setDetalleHorarioL(List<detalleHorario> detalleHorarioL) {
            this.detalleHorarioL = detalleHorarioL;
        }
    }
    
    public static class detalleHorario{
        private String personal;
        private String carrera;
        private String desCarrera;
        private String curso;
        private String desCurso;
        private String seccion;
        private String aula;
        private int tipoClase;
        private String desTipoClase;
        private Date horaInicio; 
        private Date horaFinal; 
        private int alto;
        private int top;

       
        public String getPersonal() {
            return personal;
        }
        public void setPersonal(String personal) {
            this.personal = personal;
        }
        public String getCarrera() {
            return carrera;
        }
        public void setCarrera(String carrera) {
            this.carrera = carrera;
        }
        public String getCurso() {
            return curso;
        }
        public void setCurso(String curso) {
            this.curso = curso;
        }
        public String getSeccion() {
            return seccion;
        }
        public void setSeccion(String seccion) {
            this.seccion = seccion;
        }
        public Date getHoraInicio() {
            return horaInicio;
        }
        public void setHoraInicio(Date horaInicio) {
            this.horaInicio = horaInicio;
        }
        public Date getHoraFinal() {
            return horaFinal;
        }
        public void setHoraFinal(Date horaFinal) {
            this.horaFinal = horaFinal;
        }
        public int getAlto() {
            return alto;
        }
        public void setAlto(int alto) {
            this.alto = alto;
        }
        public int getTop() {
            return top;
        }
        public void setTop(int top) {
            this.top = top;
        }
        public String getDesCarrera() {
            return desCarrera;
        }
        public void setDesCarrera(String desCarrera) {
            this.desCarrera = desCarrera;
        }
        public String getDesCurso() {
            return desCurso;
        }
        public void setDesCurso(String desCurso) {
            this.desCurso = desCurso;
        }
        public String getAula() {
            return aula;
        }
        public void setAula(String aula) {
            this.aula = aula;
        }
        public int getTipoClase() {
            return tipoClase;
        }
        public void setTipoClase(int tipoClase) {
            this.tipoClase = tipoClase;
        }
        public String getDesTipoClase() {
            return desTipoClase;
        }
        public void setDesTipoClase(String desTipoClase) {
            this.desTipoClase = desTipoClase;
        }
    }
   

    
}
