

package  ENTIDAD;

import java.io.Serializable;

public class alumnoCarreraC implements Serializable {

	private static final long serialVersionUID = 1L;
	private int institucion;
    private String carrera;
    private String alumno;
    private String malla;
    private String categoria;
    private String descuento;
    private double creditosAcumulados;
    private double promedioPonderado;
    private int ordenPonderado;
    private String periodoIngreso;
    private int estadoRegistro;
    
    public alumnoCarreraC() {
		
	}
    
    
    public alumnoCarreraC( int institucion,String carrera, String alumno,String malla, String categoria,String descuento, double creditosAcumulados, double promedioPonderado, int ordenPonderado, String periodoIngreso, int estadoRegistro) {
	this.institucion=institucion;
	this.carrera=carrera;
	this.alumno=alumno;
	this.malla=malla;
	this.categoria=categoria;
	this.descuento=descuento;
	this.creditosAcumulados=creditosAcumulados;
	this.promedioPonderado=promedioPonderado;
	this.ordenPonderado=ordenPonderado;
	this.periodoIngreso=periodoIngreso;
	this.estadoRegistro=estadoRegistro;
			
  	}
    
    public int getInstitucion() {
        return institucion;
    }
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }
    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    public String getAlumno() {
        return alumno;
    }
    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }
    public String getMalla() {
        return malla;
    }
    public void setMalla(String malla) {
        this.malla = malla;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public String getDescuento() {
        return descuento;
    }
    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }
    public double getCreditosAcumulados() {
        return creditosAcumulados;
    }
    public void setCreditosAcumulados(double creditosAcumulados) {
        this.creditosAcumulados = creditosAcumulados;
    }
    public double getPromedioPonderado() {
        return promedioPonderado;
    }
    public void setPromedioPonderado(double promedioPonderado) {
        this.promedioPonderado = promedioPonderado;
    }
    public int getOrdenPonderado() {
        return ordenPonderado;
    }
    public void setOrdenPonderado(int ordenPonderado) {
        this.ordenPonderado = ordenPonderado;
    }
 
    public String getPeriodoIngreso() {
		return periodoIngreso;
	}
	public void setPeriodoIngreso(String periodoIngreso) {
		this.periodoIngreso = periodoIngreso;
	}
	public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    
}
