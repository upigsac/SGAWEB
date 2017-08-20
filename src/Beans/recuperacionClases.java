/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import DAO.registroReprogramacionmDAO;
import DAO.registrotmDAO;
import ENTIDAD.registroReprogramacionmC;
import ENTIDAD.registrotmC;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.swing.JOptionPane;

//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
import org.apache.commons.lang3.StringEscapeUtils;
import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;

/**
 *
 * @author Administrador
 */
@ManagedBean(name = "recuperacionClasesB")
@RequestScoped
public class recuperacionClases implements Serializable {

    private FacesMessage fcmes;
    private final String CANAL_2 = "/upig";
    private final PushContext pushcon = PushContextFactory.getDefault().getPushContext();
    private List<registrotmC> registrotmL;
    private registrotmC registrotm;
    private registroReprogramacionmC registroReprogramacionm;

    util js = new util();
    registrotmDAO registrotmD;
    registroReprogramacionmDAO registroReprogramacionmD;

    public void solicitar(registroReprogramacionmC item) {

        // registroReprogramacionmD=new registroReprogramacionmDAO();
        // registroReprogramacionmD.insertar(new registroReprogramacionmC(null, 1, "20142", "200702", "000001", "", "", 1, "IV", "AULA", "PERSONAL", js.fechaHoy(), js.fechaHoy(), js.fechaHoy(), "FUT", "OBSERVACION", 1));
        fcmes = new FacesMessage(StringEscapeUtils.escapeHtml3("prueba"), StringEscapeUtils.escapeHtml3("xx"));
        pushcon.push(CANAL_2, fcmes);
    }

    public void nuevo() {
        registrotm = new registrotmC();
        registrotm.setId(-1);
        registrotm.setInstitucion(1);
        registrotm.setPeriodo("20142");

    }

    public List<registrotmC> mostrarRecuperacion(int institucion, String periodo, int estado) {
        registrotmD = new registrotmDAO();
        registrotmL = registrotmD.mostrarRecuperacionRegistrotm(institucion, periodo, estado);
        return registrotmL;
    }

    /**
     * @return the registrotmL
     */
    public List<registrotmC> getRegistrotmL() {
        return registrotmL;
    }

    /**
     * @param registrotmL the registrotmL to set
     */
    public void setRegistrotmL(List<registrotmC> registrotmL) {
        this.registrotmL = registrotmL;
    }

    /**
     * @return the registrotm
     */
    public registrotmC getRegistrotm() {
        return registrotm;
    }

    /**
     * @param registrotm the registrotm to set
     */
    public void setRegistrotm(registrotmC registrotm) {
        this.registrotm = registrotm;
    }

    /**
     * @return the registroReprogramacionm
     */
    public registroReprogramacionmC getRegistroReprogramacionm() {
        return registroReprogramacionm;
    }

    /**
     * @param registroReprogramacionm the registroReprogramacionm to set
     */
    public void setRegistroReprogramacionm(registroReprogramacionmC registroReprogramacionm) {
        this.registroReprogramacionm = registroReprogramacionm;
    }

}
