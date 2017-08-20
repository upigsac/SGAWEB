
package Beans;

import DAO.alumnoDAO;
import DAO.autorizacionDAO;
import DAO.carrerasDAO;
import DAO.cuentaPersonaDAO;
import DAO.periodoDAO;
import DAO.personaAutorizacionDAO;
import DAO.personaDAO;

import DAO.tipoDocumentoDAO;
import ENTIDAD.carrerasC;
import ENTIDAD.cuentaPersonaC;
import ENTIDAD.periodoC;
import ENTIDAD.personaAutorizacionC;
import ENTIDAD.personaC;
import ENTIDAD.personalAutorizaC;
import ENTIDAD.tipoDocumentoC;

import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;


@ManagedBean(name = "cpB")
@ViewScoped
public class cuentaPersona  {

    personaDAO perdao;
    autorizacionDAO autodao;
    periodoDAO periodao;
    carrerasDAO cardao;
    cuentaPersonaDAO cuentaPersonaD;
    tipoDocumentoDAO tddao;
    alumnoDAO adao;
    personaAutorizacionDAO personaAutorizacionD;
    @ManagedProperty(value = "#{usuarioB}")
    private usuario usuB;

    //datos persona
    private personaC seleccionPersona=new personaC();

    private String filtroInstitucion;
    private List<cuentaPersona.personaInstitucion> listadoInstitucionesPersona;

    private String filtroPeriodo;
    private List<periodoC> listadoPeriodosPersona;
    private String filtroCarrera;
    private List<carrerasC> listadoCarrerasPersona;
    private String filtroEstado="%";
    
    //dtalle
    private List<tipoDocumentoC> listadoTC;
   
    private List<cuentaPersona.autoriPersonaEC> listadoAutorizaciones;
  

 

    //busqueda persona
    private String nombres="";
    private String ape_pat="";
    private String ape_mat="";
    private List<personaC> listadoPersona=new ArrayList<>();

    
    private List<cuentaPersonaC> cuentaPersonaL;
    private cuentaPersonaC cuentaPersona;
    
    //listado estado cuenta
    private List<cuentaPersona.detalleCuentaPersona> seleccionCP=new ArrayList<>();
    private List<cuentaPersona.detalleCuentaPersona> listadoCP;
    private List<cuentaPersona.detalleCuentaPersona> temporalCP;
    private List<cuentaPersona.detalleCuentaPersona> temporalCuotaCP;

    //autorizacion
   
  
    private List<personalAutorizaC> listadoPersonal;
  

  

    
    private int numeroCuotas;
    private double pagoxCuota;
    private double pagomontoTotal;
    private Date fechaInicio;
    private List<Date> fechaxCuota = new ArrayList<>();
    private personaAutorizacionC personaAutorizacion;
    private String mensaje=".:. Listado .:.";
    private boolean banderaBotonera;

   
    private int accion=0;
    
    private double totalMontoBase;
    private double totalMontoTotal;
    private double totalMontoParte;
    private double totalMontoInteres;
    private double totalPagado;
    private double totalSeleccionado;
    private String porcentajeDescuentoMora;
    private double totalDescuentoMora;
    private double totalDescuentoMoraItem;
    @PostConstruct
    public void init() {
     
    }
    
    
    public void calcularPorcentaje(int porcentaje){
        totalDescuentoMora=((totalSeleccionado * porcentaje) /100);
        totalDescuentoMoraItem=(totalDescuentoMora/seleccionCP.size());
    }
    
    
    
    
    public void totales(){
        
        totalMontoInteres=0;
        totalMontoTotal=0;
        totalMontoBase=0;
        totalMontoParte=0;
        totalPagado=0;
        for (detalleCuentaPersona item : listadoCP) {            
                if (item.estado_registro==27 || item.estado_registro==28 ){                    
                totalMontoInteres+=item.monto_interes.doubleValue();
                totalMontoTotal+=item.monto_total.doubleValue();
                setTotalMontoParte(getTotalMontoParte() + item.monto_parte.doubleValue());
                totalMontoBase+=item.monto_base.doubleValue();
                if (item.estado_registro==28){
                    totalPagado+=item.monto_total.doubleValue();
                }    
            }
        }
    }

   
    
   
     public void onRowSelect(SelectEvent event) {

         totalSeleccionado=0;
         for (detalleCuentaPersona item : seleccionCP) {
             totalSeleccionado+=item.monto_total.doubleValue();
         }
         
         util.script("$('#pnMensaje').fadeIn(400);");
     
    }
     
     public void onRowUnselect(UnselectEvent event) {
      
         totalSeleccionado=0;
         for (detalleCuentaPersona item : seleccionCP) {
             totalSeleccionado+=item.monto_total.doubleValue();
         }
        
       
       util.script("$('#pnMensaje').fadeIn(400);");
      
    }
    
    
    public void cancelar(){
        mensaje=".:. Listado .:.";
        accion=0;
        banderaBotonera=false;
        seleccionCP=new ArrayList<>();
        util.script("$('#pnMensaje').fadeOut(400);");
    }
    public void inicioFraccion(){
        mensaje="Iniciando la Fraccion";
        accion=1;
        banderaBotonera=true;
    }
    public void inicioCondonacion(){
        mensaje="Iniciando la Condonacion de deuda";
        accion=2;
        banderaBotonera=true;
    }
    public void inicioDeglozar(){
        mensaje="Iniciando la Desglozar";
        accion=3;
        banderaBotonera=true;
    }
    public void inicioDescuentoMora(){
        mensaje="Iniciando la decuento MOra";
        accion=4;
        banderaBotonera=true;
    }
    
    public void gestionarAccion(){
        if (seleccionCP.size()==0){
            util.alert("Debe seleccionar conceptos");
        }else{
            switch (accion){
                case 1: fraccionar(); util.script("dlgFracionesMultiples.show()"); break;
                case 2: nuevoDescuento();util.script("dlgAutorizacion.show()"); break;
                case 3: desglozar();break; 
                case 4: nuevoDescuentoMora();util.script("dlgDescuentoMora.show()");break;
            } 
        }
       
    }
    
    public void nuevoDescuentoMora(){
        
    }        
            
            
    public boolean validaSeleccion(detalleCuentaPersona item){
        boolean estado =true;
        switch (accion){
            case 1:
                if (item.estado_registro==27){
                    estado= false;
                }
                break;
            case 2:
                if (item.estado_registro==27){
                    estado= false;
                }
                break; 
                
            case 3:
                if(item.estado_registro==27 && !item.concepto.endsWith("0000000074") && item.monto_interes.doubleValue() >0  ){
                    estado=false;
                }
             break;
            case 4:
                if(item.estado_registro==27 && item.concepto.endsWith("0000000074") ){
                    estado=false;
                }
                
                break;
                
                
        }
        return estado;
    }
 
