
package Beans;



import javax.faces.bean.ManagedBean;

@ManagedBean(name = "buzonSugerenciaB")
public class sugerencia {

    private String asunto;
    private String mesaje;
    private int seleccion_destino;
    private int seleccion_tipo_mensaje;

    

    public String getAsunto() {
        return asunto;
    }
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    public String getMesaje() {
        return mesaje;
    }
    public void setMesaje(String mesaje) {
        this.mesaje = mesaje;
    }
    public int getSeleccion_destino() {
        return seleccion_destino;
    }
    public void setSeleccion_destino(int seleccion_destino) {
        this.seleccion_destino = seleccion_destino;
    }
    public int getSeleccion_tipo_mensaje() {
        return seleccion_tipo_mensaje;
    }
    public void setSeleccion_tipo_mensaje(int seleccion_tipo_mensaje) {
        this.seleccion_tipo_mensaje = seleccion_tipo_mensaje;
    }

}
