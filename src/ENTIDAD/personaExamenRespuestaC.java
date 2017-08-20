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
public class personaExamenRespuestaC {
    private int examen;
    private int grupo;
    private int pregunta;
    private int respuesta;
    private String personaExamen; 

    public personaExamenRespuestaC() {
    }

    public personaExamenRespuestaC(int examen, int grupo, int pregunta, int respuesta, String personaExamen) {
        this.examen = examen;
        this.grupo = grupo;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.personaExamen = personaExamen;
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
     * @return the personaExamen
     */
    public String getPersonaExamen() {
        return personaExamen;
    }

    /**
     * @param personaExamen the personaExamen to set
     */
    public void setPersonaExamen(String personaExamen) {
        this.personaExamen = personaExamen;
    }
            
}
