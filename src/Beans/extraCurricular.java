
package Beans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "extraCurricularB")
@ViewScoped
public class extraCurricular  {

    private String centroProduccion = "%";

    public String getCentroProduccion() {
        return centroProduccion;
    }
    public void setCentroProduccion(String centroProduccion) {
        this.centroProduccion = centroProduccion;
    }
}