    public void desglozar(){
        
        cuentaPersonaL=new ArrayList<>();
        for (detalleCuentaPersona item : seleccionCP) {
            if (item.monto_interes.doubleValue() > 0){
                
                item.setPersona(seleccionPersona.getPersona());
                
                cuentaPersonaL.add(new cuentaPersonaC(item.persona, -1, item.institucion, item.periodo_concepto, item.tipo_concepto, "0000000074", "0000", "0000", item.num_cuota, item.num_parte, 1, item.monto_interes.doubleValue(), item.monto_interes.doubleValue(), 0.0, 0.0, 0.0, 0.0, null , "observacion", item.periodo, item.alumno, item.carrera, item.seccion, item.grupo , item.categoria, 0, "", item.estado_registro,0));
                
                cuentaPersonaL.add(new cuentaPersonaC(item.persona, item.item, item.institucion, item.periodo_concepto, item.tipo_concepto, item.concepto, item.descuento, item.descuento_adi, item.num_cuota, item.num_parte, 1, (item.monto_base.doubleValue() -(item.monto_desc.doubleValue() + item.monto_desc_adic.doubleValue()) ), (item.monto_base.doubleValue() -(item.monto_desc.doubleValue() + item.monto_desc_adic.doubleValue()) ), item.monto_desc.doubleValue(), item.monto_desc_adic.doubleValue(), 0.0, 0.0, util.fechaHoy() , "observacion", item.periodo, item.alumno, item.carrera, item.seccion, item.grupo , item.categoria, 0, "", item.estado_registro,0));
                }
        }
        
        
        cuentaPersonaD.insertarMultiple(cuentaPersonaL);
        ecXpersona();
        cancelar();
        
    }
    public void fraccionar(){
        fechaInicio=null;
        numeroCuotas=0;
        pagomontoTotal=0;
        pagoxCuota=0;
        temporalCP=new ArrayList<>();        
        for (cuentaPersona.detalleCuentaPersona item : seleccionCP) {
            
            if (item.monto_interes.doubleValue() != 0.00){                
                
                if (!"0000000074".equals(item.concepto))
                {
                    // creando el item con mora                                                             
                    temporalCP.add(new detalleCuentaPersona(item.item, item.persona, item.getInstitucion(), item.institucion_desc, item.carrera, item.carrera_desc, item.tipo_concepto, "0000000074", "MORA", item.periodo_concepto, item.periodo_desc,  item.fecha_venc, (item.monto_interes), item.monto_parte, item.monto_desc, item.monto_desc_adic, new BigDecimal("0.0") , item.monto_comparacion, item.monto_total, item.getTipo_comp(), item.tipo_comp_desc, item.num_comp, item.estado_registro, item.estado_registro_desc, item.num_cuota, item.num_parte,   item.observacion, item.monto_fraccion,   item.itemPadre , item.periodo,item.alumno));
                
                    // creando la cuota sin mora
                    temporalCP.add(new detalleCuentaPersona(item.item, item.persona, item.getInstitucion(), item.institucion_desc, item.carrera, item.carrera_desc, item.tipo_concepto, item.concepto, item.concepto_desc, item.periodo_concepto, item.periodo_desc,  item.fecha_venc, new BigDecimal((item.monto_base.doubleValue() -(item.monto_desc.doubleValue() + item.monto_desc_adic.doubleValue()) ) ), item.monto_parte, item.monto_desc, item.monto_desc_adic, new BigDecimal("0.0"), item.monto_comparacion, item.monto_total, item.getTipo_comp(), item.tipo_comp_desc, item.num_comp, item.estado_registro, item.estado_registro_desc, item.num_cuota, item.num_parte,   item.observacion, item.monto_fraccion,  item.itemPadre,item.periodo,item.alumno));
                }else{
                    temporalCP.add(new detalleCuentaPersona(item.item, item.persona, item.getInstitucion(), item.institucion_desc, item.carrera, item.carrera_desc, item.tipo_concepto, item.concepto, item.concepto_desc, item.periodo_concepto, item.periodo_desc,  item.fecha_venc,new BigDecimal(item.monto_base.doubleValue() + item.monto_interes.doubleValue()) , item.monto_parte, item.monto_desc, item.monto_desc_adic, new BigDecimal("0.0"), item.monto_comparacion, item.monto_total, item.getTipo_comp(), item.tipo_comp_desc, item.num_comp, item.estado_registro, item.estado_registro_desc, item.num_cuota, item.num_parte,   item.observacion, item.monto_fraccion,  item.itemPadre,item.periodo,item.alumno));
                }
                
            } else {
                temporalCP.add(new detalleCuentaPersona(item.item, item.persona, item.getInstitucion(), item.institucion_desc, item.carrera, item.carrera_desc, item.tipo_concepto, item.concepto, item.concepto_desc, item.periodo_concepto, item.periodo_desc,  item.fecha_venc, new BigDecimal((item.monto_base.doubleValue() -(item.monto_desc.doubleValue() + item.monto_desc_adic.doubleValue()) ) ), item.monto_parte, item.monto_desc, item.monto_desc_adic, item.monto_interes , item.monto_comparacion, item.monto_total, item.getTipo_comp(), item.tipo_comp_desc, item.num_comp, item.estado_registro, item.estado_registro_desc, item.num_cuota, item.num_parte,  item.observacion, item.monto_fraccion,   item.itemPadre,item.periodo,item.alumno));
                
            }
                
             
        }
        util.script("$('#pnMensaje').fadeOut(400);");
        
    }
    
