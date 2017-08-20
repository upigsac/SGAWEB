

package ENTIDAD;

import java.io.Serializable;
import java.util.Date;

public class personalExperienciaLaboralC implements Serializable {
	
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String personal;
    private int item;
     private String cargo;
    private String empresa;
    private Date fechaInicio;
    private Date fechaFinal;
    private int estadoRegistro;
   
    
   
    

    public personalExperienciaLaboralC() {
    }

    public personalExperienciaLaboralC(String personal, int item, String cargo, String empresa, Date fechaInicio, Date fechaFinal, int estadoRegistro) {
        this.personal = personal;
        this.item = item;
        this.cargo = cargo;
        this.empresa = empresa;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.estadoRegistro = estadoRegistro;
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

    public String getPersonal() {
        return personal;
    }
    public void setPersonal(String personal) {
        this.personal = personal;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public int getItem() {
        return item;
    }
    public void setItem(int item) {
        this.item = item;
    }
    public String getEmpresa() {
        return empresa;
    }
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    
}

