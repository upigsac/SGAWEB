
package Beans;



import DAO.recepcionActaDAO;
import ENTIDAD.ColumnModel;
import ENTIDAD.controlDocente;



import java.util.ArrayList;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class recepcionActa {

    private String filtro_carrera;
    private String filtro_personal;

    private List<controlDocente> listadoControl=new ArrayList<>();
    private controlDocente seleccionControl;
    private List<ColumnModel> listadoExamenActa;

    private List<controlDocente> totalNotas;
    private List<ColumnModel> totalNotas_examenes;

  
    @ManagedProperty(value = "#{usuarioB}")
    private usuario usuB;
    /*
    @PostConstruct
    public void init() {
        
    }
    */
    public recepcionActa() {
        filtro_carrera = "%";
        filtro_personal = "%";
        listadoExamenActa = new ArrayList<>();
        listadoExamenActa.add(new ColumnModel("1° PRACTICA<br/>CALIFICADA", "U4", 0));
        listadoExamenActa.add(new ColumnModel("EXAMEN<br/>PARCIAL", "EP", 1));
        listadoExamenActa.add(new ColumnModel("2° PRACTICA<br/>CALIFICADA", "U9", 2));
        listadoExamenActa.add(new ColumnModel("EXAMEN<br/>FINAL", "EF", 3));
        listadoExamenActa.add(new ColumnModel("ACTA<br/>FINAL", "AF", 4));
    }

    public void listarRecepciones() {
      
       
        //filtro_personal = !filtro_personal.equals("%") ? new personalDAO().BuscaPersonaCodigo(filtro_personal).getPersonal() : "%";
        listadoControl = new recepcionActaDAO().controlEntregaActas(usuB.getInstitucionS(), usuB.getPeriodoS(), filtro_carrera, filtro_personal);
    }

    public void onCellEdit(controlDocente cd, String tipo_examen) {

     

        for (ColumnModel cm : cd.getExamenes()) {
            if (cm.getHeader().equals(tipo_examen)) {
                cd.setRecepcion_fecha(cm.getProperty_date());
                System.out.println(cm.getHeader() + " " + cm.getProperty_date());
            }
        }

        new recepcionActaDAO().registrarControlActaDocente(cd, tipo_examen);

        listarRecepciones();
    }

    public void mostrarDetalle() {
        
        totalNotas = new recepcionActaDAO().controlEntregaActasDetalle(usuB.getInstitucionS(), usuB.getPeriodoS(), filtro_carrera, filtro_personal);
        totalNotas_examenes = new ArrayList<>(totalNotas.get(0).getExamenes());
    }

    public List<controlDocente> getListadoControl() {
        return listadoControl;
    }

    public void setListadoControl(List<controlDocente> listadoControl) {
        this.listadoControl = listadoControl;
    }

   
    public String getFiltro_carrera() {
        return filtro_carrera;
    }

    public void setFiltro_carrera(String filtro_carrera) {
        this.filtro_carrera = filtro_carrera;
    }

    public String getFiltro_personal() {
        return filtro_personal;
    }

   
    public void setFiltro_personal(String filtro_personal) {
        this.filtro_personal = filtro_personal;
    }

    public List<ColumnModel> getListadoExamenActa() {
        return listadoExamenActa;
    }

   
    public void setListadoExamenActa(List<ColumnModel> listadoExamenActa) {
        this.listadoExamenActa = listadoExamenActa;
    }

    public controlDocente getSeleccionControl() {
        return seleccionControl;
    }

    public void setSeleccionControl(controlDocente seleccionControl) {
        this.seleccionControl = seleccionControl;
    }

    public List<controlDocente> getTotalNotas() {
        return totalNotas;
    }
    public void setTotalNotas(List<controlDocente> totalNotas) {
        this.totalNotas = totalNotas;
    }
    public List<ColumnModel> getTotalNotas_examenes() {
        return totalNotas_examenes;
    }
    public void setTotalNotas_examenes(List<ColumnModel> totalNotas_examenes) {
        this.totalNotas_examenes = totalNotas_examenes;
    }
    public usuario getUsuB() {
        return usuB;
    }
    public void setUsuB(usuario usuB) {
        this.usuB = usuB;
    }

}
