
package Beans;

import DAO.personaDAO;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



@ManagedBean(name = "constanciaDeNoAdeudarB")
@ViewScoped
public class constanciaDeNoAdeudar{

    private List<detalle> detalleL;
    
    private String observacion="";
    private int option=0;

    personaDAO personaD;
    

    public List<detalle> mostrarConstanciaPersona(String persona) {
        
        personaD = new personaDAO();
        detalleL = personaD.mostrarConstanciaPersonal(persona);
        return detalleL;
    }

    public void quitarConstancia(int serie, String persona) {
        personaD = new personaDAO();
        personaD.eliminarConstanciaPerson(serie, persona);
    }
    public List<detalle> getDetalleL() {
        return detalleL;
    }
    public void setDetalleL(List<detalle> detalleL) {
        this.detalleL = detalleL;
    }
    public String getObservacion() {
        return observacion;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    public int getOption() {
        return option;
    }
    public void setOption(int option) {
        this.option = option;
    }

    public static class detalle {

        private int serie;
        private String persona;
        private String nombres;
        private String institucion;
        private String usuario;
        private String tipo;
       
        public String getNombres() {
            return nombres;
        }
        public void setNombres(String nombres) {
            this.nombres = nombres;
        }
        public int getSerie() {
            return serie;
        }
        public void setSerie(int serie) {
            this.serie = serie;
        }
        public String getPersona() {
            return persona;
        }
        public void setPersona(String persona) {
            this.persona = persona;
        }
        public String getInstitucion() {
            return institucion;
        }
        public void setInstitucion(String institucion) {
            this.institucion = institucion;
        }
        public String getUsuario() {
            return usuario;
        }
        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }
        public String getTipo() {
            return tipo;
        }
        public void setTipo(String tipo) {
            this.tipo = tipo;
        }
    }

}
