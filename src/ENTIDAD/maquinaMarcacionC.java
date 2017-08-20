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
public class maquinaMarcacionC {
    private int item ;
    private String area;
    private String pc;
    private String ip;
    private int estadoRegistro;

    public maquinaMarcacionC() {
    }

    public maquinaMarcacionC(int item, String area, String pc, String ip, int estadoRegistro) {
        this.item = item;
        this.area = area;
        this.pc = pc;
        this.ip = ip;
        this.estadoRegistro = estadoRegistro;
    }
    

    /**
     * @return the item
     */
    public int getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(int item) {
        this.item = item;
    }

    /**
     * @return the pc
     */
    public String getPc() {
        return pc;
    }

    /**
     * @param pc the pc to set
     */
    public void setPc(String pc) {
        this.pc = pc;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
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
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }
}
