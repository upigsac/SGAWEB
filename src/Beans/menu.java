package Beans;

import DAO.menuDAO;
import ENTIDAD.menuC;
import ENTIDAD.usuarioC;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.bean.ViewScoped;


@ManagedBean(name = "menuB")
@ViewScoped
public class menu implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{usuarioB}")
    private usuario usuarioB;

    private List<menuC> lista;
    private List<menuC> listaHijos;

    private List<menuC> menuL=new ArrayList<>();
    private List<menuC> menuHijoL=new ArrayList<>();
    private menuC menu;

    menuDAO menuD = null;
    usuarioC usuario = null;

    @PostConstruct
    public void init() {
       // ListarMenu();
          cargarMenu();
    }
    // constructor

   

    public List<menuC> getLista() {
        return lista;
    }

    public void setLista(List<menuC> lista) {
        this.lista = lista;
    }

    
    
    // CARGAR EL MENU 
    public void cargarMenu(){
         menuD = new menuDAO();
         if (usuarioB.getTipo() == 1) {//alumno
            menuL=new ArrayList<>();
           
                       for (menuC itemCabecera : menuD.getModulo(13, 1, "{ALUMNOUPIG}", 0)) {
                        
                        menuHijoL=menuD.getModuloHijo(itemCabecera.getSigu(), itemCabecera.getmPadre(), "{ALUMNOUPIG}");
                          for (menuC itemDetalle : menuHijoL) {
                               itemCabecera.getMenuL().add(itemDetalle);
                          }
                       menuL.add(itemCabecera);
                       }

        } else if (usuarioB.getTipo() == 2) {//docente
            
            menuL=new ArrayList<>();
            
            for (menuC itemCabecera : menuD.getModulo(14, 1, "{DOCENTE}", 0)) {
            
            menuHijoL=menuD.getModuloHijo(itemCabecera.getSigu(), itemCabecera.getmPadre(), "{DOCENTE}");
              for (menuC itemDetalle : menuHijoL) {
                   itemCabecera.getMenuL().add(itemDetalle);
              }
           menuL.add(itemCabecera);
        }
            
            
            
            
            
            
        } else if (usuarioB.getTipo() == 3) {//padre
            menuL=new ArrayList<>();
                  for (menuC itemCabecera : menuD.getModulo(16, 1, "{PADREUPIG}", 0)) {
                        menu=new menuC(itemCabecera.getSigu(), itemCabecera.getId(),itemCabecera.getTipoIcono(),itemCabecera.getIconoTitle(), itemCabecera.getModulo(), itemCabecera.getNombre(), itemCabecera.getImgMod(), itemCabecera.getUrl(), itemCabecera.getUrlvar(), itemCabecera.getTipo(), itemCabecera.getmPadre(), itemCabecera.getOrden(), itemCabecera.getTarget());
                        menuHijoL=menuD.getModuloHijo(itemCabecera.getSigu(), itemCabecera.getmPadre(), "{PADREUPIG}");
                          for (menuC itemDetalle : menuHijoL) {
                               menu.getMenuL().add(itemDetalle);
                          }
                       menuL.add(menu);
                    }
            



        } else if (usuarioB.getTipo() == 4) { // administrativo
            menuL=new ArrayList<>();   
            menuHijoL=new ArrayList<>();
            for (menuC itemCabecera : menuD.getModulo(usuarioB.getInstitucionAcceso().getPrograma(), 1, usuarioB.getUsu(), 0)) {
            menu=itemCabecera;
            menuHijoL=menuD.getModuloHijo(itemCabecera.getSigu(), itemCabecera.getmPadre(), usuarioB.getUsu());
              for (menuC itemDetalle : menuHijoL) {
                   menu.getMenuL().add(itemDetalle);
              }
           menuL.add(menu);
            
        }
         
         
          
        }
    }
   

    public void mostrarMenus(int programa) {
        menuD = new menuDAO();
        menuL = menuD.getModuloAcceso(programa);
    }

    public void mostrarMenuHijo(int programa, menuC item) {

        menuD = new menuDAO();
        menuHijoL = menuD.getModuloHijoAcceso(programa, item.getmPadre());
    }
    public usuario getUsuarioB() {
        return usuarioB;
    }
    public void setUsuarioB(usuario usuarioB) {
        this.usuarioB = usuarioB;
    }
    public List<menuC> getListaHijos() {
        return listaHijos;
    }
    public void setListaHijos(List<menuC> listaHijos) {
        this.listaHijos = listaHijos;
    }
    public List<menuC> getMenuHijoL() {
        return menuHijoL;
    }
    public void setMenuHijoL(List<menuC> menuHijoL) {
        this.menuHijoL = menuHijoL;
    }
    public List<menuC> getMenuL() {
        return menuL;
    }
    public void setMenuL(List<menuC> menuL) {
        this.menuL = menuL;
    }
    public menuC getMenu() {
        return menu;
    }
    public void setMenu(menuC menu) {
        this.menu = menu;
    }

   

}
