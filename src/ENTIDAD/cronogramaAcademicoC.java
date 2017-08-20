package ENTIDAD;

import java.io.Serializable;

public class cronogramaAcademicoC implements Serializable {
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int institucion;
    private String periodo;
    private int vencimiento;
    private int semana;
    private String descripcion;
    private int duracion;
    private boolean rotacion;
    private int estadoRegistro;
    
    public cronogramaAcademicoC() {
		// TODO Auto-generated constructor stub
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
    public int getVencimiento() {
        return vencimiento;
    }
    public void setVencimiento(int vencimiento) {
        this.vencimiento = vencimiento;
    }
    public int getSemana() {
        return semana;
    }
    public void setSemana(int semana) {
        this.semana = semana;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getDuracion() {
        return duracion;
    }
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    public boolean isRotacion() {
        return rotacion;
    }
    public void setRotacion(boolean rotacion) {
        this.rotacion = rotacion;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
   
}
