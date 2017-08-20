/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.cuentaPersona;

import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import ENTIDAD.autorizacionC;
import ENTIDAD.pagoPersonaDetC;
import ENTIDAD.personaAutorizacionC;

import ENTIDAD.personalAutorizaC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;

import java.util.List;


public class autorizacionDAO {

    public List<autorizacionC> datosTipoAutorizacion(int tipo) {

        List<autorizacionC> lista = new ArrayList<>();

        try {
            Connection c = null;
            CallableStatement cs = null;
            ResultSet rs = null;
            autorizacionC item = null;
            c = ConexionWeb();
            cs = c.prepareCall("SELECT * FROM SIGU.SYS_TIPO_AUTORIZACION WHERE TIPO_AUTORIZACION IN (?)");
            cs.setInt(1, tipo);

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new autorizacionC();
                item.setTipoAutorizacion(rs.getInt("TIPO_AUTORIZACION"));
                item.setDescripcion(rs.getString("DESCRIPCION"));
                item.setAbreviatura(rs.getString("ABREVIATURA"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            System.out.println("datosAutorizacion " + e.getMessage());
        }
        return lista;
    }

    public List<cuentaPersona.autoriPersonaEC> listadoAutorizacionesEC(String persona, String item) {
        List<cuentaPersona.autoriPersonaEC> lista = new ArrayList<>();

        try {
            Connection c = null;
            CallableStatement cs = null;
            ResultSet rs = null;
            cuentaPersona.autoriPersonaEC i = null;
            c = ConexionWeb();
            cs = c.prepareCall("SELECT\n"
                    + "DPA.ITEM,DPA.TIPO_AUTORIZACION,TA.DESCRIPCION,DPA.AUT_FECHA,DPA.AUT_PERSONAL,DP.APELLIDO_PATERNO+' '+DP.APELLIDO_MATERNO+', '+DP.NOMBRES NOMBRES,DPA.AUT_DOCUMENTO,DPA.AUT_OBSERVACION,DPA.CREACION_FECHA\n"
                    + "FROM \n"
                    + "SIGU.DAT_PERSONA_AUTORIZACION DPA JOIN SIGU.PER_PERSONAL PP\n"
                    + "ON PP.PERSONAL=DPA.AUT_PERSONAL JOIN UPA.DAT_PERSONAS DP\n"
                    + "ON DP.PERSONA=PP.PERSONA JOIN SIGU.SYS_TIPO_AUTORIZACION TA\n"
                    + "ON DPA.TIPO_AUTORIZACION=TA.TIPO_AUTORIZACION\n"
                    + "WHERE \n"
                    + "DPA.PERSONA=?\n"
                    + "AND DPA.TIPO_AUTORIZACION IN (2,3,4)\n"
                    + "AND substring(RTRIM(DPA.CADENA),21,LEN(RTRIM(DPA.CADENA))) = ?\n"                    
                    + "AND DPA.ESTADO_REGISTRO=1\n"
                    + "ORDER BY DPA.CREACION_FECHA DESC");
            cs.setString(1, persona);
            cs.setString(2, item);            

            rs = cs.executeQuery();
            while (rs.next()) {
                i = new cuentaPersona.autoriPersonaEC();
                i.setItem(rs.getInt("ITEM"));
                i.setTipo_autori(rs.getInt("TIPO_AUTORIZACION"));
                i.setDescripcion(rs.getString("DESCRIPCION"));
                i.setFecha_autori(rs.getDate("AUT_FECHA"));
                i.setPersonal_autori(rs.getString("AUT_PERSONAL"));
                i.setPersonal_nombres(rs.getString("NOMBRES"));
                i.setDocumento_autori(rs.getString("AUT_DOCUMENTO"));
                i.setObs_autori(rs.getString("AUT_OBSERVACION"));
                i.setCreacion_fecha(rs.getDate("CREACION_FECHA"));
                
                lista.add(i);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            System.out.println("listadoAutorizacionesEC " + e.getMessage());
        }
        return lista;
    }

    public List<personalAutorizaC> personalAutorizacion(int institucion, int tipo_autorizacion, int estado_registro) {

        List<personalAutorizaC> lista = new ArrayList<>();

        try {
            Connection c;
            CallableStatement cs ;
            ResultSet rs ;
            personalAutorizaC item ;
            c = ConexionWeb();
            cs = c.prepareCall("{CALL SIGU.CON_AUTORIZACIONX (1,?,'','','',?,0,'',?)}");
            cs.setInt(1, institucion);
            cs.setInt(2, tipo_autorizacion);
            cs.setInt(3, estado_registro);

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new personalAutorizaC();
                item.setPersonal(rs.getString("PERSONAL"));
                item.setNombres(rs.getString("NOMBRES"));
                //item.setTipo_autorizacion(rs.getInt("TIPO_AUTORIZACION"));
                item.setTipo_autorizacion(tipo_autorizacion);
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            System.out.println("personalAutorizacion " + e.getMessage());
        }
        return lista;
    }

    public personaAutorizacionC datosAutorizacion(String persona, int autorizacion) {

        personaAutorizacionC item = null;

        try {
            Connection c = null;
            CallableStatement cs = null;
            ResultSet rs = null;
            c = ConexionWeb();
            cs = c.prepareCall("{CALL SIGU.CON_AUTORIZACIONX (2,0,'',?,'',0,?,'',0)}");
            cs.setString(1, persona);
            cs.setInt(2, autorizacion);

            rs = cs.executeQuery();
            while (rs.next()) {
                item = new personaAutorizacionC();
                item.setTipo_autorizacion(rs.getInt("TIPO_AUTORIZACION"));
                item.setAutorizacion_fecha(rs.getDate("AUT_FECHA"));
                item.setAutorizacion_documento(rs.getString("AUT_DOCUMENTO").equals("") ? null : rs.getString("AUT_DOCUMENTO"));
                item.setAutorizacion_observacion(rs.getString("AUT_OBSERVACION").equals("") ? null : rs.getString("AUT_OBSERVACION"));
                item.setAutorizacion_personal(rs.getString("AUT_PERSONAL"));
                item.setNombre_archivo(rs.getString("NOMBRE_ARCHIVO").equals("") ? null : rs.getString("NOMBRE_ARCHIVO"));
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            System.out.println("datosAutorizacion " + e.getMessage());
        }
        return item;
    }

    public String registrarAutorizacionMultiple(int accion, String persona,personaAutorizacionC item ) {
        String retorno = "";
 
        try {
            Connection c  ;
            CallableStatement cs ;
            c = ConexionWeb();
            cs = c.prepareCall("{CALL [DI].[SQL_DAT_PERSONA_AUTORIZA_WEB] (?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, accion);
            cs.setString(2, persona);
            cs.setInt(3, item.getItem());
            cs.setInt(4, item.getTipo_autorizacion());
            cs.setString(5,  item.getCadena());
            cs.setString(6,  item.getNombre_archivo() );
            cs.setString(7, util.convertDate(item.getAutorizacion_fecha()) );
            cs.setString(8, item.getAutorizacion_personal());
            cs.setString(9, item.getAutorizacion_documento());
            cs.setString(10, item.getAutorizacion_observacion());
            cs.setInt(11, item.getEstado_registro());
            cs.registerOutParameter(12, Types.VARCHAR);
            cs.executeUpdate();
            retorno = cs.getString(12);
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException | NullPointerException e) {
            System.out.println("registrarAutorizacionMultiple " + e.getMessage());
        }
        return retorno;
    }
    
     public String registrarAutorizacionFraccion(personaAutorizacionC item,List<cuentaPersona.detalleCuentaPersona> cuentaPersonaL) {
         
  //      int codigoItem = 0;
 /*
        try {
            Connection c  ;
            CallableStatement insertaAutorizacacion ;
            CallableStatement insertaCuentaPersonaAutoriza ;
            c = ConexionWeb();
            c.setAutoCommit(false);
            insertaAutorizacacion = c.prepareCall("{CALL [DI].[SQL_DAT_PERSONA_AUTORIZA_WEB] (?,?,?,?,?,?,?,?,?,?,?,?)}");
            insertaAutorizacacion.setString(1, item.getPersona());
            insertaAutorizacacion.setInt(2, item.getItem());            
            insertaAutorizacacion.setInt(4, item.getTipo_autorizacion());
            insertaAutorizacacion.setString(7, util.convertDate(item.getAutorizacion_fecha()) );
            insertaAutorizacacion.setString(8, item.getAutorizacion_personal());
            insertaAutorizacacion.setString(9, item.getAutorizacion_documento());
            insertaAutorizacacion.setString(10, item.getAutorizacion_observacion());
            insertaAutorizacacion.setInt(11, item.getEstado_registro());
            insertaAutorizacacion.registerOutParameter(2, Types.VARCHAR);
            insertaAutorizacacion.executeUpdate();
            codigoItem = insertaAutorizacacion.getInt(2);
             for (detalleCuentaPersona itemL : cuentaPersonaL) {                             
                            cpdao.fraccionCuentaPersona(codigoItem, item.getAutorizacion_personal(), itemL);
                          
              }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException | NullPointerException e) {
            System.out.println("registrarAutorizacionMultiple " + e.getMessage());
        */
        return "";
                 
    }
    
    
    
    
    
    
    
    
    
    

    public int accionAutorizacion(int accion, pagoPersonaDetC pp, personaAutorizacionC pa) {
        int item = 0;
       
        try {
            Connection c = null;
            CallableStatement cs = null;
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SQL_DAT_PERSONA_AUTORIZA_JAVA (?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, accion);
            cs.setString(2, pp.getPersona());
            cs.setInt(3, pp.getAuto_desc_adic());
            cs.setInt(4, pa.getTipo_autorizacion());
            cs.setString(5, pa.getCadena() == null ? "" : pa.getCadena());
            cs.setString(6, pa.getNombre_archivo() == null ? "" : pa.getNombre_archivo());
            cs.setDate(7, new java.sql.Date(pa.getAutorizacion_fecha().getTime()));
            cs.setString(8, pa.getAutorizacion_personal());
            cs.setString(9, pa.getAutorizacion_documento() == null ? "" : pa.getAutorizacion_documento());
            cs.setString(10, pa.getAutorizacion_observacion() == null ? "" : pa.getAutorizacion_observacion());
            cs.registerOutParameter(11, Types.INTEGER);
            cs.executeUpdate();
            item = cs.getInt(11);
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException | NullPointerException e) {
            System.out.println("registro autorizacion " + e.getMessage());
        }
        return item;
    }

    public int ultimaAutorizacion(String persona, int tipo_autorizacion, int estado) {
        int item = 0;
        //AGREGE UNA VAR MAS ESTADO
        try {
            Connection c = null;
            CallableStatement cs = null;
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SQL_DAT_PERSONA_AUTORIZA_JAVA (4,?,0,?,'','','','','','',?,?)}");
            cs.setString(1, persona);
            cs.setInt(2, tipo_autorizacion);
            cs.setInt(3, estado);
            cs.registerOutParameter(4, Types.INTEGER);
            cs.executeUpdate();
            item = cs.getInt(4);
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException | NullPointerException e) {
            System.out.println("ultima autorizacion " + e.getMessage());
        }
        return item;
    }

    public int registrarAutorizacionPagoPersona(String persona, String personal) {
        int item = 0;

        try {
            Connection c = null;
            CallableStatement cs = null;
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SQL_DAT_PERSONA_AUTORIZA_JAVA (5,?,0,0,'','','',?,'','',?)}");
            cs.setString(1, persona);
            cs.setString(2, personal);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.executeUpdate();
            item = cs.getInt(3);
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException | NullPointerException e) {
            System.out.println("registrarAutorizacionPagoPersona " + e.getMessage());
        }
        return item;
    }

    public int eliminarAutorizacionConceptos(String persona, String personal) {
        int item = -1;

        try {
            Connection c = null;
            CallableStatement cs = null;
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SQL_DAT_PERSONA_AUTORIZA_JAVA (9,?,0,0,'','','',?,'','',?)}");
            cs.setString(1, persona);
            cs.setString(2, personal);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.executeUpdate();
            item = cs.getInt(3);
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException | NullPointerException e) {
            System.out.println("eliminarAutorizacionConceptos " + e.getMessage());
        }
        return item;
    }

}
