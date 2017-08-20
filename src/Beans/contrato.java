
package Beans;

import DAO.asignacionDAO;
import DAO.bancoDAO;
import DAO.cargoDAO;
import DAO.centroEducativoDAO;
import DAO.comisionDAO;
import DAO.curriculumVitaeDAO;
import DAO.cursoSeccionDocenteDAO;
import DAO.entidadPrestadoraSaludDAO;
import DAO.formaPagoDAO;
import DAO.gradoInstruccionDAO;

import DAO.idiomaDAO;
import DAO.institucionDAO;
import DAO.localDAO;
import DAO.modalidadPensionDAO;
import DAO.montoDAO;
import DAO.ocupacionDAO;
import DAO.oficinaDAO;
import DAO.paisDAO;
import DAO.parentescoDAO;
import DAO.periocidadDAO;
import DAO.periodoDAO;
import DAO.personaDAO;
import DAO.personaTituloDAO;
import DAO.personalAmonestacionDAO;
import DAO.personalCertificadoTrabajoDAO;
import DAO.personalContratoComisionDAO;
import DAO.personaCapacitacionDAO;
import DAO.personalContratoCursoDAO;
import DAO.personalContratoDAO;
import DAO.personalDAO;
import DAO.personalDocenteCategoriaDAO;
import DAO.personalExperienciaLaboralDAO;
import DAO.personaGradoAcademicoDAO;
import DAO.personaIdiomaDAO;
import DAO.personaParentescoDAO;

import DAO.personalDocenteDAO;
import DAO.personalDocenteRegimenDAO;
import DAO.personalTrabajoInvestigacionDAO;
import DAO.regimenPensionDAO;
import DAO.tipoCentroEducativoDAO;
import DAO.tipoContratoDAO;
import DAO.tipoMonedaDAO;
import DAO.ubigeoDepartamentoDAO;
import ENTIDAD.asignacionC;
import ENTIDAD.bancoC;
import ENTIDAD.cargoC;
import ENTIDAD.carrerasC;
import ENTIDAD.centroEducativoC;
import ENTIDAD.comisionC;
import ENTIDAD.curriculumVitaeC;
import ENTIDAD.cursoSeccionDocenteC;
import ENTIDAD.cursosC;
import ENTIDAD.entidadPrestadoraSaludC;
import ENTIDAD.formaPagoC;
import ENTIDAD.gradoInstruccionC;
import ENTIDAD.idiomaC;
import ENTIDAD.institucionC;
import ENTIDAD.localC;
import ENTIDAD.modalidadPensionC;
import ENTIDAD.montoC;
import ENTIDAD.ocupacionC;
import ENTIDAD.oficinaC;
import ENTIDAD.paisC;
import ENTIDAD.parentescoC;
import ENTIDAD.periocidadC;
import ENTIDAD.periodoC;
import ENTIDAD.personaC;
import ENTIDAD.personaCapacitacionC;
import ENTIDAD.personalC;
import ENTIDAD.personalCertificadoTrabajoC;
import ENTIDAD.personalContratoC;
import ENTIDAD.personalContratoComisionC;
import ENTIDAD.personaTituloC;

import ENTIDAD.personalAmonestacionC;
import ENTIDAD.personalContratoCursosC;
import ENTIDAD.personalExperienciaLaboralC;
import ENTIDAD.personaGradoAcademicoC;
import ENTIDAD.personaIdiomaC;
import ENTIDAD.personaParentescoC;

import ENTIDAD.personalDocenteC;
import ENTIDAD.personalDocenteCategoriaC;
import ENTIDAD.personalDocenteRegimenC;
import ENTIDAD.personalTrabajoInvestigacionC;
import ENTIDAD.regimenPensionC;
import ENTIDAD.seccionC;
import ENTIDAD.tipoCentroEducativoC;
import ENTIDAD.tipoContratoC;
import ENTIDAD.tipoMonedaC;
import ENTIDAD.tipoPersonalC;
import ENTIDAD.ubigeoDepartamentoC;

