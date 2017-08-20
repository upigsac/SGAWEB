

package Beans;

import DAO.resultadoPostulanteDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="resultadoPostulanteB")
@ViewScoped
public class resultadoPostulante {
    private boolean bandera;
    private String busPostulante;
    private postulanteC postulante;

    /**
     * @return the postulante
     */
    
    resultadoPostulanteDAO resultadoPostulanteD;
    public void consultar(){
       
        resultadoPostulanteD=new resultadoPostulanteDAO();
        postulante=resultadoPostulanteD.mostrarResultado(1, "20152", busPostulante);
        if (postulante==null){
            bandera=false;
        }else{
            bandera=true;
        }
    }
    
    public postulanteC getPostulante() {
        return postulante;
    }

    /**
     * @param postulante the postulante to set
     */
    public void setPostulante(postulanteC postulante) {
        this.postulante = postulante;
    }

    /**
     * @return the busPostulante
     */
    public String getBusPostulante() {
        return busPostulante;
    }

    /**
     * @param busPostulante the busPostulante to set
     */
    public void setBusPostulante(String busPostulante) {
        this.busPostulante = busPostulante;
    }

    /**
     * @return the bandera
     */
    public boolean isBandera() {
        return bandera;
    }

    /**
     * @param bandera the bandera to set
     */
    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }
    
    
    public static class postulanteC{
        private int institucion;
        private String periodo;
        private String postulante;
        private String desPersona;
        private String persona;
        private String desCarrera;
        private String desEstado;

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
         * @return the persona
         */
        public String getPersona() {
            return persona;
        }

        /**
         * @param persona the persona to set
         */
        public void setPersona(String persona) {
            this.persona = persona;
        }

        /**
         * @return the desCarrera
         */
        public String getDesCarrera() {
            return desCarrera;
        }

        /**
         * @param desCarrera the desCarrera to set
         */
        public void setDesCarrera(String desCarrera) {
            this.desCarrera = desCarrera;
        }

        /**
         * @return the desEstado
         */
        public String getDesEstado() {
            return desEstado;
        }

        /**
         * @param desEstado the desEstado to set
         */
        public void setDesEstado(String desEstado) {
            this.desEstado = desEstado;
        }

        /**
         * @return the postulante
         */
        public String getPostulante() {
            return postulante;
        }

        /**
         * @param postulante the postulante to set
         */
        public void setPostulante(String postulante) {
            this.postulante = postulante;
        }
    }
}
