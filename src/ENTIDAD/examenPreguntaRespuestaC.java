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
public class examenPreguntaRespuestaC {
    private int examen;
    private int grupo;
    private int pregunta;
    private int respuesta;
    private int orden;
    private double valor;
    private int estadoRegistro;

    public examenPreguntaRespuestaC() {
    }

    public examenPreguntaRespuestaC(int examen, int grupo, int pregunta, int respuesta, int orden, double valor, int estadoRegistro) {
        this.examen = examen;
        this.grupo = grupo;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.orden = orden;
        this.valor = valor;
        this.estadoRegistro = estadoRegistro;
    }
    

    /**
     * @return the examen
     */
    public int getExamen() {
        return examen;
    }

    /**
     * @param examen the examen to set
     */
    public void setExamen(int examen) {
        this.examen = examen;
    }

    /**
     * @return the grupo
     */
    public int getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(int grupo) {
        this.grupo = grupo;
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
     * @return the respuesta
     */
    public int getRespuesta() {
        return respuesta;
    }

    /**
     * @param respuesta the respuesta to set
     */
    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
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
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }
}
