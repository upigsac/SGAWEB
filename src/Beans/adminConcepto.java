package Beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.centroCostoDAO;
import DAO.conceptoDAO;
import DAO.conceptoInstitucionDAO;
import DAO.conceptoInstitucionDescuentoDAO;
import DAO.conceptoInstitucionPeriodoDAO;
import DAO.descuentoDAO;
import DAO.institucionDAO;
import DAO.periodoDAO;
import DAO.tipoConceptoDAO;
import DAO.tipoMonedaDAO;
import ENTIDAD.centroCostoC;
import ENTIDAD.conceptoC;
import ENTIDAD.conceptoInstitucionC;
import ENTIDAD.conceptoInstitucionDescuentoC;
import ENTIDAD.conceptoInstitucionPeriodoC;
import ENTIDAD.descuentoC;
import ENTIDAD.institucionC;
import ENTIDAD.periodoC;
import ENTIDAD.tipoConceptoC;
import ENTIDAD.tipoMonedaC;

@ManagedBean(name="adminConceptoB")
@ViewScoped

public class adminConcepto {
	private List<conceptoC> conceptoL=new ArrayList<>();
	private conceptoC concepto=new conceptoC();
	private List<tipoConceptoC> tipoConceptoL=new ArrayList<>();
	private List<conceptoInstitucionDescuentoC> conceptoInstitucionDescuentoL;
	private List<conceptoInstitucionC> conceptoInstitucionL=new ArrayList<>();
	
	
	
	
	
	public List<conceptoInstitucionC> getConceptoInstitucionL() {
		return conceptoInstitucionL;
	}
	public void setConceptoInstitucionL(List<conceptoInstitucionC> conceptoInstitucionL) {
		this.conceptoInstitucionL = conceptoInstitucionL;
	}

	private conceptoInstitucionC conceptoInstitucion=new conceptoInstitucionC();
	
	private conceptoInstitucionDescuentoC conceptoInstitucionDescuento=new conceptoInstitucionDescuentoC();
	private List<centroCostoC> centroCostoL=new ArrayList<>();
	private List<centroCostoC> centroCostoPadreL=new ArrayList<>();
	private centroCostoC centroCosto=new centroCostoC();
	private List<descuentoC> descuentoL=new ArrayList<>();
	private List<institucionC> institucionL=new ArrayList<>();
	private List<periodoC> periodoL=new ArrayList<>();
	private List<conceptoInstitucionPeriodoC> conceptoInstitucionPeriodoL=new ArrayList<>();
	private conceptoInstitucionPeriodoC conceptoInstitucionPeriodo=new conceptoInstitucionPeriodoC();
	private List<tipoMonedaC> tipoMonedaL; 
	private boolean banderaConceptoInstitucionDescuento;
	private boolean banderaCentroCosto;
	private boolean banderaConceptoInstitucionPeriodo;
	
	
	
	
	// ----------filtros
	
	



	private String tipoConceptoF="%";


	private String conceptoF="";
	private String nivelF="%";
	private String desCentroCostoF="";
	private String costoPadreF="";
	
	
	

	

	
	
	public adminConcepto() {
		
		conceptoL=new conceptoDAO().filtroConcepto(tipoConceptoF, conceptoF);
		tipoConceptoL=new tipoConceptoDAO().mostrarTipoConcepto();
		centroCostoL=new centroCostoDAO().mostrarCentroCosto();
		descuentoL=new descuentoDAO().mostrarDescuento();
		tipoMonedaL=new tipoMonedaDAO().mostrarTipoMoneda();
		centroCostoPadreL=new centroCostoDAO().mostrarCentroCosto();
		
	}
	public void mostrarConceptoInstitucion(){
		conceptoInstitucionL=new conceptoInstitucionDAO().mostrarConceptoInstitucion(concepto.getTipoConcepto(), concepto.getConcepto());
	}
	public void nuevoConceptoInstitucion(){
		conceptoInstitucion=new conceptoInstitucionC(0, concepto.getConcepto(), concepto.getTipoConcepto(), false, 1);
		institucionL=new institucionDAO().mostrarInstitucion();
		util.script("dlgConceptoInstitucion.show()");
	}
	public void insertarConceptoInstitucion(){
			new conceptoInstitucionDAO().insertar(conceptoInstitucion);
			mostrarConceptoInstitucion();
			util.script("dlgConceptoInstitucion.hide()");
	}
	
