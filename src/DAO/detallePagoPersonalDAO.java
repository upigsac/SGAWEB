/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.detallePagoPersonal;
import Beans.util;
import static Conexiones.Conexion.ConexionWeb;
import static Conexiones.Conexion.cerrarCall;
import static Conexiones.Conexion.cerrarConexion;
import static Conexiones.Conexion.deshacerCambios;
import ENTIDAD.detallePagoPersonalC;
import ENTIDAD.serieC;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class detallePagoPersonalDAO {
    
    
    
    public boolean insertarPersonal(detallePagoPersonal.pagoPersonal item) {
     
  
        
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_MANTENIMIENTO_PAGO_PERSONAL  (?,?,?)}");            
            cs.setString(1, item.getCodigo());
            cs.setString(2, item.getApellidos().concat(" ").concat(item.getNombres()));                       
            cs.setInt(3, item.getEstadoRegistro());
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());

            //e.printStackTrace();
        }
        return rpta;
    }
    
    
    public boolean insertar(detallePagoPersonalC item) {
        
  
        
        Connection c ;
        CallableStatement cs ;
        boolean rpta = false;

        try {

            c = ConexionWeb();
            cs = c.prepareCall("{CALL  DI.SP_MANTENIMIENTO_DETALLE_PAGO_PERSONAL  (?,?,?,?,?,?,?,?,?,? ,?,?)}");
            cs.setInt(1, item.getCodigo());
            cs.setString(2, item.getPersonal());
            cs.setString(3, item.getMes());
            cs.setString(4, item.getObservacion());
            cs.setDouble(5, item.getMontoTotal());
            cs.setDouble(6, item.getMontoPago());
            cs.setDouble(7, item.getMontoSaldo());            
            cs.setString(8,util.convertDate(item.getFechaPago()) );
            cs.setString(9, item.getTipoPago());
            cs.setString(10, item.getTipoConcepto());
            cs.setString(11, item.getNumeroComprobante());            
            cs.setInt(12, item.getEstadoRegistro());
            rpta = cs.executeUpdate() == 1 ;
            if (rpta) {

                c.commit();
            } else {
                deshacerCambios(c);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( e.getMessage());

            //e.printStackTrace();
        }
        return rpta;
    }
    
    public boolean insertar(detallePagoPersonalC item,serieC itemSerie) {
  
        
        Connection c ;
        CallableStatement cs=null ;
        CallableStatement css=null ;
        boolean rpta = false;
        c = ConexionWeb();
        try { 
            
            
           
            cs = c.prepareCall("{CALL  DI.SP_MANTENIMIENTO_DETALLE_PAGO_PERSONAL  (?,?,?,?,?,?,?,?,?,? ,?,?)}");
            c.setAutoCommit(false);
            cs.setInt(1, item.getCodigo());
            cs.setString(2, item.getPersonal());
            cs.setString(3, item.getMes());
            cs.setString(4, item.getObservacion());
            cs.setDouble(5, item.getMontoTotal());
            cs.setDouble(6, item.getMontoPago());
            cs.setDouble(7, item.getMontoSaldo());            
            cs.setString(8,util.convertDate(item.getFechaPago()) );
            cs.setString(9, item.getTipoPago());
            cs.setString(10, item.getTipoConcepto());
            cs.setString(11, item.getNumeroComprobante());            
            cs.setInt(12, item.getEstadoRegistro());
            cs.executeUpdate() ;
            
            css = c.prepareCall("{CALL DI.SP_MANTENIMIENTO_SERIE  (?,?,?,?,?,?,?,?,?,?, ?,?)}");
            css.setInt(1, itemSerie.getLocal());
            css.setString(2, itemSerie.getModulo());
            css.setString(3, itemSerie.getSerie());
            css.setInt(4, itemSerie.getTipoDocumento());
            css.setString(5, itemSerie.getNumeroSerie());
            css.setInt(6, itemSerie.getSecuencia());
            css.setInt(7, itemSerie.getNumeroInicio());
            css.setInt(8, itemSerie.getNumeroFinal());
            css.setInt(9, itemSerie.getNumeroActual());
            css.setInt(10, itemSerie.getNumeroDigitos());
            css.setInt(11, itemSerie.getImpresion());
            css.setInt(12, itemSerie.getEstadoRegistro());
            css.executeUpdate();
           

            c.commit();
           
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
            cerrarCall(css);
            cerrarConexion(c);
            util.consolaCliente("Se guardo correctamente" );
        }
        return rpta;
    }
    
    public List<detallePagoPersonalC> mostrarDetallePagoPersonal() {
       
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;      
        List<detallePagoPersonalC> lista=new ArrayList<>();
        detallePagoPersonalC item;
        try {
            
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DETALLE_PAGO_PERSONAL");

            
            

            rs = cs.executeQuery();
            while (rs.next()) {
                
                item = new detallePagoPersonalC();
                item.setCodigo(rs.getInt("CODPAGO"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setMes(rs.getString("MES"));
                item.setObservacion(rs.getString("OBSERVACION")); 
                item.setMontoTotal(rs.getDouble("MONTO_TOTAL"));
                item.setMontoPago(rs.getDouble("MONTO_PAGO"));
                item.setMontoSaldo(rs.getDouble("MONTO_SALDO"));
                item.setFechaPago(rs.getDate("FECHA_PAGO"));
                item.setTipoPago(rs.getString("TIPO_PAGO"));
                item.setTipoConcepto(rs.getString("TIPO_CONCEPTO"));
                item.setNumeroComprobante(rs.getString("NUM_COMPROBANTE"));  
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                
                
                lista.add(item);
                
                
        
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error ssss" + e.getMessage());
            //e.printStackTrace();
        }

        return lista;
    }
    
    
    public List<detallePagoPersonalC> mostrarDetallePagoPersonalDiario(Date fecha) {       
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;      
        List<detallePagoPersonalC> lista=new ArrayList<>();
        detallePagoPersonalC item;
        try {
            
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DETALLE_PAGO_PERSONAL WHERE FECHA_PAGO=? ORDER BY NUM_COMPROBANTE DESC");
            cs.setString(1, util.convertDate(fecha));
            rs = cs.executeQuery();
            while (rs.next()) {                
                item = new detallePagoPersonalC();
                item.setCodigo(rs.getInt("CODPAGO"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setMes(rs.getString("MES"));
                item.setObservacion(rs.getString("OBSERVACION")); 
                item.setMontoTotal(rs.getDouble("MONTO_TOTAL"));
                item.setMontoPago(rs.getDouble("MONTO_PAGO"));
                item.setMontoSaldo(rs.getDouble("MONTO_SALDO"));
                item.setFechaPago(rs.getDate("FECHA_PAGO"));
                item.setTipoPago(rs.getString("TIPO_PAGO"));
                item.setTipoConcepto(rs.getString("TIPO_CONCEPTO"));
                item.setNumeroComprobante(rs.getString("NUM_COMPROBANTE"));  
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error ssss" + e.getMessage());
            //e.printStackTrace();
        }

        return lista;
    }
    
    public List<detallePagoPersonalC> mostrarDetallePagoPersonal(String ccpersonal) {
       
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;      
        List<detallePagoPersonalC> lista=new ArrayList<>();
        detallePagoPersonalC item;
        try {
            
            c = ConexionWeb();
            cs = c.prepareCall("SELECT *FROM DETALLE_PAGO_PERSONAL where personal=?");
            cs.setString(1, ccpersonal);
            
            

            rs = cs.executeQuery();
            while (rs.next()) {
                
                item = new detallePagoPersonalC();
                item.setCodigo(rs.getInt("CODPAGO"));
                item.setPersonal(rs.getString("PERSONAL"));
                item.setMes(rs.getString("MES"));
                item.setObservacion(rs.getString("OBSERVACION")); 
                item.setMontoTotal(rs.getDouble("MONTO_TOTAL"));
                item.setMontoPago(rs.getDouble("MONTO_PAGO"));
                item.setMontoSaldo(rs.getDouble("MONTO_SALDO"));
                item.setFechaPago(rs.getDate("FECHA_PAGO"));
                item.setTipoPago(rs.getString("TIPO_PAGO"));
                item.setTipoConcepto(rs.getString("TIPO_CONCEPTO"));
                item.setNumeroComprobante(rs.getString("NUM_COMPROBANTE"));  
                item.setEstadoRegistro(rs.getInt("ESTADO_REGISTRO"));
                
                
                lista.add(item);
                
                
        
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error ssss" + e.getMessage());
            //e.printStackTrace();
        }

        return lista;
    }
    
    
    public detallePagoPersonal.pagoPersonal mostrarPagoPersonal(int index) {       
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;      
        detallePagoPersonal.pagoPersonal item=null;
        try {
            
            c = ConexionWeb();
            cs = c.prepareCall(" SELECT A.*FROM(SELECT ROW_NUMBER() OVER(ORDER BY APELLIDOS_NOMBRES ) AS ITEM, * FROM  dbo.PAGO_PERSONAL  )A WHERE A.ITEM= ?");
            cs.setInt(1, index);
            rs = cs.executeQuery();
            while (rs.next()) {
                
                item = new detallePagoPersonal.pagoPersonal();
                item.setCodigo(rs.getString("PERSONAL"));
                item.setApellidos(rs.getString("APELLIDOS_NOMBRES"));               
        
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error ssss" + e.getMessage());
            //e.printStackTrace();
        }

        return item;
    }
    public detallePagoPersonal.pagoPersonal mostrarPagoPersonalCodigo(String personal) {       
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;      
        detallePagoPersonal.pagoPersonal item=null;
        try {
            
            c = ConexionWeb();
            cs = c.prepareCall(" SELECT  * FROM  dbo.PAGO_PERSONAL  WHERE personal= ?");
            cs.setString(1, personal);
            rs = cs.executeQuery();
            while (rs.next()) {
                
                item = new detallePagoPersonal.pagoPersonal();
                item.setCodigo(rs.getString("PERSONAL"));
                item.setApellidos(rs.getString("APELLIDOS_NOMBRES"));               
        
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error ssss" + e.getMessage());
            //e.printStackTrace();
        }

        return item;
    }
    
    
        public List<detallePagoPersonal.pagoPersonal> mostrarFiltroPagoPersonal(String nombres) {       
        Connection c ;
        CallableStatement cs ;
        ResultSet rs ;   
        List<detallePagoPersonal.pagoPersonal> lista=new ArrayList<>();
        detallePagoPersonal.pagoPersonal item=null;
        try {
            
            c = ConexionWeb();
            cs = c.prepareCall(" SELECT A.*FROM(SELECT ROW_NUMBER() OVER(ORDER BY APELLIDOS_NOMBRES ) AS ITEM, * FROM  dbo.PAGO_PERSONAL  )A\n" +
                               " WHERE A.APELLIDOS_NOMBRES LIKE ?");
            cs.setString(1, nombres);
            rs = cs.executeQuery();
            while (rs.next()) {
                
                item = new detallePagoPersonal.pagoPersonal();
                item.setCodigo(rs.getString("PERSONAL"));
                item.setApellidos(rs.getString("APELLIDOS_NOMBRES"));               
                item.setIndex(rs.getInt("ITEM"));               
                lista.add(item);
            }
            cerrarCall(cs);
            cerrarConexion(c);
        } catch (SQLException e) {
            util.consolaCliente( "error ssss" + e.getMessage());
            //e.printStackTrace();
        }

        return lista;
    }
}
