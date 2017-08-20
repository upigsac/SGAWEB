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
public class personaGastosC {
    private String persona;
    private int gasto;
    private double monto;
    private int estadoRegistro;

    public personaGastosC() {
    }

    public personaGastosC(String persona, int gasto, double monto, int estadoRegistro) {
        this.persona = persona;
        this.gasto = gasto;
        this.monto = monto;
        this.estadoRegistro = estadoRegistro;
    }

    /**
     * @return the persona
     */
    public String getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(String persona) {
        this.persona = persona;
    }

    /**
     * @return the gasto
     */
    public int getGasto() {
        return gasto;
    }

    /**
     * @param gasto the gasto to set
     */
    public void setGasto(int gasto) {
        this.gasto = gasto;
    }

    /**
     * @return the monto
     */
    public double getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(double monto) {
        this.monto = monto;
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
