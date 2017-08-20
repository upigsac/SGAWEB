package ENTIDAD;

import java.io.Serializable;
import java.util.Date;

public class personalCertificadoTrabajoC implements Serializable {

	private static final long serialVersionUID = 1L;
	private int certificado;
	private int tipo;
	private int tipoPersonal;
	private String personal;
	private int institucion;
	
	private Date fechaInicio;
	private Date fechaFinal;
	private int estadoRegistro;
	
	
	public personalCertificadoTrabajoC() {
		// TODO Auto-generated constructor stub
	}
	
	public personalCertificadoTrabajoC( int certificado,int tipo,int tipoPersonal,	 String personal,	 int institucion,	 Date fechaInicio,	 Date fechaFinal,	 int estadoRegistro) {
		this.certificado=certificado;
		this.tipo=tipo;
		this.tipoPersonal=tipoPersonal;
		this.personal=personal;
		this.institucion=institucion;
		this.fechaInicio=fechaInicio;
		this.fechaFinal=fechaFinal;
		this.estadoRegistro=estadoRegistro;
	}
	
	
	
	
	
	public int getTipoPersonal() {
		return tipoPersonal;
	}

	public void setTipoPersonal(int tipoPersonal) {
		this.tipoPersonal = tipoPersonal;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getCertificado() {
		return certificado;
	}
	public void setCertificado(int certificado) {
		this.certificado = certificado;
	}
	public String getPersonal() {
		return personal;
	}
	public void setPersonal(String personal) {
		this.personal = personal;
	}
	public int getInstitucion() {
		return institucion;
	}
	public void setInstitucion(int institucion) {
		this.institucion = institucion;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public int getEstadoRegistro() {
		return estadoRegistro;
	}
	public void setEstadoRegistro(int estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
	
}
