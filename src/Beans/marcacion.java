
package Beans;

import DAO.carrerasDAO;
import DAO.cursoDAO;
import DAO.marcacionDocenteDAO;
import DAO.seccionDAO;
import DAO.personaDAO;


import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "marcacionB")
@ViewScoped
public class marcacion  {

    carrerasDAO cardao;
    cursoDAO curdao;
    seccionDAO secdao;
    marcacionDocenteDAO mardao;
    personaDAO personadao;

    /*FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("seleccionado").toString();*/
    private String periodo = "20141";

    private String usuarioDocente;

    //BUSQUEDA
    private String apaterno;
    private String amaterno;
    private String docenteNombre;
    private String nombreCompleto;

    private List<marcacionDocenteC> listadoDocentes;
    private marcacionDocenteC seleccionBusqueda;

    //ingreso de docente
    private String temaDesarrollo;
    private int semana;
    private int sesionClase;
    private int cantAlumnos;
    //ingreso de docente

    private List<marcacionDocenteC> listadoMarcacionDocente;
    private List<marcacionDocenteC> listadoSemanaDocente;

    public void cerrar() {
        apaterno = "";
        amaterno = "";
        docenteNombre = "";
        usuarioDocente = "";
        temaDesarrollo = null;
        nombreCompleto = "";
        cantAlumnos = 0;
        semana = 0;
        sesionClase = 0;
        seleccionBusqueda = null;
        listadoDocentes = new ArrayList<>();
        listadoMarcacionDocente = new ArrayList<>();
        listadoSemanaDocente = new ArrayList<>();
    }

    public void buscoUsuarioDocente() {
        personadao = new personaDAO();
        //seleccionBusqueda = personadao.BuscarCodigoDocente(usuarioDocente);             
        listadoTablaMarcacionDocente();
    }

    public void busquedaDocentes() {
        personadao = new personaDAO();
        //listadoDocentes = personadao.BuscaDocente(nombreCompleto.trim());
        System.out.println(getListadoDocentes().size());
    }

    public void listadoTablaMarcacionDocente() {
        mardao = new marcacionDocenteDAO();
        if (seleccionBusqueda != null) {
            System.out.println(seleccionBusqueda.getCpersonal().toString());
            listadoMarcacionDocente = mardao.listadoMarcacionDocente(seleccionBusqueda.getCpersonal().toString());
            System.out.println("tamaño marcacion caso 1 " + listadoMarcacionDocente.size());

        } else {
            System.out.println("problemas en listado 1");
        }
    }

    public void listadoTodaSemanaDocente() {
        mardao = new marcacionDocenteDAO();
        if (seleccionBusqueda != null) {
            listadoSemanaDocente = mardao.listadoCompletoSemanaDocente(seleccionBusqueda.getCpersonal().toString());
            System.out.println("tamaño semana caso 2 " + listadoMarcacionDocente.size());
        } else {
            System.out.println("problemas en listado 2");
        }
    }

