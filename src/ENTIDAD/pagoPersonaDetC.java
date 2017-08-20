/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ENTIDAD;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Diseño
 */
public class pagoPersonaDetC {

    private String numero_fut;
    private String persona;
    private int institucion;
    private String periodo;
    private String concepto;
    
    private String concepto_desc;
    
    private String periodo_matricula;
    private String docente;
    private int tipo_concepto;
    private int num_cuota;
    private int num_parte;
    private int item;
    private String carrera;
    private String curso;
    private String seccion;

    private BigDecimal monto_base;
    private BigDecimal monto_parte;
    private int cantidad;    
    private String descuento;
    private BigDecimal monto_desc;
    
    private int auto_desc_adic;
    
    private String descuento_adic;
    private BigDecimal monto_desc_adic;
    
    private boolean no_mora;
    
    private BigDecimal monto_interes;
    private Date fecha_vencimiento;
    private BigDecimal monto_total;
    private String observacion;
    
    private BigDecimal pago_a_cancelar;
    
    private BigDecimal saldo_item;
    private int estado_registro;
    
    private BigDecimal mora_antiguo_valor;

    public pagoPersonaDetC(){
        
    }
    
    public pagoPersonaDetC(String numero_fut,String persona,int institucion,String periodo,String concepto,String periodo_matricula,String docente,int tipo_concepto,int num_cuota,
            int num_parte,int item,String carrera,String curso,String seccion,BigDecimal monto_base,BigDecimal monto_parte,int cantidad,String descuento,BigDecimal monto_desc,
            int auto_desc_adic,String descuento_adic,BigDecimal monto_desc_adic,
            BigDecimal monto_interes,Date fecha_vencimiento,BigDecimal monto_total,String observacion,
            BigDecimal pago_a_cancelar,
            BigDecimal saldo_item,int estado_registro) {
        
        this.numero_fut = numero_fut;
        this.persona = persona;
        this.institucion = institucion;
        this.periodo = periodo;
        this.concepto = concepto;
        this.periodo_matricula = periodo_matricula;
        this.docente = docente;
        this.tipo_concepto = tipo_concepto;
        this.num_cuota = num_cuota;
        this.num_parte = num_parte;
        this.item = item;
        this.carrera = carrera;
        this.curso = curso;
        this.seccion = seccion;
        this.monto_base = monto_base;
        this.monto_parte = monto_parte;
        this.cantidad = cantidad;        
        this.descuento = descuento;
        this.monto_desc = monto_desc;
        this.auto_desc_adic = auto_desc_adic;
        this.descuento_adic = descuento_adic;
        this.monto_desc_adic = monto_desc_adic;
        this.monto_interes = monto_interes;
        this.fecha_vencimiento = fecha_vencimiento;
        this.monto_total = monto_total;
        this.observacion = observacion;
        
        this.pago_a_cancelar = pago_a_cancelar;
        
        this.saldo_item = saldo_item;        
        this.estado_registro = estado_registro;
    }

    /**
     * @return the numero_fut
     */
    public String getNumero_fut() {
        return numero_fut;
    }

