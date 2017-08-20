package ENTIDAD;

import java.io.Serializable;

public class rolExamenC implements Serializable {

	private static final long serialVersionUID = 1L;
	private int institucion;
	private String periodo;
	private String carrera;
	private int vencimiento;
	private String ruta;
	private String archivo;
	private String tipoExamen;
	private int estadoRegistro;
	
	public rolExamenC() {
		// TODO Auto-generated constructor stub
	}
	
	
	public rolExamenC( int institucion,String periodo,String carrera,int vencimiento,String ruta,String archivo,String tipoExamen,int estadoRegistro) {
		this.institucion=institucion;
		this.periodo=periodo;
		this.carrera=carrera;
		this.vencimiento=vencimiento;
		this.ruta=ruta;
		this.archivo=archivo;
		this.tipoExamen=tipoExamen;
		this.estadoRegistro=estadoRegistro;
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
	
	
	
	public String getCarrera() {
		return carrera;
	}









	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}









	public int getVencimiento() {
		return vencimiento;
	}
	public void setVencimiento(int vencimiento) {
		this.vencimiento = vencimiento;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	public String getTipoExamen() {
		return tipoExamen;
	}
	public void setTipoExamen(String tipoArchivo) {
		this.tipoExamen = tipoArchivo;
	}
	public int getEstadoRegistro() {
		return estadoRegistro;
	}
	public void setEstadoRegistro(int estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
}
