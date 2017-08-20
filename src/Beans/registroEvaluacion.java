
package Beans;

import DAO.alumnoDAO;
import DAO.carrerasDAO;
import DAO.cursoDAO;
import DAO.seccionDAO;
import ENTIDAD.carrerasC;
import ENTIDAD.cursosC;
import ENTIDAD.seccionC;


import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.CellEditEvent;


@ManagedBean(name = "registroEvaluacionB")
@ViewScoped
public class registroEvaluacion  {

    private String seleccionCarrera;
    private String seleccionSeccion;
    private String seleccionCurso;

    private List<carrerasC> listaCarrera;
    private List<cursosC> listaCurso;
    private List<seccionC> listaSeccion;
    private List<tablaDetalleC> listaDetalle;
     @ManagedProperty(value = "#{usuarioB}")
    private usuario usuarioS;

    
    public List<tablaDetalleC> getListaDetalle() {
        return listaDetalle;
    }

    
    public void setListaDetalle(List<tablaDetalleC> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }

    public usuario getUsuarioS() {
        return usuarioS;
    }

    public void setUsuarioS(usuario usuarioS) {
        this.usuarioS = usuarioS;
    }


    public static class tablaDetalleC {

        private int grupo;
        private String codAlumno;
        private String alumno;
        private int ra;
        private int rb;
        private int ma;
        private int mb;
        private int aa;
        private int ab;
        private int pa;
        private int pb;

        
        public int getGrupo() {
            return grupo;
        }

       
        public void setGrupo(int grupo) {
            this.grupo = grupo;
        }

        
        public String getCodAlumno() {
            return codAlumno;
        }

        
        public void setCodAlumno(String codAlumno) {
            this.codAlumno = codAlumno;
        }

        public String getAlumno() {
            return alumno;
        }

       
        public void setAlumno(String alumno) {
            this.alumno = alumno;
        }

        public int getRa() {
            return ra;
        }

        public void setRa(int ra) {
            this.ra = ra;
        }

     
        public int getRb() {
            return rb;
        }

        public void setRb(int rb) {
            this.rb = rb;
        }

        public int getMa() {
            return ma;
        }

        public void setMa(int ma) {
            this.ma = ma;
        }

     
        public int getMb() {
            return mb;
        }

        public void setMb(int mb) {
            this.mb = mb;
        }

        public int getAa() {
            return aa;
        }

        public void setAa(int aa) {
            this.aa = aa;
        }

        public int getAb() {
            return ab;
        }

      
        public void setAb(int ab) {
            this.ab = ab;
        }

        public int getPa() {
            return pa;
        }

        public void setPa(int pa) {
            this.pa = pa;
        }

        public int getPb() {
            return pb;
        }

        public void setPb(int pb) {
            this.pb = pb;
        }

    }

    cursoDAO cursoD;

    public void mostrarCursos() {

        cursoD = new cursoDAO();
        listaCurso = cursoD.BuscaCursosPersonaPeriodo(usuarioS.getPersona().getPersona(), usuarioS.getPeriodoS(), seleccionCarrera);

    }
    seccionDAO seccionD;

    public void mostrarSeccion() {
        seccionD = new seccionDAO();
        listaSeccion = seccionD.mostrarSeccionCursoDocente(usuarioS.getPersona().getPersona(), usuarioS.getPeriodoS(), seleccionCarrera, seleccionCurso);
    }
    alumnoDAO alumnoD;

    public void mostrarListaAlumnos() {
        //util.consolaCliente( "ss");
        alumnoD = new alumnoDAO();
        listaDetalle = alumnoD.registroEvaluacion(1, usuarioS.getPeriodoS(), seleccionCarrera, seleccionCurso, seleccionSeccion);
    }

    public void ingresoNota(String alumno, String colum, String nota) {

        alumnoD.insertarEvaluacionNota(1, usuarioS.getPeriodoS(), seleccionCarrera, seleccionCurso, seleccionSeccion, alumno, colum, nota);
    }

    public void onCellEdit(CellEditEvent event) {

       
        Object newValue = event.getNewValue();

        tablaDetalleC item = listaDetalle.get(event.getRowIndex());

        //util.consolaCliente( item.alumno);
        alumnoD.insertarGrupoAlumno(1, usuarioS.getPeriodoS(), seleccionCarrera, seleccionCurso, seleccionSeccion, item.codAlumno, newValue.toString());

    }

    public String getSeleccionCarrera() {
        return seleccionCarrera;
    }

    public void setSeleccionCarrera(String seleccionCarrera) {
        this.seleccionCarrera = seleccionCarrera;
    }

    public String getSeleccionSeccion() {
        return seleccionSeccion;
    }

    public void setSeleccionSeccion(String seleccionSeccion) {
        this.seleccionSeccion = seleccionSeccion;
    }
    public String getSeleccionCurso() {
        return seleccionCurso;
    }
    public void setSeleccionCurso(String seleccionCurso) {
        this.seleccionCurso = seleccionCurso;
    }

    carrerasDAO carreraD;

    public List<carrerasC> getListaCarrera() {
        carreraD = new carrerasDAO();
        listaCarrera = carreraD.mostrarCarrerasDocentePeriodo(1, usuarioS.getPeriodoS(), usuarioS.getPersona().getPersona());
        return listaCarrera;
    }
    public void setListaCarrera(List<carrerasC> listaCarrera) {
        this.listaCarrera = listaCarrera;
    }
    public List<cursosC> getListaCurso() {
        return listaCurso;
    }
    public void setListaCurso(List<cursosC> listaCurso) {
        this.listaCurso = listaCurso;
    }
    public List<seccionC> getListaSeccion() {
        return listaSeccion;
    }
    public void setListaSeccion(List<seccionC> listaSeccion) {
        this.listaSeccion = listaSeccion;
    }


}