    public void generarCuotas(){
        double montoParte;        
        double montoTotal=0;
        temporalCuotaCP=new ArrayList<>();
        for (detalleCuentaPersona item : temporalCP) {
            montoTotal+=item.monto_base.doubleValue();
            montoParte=item.monto_base.doubleValue() / numeroCuotas;
            for(int indice =1;indice<=numeroCuotas;indice++){
                temporalCuotaCP.add(new detalleCuentaPersona(-1, item.persona, item.getInstitucion(), item.institucion_desc, item.carrera, item.carrera_desc, item.tipo_concepto, item.concepto, item.concepto_desc, item.periodo_concepto, item.periodo_desc, item.fecha_venc, new BigDecimal(montoParte), new BigDecimal(montoParte), item.monto_desc, item.monto_desc_adic, item.monto_interes, item.monto_comparacion, item.monto_total, item.getTipo_comp(), item.tipo_comp_desc, item.num_comp, 0, item.estado_registro_desc, item.num_cuota, indice,  item.observacion, item.monto_fraccion, item.item,item.periodo,item.alumno));
                
            }
            
        }
        pagoxCuota=montoTotal/numeroCuotas;
        pagomontoTotal=montoTotal;
        generarFecha();
        
    }
    public void generarFecha(){
     fechaxCuota=new ArrayList<>();
     Date fecha=fechaInicio;
     for(int x=0;x<numeroCuotas;x++){
         
         fechaxCuota.add(fecha);
         fecha=util.dateAdd(fecha, 2, 1);
     }
    }
    
    public void nuevoAutorizacion(){
        personaAutorizacion=new personaAutorizacionC();
        personaAutorizacion.setEstado_registro(1);
        personaAutorizacion.setPersona(seleccionPersona.getPersona()); 
        personaAutorizacion.setTipo_autorizacion(3);
        personaAutorizacion.setAutorizacion_fecha(util.fechaHoy());
        for (detalleCuentaPersona item : temporalCuotaCP) {
        
            item.setFecha_venc(fechaxCuota.get(item.getNum_parte()-1));
        }
        listarPersonalAutorizacionEC(1,3);
    }
    public void nuevoDescuento(){
        personaAutorizacion=new personaAutorizacionC();
        personaAutorizacion.setEstado_registro(1);
        personaAutorizacion.setPersona(seleccionPersona.getPersona()); 
        personaAutorizacion.setTipo_autorizacion(1);
        personaAutorizacion.setAutorizacion_fecha(util.fechaHoy());
        listarPersonalAutorizacionEC(1,1);
        util.script("$('#pnMensaje').fadeOut(400);");
    }
    public void nuevoAutorizacionMora(){
        personaAutorizacion=new personaAutorizacionC();
        personaAutorizacion.setEstado_registro(1);
        personaAutorizacion.setPersona(seleccionPersona.getPersona()); 
        personaAutorizacion.setTipo_autorizacion(5);
        personaAutorizacion.setAutorizacion_fecha(util.fechaHoy());
        listarPersonalAutorizacionEC(1,5);
        util.script("$('#pnMensaje').fadeOut(400);");
    }
 

    /*LISTA DE CONCEPTOS*/
    public void listarTipoDocumentos() {
        tddao = new tipoDocumentoDAO();
        listadoTC = tddao.mostrarTipoDocumento(20);
    }

    public void ecXpersona() {
        cuentaPersonaD = new cuentaPersonaDAO();
        listadoCP = cuentaPersonaD.listarECPersona(filtroInstitucion, filtroPeriodo, filtroCarrera, seleccionPersona.getPersona(),filtroEstado);
        totales();
       
    }

    public void busquedaPersona() {
        perdao = new personaDAO();
        //listadoPersona = perdao.buscaPersona(ape_pat == null ? "" : ape_pat.trim(), ape_mat == null ? "" : ape_mat.trim(), nombres == null ? "" : nombres.trim());
        listadoPersona = perdao.filtroPersona( ape_pat.trim(),  ape_mat.trim(),  nombres.trim());
        filtroInstitucion = null;
        filtroPeriodo = null;
        
    }

    public void carrerasPersonaEC() {
        String cod_alumno = adao.mostrarAlumno(Integer.parseInt(filtroInstitucion), seleccionPersona.getPersona()).getAlumno();

        

        if (cod_alumno != null) {
            listadoCarrerasPersona = cardao.cuentaPersonaCarreras(cod_alumno, filtroPeriodo);
            if (!listadoCarrerasPersona.isEmpty()) {
                filtroCarrera = listadoCarrerasPersona.get(0).getCarrera();
            } else {
        
                filtroCarrera = "%";
            }
        }
        ecXpersona();
    }
    
    public void buscarCodigoPersona(){
       
        perdao = new personaDAO();
        String persona=("0000000000000000000000"+seleccionPersona.getPersona()).toString();        
        seleccionPersona.setPersona(persona.substring(persona.length()-20,persona.length()));
        seleccionPersona=perdao.mostrarPersona(seleccionPersona.getPersona());
        if(seleccionPersona ==null){
            
            seleccionPersona=new personaC();
            totalMontoInteres=0;
            totalMontoTotal=0;
            totalMontoBase=0;
            totalMontoParte=0;
            listadoCP=new ArrayList<>();
            
            listadoPeriodosPersona=new ArrayList<>();
            listadoCarrerasPersona=new ArrayList<>();
              util.script("document.getElementById('frmCuentaPersona\\:txtCodigoPersona').focus()");
        }else{
            institucionesPersona();
        }
            
            
    }

    public void institucionesPersona() {
       
      
            perdao = new personaDAO();
            periodao = new periodoDAO();
            cardao = new carrerasDAO();
            adao = new alumnoDAO();

            if (filtroInstitucion == null) {
                
                listadoInstitucionesPersona = perdao.cuentaPersonaInstituciones(seleccionPersona.getPersona());

                if (!listadoInstitucionesPersona.isEmpty()) {
                    filtroInstitucion = String.valueOf(listadoInstitucionesPersona.get(0).getInstitucion());
                    listadoPeriodosPersona = periodao.cuentaPersonaPeriodo(filtroInstitucion, seleccionPersona.getPersona());

                    if (!listadoPeriodosPersona.isEmpty()) {
                        filtroPeriodo = String.valueOf(listadoPeriodosPersona.get(0).getPeriodo());

                        listadoCarrerasPersona = cardao.cuentaPersonaCarreras(listadoInstitucionesPersona.get(0).getCod_alumno(), filtroPeriodo);
                        if (!listadoCarrerasPersona.isEmpty()) {
                            filtroCarrera = listadoCarrerasPersona.get(0).getCarrera();
                        } else {                            
                            filtroCarrera = "%";
                        }

                    }
                    filtroPeriodo = "%";
                    filtroCarrera = "%";

                    ecXpersona();

                } else {
            
                    listadoCP=new ArrayList<>();
                    listadoPeriodosPersona = new ArrayList<>();
                    listadoCarrerasPersona = new ArrayList<>();
                }

            } else {
                listadoPeriodosPersona = periodao.cuentaPersonaPeriodo(filtroInstitucion, seleccionPersona.getPersona());

                if (!listadoPeriodosPersona.isEmpty()) {
                    filtroPeriodo = String.valueOf(listadoPeriodosPersona.get(0).getPeriodo());
                    String cod_alumno = adao.mostrarAlumno(Integer.parseInt(filtroInstitucion), seleccionPersona.getPersona()).getAlumno();

                    System.out.println("cod alumno " + cod_alumno + " periodo " + filtroPeriodo);

                    if (cod_alumno != null) {
                        listadoCarrerasPersona = cardao.cuentaPersonaCarreras(cod_alumno, filtroPeriodo);
                        if (!listadoCarrerasPersona.isEmpty()) {
                            filtroCarrera = listadoCarrerasPersona.get(0).getCarrera();
                        } else {
                            System.out.println("listado carreras 2");
                            filtroCarrera = "%";
                        }

                    }
                } else {
                    filtroPeriodo = "%";
                    filtroCarrera = "%";
                }

                ecXpersona();
            }

            ape_pat = "";
            ape_mat = "";
            nombres = "";
        }
    
