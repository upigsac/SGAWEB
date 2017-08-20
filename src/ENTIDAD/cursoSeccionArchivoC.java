/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ENTIDAD;

/**
 *
 * @author Administrador
 */
public class cursoSeccionArchivoC {
    private int institucion;
    private String periodo;
    private String carrera;
    private String malla;
    private String curso;
    private String seccion;
    private String archivo;
    private String ruta;
    private String formato;
    private int peso;
    private String archivoPadre;
    private int estadoRegistro;

    public cursoSeccionArchivoC() {
    }

    public cursoSeccionArchivoC(int institucion, String periodo, String carrera, String malla, String curso, String seccion, String archivo, String ruta, String formato, int peso, String archivoPadre, int estadoRegistro) {
        this.institucion = institucion;
        this.periodo = periodo;
        this.carrera = carrera;
        this.malla = malla;
        this.curso = curso;
        this.seccion = seccion;
        this.archivo = archivo;
        this.ruta = ruta;
        this.formato = formato;
        this.peso = peso;
        this.archivoPadre = archivoPadre;
        this.estadoRegistro = estadoRegistro;
    }
    
    
    

    /**
     * @return the institucion
     */
    public int getInstitucion() {
        return institucion;
    }

    /**
     * @param institucion the institucion to set
     */
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }

    /**
     * @return the periodo
     */
    public String getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
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
     * @return the malla
     */
    public String getMalla() {
        return malla;
    }

    /**
     * @param malla the malla to set
     */
    public void setMalla(String malla) {
        this.malla = malla;
    }

    /**
     * @return the curso
     */
    public String getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(String curso) {
        this.curso = curso;
    }

    /**
     * @return the seccion
     */
    public String getSeccion() {
        return seccion;
    }

    /**
     * @param seccion the seccion to set
     */
    public void setSeccion(String seccion) {
        this.seccion = seccion;
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
     * @return the ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    /**
     * @return the formato
     */
    public String getFormato() {
        return formato;
    }

    /**
     * @param formato the formato to set
     */
    public void setFormato(String formato) {
        this.formato = formato;
    }

    /**
     * @return the peso
     */
    public int getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }

    /**
     * @return the archivoPadre
     */
    public String getArchivoPadre() {
        return archivoPadre;
    }

    /**
     * @param archivoPadre the archivoPadre to set
     */
    public void setArchivoPadre(String archivoPadre) {
        this.archivoPadre = archivoPadre;
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
    
}
