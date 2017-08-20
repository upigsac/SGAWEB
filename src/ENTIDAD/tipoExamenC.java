
package ENTIDAD;

import java.io.Serializable;

public class tipoExamenC implements Serializable {
    
   
	private static final long serialVersionUID = 1L;
	private String tipo_examen;
    private String descripcion;
    private String abreviatura;
    private String deslinea1;
    private String deslinea2;
    private int estado_registro;

    public tipoExamenC() {
		// TODO Auto-generated constructor stub
	}
  
    public String getTipo_examen() {
        return tipo_examen;
    }

   
    public void setTipo_examen(String tipo_examen) {
        this.tipo_examen = tipo_examen;
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
    public String getDeslinea1() {
        return deslinea1;
    }
    public void setDeslinea1(String deslinea1) {
        this.deslinea1 = deslinea1;
    }
    public String getDeslinea2() {
        return deslinea2;
    }
    public void setDeslinea2(String deslinea2) {
        this.deslinea2 = deslinea2;
    }
    public int getEstado_registro() {
        return estado_registro;
    }
    public void setEstado_registro(int estado_registro) {
        this.estado_registro = estado_registro;
    }
    
    
    
}

