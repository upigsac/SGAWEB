
package ENTIDAD;

import java.io.Serializable;
import java.util.Date;

public class usuarioHistorialC implements Serializable {

	private static final long serialVersionUID = 1L;
	private int item;
    private String usuario;
    private String persona;
    private String plataforma;
    private String navegador;
    private String ip;
    private String pais;
    private int perfil;
    private Date fecha;
    private int estadoRegistro;

    public usuarioHistorialC() {
    }

    public usuarioHistorialC(int item, String usuario, String persona, String plataforma, String navegador, String ip, String pais,int perfil, Date fecha, int estadoRegistro) {
        this.item = item;
        this.usuario = usuario;
        this.persona = persona;
        this.plataforma = plataforma;
        this.navegador = navegador;
        this.ip = ip;
        this.pais = pais;
        this.perfil=perfil;
        this.fecha = fecha;
        this.estadoRegistro = estadoRegistro;
    }


    
    
    public int getPerfil() {
		return perfil;
	}

	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}

	public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getPersona() {
        return persona;
    }
    public void setPersona(String persona) {
        this.persona = persona;
    }
    public String getNavegador() {
        return navegador;
    }
    public void setNavegador(String navegador) {
        this.navegador = navegador;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    public int getItem() {
        return item;
    }
    public void setItem(int item) {
        this.item = item;
    }
    public String getPlataforma() {
        return plataforma;
    }
    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }
}