import java.io.IOException;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name="contratoB")
@ViewScoped
public class contrato implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<institucionC> institucionL;
	private UploadedFile file=null;

	private List<cursoSeccionDocenteC> cursoSeccionDocenteL=new ArrayList<>();
    private personalExperienciaLaboralC personalExperienciaLaboral=new personalExperienciaLaboralC();
    private List<personalExperienciaLaboralC> personalExperienciaLaboralL=new ArrayList<>();
    private List<personaTituloC> personaTituloL=new ArrayList<>();
    private List<personalAmonestacionC> personalAmonestacionL=new ArrayList<>();
    private List<personaIdiomaC> personaIdiomaL=new ArrayList<>();
    private personaIdiomaC personaIdioma=new personaIdiomaC();  
    private personalAmonestacionC personalAmonestacion=new personalAmonestacionC();
    private List<personaGradoAcademicoC> personaGradoAcademicoL=new ArrayList<>();
    private personaGradoAcademicoC personalGradoAcademico=new personaGradoAcademicoC();
    private personaTituloC personaTitulo=new personaTituloC();
    private List<personalTrabajoInvestigacionC> personalTrabajoInvestigacionL=new ArrayList<>();
    private personalTrabajoInvestigacionC personalTrabajoInvestigacion=new personalTrabajoInvestigacionC();
    private List<personaCapacitacionC> personaCapacitacionL=new ArrayList<>();
    private personaCapacitacionC personaCapacitacion=new personaCapacitacionC();
    private personalDocenteC personalDocente=null;
    private personaC persona=null;
    private personalContratoC personalContrato=new personalContratoC() ;
    
    private personalC personal=null;
    
    private tipoCentroEducativoC tipoCentroEducativo=new tipoCentroEducativoC();
    private List<asignacionPersonalC> asignacionPersonalL=new ArrayList<>();
    
    private List<tipoPersonalC> tipoPersonalL;
    
    
    private parentescoC parentesco;
    private List<parentescoC> parentescoL;
    private personaParentescoC personaParentesco;
    private curriculumVitaeC curriculumVitae=null; 
    private personaC personaFamiliar=new personaC();
    
    
    private List<personaC> personaFiltroL=new ArrayList<>();
    
    private List<contratoCursoPersonalC> contratoCursoPersonalL=new ArrayList<>();
    private List<personaFamilia> personaFamiliaL=new ArrayList<>();
    private List<personalDocenteRegimenC> personalDocenteRegimenL =new ArrayList<>();
    private List<personalDocenteCategoriaC> personalDocenteCategoriaL =new ArrayList<>();
    
    private String departamento;
    private String provincia;
    private String distrito;
    
    private String departamentoNaci;
    private String provinciaNaci;
    private String distritoNaci;
    
    private boolean bandera;
    private boolean banderaContrato;
    private boolean banderaPersonaTitulo;
    private boolean banderaGradoAcademico;
    private boolean banderaCapacitacion;
    private boolean banderaIdioma;
    private boolean banderaTrabajoInvestigacion;
    private boolean banderaExperienciaLaboral;
    private boolean banderaPestaña;
    private boolean banderaPersonalDocente=false;
    
    private boolean banderaNuevo;
    private boolean banderaGuardar;
    private boolean banderaCancelar;
    private boolean banderaEditar;
    private boolean banderaEliminar;
    private boolean banderaBuscar;
    private boolean banderaImprimir;    
    private boolean banderaCentroEducativo;
   



	private int pestanaActiva=0;
    private Date fechaImpresion; 
    private int tituloTipoInstitucion;
    private String institucion="%";
    private String periodo="%";
    private int totalHora=0;
  //  private String filtroPersona;
    
    
    private String busPaterno;
    private String busMaterno;
    private String busNombres;
    
    
    private List<personalC> personalL;
   
    private List<personalContratoC> personalContratoL=new ArrayList<>();


	private List<personaC> personaL;
    private List<paisC> paisL=new ArrayList<>();
    private List<ubigeoDepartamentoC> departamentoL;
  
    private List<tipoMonedaC> tipoMonedaL;
    
  
    private List<bancoC> bancoL;
    private List<regimenPensionC> regimenPensionL;
    
    private List<entidadPrestadoraSaludC> entidadPrestadoraSaludL;
    private List<ocupacionC> ocupacionL;
    private List<cargoC> cargoL;
    
    private List<formaPagoC> formaPagoL;
    
    private List<modalidadPensionC> modalidadPensionL;
    
    private List<oficinaC> oficinaL;
   private List<localC> localL;
   private List<gradoInstruccionC> gradoInstruccionL;
   private List<centroEducativoC> centroEducativoL;
   private List<centroEducativoC> centroEducativoUniversidadL;
   private List<centroEducativoC> centroEducativoTituloL;
   
   private centroEducativoC centroEducativo=null;
   
   
   private List<tipoCentroEducativoC> tipoCentroEducativoL;
   private List<idiomaC> idiomaL;
   private List<periodoC> periodoL=new ArrayList<>();
   
   private List<periocidadC> periocidadL;
	private List<tipoContratoC> tipoContratoL;
   private String personaParantescoF="";
	
	private String codigoPersonaT="";
	
	private personalCertificadoTrabajoC personalCertificadoTrabajo;
	private List<personalCertificadoTrabajoC> personalCertificadoTrabajoL;
	
	
	
	
	
	public boolean isBanderaCentroEducativo() {
		return banderaCentroEducativo;
		
	}
	public void setBanderaCentroEducativo(boolean banderaCentroEducativo) {
		this.banderaCentroEducativo = banderaCentroEducativo;
	}
	
	
	
	
	
	
	public List<centroEducativoC> getCentroEducativoTituloL() {
		return centroEducativoTituloL;
	}
	public void setCentroEducativoTituloL(List<centroEducativoC> centroEducativoTituloL) {
		this.centroEducativoTituloL = centroEducativoTituloL;
	}
	public List<centroEducativoC> getCentroEducativoUniversidadL() {
		return centroEducativoUniversidadL;
	}
	public void setCentroEducativoUniversidadL(List<centroEducativoC> centroEducativoUniversidadL) {
		this.centroEducativoUniversidadL = centroEducativoUniversidadL;
	}

	public void mostrarCentroEducativo(){

	       
		centroEducativoL = new centroEducativoDAO().mostrarCentroEducativo();
	}
	
	public void mostrarTipoCentroEducativo(){

       
		centroEducativoTituloL = new centroEducativoDAO().mostrarCentroEducativo(tituloTipoInstitucion);
	}
	
	
	public void insertarCentroEducativo(){
		new centroEducativoDAO().insertar(centroEducativo);
		centroEducativoL = new centroEducativoDAO().mostrarCentroEducativo();
		centroEducativoUniversidadL = new centroEducativoDAO().mostrarCentroEducativo(3);
		banderaCentroEducativo=false;
		util.script("$('.bloquear').removeClass('bloquear-select');");
	}
	
	public void eliminarCentroEducativo(){
		new centroEducativoDAO().eliminar(centroEducativo);		
		centroEducativoL = new centroEducativoDAO().mostrarCentroEducativo();
		centroEducativo=null;
	}
	public void editarCentroEducativo(){
		banderaCentroEducativo=true;
		util.script("$('.bloquear').addClass('bloquear-select');");
	}
	public void cancelarCentroEducativo(){
		banderaCentroEducativo=false;
		util.script("$('.bloquear').removeClass('bloquear-select');");
	}
	public void nuevoCentroEducativo(){
	
		centroEducativo=new centroEducativoC(null, null, null, 1, 3, 1);
		banderaCentroEducativo=true;
		util.script("$('#txtDescripcionCE').focus();");
		util.script("$('.bloquear').addClass('bloquear-select');");
	
	}
	
	
	public centroEducativoC getCentroEducativo() {
		return centroEducativo;
	}

	public void setCentroEducativo(centroEducativoC centroEducativo) {
		this.centroEducativo = centroEducativo;
	}

	public void nuevoPersonalCertificadoTrabajo(){
		personalCertificadoTrabajo=new personalCertificadoTrabajoC(-1,1,0, personal.getPersonal(), 1, null, null, 1);
		util.script("dlgCertificadoTrabajo.show()");
	}
	
	public void editarPersonalCertificadoTrabajo(personalCertificadoTrabajoC item){
		personalCertificadoTrabajo=item;
		util.script("dlgCertificadoTrabajo.show()");
	}
	
	public void insertarPersonalCertificadoTrabajo(){
		new personalCertificadoTrabajoDAO().insertar(personalCertificadoTrabajo);
		personalCertificadoTrabajoL=new personalCertificadoTrabajoDAO().mostrarPersonalCertificadoTrabajo(personal.getPersonal());
		util.script("dlgCertificadoTrabajo.hide()");
	}
	
	public void eliminarPersonalCertificadoTrabajo(personalCertificadoTrabajoC item){
		new personalCertificadoTrabajoDAO().eliminar(item);
		personalCertificadoTrabajoL=new personalCertificadoTrabajoDAO().mostrarPersonalCertificadoTrabajo(personal.getPersonal());
		
	}
	
	
	public personalCertificadoTrabajoC getPersonalCertificadoTrabajo() {
		return personalCertificadoTrabajo;
	}


	public void setPersonalCertificadoTrabajo(personalCertificadoTrabajoC personalCertificadoTrabajo) {
		this.personalCertificadoTrabajo = personalCertificadoTrabajo;
	}


	public List<personalCertificadoTrabajoC> getPersonalCertificadoTrabajoL() {
		return personalCertificadoTrabajoL;
	}


	public void setPersonalCertificadoTrabajoL(List<personalCertificadoTrabajoC> personalCertificadoTrabajoL) {
		this.personalCertificadoTrabajoL = personalCertificadoTrabajoL;
	}


	public boolean isBanderaImprimir() {
		return banderaImprimir;
	}


	public void setBanderaImprimir(boolean banderaImprimir) {
		this.banderaImprimir = banderaImprimir;
	}


	public boolean isBanderaBuscar() {
		return banderaBuscar;
	}


	public void setBanderaBuscar(boolean banderaBuscar) {
		this.banderaBuscar = banderaBuscar;
	}


	public String getPersonaParantescoF() {
	return personaParantescoF;
}


