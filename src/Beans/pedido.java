/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.aulaDAO;
import DAO.pabellonDAO;
import DAO.pabellonPisoDAO;
import ENTIDAD.aulaC;
import ENTIDAD.pabellonC;
import ENTIDAD.pabellonPisoC;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Administrador
 */
@ManagedBean(name="pedidoB")
@ViewScoped
public class pedido {
    private List<pabellonC> pabellonL;
 
    private List<pedidoPisoAula> pedidoPisoAulaL=new ArrayList<>();
    
    pabellonDAO pabellonD;
    pabellonPisoDAO pabellinPisoD;
    aulaDAO aulaD;
    public pedido() {
        pabellonD=new pabellonDAO();
        pabellonL=pabellonD.mostrarPabellon();
    }
    
    
    public void seleccionPabellon(pabellonC item){
        
        
        pabellinPisoD=new pabellonPisoDAO();
        aulaD=new aulaDAO();
       pedidoPisoAulaL=new ArrayList<>();
        pedidoPisoAula itemPedidoPisoAula;
        for (pabellonPisoC itemPiso : pabellinPisoD.mostrarPabellonPiso(item.getPabellon())) {
            itemPedidoPisoAula=new pedidoPisoAula(itemPiso);
            for (aulaC itemAula : aulaD.mostrarAulaPabellonPiso(itemPiso.getPabellon(), itemPiso.getPiso())) {
                itemPedidoPisoAula.aulaL.add(itemAula);
            }
            getPedidoPisoAulaL().add(itemPedidoPisoAula);
        }
    }

    /**
     * @return the pedidoPisoAulaL
     */
    public List<pedidoPisoAula> getPedidoPisoAulaL() {
        return pedidoPisoAulaL;
    }

    /**
     * @param pedidoPisoAulaL the pedidoPisoAulaL to set
     */
    public void setPedidoPisoAulaL(List<pedidoPisoAula> pedidoPisoAulaL) {
        this.pedidoPisoAulaL = pedidoPisoAulaL;
    }

   
    
    
    public class pedidoPisoAula{
       private pabellonPisoC pabellonPiso;
       private List<aulaC> aulaL=new ArrayList<>();

        public pedidoPisoAula() {
        }

        public pedidoPisoAula(pabellonPisoC pabellonPiso) {
            this.pabellonPiso = pabellonPiso;
        }

   
        

        /**
         * @return the pabellonPiso
         */
        public pabellonPisoC getPabellonPiso() {
            return pabellonPiso;
        }

        /**
         * @param pabellonPiso the pabellonPiso to set
         */
        public void setPabellonPiso(pabellonPisoC pabellonPiso) {
            this.pabellonPiso = pabellonPiso;
        }

        /**
         * @return the aulaL
         */
        public List<aulaC> getAulaL() {
            return aulaL;
        }

        /**
         * @param aulaL the aulaL to set
         */
        public void setAulaL(List<aulaC> aulaL) {
            this.aulaL = aulaL;
        }
                
    }
    

    /**
     * @return the pabellonL
     */
    public List<pabellonC> getPabellonL() {
        return pabellonL;
    }

    /**
     * @param pabellonL the pabellonL to set
     */
    public void setPabellonL(List<pabellonC> pabellonL) {
        this.pabellonL = pabellonL;
    }

    
}
