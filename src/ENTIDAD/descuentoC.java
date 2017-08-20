
package ENTIDAD;

import java.io.Serializable;

public class descuentoC implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String descuento;
    private String descripcion;
    private int tipoDescuento;
    private double cantidad;
    private int tipoMoneda;
    private int estadoRegistro;

    public descuentoC() {
    }

    public descuentoC(String descuento, String descripcion, int tipoDescuento, double cantidad, int tipoMoneda, int estadoRegistro) {
        this.descuento = descuento;
        this.descripcion = descripcion;
        this.tipoDescuento = tipoDescuento;
        this.cantidad = cantidad;
        this.tipoMoneda = tipoMoneda;
        this.estadoRegistro = estadoRegistro;
    }
    public String getDescuento() {
        return descuento;
    }
    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getTipoDescuento() {
        return tipoDescuento;
    }
    public void setTipoDescuento(int tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }
    public double getCantidad() {
        return cantidad;
    }
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    public int getTipoMoneda() {
        return tipoMoneda;
    }
    public void setTipoMoneda(int tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
}