	public void editarConceptoInstitucion(conceptoInstitucionC itemConceptoInstitucion){
		conceptoInstitucion=itemConceptoInstitucion;
		util.script("dlgConceptoInstitucion.show()");
	}
	
	public void eliminarConceptoInstitucion(conceptoInstitucionC itemConceptoInstitucion){
		conceptoInstitucion=itemConceptoInstitucion;		
		new conceptoInstitucionDAO().eliminar(itemConceptoInstitucion);
		mostrarConceptoInstitucion();
	}
	
	
	public void filtroCentroCosto(){
		centroCostoL=new centroCostoDAO().filtroCentroCosto(costoPadreF,nivelF, desCentroCostoF);
	}
	public void nuevoCentroCosto(){
		centroCosto=new centroCostoC(null, null, 1, null, null, 1);
		banderaCentroCosto=true;
		util.script("dlgCentroCosto.show()");
	}
	
	public void editarCentroCosto(centroCostoC itemCentroCosto){
		centroCosto=itemCentroCosto;
		banderaCentroCosto=false;
		util.script("dlgCentroCosto.show()");
	}
	
	public void insertarCentroCosto(){
		new centroCostoDAO().insertar(centroCosto);
		filtroCentroCosto();
		util.script("dlgCentroCosto.hide()");
	}
	
	public void elimnarCentroCosto(){
		new centroCostoDAO().eliminar(centroCosto);
		filtroCentroCosto();
	}
	
	
	public void mostrarInstitucion(){
		institucionL=new institucionDAO().mostrarConceptoInstitucion(concepto.getConcepto(),concepto.getTipoConcepto());
	}
	public void mostrarPeriodo(){
		periodoL=new periodoDAO().mostrarPeriodoConcepto(conceptoInstitucionDescuento.getInstitucion(),concepto.getConcepto());
	}
	public void seleccionInstitucion(){
		periodoL=new periodoDAO().mostrarPeriodoInstitucion(conceptoInstitucionPeriodo.getInstitucion());
	}
	
	public void mostrarConceptoInstitucionDescuento(){
		
		conceptoInstitucionDescuentoL=new conceptoInstitucionDescuentoDAO().mostrarConceptoInstitucionDescuento(concepto.getConcepto());
		util.script("niveles('#pnConceptoDescuento')");
		
	}
	
	
	public void filtroConcepto(){
		conceptoL=new conceptoDAO().filtroConcepto(tipoConceptoF, conceptoF);
	}
	
	public void nuevoConceptoInstitucionDescuento(){
		
		conceptoInstitucionDescuento=new conceptoInstitucionDescuentoC(0, null, concepto.getConcepto(), concepto.getTipoConcepto(), null, null, 1);
		mostrarInstitucion();
		if(institucionL.size()>0){
			conceptoInstitucionDescuento.setInstitucion(institucionL.get(0).getInstitucion());
			mostrarPeriodo();
		}
		
		banderaConceptoInstitucionDescuento=true;
		util.script("dlgConceptoDescuento.show()");
	}
	
	public void insertarConceptoInstitucionPeriodo(){
		new conceptoInstitucionPeriodoDAO().insertar(conceptoInstitucionPeriodo);
		util.script("dlgConeptoInstitucionPeriodo.hide()");
	}
	