    /**
     * @param numero_fut the numero_fut to set
     */
    public void setNumero_fut(String numero_fut) {
        this.numero_fut = numero_fut;
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
     * @return the periodo_matricula
     */
    public String getPeriodo_matricula() {
        return periodo_matricula;
    }

    /**
     * @param periodo_matricula the periodo_matricula to set
     */
    public void setPeriodo_matricula(String periodo_matricula) {
        this.periodo_matricula = periodo_matricula;
    }

    /**
     * @return the docente
     */
    public String getDocente() {
        return docente;
    }

    /**
     * @param docente the docente to set
     */
    public void setDocente(String docente) {
        this.docente = docente;
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
     * @return the curso
     */
    public String getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(String curso) {
        this.curso = curso;
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
     * @return the monto_base
     */
    public BigDecimal getMonto_base() {
        return monto_base;
    }

    /**
     * @param monto_base the monto_base to set
     */
    public void setMonto_base(BigDecimal monto_base) {
        this.monto_base = monto_base;
    }

    /**
     * @return the monto_parte
     */
    public BigDecimal getMonto_parte() {
        return monto_parte;
    }

    /**
     * @param monto_parte the monto_parte to set
     */
    public void setMonto_parte(BigDecimal monto_parte) {
        this.monto_parte = monto_parte;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the monto_desc
     */
    public BigDecimal getMonto_desc() {
        return monto_desc;
    }

    /**
     * @param monto_desc the monto_desc to set
     */
    public void setMonto_desc(BigDecimal monto_desc) {
        this.monto_desc = monto_desc;
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
     * @return the monto_interes
     */
    public BigDecimal getMonto_interes() {
        return monto_interes;
    }

    /**
     * @param monto_interes the monto_interes to set
     */
    public void setMonto_interes(BigDecimal monto_interes) {
        this.monto_interes = monto_interes;
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
     * @return the monto_total
     */
    public BigDecimal getMonto_total() {
        return monto_total;
    }

    /**
     * @param monto_total the monto_total to set
     */
    public void setMonto_total(BigDecimal monto_total) {
        this.monto_total = monto_total;
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
     * @return the saldo_item
     */
    public BigDecimal getSaldo_item() {
        return saldo_item;
    }

    /**
     * @param saldo_item the saldo_item to set
     */
    public void setSaldo_item(BigDecimal saldo_item) {
        this.saldo_item = saldo_item;
    }

    /**
     * @return the estado_registro
     */
    public int getEstado_registro() {
        return estado_registro;
    }

    /**
     * @param estado_registro the estado_registro to set
     */
    public void setEstado_registro(int estado_registro) {
        this.estado_registro = estado_registro;
    }

    /**
     * @return the descuento_adic
     */
    public String getDescuento_adic() {
        return descuento_adic;
    }

    /**
     * @param descuento_adic the descuento_adic to set
     */
    public void setDescuento_adic(String descuento_adic) {
        this.descuento_adic = descuento_adic;
    }

    /**
     * @return the monto_desc_adic
     */
    public BigDecimal getMonto_desc_adic() {
        return monto_desc_adic;
    }

    /**
     * @param monto_desc_adic the monto_desc_adic to set
     */
    public void setMonto_desc_adic(BigDecimal monto_desc_adic) {
        this.monto_desc_adic = monto_desc_adic;
    }

    /**
     * @return the concepto_desc
     */
    public String getConcepto_desc() {
        return concepto_desc;
    }

    /**
     * @param concepto_desc the concepto_desc to set
     */
    public void setConcepto_desc(String concepto_desc) {
        this.concepto_desc = concepto_desc;
    }

    /**
     * @return the auto_desc_adic
     */
    public int getAuto_desc_adic() {
        return auto_desc_adic;
    }

    /**
     * @param auto_desc_adic the auto_desc_adic to set
     */
    public void setAuto_desc_adic(int auto_desc_adic) {
        this.auto_desc_adic = auto_desc_adic;
    }

    /**
     * @return the pago_a_cancelar
     */
    public BigDecimal getPago_a_cancelar() {
        return pago_a_cancelar;
    }

    /**
     * @param pago_a_cancelar the pago_a_cancelar to set
     */
    public void setPago_a_cancelar(BigDecimal pago_a_cancelar) {
        this.pago_a_cancelar = pago_a_cancelar;
    }

    /**
     * @return the no_mora
     */
    public boolean isNo_mora() {
        return no_mora;
    }

    /**
     * @param no_mora the no_mora to set
     */
    public void setNo_mora(boolean no_mora) {
        this.no_mora = no_mora;
    }

    /**
     * @return the mora_antiguo_valor
     */
    public BigDecimal getMora_antiguo_valor() {
        return mora_antiguo_valor;
    }

    /**
     * @param mora_antiguo_valor the mora_antiguo_valor to set
     */
    public void setMora_antiguo_valor(BigDecimal mora_antiguo_valor) {
        this.mora_antiguo_valor = mora_antiguo_valor;
    }

}
