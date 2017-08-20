
package Beans;

import DAO.examenAdmisionDAO;


import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "examenAdmisionB")
@ViewScoped
public class examenAdmision  {

    private List<detalle> detalleL;
    private String facultad;
    private String carrera;
    examenAdmisionDAO examenAdmisionD;

    public List<detalle> mostrarPostulantes(int institucion, String periodo, String carrera) {
        examenAdmisionD = new examenAdmisionDAO();
        detalleL = examenAdmisionD.mostrarEstadoCodigo(institucion, periodo, carrera);
        return detalleL;
    }

    public void ingresar(int institucion, int periodo, String carrera) {

        for (int x = 0; x <= detalleL.size() - 1; x++) {
            examenAdmisionD = new examenAdmisionDAO();
            examenAdmisionD.insertarPuntuacion(institucion, periodo, carrera, detalleL.get(x).alumno, "AC1", detalleL.get(x).aptitud_1);
            examenAdmisionD.insertarPuntuacion(institucion, periodo, carrera, detalleL.get(x).alumno, "AC2", detalleL.get(x).aptitud_2);
            examenAdmisionD.insertarPuntuacion(institucion, periodo, carrera, detalleL.get(x).alumno, "G1", detalleL.get(x).grupo_1);
            examenAdmisionD.insertarPuntuacion(institucion, periodo, carrera, detalleL.get(x).alumno, "G2", detalleL.get(x).grupo_2);
            examenAdmisionD.insertarPuntuacion(institucion, periodo, carrera, detalleL.get(x).alumno, "G3", detalleL.get(x).grupo_3);
            examenAdmisionD.insertarPuntuacion(institucion, periodo, carrera, detalleL.get(x).alumno, "CG", detalleL.get(x).culturaGeneral);

        }

    }

    public void ingresarBD(int institucion, int periodo, String carrera) {

        examenAdmisionD = new examenAdmisionDAO();
        examenAdmisionD.insertarPuntuacionBD(institucion, periodo, carrera);

    }
    public List<detalle> getDetalleL() {
        return detalleL;
    }
    public void setDetalleL(List<detalle> detalleL) {
        this.detalleL = detalleL;
    }
    public String getFacultad() {
        return facultad;
    }
    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }
    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public static class detalle {

        private String alumno;
        private String postulante;
        private String aptitud_1;
        private String aptitud_2;
        private String grupo_1;
        private String grupo_2;
        private String grupo_3;
        private String culturaGeneral;
        private String promedio;

        public String getAlumno() {
            return alumno;
        }
        public void setAlumno(String alumno) {
            this.alumno = alumno;
        }
        public String getPostulante() {
            return postulante;
        }
        public void setPostulante(String postulante) {
            this.postulante = postulante;
        }
        public String getCulturaGeneral() {
            return culturaGeneral;
        }
        public void setCulturaGeneral(String culturaGeneral) {
            this.culturaGeneral = culturaGeneral;
        }
        public String getGrupo_3() {
            return grupo_3;
        }
        public void setGrupo_3(String grupo_3) {
            this.grupo_3 = grupo_3;
        }
        public String getGrupo_2() {
            return grupo_2;
        }
        public void setGrupo_2(String grupo_2) {
            this.grupo_2 = grupo_2;
        }
        public String getGrupo_1() {
            return grupo_1;
        }
        public void setGrupo_1(String grupo_1) {
            this.grupo_1 = grupo_1;
        }
        public String getPromedio() {
            return promedio;
        }
        public void setPromedio(String promedio) {
            this.promedio = promedio;
        }
        public String getAptitud_1() {
            return aptitud_1;
        }
        public void setAptitud_1(String aptitud_1) {
            this.aptitud_1 = aptitud_1;
        }
        public String getAptitud_2() {
            return aptitud_2;
        }
        public void setAptitud_2(String aptitud_2) {
            this.aptitud_2 = aptitud_2;
        }
    }
}
