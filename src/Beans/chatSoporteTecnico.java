

package Beans;


import org.primefaces.context.RequestContext;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;


import org.apache.commons.lang3.StringEscapeUtils;

@ManagedBean(name="chatSoporteTecnicoB")
@ViewScoped
public class chatSoporteTecnico{
    private String textoCliente;
    
    
    private final EventBus eventBus = EventBusFactory.getDefault().eventBus();
    
     public void notificar(String canal) {

        String summary = "Nuevo Elemento";
        String detail = "NUEVO USUARIO SE CONECTO";
      
         eventBus.publish(canal, new FacesMessage(StringEscapeUtils.escapeHtml3(summary), StringEscapeUtils.escapeHtml3(detail)));
        
    }
      public void conectarServer(){            
              
        notificar("/{room}/");
//        RequestContext reqcon = RequestContext.getCurrentInstance();
//        reqcon.execute("subscriber.connect('PRINCIPAL')");    
        
    //    RequestContext requestContext = RequestContext.getCurrentInstance();
      ///  requestContext.execute("PF('subscriber').connect('PRINCIPAL')");
         
        
    }
        public void desconectarServer(){
        
        notificar("/CANALCLIENTE");
        
    }
        
      
        public void conectarCliente(){
            System.out.println("----INICIO------");
         RequestContext reqcon = RequestContext.getCurrentInstance();      
            
            reqcon.execute("PF('subscriber').connect('/MICHELL')"); 
       System.out.println("-----FIN-----");
            
            //util.script("PF('subscriber').connect('/MICHELL')");            
            
            //PushContext.push(CANAL + "ALUMNO", textoCliente);
          
//            eventBus.publish(CANAL + "ALUMNO", textoCliente);
            
        }

  
    public String getTextoCliente() {
        return textoCliente;
    }

    public void setTextoCliente(String textoCliente) {
        this.textoCliente = textoCliente;
    }
    
}
