
package Beans;

import DAO.personaTituloDAO;
import ENTIDAD.personaTituloC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



@ManagedBean(name = "personalTituloB")
@ViewScoped
public class personaTitulo  {

    private List<personaTituloC> personaTituloL;
    private personaTituloC personalTitulo = new personaTituloC();

    personaTituloDAO personalTituloD;

    public List<personaTituloC> mostrarPersonalTitulo(String personal) {
        personalTituloD = new personaTituloDAO();
        personaTituloL = personalTituloD.mostrarPersonalTitulo(personal);
        return personaTituloL;
    }

    public void insertar(personaTituloC item) {
        util.consolaCliente( item.getPersona());
        personalTituloD = new personaTituloDAO();
        personalTituloD.insertar(item);
    }

    public void nuevo(String persona) {
        util.consolaCliente( persona);
        personalTitulo = new personaTituloC();
        personalTitulo.setPersona(persona);
    }


    /**
     * @return the personaTitulo
     */
    public personaTituloC getPersonalTitulo() {
        return personalTitulo;
    }

    /**
     * @param personalTitulo the personaTitulo to set
     */
    public void setPersonalTitulo(personaTituloC personalTitulo) {
        this.personalTitulo = personalTitulo;
    }

    /**
     * @return the personaTituloL
     */
    public List<personaTituloC> getPersonaTituloL() {
        return personaTituloL;
    }

    /**
     * @param personaTituloL the personaTituloL to set
     */
    public void setPersonaTituloL(List<personaTituloC> personaTituloL) {
        this.personaTituloL = personaTituloL;
    }

}
