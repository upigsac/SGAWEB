

package ENTIDAD;

import java.io.Serializable;

public class personalContratoComisionC implements Serializable {

	private static final long serialVersionUID = 1L;
	private String personal;
    private int contrato;
    private int comision;
    private int asignacion;
    private int estadoRegistro;

    public personalContratoComisionC() {
    }

    public personalContratoComisionC(String personal, int contrato, int comision,int asignacion, int estadoRegistro) {
        this.personal = personal;
        this.contrato = contrato;
        this.comision = comision;
        this.asignacion=asignacion;
        this.estadoRegistro = estadoRegistro;
    }
    
    
    
    
    public int getAsignacion() {
		return asignacion;
	}

	public void setAsignacion(int asignacion) {
		this.asignacion = asignacion;
	}

	public String getPersonal() {
        return personal;
    }
    public void setPersonal(String personal) {
        this.personal = personal;
    }
    public int getContrato() {
        return contrato;
    }
    public void setContrato(int contrato) {
        this.contrato = contrato;
    }
    public int getComision() {
        return comision;
    }
    public void setComision(int comision) {
        this.comision = comision;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
}
