/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import DAO.opcionAccesoDAO;
import DAO.opcionAccesoUsuarioDAO;
import ENTIDAD.opcionAccesoC;
import ENTIDAD.opcionAccesoUsuarioC;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Administrador
 */
@ManagedBean(name = "opcionAccesoUsuarioB")
public class opcionAccesoUsuario implements Serializable {

    private static List<opcionAccesoUsuarioC> opcionUsuarioL;
    private static List<opcionAccesoUsuarioC> opcionUsuarioHijoL;

    @ManagedProperty(value = "#{opcionModuloB}")
    private opcionModulo modulo;

    @ManagedProperty(value = "#{usuarioB}")
    private usuario usuB;

    public opcionAccesoUsuario() {

     
    }

    opcionAccesoUsuarioDAO opcionUsuario = null;

    public void mostrarMenu() {
        //util.consolaCliente( "menu de administrativo");
        opcionUsuario = new opcionAccesoUsuarioDAO();                                         //estatico 3
        opcionUsuarioL = opcionUsuario.MostarMenuPadre(usuB.getUsu(), modulo.getPrograma().getSeleccion(),
                modulo.getSeleccion(), modulo.getPrograma().getTipo());

    }

    public List<opcionAccesoUsuarioC> mostrarMenuHijo(int padre) {

        opcionUsuario = new opcionAccesoUsuarioDAO();
        opcionUsuarioHijoL = new ArrayList<opcionAccesoUsuarioC>();
        opcionUsuarioHijoL = opcionUsuario.MostarMenuHijo(usuB.getUsu(), 1, 3, 1, padre);

        return opcionUsuarioHijoL;
    }
    opcionAccesoC opcionAcceso = null;
    opcionAccesoDAO acceso = null;

    public opcionAccesoC opcionAccesoCodigo(int codigo) {

        acceso = new opcionAccesoDAO();
        opcionAcceso = new opcionAccesoC();
        opcionAcceso = acceso.opcionAccesoCodigo(codigo);
        return opcionAcceso;

    }

    /**
     * @return the opcionUsuarioL
     */
    public List<opcionAccesoUsuarioC> getOpcionUsuarioL() {
        return opcionUsuarioL;
    }

    /**
     * @param opcionUsuarioL the opcionUsuarioL to set
     */
    public void setOpcionUsuarioL(List<opcionAccesoUsuarioC> opcionUsuarioL) {
        this.opcionUsuarioL = opcionUsuarioL;
    }

    /**
     * @return the opcionUsuarioHijoL
     */
    public List<opcionAccesoUsuarioC> getOpcionUsuarioHijoL() {
        return opcionUsuarioHijoL;
    }

    /**
     * @param opcionUsuarioHijoL the opcionUsuarioHijoL to set
     */
    public void setOpcionUsuarioHijoL(List<opcionAccesoUsuarioC> opcionUsuarioHijoL) {
        this.opcionUsuarioHijoL = opcionUsuarioHijoL;
    }

    /**
     * @return the modulo
     */
    public opcionModulo getModulo() {
        return modulo;
    }

    /**
     * @param modulo the modulo to set
     */
    public void setModulo(opcionModulo modulo) {
        this.modulo = modulo;
    }

    /**
     * @return the usuB
     */
    public usuario getUsuB() {
        return usuB;
    }

    /**
     * @param usuB the usuB to set
     */
    public void setUsuB(usuario usuB) {
        this.usuB = usuB;
    }

}
