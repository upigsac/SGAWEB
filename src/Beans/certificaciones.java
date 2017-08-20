
package Beans;

import DAO.alumnoCertificacionDAO;
import DAO.alumnoCursoSeccionDAO;
import DAO.alumnoDAO;
import DAO.carrerasDAO;
import DAO.conceptoDAO;
import DAO.conceptoInstitucionPeriodoDAO;
import DAO.cursoDAO;
import DAO.cursoSeccionDAO;
import DAO.descuentoDAO;
import DAO.detallePagoPersonaDAO;
import DAO.firmaCertificacionCursoDAO;
import DAO.formatoUnicoTramiteDAO;
import DAO.personaDAO;
import DAO.productoConceptoDAO;
import DAO.seccionDAO;
import DAO.seccionPeriodoDAO;
import DAO.tipoCertificadoDAO;
import ENTIDAD.alumnoCertificacionC;
import ENTIDAD.alumnoCursoSeccionC;
import ENTIDAD.carrerasC;
import ENTIDAD.conceptoC;
import ENTIDAD.conceptoInstitucionPeriodoC;
import ENTIDAD.cursoSeccionC;
import ENTIDAD.cursosC;
import ENTIDAD.descuentoC;

import ENTIDAD.firmaCertificacionCursoC;
import ENTIDAD.formatoUnicoTramiteC;
import ENTIDAD.personaC;
import ENTIDAD.productoConceptoC;
import ENTIDAD.seccionC;
import ENTIDAD.seccionPeriodoC;
import ENTIDAD.tipoCertificadoC;

import java.io.Serializable;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



@ManagedBean(name = "certificacionesB")
@ViewScoped
public class certificaciones implements Serializable {

	
	
	
	
  
	private static final long serialVersionUID = 1L;
	
	
	private List<detalle> alumnoDetalleL=new ArrayList<>();
    private List<detalle> alumnoSeleccionadoL= new ArrayList<>();
    
    private List<alumnoCursoSeccion> alumnoCursoSeccionL=new ArrayList<>();
    private List<firmaCertificacionCursoC> firmaCertificacionCursoL=new ArrayList<>();
    
    private firmaCertificacionCursoC firmaCertificacionCurso=new firmaCertificacionCursoC();
    
    private conceptoInstitucionPeriodoC conceptoCertificado;
    private conceptoInstitucionPeriodoC conceptoCurso;
    private descuentoC descuento;
    
    private cursoSeccionC  cursoSeccion;
    private conceptoC  concepto=new conceptoC();
    private seccionPeriodoC seccionPeriodo;
    private List<productoConceptoC> productoConceptoL;
   
    private int institucion;
    private String persona;
    private String periodo;
    private String carrera;
    private String curso;
    private String seccion;
   
    private String alumnoCopia="";
    
    
    
    
   
	
	private boolean banderaTipoCertificado=true;
    
    
    private List<personaC> personaL=new ArrayList<>();
    private List<carrerasC> carreraL=new ArrayList<>();
    private List<seccionC> seccionL=new ArrayList<>();
    private List<cursosC> cursoL=new ArrayList<>(); 
    
    private List<alumnoCertificadoPagoC> alumnoCertificadoPagoL=new ArrayList<>();
    private List<alumnoCertificadoPagoC> seleccionAlumnoCertificadoPagoL=new ArrayList<>();
    private tipoCertificadoC tipoCertificado=new tipoCertificadoC();
    private List<tipoCertificadoC> tipoCertificadoL=new ArrayList<>();
    private formatoUnicoTramiteC formatoUnicoTramite=new formatoUnicoTramiteC();
    
    
    
    