    //*********************************************************************************************************************************************************
    public void ingreso(marcacionDocenteC e) {

        if (semana == 0 || sesionClase == 0 || temaDesarrollo == null || temaDesarrollo.equals("")) {
            //RequestContext.getCurrentInstance().execute("alert('Debe ingresar semana,sesion y/o tema de Desarrollo')");
            //limpio
            temaDesarrollo = null;
            semana = 0;
            sesionClase = 0;
        } else {
            //AQUI ENTRO SOLO SI COMPLETO LOS CAMPOS
            //entrada 1 para registro                                              
            int salida = mardao.ingresoRegistroMarcacionDocente(e, temaDesarrollo, semana, sesionClase, cantAlumnos, 1);
            if (salida == 0) {
                System.out.println("entrada - fuera de rango");
                //RequestContext.getCurrentInstance().execute("msg('Entrada Fuera de Rango de Tolerancia','alerta')");                
                //RequestContext.getCurrentInstance().execute("alert('Entrada Fuera de Rango de Tolerancia')");
            } else if (salida == 1) {
                System.out.println("entrada - registro");
                //RequestContext.getCurrentInstance().execute("alert('Registro Entrada')");
            } else if (salida == 2) {
                System.out.println("entrada - fuera de rango");
                //RequestContext.getCurrentInstance().execute("msg('Aun no se encuentra dentro del rango de tolerancia','alerta')");
                //RequestContext.getCurrentInstance().execute("alert('Aun no se encuentra dentro del rango de tolerancia')");
            } else if (salida == 99) {
                System.out.println("error en entrada if");
            }
            listadoTablaMarcacionDocente();
            //limpio
            temaDesarrollo = null;
            semana = 0;
            sesionClase = 0;
        }

        /*
         //ip cliente**********************************************************************************************************************
         HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
         String ipAddress = req.getHeader("X-FORWARDED-FOR");  
                
         if (ipAddress == null) {  
         ipAddress = req.getRemoteAddr();  
         System.out.println("usuario "+ipAddress);
         byte[] clienteIpByte = ipAddress.getBytes();
         long resultado = 0;
         for (byte octet : clienteIpByte) {
         resultado <<= 8;
         resultado |= octet & 0xff;
         }
         System.out.println("ip cliente en bytes "+resultado);
        
         }else{
         System.out.println("usuario oculto "+ipAddress);
         }
         //ip cliente***********************************************************************************************************************
         */
        /*
         //ip servidor************************************************************************************************************************
         InetAddress ipServer;
         try {
         ipServer=InetAddress.getLocalHost();
         System.out.println("servidor "+ipServer.getHostAddress());
         byte[] servidorIpByte =ipServer.getAddress();            
         long resultado = 0;
         for (byte octet : servidorIpByte) {
         resultado <<= 8;
         resultado |= octet & 0xff;
         }
         System.out.println("ip servidor en bytes "+resultado);
            
         } catch (UnknownHostException ex) {
         Logger.getLogger(marcacion.class.getName()).log(Level.SEVERE, null, ex);
         }
         //ip servidor*****************************************************************************************************************************
         */
    }

    public void salida(marcacionDocenteC e) {

        if (cantAlumnos == 0) {
            //RequestContext.getCurrentInstance().execute("alert('Debe ingresar la cantidad de Alumnos')");
            cantAlumnos = 0;
        } else {
            //DEBE INGRESAR LOS ALUMNOS PARA REGISTRAR SALIDA
            int salida = mardao.ingresoRegistroMarcacionDocente(e, null, 0, 0, cantAlumnos, 2);
            if (salida == 0) {
                System.out.println("salida - no tiene entrada o esta fuera de rango");
                //RequestContext.getCurrentInstance().execute("msg('No ha registrado su entrada o su salida esta fuera del rango','alerta')");
                //RequestContext.getCurrentInstance().execute("alert('No ha registrado su entrada o su salida esta fuera del rango')");
            } else if (salida == 1) {
                System.out.println("registro salida");
                //RequestContext.getCurrentInstance().execute("msg('Aun no puede registrar su salida','alerta')");
                //RequestContext.getCurrentInstance().execute("alert('Registro Salida')");
            } else if (salida == 2) {
                System.out.println("registro salida mas alla de los 10 min");
                //RequestContext.getCurrentInstance().execute("msg('Aun no puede registrar su salida','alerta')");
                //RequestContext.getCurrentInstance().execute("alert('Registro Salida')");
            } else if (salida == 3) {
                System.out.println("salida - no tiene entrada o esta fuera de rango");
                //RequestContext.getCurrentInstance().execute("msg('Aun no puede registrar su salida','alerta')");
                //RequestContext.getCurrentInstance().execute("alert('Aun no puede registrar su hora de salida')");
            }
            listadoTablaMarcacionDocente();
            //limpio
            cantAlumnos = 0;
        }

        /*
         //ip cliente
         HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
         String ipAddress = req.getHeader("X-FORWARDED-FOR");  
         if (ipAddress == null) {  
         ipAddress = req.getRemoteAddr();  
         System.out.println("usuario "+ipAddress);           
         }else{
         System.out.println("usuario oculto "+ipAddress);
         }
         //ip cliente
         */
        /*
         //ip servidor
         InetAddress ipServer;
         try {
         ipServer=InetAddress.getLocalHost();
         System.out.println("servidor "+ipServer.getHostAddress());                
         } catch (UnknownHostException ex) {
         Logger.getLogger(marcacion.class.getName()).log(Level.SEVERE, null, ex);
         } 
         //ip servidor
         */
    }

