package ENTIDAD;
import java.io.Serializable;
public class montoC implements Serializable {

	private static final long serialVersionUID = 1L;
	private int monto;
    private int gradoInstruccion;
    private int desde;
    private int hasta;
    private double totalMonto;
    private int estadoRegistro;
    
    public montoC() {
	
	}
    public montoC(int monto,int gradoInstruccion,int desde,int hasta, double totalMonto,int estadoRegistro) {
    	this.monto=monto;
    	this.gradoInstruccion=gradoInstruccion;
    	this.desde=desde;
    	this.hasta=hasta;
    	this.totalMonto=totalMonto;
    	this.estadoRegistro=estadoRegistro;
    }
    
    public int getMonto() {
        return monto;
    }
    public void setMonto(int monto) {
        this.monto = monto;
    }
    public int getGradoInstruccion() {
        return gradoInstruccion;
    }
    public void setGradoInstruccion(int gradoInstruccion) {
        this.gradoInstruccion = gradoInstruccion;
    }
    public int getDesde() {
        return desde;
    }
    public void setDesde(int desde) {
        this.desde = desde;
    }
    public int getHasta() {
        return hasta;
    }
    public void setHasta(int hasta) {
        this.hasta = hasta;
    }
    public double getTotalMonto() {
        return totalMonto;
    }
    public void setTotalMonto(double totalMonto) {
        this.totalMonto = totalMonto;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
}
