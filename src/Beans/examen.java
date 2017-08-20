

package Beans;

import DAO.examenDAO;
import DAO.examenGrupoPreguntaDAO;
import DAO.examenPreguntaDAO;
import DAO.examenPreguntaRespuestaDAO;
import DAO.examenRespuestaDAO;
import DAO.grupoDAO;
import ENTIDAD.examenC;
import ENTIDAD.examenPreguntaRespuestaC;
import ENTIDAD.examenRespuestaC;
import ENTIDAD.grupoC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="examenB")
@ViewScoped
public class examen  {
    private examenRespuestaC examenRespuesta;
    private List<examenRespuestaC> examenRespuestaL;
    private List<examenC> examenL;
    private examenC examen;  
    private List<grupoC> grupoL;
    
    examenRespuestaDAO examenRespuestaD;
    examenPreguntaDAO examenPreguntaD;
    examenPreguntaRespuestaDAO examenPreguntaRespuestaD;
    examenGrupoPreguntaDAO examenGrupoPreguntaD;
    examenDAO examenD;
    grupoDAO grupoD;
    public examen() {
        mostrarExamen();
        mostrarRepuesta();
        
    }
    
    
    public void mostrarExamen(){
        examenD=new examenDAO();
        examenL=examenD.mostrarExamen();
    }
    
    public void insertarRespuesta(){
        examenRespuestaD=new examenRespuestaDAO();
        examenRespuestaD.insertar(examenRespuesta);
        util.script("dlgPregunta.hide()");
    }
    
      public void insertarExamen(){
        examenD=new examenDAO();
        examenD.insertar(examen);
        
        examenL=examenD.mostrarExamen();
        util.script("dlgExamen.hide()");
    }
      public void nuevoExamen(){
          examen=new examenC(-1, null, null, null, 1);
      }
      public void seleccionExamen(examenC item){
          examen=item;
          grupoD=new grupoDAO();
          grupoL=grupoD.mostrarGrupo(examen.getExamen());
      }
    
    public void insertarPreguntaRespuesta(){
        examenPreguntaRespuestaD=new examenPreguntaRespuestaDAO();
        examenPreguntaRespuestaD.insertar(new examenPreguntaRespuestaC(1, 1, 1, 0, 0, 0, 1));
    }
    
    
    public void nuevoRespuesta(){
        examenRespuesta=new examenRespuestaC(-1, null, 1);
    }
    
    
    public void mostrarRepuesta(){
        examenRespuestaD=new examenRespuestaDAO();
        examenRespuestaL=examenRespuestaD.mostrarRespuesta(1, 1, 1);
    }

    /**
     * @return the examenRespuesta
     */
    public examenRespuestaC getExamenRespuesta() {
        return examenRespuesta;
    }

    /**
     * @param examenRespuesta the examenRespuesta to set
     */
    public void setExamenRespuesta(examenRespuestaC examenRespuesta) {
        this.examenRespuesta = examenRespuesta;
    }

    /**
     * @return the examenRespuestaL
     */
    public List<examenRespuestaC> getExamenRespuestaL() {
        return examenRespuestaL;
    }

    /**
     * @param examenRespuestaL the examenRespuestaL to set
     */
    public void setExamenRespuestaL(List<examenRespuestaC> examenRespuestaL) {
        this.examenRespuestaL = examenRespuestaL;
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
     * @return the grupoL
     */
    public List<grupoC> getGrupoL() {
        return grupoL;
    }

    /**
     * @param grupoL the grupoL to set
     */
    public void setGrupoL(List<grupoC> grupoL) {
        this.grupoL = grupoL;
    }
}