    /*FIN PERSONA*/

    public void datos_carga() {
        if (getSeleccionCP() != null) {
            autodao = new autorizacionDAO();
//            listadoAutorizaciones = autodao.listadoAutorizacionesEC(seleccionPersona.getPersona(), String.valueOf(getSeleccionCP().getItem()));
          
        }
    }

    public void listarPersonalAutorizacionEC(int institucion,int tipo) {
        autodao = new autorizacionDAO();        
        listadoPersonal=autodao.personalAutorizacion(institucion, tipo, 1);


      


        
    }

 


 

  
 

    public void registrarEdicionEC() {       

        cuentaPersonaD = new cuentaPersonaDAO();
                   
         autodao = new autorizacionDAO();
         
         
            switch(accion){
                case 1:
                
                    personaAutorizacionD=new personaAutorizacionDAO();
                    cuentaPersonaL=new ArrayList<>();
                    for(detalleCuentaPersona item : temporalCuotaCP) {                                                     
                            item.setPersona(seleccionPersona.getPersona());
                            cuentaPersonaL.add(new cuentaPersonaC(item.persona, item.item, item.institucion, item.periodo_concepto, item.tipo_concepto, item.concepto, "0000", "0000", item.num_cuota, item.num_parte, 0, item.monto_base.doubleValue(), item.monto_parte.doubleValue(), 0.0, 0.0, item.monto_interes.doubleValue(), 0.0, item.fecha_venc, "ingresado por intranet", item.periodo, item.alumno, item.carrera, item.seccion, item.grupo, item.categoria, item.getTipo_comp(), item.num_comp, item.estado_registro,item.itemPadre));                          
                        }
                    personaAutorizacionD.insertarTransaccionFraccionar(personaAutorizacion, cuentaPersonaL);
                
                ecXpersona();
                util.script("dlgAutorizacion.hide();");
                util.script("dlgFracionesMultiples.hide()");
                cancelar();
                
                    break;
                case 2:
                   
                    personaAutorizacionD=new personaAutorizacionDAO();
                    cuentaPersonaL=new ArrayList<>();
                    
                    for(detalleCuentaPersona item : seleccionCP) {                                                     
                            item.setPersona(seleccionPersona.getPersona());
                            cuentaPersonaL.add(new cuentaPersonaC(item.persona, item.item, item.institucion, item.periodo_concepto, item.tipo_concepto, item.concepto, "0000", "0000", item.num_cuota, item.num_parte, 0, item.monto_base.doubleValue(), item.monto_parte.doubleValue(), 0.0, 0.0, item.monto_interes.doubleValue(), 0.0, item.fecha_venc, "ingresado por intranet", item.periodo, item.alumno, item.carrera, item.seccion, item.grupo, item.categoria, item.getTipo_comp(), item.num_comp, item.estado_registro,item.itemPadre));                          
                        }
                    personaAutorizacionD.insertarTransaccionFraccionar(personaAutorizacion, cuentaPersonaL);
                    
                    
                    
                    util.script("dlgAutorizacion.hide();");
                    cancelar(); 
                    ecXpersona();
                    break;
               case 4:
                
                   
                     cuentaPersonaL=new ArrayList<>();
                    personaAutorizacionD=new personaAutorizacionDAO();
        
                        for (detalleCuentaPersona item : seleccionCP) {
                            cuentaPersonaL.add(new cuentaPersonaC(item.persona, -1, item.institucion, item.periodo_concepto, item.tipo_concepto, item.concepto, "0000", "0000", item.num_cuota, item.num_parte, 1, item.monto_base.doubleValue(), item.monto_parte.doubleValue(), item.monto_desc.doubleValue(), totalDescuentoMoraItem, item.monto_interes.doubleValue(), item.monto_total.doubleValue(), item.fecha_venc, item.observacion, item.periodo, item.alumno, item.carrera, item.seccion, item.grupo, item.categoria, item.tipo_comp, item.num_comp, 0, item.item));
                         
                        }
                   personaAutorizacionD.insertarTransaccionFraccionar(personaAutorizacion, cuentaPersonaL);
                   cancelar(); 
                   ecXpersona();
                   util.script("dlgAutorizacion.hide();");
                   util.script("dlgDescuentoMora.hide();");
            }
            
    }

    
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
     * @return the ape_pat
     */
    public String getApe_pat() {
        return ape_pat;
    }

    /**
     * @param ape_pat the ape_pat to set
     */
    public void setApe_pat(String ape_pat) {
        this.ape_pat = ape_pat;
    }

    /**
     * @return the ape_mat
     */
    public String getApe_mat() {
        return ape_mat;
    }

    /**
     * @param ape_mat the ape_mat to set
     */
    public void setApe_mat(String ape_mat) {
        this.ape_mat = ape_mat;
    }

    /**
     * @return the listadoPersona
     */
    public List<personaC> getListadoPersona() {
        return listadoPersona;
    }

    /**
     * @param listadoPersona the listadoPersona to set
     */
    public void setListadoPersona(List<personaC> listadoPersona) {
        this.listadoPersona = listadoPersona;
    }

    /**
     * @return the seleccionPersona
     */
    public personaC getSeleccionPersona() {
        return seleccionPersona;
    }

    /**
     * @param seleccionPersona the seleccionPersona to set
     */
    public void setSeleccionPersona(personaC seleccionPersona) {
        this.seleccionPersona = seleccionPersona;
    }

    /**
     * @return the listadoInstitucionesPersona
     */
    public List<personaInstitucion> getListadoInstitucionesPersona() {
        return listadoInstitucionesPersona;
    }

