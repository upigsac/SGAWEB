
package Beans;

import DAO.grupoSangineoDAO;
import ENTIDAD.grupoSangineoC;

import java.util.List;
import javax.faces.bean.ManagedBean;


@ManagedBean(name = "grupoSangineoB")
public class grupoSangineo  {

    private List<grupoSangineoC> grupoSangenoL;

    grupoSangineoDAO grupoSangineoD;

    public List<grupoSangineoC> mostrarGrupoSangineo() {
        grupoSangineoD = new grupoSangineoDAO();
        grupoSangenoL = grupoSangineoD.mostrarGrupoSangineo();
        return grupoSangenoL;
    }

 
    public List<grupoSangineoC> getGrupoSangenoL() {
        return grupoSangenoL;
    }

    public void setGrupoSangenoL(List<grupoSangineoC> grupoSangenoL) {
        this.grupoSangenoL = grupoSangenoL;
    }

}
