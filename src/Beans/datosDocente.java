
package Beans;

import DAO.ubigeoDistritoDAO;
import ENTIDAD.ubigeoDistritoC;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;


@ManagedBean(name="datosDocenteB")

@ViewScoped
public class datosDocente {
      private MapModel draggableModel;
      private Marker marker;

    public datosDocente() {
        draggableModel = new DefaultMapModel();
        LatLng coord1 = new LatLng(-12.159046547857223, -76.99922242265626);           
        
        draggableModel.addOverlay(new Marker(coord1, "MI DIRECCION"));
        
    }
      
    
  
      
      private ubigeoDistritoC ubigeoDistrito;
      
      ubigeoDistritoDAO ubigeoDistritoD;
      public void mostrarMapa(String departamento,String provincia,String distrito){
            ubigeoDistritoD=new ubigeoDistritoDAO();
            ubigeoDistrito=ubigeoDistritoD.mostrarDistrito(departamento, provincia, distrito);          
            draggableModel = new DefaultMapModel();
            
            LatLng coord1 = new LatLng(ubigeoDistrito.getLatitud() , ubigeoDistrito.getLongitud());
            draggableModel.addOverlay(new Marker(coord1, "MI DIRECCION"));
          
          //return draggableModel;
      }
      
    

    /**
     * @return the draggableModel
     */
    public MapModel getDraggableModel() {
        
        return draggableModel;
    }

    /**
     * @param draggableModel the draggableModel to set
     */
    public void setDraggableModel(MapModel draggableModel) {
        this.draggableModel = draggableModel;
    }

    /**
     * @return the marker
     */
    public Marker getMarker() {
        return marker;
    }

    /**
     * @param marker the marker to set
     */
    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    /**
     * @return the ubigeoDistrito
     */
    public ubigeoDistritoC getUbigeoDistrito() {
        return ubigeoDistrito;
    }

    /**
     * @param ubigeoDistrito the ubigeoDistrito to set
     */
    public void setUbigeoDistrito(ubigeoDistritoC ubigeoDistrito) {
        this.ubigeoDistrito = ubigeoDistrito;
    }
}
