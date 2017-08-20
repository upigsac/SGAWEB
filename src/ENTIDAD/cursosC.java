package ENTIDAD;
import java.io.Serializable;


public class cursosC implements Serializable{
    
   
	private static final long serialVersionUID = 1L;
	private String curso;
    private String descripcion;
    private String abreviatura;    
    private int estadoRegistro;
    public cursosC() {
		// TODO Auto-generated constructor stub
	}
    
    public cursosC( String curso,     String descripcion,     String abreviatura,   int estadoRegistro) {
    	
    	this.curso=curso;
    	this.descripcion=descripcion;
    	this.abreviatura=abreviatura;
    	this.estadoRegistro=estadoRegistro;
  		
  	}
    

    public String getCurso() {
        return curso;
    }

    
    public void setCurso(String curso) {
        this.curso = curso;
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
    
}
