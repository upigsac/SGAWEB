package ENTIDAD;

import java.io.Serializable;

public class conceptoC implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String concepto;
    private int tipoConcepto;
    private String descripcion;
    private String abreviatura;
    private boolean certificacion;
    private String carrera;
    private boolean entradaSalida;
    private String cuenta;
    private String rubro;
    
    private int estadoRegistro;
    
    public conceptoC() {
		// TODO Auto-generated constructor stub
	}
    
    public conceptoC( String concepto,int tipoConcepto,String descripcion, String abreviatura,boolean certificacion,String carrera,boolean entradaSalida,String cuenta, String rubro,int estadoRegistro) {
    	this.concepto=concepto;
    	this.tipoConcepto=tipoConcepto;
    	this.descripcion=descripcion;
    	this.abreviatura=abreviatura;
    	this.certificacion=certificacion;
    	this.carrera=carrera;
    	this.entradaSalida=entradaSalida;
    	this.cuenta=cuenta;
    	this.rubro=rubro;
    	this.estadoRegistro=estadoRegistro;
	}
    
    

	public String getConcepto() {
        return concepto;
    }

   
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

   
    public int getTipoConcepto() {
        return tipoConcepto;
    }

    public void setTipoConcepto(int tipoConcepto) {
        this.tipoConcepto = tipoConcepto;
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
    public boolean isCertificacion() {
        return certificacion;
    }
    public void setCertificacion(boolean certificacion) {
        this.certificacion = certificacion;
    }
    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
  
    public boolean isEntradaSalida() {
		return entradaSalida;
	}




	public void setEntradaSalida(boolean entradaSalida) {
		this.entradaSalida = entradaSalida;
	}




	public String getCuenta() {
		return cuenta;
	}




	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}




	public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    
    
    public String getRubro() {
  		return rubro;
  	}
    
  	public void setRubro(String rubro) {
  		this.rubro = rubro;
  	}
}