public void setPersonaParantescoF(String personaParantescoF) {
	this.personaParantescoF = personaParantescoF;
}






	public UploadedFile getFile() {
		return file;
	}






	public void setFile(UploadedFile file) {
		this.file = file;
	}






	public List<asignacionPersonalC> getAsignacionPersonalL() {
		return asignacionPersonalL;
	}






	public void setAsignacionPersonalL(List<asignacionPersonalC> asignacionPersonalL) {
		this.asignacionPersonalL = asignacionPersonalL;
	}






	public curriculumVitaeC getCurriculumVitae() {
		return curriculumVitae;
	}






	public void setCurriculumVitae(curriculumVitaeC curriculumVitae) {
		this.curriculumVitae = curriculumVitae;
	}



	public void mostrarFechaPeriodo(){
		periodoC itemPeriodo=new periodoDAO().mostrarPeriodo(personalContrato.getInstitucion(), personalContrato.getPeriodo());
		personalContrato.setFechaInicio(itemPeriodo.getFechaDesde());
		personalContrato.setFechaFin(itemPeriodo.getFechaHasta());
	}


	public contrato() {
        
        
        
        
        institucionL=new institucionDAO().mostrarInstitucion();
        
        
        
        paisL=new paisDAO().mostrarPais();
      
        departamentoL = new ubigeoDepartamentoDAO().mostrarDepartamento();
        

        tipoMonedaL=new tipoMonedaDAO().mostrarTipoMoneda();
        
        bancoL=new bancoDAO().mostrarBanco();
        
        regimenPensionL=new regimenPensionDAO().mostrarRegimenPension();
   
        entidadPrestadoraSaludL=new entidadPrestadoraSaludDAO().mostrarEntidadPrestadoraSalud();
        
        
        ocupacionL = new ocupacionDAO().mostrarOcupacion();
        
      
        cargoL=new cargoDAO().mostrarCargo();
        
      
        formaPagoL=new formaPagoDAO().mostrarFormaPago();
        
       
        modalidadPensionL=new modalidadPensionDAO().mostrarModalidadIngreso();
        
       
        oficinaL=new oficinaDAO().mostrarOficinas(1);
       
        localL=new localDAO().mostrarLocal();
        
        
        
        gradoInstruccionL = new gradoInstruccionDAO().mostrarGrado(4);
        
        
        
        centroEducativoUniversidadL = new centroEducativoDAO().mostrarCentroEducativo(3);
        
       
        tipoCentroEducativoL=new tipoCentroEducativoDAO().mostrarTipoCentroEducativo();
        
     
        idiomaL=new idiomaDAO().mostrarIdioma();
      
        
        
        periocidadL=new periocidadDAO().mostrarPeriocidad();
        
        personalDocenteRegimenL=new personalDocenteRegimenDAO().mostrarPersonalDocenteRegimen(); 
        
        personalDocenteCategoriaL=new personalDocenteCategoriaDAO().mostrarPersonalDocenteCategoria();
        
        parentescoL=new parentescoDAO().mostrarParentesco();
        
        asignacionPersonalC itemAsignacionPersonal;
        for (comisionC itemComision: new comisionDAO().mostrarComision()){
        	itemAsignacionPersonal=new asignacionPersonalC(itemComision);
        	for(asignacionC itemAsignacion:new asignacionDAO().mostrarAsignacion(itemComision.getComision())){
        		itemAsignacionPersonal.asignacionL.add(itemAsignacion);
        	}
        	asignacionPersonalL.add(itemAsignacionPersonal);
        }
        
        
        estadoBotones(true,false,false,false,false,true,false);
    }
	

	
	public void editarCurriculumVitae(){
		estadoBotones(false,true,true,false,false,false,false);
		bandera=true;
		util.script("habilitarPestana(9);");
	}
	
	public void insertaCurriculum(){
		new curriculumVitaeDAO().insertar(curriculumVitae);
		util.script("habilitarPestana(-1);");
		bandera=false;
		estadoBotones(false,false,false,true,true,true,false);
	}
	
	
    public void insertarCurriculumVitae() throws IOException{
    	
    	
    curriculumVitae.setRuta("D:\\imagenJava\\"+curriculumVitae.getPersonal()+".pdf");
     curriculumVitae.setPeso((int) file.getSize());
     
     
     new curriculumVitaeDAO().insertar(curriculumVitae, file);
 
     System.out.println("Se registro correctamente");
    
     
     /*
   	 InputStream inputStream = null;
     OutputStream outputStream = null;
	 try {
	 System.out.println("file.getFileName()");
      inputStream = file.getInputstream();          
      outputStream = new FileOutputStream(new File(curriculumVitae.getRuta())) ;
      int read = 0;
      byte[] bytes = new byte[1024];
   
      while ((read = inputStream.read(bytes)) != -1) {
         outputStream.write(bytes, 0, read);
      }
      estadoBotones(false,false,false,false,true);
	 } catch (IOException e) {
         System.out.println(e.getMessage());
} finally {
     if (inputStream != null) {
    inputStream.close();
    
 }
 if (outputStream != null) {
    outputStream.close();
 }
 
}*/
    }
    
	
	public void nuevoCurriculumVitae(){
		curriculumVitae=new curriculumVitaeC(personal.getPersonal(), null, null,0,null, 0,1);
		estadoBotones(false,false,false,false,false,true,false);
		
		util.script("$('#btInsertarCV').attr('disabled','disabled');");
		util.script("dlgCurriculum.show()");
	}
	
	public void cancelarCurriculum(){
		curriculumVitae=new curriculumVitaeDAO().mostrarCurriculumVitae(personal.getcPersonal());
		System.out.println("cerrar");
		util.script("dlgCurriculum.hide()");
	}
	
	public void cancelarCurriculumVitae(){
		estadoBotones(false,false,false,true,true,true,false);
		bandera=false;
		   util.script("habilitarPestana(-1);");
	}
	public void eliminarCurriculumVitae(){
		new curriculumVitaeDAO().eliminar(curriculumVitae);
		curriculumVitae=new curriculumVitaeDAO().mostrarCurriculumVitae(personal.getcPersonal());
		estadoBotones(true,false,false,false,false,true,false);
	}
	
    public void insertarPersonalComision(int comision,int asignacion) throws IOException{    	
    	new personalContratoComisionDAO().insertar(new personalContratoComisionC(personal.getPersonal(), 1, comision, asignacion, 1)); 
    	
    }
    
    public List<personalDocenteCategoriaC> getPersonalDocenteCategoriaL() {
		return personalDocenteCategoriaL;
	}

	public void setPersonalDocenteCategoriaL(List<personalDocenteCategoriaC> personalDocenteCategoriaL) {
		this.personalDocenteCategoriaL = personalDocenteCategoriaL;
	}

	public List<personalDocenteRegimenC> getPersonalDocenteRegimenL() {
		return personalDocenteRegimenL;
	}

	public void setPersonalDocenteRegimenL(List<personalDocenteRegimenC> personalDocenteRegimenL) {
		this.personalDocenteRegimenL = personalDocenteRegimenL;
	}

	public void mostrarTipoContrato(){
    	tipoContratoL = new tipoContratoDAO().mostrarTipoContratoTipoPersona(personalContrato.getTipoPersonal());
    }
 
            
    public void buscarPersona(){
    	busPaterno="";
    	busMaterno="";
    	busNombres="";
    
    }
    public void mostrarPeriodo(){
    	periodoL=new periodoDAO().mostrarPeriodoInstitucion(personalContrato.getInstitucion());
    }
    
    public void validarNumero(){
  
    	personaC item=new personaDAO().validaDNI(persona.getPersona(), persona.getNumero_documento());
    	if(item!=null){
    		persona.setNumero_documento("");
    		util.script("notificacion('El D.N.I ya existe',500,5000,'error')");	
    	}
    	 
    	
    }
    
    public void seleccionarFiltroPersona(personaC itemPersona){
        personaParentesco.setPersonaParentesco(itemPersona.getPersona());
    //    filtroPersona=itemPersona.getPaterno() +" "+ itemPersona.getMaterno() +" "+ itemPersona.getNombres();
    }
    public void insertarPersonaFamiliar(){
      
       // new personaDAO().insertar(personaFamiliar);
        util.script("dlgParentesco.hide()");
    }
    
    public void mostrarParentesco(){

        parentescoL=new parentescoDAO().mostrarParentesco();
    }
    public void nuevoParentesco(){
        personaParentesco=new personaParentescoC();
        personaParentesco.setPersona(persona.getPersona());
        util.script("dlgParentesco.show()");
  //      filtroPersona="";
    }
    public void insertarPersonaParentesco(){
   
        new personaParentescoDAO().insertar(personaParentesco);
        mostrarPersonaParentesco();
        util.script("dlgParentesco.hide()");
    }
    public void eliminarPersonaParentesco(personaParentescoC itemPersonaParentesco){
   
        new personaParentescoDAO().eliminar(itemPersonaParentesco);
        mostrarPersonaParentesco();
      
    }
    
    public void mostrarPersonaParentesco(){
        personaFamiliaL=new ArrayList<>();
        
        personaFamilia itemPersonaFamilia ;
        for(personaParentescoC itemPersonaParentesco:new personaParentescoDAO().mostrarPersonaParentesco("00000000000000002089")){
            itemPersonaFamilia=new personaFamilia(itemPersonaParentesco);
            
            itemPersonaFamilia.personaF=new personaDAO().getBuscaPersonaCodigo(itemPersonaParentesco.getPersonaParentesco());
            itemPersonaFamilia.parentesco=new parentescoDAO().mostrarParentesco(itemPersonaParentesco.getParentesco());
            personaFamiliaL.add(itemPersonaFamilia);
        }
        
    }
    
    public void nuevoPersonaParentesco(){
    	System.out.println("parentesco:" + personaParentesco.getParentesco());        
        switch (personaParentesco.getParentesco()){
            case 1:                 
                    personaFamiliar.setPaterno(persona.getPaterno());    
                    util.script("$('#txtMaternoF').focus()");              
                break;
            case 2:
            		personaFamiliar.setPaterno(persona.getMaterno());
            		util.script("$('#txtMaternoF').focus()");
            break;
            default:            	
                personaFamiliar.setPaterno("");
                personaFamiliar.setMaterno("");
                util.script("$('#txtPaternoF').focus()");
                
        }
        util.script("dlgPersona.show()");
    }
    public void estadoPestaña(boolean item){
        banderaPestaña=item;
    }
    
    public void estadoBotones(boolean nuevo,boolean guardar,boolean cancelar,boolean editar,boolean eliminar,boolean buscar ,boolean imprimir){
    	banderaNuevo=nuevo;
    	banderaGuardar=guardar;
    	banderaCancelar=cancelar;
    	banderaEditar=editar;
    	banderaEliminar=eliminar;
    	banderaBuscar=buscar;
    	banderaImprimir=imprimir;
    }
    
    public void estadoPestania(){
    	 switch (pestanaActiva) {
  		case 0: // Datos personales
  			if(persona ==null){
  				estadoBotones(true,false,false,false,false,true,false);	
  			}else{
  				estadoBotones(true,false,false,true,false,true,false);
  			}
  			
  			break;

  		case 1: // Datos Laborales
  			
  			if(persona ==null){
  				estadoBotones(false,false,false,false,false,true,false);
  			}else{
  				if(personal ==null){
  	 				estadoBotones(true,false,false,false,false,true,false);
  	 			}else{
  	 				estadoBotones(false,false,false,true,false,true,false);
  	 			}
  			}
  				
  			
  			break;
  		case 2: // Formacion Academica
  			estadoBotones(false,false,false,false,false,true,false);
  			break;
  		case 3: // Experiencia Laboras
  			if(personal ==null){
  				estadoBotones(false,false,false,false,false,true,false);
  				
  			}else{
  				estadoBotones(true,false,false,false,false,true,false);
  			}
  			
  		
  			break;
  		case 4: // Contrato
  			if(personal ==null){
  				estadoBotones(false,false,false,false,false,true,false);
  				
  			}else{
  				estadoBotones(true,false,false,false,false,true,false);
  			}
  			
  			break;
  		case 7: // Asignacion personal
  			
  	 				estadoBotones(false,false,false,false,false,false,false);
  	 		
  	
  			break;
  		case 8: // Personal Docente
  			if(personal ==null){
  				estadoBotones(false,false,false,false,false,true,false);
  			}else {
  				if(personalDocente==null){
  	 				estadoBotones(true,false,false,false,false,true,false);
  	 			}else{
  	 				estadoBotones(false,false,false,true,false,true,false);
  	 			}
  			}
  				
  	
  			break;
  		case 9:
  			if(personal ==null){
  				estadoBotones(false,false,false,false,false,true,false);
  			}else {
  				if(curriculumVitae==null){
  					estadoBotones(true,false,false,false,false,true,false);
  				}else{
  					estadoBotones(false,false,false,true,true,true,false);	
  				}
  				
  			}
  			break;
  		case 10:
  			if(personal ==null){
  				estadoBotones(false,false,false,false,false,true,false);
  			}else {
  				
  					estadoBotones(true,false,false,false,false,false,false);	
  				}
  				
  			
  			break;
  		}
    }
    
    
   
    
    public void onTabChange(TabChangeEvent event) {        
    	//System.out.println(event.getTab().getAriaLabel().toString()); 
        pestanaActiva=Integer.parseInt(event.getTab().getTitletip()) ;
        estadoPestania();
        
       
   
    }
    
    public void nuevoPersonalDocente(){
    	
	    personalDocente=new personalDocenteC(null,personal.getPersonal(),null, null, false, 0, null, false, false, 1);
	    periodoL=new periodoDAO().mostrarPeriodoInstitucion(1);
        banderaPersonalDocente=true;
        
        estadoBotones(false,true,true,false,false,true,false);
        util.script("habilitarPestana(8);");
       
    }
    public void insertarPersonalDocente(){
        
        String codigo="";
        codigo=new personalDocenteDAO().insertar(personalDocente);
        personalDocente.setCpersonal(codigo);
        
        System.out.println("persona " +personal.getPersonal());
        System.out.println("personaC " +personal.getcPersonal());
        
        if(personal.getcPersonal()==null){        	
        	personal.setcPersonal(codigo);
        	new personalDAO().insertarPersonal(personal);
        }
        
        banderaPersonalDocente=false;
        bandera=false;
        estadoBotones(false, false, false, true, false,true,false);
        util.script("habilitarPestana(-1);");
    }
    
    public void editarPersonalDocente(){
        banderaPersonalDocente=true;
        bandera=true;
        estadoBotones(false, true, true, false, false,false,false);
        util.script("habilitarPestana(8);");
    }
    
    public void eliminarPersonalDocente(){
          
        new personalDocenteDAO().eliminar(personalDocente);
    }
    public void cancelarPersonalDocente(){
        banderaPersonalDocente=false;
        bandera=false;
        estadoBotones(false, false, false, true, false,true,false);
        util.script("habilitarPestana(-1);");
    }
   
   
    
 
    
    
    
    public void guardarCursoContrato(){
        
  
        for (cursoSeccionDocenteC item : cursoSeccionDocenteL) {
        	new cursoSeccionDocenteDAO().insertar(item);
        }
        util.alert("se guardo Correctamente");
                
        
    }
    public void busquedaPersona(){        
        
        if(persona.getDireccion_ubigeo().toString().length()==6){
            departamento=persona.getDireccion_ubigeo().substring(0, 2);        
            provincia=persona.getDireccion_ubigeo().substring(2, 4);        
            distrito=persona.getDireccion_ubigeo().substring(4, 6);
        }        
        
         if(persona.getNacimiento_ubigeo().toString().length()==6){
            departamentoNaci=persona.getNacimiento_ubigeo().substring(0, 2);        
            provinciaNaci=persona.getNacimiento_ubigeo().substring(2, 4);        
            distritoNaci=persona.getNacimiento_ubigeo().substring(4, 6);
        }
         
         
         
        personaGradoAcademicoL=new ArrayList<>();
     	personalAmonestacionL=new ArrayList<>();
     	personalTrabajoInvestigacionL=new ArrayList<>();
     	personalExperienciaLaboralL=new ArrayList<>();
     	curriculumVitae=new curriculumVitaeC();
     	personalDocente=new personalDocenteC();
     	personalContratoL=new ArrayList<>();
     	personalCertificadoTrabajoL=new ArrayList<>();
     	contratoCursoPersonalL=new ArrayList<>();
     	personalContrato=new personalContratoC();
     	
     	
         
        personaIdiomaL=new personaIdiomaDAO().mostrarPersonaIdioma(persona.getPersona());        
        personaTituloL=new personaTituloDAO().mostrarPersonalTitulo(persona.getPersona());
        personaCapacitacionL=new personaCapacitacionDAO().mostrarPersonalCapacitacion(persona.getPersona());
        personaGradoAcademicoL=new personaGradoAcademicoDAO().mostrarPersonalGradoAcademico(persona.getPersona());
        personalTrabajoInvestigacionL=new personalTrabajoInvestigacionDAO().mostrarPersonaTrabajoInvestigacion(persona.getPersona());
        
        personal=new personalDAO().mostrarPersona(persona.getPersona());
        if (personal != null)
        {
          
            
       
            personalAmonestacionL=new personalAmonestacionDAO().mostrarPersonalAmonestacion(personal.getPersonal());
            personalExperienciaLaboralL=new personalExperienciaLaboralDAO().mostrarPersonalExperienciaLaboral(personal.getPersonal());            
            curriculumVitae=new curriculumVitaeDAO().mostrarCurriculumVitae(personal.getPersonal());             
            personalDocente=new personalDocenteDAO().mostrarPersonalDocente(personal.getPersonal());
            personalContratoL=new personalContratoDAO().mostrarPersonalContrato(personal.getPersonal());
            personalCertificadoTrabajoL=new personalCertificadoTrabajoDAO().mostrarPersonalCertificadoTrabajo(personal.getPersonal());
            
            
        }
        
        estadoPestania();
        
    }
  
    
    public void cancelarPersona(){
    	
    	if(!codigoPersonaT.isEmpty()){
    		persona=new personaDAO().getBuscaPersonaCodigo(codigoPersonaT);
    		busquedaPersona();
    	}
    	
        bandera=false;
    	estadoBotones(true,false,false,false,false,true,false);
    	 util.script("habilitarPestana(-1);");
    }
    public void cancelarDatoLaboral(){
    	if (personal!=null){
    		estadoBotones(false,false,false,true,false,true,false);	
    	}else{
    		estadoBotones(true,false,false,false,false,true,false);
    	}
        bandera=false;
        util.script("habilitarPestana(-1);");
    }
    
    public void editarPersona(){
        
        bandera=true;        
        estadoBotones(false,true,true,false,false,false,false);
        util.script("habilitarPestana(0);");
   }
    
    public void editarPersonal(){
     
         bandera=true;        
         estadoBotones(false,true,true,false,false,false,false);
         util.script("habilitarPestana(1);");
    }
    
    public void nuevoPersona(){
       
            if(persona!=null){
            	codigoPersonaT=persona.getPersona();
            }
    
        
        bandera=true;
        departamento="";
        provincia="";
        distrito="";
        
        tituloTipoInstitucion=0;
        persona=new personaC();
        persona.setPersona("");
        persona.setPais(143);
        persona.setTipodocumento(1);
        departamento="15";
        provincia="01";
        estadoBotones(false,true,true,false,false,false,false);
        
        
       util.script("habilitarPestana(0);");
       util.script("$('#txtNumero').focus()");
        
    }

    
  
   
   
    
    public void eliminarTitulo(personaTituloC item ){

        new personaTituloDAO().eliminar(item);
        
        personaTituloL=new personaTituloDAO().mostrarPersonalTitulo(persona.getPersona());
    }
    
   
      public void eliminarTrabajo(int index ){        
        
        personalTrabajoInvestigacionL.get(index).setEstadoRegistro(personalTrabajoInvestigacionL.get(index).getEstadoRegistro()==0?1:0);
        
    }
      
     
    
     public void eliminarAmonestacion(int index ){
        personalAmonestacionL.get(index).setEstadoRegistro(0);
        
        
    }
    
    public void nuevoContrato( ){
        personalContrato=new personalContratoC(0,null,personal.getPersonal(), -1, 0, 0, 0, null, null, 0.0, 0.0, 0.0, null, 0, 1);
        bandera=true;
        banderaContrato=true;
        estadoBotones(false,true,true,false,false,false,false);
        util.script("habilitarPestana(4);");
        
        
    }
    
    public void cancelarContrato(){
    	bandera=false;
        banderaContrato=false;
        if(personalContrato!=null){
        	estadoBotones(true,false,false,false,false,true,true);	
        }else{
        	estadoBotones(true,false,false,false,false,true,false);
        }
        
        util.script("habilitarPestana(-1);");
    }
    
    public void editarContrato(){
        
        banderaContrato=true;
        estadoBotones(false,true,true,false,false,false,false);
    }
    
    public void renovacionContrato(){
//        personalContrato =new personalContratoC();
//        personalContrato.setItem(-1);
//        personalContrato.setSituacion(2);
//        personalContrato.setFechaInicio(item.getFechaFin());
//        personalContrato.setPeriocidad(item.getPeriocidad());
//        personalContrato.setTipoContrato(item.tipoContrato);
        banderaContrato=false;
        
    }


    
    public void eliminarTitulo(int index ){
        personaTituloL.get(index).setEstadoRegistro(0);
        
        
    }
    public void guardarPersona(){
    	
    	 persona.setDireccion_ubigeo(departamento+provincia+distrito);
	     persona.setNacimiento_ubigeo(departamentoNaci+provinciaNaci+distritoNaci);
	     persona.setPersona(new personaDAO().insertar(persona)); 
	     bandera=false;
        estadoBotones(true,false,false,true,false,true,false); 
        util.script("notificacion('Se registro persona ',500,5000,'correcto')");
        util.script("habilitarPestana(-1);");
    }

    
    public void nuevoPersonal(){
    	personal=new personalC();
    	personal.setPersona(persona.getPersona());
   	 	estadoBotones(false,true,true,false,false,true,false);  	

   	 	bandera=true;
   	  util.script("habilitarPestana(1);");
    	
    	
    }
    
 public void insertarPersonal(){
	 	String codigo=new personalDAO().insertarPersonal(personal);
	 	personal.setPersonal(codigo);
	 	estadoBotones(false,false,false,true,false,true,false);  	

   	 	bandera=false;
   	 util.script("habilitarPestana(-1);");
		util.script("notificacion('Se registro personal ',500,5000,'correcto')");
    }
    
    public void eliminarContrato(){
    	new personalContratoDAO().eliminar(personalContrato);
    	personalContratoL=new personalContratoDAO().mostrarPersonalContrato(personal.getPersonal());
    	util.script("notificacion('Se Elimino el contrato ',500,5000,'correcto')");
    }
    public void insertarContrato(){
        
        new personalContratoDAO().insertar(personalContrato);             
        personalContratoL=new personalContratoDAO().mostrarPersonalContrato(personal.getPersonal());
        banderaContrato=false;
        bandera=false;
        estadoBotones(true, false, false, false, false,true,false);
        util.script("habilitarPestana(-1);");
        util.script("notificacion('Se registro el contrato ',500,5000,'correcto')");
    }
    
    public void nuevoGrado(){
        personalGradoAcademico=new personaGradoAcademicoC(persona.getPersona(), -1, 0, null, 0, null, 1);
        banderaGradoAcademico=true;
    }
    
    public void editarGrado(personaGradoAcademicoC item){
        personalGradoAcademico=item;
        banderaGradoAcademico=true;
    }
    
    
    public void agregarGrado(){
       
        new personaGradoAcademicoDAO().insertar(personalGradoAcademico);     
        personaGradoAcademicoL=new personaGradoAcademicoDAO().mostrarPersonalGradoAcademico(personal.getPersona());
        banderaGradoAcademico=false;
        
    }
    public void seleccionarContrato(){
    	mostrarPeriodo();
    	mostrarTipoContrato();
        estadoBotones(true, false, false, true, true,true,true);
        if(personalContrato.getTipoPersonal()==4){
        		contratoCursoPersonalL=new personalContratoCursoDAO().mostrarCursoSeccionDocenteContrato(personalContrato.getInstitucion(), personalContrato.getPeriodo(), personalContrato.getPersonal()) ;
        }
        
    }
    
    public void cancelarGrado(){
 
         banderaGradoAcademico=false;
        
    }
    
     public void eliminarGrado(personaGradoAcademicoC item ){
        
    
        new personaGradoAcademicoDAO().eliminar(item);
      
        personaGradoAcademicoL=new personaGradoAcademicoDAO().mostrarPersonalGradoAcademico(personal.getPersona());
        
    }
    

    public void insertarContratoCursoPersonal(contratoCursoPersonalC item){
    	
    	
       new personalContratoCursoDAO().insertar(new personalContratoCursosC(personalContrato.getPersonal() , personalContrato.getContrato(), personalContrato.getInstitucion(), personalContrato.getPeriodo(), item.carrera.getCarrera(), item.cursoSeccionDocente.getMalla(), item.seccion.getSeccion(), item.curso.getCurso(), personalContrato.getFechaInicio(), personalContrato.getFechaFin(), null, 1));       
       item.cursoSeccionDocente.setEstadoRegistro(1);
       new cursoSeccionDocenteDAO().insertar(item.cursoSeccionDocente);
       contratoCursoPersonalL=new personalContratoCursoDAO().mostrarCursoSeccionDocenteContrato(personalContrato.getInstitucion(), personalContrato.getPeriodo(), personalContrato.getPersonal()) ;
        
    }
    
    public void eliminarContratoCursoPersonal(contratoCursoPersonalC item){
        
    	 new personalContratoCursoDAO().eliminar(new personalContratoCursosC(personalContrato.getPersonal() , personalContrato.getContrato(), personalContrato.getInstitucion(), personalContrato.getPeriodo(), item.carrera.getCarrera(), item.cursoSeccionDocente.getMalla(), item.seccion.getSeccion(), item.curso.getCurso(), personalContrato.getFechaInicio(), personalContrato.getFechaFin(), null, 1));
    	 item.cursoSeccionDocente.setEstadoRegistro(59);
         new cursoSeccionDocenteDAO().insertar(item.cursoSeccionDocente);
         contratoCursoPersonalL=new personalContratoCursoDAO().mostrarCursoSeccionDocenteContrato(personalContrato.getInstitucion(), personalContrato.getPeriodo(), personalContrato.getPersonal()) ;
    }
        
    public void nuevoCapacitacion() {
        
        personaCapacitacion=new personaCapacitacionC(persona.getPersona(), -1, 0, null, 0, null, 1);
        banderaCapacitacion=true;
}
    
 public void editarCapacitacion(personaCapacitacionC item) {
	 	personaCapacitacion=item;
        
        banderaCapacitacion=true;
}
    
     public void agregarCapacitacion(){
      
        new personaCapacitacionDAO().insertar(personaCapacitacion);
      
        personaCapacitacionL=new personaCapacitacionDAO().mostrarPersonalCapacitacion(persona.getPersona());
        banderaCapacitacion=false;
        
    }
     
     public void cancelarCapacitacion(){
     
         banderaCapacitacion=false;
         
     }
     
     public void eliminarCapacitacion(personaCapacitacionC item){
         
         new personaCapacitacionDAO().eliminar(item);
      
        personaCapacitacionL=new personaCapacitacionDAO().mostrarPersonalCapacitacion(persona.getPersona());
     }
     
     public void nuevoIdioma(){
         personaIdioma=new personaIdiomaC(persona.getPersona(), -1, 0, null, 1);        
        banderaIdioma=true;
        
    }
     public void editarIdioma(personaIdiomaC item){
         personaIdioma=item;        
        banderaIdioma=true;
        
    }
    
    public void agregarIdioma(){
     
        new personaIdiomaDAO().insertar(personaIdioma);
      
        personaIdiomaL=new personaIdiomaDAO().mostrarPersonaIdioma(persona.getPersona());
        banderaIdioma=false;
        
    }
    
    public void cancelarIdioma(){
       
        banderaIdioma=false;
        
    }
    
     public void eliminarIdiomas(personaIdiomaC item ){        
        
     
       new personaIdiomaDAO().eliminar(item);
   
        personaIdiomaL=new personaIdiomaDAO().mostrarPersonaIdioma(persona.getPersona());
        
    }
    
    public void agregarTrabajoInvestigacion(){
        new personalTrabajoInvestigacionDAO().insertar(personalTrabajoInvestigacion);
        personalTrabajoInvestigacionL=new personalTrabajoInvestigacionDAO().mostrarPersonaTrabajoInvestigacion(persona.getPersona());
        banderaTrabajoInvestigacion=false;   
    }
    
    public void eliminarTrabajoInvestigacion(personalTrabajoInvestigacionC item){
        new personalTrabajoInvestigacionDAO().eliminar(item);
        personalTrabajoInvestigacionL=new personalTrabajoInvestigacionDAO().mostrarPersonaTrabajoInvestigacion(persona.getPersona());
        banderaTrabajoInvestigacion=false;   
    }
    
 public void nuevoTrabajoInvestigacion(){
	 	personalTrabajoInvestigacion=new personalTrabajoInvestigacionC(persona.getPersona(), 0, null, null, 1);
        banderaTrabajoInvestigacion=true;
        util.script("$('#txtTrabajoTema').focus();");
        
    }
 
 public void editarTrabajoInvestigacion(personalTrabajoInvestigacionC item ){
	 	personalTrabajoInvestigacion=item;
     banderaTrabajoInvestigacion=true;
     
     
 }
 
 public void mostrarMonto(){
	 montoC item=new montoDAO().mostrarMonto(persona.getPersona());
	 if(item!=null){
		 personalContrato.setBasico(item.getTotalMonto());
	 }
 }
 
 public void cancelarTrabajoInvestigacion(){
	 banderaTrabajoInvestigacion=false;
     
     
     
 }
    
    public void nuevoExperienciaLAboral(){
        personalExperienciaLaboral=new personalExperienciaLaboralC(personal.getPersonal(), -1, null, null, null, null, 1);
        
        banderaExperienciaLaboral=true;
        bandera=true;
        estadoBotones(false, true, true, false, false,true,false);
        util.script("habilitarPestana(3);");
        util.script("$('#txtEmpresa').focus()");
                
    }
    
    public void insertarExperienciaLaboral(){     
        
                           
            new personalExperienciaLaboralDAO().insertar(personalExperienciaLaboral);           
            personalExperienciaLaboralL=new personalExperienciaLaboralDAO().mostrarPersonalExperienciaLaboral(personal.getPersonal());
            banderaExperienciaLaboral=false; 
            bandera=false;
            estadoBotones(true, false, false, false, false,true,false);
            util.script("habilitarPestana(-1);");
    }
    
    public void eliminarExperiencia(personalExperienciaLaboralC item ){
                     
        new personalExperienciaLaboralDAO().eliminar(item);   
        personalExperienciaLaboralL=new personalExperienciaLaboralDAO().mostrarPersonalExperienciaLaboral(personal.getPersonal());
        
        
    }
    public void cancelarExperienciaLaboral(){
    	banderaExperienciaLaboral=false;   
    	bandera=false;
    	estadoBotones(true, false, false, false, false,true,false);
    	 util.script("habilitarPestana(-1);");
    }
    
    public void agregarAmonestacion(){       
        personalAmonestacionL.add(new personalAmonestacionC(personal.getPersonal(), -1, personalAmonestacion.getTipo(), personalAmonestacion.getMotivo(),personalAmonestacion.getFecha(),1));
        personalAmonestacion=new personalAmonestacionC();        
    }
    
    public void nuevoTitulo(){
        banderaPersonaTitulo=true;
        personaTitulo=new personaTituloC();
        tituloTipoInstitucion=0;
    }
    
    public void agregarTitulo(){
        
       
         new personaTituloDAO().insertar(new personaTituloC(persona.getPersona(),-1,personaTitulo.getTitulo(),personaTitulo.getInstitucion(),personaTitulo.getFecha(),1));        
         personaTituloL=new personaTituloDAO().mostrarPersonalTitulo(persona.getPersona());
         banderaPersonaTitulo=false; 
    }
    public void editarTitulo(personaTituloC item){
    	personaTitulo=item;
    	banderaPersonaTitulo=true; 
      
    }
    
    public void cancelarTitulo(){
    	banderaPersonaTitulo=false; 
    }
    
 
    
    
    public List<personaFamilia> getPersonaFamiliaL() {
        return personaFamiliaL;
    }
    public void setPersonaFamiliaL(List<personaFamilia> personaFamiliaL) {
        this.personaFamiliaL = personaFamiliaL;
    }
    public List<personaC> getPersonaFiltroL() {
        return personaFiltroL;
    }
    public void setPersonaFiltroL(List<personaC> personaFiltroL) {
        this.personaFiltroL = personaFiltroL;
    }
    
    public class asignacionPersonalC{
    	private comisionC comision;
    	private List<asignacionC> asignacionL=new ArrayList<>();
    	public asignacionPersonalC() {
		
		}
    	public asignacionPersonalC(comisionC comision) {
			this.comision=comision;
		}
    	
		public comisionC getComision() {
			return comision;
		}
		public void setComision(comisionC comision) {
			this.comision = comision;
		}
		public List<asignacionC> getAsignacionL() {
			return asignacionL;
		}
		public void setAsignacionL(List<asignacionC> asignacionL) {
			this.asignacionL = asignacionL;
		}
    	
    	
    }

    public class personaFamilia{
        private personaC personaF=new personaC();
        private parentescoC parentesco=new parentescoC();
        private personaParentescoC personaParentesco;

        public personaFamilia() {
        }

        public personaFamilia(personaParentescoC personaParentesco) {
            this.personaParentesco = personaParentesco;
        }
        public personaC getPersonaF() {
            return personaF;
        }
        public void setPersonaF(personaC personaF) {
            this.personaF = personaF;
        }
        public personaParentescoC getPersonaParentesco() {
            return personaParentesco;
        }
        public parentescoC getParentesco() {
            return parentesco;
        }
        public void setParentesco(parentescoC parentesco) {
            this.parentesco = parentesco;
        }
    }
    public List<contratoCursoPersonalC> getContratoCursoPersonalL() {
        return contratoCursoPersonalL;
    }
    public void setContratoCursoPersonalL(List<contratoCursoPersonalC> contratoCursoPersonalL) {
        this.contratoCursoPersonalL = contratoCursoPersonalL;
    }
    public List<personaGradoAcademicoC> getPersonaGradoAcademicoL() {
        return personaGradoAcademicoL;
    }
    public void setPersonaGradoAcademicoL(List<personaGradoAcademicoC> personaGradoAcademicoL) {
        this.personaGradoAcademicoL = personaGradoAcademicoL;
    }
    public boolean isBanderaPersonaTitulo() {
        return banderaPersonaTitulo;
    }
    public void setBanderaPersonaTitulo(boolean banderaPersonaTitulo) {
        this.banderaPersonaTitulo = banderaPersonaTitulo;
    }
    public boolean isBanderaGradoAcademico() {
        return banderaGradoAcademico;
    }
    public void setBanderaGradoAcademico(boolean banderaGradoAcademico) {
        this.banderaGradoAcademico = banderaGradoAcademico;
    }
    public List<personaCapacitacionC> getPersonaCapacitacionL() {
        return personaCapacitacionL;
    }
    public void setPersonaCapacitacionL(List<personaCapacitacionC> personaCapacitacionL) {
        this.personaCapacitacionL = personaCapacitacionL;
    }
    public personaCapacitacionC getPersonaCapacitacion() {
        return personaCapacitacion;
    }
    public void setPersonaCapacitacion(personaCapacitacionC personaCapacitacion) {
        this.personaCapacitacion = personaCapacitacion;
    }
    public boolean isBanderaCapacitacion() {
        return banderaCapacitacion;
    }
    public void setBanderaCapacitacion(boolean banderaCapacitacion) {
        this.banderaCapacitacion = banderaCapacitacion;
    }
    public List<personaIdiomaC> getPersonaIdiomaL() {
        return personaIdiomaL;
    }
    public void setPersonaIdiomaL(List<personaIdiomaC> personaIdiomaL) {
        this.personaIdiomaL = personaIdiomaL;
    }
    public boolean isBanderaIdioma() {
        return banderaIdioma;
    }
    public void setBanderaIdioma(boolean banderaIdioma) {
        this.banderaIdioma = banderaIdioma;
    }
    public personaIdiomaC getPersonaIdioma() {
        return personaIdioma;
    }
    public void setPersonaIdioma(personaIdiomaC personaIdioma) {
        this.personaIdioma = personaIdioma;
    }

  
   
    public boolean isBanderaExperienciaLaboral() {
        return banderaExperienciaLaboral;
    }
    public void setBanderaExperienciaLaboral(boolean banderaExperienciaLaboral) {
        this.banderaExperienciaLaboral = banderaExperienciaLaboral;
    }
    public boolean isBanderaPestaña() {
        return banderaPestaña;
    }
    public void setBanderaPestaña(boolean banderaPestaña) {
        this.banderaPestaña = banderaPestaña;
    }
    public int getPestanaActiva() {
        return pestanaActiva;
    }
    public void setPestanaActiva(int pestanaActiva) {
        this.pestanaActiva = pestanaActiva;
    }
    public List<tipoPersonalC> getTipoPersonalL() {
        return tipoPersonalL;
    }
    public void setTipoPersonalL(List<tipoPersonalC> tipoPersonalL) {
        this.tipoPersonalL = tipoPersonalL;
    }
    public int getTotalHora() {
        return totalHora;
    }
    public void setTotalHora(int totalHora) {
        this.totalHora = totalHora;
    }
    public personalDocenteC getPersonalDocente() {
        return personalDocente;
    }
    public void setPersonalDocente(personalDocenteC personalDocente) {
        this.personalDocente = personalDocente;
    }
    public boolean isBanderaPersonalDocente() {
        return banderaPersonalDocente;
    }
    public void setBanderaPersonalDocente(boolean banderaPersonalDocente) {
        this.banderaPersonalDocente = banderaPersonalDocente;
    }
    public parentescoC getParentesco() {
        return parentesco;
    }
    public void setParentesco(parentescoC parentesco) {
        this.parentesco = parentesco;
    }
    public personaParentescoC getPersonaParentesco() {
        return personaParentesco;
    }
    public void setPersonaParentesco(personaParentescoC personaParentesco) {
        this.personaParentesco = personaParentesco;
    }
    public List<parentescoC> getParentescoL() {
        return parentescoL;
    }
    public void setParentescoL(List<parentescoC> parentescoL) {
        this.parentescoL = parentescoL;
    }
    public personaC getPersonaFamiliar() {
        return personaFamiliar;
    }
    public void setPersonaFamiliar(personaC personaFamiliar) {
        this.personaFamiliar = personaFamiliar;
    }

  

 
     
     
     
     public static class  contratoCursoPersonalC{
        private cursoSeccionDocenteC cursoSeccionDocente=new cursoSeccionDocenteC();
        private cursosC curso=new cursosC();
        private carrerasC carrera=new carrerasC();
        private seccionC seccion=new seccionC();
        
        public contratoCursoPersonalC() {
		
		}
        
        
		public cursoSeccionDocenteC getCursoSeccionDocente() {
			return cursoSeccionDocente;
		}
		public void setCursoSeccionDocente(cursoSeccionDocenteC cursoSeccionDocente) {
			this.cursoSeccionDocente = cursoSeccionDocente;
		}
		public cursosC getCurso() {
			return curso;
		}
		public void setCurso(cursosC curso) {
			this.curso = curso;
		}
		public carrerasC getCarrera() {
			return carrera;
		}
		public void setCarrera(carrerasC carrera) {
			this.carrera = carrera;
		}
		public seccionC getSeccion() {
			return seccion;
		}
		public void setSeccion(seccionC seccion) {
			this.seccion = seccion;
		}
        
        
        
        
         
     }
   
    public boolean isBandera() {
        return bandera;
    }
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }
  
    public personaTituloC getPersonaTitulo() {
        return personaTitulo;
    }
    public void setPersonaTitulo(personaTituloC personalTitulo) {
        this.personaTitulo = personalTitulo;
    }
    public String getDepartamentoNaci() {
        return departamentoNaci;
    }
    public void setDepartamentoNaci(String departamentoNaci) {
        this.departamentoNaci = departamentoNaci;
    }
    public String getProvinciaNaci() {
        return provinciaNaci;
    }
    public void setProvinciaNaci(String provinciaNaci) {
        this.provinciaNaci = provinciaNaci;
    }
    public String getDistritoNaci() {
        return distritoNaci;
    }
    public void setDistritoNaci(String distritoNaci) {
        this.distritoNaci = distritoNaci;
    }
    public boolean isBanderaContrato() {
        return banderaContrato;
    }
    public void setBanderaContrato(boolean banderaContrato) {
        this.banderaContrato = banderaContrato;
    }
    public Date getFechaImpresion() {
        return fechaImpresion;
    }
    public void setFechaImpresion(Date fechaImpresion) {
        this.fechaImpresion = fechaImpresion;
    }
    public tipoCentroEducativoC getTipoCentroEducativo() {
        return tipoCentroEducativo;
    }
    public void setTipoCentroEducativo(tipoCentroEducativoC tipoCentroEducativo) {
        this.tipoCentroEducativo = tipoCentroEducativo;
    }
    public List<personalAmonestacionC> getPersonalAmonestacionL() {
        return personalAmonestacionL;
    }
    public void setPersonalAmonestacionL(List<personalAmonestacionC> personalAmonestacionL) {
        this.personalAmonestacionL = personalAmonestacionL;
    }
    public personalAmonestacionC getPersonalAmonestacion() {
        return personalAmonestacion;
    }
    public void setPersonalAmonestacion(personalAmonestacionC personalAmonestacion) {
        this.personalAmonestacion = personalAmonestacion;
    }
    public personalExperienciaLaboralC getPersonalExperienciaLaboral() {
        return personalExperienciaLaboral;
    }
    public void setPersonalExperienciaLaboral(personalExperienciaLaboralC personalExperienciaLaboral) {
        this.personalExperienciaLaboral = personalExperienciaLaboral;
    }
    public List<personalExperienciaLaboralC> getPersonalExperienciaLaboralL() {
        return personalExperienciaLaboralL;
    }
    public void setPersonalExperienciaLaboralL(List<personalExperienciaLaboralC> personalExperienciaLaboralL) {
        this.personalExperienciaLaboralL = personalExperienciaLaboralL;
    }
    public personaGradoAcademicoC getPersonalGradoAcademico() {
        return personalGradoAcademico;
    }
    public void setPersonalGradoAcademico(personaGradoAcademicoC personalGradoAcademico) {
        this.personalGradoAcademico = personalGradoAcademico;
    }
    public List<personalTrabajoInvestigacionC> getPersonalTrabajoInvestigacionL() {
        return personalTrabajoInvestigacionL;
    }
    public void setPersonalTrabajoInvestigacionL(List<personalTrabajoInvestigacionC> personalTrabajoInvestigacionL) {
        this.personalTrabajoInvestigacionL = personalTrabajoInvestigacionL;
    }
    public personalTrabajoInvestigacionC getPersonalTrabajoInvestigacion() {
        return personalTrabajoInvestigacion;
    }
    public void setPersonalTrabajoInvestigacion(personalTrabajoInvestigacionC personalTrabajoInvestigacion) {
        this.personalTrabajoInvestigacion = personalTrabajoInvestigacion;
    }
    public List<personaTituloC> getPersonaTituloL() {
        return personaTituloL;
    }
    public void setPersonaTituloL(List<personaTituloC> personaTituloL) {
        this.personaTituloL = personaTituloL;
    }
    public int getTituloTipoInstitucion() {
        return tituloTipoInstitucion;
    }
    public void setTituloTipoInstitucion(int tituloTipoInstitucion) {
        this.tituloTipoInstitucion = tituloTipoInstitucion;
    }
    public List<cursoSeccionDocenteC> getCursoSeccionDocenteL() {
        return cursoSeccionDocenteL;
    }
    public void setCursoSeccionDocenteL(List<cursoSeccionDocenteC> cursoSeccionDocenteL) {
        this.cursoSeccionDocenteL = cursoSeccionDocenteL;
    }
    public String getInstitucion() {
        return institucion;
    }
    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }
    public String getPeriodo() {
        return periodo;
    }
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    
    
 
    public personaC getPersona() {
        return persona;
    }
    public void setPersona(personaC persona) {
        this.persona = persona;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    public String getProvincia() {
        return provincia;
    }
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    public String getDistrito() {
        return distrito;
    }
    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }
    public personalC getPersonal() {
        return personal;
    }
    public void setPersonal(personalC personal) {
        this.personal = personal;
    }

    
    
  
    
    public boolean isBanderaNuevo() {
		return banderaNuevo;
	}

	public void setBanderaNuevo(boolean banderaNuevo) {
		this.banderaNuevo = banderaNuevo;
	}

	public boolean isBanderaGuardar() {
		return banderaGuardar;
	}

	public void setBanderaGuardar(boolean banderaGuardar) {
		this.banderaGuardar = banderaGuardar;
	}

	public boolean isBanderaCancelar() {
		return banderaCancelar;
	}

	public void setBanderaCancelar(boolean banderaCancelar) {
		this.banderaCancelar = banderaCancelar;
	}

	public boolean isBanderaEditar() {
		return banderaEditar;
	}

	public void setBanderaEditar(boolean banderaEditar) {
		this.banderaEditar = banderaEditar;
	}

	public boolean isBanderaEliminar() {
		return banderaEliminar;
	}

	public void setBanderaEliminar(boolean banderaEliminar) {
		this.banderaEliminar = banderaEliminar;
	}
	 
    public personalContratoC getPersonalContrato() {
		return personalContrato;
	}

	public void setPersonalContrato(personalContratoC personalContrato) {
		this.personalContrato = personalContrato;
	}

	public List<personalContratoC> getPersonalContratoL() {
		return personalContratoL;
	}

	public void setPersonalContratoL(List<personalContratoC> personalContratoL) {
		this.personalContratoL = personalContratoL;
	}
	public List<institucionC> getInstitucionL() {
		return institucionL;
	}

	public void setInstitucionL(List<institucionC> institucionL) {
		this.institucionL = institucionL;
	}
	   
    public List<periocidadC> getPeriocidadL() {
	return periocidadL;
}

