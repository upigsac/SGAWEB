/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ENTIDAD;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Diseño
 */
public class controlDocente {

    private institucionC institucion;
    private periodoC periodo;
    private carrerasC carrera;
    private cursoSeccionC curso_seccion;
    private seccionPeriodoC seccion_periodo;
    private cursosC curso;
    private personalC personal;
    
    private tipoExamenC tipo_examen;
    private Date recepcion_fecha;

    private List<ColumnModel> examenes;
    private personaC persona;
    
    public controlDocente() {
        institucion = new institucionC();
        periodo = new periodoC();
        carrera = new carrerasC();
        curso_seccion = new cursoSeccionC();
        seccion_periodo = new seccionPeriodoC();
        curso = new cursosC();
        personal = new personalC();
        tipo_examen = new tipoExamenC();
        persona = new personaC();
    }

    /**
     * @return the institucion
     */
    public institucionC getInstitucion() {
        return institucion;
    }

    /**
     * @param institucion the institucion to set
     */
    public void setInstitucion(institucionC institucion) {
        this.institucion = institucion;
    }

    /**
     * @return the periodo
     */
    public periodoC getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(periodoC periodo) {
        this.periodo = periodo;
    }

    /**
     * @return the carrera
     */
    public carrerasC getCarrera() {
        return carrera;
    }

    /**
     * @param carrera the carrera to set
     */
    public void setCarrera(carrerasC carrera) {
        this.carrera = carrera;
    }

    /**
     * @return the personal
     */
    public personalC getPersonal() {
        return personal;
    }

    /**
     * @param personal the personal to set
     */
    public void setPersonal(personalC personal) {
        this.personal = personal;
    }

    /**
     * @return the tipo_examen
     */
    public tipoExamenC getTipo_examen() {
        return tipo_examen;
    }

    /**
     * @param tipo_examen the tipo_examen to set
     */
    public void setTipo_examen(tipoExamenC tipo_examen) {
        this.tipo_examen = tipo_examen;
    }

    /**
     * @return the recepcion_fecha
     */
    public Date getRecepcion_fecha() {
        return recepcion_fecha;
    }

    /**
     * @param recepcion_fecha the recepcion_fecha to set
     */
    public void setRecepcion_fecha(Date recepcion_fecha) {
        this.recepcion_fecha = recepcion_fecha;
    }

    /**
     * @return the examenes
     */
    public List<ColumnModel> getExamenes() {
        return examenes;
    }

    /**
     * @param examenes the examenes to set
     */
    public void setExamenes(List<ColumnModel> examenes) {
        this.examenes = examenes;
    }

    /**
     * @return the curso_seccion
     */
    public cursoSeccionC getCurso_seccion() {
        return curso_seccion;
    }

    /**
     * @param curso_seccion the curso_seccion to set
     */
    public void setCurso_seccion(cursoSeccionC curso_seccion) {
        this.curso_seccion = curso_seccion;
    }

    /**
     * @return the curso
     */
    public cursosC getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(cursosC curso) {
        this.curso = curso;
    }

    /**
     * @return the seccion_periodo
     */
    public seccionPeriodoC getSeccion_periodo() {
        return seccion_periodo;
    }

    /**
     * @param seccion_periodo the seccion_periodo to set
     */
    public void setSeccion_periodo(seccionPeriodoC seccion_periodo) {
        this.seccion_periodo = seccion_periodo;
    }

    /**
     * @return the persona
     */
    public personaC getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(personaC persona) {
        this.persona = persona;
    }

}
