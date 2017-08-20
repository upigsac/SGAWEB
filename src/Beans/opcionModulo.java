/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import DAO.opcionModuloDAO;
import ENTIDAD.opcionModuloC;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name = "opcionModuloB")
public class opcionModulo {

    @ManagedProperty(value = "#{opcionProgramaB}")
    private opcionPrograma programa;

    private static List<opcionModuloC> moduloL;
    private static int Seleccion;

    @PostConstruct
    public void init() {
      //  Seleccion=1;
        //    cargarModulo();
        //refrescar();

    }

    opcionModuloDAO modulo = null;

    public void cargarModulo() {

        // util.consolaCliente( "modulo");
        modulo = new opcionModuloDAO();

        moduloL = modulo.MostarOpcion(programa.getSeleccion());
    }

    /**
     * @return the moduloL
     */
    public List<opcionModuloC> getModuloL() {
        return moduloL;
    }

    /**
     * @param moduloL the moduloL to set
     */
    public void setModuloL(List<opcionModuloC> moduloL) {
        this.moduloL = moduloL;
    }

    /**
     * @return the Seleccion
     */
    public int getSeleccion() {
        return Seleccion;
    }

    /**
     * @param Seleccion the Seleccion to set
     */
    public void setSeleccion(int Seleccion) {
        this.Seleccion = Seleccion;
    }

    public opcionPrograma getPrograma() {
        return programa;
    }

    public void setPrograma(opcionPrograma programa) {
        this.programa = programa;
    }

}
