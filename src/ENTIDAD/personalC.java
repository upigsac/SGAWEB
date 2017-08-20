

package ENTIDAD;

import java.io.Serializable;
import java.util.Date;

public class personalC implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String personal;
    private String persona;
    private boolean codigoPlanilla;
    private String cPersonal;
    private int cargo;
    private int oficina;
    private String esSalud;
    private String cuspp;
    private int turno;
    private int ocupacion;
    private int formaPago;
    private int tipoMoneda;
    private int tipoPersonal;
    private String numeroCTS;
    private int tipoRegimen;
    private int regimenPension;
    private Date fechaRegimen;
    private Date fechaIngreso;
    private int bancoRemuneracion;
    private String numeroCuenta;
    private int bancoCTS;
    private int eps;
    private int sede;
    private int centroCosto;
    private int estadoRegistro;
    
    private int situacion;

    public personalC() {
    }

    public personalC(String personal, String persona, boolean codigoPlanilla, String cPersonal, int estadoRegistro) {
        this.personal = personal;
        this.persona = persona;
        this.codigoPlanilla = codigoPlanilla;
        this.cPersonal = cPersonal;
        this.estadoRegistro = estadoRegistro;
    }

    
    
    
    public String getPersonal() {
        return personal;
    }
    public void setPersonal(String personal) {
        this.personal = personal;
    }
    public String getPersona() {
        return persona;
    }
    public void setPersona(String persona) {
        this.persona = persona;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    public String getcPersonal() {
        return cPersonal;
    }
    public void setcPersonal(String cPersonal) {
        this.cPersonal = cPersonal;
    }

    public int getCargo() {
        return cargo;
    }
    public void setCargo(int cargo) {
        this.cargo = cargo;
    }
    public int getOficina() {
        return oficina;
    }
    public void setOficina(int oficina) {
        this.oficina = oficina;
    }
    public String getEsSalud() {
        return esSalud;
    }
    public void setEsSalud(String esSalud) {
        this.esSalud = esSalud;
    }
    public String getCuspp() {
        return cuspp;
    }
    public void setCuspp(String cuspp) {
        this.cuspp = cuspp;
    }
    public int getTurno() {
        return turno;
    }
    public void setTurno(int turno) {
        this.turno = turno;
    }
    public int getOcupacion() {
        return ocupacion;
    }
    public void setOcupacion(int ocupacion) {
        this.ocupacion = ocupacion;
    }
    public int getFormaPago() {
        return formaPago;
    }
    public void setFormaPago(int formaPago) {
        this.formaPago = formaPago;
    }
    public int getTipoMoneda() {
        return tipoMoneda;
    }
    public void setTipoMoneda(int tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }
    public String getNumeroCTS() {
        return numeroCTS;
    }
    public void setNumeroCTS(String numeroCTS) {
        this.numeroCTS = numeroCTS;
    }
    public int getTipoRegimen() {
        return tipoRegimen;
    }
    public void setTipoRegimen(int tipoRegimen) {
        this.tipoRegimen = tipoRegimen;
    }
    public int getRegimenPension() {
        return regimenPension;
    }
    public void setRegimenPension(int regimenPension) {
        this.regimenPension = regimenPension;
    }
    public Date getFechaIngreso() {
        return fechaIngreso;
    }
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    public int getBancoRemuneracion() {
        return bancoRemuneracion;
    }
    public void setBancoRemuneracion(int bancoRemuneracion) {
        this.bancoRemuneracion = bancoRemuneracion;
    }
    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
    public int getBancoCTS() {
        return bancoCTS;
    }
    public void setBancoCTS(int bancoCTS) {
        this.bancoCTS = bancoCTS;
    }
    public Date getFechaRegimen() {
        return fechaRegimen;
    }
    public void setFechaRegimen(Date fechaRegimen) {
        this.fechaRegimen = fechaRegimen;
    }
    public int getEps() {
        return eps;
    }
    public void setEps(int eps) {
        this.eps = eps;
    }
    public int getSede() {
        return sede;
    }
    public void setSede(int sede) {
        this.sede = sede;
    }
    public int getSituacion() {
        return situacion;
    }
    public void setSituacion(int situacion) {
        this.situacion = situacion;
    }
    public int getCentroCosto() {
        return centroCosto;
    }
    public void setCentroCosto(int centroCosto) {
        this.centroCosto = centroCosto;
    }
    public boolean isCodigoPlanilla() {
        return codigoPlanilla;
    }
    public void setCodigoPlanilla(boolean codigoPlanilla) {
        this.codigoPlanilla = codigoPlanilla;
    }
    public int getTipoPersonal() {
        return tipoPersonal;
    }
    public void setTipoPersonal(int tipoPersonal) {
        this.tipoPersonal = tipoPersonal;
    }
    
}
