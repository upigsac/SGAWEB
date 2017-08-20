
package Beans;

import DAO.ArchivosDAO;
import ENTIDAD.ArchivosC;
import java.io.Serializable;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;


@ManagedBean(name = "archivosB")
public class Archivos implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private List<ArchivosC> listadoArchivos;
    ArchivosDAO archdao = null;

    @ManagedProperty(value = "#{carreraB}")
    private carrera carreraB;

    private String carrera_archivo;

    public Archivos() {

    }

    public List<ArchivosC> mostrarArchivos(int institucion,int periodo, String alumno, int tipo) {
        
        archdao = new ArchivosDAO();
        listadoArchivos = archdao.mostrarArchivosPorCarrera(institucion,periodo, alumno, tipo);
        carrera_archivo = carreraB.getCarrera().getCarrera();
        return listadoArchivos;
    }

    public List<ArchivosC> mostrarCarpeta(String tipo, String usuario) {
        archdao = new ArchivosDAO();
        listadoArchivos = archdao.mostrarArchivosCaperta(tipo, usuario);
        return listadoArchivos;
    }
    public List<ArchivosC> getListadoArchivos() {
        return listadoArchivos;
    }
    public void setListadoArchivos(List<ArchivosC> listadoArchivos) {
        this.listadoArchivos = listadoArchivos;
    }
    public carrera getCarreraB() {
        return carreraB;
    }
    public void setCarreraB(carrera carreraB) {
        this.carreraB = carreraB;
    }
    public String getCarrera_archivo() {
        return carrera_archivo;
    }
    public void setCarrera_archivo(String carrera_archivo) {
        this.carrera_archivo = carrera_archivo;
    }

}
