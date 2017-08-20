/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ENTIDAD;

import java.util.Date;

/**
 *
 * @author Administrador
 */
public class foroPersonaCursoSeccionRespuestaC extends personaC{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pregunta;
    private int respuesta;
    
    private int institucion;
    private String periodo;
    private String carrera;
    private String malla;
    private String curso;
    private String seccion;
    private int tipoPersona;
    private String persona;
    private String mensaje;
    private int estadoRegistro;

    public foroPersonaCursoSeccionRespuestaC() {
    }

    public foroPersonaCursoSeccionRespuestaC(int pregunta, int respuesta, int institucion, String periodo, String carrera, String malla, String curso, String seccion, int tipoPersona, String persona, String mensaje, int estadoRegistro) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.institucion = institucion;
        this.periodo = periodo;
        this.carrera = carrera;
        this.malla = malla;
        this.curso = curso;
        this.seccion = seccion;
        this.tipoPersona = tipoPersona;
        this.persona = persona;
        this.mensaje = mensaje;
        this.estadoRegistro = estadoRegistro;
    }

  

    public foroPersonaCursoSeccionRespuestaC(int pregunta, int respuesta, int institucion, String periodo, String carrera, String malla, String curso, String seccion, int tipoPersona,  String mensaje, int estadoRegistro, String persona, String aPaterno, String aMaterno, String nombres, String direccion_ubigeo, int tipo_transito, String direccion_recidencial, String direccion_referencial, String direccion_ubigeo_pro, int tipo_transito_pro, String direccion_recidencial_pro, String direccion_referencial_pro, String telefono, String celular, String emailP, String emailS, Date fecha_nacimiento, String nacimiento_ubigeo, int sexo, int estado_civil, int pais, String telefono_emergencia_01, String telefono_emergencia_02, String telefono_emergencia_03, String telefono_emergencia_04, int trabaja, int profesion, int ocupacion, String centro_trabajo, String telefonoTrabajo, int tipoVivienda, int situacionVivienda, boolean servicio_agua, boolean servicio_desague, boolean servicio_luz, boolean servicio_apublico, int numero_pisos, int numero_dormitorios, String material_construccion, String vivienda_cuenta, String movilidad, boolean enfermedad_cronica, String enfermedad_descripcion, int tipodocumento, String numero_documento,  int grupoSangineo) {
        super(persona, aPaterno, aMaterno, nombres, direccion_ubigeo, tipo_transito, direccion_recidencial, direccion_referencial, direccion_ubigeo_pro, tipo_transito_pro, direccion_recidencial_pro, direccion_referencial_pro, telefono, celular, emailP, emailS, fecha_nacimiento, nacimiento_ubigeo, sexo, estado_civil, pais, telefono_emergencia_01, telefono_emergencia_02, telefono_emergencia_03, telefono_emergencia_04, trabaja, profesion, ocupacion, centro_trabajo, telefonoTrabajo, tipoVivienda, situacionVivienda, servicio_agua, servicio_desague, servicio_luz, servicio_apublico, numero_pisos, numero_dormitorios, material_construccion, vivienda_cuenta, movilidad, enfermedad_cronica, enfermedad_descripcion, tipodocumento, numero_documento, estadoRegistro, grupoSangineo);
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.institucion = institucion;
        this.periodo = periodo;
        this.carrera = carrera;
        this.malla = malla;
        this.curso = curso;
        this.seccion = seccion;
          this.tipoPersona = tipoPersona;
        this.persona = persona;
        this.mensaje = mensaje;
        this.estadoRegistro = estadoRegistro;
    }
    


    
    

    /**
     * @return the respuesta
     */
    public int getRespuesta() {
        return respuesta;
    }

    /**
     * @param respuesta the respuesta to set
     */
    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }

    /**
     * @return the pregunta
     */
    public int getPregunta() {
        return pregunta;
    }

    /**
     * @param pregunta the pregunta to set
     */
    public void setPregunta(int pregunta) {
        this.pregunta = pregunta;
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
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the estadoRegistro
     */
    public int getEstadoRegistro() {
        return estadoRegistro;
    }

    /**
     * @param estadoRegistro the estadoRegistro to set
     */
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    /**
     * @return the tipoPersona
     */
    public int getTipoPersona() {
        return tipoPersona;
    }

    /**
     * @param tipoPersona the tipoPersona to set
     */
    public void setTipoPersona(int tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
}