	public void editarConceptoInstitucionPeriodo(conceptoInstitucionPeriodoC itemConceptoInstitucionPeriodo){
		
		conceptoInstitucionPeriodo=itemConceptoInstitucionPeriodo;
		institucionL=new institucionDAO().mostrarInstitucion();
		seleccionInstitucion();
		banderaConceptoInstitucionPeriodo=false;
		util.script("dlgConeptoInstitucionPeriodo.show()");
	}
	
	
	public void mostrarConceptoInstitucionPeriodo(){
		
		conceptoInstitucionPeriodoL=new conceptoInstitucionPeriodoDAO().mostrarConceptoInstitucionPeriodo(concepto.getTipoConcepto(),concepto.getConcepto());
		util.script("niveles('#pnConceptoPeriodo')");
	}

	
	public void editarConceptoInstitucionDescuento(conceptoInstitucionDescuentoC itemConceptoInstitucionDescuento){
		conceptoInstitucionDescuento=itemConceptoInstitucionDescuento;
		mostrarInstitucion();
		mostrarPeriodo();
		banderaConceptoInstitucionDescuento=false;
		util.script("dlgConceptoDescuento.show()");
	}
	public void insertarConceptoInstitucionDescuento(){
		
		new conceptoInstitucionDescuentoDAO().insertar(conceptoInstitucionDescuento);
		conceptoInstitucionDescuentoL=new conceptoInstitucionDescuentoDAO().mostrarConceptoInstitucionDescuento(concepto.getConcepto());
		util.script("dlgConceptoDescuento.hide()");
	}
	
	public void eliminarConceptoInstitucionDescuento(conceptoInstitucionDescuentoC itemConceptoInstitucionDescuento){
		System.out.print("---- eliminar ---");
		conceptoInstitucionDescuento=itemConceptoInstitucionDescuento;
		new conceptoInstitucionDescuentoDAO().eliminar(conceptoInstitucionDescuento);
		conceptoInstitucionDescuentoL=new conceptoInstitucionDescuentoDAO().mostrarConceptoInstitucionDescuento(concepto.getConcepto());
		
	}
	
	public void nuevo(){
		concepto=new conceptoC(null, 1, null, null, false, null, false, null, null, 1);
		util.script("dlgConcepto.show()");
	}
	public void insertar(){
		new conceptoDAO().insertar(concepto);
		conceptoL=new conceptoDAO().filtroConcepto(tipoConceptoF, conceptoF);
		util.script("dlgConcepto.hide()");
	}
	public void eliminar(conceptoC itemConcepto){
		new conceptoDAO().eliminar(itemConcepto);
		conceptoL=new conceptoDAO().filtroConcepto(tipoConceptoF, conceptoF);
	}
	
	public void editar(conceptoC itemConcepto){
		concepto=itemConcepto;
		util.script("dlgConcepto.show()");
	}
	public void nuevoConceptoInstitucionPeriodo(){
		conceptoInstitucionPeriodo=new conceptoInstitucionPeriodoC(0, null, concepto.getConcepto(), concepto.getTipoConcepto(), 0.0, 1, 1, 0.0, 1);
		institucionL=new institucionDAO().mostrarInstitucion();
		periodoL=new ArrayList<>();
		banderaConceptoInstitucionPeriodo=true;
		util.script("dlgConeptoInstitucionPeriodo.show()");
	}
	

	public List<conceptoC> getConceptoL() {
		return conceptoL;
	}

	public void setConceptoL(List<conceptoC> conceptoL) {
		this.conceptoL = conceptoL;
	}
	
	public List<tipoConceptoC> getTipoConceptoL() {
		return tipoConceptoL;
	}

	public void setTipoConceptoL(List<tipoConceptoC> tipoConceptoL) {
		this.tipoConceptoL = tipoConceptoL;
	}

	public conceptoC getConcepto() {
		return concepto;
	}

	public void setConcepto(conceptoC concepto) {
		this.concepto = concepto;
	}
	public String getConceptoF() {
		return conceptoF;
	}

	public void setConceptoF(String conceptoF) {
		this.conceptoF = conceptoF;
	}

	public String getTipoConceptoF() {
		return tipoConceptoF;
	}

	public void setTipoConceptoF(String tipoConceptoF) {
		this.tipoConceptoF = tipoConceptoF;
	}
	public List<conceptoInstitucionDescuentoC> getConceptoInstitucionDescuentoL() {
		return conceptoInstitucionDescuentoL;
	}

	public void setConceptoInstitucionDescuentoL(List<conceptoInstitucionDescuentoC> conceptoInstitucionDescuentoL) {
		this.conceptoInstitucionDescuentoL = conceptoInstitucionDescuentoL;
	}
	public List<centroCostoC> getCentroCostoL() {
		return centroCostoL;
	}

