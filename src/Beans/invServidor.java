

package Beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.apache.commons.lang3.StringEscapeUtils;

import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

@ManagedBean(name = "invServidorB")
@ApplicationScoped
//@ViewScoped
public class invServidor {
    private String carrera;
    private String curso;
    private String seccion;
    private int grupo;
    
    
    
    public void sincronizar(){
        notificarPUSH();
    }
    private final static String CHANNEL = "/notify";
       public void notificarPUSH() {

        String summary = "Nuevo Elemento";
        String detail = "Se agrego a la lista";
      //  String CHANNEL = "/index";
/*
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary), StringEscapeUtils.escapeHtml3(detail)));
        */
         EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary), StringEscapeUtils.escapeHtml3(detail)));
        
        
    }
    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    public String getCurso() {
        return curso;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
    public String getSeccion() {
        return seccion;
    }
    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }
    public int getGrupo() {
        return grupo;
    }
    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }
}
