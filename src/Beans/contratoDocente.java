

package Beans;

import DAO.cursoSeccionDocenteDAO;

import DAO.personalContratoCursoDAO;
import DAO.personalDAO;
import ENTIDAD.cursoSeccionDocenteC;

import ENTIDAD.personalC;
import ENTIDAD.personalContratoCursosC;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="contratoDocenteB")
@ViewScoped
public class contratoDocente {
    private int institucion=1;
    private String periodo;
    private String persona;
    private personalC personal;
    private List<contratoCurso> contratoCursoL=new ArrayList<>();
 
    
    
   cursoSeccionDocenteDAO cursoSeccionDocenteD;
   personalDAO personalD;
    personalContratoCursoDAO personalContratoCursoD;

    public List<contratoCurso> getContratoCursoL() {
        return contratoCursoL;
    }
    public void setContratoCursoL(List<contratoCurso> contratoCursoL) {
        this.contratoCursoL = contratoCursoL;
    }

   
   
   public class contratoCurso{
       private cursoSeccionDocenteC cursoSeccionDocente;
       private personalContratoCursosC personalContratoCurso=new personalContratoCursosC();

        public contratoCurso() {
        }

        public contratoCurso(cursoSeccionDocenteC cursoSeccionDocente) {
            this.cursoSeccionDocente = cursoSeccionDocente;
        }

   
        public personalContratoCursosC getPersonalContratoCurso() {
            return personalContratoCurso;
        }
        public void setPersonalContratoCurso(personalContratoCursosC personalContratoCurso) {
            this.personalContratoCurso = personalContratoCurso;
        }
        public cursoSeccionDocenteC getCursoSeccionDocente() {
            return cursoSeccionDocente;
        }
        public void setCursoSeccionDocente(cursoSeccionDocenteC cursoSeccionDocente) {
            this.cursoSeccionDocente = cursoSeccionDocente;
        }
   }
   
   public void mostrarCursoSeccionDocente(){
       personalD=new personalDAO();
       personal=personalD.mostrarPersona(persona);
       
       
       cursoSeccionDocenteD=new cursoSeccionDocenteDAO();
       personalContratoCursoD=new personalContratoCursoDAO();
       contratoCursoL=new ArrayList<>();
       contratoCurso itemContratoCurso;
       for(cursoSeccionDocenteC itemCursoSeccionDocente:cursoSeccionDocenteD.mostrarCursoSeccionDocente(institucion, periodo, personal.getPersonal())){
           itemContratoCurso=new contratoCurso(itemCursoSeccionDocente);
           itemContratoCurso.personalContratoCurso=personalContratoCursoD.mostrarContratoCursoPersonal(itemCursoSeccionDocente.getInstitucion(), itemCursoSeccionDocente.getPeriodo(), itemCursoSeccionDocente.getCarrera(), itemCursoSeccionDocente.getMalla(), itemCursoSeccionDocente.getCurso(), itemCursoSeccionDocente.getSeccion(), itemCursoSeccionDocente.getPersonal());
           if(itemContratoCurso.personalContratoCurso ==null){
               itemContratoCurso.personalContratoCurso=new personalContratoCursosC(itemCursoSeccionDocente.getPersonal(), -1, itemCursoSeccionDocente.getInstitucion(), itemCursoSeccionDocente.getPeriodo(), itemCursoSeccionDocente.getCarrera(), itemCursoSeccionDocente.getMalla(), itemCursoSeccionDocente.getSeccion(), itemCursoSeccionDocente.getCurso(), null,null,null, 1);
           }
           
          contratoCursoL.add(itemContratoCurso);
       }

   }
   public void guardar(contratoCurso itemContratoCurso){
       cursoSeccionDocenteD=new cursoSeccionDocenteDAO();
       itemContratoCurso.cursoSeccionDocente.setEstadoRegistro(itemContratoCurso.cursoSeccionDocente.getEstadoRegistro()==1?59:1);
       
       cursoSeccionDocenteD.insertar(itemContratoCurso.cursoSeccionDocente);
       
       if(itemContratoCurso.cursoSeccionDocente.getEstadoRegistro()==59){
           personalContratoCursoD.eliminar(itemContratoCurso.personalContratoCurso);
       }else{
           
           personalContratoCursoD.insertar(itemContratoCurso.personalContratoCurso);
       }
       mostrarCursoSeccionDocente();
   }
   
    public String getPeriodo() {
        return periodo;
    }
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    public int getInstitucion() {
        return institucion;
    }
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }
    public String getPersona() {
        return persona;
    }
    public void setPersona(String persona) {
        this.persona = persona;
    }
    public personalC getPersonal() {
        return personal;
    }
    public void setPersonal(personalC personal) {
        this.personal = personal;
    }
    
    
}