	alumnoDAO alumnoD;
    firmaCertificacionCursoDAO firmaCertificacionCursoD;
    alumnoCertificacionDAO alumnoCertificacionD;
    productoConceptoDAO productoConceptoD;
    cursoSeccionDAO cursoSeccionD;
    conceptoDAO conceptoD;
    cursoDAO cursoD;
    seccionPeriodoDAO  seccionPeriodoD;    
    conceptoInstitucionPeriodoDAO conceptoInstitucionPeriodoD;
    descuentoDAO descuentoD;
    
    
    
    
    public void editarTipoCertificado(){
    	banderaTipoCertificado=false;
    }
    public void cancelarTipoCertificado(){
    	banderaTipoCertificado=true;
    }

	public certificaciones() {
		// TODO Auto-generated constructor stub
    	tipoCertificadoL=new tipoCertificadoDAO().mostrarTipoCertificado();
	}
	
	
	public void buscarFUT(){
		System.out.println("FUT "+formatoUnicoTramite.getFut());
		formatoUnicoTramite=new formatoUnicoTramiteDAO().mostrarFormatoUnicoTramite(formatoUnicoTramite.getFut());
		if(formatoUnicoTramite==null){
			formatoUnicoTramite=new formatoUnicoTramiteC(null, null, null, null, null, 2);
		}
	}
	public void nuevoFUT(String alumno){
		this.alumnoCopia=alumno;
		
		formatoUnicoTramite=new formatoUnicoTramiteC(null, null, null, null, null, 2);
		
		util.script("$('#pnDialogo').css('display','block');");
	}
	public void cerrarFUT(){
		util.script("$('#pnDialogo').css('display','none');");
	}
	public String insertarFUT(){
		
		formatoUnicoTramite.setEstadoRegistro(2);
		new formatoUnicoTramiteDAO().insertar(formatoUnicoTramite);
		util.script("dlgCopiaCertificado.hide()");
		return alumnoCopia;
	}
    
   
    public void recogerCertificado(alumnoCertificacionC item){
    	
    	item.setFechaEntrega(util.FechaHoraHoy());
    	new alumnoCertificacionDAO().insertar(item);
        mostrarDetalleAlumnoPago();
    }
    
    public void insertarAlumnoCertificacion(alumnoCertificacionC item) {

        alumnoCertificacionD = new alumnoCertificacionDAO();
        alumnoCertificacionD.insertar(item);
        
    }
    
    public void insertarCursoSeccion(int tipoCertificado){
    	
    	cursoSeccion.setTipoCertificado(tipoCertificado);
    	new cursoSeccionDAO().insertar(cursoSeccion);
    	banderaTipoCertificado=true;
    }
    

	

	public void pasarSesiones(int institucion,String periodo){
    	this.institucion=institucion;
    	this.periodo=periodo;
    	mostrarPersona();
    }
	
	public void mostrarPersona(){
		personaL=new personaDAO().BuscaPersonaPeriodoInstitucion(institucion, periodo);
		
	}
	public void mostrarCarrera(){
		carreraL=new carrerasDAO().mostrarCarrerasDocentePeriodo(institucion, periodo,persona);
		carrera="";
	}
	public void mostrarSeccion(){
		seccionL=new seccionDAO().mostrarSeccionPeriodoCarreraDocente(institucion, periodo,carrera,persona);
		seccion="";
	}
	
	public void mostrarCurso(){
		cursoL=new cursoDAO().mostrarCurso(institucion, periodo,carrera,persona,seccion);
		curso="";
	}
    
    public conceptoC mostrarConceptoDescripcion(String descripcion){
        
        conceptoD=new conceptoDAO();
        concepto=conceptoD.mostrarConceptoDescripcion(descripcion);
        return concepto;
    }

    public void nuevoTipoCertificado(){
    	tipoCertificadoL=new tipoCertificadoDAO().mostrarTipoCertificado(); 
    }

    public void ingresarFirma() {
        new firmaCertificacionCursoDAO().insertar(firmaCertificacionCurso);
        mostrarFirma();
        util.script("dlgFirma.hide()");
        
    }

    public void eliminarFirma(firmaCertificacionCursoC item) {
    	new firmaCertificacionCursoDAO().eliminar(item);
        
        mostrarFirma();
    }
    public void editarFirma(firmaCertificacionCursoC item){
    	firmaCertificacionCurso=item;
    			
    	util.script("dlgFirma.show()");
    }
    
