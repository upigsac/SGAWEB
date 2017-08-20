

package Beans;

import DAO.cursoSeccionDocenteTipoExamenDAO;
import ENTIDAD.cursoSeccionDocenteTipoExamenC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="cursoSeccionDocenteTipoExamenB")
@ViewScoped
public class cursoSeccionDocenteTipoExamen  {
    private List<cursoSeccionDocenteTipoExamenC> cursoSeccionDocenteTipoExamenL;
    
    cursoSeccionDocenteTipoExamenDAO cursoSeccionDocenteTipoExamenD;
    
    public List<cursoSeccionDocenteTipoExamenC> mostrarGrupos(int institucion,String periodo,String personal,String carrera,String seccion,String curso,String tipoExamen){
        cursoSeccionDocenteTipoExamenD=new cursoSeccionDocenteTipoExamenDAO();
        cursoSeccionDocenteTipoExamenL=cursoSeccionDocenteTipoExamenD.mostrarCursoSeccionDocenteTipoExamenGrupo(institucion, periodo, personal, carrera, seccion, curso, tipoExamen);
        return cursoSeccionDocenteTipoExamenL;
    }
    
    public boolean mostrarOpcion(int institucion,String periodo,String persona){
        cursoSeccionDocenteTipoExamenD=new cursoSeccionDocenteTipoExamenDAO();
        return cursoSeccionDocenteTipoExamenD.mostrarOpcion(institucion, periodo, persona);
    }
    public List<cursoSeccionDocenteTipoExamenC> getCursoSeccionDocenteTipoExamenL() {
        return cursoSeccionDocenteTipoExamenL;
    }
    public void setCursoSeccionDocenteTipoExamenL(List<cursoSeccionDocenteTipoExamenC> cursoSeccionDocenteTipoExamenL) {
        this.cursoSeccionDocenteTipoExamenL = cursoSeccionDocenteTipoExamenL;
    }
}
