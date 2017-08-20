package ENTIDAD;

import java.util.Date;

public class detallePagoPersonalC {
    private int codigo;
    private String personal;
    private String mes;
    private String tipoConcepto;
    private String observacion;
    private double montoTotal;
    private double montoPago;
    private double montoSaldo;
    private Date fechaPago;
    private String tipoPago ;
    private String numeroComprobante;
    private int estadoRegistro;

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the personal
     */
    public String getPersonal() {
        return personal;
    }

    /**
     * @param personal the personal to set
     */
    public void setPersonal(String personal) {
        this.personal = personal;
    }

    /**
     * @return the mes
     */
    public String getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(String mes) {
        this.mes = mes;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * @return the montoTotal
     */
    public double getMontoTotal() {
        return montoTotal;
    }

    /**
     * @param montoTotal the montoTotal to set
     */
    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    /**
     * @return the montoPago
     */
    public double getMontoPago() {
        return montoPago;
    }

    /**
     * @param montoPago the montoPago to set
     */
    public void setMontoPago(double montoPago) {
        this.montoPago = montoPago;
    }

    /**
     * @return the montoSaldo
     */
    public double getMontoSaldo() {
        return montoSaldo;
    }

    /**
     * @param montoSaldo the montoSaldo to set
     */
    public void setMontoSaldo(double montoSaldo) {
        this.montoSaldo = montoSaldo;
    }

    /**
     * @return the fechaPago
     */
    public Date getFechaPago() {
        return fechaPago;
    }

    /**
     * @param fechaPago the fechaPago to set
     */
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * @return the tipoPago
     */
    public String getTipoPago() {
        return tipoPago;
    }

    /**
     * @param tipoPago the tipoPago to set
     */
    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    /**
     * @return the numeroComprobante
     */
    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    /**
     * @param numeroComprobante the numeroComprobante to set
     */
    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    /**
     * @return the estadoRegistro
     */
    public int getEstadoRegistro() {
        return estadoRegistro;
    }

    /**
     * @param estadoRegistro the estadoRegistro to set
     */
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    /**
     * @return the tipoConcepto
     */
    public String getTipoConcepto() {
        return tipoConcepto;
    }

    /**
     * @param tipoConcepto the tipoConcepto to set
     */
    public void setTipoConcepto(String tipoConcepto) {
        this.tipoConcepto = tipoConcepto;
    }
}
