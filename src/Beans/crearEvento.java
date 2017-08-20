
package Beans;

import DAO.eventoDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "crearEventoB")
@ViewScoped
public class crearEvento  {

    private String evento;
    private String desEvento;
    private int institucion;
    private String tipo_evento;
    private String fecha_ini;
    private String fecha_fin;
    private int hora_practica;
    private int hora_teoria;
    private String lugar;

    eventoDAO eventoD;

    public void registrarEvento() {
        eventoD = new eventoDAO();
      //  eventoD.registrarEvento(institucion, desEvento, evento, fecha_ini, fecha_fin, tipo_evento, evento, lugar, lugar);
        //  evento="xxx";
    }
    public String getDesEvento() {
        return desEvento;
    }
    public void setDesEvento(String desEvento) {
        this.desEvento = desEvento;
    }
    public int getInstitucion() {
        return institucion;
    }
    public void setInstitucion(int institucion) {
        this.institucion = institucion;
    }
    public String getTipo_evento() {
        return tipo_evento;
    }
    public void setTipo_evento(String tipo_evento) {
        this.tipo_evento = tipo_evento;
    }
    public String getFecha_ini() {
        return fecha_ini;
    }
    public void setFecha_ini(String fecha_ini) {
        this.fecha_ini = fecha_ini;
    }
    public String getFecha_fin() {
        return fecha_fin;
    }
    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
    public int getHora_practica() {
        return hora_practica;
    }
    public void setHora_practica(int hora_practica) {
        this.hora_practica = hora_practica;
    }
    public int getHora_teoria() {
        return hora_teoria;
    }
    public void setHora_teoria(int hora_teoria) {
        this.hora_teoria = hora_teoria;
    }
    public String getLugar() {
        return lugar;
    }
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    public String getEvento() {
        return evento;
    }
    public void setEvento(String evento) {
        this.evento = evento;
    }
}
