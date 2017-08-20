

package ENTIDAD;

import java.io.Serializable;

public class tipoPersonaC implements Serializable {

	private static final long serialVersionUID = 1L;
	private int tipoPersona;
    private String descripcion;
    private String abreviatura;
    private int estadoRegistro;

    public tipoPersonaC() {
    }

    public tipoPersonaC(int tipoPersona, String descripcion, String abreviatura, int estadoRegistro) {
        this.tipoPersona = tipoPersona;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
        this.estadoRegistro = estadoRegistro;
    }
    public int getTipoPersona() {
        return tipoPersona;
    }
    public void setTipoPersona(int tipoPersona) {
        this.tipoPersona = tipoPersona;
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
