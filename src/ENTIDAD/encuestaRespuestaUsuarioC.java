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
public class encuestaRespuestaUsuarioC {
    private int encuesta;
    private int pregunta;
    private int repuesta;
    private String descripcion;
    private String usuario;

    public encuestaRespuestaUsuarioC(int encuesta, int pregunta, int repuesta, String descripcion, String usuario) {
        this.encuesta = encuesta;
        this.pregunta = pregunta;
        this.repuesta = repuesta;
        this.descripcion = descripcion;
        this.usuario = usuario;
    }

    
    
    /**
     * @return the encuesta
     */
    public int getEncuesta() {
        return encuesta;
    }

    /**
     * @param encuesta the encuesta to set
     */
    public void setEncuesta(int encuesta) {
        this.encuesta = encuesta;
    }

    /**
     * @return the pregunta
     */
    public int getPregunta() {
        return pregunta;
    }

    /**
     * @param pregunta the pregunta to set
     */
    public void setPregunta(int pregunta) {
        this.pregunta = pregunta;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the repuesta
     */
    public int getRepuesta() {
        return repuesta;
    }

    /**
     * @param repuesta the repuesta to set
     */
    public void setRepuesta(int repuesta) {
        this.repuesta = repuesta;
    }
}
