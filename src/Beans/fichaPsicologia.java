
package Beans;

import DAO.encuestaDAO;

import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "fichaPsicologiaB")
@ViewScoped
public class fichaPsicologia {

    private List<detalle> respuestaL;

    public static class detalle {

        private int periodo;
        private String usuario;
        private String persona;
        private String carrera;
        private String ciclo;
        private String turno;
        private Date fecha;

        public int getPeriodo() {
            return periodo;
        }

        public void setPeriodo(int periodo) {
            this.periodo = periodo;
        }

        public String getUsuario() {
            return usuario;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }
        public String getPersona() {
            return persona;
        }
        public void setPersona(String persona) {
            this.persona = persona;
        }
        public String getCarrera() {
            return carrera;
        }
        public void setCarrera(String carrera) {
            this.carrera = carrera;
        }
        public String getCiclo() {
            return ciclo;
        }
        public void setCiclo(String ciclo) {
            this.ciclo = ciclo;
        }
        public String getTurno() {
            return turno;
        }
        public void setTurno(String turno) {
            this.turno = turno;
        }
        public Date getFecha() {
            return fecha;
        }
        public void setFecha(Date fecha) {
            this.fecha = fecha;
        }

    }

    encuestaDAO encuestaD;

    public List<detalle> mostrarEncuesta(int institucion, int periodo, String carrera, String ciclo, int encuesta) {
        encuestaD = new encuestaDAO();
        respuestaL = encuestaD.mostrarResultadoEncuesta(institucion, periodo, carrera, ciclo, encuesta);
        return respuestaL;
    }
    public List<detalle> getRespuestaL() {
        return respuestaL;
    }
    public void setRespuestaL(List<detalle> respuestaL) {
        this.respuestaL = respuestaL;
    }

}
