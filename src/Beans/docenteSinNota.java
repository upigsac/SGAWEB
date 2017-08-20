
package Beans;

import DAO.personalDAO;
import DAO.tipoExamenDAO;
import ENTIDAD.tipoExamenC;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "docenteSinNotaB")
@ViewScoped
public class docenteSinNota {

    private List<tipoExamenC> tipoExamenL;
    private List<String> examenSeleccionadosL = new ArrayList<>();
    private List<String> nivelSeleccionadosL = new ArrayList<>();
    private List<dtDetalle> detalleL;

    private String personal = "%";
    private String carrera = "%";

    tipoExamenDAO tipoExamenD;
    personalDAO personalD;

    public List<tipoExamenC> mostrarTipoExamen() {
        tipoExamenD = new tipoExamenDAO();
        tipoExamenL = tipoExamenD.mostrarTipoExamenFormulaRegular();
        return tipoExamenL;
    }

    public void mostrarDetalle(int institucion, int periodo, String carrera, String docente) {

        personalD = new personalDAO();
        String examen = "";
        String nivel = "";

        for (int i = 0; i <= examenSeleccionadosL.size() - 1; i++) {
            examen += examenSeleccionadosL.get(i).toString() + '-';

        }

        for (int i = 0; i <= nivelSeleccionadosL.size() - 1; i++) {
            nivel += nivelSeleccionadosL.get(i).toString() + '-';

        }

        detalleL = personalD.mostrarDocenteSinNota(institucion, periodo, carrera, docente, examen.substring(0, examen.length() - 1), nivel.substring(0, nivel.length() - 1));

    }

    /**
     * @return the detalleL
     */
    public List<dtDetalle> getDetalleL() {
        return detalleL;
    }

    /**
     * @param detalleL the detalleL to set
     */
    public void setDetalleL(List<dtDetalle> detalleL) {
        this.detalleL = detalleL;
    }

    /**
     * @return the nivelSeleccionadosL
     */
    public List<String> getNivelSeleccionadosL() {
        return nivelSeleccionadosL;
    }

    /**
     * @param nivelSeleccionadosL the nivelSeleccionadosL to set
     */
    public void setNivelSeleccionadosL(List<String> nivelSeleccionadosL) {
        this.nivelSeleccionadosL = nivelSeleccionadosL;
    }

    /**
     * @return the personal
     */
    public String getPersonal() {
        return personal;
    }

    /**
     * @param personal the personal to set
     */
    public void setPersonal(String personal) {
        this.personal = personal;
    }

    /**
     * @return the carrera
     */
    public String getCarrera() {
        return carrera;
    }

    /**
     * @param carrera the carrera to set
     */
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public static class dtDetalle {

        private String periodo;
        private String carrera;
        private String malla;
        private String curso;
        private String tipoExamen;
        private String seccion;
        private String ciclo;
        private String personal;
        private String alumno;
        private String totalAlumno;

        /**
         * @return the periodo
         */
        public String getPeriodo() {
            return periodo;
        }

        /**
         * @param periodo the periodo to set
         */
        public void setPeriodo(String periodo) {
            this.periodo = periodo;
        }

        /**
         * @return the carrera
         */
        public String getCarrera() {
            return carrera;
        }

        /**
         * @param carrera the carrera to set
         */
        public void setCarrera(String carrera) {
            this.carrera = carrera;
        }

        /**
         * @return the curso
         */
        public String getCurso() {
            return curso;
        }

        /**
         * @param curso the curso to set
         */
        public void setCurso(String curso) {
            this.curso = curso;
        }

        /**
         * @return the seccion
         */
        public String getSeccion() {
            return seccion;
        }

        /**
         * @param seccion the seccion to set
         */
        public void setSeccion(String seccion) {
            this.seccion = seccion;
        }

        /**
         * @return the ciclo
         */
        public String getCiclo() {
            return ciclo;
        }

        /**
         * @param ciclo the ciclo to set
         */
        public void setCiclo(String ciclo) {
            this.ciclo = ciclo;
        }

        /**
         * @return the personal
         */
        public String getPersonal() {
            return personal;
        }

        /**
         * @param personal the personal to set
         */
        public void setPersonal(String personal) {
            this.personal = personal;
        }

        /**
         * @return the alumno
         */
        public String getAlumno() {
            return alumno;
        }

        /**
         * @param alumno the alumno to set
         */
        public void setAlumno(String alumno) {
            this.alumno = alumno;
        }

        /**
         * @return the tipoExamen
         */
        public String getTipoExamen() {
            return tipoExamen;
        }

        /**
         * @param tipoExamen the tipoExamen to set
         */
        public void setTipoExamen(String tipoExamen) {
            this.tipoExamen = tipoExamen;
        }

        /**
         * @return the totalAlumno
         */
        public String getTotalAlumno() {
            return totalAlumno;
        }

        /**
         * @param totalAlumno the totalAlumno to set
         */
        public void setTotalAlumno(String totalAlumno) {
            this.totalAlumno = totalAlumno;
        }

        /**
         * @return the malla
         */
        public String getMalla() {
            return malla;
        }

        /**
         * @param malla the malla to set
         */
        public void setMalla(String malla) {
            this.malla = malla;
        }

    }

    /**
     * @return the tipoExamenL
     */
    public List<tipoExamenC> getTipoExamenL() {
        return tipoExamenL;
    }

    /**
     * @param tipoExamenL the tipoExamenL to set
     */
    public void setTipoExamenL(List<tipoExamenC> tipoExamenL) {
        this.tipoExamenL = tipoExamenL;
    }

    /**
     * @return the examenSeleccionadosL
     */
    public List<String> getExamenSeleccionadosL() {
        return examenSeleccionadosL;
    }

    /**
     * @param examenSeleccionadosL the examenSeleccionadosL to set
     */
    public void setExamenSeleccionadosL(List<String> examenSeleccionadosL) {
        this.examenSeleccionadosL = examenSeleccionadosL;
    }

}