public void setPeriocidadL(List<periocidadC> periocidadL) {
	this.periocidadL = periocidadL;
}

	public List<periodoC> getPeriodoL() {
	return periodoL;
}

public void setPeriodoL(List<periodoC> periodoL) {
	this.periodoL = periodoL;
}

	public boolean isBanderaTrabajoInvestigacion() {
	return banderaTrabajoInvestigacion;
}

public void setBanderaTrabajoInvestigacion(boolean banderaTrabajoInvestigacion) {
	this.banderaTrabajoInvestigacion = banderaTrabajoInvestigacion;
}

	public List<idiomaC> getIdiomaL() {
	return idiomaL;
}

public void setIdiomaL(List<idiomaC> idiomaL) {
	this.idiomaL = idiomaL;
}

	public List<tipoCentroEducativoC> getTipoCentroEducativoL() {
	return tipoCentroEducativoL;
}

public void setTipoCentroEducativoL(List<tipoCentroEducativoC> tipoCentroEducativoL) {
	this.tipoCentroEducativoL = tipoCentroEducativoL;
}

	public List<centroEducativoC> getCentroEducativoL() {
	return centroEducativoL;
}

public void setCentroEducativoL(List<centroEducativoC> centroEducativoL) {
	this.centroEducativoL = centroEducativoL;
}

	public List<gradoInstruccionC> getGradoInstruccionL() {
	return gradoInstruccionL;
}

