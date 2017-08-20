/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.examenGrupoDAO;
import DAO.examPreguntaDAO;
import DAO.examRespuestaDAO;
import DAO.personaExamenDAO;

import ENTIDAD.examPreguntaC;
import ENTIDAD.examenRespuestaC;

import ENTIDAD.personaExamenC;
import ENTIDAD.personaExamenRespuestaC;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 *
 * @author Administrador
 */
@ManagedBean(name="examenVirtualB")
@ViewScoped
public class examenVirtual {
    
    private List<examenRespuestaC> examRespuestaL;
    private List<examPreguntaC> examPreguntaL;
  
    
    private personaExamenC personaExamen=new personaExamenC();
    private List<personaExamenRespuestaC> personaExamenRespuestaL=new ArrayList<>();
    private List<resumen> resumenL;
    private detallePregunta preguntaResumen;
    private int respuesta=0;
    private int totalPreguntas;
    private int totalCorrectas;
    
    private boolean bandera=false;
    private boolean banderaPregunta=false;
    
    private Date tiempoTermino;
    private Date tiempoInicio;
    
    
    
    public void comenzar(){
        bandera=true;
        util.script("iniciarExamen()");
    }
     
    
    
    public List<examPreguntaC> mostrarPreguntas(int examen,int grupo){
        examPreguntaD=new examPreguntaDAO();
        examPreguntaL=examPreguntaD.mostrarPregunta(examen, grupo);
        return examPreguntaL;
    }
    public List<examenRespuestaC> mostrarRespuesta(int examen,int grupo,int pregunta){
        examRespuestaD=new examRespuestaDAO();
        examRespuestaL=examRespuestaD.mostrarRespuesta(examen, grupo,pregunta);
        return examRespuestaL;
    }
    
    
    public void validarRespuesta(int examen,int grupo,int pregunta,int respuesta){
        boolean bandera=false;
        int indice =0;
        for (personaExamenRespuestaC item : personaExamenRespuestaL) {
            if (item.getPregunta()==pregunta){
                bandera=true;
                
               break;     
            }
           indice++;
        }
                    
            
        
        if (bandera){
            
            personaExamenRespuestaL.set(indice, new personaExamenRespuestaC(examen, grupo, pregunta, respuesta, null));
        }else{
            personaExamenRespuestaL.add(new personaExamenRespuestaC(examen, grupo, pregunta, respuesta, ""));
        }
        
      //  personaExamenRespuestaL.add(new personaExamenRespuestaC(examen, grupo, pregunta, respuesta, ""));
        banderaPregunta=true;
        util.consolaCliente("cantidad " +personaExamenRespuestaL.size());
    }
    
    examenGrupoDAO examGrupoD;
    examPreguntaDAO examPreguntaD;
    examRespuestaDAO examRespuestaD;
    personaExamenDAO personaExamenD;

    /**
     * @return the preguntaResumen
     */
    public detallePregunta getPreguntaResumen() {
        return preguntaResumen;
    }

    /**
     * @param preguntaResumen the preguntaResumen to set
     */
    public void setPreguntaResumen(detallePregunta preguntaResumen) {
        this.preguntaResumen = preguntaResumen;
    }

    /**
     * @return the tiempoTermino
     */
    public Date getTiempoTermino() {
        return tiempoTermino;
    }

    /**
     * @param tiempoTermino the tiempoTermino to set
     */
    public void setTiempoTermino(Date tiempoTermino) {
        this.tiempoTermino = tiempoTermino;
    }

    /**
     * @return the tiempoInicio
     */
    public Date getTiempoInicio() {
        return tiempoInicio;
    }

    /**
     * @param tiempoInicio the tiempoInicio to set
     */
    public void setTiempoInicio(Date tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }

    
    
    
    public static class detallePregunta{
        private int total_preguntas;
        private int total_minutos;

        /**
         * @return the total_preguntas
         */
        public int getTotal_preguntas() {
            return total_preguntas;
        }

        /**
         * @param total_preguntas the total_preguntas to set
         */
        public void setTotal_preguntas(int total_preguntas) {
            this.total_preguntas = total_preguntas;
        }

        /**
         * @return the total_minutos
         */
        public int getTotal_minutos() {
            return total_minutos;
        }

