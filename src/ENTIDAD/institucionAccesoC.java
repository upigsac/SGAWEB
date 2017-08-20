
package ENTIDAD;

import java.io.Serializable;

public class institucionAccesoC implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int institucion;
    private int programa;
    private String colorCabecera;
    private String colorPie;
    private String colorMenu;
    private String colorLetraMenu;
    private String logoTipo;
    private String colorLinea;
     
    private int estadoRegistro;

    
    
    
    
    
    
    
   
	public String getColorLinea() {
		return colorLinea;
	}
	public void setColorLinea(String colorLinea) {
		this.colorLinea = colorLinea;
	}
	public int getInstitucion() {
        return institucion;
    }
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }
    public int getPrograma() {
        return programa;
    }
    public void setPrograma(int programa) {
        this.programa = programa;
    }
    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    public String getColorCabecera() {
        return colorCabecera;
    }
    public void setColorCabecera(String colorCabecera) {
        this.colorCabecera = colorCabecera;
    }
    public String getColorPie() {
        return colorPie;
    }
    public void setColorPie(String colorPie) {
        this.colorPie = colorPie;
    }
    public String getColorMenu() {
        return colorMenu;
    }
    public void setColorMenu(String colorMenu) {
        this.colorMenu = colorMenu;
    }
    public String getLogoTipo() {
        return logoTipo;
    }
    public void setLogoTipo(String logoTipo) {
        this.logoTipo = logoTipo;
    }
    
    public String getColorLetraMenu() {
		return colorLetraMenu;
	}
	public void setColorLetraMenu(String colorLetraMenu) {
		this.colorLetraMenu = colorLetraMenu;
	}
}
