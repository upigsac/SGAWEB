
package ENTIDAD;
import java.io.Serializable;
import java.util.Date;



public class personaC implements Serializable{
   
	private static final long serialVersionUID = 1L;
	private String persona;
    private String paterno="";
    private String materno="";
    private String nombres="";
    private String direccion_ubigeo;
 
    private int tipo_transito;
    private String direccion_recidencial;
    private String direccion_referencial;
    private String direccion_ubigeo_pro;
    private int tipo_transito_pro;
    private String direccion_recidencial_pro;
    private String direccion_referencial_pro;            
    private String telefono;
    private String celular;    
    private String emailP;
    private String emailS;
    private Date fecha_nacimiento;
    private String nacimiento_ubigeo;
    private int sexo;    
    private int grupo_sangineo;
    private int estado_civil;
    private int pais;
    private String telefono_emergencia_01;
    private String telefono_emergencia_02;
    private String telefono_emergencia_03;
    private String telefono_emergencia_04;
    private int trabaja;
    private int profesion;
    private int ocupacion;
    private String centro_trabajo;   
    private String telefonoTrabajo;
    private int tipoVivienda;
    private int situacionVivienda;
    private boolean servicio_agua;
    private boolean servicio_desague;
    private boolean servicio_luz;
    private boolean servicio_apublico;
    private int numero_pisos;
    private int numero_dormitorios;
    private String material_construccion;
    private String vivienda_cuenta;
    private String movilidad;
    private boolean enfermedad_cronica;
    private String enfermedad_descripcion;

    private int tipodocumento;
    private String numero_documento;
    private int nivel_educativo; 
    private int estadoRegistro;
    
    
    
   
    private int grupoSangineo;
    private String tipo_servicio;   
    private int condicion_laboral;
    private String cargo_desempeña;
    private int dedicacion;
    private int tipo_contrato;
    
    
    private String institucion_grado;
    private String mension_grado;
    private String anio_grado;
    
    private String ubigeoDepartamento;
    private String ubigeoProvincia;
    private String ubigeoDistrito;

    public personaC() {
    }

    public personaC(String persona, String aPaterno, String aMaterno, String nombres, String direccion_ubigeo, int tipo_transito, String direccion_recidencial, String direccion_referencial, String direccion_ubigeo_pro, int tipo_transito_pro, String direccion_recidencial_pro, String direccion_referencial_pro, String telefono, String celular, String emailP, String emailS, Date fecha_nacimiento, String nacimiento_ubigeo, int sexo, int estado_civil, int pais, String telefono_emergencia_01, String telefono_emergencia_02, String telefono_emergencia_03, String telefono_emergencia_04, int trabaja, int profesion, int ocupacion, String centro_trabajo, String telefonoTrabajo, int tipoVivienda, int situacionVivienda, boolean servicio_agua, boolean servicio_desague, boolean servicio_luz, boolean servicio_apublico, int numero_pisos, int numero_dormitorios, String material_construccion, String vivienda_cuenta, String movilidad, boolean enfermedad_cronica, String enfermedad_descripcion, int tipodocumento, String numero_documento, int estadoRegistro, int grupoSangineo) {
        this.persona = persona;
        this.paterno = aPaterno;
        this.materno = aMaterno;
        this.nombres = nombres;
        this.direccion_ubigeo = direccion_ubigeo;
        this.tipo_transito = tipo_transito;
        this.direccion_recidencial = direccion_recidencial;
        this.direccion_referencial = direccion_referencial;
        this.direccion_ubigeo_pro = direccion_ubigeo_pro;
        this.tipo_transito_pro = tipo_transito_pro;
        this.direccion_recidencial_pro = direccion_recidencial_pro;
        this.direccion_referencial_pro = direccion_referencial_pro;
        this.telefono = telefono;
        this.celular = celular;
        this.emailP = emailP;
        this.emailS = emailS;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nacimiento_ubigeo = nacimiento_ubigeo;
        this.sexo = sexo;
        this.estado_civil = estado_civil;
        this.pais = pais;
        this.telefono_emergencia_01 = telefono_emergencia_01;
        this.telefono_emergencia_02 = telefono_emergencia_02;
        this.telefono_emergencia_03 = telefono_emergencia_03;
        this.telefono_emergencia_04 = telefono_emergencia_04;
        this.trabaja = trabaja;
        this.profesion = profesion;
        this.ocupacion = ocupacion;
        this.centro_trabajo = centro_trabajo;
        this.telefonoTrabajo = telefonoTrabajo;
        this.tipoVivienda = tipoVivienda;
        this.situacionVivienda = situacionVivienda;
        this.servicio_agua = servicio_agua;
        this.servicio_desague = servicio_desague;
        this.servicio_luz = servicio_luz;
        this.servicio_apublico = servicio_apublico;
        this.numero_pisos = numero_pisos;
        this.numero_dormitorios = numero_dormitorios;
        this.material_construccion = material_construccion;
        this.vivienda_cuenta = vivienda_cuenta;
        this.movilidad = movilidad;
        this.enfermedad_cronica = enfermedad_cronica;
        this.enfermedad_descripcion = enfermedad_descripcion;
        this.tipodocumento = tipodocumento;
        this.numero_documento = numero_documento;
        this.estadoRegistro = estadoRegistro;
        this.grupoSangineo = grupoSangineo;
    }
    
    
    public String nombreCompletos(){
        return this.getPaterno() +" "+ this.getMaterno() +", "+this.nombres;
    }

   
    
