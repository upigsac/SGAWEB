
package ENTIDAD;

import java.io.Serializable;
import java.util.Date;


public class turnoC implements  Serializable{
  
	private static final long serialVersionUID = 1L;
	private int turno;
    private String descripcion;
    private String abreviatura;
    private Date horaInicio;
    private Date horaFin;
    private Date horaInicioMarcacion;
    private Date horaFinMarcacion;
    private int estadoRegistro;

    public turnoC() {
    }

    public turnoC(int turno, String descripcion, String abreviatura, Date horaInicio, Date horaFin, int estadoRegistro) {
        this.turno = turno;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estadoRegistro = estadoRegistro;
    }

    public turnoC(int turno, String descripcion, String abreviatura, Date horaInicio, Date horaFin, Date horaInicioMarcacion, Date horaFinMarcacion, int estadoRegistro) {
        this.turno = turno;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.horaInicioMarcacion = horaInicioMarcacion;
        this.horaFinMarcacion = horaFinMarcacion;
        this.estadoRegistro = estadoRegistro;
    }
    

    
    
    
    
    public int getTurno() {
        return turno;
    }

   
    public void setTurno(int turno) {
        this.turno = turno;
    }

   
    public String getDescripcion() {
        return descripcion;
    }

    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    public String getAbreviatura() {
        return abreviatura;
    }

    
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

  

  
    public int getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

   
    public Date getHoraInicio() {
        return horaInicio;
    }

   
    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

  
    public Date getHoraFin() {
        return horaFin;
    }

  
    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    
    public Date getHoraInicioMarcacion() {
        return horaInicioMarcacion;
    }

  
    public void setHoraInicioMarcacion(Date horaInicioMarcacion) {
        this.horaInicioMarcacion = horaInicioMarcacion;
    }

   
    public Date getHoraFinMarcacion() {
        return horaFinMarcacion;
    }

    
    public void setHoraFinMarcacion(Date horaFinMarcacion) {
        this.horaFinMarcacion = horaFinMarcacion;
    }
}
