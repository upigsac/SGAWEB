
package ENTIDAD;

import java.io.Serializable;


public class personalOficinaC implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String personal ;
    private int institucion;
    private int oficina;
    private int cargo;
    private int estadoRegistro;

    /**
     * @return the personal
     */
    public String getPersonal() {
        return personal;
    }

    /**
     * @param personal the personal to set
     */
    public void setPersonal(String personal) {
        this.personal = personal;
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
     * @return the oficina
     */
    public int getOficina() {
        return oficina;
    }

    /**
     * @param oficina the oficina to set
     */
    public void setOficina(int oficina) {
        this.oficina = oficina;
    }

    /**
     * @return the cargo
     */
    public int getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(int cargo) {
        this.cargo = cargo;
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
}
