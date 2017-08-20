
package Beans;

import DAO.empresaDAO;
import ENTIDAD.empresaC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="empresaB")
@ViewScoped
public class empresa  {
    private List<empresaC> empresaL;
    private empresaC empresa;
    private empresaC empresaT;
    private String busqueda;
    private boolean bandera;

    public empresa() {
        empresaD=new empresaDAO();
        empresaL=empresaD.mostrarEmpresa();
    }
    

    
    empresaDAO empresaD;
    public void filtroEmpresa(String razonSocial){
        empresaD=new empresaDAO();
        empresaL=empresaD.mostrarFiltroEmpresa(razonSocial);
    }
    
    public void nuevo(){
        empresaT=empresa;
        empresa=new empresaC();
        empresa.setEmpresa("000000000");
        empresa.setEstadoRegistro(1);
        util.script("pnBloqueo.show()");        
        util.script("document.getElementById('frmEmpresa\\:txtRuc').focus()");
        bandera=true;
    }
    public void cancelar(){
        bandera=false;
        empresa=empresaT;
        util.script("pnBloqueo.hide()");
    }
    public void ingresar(){
        empresaD=new empresaDAO();
        empresaD.insertar(empresa);        
        
        empresaL=empresaD.mostrarEmpresa();        
        bandera=false;
        util.script("pnBloqueo.hide()");
    }
    
    
    public void editar(){
        util.script("pnBloqueo.show()");  
        bandera=true;
    }
    
    public void cambiarEstado(empresaC item){
        empresaD=new empresaDAO();
        
        item.setEstadoRegistro(item.getEstadoRegistro()==1?0:1); 
        empresaD.insertar(item);
    }
    public List<empresaC> getEmpresaL() {
        return empresaL;
    }
    public void setEmpresaL(List<empresaC> empresaL) {
        this.empresaL = empresaL;
    }
    public empresaC getEmpresa() {
        return empresa;
    }
    public void setEmpresa(empresaC empresa) {
        this.empresa = empresa;
    }
    public boolean isBandera() {
        return bandera;
    }
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }
    public empresaC getEmpresaT() {
        return empresaT;
    }
    public void setEmpresaT(empresaC empresaT) {
        this.empresaT = empresaT;
    }
    public String getBusqueda() {
        return busqueda;
    }
    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
}
