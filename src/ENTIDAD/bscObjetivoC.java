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
public class bscObjetivoC extends bscCuadroC{
    private String objetivo;
    private String descripcion;
    private String abreviatura;
    private int estadoRegistro;

    

    public bscObjetivoC() {
    }

    public bscObjetivoC(String objetivo, String descripcion, String abreviatura, int estadoRegistro) {
        this.objetivo = objetivo;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
        this.estadoRegistro = estadoRegistro;
    }

    public bscObjetivoC(String objetivo, String descripcion, String abreviatura, int estadoRegistro, int anio, int mes, double actual, double meta, double porcentaje, String color) {
        super(anio, mes, actual, meta, porcentaje, color);
        this.objetivo = objetivo;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
        this.estadoRegistro = estadoRegistro;
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
     * @return the abreviatura
     */
    public String getAbreviatura() {
        return abreviatura;
    }

    /**
     * @param abreviatura the abreviatura to set
     */
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
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
     * @return the objetivo
     */
    public String getObjetivo() {
        return objetivo;
    }

    /**
     * @param objetivo the objetivo to set
     */
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

   
}
