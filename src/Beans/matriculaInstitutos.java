
package Beans;

import DAO.alumnoCursoSeccionDAO;
import ENTIDAD.alumnoCursoSeccionC;
import ENTIDAD.seccionPeriodoC;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



@ManagedBean(name = "matriculaInstitutosB")
@ViewScoped
public class matriculaInstitutos  {

    private seccionPeriodoC seccion;
    private String usuario;
    private List<listaAlumnosMatriculados> alumnoMatriculadosL;
    alumnoCursoSeccionDAO alumnoCursoSeccionD;
    
    
    public List<listaAlumnosMatriculados> mostrarListAlumno(int institucion,String periodo, String carrera, String seccion){
        alumnoCursoSeccionD=new alumnoCursoSeccionDAO();
        alumnoMatriculadosL=alumnoCursoSeccionD.listadoMatriculadoInstituto(institucion, periodo, carrera, seccion);                
        return alumnoMatriculadosL;
    }
    public List<listaAlumnosMatriculados> getAlumnoMatriculadosL() {
        return alumnoMatriculadosL;
    }
    public void setAlumnoMatriculadosL(List<listaAlumnosMatriculados> alumnoMatriculadosL) {
        this.alumnoMatriculadosL = alumnoMatriculadosL;
    }
    
    
    
    public static class  listaAlumnosMatriculados {
        private String persona;
        private String alumno;
        private String datos;
        private String fecha;
        private String hora;

      
        public String getAlumno() {
            return alumno;
        }

        public void setAlumno(String alumno) {
            this.alumno = alumno;
        }

        public String getDatos() {
            return datos;
        }

        public void setDatos(String datos) {
            this.datos = datos;
        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        public String getHora() {
            return hora;
        }

        public void setHora(String hora) {
            this.hora = hora;
        }

        public String getPersona() {
            return persona;
        }

        public void setPersona(String persona) {
            this.persona = persona;
        }
    }
    
    public void insertarMatricula(int institucion, String periodo, String seccion, String alumno, int estado) {
       
        alumnoCursoSeccionD = new alumnoCursoSeccionDAO();
        alumnoCursoSeccionD.insertarMatriculaInstituto(institucion, periodo, seccion, alumno, estado);
    }

    public void eliminarMatricula(alumnoCursoSeccionC item) {
        alumnoCursoSeccionD = new alumnoCursoSeccionDAO();

        alumnoCursoSeccionD.eliminarMatriculaInstituto(item.getInstitucion(), item.getPeriodo(), item.getSeccion(), item.getAlumno());
    }

    public void imprimir() {
        util.consolaCliente( " imprimi xxx");
    }

    public seccionPeriodoC getSeccion() {
        return seccion;
    }

    public void setSeccion(seccionPeriodoC seccion) {
        this.seccion = seccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


}
