/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ENTIDAD;

/**
 *
 * @author Diseño
 */
public class alumnoCarreraCambio {
    
    private String carrera;
    private String codigoAlumno;
    private String nombreCompleto;

    public alumnoCarreraCambio(String codigoAlumno){
        this.codigoAlumno=codigoAlumno;
    }
    
    public alumnoCarreraCambio(String carrera,String codigoAlumno,String nombreCompleto){
        this.carrera=carrera;
        this.codigoAlumno=codigoAlumno;
        this.nombreCompleto=nombreCompleto;
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
     * @return the nombreCompleto
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * @param nombreCompleto the nombreCompleto to set
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * @return the codigoAlumno
     */
    public String getCodigoAlumno() {
        return codigoAlumno;
    }

    /**
     * @param codigoAlumno the codigoAlumno to set
     */
    public void setCodigoAlumno(String codigoAlumno) {
        this.codigoAlumno = codigoAlumno;
    }
    
    
    
    
}
