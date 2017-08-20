
package Beans;
import DAO.personalDAO;
import DAO.personalEntregaDAO;
import DAO.personalEntregaMonedaDAO;
import ENTIDAD.personaC;
import ENTIDAD.personalC;
import ENTIDAD.personalEntregaC;
import ENTIDAD.personalEntregaMonedaC;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;


@ManagedBean(name="ingresoMonedaB")
@ViewScoped
public class ingresoMoneda {
    
    private List<detalleMoneda> detalleMonedaL;
    private personaC persona ;
    private personalC personal;
    private personalEntregaC personalEntrega=new personalEntregaC();
    private List<personalEntregaMonedaC> personalEntregaMonedaL=new ArrayList<>();
    private boolean bandera;
    private Date fecha=util.fechaHoy();
    private String nombresCompledos;
    
    private double totalGeneral;
    private int totalCantidad;
    
    
    @ManagedProperty(value = "#{usuarioB}")
    private usuario global;
    
    
    @PostConstruct
    public void init() {
        
      persona=global.getPersona();
      personal=global.getPersonal();
      nombresCompledos=persona.nombreCompletos();
      personalEntregaD=new personalEntregaDAO();
     personalEntrega=personalEntregaD.mostrarPersonalEntrega(personal.getPersonal(), Integer.parseInt(util.convertDate(fecha, "yyyy")),Integer.parseInt(util.convertDate(fecha, "MM")), Integer.parseInt(util.convertDate(fecha, "dd")));
         
         personalEntregaMonedaD=new personalEntregaMonedaDAO();
         detalleMonedaL=personalEntregaMonedaD.mostrarMonedas(personal.getPersonal(),Integer.parseInt(util.convertDate(fecha, "yyyy")),Integer.parseInt(util.convertDate(fecha, "MM")),Integer.parseInt(util.convertDate(fecha, "dd")));   
         totales();
      
    }
   public ingresoMoneda() {
             
    }
    public void nuevo(){
        bandera=true;
        personalEntrega=new personalEntregaC();
        personalEntrega.setPersonal(personal.getPersonal());
        personalEntrega.setAno(Integer.parseInt(util.convertDate(fecha, "yyyy"))  );
        personalEntrega.setMes(Integer.parseInt(util.convertDate(fecha, "MM")));
        personalEntrega.setDia(Integer.parseInt(util.convertDate(fecha, "dd")));
    }
    public void guardar(){
        personalEntregaMonedaL=new ArrayList<>();
        for (detalleMoneda item : detalleMonedaL) {
            personalEntregaMonedaL.add(new personalEntregaMonedaC(personalEntrega.getPersonal(), personalEntrega.getAno(), personalEntrega.getMes(), personalEntrega.getDia(), item.dinero , item.cantidad, 1));
            
        }
        
        personalEntregaD=new personalEntregaDAO();
        personalEntregaD.insertar(personalEntrega, personalEntregaMonedaL);
        
        
         personalEntregaMonedaD=new personalEntregaMonedaDAO();
         detalleMonedaL=personalEntregaMonedaD.mostrarMonedas(personal.getPersonal(),Integer.parseInt(util.convertDate(fecha, "yyyy")),Integer.parseInt(util.convertDate(fecha, "MM")),Integer.parseInt(util.convertDate(fecha, "dd")));   
        
        bandera=false;
        util.alert("SE GUARDO CORRECTAMENTE");
    }
    public void cancelar(){
        bandera=false;
    }

  

    
     public void onRowSelect(SelectEvent event) {
         
        String codPersona= (((personaC) event.getObject()).getPersona());
        nombresCompledos=(((personaC) event.getObject()).nombreCompletos());
        util.consolaCliente(codPersona);
        personalD=new personalDAO();
        personal=personalD.BuscaPersonaCodigo(codPersona);
        personalEntregaD=new personalEntregaDAO();
        
        personalEntrega=personalEntregaD.mostrarPersonalEntrega(personal.getPersonal(), fecha.getYear(), fecha.getMonth(), fecha.getDay());
        if (personalEntrega == null){
            personalEntrega=new personalEntregaC();
            personalEntrega.setPersonal(personal.getPersonal());
        }else{
            personalEntregaMonedaD=new personalEntregaMonedaDAO();
            detalleMonedaL=personalEntregaMonedaD.mostrarMonedas(personal.getPersonal(),Integer.parseInt(util.convertDate(fecha, "yyyy")),Integer.parseInt(util.convertDate(fecha, "MM")),Integer.parseInt(util.convertDate(fecha, "dd")));   
            totales();
        }
        
    }
    
