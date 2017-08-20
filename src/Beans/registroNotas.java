
package Beans;

import DAO.cursoPeriodoDAO;
import ENTIDAD.cursoPeriodoC;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



@ManagedBean(name="registroNotasB")
@ViewScoped
public class registroNotas {
    private cursoPeriodoC cursoPerido;
    private int institucion;
    private int periodo;

    
    cursoPeriodoDAO cursoPeriodoD;
    public void mostrarCursoPeriodo(int institucion,String periodo,String carrera,String curso){
      
       
        cursoPeriodoD=new cursoPeriodoDAO();
        cursoPerido=cursoPeriodoD.mostrarCursoPerido(institucion,periodo,carrera,curso);
    }
    
    public cursoPeriodoC getCursoPerido() {
        return cursoPerido;
    }

    public void setCursoPerido(cursoPeriodoC cursoPerido) {
        this.cursoPerido = cursoPerido;
    }

   
    public int getInstitucion() {
        return institucion;
    }

   
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }

    public int getPeriodo() {
        return periodo;
    }

   
    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }


}
