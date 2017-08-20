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
public class usuarioInstitucionC {
    private String usuario ;
    private int institucion;
    private int estado_registro;

    public usuarioInstitucionC() {
    }

    public usuarioInstitucionC(String usuario, int institucion, int estado_registro) {
        this.usuario = usuario;
        this.institucion = institucion;
        this.estado_registro = estado_registro;
    }
    

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
     * @return the estado_registro
     */
    public int getEstado_registro() {
        return estado_registro;
    }

    /**
     * @param estado_registro the estado_registro to set
     */
    public void setEstado_registro(int estado_registro) {
        this.estado_registro = estado_registro;
    }
            
            
            
}
