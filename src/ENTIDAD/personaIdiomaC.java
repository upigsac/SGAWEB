
package ENTIDAD;

public class personaIdiomaC {
    private String persona;
    private int item;
    private int idioma;
    private String nivel;
    private int estadoRegistro;

    public personaIdiomaC() {
    }

    public personaIdiomaC(String persona, int item, int idioma, String nivel, int estadoRegistro) {
        this.persona = persona;
        this.item = item;
        this.idioma = idioma;
        this.nivel = nivel;
        this.estadoRegistro = estadoRegistro;
    }
    public int getItem() {
        return item;
    }
    public void setItem(int item) {
        this.item = item;
    }
    public int getIdioma() {
        return idioma;
    }
    public void setIdioma(int idioma) {
        this.idioma = idioma;
    }

    public String getNivel() {
        return nivel;
    }
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    public String getPersona() {
        return persona;
    }
    public void setPersona(String persona) {
        this.persona = persona;
    }
}
