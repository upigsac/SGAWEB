
package ENTIDAD;

import java.io.Serializable;

public class oficinaC implements Serializable{
    
    
	private static final long serialVersionUID = 1L;
	private int institucion;
    private int oficina;
    private String descripcion;
    private String abreviatura;
    private String anexo;
    private String classIcon;
    private int estado_registro;

 
    public int getInstitucion() {
        return institucion;
    }

   
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }

    
    public int getOficina() {
        return oficina;
    }

   
    public void setOficina(int oficina) {
        this.oficina = oficina;
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

    
    public int getEstado_registro() {
        return estado_registro;
    }

   
    public void setEstado_registro(int estado_registro) {
        this.estado_registro = estado_registro;
    }

    
    public String getAnexo() {
        return anexo;
    }

   
    public void setAnexo(String anexo) {
        this.anexo = anexo;
    }

    
    public String getClassIcon() {
        return classIcon;
    }

    
    public void setClassIcon(String classIcon) {
        this.classIcon = classIcon;
    }
    
}