    //*********************************************************************************************************************************************************
    /**
     * @return the periodo
     */
    public String getPeriodo() {
        return periodo;
    }

    /**
     * @return the listadoMarcacionDocente
     */
    public List<marcacionDocenteC> getListadoMarcacionDocente() {
        if (listadoMarcacionDocente == null) {
            listadoMarcacionDocente = new ArrayList<>();
        }
        return listadoMarcacionDocente;
    }

    /**
     * @param listadoMarcacionDocente the listadoMarcacionDocente to set
     */
    public void setListadoMarcacionDocente(List<marcacionDocenteC> listadoMarcacionDocente) {
        this.listadoMarcacionDocente = listadoMarcacionDocente;
    }

    /**
     * @return the temaDesarrollo
     */
    public String getTemaDesarrollo() {
        return temaDesarrollo;
    }

    /**
     * @param temaDesarrollo the temaDesarrollo to set
     */
    public void setTemaDesarrollo(String temaDesarrollo) {
        this.temaDesarrollo = temaDesarrollo;
    }

    /**
     * @return the semana
     */
    public int getSemana() {
        return semana;
    }

    /**
     * @param semana the semana to set
     */
    public void setSemana(int semana) {
        this.semana = semana;
    }

    /**
     * @return the sesionClase
     */
    public int getSesionClase() {
        return sesionClase;
    }

    /**
     * @param sesionClase the sesionClase to set
     */
    public void setSesionClase(int sesionClase) {
        this.sesionClase = sesionClase;
    }

    /**
     * @return the apaterno
     */
    public String getApaterno() {
        return apaterno;
    }

    /**
     * @param apaterno the apaterno to set
     */
    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    /**
     * @return the amaterno
     */
    public String getAmaterno() {
        return amaterno;
    }

    /**
     * @param amaterno the amaterno to set
     */
    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    /**
     * @return the docenteNombre
     */
    public String getDocenteNombre() {
        return docenteNombre;
    }

    /**
     * @param docenteNombre the docenteNombre to set
     */
    public void setDocenteNombre(String docenteNombre) {
        this.docenteNombre = docenteNombre;
    }

    /*
     public List<personaC> getListadoDocentes() {
     if (listadoDocentes == null) {
     listadoDocentes = new ArrayList<>();
     }
     return listadoDocentes;
     }
   
     public void setListadoDocentes(List<personaC> listadoDocentes) {
     this.listadoDocentes = listadoDocentes;
     }
     */
    /*
     public personaC getSeleccionBusqueda() {
     return seleccionBusqueda;
     }

   
     public void setSeleccionBusqueda(personaC seleccionBusqueda) {
     this.seleccionBusqueda = seleccionBusqueda;
     }
     */
    /**
     * @return the listadoSemanaDocente
     */
    public List<marcacionDocenteC> getListadoSemanaDocente() {
        if (listadoSemanaDocente == null) {
            listadoSemanaDocente = new ArrayList<>();
        }
        return listadoSemanaDocente;
    }

    /**
     * @param listadoSemanaDocente the listadoSemanaDocente to set
     */
    public void setListadoSemanaDocente(List<marcacionDocenteC> listadoSemanaDocente) {
        this.listadoSemanaDocente = listadoSemanaDocente;
    }

    /**
     * @return the cantAlumnos
     */
    public int getCantAlumnos() {
        return cantAlumnos;
    }

    /**
     * @param cantAlumnos the cantAlumnos to set
     */
    public void setCantAlumnos(int cantAlumnos) {
        this.cantAlumnos = cantAlumnos;
    }

