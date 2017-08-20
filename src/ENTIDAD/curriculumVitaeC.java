package ENTIDAD;

import java.io.Serializable;

public class curriculumVitaeC implements Serializable {

	private static final long serialVersionUID = 1L;
	private String personal;
	private String ubicacion;
	private String ruta;
	private int peso;
	private String formato; 
	private int folio;
	private int folioDesde;
	private int folioHasta;
	private int estadoRegistro;
	
	
	public curriculumVitaeC( String personal,String ubicacion,String ruta,int peso,String formato,int folio,int estadoRegistro) 
	{
		this.personal=personal;
		this.ubicacion=ubicacion;
		this.ruta=ruta;
		this.peso=peso;
		this.formato=formato;
		this.folio=folio;
		this.estadoRegistro=estadoRegistro;
	}
	
	public curriculumVitaeC() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	public int getFolioDesde() {
		return folioDesde;
	}

	public void setFolioDesde(int folioDesde) {
		this.folioDesde = folioDesde;
	}

	public int getFolioHasta() {
		return folioHasta;
	}

	public void setFolioHasta(int folioHasta) {
		this.folioHasta = folioHasta;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getPersonal() {
		return personal;
	}
	public void setPersonal(String personal) {
		this.personal = personal;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public int getFolio() {
		return folio;
	}
	public void setFolio(int folio) {
		this.folio = folio;
	}
	public int getEstadoRegistro() {
		return estadoRegistro;
	}
	public void setEstadoRegistro(int estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	
}
