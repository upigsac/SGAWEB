/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;


import DAO.revistaDAO;
import DAO.revistaDetalleDAO;
import ENTIDAD.revistaC;
import ENTIDAD.revistaDetalleC;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Administrador
 */

@ManagedBean(name="revistaB")
@ViewScoped
public class revista {
    private List<revistaC> revistaL;
    
    private List<revistaDetalleC> revistaDetalleL=new ArrayList<>();
    
    private String msg="'hola'";
    private String pagina="";
    
    
    
    revistaDAO revistaD;
    revistaDetalleDAO revistaDetalleD;
    
    public List<revistaC> mostrarRevista(String periodo,String carrera){
        revistaD=new revistaDAO();
        revistaL=revistaD.mostrarRevista(periodo, carrera);
        return revistaL;
    }

    /**
     * @return the revistaL
     */
    public List<revistaC> getRevistaL() {
        return revistaL;
    }

    /**
     * @param revistaL the revistaL to set
     */
    public void setRevistaL(List<revistaC> revistaL) {
        this.revistaL = revistaL;
    }


    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the revistaDetalleL
     */
    public List<revistaDetalleC> getRevistaDetalleL() {
       
        return revistaDetalleL;
    }

    /**
     * @param revistaDetalleL the revistaDetalleL to set
     */
    public void setRevistaDetalleL(List<revistaDetalleC> revistaDetalleL) {
        this.revistaDetalleL = revistaDetalleL;
    }

    /**
     * @return the pagina
     */
    public String getPagina() {
        revistaDetalleD=new revistaDetalleDAO();
        pagina=revistaDetalleD.detalleRevistaLineal("0000000001");
        return pagina;
    }

    /**
     * @param pagina the pagina to set
     */
    public void setPagina(String pagina) {
        
        this.pagina = pagina;
    }


    
    
}
