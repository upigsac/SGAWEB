

package Beans;

import DAO.cargoDAO;
import ENTIDAD.cargoC;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="cargoB")
@ViewScoped
public class cargo implements Serializable {
	
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<cargoC>  cargoL;
    private cargoC  cargo=new cargoC();
    private boolean bandera;
    
    
    cargoDAO cargoD;
    
    
    public void nuevo(){
        cargo=new cargoC();
        bandera=true;
    }
    
    public void cancelar(){
        bandera=false;
    }
    
    public void editar(){
        bandera=true;
    }
    
    public void guardar(){
        int codigo;
        codigo=cargoD.insertarCargo(cargo);
        cargoD=new cargoDAO();
        cargoL=cargoD.mostrarCargo();
        cargo.setCargo(codigo);
        bandera=false;
    }
    
    public List<cargoC> mostrarCargo(){
        cargoD=new cargoDAO();
        cargoL=cargoD.mostrarCargo();
        return cargoL;
    }
    
    public cargoC mostrarCargo(int cargo){
        cargoD=new cargoDAO();
        this.cargo=cargoD.mostrarCargo(cargo);
        return this.cargo;
    }

    /**
     * @return the cargoL
     */
    public List<cargoC> getCargoL() {
        cargoD=new cargoDAO();
        cargoL=cargoD.mostrarCargo();
        return cargoL;
    }

    /**
     * @param cargoL the cargoL to set
     */
    public void setCargoL(List<cargoC> cargoL) {
        this.cargoL = cargoL;
    }

    /**
     * @return the cargo
     */
    public cargoC getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(cargoC cargo) {
        this.cargo = cargo;
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
}
