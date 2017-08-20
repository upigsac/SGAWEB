

package ENTIDAD;

import java.io.Serializable;
import java.util.Date;

public class personalDocenteC implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cpersonal;
	private String personal;
	private String periodo;
	private String modalidad;
    private Date ingreso;
    private int institucion;
    private boolean vigencia;
    private int categoria;
    private String regimen;
    private boolean dina;
    private boolean investiga;
    private int estadoRegistro;

    public personalDocenteC() {
    }

    public personalDocenteC(String cpersonal,String personal,String periodo, Date ingreso, boolean vigencia, int categoria, String regimen, boolean dina, boolean investiga, int estadoRegistro) {
    	this.cpersonal = cpersonal;
        this.personal = personal;
        this.periodo=periodo;
        this.ingreso = ingreso;
        this.vigencia = vigencia;
        this.categoria = categoria;
        this.regimen = regimen;
        this.dina = dina;
        this.investiga = investiga;
        this.estadoRegistro = estadoRegistro;
    }
    
    
    
    
    
    
 

	public int getInstitucion() {
		return institucion;
	}

	public void setInstitucion(int institucion) {
		this.institucion = institucion;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getCpersonal() {
		return cpersonal;
	}

	public void setCpersonal(String cpersonal) {
		this.cpersonal = cpersonal;
	}

	public String getPersonal() {
        return personal;
    }
    public void setPersonal(String personal) {
        this.personal = personal;
    }
    public Date getIngreso() {
        return ingreso;
    }
    public void setIngreso(Date ingreso) {
        this.ingreso = ingreso;
    }
    public boolean isVigencia() {
        return vigencia;
    }
    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }
    public int getCategoria() {
        return categoria;
    }
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
    public String getRegimen() {
        return regimen;
    }
    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }
    public boolean isDina() {
        return dina;
    }
    public void setDina(boolean dina) {
        this.dina = dina;
    }
    public boolean isInvestiga() {
        return investiga;
    }
    public void setInvestiga(boolean investiga) {
        this.investiga = investiga;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
}
