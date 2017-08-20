/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author Administrador
 */
@ManagedBean(name="certificacionSeccionB")
public class certificacionSeccion {
    
    
    public static class detalle{
        private String alumno;
        private String desPersona;
        private int promedio;
        private String fechaImpresion;
        private String fechaEntrega;

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
         * @return the desPersona
         */
        public String getDesPersona() {
            return desPersona;
        }

        /**
         * @param desPersona the desPersona to set
         */
        public void setDesPersona(String desPersona) {
            this.desPersona = desPersona;
        }

        /**
         * @return the promedio
         */
        public int getPromedio() {
            return promedio;
        }

        /**
         * @param promedio the promedio to set
         */
        public void setPromedio(int promedio) {
            this.promedio = promedio;
        }

        /**
         * @return the fechaImpresion
         */
        public String getFechaImpresion() {
            return fechaImpresion;
        }

        /**
         * @param fechaImpresion the fechaImpresion to set
         */
        public void setFechaImpresion(String fechaImpresion) {
            this.fechaImpresion = fechaImpresion;
        }

        /**
         * @return the fechaEntrega
         */
        public String getFechaEntrega() {
            return fechaEntrega;
        }

        /**
         * @param fechaEntrega the fechaEntrega to set
         */
        public void setFechaEntrega(String fechaEntrega) {
            this.fechaEntrega = fechaEntrega;
        }
        
    }
}