    /**
     * @return the usuarioDocente
     */
    public String getUsuarioDocente() {
        return usuarioDocente;
    }

    /**
     * @param usuarioDocente the usuarioDocente to set
     */
    public void setUsuarioDocente(String usuarioDocente) {
        this.usuarioDocente = usuarioDocente;
    }

    /**
     * @return the nombreCompleto
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * @param nombreCompleto the nombreCompleto to set
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * @return the seleccionBusqueda
     */
    public marcacionDocenteC getSeleccionBusqueda() {
        return seleccionBusqueda;
    }

    /**
     * @param seleccionBusqueda the seleccionBusqueda to set
     */
    public void setSeleccionBusqueda(marcacionDocenteC seleccionBusqueda) {
        this.seleccionBusqueda = seleccionBusqueda;

    }

    /**
     * @return the listadoDocentes
     */
    public List<marcacionDocenteC> getListadoDocentes() {
        if (listadoDocentes == null) {
            listadoDocentes = new ArrayList<>();
        }
        return listadoDocentes;
    }

    /**
     * @param listadoDocentes the listadoDocentes to set
     */
    public void setListadoDocentes(List<marcacionDocenteC> listadoDocentes) {
        this.listadoDocentes = listadoDocentes;
    }

    //************************************************************************************************************************************************    
    public static class marcacionDocenteC {

        private int institucion;
        private int dia;
        private String dia_texto;
        private String fecha_ingreso;
        private String inicioClase;
        private String finClase;
        private String cpersonal;
        private String docente;
        private String turno;
        private String curso;
        private String desc_curso;
        private String ciclo;
        private String carrera;
        private String desc_carrera;
        private String aula;
        private int nalumnos;
        private int totalHoras;
        private int hora_acu;
        private int tardanza_acu;
        private int estado_registro;
        private String desc_estado_registro;
        private String temaDesarrollo;
        private String horaEntrada;
        private String horaSalida;
        private String tardanza;
        private Integer semana;
        private Integer sesion_clase;

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
         * @return the dia
         */
        public int getDia() {
            return dia;
        }

        /**
         * @param dia the dia to set
         */
        public void setDia(int dia) {
            this.dia = dia;
        }

        /**
         * @return the inicioClase
         */
        public String getInicioClase() {
            return inicioClase;
        }

        /**
         * @param inicioClase the inicioClase to set
         */
        public void setInicioClase(String inicioClase) {
            this.inicioClase = inicioClase;
        }

        /**
         * @return the finClase
         */
        public String getFinClase() {
            return finClase;
        }

        /**
         * @param finClase the finClase to set
         */
        public void setFinClase(String finClase) {
            this.finClase = finClase;
        }

        /**
         * @return the cpersonal
         */
        public String getCpersonal() {
            return cpersonal;
        }

        /**
         * @param cpersonal the cpersonal to set
         */
        public void setCpersonal(String cpersonal) {
            this.cpersonal = cpersonal;
        }

        /**
         * @return the docente
         */
        public String getDocente() {
            return docente;
        }

        /**
         * @param docente the docente to set
         */
        public void setDocente(String docente) {
            this.docente = docente;
        }

        /**
         * @return the turno
         */
        public String getTurno() {
            return turno;
        }