    public void mostrarFirma(){
    	firmaCertificacionCursoL = new firmaCertificacionCursoDAO().mostrarFirmaCurso(institucion, periodo, carrera, seccion, curso);
    }
    
    public void mostrarCertificaciones() {
    	
    	mostrarFirma();
    	cursoSeccion=new cursoSeccionDAO().mostrarCursoSeccion(""+institucion, periodo, carrera, curso, seccion);
    	tipoCertificado= new tipoCertificadoDAO().mostrarTipoCertificado(institucion, periodo, carrera, "%", curso, seccion);
    	seleccionAlumnoCertificadoPagoL=new ArrayList<>();
        
        //---------------- Mostrar conceptos  -----------------
        
       
        conceptoCurso=new conceptoInstitucionPeriodoDAO().mostrarConceptoInstitucionPeriodo(institucion, periodo, seccion, false);
        conceptoCertificado=new conceptoInstitucionPeriodoDAO().mostrarConceptoInstitucionPeriodo(institucion, periodo, seccion, true);
        
        if(conceptoCurso!=null){        
            descuento=new descuentoDAO().mostrarUnicoDescuento(institucion, periodo, conceptoCurso.getConcepto());
        if(descuento!=null){
            conceptoCurso.setMontoPago(conceptoCurso.getMontoPago()-descuento.getCantidad());
        }
        }
        
       
        
        
       
        if (conceptoCurso!=null){
            util.consolaCliente(conceptoCurso.getConcepto());
        }
        if (conceptoCertificado!=null){
            util.consolaCliente(conceptoCertificado.getConcepto());
        }
        //util.consolaCliente(conceptoCurso.getConcepto());
        
        
        //-----------------------------------------------------
        mostrarDetalleAlumnoPago();
        
      
    }
    public void mostrarDetalleAlumnoPago(){
    	alumnoCertificadoPagoL=new ArrayList<>();
    	alumnoCertificadoPagoC itemAlumnoCertificadoPago;
    	for (alumnoCursoSeccionC itemAlumnoCursoSeccion : new alumnoCursoSeccionDAO().mostrarAlumnoCursoSeccion(institucion, periodo, carrera, "%", seccion, curso)) {
    		itemAlumnoCertificadoPago=new alumnoCertificadoPagoC(itemAlumnoCursoSeccion);
    		itemAlumnoCertificadoPago.alumnoCertificacion=new alumnoCertificacionDAO().mostrarUltimoAlumnoCertificacion(itemAlumnoCursoSeccion.getInstitucion(), itemAlumnoCursoSeccion.getPeriodo(), itemAlumnoCursoSeccion.getCarrera(), itemAlumnoCursoSeccion.getMalla(), itemAlumnoCursoSeccion.getCurso(), itemAlumnoCursoSeccion.getSeccion(), itemAlumnoCursoSeccion.getAlumno());
    		itemAlumnoCertificadoPago.persona=new personaDAO().busquedaAlumnocodigo(itemAlumnoCursoSeccion.getInstitucion(), itemAlumnoCursoSeccion.getAlumno());
    		itemAlumnoCertificadoPago.totalConcepto=new detallePagoPersonaDAO().mostrarTotalConcepto("%", conceptoCurso!=null? conceptoCurso.getConcepto():"", itemAlumnoCursoSeccion.getAlumno());
    		itemAlumnoCertificadoPago.totalCertificado=new detallePagoPersonaDAO().mostrarTotalConcepto("%", conceptoCertificado!=null? conceptoCertificado.getConcepto():"", itemAlumnoCursoSeccion.getAlumno());
    		
    		
    		alumnoCertificadoPagoL.add(itemAlumnoCertificadoPago);
		}
    	
    	
    	Collections.sort(alumnoCertificadoPagoL, new Comparator<alumnoCertificadoPagoC>(){

			@Override
			public int compare(alumnoCertificadoPagoC arg0, alumnoCertificadoPagoC arg1) {
				// TODO Auto-generated method stub
				return arg0.persona.nombreCompletos().compareTo(arg1.persona.nombreCompletos());
			}
    		
    	
    	});
    }
    
   

