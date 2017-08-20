package ENTIDAD;
import java.io.Serializable;

public class centroEducativoC implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String centroEducativo;
    private String descripcion;
    private String abreviatura;
    private int regimen;
    private int tipo;
    private int estadoRegistro;
    
    
    public centroEducativoC() {
		
	}
    
    public centroEducativoC( String centroEducativo,     String descripcion,     String abreviatura,     int regimen,     int tipo,     int estadoRegistro) {
    	this.centroEducativo=centroEducativo;
    	this.descripcion=descripcion;
    	this.abreviatura=abreviatura;
    	this.regimen=regimen;
    	this.tipo=tipo;
    	this.estadoRegistro=estadoRegistro;
  	}

   
    public String getCentroEducativo() {
        return centroEducativo;
    }
    public void setCentroEducativo(String centroEducativo) {
        this.centroEducativo = centroEducativo;
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
    public int getTipo() {
        return tipo;
    }
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    public int getRegimen() {
        return regimen;
    }
    public void setRegimen(int regimen) {
        this.regimen = regimen;
    }

   

  
}
