
package Beans;

import DAO.oficinaDAO;
import ENTIDAD.oficinaC;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="oficinaB")
@ViewScoped
public class oficina implements Serializable{
    
	private static final long serialVersionUID = 1L;
	private List<oficinaC> oficinaL;
    private oficinaC oficina;
    private int seleccion;
    
    oficinaDAO oficinaD;
    public List<oficinaC> mostrarOficina(int institucion){
        oficinaD=new oficinaDAO();
        oficinaL=oficinaD.mostrarOficinas(institucion);
        return oficinaL;
    }
     public List<oficinaC> mostrarOficina(){
        oficinaD=new oficinaDAO();
        oficinaL=oficinaD.mostrarOficinas();
        return oficinaL;
    }
     public List<oficinaC> filtroOficina(String institucion,String descripcion){
        oficinaD=new oficinaDAO();
        oficinaL=oficinaD.filtroOficinas(institucion,descripcion);
        return oficinaL;
    }
    
    public List<oficinaC> mostrarOficina(int institucion,int tipoTrabajor){
        oficinaD=new oficinaDAO();
        oficinaL=oficinaD.mostrarOficinas(institucion,tipoTrabajor);
        return oficinaL;
    }
    
    public oficinaC mostrarOficinaCodigo(int institucion,int oficina){
        oficinaD=new oficinaDAO();
        this.oficina=oficinaD.mostrarOficina(institucion,oficina);
        return this.oficina;
    }
    
     public List<oficinaC> mostrarOficinaTramite(int institucion,int tramite,int oficinaEmisor){
        oficinaD=new oficinaDAO();
        oficinaL=oficinaD.mostrarOficinaTramite(institucion,tramite,oficinaEmisor);
        return oficinaL;
    }

   
    public List<oficinaC> getOficinaL() {
        return oficinaL;
    }

   
    public void setOficinaL(List<oficinaC> oficinaL) {
        this.oficinaL = oficinaL;
    }

    
    public oficinaC getOficina() {
        return oficina;
    }

   
    public void setOficina(oficinaC oficina) {
        this.oficina = oficina;
    }

   
    public int getSeleccion() {
        return seleccion;
    }

   
    public void setSeleccion(int seleccion) {
        this.seleccion = seleccion;
    }
    
}
