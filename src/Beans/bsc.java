/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author Administrador
 */
@ManagedBean(name="bscB")
@SessionScoped
public class bsc {
    private String subTitulo;
    private int anio=2016;
    private int perspetiva;
    
    
    
    public void seleccionMenu(int indice,String name){
        
        subTitulo=name;
        
        switch (indice){
            case 1:util.redirigir("datos.xhtml"); break;
            case 2: break;
            case 3:util.redirigir("balance.xhtml"); break;
            
            case 4:util.redirigir("financiera.xhtml");perspetiva=2; break;
            case 5:util.redirigir("cliente.xhtml");perspetiva=1; break;
            case 6:util.redirigir("proceso.xhtml");perspetiva=3; break;    
            case 7:util.redirigir("recursoHumano.xhtml");perspetiva=4; break;
            case 8:util.redirigir("semaforo.xhtml"); break;
        }
        
        
        
        
    }
    

    /**
     * @return the subTitulo
     */
    public String getSubTitulo() {
        return subTitulo;
    }

    /**
     * @param subTitulo the subTitulo to set
     */
    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }

    /**
     * @return the anio
     */
    public int getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    /**
     * @return the perspetiva
     */
    public int getPerspetiva() {
        return perspetiva;
    }

    /**
     * @param perspetiva the perspetiva to set
     */
    public void setPerspetiva(int perspetiva) {
        this.perspetiva = perspetiva;
    }
}