public void setGradoInstruccionL(List<gradoInstruccionC> gradoInstruccionL) {
	this.gradoInstruccionL = gradoInstruccionL;
}

	public List<localC> getLocalL() {
	return localL;
}

public void setLocalL(List<localC> localL) {
	this.localL = localL;
}

	public List<oficinaC> getOficinaL() {
		return oficinaL;
	}

	public void setOficinaL(List<oficinaC> oficinaL) {
		this.oficinaL = oficinaL;
	}

	public List<modalidadPensionC> getModalidadPensionL() {
		return modalidadPensionL;
	}

	public void setModalidadPensionL(List<modalidadPensionC> modalidadPensionL) {
		this.modalidadPensionL = modalidadPensionL;
	}

	public List<formaPagoC> getFormaPagoL() {
		return formaPagoL;
	}

	public void setFormaPagoL(List<formaPagoC> formaPagoL) {
		this.formaPagoL = formaPagoL;
	}

	public List<cargoC> getCargoL() {
		return cargoL;
	}

	public void setCargoL(List<cargoC> cargoL) {
		this.cargoL = cargoL;
	}

	public List<ocupacionC> getOcupacionL() {
		return ocupacionL;
	}

	public void setOcupacionL(List<ocupacionC> ocupacionL) {
		this.ocupacionL = ocupacionL;
	}

	public List<entidadPrestadoraSaludC> getEntidadPrestadoraSaludL() {
		return entidadPrestadoraSaludL;
	}

	public void setEntidadPrestadoraSaludL(List<entidadPrestadoraSaludC> entidadPrestadoraSaludL) {
		this.entidadPrestadoraSaludL = entidadPrestadoraSaludL;
	}

	public List<regimenPensionC> getRegimenPensionL() {
		return regimenPensionL;
	}

	public void setRegimenPensionL(List<regimenPensionC> regimenPensionL) {
		this.regimenPensionL = regimenPensionL;
	}

	public List<bancoC> getBancoL() {
		return bancoL;
	}

	public void setBancoL(List<bancoC> bancoL) {
		this.bancoL = bancoL;
	}

	public List<tipoMonedaC> getTipoMonedaL() {
		return tipoMonedaL;
	}

	public void setTipoMonedaL(List<tipoMonedaC> tipoMonedaL) {
		this.tipoMonedaL = tipoMonedaL;
	}

	public List<personalC> getPersonalL() {
		return personalL;
	}

	public void setPersonalL(List<personalC> personalL) {
		this.personalL = personalL;
	}

	public List<ubigeoDepartamentoC> getDepartamentoL() {
		return departamentoL;
	}

	public void setDepartamentoL(List<ubigeoDepartamentoC> departamentoL) {
		this.departamentoL = departamentoL;
	}

	public List<paisC> getPaisL() {
		return paisL;
	}

	public void setPaisL(List<paisC> paisL) {
		this.paisL = paisL;
	}

	public void filtroPersona(){
    	
           personaL = new personaDAO().filtroPersona(busPaterno.trim(), busMaterno.trim(), busNombres.trim());
    }
    
	public void filtroPersonaParentesco(){
		personaFiltroL=new personaDAO().filtroPersona(personaParantescoF);
	}
    
    
    public List<personaC> getPersonaL() {
		return personaL;
	}

	public void setPersonaL(List<personaC> personaL) {
		this.personaL = personaL;
	}

	public String getBusPaterno() {
		return busPaterno;
	}

	public void setBusPaterno(String busPaterno) {
		this.busPaterno = busPaterno;
	}

	public String getBusMaterno() {
		return busMaterno;
	}

	public void setBusMaterno(String busMaterno) {
		this.busMaterno = busMaterno;
	}

	public String getBusNombres() {
		return busNombres;
	}

	public void setBusNombres(String busNombres) {
		this.busNombres = busNombres;
	}
	
	public List<tipoContratoC> getTipoContratoL() {
	return tipoContratoL;
}

public void setTipoContratoL(List<tipoContratoC> tipoContratoL) {
	this.tipoContratoL = tipoContratoL;
}
}
