package Beans;

import DAO.detallePagoPersonalDAO;
import DAO.serieDAO;
import ENTIDAD.detallePagoPersonalC;
import ENTIDAD.serieC;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



@ManagedBean(name="detallePagoPersonalB")
@ViewScoped
public class detallePagoPersonal {
    private List<detallePagoPersonalC> detallePagoPersonalL;
    private List<detallePagoPersonalC> detallePagoPersonalLD;
    private detallePagoPersonalC detallePagoPersonal=new detallePagoPersonalC();
    private pagoPersonal  pagoPersonal;
    private pagoPersonal  pagoPersonalN;
    private serieC serie=new serieC();
    private List<pagoPersonal>  pagoPersonalL;
    private int index=1;
    private String busqueda;
    private boolean bandera;
    private Date busFecha=util.fechaHoy();

    public detallePagoPersonal() {
        detallePagoPersonalD=new detallePagoPersonalDAO();
        pagoPersonal=detallePagoPersonalD.mostrarPagoPersonal(index);
        
        
        detallePagoPersonalD=new detallePagoPersonalDAO();
        detallePagoPersonalL=detallePagoPersonalD.mostrarDetallePagoPersonal(pagoPersonal.codigo);
        
        
        detallePagoPersonalLD=detallePagoPersonalD.mostrarDetallePagoPersonalDiario(busFecha);
        
    }
    
    
    public pagoPersonal mostrarPersonal(String personal){
        detallePagoPersonalD=new detallePagoPersonalDAO();
        
        return detallePagoPersonalD.mostrarPagoPersonalCodigo(personal);
    }
    
    public void mostarDetalleDiario(){
        detallePagoPersonalD=new detallePagoPersonalDAO();
        detallePagoPersonalLD=detallePagoPersonalD.mostrarDetallePagoPersonalDiario(busFecha);
    }
    
    
    public void seleccionarBusqueda(){
        index=pagoPersonal.index;
        detallePagoPersonalD=new detallePagoPersonalDAO();
        detallePagoPersonalL=detallePagoPersonalD.mostrarDetallePagoPersonal(pagoPersonal.codigo);
    }
    
    public void nuevoBusqueda(){
        busqueda="";
        pagoPersonalL=new ArrayList<>();
    
    }
    public void nuevoPersonal(){
        pagoPersonalN=new pagoPersonal();
        pagoPersonalN.setEstadoRegistro(1);
    }
    public void nuevo(){
        
        
        serieD=new serieDAO();
        serie=serieD.mostrarSerie(43);
        
        //-------------------------
        detallePagoPersonal=new detallePagoPersonalC();
        detallePagoPersonal.setPersonal(pagoPersonal.codigo);
        detallePagoPersonal.setFechaPago(util.fechaHoy());
        detallePagoPersonal.setNumeroComprobante("" +(serie.getNumeroActual()+1));
        detallePagoPersonal.setMes(util.listaMeses.get(util.mesHoy()) );
        detallePagoPersonal.setEstadoRegistro(1);
        
        bandera=true;
        
                
                
    }
    public void insertar(){       
        
        
        
        detallePagoPersonalD=new detallePagoPersonalDAO();
        if (bandera){            
            serie.setNumeroActual(Integer.parseInt(detallePagoPersonal.getNumeroComprobante()));
            detallePagoPersonalD.insertar(detallePagoPersonal,serie);
            detallePagoPersonalD=new detallePagoPersonalDAO();
            detallePagoPersonalL=detallePagoPersonalD.mostrarDetallePagoPersonal(pagoPersonal.codigo);
        }else{
            detallePagoPersonalD.insertar(detallePagoPersonal);
            detallePagoPersonalD=new detallePagoPersonalDAO();
            detallePagoPersonalL=detallePagoPersonalD.mostrarDetallePagoPersonal(pagoPersonal.codigo);
        }
        util.script("dlgMatenimiento.hide()");
        
           
    }
    public void cancelar(){
        
    }
    public void eliminar(){
       detallePagoPersonal.setEstadoRegistro(0);
       bandera=false;
        insertar();
    }
    
    public void anular(){
    detallePagoPersonal.setEstadoRegistro(23);
    bandera=false;
    insertar();
    }
    
    public void espejo(){
        detallePagoPersonal.setMontoPago(detallePagoPersonal.getMontoTotal());
    }
    
