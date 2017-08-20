

package ENTIDAD;

import java.util.Date;

public class personaGradoAcademicoC {
    private String persona;
    private int item;
    private int grado;
    private String denominacion;
    private int institucion;
    private Date fecha;
    private int estadoRegistro;

    public personaGradoAcademicoC() {
    }

    public personaGradoAcademicoC(String persona, int item, int grado, String denominacion, int institucion, Date fecha, int estadoRegistro) {
        this.persona = persona;
        this.item = item;
        this.grado = grado;
        this.denominacion = denominacion;
        this.institucion = institucion;
        this.fecha = fecha;
        this.estadoRegistro = estadoRegistro;
    }



    public int getItem() {
        return item;
    }
    public void setItem(int item) {
        this.item = item;
    }
    public int getGrado() {
        return grado;
    }
    public void setGrado(int grado) {
        this.grado = grado;
    }
    public String getDenominacion() {
        return denominacion;
    }
    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }
    public int getInstitucion() {
        return institucion;
    }
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    public String getPersona() {
        return persona;
    }
    public void setPersona(String persona) {
        this.persona = persona;
    }
}