    public String getNombres() {
        return nombres;
    }

    
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

  

   
    public String getTelefono() {
        return telefono;
    }

  
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

   
    public String getCelular() {
        return celular;
    }

   
    public void setCelular(String celular) {
        this.celular = celular;
    }

  
    public String getEmailP() {
        return emailP;
    }

    
    public void setEmailP(String emailP) {
        this.emailP = emailP;
    }

  
    public String getEmailS() {
        return emailS;
    }

   
    public void setEmailS(String emailS) {
        this.emailS = emailS;
    }

   
    public int getTipodocumento() {
        return tipodocumento;
    }

   
    public void setTipodocumento(int tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

  
   
    public String getTelefono_emergencia_04() {
        return telefono_emergencia_04;
    }

   
    public void setTelefono_emergencia_04(String telefono_emergencia_04) {
        this.telefono_emergencia_04 = telefono_emergencia_04;
    }

   
    public String getNumero_documento() {
        return numero_documento;
    }

   
    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

   
    public int getEstado_civil() {
        return estado_civil;
    }

    
    public void setEstado_civil(int estado_civil) {
        this.estado_civil = estado_civil;
    }

   
    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

   
    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    
    public int getOcupacion() {
        return ocupacion;
    }

    
    public void setOcupacion(int ocupacion) {
        this.ocupacion = ocupacion;
    }

   
    public String getCentro_trabajo() {
        return centro_trabajo;
    }

   
    public void setCentro_trabajo(String centro_trabajo) {
        this.centro_trabajo = centro_trabajo;
    }

  
    public int getSexo() {
        return sexo;
    }

    
    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

   
    public String getTelefonoTrabajo() {
        return telefonoTrabajo;
    }

  
    public void setTelefonoTrabajo(String telefonoTrabajo) {
        this.telefonoTrabajo = telefonoTrabajo;
    }

    
    public int getTipoVivienda() {
        return tipoVivienda;
    }

   
    public void setTipoVivienda(int tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    
    public int getSituacionVivienda() {
        return situacionVivienda;
    }

    
    public void setSituacionVivienda(int situacionVivienda) {
        this.situacionVivienda = situacionVivienda;
    }

   
    public int getGrupoSangineo() {
        return grupoSangineo;
    }

    
    public void setGrupoSangineo(int grupoSangineo) {
        this.grupoSangineo = grupoSangineo;
    }

    
    public String getDireccion_ubigeo() {
        return direccion_ubigeo;
    }

    
    public void setDireccion_ubigeo(String direccion_ubigeo) {
        this.direccion_ubigeo = direccion_ubigeo;
    }

   
    public int getTipo_transito() {
        return tipo_transito;
    }

  
    public void setTipo_transito(int tipo_transito) {
        this.tipo_transito = tipo_transito;
    }

   
    public String getDireccion_recidencial() {
        return direccion_recidencial;
    }

   
    public void setDireccion_recidencial(String direccion_recidencial) {
        this.direccion_recidencial = direccion_recidencial;
    }

    
    public String getDireccion_referencial() {
        return direccion_referencial;
    }

   
    public void setDireccion_referencial(String direccion_referencial) {
        this.direccion_referencial = direccion_referencial;
    }

   
    public String getDireccion_ubigeo_pro() {
        return direccion_ubigeo_pro;
    }

 
    public void setDireccion_ubigeo_pro(String direccion_ubigeo_pro) {
        this.direccion_ubigeo_pro = direccion_ubigeo_pro;
    }

    
    public int getTipo_transito_pro() {
        return tipo_transito_pro;
    }

  
    public void setTipo_transito_pro(int tipo_transito_pro) {
        this.tipo_transito_pro = tipo_transito_pro;
    }

   
    public String getDireccion_recidencial_pro() {
        return direccion_recidencial_pro;
    }

  
    public void setDireccion_recidencial_pro(String direccion_recidencial_pro) {
        this.direccion_recidencial_pro = direccion_recidencial_pro;
    }

   
    public String getDireccion_referencial_pro() {
        return direccion_referencial_pro;
    }

   
    public void setDireccion_referencial_pro(String direccion_referencial_pro) {
        this.direccion_referencial_pro = direccion_referencial_pro;
    }

    
    public int getPais() {
        return pais;
    }

   
    public void setPais(int pais) {
        this.pais = pais;
    }

   
    public String getTelefono_emergencia_01() {
        return telefono_emergencia_01;
    }

   
    public void setTelefono_emergencia_01(String telefono_emergencia_01) {
        this.telefono_emergencia_01 = telefono_emergencia_01;
    }

  
    public String getTelefono_emergencia_02() {
        return telefono_emergencia_02;
    }

    
    public void setTelefono_emergencia_02(String telefono_emergencia_02) {
        this.telefono_emergencia_02 = telefono_emergencia_02;
    }

  
    public String getTelefono_emergencia_03() {
        return telefono_emergencia_03;
    }

    
    public void setTelefono_emergencia_03(String telefono_emergencia_03) {
        this.telefono_emergencia_03 = telefono_emergencia_03;
    }

   
    public int getTrabaja() {
        return trabaja;
    }

    
    public void setTrabaja(int trabaja) {
        this.trabaja = trabaja;
    }

   
    public int getProfesion() {
        return profesion;
    }

    
    public void setProfesion(int profesion) {
        this.profesion = profesion;
    }

   
    public boolean isServicio_agua() {
        return servicio_agua;
    }

    
    public void setServicio_agua(boolean servicio_agua) {
        this.servicio_agua = servicio_agua;
    }

  
    public boolean isServicio_desague() {
        return servicio_desague;
    }

    
    public void setServicio_desague(boolean servicio_desague) {
        this.servicio_desague = servicio_desague;
    }

   
    public boolean isServicio_luz() {
        return servicio_luz;
    }

  
    public void setServicio_luz(boolean servicio_luz) {
        this.servicio_luz = servicio_luz;
    }

 
    public boolean isServicio_apublico() {
        return servicio_apublico;
    }

   
    public void setServicio_apublico(boolean servicio_apublico) {
        this.servicio_apublico = servicio_apublico;
    }

   
    public int getNumero_pisos() {
        return numero_pisos;
    }

   
    public void setNumero_pisos(int numero_pisos) {
        this.numero_pisos = numero_pisos;
    }

    
    public int getNumero_dormitorios() {
        return numero_dormitorios;
    }

   
    public void setNumero_dormitorios(int numero_dormitorios) {
        this.numero_dormitorios = numero_dormitorios;
    }

 
    public String getMaterial_construccion() {
        return material_construccion;
    }

 
    public void setMaterial_construccion(String material_construccion) {
        this.material_construccion = material_construccion;
    }

  
    public String getVivienda_cuenta() {
        return vivienda_cuenta;
    }

   
    public void setVivienda_cuenta(String vivienda_cuenta) {
        this.vivienda_cuenta = vivienda_cuenta;
    }

    
    public String getMovilidad() {
        return movilidad;
    }

    
    public void setMovilidad(String movilidad) {
        this.movilidad = movilidad;
    }

   
    public boolean isEnfermedad_cronica() {
        return enfermedad_cronica;
    }

    
    public void setEnfermedad_cronica(boolean enfermedad_cronica) {
        this.enfermedad_cronica = enfermedad_cronica;
    }

  
    public String getEnfermedad_descripcion() {
        return enfermedad_descripcion;
    }

   
    public void setEnfermedad_descripcion(String enfermedad_descripcion) {
        this.enfermedad_descripcion = enfermedad_descripcion;
    }

   
    public int getEstadoRegistro() {
        return estadoRegistro;
    }

    
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

   
    public String getNacimiento_ubigeo() {
        return nacimiento_ubigeo;
    }

    
    public void setNacimiento_ubigeo(String nacimiento_ubigeo) {
        this.nacimiento_ubigeo = nacimiento_ubigeo;
    }

  
    public int getGrupo_sangineo() {
        return grupo_sangineo;
    }

   
    public void setGrupo_sangineo(int grupo_sangineo) {
        this.grupo_sangineo = grupo_sangineo;
    }

    
    public String getTipo_servicio() {
        return tipo_servicio;
    }

   
    public void setTipo_servicio(String tipo_servicio) {
        this.tipo_servicio = tipo_servicio;
    }

   
    public int getCondicion_laboral() {
        return condicion_laboral;
    }

    
    public void setCondicion_laboral(int condicion_laboral) {
        this.condicion_laboral = condicion_laboral;
    }

    
    public int getDedicacion() {
        return dedicacion;
    }

   
    public void setDedicacion(int dedicacion) {
        this.dedicacion = dedicacion;
    }

   
    public int getTipo_contrato() {
        return tipo_contrato;
    }

 
    public void setTipo_contrato(int tipo_contrato) {
        this.tipo_contrato = tipo_contrato;
    }

   
    public String getCargo_desempeña() {
        return cargo_desempeña;
    }

  
    public void setCargo_desempeña(String cargo_desempeña) {
        this.cargo_desempeña = cargo_desempeña;
    }

   
    public int getNivel_educativo() {
        return nivel_educativo;
    }

  
    public void setNivel_educativo(int nivel_educativo) {
        this.nivel_educativo = nivel_educativo;
    }

   
    public String getInstitucion_grado() {
        return institucion_grado;
    }

    
    public void setInstitucion_grado(String institucion_grado) {
        this.institucion_grado = institucion_grado;
    }

    
    public String getMension_grado() {
        return mension_grado;
    }

    
    public void setMension_grado(String mension_grado) {
        this.mension_grado = mension_grado;
    }

    
    public String getAnio_grado() {
        return anio_grado;
    }

   
    public void setAnio_grado(String anio_grado) {
        this.anio_grado = anio_grado;
    }

    
    public String getPaterno() {
        return paterno;
    }

   
    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    
    public String getMaterno() {
        return materno;
    }

   
    public void setMaterno(String materno) {
        this.materno = materno;
    }

   
    public String getPersona() {
        return persona;
    }

   
    public void setPersona(String persona) {
        this.persona = persona;
    }

    
    public String getUbigeoDepartamento() {
    	
        return ubigeoDepartamento;
    }

   
    public void setUbigeoDepartamento(String ubigeoDepartamento) {
        this.ubigeoDepartamento = ubigeoDepartamento;
    }

   
    public String getUbigeoProvincia() {
        return ubigeoProvincia;
    }

    
    public void setUbigeoProvincia(String ubigeoProvincia) {
        this.ubigeoProvincia = ubigeoProvincia;
    }

    
    public String getUbigeoDistrito() {
        return ubigeoDistrito;
    }

   
    public void setUbigeoDistrito(String ubigeoDistrito) {
        this.ubigeoDistrito = ubigeoDistrito;
    }
    
    
    

    
    
 
      
}