    public void editar(){
        util.consolaCliente(""+ detallePagoPersonal.getPersonal());
         util.consolaCliente(""+ detallePagoPersonal.getCodigo());
         bandera=false;
    }
    public void siguiente(){
        index++;
        detallePagoPersonalD=new detallePagoPersonalDAO();
        pagoPersonal=detallePagoPersonalD.mostrarPagoPersonal(index);
        
        
        detallePagoPersonalD=new detallePagoPersonalDAO();
        detallePagoPersonalL=detallePagoPersonalD.mostrarDetallePagoPersonal(pagoPersonal.codigo);
        
    }
    public void anterior(){
        if (index!=1){
            index--;
        }
        
        detallePagoPersonalD=new detallePagoPersonalDAO();
        pagoPersonal=detallePagoPersonalD.mostrarPagoPersonal(index);        
        detallePagoPersonalD=new detallePagoPersonalDAO();
        detallePagoPersonalL=detallePagoPersonalD.mostrarDetallePagoPersonal(pagoPersonal.codigo);
        
    }
    
    public void buscarPersonal(){
        detallePagoPersonalD=new detallePagoPersonalDAO();
        pagoPersonalL=detallePagoPersonalD.mostrarFiltroPagoPersonal("%" + busqueda.concat("%"));
    }
    
    
    public void insertarPersonal(){
    detallePagoPersonalD=new detallePagoPersonalDAO();
    detallePagoPersonalD.insertarPersonal(pagoPersonalN);
  //  pagoPersonal=pagoPersonalN;
    
    util.script("dlgPersonal.hide()");
}
    
    detallePagoPersonalDAO detallePagoPersonalD;
    serieDAO serieD;
   
    public pagoPersonal getPagoPersonal() {
        return pagoPersonal;
    }

    public void setPagoPersonal(pagoPersonal pagoPersonal) {
        this.pagoPersonal = pagoPersonal;
    }

    public List<pagoPersonal> getPagoPersonalL() {
        return pagoPersonalL;
    }

    public void setPagoPersonalL(List<pagoPersonal> pagoPersonalL) {
        this.pagoPersonalL = pagoPersonalL;
    }

    
    public int getIndex() {
        return index;
    }

   
    public void setIndex(int index) {
        this.index = index;
    }
    public String getBusqueda() {
        return busqueda;
    }
    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
    public serieC getSerie() {
        return serie;
    }
    public void setSerie(serieC serie) {
        this.serie = serie;
    }
    public boolean isBandera() {
        return bandera;
    }
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }
    public pagoPersonal getPagoPersonalN() {
        return pagoPersonalN;
    }
    public void setPagoPersonalN(pagoPersonal pagoPersonalN) {
        this.pagoPersonalN = pagoPersonalN;
    }
    public List<detallePagoPersonalC> getDetallePagoPersonalLD() {
        return detallePagoPersonalLD;
    }
    public void setDetallePagoPersonalLD(List<detallePagoPersonalC> detallePagoPersonalLD) {
        this.detallePagoPersonalLD = detallePagoPersonalLD;
    }
    public Date getBusFecha() {
        return busFecha;
    }
    public void setBusFecha(Date busFecha) {
        this.busFecha = busFecha;
    }
    
    
    public static class pagoPersonal{
        private String codigo;
        private String apellidos;
        private String nombres;
        private int index;
        private int estadoRegistro;

        public String getCodigo() {
            return codigo;
        }
        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
        public int getEstadoRegistro() {
            return estadoRegistro;
        }
        public void setEstadoRegistro(int estadoRegistro) {
            this.estadoRegistro = estadoRegistro;
        }
        public String getApellidos() {
            return apellidos;
        }
        public void setApellidos(String apellidos) {
            this.apellidos = apellidos;
        }
        public String getNombres() {
            return nombres;
        }
        public void setNombres(String nombres) {
            this.nombres = nombres;
        }
                
    }
    
    public List<detallePagoPersonalC> getDetallePagoPersonalL() {
        return detallePagoPersonalL;
    }

    public void setDetallePagoPersonalL(List<detallePagoPersonalC> detallePagoPersonalL) {
        this.detallePagoPersonalL = detallePagoPersonalL;
    }

    public detallePagoPersonalC getDetallePagoPersonal() {
        return detallePagoPersonal;
    }

    public void setDetallePagoPersonal(detallePagoPersonalC detallePagoPersonal) {
        this.detallePagoPersonal = detallePagoPersonal;
    }
}
