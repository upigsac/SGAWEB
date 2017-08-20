/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ENTIDAD;


import java.util.Date;

/**
 *
 * @author Administrador
 */
public class cuentaPersonaC {
    private String persona;
    private int item;
    
    private int institucion;
    private String periodo_concepto;
    private int tipo_concepto;
    private String concepto;
    private String descuento;
    private String descuento_adi;
    private int num_cuota;
    private int num_parte;
    private int tipo_moneda;
    private double monto_pago;
    private double monto_parte;
    private double monto_descuento;
    private double monto_descuento_adi;
    private double monto_interes;
    private double monto_total;
    private Date fecha_vencimiento;
    private String observacion;
    private String periodo;
    private String alumno;
    private String carrera;
    private String seccion;
    private String grupo;
    private String categoria;
    private int tipo_documento;
    private String num_comprobante;
    private int estadoRegistro;
    private int itemPadre;
    public cuentaPersonaC() {
    }

    public cuentaPersonaC(String persona, int item, int institucion, String periodo_concepto, int tipo_concepto, String concepto, String descuento, String descuento_adi, int num_cuota, int num_parte, int tipo_moneda, double monto_pago, double monto_parte, double monto_descuento, double monto_descuento_adi, double monto_interes, double monto_total, Date fecha_vencimiento, String observacion, String periodo, String alumno, String carrera, String seccion, String grupo, String categoria, int tipo_documento, String num_comprobante, int estadoRegistro, int itemPadre) {
        this.persona = persona;
        this.item = item;
        this.institucion = institucion;
        this.periodo_concepto = periodo_concepto;
        this.tipo_concepto = tipo_concepto;
        this.concepto = concepto;
        this.descuento = descuento;
        this.descuento_adi = descuento_adi;
        this.num_cuota = num_cuota;
        this.num_parte = num_parte;
        this.tipo_moneda = tipo_moneda;
        this.monto_pago = monto_pago;
        this.monto_parte = monto_parte;
        this.monto_descuento = monto_descuento;
        this.monto_descuento_adi = monto_descuento_adi;
        this.monto_interes = monto_interes;
        this.monto_total = monto_total;
        this.fecha_vencimiento = fecha_vencimiento;
        this.observacion = observacion;
        this.periodo = periodo;
        this.alumno = alumno;
        this.carrera = carrera;
        this.seccion = seccion;
        this.grupo = grupo;
        this.categoria = categoria;
        this.tipo_documento = tipo_documento;
        this.num_comprobante = num_comprobante;
        this.estadoRegistro = estadoRegistro;
        this.itemPadre = itemPadre;
    }

    
    
    
    
    
   /**
     * @return the persona
     */
    public String getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(String persona) {
        this.persona = persona;
    }

    /**
     * @return the item
     */
    public int getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(int item) {
        this.item = item;
    }

    /**
     * @return the institucion
     */
    public int getInstitucion() {
        return institucion;
    }

