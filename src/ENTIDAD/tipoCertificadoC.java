
package ENTIDAD;

import java.io.Serializable;

public class tipoCertificadoC implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private int certificado;
    private String descripcion;
    private String abreviatura;
    private String reporte;
    private String formato;
    private int estadoRegistro;
    
    public tipoCertificadoC() {
		// TODO Auto-generated constructor stub
	}

    public int getCertificado() {
        return certificado;
    }

    
    public void setCertificado(int certificado) {
        this.certificado = certificado;
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

    public String getReporte() {
        return reporte;
    }

   
    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public int getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
}
