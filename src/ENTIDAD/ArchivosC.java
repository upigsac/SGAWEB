
package  ENTIDAD;

import java.util.Date;


public class ArchivosC {
    
    private String titulo;
    private String portada;
    private String url;
    private String url_externo;
    private int ancho;
    private int alto;
    private int orden;
    private String carrera;
    
    private Date fecha_caducidad;
    private Date fecha_importacia;
    private int estado;

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the portada
     */
    public String getPortada() {
        return portada;
    }

    /**
     * @param portada the portada to set
     */
    public void setPortada(String portada) {
        this.portada = portada;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the url_externo
     */
    public String getUrl_externo() {
        return url_externo;
    }

    /**
     * @param url_externo the url_externo to set
     */
    public void setUrl_externo(String url_externo) {
        this.url_externo = url_externo;
    }

    /**
     * @return the ancho
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * @param ancho the ancho to set
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    /**
     * @return the alto
     */
    public int getAlto() {
        return alto;
    }

    /**
     * @param alto the alto to set
     */
    public void setAlto(int alto) {
        this.alto = alto;
    }

    /**
     * @return the carrera
     */
    public String getCarrera() {
        return carrera;
    }

    /**
     * @param carrera the carrera to set
     */
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    /**
     * @return the fecha_caducidad
     */
    public Date getFecha_caducidad() {
        return fecha_caducidad;
    }

    /**
     * @param fecha_caducidad the fecha_caducidad to set
     */
    public void setFecha_caducidad(Date fecha_caducidad) {
        this.fecha_caducidad = fecha_caducidad;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * @return the orden
     */
    public int getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }

 
    public Date getFecha_importacia() {
        return fecha_importacia;
    }

    /**
     * @param fecha_importacia the fecha_importacia to set
     */
    public void setFecha_importacia(Date fecha_importacia) {
        this.fecha_importacia = fecha_importacia;
    }

    
    
}
