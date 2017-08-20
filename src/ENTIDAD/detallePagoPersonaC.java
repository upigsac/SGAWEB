package ENTIDAD;

import java.io.Serializable;

public class detallePagoPersonaC implements Serializable {
	
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tipoDocumento;
    private String numeroComprobante;
    private String numeroFut;
    private String persona;
    private int institucion ;
    private String periodo;
    private String concepto;
    private double montoBase;
    private double montoParte;
    private double montoTotal;
    private double saldoItem;
    private double montoDescuento;
    private double montoDescuentoAdi;
    private String descuento;
    private String descuentoAdi; 
    private int tipoConcepto;
    private String estadoRegistro;
    
    public detallePagoPersonaC() {
		// TODO Auto-generated constructor stub
	}

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    
    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

  

   
    public String getNumeroFut() {
        return numeroFut;
    }

   
    public void setNumeroFut(String numeroFut) {
        this.numeroFut = numeroFut;
    }

   
    public String getPersona() {
        return persona;
    }

   
    public void setPersona(String persona) {
        this.persona = persona;
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

  
    public String getConcepto() {
        return concepto;
    }

   
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

   
    public String getEstadoRegistro() {
        return estadoRegistro;
    }

  
    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    
    public String getNumeroComprobante() {
        return numeroComprobante;
    }

  
    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    
    public double getMontoBase() {
        return montoBase;
    }

   
    public void setMontoBase(double montoBase) {
        this.montoBase = montoBase;
    }

   
    public double getMontoParte() {
        return montoParte;
    }

   
    public void setMontoParte(double montoParte) {
        this.montoParte = montoParte;
    }

 
    public double getMontoTotal() {
        return montoTotal;
    }

    
    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

  
    public int getTipoConcepto() {
        return tipoConcepto;
    }

  
    public void setTipoConcepto(int tipoConcepto) {
        this.tipoConcepto = tipoConcepto;
    }

    public double getSaldoItem() {
        return saldoItem;
    }

  
    public void setSaldoItem(double saldoItem) {
        this.saldoItem = saldoItem;
    }

    public double getMontoDescuento() {
        return montoDescuento;
    }

    
    public void setMontoDescuento(double montoDescuento) {
        this.montoDescuento = montoDescuento;
    }

   
    public String getDescuento() {
        return descuento;
    }

    
    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

  
    public String getDescuentoAdi() {
        return descuentoAdi;
    }

    
    public void setDescuentoAdi(String descuentoAdi) {
        this.descuentoAdi = descuentoAdi;
    }

   
    public double getMontoDescuentoAdi() {
        return montoDescuentoAdi;
    }

   
    public void setMontoDescuentoAdi(double montoDescuentoAdi) {
        this.montoDescuentoAdi = montoDescuentoAdi;
    }

  

    
}
