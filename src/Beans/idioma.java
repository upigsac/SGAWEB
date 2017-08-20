
package Beans;

import DAO.idiomaDAO;
import ENTIDAD.idiomaC;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="idiomaB")
@ViewScoped

public class idioma {
    
    private List<idiomaC> idiomaL;
    private idiomaC idioma;
    
    
    idiomaDAO idiomaD;
    public List<idiomaC> mostrarIdiomas(){
        idiomaD=new idiomaDAO();
        idiomaL=idiomaD.mostrarIdioma();
        return idiomaL;
    }
    
    public idiomaC mostrarIdiomas(int idioma){
        idiomaD=new idiomaDAO();
        this.idioma=idiomaD.mostrarIdioma(idioma);
        return this.idioma;
    }
    public List<idiomaC> getIdiomaL() {
        return idiomaL;
    }
    public void setIdiomaL(List<idiomaC> idiomaL) {
        this.idiomaL = idiomaL;
    }
    public idiomaC getIdioma() {
        return idioma;
    }
    public void setIdioma(idiomaC idioma) {
        this.idioma = idioma;
    }

}
