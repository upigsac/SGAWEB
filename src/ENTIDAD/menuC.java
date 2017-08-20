
package ENTIDAD;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class menuC implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sigu;
    private int id;
    private int tipoIcono;
    private String iconoTitle;
    private String classIcono;
    private String modulo ;
    private String nombre ;
    private String imgMod;
    private String url ;
    private String urlvar ;
    private String tipo;
    private String mPadre;
    private int orden;
    private String target;
  
    
    
    
    
   //-----------------------------------
    private List<menuC> menuL=new ArrayList<>();

    public menuC() {
    }

    public menuC(int sigu, int id, int tipoIcono, String iconoTitle, String modulo, String nombre, String imgMod, String url, String urlvar, String tipo, String mPadre, int orden, String target) {
        this.sigu = sigu;
        this.id = id;
        this.tipoIcono = tipoIcono;
        this.iconoTitle = iconoTitle;
        this.modulo = modulo;
        this.nombre = nombre;
        this.imgMod = imgMod;
        this.url = url;
        this.urlvar = urlvar;
        this.tipo = tipo;
        this.mPadre = mPadre;
        this.orden = orden;
        this.target = target;
       
       this.menuL = new ArrayList<>();
    }
    public String getModulo() {
        return modulo;
    }
    public void setModulo(String modulo) {
        this.modulo = modulo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getmPadre() {
        return mPadre;
    }
    public void setmPadre(String mPadre) {
        this.mPadre = mPadre;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getImgMod() {
        return imgMod;
    }
    public void setImgMod(String imgMod) {
        this.imgMod = imgMod;
    }
    public String getUrlvar() {
        return urlvar;
    }
    public void setUrlvar(String urlvar) {
        this.urlvar = urlvar;
    }
   
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTarget() {
        return target;
    }
    public void setTarget(String target) {
        this.target = target;
    }
    public List<menuC> getMenuL() {
        return menuL;
    }
    public void setMenuL(List<menuC> menuL) {
        this.menuL = menuL;
    }
    public int getSigu() {
        return sigu;
    }
    public void setSigu(int sigu) {
        this.sigu = sigu;
    }
    public int getTipoIcono() {
        return tipoIcono;
    }
    public void setTipoIcono(int tipoIcono) {
        this.tipoIcono = tipoIcono;
    }
    public String getIconoTitle() {
        return iconoTitle;
    }
    public void setIconoTitle(String iconoTitle) {
        this.iconoTitle = iconoTitle;
    }
    public int getOrden() {
        return orden;
    }
    public void setOrden(int orden) {
        this.orden = orden;
    }
    public String getClassIcono() {
        return classIcono;
    }
    public void setClassIcono(String classIcono) {
        this.classIcono = classIcono;
    }
    
    
}