        /**
         * @param total_minutos the total_minutos to set
         */
        public void setTotal_minutos(int total_minutos) {
            this.total_minutos = total_minutos;
        }
    }
    /**
     * @return the resumenL
     */
    public List<resumen> getResumenL() {
      
        return resumenL;
    }

    /**
     * @param resumenL the resumenL to set
     */
    public void setResumenL(List<resumen> resumenL) {
        this.resumenL = resumenL;
    }

    /**
     * @return the totalPreguntas
     */
    public int getTotalPreguntas() {
        return totalPreguntas;
    }

    /**
     * @param totalPreguntas the totalPreguntas to set
     */
    public void setTotalPreguntas(int totalPreguntas) {
        this.totalPreguntas = totalPreguntas;
    }

    /**
     * @return the totalCorrectas
     */
    public int getTotalCorrectas() {
        return totalCorrectas;
    }

    /**
     * @param totalCorrectas the totalCorrectas to set
     */
    public void setTotalCorrectas(int totalCorrectas) {
        this.totalCorrectas = totalCorrectas;
    }

    /**
     * @return the bandera
     */
    public boolean isBandera() {
        return bandera;
    }

    /**
     * @param bandera the bandera to set
     */
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    /**
     * @return the banderaPregunta
     */
    public boolean isBanderaPregunta() {
        return banderaPregunta;
    }

    /**
     * @param banderaPregunta the banderaPregunta to set
     */
    public void setBanderaPregunta(boolean banderaPregunta) {
        this.banderaPregunta = banderaPregunta;
    }
    
    
    
    public static class resumen{
        private String tema;
        private int totalFormuladas;
        private int totalCorrectas;
        private double totalPuntaje;

        /**
         * @return the tema
         */
        public String getTema() {
            return tema;
        }

        /**
         * @param tema the tema to set
         */
        public void setTema(String tema) {
            this.tema = tema;
        }

        /**
         * @return the totalFormuladas
         */
        public int getTotalFormuladas() {
            return totalFormuladas;
        }

        /**
         * @param totalFormuladas the totalFormuladas to set
         */
        public void setTotalFormuladas(int totalFormuladas) {
            this.totalFormuladas = totalFormuladas;
        }

        /**
         * @return the totalCorrectas
         */
        public int getTotalCorrectas() {
            return totalCorrectas;
        }

        /**
         * @param totalCorrectas the totalCorrectas to set
         */
        public void setTotalCorrectas(int totalCorrectas) {
            this.totalCorrectas = totalCorrectas;
        }

        /**
         * @return the totalPuntaje
         */
        public double getTotalPuntaje() {
            return totalPuntaje;
        }

        /**
         * @param totalPuntaje the totalPuntaje to set
         */
        public void setTotalPuntaje(double totalPuntaje) {
            this.totalPuntaje = totalPuntaje;
        }
        
    }
    
    
    /**
     * @return the examRespuestaL
     */
    public List<examenRespuestaC> getExamRespuestaL() {
        return examRespuestaL;
    }

    /**
     * @param examRespuestaL the examRespuestaL to set
     */
    public void setExamRespuestaL(List<examenRespuestaC> examRespuestaL) {
        this.examRespuestaL = examRespuestaL;
    }

    /**
     * @return the examPreguntaL
     */
    public List<examPreguntaC> getExamPreguntaL() {
        return examPreguntaL;
    }

    /**
     * @param examPreguntaL the examPreguntaL to set
     */
    public void setExamPreguntaL(List<examPreguntaC> examPreguntaL) {
        this.examPreguntaL = examPreguntaL;
    }

    /**
     * @return the personaExamen
     */
    public personaExamenC getPersonaExamen() {
        return personaExamen;
    }

    /**
     * @param personaExamen the personaExamen to set
     */
    public void setPersonaExamen(personaExamenC personaExamen) {
        this.personaExamen = personaExamen;
    }

    /**
     * @return the personaExamenRespuestaL
     */
    public List<personaExamenRespuestaC> getPersonaExamenRespuestaL() {
        return personaExamenRespuestaL;
    }

    /**
     * @param personaExamenRespuestaL the personaExamenRespuestaL to set
     */
    public void setPersonaExamenRespuestaL(List<personaExamenRespuestaC> personaExamenRespuestaL) {
        this.personaExamenRespuestaL = personaExamenRespuestaL;
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

 

 
}
