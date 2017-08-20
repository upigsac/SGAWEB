
package ENTIDAD;

import java.io.Serializable;
import java.util.Date;


public class alumnoPeriodoC implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int institucion;
    private String periodo;
    private String alumno;
    private String carrera ;
    private String malla;
    private int cursos_matriculados;
    private int cursos_aprobados;
    private int cursos_convalidados; 
    private int cursos_retirados; 
    private int creditos_matriculados;
    private int creditos_aprobados;
    private int creditos_convalidados;
    private int creditos_retirados;
    private String seccion_referencial;
    private int nivel_referencial;
    private double promedio_semestral;
    private int orden_semestral;
    private int orden_nivel;
    
    
    private double promedio_ponderado;
    private int orden_ponderado;
    private String documento_autoriza;
    private String resolucion_numero;
    private Date resolucion_fecha;
    private String codigo_reserva;
    private String observacion;
    private Date fecha_matricula;
    
    private String categoria;
    private String descuento;
    private double monto_base;
    private double monto_pago;
    private double monto_pago_adc;
    private double monto_adicinal;
    
    private String aut_per_matricula;
    private String aut_doc_matricula;
    private String aut_obs_matricula;
    
    private String aut_per_malla;
    private String aut_doc_malla;
    private String aut_obs_malla;
    
    private String aut_per_carrera;
    private String aut_doc_carrera;
    private String aut_obs_carrera;
    
    private String aut_per_categoria;
    private String aut_doc_categoria;
    private String aut_obs_categoria;
  

    private int estado_registro;
    

    public alumnoPeriodoC() {
    }
    
    
    public alumnoPeriodoC(int institucion,String periodo,String alumno,String carrera , String malla,int cursos_matriculados,int cursos_aprobados,int cursos_convalidados,int cursos_retirados,int creditos_matriculados,int creditos_aprobados,int creditos_convalidados,int creditos_retirados,String seccion_referencial,int nivel_referencial,double promedio_semestral,int orden_semestral,int orden_nivel,double promedio_ponderado,int orden_ponderado, String documento_autoriza,String resolucion_numero,Date resolucion_fecha,String codigo_reserva,String observacion,Date fecha_matricula,String categoria,String descuento,double monto_base,double monto_pago,double monto_pago_adc,double monto_adicinal,String aut_per_matricula,String aut_doc_matricula,String aut_obs_matricula,String aut_per_malla,String aut_doc_malla,String aut_obs_malla,String aut_per_carrera,String aut_doc_carrera,String aut_obs_carrera,String aut_per_categoria,String aut_doc_categoria,String aut_obs_categoria,int estado_registro) {
    	this.institucion=institucion;
    	this.periodo=periodo;
    	this.alumno=alumno;
    	this.carrera=carrera; 
    	this.malla=malla;
    	this.cursos_matriculados=cursos_matriculados;
    	this.cursos_aprobados=cursos_aprobados;
    	this.cursos_convalidados=cursos_convalidados;
    	this.cursos_retirados=cursos_retirados;
    	this.creditos_matriculados=creditos_matriculados;
    	this.creditos_aprobados=creditos_aprobados;
    	this.creditos_convalidados=creditos_convalidados;
    	this.creditos_retirados=creditos_retirados;
    	this.seccion_referencial=seccion_referencial;
    	this.nivel_referencial=nivel_referencial;
    	this.promedio_semestral=promedio_semestral;
    	this.orden_semestral=orden_semestral;
    	this.orden_nivel=orden_nivel;
    	this.promedio_ponderado=promedio_ponderado;
    	this.orden_ponderado=orden_ponderado;
    	this.documento_autoriza=documento_autoriza;
    	this.resolucion_numero=resolucion_numero;
    	this.resolucion_fecha=resolucion_fecha;
    	this.codigo_reserva=codigo_reserva;
    	this.observacion=observacion;
    	this.fecha_matricula=fecha_matricula;
    	this.categoria=categoria;
    	this.descuento=descuento;
    	this.monto_base=monto_base;
    	this.monto_pago=monto_pago;
    	this.monto_pago_adc=monto_pago_adc;
    	this.monto_adicinal=monto_adicinal;
    	this.aut_per_matricula=aut_per_matricula;
    	this.aut_doc_matricula=aut_doc_matricula;
    	this.aut_obs_matricula=aut_obs_matricula;
    	this.aut_per_malla=aut_per_malla;
    	this.aut_doc_malla=aut_doc_malla;
    	this.aut_obs_malla=aut_obs_malla;
    	this.aut_per_carrera=aut_per_carrera;
    	this.aut_doc_carrera=aut_doc_carrera;
    	this.aut_obs_carrera=aut_obs_carrera;
    	this.aut_per_categoria=aut_per_categoria;
    	this.aut_doc_categoria=aut_doc_categoria;
    	this.aut_obs_categoria=aut_obs_categoria;
    	this.estado_registro=estado_registro;
    }


    public alumnoPeriodoC(int institucion, String periodo, String alumno, String carrera, String malla, int cursos_matriculados, int cursos_aprobados, int creditos_matriculados, int nivel_referencial, String seccion_referencial, String categoria, double promedio_semestral, double promedio_ponderado, String aut_doc_matricula, String aut_obs_matricula, String descuento, int estado_registro) {
        this.institucion = institucion;
        this.periodo = periodo;
        this.alumno = alumno;
        this.carrera = carrera;
        this.malla = malla;
        this.cursos_matriculados = cursos_matriculados;
        this.cursos_aprobados = cursos_aprobados;
        this.creditos_matriculados = creditos_matriculados;
        this.nivel_referencial = nivel_referencial;
        this.seccion_referencial = seccion_referencial;
        this.categoria = categoria;
        this.promedio_semestral = promedio_semestral;
        this.promedio_ponderado = promedio_ponderado;
        this.aut_doc_matricula = aut_doc_matricula;
        this.aut_obs_matricula = aut_obs_matricula;
        this.descuento = descuento;
        this.estado_registro = estado_registro;
    }

    
    
    

    public int getEstado_registro() {
        return estado_registro;
    }

    public void setEstado_registro(int estado_registro) {
        this.estado_registro = estado_registro;
    }
     
    
 

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getMalla() {
        return malla;
    }

    public void setMalla(String malla) {
        this.malla = malla;
    }

    public int getCursos_matriculados() {
        return cursos_matriculados;
    }

    public void setCursos_matriculados(int cursos_matriculados) {
        this.cursos_matriculados = cursos_matriculados;
    }

    public String getSeccion_referencial() {
        return seccion_referencial;
    }

    public void setSeccion_referencial(String seccion_referencial) {
        this.seccion_referencial = seccion_referencial;
    }

    
    
    public int getCursos_convalidados() {
		return cursos_convalidados;
	}

	public void setCursos_convalidados(int cursos_convalidados) {
		this.cursos_convalidados = cursos_convalidados;
	}

	public int getCursos_retirados() {
		return cursos_retirados;
	}

	public void setCursos_retirados(int cursos_retirados) {
		this.cursos_retirados = cursos_retirados;
	}

	public int getCreditos_aprobados() {
		return creditos_aprobados;
	}

	public void setCreditos_aprobados(int creditos_aprobados) {
		this.creditos_aprobados = creditos_aprobados;
	}

	public int getCreditos_convalidados() {
		return creditos_convalidados;
	}

	public void setCreditos_convalidados(int creditos_convalidados) {
		this.creditos_convalidados = creditos_convalidados;
	}

	public int getCreditos_retirados() {
		return creditos_retirados;
	}

	public void setCreditos_retirados(int creditos_retirados) {
		this.creditos_retirados = creditos_retirados;
	}

	public int getOrden_semestral() {
		return orden_semestral;
	}

	public void setOrden_semestral(int orden_semestral) {
		this.orden_semestral = orden_semestral;
	}

	public int getOrden_nivel() {
		return orden_nivel;
	}

	public void setOrden_nivel(int orden_nivel) {
		this.orden_nivel = orden_nivel;
	}

	public int getOrden_ponderado() {
		return orden_ponderado;
	}

	public void setOrden_ponderado(int orden_ponderado) {
		this.orden_ponderado = orden_ponderado;
	}

	public String getDocumento_autoriza() {
		return documento_autoriza;
	}

	public void setDocumento_autoriza(String documento_autoriza) {
		this.documento_autoriza = documento_autoriza;
	}

	public String getResolucion_numero() {
		return resolucion_numero;
	}

	public void setResolucion_numero(String resolucion_numero) {
		this.resolucion_numero = resolucion_numero;
	}

	public Date getResolucion_fecha() {
		return resolucion_fecha;
	}

	public void setResolucion_fecha(Date resolucion_fecha) {
		this.resolucion_fecha = resolucion_fecha;
	}

	public String getCodigo_reserva() {
		return codigo_reserva;
	}

	public void setCodigo_reserva(String codigo_reserva) {
		this.codigo_reserva = codigo_reserva;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getFecha_matricula() {
		return fecha_matricula;
	}

	public void setFecha_matricula(Date fecha_matricula) {
		this.fecha_matricula = fecha_matricula;
	}

	public double getMonto_base() {
		return monto_base;
	}

	public void setMonto_base(double monto_base) {
		this.monto_base = monto_base;
	}

	public double getMonto_pago() {
		return monto_pago;
	}

	public void setMonto_pago(double monto_pago) {
		this.monto_pago = monto_pago;
	}

	public double getMonto_pago_adc() {
		return monto_pago_adc;
	}

	public void setMonto_pago_adc(double monto_pago_adc) {
		this.monto_pago_adc = monto_pago_adc;
	}

	public double getMonto_adicinal() {
		return monto_adicinal;
	}

	public void setMonto_adicinal(double monto_adicinal) {
		this.monto_adicinal = monto_adicinal;
	}

	public String getAut_per_matricula() {
		return aut_per_matricula;
	}

	public void setAut_per_matricula(String aut_per_matricula) {
		this.aut_per_matricula = aut_per_matricula;
	}

	public String getAut_per_malla() {
		return aut_per_malla;
	}

	public void setAut_per_malla(String aut_per_malla) {
		this.aut_per_malla = aut_per_malla;
	}

	public String getAut_doc_malla() {
		return aut_doc_malla;
	}

	public void setAut_doc_malla(String aut_doc_malla) {
		this.aut_doc_malla = aut_doc_malla;
	}

	public String getAut_obs_malla() {
		return aut_obs_malla;
	}

	public void setAut_obs_malla(String aut_obs_malla) {
		this.aut_obs_malla = aut_obs_malla;
	}

	public String getAut_per_carrera() {
		return aut_per_carrera;
	}

	public void setAut_per_carrera(String aut_per_carrera) {
		this.aut_per_carrera = aut_per_carrera;
	}

	public String getAut_doc_carrera() {
		return aut_doc_carrera;
	}

	public void setAut_doc_carrera(String aut_doc_carrera) {
		this.aut_doc_carrera = aut_doc_carrera;
	}

	public String getAut_obs_carrera() {
		return aut_obs_carrera;
	}

	public void setAut_obs_carrera(String aut_obs_carrera) {
		this.aut_obs_carrera = aut_obs_carrera;
	}

	public String getAut_per_categoria() {
		return aut_per_categoria;
	}

	public void setAut_per_categoria(String aut_per_categoria) {
		this.aut_per_categoria = aut_per_categoria;
	}

	public String getAut_doc_categoria() {
		return aut_doc_categoria;
	}

	public void setAut_doc_categoria(String aut_doc_categoria) {
		this.aut_doc_categoria = aut_doc_categoria;
	}

	public String getAut_obs_categoria() {
		return aut_obs_categoria;
	}

	public void setAut_obs_categoria(String aut_obs_categoria) {
		this.aut_obs_categoria = aut_obs_categoria;
	}

	public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public int getCursos_aprobados() {
        return cursos_aprobados;
    }
    public void setCursos_aprobados(int cursos_aprobados) {
        this.cursos_aprobados = cursos_aprobados;
    }
    public double getPromedio_semestral() {
        return promedio_semestral;
    }
    public void setPromedio_semestral(double promedio_semestral) {
        this.promedio_semestral = promedio_semestral;
    }
    public double getPromedio_ponderado() {
        return promedio_ponderado;
    }
    public void setPromedio_ponderado(double promedio_ponderado) {
        this.promedio_ponderado = promedio_ponderado;
    }
    public int getNivel_referencial() {
        return nivel_referencial;
    }
    public void setNivel_referencial(int nivel_referencial) {
        this.nivel_referencial = nivel_referencial;
    }
    public String getAut_doc_matricula() {
        return aut_doc_matricula;
    }
    public void setAut_doc_matricula(String aut_doc_matricula) {
        this.aut_doc_matricula = aut_doc_matricula;
    }
    public String getAut_obs_matricula() {
        return aut_obs_matricula;
    }
    public void setAut_obs_matricula(String aut_obs_matricula) {
        this.aut_obs_matricula = aut_obs_matricula;
    }
    public int getCreditos_matriculados() {
        return creditos_matriculados;
    }
    public void setCreditos_matriculados(int creditos_matriculados) {
        this.creditos_matriculados = creditos_matriculados;
    }
    public int getInstitucion() {
        return institucion;
    }
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }
    public String getDescuento() {
        return descuento;
    }
    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }
    public String getPeriodo() {
        return periodo;
    }
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
 
            
}
