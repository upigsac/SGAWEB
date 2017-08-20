/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import DAO.alumnoCursoNotaDAO;
import DAO.alumnoPeriodoDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Administrador
 */
@ManagedBean(name="misNotasB")
@ViewScoped
public class misNotas  {
    private List<detalleNotas> detalleNotasL;
    private List<detallePromedioPonderado> detallePromedioPonderadoL;
    private int matriculados;
    private int aprobados;
    private int desaprobados;
    private double promedioSemenestral;
    
    
    alumnoCursoNotaDAO alumnoCursoNotaD;
    alumnoPeriodoDAO alumnoPeriodoD;
    
    public void mostrarNotas(int institucion,String periodo,String alumno){
        alumnoCursoNotaD=new alumnoCursoNotaDAO();
        detalleNotasL=alumnoCursoNotaD.mostrarAlumno(institucion, periodo, alumno);
        
        
        alumnoPeriodoD=new alumnoPeriodoDAO();
        detallePromedioPonderadoL=alumnoPeriodoD.mostrarAlumnoPeriodoPromedioPonderado(institucion, alumno);
        calcularTotales();
        
        
    }
    public void calcularTotales(){
        matriculados=0;
        aprobados=0;
        desaprobados=0;
        for (detalleNotas item : detalleNotasL) {
            matriculados++;
            if (item.promedio>=11){
                aprobados++;
            }else{
                desaprobados++;
            }
                
            promedioSemenestral=item.getPromedioPonderadoPeriodo();
        }
    }

    /**
     * @return the detalleNotasL
     */
    public List<detalleNotas> getDetalleNotasL() {
        return detalleNotasL;
    }

    /**
     * @param detalleNotasL the detalleNotasL to set
     */
    public void setDetalleNotasL(List<detalleNotas> detalleNotasL) {
        this.detalleNotasL = detalleNotasL;
    }

    /**
     * @return the matriculados
     */
    public int getMatriculados() {
        return matriculados;
    }

    /**
     * @param matriculados the matriculados to set
     */
    public void setMatriculados(int matriculados) {
        this.matriculados = matriculados;
    }

    /**
     * @return the aprobados
     */
    public int getAprobados() {
        return aprobados;
    }

    /**
     * @param aprobados the aprobados to set
     */
    public void setAprobados(int aprobados) {
        this.aprobados = aprobados;
    }

    /**
     * @return the desaprobados
     */
    public int getDesaprobados() {
        return desaprobados;
    }

    /**
     * @param desaprobados the desaprobados to set
     */
    public void setDesaprobados(int desaprobados) {
        this.desaprobados = desaprobados;
    }

    /**
     * @return the detallePromedioPonderadoL
     */
    public List<detallePromedioPonderado> getDetallePromedioPonderadoL() {
        return detallePromedioPonderadoL;
    }

    /**
     * @param detallePromedioPonderadoL the detallePromedioPonderadoL to set
     */
    public void setDetallePromedioPonderadoL(List<detallePromedioPonderado> detallePromedioPonderadoL) {
        this.detallePromedioPonderadoL = detallePromedioPonderadoL;
    }

    /**
     * @return the promedioSemenestral
     */
    public double getPromedioSemenestral() {
        return promedioSemenestral;
    }

    /**
     * @param promedioSemenestral the promedioSemenestral to set
     */
    public void setPromedioSemenestral(double promedioSemenestral) {
        this.promedioSemenestral = promedioSemenestral;
    }
    
    public static class detallePromedioPonderado{
        private int institucion;
        private String periodo;
        private String desPeriodo;
        private String alumno;
        private String promedioGeneral;

     

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
         * @return the desPeriodo
         */
        public String getDesPeriodo() {
            return desPeriodo;
        }

        /**
         * @param desPeriodo the desPeriodo to set
         */
        public void setDesPeriodo(String desPeriodo) {
            this.desPeriodo = desPeriodo;
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
         * @return the promedioGeneral
         */
        public String getPromedioGeneral() {
            return promedioGeneral;
        }

        /**
         * @param promedioGeneral the promedioGeneral to set
         */
        public void setPromedioGeneral(String promedioGeneral) {
            this.promedioGeneral = promedioGeneral;
        }

        /**
         * @return the institucion
         */
        public int getInstitucion() {
            return institucion;
        }

        /**
         * @param institucion the institucion to set
         */
        public void setInstitucion(int institucion) {
            this.institucion = institucion;
        }
        
    }
    
    public static class detalleNotas{
        private int institucion;
        private String periodo;
        private String carrera;
        private String malla;
        private String curso;
        private String desCurso;
        private String seccion;
        private String alumno;
        private double practica1;    
        private double practica2;    
        private double examenParcial;    
        private double examenFinal;    
        private double promedio;    
        private double promedioPonderadoPeriodo;
        private double promedioPonderadoGeneral;

        /**
         * @return the institucion
         */
        public int getInstitucion() {
            return institucion;
        }

        /**
         * @param institucion the institucion to set
         */
        public void setInstitucion(int institucion) {
            this.institucion = institucion;
        }

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
         * @return the desCurso
         */
        public String getDesCurso() {
            return desCurso;
        }

        /**
         * @param desCurso the desCurso to set
         */
        public void setDesCurso(String desCurso) {
            this.desCurso = desCurso;
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
         * @return the practica1
         */
        public double getPractica1() {
            return practica1;
        }

        /**
         * @param practica1 the practica1 to set
         */
        public void setPractica1(double practica1) {
            this.practica1 = practica1;
        }

        /**
         * @return the practica2
         */
        public double getPractica2() {
            return practica2;
        }

        /**
         * @param practica2 the practica2 to set
         */
        public void setPractica2(double practica2) {
            this.practica2 = practica2;
        }

        /**
         * @return the examenParcial
         */
        public double getExamenParcial() {
            return examenParcial;
        }

        /**
         * @param examenParcial the examenParcial to set
         */
        public void setExamenParcial(double examenParcial) {
            this.examenParcial = examenParcial;
        }

        /**
         * @return the examenFinal
         */
        public double getExamenFinal() {
            return examenFinal;
        }

        /**
         * @param examenFinal the examenFinal to set
         */
        public void setExamenFinal(double examenFinal) {
            this.examenFinal = examenFinal;
        }

        /**
         * @return the promedio
         */
        public double getPromedio() {
            return promedio;
        }

        /**
         * @param promedio the promedio to set
         */
        public void setPromedio(double promedio) {
            this.promedio = promedio;
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
         * @return the promedioPonderadoGeneral
         */
        public double getPromedioPonderadoGeneral() {
            return promedioPonderadoGeneral;
        }

        /**
         * @param promedioPonderadoGeneral the promedioPonderadoGeneral to set
         */
        public void setPromedioPonderadoGeneral(double promedioPonderadoGeneral) {
            this.promedioPonderadoGeneral = promedioPonderadoGeneral;
        }

        /**
         * @return the promedioPonderadoPeriodo
         */
        public double getPromedioPonderadoPeriodo() {
            return promedioPonderadoPeriodo;
        }

        /**
         * @param promedioPonderadoPeriodo the promedioPonderadoPeriodo to set
         */
        public void setPromedioPonderadoPeriodo(double promedioPonderadoPeriodo) {
            this.promedioPonderadoPeriodo = promedioPonderadoPeriodo;
        }
                
    }
}
