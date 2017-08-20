
package Beans;

import DAO.personalRutaCaptacionDAO;
import ENTIDAD.personalRutaCaptacionC;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

//import org.primefaces.event.map.GeocodeEvent;





import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.StateChangeEvent;

import org.primefaces.model.map.DefaultMapModel;
//import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean(name="rutaCaptacionB")
@ViewScoped
public class rutaCaptacion {
      private MapModel draggableModel;
      private Marker marker;
      private List<personalRutaCaptacionC> personalRutaCaptacionL=new ArrayList<>();
      private personalRutaCaptacionC personalRutaCaptacion=new personalRutaCaptacionC();
      private boolean bandera;
      private boolean banderaBusqueda;
      private String centerGeoMap = "-12.159046547857223, -76.99922242265626";
      private int zoon=11;
      private String busqueda;
      String fila[];
      personalRutaCaptacionDAO personalRutaCaptacionD;
      
      /* TIPO DE MAPAS 
      -ROADMAP
      -HYBRID
      
      
      */
  
    
    

    
     public void seleccionPersonal() {
        
        
        draggableModel = new DefaultMapModel();
        personalRutaCaptacionD=new personalRutaCaptacionDAO();        
        personalRutaCaptacionL=personalRutaCaptacionD.mostrarPersonalRutaCaptacion(util.fechaHoy());        
        
        for (personalRutaCaptacionC item : personalRutaCaptacionL) {            
            LatLng coord;
            if (item.getEstadoRegistro()==1){
                 coord = new LatLng(item.getLatitud(), item.getLongitud());           
            }else{
                 coord = new LatLng(-12.159046547857223, -76.99922242265626);           
            }        
            String icono=(personalRutaCaptacion.getPersonal().endsWith(item.getPersonal())?"":"http://maps.google.com/mapfiles/ms/micons/blue-dot.png");
            draggableModel.addOverlay(new Marker(coord, item.getPersonal(),item.getEstadoRegistro(),icono));
        }
        
        
         for(Marker item : draggableModel.getMarkers()) {
             
             //if (item.getData().equals("1")){
                 item.setDraggable(true);
             //}
            
        }
        
    }
     public void mostrarRutaPersonal(Date fecha,String personal){
        draggableModel = new DefaultMapModel();
        personalRutaCaptacionD=new personalRutaCaptacionDAO();        
        personalRutaCaptacionL=personalRutaCaptacionD.mostrarListaPersonalRutaCaptacion(fecha,personal);        
        
        for (personalRutaCaptacionC item : personalRutaCaptacionL) {            
            LatLng coord;
         
            coord = new LatLng(item.getLatitud(), item.getLongitud());           
            String icono="http://maps.google.com/mapfiles/ms/micons/yellow-dot.png";
            draggableModel.addOverlay(new Marker(coord, item.getPersonal(),item.getPersonal() +"-"+ item.getItem() +"-"+item.getEstadoRegistro(),icono));
        }
        
        
         for(Marker item : draggableModel.getMarkers()) {
             
             //if (item.getData().equals("1")){
                 item.setDraggable(false);
             //}
            
        }
     }
     public void limpiar(){
         centerGeoMap = "-12.159046547857223, -76.99922242265626";
          zoon=11;
     }
     
     
     public void nuevo(Date fecha ,String personal){
         personalRutaCaptacion=new personalRutaCaptacionC();
         personalRutaCaptacion.setInstitucion(1);
         personalRutaCaptacion.setFecha(fecha);
         personalRutaCaptacion.setPersonal(personal);
         personalRutaCaptacion.setEstadoRegistro(1);
         busqueda="";
         bandera=true;
         banderaBusqueda=true;
         
     }
     public void editar(){
         bandera=true;
         marker.setDraggable(true);
         personalRutaCaptacion.setEstadoRegistro(1);
         
     }
     public void cancelar(Date fecha ,String personal){
         bandera=false;         
         banderaBusqueda=false;
         mostrarRutaPersonal(fecha,personal);
         limpiar();
     }
     public void eliminar(Date fecha ,String personal){
         personalRutaCaptacionD=new personalRutaCaptacionDAO();         
         personalRutaCaptacion=personalRutaCaptacionD.mostrarPersonalRutaCaptacion(fecha, personal, fila[1]);         
         personalRutaCaptacion.setEstadoRegistro(0);
         personalRutaCaptacionD.insertar(personalRutaCaptacion);
         mostrarRutaPersonal(fecha,personal);
         util.alert("SE ELIMINO CORRECTAMENTE");
     }
             
     
     public void guardar(Date fecha,String personal){
        
        marker.setDraggable(false);
        marker.setIcon("http://maps.google.com/mapfiles/ms/micons/blue-dot.png");
        personalRutaCaptacion.setLatitud(marker.getLatlng().getLat());
        personalRutaCaptacion.setLongitud(marker.getLatlng().getLng());
        
        
        personalRutaCaptacionD=new personalRutaCaptacionDAO();
        personalRutaCaptacionD.insertar(personalRutaCaptacion);
        
        util.alert("SE INSERTO CORRECTAMENTE");
        mostrarRutaPersonal(fecha,personal);
        bandera=false;
        banderaBusqueda=false;
     }
    
      
      public void onMarkerDrag(MarkerDragEvent event) {
        marker = event.getMarker();        
        
         
         
    }
       public void onMarkerSelect(OverlaySelectEvent event) {           
           
        marker = (Marker) event.getOverlay(); 
         
         
        
        
        for(Marker item : draggableModel.getMarkers()) {
             String filaT[]=item.getData().toString().split("-");
            if(filaT[1].toString().endsWith("0")){
                item.setIcon("");
            }else{
                item.setIcon("http://maps.google.com/mapfiles/ms/micons/yellow-dot.png");
            }          
             item.setDraggable(false);            
        }
        
        
        
        centerGeoMap=marker.getLatlng().getLat() + "," + marker.getLatlng().getLng();         
        marker.setIcon("http://maps.google.com/mapfiles/ms/micons/blue-dot.png");
        
         fila=marker.getData().toString().split("-");
         personalRutaCaptacion.setItem(Integer.parseInt(fila[1]));        
        
        
        
       
        
        if (!fila[1].endsWith("0")){
            personalRutaCaptacionD=new personalRutaCaptacionDAO();
            personalRutaCaptacion=personalRutaCaptacionD.mostrarPersonalRutaCaptacion(personalRutaCaptacion.getFecha(),personalRutaCaptacion.getPersonal(),fila[1]);
        }else{
            marker.setDraggable(true);
        }
            
        
        util.consolaCliente(marker.getTitle());
       // draggableModel.getMarkers().remove(marker);
        
    }
//public void onGeocode(GeocodeEvent event) {
//          List<GeocodeResult> results = event.getResults();         
//        if (results != null && !results.isEmpty()) {
//            LatLng center = results.get(0).getLatLng();
//            setCenterGeoMap(center.getLat() + "," + center.getLng());             
//            for (int i = 0; i < results.size(); i++) {
//                GeocodeResult result = results.get(i);
//                draggableModel.addOverlay(new Marker(result.getLatLng(), result.getAddress(),"null"+"-"+"0"+"-"+"0"));
//            }
//        }
//    
//    }

public void onStateChange(StateChangeEvent event) {  
        zoon= event.getZoomLevel();
    
}
      