    public void mostrarPersonasFirmas() {
        firmaCertificacionCursoD = new firmaCertificacionCursoDAO();
        firmaCertificacionCursoL = firmaCertificacionCursoD.mostrarFirmaCurso(institucion, periodo, carrera, seccion, curso);
    }

    public void nuevaFirma() {
    	firmaCertificacionCurso=new firmaCertificacionCursoC(institucion, periodo, carrera, null, curso, seccion, null, "", "", 1);
    	util.script("dlgFirma.show()");
   
    }

    public String parramArray(int institucion, String periodo, String carrera, String malla, String curso, String seccion) {
        String cadena = "";
        alumnoCertificacionD = new alumnoCertificacionDAO();
        for (int i = 0; i <= getAlumnoSeleccionadoL().size() - 1; i++) {
            cadena += alumnoSeleccionadoL.get(i).codigo + "-";
            alumnoCertificacionD.insertar(new alumnoCertificacionC(institucion, periodo, carrera, malla, curso, seccion, alumnoSeleccionadoL.get(i).codigo, 0, null, null, 0,null,null, 0));

        }
        
        return cadena.substring(0, cadena.length() - 1);
    }

    public String parramArray(List<alumnoCertificadoPagoC> lista) {
        String cadena = "";
        
        
        for (alumnoCertificadoPagoC item : lista) {
        	cadena += item.alumnoCursoSeccion.getAlumno() + "-";
        	  new alumnoCertificacionDAO().insertar(new alumnoCertificacionC(item.alumnoCursoSeccion.getInstitucion(), item.alumnoCursoSeccion.getPeriodo(), item.alumnoCursoSeccion.getCarrera(), item.alumnoCursoSeccion.getMalla(), item.alumnoCursoSeccion.getCurso(), item.alumnoCursoSeccion.getSeccion(), item.alumnoCursoSeccion.getAlumno(), 0, util.FechaHoraHoy(), null, 1, null, null, 1));
		}
        
        
        

        
        return cadena.substring(0, cadena.length() - 1);
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

	public String getCarrera() {
		return carrera;
	}



	public void setCarrera(String carrera) {
		this.carrera = carrera;
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



	public firmaCertificacionCursoC getFirmaCertificacionCurso() {
		return firmaCertificacionCurso;
	}

	public void setFirmaCertificacionCurso(firmaCertificacionCursoC firmaCertificacionCurso) {
		this.firmaCertificacionCurso = firmaCertificacionCurso;
	}

	public List<detalle> getAlumnoDetalleL() {
        return alumnoDetalleL;
    }

   
    public void setAlumnoDetalleL(List<detalle> alumnoDetalleL) {
        this.alumnoDetalleL = alumnoDetalleL;
    }

   
    public List<detalle> getAlumnoSeleccionadoL() {
        return alumnoSeleccionadoL;
    }

    
    public void setAlumnoSeleccionadoL(List<detalle> alumnoSeleccionadoL) {
        this.alumnoSeleccionadoL = alumnoSeleccionadoL;
    }

   

   
    public List<alumnoCursoSeccion> getAlumnoCursoSeccionL() {
        return alumnoCursoSeccionL;
    }

   
    public void setAlumnoCursoSeccionL(List<alumnoCursoSeccion> alumnoCursoSeccionL) {
        this.alumnoCursoSeccionL = alumnoCursoSeccionL;
    }

   
    public conceptoC getConcepto() {
        return concepto;
    }

    
    public void setConcepto(conceptoC concepto) {
        this.concepto = concepto;
    }

  
    public cursoSeccionC getCursoSeccion() {
        return cursoSeccion;
    }

   
    public void setCursoSeccion(cursoSeccionC cursoSeccion) {
        this.cursoSeccion = cursoSeccion;
    }

   
    public List<productoConceptoC> getProductoConceptoL() {
        return productoConceptoL;
    }

    
    public void setProductoConceptoL(List<productoConceptoC> productoConceptoL) {
        this.productoConceptoL = productoConceptoL;
    }

   
    public seccionPeriodoC getSeccionPeriodo() {
        return seccionPeriodo;
    }

    
    public void setSeccionPeriodo(seccionPeriodoC seccionPeriodo) {
        this.seccionPeriodo = seccionPeriodo;
    }

    
    public conceptoInstitucionPeriodoC getConceptoCertificado() {
        return conceptoCertificado;
    }

  
    public void setConceptoCertificado(conceptoInstitucionPeriodoC conceptoCertificado) {
        this.conceptoCertificado = conceptoCertificado;
    }

   
    public conceptoInstitucionPeriodoC getConceptoCurso() {
        return conceptoCurso;
    }

    
    public void setConceptoCurso(conceptoInstitucionPeriodoC conceptoCurso) {
        this.conceptoCurso = conceptoCurso;
    }

   
    public descuentoC getDescuento() {
        return descuento;
    }

   
    public void setDescuento(descuentoC descuento) {
        this.descuento = descuento;
    }

    public static class alumnoCertificadoPagoC{
    	
		private alumnoCursoSeccionC alumnoCursoSeccion;
    	private alumnoCertificacionC alumnoCertificacion; 
    	private personaC persona;
    	private String totalConcepto;
    	private String totalCertificado;
    	
    	public alumnoCertificadoPagoC() {
			
		}
    	public alumnoCertificadoPagoC(alumnoCursoSeccionC alumnoCursoSeccion) {
    		this.alumnoCursoSeccion=alumnoCursoSeccion;
 
		}
    	
    	public alumnoCursoSeccionC getAlumnoCursoSeccion() {
			return alumnoCursoSeccion;
		}
		public void setAlumnoCursoSeccion(alumnoCursoSeccionC alumnoCursoSeccion) {
			this.alumnoCursoSeccion = alumnoCursoSeccion;
		}
		public alumnoCertificacionC getAlumnoCertificacion() {
			return alumnoCertificacion;
		}
		public void setAlumnoCertificacion(alumnoCertificacionC alumnoCertificacion) {
			this.alumnoCertificacion = alumnoCertificacion;
		}
		public personaC getPersona() {
			return persona;
		}
		public void setPersona(personaC persona) {
			this.persona = persona;
		}
		public String getTotalConcepto() {
			return totalConcepto;
		}
		public void setTotalConcepto(String totalConcepto) {
			this.totalConcepto = totalConcepto;
		}
		public String getTotalCertificado() {
			return totalCertificado;
		}
		public void setTotalCertificado(String totalCertificado) {
			this.totalCertificado = totalCertificado;
		}
		
		
    }
    


    public static class detalle {

        private String codigo;
        private String estudiante;
        private double promedio;        
        
        private String totalConcepto;
        private String totalCertificado;
        private String fechaImpresion;
        private String fechaEntrega;
        private double pagoConcepto;
        private double pagoCertificado;
        private int item;

        
        public String getCodigo() {
            return codigo;
        }

        
        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

       
        public String getEstudiante() {
            return estudiante;
        }

        
        public void setEstudiante(String estudiante) {
            this.estudiante = estudiante;
        }

        
        public double getPromedio() {
            return promedio;
        }

        
        public void setPromedio(double promedio) {
            this.promedio = promedio;
        }

       
        public String getTotalConcepto() {
            return totalConcepto;
        }

       
        public void setTotalConcepto(String totalConcepto) {
            this.totalConcepto = totalConcepto;
        }

        
        public String getTotalCertificado() {
            return totalCertificado;
        }

       
        public void setTotalCertificado(String totalCertificado) {
            this.totalCertificado = totalCertificado;
        }

      
        public String getFechaImpresion() {
            return fechaImpresion;
        }

       
        public void setFechaImpresion(String fechaImpresion) {
            this.fechaImpresion = fechaImpresion;
        }

       
        public String getFechaEntrega() {
            return fechaEntrega;
        }

        
        public void setFechaEntrega(String fechaEntrega) {
            this.fechaEntrega = fechaEntrega;
        }

       
        public int getItem() {
            return item;
        }

        
        public void setItem(int item) {
            this.item = item;
        }

        
        public double getPagoConcepto() {
            return pagoConcepto;
        }

       
        public void setPagoConcepto(double pagoConcepto) {
            this.pagoConcepto = pagoConcepto;
        }

       
        public double getPagoCertificado() {
            return pagoCertificado;
        }

       
        public void setPagoCertificado(double pagoCertificado) {
            this.pagoCertificado = pagoCertificado;
        }

      

    }

    public List<personaC> getPersonaL() {
		return personaL;
	}

	public void setPersonaL(List<personaC> personaL) {
		this.personaL = personaL;
	}

	public List<carrerasC> getCarreraL() {
		return carreraL;
	}

	public void setCarreraL(List<carrerasC> carreraL) {
		this.carreraL = carreraL;
	}

	public List<seccionC> getSeccionL() {
		return seccionL;
	}

	public void setSeccionL(List<seccionC> seccionL) {
		this.seccionL = seccionL;
	}

	public List<cursosC> getCursoL() {
		return cursoL;
	}

	public void setCursoL(List<cursosC> cursoL) {
		this.cursoL = cursoL;
	}
    
  

   
    public List<firmaCertificacionCursoC> getFirmaCertificacionCursoL() {
		return firmaCertificacionCursoL;
	}

	public void setFirmaCertificacionCursoL(List<firmaCertificacionCursoC> firmaCertificacionCursoL) {
		this.firmaCertificacionCursoL = firmaCertificacionCursoL;
	}
	public List<tipoCertificadoC> getTipoCertificadoL() {
		return tipoCertificadoL;
	}

	public void setTipoCertificadoL(List<tipoCertificadoC> tipoCertificadoL) {
		this.tipoCertificadoL = tipoCertificadoL;
	}

	public tipoCertificadoC getTipoCertificado() {
		return tipoCertificado;
	}

	public void setTipoCertificado(tipoCertificadoC tipoCertificado) {
		this.tipoCertificado = tipoCertificado;
	}

	public List<alumnoCertificadoPagoC> getSeleccionAlumnoCertificadoPagoL() {
		return seleccionAlumnoCertificadoPagoL;
	}

	public void setSeleccionAlumnoCertificadoPagoL(List<alumnoCertificadoPagoC> seleccionAlumnoCertificadoPagoL) {
		this.seleccionAlumnoCertificadoPagoL = seleccionAlumnoCertificadoPagoL;
	}

	public List<alumnoCertificadoPagoC> getAlumnoCertificadoPagoL() {
		return alumnoCertificadoPagoL;
	}

	public void setAlumnoCertificadoPagoL(List<alumnoCertificadoPagoC> alumnoCertificadoPagoL) {
		this.alumnoCertificadoPagoL = alumnoCertificadoPagoL;
	}
	   public boolean isBanderaTipoCertificado() {
			return banderaTipoCertificado;
		}

		public void setBanderaTipoCertificado(boolean banderaTipoCertificado) {
			this.banderaTipoCertificado = banderaTipoCertificado;
		}

	    public formatoUnicoTramiteC getFormatoUnicoTramite() {
			return formatoUnicoTramite;
		}
		public void setFormatoUnicoTramite(formatoUnicoTramiteC formatoUnicoTramite) {
			this.formatoUnicoTramite = formatoUnicoTramite;
		}
		 

		public String getAlumnoCopia() {
			return alumnoCopia;
		}
		public void setAlumnoCopia(String alumnoCopia) {
			this.alumnoCopia = alumnoCopia;
		}
}
