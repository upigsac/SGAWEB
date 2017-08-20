
package Beans;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

@ManagedBean(name = "fichaB")
@ViewScoped
public class fichaInscripcion {

    private inscripcion entrada;
    private String ubigeoDepartamento;
    private String ubigeoProvincia;
    private String ubigeoDistrito;

    @PostConstruct
    public void init() {
        ubigeoDepartamento = "15";
        ubigeoProvincia = "01";
        limpiar();
    }

    private void limpiar() {
        entrada = new inscripcion();
    }

    public void registrarInscripcion() {
        Connection c = null;
        PreparedStatement pr = null;
        c = ConexionWeb();
        String sql = "INSERT INTO FICHA_INSCRIPCION_CONTACT1 "
                + "(NOMBRES,APELLIDO_PATERNO,APELLIDO_MATERNO,DIRECCION_RECIDENCIA,EMAIL_PRINCIPAL,TELEFONO_CELULAR,TELEFONO,GRADO_INSTRUCCION,OPCION1,CONSULTA,LUGAR_CAPTACION,DIRECCION_UBIGEO) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pr = c.prepareStatement(sql);
            pr.setString(1, entrada.getNombres());
            pr.setString(2, entrada.getAp_pat());
            pr.setString(3, entrada.getAp_mat());
            pr.setString(4, entrada.getDireccion());
            pr.setString(5, entrada.getEmail());
            pr.setString(6, entrada.getFono_cel());
            pr.setString(7, entrada.getFono_fijo());
            pr.setString(8, entrada.getGrado_inst());
            pr.setString(9, entrada.getCarrera_int());
            pr.setString(10, entrada.getConsulta());
            pr.setString(11, entrada.getLugarCaptacion());
            pr.setString(12, ubigeoDepartamento + ubigeoProvincia + entrada.getLugarUbigeo());
            pr.executeUpdate();
            cerrarConexion(c);
            RequestContext.getCurrentInstance().execute("alert('Inscripcion Registrada')");
        } catch (SQLException e) {
            System.out.println("registrarInscripcion " + e.getMessage());
            RequestContext.getCurrentInstance().execute("alert('Ha ocurrido un problema con el registro')");
        }
        limpiar();
    }

    /**
     * @return the entrada
     */
    public inscripcion getEntrada() {
        return entrada;
    }

    /**
     * @param entrada the entrada to set
     */
    public void setEntrada(inscripcion entrada) {
        this.entrada = entrada;
    }

    /**
     * @return the ubigeoProvincia
     */
    public String getUbigeoProvincia() {
        return ubigeoProvincia;
    }

    /**
     * @param ubigeoProvincia the ubigeoProvincia to set
     */
    public void setUbigeoProvincia(String ubigeoProvincia) {
        this.ubigeoProvincia = ubigeoProvincia;
    }

    /**
     * @return the ubigeoDistrito
     */
    public String getUbigeoDistrito() {
        return ubigeoDistrito;
    }

    /**
     * @param ubigeoDistrito the ubigeoDistrito to set
     */
    public void setUbigeoDistrito(String ubigeoDistrito) {
        this.ubigeoDistrito = ubigeoDistrito;
    }

    /**
     * @return the ubigeoDepartamento
     */
    public String getUbigeoDepartamento() {
        return ubigeoDepartamento;
    }

    /**
     * @param ubigeoDepartamento the ubigeoDepartamento to set
     */
    public void setUbigeoDepartamento(String ubigeoDepartamento) {
        this.ubigeoDepartamento = ubigeoDepartamento;
    }

    public static class inscripcion {

        private String ap_pat;
        private String ap_mat;
        private String nombres;
        private String email;
        private String fono_fijo;
        private String fono_cel;
        private String direccion;
        private String grado_inst;
        private String carrera_int;

        private String lugarCaptacion;
        private String lugarUbigeo;
        private String consulta;

        /**
         * @return the ap_pat
         */
        public String getAp_pat() {
            return ap_pat;
        }

        /**
         * @param ap_pat the ap_pat to set
         */
        public void setAp_pat(String ap_pat) {
            this.ap_pat = ap_pat;
        }

        /**
         * @return the ap_mat
         */
        public String getAp_mat() {
            return ap_mat;
        }

        /**
         * @param ap_mat the ap_mat to set
         */
        public void setAp_mat(String ap_mat) {
            this.ap_mat = ap_mat;
        }

        /**
         * @return the nombres
         */
        public String getNombres() {
            return nombres;
        }

        /**
         * @param nombres the nombres to set
         */
        public void setNombres(String nombres) {
            this.nombres = nombres;
        }

        /**
         * @return the email
         */
        public String getEmail() {
            return email;
        }

        /**
         * @param email the email to set
         */
        public void setEmail(String email) {
            this.email = email;
        }

        /**
         * @return the fono_fijo
         */
        public String getFono_fijo() {
            return fono_fijo;
        }

        /**
         * @param fono_fijo the fono_fijo to set
         */
        public void setFono_fijo(String fono_fijo) {
            this.fono_fijo = fono_fijo;
        }

        /**
         * @return the fono_cel
         */
        public String getFono_cel() {
            return fono_cel;
        }

        /**
         * @param fono_cel the fono_cel to set
         */
        public void setFono_cel(String fono_cel) {
            this.fono_cel = fono_cel;
        }

        /**
         * @return the direccion
         */
        public String getDireccion() {
            return direccion;
        }

        /**
         * @param direccion the direccion to set
         */
        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        /**
         * @return the grado_inst
         */
        public String getGrado_inst() {
            return grado_inst;
        }

        /**
         * @param grado_inst the grado_inst to set
         */
        public void setGrado_inst(String grado_inst) {
            this.grado_inst = grado_inst;
        }

        /**
         * @return the carrera_int
         */
        public String getCarrera_int() {
            return carrera_int;
        }

        /**
         * @param carrera_int the carrera_int to set
         */
        public void setCarrera_int(String carrera_int) {
            this.carrera_int = carrera_int;
        }

        /**
         * @return the lugarCaptacion
         */
        public String getLugarCaptacion() {
            return lugarCaptacion;
        }

        /**
         * @param lugarCaptacion the lugarCaptacion to set
         */
        public void setLugarCaptacion(String lugarCaptacion) {
            this.lugarCaptacion = lugarCaptacion;
        }

        /**
         * @return the lugarUbigeo
         */
        public String getLugarUbigeo() {
            return lugarUbigeo;
        }

        /**
         * @param lugarUbigeo the lugarUbigeo to set
         */
        public void setLugarUbigeo(String lugarUbigeo) {
            this.lugarUbigeo = lugarUbigeo;
        }

        /**
         * @return the consulta
         */
        public String getConsulta() {
            return consulta;
        }

        /**
         * @param consulta the consulta to set
         */
        public void setConsulta(String consulta) {
            this.consulta = consulta;
        }
    }

}
