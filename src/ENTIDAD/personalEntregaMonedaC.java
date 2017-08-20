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
public class personalEntregaMonedaC {
    private String personal;
    private int ano;
    private int mes;
    private int dia;
    private String dinero;
    private int cantidad;
    private int estadoRegistro;

    public personalEntregaMonedaC() {
    }

    public personalEntregaMonedaC(String personal, int ano, int mes, int dia, String dinero, int cantidad, int estadoRegistro) {
        this.personal = personal;
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;
        this.dinero = dinero;
        this.cantidad = cantidad;
        this.estadoRegistro = estadoRegistro;
    }
    

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
     * @return the ano
     */
    public int getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(int ano) {
        this.ano = ano;
    }

    /**
     * @return the mes
     */
    public int getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * @return the dia
     */
    public int getDia() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     * @return the dinero
     */
    public String getDinero() {
        return dinero;
    }

    /**
     * @param dinero the dinero to set
     */
    public void setDinero(String dinero) {
        this.dinero = dinero;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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
