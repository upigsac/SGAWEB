

package ENTIDAD;

import java.io.Serializable;
import java.util.Date;

public class personalContratoC implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int institucion;
	private String periodo;
	private String personal;
    private int contrato;
    private int tipoPersonal;
    private int tipoContrato;
    private int periocidad;
    private Date fechaInicio;
    private Date fechaFin;
    private double basico;
    private double movilida;
    private double bonificacion;
    private String objetivo;
    private int situacion;
    
    private int estadoRegistro;

    public personalContratoC() {
    }

    public personalContratoC(int institucion,String periodo,String personal, int contrato, int tipoPersonal, int tipoContrato, int periocidad, Date fechaInicio, Date fechaFin, double basico, double movilida, double bonificacion, String objetivo, int situacion, int estadoRegistro) {
    	this.institucion=institucion;
    	this.periodo=periodo;
        this.personal = personal;
        this.contrato = contrato;
        this.tipoPersonal = tipoPersonal;
        this.tipoContrato = tipoContrato;
        this.periocidad = periocidad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.basico = basico;
        this.movilida = movilida;
        this.bonificacion = bonificacion;
        this.objetivo = objetivo;
        this.situacion = situacion;
        this.estadoRegistro = estadoRegistro;
    }

    

    
    public int getInstitucion() {
		return institucion;
	}

	public void setInstitucion(int instituccion) {
		this.institucion = instituccion;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getPersonal() {
        return personal;
    }
    public void setPersonal(String personal) {
        this.personal = personal;
    }
    public int getContrato() {
        return contrato;
    }
    public void setContrato(int contrato) {
        this.contrato = contrato;
    }
    public int getTipoContrato() {
        return tipoContrato;
    }
    public void setTipoContrato(int tipoContrato) {
        this.tipoContrato = tipoContrato;
    }
    public int getPeriocidad() {
        return periocidad;
    }
    public void setPeriocidad(int periocidad) {
        this.periocidad = periocidad;
    }
    public Date getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public Date getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    public double getBasico() {
        return basico;
    }
    public void setBasico(double basico) {
        this.basico = basico;
    }
    public String getObjetivo() {
        return objetivo;
    }
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    public double getBonificacion() {
        return bonificacion;
    }
    public void setBonificacion(double bonificacion) {
        this.bonificacion = bonificacion;
    }
    public double getMovilida() {
        return movilida;
    }
    public void setMovilida(double movilida) {
        this.movilida = movilida;
    }
    public int getSituacion() {
        return situacion;
    }
    public void setSituacion(int situacion) {
        this.situacion = situacion;
    }
    public int getTipoPersonal() {
        return tipoPersonal;
    }
    public void setTipoPersonal(int tipoPersonal) {
        this.tipoPersonal = tipoPersonal;
    }

 
}
