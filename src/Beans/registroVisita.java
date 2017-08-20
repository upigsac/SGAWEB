
package Beans;

import DAO.registroVisitaDAO;
import ENTIDAD.oficinaC;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;


@ManagedBean(name = "rvisitaB")
@ViewScoped
public class registroVisita  {

    registroVisitaDAO rvdao = null;

    private registroVisitaC entidad;
    private registroVisitaC consultado;
    private boolean chck_ingreso;
    private boolean chck_salida;
    private boolean chck_visita;
    private boolean chck_no_personal;

    private String nombrePersonal;

    private String avisoNoDNI;

    private String busquedaDNI;
    private String busquedaApe_nom;
    private String busquedaOficina;

    private List<oficinaC> listadoOficina;
    private List<registroVisita.personal_oficina_cargo> listadoOficinaPersonal;
    private List<registroVisita.personal_oficina_cargo> listadoConsultadoOficinaPersonal;
    private List<registroVisita.registroVisitaC> listadoVisitas;
    private LinkedHashMap<String, String> listadoFechaVisitas;

    @PostConstruct
    public void init() {
        limpiar();
        //listarOficinas();
    }
        
    public void limpiar() {
        avisoNoDNI = "";        
        entidad = new registroVisitaC();
        //consultado = new registroVisitaC();
        chck_ingreso = false;
        chck_salida = false;
        chck_visita = false;
        chck_no_personal = false;
        listarOficinas();
        listadoOficinaPersonal = new ArrayList<>();
        visitasDNI();
        System.out.println("termino de limpiar");
    }

