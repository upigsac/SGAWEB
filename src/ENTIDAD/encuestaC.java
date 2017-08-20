package ENTIDAD;


public class encuestaC {
    private int encuesta;
    private String descripcion;
    private String fecha_inicio;
    private String fecha_fin;
    private int estado_Registro;

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
     * @return the fecha_inicio
     */
    public String getFecha_inicio() {
        return fecha_inicio;
    }

    /**
     * @param fecha_inicio the fecha_inicio to set
     */
    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
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
     * @return the estado_Registro
     */
    public int getEstado_Registro() {
        return estado_Registro;
    }

    /**
     * @param estado_Registro the estado_Registro to set
     */
    public void setEstado_Registro(int estado_Registro) {
        this.estado_Registro = estado_Registro;
    }

    /**
     * @return the fecha_fin
     */
    public String getFecha_fin() {
        return fecha_fin;
    }

    /**
     * @param fecha_fin the fecha_fin to set
     */
    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
}