     public void totales(){
         totalCantidad=0;totalGeneral=0;
         for (detalleMoneda moneda : detalleMonedaL) {
             totalCantidad+= moneda.cantidad;
             totalGeneral+= moneda.total;
         }
     }
     public void buscarFecha(){
         personalEntregaD=new personalEntregaDAO();
         personalEntrega=personalEntregaD.mostrarPersonalEntrega(personal.getPersonal(), Integer.parseInt(util.convertDate(fecha, "yyyy")),Integer.parseInt(util.convertDate(fecha, "MM")), Integer.parseInt(util.convertDate(fecha, "dd")));
         
         personalEntregaMonedaD=new personalEntregaMonedaDAO();
         detalleMonedaL=personalEntregaMonedaD.mostrarMonedas(personal.getPersonal(),Integer.parseInt(util.convertDate(fecha, "yyyy")),Integer.parseInt(util.convertDate(fecha, "MM")),Integer.parseInt(util.convertDate(fecha, "dd")));   
         totales();
     }
     
     
    personalEntregaMonedaDAO personalEntregaMonedaD;
    personalEntregaDAO personalEntregaD;
    personalDAO personalD;
   
    public List<detalleMoneda> getDetalleMonedaL() {
        return detalleMonedaL;
    }
    public void setDetalleMonedaL(List<detalleMoneda> detalleMonedaL) {
        this.detalleMonedaL = detalleMonedaL;
    }
    public personaC getPersona() {
        return persona;
    }
    public void setPersona(personaC persona) {
        this.persona = persona;
    }
    public personalEntregaC getPersonalEntrega() {
        return personalEntrega;
    }
    public void setPersonalEntrega(personalEntregaC personalEntrega) {
        this.personalEntrega = personalEntrega;
    }
    public boolean isBandera() {
        return bandera;
    }
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }
    public List<personalEntregaMonedaC> getPersonalEntregaMonedaL() {
        return personalEntregaMonedaL;
    }
    public void setPersonalEntregaMonedaL(List<personalEntregaMonedaC> personalEntregaMonedaL) {
        this.personalEntregaMonedaL = personalEntregaMonedaL;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public personalC getPersonal() {
        return personal;
    }
    public void setPersonal(personalC personal) {
        this.personal = personal;
    }
    public String getNombresCompledos() {
        return nombresCompledos;
    }
    public void setNombresCompledos(String nombresCompledos) {
        this.nombresCompledos = nombresCompledos;
    }
    public double getTotalGeneral() {
        return totalGeneral;
    }
    public void setTotalGeneral(double totalGeneral) {
        this.totalGeneral = totalGeneral;
    }
    public int getTotalCantidad() {
        return totalCantidad;
    }
    public void setTotalCantidad(int totalCantidad) {
        this.totalCantidad = totalCantidad;
    }
    public usuario getGlobal() {
        return global;
    }
    public void setGlobal(usuario global) {
        this.global = global;
    }

 

  
    
    public static class detalleMoneda{
        private String dinero;
        private int tipoDinero;
        private String desDinero;
        private int cantidad;
        private double total;

        public String getDinero() {
            return dinero;
        }

        public void setDinero(String dinero) {
            this.dinero = dinero;
        }

        public String getDesDinero() {
            return desDinero;
        }

        public void setDesDinero(String desDinero) {
            this.desDinero = desDinero;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public int getTipoDinero() {
            return tipoDinero;
        }

        public void setTipoDinero(int tipoDinero) {
            this.tipoDinero = tipoDinero;
        }
    }
}
