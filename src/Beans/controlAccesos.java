
package Beans;


import DAO.accesoDAO;
import DAO.institucionAccesoDAO;
import DAO.institucionDAO;
import DAO.menuDAO;
import DAO.personaDAO;
import DAO.segUsuarioDAO;
import DAO.usuarioDAO;
import DAO.usuarioInstitucionDAO;
import ENTIDAD.accesoC;
import ENTIDAD.institucionAccesoC;
import ENTIDAD.institucionC;
import ENTIDAD.menuC;
import ENTIDAD.personaC;
import ENTIDAD.segUsuarioC;
import ENTIDAD.usuarioC;
import ENTIDAD.usuarioInstitucionC;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;




import org.primefaces.event.SelectEvent;


@ManagedBean(name = "controlAccesoB")
@ViewScoped
public class controlAccesos implements Serializable {



    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<menuC> menuL;
    private List<menuC> subMenuL;
    private menuC menu;
    
    private List<usuarioMenu> usuarioMenuL;
    private List<segUsuarioC> usuarioL; 
    
    
    
    
    
    
    
    
    
    
    
 
    
	
	
	private List<personaC> personaL;
    private List<usuarioInstitucionC> usuarioInstitucionL; 
    private institucionAccesoC institucionAcceso;
    private accesoC acceso;
    
    
    
    
    
    
    

	private List<institucionC> institucionL=new ArrayList<>();
	private List<institucionC> institucionFL=new ArrayList<>();
    
    
    public List<institucionC> getInstitucionFL() {
		return institucionFL;
	}
	public void setInstitucionFL(List<institucionC> institucionFL) {
		this.institucionFL = institucionFL;
	}
	private personaC seleccionPersona;
    private usuarioC seleccionUsuario;

    private String codigo;
    private String usuario;
    private String clave;
  
    
    
    private String subMenu;
    private boolean verClave;
    private int institucionUsuario;
    private int institucionS;

    
    private String paterno="";
    private String materno="";
    private String nombres="";

   
    
