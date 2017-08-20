package ENTIDAD;

import java.io.Serializable;

public class cicloCarreraC implements Serializable {

	private static final long serialVersionUID = 1L;
	private int institucion;
    private String carrera;
    private int ciclo;
    private int estadoRegistro;
    
    public cicloCarreraC() {
		// TODO Auto-generated constructor stub
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
    public int getCiclo() {
        return ciclo;
    }
    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
}
