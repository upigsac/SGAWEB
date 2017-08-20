
package Beans;

import DAO.oficinaDAO;
import DAO.tramBandejaEntradaDAO;


import DAO.tramTramitePersonaDAO;

import DAO.tramTramiteSeguimientoDAO;
import ENTIDAD.oficinaC;

import ENTIDAD.tramTramitePersonaC;

import ENTIDAD.tramTramiteSeguimientoC;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;





@ManagedBean(name="tramTramiteBandejaB")
@ViewScoped
public class tramTramiteBandeja implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date fechaFin=util.fechaHoy();
    private Date fechaInicio=util.dateAdd(fechaFin, 2, -1);
    
    
    private List<tramTramiteSeguimientoC> tramTramiteSeguimientoL;
    private List<tramTramiteSeguimientoC> tramTramiteSeguimientoFlujoL;
    private tramTramitePersonaC tramTramitePersona;
    
    private tramTramiteSeguimientoC tramTramiteSeguimiento=new tramTramiteSeguimientoC();

    
  
    private List<detalleBandeja> tramBandejaDetalleL;
    private detalleBandeja tramBandejaDetalle=null;

    private String filtroEstado="69";
    private int totalPedientes;
    private int totalFinalizados;
    private List<oficinaC> oficinaL=new ArrayList<>();
    private List<oficinaC> personalOficinaL=new ArrayList<>();
    

    
    

	private int oficina=0;
    private String persona;
    private String expediente="";


    