    public String obtenerHora() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return sdf.format(cal.getTime());
    }

    public void obtenerHoraEntrada() {
        entidad.hora_ingreso = (obtenerHora());
    }

    public void obtenerHoraSalida() {
        entidad.hora_salida = (obtenerHora());
    }

    public void listarOficinas() {
        rvdao = new registroVisitaDAO();
        listadoOficina = rvdao.listarOficinas();
        //System.out.println("listar oficinas");
    }

    public void listarAutorizacionXOficina() {
        rvdao = new registroVisitaDAO();
        listadoOficinaPersonal = rvdao.listarPersonalDeOficina(entidad.oficina);
        System.out.println("personal de oficina " + entidad.oficina + " cantidad " + listadoOficinaPersonal.size());
    }

    public void pertenecia_dato() {
        if (!entidad.pertenencia) {
            entidad.dato_pertenencia = null;
        }
    }

    public void registrar() {
        System.out.println(entidad.id);
        System.out.println(entidad.dni);
        System.out.println(entidad.apellidos_nombres);
        System.out.println(entidad.oficina);
        System.out.println(entidad.autorizacion);
        System.out.println(entidad.pertenencia);
        System.out.println(entidad.dato_pertenencia);
        System.out.println(entidad.motivo);
        System.out.println(entidad.hora_ingreso);
        System.out.println(entidad.hora_salida);

        //si chck no personal es verdad entonces el personal que elegio sera null        
        //System.out.println(chck_no_personal);
        //System.out.println(nombrePersonal);
        rvdao = new registroVisitaDAO();
        //si es falso entonces es un registro con hora_entrada
        //si es verdad entonces registra la hora_salida
        rvdao.registrarVisita(chck_visita, entidad);
        visitasDNI(entidad.dni);
        datosConsultaVisita(rvdao.idDelRegistrado(entidad.dni));
        datosConsultaDetalle(entidad.oficina, entidad.dni);
        limpiar();
    }

    public void datosXDNI() {
        rvdao = new registroVisitaDAO();
        if (rvdao.ultimaVisita(entidad.dni) == null) {
            System.out.println("no hay ultima visita en el dia");

            String personaDNI = rvdao.nombreXDNI(entidad.dni);

            if (entidad.dni == null || entidad.dni.equals("")) {
                entidad.apellidos_nombres = null;
                avisoNoDNI = "Ingrese un DNI, o agregue uno de no existir";
            } else if (personaDNI == null) {
                entidad.apellidos_nombres = null;
                avisoNoDNI = "No existe el DNI, ingrese apellidos y nombres";
            } else {
                entidad.apellidos_nombres = personaDNI;
            }

            System.out.println(personaDNI == null ? "no hay persona con dni" : "si hay persona con dni");
        } else {
            System.out.println("dni tiene ultima visita");
            entidad = rvdao.ultimaVisita(entidad.dni);
            listarAutorizacionXOficina();
            chck_ingreso = true;
            chck_visita = true;
        }
        RequestContext.getCurrentInstance().execute("document.getElementById('frmRegistroVisita:txtapenom').focus()");
    }

    public void visitasDNI() {
        rvdao = new registroVisitaDAO();
        //System.out.println("busqueda " + busquedaDNI == null ? "%" : busquedaDNI + " " + busquedaApe_nom == null ? "%" : busquedaApe_nom.trim() + " " + busquedaOficina == null ? "%" : busquedaOficina);
        listadoVisitas = rvdao.listarVisitas(busquedaDNI == null ? "%" : busquedaDNI, busquedaApe_nom == null ? "%" : busquedaApe_nom.trim(), busquedaOficina == null ? "%" : busquedaOficina);
    }

    public void visitasDNI(String dni) {
        rvdao = new registroVisitaDAO();
        listadoVisitas = rvdao.listarVisitas(dni, "%", "%");
    }

    public void datosConsultaDetalle() {
        rvdao = new registroVisitaDAO();
        listadoConsultadoOficinaPersonal = rvdao.listarPersonalDeOficina(consultado.oficina);
        listadoFechaVisitas = rvdao.fechasDeVisita(consultado.dni);
    }

    public void datosConsultaDetalle(int oficina, String dni) {
        rvdao = new registroVisitaDAO();
        System.out.println("oficina " + oficina + " dni " + dni);
        listadoConsultadoOficinaPersonal = rvdao.listarPersonalDeOficina(oficina);
        listadoFechaVisitas = rvdao.fechasDeVisita(dni);
    }

    public void datosConsultaVisita() {
        System.out.println("en datos consulta fecha " + consultado.id + " " + consultado.oficina);
        rvdao = new registroVisitaDAO();
        consultado = rvdao.datosVisita(consultado.id);
        listadoConsultadoOficinaPersonal = rvdao.listarPersonalDeOficina(consultado.oficina);
    }

    public void datosConsultaVisita(String id) {
        System.out.println("en datos consulta param " + id);
        rvdao = new registroVisitaDAO();
        consultado = rvdao.datosVisita(id);
        listadoConsultadoOficinaPersonal = rvdao.listarPersonalDeOficina(consultado.oficina);
    }
    public registroVisitaC getEntidad() {
        return entidad;
    }
    public void setEntidad(registroVisitaC entidad) {
        this.entidad = entidad;
    }
    public boolean isChck_ingreso() {
        return chck_ingreso;
    }
    public void setChck_ingreso(boolean chck_ingreso) {
        this.chck_ingreso = chck_ingreso;
    }
    public boolean isChck_salida() {
        return chck_salida;
    }
    public void setChck_salida(boolean chck_salida) {
        this.chck_salida = chck_salida;
    }
    public List<registroVisita.personal_oficina_cargo> getListadoOficinaPersonal() {
        if (listadoOficinaPersonal == null) {
            listadoOficinaPersonal = new ArrayList<>();
        }
        return listadoOficinaPersonal;
    }
    public void setListadoOficinaPersonal(List<registroVisita.personal_oficina_cargo> listadoOficinaPersonal) {
        this.listadoOficinaPersonal = listadoOficinaPersonal;
    }
    public List<oficinaC> getListadoOficina() {
        if (listadoOficina == null) {
            listadoOficina = new ArrayList<>();
        }
        return listadoOficina;
    }
    public void setListadoOficina(List<oficinaC> listadoOficina) {
        this.listadoOficina = listadoOficina;
    }
    public boolean isChck_visita() {
        return chck_visita;
    }
    public void setChck_visita(boolean chck_visita) {
        this.chck_visita = chck_visita;
    }
    public boolean isChck_no_personal() {
        return chck_no_personal;
    }
    public void setChck_no_personal(boolean chck_no_personal) {
        this.chck_no_personal = chck_no_personal;
    }
    public String getNombrePersonal() {
        return nombrePersonal;
    }
    public void setNombrePersonal(String nombrePersonal) {
        this.nombrePersonal = nombrePersonal;
    }
    public String getBusquedaDNI() {
        return busquedaDNI;
    }
    public void setBusquedaDNI(String busquedaDNI) {
        this.busquedaDNI = busquedaDNI;
    }
    public List<registroVisita.registroVisitaC> getListadoVisitas() {
        if (listadoVisitas == null) {
            listadoVisitas = new ArrayList<>();
        }
        return listadoVisitas;
    }
    public void setListadoVisitas(List<registroVisita.registroVisitaC> listadoVisitas) {
        this.listadoVisitas = listadoVisitas;
    }
    public registroVisitaC getConsultado() {
        /*if(consultado == null){
         consultado = new registroVisitaC();
         }*/
        return consultado;
    }

  
    public void setConsultado(registroVisitaC consultado) {
        this.consultado = consultado;
    }
    public LinkedHashMap<String, String> getListadoFechaVisitas() {
        return listadoFechaVisitas;
    }
    public void setListadoFechaVisitas(LinkedHashMap<String, String> listadoFechaVisitas) {
        this.listadoFechaVisitas = listadoFechaVisitas;
    }
    public List<registroVisita.personal_oficina_cargo> getListadoConsultadoOficinaPersonal() {
        if (listadoConsultadoOficinaPersonal == null) {
            listadoConsultadoOficinaPersonal = new ArrayList<>();
        }
        return listadoConsultadoOficinaPersonal;
    }
    public void setListadoConsultadoOficinaPersonal(List<registroVisita.personal_oficina_cargo> listadoConsultadoOficinaPersonal) {
        this.listadoConsultadoOficinaPersonal = listadoConsultadoOficinaPersonal;
    }
    public String getBusquedaOficina() {
        return busquedaOficina;
    }
    public void setBusquedaOficina(String busquedaOficina) {
        this.busquedaOficina = busquedaOficina;
    }
    public String getBusquedaApe_nom() {
        return busquedaApe_nom;
    }
    public void setBusquedaApe_nom(String busquedaApe_nom) {
        this.busquedaApe_nom = busquedaApe_nom;
    }
    public String getAvisoNoDNI() {
        return avisoNoDNI;
    }
    public void setAvisoNoDNI(String avisoNoDNI) {
        this.avisoNoDNI = avisoNoDNI;
    }

    public static class registroVisitaC  {

        private String id;
        private String dni;
        private String apellidos_nombres;
        //private int n_visita;
        private String fecha_visita;
        private int oficina;
        private String oficina_texto;
        private String autorizacion;
        private String autorizacion_nombre;
        private String motivo;
        private boolean pertenencia;
        private String dato_pertenencia;
        private String hora_ingreso;
        private String hora_salida;
        private int estado_registro;

      
        public String getDni() {
            return dni;
        }
        public void setDni(String dni) {
            this.dni = dni;
        }
        public String getApellidos_nombres() {
            return apellidos_nombres;
        }
        public void setApellidos_nombres(String apellidos_nombres) {
            this.apellidos_nombres = apellidos_nombres;
        }
        public String getAutorizacion() {
            return autorizacion;
        }
        public void setAutorizacion(String autorizacion) {
            this.autorizacion = autorizacion;
        }
        public String getMotivo() {
            return motivo;
        }
        public void setMotivo(String motivo) {
            this.motivo = motivo;
        }
        public String getHora_ingreso() {
            return hora_ingreso;
        }
        public void setHora_ingreso(String hora_ingreso) {
            this.hora_ingreso = hora_ingreso;
        }
        public String getHora_salida() {
            return hora_salida;
        }
        public void setHora_salida(String hora_salida) {
            this.hora_salida = hora_salida;
        }
        public boolean isPertenencia() {
            return pertenencia;
        }
        public void setPertenencia(boolean pertenencia) {
            this.pertenencia = pertenencia;
        }
        public String getDato_pertenencia() {
            return dato_pertenencia;
        }
        public void setDato_pertenencia(String dato_pertenencia) {
            this.dato_pertenencia = dato_pertenencia;
        }
        public int getOficina() {
            return oficina;
        }
        public void setOficina(int oficina) {
            this.oficina = oficina;
        }
        public int getEstado_registro() {
            return estado_registro;
        }
        public void setEstado_registro(int estado_registro) {
            this.estado_registro = estado_registro;
        }
        public String getOficina_texto() {
            return oficina_texto;
        }
        public void setOficina_texto(String oficina_texto) {
            this.oficina_texto = oficina_texto;
        }
        public String getAutorizacion_nombre() {
            return autorizacion_nombre;
        }
        public void setAutorizacion_nombre(String autorizacion_nombre) {
            this.autorizacion_nombre = autorizacion_nombre;
        }
        public String getFecha_visita() {
            return fecha_visita;
        }
        public void setFecha_visita(String fecha_visita) {
            this.fecha_visita = fecha_visita;
        }
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
    }

    public static class personal_oficina_cargo  {

        private String personal;
        private String persona;
        private String nombre_completo;
        private String institucion;
        private int oficina;
        private String oficina_texto;
        private int cargo;
        private String cargo_texto;
        private int estado_registro;

      
        public String getPersonal() {
            return personal;
        }

        
        public void setPersonal(String personal) {
            this.personal = personal;
        }
        public String getPersona() {
            return persona;
        }
        public void setPersona(String persona) {
            this.persona = persona;
        }
        public String getNombre_completo() {
            return nombre_completo;
        }
        public void setNombre_completo(String nombre_completo) {
            this.nombre_completo = nombre_completo;
        }
        public String getInstitucion() {
            return institucion;
        }
        public void setInstitucion(String institucion) {
            this.institucion = institucion;
        }
        public int getOficina() {
            return oficina;
        }
        public void setOficina(int oficina) {
            this.oficina = oficina;
        }
        public String getOficina_texto() {
            return oficina_texto;
        }
        public void setOficina_texto(String oficina_texto) {
            this.oficina_texto = oficina_texto;
        }
        public int getCargo() {
            return cargo;
        }
        public void setCargo(int cargo) {
            this.cargo = cargo;
        }
        public String getCargo_texto() {
            return cargo_texto;
        }
        public void setCargo_texto(String cargo_texto) {
            this.cargo_texto = cargo_texto;
        }
        public int getEstado_registro() {
            return estado_registro;
        }
        public void setEstado_registro(int estado_registro) {
            this.estado_registro = estado_registro;
        }
    }

}