    public String getPaterno() {
		return paterno;
	}
	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}
	public String getMaterno() {
		return materno;
	}
	public void setMaterno(String materno) {
		this.materno = materno;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	public void buscar(){
		paterno="";
		materno="";
		nombres="";
		util.script("dlgBusquedaPersona.show()");
	}
	
	public void insertarAcceso(usuarioMenu item){
    	if(item.acceso==null){
    		if (new accesoDAO().mostrarAccesos(menu.getSigu(), menu.getModulo(), "M", usuario).size()==0){    			
    			new accesoDAO().insertar(new accesoC(usuario, menu.getSigu(), menu.getId(), 1, 1));
    		}
    		
    		
    		new accesoDAO().insertar(new accesoC(usuario, item.menu.getSigu(), item.menu.getId(), 1, 1));
    	}else{
    		new accesoDAO().eliminar(item.acceso);
    		
    		if (new accesoDAO().mostrarAccesos(menu.getSigu(), menu.getModulo(), "S", usuario).size()==0){
    			new accesoDAO().eliminar(new accesoC(usuario, menu.getSigu(), menu.getId(), 0, 1)  );
    		}
    			
    		
    	
    	}
    	
    	mostrarSubMenu();
    	System.out.println("se inserto");
    	
    }
    public void nivelSeguridad(accesoC item){
    	new accesoDAO().insertar(item);
    	 util.script("notificacion('Se cambio al nivel "+ item.getTipoSeguridad() +" de seguridad',500,5000,'correcto')");
    }
    
    public void mostrarMenu() {
       
        menuL = new menuDAO().getModuloAcceso(institucionAcceso.getPrograma());
    }
    
    
    public void mostrarInstitucionUsuario() {
       
        institucionL = new institucionDAO().mostrarInstitucionUsuario(usuario);
      

        
    }
    
   
    public void mostrarInstitucionAcceso(int item) {
        
        institucionUsuario=item;
        
        institucionAcceso = new institucionAccesoDAO().mostrarInstitucionAcceso(institucionUsuario);
        mostrarMenu();
        
    }
    
    public void mostrarInstitucion(){
        institucionS=0;
      
        institucionFL=new institucionDAO().mostrarInstitucionFaltantesUsuario(usuario);
        util.script("dlgInstitucion.show()");
    }
    
   
   public void mostrarSubMenu() {    
       
       usuarioMenuL=new ArrayList<>();
	  
       for (menuC itemSubMenu : new menuDAO().getModuloHijoAcceso(institucionAcceso.getPrograma(), menu.getModulo())) {
		   usuarioMenu itemUsuarioMenu=new usuarioMenu(itemSubMenu);
		   itemUsuarioMenu.acceso=new accesoDAO().mostrarAccesos(usuario, itemSubMenu.getSigu(), itemSubMenu.getId());
		   usuarioMenuL.add(itemUsuarioMenu);
		   
	}
	   
   }
   public void selectMenuPadre(menuC item){
	   menu=item;
	   mostrarSubMenu();
   }
   
   
   public void insertarInstitucion(){
      
   
       new usuarioInstitucionDAO().insertar(new usuarioInstitucionC(usuario, institucionS, 1));
       mostrarInstitucionUsuario();
       util.script("dlgInstitucion.hide()");
       institucionUsuario=institucionS;
   }
   public void eliminarInstitucion(int institucion){
	    
       new usuarioInstitucionDAO().eliminar(new usuarioInstitucionC(usuario, institucion, 1));
       mostrarInstitucionUsuario();
    }
    
   
   
   public List<usuarioInstitucionC> mostrarUsuarioInstitucion(String usuario){
       
       usuarioInstitucionL=new usuarioInstitucionDAO().mostrarUsuarioInstitucion(usuario);
       return usuarioInstitucionL;
   }
   
   public void desencriptar(){
      
       clave=new usuarioDAO().desencriptarClave(clave);
   }
  
    public String getClave() {
        return clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }
    public boolean isVerClave() {
        return verClave;
    }
    public void setVerClave(boolean verClave) {
        this.verClave = verClave;
    }
    public List<usuarioInstitucionC> getUsuarioInstitucionL() {
        return usuarioInstitucionL;
    }
    public void setUsuarioInstitucionL(List<usuarioInstitucionC> usuarioInstitucionL) {
        this.usuarioInstitucionL = usuarioInstitucionL;
    }
    public int getInstitucionUsuario() {
        return institucionUsuario;
    }
    public void setInstitucionUsuario(int institucionUsuario) {
        this.institucionUsuario = institucionUsuario;
    }
    public List<institucionC> getInstitucionL() {
        return institucionL;
    }
    public void setInstitucionL(List<institucionC> institucionL) {
        this.institucionL = institucionL;
    }
    public int getInstitucionS() {
        return institucionS;
    }
    public void setInstitucionS(int institucionS) {
        this.institucionS = institucionS;
    }
    public institucionAccesoC getInstitucionAcceso() {
        return institucionAcceso;
    }
    public void setInstitucionAcceso(institucionAccesoC institucionAcceso) {
        this.institucionAcceso = institucionAcceso;
    }
    
    


    public void seleccion(String usuario, int programa, int modulo) {

      
        new menuDAO().insertarMenuAcceso(usuario, programa, modulo, 1);
    }


    
   
            

    public void onRowSelect(SelectEvent event) {
        

        seleccionPersona = (personaC) event.getObject();

        seleccionUsuario = new usuarioDAO().busquedaUsuarioCodigo(seleccionPersona.getPersona());
        usuarioL = new segUsuarioDAO().mostrarSegUsuario(seleccionPersona.getPersona());
        if(usuarioL.size()>0){
        	usuario=usuarioL.get(0).getUsuario();
        	mostrarInstitucionUsuario();
        }

       
        codigo = seleccionUsuario.getPersona();
        clave=seleccionUsuario.getClave();

    }

   
    public void filtroPersona() {

     
        personaL = new personaDAO().BuscaPersona(paterno, materno, nombres);
    }
    
    public static class usuarioMenu{
    	


		menuC menu;
    	accesoC acceso=new accesoC();
    	
    	public usuarioMenu() {
			
		}
    	
    	
    	public usuarioMenu(menuC menu) {
    		this.menu=menu;
			
		}
    	public menuC getMenu() {
			return menu;
		}


		public void setMenu(menuC menu) {
			this.menu = menu;
		}


		public accesoC getAcceso() {
			return acceso;
		}


		public void setAcceso(accesoC acceso) {
			this.acceso = acceso;
		}
    	
    }
    
    
    
    
    
    public List<menuC> getMenuL() {
        return menuL;
    }
    public void setMenuL(List<menuC> menuL) {
        this.menuL = menuL;
    }
    public List<menuC> getSubMenuL() {
        return subMenuL;
    }
    public void setSubMenuL(List<menuC> subMenuL) {
        this.subMenuL = subMenuL;
    }
 
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    personaDAO per;

    public List<personaC> getPersonaL() {

        return personaL;
    }
    public void setPersonaL(List<personaC> personaL) {
        this.personaL = personaL;
    }
    public personaC getSeleccionPersona() {
        return seleccionPersona;
    }
    public void setSeleccionPersona(personaC seleccionPersona) {
        this.seleccionPersona = seleccionPersona;
    }
    public usuarioC getSeleccionUsuario() {
        return seleccionUsuario;
    }
    public void setSeleccionUsuario(usuarioC seleccionUsuario) {
        this.seleccionUsuario = seleccionUsuario;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getSubMenu() {
        return subMenu;
    }
    public void setSubMenu(String subMenu) {
        this.subMenu = subMenu;
    }
    
    
    public accesoC getAcceso() {
		return acceso;
	}


	public void setAcceso(accesoC acceso) {
		this.acceso = acceso;
	}
	
	public menuC getMenu() {
		return menu;
	}

	public void setMenu(menuC menu) {
		this.menu = menu;
	}
	
	public List<usuarioMenu> getUsuarioMenuL() {
		return usuarioMenuL;
	}

	public void setUsuarioMenuL(List<usuarioMenu> usuarioMenuL) {
		this.usuarioMenuL = usuarioMenuL;
	}
	
	public List<segUsuarioC> getUsuarioL() {
		return usuarioL;
	}


	public void setUsuarioL(List<segUsuarioC> usuarioL) {
		this.usuarioL = usuarioL;
	}
}
