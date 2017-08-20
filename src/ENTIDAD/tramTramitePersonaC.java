/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package  ENTIDAD;

import java.io.Serializable;
import java.util.Date;

public class tramTramitePersonaC implements Serializable {
    
   
	private static final long serialVersionUID = 1L;
	private int item;
    private String expediente;
    private String fut;
    private int tipoTramite;
    private String tipoPersona;
    private String personaEmpresa;
    private String alumno;
    private int tramite;
    private String asunto;
    private String anexo;
    private Date fechaIngreso;
    private Date horaIngreso;
    private Date fechaFin;
    private int prioridad;
    private String observacion;
    private int institucion;
    private String  periodo;
    private int estadoRegistro;
    
    public tramTramitePersonaC() {
		
	}
    

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

   
    public String getExpediente() {
        return expediente;
    }

  
    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

  
    public String getAlumno() {
        return alumno;
    }

  
    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

 
    public int getTramite() {
        return tramite;
    }

   
    public void setTramite(int tramite) {
        this.tramite = tramite;
    }

  
    public String getAsunto() {
        return asunto;
    }

 
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getAnexo() {
        return anexo;
    }

   
    public void setAnexo(String anexo) {
        this.anexo = anexo;
    }

 
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

  
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

  
    public int getEstadoRegistro() {
        return estadoRegistro;
    }

 
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    
    public int getPrioridad() {
        return prioridad;
    }

  
    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

   
    public Date getHoraIngreso() {
        return horaIngreso;
    }

 
    public void setHoraIngreso(Date horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

 
    public String getObservacion() {
        return observacion;
    }

   
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

 
    public Date getFechaFin() {
        return fechaFin;
    }

   
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

 
    public String getFut() {
        return fut;
    }

  
    public void setFut(String fut) {
        this.fut = fut;
    }

   
    public String getTipoPersona() {
        return tipoPersona;
    }

  
    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

 
    public String getPersonaEmpresa() {
        return personaEmpresa;
    }

   
    public void setPersonaEmpresa(String personaEmpresa) {
        this.personaEmpresa = personaEmpresa;
    }



    public int getItem() {
        return item;
    }

    
    public void setItem(int item) {
        this.item = item;
    }

  
    public int getTipoTramite() {
        return tipoTramite;
    }

 
    public void setTipoTramite(int tipoTramite) {
        this.tipoTramite = tipoTramite;
    }
}
