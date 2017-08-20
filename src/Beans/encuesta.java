
package Beans;

import DAO.carrerasDAO;
import DAO.encuestaDAO;
import ENTIDAD.carrerasC;
import ENTIDAD.encuestaC;
import ENTIDAD.encuestaGrupoC;
import ENTIDAD.encuestaPreguntaC;
import ENTIDAD.encuestaRespuestaC;
import ENTIDAD.encuestaRespuestaUsuarioC;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;


@ManagedBean(name = "encuestaB")
@ViewScoped
public class encuesta {

    private List<encuestaGrupoC> gruposL;
    private List<encuestaPreguntaC> preguntaL;
    private List<encuestaRespuestaC> respuestaL;
    private List<encuestaRespuestaUsuarioC> repuestaUsuarioL = new ArrayList<encuestaRespuestaUsuarioC>();
    private List<ArrayList<String>> usuarioL;
    private List<carrerasC> carreraL;
    private encuestaC encuesta;
    private int respuesta;
    private String texto;
    private String usuario;
    private String carrera;
    private String ano;
    private String rpt;
    private boolean estado;
    private String[] positions = new String[10];
    encuestaDAO egresadoD;

    public void guardar(int institucion, int periodo, String carrera, String usuario) throws IOException {
        egresadoD = new encuestaDAO();
        for (encuestaRespuestaUsuarioC item : repuestaUsuarioL) {
            egresadoD.resgitrarEncuesta(item.getEncuesta(), item.getPregunta(), item.getRepuesta(), item.getDescripcion(), item.getUsuario());
        }
        egresadoD.resgitrarCabecera(institucion, periodo, carrera, usuario, "4", 1);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/SGAWEB/faces/Alumno/index.xhtml");
    }

    public void limpiar() {
        util.consolaCliente( "limpiando");
    }
    int index = 0;

    public boolean buscarRepuesta(int pregunta) {
        index = 0;
        for (encuestaRespuestaUsuarioC item : repuestaUsuarioL) {
            if (item.getPregunta() == pregunta) {
                return true;
            }
            index++;
        }
        return false;
    }

    public void adicionarRepuesta(int encuesta, int pregunta, int respuesta, String descripcion, String usuario) {
        if (!buscarRepuesta(pregunta)) {
            repuestaUsuarioL.add(new encuestaRespuestaUsuarioC(encuesta, pregunta, respuesta, descripcion, usuario));
        } else {
            repuestaUsuarioL.set(index, new encuestaRespuestaUsuarioC(encuesta, pregunta, respuesta, descripcion, usuario));
        }
    }

    public List<encuestaRespuestaC> mostrarRespuesta(int encuesta, int pregunta) {
        egresadoD = new encuestaDAO();
        respuestaL = egresadoD.mostrarRespuesta(encuesta,0, pregunta);
        return respuestaL;
    }

    public void refrescar(String usuario, int pregunta) {
        egresadoD = new encuestaDAO();
        respuesta = egresadoD.getRespuestasMarcada(usuario, pregunta);
    }

    public encuestaC mostrarEncuesta(int codigo) {
        egresadoD = new encuestaDAO();
        encuesta = egresadoD.mostrarEncuesta(codigo);
        return encuesta;
    }

    public void registrarse() {
        egresadoD = new encuestaDAO();

        usuarioL = egresadoD.getVerificar(usuario);
        if (usuarioL.size() > 0) {
            if (usuarioL.get(0).get(3).toString().equals("2")) {

            } else {
                egresadoD = new encuestaDAO();
                egresadoD.registro(usuario, ano);
                estado = true;
            }
        } else {
            egresadoD = new encuestaDAO();
            egresadoD.registro(usuario, ano);
            estado = true;
        }

    }

    public void insertarEncuesta(String usuario, int encuesta, int pregunta, int tipo) {
        String rpt = "";
        if (tipo == 0) {
            rpt = egresadoD.insertarEncuesta(usuario, encuesta, pregunta, 0, texto);
        } else {
            rpt = egresadoD.insertarEncuesta(usuario, encuesta, pregunta, getRespuesta(), "");
        }
        if (!rpt.isEmpty()) {
            RequestContext.getCurrentInstance().execute("encuesta();");
            // RequestContext.getCurrentInstance().execute("$('.contenedor_encuesta').css('display','none')");
        }

    }

    public boolean controlEncuesta(int institucion, int periodo, String usuario, int encuesta) {
        egresadoD = new encuestaDAO();
        return egresadoD.controlEncuesta(institucion, periodo, usuario, encuesta);
    }

    public void verificar() throws IOException {

        usuarioL = egresadoD.getVerificar(usuario);

        if (usuarioL.get(0).get(3).toString().equals("2")) {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/SGAWEB/Encuesta/exito.html");
        }
    }

    

    public List<encuestaPreguntaC> mostrarSubPreguntas(int encuesta, int grupo, String pregunta, String tipo) {
        egresadoD = new encuestaDAO();
        preguntaL = egresadoD.mostrarSubPreguntas(encuesta, grupo, pregunta, tipo);
        return preguntaL;
    }

    public List<encuestaGrupoC> mostrarGrupo(int encuesta) {
        egresadoD = new encuestaDAO();
        gruposL = egresadoD.mostrarGrupo(encuesta);
        return gruposL;
    }

    public List<encuestaPreguntaC> getPreguntaL() {
        return preguntaL;
    }
    public void setPreguntaL(List<encuestaPreguntaC> preguntaL) {
        this.preguntaL = preguntaL;
    }
    public List<encuestaRespuestaC> getRespuestaL() {
        return respuestaL;
    }
    public void setRespuestaL(List<encuestaRespuestaC> respuestaL) {
        this.respuestaL = respuestaL;
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public int getRespuesta() {
        return respuesta;
    }
    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    public String getAno() {
        return ano;
    }
    public void setAno(String ano) {
        this.ano = ano;
    }
    carrerasDAO careDAO;

    public List<carrerasC> getCarreraL() {
        
        carreraL = new carrerasDAO().listaCarrera();
        return carreraL;
    }
    public void setCarreraL(List<carrerasC> carreraL) {
        this.carreraL = carreraL;
    }
    public List<ArrayList<String>> getUsuarioL() {
        return usuarioL;
    }
    public void setUsuarioL(List<ArrayList<String>> usuarioL) {
        this.usuarioL = usuarioL;
    }
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    public encuestaC getEncuesta() {
        return encuesta;
    }
    public void setEncuesta(encuestaC encuesta) {
        this.encuesta = encuesta;
    }
    public List<encuestaGrupoC> getGruposL() {
        return gruposL;
    }
    public void setGruposL(List<encuestaGrupoC> gruposL) {
        this.gruposL = gruposL;
    }
    public String[] getPositions() {
        return positions;
    }
    public void setPositions(String[] positions) {
        this.positions = positions;
    }
    public List<encuestaRespuestaUsuarioC> getRepuestaUsuarioL() {
        return repuestaUsuarioL;
    }
    public void setRepuestaUsuarioL(List<encuestaRespuestaUsuarioC> repuestaUsuarioL) {
        this.repuestaUsuarioL = repuestaUsuarioL;
    }
    public String getRpt() {
        return rpt;
    }
    public void setRpt(String rpt) {
        this.rpt = rpt;
    }
}
