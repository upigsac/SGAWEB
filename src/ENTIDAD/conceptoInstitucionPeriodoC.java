package ENTIDAD;
public class conceptoInstitucionPeriodoC {
    private int institucion;
    private String periodo;
    private String concepto;
    private int tipoConcepto;
    private double montoPago;
    private int tipoMoneda;
    private int tipoInteres;
    private double montoInteres;
    private int estadoRegistro;
    
    public conceptoInstitucionPeriodoC() {
		// TODO Auto-generated constructor stub
	}
    
    public conceptoInstitucionPeriodoC( int institucion, String periodo,String concepto,int tipoConcepto,double montoPago,int tipoMoneda,int tipoInteres,double montoInteres,int estadoRegistro) 
    {
    	this.institucion=institucion;
    	this.periodo=periodo;
    	this.concepto=concepto;
    	this.tipoConcepto=tipoConcepto;
    	this.montoPago=montoPago;
    	this.tipoMoneda=tipoMoneda;
    	this.tipoInteres=tipoInteres;
    	this.montoInteres=montoInteres;
    	this.estadoRegistro=estadoRegistro;
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
    public int getTipoConcepto() {
        return tipoConcepto;
    }
    public void setTipoConcepto(int tipoConcepto) {
        this.tipoConcepto = tipoConcepto;
    }
    public double getMontoPago() {
        return montoPago;
    }
    public void setMontoPago(double montoPago) {
        this.montoPago = montoPago;
    }
    public int getTipoMoneda() {
        return tipoMoneda;
    }
    public void setTipoMoneda(int tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }
    public int getTipoInteres() {
        return tipoInteres;
    }
    public void setTipoInteres(int tipoInteres) {
        this.tipoInteres = tipoInteres;
    }
    public double getMontoInteres() {
        return montoInteres;
    }
    public void setMontoInteres(double montoInteres) {
        this.montoInteres = montoInteres;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
}
