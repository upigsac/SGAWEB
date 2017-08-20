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
public class bscPerpestivaC extends bscCuadroC{
    private int perpestiva;
    private String descripcion;
    private String abreviatura;
    private String classIcon;
    private String prefijo;
    private int estadoRegistro;

    public bscPerpestivaC() {
    }

    public bscPerpestivaC(int perpestiva, String descripcion, String abreviatura, String classIcon, String prefijo, int estadoRegistro) {
        this.perpestiva = perpestiva;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
        this.classIcon = classIcon;
        this.prefijo = prefijo;
        this.estadoRegistro = estadoRegistro;
    }

 

    public bscPerpestivaC(int perpestiva, String descripcion, String abreviatura, String classIcon, String prefijo, int estadoRegistro, int anio, int mes, double actual, double meta, double porcentaje, String color) {
        super(anio, mes, actual, meta, porcentaje, color);
        this.perpestiva = perpestiva;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
        this.classIcon = classIcon;
        this.prefijo = prefijo;
        this.estadoRegistro = estadoRegistro;
    }

 

    
    
    

    /**
     * @return the perpestiva
     */
    public int getPerpestiva() {
        return perpestiva;
    }

    /**
     * @param perpestiva the perpestiva to set
     */
    public void setPerpestiva(int perpestiva) {
        this.perpestiva = perpestiva;
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
     * @return the classIcon
     */
    public String getClassIcon() {
        return classIcon;
    }

    /**
     * @param classIcon the classIcon to set
     */
    public void setClassIcon(String classIcon) {
        this.classIcon = classIcon;
    }

    /**
     * @return the prefijo
     */
    public String getPrefijo() {
        return prefijo;
    }

    /**
     * @param prefijo the prefijo to set
     */
    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }
}
