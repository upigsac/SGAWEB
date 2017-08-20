/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.examRespuestaDAO;
import DAO.examenDAO;

import ENTIDAD.examenC;
import ENTIDAD.examenRespuestaC;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 *
 * @author Administrador
 */
@ManagedBean(name="administrarExamenOnlineB")
@ViewScoped
public class administrarExamenOnline {
    private List<examenC> examenL;

    private examenC examen;
    private List<examenRespuestaC> examRespuestaL;
    
    examenDAO examenD;
    examRespuestaDAO examRespuestaD;

    public administrarExamenOnline() {
        examenD=new examenDAO();
       examenL=examenD.mostrarExamen();
       mostrarRespuesta();
    }
    
    
    public void mostrarRespuesta(){
        examRespuestaD=new examRespuestaDAO();
        examRespuestaL=examRespuestaD.mostrarRespuesta();
    }
    
    public void nuevoExamen(){
        examen=new examenC();
        examen.setEstadoRegistro(1);
    }
    
    public void guardarExamen(){        
        util.consolaCliente("inciando ");
        examenD=new examenDAO();
        examenD.insertar(examen);
        util.consolaCliente("termino ");
        
         examenD=new examenDAO();
       examenL=examenD.mostrarExamen();
    }
    
    /**
     * @return the examenL
     */
    public List<examenC> getExamenL() {
        return examenL;
    }

    /**
     * @param examenL the examenL to set
     */
    public void setExamenL(List<examenC> examenL) {
        this.examenL = examenL;
    }

    /**
     * @return the examen
     */
    public examenC getExamen() {
        return examen;
    }

    /**
     * @param examen the examen to set
     */
    public void setExamen(examenC examen) {
        this.examen = examen;
    }

    /**
     * @return the examRespuestaL
     */
    public List<examenRespuestaC> getExamRespuestaL() {
        return examRespuestaL;
    }

    /**
     * @param examRespuestaL the examRespuestaL to set
     */
    public void setExamRespuestaL(List<examenRespuestaC> examRespuestaL) {
        this.examRespuestaL = examRespuestaL;
    }
}
