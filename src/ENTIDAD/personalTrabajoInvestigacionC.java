
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
    private String a�o;
    private int estadoRegistro;

    public personalTrabajoInvestigacionC() {
    }

    public personalTrabajoInvestigacionC(String persona, int item, String tema, String a�o, int estadoRegistro) {
        this.persona = persona;
        this.item = item;
        this.tema = tema;
        this.a�o = a�o;
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
    public String getA�o() {
        return a�o;
    }
    public void setA�o(String a�o) {
        this.a�o = a�o;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
}
