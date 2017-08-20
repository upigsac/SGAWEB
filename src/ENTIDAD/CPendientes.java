/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ENTIDAD;

public class CPendientes {

    private String cconcepto;
    private String abreviatura;
    private int institucion;
    private String carrera;
    private int tconcepto;
    private String descripcion;
    private String cuota;
    private String periodo;
    private String descperiodo;
    private String fvencimiento;
    private double mpago;
    private double mparte;
    private double mdscto;
    private double mdsctoadic;
    private double moradic;
    private double mora;
    private double mint;
    private double subtotal;
    private double mtotal;
    private double pagocancelar;
    private int cantidad;
    private boolean opc;
    private double saldo;
    private String concepto;
    private String cdescripcion;
    private double cmonto;
    private int item;
    private int tipo;
    private String ccarrera;
    private String cdes;
    private String curso;
    private String seccion;
    private String seriefut;
    
    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getCdescripcion() {
        return cdescripcion;
    }

    public void setCdescripcion(String cdescripcion) {
        this.cdescripcion = cdescripcion;
    }

    public double getCmonto() {
        return cmonto;
    }

    public void setCmonto(double cmonto) {
        this.cmonto = cmonto;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isOpc() {
        return opc;
    }

    public CPendientes(int institucion, String carrera, int tconcepto, String descripcion, String cuota, String periodo, String fvencimiento, double mpago, double mparte, double mdscto, double mdsctoadic, double moradic, double mora, double mint, double subtotal, double mtotal, int cantidad, boolean opc, double saldo, String concepto, double cmonto, int item, String ccarrera, String cdes) {
        this.institucion = institucion;
        this.carrera = carrera;
        this.tconcepto = tconcepto;
        this.descripcion = descripcion;
        this.cuota = cuota;
        this.periodo = periodo;
        this.fvencimiento = fvencimiento;
        this.mpago = mpago;
        this.mparte = mparte;
        this.mdscto = mdscto;
        this.mdsctoadic = mdsctoadic;
        this.moradic = moradic;
        this.mora = mora;
        this.mint = mint;
        this.subtotal = subtotal;
        this.mtotal = mtotal;
        this.cantidad = cantidad;
        this.opc = opc;
        this.saldo = saldo;
        this.concepto = concepto;
        this.cmonto = cmonto;
        this.item = item;
        this.ccarrera = ccarrera;
        this.cdes = cdes;
    }

    public void setOpc(boolean opc) {
        this.opc = opc;
    }

    public double getMdsctoadic() {
        return mdsctoadic;
    }

    public void setMdsctoadic(double mdsctoadic) {
        this.mdsctoadic = mdsctoadic;
    }

    public double getMoradic() {
        return moradic;
    }

    public void setMoradic(double moradic) {
        this.moradic = moradic;
    }

    public double getMora() {
        return mora;
    }

    public void setMora(double mora) {
        this.mora = mora;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getPagocancelar() {
        return pagocancelar;
    }

    public void setPagocancelar(double pagocancelar) {
        this.pagocancelar = pagocancelar;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getFvencimiento() {
        return fvencimiento;
    }

    public void setFvencimiento(String fvencimiento) {
        this.fvencimiento = fvencimiento;
    }

    public CPendientes() {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCuota() {
        return cuota;
    }

    public void setCuota(String cuota) {
        this.cuota = cuota;
    }

    public double getMpago() {
        return mpago;
    }

    public void setMpago(double mpago) {
        this.mpago = mpago;
    }

    public double getMparte() {
        return mparte;
    }

    public void setMparte(double mparte) {
        this.mparte = mparte;
    }

    public double getMdscto() {
        return mdscto;
    }

    public void setMdscto(double mdscto) {
        this.mdscto = mdscto;
    }

    public double getMint() {
        return mint;
    }

    public void setMint(double mint) {
        this.mint = mint;
    }

    public double getMtotal() {
        return mtotal;
    }

    public void setMtotal(double mtotal) {
        this.mtotal = mtotal;
    }

    public String getCconcepto() {
        return cconcepto;
    }

    public void setCconcepto(String cconcepto) {
        this.cconcepto = cconcepto;
    }

    public int getTconcepto() {
        return tconcepto;
    }

    public void setTconcepto(int tconcepto) {
        this.tconcepto = tconcepto;
    }

    public String getCcarrera() {
        return ccarrera;
    }

    public void setCcarrera(String ccarrera) {
        this.ccarrera = ccarrera;
    }

    public String getCdes() {
        return cdes;
    }

    public void setCdes(String cdes) {
        this.cdes = cdes;
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDescperiodo() {
        return descperiodo;
    }

    public void setDescperiodo(String descperiodo) {
        this.descperiodo = descperiodo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getSeriefut() {
        return seriefut;
    }

    public void setSeriefut(String seriefut) {
        this.seriefut = seriefut;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

}
