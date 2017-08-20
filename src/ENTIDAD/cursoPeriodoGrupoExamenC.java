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
public class cursoPeriodoGrupoExamenC {
    private int institucion;
    private String periodo;
    private String carrera;
    private String malla;
    private String curso;
    private int grupoExamen;
    private int maximoVacantes;
    private int vacantesActuales;
    private int estadoRegistro;

    public cursoPeriodoGrupoExamenC() {
    }

    public cursoPeriodoGrupoExamenC(int institucion, String periodo, String carrera, String malla, String curso, int grupoExamen, int maximoVacantes, int vacantesActuales, int estadoRegistro) {
        this.institucion = institucion;
        this.periodo = periodo;
        this.carrera = carrera;
        this.malla = malla;
        this.curso = curso;
        this.grupoExamen = grupoExamen;
        this.maximoVacantes = maximoVacantes;
        this.vacantesActuales = vacantesActuales;
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
     * @return the grupoExamen
     */
    public int getGrupoExamen() {
        return grupoExamen;
    }

    /**
     * @param grupoExamen the grupoExamen to set
     */
    public void setGrupoExamen(int grupoExamen) {
        this.grupoExamen = grupoExamen;
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
     * @return the maximoVacantes
     */
    public int getMaximoVacantes() {
        return maximoVacantes;
    }

    /**
     * @param maximoVacantes the maximoVacantes to set
     */
    public void setMaximoVacantes(int maximoVacantes) {
        this.maximoVacantes = maximoVacantes;
    }

    /**
     * @return the vacantesActuales
     */
    public int getVacantesActuales() {
        return vacantesActuales;
    }

    /**
     * @param vacantesActuales the vacantesActuales to set
     */
    public void setVacantesActuales(int vacantesActuales) {
        this.vacantesActuales = vacantesActuales;
    }
}
