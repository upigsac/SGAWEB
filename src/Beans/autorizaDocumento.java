/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.personaAutorizacionDAO;
import ENTIDAD.personaAutorizacionC;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 *
 * @author Administrador
 */
@ManagedBean(name="autorizaDocumentoB")
@ViewScoped
public class autorizaDocumento {
    private int tipoAutorizacionS;
    private List<personaAutorizacionC> personaAutorizacionL;
    private personaAutorizacionC personaAutorizacion;
    private Date fechaFin=util.fechaHoy();
    private Date fechaInicio=util.dateAdd(getFechaFin(), 2, -1);
    personaAutorizacionDAO personaAutorizacionD;

    
     public void mostrarAutorizaciones(String personal,int tipoAutorizacion,Date fechaIni,Date fechaFin){
        personaAutorizacionD=new personaAutorizacionDAO();
        personaAutorizacionL=personaAutorizacionD.mostrarAutorizacion(personal, tipoAutorizacion,fechaIni,fechaFin);
    }
     public void inserta(personaAutorizacionC item){
         personaAutorizacionD=new personaAutorizacionDAO();
         personaAutorizacionD.insertar(item);
     }
     
     public void autorizar(personaAutorizacionC item,String resolucion){
         
         item.setEstado_registro(10);
         item.setResolucion(resolucion);
         inserta(item);
         mostrarAutorizaciones("0000000006",tipoAutorizacionS,fechaInicio,fechaFin);
     }
     public void rechazar(personaAutorizacionC item){
         item.setEstado_registro(0);
         inserta(item);
         mostrarAutorizaciones("0000000006",tipoAutorizacionS,fechaInicio,fechaFin);
     }
             
    /**
     * @return the tipoAutorizacionS
     */
    public int getTipoAutorizacionS() {
        return tipoAutorizacionS;
    }

    /**
     * @param tipoAutorizacionS the tipoAutorizacionS to set
     */
    public void setTipoAutorizacionS(int tipoAutorizacionS) {
        this.tipoAutorizacionS = tipoAutorizacionS;
    }

    /**
     * @return the personaAutorizacionL
     */
    public List<personaAutorizacionC> getPersonaAutorizacionL() {
        return personaAutorizacionL;
    }

    /**
     * @param personaAutorizacionL the personaAutorizacionL to set
     */
    public void setPersonaAutorizacionL(List<personaAutorizacionC> personaAutorizacionL) {
        this.personaAutorizacionL = personaAutorizacionL;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
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
     * @return the personaAutorizacion
     */
    public personaAutorizacionC getPersonaAutorizacion() {
        return personaAutorizacion;
    }

    /**
     * @param personaAutorizacion the personaAutorizacion to set
     */
    public void setPersonaAutorizacion(personaAutorizacionC personaAutorizacion) {
        this.personaAutorizacion = personaAutorizacion;
    }
}
