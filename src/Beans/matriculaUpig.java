
package Beans;

import DAO.alumnoCarreraDAO;
import DAO.matriculaUpigDAO;
import DAO.periodoDAO;
import ENTIDAD.alumnoCarreraC;
import ENTIDAD.alumnoCursoSeccionC;
import ENTIDAD.alumnoPeriodoC;
import ENTIDAD.periodoC;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="matriculaUpigB")
@ViewScoped
public class matriculaUpig {
    private List<asignaturasAsigandas>  asignaturaAsignadaL=new ArrayList<>();
    private List<seccionesAsigandas> seccionesAsigandaL=new ArrayList<>();
    private seccionesAsigandas seccionesAsiganda=new seccionesAsigandas();
    private List<asignaturasRegistradas> asignaturasRegistradaL=new ArrayList<>();
    
    private alumnoCarreraC alumnoCarrera;
    private int creditosRegistrados;
    private int creditosDisponibles=22;
    private periodoC periodo;

    
    periodoDAO periodoD;
    public matriculaUpig() {
        periodoD=new periodoDAO();
       periodo=periodoD.periodoMatricula(1, 20142);
    }
    
    
    
    
    
      matriculaUpigDAO matriculaUpigD;
      alumnoCarreraDAO alumnoCarreraD;
        public void mostrarCursosAsignados(int institucion,String periodo,String malla,String carrera,String alumno){
            matriculaUpigD=new matriculaUpigDAO();
            asignaturaAsignadaL=matriculaUpigD.mostrarCursosAlumno(institucion, periodo, malla, carrera, alumno);
        }
    
        
         public List<seccionesAsigandas> mostrarSeccionAsignados(int institucion,String periodo,String malla,String carrera,String curso){
            matriculaUpigD=new matriculaUpigDAO();
            seccionesAsigandaL=matriculaUpigD.mostrarSeccionCurso(institucion, periodo, malla, carrera, curso);
            return seccionesAsigandaL;
        }
         
         public void agregarCurso(asignaturasAsigandas itemCursos, seccionesAsigandas itemSeccion){
             creditosRegistrados+=itemCursos.creditos;
             if (creditosRegistrados > creditosDisponibles){
                 util.alert("los creditos sobre pasaron");
                 creditosRegistrados-=itemCursos.creditos;
             }
                 
                 
                     
             asignaturasRegistradaL.add(new asignaturasRegistradas(itemCursos.ciclo,itemCursos.curso,itemCursos.desCurso,itemSeccion.seccion,itemCursos.creditos,itemCursos.veces,1,itemCursos.desTipoCurso));
            
             matriculaUpigD=new matriculaUpigDAO();
             alumnoPeriodoC itemPeriodo=new alumnoPeriodoC(1, "20151", itemCursos.carrera,"2009010010", itemCursos.malla, 0, 0, 0, 0, null, null, 0, 0, null, null, null,  14);
             alumnoCursoSeccionC itemCursoSeccion=new alumnoCursoSeccionC(1, "20151", itemCursos.carrera, itemCursos.malla, itemCursos.curso, itemSeccion.seccion, "2009010010", itemSeccion.grupo, Double.NaN, 1);
             matriculaUpigD.InsertaMatricula(itemPeriodo, itemCursoSeccion);
             
             mostrarCursosAsignados(1,"20141","200702","000001","2009010010");
             util.alert("La asignatura se registro correctamente.. ");
         }
         
         public void quitarCurso(asignaturasRegistradas item){
             
             asignaturasRegistradaL.remove(item);
             creditosRegistrados-=item.creditos;
         }
                 
         
         
         public void buscarAlumno(){
            
         }
    
    
    
    
    public seccionesAsigandas getSeccionesAsiganda() {
        return seccionesAsiganda;
    }
    public void setSeccionesAsiganda(seccionesAsigandas seccionesAsiganda) {
        this.seccionesAsiganda = seccionesAsiganda;
    }
    public List<seccionesAsigandas> getSeccionesAsigandaL() {
        return seccionesAsigandaL;
    }
    public void setSeccionesAsigandaL(List<seccionesAsigandas> seccionesAsigandaL) {
        this.seccionesAsigandaL = seccionesAsigandaL;
    }
    public List<asignaturasAsigandas> getAsignaturaAsignadaL() {
        return asignaturaAsignadaL;
    }
    public void setAsignaturaAsignadaL(List<asignaturasAsigandas> asignaturaAsignadaL) {
        this.asignaturaAsignadaL = asignaturaAsignadaL;
    }
    public List<asignaturasRegistradas> getAsignaturasRegistradaL() {
        return asignaturasRegistradaL;
    }
    public void setAsignaturasRegistradaL(List<asignaturasRegistradas> asignaturasRegistradaL) {
        this.asignaturasRegistradaL = asignaturasRegistradaL;
    }
    public alumnoCarreraC getAlumnoCarrera() {
        return alumnoCarrera;
    }
    public void setAlumnoCarrera(alumnoCarreraC alumnoCarrera) {
        this.alumnoCarrera = alumnoCarrera;
    }
    public int getCreditosRegistrados() {
        return creditosRegistrados;
    }
    public void setCreditosRegistrados(int creditosRegistrados) {
        this.creditosRegistrados = creditosRegistrados;
    }
    public int getCreditosDisponibles() {
        return creditosDisponibles;
    }
    public void setCreditosDisponibles(int creditosDisponibles) {
        this.creditosDisponibles = creditosDisponibles;
    }
    public periodoC getPeriodo() {
        return periodo;
    }
    public void setPeriodo(periodoC periodo) {
        this.periodo = periodo;
    }

 
    
