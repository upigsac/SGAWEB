/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;


import Beans.util;

import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.cuentaPersonaC;

import ENTIDAD.personaAutorizacionC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;


public class personaAutorizacionDAO {
    
     public String insertar(personaAutorizacionC item) {
        int itemRetorno=0;
        Connection c ;
        CallableStatement cs ;
        boolean rpta ;
        String codigo = "";
        try {
            c = ConexionWeb();
            cs = c.prepareCall("{CALL DI.SP_MANTENIMENTO_PERSONA_AUTORIZACION(?,?,?,?,?,?,?,?,?,?, ?,?)}");
            cs.setString(1, item.getPersona());
            cs.setInt(2, item.getItem());
            cs.setInt(3, item.getTipo_autorizacion());
            cs.setString(4, util.convertDate(item.getAutorizacion_fecha()));
            cs.setString(5, item.getAutorizacion_personal());
            cs.setString(6, item.getAutorizacion_documento());
            cs.setString(7, item.getAutorizacion_observacion());
            cs.setString(8, item.getDocumento());
            cs.setString(9, item.getResolucion());
            cs.setString(10, item.getExpediente());
            cs.setString(11, item.getFut());
            cs.setInt(12, item.getEstado_registro());
            cs.registerOutParameter(2, Types.INTEGER);
            rpta = cs.executeUpdate() == 1 ;
            itemRetorno = cs.getInt(2);
            util.consolaCliente(""+itemRetorno);
            if (rpta) {

                c.commit();

            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException ex) {
            util.consolaCliente( ex.getMessage());
        }
        return codigo;
    }
     
     public String insertarTransaccionFraccionar(personaAutorizacionC itemAutorizacion,List<cuentaPersonaC> listaCuentaPersona)  {
        int itemAutorizacionRetorno;
        int itemCuentaPersonaRetorno;        
        Connection c =null;
        c = ConexionWeb();
        CallableStatement cs=null ;
        CallableStatement css =null;
        CallableStatement csss =null;        
        String codigo = "";
        try {
           
            c.setAutoCommit(false);
            cs = c.prepareCall("{CALL DI.SP_MANTENIMENTO_PERSONA_AUTORIZACION(?,?,?,?,?,?,?,?,?,?, ?,?)}");
            cs.setString(1, itemAutorizacion.getPersona());
            cs.setInt(2, itemAutorizacion.getItem());
            cs.setInt(3, itemAutorizacion.getTipo_autorizacion());
            cs.setString(4, util.convertDate(itemAutorizacion.getAutorizacion_fecha()));
            cs.setString(5, itemAutorizacion.getAutorizacion_personal());
            cs.setString(6, itemAutorizacion.getAutorizacion_documento());
            cs.setString(7, itemAutorizacion.getAutorizacion_observacion());
            cs.setString(8, itemAutorizacion.getDocumento());
            cs.setString(9, itemAutorizacion.getResolucion());
            cs.setString(10, itemAutorizacion.getExpediente());
            cs.setString(11, itemAutorizacion.getFut());
            cs.setInt(12, itemAutorizacion.getEstado_registro());
            cs.registerOutParameter(2, Types.INTEGER);
            cs.executeUpdate() ;
            itemAutorizacionRetorno = cs.getInt(2);
            util.consolaCliente(""+itemAutorizacionRetorno);
            
            
            // INSERTANDO EL PADRE 
            css = c.prepareCall("{CALL [DI].[SP_MANTENIMIENTO_CUENTA_PERSONA](?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?)}");
            csss = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_CUENTA_PERSONA_AUTORIZACION(?,?,?,?,?,?,?)}");
            for (cuentaPersonaC itemCuentaPersona : listaCuentaPersona) {
                
                
                css.setString(1, itemCuentaPersona.getPersona());
                css.setInt(2, itemCuentaPersona.getItem());
                css.setInt(3, itemCuentaPersona.getInstitucion());
                css.setString(4, itemCuentaPersona.getPeriodo_concepto());
                css.setInt(5, itemCuentaPersona.getTipo_concepto());
                css.setString(6, itemCuentaPersona.getConcepto());
                css.setString(7, itemCuentaPersona.getDescuento());
                css.setString(8, itemCuentaPersona.getDescuento_adi());
                css.setInt(9, itemCuentaPersona.getNum_cuota());
                css.setInt(10, itemCuentaPersona.getNum_parte());
                css.setInt(11, itemCuentaPersona.getTipo_moneda());
                css.setDouble(12, itemCuentaPersona.getMonto_pago());            
                css.setDouble(13, itemCuentaPersona.getMonto_parte());            
                css.setDouble(14, itemCuentaPersona.getMonto_descuento());
                css.setDouble(15, itemCuentaPersona.getMonto_descuento_adi());
                css.setDouble(16, itemCuentaPersona.getMonto_interes());
                css.setDouble(17, itemCuentaPersona.getMonto_total());
                css.setString(18, util.convertDate(itemCuentaPersona.getFecha_vencimiento()));
                css.setString(19, itemCuentaPersona.getObservacion());
                css.setString(20, itemCuentaPersona.getPeriodo());
                css.setString(21, itemCuentaPersona.getAlumno());
                css.setString(22, itemCuentaPersona.getCarrera());
                css.setString(23, itemCuentaPersona.getSeccion());
                css.setString(24, itemCuentaPersona.getGrupo());
                css.setString(25, itemCuentaPersona.getCategoria());
                css.setInt(26, itemCuentaPersona.getTipo_documento());
                css.setString(27, itemCuentaPersona.getNum_comprobante());
                css.setInt(28, itemCuentaPersona.getEstadoRegistro());
                css.registerOutParameter(2, Types.INTEGER);
                css.executeUpdate() ;
                itemCuentaPersonaRetorno = css.getInt(2);
                util.consolaCliente(""+itemCuentaPersonaRetorno);
            
                
                csss.setString(1, itemCuentaPersona.getPersona());//persona
                csss.setInt(2, itemAutorizacion.getTipo_autorizacion());//tipo_autorizacion
                csss.setInt(3, itemAutorizacionRetorno);//autorizacion
                csss.setInt(4, itemCuentaPersonaRetorno);//item
                csss.setInt(5, itemCuentaPersona.getItemPadre());//item-Padre
                csss.setString(6, null);//Fecha-Apertura
                csss.setInt(7, 1);//Estado-registro
                csss.executeUpdate() ;
                
            }
           //c.rollback();
            c.commit();            
          // c.setAutoCommit(true);           
            
        } catch (SQLException e) {
            
            util.consolaCliente("erro" + e.getMessage());
             if (c != null) {
                try {
                    deshacerCambios(c);
                    
                } catch (Exception ex) {
                    util.consolaCliente("error de transaccion"+ ex.getMessage());
                    
                }

            }
            
            
            
           
        }finally{
            cerrarCall(cs);
            cerrarConexion(c);
            util.consolaCliente("Se guardo correctamente" );
        }
        return codigo;
    }
     
     
     
     
     public String insertarTransaccion(personaAutorizacionC itemAutorizacion,List<cuentaPersonaC> listaCuentaPersona,cuentaPersonaC padreCuentaPersona)  {
        int itemAutorizacionRetorno;
        int itemCuentaPersonaRetorno;
        int itemCuentaPersonaPadreRetorno;
        Connection c =null;
        c = ConexionWeb();
        CallableStatement cs=null ;
        CallableStatement css =null;
        CallableStatement csss =null;        
        String codigo = "";
        try {
           
            c.setAutoCommit(false);
            cs = c.prepareCall("{CALL DI.SP_MANTENIMENTO_PERSONA_AUTORIZACION(?,?,?,?,?,?,?,?,?,?, ?,?)}");
            cs.setString(1, itemAutorizacion.getPersona());
            cs.setInt(2, itemAutorizacion.getItem());
            cs.setInt(3, itemAutorizacion.getTipo_autorizacion());
            cs.setString(4, util.convertDate(itemAutorizacion.getAutorizacion_fecha()));
            cs.setString(5, itemAutorizacion.getAutorizacion_personal());
            cs.setString(6, itemAutorizacion.getAutorizacion_documento());
            cs.setString(7, itemAutorizacion.getAutorizacion_observacion());
            cs.setString(8, itemAutorizacion.getDocumento());
            cs.setString(9, itemAutorizacion.getResolucion());
            cs.setString(10, itemAutorizacion.getExpediente());
            cs.setString(11, itemAutorizacion.getFut());
            cs.setInt(12, itemAutorizacion.getEstado_registro());
            cs.registerOutParameter(2, Types.INTEGER);
            cs.executeUpdate() ;
            itemAutorizacionRetorno = cs.getInt(2);
            util.consolaCliente(""+itemAutorizacionRetorno);
            
            
            // INSERTANDO EL PADRE 
            css = c.prepareCall("{CALL [DI].[SP_MANTENIMIENTO_CUENTA_PERSONA](?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?)}");
                css.setString(1, padreCuentaPersona.getPersona());
                css.setInt(2, padreCuentaPersona.getItem());
                css.setInt(3, padreCuentaPersona.getInstitucion());
                css.setString(4, padreCuentaPersona.getPeriodo_concepto());
                css.setInt(5, padreCuentaPersona.getTipo_concepto());
                css.setString(6, padreCuentaPersona.getConcepto());
                css.setString(7, padreCuentaPersona.getDescuento());
                css.setString(8, padreCuentaPersona.getDescuento_adi());
                css.setInt(9, padreCuentaPersona.getNum_cuota());
                css.setInt(10, padreCuentaPersona.getNum_parte());
                css.setInt(11, padreCuentaPersona.getTipo_moneda());
                css.setDouble(12, padreCuentaPersona.getMonto_pago());
                css.setDouble(13, padreCuentaPersona.getMonto_parte());
                css.setDouble(14, padreCuentaPersona.getMonto_descuento());
                css.setDouble(15, padreCuentaPersona.getMonto_descuento_adi());
                css.setDouble(16, padreCuentaPersona.getMonto_interes());
                css.setDouble(17, padreCuentaPersona.getMonto_total());
                css.setString(18, util.convertDate(padreCuentaPersona.getFecha_vencimiento()));
                css.setString(19, padreCuentaPersona.getObservacion());
                css.setString(20, padreCuentaPersona.getPeriodo());
                css.setString(21, padreCuentaPersona.getAlumno());
                css.setString(22, padreCuentaPersona.getCarrera());
                css.setString(23, padreCuentaPersona.getSeccion());
                css.setString(24, padreCuentaPersona.getGrupo());
                css.setString(25, padreCuentaPersona.getCategoria());
                css.setInt(26, padreCuentaPersona.getTipo_documento());
                css.setString(27, padreCuentaPersona.getNum_comprobante());
                css.setInt(28, padreCuentaPersona.getEstadoRegistro());
                css.registerOutParameter(2, Types.INTEGER);
                css.executeUpdate() ;
                itemCuentaPersonaPadreRetorno = css.getInt(2);
                util.consolaCliente(""+itemCuentaPersonaPadreRetorno);
            
            
            
            //-----------------------------------------------
            
            
            
            
            
            csss = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_CUENTA_PERSONA_AUTORIZACION(?,?,?,?,?,?,?)}");
            for (cuentaPersonaC itemCuentaPersona : listaCuentaPersona) {
                css.setString(1, itemCuentaPersona.getPersona());
                css.setInt(2, itemCuentaPersona.getItem());
                css.setInt(3, itemCuentaPersona.getInstitucion());
                css.setString(4, itemCuentaPersona.getPeriodo_concepto());
                css.setInt(5, itemCuentaPersona.getTipo_concepto());
                css.setString(6, itemCuentaPersona.getConcepto());
                css.setString(7, itemCuentaPersona.getDescuento());
                css.setString(8, itemCuentaPersona.getDescuento_adi());
                css.setInt(9, itemCuentaPersona.getNum_cuota());
                css.setInt(10, itemCuentaPersona.getNum_parte());
                css.setInt(11, itemCuentaPersona.getTipo_moneda());
                css.setDouble(12, itemCuentaPersona.getMonto_pago());
                css.setDouble(13, itemCuentaPersona.getMonto_parte());
                css.setDouble(14, itemCuentaPersona.getMonto_descuento());
                css.setDouble(15, itemCuentaPersona.getMonto_descuento_adi());
                css.setDouble(16, itemCuentaPersona.getMonto_interes());
                css.setDouble(17, itemCuentaPersona.getMonto_total());
                css.setString(18, util.convertDate(itemCuentaPersona.getFecha_vencimiento()));
                css.setString(19, itemCuentaPersona.getObservacion());
                css.setString(20, itemCuentaPersona.getPeriodo());
                css.setString(21, itemCuentaPersona.getAlumno());
                css.setString(22, itemCuentaPersona.getCarrera());
                css.setString(23, itemCuentaPersona.getSeccion());
                css.setString(24, itemCuentaPersona.getGrupo());
                css.setString(25, itemCuentaPersona.getCategoria());
                css.setInt(26, itemCuentaPersona.getTipo_documento());
                css.setString(27, itemCuentaPersona.getNum_comprobante());
                css.setInt(28, itemCuentaPersona.getEstadoRegistro());
                css.registerOutParameter(2, Types.INTEGER);
                css.executeUpdate() ;
                itemCuentaPersonaRetorno = css.getInt(2);
                util.consolaCliente(""+itemCuentaPersonaRetorno);
                
                // ENLAZANDO ITEM CON AUTORIZACION        
                csss.setString(1, itemCuentaPersona.getPersona());//persona
                csss.setInt(2, itemAutorizacion.getTipo_autorizacion());//tipo_autorizacion
                csss.setInt(3, itemAutorizacionRetorno);//autorizacion
                csss.setInt(4, itemCuentaPersonaRetorno);//item
                csss.setInt(5, itemCuentaPersonaPadreRetorno);//item-Padre
                csss.setString(6, null);//Fecha-Apertura
                csss.setInt(7, 1);//Estado-registro
                csss.executeUpdate() ;
            }
            
            c.commit();            
          // c.setAutoCommit(true);           
            
        } catch (SQLException e) {
            
            util.consolaCliente("erro" + e.getMessage());
             if (c != null) {
                try {
                    deshacerCambios(c);
                    
                } catch (Exception ex) {
                    util.consolaCliente( ex.getMessage());
                    
                }

            }
            
            
            
           
        }finally{
            cerrarCall(cs);
            cerrarConexion(c);
        }
        return codigo;
    }
    
    
    
     public List<personaAutorizacionC> mostrarAutorizacion(String personal, int tipo,Date fechaIni,Date fechaFin) {
        List<personaAutorizacionC> lista = new ArrayList<>();
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;
        personaAutorizacionC item;
        try {
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *from SIGU.DAT_PERSONA_AUTORIZACION WHERE  AUT_PERSONAL=? AND TIPO_AUTORIZACION=? AND AUT_FECHA BETWEEN ?  AND ? ORDER BY ITEM DESC");
           
            cs.setString(1, personal);
            cs.setInt(2, tipo);
            cs.setString(3,util.convertDate(fechaIni) );
            cs.setString(4, util.convertDate(fechaFin));
            
            rs = cs.executeQuery();
            while (rs.next()) {
                item = new personaAutorizacionC();
                item.setPersona(rs.getString("PERSONA"));
                item.setItem(rs.getInt("ITEM"));
                item.setTipo_autorizacion(rs.getInt("TIPO_AUTORIZACION"));
                item.setAutorizacion_fecha(rs.getDate("AUT_FECHA"));
                item.setAutorizacion_observacion(rs.getString("AUT_OBSERVACION"));
                item.setDocumento(rs.getString("DOCUMENTO"));
                item.setResolucion(rs.getString("RESOLUCION"));
                item.setEstado_registro(rs.getInt("ESTADO_REGISTRO"));                
                lista.add(item);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return lista;
    }
}