public tramTramiteBandeja() {
        
    }
    
    public void load(String persona,String personal){
    	
    	this.persona=persona;
    	personalOficinaL=new oficinaDAO().mostrarOficinaPersonal("%", personal);
    	if(personalOficinaL.size()!=0){
    		oficina=personalOficinaL.get(0).getOficina();
    	}
    	
    	mostrarBandejaEntrada();
    }
    
    
   
    
    
    public List<tramTramiteSeguimientoC> mostrarbandeja(String expediente ){

        tramTramiteSeguimientoL=new tramTramiteSeguimientoDAO().mostrarTramiteSeguimiento(expediente, ""+oficina, persona,fechaInicio,fechaFin);
        return tramTramiteSeguimientoL;
    }
    
     public void mostrarBandejaEntrada(){
             
        
        tramBandejaDetalleL=new tramBandejaEntradaDAO().mostrarTramiteSeguimiento(""+oficina, fechaInicio,fechaFin,filtroEstado);
        totalFinalizados=0;
        totalPedientes=0;
         for (detalleBandeja item : tramBandejaDetalleL) {
             if (item.estadoTramite==1){
                 totalPedientes++;
             }else{
                 totalFinalizados++;
             }
         }
        
        
        
        
    }
    
 
    
    public void mostraFlujoTramite(String expediente ){
        
      
        tramTramiteSeguimientoFlujoL=new tramTramiteSeguimientoDAO().mostrarTramiteSeguimiento(expediente);
        util.script("dlgFlujo.show()");
        
    }
    
    public void leer(detalleBandeja  item,int indice){ 
    	 tramBandejaDetalle=item;
    	 expediente=item.expediente;
        tramTramitePersona=new tramTramitePersonaDAO().mostrarTramitePersona(item.expediente);
        if(item.estadoCorreo == 69){ // SIN LEER
        	tramTramiteSeguimientoC registro= new tramTramiteSeguimientoDAO().mostrarTramiteSeguimiento(item.expediente, item.oficina, item.item);
            registro.setEstadoRegistro(70);
            registro.setUsuarioReceptor(persona);
            new tramTramiteSeguimientoDAO().insertar(registro );
            tramBandejaDetalleL.get(indice).estadoCorreo=70;
        }
        
             
        util.script("$('#pnDialogo').css('display','block');");

    }
   
    
    public void nuevoReenviar(detalleBandeja  item){
    	tramTramiteSeguimiento=new tramTramiteSeguimientoC(item.getExpediente(), -1, util.fechaHoy() , util.HoraHoy(), persona, null, null, null, null, 69);
    	//tramTramiteSeguimiento=new tramTramiteSeguimientoDAO().mostrarTramiteSeguimientoExpedienteOficina(item.getExpediente(), oficina);
    	
        oficinaL=new oficinaDAO().mostrarOficinaTramite(1, item.getExpediente(),0) ;
        util.script("dlgReenviar.show()");
    }
    public void reenviar(){
        
        util.alert("se envio correctamente");
        
        int item =new tramTramiteSeguimientoDAO().mostrarTramiteSeguimientoExpedienteOficina(tramTramiteSeguimiento.getExpediente(), tramTramiteSeguimiento.getOficina()).getItem();
        tramTramiteSeguimiento.setItem(item);
        new tramTramiteSeguimientoDAO().insertar(tramTramiteSeguimiento);
       
        
        util.script("dlgReenviar.hide()");
    }
   
  
    public void finalizarTramite(String expediente){
    	tramTramitePersona=new tramTramitePersonaDAO().mostrarTramitePersona(expediente);
        tramTramitePersona.setEstadoRegistro(23);
    
        new tramTramitePersonaDAO().insertar(tramTramitePersona);
        util.alert("EL TRAMITE SE FINALIZO");
    }

   
    public List<detalleBandeja> getTramBandejaDetalleL() {
        return tramBandejaDetalleL;
    }

   
    public void setTramBandejaDetalleL(List<detalleBandeja> tramBandejaDetalleL) {
        this.tramBandejaDetalleL = tramBandejaDetalleL;
    }


   
    public detalleBandeja getTramBandejaDetalle() {
        return tramBandejaDetalle;
    }

    
    public void setTramBandejaDetalle(detalleBandeja tramBandejaDetalle) {
        this.tramBandejaDetalle = tramBandejaDetalle;
    }

   
  
   
    public String getFiltroEstado() {
        return filtroEstado;
    }

   
    public void setFiltroEstado(String filtroEstado) {
        this.filtroEstado = filtroEstado;
    }

   
    public int getTotalPedientes() {
        return totalPedientes;
    }

   
    public void setTotalPedientes(int totalPedientes) {
        this.totalPedientes = totalPedientes;
    }

    
    public int getTotalFinalizados() {
        return totalFinalizados;
    }

    
    public void setTotalFinalizados(int totalFinalizados) {
        this.totalFinalizados = totalFinalizados;
    }
    
    
    public static class detalleBandeja{
        private String expediente;
        private int tipoPersona;
        private int tipoTramite;
        private String desTipoTramite;
        private int tramite;
        private String desTramite;
        private String persona_empresa;
        private String desPersona;
        private String alumno;
        private String observacion;
        private int importancia;
        private int oficina;
        private int item;
        private String desOficina;
        private Date fechaEntrega;
        private Date HoraEntrega;
        private String asunto;
        private String usuarioEmisor;
        private int estadoCorreo;
        private int estadoTramite;

     
        public String getExpediente() {
            return expediente;
        }

       
        public void setExpediente(String expediente) {
            this.expediente = expediente;
        }

       

        public int getTipoTramite() {
            return tipoTramite;
        }

     
        public void setTipoTramite(int tipoTramite) {
            this.tipoTramite = tipoTramite;
        }

      
        public String getDesTipoTramite() {
            return desTipoTramite;
        }

      
        public void setDesTipoTramite(String desTipoTramite) {
            this.desTipoTramite = desTipoTramite;
        }

       
        public int getTramite() {
            return tramite;
        }

       
        public void setTramite(int tramite) {
            this.tramite = tramite;
        }

        
        public String getDesTramite() {
            return desTramite;
        }

       
        public void setDesTramite(String desTramite) {
            this.desTramite = desTramite;
        }

       

        public String getAlumno() {
            return alumno;
        }

        
        public void setAlumno(String alumno) {
            this.alumno = alumno;
        }

      

       
        public int getImportancia() {
            return importancia;
        }

        public void setImportancia(int importancia) {
            this.importancia = importancia;
        }

        
        public int getOficina() {
            return oficina;
        }

       
        public void setOficina(int oficina) {
            this.oficina = oficina;
        }

     

      
        public String getAsunto() {
            return asunto;
        }

       
        public void setAsunto(String asunto) {
            this.asunto = asunto;
        }

       
        public int getEstadoCorreo() {
            return estadoCorreo;
        }

      
        public void setEstadoCorreo(int estadoCorreo) {
            this.estadoCorreo = estadoCorreo;
        }

        
        public int getEstadoTramite() {
            return estadoTramite;
        }

       
        public void setEstadoTramite(int estadoTramite) {
            this.estadoTramite = estadoTramite;
        }

       
        public String getObservacion() {
            return observacion;
        }

        
        public void setObservacion(String observacion) {
            this.observacion = observacion;
        }

       
        public Date getFechaEntrega() {
            return fechaEntrega;
        }

       
        public void setFechaEntrega(Date fechaEntrega) {
            this.fechaEntrega = fechaEntrega;
        }

       
        public Date getHoraEntrega() {
            return HoraEntrega;
        }

        
        public void setHoraEntrega(Date HoraEntrega) {
            this.HoraEntrega = HoraEntrega;
        }

        
        public int getItem() {
            return item;
        }

       
        public void setItem(int item) {
            this.item = item;
        }

        
        public String getPersona_empresa() {
            return persona_empresa;
        }

       
        public void setPersona_empresa(String persona_empresa) {
            this.persona_empresa = persona_empresa;
        }

       
        public int getTipoPersona() {
            return tipoPersona;
        }

       
        public void setTipoPersona(int tipoPersona) {
            this.tipoPersona = tipoPersona;
        }

      
        public String getDesOficina() {
            return desOficina;
        }

        
        public void setDesOficina(String desOficina) {
            this.desOficina = desOficina;
        }

       
        public String getDesPersona() {
            return desPersona;
        }

       
        public void setDesPersona(String desPersona) {
            this.desPersona = desPersona;
        }

       
        public String getUsuarioEmisor() {
            return usuarioEmisor;
        }

        
        public void setUsuarioEmisor(String usuarioEmisor) {
            this.usuarioEmisor = usuarioEmisor;
        }
        
        
        
    }
    

   
    public List<tramTramiteSeguimientoC> getTramTramiteSeguimientoL() {
        return tramTramiteSeguimientoL;
    }

    
    public void setTramTramiteSeguimientoL(List<tramTramiteSeguimientoC> tramTramiteSeguimientoL) {
        this.tramTramiteSeguimientoL = tramTramiteSeguimientoL;
    }

   
    public tramTramiteSeguimientoC getTramTramiteSeguimiento() {
        return tramTramiteSeguimiento;
    }

  
    public void setTramTramiteSeguimiento(tramTramiteSeguimientoC tramTramiteSeguimiento) {
        this.tramTramiteSeguimiento = tramTramiteSeguimiento;
    }

   
    public List<tramTramiteSeguimientoC> getTramTramiteSeguimientoFlujoL() {
        return tramTramiteSeguimientoFlujoL;
    }

   
    public void setTramTramiteSeguimientoFlujoL(List<tramTramiteSeguimientoC> tramTramiteSeguimientoFlujoL) {
        this.tramTramiteSeguimientoFlujoL = tramTramiteSeguimientoFlujoL;
    }

   
    public tramTramitePersonaC getTramTramitePersona() {
        return tramTramitePersona;
    }

    
    public void setTramTramitePersona(tramTramitePersonaC tramTramitePersona) {
        this.tramTramitePersona = tramTramitePersona;
    }


  
   

    public Date getFechaInicio() {
        return fechaInicio;
    }

   
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    
    public Date getFechaFin()  {
        
        return fechaFin;
    }

   
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    public List<oficinaC> getOficinaL() {
		return oficinaL;
	}
	public void setOficinaL(List<oficinaC> oficinaL) {
		this.oficinaL = oficinaL;
	}

    
  public String getExpediente() {
		return expediente;
	}

	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}
	
    public List<oficinaC> getPersonalOficinaL() {
		return personalOficinaL;
	}

	public void setPersonalOficinaL(List<oficinaC> personalOficinaL) {
		this.personalOficinaL = personalOficinaL;
	}
	

public int getOficina() {
		return oficina;
	}

	public void setOficina(int oficina) {
		this.oficina = oficina;
	}

}