    public static class asignaturasRegistradas{
        private int institucion;
        private String periodo;
        private String carrera;
        private int ciclo;
        private String curso;
        private String desCurso;
        private String seccion;
        private int creditos;
        private int veces;
        private int tipo;
        private String desTipo;

        public asignaturasRegistradas() {
        }

        public asignaturasRegistradas(int ciclo, String curso, String desCurso, String seccion, int creditos, int veces, int tipo, String desTipo) {
            this.ciclo = ciclo;
            this.curso = curso;
            this.desCurso = desCurso;
            this.seccion = seccion;
            this.creditos = creditos;
            this.veces = veces;
            this.tipo = tipo;
            this.desTipo = desTipo;
        }
        public int getCiclo() {
            return ciclo;
        }
        public void setCiclo(int ciclo) {
            this.ciclo = ciclo;
        }
        public String getCurso() {
            return curso;
        }
        public void setCurso(String curso) {
            this.curso = curso;
        }
        public String getDesCurso() {
            return desCurso;
        }
        public void setDesCurso(String desCurso) {
            this.desCurso = desCurso;
        }
        public String getSeccion() {
            return seccion;
        }
        public void setSeccion(String seccion) {
            this.seccion = seccion;
        }
        public int getCreditos() {
            return creditos;
        }
        public void setCreditos(int creditos) {
            this.creditos = creditos;
        }
        public int getVeces() {
            return veces;
        }
        public void setVeces(int veces) {
            this.veces = veces;
        }
        public int getTipo() {
            return tipo;
        }
        public void setTipo(int tipo) {
            this.tipo = tipo;
        }
        public String getDesTipo() {
            return desTipo;
        }
        public void setDesTipo(String desTipo) {
            this.desTipo = desTipo;
        }
        public int getInstitucion() {
            return institucion;
        }
        public void setInstitucion(int institucion) {
            this.institucion = institucion;
        }
        public String getPeriodo() {
            return periodo;
        }
        public void setPeriodo(String periodo) {
            this.periodo = periodo;
        }
        public String getCarrera() {
            return carrera;
        }
        public void setCarrera(String carrera) {
            this.carrera = carrera;
        }

     

    }
    
    
    public static class seccionesAsigandas{
        private String seccion;
        private String grupo;
        private String personal;
        private String persona;
        private String desPersona;
        private int capacidad;
        private int matriculados;
        private int turno;
        private String desTurno;
        
        public String getSeccion() {
            return seccion;
        }
        public void setSeccion(String seccion) {
            this.seccion = seccion;
        }
        public int getCapacidad() {
            return capacidad;
        }
        public void setCapacidad(int capacidad) {
            this.capacidad = capacidad;
        }
        public int getMatriculados() {
            return matriculados;
        }
        public void setMatriculados(int matriculados) {
            this.matriculados = matriculados;
        }
        public int getTurno() {
            return turno;
        }
        public void setTurno(int turno) {
            this.turno = turno;
        }
        public String getDesTurno() {
            return desTurno;
        }
        public void setDesTurno(String desTurno) {
            this.desTurno = desTurno;
        }
        public String getPersonal() {
            return personal;
        }
        public void setPersonal(String personal) {
            this.personal = personal;
        }
        public String getGrupo() {
            return grupo;
        }
        public void setGrupo(String grupo) {
            this.grupo = grupo;
        }
        public String getPersona() {
            return persona;
        }
        public void setPersona(String persona) {
            this.persona = persona;
        }
        public String getDesPersona() {
            return desPersona;
        }
        public void setDesPersona(String desPersona) {
            this.desPersona = desPersona;
        }

      
      
        
    }
    
    
    
    public static class asignaturasAsigandas{
        private int institucion;
        private String carrera;
        private String malla;
        private int ciclo;
        private String curso;
        private String desCurso;
        private int creditos;
        private int veces;
        private int horaPractica;
        private int horaTeoria;
        private int tipoCurso;
        private String desTipoCurso;

        public int getCiclo() {
            return ciclo;
        }

        public void setCiclo(int ciclo) {
            this.ciclo = ciclo;
        }

        public String getCurso() {
            return curso;
        }
        public void setCurso(String curso) {
            this.curso = curso;
        }
        public String getDesCurso() {
            return desCurso;
        }
        public void setDesCurso(String desCurso) {
            this.desCurso = desCurso;
        }
        public int getCreditos() {
            return creditos;
        }
        public void setCreditos(int creditos) {
            this.creditos = creditos;
        }
        public int getVeces() {
            return veces;
        }
        public void setVeces(int veces) {
            this.veces = veces;
        }
        public int getHoraPractica() {
            return horaPractica;
        }
        public void setHoraPractica(int horaPractica) {
            this.horaPractica = horaPractica;
        }
        public int getHoraTeoria() {
            return horaTeoria;
        }
        public void setHoraTeoria(int horaTeoria) {
            this.horaTeoria = horaTeoria;
        }
        public int getInstitucion() {
            return institucion;
        }
        public void setInstitucion(int institucion) {
            this.institucion = institucion;
        }
        public String getCarrera() {
            return carrera;
        }
        public void setCarrera(String carrera) {
            this.carrera = carrera;
        }
        public String getMalla() {
            return malla;
        }
        public void setMalla(String malla) {
            this.malla = malla;
        }
        public int getTipoCurso() {
            return tipoCurso;
        }
        public void setTipoCurso(int tipoCurso) {
            this.tipoCurso = tipoCurso;
        }
        public String getDesTipoCurso() {
            return desTipoCurso;
        }
        public void setDesTipoCurso(String desTipoCurso) {
            this.desTipoCurso = desTipoCurso;
        }
        
    }
}
