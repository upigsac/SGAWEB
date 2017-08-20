
package Beans;

import DAO.personalDAO;
import ENTIDAD.personaC;
import ENTIDAD.personalC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;


@ManagedBean(name = "personalB")
@ViewScoped
public class personal  {

    private personalC personal=new personalC();
    private List<personalC> personalL;

    personalDAO personalD = null;

    public void insertarPersonal(personalC item) {
        personalD = new personalDAO();
        personalD.insertarPersonal(item);
    }

    public void generarPersonal(String persona) {
        personalD = new personalDAO();
        personalD.insertarPersonal(new personalC(null, persona, false, null, 1));
        personal = personalD.BuscaPersonaCodigo(persona);
    }

    public personalC buscarPersonalCodigo(String persona) {

        personalD = new personalDAO();

        personal = personalD.BuscaPersonaCodigo(persona);
        return personal;
    }
    
      public List<personalC> mostrarListaPersonal(String persona) {

        personalD = new personalDAO();
        personalL = personalD.mostrarListaPersonal(persona);
        return personalL;
    }

    public personalC mostrarCPersona(String cpersonal) {
        personalD = new personalDAO();
        personal = personalD.mostrarCPersonal(cpersonal);
        return personal;
    }

    public personalC mostrarPersona(String personal) {
        personalD = new personalDAO();
        this.personal = personalD.mostrarPersonal(personal);
        return this.personal;
    }

    public void onRowSelect(SelectEvent event) {
        personalD = new personalDAO();
        this.personal = personalD.BuscaPersonaCodigo(((personaC) event.getObject()).getPersona());

    }
    public personalC getPersonal() {
        return personal;
    }
    public void setPersonal(personalC personal) {
        this.personal = personal;
    }
    public List<personalC> getPersonalL() {
        return personalL;
    }
    public void setPersonalL(List<personalC> personalL) {
        this.personalL = personalL;
    }
}