    /**
     * @param listadoInstitucionesPersona the listadoInstitucionesPersona to set
     */
    public void setListadoInstitucionesPersona(List<personaInstitucion> listadoInstitucionesPersona) {
        this.listadoInstitucionesPersona = listadoInstitucionesPersona;
    }

  

    

    /**
     * @return the usuB
     */
    public usuario getUsuB() {
        return usuB;
    }

    /**
     * @param usuB the usuB to set
     */
    public void setUsuB(usuario usuB) {
        this.usuB = usuB;
    }



    /**
     * @return the filtroInstitucion
     */
    public String getFiltroInstitucion() {
        return filtroInstitucion;
    }

    /**
     * @param filtroInstitucion the filtroInstitucion to set
     */
    public void setFiltroInstitucion(String filtroInstitucion) {
        this.filtroInstitucion = filtroInstitucion;
    }

    /**
     * @return the listadoPeriodosPersona
     */
    public List<periodoC> getListadoPeriodosPersona() {
        return listadoPeriodosPersona;
    }

    /**
     * @param listadoPeriodosPersona the listadoPeriodosPersona to set
     */
    public void setListadoPeriodosPersona(List<periodoC> listadoPeriodosPersona) {
        this.listadoPeriodosPersona = listadoPeriodosPersona;
    }

    /**
     * @return the filtroPeriodo
     */
    public String getFiltroPeriodo() {
        return filtroPeriodo;
    }

    /**
     * @param filtroPeriodo the filtroPeriodo to set
     */
    public void setFiltroPeriodo(String filtroPeriodo) {
        this.filtroPeriodo = filtroPeriodo;
    }

  

    /**
     * @return the listadoCP
     */
    public List<cuentaPersona.detalleCuentaPersona> getListadoCP() {
        return listadoCP;
    }

    /**
     * @param listadoCP the listadoCP to set
     */
    public void setListadoCP(List<cuentaPersona.detalleCuentaPersona> listadoCP) {
        this.listadoCP = listadoCP;
    }

    /**
     * @return the listadoTC
     */
    public List<tipoDocumentoC> getListadoTC() {
        return listadoTC;
    }

    /**
     * @param listadoTC the listadoTC to set
     */
    public void setListadoTC(List<tipoDocumentoC> listadoTC) {
        this.listadoTC = listadoTC;
    }

 

  

   


    
 


   

    /**
     * @return the listadoAutorizaciones
     */
    public List<cuentaPersona.autoriPersonaEC> getListadoAutorizaciones() {
        return listadoAutorizaciones;
    }

    /**
     * @param listadoAutorizaciones the listadoAutorizaciones to set
     */
    public void setListadoAutorizaciones(List<cuentaPersona.autoriPersonaEC> listadoAutorizaciones) {
        this.listadoAutorizaciones = listadoAutorizaciones;
    }

 

    /**
     * @return the filtroCarrera
     */
    public String getFiltroCarrera() {
        return filtroCarrera;
    }

    /**
     * @param filtroCarrera the filtroCarrera to set
     */
    public void setFiltroCarrera(String filtroCarrera) {
        this.filtroCarrera = filtroCarrera;
    }

    /**
     * @return the listadoCarrerasPersona
     */
    public List<carrerasC> getListadoCarrerasPersona() {
        return listadoCarrerasPersona;
    }

    /**
     * @param listadoCarrerasPersona the listadoCarrerasPersona to set
     */
    public void setListadoCarrerasPersona(List<carrerasC> listadoCarrerasPersona) {
        this.listadoCarrerasPersona = listadoCarrerasPersona;
    }

    /**
     * @return the seleccionCP
     */
    public List<cuentaPersona.detalleCuentaPersona> getSeleccionCP() {
        return seleccionCP;
    }

    /**
     * @param seleccionCP the seleccionCP to set
     */
    public void setSeleccionCP(List<cuentaPersona.detalleCuentaPersona> seleccionCP) {
        this.seleccionCP = seleccionCP;
    }

    /**
     * @return the temporalCP
     */
    public List<cuentaPersona.detalleCuentaPersona> getTemporalCP() {
        return temporalCP;
    }

    /**
     * @param temporalCP the temporalCP to set
     */
    public void setTemporalCP(List<cuentaPersona.detalleCuentaPersona> temporalCP) {
        this.temporalCP = temporalCP;
    }

    /**
     * @return the numeroCuotas
     */
    public int getNumeroCuotas() {
        return numeroCuotas;
    }