	public void setCentroCostoL(List<centroCostoC> centroCostoL) {
		this.centroCostoL = centroCostoL;
	}
	public List<descuentoC> getDescuentoL() {
		return descuentoL;
	}

	public void setDescuentoL(List<descuentoC> descuentoL) {
		this.descuentoL = descuentoL;
	}
	public conceptoInstitucionDescuentoC getConceptoInstitucionDescuento() {
		return conceptoInstitucionDescuento;
	}

	public void setConceptoInstitucionDescuento(conceptoInstitucionDescuentoC conceptoInstitucionDescuento) {
		this.conceptoInstitucionDescuento = conceptoInstitucionDescuento;
	}
	
	public List<institucionC> getInstitucionL() {
		return institucionL;
	}

	public void setInstitucionL(List<institucionC> institucionL) {
		this.institucionL = institucionL;
	}
	
	public List<periodoC> getPeriodoL() {
		return periodoL;
	}

	public void setPeriodoL(List<periodoC> periodoL) {
		this.periodoL = periodoL;
	}
	public conceptoInstitucionPeriodoC getConceptoInstitucionPeriodo() {
		return conceptoInstitucionPeriodo;
	}
	public void setConceptoInstitucionPeriodo(conceptoInstitucionPeriodoC conceptoInstitucionPeriodo) {
		this.conceptoInstitucionPeriodo = conceptoInstitucionPeriodo;
	}
	
	public List<conceptoInstitucionPeriodoC> getConceptoInstitucionPeriodoL() {
		return conceptoInstitucionPeriodoL;
	}
	public void setConceptoInstitucionPeriodoL(List<conceptoInstitucionPeriodoC> conceptoInstitucionPeriodoL) {
		this.conceptoInstitucionPeriodoL = conceptoInstitucionPeriodoL;
	}
	public centroCostoC getCentroCosto() {
		return centroCosto;
	}
	public void setCentroCosto(centroCostoC centroCosto) {
		this.centroCosto = centroCosto;
	}
	
	public String getNivelF() {
		return nivelF;
	}
	public void setNivelF(String nivelF) {
		this.nivelF = nivelF;
	}
	public String getDesCentroCostoF() {
		return desCentroCostoF;
	}
	public void setDesCentroCostoF(String desCentroCostoF) {
		this.desCentroCostoF = desCentroCostoF;
	}
	public boolean isBanderaConceptoInstitucionDescuento() {
		return banderaConceptoInstitucionDescuento;
	}

	public void setBanderaConceptoInstitucionDescuento(boolean banderaConceptoInstitucionDescuento) {
		this.banderaConceptoInstitucionDescuento = banderaConceptoInstitucionDescuento;
	}

	public boolean isBanderaCentroCosto() {
		return banderaCentroCosto;
	}

	public void setBanderaCentroCosto(boolean banderaCentroCosto) {
		this.banderaCentroCosto = banderaCentroCosto;
	}
	public boolean isBanderaConceptoInstitucionPeriodo() {
		return banderaConceptoInstitucionPeriodo;
	}

	public void setBanderaConceptoInstitucionPeriodo(boolean banderaConceptoInstitucionPeriodo) {
		this.banderaConceptoInstitucionPeriodo = banderaConceptoInstitucionPeriodo;
	}
	public List<tipoMonedaC> getTipoMonedaL() {
		return tipoMonedaL;
	}

	public void setTipoMonedaL(List<tipoMonedaC> tipoMonedaL) {
		this.tipoMonedaL = tipoMonedaL;
	}

	
	
	public conceptoInstitucionC getConceptoInstitucion() {
		return conceptoInstitucion;
	}

	public void setConceptoInstitucion(conceptoInstitucionC conceptoInstitucion) {
		this.conceptoInstitucion = conceptoInstitucion;
	}
	public String getCostoPadreF() {
		return costoPadreF;
	}
	public void setCostoPadreF(String costoPadreF) {
		this.costoPadreF = costoPadreF;
	}
	public List<centroCostoC> getCentroCostoPadreL() {
		return centroCostoPadreL;
	}
	public void setCentroCostoPadreL(List<centroCostoC> centroCostoPadreL) {
		this.centroCostoPadreL = centroCostoPadreL;
	}
	
}
