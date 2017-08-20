package ENTIDAD;
import java.text.SimpleDateFormat;
import java.util.Date;

public class cronogramaPagoC {
    private int institucion;
    private String periodo;
    private int vencimiento;
    private String actividad;
    private double inversion;
    
    private Date fechaIni;
    private Date fechaFin;
    private int estadoRegistro;

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
     * @return the actividad
     */
    public String getActividad() {
        return actividad;
    }

    /**
     * @param actividad the actividad to set
     */
    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    /**
     * @return the inversion
     */
    public double getInversion() {
        return inversion;
    }

    /**
     * @param inversion the inversion to set
     */
    public void setInversion(double inversion) {
        this.inversion = inversion;
    }

    /**
     * @return the fechaIni
     */
    public Date getFechaIni() {
        return fechaIni;
    }

    /**
     * @param fechaIni the fechaIni to set
     */
    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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
     * @return the vencimiento
     */
    public int getVencimiento() {
        return vencimiento;
    }

    /**
     * @param vencimiento the vencimiento to set
     */
    public void setVencimiento(int vencimiento) {
        this.vencimiento = vencimiento;
    }
    
     public String fechaCompleta(){
         String fecha="";
         try{
             
             SimpleDateFormat format = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
            fecha= format.format(this.fechaIni) +" al "+ format.format(this.fechaFin);
         }catch(Exception ex){
             
         }
             
    
        return fecha;
    }
}