    public MapModel getDraggableModel() {
        return draggableModel;
    }
    public void setDraggableModel(MapModel draggableModel) {
        this.draggableModel = draggableModel;
    }
    public Marker getMarker() {
        return marker;
    }
    public void setMarker(Marker marker) {
        this.marker = marker;
    }
    public List<personalRutaCaptacionC> getPersonalRutaCaptacionL() {
        return personalRutaCaptacionL;
    }
    public void setPersonalRutaCaptacionL(List<personalRutaCaptacionC> personalRutaCaptacionL) {
        this.personalRutaCaptacionL = personalRutaCaptacionL;
    }
    public personalRutaCaptacionC getPersonalRutaCaptacion() {
        return personalRutaCaptacion;
    }
    public void setPersonalRutaCaptacion(personalRutaCaptacionC personalRutaCaptacion) {
        this.personalRutaCaptacion = personalRutaCaptacion;
    }
    public boolean isBandera() {
        return bandera;
    }
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }
    public String getCenterGeoMap() {
        return centerGeoMap;
    }
    public void setCenterGeoMap(String centerGeoMap) {
        this.centerGeoMap = centerGeoMap;
    }
    public int getZoon() {
        return zoon;
    }
    public void setZoon(int zoon) {
        this.zoon = zoon;
    }
    public String getBusqueda() {
        return busqueda;
    }
    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
    public boolean isBanderaBusqueda() {
        return banderaBusqueda;
    }
    public void setBanderaBusqueda(boolean banderaBusqueda) {
        this.banderaBusqueda = banderaBusqueda;
    }

    
}
