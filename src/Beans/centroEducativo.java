
package Beans;

import DAO.centroEducativoDAO;
import ENTIDAD.centroEducativoC;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



@ManagedBean(name = "centroEducativoB")
@ViewScoped
public class centroEducativo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<centroEducativoC> centroEducativoL=new ArrayList<>();
    private centroEducativoC centroEducativo=new centroEducativoC();
    private boolean bandera;
    private String busDescripcion;

    centroEducativoDAO centroEducativoD; 


    
    
    public void nuevo(){
    bandera=true;
    centroEducativo=new centroEducativoC();
    }
    public void cancelar(){
        bandera=false;
    }
    public void guardar(){
        String codigo;
        centroEducativoD=new centroEducativoDAO();
        codigo=centroEducativoD.insertar(centroEducativo);
        centroEducativo.setCentroEducativo(codigo); 
        bandera=false;
    }
    public void editar(){
          bandera=true;
    }
    
    public void buscar(){
           centroEducativoD=new centroEducativoDAO();
           centroEducativoL=centroEducativoD.filtrarCentroEducativo(busDescripcion);
    }
    

    public List<centroEducativoC> mostrarCentroEducativos(int tipo) {

        centroEducativoD = new centroEducativoDAO();
        centroEducativoL = centroEducativoD.mostrarCentroEducativo(tipo);
        return centroEducativoL;
    }
    
    public centroEducativoC mostrarCentroEducativo(String centroEducativo) {

        centroEducativoD = new centroEducativoDAO();
        this.centroEducativo = centroEducativoD.mostrarCentroEducativo(centroEducativo);
        return this.centroEducativo;
    }
    public List<centroEducativoC> mostrarCentroEducativo() {

        centroEducativoD = new centroEducativoDAO();
        centroEducativoL = centroEducativoD.mostrarCentroEducativo();
        return centroEducativoL;
    }


   

  

    /**
     * @return the centroEducativoL
     */
    public List<centroEducativoC> getCentroEducativoL() {
        
        return centroEducativoL;
    }

    /**
     * @param centroEducativoL the centroEducativoL to set
     */
    public void setCentroEducativoL(List<centroEducativoC> centroEducativoL) {
        this.centroEducativoL = centroEducativoL;
    }

    /**
     * @return the centroEducativo
     */
    public centroEducativoC getCentroEducativo() {
        return centroEducativo;
    }

    /**
     * @param centroEducativo the centroEducativo to set
     */
    public void setCentroEducativo(centroEducativoC centroEducativo) {
        this.centroEducativo = centroEducativo;
    }

    /**
     * @return the bandera
     */
    public boolean isBandera() {
        return bandera;
    }

    /**
     * @param bandera the bandera to set
     */
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    /**
     * @return the busDescripcion
     */
    public String getBusDescripcion() {
        return busDescripcion;
    }

    /**
     * @param busDescripcion the busDescripcion to set
     */
    public void setBusDescripcion(String busDescripcion) {
        this.busDescripcion = busDescripcion;
    }
}
