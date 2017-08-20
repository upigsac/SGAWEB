package Beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.conceptoDAO;
import DAO.formatoUnicoTramiteDAO;
import DAO.pagoPersonaDAO;
import DAO.personaDAO;
import ENTIDAD.conceptoC;

import ENTIDAD.formatoUnicoTramiteC;
import ENTIDAD.pagoPersonaC;
import ENTIDAD.personaC;

@ManagedBean(name="administrarFUTB")
@ViewScoped
public class administrarFUT {
	private List<conceptoC> conceptoL=new ArrayList<>();
	private formatoUnicoTramiteC formatoUnicoTramite=new formatoUnicoTramiteC();
	private List<formatoUnicoTramiteC> formatoUnicoTramiteL=new ArrayList<>();
	private pagoPersonaC pagoPersona=new pagoPersonaC();
	private personaC persona;
	
	
	
	
	
	
	



	

	public administrarFUT() {
		// TODO Auto-generated constructor stub
		formatoUnicoTramiteL=new formatoUnicoTramiteDAO().mostrarFormatoUnicoTramite();
	}
	
	public void busquedaComprobante(){
		conceptoL=new conceptoDAO().mostrarConcepto(formatoUnicoTramite.getNumeroComprobante());
		pagoPersona=new pagoPersonaDAO().mostrarPagoPersona(formatoUnicoTramite.getNumeroComprobante());
		formatoUnicoTramite.setPersona(pagoPersona.getPersonaEmpresa());
		persona=new personaDAO().getBuscaPersonaCodigo(pagoPersona.getPersonaEmpresa());
	}
	
	public void nuevo(){
		formatoUnicoTramite=new formatoUnicoTramiteC(null, null, null, null, null, 1);
		conceptoL=new ArrayList<>();
		util.script("dlgFUT.show()");
	}
	public void editar(formatoUnicoTramiteC itemFormatoUnicoTramite){
		formatoUnicoTramite=itemFormatoUnicoTramite;
		busquedaComprobante();
		util.script("dlgFUT.show()");
	}
	
	
	public void insertar(){

		new formatoUnicoTramiteDAO().insertar(formatoUnicoTramite);
		formatoUnicoTramiteL=new formatoUnicoTramiteDAO().mostrarFormatoUnicoTramite();
		util.script("dlgFUT.hide()");
	}
	public void eliminar(formatoUnicoTramiteC itemFormatoUnicoTramite){
		new formatoUnicoTramiteDAO().eliminar(itemFormatoUnicoTramite);
		formatoUnicoTramiteL=new formatoUnicoTramiteDAO().mostrarFormatoUnicoTramite();
	
	}

	public List<conceptoC> getConceptoL() {
		return conceptoL;
	}

	public void setConceptoL(List<conceptoC> conceptoL) {
		this.conceptoL = conceptoL;
	}

	public formatoUnicoTramiteC getFormatoUnicoTramite() {
		return formatoUnicoTramite;
	}

	public void setFormatoUnicoTramite(formatoUnicoTramiteC formatoUnicoTramite) {
		this.formatoUnicoTramite = formatoUnicoTramite;
	}
	
	
	
	public List<formatoUnicoTramiteC> getFormatoUnicoTramiteL() {
		return formatoUnicoTramiteL;
	}

	public void setFormatoUnicoTramiteL(List<formatoUnicoTramiteC> formatoUnicoTramiteL) {
		this.formatoUnicoTramiteL = formatoUnicoTramiteL;
	}
	
	public pagoPersonaC getPagoPersona() {
		return pagoPersona;
	}

	public void setPagoPersona(pagoPersonaC pagoPersona) {
		this.pagoPersona = pagoPersona;
	}
	
	public personaC getPersona() {
		return persona;
	}

	public void setPersona(personaC persona) {
		this.persona = persona;
	}
}
