/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.bscSemaforoDAO;
import ENTIDAD.bscSemaforoC;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Administrador
 */
@ManagedBean(name="bscSemaforoB")
@ViewScoped
public class bscSemaforo {
    private bscSemaforoC bscSemaforo;
    private List<bscSemaforoC> bscSemaforoL;
    
    bscSemaforoDAO bscSemaforoD;
    public bscSemaforo() {
        mostrarSemaforo();
    }
    
    
    
    
    public void mostrarSemaforo(){
        bscSemaforoD=new bscSemaforoDAO();
        bscSemaforoL=bscSemaforoD.mostrarSemaforo();
    }
    
    public void insertar(){
        bscSemaforoD=new bscSemaforoDAO();
        bscSemaforoD.insertar(bscSemaforo);
        mostrarSemaforo();
    }
    public void editar(bscSemaforoC item){
        bscSemaforo=item;
        util.script("dlSemaforo.show()");
        
    }
    public void nuevo(){
        bscSemaforo=new bscSemaforoC(-1, 0, 0, null, null, 1);
        util.script("dlSemaforo.show()");
    }
    
    public void eliminar(bscSemaforoC item){
        
        bscSemaforoD=new bscSemaforoDAO();
        bscSemaforoD.eliminar(item);
        mostrarSemaforo();
        
        
        
    }
    

    /**
     * @return the bscSemaforo
     */
    public bscSemaforoC getBscSemaforo() {
        return bscSemaforo;
    }

    /**
     * @param bscSemaforo the bscSemaforo to set
     */
    public void setBscSemaforo(bscSemaforoC bscSemaforo) {
        this.bscSemaforo = bscSemaforo;
    }

    /**
     * @return the bscSemaforoL
     */
    public List<bscSemaforoC> getBscSemaforoL() {
        return bscSemaforoL;
    }

    /**
     * @param bscSemaforoL the bscSemaforoL to set
     */
    public void setBscSemaforoL(List<bscSemaforoC> bscSemaforoL) {
        this.bscSemaforoL = bscSemaforoL;
    }
    
}
