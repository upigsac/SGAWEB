
package Beans;

import DAO.ocupacionDAO;
import ENTIDAD.ocupacionC;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "ocupacionB")
@ViewScoped
public class ocupacion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ocupacionC ocupacion;
    private List<ocupacionC> ocupacionL;
    private boolean bandera;

    ocupacionDAO ocuapacionD;
    
    
    public void nuevo(){
        ocupacion=new ocupacionC();
        ocupacion.setOcupacion(-1);
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
        ocuapacionD=new ocupacionDAO();
        codigo=ocuapacionD.insertarOcupacion(ocupacion);
        
        ocupacionL=ocuapacionD.mostrarOcupacion();
        ocupacion.setOcupacion(codigo);
        bandera=false;
    }

    public ocupacionC mostrarOcupacion(int codigo) {
        ocuapacionD = new ocupacionDAO();
        ocupacion = ocuapacionD.mostrarOcpacion(codigo);
        return ocupacion;
    }

    public List<ocupacionC> mostrarOcupacion() {
        ocuapacionD = new ocupacionDAO();
        ocupacionL = ocuapacionD.mostrarOcupacion();
        return ocupacionL;
    }
    public ocupacionC getOcupacion() {
        return ocupacion;
    }
    public void setOcupacion(ocupacionC ocupacion) {
        this.ocupacion = ocupacion;
    }
    public List<ocupacionC> getOcupacionL() {
        ocuapacionD=new ocupacionDAO();        
        ocupacionL=ocuapacionD.mostrarOcupacion();
        return ocupacionL;
    }
    public void setOcupacionL(List<ocupacionC> ocupacionL) {
        this.ocupacionL = ocupacionL;
    }
    public boolean isBandera() {
        return bandera;
    }
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

}