    /**
     * @param institucion the institucion to set
     */
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }

    /**
     * @return the periodo_concepto
     */
    public String getPeriodo_concepto() {
        return periodo_concepto;
    }

    /**
     * @param periodo_concepto the periodo_concepto to set
     */
    public void setPeriodo_concepto(String periodo_concepto) {
        this.periodo_concepto = periodo_concepto;
    }

    /**
     * @return the tipo_concepto
     */
    public int getTipo_concepto() {
        return tipo_concepto;
    }

    /**
     * @param tipo_concepto the tipo_concepto to set
     */
    public void setTipo_concepto(int tipo_concepto) {
        this.tipo_concepto = tipo_concepto;
    }

    /**
     * @return the concepto
     */
    public String getConcepto() {
        return concepto;
    }

    /**
     * @param concepto the concepto to set
     */
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    /**
     * @return the descuento
     */
    public String getDescuento() {
        return descuento;
    }

    /**
     * @param descuento the descuento to set
     */
    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    /**
     * @return the descuento_adi
     */
    public String getDescuento_adi() {
        return descuento_adi;
    }

    /**
     * @param descuento_adi the descuento_adi to set
     */
    public void setDescuento_adi(String descuento_adi) {
        this.descuento_adi = descuento_adi;
    }

    /**
     * @return the num_cuota
     */
    public int getNum_cuota() {
        return num_cuota;
    }

    /**
     * @param num_cuota the num_cuota to set
     */
    public void setNum_cuota(int num_cuota) {
        this.num_cuota = num_cuota;
    }

    /**
     * @return the num_parte
     */
    public int getNum_parte() {
        return num_parte;
    }

    /**
     * @param num_parte the num_parte to set
     */
    public void setNum_parte(int num_parte) {
        this.num_parte = num_parte;
    }

    /**
     * @return the tipo_moneda
     */
    public int getTipo_moneda() {
        return tipo_moneda;
    }

    /**
     * @param tipo_moneda the tipo_moneda to set
     */
    public void setTipo_moneda(int tipo_moneda) {
        this.tipo_moneda = tipo_moneda;
    }

    /**
     * @return the monto_pago
     */
    public double getMonto_pago() {
        return monto_pago;
    }

    /**
     * @param monto_pago the monto_pago to set
     */
    public void setMonto_pago(double monto_pago) {
        this.monto_pago = monto_pago;
    }

    /**
     * @return the monto_parte
     */
    public double getMonto_parte() {
        return monto_parte;
    }

    /**
     * @param monto_parte the monto_parte to set
     */
    public void setMonto_parte(double monto_parte) {
        this.monto_parte = monto_parte;
    }

    /**
     * @return the monto_descuento
     */
    public double getMonto_descuento() {
        return monto_descuento;
    }

    /**
     * @param monto_descuento the monto_descuento to set
     */
    public void setMonto_descuento(double monto_descuento) {
        this.monto_descuento = monto_descuento;
    }

    /**
     * @return the monto_descuento_adi
     */
    public double getMonto_descuento_adi() {
        return monto_descuento_adi;
    }

    /**
     * @param monto_descuento_adi the monto_descuento_adi to set
     */
    public void setMonto_descuento_adi(double monto_descuento_adi) {
        this.monto_descuento_adi = monto_descuento_adi;
    }

    /**
     * @return the monto_interes
     */
    public double getMonto_interes() {
        return monto_interes;
    }

    /**
     * @param monto_interes the monto_interes to set
     */
    public void setMonto_interes(double monto_interes) {
        this.monto_interes = monto_interes;
    }

    /**
     * @return the monto_total
     */
    public double getMonto_total() {
        return monto_total;
    }

    /**
     * @param monto_total the monto_total to set
     */
    public void setMonto_total(double monto_total) {
        this.monto_total = monto_total;
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
     * @return the fecha_vencimiento
     */
    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    /**
     * @param fecha_vencimiento the fecha_vencimiento to set
     */
    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
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
     * @return the periodo
     */
    public String getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    /**
     * @return the alumno
     */
    public String getAlumno() {
        return alumno;
    }

    /**
     * @param alumno the alumno to set
     */
    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    /**
     * @return the carrera
     */
    public String getCarrera() {
        return carrera;
    }

    /**
     * @param carrera the carrera to set
     */
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    /**
     * @return the seccion
     */
    public String getSeccion() {
        return seccion;
    }

    /**
     * @param seccion the seccion to set
     */
    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    /**
     * @return the grupo
     */
    public String getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the tipo_documento
     */
    public int getTipo_documento() {
        return tipo_documento;
    }

    /**
     * @param tipo_documento the tipo_documento to set
     */
    public void setTipo_documento(int tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    /**
     * @return the num_comprobante
     */
    public String getNum_comprobante() {
        return num_comprobante;
    }

    /**
     * @param num_comprobante the num_comprobante to set
     */
    public void setNum_comprobante(String num_comprobante) {
        this.num_comprobante = num_comprobante;
    }

    /**
     * @return the itemPadre
     */
    public int getItemPadre() {
        return itemPadre;
    }

    /**
     * @param itemPadre the itemPadre to set
     */
    public void setItemPadre(int itemPadre) {
        this.itemPadre = itemPadre;
    }

    

    




    
}
