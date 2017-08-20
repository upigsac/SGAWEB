
package ENTIDAD;

import java.io.Serializable;

public class personalTrabajoInvestigacionC implements Serializable {
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String persona;
    private int item;
    private String tema;
    private String año;
    private int estadoRegistro;

    public personalTrabajoInvestigacionC() {
    }

    public personalTrabajoInvestigacionC(String persona, int item, String tema, String año, int estadoRegistro) {
        this.persona = persona;
        this.item = item;
        this.tema = tema;
        this.año = año;
        this.estadoRegistro = estadoRegistro;
    }
    
    
    public String getPersona() {
        return persona;
    }
    public void setPersona(String persona) {
        this.persona = persona;
    }
    public int getItem() {
        return item;
    }
    public void setItem(int item) {
        this.item = item;
    }
    public String getTema() {
        return tema;
    }
    public void setTema(String tema) {
        this.tema = tema;
    }
    public String getAño() {
        return año;
    }
    public void setAño(String año) {
        this.año = año;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
}