        /**
         * @param turno the turno to set
         */
        public void setTurno(String turno) {
            this.turno = turno;
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
         * @return the desc_curso
         */
        public String getDesc_curso() {
            return desc_curso;
        }

        /**
         * @param desc_curso the desc_curso to set
         */
        public void setDesc_curso(String desc_curso) {
            this.desc_curso = desc_curso;
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
         * @return the desc_carrera
         */
        public String getDesc_carrera() {
            return desc_carrera;
        }

        /**
         * @param desc_carrera the desc_carrera to set
         */
        public void setDesc_carrera(String desc_carrera) {
            this.desc_carrera = desc_carrera;
        }

        /**
         * @return the aula
         */
        public String getAula() {
            return aula;
        }

        /**
         * @param aula the aula to set
         */
        public void setAula(String aula) {
            this.aula = aula;
        }

        /**
         * @return the nalumnos
         */
        public int getNalumnos() {
            return nalumnos;
        }

        /**
         * @param nalumnos the nalumnos to set
         */
        public void setNalumnos(int nalumnos) {
            this.nalumnos = nalumnos;
        }

        /**
         * @return the totalHoras
         */
        public int getTotalHoras() {
            return totalHoras;
        }

        /**
         * @param totalHoras the totalHoras to set
         */
        public void setTotalHoras(int totalHoras) {
            this.totalHoras = totalHoras;
        }

        /**
         * @return the estado_registro
         */
        public int getEstado_registro() {
            return estado_registro;
        }

        /**
         * @param estado_registro the estado_registro to set
         */
        public void setEstado_registro(int estado_registro) {
            this.estado_registro = estado_registro;
        }

        /**
         * @return the temaDesarrollo
         */
        public String getTemaDesarrollo() {
            return temaDesarrollo;
        }

        /**
         * @param temaDesarrollo the temaDesarrollo to set
         */
        public void setTemaDesarrollo(String temaDesarrollo) {
            this.temaDesarrollo = temaDesarrollo;
        }

        /**
         * @return the horaEntrada
         */
        public String getHoraEntrada() {
            return horaEntrada;
        }

        /**
         * @param horaEntrada the horaEntrada to set
         */
        public void setHoraEntrada(String horaEntrada) {
            this.horaEntrada = horaEntrada;
        }

        /**
         * @return the horaSalida
         */
        public String getHoraSalida() {
            return horaSalida;
        }

        /**
         * @param horaSalida the horaSalida to set
         */
        public void setHoraSalida(String horaSalida) {
            this.horaSalida = horaSalida;
        }

        /**
         * @return the tardanza
         */
        public String getTardanza() {
            return tardanza;
        }

        /**
         * @param tardanza the tardanza to set
         */
        public void setTardanza(String tardanza) {
            this.tardanza = tardanza;
        }

        /**
         * @return the semana
         */
        public Integer getSemana() {
            return semana;
        }

        /**
         * @param semana the semana to set
         */
        public void setSemana(Integer semana) {
            this.semana = semana;
        }

        /**
         * @return the sesion_clase
         */
        public Integer getSesion_clase() {
            return sesion_clase;
        }

        /**
         * @param sesion_clase the sesion_clase to set
         */
        public void setSesion_clase(Integer sesion_clase) {
            this.sesion_clase = sesion_clase;
        }

        /**
         * @return the dia_texto
         */
        public String getDia_texto() {
            return dia_texto;
        }

        /**
         * @param dia_texto the dia_texto to set
         */
        public void setDia_texto(String dia_texto) {
            this.dia_texto = dia_texto;
        }

        /**
         * @return the fecha_ingreso
         */
        public String getFecha_ingreso() {
            return fecha_ingreso;
        }

        /**
         * @param fecha_ingreso the fecha_ingreso to set
         */
        public void setFecha_ingreso(String fecha_ingreso) {
            this.fecha_ingreso = fecha_ingreso;
        }

        /**
         * @return the desc_estado_registro
         */
        public String getDesc_estado_registro() {
            return desc_estado_registro;
        }

        /**
         * @param desc_estado_registro the desc_estado_registro to set
         */
        public void setDesc_estado_registro(String desc_estado_registro) {
            this.desc_estado_registro = desc_estado_registro;
        }

        /**
         * @return the hora_acu
         */
        public int getHora_acu() {
            return hora_acu;
        }

        /**
         * @param hora_acu the hora_acu to set
         */
        public void setHora_acu(int hora_acu) {
            this.hora_acu = hora_acu;
        }

        /**
         * @return the tardanza_acu
         */
        public int getTardanza_acu() {
            return tardanza_acu;
        }

        /**
         * @param tardanza_acu the tardanza_acu to set
         */
        public void setTardanza_acu(int tardanza_acu) {
            this.tardanza_acu = tardanza_acu;
        }

    }

    //************************************************************************************************************************************************    
}
