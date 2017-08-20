
package Beans;

import DAO.tipoContratoDAO;
import DAO.tipoContratoPersonalDAO;
import ENTIDAD.tipoContratoC;
import ENTIDAD.tipoContratoPersonalC;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "tipoContratoB")
@ViewScoped
public class tipoContrato implements Serializable {
private static final long serialVersionUID = 1L;




private List<tipoContratoC> tipoContratoL=new ArrayList<>();
private List<tipoContratoC> tipoContratoPersonalL=new ArrayList<>();
private tipoContratoC tipoContrato;
private tipoContratoPersonalC tipoContratoPersonal; 
private int tipoPersonal=3;
private boolean bandera;
private boolean banderaTipoPersonal;









public tipoContrato() {
	
	tipoContratoL = new  tipoContratoDAO().mostrarTipoContrato();
	mostrarTipoContratoPersonal();
}
    

public void nuevoTipoContrato(){
	tipoContrato=new tipoContratoC();
	bandera=true;
	util.script("$('#txtDescripcion').focus();");
}

public void mostrarTipoContratoPersonal(){
	tipoContratoPersonalL = new  tipoContratoDAO().mostrarTipoContratoTipoPersona(tipoPersonal);
}

public void editarTipoContrato(){
	
	bandera=true;
	
}

public void cancelarTipoContrato(){
	
	bandera=false;
	
}

public void nuevoTipoContratoPersona() {
	tipoContratoPersonal=new tipoContratoPersonalC(0, tipoPersonal, null,null, null, 1);
	seleccionaTipoPersonal();
	banderaTipoPersonal=true;
	
	util.script("dlgTipoContratoPersona.show()");
}
public void editarTipoContratoPersona(tipoContratoC item) {
	
	tipoContratoPersonal=new tipoContratoPersonalDAO().mostrarTipoContratoPersonal(tipoPersonal, item.getTipoContrato());
	banderaTipoPersonal=false;
	util.script("dlgTipoContratoPersona.show()");
}

public void seleccionaTipoPersonal(){
	tipoContratoL=new  tipoContratoDAO().mostrarTipoContratoNopTipoPersona(tipoContratoPersonal.getTipoPersona());
}

public void insertarTipoContratoPersona() {
	new tipoContratoPersonalDAO().insertar(tipoContratoPersonal);
	tipoContratoPersonalL = new  tipoContratoDAO().mostrarTipoContratoTipoPersona(tipoPersonal);
	util.script("dlgTipoContratoPersona.hide()");
}
public void eliminarTipoContratoPersona(tipoContratoC item) {
	new tipoContratoPersonalDAO().eliminar(new tipoContratoPersonalC(item.getTipoContrato(), tipoPersonal,null, null, null, 1));
	tipoContratoPersonalL = new  tipoContratoDAO().mostrarTipoContratoTipoPersona(tipoPersonal);
}




public tipoContratoPersonalC getTipoContratoPersonal() {
	return tipoContratoPersonal;
}


public void setTipoContratoPersonal(tipoContratoPersonalC tipoContratoPersonal) {
	this.tipoContratoPersonal = tipoContratoPersonal;
}


public int getTipoPersonal() {
	return tipoPersonal;
}


public void setTipoPersonal(int tipoPersonal) {
	this.tipoPersonal = tipoPersonal;
}


public List<tipoContratoC> getTipoContratoPersonalL() {
	return tipoContratoPersonalL;
}


public void setTipoContratoPersonalL(List<tipoContratoC> tipoContratoPersonalL) {
	this.tipoContratoPersonalL = tipoContratoPersonalL;
}


public void insertarTipoContrato(){
	new tipoContratoDAO().insertar(tipoContrato);
	tipoContratoL = new  tipoContratoDAO().mostrarTipoContrato();
	bandera=false;
	
}

public void eliminarTipoContrato(tipoContratoC item){
	new tipoContratoDAO().eliminar(item);
	tipoContratoL = new  tipoContratoDAO().mostrarTipoContrato();
	
	
}


    public List<tipoContratoC> mostrarTipoContrato() {
        
        tipoContratoL = new  tipoContratoDAO().mostrarTipoContrato();
        return tipoContratoL;
    }
    public List<tipoContratoC> mostrarTipoContratoTipoPersona(int tipoPersona) {
        
        tipoContratoL = new  tipoContratoDAO().mostrarTipoContratoTipoPersona(tipoPersona);
        return tipoContratoL;
    }
    
     public tipoContratoC mostrarTipoContrato(int contrato) {
        
        tipoContrato = new tipoContratoDAO().mostrarTipoContrato(contrato);
        return tipoContrato;
    }

    
    public List<tipoContratoC> getTipoContratoL() {
        return tipoContratoL;
    }

    public void setTipoContratoL(List<tipoContratoC> tipoContratoL) {
        this.tipoContratoL = tipoContratoL;
    }
    public tipoContratoC getTipoContrato() {
        return tipoContrato;
    }
    public void setTipoContrato(tipoContratoC tipoContrato) {
        this.tipoContrato = tipoContrato;
    }


	public boolean isBandera() {
		return bandera;
	}


	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}
    

public boolean isBanderaTipoPersonal() {
	return banderaTipoPersonal;
}


public void setBanderaTipoPersonal(boolean banderaTipoPersonal) {
	this.banderaTipoPersonal = banderaTipoPersonal;
}

}