    /**
     * @param numeroCuotas the numeroCuotas to set
     */
    public void setNumeroCuotas(int numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

  

    /**
     * @return the pagoxCuota
     */
    public double getPagoxCuota() {
        return pagoxCuota;
    }

    /**
     * @param pagoxCuota the pagoxCuota to set
     */
    public void setPagoxCuota(double pagoxCuota) {
        this.pagoxCuota = pagoxCuota;
    }

  

    /**
     * @return the pagomontoTotal
     */
    public double getPagomontoTotal() {
        return pagomontoTotal;
    }

    /**
     * @param pagomontoTotal the pagomontoTotal to set
     */
    public void setPagomontoTotal(double pagomontoTotal) {
        this.pagomontoTotal = pagomontoTotal;
    }

    /**
     * @return the fechaxCuota
     */
    public List<Date> getFechaxCuota() {
        return fechaxCuota;
    }

    /**
     * @param fechaxCuota the fechaxCuota to set
     */
    public void setFechaxCuota(List<Date> fechaxCuota) {
        this.fechaxCuota = fechaxCuota;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the personaAutorizacion
     */
    public personaAutorizacionC getPersonaAutorizacion() {
        return personaAutorizacion;
    }

    /**
     * @param personaAutorizacion the personaAutorizacion to set
     */
    public void setPersonaAutorizacion(personaAutorizacionC personaAutorizacion) {
        this.personaAutorizacion = personaAutorizacion;
    }

    /**
     * @return the temporalCuotaCP
     */
    public List<cuentaPersona.detalleCuentaPersona> getTemporalCuotaCP() {
        return temporalCuotaCP;
    }

    /**
     * @param temporalCuotaCP the temporalCuotaCP to set
     */
    public void setTemporalCuotaCP(List<cuentaPersona.detalleCuentaPersona> temporalCuotaCP) {
        this.temporalCuotaCP = temporalCuotaCP;
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
     * @return the banderaBotonera
     */
    public boolean isBanderaBotonera() {
        return banderaBotonera;
    }

    /**
     * @param banderaBotonera the banderaBotonera to set
     */
    public void setBanderaBotonera(boolean banderaBotonera) {
        this.banderaBotonera = banderaBotonera;
    }

 

    /**
     * @return the totalMontoBase
     */
    public double getTotalMontoBase() {
        return totalMontoBase;
    }

    /**
     * @param totalMontoBase the totalMontoBase to set
     */
    public void setTotalMontoBase(double totalMontoBase) {
        this.totalMontoBase = totalMontoBase;
    }

    /**
     * @return the totalMontoTotal
     */
    public double getTotalMontoTotal() {
        return totalMontoTotal;
    }

    /**
     * @param totalMontoTotal the totalMontoTotal to set
     */
    public void setTotalMontoTotal(double totalMontoTotal) {
        this.totalMontoTotal = totalMontoTotal;
    }

    /**
     * @return the totalMontoInteres
     */
    public double getTotalMontoInteres() {
        return totalMontoInteres;
    }

    /**
     * @param totalMontoInteres the totalMontoInteres to set
     */
    public void setTotalMontoInteres(double totalMontoInteres) {
        this.totalMontoInteres = totalMontoInteres;
    }

    /**
     * @return the filtroEstado
     */
    public String getFiltroEstado() {
        return filtroEstado;
    }

    /**
     * @param filtroEstado the filtroEstado to set
     */
    public void setFiltroEstado(String filtroEstado) {
        this.filtroEstado = filtroEstado;
    }

    /**
     * @return the totalMontoParte
     */
    public double getTotalMontoParte() {
        return totalMontoParte;
    }

    /**
     * @param totalMontoParte the totalMontoParte to set
     */
    public void setTotalMontoParte(double totalMontoParte) {
        this.totalMontoParte = totalMontoParte;
    }

    /**
     * @return the totalPagado
     */
    public double getTotalPagado() {
        return totalPagado;
    }

    /**
     * @param totalPagado the totalPagado to set
     */
    public void setTotalPagado(double totalPagado) {
        this.totalPagado = totalPagado;
    }

    /**
     * @return the totalSeleccionado
     */
    public double getTotalSeleccionado() {
        return totalSeleccionado;
    }

    /**
     * @param totalSeleccionado the totalSeleccionado to set
     */
    public void setTotalSeleccionado(double totalSeleccionado) {
        this.totalSeleccionado = totalSeleccionado;
    }

    /**
     * @return the listadoPersonal
     */
    public List<personalAutorizaC> getListadoPersonal() {
        return listadoPersonal;
    }

    /**
     * @param listadoPersonal the listadoPersonal to set
     */
    public void setListadoPersonal(List<personalAutorizaC> listadoPersonal) {
        this.listadoPersonal = listadoPersonal;
    }

    /**
     * @return the accion
     */
    public int getAccion() {
        return accion;
    }

    /**
     * @param accion the accion to set
     */
    public void setAccion(int accion) {
        this.accion = accion;
    }

    /**
     * @return the porcentajeDescuentoMora
     */
    public String getPorcentajeDescuentoMora() {
        return porcentajeDescuentoMora;
    }

    /**
     * @param porcentajeDescuentoMora the porcentajeDescuentoMora to set
     */
    public void setPorcentajeDescuentoMora(String porcentajeDescuentoMora) {
        this.porcentajeDescuentoMora = porcentajeDescuentoMora;
    }

    /**
     * @return the totalDescuentoMora
     */
    public double getTotalDescuentoMora() {
        return totalDescuentoMora;
    }

    /**
     * @param totalDescuentoMora the totalDescuentoMora to set
     */
    public void setTotalDescuentoMora(double totalDescuentoMora) {
        this.totalDescuentoMora = totalDescuentoMora;
    }

    /**
     * @return the cuentaPersonaL
     */
    public List<cuentaPersonaC> getCuentaPersonaL() {
        return cuentaPersonaL;
    }

    /**
     * @param cuentaPersonaL the cuentaPersonaL to set
     */
    public void setCuentaPersonaL(List<cuentaPersonaC> cuentaPersonaL) {
        this.cuentaPersonaL = cuentaPersonaL;
    }

    /**
     * @return the cuentaPersona
     */
    public cuentaPersonaC getCuentaPersona() {
        return cuentaPersona;
    }

    /**
     * @param cuentaPersona the cuentaPersona to set
     */
    public void setCuentaPersona(cuentaPersonaC cuentaPersona) {
        this.cuentaPersona = cuentaPersona;
    }

    /**
     * @return the totalDescuentoMoraItem
     */
    public double getTotalDescuentoMoraItem() {
        return totalDescuentoMoraItem;
    }

    /**
     * @param totalDescuentoMoraItem the totalDescuentoMoraItem to set
     */
    public void setTotalDescuentoMoraItem(double totalDescuentoMoraItem) {
        this.totalDescuentoMoraItem = totalDescuentoMoraItem;
    }

    

  

  

    public static class personaInstitucion {

        private int institucion;
        private String abrev_inst;
        private String desc_inst;
        private String cod_alumno;
        private String carrera;
        private String desc_carrera;

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
         * @return the desc_inst
         */
        public String getDesc_inst() {
            return desc_inst;
        }

        /**
         * @param desc_inst the desc_inst to set
         */
        public void setDesc_inst(String desc_inst) {
            this.desc_inst = desc_inst;
        }

        /**
         * @return the cod_alumno
         */
        public String getCod_alumno() {
            return cod_alumno;
        }

        /**
         * @param cod_alumno the cod_alumno to set
         */
        public void setCod_alumno(String cod_alumno) {
            this.cod_alumno = cod_alumno;
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
         * @return the abrev_inst
         */
        public String getAbrev_inst() {
            return abrev_inst;
        }

        /**
         * @param abrev_inst the abrev_inst to set
         */
        public void setAbrev_inst(String abrev_inst) {
            this.abrev_inst = abrev_inst;
        }
    }

    public static class detalleCuentaPersona {
        private int item;        
        private String persona;
        private int institucion;
        private String institucion_desc;
        private String carrera;
        private String carrera_desc;
        private int tipo_concepto;
        private String concepto;
        private String concepto_desc;
        private String periodo_concepto;        
        private String periodo_desc;        
        private Date fecha_venc;
        private BigDecimal monto_base;
        private BigDecimal monto_parte;
        private BigDecimal monto_desc;
        private BigDecimal monto_desc_adic;
        private BigDecimal monto_interes;
        private BigDecimal monto_comparacion;        
        private BigDecimal monto_total;
        private int tipo_comp;
        private String tipo_comp_desc;
        private String num_comp;
        private int estado_registro;
        private String estado_registro_desc;
        private int num_cuota;
        private int num_parte;
        private String observacion;
        private BigDecimal monto_fraccion;     
        private int itemPadre;
        private String periodo;
        private String alumno;
        private String seccion;
        private String descuento;
        private String descuento_adi;
        private String categoria;
        private String grupo;
        
        
        public detalleCuentaPersona() {
            
        }

        public detalleCuentaPersona(int item, String persona, int institucion, String institucion_desc, String carrera, String carrera_desc, int tipo_concepto, String concepto, String concepto_desc, String periodo_concepto, String periodo_desc, Date fecha_venc, BigDecimal monto_base, BigDecimal monto_parte, BigDecimal monto_desc, BigDecimal monto_desc_adic, BigDecimal monto_interes, BigDecimal monto_comparacion, BigDecimal monto_total, int tipo_comp, String tipo_comp_desc, String num_comp, int estado_registro, String estado_registro_desc, int num_cuota, int num_parte, String observacion, BigDecimal monto_fraccion, int itemPadre, String periodo, String alumno) {
            this.item = item;
            this.persona = persona;
            this.institucion = institucion;
            this.institucion_desc = institucion_desc;
            this.carrera = carrera;
            this.carrera_desc = carrera_desc;
            this.tipo_concepto = tipo_concepto;
            this.concepto = concepto;
            this.concepto_desc = concepto_desc;
            this.periodo_concepto = periodo_concepto;
            this.periodo_desc = periodo_desc;
            this.fecha_venc = fecha_venc;
            this.monto_base = monto_base;
            this.monto_parte = monto_parte;
            this.monto_desc = monto_desc;
            this.monto_desc_adic = monto_desc_adic;
            this.monto_interes = monto_interes;
            this.monto_comparacion = monto_comparacion;
            this.monto_total = monto_total;
            this.tipo_comp = tipo_comp;
            this.tipo_comp_desc = tipo_comp_desc;
            this.num_comp = num_comp;
            this.estado_registro = estado_registro;
            this.estado_registro_desc = estado_registro_desc;
            this.num_cuota = num_cuota;
            this.num_parte = num_parte;
            this.observacion = observacion;
            this.monto_fraccion = monto_fraccion;
            this.itemPadre = itemPadre;
            this.periodo = periodo;
            this.alumno = alumno;
        }

     

      


       

        

     

        /**
         * @return the institucion_desc
         */
        public String getInstitucion_desc() {
            return institucion_desc;
        }

        /**
         * @param institucion_desc the institucion_desc to set
         */
        public void setInstitucion_desc(String institucion_desc) {
            this.institucion_desc = institucion_desc;
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
         * @return the carrera_desc
         */
        public String getCarrera_desc() {
            return carrera_desc;
        }

        /**
         * @param carrera_desc the carrera_desc to set
         */
        public void setCarrera_desc(String carrera_desc) {
            this.carrera_desc = carrera_desc;
        }

        /**
         * @return the concepto
         */
        public String getConcepto() {
            return concepto;
        }

        /**
         * @param concepto the concepto to set
         */
        public void setConcepto(String concepto) {
            this.concepto = concepto;
        }

        /**
         * @return the concepto_desc
         */
        public String getConcepto_desc() {
            return concepto_desc;
        }

        /**
         * @param concepto_desc the concepto_desc to set
         */
        public void setConcepto_desc(String concepto_desc) {
            this.concepto_desc = concepto_desc;
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
         * @return the periodo_desc
         */
        public String getPeriodo_desc() {
            return periodo_desc;
        }

        /**
         * @param periodo_desc the periodo_desc to set
         */
        public void setPeriodo_desc(String periodo_desc) {
            this.periodo_desc = periodo_desc;
        }


        /**
         * @return the fecha_venc
         */
        public Date getFecha_venc() {
            return fecha_venc;
        }

        /**
         * @param fecha_venc the fecha_venc to set
         */
        public void setFecha_venc(Date fecha_venc) {
            this.fecha_venc = fecha_venc;
        }

        /**
         * @return the monto_base
         */
        public BigDecimal getMonto_base() {
            return monto_base;
        }

        /**
         * @param monto_base the monto_base to set
         */
        public void setMonto_base(BigDecimal monto_base) {
            this.monto_base = monto_base;
        }

        /**
         * @return the monto_parte
         */
        public BigDecimal getMonto_parte() {
            return monto_parte;
        }

        /**
         * @param monto_parte the monto_parte to set
         */
        public void setMonto_parte(BigDecimal monto_parte) {
            this.monto_parte = monto_parte;
        }

        /**
         * @return the monto_desc
         */
        public BigDecimal getMonto_desc() {
            return monto_desc;
        }

        /**
         * @param monto_desc the monto_desc to set
         */
        public void setMonto_desc(BigDecimal monto_desc) {
            this.monto_desc = monto_desc;
        }

        /**
         * @return the monto_desc_adic
         */
        public BigDecimal getMonto_desc_adic() {
            return monto_desc_adic;
        }

        /**
         * @param monto_desc_adic the monto_desc_adic to set
         */
        public void setMonto_desc_adic(BigDecimal monto_desc_adic) {
            this.monto_desc_adic = monto_desc_adic;
        }

        /**
         * @return the monto_interes
         */
        public BigDecimal getMonto_interes() {
            return monto_interes;
        }

        /**
         * @param monto_interes the monto_interes to set
         */
        public void setMonto_interes(BigDecimal monto_interes) {
            this.monto_interes = monto_interes;
        }

        /**
         * @return the monto_total
         */
        public BigDecimal getMonto_total() {
            return monto_total;
        }

        /**
         * @param monto_total the monto_total to set
         */
        public void setMonto_total(BigDecimal monto_total) {
            this.monto_total = monto_total;
        }

       

        /**
         * @return the tipo_comp_desc
         */
        public String getTipo_comp_desc() {
            return tipo_comp_desc;
        }

        /**
         * @param tipo_comp_desc the tipo_comp_desc to set
         */
        public void setTipo_comp_desc(String tipo_comp_desc) {
            this.tipo_comp_desc = tipo_comp_desc;
        }

        /**
         * @return the num_comp
         */
        public String getNum_comp() {
            return num_comp;
        }

        /**
         * @param num_comp the num_comp to set
         */
        public void setNum_comp(String num_comp) {
            this.num_comp = num_comp;
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
         * @return the estado_registro_desc
         */
        public String getEstado_registro_desc() {
            return estado_registro_desc;
        }

        /**
         * @param estado_registro_desc the estado_registro_desc to set
         */
        public void setEstado_registro_desc(String estado_registro_desc) {
            this.estado_registro_desc = estado_registro_desc;
        }

        /**
         * @return the observacion
         */
        public String getObservacion() {
            return observacion;
        }

        /**
         * @param observacion the observacion to set
         */
        public void setObservacion(String observacion) {
            this.observacion = observacion;
        }

       

        /**
         * @return the item
         */
        public int getItem() {
            return item;
        }

        /**
         * @param item the item to set
         */
        public void setItem(int item) {
            this.item = item;
        }

        /**
         * @return the tipo_concepto
         */
        public int getTipo_concepto() {
            return tipo_concepto;
        }

        /**
         * @param tipo_concepto the tipo_concepto to set
         */
        public void setTipo_concepto(int tipo_concepto) {
            this.tipo_concepto = tipo_concepto;
        }

        /**
         * @return the num_couta
         */
        public int getNum_cuota() {
            return num_cuota;
        }

        /**
         * @param num_cuota the num_cuota to set
         */
        public void setNum_cuota(int num_cuota) {
            this.num_cuota = num_cuota;
        }

        /**
         * @return the num_parte
         */
        public int getNum_parte() {
            return num_parte;
        }

        /**
         * @param num_parte the num_parte to set
         */
        public void setNum_parte(int num_parte) {
            this.num_parte = num_parte;
        }

  
        /**
         * @return the monto_fraccion
         */
        public BigDecimal getMonto_fraccion() {
            return monto_fraccion;
        }

        /**
         * @param monto_fraccion the monto_fraccion to set
         */
        public void setMonto_fraccion(BigDecimal monto_fraccion) {
            this.monto_fraccion = monto_fraccion;
        }

       


       

      

       

        /**
         * @return the monto_comparacion
         */
        public BigDecimal getMonto_comparacion() {
            return monto_comparacion;
        }

        /**
         * @param monto_comparacion the monto_comparacion to set
         */
        public void setMonto_comparacion(BigDecimal monto_comparacion) {
            this.monto_comparacion = monto_comparacion;
        }


        /**
         * @return the periodo_concepto
         */
        public String getPeriodo_concepto() {
            return periodo_concepto;
        }

        /**
         * @param periodo_concepto the periodo_concepto to set
         */
        public void setPeriodo_concepto(String periodo_concepto) {
            this.periodo_concepto = periodo_concepto;
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
         * @return the itemPadre
         */
        public int getItemPadre() {
            return itemPadre;
        }

        /**
         * @param itemPadre the itemPadre to set
         */
        public void setItemPadre(int itemPadre) {
            this.itemPadre = itemPadre;
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
         * @return the descuento_adi
         */
        public String getDescuento_adi() {
            return descuento_adi;
        }

        /**
         * @param descuento_adi the descuento_adi to set
         */
        public void setDescuento_adi(String descuento_adi) {
            this.descuento_adi = descuento_adi;
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
         * @return the descuento
         */
        public String getDescuento() {
            return descuento;
        }

        /**
         * @param descuento the descuento to set
         */
        public void setDescuento(String descuento) {
            this.descuento = descuento;
        }

        /**
         * @return the categoria
         */
        public String getCategoria() {
            return categoria;
        }

        /**
         * @param categoria the categoria to set
         */
        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        /**
         * @return the grupo
         */
        public String getGrupo() {
            return grupo;
        }

        /**
         * @param grupo the grupo to set
         */
        public void setGrupo(String grupo) {
            this.grupo = grupo;
        }

        /**
         * @return the tipo_comp
         */
        public int getTipo_comp() {
            return tipo_comp;
        }

        /**
         * @param tipo_comp the tipo_comp to set
         */
        public void setTipo_comp(int tipo_comp) {
            this.tipo_comp = tipo_comp;
        }

        

     

    }

    public static class autoriPersonaEC {

        private int item;
        private int tipo_autori;
        private String descripcion;
        private Date fecha_autori;
        private String personal_autori;
        private String personal_nombres;
        private String documento_autori;
        private String obs_autori;
        private Date creacion_fecha;

        /**
         * @return the item
         */
        public int getItem() {
            return item;
        }

        /**
         * @param item the item to set
         */
        public void setItem(int item) {
            this.item = item;
        }

        /**
         * @return the tipo_autori
         */
        public int getTipo_autori() {
            return tipo_autori;
        }

        /**
         * @param tipo_autori the tipo_autori to set
         */
        public void setTipo_autori(int tipo_autori) {
            this.tipo_autori = tipo_autori;
        }

        /**
         * @return the descripcion
         */
        public String getDescripcion() {
            return descripcion;
        }

        /**
         * @param descripcion the descripcion to set
         */
        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        /**
         * @return the fecha_autori
         */
        public Date getFecha_autori() {
            return fecha_autori;
        }

        /**
         * @param fecha_autori the fecha_autori to set
         */
        public void setFecha_autori(Date fecha_autori) {
            this.fecha_autori = fecha_autori;
        }

        /**
         * @return the personal_autori
         */
        public String getPersonal_autori() {
            return personal_autori;
        }

        /**
         * @param personal_autori the personal_autori to set
         */
        public void setPersonal_autori(String personal_autori) {
            this.personal_autori = personal_autori;
        }

        /**
         * @return the personal_nombres
         */
        public String getPersonal_nombres() {
            return personal_nombres;
        }

        /**
         * @param personal_nombres the personal_nombres to set
         */
        public void setPersonal_nombres(String personal_nombres) {
            this.personal_nombres = personal_nombres;
        }

        /**
         * @return the documento_autori
         */
        public String getDocumento_autori() {
            return documento_autori;
        }

        /**
         * @param documento_autori the documento_autori to set
         */
        public void setDocumento_autori(String documento_autori) {
            this.documento_autori = documento_autori;
        }

        /**
         * @return the obs_autori
         */
        public String getObs_autori() {
            return obs_autori;
        }

        /**
         * @param obs_autori the obs_autori to set
         */
        public void setObs_autori(String obs_autori) {
            this.obs_autori = obs_autori;
        }

        /**
         * @return the creacion_fecha
         */
        public Date getCreacion_fecha() {
            return creacion_fecha;
        }

        /**
         * @param creacion_fecha the creacion_fecha to set
         */
        public void setCreacion_fecha(Date creacion_fecha) {
            this.creacion_fecha = creacion_fecha;
        }
    }

}
