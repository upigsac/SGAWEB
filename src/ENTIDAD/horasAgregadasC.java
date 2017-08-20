

package ENTIDAD;

import java.io.Serializable;

public class horasAgregadasC implements Serializable {

	private static final long serialVersionUID = 1L;
	private int anio;
    private int mes;
    private int dia;
    private int item;
    private double horas;
    private String cpersonal;
    private String observacion;
    private int estadoRegistro;

    public horasAgregadasC() {
    }

    public horasAgregadasC(int anio, int mes, int dia, int item, double horas, String cpersonal, String observacion, int estadoRegistro) {
        this.anio = anio;
        this.mes = mes;
        this.dia = dia;
        this.item = item;
        this.horas = horas;
        this.cpersonal = cpersonal;
        this.observacion = observacion;
        this.estadoRegistro = estadoRegistro;
    }
    public int getAnio() {
        return anio;
    }
    public void setAnio(int año) {
        this.anio = año;
    }
    public int getMes() {
        return mes;
    }
    public void setMes(int mes) {
        this.mes = mes;
    }
    public int getDia() {
        return dia;
    }
    public void setDia(int dia) {
        this.dia = dia;
    }
    public int getItem() {
        return item;
    }
    public void setItem(int item) {
        this.item = item;
    }
    public double getHoras() {
        return horas;
    }
    public void setHoras(double horas) {
        this.horas = horas;
    }
    public String getCpersonal() {
        return cpersonal;
    }
    public void setCpersonal(String cpersonal) {
        this.cpersonal = cpersonal;
    }
    public String getObservacion() {
        return observacion;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
            
}
