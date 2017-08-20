
package  ENTIDAD;


public class carpetaArchivoC {
    private int carpeta;
    private String archivo;
    private String extension;
    private int pesoMaximo;
    private int estadoRegistro;

    /**
     * @return the carpeta
     */
    public int getCarpeta() {
        return carpeta;
    }

    /**
     * @param carpeta the carpeta to set
     */
    public void setCarpeta(int carpeta) {
        this.carpeta = carpeta;
    }

    /**
     * @return the archivo
     */
    public String getArchivo() {
        return archivo;
    }

    /**
     * @param archivo the archivo to set
     */
    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

  
    /**
     * @return the estadoRegistro
     */
    public int getEstadoRegistro() {
        return estadoRegistro;
    }

    /**
     * @param estadoRegistro the estadoRegistro to set
     */
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    /**
     * @return the extension
     */
    public String getExtension() {
        return extension;
    }

    /**
     * @param extension the extension to set
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * @return the pesoMaximo
     */
    public int getPesoMaximo() {
        return pesoMaximo;
    }

    /**
     * @param pesoMaximo the pesoMaximo to set
     */
    public void setPesoMaximo(int pesoMaximo) {
        this.pesoMaximo = pesoMaximo;
    }
}
